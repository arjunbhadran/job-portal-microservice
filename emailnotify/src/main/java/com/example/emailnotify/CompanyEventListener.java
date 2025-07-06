package com.example.emailnotify;


import com.example.emailnotify.DTO.CompanyDeletedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class CompanyEventListener {

    @Autowired
    private JavaMailSender mailSender;

    @KafkaListener(topics = "company-events", groupId = "email-group")
    public void handleCompanyDeletedEvent(CompanyDeletedEvent event) {
        // Build and send the email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("abbwic@gmail.com"); // Set recipient(s)
        message.setSubject("Company Deleted");
        message.setText("Company deleted: " + event.getCompanyName() + " (ID: " + event.getCompanyId() + ")");
        mailSender.send(message);
    }

}

