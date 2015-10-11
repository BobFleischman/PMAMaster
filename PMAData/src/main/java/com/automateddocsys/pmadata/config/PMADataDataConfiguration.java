package com.automateddocsys.pmadata.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.automateddocsys.pmadata.bo.PositionTotal;


@Configuration
@EnableJpaRepositories(
	entityManagerFactoryRef="entityManagerFactoryPMA",
	transactionManagerRef="transactionManagerPMA",
value={"com.automateddocsys.pmadata.repository"})
@ComponentScan(basePackages={"com.automateddocsys.pmadata"})
@EnableTransactionManagement
public class PMADataDataConfiguration {

	private static Database DATABASE_TYPE = Database.SQL_SERVER;
	private static String DATABASE_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";	                                         
	//private static String DATABASE_URL = "jdbc:sqlserver://localhost\\sqlexpress;databaseName=PMAWEB";
	private static String DATABASE_URL = "jdbc:sqlserver://PMAWEB;databaseName=PMAWEB";
	private static String DATABASE_PASSWORD = "pm@w3bm@st3r2015";
	private static String DATABASE_USER = "pmawebmaster";
/*	
 	private static Database DatabaseType = Database.MYSQL;
	private static String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
	private static String DATABASE_URL = "jdbc:mysql://localhost/pmamaster?rewriteBatchedStatements=true";
*/	
    @Bean
    public DataSource dataSourcePMA() {
        //EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        DriverManagerDataSource ds = new org.springframework.jdbc.datasource.DriverManagerDataSource();
        ds.setUsername(DATABASE_USER);
        ds.setPassword(DATABASE_PASSWORD);
//        ds.setUrl("jdbc:mysql://localhost/pmamaster?autoReconnect=true&rewriteBatchedStatements=true");
//        ds.setDriverClassName("com.mysql.jdbc.Driver");                   
        ds.setUrl(DATABASE_URL);
        ds.setDriverClassName(DATABASE_DRIVER);
        return ds;
    }

    @Bean
    public DataSource poolDataSourcePMA() {
    	BasicDataSource ds = new BasicDataSource();
        ds.setUsername(DATABASE_USER);
        ds.setPassword(DATABASE_PASSWORD);
//        ds.setUsername("security");
//        ds.setPassword("springsecurity");
//        ds.setUrl("jdbc:mysql://localhost/pmamaster?rewriteBatchedStatements=true");
        //ds.setUrl("jdbc:mysql://192.168.2.53/pmamaster?rewriteBatchedStatements=true");
        ds.setInitialSize(5);
        ds.setTestOnBorrow(true);
        ds.setValidationQuery("select 1");
        ds.setUrl(DATABASE_URL);
        ds.setDriverClassName(DATABASE_DRIVER);
    	return ds;
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPMA() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(DATABASE_TYPE);
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(true);
//        Map<String, Object> m = vendorAdapter.getJpaPropertyMap();
//        m.put("hibernate.format_sql", true);
//        for (String key : m.keySet()) {
//        	System.out.println(key);
//        }

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(PositionTotal.class.getPackage().getName());
        factory.setDataSource(poolDataSourcePMA());
        Properties props = new Properties();
        props.put("hibernate.show_sql", true );
        props.put("show_sql", true );
        props.put("hibernate.format_sql", true);
        props.put("format_sql", true);
        props.put("hibernate.use_sql_comments", true);
        props.put("use_sql_comments", true);
        factory.setJpaProperties(props);        
        factory.afterPropertiesSet();
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManagerPMA() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactoryPMA().getObject());
        return txManager;
    }
}
