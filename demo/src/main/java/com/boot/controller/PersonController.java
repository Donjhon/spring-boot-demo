package com.boot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.entity.Person;
import com.boot.exception.MyException;
import com.boot.service.PersonService;
import com.boot.util.StringTool;

import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@RestController
@RequestMapping(value = "/person", produces = "application/json; charset=UTF-8")
public class PersonController {

	private final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonService personService;

	/**
	 * 根据用户名查询用户信息
	 * 
	 * @param name
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String getByPersonName(@PathVariable String name, HttpServletRequest request) {
		// String href = request.getRequestURI();
		String href = request.getRequestURI();
		LOGGER.info("###########" + href);
		List<com.boot.model.Person> personMO = new ArrayList<>();

		try {
			Person personEn = personService.getByPersonMyName(name);

			com.boot.model.Person personmd;
			try {
				personmd = ObjectConverter.entityConverter(personEn, com.boot.model.Person.class);
				personmd.setHref(href);
				personMO.add(personmd);
				LOGGER.info("###########" + personmd.getHref() + " " + personmd.getAge() + " " + personmd.getMyname());

				return RestfulTool.buildCollection(personMO, StringTool.getBaseHref(href), com.boot.model.Person.class);
			} catch (MyException e) {
				LOGGER.error(e.getMessage());
				return RestfulTool.buildCollection(new ErrorInfo(e.getTitle(), e.getReturnCode(), e.getMessage()),
						href);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), href);
		}
	}

	/**
	 * 查询全部用户信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getPersonAll(HttpServletRequest request) {
		String href = request.getRequestURI();

		try {
			List<com.boot.model.Person> personMO = new ArrayList<>();
			List<Person> personEN;
			try {
				personEN = personService.getPersonAll();

				for (Person source : personEN) {
					com.boot.model.Person personMo = ObjectConverter.entityConverter(source,
							com.boot.model.Person.class);
					personMo.setHref(href);
					personMO.add(personMo);
					LOGGER.info("##########" + personMo.getMyname() + " " + personMo.getAge());
				}

				return RestfulTool.buildCollection(personMO, StringTool.getBaseHref(href), com.boot.model.Person.class);
			} catch (MyException e) {
				LOGGER.error(e.getMessage());
				return RestfulTool.buildCollection(new ErrorInfo(e.getTitle(), e.getReturnCode(), e.getMessage()),
						href);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), href);
		}
	}

	/**
	 * 根据年龄查询用户信息
	 * 
	 * @param age
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/age/{age}", method = RequestMethod.GET)
	public String getPersonByAge(@PathVariable int age, HttpServletRequest request) {

		String href = request.getRequestURI();
		try {
			List<com.boot.model.Person> personMO = new ArrayList<>();
			List<Person> personEN;
			try {
				personEN = personService.getByPersonAge(age);
				for (Person source : personEN) {
					com.boot.model.Person personMo = ObjectConverter.entityConverter(source,
							com.boot.model.Person.class);
					personMo.setHref(href);
					personMO.add(personMo);
					LOGGER.info("##########" + personMo.getMyname() + " " + personMo.getAge());
				}
				return RestfulTool.buildCollection(personMO, StringTool.getBaseHref(href), com.boot.model.Person.class);
			} catch (MyException e) {
				LOGGER.error(e.getMessage());
				return RestfulTool.buildCollection(new ErrorInfo(e.getTitle(), e.getReturnCode(), e.getMessage()),
						href);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), href);
		}
	}

	/**
	 * 添加用户信息
	 * 
	 * @param collection
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String addApp(@RequestBody String collection, HttpServletRequest request, HttpServletResponse response) {

		String resultStr = "";
		try {
			@SuppressWarnings("unchecked")
			List<com.boot.model.Person> appList = RestfulTool.toResourceRep(collection, com.boot.model.Person.class);

			Integer dataCount = appList.size();
			List<Person> etAppList = new ArrayList<Person>();
			for (com.boot.model.Person app : appList) {

				dataCount--;
				Person etApp = ObjectConverter.entityConverter(app, Person.class);
				etAppList.add(etApp);
				LOGGER.info("loggerInfo##########" + etApp.getMyname() + " " + etApp.getAge());
			}

			resultStr = personService.saveApps(etAppList);

		} catch (MyException e) {
			resultStr = RestfulTool.buildCollection(new ErrorInfo(e.getTitle(), e.getReturnCode(), e.getMessage()),
					request.getRequestURI());
			LOGGER.error(e.getMessage());
		} catch (Exception e) {
			resultStr = RestfulTool.buildCollection(new ErrorInfo("ssss", "", e.getMessage()), request.getRequestURI());
			LOGGER.error("*******" + e.getMessage());
		}
		return resultStr;
	}

	/**
	 * 更新用户信息
	 * 
	 * @param collection
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String updatePerson(@RequestBody String collection, HttpServletRequest request,
			HttpServletResponse response) {
		LOGGER.info(collection);
		String resultStr = "";
		try {
			@SuppressWarnings("unchecked")
			List<com.boot.model.Person> person_mo = (List<com.boot.model.Person>) RestfulTool.toResourceRep(collection,
					com.boot.model.Person.class);
			Integer dataCount = person_mo.size();
			Integer i = null;
			for (com.boot.model.Person app : person_mo) {

				dataCount--;
				Person person_en = ObjectConverter.entityConverter(app, Person.class);
				LOGGER.info("######" + person_en.getId() + person_en.getMyname() + person_en.getAge());
				i = personService.updatePerosn(person_en);
			}
			resultStr = String.valueOf(i);
		} catch (MyException e) {
			resultStr = RestfulTool.buildCollection(new ErrorInfo(e.getTitle(), e.getReturnCode(), e.getMessage()),
					request.getRequestURI());
			LOGGER.error(e.getMessage());
		} catch (Exception e) {
			resultStr = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), request.getRequestURI());
		}
		return resultStr;
	}

	/**
	 * 根据ID删除信息
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/del/{id}")
	public String delPersonById(@PathVariable Integer id, HttpServletRequest request) {
		String resultStr = "";
		try {
			Integer i = personService.delPerosnByID(id);
			resultStr = String.valueOf(i);
		} catch (Exception e) {
			resultStr = RestfulTool.buildCollection(new ErrorInfo("", "", e.getMessage()), request.getRequestURI());
		}
		return resultStr;
	}

	/**
	 * 分页查询
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/get/{page}", method = RequestMethod.GET)
	public String pagefen(@PathVariable int page, HttpServletRequest request, HttpServletResponse response) {
		int size = 1;
		String href = request.getRequestURI();
		try {
			List<com.boot.model.Person> person_MO = new ArrayList<>();

			try {
				List<Person> perosnen = personService.PageAble(size, page);

				for (Person source : perosnen) {
					com.boot.model.Person personMo = ObjectConverter.entityConverter(source,
							com.boot.model.Person.class);
					personMo.setHref(href);
					person_MO.add(personMo);
				}
				return RestfulTool.buildCollection(person_MO, StringTool.getBaseHref(href),
						com.boot.model.Person.class);
			} catch (MyException e) {
				return RestfulTool.buildCollection(new ErrorInfo(e.getTitle(), e.getReturnCode(), e.getMessage()),
						href);
			}
		} catch (Exception e) {
			return RestfulTool.buildCollection(new ErrorInfo("wewe", "", e.getMessage()), request.getRequestURI());
		}
	}

}
