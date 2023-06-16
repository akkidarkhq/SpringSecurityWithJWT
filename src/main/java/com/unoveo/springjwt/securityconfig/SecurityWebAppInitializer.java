package com.unoveo.springjwt.securityconfig;

import com.unoveo.springjwt.WebSecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@ComponentScan
public class SecurityWebAppInitializer  extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebAppInitializer(){
        super(WebSecurityConfig.class);
    }
    @Override
    protected boolean enableHttpSessionEventPublisher() {
        return true;
    }
}
