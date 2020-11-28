package com.ihi.cope.springbootstartercope;

import com.ihi.cope.springbootstartercope.model.CopeServerConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        CopeServerConfiguration.class,
        CopeServerConfiguration.RestfulConfiguration.class,
        CopeServerConfiguration.PubsubConfiguration.class
})
@ComponentScan("com.ihi.cope.springbootstartercope")
public class CopeAutoConfiguration {
}
