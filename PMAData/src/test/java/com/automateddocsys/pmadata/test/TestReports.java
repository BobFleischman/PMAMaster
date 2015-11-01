package com.automateddocsys.pmadata.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.automateddocsys.pmadata.bo.ReportRenamedFilesForWeb;
import com.automateddocsys.pmadata.config.PMADataDataConfiguration;
import com.automateddocsys.pmadata.repository.ReportsRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={PMADataDataConfiguration.class})
public class TestReports {

	@Autowired
	private ReportsRepository reportsRepository; 

	@Test
	public void testFindAll() {
		List<ReportRenamedFilesForWeb> lst = reportsRepository.findAll();
		for (ReportRenamedFilesForWeb reportRenamedFilesForWeb : lst) {
			System.out.println(reportRenamedFilesForWeb);
		}
	}
	
	@Test	
	public void testEnvironment() {
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n",
                              envName,
                              env.get(envName));
        }
        
        System.out.println(env.get("COMPUTERNAME"));
     }
	
	
	@Test
	public void testFindAllForClient() {
		//report_period, report_type, report_year, report_month
		Sort sort = new Sort(Direction.ASC,
				ReportRenamedFilesForWeb.REPORT_PERIOD,
				ReportRenamedFilesForWeb.REPORT_TYPE,
				ReportRenamedFilesForWeb.REPORT_YEAR,
				ReportRenamedFilesForWeb.REPORT_MONTH);
		System.out.println("-----------------------------------------");
		System.out.println("-----------------------------------------");
		System.out.println("-----------------------------------------");
		List<ReportRenamedFilesForWeb> lst = reportsRepository.findByPmaAccountNumber(355,sort);
		for (ReportRenamedFilesForWeb reportRenamedFilesForWeb : lst) {
			System.out.println(reportRenamedFilesForWeb);
		}
		System.out.println("-----------------------------------------");
		System.out.println("-----------------------------------------");
		System.out.println("-----------------------------------------");
	}
	
	
}
