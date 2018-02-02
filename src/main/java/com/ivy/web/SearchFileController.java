package com.ivy.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ivy.entity.File;
import com.ivy.entity.PageBean;
import com.ivy.exception.NoNumberException;
import com.ivy.service.SearchFileService;
import com.ivy.util.PropertiesUtil;

@Controller
@RequestMapping("file")
public class SearchFileController {
	
	@Autowired
	private SearchFileService searchFileService;
	
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
			fileList = searchFileService.searchFile(pageBean);
			
			pageBean.setList(fileList);
			pageBean.setTotalrecord(searchFileService.countShareFiles(pageBean));
			
			model.addAttribute("pagebean", pageBean);
			model.addAttribute("searchcontent", searchcontent);
			return "showsearchfiles";
		} catch (NoNumberException e) {
			model.addAttribute("message", e.getMessage());
			return "userhome";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "There are no match file!");
			return "userhome";
		}
	}
	
	
/*	public int getPageSize() {
		PropertiesUtil.readProperties("icloud.properties");
		pagesize = Integer.parseInt(PropertiesUtil.getProperty("pageSize"));
		return pageSize;
	}
*/
}
