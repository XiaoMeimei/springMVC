package com.ivy.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UploadFileController {
	
	/*@RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
	public String searchFileWithPage(@RequestParam(value = "searchcontent") String searchcontent, 
									 @RequestParam(value = "currentpage", defaultValue="0", required=false) int currentpage,
									 @RequestParam(value = "pagesize", defaultValue="5", required=false) int pagesize,
									 Model model) {
		
		//session域存的username和传进来的username一致，说明用户名没有造假
		String user_name = (String) ActionContext.getContext().getSession().get("user_name");
		if(user_name == null || "".equals(user_name) || !user_name.equals(this.username))
			 return SUCCESS;
		
		//从数据库查询该用户是否为vip 
		Integer isvip = null;
		try {
			isvip = userService.isVip(user_name);
			//把是否是vip的信息带到userhome主页，用于在客户端限制文件上传大小
			ServletActionContext.getRequest().setAttribute("isvip", isvip);
		} catch (Exception e) {
			e.printStackTrace();
			ServletActionContext.getRequest().setAttribute("message", "未知错误，请重试");
			return SUCCESS;
		}  
		
		File store = null;  //目的文件
	   	try{
    		//存在每个用户有一个自己名字命名的文件夹
    		 store = new File(storePath+File.separator+username,fileFileName);
    	}catch (Exception e) {
    		ServletActionContext.getRequest().setAttribute("message", "请先选择文件！");
    		return SUCCESS;
    	}
	   	
	   	long size = this.file.length() ;  //上传文件的大小
	   	
		if(SUCCESS.equals(checkFile(store, storePath, isvip, size)))//检查文件大小等是否符合要求
			return SUCCESS; //有问题 转发回用户空间页面显示原因
		
			
		//todo 检查用户的云空间是否超过限额
		
		
		//验证全部通过，把文件复制到本地硬盘的用户的目录下
		Integer fileid = null;
		try {
			FileUtils.copyFile(file,store);   //上传文件到本地硬盘
			//把文件信息存入数据库
			f.setCreatetime(new java.util.Date());
			f.setFilename(fileFileName);
			f.setFilepath(username);
			f.setFilesize(String.valueOf(size/1024+1));
			f.setCanshare(0);
			
		    fileid = fileService.insertFile(f);
			
			ServletActionContext.getRequest().setAttribute("message", "上传成功！");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			if(store.exists()){ //中途出现异常，把拷贝的文件删除
				store.delete();
			}
			if(fileid!=null){
				fileService.deleteFileById(fileid);
			}
			ServletActionContext.getRequest().setAttribute("message", "上传失败！请重试");
			return SUCCESS;
		}
		
		return null;
		
	}
	
    private String checkFile(File store , String storePath , int isvip , long size){
    	 
		
		if(store.exists()){
			ServletActionContext.getRequest().setAttribute("message", "文件已存在");
			return SUCCESS;
		}
		
		if( size == 0 ){
			ServletActionContext.getRequest().setAttribute("message", "文件大小不能为0");
			return SUCCESS;
		}else if(isvip == 0 && size > normallimit){
			ServletActionContext.getRequest().setAttribute("message", "普通用户最大只能上传"+normallimit/factor+"Mb的文件");
			return SUCCESS;
		}else if(isvip == 1 && size > viplimit){
			ServletActionContext.getRequest().setAttribute("message", "VIP用户最大只能上传"+viplimit/factor+"Mb的文件");
			return SUCCESS;
		}else  return "OK";
     }*/

}
