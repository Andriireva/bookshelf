package com.areva.bookshelf.layers.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsBookSender {

    private JmsTemplate jmsTemplate; // This class helps to send data to the queues
    private ObjectMapper objectMapper;

    public JmsBookSender(JmsTemplate jmsTemplate,
                         ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendDeleteMessage(Object data) {
        //jmsTemplate.send("notifyQueue", session -> new ActiveMQMessage()); // low level createing message and sending

        try {
            jmsTemplate.convertAndSend("AppDeleteBook", objectMapper.writeValueAsString(data));
        } catch (JsonProcessingException e) {
            System.out.println("There is an issue to convert data to json");
        }
    }
}
