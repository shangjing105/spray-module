package com.shang.spray.activemq;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * info:
 * Created by shang on 2016/12/3.
 */
@Component
public class SprayMessageConverter implements MessageConverter{
    @Override
    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
        Assert.notNull(o);
        TextMessage message = session.createTextMessage();
        if(o instanceof SprayEvent){
            SprayEvent event = (SprayEvent) o;
            message.setStringProperty("event", event.getEvent().toString());
            message.setText(event.getText());
        }
        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        return message;
    }
}
