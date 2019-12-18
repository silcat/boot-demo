package com.boot.demo.config.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.from}")
    private String mailFrom;
    @Value("${spring.mail.to}")
    private String toString;
    @Value("${spring.mail.cc}")
    private String ccString;
    @Value("${spring.profiles.active}")
    private String active;

    public void sendEmailWarning(String subject, String content) {
        SimpleMailMessage smm=new SimpleMailMessage();
        //由谁来发送邮件
        smm.setFrom(mailFrom);
        //邮件主题
        if (StringUtils.equals("release", active)) {
            subject = "[线上邮件]" + subject;
        } else {
            subject = "[测试邮件]" + subject;
        }
        smm.setSubject(subject);
        //邮件内容
        smm.setText(content);
        //接受邮件
        String[] to = toString.split(",");
        smm.setTo(to);
        String[] cc = ccString.split(",");
        smm.setCc(cc);
        try {
            mailSender.send(smm);
        } catch (Exception e) {
            log.error("send mail failed!", e);
        }
    }
}
