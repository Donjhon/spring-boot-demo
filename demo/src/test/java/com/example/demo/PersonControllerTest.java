package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.boot.DemoApplication;
import com.boot.controller.PersonController;

@RunWith(value = SpringJUnit4ClassRunner.class)  
//@SpringApplicationConfiguration(classes = DemoApplication.class) 已经过期 
@SpringBootTest(classes = DemoApplication.class)
@WebAppConfiguration 
public class PersonControllerTest extends MockMvcResultMatchers{
	
	@Autowired
	PersonController personCon;
	
	@Autowired
	WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("测试开始加载");
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(personCon).build();
	}
	
	/**
	 * 增加测试
	 */
	@Test
	public void TestAddPersonTest() {
		RequestBuilder request = null;

		try {
			String addStr="{"+
					"\"collection\": {"+
					"\"templates\": ["+
					"{"+
					"\"data\": ["+
					"{"+
					"\"name\": \"id\","+
					"\"value\": \"\""+
					"},"+
					"{"+
					"\"name\": \"myname\","+
					"\"value\": \"deng\""+
					"},"+
					"{"+
					"\"name\": \"age\","+
					"\"value\": \"12\""+
					"}"+
					"]"+
					"}"+
					"]"+
					"}"+
					"}";
		
			request = MockMvcRequestBuilders.post("/person").contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(addStr);
			mvc.perform(request).andExpect(status().isOk());
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	/**
	 * 查询测试
	 */
	@Test
	public void getPersonByname() {
		RequestBuilder request = null;
		try {
			request = MockMvcRequestBuilders.get("/person/tim").contentType(MediaType.APPLICATION_JSON_UTF8);
			mvc.perform(request).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
