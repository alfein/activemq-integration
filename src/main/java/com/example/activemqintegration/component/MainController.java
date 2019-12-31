package com.example.activemqintegration.component;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.activemqintegration.config.ActiveMQConfig.QUEUE_NAME;

@RestController
public class MainController {

    private final JmsTemplate sendTemplate;

    public MainController(JmsTemplate sendTemplate) {
        this.sendTemplate = sendTemplate;
    }

    @PostMapping("/send/{message}")
    public void sendMessage(@PathVariable(name = "message") String message) {
//        Message msg = new Message();
//        msg.setName(message);
        String jsonMessage = "{\"name\":\"" + message + "\"}";
        sendTemplate.convertAndSend(QUEUE_NAME, jsonMessage);
    }
}
