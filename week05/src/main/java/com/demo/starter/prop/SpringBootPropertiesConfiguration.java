package com.demo.starter.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

/**
 * Spring boot properties configuration.
 */
@ConfigurationProperties(prefix = "demo.lyp")
@Getter
@Setter
public final class SpringBootPropertiesConfiguration {

    private Properties props = new Properties();

}
