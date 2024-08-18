package com.idat.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.idat.service.CommonService;
import com.idat.service.impl.CommonServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public CommonService commonService() {
        return new CommonServiceImpl();
    }
}
