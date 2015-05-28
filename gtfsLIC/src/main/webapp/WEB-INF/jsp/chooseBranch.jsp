<%@include file="taglib.jsp" %>


 <div class="container">
		
      <form id="login" class="form-signin" method="post" action="/gtfsLIC/spring/chooseBranch">
      	<h5 class="form-signin-heading" style="color:red">${message}</h5>
        <h3 class="form-signin-heading">Choose Branch</h3>
        
        <label for="loginid" class="sr-only">Select Branch*</label>
       <select name="branchId" class="form-control" required autofocus>
       		<option value="">Select One</option>
	       	<c:forEach items="${branches}" var="row">
	       		<option value="${row.branchId}">${row.branchName}</option>
	       	</c:forEach>
		</select>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
      </form>

    </div> <!-- /container -->
