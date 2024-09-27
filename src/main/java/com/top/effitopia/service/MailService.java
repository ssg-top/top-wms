package com.top.effitopia.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    /**
     *
     * @param title 이메일 제목
     * @param to 받는 이메일 주소
     * @param templateName 이메일 템플릿명
     * @param values 이메일 템플릿 바인딩 파라미터
     * @throws MessagingException
     */
    public void sendMail(String title, String to, String templateName, Map<String, String> values) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject(title);
        helper.setTo(to);
        Context context = new Context();
        values.forEach(context::setVariable);
        String html = templateEngine.process(templateName, context);
        helper.setText(html, true);

        javaMailSender.send(message);
    }

}
