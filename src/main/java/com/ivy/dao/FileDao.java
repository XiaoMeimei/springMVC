package com.ivy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ivy.entity.File;
import com.ivy.entity.PageBean;

public interface FileDao {
	
	public List<File> getAllFilesWithPage(PageBean pageBean) throws Exception;
	public int count(PageBean pageBean) throws Exception;
	public List<File> getUserFiles(@Param("filepath")String filepath, @Param("startindex")int startindex, @Param("pagesize")int pagesize)throws Exception;
	public int countUserFiles(@Param("filepath")String filepath) throws Exception;
	public Integer insertFile(File file) throws Exception;
	public String findFilenameById(int id);
	public String findFilepathById(int id) throws Exception;
/*	public void updateFileById(ChangeFileStatusAction changeFileStatusAction) throws Exception;
	public void deleteFileById(int id);*/
	
}