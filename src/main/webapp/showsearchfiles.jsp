<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%String path= request.getContextPath();%>
<link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" />
<link href="<%=path %>/js/bootstrap.min.js" rel="stylesheet" />
<script src="<%=path %>/jquery/jquery/jquery-3.3.1.min.js"></script>

<title>iCloud搜索结果</title>
</head>
<body >

  <a href="<%=path %>/userhome.jsp" >首页</a> 
  <div style="text-align: center; width: 90%; margin-left: 5%">
  <br/>
  <font size="60px">iCloud搜索结果</font><br/><br/>
    
    <table class="table table-striped">
    <thead>
    	<tr >
    		<td>文件名</td>
    		<td>文件大小</td>
    		<td>创建日期</td>
    		<td>下载文件</td>
    	</tr>
    </thead>	
    <tbody>
    	<c:forEach var="c" items="${pagebean.list}" varStatus="stat">
    		<tr>
	    		<td>${c.filename }</td>
	    		<td>${c.filesize }</td>
	    		<td>${c.createtime }</td>
	    		<td>
	    			<a href="">下载</a>
	    		</td>
    		</tr>
    	</c:forEach>  
    </tbody>	 	
    </table>
    <br/>
    <div class="pagination" style="float:left">
	              共[${pagebean.totalrecord}]条记录,
	             每页 <input type="text" id = "pagesize" value="${pagebean.pagesize }" onchange="gotopage(${pagebean.currentpage})" style="width: 25px;" maxlength="5">条
	             共[${pagebean.totalpage}]页
    </div>
    <div style="float:right">
	    <nav aria-label="Page navigation">
		  <ul class="pagination">
		    <li>
		      <a href="javascript:void(0)" onclick="gotopage(${pagebean.previouspage})" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
		    <c:forEach var="pagenum" items="${pagebean.pagebar}">
		       <c:if test="${pagenum==pagebean.currentpage }">
		            <li class="active"><a href="#">${pagenum }</a></li>
		       </c:if>
		        <c:if test="${pagenum!=pagebean.currentpage }">
		        	<li><a href="javascript:void(0)" onclick="gotopage(${pagenum})">${pagenum}</a></li>
		       </c:if>
	         </c:forEach>
		    <li>
		      <a href="javascript:void(0)" onclick="gotopage(${pagebean.nextpage})" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
		  </ul>
		</nav>
    </div>
	
    <input type="hidden" id="searchcontent" value="${searchcontent}">
    </div>

 
  <script type="text/javascript">
      function gotopage(currentpage){
    	  
    	  var pagesize = document.getElementById("pagesize").value;
    	  var searchcontent = document.getElementById("searchcontent").value;
    	  
    	  if(pagesize > 10 || pagesize >= ${pagebean.totalrecord - pagebean.pagesize * ( pagebean.currentpage - 1 )}){
    		  pagesize = Math.min(pagesize,${pagebean.totalrecord});
    		  currentpage = 1 ;
    	  }else if(pagesize < 1){
    		  pagesize = 1;
    	  }
    	  
    	  
    	  window.location.href = '/springMVC/file/searchFileWithPage?currentpage='+currentpage+'&pagesize='+ pagesize+'&searchcontent='+searchcontent;
/*     	  $.ajax({
    		  "url":'/springMVC/file/searchFileWithPage?currentpage='+currentpage+'&pagesize='+ pagesize+'&searchcontent='+searchcontent
    	  }); */
      }
  
  </script>
  
 </body> 
 </html>