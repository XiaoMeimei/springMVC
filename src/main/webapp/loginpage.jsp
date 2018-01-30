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
<%-- <div class="panel panel-default">
  <div class="panel-heading">LOGIN</div>
  <div class="panel-body">
   <form class="form-horizontal" action="<%=path %>/user/login" method="post">
		  <div class="form-group">
		    <label for="username" class="col-sm-2 control-label">Username</label>
		    <div class="col-sm-6">
		      <input class="form-control" id="username" placeholder="username" name="username">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
		    <div class="col-sm-6">
		      <input type="password" class="form-control" id="inputPassword3" placeholder="Password" name="password">
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <div class="checkbox">
		        <label>
		          <input type="checkbox"> Remember me
		        </label>
		      </div>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-default">Sign in</button>
		    </div>
		  </div>
		</form>
  </div>
</div> --%>
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
