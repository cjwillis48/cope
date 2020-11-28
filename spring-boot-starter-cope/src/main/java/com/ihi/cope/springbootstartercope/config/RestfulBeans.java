package com.ihi.cope.springbootstartercope.config;

import com.ihi.cope.springbootstartercope.model.CopeServerConfiguration;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConditionalOnProperty(prefix = "cope", name = "mode", havingValue = "restful")
public class RestfulBeans {
    private final CopeServerConfiguration.RestfulConfiguration restConfigs;

    public RestfulBeans(CopeServerConfiguration copeServerConfiguration) {
        this.restConfigs = copeServerConfiguration.getRestfulConfiguration();
    }


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .rootUri(restConfigs.getServerUrl())
                .build();
    }
}