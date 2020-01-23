package com.samourai.javaserver.web.models;

import org.springframework.ui.Model;

public class DashboardTemplateModel {
  public String pageTitle;
  public String logoTitle;

  public DashboardTemplateModel(String pageTitle, String logoTitle) {
    this.pageTitle = pageTitle;
    this.logoTitle = logoTitle;
  }

  public void apply(Model model) {
    model.addAttribute("dashboardModel", this);
  }
}
