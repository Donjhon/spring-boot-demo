package com.boot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boot.dao.PerosnDaoImpl;
import com.boot.dao.PersonDao;
import com.boot.entity.Person;
import com.boot.exception.MyException;
import com.boot.service.PersonService;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;
	
	@Autowired
	private PerosnDaoImpl personD;
	
	@Override
	public Person getByPersonMyName(String myname) throws Exception {
		
		System.out.println("xxxxxx"+myname);
		com.boot.pojo.Person personService = personDao.getPersonByMyname(myname);
	
		System.out.println("xxxxxx"+personService.getMyname());
		return ObjectConverter.entityConverter(personService, Person.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getByPersonAge(int age) throws Exception {
		List<com.boot.pojo.Person> personPj = personDao.getPersonByAge(age);
			return ObjectConverter.listConverter(personPj, Person.class);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getPersonAll() throws Exception {
		List<com.boot.pojo.Person> personPj = personDao.findAll();
			return ObjectConverter.listConverter(personPj, Person.class);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(value = "transactionManager")
	public String saveApps(List<Person> person) throws Exception {
		List<com.boot.pojo.Person> listPerson_pojo = new ArrayList<>();
		
		try {
			listPerson_pojo = ObjectConverter.listConverter(person, com.boot.pojo.Person.class);
			listPerson_pojo = personDao.save(listPerson_pojo);
			System.out.println(listPerson_pojo.get(0).getMyname());
			personD.testTransactional(listPerson_pojo.get(0).getMyname()); //测试事物
		} catch (MyException e) {
			throw e;
		}
		return listPerson_pojo.toString();
	}
	
	@Override
	@Transactional(value = "transactionManager")
	public Integer updatePerosn(Person person) throws Exception {
		Integer id = person.getId();
		com.boot.pojo.Person person_pj = personDao.findPersonByID(id);
		person_pj.setMyname(person.getMyname());
		person_pj.setAge(person.getAge());
		person_pj = personDao.saveAndFlush(person_pj);
		return id;
	}
	
	@Override
	@Transactional(value = "transactionManager")
	public Integer delPerosnByID(Integer id) throws Exception {
		Integer i ;
		try {
			personDao.delete(id);
			i = 1;
		} catch (Exception e) {
			throw new MyException("", "", e.getMessage());
		}
		return i;
	}

	@Override
	public List<Person> PageAble(int size, int page) throws Exception {
		Sort sort = new Sort(Direction.ASC, "id");
		Pageable pageAble = new PageRequest(page, size, sort);
		Page<com.boot.pojo.Person> perosn_pj = personDao.findAll(pageAble);
		Person person_en1;
		List<Person> person_en = new ArrayList<>();
		for (com.boot.pojo.Person person1 : perosn_pj) {
			System.out.println(person1.getId() + person1.getMyname() + person1.getAge());
			person_en1 = ObjectConverter.entityConverter(person1, Person.class);
			person_en.add(person_en1);
		}
		return person_en;

	}

}
