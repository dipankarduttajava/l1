<%@include file="../taglib.jsp" %>

<div class="container" style="margin-top:175px;">
	<c:if test="${param.error == 'sessionExpired'}">
	    <div class="alert alert-danger alert-error" style="margin-left: 300px; margin-right: 300px;">
	        <a href="#" class="close" data-dismiss="alert">&times;</a>
	        <strong>Error...!!</strong> Session Expired, Please Try Again.
	    </div>
	</c:if>

	<c:if test="${param.error == 'invalidUser'}">
	    <div class="alert alert-danger alert-error" style="margin-left: 300px; margin-right: 300px;">
	        <a href="#" class="close" data-dismiss="alert">&times;</a>
	        <strong>Error...!!</strong> Please Enter Valid Login-ID OR Password.
	    </div>
	</c:if>
	
	<div id="loginbox" style="margin-left: 350px;" class="mainbox col-md-5">
		<div class="panel panel-primary" style="box-shadow: 8px 8px 5px #888888; border-radius: 0px;">	
			<div class="panel-heading" style="border-radius: 0px; line-height: 2">
				<div class="panel-title">Sign In</div>
				<!-- <div style="float:right; font-size: 80%; position: relative; top:-10px"><a href="#">Forgot password?</a></div> -->
			</div>
	
			<div class="panel-body">
				<div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
				
				<form id="loginform" class="form-horizontal" action="${pageContext.request.contextPath}/j_spring_security_check" method="POST">		
					<div class="input-group" style="margin-bottom: 10px">
						<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
						<div class="col-sm-11">
							<input id="login-username" type="text" class="form-control" name="j_username" value="" placeholder="Login Id" required="required">
						</div>
					</div>
					
					<div class="input-group" style="margin-bottom: 10px">
						<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
						<div class="col-sm-11">
							<input id="login-password" type="password" class="form-control" name="j_password" placeholder="Password" required="required">
						</div>
					</div>
					
					<div class="form-group panel-footer" style="margin-bottom: 0px;">
						<!-- Button -->
						<button id="btn-login" class="btn btn-success" type="submit">Login</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>