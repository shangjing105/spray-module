package com.shang.spray.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * info:
 * Created by shang on 2016/12/2.
 */
@Component
public class Consumer {

    @JmsListener(destination = "SPRAY_TASK_EVENT_QUEUE")
    public void receiveMessage(String text) {
        System.out.println("消费了: <" + text + ">");
    }
}
