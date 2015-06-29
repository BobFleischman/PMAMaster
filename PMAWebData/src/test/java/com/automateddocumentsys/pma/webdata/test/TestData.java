package com.automateddocumentsys.pma.webdata.test;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.automateddocsys.pma.repository.QuestionRepository;
import com.automateddocsys.pma.repository.WebClientRepository;
import com.automateddocsys.pma.webdata.bo.PotentialQuestion;
import com.automateddocsys.pma.webdata.bo.WebClient;
import com.automateddocsys.pma.webdata.config.DataConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DataConfiguration.class})

public class TestData {

	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	WebClientRepository webClientRepository;
	
	@Test
	@Transactional
	//@Rollback(false)
	public void test() {
		int ct = 0;
		makeQuestion(ct++,"In what city were you born?");
		makeQuestion(ct++,"What is the last name of your favorite author?");
		makeQuestion(ct++,"What is your Mother's middle name?");
		makeQuestion(ct++,"What is your Father's middle name?");
		makeQuestion(ct++,"What's your favorite car?");
		makeQuestion(ct++,"What's your pet's name?");
		makeQuestion(ct++,"What's your favorite film?");
		makeQuestion(ct++,"What's your favorite team?");
	}

	private void makeQuestion(int ct, String pQuestion) {
		PotentialQuestion q = new PotentialQuestion(ct,pQuestion);
		questionRepository.save(q);
	}
	
	@Test
	@Transactional
	public void testRandom() {
		for (int i = 0; i< 10; i++) {
			Set<PotentialQuestion> rsult = questionRepository.getRandom(3);
		for (PotentialQuestion potentialQuestion : rsult) {
			System.out.println(potentialQuestion);
		}
		System.out.println("------------------------------");
		}
	}

	@Test
	@Transactional
	//@Rollback(false)
	public void makeTestClients() {
		makeClient("0001","Nancy Kahn","kahn","kahn123");
		makeClient("0466", "Caryl A. Golden Revocable Inter Vivos Trust dtd 3/10/97","golden","golden123");
		makeClient("0688", "Nancy E. Roll IRA","roll","roll123");
//
	}
	
	@Test
	@Transactional
	//@Rollback(false)
	public void testAddAnswer() {
		WebClient wc = new WebClient("9998","TEST CLIENT2","tester2","tester21");
		//WebClient wc = webClientRepository.findByUsername("tester");
		PotentialQuestion q = questionRepository.findOne((long) 0);
		System.out.println(q);
		wc.addAnswer(q,"Philadelphia");
		Assert.assertTrue(wc.getAnswers().size() == 1);
		System.out.println(wc);
		wc.addAnswer(q, "New Haven");
		Assert.assertTrue(wc.getAnswers().size() == 1);
		System.out.println(wc);
		String answer = wc.getAnswerFor(q);
		Assert.assertEquals("New Haven", answer);
		q = questionRepository.findOne((long) 5);
		wc.addAnswer(q, "Merlin");
		answer = wc.getAnswerFor(0);
		Assert.assertEquals("New Haven", answer);
		Assert.assertTrue(wc.getAnswers().size() == 2);
		System.out.println(wc);
		//webClientRepository.save(wc);
		Assert.assertTrue(wc.answeredCorrectly(0,"New Haven"));
		Assert.assertFalse(wc.answeredCorrectly(5,"Sam"));
		wc.removeQuestion(0);
		Assert.assertTrue(wc.getAnswers().size() == 1);
		System.out.println(wc);
		wc.clearAnswers();
		Assert.assertTrue(wc.getAnswers().size() == 0);
		//webClientRepository.save(wc);
	}

	private void makeClient(String pMastAccountNumber, String pMastCLientName, String pUserName, String pPassword) {
		WebClient wc = new WebClient(pMastAccountNumber, pMastCLientName, pUserName, pPassword);
		wc.addAuthority("ROLE_USER");
		try {
			WebClient test = webClientRepository.findByUsername(pUserName);
			if (test == null) {
				webClientRepository.save(wc);
			} else {
				if (test.getAuthorities().size() == 0) {
					test.addAuthority("ROLE_USER");
				}
			}
		} catch (DataIntegrityViolationException e) {
			System.out.println("expected - " + wc);
			// do nothing as this is expected for multiple runs
		}
	}
}


