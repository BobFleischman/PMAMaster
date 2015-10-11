package com.automateddocumentsys.pma.webdata.test;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.automateddocsys.pma.repository.WebClientRepository;
import com.automateddocsys.pma.webdata.bo.WebClient;
import com.automateddocsys.pma.webdata.config.PMAWebDataConfiguration;

/**
 * JUnit tests for pmaweb.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={PMAWebDataConfiguration.class})
public class PMAwebTest /*extends BaseJNDITests*/ {
	
//	@Autowired
//	ReportRepository reportRepository;
	
	@Autowired
	WebClientRepository clientRepository;
	
	private static final Logger log = LoggerFactory.getLogger(PMAwebTest.class);
	
	
	public String getCurrentTimestamp() {
		
		Locale locale = new Locale("en");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		return dateFormat.format(date);
	}
	
	/**
	 * Annotate each unit test with @Test.
	 * 
	 */
	@Test
	@Ignore
	@Rollback(true)
	public void testpmaweb()
	{
		log.info("testpmaweb:\tSTART:\t"+getCurrentTimestamp());
//		Integer year = 2015;
//		for (ReportType type : ReportType.values()) { //m,t,p
//			for (ReportPeriod period : type.getPeriods()) { //A,Q,M
//				System.out.println(type + " / " + period);
//				switch (period) {
//				case Month: {
//					for (Integer month = 1; month < 13; month++) {
//						System.out.println("- Month: " + month);
//						PMAReport r = new PMAReport();
//						r.setPeriod(period);
//						r.setReportType(type);
//						r.setReportMonth(month);
//						r.setReportYear(year);
//						r.setUserId("bob");
//						r.setAccountNumber("0142");
//						r.setReportDate(getLastDayOfMonth(year,month));					
//						r.setReportTitle(r.getFileName());
//						//reportRepository.save(r);
//						//System.out.println(r);					
//					}
//					break;
//				}
//				case Annual: {
//						Integer month = 12;
//						System.out.println("- Month: " + month);
//						PMAReport r = new PMAReport();
//						r.setPeriod(period);
//						r.setReportType(type);
//						r.setReportMonth(month);
//						r.setReportYear(year);
//						r.setUserId("bob");
//						r.setAccountNumber("0142");
//						r.setReportDate(getLastDayOfMonth(year,month));					
//						r.setReportTitle(r.getFileName());
//						//reportRepository.save(r);
//						//System.out.println(r);					
//						break;
//					}
//				case Quarter: {
//					Integer[] imonths = new Integer[]{3,6,9,12};
//					for (int i=0; i < imonths.length; i++) {
//					Integer month = imonths[i];
//					System.out.println("- Month: " + month);
//					PMAReport r = new PMAReport();
//					r.setPeriod(period);
//					r.setReportType(type);
//					r.setReportMonth(month);
//					r.setReportYear(year);
//					r.setUserId("bob");
//					r.setAccountNumber("0142");
//					r.setReportDate(getLastDayOfMonth(year,month));					
//					r.setReportTitle(r.getFileName());
//					//reportRepository.save(r);
//					//System.out.println(r);					
//					}
//					break;
//				}
//
//				default:
//					break;
//				}
//			}
//		}
		//Perform your tests here with assertEquals(...), assertSame(...), etc.
		log.info("testpmaweb:\tYou should be performing some meaningful testing here...");
		log.info("testpmaweb:\tEND:\t"+getCurrentTimestamp());
	}

	@Test
	public void testLastDayOfMonth() {
		int year = 2015;
		for (Integer month = 1; month < 13; month++) {
			System.out.println(getLastDayOfMonth(year, month));		}		
	}
	
	private Date getLastDayOfMonth(Integer year, Integer month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1); // this is zero based
		cal.set(Calendar.DAY_OF_MONTH,1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
		cal.set(Calendar.HOUR,0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND,0);

		Date lastDayOfMonth = cal.getTime();		
		return lastDayOfMonth;
	}

	@Ignore
	@Test
	@Transactional
	@Rollback(true)
	public void testPMAClientRepository() {
		Iterable<WebClient> lst = clientRepository.findAll();
		for (WebClient pmaClient : lst) {
			System.out.println(pmaClient);
		}
		WebClient clnt = clientRepository.findByUsername("bob");
		if (clnt == null) {
			clnt = new WebClient();
			clnt.setUsername("bob");
			clnt.setPassword("bob123");
			clnt.setMasterClientName("Robert Fleischman");
			clnt.setClientNumber("09877");
			clientRepository.save(clnt);
		}
		clnt.addAuthority("ROLE_USER");
		try {
		clientRepository.save(clnt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		clnt = clientRepository.findByUsername("admin");
		if (clnt == null) {
			clnt = new WebClient();
			clnt.setUsername("admin");
			clnt.setPassword("admin123");
			clnt.setMasterClientName("PMA Admin");
			clnt.setClientNumber("09876");
			clientRepository.save(clnt);
		}
		clnt.addAuthority("ROLE_ADMIN");
		try {
		clientRepository.save(clnt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("XX" + clnt);
	}

}