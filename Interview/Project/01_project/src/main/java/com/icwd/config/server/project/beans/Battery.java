package com.icwd.config.server.project.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;


public class Battery implements InitializingBean , DisposableBean {
    public Battery() {
        System.out.println("Battery is created");
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("Battery is closed");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Battery is initialized");
    }
}
