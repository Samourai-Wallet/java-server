package com.samourai.javaserver.config;

import com.samourai.javaserver.utils.ServerUtils;
import java.lang.invoke.MethodHandles;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

public class ServerServicesConfig {
  private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  public static final String[] STATICS =
      new String[] {"/css/**.css", "/img/**", "/webjars/bootstrap/**", "/webjars/jquery/**"};

  public ServerServicesConfig() {}

  @Bean
  LayoutDialect layoutDialect() {
    // enable layout:decorate for thymeleaf
    return new LayoutDialect();
  }

  @Bean
  ServerUtils serverUtils() {
    return ServerUtils.getInstance();
  }
}
