package com.automateddocsys.pmadata.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.automateddocsys.pmadata.bo.PositionTotal;

public interface PositionTotalRepository extends JpaRepository<PositionTotal, Long> {

	List<PositionTotal> findByClientNo(Integer pClientNo);
	List<PositionTotal> findByClientNoIn(List<Integer> pClientNo, Sort pSort);
}
