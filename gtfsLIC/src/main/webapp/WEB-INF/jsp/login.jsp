<%@include file="taglib.jsp" %> 
     
     <div class="container">
	<form:form id="login" cssClass="form-signin" method="post" action="/gtfsLIC/spring/login">
      	<h5 class="form-signin-heading" style="color:red">${message}</h5>
        <h3 class="form-signin-heading">Please sign in</h3>
        
        <label for="loginid" class="sr-only">Login Id</label>
        <input type="text" name="loginid" id="loginid" class="form-control" placeholder="Login Id" required autofocus>
        <label for="password" class="sr-only">Password</label>
        <input type="password" name="password" id="password" class="form-control" placeholder="Password" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form:form>

    </div> <!-- /container -->
     
    
    
    


