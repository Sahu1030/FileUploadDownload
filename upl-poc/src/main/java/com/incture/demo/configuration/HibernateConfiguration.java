//package com.incture.demo.configuration;
//
//
//import java.util.Properties;
//
//import javax.sql.DataSource;
//
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//

// @EmbeddedId
// @IdClass
//@Configuration
//@EnableTransactionManagement
//@ComponentScan({ "com.incture.demo.configuration" })
//@PropertySource(value = { "classpath:application.properties" })
//public class HibernateConfiguration {
//
//	@Autowired
//	private Environment environment;
//
//	@Bean
//	public LocalSessionFactoryBean sessionFactory() {
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//		sessionFactory.setDataSource(dataSource());
//		sessionFactory.setPackagesToScan(new String[] { "com.incture.demo" });
//		sessionFactory.setHibernateProperties(hibernateProperties());
//		return sessionFactory;
//	}
//
//	@Bean
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(environment.getRequiredProperty("spring.datasource.driver-class-name"));
//		dataSource.setUrl(environment.getRequiredProperty("spring.datasource.url"));
//		dataSource.setUsername(environment.getRequiredProperty("spring.datasource.username"));
//		dataSource.setPassword(environment.getRequiredProperty("spring.datasource.password"));
//		return dataSource;
//	}
//
//	private Properties hibernateProperties() {
//		Properties properties = new Properties();
//		properties.put("hibernate.dialect", environment.getRequiredProperty("spring.jpa.properties.hibernate.dialect"));
//		properties.put("hibernate.show_sql", environment.getRequiredProperty("spring.jpa.hibernate.show_sql"));
//		properties.put("hibernate.format_sql", environment.getRequiredProperty("spring.jpa.hibernate.format_sql = false"));
//		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
//		return properties;
//	}
//
//	@Bean
//	@Autowired
//	public HibernateTransactionManager transactionManager(SessionFactory s) {
//		HibernateTransactionManager txManager = new HibernateTransactionManager();
//		txManager.setSessionFactory(s);
//		return txManager;
//	}
//
//	
//
//}
//
