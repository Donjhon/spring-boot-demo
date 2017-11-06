package com.boot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boot.entity.Person;

@Service
public interface PersonService {

	/**
	 * 根据用户姓名查询用户信息
	 * @param myname
	 * @return
	 * @throws Exception
	 */
	Person getByPersonMyName(String myname) throws Exception;
	
	/**
	 * 根据用户年龄查询用户信息
	 * @param age
	 * @return
	 * @throws Exception
	 */
	List<Person> getByPersonAge(int age) throws Exception;
	
	/**
	 * 查询全部用户信息
	 * @return
	 * @throws Exception
	 */
	List<Person> getPersonAll() throws Exception;
	
	/**
	 * 增加一条用户信息
	 * @param person
	 * @return
	 * @throws Exception
	 */
	String saveApps(List<Person> person) throws Exception;
	
	/**
	 * 更新用户信息
	 * @param person
	 * @return
	 * @throws Exception
	 */
	Integer updatePerosn(Person person) throws Exception;
	
	/**
	 * 根据用户ID删除用户信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Integer delPerosnByID(Integer id) throws Exception;
	
	/**
	 * 分页查询
	 * @param size
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<Person> PageAble(int size, int page) throws Exception;
	
}
