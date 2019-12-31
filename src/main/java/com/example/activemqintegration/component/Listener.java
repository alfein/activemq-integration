package com.example.activemqintegration.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.io.IOException;
import java.util.Map;

@Component
public class Listener {

    private final Object receiveSync = new Object();
    private int messageCount;

    Object getReceiveSync() {
        return  receiveSync;
    }

    int getMessageCount() {
        return messageCount;
    }

    @JmsListener(destination = "inbound.queue")
    public void receiveMessage(final Message jsonMessage) throws JMSException, IOException {
        String messageData = null;

        synchronized (receiveSync) {
            messageCount++;
            receiveSync.notify();
        }
        System.out.println("Received message " + jsonMessage);
        String response = null;

        if(jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)jsonMessage;
            messageData = textMessage.getText();

            Map<String, String> map = new ObjectMapper().readValue(messageData, Map.class);
            System.out.print("Hello " + map.get("name"));
        }
        //return response;
    }
}