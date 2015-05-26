package com.automateddocsys.pma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.automateddocsys.pma.webdata.bo.PotentialQuestion;

@Service
public interface QuestionRepository  extends JpaRepository<PotentialQuestion, Long>, QuestionRepositoryCustom {
	


}
