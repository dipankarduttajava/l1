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
		<h3 class="panel-title">Product Master Entry</h3>
	</div>

	<div class="panel-body">
		<form class="form-horizontal" id="projectForm" method="post">
			<input type="hidden" name="id" id="id" value="" />
			<div class="form-group">
				<label for="projectName" class="col-sm-2">Project Name : </label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="projectName" name="projectName">
				</div>
			</div>

			<div class="form-group">
				<label for="address" class="col-sm-2">Address : </label>
				<div class="col-sm-3">
					<textarea class="form-control" id="address" rows="6" name="address" style="resize: none;"></textarea>
				</div>
			</div>

			<div class="form-group">
				<label for="noOfFloor" class="col-sm-2">Number Of Floor : </label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="noOfFloor" name="noOfFloor">
				</div>
			</div>

			<div class="form-group">
				<label for="noOfFlat" class="col-sm-2">Number Of Flat : </label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="noOfFlat" name="noOfFlat">
				</div>
			</div>

			<div class="form-group">
				<label for="noOfCarPark" class="col-sm-2">Number Of Car Park : </label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="noOfCarPark" name="noOfCarPark">
				</div>
			</div>
			
			<div class="form-group">
				<label for="chqInFavour" class="col-sm-2">Cheque In Favour : </label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="chqInFavour" name="chqInFavour">
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
		<h3 class="panel-title">Product Master Details</h3>
	</div>

	<div class="panel-body">
		<form>
			<table id="table" class="table table-striped table-bordered"
				data-pagination="true">
				<thead>
					<tr>
						<th>ID</th>
						<th>Project Name</th>
						<th>Address</th>
						<th>No. Of Floor</th>
						<th>No. Of Flat</th>
						<th>No. Of Car Park</th>
						<th>No. Of Flat Available</th>
						<th>No. Of Car Park Available</th>
						<th>Milestone Completed</th>
						<th>Cheque In Favour</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</form>
	</div>
</div>
<script src="${pageContext.request.contextPath}/resources/js/pro/projectEntry.js"></script>