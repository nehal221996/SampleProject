package com.demo.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" } , ignoreResourceNotFound = true)
public class HibernateConfiguration
{
	@Value("${db.driver}")
	private String DB_DRIVER;

	@Value("${db.password}")
	private String DB_PASSWORD;

	@Value("${db.url}")
	private String DB_URL;

	@Value("${db.username}")
	private String DB_USERNAME;

	@Value("${hibernate.dialect}")
	private String HIBERNATE_DIALECT;

	@Value("${hibernate.show_sql}")
	private String HIBERNATE_SHOW_SQL;

	@Value("${hibernate.hbm2ddl.auto}")
	private String HIBERNATE_HBM2DDL_AUTO;
	
	@Value("${hibernate.current_session_context_class}")
	private String HIBERNATE_CURRENT_SESSION;
	
	@Value("${entitymanager.packagesToScan}")
	private String ENTITYMANAGER_PACKAGES_TO_SCAN;

	@Bean
	public LocalSessionFactoryBean sessionFactoryBean()
	{
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		try 
		{
			sessionFactoryBean.setDataSource(dataSource());
			sessionFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
			Properties hibernateProperties = new Properties();
			hibernateProperties.put("hibernate.dialect", HIBERNATE_DIALECT);
			hibernateProperties.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
			hibernateProperties.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
			hibernateProperties.put("hibernate.current_session_context_class", HIBERNATE_CURRENT_SESSION);
			sessionFactoryBean.setHibernateProperties(hibernateProperties);
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
		return sessionFactoryBean;
	}

	@Bean
	public DataSource dataSource() 
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		try 
		{
			dataSource.setDriverClassName(DB_DRIVER);
			dataSource.setUrl(DB_URL);
			dataSource.setUsername(DB_USERNAME);
			dataSource.setPassword(DB_PASSWORD);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return dataSource;
	}

	@Bean
	public HibernateTransactionManager transactionManager() 
	{
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactoryBean().getObject());
		return txManager;
	}
	
	 public void addResourceHandlers(ResourceHandlerRegistry registry)
	 {
	        registry
	          .addResourceHandler("/resources/**")
	          .addResourceLocations("/resources/"); 
	 }
	 
	 /*@Bean
		public PasswordEncoder passwordEncoder(){
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			return encoder;
		}*/
	
//	 @Bean(name = "multipartResolver")
//	 public CommonsMultipartResolver getCommonsMultipartResolver() {
//	     CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//	     multipartResolver.setMaxUploadSize(20971520);   // 20MB
//	     multipartResolver.setMaxInMemorySize(1048576);  // 1MB
//	     System.out.println("multipartResolver");
//	     return multipartResolver;
//	 }

	public String getDB_DRIVER() {
		return DB_DRIVER;
	}

	public void setDB_DRIVER(String dB_DRIVER) {
		DB_DRIVER = dB_DRIVER;
	}

	public String getDB_PASSWORD() {
		return DB_PASSWORD;
	}

	public void setDB_PASSWORD(String dB_PASSWORD) {
		DB_PASSWORD = dB_PASSWORD;
	}

	public String getDB_URL() {
		return DB_URL;
	}

	public void setDB_URL(String dB_URL) {
		DB_URL = dB_URL;
	}

	public String getDB_USERNAME() {
		return DB_USERNAME;
	}

	public void setDB_USERNAME(String dB_USERNAME) {
		DB_USERNAME = dB_USERNAME;
	}

	public String getHIBERNATE_DIALECT() {
		return HIBERNATE_DIALECT;
	}

	public void setHIBERNATE_DIALECT(String hIBERNATE_DIALECT) {
		HIBERNATE_DIALECT = hIBERNATE_DIALECT;
	}

	public String getHIBERNATE_SHOW_SQL() {
		return HIBERNATE_SHOW_SQL;
	}

	public void setHIBERNATE_SHOW_SQL(String hIBERNATE_SHOW_SQL) {
		HIBERNATE_SHOW_SQL = hIBERNATE_SHOW_SQL;
	}

	public String getHIBERNATE_HBM2DDL_AUTO() {
		return HIBERNATE_HBM2DDL_AUTO;
	}

	public void setHIBERNATE_HBM2DDL_AUTO(String hIBERNATE_HBM2DDL_AUTO) {
		HIBERNATE_HBM2DDL_AUTO = hIBERNATE_HBM2DDL_AUTO;
	}

	public String getHIBERNATE_CURRENT_SESSION() {
		return HIBERNATE_CURRENT_SESSION;
	}

	public void setHIBERNATE_CURRENT_SESSION(String hIBERNATE_CURRENT_SESSION) {
		HIBERNATE_CURRENT_SESSION = hIBERNATE_CURRENT_SESSION;
	}

	public String getENTITYMANAGER_PACKAGES_TO_SCAN() {
		return ENTITYMANAGER_PACKAGES_TO_SCAN;
	}

	public void setENTITYMANAGER_PACKAGES_TO_SCAN(String eNTITYMANAGER_PACKAGES_TO_SCAN) {
		ENTITYMANAGER_PACKAGES_TO_SCAN = eNTITYMANAGER_PACKAGES_TO_SCAN;
	}
	
	
}
