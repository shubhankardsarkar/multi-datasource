package com.example.multidatasource.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })
public class PostgresDatabaseConfiguration {


	@Primary
	@Bean
	public DataSource postgresDataSource(Environment env) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		return dataSource;
	}

	@Primary
	@Bean(name = "postgresEntityManagerFactory")
	LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, Environment env) {
		Map<String,Object> properties = new HashMap<>(1);
		properties.put("hibernate.dialect","org.hibernate.dialect.PostgreSQL9Dialect");
        return builder.dataSource(postgresDataSource(env))
        		.properties(properties)
				.packages("com.example.multidatasource")
				.persistenceUnit("postgres")
				.build();
	}

	@Primary
	@Bean
	public PlatformTransactionManager postgresTransactionManager(
			final @Qualifier("postgresEntityManagerFactory") LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory) {
		return new JpaTransactionManager(postgresEntityManagerFactory.getObject());
	}
}
