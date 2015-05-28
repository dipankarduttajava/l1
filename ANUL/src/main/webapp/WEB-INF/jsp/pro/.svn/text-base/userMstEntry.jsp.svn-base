<%@include file="../../taglib.jsp"%>

<c:if test="${param.status == 'error'}">
	<div class="alert alert-danger alert-error" style="margin-left: 10px; margin-right: 10px; margin-top: 10px;">
		<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>Error!</strong>
		Process Unsuccessful.
	</div>
</c:if>

<c:if test="${param.status == 'success'}">
	<div class="alert alert-success" style="margin-left: 10px; margin-right: 10px; margin-top: 10px;">
		<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>Success!</strong>
		Process Successfully Completed.
	</div>
</c:if>


<div class="panel panel-primary" style="margin: 10px 10px 20px;">
	<div class="panel-heading">
		<h3 class="panel-title">User Master Entry</h3>
	</div>
	
	<div class="panel-body">
		<form class="form-horizontal" id="userMstForm" method="POST">
			<input type="hidden" name="id" id="id" value="" />			
			
			<div class="form-group">
				<label for="userName" class="col-sm-2">User Name : </label>
				<div class="col-sm-3">
					<input type="text" name="userName" class="form-control" id="userName"/>
				</div>
			</div>	
			<div class="form-group">
				<label for="loginId" class="col-sm-2">Login Id : </label>
				<div class="col-sm-3">
					<input type="text" name="loginId" class="form-control" id="loginId"/>
				</div>
			</div>		
			<div class="form-group">
				<label for="password" class="col-sm-2">Password : </label>
				<div class="col-sm-3">
					<input type="password" name="password" class="form-control" id="password" />
				</div>
			</div>					
			<div class="form-group">
				<label for="confirm" class="col-sm-2">Confirm : </label>
				<div class="col-sm-3">
					<input type="password" name="confirm" class="form-control" id="confirm" />
				</div>
			</div>									
				
			
				<div class="form-group panel-footer">
					<button id="saveBtn" type="submit" class="btn btn-success">Save</button>
					<button type="button" id="reset" class="btn btn-primary">Reset</button>
				</div>		
		</form>
	</div>
</div>









<div class="panel panel-primary" style="margin: 10px 10px 20px;">

	<div class="panel-heading">
		<h3 class="panel-title">User Master Detail(s)</h3>
	</div>

	<div class="panel-body">
		<form>
			<table id="userDispTable" class="table table-striped table-bordered" data-pagination="true">
				<thead>
					<tr align="center">
						<th>ID</th>
						<th>User Name</th>
						<th>User Type</th>
						<th>Login ID</th>
						<th>Password</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</form>
	</div>
</div>
<script src="${pageContext.request.contextPath}/resources/js/pro/userMstEntry.js"></script>