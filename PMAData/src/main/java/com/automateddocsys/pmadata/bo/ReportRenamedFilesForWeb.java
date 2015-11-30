package com.automateddocsys.pmadata.bo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the ReportRenamedFilesForWeb database table.
 * 
 */
@Entity
@NamedQuery(name="ReportRenamedFilesForWeb.findAll", query="SELECT r FROM ReportRenamedFilesForWeb r")
public class ReportRenamedFilesForWeb implements Serializable {

	
	public static final String REPORT_MONTH ="reportMonth";
	public static final String REPORT_YEAR ="reportYear";
	public static final String REPORT_PERIOD ="reportPeriod";
	public static final String REPORT_TYPE ="reportType";	
	   private static final Map<String, String> months;
	    static {
	        Map<String, String> aMap = new HashMap<String,String>();
	        aMap.put("01", "January");
	        aMap.put("02", "February");
	        aMap.put("03", "March");
	        aMap.put("04", "April");
	        aMap.put("05", "May");
	        aMap.put("06", "June");
	        aMap.put("07", "July");
	        aMap.put("08", "August");
	        aMap.put("09", "September");
	        aMap.put("10", "October");
	        aMap.put("11", "November");
	        aMap.put("12", "December");
	        months = Collections.unmodifiableMap(aMap);
	    }
	
	private static final long serialVersionUID = 1L;

	@Column(name="pma_account_number")
	private int pmaAccountNumber;

	@Column(name="process_by")
	private String processBy;

	@Column(name="process_date")
	private Timestamp processDate;

	@Id
	@Column(name="report_id")
	private int reportId;

	@Column(name="report_location")
	private String reportLocation;

	@Column(name="report_month")
	private String reportMonth;

	@Column(name="report_new_name")
	private String reportNewName;

	@Column(name="report_old_name")
	private String reportOldName;

	@Column(name="report_period")
	private String reportPeriod;

	@Column(name="report_type")
	private String reportType;

	@Column(name="report_year")
	private String reportYear;

	public ReportRenamedFilesForWeb() {
	}

	public int getPmaAccountNumber() {
		return this.pmaAccountNumber;
	}

	public void setPmaAccountNumber(int pmaAccountNumber) {
		this.pmaAccountNumber = pmaAccountNumber;
	}

	public String getProcessBy() {
		return this.processBy;
	}

	public void setProcessBy(String processBy) {
		this.processBy = processBy;
	}

	public Timestamp getProcessDate() {
		return this.processDate;
	}

	public void setProcessDate(Timestamp processDate) {
		this.processDate = processDate;
	}

	public int getReportId() {
		return this.reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public String getReportLocation() {
		return this.reportLocation;
	}

	public void setReportLocation(String reportLocation) {
		this.reportLocation = reportLocation;
	}

	public String getReportMonth() {
		return this.reportMonth;
	}

	public void setReportMonth(String reportMonth) {
		this.reportMonth = reportMonth;
	}

	public String getReportNewName() {
		return this.reportNewName;
	}

	public void setReportNewName(String reportNewName) {
		this.reportNewName = reportNewName;
	}

	public String getReportOldName() {
		return this.reportOldName;
	}

	public void setReportOldName(String reportOldName) {
		this.reportOldName = reportOldName;
	}

	public String getReportPeriod() {
		return this.reportPeriod;
	}

	public void setReportPeriod(String reportPeriod) {
		this.reportPeriod = reportPeriod;
	}

	public String getReportType() {
		return this.reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getReportYear() {
		return this.reportYear;
	}

	public void setReportYear(String reportYear) {
		this.reportYear = reportYear;
	}
	
	public String getMonth() {
		return months.get(getReportMonth());
	}
	
	public String getReportDate() {
		return getReportYear() + " " + getMonth();
	}
	
	public String getReportTypeName() {
		if ("TRN".equals(reportType)) {
			return "Transactions";
		} else if ("PER".equals(reportType)) {
			return "Performance";
		} else if ("MVS".equals(reportType)) {
			return "Market Value";
		} else {
			return reportType;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReportRenamedFilesForWeb [pmaAccountNumber=");
		builder.append(pmaAccountNumber);
		builder.append(", reportLocation=");
		builder.append(reportLocation);
		builder.append(", reportMonth=");
		builder.append(reportMonth);
		builder.append(", reportPeriod=");
		builder.append(reportPeriod);
		builder.append(", reportType=");
		builder.append(reportType);
		builder.append(", reportDate=");
		builder.append(getReportDate());
		builder.append(", reportYear=");
		builder.append(reportYear);
		builder.append("]");
		return builder.toString();
	}

}