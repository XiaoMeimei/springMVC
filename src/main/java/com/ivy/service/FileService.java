package com.ivy.service;

import java.util.List;

import com.ivy.entity.UploadFile;
import com.ivy.entity.PageBean;

public interface FileService {

	public List<UploadFile> searchFile(PageBean pageBean) throws Exception;
	
	public int countShareFiles(PageBean pageBean) throws Exception;
	
	public List<UploadFile> searchUserFile(String filepath,int startindex,int pagesize) throws Exception;
	
	public int countUserFiles(String filepath) throws Exception;
	
	public int insertFile(UploadFile file) throws Exception;
	
	public String findFilenameById(int id);
	
	public String findFilepathById(int id) throws Exception;

	public void deleteFileById(Integer fileid);
	
	public void updateFileById(int id, int share) throws Exception;
}
