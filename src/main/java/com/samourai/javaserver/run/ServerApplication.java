package com.samourai.javaserver.run;

import com.samourai.javaserver.config.ServerConfig;
import java.util.Arrays;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

public abstract class ServerApplication implements ApplicationRunner {
  private static final Logger log = LoggerFactory.getLogger(ServerApplication.class);

  private static final String ARG_DEBUG = "debugserver";

  protected ApplicationArguments args;

  @Autowired Environment env;

  @Autowired protected ApplicationContext applicationContext;

  public static void main(String[] args) {
    SpringApplication.run(ServerApplication.class, args);
  }

  protected abstract void setLoggerDebug();

  protected abstract ServerConfig getServerConfig();

  protected abstract void runServer() throws Exception;

  @Override
  public final void run(ApplicationArguments args) {
    this.args = args;

    if (args.containsOption(ARG_DEBUG)) {
      // enable debug logs
      setLoggerDebug();
    }

    ServerConfig serverConfig = getServerConfig();
    try {
      serverConfig.validate();
    } catch (Exception e) {
      log.error("Invalid server configuration", e);
      exit();
      return;
    }

    log.info("------------ " + serverConfig.getName() + " ------------");
    log.info(
        "Running {} {} on java {}",
        serverConfig.getName(),
        Arrays.toString(args.getSourceArgs()),
        System.getProperty("java.version"));
    for (Map.Entry<String, String> entry : serverConfig.getConfigInfo().entrySet()) {
      log.info("config: " + entry.getKey() + ": " + entry.getValue());
    }
    log.info("profiles: " + Arrays.toString(env.getActiveProfiles()));

    // run
    try {
      runServer();
    } catch (Exception e) {
      log.error("Server startup failed", e);
      exit();
      return;
    }
  }

  protected void exit() {
    final int exitCode = 1;
    SpringApplication.exit(applicationContext, () -> exitCode);
    System.exit(exitCode);
  }
}
