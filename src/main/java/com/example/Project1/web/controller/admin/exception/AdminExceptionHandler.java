package com.example.Project1.web.controller.admin.exception;

import com.example.Project1.web.controller.admin.ProductController;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(basePackageClasses = ProductController.class)
public class AdminExceptionHandler {
    private static final String ERROR_PATH = "admin/errors/";
    @ExceptionHandler(FileUploadException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleFileUploadException(FileUploadException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ERROR_PATH + "4xx");
        modelAndView.addObject("message",ex.getMessage());
        return modelAndView;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ERROR_PATH + "4xx");
        modelAndView.addObject("message",ex.getMessage());
        return modelAndView;
    }
}
