package com.automateddocsys.pma.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.automateddocsys.pma.web.mvc.models.AnswerSet;

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

	}

}
