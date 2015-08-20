package com.github.jntakpe.sf.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jntakpe
 */
@Configuration
public class WebConfig {

    @Bean
    public Module hibernate4Module() {
        return new Hibernate4Module();
    }
}
