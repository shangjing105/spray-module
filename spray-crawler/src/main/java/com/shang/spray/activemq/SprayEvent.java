package com.shang.spray.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Component;

/**
 * info:jms activemq的事件
 * Created by shang on 2016/12/3.
 */
@Component
public class SprayEvent {

    /**
     * 消息类型:队列模式
     */
    public static final ActiveMQQueue SPRAY_TASK_EVENT_QUEUE=new ActiveMQQueue("SPRAY_TASK_EVENT_QUEUE");

    private Event event;

    private String text;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static enum Event {
        EVENT_TACK_SUCCESS; //定时任务执行成功
    }
}
