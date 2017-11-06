package com.boot.dao;

import org.springframework.stereotype.Repository;

import com.boot.exception.TranException;

@Repository 
public class PerosnDaoImpl {

	 public void testTransactional(String username){
		        throw new TranException("测试事务");
		     }
}
