package com.montani.exam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HomeController {

    @GetMapping("/home")
    public ResponseEntity getDeviceStatus(@PathVariable("deviceId") String deviceId, HttpServletRequest request) {

        return new ResponseEntity(HttpStatus.OK);
    }
}
