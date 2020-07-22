//package com.incture.configuration;
//
//import javax.sql.DataSource;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.cloud.config.java.AbstractCloudConfig;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import com.zaxxer.hikari.HikariDataSource;
//
//
//
// 
//
///**
// * @author Sushmita.Naresh
// * @version 1.0.0
// * @since 17-Apr-2020
// */
//
// 
//
//@Configuration
////@Profile("cloud")
////@ServiceScan
//public class HibernateConfiguration extends AbstractCloudConfig {
//    Logger logger = LoggerFactory.getLogger(HibernateConfiguration.class);
//
// 
//
//    public HibernateConfiguration() {
//        logger.info("inside hibenate config ");
//        System.out.println("inside hib config ");
//    }
//
// 
//
// 
//
//    @Bean
////    @Primary
//    public DataSource datasource() {
////       CfJdbcEnv cfJdbcEnv = new CfJdbcEnv();
////        CfCredentials cfCredentials = cfJdbcEnv.findCredentialsByTag("hana");
////        System.err.println("cfCredentials.getUri() : " + cfCredentials.getUri() + "cfCredentials.getUsername() : "
////                + cfCredentials.getUsername() + " cfCredentials.getPassword() : " + cfCredentials.getPassword());
//        return DataSourceBuilder.create().type(HikariDataSource.class)
//                .driverClassName(com.sap.db.jdbc.Driver.class.getName()).url("jdbc:sap://localhost:30149")
//                .username("DELFI12345").password("Incture@123456789").build();
//
// 
//
//    }
//
// 
//
//}
