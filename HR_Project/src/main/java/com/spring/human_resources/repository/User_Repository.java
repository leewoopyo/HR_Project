package com.spring.human_resources.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.spring.human_resources.domain.User;

@Repository
public interface User_Repository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>{
	//username을 기반으로 한 데이터 검색 
	User findByUsername(String username);
}
