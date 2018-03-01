package com.ivy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivy.dao.FileDao;
import com.ivy.entity.UploadFile;
import com.ivy.entity.PageBean;
import com.ivy.exception.NoNumberException;
import com.ivy.service.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Autowired
	private FileDao fileDao;
	
	public List<UploadFile> searchFile(PageBean pageBean) throws Exception {
		
		List<UploadFile> fileList = fileDao.getAllFilesWithPage(pageBean);
		if (fileList.size() <= 0) {
			throw new NoNumberException("no number");
		}
		return fileList;
	}

	public int countShareFiles(PageBean pageBean) throws Exception {
		return fileDao.count(pageBean);
	}
	

	public List<UploadFile> searchUserFile(String filepath, int startindex, int pagesize) throws Exception {
		List<UploadFile> fileList = fileDao.getUserFiles(filepath, startindex, pagesize);
		if (fileList.size() <= 0) {
			throw new NoNumberException("no number");
		}
		return fileList;
	}

	public int countUserFiles(String filepath) throws Exception {
		return fileDao.countUserFiles(filepath);
	}

	public int insertFile(UploadFile file) throws Exception {
		
		return fileDao.insertFile(file);
	}

	public String findFilenameById(int id) {
		return fileDao.findFilenameById(id);
	}

	public String findFilepathById(int id) throws Exception {
		return fileDao.findFilepathById(id);
	}

	public void deleteFileById(Integer fileid) {
		fileDao.deleteFileById(fileid);
		
	}

	public void updateFileById(int id, int share) throws Exception {
		fileDao.updateFileById(id, share);
		
	}


}
