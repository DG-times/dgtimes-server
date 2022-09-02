package com.v2.dgtimes.config.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.v2.dgtimes.layer.*.repository.replica",
        entityManagerFactoryRef = "replicaEntityManagerFactory",
        transactionManagerRef = "replicaTransactionManager"
)
public class ReplicaDatabaseConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.replica-datasource")
    public DataSourceProperties replicaDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource replicaDataSource(@Qualifier("replicaDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "replicaEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean replicaEntityManagerFactory(@Qualifier("replicaDataSource") DataSource sqlServerDataSource, EntityManagerFactoryBuilder builder) {

        return builder.dataSource(sqlServerDataSource)
                .packages("com.v2.dgtimes.layer.*.model")
                .persistenceUnit("replica")
                .build();
    }

    @Bean
    public PlatformTransactionManager replicaTransactionManager(@Qualifier("replicaEntityManagerFactory")
                                                                EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }
}

