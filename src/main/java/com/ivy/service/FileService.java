package com.ivy.service;

import java.util.List;

import com.ivy.entity.File;
import com.ivy.entity.PageBean;

public interface FileService {

	public List<File> searchFile(PageBean pageBean) throws Exception;
	
	public int countShareFiles(PageBean pageBean) throws Exception;
	
	public List<File> searchUserFile(String filepath,int startindex,int pagesize) throws Exception;
	
	public int countUserFiles(String filepath) throws Exception;
	
	public int insertFile(File file) throws Exception;
	
	public String findFilenameById(int id);
	
	public String findFilepathById(int id) throws Exception;
}
