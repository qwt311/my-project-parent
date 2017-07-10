package com.project.common.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * 邮件发送工具类
 *
 * Created by qiaowentao on 2016/9/29.
 */
public class SendMailUtil {

    // 设置发件人的邮箱信息，邮件服务器地址
    /**
     * 发送邮件的邮箱地址
     */
    public static String mailAddress = null;

    /**
     * 邮箱用户名
     */
    public static String mailCount = null; // @前的邮件地址和用户名要一致

    /**
     * 邮箱密码
     */
    public static String mailPassword = null;

    /**
     * 邮箱服务器
     */
    public static String mailServer = null;

//	static {
//        try {
//        	mailAddress = AppConfigUtil.getStringValue("mailAddress");
//        	mailCount = AppConfigUtil.getStringValue("mailCount");
//        	mailPassword = AppConfigUtil.getStringValue("mailPassword");
//        	mailServer = AppConfigUtil.getStringValue("mailServer");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
    /**
     * 发送简单邮件
     *
     * @param str_to
     *            :收件人地址
     * @param str_title
     *            ：邮件标题
     * @param str_content
     *            ：邮件正文
     */
    public static void sendMail(String str_to, String str_title, String str_content) {
        try {
            // 建立邮件会话
            Properties props = new Properties(); // 用来在一个文件中存储键-值对的，其中键和值是用等号分隔的，
            // 存储发送邮件服务器的信息
            props.put("mail.smtp.host", mailServer);
            // 同时通过验证
            props.put("mail.smtp.auth", "true");
            // 根据属性新建一个邮件会话
            Session s = Session.getInstance(props);
            s.setDebug(true); // 有他会打印一些调试信息。

            // 由邮件会话新建一个消息对象
            MimeMessage message = new MimeMessage(s);

            // 设置邮件
            InternetAddress from = new InternetAddress(mailAddress);
            message.setFrom(from); // 设置发件人的地址

            // 设置收件人,并设置其接收类型为TO
            InternetAddress to = new InternetAddress(str_to);
            message.setRecipient(Message.RecipientType.TO, to);

            // 设置标题
            message.setSubject(str_title);

            // 设置信件内容
            message.setContent(str_content, "text/html;charset=utf-8"); // 发送HTML邮件

            // 设置发信时间
            message.setSentDate(new Date());

            // 存储邮件信息
            message.saveChanges();

            // 发送邮件
            Transport transport = s.getTransport("smtp");
            // 以smtp方式登录邮箱,第一个参数是发送邮件用的邮件服务器SMTP地址,第二个参数为用户名,第三个参数为密码
            transport.connect(mailServer, mailCount, mailPassword);
            // 发送邮件,其中第二个参数是所有已设好的收件人地址
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //SendMail.sendMails("qwt311@126.com".split(";"), "首页A版发布通知", DateUtil.DateToStr("yyyy-MM-dd HH:mm:ss") + "手动发布首页A版");
        //String[] str_to = {"qwt311@126.com"};
        String str_to = "qwt311@126.com";
        String str_title = "图片";
        //String str_content = DateUtil.DateToStr("yyyy-MM-dd HH:mm:ss") + "图片";
        String filePath = "C:/Users/qiaowentao/Desktop/dfdc0607d2540dac7989476c.jpg";
        String str_content = "<img src='"+filePath+"'  width=1000 height=800  border=0  color=gray>";
        String fileName = "这是一张图片";
        //SendMailUtil.sendEmailAttachment(str_to, str_title, str_content, filePath, fileName);
        SendMailUtil.sendMail(str_to,str_title,str_content);
    }

    /**
     * @desc 发送附件邮件
     * @param str_to
     * @param str_title
     * @param str_content
     * @param fileName
     */
    public static void sendEmailAttachment(String[] str_to, String str_title, String str_content, String filePath, String fileName) {
        try {
            try {
                // 建立邮件会话
                Properties props = new Properties(); // 用来在一个文件中存储键-值对的，其中键和值是用等号分隔的，
                // 存储发送邮件服务器的信息
                props.put("mail.smtp.host", mailServer);
                // 同时通过验证
                props.put("mail.smtp.auth", "true");
                // 根据属性新建一个邮件会话
                Session s = Session.getInstance(props);
                s.setDebug(true); // 有他会打印一些调试信息。

                // 由邮件会话新建一个消息对象
                MimeMessage message = new MimeMessage(s);

                // 设置邮件
                InternetAddress from = new InternetAddress(mailAddress);
                message.setFrom(from); // 设置发件人的地址

                // 临时添加，测试群发
                InternetAddress[] internetAddrs = new InternetAddress[str_to.length];
                for (int i = 0; i < str_to.length; i++) {
                    internetAddrs[i] = new InternetAddress(str_to[i]);
                }
                message.setRecipients(Message.RecipientType.TO, internetAddrs);

                // 设置标题
                message.setSubject(str_title);

                // 设置信件内容
                // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
                Multipart multipart = new MimeMultipart();
                BodyPart contentPart = new MimeBodyPart();
                contentPart.setText(str_content);
                multipart.addBodyPart(contentPart);
                // 添加附件
                BodyPart messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(filePath);// 路径和文件名
                // 添加附件的内容
                messageBodyPart.setDataHandler(new DataHandler(source));
                // 添加附件的标题
                // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
                sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
                messageBodyPart.setFileName(MimeUtility.encodeText(fileName));
                //messageBodyPart.setFileName("=?GBK?B?" + enc.encode(fileName.getBytes()) + "?=");
                multipart.addBodyPart(messageBodyPart);

                message.setContent(multipart);

                // 设置发信时间
                message.setSentDate(new Date());

                // 存储邮件信息
                message.saveChanges();

                // 发送邮件
                Transport transport = s.getTransport("smtp");
                // 以smtp方式登录邮箱,第一个参数是发送邮件用的邮件服务器SMTP地址,第二个参数为用户名,第三个参数为密码
                transport.connect(mailServer, mailCount, mailPassword);
                // 发送邮件,其中第二个参数是所有已设好的收件人地址
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 实现邮件的群发
     *
     * @param str_to
     *            ：收件人地址列表
     * @param str_title
     *            ：邮件标题
     * @param str_content
     *            :邮件正文
     */
    public static void sendMails(String[] str_to, String str_title, String str_content) {
        try {
            // 建立邮件会话
            Properties props = new Properties(); // 用来在一个文件中存储键-值对的，其中键和值是用等号分隔的，
            // 存储发送邮件服务器的信息
            props.put("mail.smtp.host", mailServer);
            // 同时通过验证
            props.put("mail.smtp.auth", "true");
            // 根据属性新建一个邮件会话
            Session s = Session.getInstance(props);
            s.setDebug(true); // 有他会打印一些调试信息。

            // 由邮件会话新建一个消息对象
            MimeMessage message = new MimeMessage(s);

            // 设置邮件
            InternetAddress from = new InternetAddress(mailAddress);
            message.setFrom(from); // 设置发件人的地址

            // 临时添加，测试群发
            InternetAddress[] internetAddrs = new InternetAddress[str_to.length];
            for (int i = 0; i < str_to.length; i++) {
                internetAddrs[i] = new InternetAddress(str_to[i]);
            }
            message.setRecipients(Message.RecipientType.TO, internetAddrs);

            // 设置标题
            message.setSubject(str_title);

            // 设置信件内容
            // message.setText(str_content); //发送文本邮件
            message.setContent(str_content, "text/html;charset=utf-8"); // 发送HTML邮件
            // 设置发信时间
            message.setSentDate(new Date());

            // 存储邮件信息
            message.saveChanges();

            // 发送邮件
            Transport transport = s.getTransport("smtp");
            // 以smtp方式登录邮箱,第一个参数是发送邮件用的邮件服务器SMTP地址,第二个参数为用户名,第三个参数为密码
            transport.connect(mailServer, mailCount, mailPassword);
            // 发送邮件,其中第二个参数是所有已设好的收件人地址
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
