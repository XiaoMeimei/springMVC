package com.ivy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivy.dao.FileDao;
import com.ivy.entity.File;
import com.ivy.entity.PageBean;
import com.ivy.exception.NoNumberException;
import com.ivy.service.FileService;

@Service
public class FileServiceImpl implements FileService{

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
	

	public List<File> searchUserFile(String filepath, int startindex, int pagesize) throws Exception {
		List<File> fileList = fileDao.getUserFiles(filepath, startindex, pagesize);
		if (fileList.size() <= 0) {
			throw new NoNumberException("no number");
		}
		return fileList;
	}

	public int countUserFiles(String filepath) throws Exception {
		return fileDao.countUserFiles(filepath);
	}

	public int insertFile(File file) throws Exception {
		
		return fileDao.insertFile(file);
	}

	public String findFilenameById(int id) {
		return fileDao.findFilenameById(id);
	}

	public String findFilepathById(int id) throws Exception {
		return fileDao.findFilepathById(id);
	}


}
