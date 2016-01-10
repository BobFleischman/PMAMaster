package com.automateddocsys.pma.webdata.config;

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

import com.automateddocsys.pma.webdata.bo.WebClient;


@Configuration
@EnableJpaRepositories(entityManagerFactoryRef="entityManagerFactoryPMAMaster",
transactionManagerRef="transactionManagerPMAMaster",
value={"com.automateddocsys.pma.repository"})
@ComponentScan(basePackages={"com.automateddocsys.pma"})
@EnableTransactionManagement
public class PMAWebDataConfiguration {

	private static Database DATABASE_TYPE = Database.SQL_SERVER;
	private static String DATABASE_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	//private static String DATABASE_URL_DEV = "jdbc:sqlserver://localhost\\sqlexpress;databaseName=pmamaster";
	private static String DATABASE_URL_DEV = "jdbc:sqlserver://192.168.2.53\\sqlexpress;databaseName=pmamaster";
	private static String DATABASE_URL_PMA = "jdbc:sqlserver://PMAWEB;databaseName=PMAMASTER";
	private static String DATABASE_PASSWORD = "pm@w3bm@st3r2015";
	private static String DATABASE_USER = "pmawebmaster";
	private static String DATABASE_URL = DATABASE_URL_PMA;
    static {
    	DATABASE_URL = "BOB-WIN8".equalsIgnoreCase(System.getenv("COMPUTERNAME")) ||
    			"BOB-THINK".equalsIgnoreCase(System.getenv("COMPUTERNAME")) ||
    			"ROOT".equalsIgnoreCase(System.getenv("USERNAME")) 
    			? DATABASE_URL_DEV : DATABASE_URL_PMA;
    }

/*	
 	private static Database DatabaseType = Database.MYSQL;
	private static String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
	private static String DATABASE_URL = "jdbc:mysql://localhost/pmamaster?rewriteBatchedStatements=true";
*/	
    @Bean
    public DataSource dataSourcePMAMaster() {
        //EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        DriverManagerDataSource ds = new org.springframework.jdbc.datasource.DriverManagerDataSource();
        ds.setUsername(DATABASE_USER);
        ds.setPassword(DATABASE_PASSWORD);
//        ds.setUsername("security");
//        ds.setPassword("springsecurity");
        ds.setUrl(DATABASE_URL);
        ds.setDriverClassName(DATABASE_DRIVER);
        return ds;
    }

    @Bean
    public DataSource poolDataSourcePMAMaster() {
    	BasicDataSource ds = new BasicDataSource();
        ds.setUsername(DATABASE_USER);
        ds.setPassword(DATABASE_PASSWORD);
//        ds.setUsername("security");
//        ds.setPassword("springsecurity");
        ds.setInitialSize(5);
        ds.setTestOnBorrow(true);
        ds.setValidationQuery("select 1");
        ds.setUrl(DATABASE_URL);
        ds.setDriverClassName(DATABASE_DRIVER);
    	return ds;
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPMAMaster() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(DATABASE_TYPE);
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(WebClient.class.getPackage().getName());
        factory.setDataSource(dataSourcePMAMaster());
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
    public PlatformTransactionManager transactionManagerPMAMaster() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactoryPMAMaster().getObject());
        return txManager;
    }
}
