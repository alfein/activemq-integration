package com.example.activemqintegration.component;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.example.activemqintegration.config.ActiveMQConfig.QUEUE_NAME;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class ListenerTests {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Listener target;

    @Test
    public void shouldReceiveMessage() throws InterruptedException {

        String jsonMessage = "{\"name\":\"Alex\"}";
        synchronized (target.getReceiveSync()) {
            jmsTemplate.convertAndSend(QUEUE_NAME, jsonMessage);

            target.getReceiveSync().wait(1000);

            assertEquals(1, target.getMessageCount());
        }
    }
}
