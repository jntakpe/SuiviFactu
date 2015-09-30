package com.github.jntakpe.sf.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration web
 *
 * @author jntakpe
 */
@Configuration
public class WebConfig {

    @Bean
    public Module hibernate4Module() {
        Hibernate4Module hibernate4Module = new Hibernate4Module();
        hibernate4Module.disable(Hibernate4Module.Feature.USE_TRANSIENT_ANNOTATION);
        return hibernate4Module;
    }

    @Bean
    public Module javaTimeModule() {
        return new JavaTimeModule();
    }
}
