package com.samourai.javaserver.web.controllers;

import com.samourai.javaserver.web.models.ConfigTemplateModel;
import org.springframework.ui.Model;

public class AbstractConfigWebController {

  public String config(Model model, ConfigTemplateModel templateModel) {
    templateModel.apply(model);
    return "decorators/config.html";
  }
}
