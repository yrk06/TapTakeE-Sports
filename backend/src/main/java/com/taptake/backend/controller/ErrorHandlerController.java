package com.taptake.backend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taptake.backend.service.Ansi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/error")
public class ErrorHandlerController implements ErrorController {

    Logger logger = LoggerFactory.getLogger(ErrorHandlerController.class);

    @GetMapping
    public ResponseEntity<Object> getError(HttpServletRequest request, HttpServletResponse resp) {

        logger.error(String.format("%s %d", Ansi.Red.colorize("[ERROR]"), resp.getStatus()));
        return ResponseEntity.status(resp.getStatus()).build();
    }

    @PostMapping
    public ResponseEntity<Object> postError(HttpServletRequest request, HttpServletResponse resp) {

        logger.error(String.format("%s %d", Ansi.Red.colorize("[ERROR]"), resp.getStatus()));
        return ResponseEntity.status(resp.getStatus()).build();
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteError(HttpServletRequest request, HttpServletResponse resp) {

        logger.error(String.format("%s %d", Ansi.Red.colorize("[ERROR]"), resp.getStatus()));
        return ResponseEntity.status(resp.getStatus()).build();
    }

    @PutMapping
    public ResponseEntity<Object> putError(HttpServletRequest request, HttpServletResponse resp) {

        logger.error(String.format("%s %d", Ansi.Red.colorize("[ERROR]"), resp.getStatus()));
        return ResponseEntity.status(resp.getStatus()).build();
    }
}
