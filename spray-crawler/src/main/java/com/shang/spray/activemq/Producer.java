package com.shang.spray.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * info:
 * Created by shang on 2016/12/2.
 */
@Component
public class Producer {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(SprayEvent event,Destination destination,SprayMessageConverter messageConverter) {
        jmsTemplate.setMessageConverter(messageConverter);
        jmsTemplate.setDefaultDestination(destination);
        jmsTemplate.convertAndSend(event);
    }


}
