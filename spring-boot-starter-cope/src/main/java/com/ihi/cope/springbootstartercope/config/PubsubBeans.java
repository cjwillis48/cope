package com.ihi.cope.springbootstartercope.config;

import com.ihi.cope.springbootstartercope.model.CopeServerConfiguration;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@EnableRabbit
@Configuration
@ConditionalOnProperty(prefix = "cope", name = "mode", havingValue = "pubsub")
public class PubsubBeans {
    private static final String DEFAULT_QUEUE_NAME = "cope.inbound.prescription";
    private final CopeServerConfiguration.PubsubConfiguration pubsubConfigs;

    public PubsubBeans(CopeServerConfiguration copeServerConfiguration) {
        final CopeServerConfiguration.PubsubConfiguration pubsubConfigs = copeServerConfiguration.getPubsubConfiguration();
        if (pubsubConfigs.getQueueName() == null) {
            pubsubConfigs.setQueueName(DEFAULT_QUEUE_NAME);
        }

        this.pubsubConfigs = pubsubConfigs;

    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(pubsubConfigs.getRabbitUrl());
        connectionFactory.setUsername(pubsubConfigs.getUsername());
        connectionFactory.setPassword(pubsubConfigs.getPassword());
        connectionFactory.setVirtualHost(pubsubConfigs.getVirtualHost());
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setRoutingKey(pubsubConfigs.getQueueName());
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}