package com.automateddocsys.pmadata.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.automateddocsys.pmadata.bo.ReportRenamedFilesForWeb;

public interface ReportsRepository extends JpaRepository<ReportRenamedFilesForWeb, Integer> {
	
	List<ReportRenamedFilesForWeb> findByPmaAccountNumber(int pClientNo);

	List<ReportRenamedFilesForWeb> findByPmaAccountNumber(int pClientNo, Sort sort);

}

