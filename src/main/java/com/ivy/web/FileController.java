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

import com.ivy.entity.UploadFile;
import com.ivy.entity.PageBean;
import com.ivy.entity.UserInfo;
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
									 @RequestParam(value = "currentpage", defaultValue="1", required=false) int currentpage,
									 @RequestParam(value = "pagesize", defaultValue="5", required=false) int pagesize,
									 Model model) {
//		this.pageSize = getPageSize();
		List<UploadFile> fileList = new ArrayList<UploadFile>();
		try {
			searchcontent = new String(searchcontent.getBytes("iso8859-1"),"utf-8");
			if("".equals(searchcontent) || searchcontent==null){
				model.addAttribute("message", "There are no match file!");
				return "home";
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
						   @RequestParam(value = "currentpage", defaultValue="1", required=false) int currentpage,
						   @RequestParam(value = "pagesize", defaultValue="5", required=false) int pagesize,
						   Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserInfo userInfo = (UserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer isvip = userInfo.getIsvip();
		request.getSession().setAttribute("username", username);
		request.getSession().setAttribute("isvip", isvip);
		System.out.println("vip==="+isvip);
		try {
			pageBean.setCurrentpage(currentpage);
			pageBean.setPagesize(pagesize);
			List<UploadFile> fileList = fileService.searchUserFile(username, pageBean.getStartindex(), pagesize);
			pageBean.setList(fileList);
			pageBean.setTotalrecord(fileService.countUserFiles(username));
			model.addAttribute("pagebean", pageBean);
			return "userhome";
		} catch (NoNumberException e) {
			model.addAttribute("message", e.getMessage());
			return "userhome";
		} catch (Exception e) {
			model.addAttribute("message", "There are errors to search files!");
			return "userhome";
		}
		
	}
	
	@RequestMapping(value = "/deleteFileByID", method = RequestMethod.GET)
	public String deleteFileByID(HttpServletRequest request,
						   @RequestParam(value = "currentpage", defaultValue="1", required=false) int currentpage,
						   @RequestParam(value = "pagesize", defaultValue="5", required=false) int pagesize,
						   @RequestParam(value = "id") int id,
						   Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		request.getSession().setAttribute("username", username);
		try {
			fileService.deleteFileById(id);
			pageBean.setCurrentpage(currentpage);
			pageBean.setPagesize(pagesize);
			List<UploadFile> fileList = fileService.searchUserFile(username, pageBean.getStartindex(), pagesize);
			pageBean.setList(fileList);
			pageBean.setTotalrecord(fileService.countUserFiles(username));
			model.addAttribute("pagebean", pageBean);
			return "userhome";
		} catch (NoNumberException e) {
			model.addAttribute("message", e.getMessage());
			return "userhome";
		} catch (Exception e) {
			model.addAttribute("message", "There are errors to search files!");
			return "userhome";
		}
		
	}
	
	@RequestMapping(value = "/changeFileStatus", method = RequestMethod.GET)
	public void changeFileStatus(@RequestParam(value = "id") int id, @RequestParam(value = "canshare") int canshare) {

		try {
			fileService.updateFileById(id, canshare);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public int getPageSize() {
		PropertiesUtil.readProperties("icloud.properties");
		return Integer.parseInt(PropertiesUtil.getProperty("chooseDealerTxt"));
	}
}
