package com.ivy.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ivy.BaseTest;
import com.ivy.entity.UploadFile;
import com.ivy.entity.PageBean;

import junit.framework.Assert;

public class FileDaoTest extends BaseTest{
	
	@Autowired
	private FileDao fileDao;
	
	private PageBean pageBean = new PageBean();
	
	public UploadFile file;
	String pageContent = "java";
	int startIndex = 0;
	int pageSize = 5;
	
	@Before
	public void setUp(){
		pageBean.setCurrentpage(0);
		pageBean.setPagesize(5);
		pageBean.setSearchcontent("java");
	}

	@Test
	public void testGetAllFilesWithPager() throws Exception {
		List<UploadFile> fileList = fileDao.getAllFilesWithPage(pageBean);
		Assert.assertTrue(fileList != null);
	}
	
	@Test
	public void testCount() throws Exception {
		int count = fileDao.count(pageBean);
		Assert.assertTrue(count >= 0);
	}

}
