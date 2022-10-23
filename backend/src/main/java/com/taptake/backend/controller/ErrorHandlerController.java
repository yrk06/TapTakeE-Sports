package com.taptake.backend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorHandlerController implements ErrorController {

    @GetMapping
    public String error(HttpServletRequest request, HttpServletResponse resp) {

        if (request.getParameter("error") == null) {
            System.out.println("forward:/error?error=" + resp.getStatus());
            return "redirect:/error?error=" + resp.getStatus();
        } else {
            return "index.html";
        }
    }
}
