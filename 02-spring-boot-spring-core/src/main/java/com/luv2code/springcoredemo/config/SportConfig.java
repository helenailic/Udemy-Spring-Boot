package com.luv2code.springcoredemo.config;

import com.luv2code.springcoredemo.common.Coach;
import com.luv2code.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {
    //defining a bean method to configure the bean
    @Bean("aquatic")//bean id defaults to the method name unless we make a custom one like this
    public Coach swimCoach() {
        return new SwimCoach();
    }

    //bean id purpose is an alternate to labeling a class @Component for example if you don't have access to the class but want to use it as a bean in your Spring
}
