package com.automateddocsys.pma.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.automateddocsys.pma.webdata.config.PMAWebDataConfiguration;
import com.automateddocsys.pmadata.config.PMADataDataConfiguration;

@Configuration
@Import({WebConfig.class, SecurityConfig.class, PMAWebDataConfiguration.class, PMADataDataConfiguration.class })
@ImportResource({
  "classpath:META-INF/spring/pmaweb-app-context.xml",
  "classpath:META-INF/spring/web-context.xml",
})
public class ServletConfig {
	
//	private static String DATABASE_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";	                                         
//	private static String DATABASE_URL_PMAWEB = "jdbc:sqlserver://localhost\\sqlexpress;databaseName=PMAWEB";
//	private static String DATABASE_URL_PMAMASTER = "jdbc:sqlserver://localhost\\sqlexpress;databaseName=PMAMASTER";
//	//private static String DATABASE_URL = "jdbc:sqlserver://PMAWEB;databaseName=PMAWEB";
//	private static String DATABASE_PASSWORD = "pm@w3bm@st3r2015";
//	private static String DATABASE_USER = "pmawebmaster";
	
//	<!-- freemarker config -->
//	<beans:bean id="freemarkerConfig"
//		class="org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer">
//		<beans:property name="templateLoaderPath" value="/WEB-INF/views/ftl" />
//	</beans:bean>


	@Bean
	public FreeMarkerViewResolver viewResolver() {
		FreeMarkerViewResolver result = new org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver();
		result.setCache(true);
		result.setPrefix("");;
		result.setSuffix(".ftl");
		result.setExposeSpringMacroHelpers(true);
		return result;
	}
	
	@Bean
	public FreeMarkerConfigurer freemarkerConfig() {
		FreeMarkerConfigurer result = new org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer();
		result.setTemplateLoaderPath("/WEB-INF/views/ftl/");
		return result;
	}
	
/*    @Bean
    public DataSource poolDataSourcePMA() {
    	BasicDataSource ds = new BasicDataSource();
        ds.setUsername(DATABASE_USER);
        ds.setPassword(DATABASE_PASSWORD);
//        ds.setUsername("security");
//        ds.setPassword("springsecurity");
//        ds.setUrl("jdbc:mysql://localhost/pmamaster?rewriteBatchedStatements=true");
        //ds.setUrl("jdbc:mysql://192.168.2.53/pmamaster?rewriteBatchedStatements=true");
        ds.setInitialSize(5);
        ds.setMaxActive(50);
        ds.setTestOnBorrow(true);
        ds.setValidationQuery("select 1");
        ds.setUrl(DATABASE_URL_PMAWEB);
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
        ds.setUrl(DATABASE_URL_PMAMASTER);
        ds.setDriverClassName(DATABASE_DRIVER);
    	return ds;
    }
*/
}
