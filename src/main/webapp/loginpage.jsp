<!doctype html>
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

</head>
<body>
	<a href="/springMVC/registerpage.jsp" style="margin-left:10px">REGISTER</a>
	<a href="/springMVC/user/login" style="margin-left:20px">LOGIN OUT</a>
	
	
<div class="container">
	  <div id="login-error">${error}</div>
      <form class="form-signin"  action="../j_spring_security_check" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputUsername" class="sr-only">Username</label>
        <input id="inputUsername" class="form-control" placeholder="Username" required="" autofocus="" type="username" name="j_username">
        <label for="inputPassword" class="sr-only">Password</label>
        <input id="inputPassword" class="form-control" placeholder="Password" required="" type="password" name="j_password">
        <div class="checkbox">
          <label>
            <input value="remember-me" type="checkbox"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>

</div>
</body>
</html>
