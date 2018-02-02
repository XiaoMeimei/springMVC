package com.ivy.dao;

import java.util.List;

import com.ivy.entity.File;
import com.ivy.entity.PageBean;

public interface FileDao {
	
	public List<File> getAllFilesWithPage(PageBean pageBean) throws Exception;
	public int count(PageBean pageBean) throws Exception;
	/*public int count(SearchFileAction searchFileAction) throws Exception;
	public String findFilepathById(int id) throws Exception;
	public Integer insertFile(File file) throws Exception;
	public List<File> getUserFiles(SearchUserFileAction action)throws Exception;
	public int countUserFiles(SearchUserFileAction action) throws Exception;
	public void updateFileById(ChangeFileStatusAction changeFileStatusAction) throws Exception;*/
	public void deleteFileById(int id);
	public String findFilenameById(int id);
	
}