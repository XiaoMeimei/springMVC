<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%String path= request.getContextPath();%>
<link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" />
<link href="<%=path %>/js/bootstrap.min.js" rel="stylesheet" />
<script src="<%=path %>/jquery/jquery-3.3.1.min.js"></script>

<title>iCloud搜索结果</title>
</head>
<body style="width: 95%; margin-left: 2%">

 <div>
     <a href="<%=path %>/home.jsp" >首页</a> &nbsp;
     <a href="<%=path %>/user/logout" >退出</a> &nbsp;
 </div>
 <div style="font-size: 24px ; text-align: center">欢迎你登陆iCloud <div style="font-size: 20px; color: green;font-style: oblique; float:inherit; ">${username}</div></div>
 <hr  size="20"  color="blue"><br/>
 
 <div>
   <form action="<%=path %>/uploadFile" method="post" enctype="multipart/form-data" id="uploadForm">
   		<div>${message}</div>
        <input type="submit" onclick="return checkfile()" value="上传文件" style="background: white;"/>
    	<input type="file" onchange="checkFile()" id="fileupload" name="multilpartFile" onpropertychange="getFileSize(this.value)" style="display:inline"/>
    	<input type="hidden" name="username" value="${username}" /><br/>
        <img id="tempimg" dynsrc="" src="" style="display:none" />  
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
	    		<td>${c.filesize }kb</td>
	    		<td>${c.createtime }</td>
	    		<td>
	    			<a href="">下载</a>
	    		</td>
	    		<td>
	    			<form>
	    		      <select  id="${c.id}" onchange="gochange(${c.id})" >
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
    	  if(pagesize > 10 || pagesize >= ${pagebean.totalrecord - pagebean.pagesize * ( pagebean.currentpage - 1 )}){
    		  pagesize = Math.min(pagesize,${pagebean.totalrecord});
    		  currentpage = 1 ;
    	  }else if(pagesize < 1){
    		  pagesize = 1;
    	  }
    	  
    /* 	  $.ajax({
		 		 "url":'/springMVC/file/userFiles?currentpage='+currentpage+'&pagesize='+ pagesize
	  	   }).success; */
    	  window.location.href = '/springMVC/file/userFiles?currentpage='+currentpage+'&pagesize='+ pagesize;
      }
      
      
      function godelete(currentpage,fileid){
    	  var pagesize = document.getElementById("pagesize").value;
    	  
    	  if(pagesize > 10 || pagesize >= ${pagebean.totalrecord - pagebean.pagesize * ( pagebean.currentpage - 1 )}){
    		  pagesize = Math.min(pagesize,${pagebean.totalrecord});
    		  currentpage = 1 ;
    	  }else if(pagesize < 1){
    		  pagesize = 1;
    	  }
    	  
    	  var r=confirm("确认删除文件？");
    	  if(r==true){
        	  window.location.href = '/springMVC/file/deleteFileByID?currentpage='+currentpage+'&pagesize='+ pagesize+'&id='+fileid;
    	  }else{
    		  return false;
    	  }
      }
      
      
        var vipmaxsize = 50*1024*1024 ;
        var normalmaxsize = 20*1024*1024 ;
        var viperrMsg = "VIP用户上传的附件文件不能超过50M！！！";
        var normalerrMsg = "普通用户上传的附件文件不能超过20M！！！";
        var tipMsg = "建议使用chrome firefox ie等浏览器";  
        var  browserCfg = {};
        //下面一段鉴别使用者的浏览器
        var ua = window.navigator.userAgent;
        if (ua.indexOf("MSIE")>=1){
            browserCfg.ie = true;
        }else if(ua.indexOf("Firefox")>=1){  
            browserCfg.firefox = true;  
        }else if(ua.indexOf("Chrome")>=1){  
            browserCfg.chrome = true;  
        }  
        function checkfile(){  
            try{  
                var obj_file = document.getElementById("fileupload"); 
                var isvip = ${isvip};
                if(obj_file.value==""){  
                    alert("请先选择上传文件");  
                    return false;  
               } 
                var filesize = 0;  
                if(browserCfg.firefox || browserCfg.chrome ){  
                    filesize = obj_file.files[0].size;  //chrome等浏览器支持这个方法拿到文件大小
                }else if(browserCfg.ie){  
                    var obj_img = document.getElementById('tempimg');  
                    obj_img.dynsrc=obj_file.value;  
                    filesize = obj_img.fileSize;  
                }else{  
                    alert(tipMsg);  
                return false;  
                }  
                if(filesize==-1){  
                    alert(tipMsg);  
                    return false;  
                }else if(isvip==1 && filesize>vipmaxsize){  
                    alert(viperrMsg);  
                    return false;  
                }else if(isvip==0 && filesize>normalmaxsize){
                    alert(normalerrMsg);  
                    return false;  
                }else{  
                    return true;  
                }  
            }catch(e){  
                alert(e); 
                return false; 
            } 
           }
      
      function gochange(fileid){
    	  
    	  var canshare = document.getElementById(fileid).value;
    	  var r=confirm("如果设置共享，您的文件将可以被其他人搜索到");
    	  if (r==true){
    	 	  $.ajax({
        		  url:"/springMVC/file/changeFileStatus",
        		  data:{
        			  "id":fileid,
        			  "canshare":canshare
        		  }
        		  
        	  })
    	  }else{
    		  location.reload();
    	  }
    	  
      }
      
      
      
  
  </script>
  
 </body> 
 </html>