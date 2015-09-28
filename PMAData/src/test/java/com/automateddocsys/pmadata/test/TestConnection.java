package com.automateddocsys.pmadata.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.automateddocsys.pmadata.bo.PositionTotal;
import com.automateddocsys.pmadata.config.DataConfiguration;
import com.automateddocsys.pmadata.repository.PositionTotalRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DataConfiguration.class})
public class TestConnection {
	
	@Autowired
	private PositionTotalRepository positionTotalRepository; 
	
	@Test
	public void testClearAnswers() {
		System.out.println("Testing");
		Sort sort = new Sort(Direction.ASC,"ClientNo","ObjectName","FundName");
		List<PositionTotal> lst2 = positionTotalRepository.findAll(sort);
		showTotals(lst2);
		List<PositionTotal> lst = positionTotalRepository.findByClientNo(355);
		showTotals(lst);
		List<Integer> lstClientNo = new ArrayList<Integer>();
		lstClientNo.add(355);
		lstClientNo.add(1643);
		lst = positionTotalRepository.findByClientNoIn(lstClientNo,sort);
		showTotals(lst);
		
	}

	private void showTotals(List<PositionTotal> lst) {
		System.out.println();System.out.println();System.out.println();
		for (PositionTotal positionTotal : lst) {
			System.out.println(positionTotal.toString());
		}
	}

}
