package com.automateddocsys.pma.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.automateddocsys.pma.web.mvc.models.AnswerSet;
import com.automateddocsys.pma.web.mvc.models.PasswordChange;
import com.automateddocsys.pma.web.utils.PasswordValidator;

public class TestUtils {

	@Test
	public void test() {
		AnswerSet as = new AnswerSet();
		assertTrue(as.duplicateQuestions());
		as.setQuestion2(4L);
		as.setQuestion3(5L);
		assertFalse(as.duplicateQuestions());
		as.setQuestion1(4L);
		assertTrue(as.duplicateQuestions());
		assertTrue(as.notAllQuestionsHaveAnswers());
		as.setAnswer1("XXX");
		as.setAnswer2("XXX");
		as.setAnswer3("XXX");
		assertFalse(as.notAllQuestionsHaveAnswers());
	}

	@Test
	public void testPasswordValidation() {
		PasswordValidator pw = new PasswordValidator();
		String testWord = "ab";
		assertFalse(pw.validate(testWord));
		testWord = "ThisI$aTest";
		assertFalse(pw.validate(testWord));
		testWord = testWord + "1";
		assertTrue(pw.validate(testWord));
	}
	
	@Test
	public void testMatcher() {
		PasswordChange pc = new PasswordChange();
		pc.setPassword("ABC");
		pc.setConfirm_password("ABC");
		assertTrue(pc.doesMatch());
	}
}
