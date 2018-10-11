package com.leon.ziru.mail;

import com.leon.ziru.dao.MissionDao;
import com.leon.ziru.log.ZRLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 邮件发送
 */
@Component
public class Mailer {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MissionDao missionDao;

    @Value("${spring.mail.from}")
    private String from;


    /**
     * 发送邮件
     *
     * @param subject 主题
     * @param text    正文
     */
    @Async
    public void sendSimpleMail(String subject, String text, String to, Integer missionId) {
        SimpleMailMessage message;
        try {
            message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
            missionDao.sendEmailSuccess(missionId);
        } catch (MailException e) {
            ZRLogger.errorLog.error("sendMailError", e);
        }
    }

    /**
     * 发送邮件
     *
     * @param subject 主题
     * @param text    正文
     */
    @Async
    public void sendHtmlMail(String subject, String text, String to, Integer missionId) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            mailSender.send(message);
            missionDao.sendEmailSuccess(missionId);
        } catch (MailException | MessagingException e) {
            ZRLogger.errorLog.error("sendMailError", e);
        }
    }

}
