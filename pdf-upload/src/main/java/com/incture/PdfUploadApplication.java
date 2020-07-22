package com.incture;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.cloud.config.java.ServiceScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
//@ServiceScan
public class PdfUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdfUploadApplication.class, args);
		System.out.println("PDF-UPLOAD Application.");
	}
	
//	 @Bean
//	    @Primary
//	    @Profile("cloud")
//	    public DataSource dataSource(@Value("${vcap.services.filereader.credentials.url}") final String url,
//	            @Value("${vcap.services.filereader.credentials.user}") final String user,
//	            @Value("${vcap.services.filereader.credentials.password}") final String password) {
//	        return DataSourceBuilder.create().type(HikariDataSource.class)
//	                .driverClassName(com.sap.db.jdbc.Driver.class.getName()).url(url).username(user).password(password)
//	                .build();
//	    }

}
