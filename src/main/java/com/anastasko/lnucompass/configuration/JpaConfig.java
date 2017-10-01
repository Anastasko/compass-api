package com.anastasko.lnucompass.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import com.p6spy.engine.spy.P6DataSource;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.PostgreSQL9Dialect;
import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class JpaConfig {

	private static final Logger logger = LoggerFactory.getLogger(JpaConfig.class);

//	public final static JpaConfigKind CONFIG_KIND = JpaConfigKind.EMBEDDED;
	public final static JpaConfigKind CONFIG_KIND = JpaConfigKind.POSTGRESQL;
	
	public enum JpaConfigKind {
		POSTGRESQL,
		EMBEDDED
	}
	
	@Autowired
	private Environment environment;
	
	@Bean
	@Primary
	public PGSimpleDataSource pgDataSource() {
		PGSimpleDataSource dataSource = new PGSimpleDataSource();
		dataSource.setServerName(environment.getProperty("postgresql.server"));
		dataSource.setPortNumber(environment.getProperty("postgresql.port", Integer.class));
		dataSource.setDatabaseName(environment.getProperty("postgresql.database"));
		dataSource.setUser(environment.getProperty("postgresql.user"));
		dataSource.setPassword(environment.getProperty("postgresql.password"));
		return dataSource;
	}

	@Bean
	public DataSource dataSource() {
		switch(CONFIG_KIND) {
		case EMBEDDED:
			return new P6DataSource(embeddedDatabase());
		case POSTGRESQL:
			return new P6DataSource(hikariDataSource());
		}
		return null;
	}
	
	@Bean
	public HikariDataSource hikariDataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDataSource(pgDataSource());
		return dataSource;
	}
	
	@Bean(destroyMethod="shutdown")
	public EmbeddedDatabase embeddedDatabase() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build(); 
	}

	@Bean
	public JpaVendorAdapter vendorAdapter() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		return vendorAdapter;
	}
	
	@Bean
	@Primary
	public EntityManagerFactory entityManagerFactory() {
		logger.info("Creating main EM factory...");
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter());
		factory.setPackagesToScan(environment.getRequiredProperty("model.domain.api.package"),
				environment.getRequiredProperty("model.domain.package"));
		factory.setDataSource(dataSource());
		Properties properties = new Properties();
		properties.put(environment.getProperty("jpa.dialect"), getJpaDialectClass().getName());
		factory.setJpaProperties(properties);
		factory.afterPropertiesSet();
		return factory.getObject();
	}
	
	@Bean
	@Primary
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		txManager.afterPropertiesSet();
		return txManager;
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}

	private Class<?> getJpaDialectClass() {
		switch(CONFIG_KIND) {
		case EMBEDDED:
			return H2Dialect.class;
		case POSTGRESQL:
			return PostgreSQL9Dialect.class; 
		}
		return null;
	}
}
