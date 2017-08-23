package com.shang.spray.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

/**
 * info:
 * Created by shang on 2017/8/21.
 */
@Component
public class SpringJobFactory extends SpringBeanJobFactory implements ApplicationContextAware{
    private transient AutowireCapableBeanFactory beanFactory;

    public void setApplicationContext(final ApplicationContext context){
        beanFactory = context.getAutowireCapableBeanFactory();
    }

    @Override
    protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception{
        final Object job = super.createJobInstance(bundle);
        beanFactory.autowireBean(job);
        return job;
    }
}
