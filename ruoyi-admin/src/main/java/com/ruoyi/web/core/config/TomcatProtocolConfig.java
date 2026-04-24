package com.ruoyi.web.core.config;

import org.springframework.boot.tomcat.servlet.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Force Tomcat to use NIO2 protocol to avoid selector loopback issues on some Windows environments.
 */
@Configuration
public class TomcatProtocolConfig
{
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatProtocolCustomizer()
    {
        return factory -> factory.setProtocol("org.apache.coyote.http11.Http11Nio2Protocol");
    }
}
