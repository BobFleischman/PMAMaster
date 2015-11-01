/**
 * 
 */
package com.automateddocsys.pmadata.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.automateddocsys.pmadata.bo.ReportRenamedFilesForWeb;

/**
 * @author Robert
 *
 */
public interface ReportsService {

	List<ReportRenamedFilesForWeb> findByPmaAccountNumber(int pClientNo);

	List<ReportRenamedFilesForWeb> findByPmaAccountNumber(int pClientNo, Sort sort);

}
