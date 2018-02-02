package com.ivy.service;

import java.util.List;

import com.ivy.entity.File;
import com.ivy.entity.PageBean;

public interface SearchFileService {

	public List<File> searchFile(PageBean pageBean) throws Exception;
	
	public int countShareFiles(PageBean pageBean) throws Exception;
	
	public List<File> searchUserFile();
}
