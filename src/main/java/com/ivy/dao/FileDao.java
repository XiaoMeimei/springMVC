package com.ivy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ivy.entity.UploadFile;
import com.ivy.entity.PageBean;

public interface FileDao {
	
	public List<UploadFile> getAllFilesWithPage(PageBean pageBean) throws Exception;
	public int count(PageBean pageBean) throws Exception;
	public List<UploadFile> getUserFiles(@Param("filepath")String filepath, @Param("startindex")int startindex, @Param("pagesize")int pagesize)throws Exception;
	public int countUserFiles(@Param("filepath")String filepath) throws Exception;
	public Integer insertFile(UploadFile file) throws Exception;
	public String findFilenameById(int id);
	public String findFilepathById(int id) throws Exception;
	public void updateFileById(@Param("id")int id, @Param("canshare")int canshare) throws Exception;
	public void deleteFileById(int id);
	
}