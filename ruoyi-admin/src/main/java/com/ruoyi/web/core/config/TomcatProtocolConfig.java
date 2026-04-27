package com.ruoyi.web.core.config;

import org.springframework.boot.tomcat.servlet.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Tomcat 全局配置
 * 配置 Tomcat 连接器以允许特殊字符,解决 "Invalid chunk" 错误
 */
@Configuration
public class TomcatProtocolConfig {

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatCustomizer() {
        return factory -> {
            factory.setProtocol("org.apache.coyote.http11.Http11Nio2Protocol");

            factory.addConnectorCustomizers(connector -> {
                // 允许在 URL 路径中使用特殊字符
                connector.setProperty("relaxedPathChars", "\"<>[\\]^`{|}");
                // 允许在查询参数中使用特殊字符(包括 =)
                connector.setProperty("relaxedQueryChars", "\"<>[\\]^`{|}=");
            });
        };
    }
}
