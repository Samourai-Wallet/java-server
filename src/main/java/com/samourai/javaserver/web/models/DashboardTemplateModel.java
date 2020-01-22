package com.samourai.javaserver.web.models;

import org.springframework.ui.Model;

public class DashboardTemplateModel {
  public String pageTitle;

  public DashboardTemplateModel(String pageTitle) {
    this.pageTitle = pageTitle;
  }

  public void apply(Model model) {
    model.addAttribute("dashboardModel", this);
  }
}
