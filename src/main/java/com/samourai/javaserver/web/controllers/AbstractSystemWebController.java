package com.samourai.javaserver.web.controllers;

import com.samourai.javaserver.web.models.SystemTemplateModel;
import org.springframework.ui.Model;

public class AbstractSystemWebController {

  public String system(Model model, SystemTemplateModel templateModel) {
    templateModel.apply(model);
    return "decorators/system.html";
  }
}
