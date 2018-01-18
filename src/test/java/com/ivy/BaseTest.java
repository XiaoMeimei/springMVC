package com.ivy;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * é…�ç½®springå’Œjunitæ•´å�ˆï¼Œjunitå�¯åŠ¨æ—¶åŠ è½½springIOCå®¹å™¨ spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
// å‘Šè¯‰junit springé…�ç½®æ–‡ä»¶
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml", "classpath:spring/spring-web.xml"  })
public class BaseTest {
	
}
