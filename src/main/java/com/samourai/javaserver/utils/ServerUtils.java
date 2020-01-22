package com.samourai.javaserver.utils;

import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

public class ServerUtils {
  private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  public static final String PROFILE_TEST = "test";
  public static final String PROFILE_DEFAULT = "default";

  private static ServerUtils instance;

  public static ServerUtils getInstance() {
    if (instance == null) {
      instance = new ServerUtils();
    }
    return instance;
  }

  public void setLoggerDebug(String logger) {
    LogbackUtils.setLogLevel(logger, Level.DEBUG.toString());
  }

  public String obfuscateString(String str, int offset) {
    if (str == null || str.length() <= offset) {
      return str;
    }
    return str.substring(0, offset) + "***" + str.substring(str.length() - offset, str.length());
  }
}
