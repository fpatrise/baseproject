package com.fpatrise.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController
{
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView get()
    {
        return new ModelAndView("index");
    }
}
