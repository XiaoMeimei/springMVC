<!doctype html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<style>body{padding-top: 60px;}</style>
	<%String path= request.getContextPath();%>
	
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" />
    <link href="<%=path %>/css/signin.css" rel="stylesheet">
 
	<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
	
	<script src="<%=path %>/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="<%=path %>/jquery/jquery-3.3.1.min.js"></script>
	<script src="<%=path %>/jquery/jquery.validate.min.js"></script>
	
 <script type="text/javascript">
 
        $(function(){
            var validate = $("#registerForm").validate({
                debug: true, //调试模式取消submit的默认提交功能   
                //errorClass: "label.error", //默认为错误的样式类为：error   
                focusInvalid: false, //当为false时，验证无效时，没有焦点响应  
                onkeyup: false,   
                submitHandler:function(form){
                    alert("提交事件!");   
                    form.submit();
                },
                rules:{
                    username:{
                        required:true
                    },
                    email:{
                        required:true,
                        email:true
                    },
                    password:{
                        required:true,
                        minlength: 5
                    },
                    confirmPassword:{
                        equalTo:"#password"
                    }                    
                },
                messages:{
                    username:{
                        required:"必填"
                    },
                    email:{
                        required:"必填",
                        email:"E-Mail格式不正确"
                    },
                    password:{
                        required: "不能为空",
                        minlength: "密码长度不能小于 5 个字母"
                    },
                    confirmPassword:{
                        equalTo:"两次密码输入不一致"
                    }                                    
                }
                          
            });    
    
        });
        </script>

</head>
<body>
 <div class="panel panel-default" style="width: 40%; margin-left: 30%;">
  <div class="panel-heading">SING UP</div>
  <div class="panel-body">
   <form class="form-horizontal" action="/springMVC/user/register" method="post" id="registerForm">
		  <div class="form-group">
		    <label for="username" class="col-sm-4 control-label">Username</label>
		    <div class="col-sm-6">
		      <input class="form-control" id="username" placeholder="username" name="username" onblur="validateUserName()">
		    </div>
		    <div id="usernameMsg" ></div>
		  </div>
		  <div class="form-group">
		    <label for="inputPassword" class="col-sm-4 control-label">Password</label>
		    <div class="col-sm-6">
		      <input type="password" class="form-control" id="password" placeholder="Password" name="password">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="confirmPassword" class="col-sm-4 control-label">Confirm Password</label>
		    <div class="col-sm-6">
		      <input type="password" class="form-control" id="confirmPassword" placeholder="Confirm Password" name="confirmPassword">
		    </div>
		    <div id="passwordMsg"></div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-4">
		      <div class="checkbox">
		        <label>
		          <input type="checkbox"> Remember me
		        </label>
		      </div>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-4">
		      <button type="submit" class="btn btn-default">Sign Up</button>
		    </div>
		  </div>
		</form>
  </div>
</div>
</body>

<script type="text/javascript">
function validateUserName() {
	
	$.ajax({
		"url":"/springMVC/user/validateUsername",
		"method": "GET",
		"data":{
			username:$("#username").val()
			}
	}).done(function(data){
		if (data == "true") {
			$("#usernameMsg").html("用户名已存在");
			$("#usernameMsg").css('color','red');
		} else {
			$("#usernameMsg").html("有效的用户名");
			$("#usernameMsg").css('color','green');
		}
	});
}

</script>
</html>
