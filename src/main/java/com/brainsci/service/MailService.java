package com.brainsci.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    //邮件发件人
    @Value("${mail.fromMail.addr}")
    private String from;

    /**
     * @author zeng
     * @param to 收件人
     * @param subject 主题
     * @param verifyCode 验证码
     */
    public void sendMail(String to, String subject, String verifyCode) {
        //创建邮件正文
        String emailContent = "您的验证码为"+verifyCode;
        //将模块引擎内容解析成html字符串
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        // 主题
        message.setSubject(subject);
        message.setText(emailContent);
        try {
            mailSender.send(message);
            //logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            //logger.error("发送简单邮件时发生异常！", e);
        }
    }
}
