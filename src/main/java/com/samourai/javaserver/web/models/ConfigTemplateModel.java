package com.samourai.javaserver.web.models;

import com.samourai.javaserver.utils.ServerUtils;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.ui.Model;

public class ConfigTemplateModel extends DashboardTemplateModel {
  public Map<String, String> configInfo;
  @Autowired protected ServerProperties serverProperties;

  public ConfigTemplateModel(String pageTitle, String logoTitle, Map<String, String> configInfo) {
    super(pageTitle, logoTitle);
    this.configInfo = configInfo;
  }

  public void apply(Model model) {
    super.apply(model);
    configInfo.put("serverProperties", ServerUtils.getInstance().toJsonString(serverProperties));
    model.addAttribute("configModel", this);
  }
}
