package com.samourai.javaserver.config;

import java.util.Map;

public interface ServerConfig {

  void validate() throws Exception;

  Map<String, String> getConfigInfo();
}
