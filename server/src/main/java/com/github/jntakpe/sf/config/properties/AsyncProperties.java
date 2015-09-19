package com.github.jntakpe.sf.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Paramètres de l'exécuteur asynchrone
 *
 * @author jntakpe
 */
@Component
@ConfigurationProperties("async")
public class AsyncProperties {

    private Integer corePoolSize = 2;

    private Integer maxPoolSize = 50;

    private Integer queueCapacity = 10000;

    public Integer getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(Integer corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public Integer getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(Integer queueCapacity) {
        this.queueCapacity = queueCapacity;
    }
}
