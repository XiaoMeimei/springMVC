package com.ivy.web;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ivy.entity.PageBean;
import com.ivy.entity.UploadFile;
import com.ivy.entity.UserInfo;
import com.ivy.service.FileService;
import com.ivy.util.PropertiesUtil;

@Controller
public class UploadFileController {
	
	private static final String storePath = "C:"+File.separator+"upload"; //存储目录 D:\\upload
	
	@Autowired
	private FileService fileService;
	
	private PageBean pageBean = new PageBean();
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFile(@RequestParam MultipartFile multilpartFile, @RequestParam String username, ModelMap modelMap) {
		
		//从数据库查询该用户是否为vip 
		UserInfo userInfo = (UserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer isvip = userInfo.getIsvip();
		modelMap.addAttribute("isvip", isvip);
		
		File store = null;  //目的文件
      /*  File dir = new File(storePath + File.separator + username);
        if (!dir.exists())
            dir.mkdirs();*/
	   	try{
    		//存在每个用户有一个自己名字命名的文件夹
    		 store = new File(storePath + File.separator + username,multilpartFile.getOriginalFilename());
    	}catch (Exception e) {
    		modelMap.addAttribute("message", "请先选择文件！");
    	}
	   	
	   	long size = multilpartFile.getSize();  //上传文件的大小
	
		//todo 检查用户的云空间是否超过限额
		
		
		//验证全部通过，把文件复制到本地硬盘的用户的目录下
		Integer fileid = null;
		UploadFile file = new UploadFile();
		try {
	
			multilpartFile.transferTo(store);
			//文件信息保存到数据库
			file.setCreatetime(new java.util.Date());
			file.setFilename(multilpartFile.getOriginalFilename());
			file.setFilepath(username);
			file.setFilesize(String.valueOf(size/1024+1));
			file.setCanshare(0);
		    fileid = fileService.insertFile(file);
		    modelMap.addAttribute("message", "上传成功！");
		    //重新获取用户的文件列表
		    int defaultPageSize = getPageSize();
		    int defaultCurrentPage = getCurrentPage();
		    pageBean.setCurrentpage(defaultCurrentPage);
			pageBean.setPagesize(defaultPageSize);
			List<UploadFile> fileList = fileService.searchUserFile(username, pageBean.getStartindex(), defaultPageSize);
			pageBean.setList(fileList);
			pageBean.setTotalrecord(fileService.countUserFiles(username));
			modelMap.addAttribute("pagebean", pageBean);
		    
			return "userhome";
		} catch (Exception e) {
			e.printStackTrace();
			if(store.exists()){ //中途出现异常，把拷贝的文件删除
				store.delete();
			}
			if(fileid!=null){
				fileService.deleteFileById(fileid);
			}
			modelMap.addAttribute("message", "上传失败！请重试");
			return null;
		}
		
	}

	
	public int getPageSize() {
		PropertiesUtil.readProperties("icloud.properties");
		return Integer.parseInt(PropertiesUtil.getProperty("pageSize"));
	}
	
	
	private int getCurrentPage() {
		PropertiesUtil.readProperties("icloud.properties");
		return Integer.parseInt(PropertiesUtil.getProperty("currentPage"));
	}

}
