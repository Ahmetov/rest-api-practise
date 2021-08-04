package com.example.microservicessteps.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;


@RestController
@RequestMapping("/hello")
public class HelloWorldResource {

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public String helloWorld(@RequestHeader(name = "Accept-Language", required = false)
                                     Locale locale) {
        return messageSource
                .getMessage("good.morning.message", null, locale);
    }

    @GetMapping("/inter")
    public String helloWorldInternationalised() {
        return "good morning!";
    }
}
