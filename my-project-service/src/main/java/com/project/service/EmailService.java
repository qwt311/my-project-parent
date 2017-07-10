package com.project.service;

/**
 * Created by qiaowentao on 2016/11/3.
 */
public interface EmailService {

    /**
     * 发送订阅电子邮件
     */
    void sendEmail(String email, String subject, String content);

}
