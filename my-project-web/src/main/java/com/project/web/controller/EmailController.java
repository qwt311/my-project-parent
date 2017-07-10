package com.project.web.controller;

import com.project.common.util.ResponsesDTO;
import com.project.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 邮箱相关
 *
 * Created by qiaowentao on 2016/10/14.
 */
@Controller
@RequestMapping(value="/email")
public class EmailController {

    private final static Logger logger = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private EmailService emailService;

    @ResponseBody
    @RequestMapping(value="/subscribeEmail",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponsesDTO sendEmail(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
                                  @RequestParam(value="email",required = true) String email){
        ResponsesDTO responsesDTO = new ResponsesDTO();
        try{
            String subject = "订阅成功";
            String content = "恭喜您成功订阅我的网站，网站上线后我会第一时间通知您的";
            emailService.sendEmail(email,subject,content);
            responsesDTO.setCode(1);
        }catch (Exception e){
            logger.error(e.getMessage());
            responsesDTO.setCode(2);
        }

        return responsesDTO;
    }

}
