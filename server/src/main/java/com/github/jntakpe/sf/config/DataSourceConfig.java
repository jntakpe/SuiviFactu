package com.github.jntakpe.sf.config;

import com.codahale.metrics.MetricRegistry;
import com.github.jntakpe.sf.config.properties.DatasourceProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

/**
 * @author jntakpe
 */
@Configuration
@EnableJpaAuditing
@EntityScan("com.github.jntakpe.sf.domain")
@EnableJpaRepositories("com.github.jntakpe.sf.repository")
public class DataSourceConfig {

    @Autowired
    private DatasourceProperties datasourceProperties;

    @Autowired
    private MetricRegistry metricRegistry;

    @Bean(destroyMethod = "shutdown")
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.addDataSourceProperty("url", datasourceProperties.getUrl());
        config.setDataSourceClassName(datasourceProperties.getDataSourceClassName());
        config.setUsername(datasourceProperties.getUsername());
        config.setPassword(datasourceProperties.getPassword());
        config.setMetricRegistry(metricRegistry);
        return new HikariDataSource(config);
    }

}


