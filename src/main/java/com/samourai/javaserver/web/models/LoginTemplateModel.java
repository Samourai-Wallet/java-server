package com.samourai.javaserver.web.models;

import org.springframework.ui.Model;

public class LoginTemplateModel {
  public String pageTitle;
  public String processEndpoint;
  public String welcomeTitle;

  public LoginTemplateModel(String pageTitle, String processEndpoint, String welcomeTitle) {
    this.pageTitle = pageTitle;
    this.processEndpoint = processEndpoint;
    this.welcomeTitle = welcomeTitle;
  }

  public void apply(Model model) {
    model.addAttribute("loginModel", this);
  }
}
