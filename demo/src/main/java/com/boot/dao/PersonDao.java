package com.boot.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boot.pojo.Person;

public interface PersonDao extends JpaRepository<Person, Integer> {

	/**
	 * 根据Myname查询用户信息
	 * @param myname
	 * @return
	 */
	@Query("select data from Person data where data.myname = :myname")
	public Person getPersonByMyname(String myname);
	
	/**
	 * 根据年龄查询用户信息
	 * @param age
	 * @return
	 */
	@Query("select data from Person data where data.age = :age")
	public List<Person> getPersonByAge(@Param("age") int age);
	
	/**
	 * 根据ID查询用户信息
	 * @param id
	 * @return
	 */
	@Query("select data from Person data where data.id = :id")
	public Person findPersonByID(@Param("id") Integer id);

	/**
	 * 分页查询
	 */
	Page<Person> findAll(Pageable pageable);
}
