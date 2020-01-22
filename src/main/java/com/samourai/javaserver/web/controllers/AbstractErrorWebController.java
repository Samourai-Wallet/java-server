package com.samourai.javaserver.web.controllers;

import com.samourai.javaserver.web.models.ErrorTemplateModel;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.ui.Model;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

public abstract class AbstractErrorWebController
    implements org.springframework.boot.web.servlet.error.ErrorController {

  @Autowired private ErrorAttributes errorAttributes;

  public ModelAndView errorHtml(
      WebRequest webRequest,
      HttpServletResponse response,
      Model model,
      ErrorTemplateModel templateModel) {
    templateModel.errorMessage = getErrorMessage(webRequest, response.getStatus());
    templateModel.apply(model);
    return new ModelAndView("decorators/error.html");
  }

  private String getErrorMessage(WebRequest webRequest, int status) {
    Throwable cause = errorAttributes.getError(webRequest);
    return (cause != null ? cause.getMessage() : String.valueOf(status));
  }
}
