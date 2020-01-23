package com.samourai.javaserver.config;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.util.StringUtils;

public abstract class ServerConfig {
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void validate() throws Exception {
    if (StringUtils.isEmpty(name)) {
      throw new Exception("server.name is empty");
    }
  }

  public Map<String, String> getConfigInfo() {
    Map<String, String> configInfo = new LinkedHashMap<>();
    configInfo.put("name", name);
    return configInfo;
  }
}
