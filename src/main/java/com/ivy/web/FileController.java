package com.ivy.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ivy.entity.File;
import com.ivy.entity.PageBean;
import com.ivy.exception.NoNumberException;
import com.ivy.service.FileService;
import com.ivy.util.PropertiesUtil;

@Controller
@RequestMapping("file")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	private PageBean pageBean = new PageBean();
	
	
	@RequestMapping(value = "/searchFileWithPage", method = RequestMethod.GET)
	public String searchFileWithPage(@RequestParam(value = "searchcontent") String searchcontent, 
									 @RequestParam(value = "currentpage", defaultValue="0", required=false) int currentpage,
									 @RequestParam(value = "pagesize", defaultValue="5", required=false) int pagesize,
									 Model model) {
//		this.pageSize = getPageSize();
		List<File> fileList = new ArrayList<File>();
		try {
			searchcontent = new String(searchcontent.getBytes("iso8859-1"),"utf-8");
			if("".equals(searchcontent) || searchcontent==null){
				model.addAttribute("message", "There are no match file!");
				return "userhome";
			}
			pageBean.setCurrentpage(currentpage);
			pageBean.setPagesize(pagesize);
			pageBean.setSearchcontent(searchcontent);
			fileList = fileService.searchFile(pageBean);
			
			pageBean.setList(fileList);
			pageBean.setTotalrecord(fileService.countShareFiles(pageBean));
			
			model.addAttribute("pagebean", pageBean);
			model.addAttribute("searchcontent", searchcontent);
			return "showsearchfiles";
		} catch (NoNumberException e) {
			model.addAttribute("message", e.getMessage());
			return "home";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "There are errors to search files!");
			return "home";
		}
	}
	
	
	@RequestMapping(value = "/userFiles", method = RequestMethod.GET)
	public String userHome(HttpServletRequest request, HttpServletResponse response,
						   @RequestParam(value = "currentpage", defaultValue="0", required=false) int currentpage,
						   @RequestParam(value = "pagesize", defaultValue="5", required=false) int pagesize,
						   Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 
		request.getSession().setAttribute("username", username);
		try {
			pageBean.setCurrentpage(currentpage);
			pageBean.setPagesize(pagesize);
			List<File> fileList = fileService.searchUserFile(username, currentpage, pagesize);
			pageBean.setList(fileList);
			pageBean.setTotalrecord(fileService.countUserFiles(username));
			model.addAttribute("pagebean", pageBean);
		} catch (NoNumberException e) {
			model.addAttribute("message", e.getMessage());
			return "userhome";
		} catch (Exception e) {
			model.addAttribute("message", "There are errors to search files!");
			return "userhome";
		}
		return "userhome";
	}
	
	
	public int getPageSize() {
		PropertiesUtil.readProperties("icloud.properties");
		return Integer.parseInt(PropertiesUtil.getProperty("pageSize"));
	}
}
