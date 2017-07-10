package com.project.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 游戏相关
 *
 * Created by qiaowentao on 2016/10/13.
 */
@RequestMapping(value="/game")
@Controller
public class GameController {

    private final static Logger logger = LoggerFactory.getLogger(GameController.class);

    @RequestMapping(value="/toPinTu",method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView toPinTu(HttpServletRequest request, HttpServletResponse response, ModelAndView model){
        model.setViewName("/youxi/pintu/pinTu");
        return model;
    }

}
