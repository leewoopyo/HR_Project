package com.spring.human_resources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.spring.human_resources.domain.HR;

@Repository
public interface HR_Repository extends JpaRepository<HR, Long>,JpaSpecificationExecutor<HR>{

}
