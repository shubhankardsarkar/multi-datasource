package com.example.multidatasource.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:application.properties"})
public class MysqlDatabaseConfiguration {
	
	@Bean(name="mysqlDataSource")
    public DataSource mysqlDataSource(Environment env) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername(env.getProperty("mysql.spring.datasource.username"));
        dataSource.setPassword(env.getProperty("mysql.spring.datasource.password"));
        dataSource.setUrl(env.getProperty("mysql.spring.datasource.url"));
        return dataSource;
    }
	
	@Bean(name = "mysqlJdbcTemplate")
	public JdbcTemplate jdbcTemplate(@Qualifier("mysqlDataSource") DataSource dsMysql) {
		return new JdbcTemplate(dsMysql);	
	}
	
	@Bean(name = "mysqlEntityManagerFactory")
    LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(EntityManagerFactoryBuilder builder, Environment env) {
		Map<String,Object> properties = new HashMap<>(1);
//		properties.put("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
		properties.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
		
        return builder.dataSource(mysqlDataSource(env))
        		.properties(properties)
                .packages("com.example.multidatasource")
                .persistenceUnit("mysql")
                .build();
    }
	
	@Bean(name = "mysqlTransactionManager")
    public PlatformTransactionManager mysqlTransactionManager(
            final @Qualifier("mysqlEntityManagerFactory") LocalContainerEntityManagerFactoryBean mysqlEntityManager) {
		return new JpaTransactionManager(mysqlEntityManager.getObject());
    }
}
