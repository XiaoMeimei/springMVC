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
 <div class="panel panel-default" style="width: 40%; margin-left: 30%;">
  <div class="panel-heading">SING UP</div>
  <div class="panel-body">
   <form class="form-horizontal" action="<%=path %>/user/login" method="post">
		  <div class="form-group">
		    <label for="username" class="col-sm-4 control-label">Username</label>
		    <div class="col-sm-6">
		      <input class="form-control" id="username" placeholder="username" name="username">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputPassword" class="col-sm-4 control-label">Password</label>
		    <div class="col-sm-6">
		      <input type="password" class="form-control" id="inputPassword" placeholder="Password" name="password">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="confirmPassword" class="col-sm-4 control-label">Confirm Password</label>
		    <div class="col-sm-6">
		      <input type="password" class="form-control" id="confirmPassword" placeholder="Confirm Password" name="confirmPassword">
		    </div>
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
</html>
