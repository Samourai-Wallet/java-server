package com.samourai.javaserver.web.controllers;

import com.samourai.javaserver.web.models.LoginTemplateModel;
import org.springframework.ui.Model;

public class AbstractLoginWebController {

  public String login(Model model, LoginTemplateModel templateModel) {
    templateModel.apply(model);
    return "decorators/login.html";
  }
}
