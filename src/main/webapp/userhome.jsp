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
<body style="width: 95%; margin-left: 2%">

 <div>
     <a href="<%=path %>/user/logout" >log out</a> &nbsp;
     <a href="<%=path %>/home.jsp" >首页</a> &nbsp;
 </div>
 <div style="font-size: 24px ; text-align: center">欢迎你登陆iCloud <div style="font-size: 20px; color: green;font-style: oblique; float:inherit; ">${username}</div></div>
 <hr  size="20"  color="blue"><br/>
 
 <div>
   <form action="<%=path %>/upload" method="post" enctype="multipart/form-data">
        <input type="submit" onclick="return checkfile()" value="上传文件" style="background: white;"/>
    	<input type="file" onchange="checkfile()" id="fileupload" name="file" onpropertychange="getFileSize(this.value)"/><br/>
    	<input type="hidden" name="username" value="${username}" /><br/>
        <img id="tempimg" dynsrc="" src="" style="display:none" />  
    	${message}
  </form>
 </div> 
 <hr style="color:red; size:2"/><br/>
 
  <div  style="text-align: center;">
    <table class="table table-striped">
    <thead>
    	<tr >
    		<td>文件名</td>
    		<td>文件大小</td>
    		<td>上传日期</td>
    		<td>下载文件</td>
    		<td>是否共享</td>
    		<td>操作</td>
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
	    		<td>
	    			<form>
	    		      <select  id="${c.id}" onchange="gochange(${pagebean.currentpage},${c.id})" >
	    		         <c:if test="${c.canshare==0 }">
    					         <option value="0">私有</option> 
    					         <option value="1" >共享</option> 
    					 </c:if>
	    		         <c:if test="${c.canshare==1 }">
   						         <option value="1" selected="selected">共享</option>
    					         <option value="0" >私有</option> 
 					     </c:if>
 					  </select>
 			    	</form>
 			    </td>
	    		<td>
	    		<a href="javascript:void(0)" onclick="godelete(${pagebean.currentpage},${c.id})">删除文件</a>
	    		</td>
    		</tr>
    	</c:forEach>  
    </tbody>	 	
    </table>
 </div>
 <div>
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
  
  </script>
  
 </body> 
 </html>