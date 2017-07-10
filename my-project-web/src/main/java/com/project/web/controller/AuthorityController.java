package com.project.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by qiaowentao on 2016/11/7.
 */
@Controller
public class AuthorityController {


    @RequestMapping(value="/403",method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView noAuthority(HttpServletRequest request, HttpServletResponse response, ModelAndView model){
        model.setViewName("/error/unauthorized");
        return model;
    }

}
