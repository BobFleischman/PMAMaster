package com.automateddocsys.pma.repository;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.automateddocsys.pma.webdata.bo.PotentialQuestion;

public class QuestionRepositoryImpl implements QuestionRepositoryCustom {

	@Autowired
	QuestionRepository questionRepository;
	
	private final static Random random = new Random();
	
	@Override
	public Set<PotentialQuestion> getRandom(int ct) {
		Set<PotentialQuestion> result = new HashSet<PotentialQuestion>();
		long questionCt = questionRepository.count();
		while(result.size() < ct) {
			long qId = random.nextInt((int) questionCt);
			PotentialQuestion q = questionRepository.findOne(qId);
			result.add(q);
		}
		return result;
	}

}
