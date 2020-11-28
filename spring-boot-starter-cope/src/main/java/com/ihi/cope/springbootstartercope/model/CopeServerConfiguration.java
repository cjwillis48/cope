package com.ihi.cope.springbootstartercope.model;

import com.ihi.cope.domain.Client;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Data
@ConfigurationProperties(prefix = "cope")
public class CopeServerConfiguration {
    private Mode mode;
    private RestfulConfiguration restfulConfiguration;
    private PubsubConfiguration pubsubConfiguration;
    private Client client;

    @PostConstruct
    public void printInitialization() {
      log.info("Cope configuration initialized: {}", this);
    }

    @Data
    @ConfigurationProperties("restful-configuration")
    public static class RestfulConfiguration {
        private String serverUrl;
        private String username;
        private String password;
    }

    @Data
    @ConfigurationProperties("pubsub-configuration")
    public static class PubsubConfiguration {
        private String rabbitUrl;
        private String username;
        private String password;
        private String queueName;
        private String virtualHost;
    }
}
