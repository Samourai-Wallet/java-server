package com.samourai.javaserver.web.models;

import org.springframework.ui.Model;

public class ErrorTemplateModel {
  public String pageTitle;
  public String errorMessage;

  public ErrorTemplateModel(String pageTitle) {
    this.pageTitle = pageTitle;
  }

  public void apply(Model model) {
    model.addAttribute("errorModel", this);
  }
}
