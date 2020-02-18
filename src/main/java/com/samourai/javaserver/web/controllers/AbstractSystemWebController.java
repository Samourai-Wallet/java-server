package com.samourai.javaserver.web.controllers;

import com.samourai.javaserver.web.models.SystemTemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;

public class AbstractSystemWebController {
  @Autowired protected ApplicationContext applicationContext;

  public String system(Model model, SystemTemplateModel templateModel) {
    templateModel.setStartupTime(applicationContext.getStartupDate());
    templateModel.apply(model);
    return "decorators/system.html";
  }
}
