package com.automateddocsys.pma.masterreport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.automateddocsys.pma.masterreport.bo.ReportRenamedFilesForWeb;
import com.automateddocsys.pma.masterreport.repository.ReportsRepository;

@Transactional(value="transactionManagerPMAMasterReport",readOnly=true)
@Service
public class ReportServiceImpl implements ReportsService {
	
	private static final Sort reportsSort = new Sort(Direction.ASC,
			ReportRenamedFilesForWeb.REPORT_PERIOD,
			ReportRenamedFilesForWeb.REPORT_TYPE,
			ReportRenamedFilesForWeb.REPORT_YEAR,
			ReportRenamedFilesForWeb.REPORT_MONTH);

	@Autowired
	private ReportsRepository reportRepository;	
	
	@Override
	public List<ReportRenamedFilesForWeb> findByPmaAccountNumber(int pClientNo) {
		return reportRepository.findByPmaAccountNumber(pClientNo,reportsSort);
	}

	@Override
	public List<ReportRenamedFilesForWeb> findByPmaAccountNumber(int pClientNo, Sort sort) {
		return reportRepository.findByPmaAccountNumber(pClientNo, sort);
	}

}
