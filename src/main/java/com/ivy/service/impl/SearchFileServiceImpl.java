package com.ivy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivy.dao.FileDao;
import com.ivy.entity.File;
import com.ivy.entity.PageBean;
import com.ivy.exception.NoNumberException;
import com.ivy.service.SearchFileService;

@Service
public class SearchFileServiceImpl implements SearchFileService{

	@Autowired
	private FileDao fileDao;
	
	public List<File> searchFile(PageBean pageBean) throws Exception {
		
		List<File> fileList = fileDao.getAllFilesWithPage(pageBean);
		if (fileList.size() <= 0) {
			throw new NoNumberException("no number");
		}
		return fileList;
	}

	public int countShareFiles(PageBean pageBean) throws Exception {
		return fileDao.count(pageBean);
	}
	
	public List<File> searchUserFile() {
		// TODO Auto-generated method stub
		return null;
	}


}
