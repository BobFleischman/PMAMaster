/**
 * 
 */
package com.automateddocsys.pma.masterreport.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.automateddocsys.pma.masterreport.bo.ReportRenamedFilesForWeb;

/**
 * @author Robert
 *
 */
public interface ReportsService {

	List<ReportRenamedFilesForWeb> findByPmaAccountNumber(int pClientNo);

	List<ReportRenamedFilesForWeb> findByPmaAccountNumber(int pClientNo, Sort sort);

}
