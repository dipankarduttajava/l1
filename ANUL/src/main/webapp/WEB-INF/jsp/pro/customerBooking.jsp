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



<div class="panel panel-primary" style="margin: 10px 10px 20px;" id="primarySearch">

	<div class="panel-heading">
		<h3 class="panel-title">Primary Search Criteria</h3>
	</div>

	<div class="panel-body">
		<form class="form-inline" id="primarySearchForm" method="post">

			<div class="form-group">
				<label for="name">Project Name :*</label>
				<select name="projectMstId" id="projectMstId" class="form-control">
					<option value="">Select One</option>
				</select>
			</div>

			<div class="form-group separator"></div>

			<div class="form-group">
				<label for="mobile">Project Milestone :*</label>
				<select name="projectMilestoneId" id="projectMilestoneId" class="form-control">
					<option value="">Select One</option>
				</select>
			</div>

			<div class="form-group separator"></div>

			<button type="submit" class="btn btn-default" id="primarySearchSubmit">Search</button>
		</form>
	</div>
</div>


<div class="panel panel-primary" style="margin: 10px 10px 20px;" id="milestoneUpdate">
	<div class="panel-heading">
			<h3 class="panel-title">Milestone Update</h3>
	</div>
	
	<div class="panel-body">
		
		<form class="form-inline" id="milestoneUpdateForm" method="post">
			<div class="form-group">
				<label for="milestoneUpdateDate" class="col-sm-3">Date :* </label>
				<div id="milestoneDate" class="col-sm-9 dateContainer">
					<div class="input-group input-append date">
						<input type="date" name="milestoneUpdateDate" class="form-control" id="milestoneUpdateDate" />
						<span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
					</div>
				</div>
			</div>

			<div class="form-group separator"></div>

			<button type="submit" class="btn btn-default" id="milestoneUpdateSubmit">Update Milestone</button>
		</form>
		
	</div>
</div>





<div class="panel panel-primary" style="margin: 10px 10px 20px;" id="customerInfo">
	<div class="panel-heading">
			<h3 class="panel-title">Customer Details</h3>
	</div>
	
	<div class="panel-body">
		<div class="panel panel-primary" style="margin: 5px 5px 10px;" id="searchCrit">
		
			<div class="panel-heading">
				<h3 class="panel-title">Search Criteria</h3>
			</div>
		
			<div class="panel-body">
				<form class="form-inline" id="bookingSearch" method="post">
		
					<div class="form-group">
						<label for="name">Name:</label>
						<input type="text" class="form-control" id="name" placeholder="Enter Name">
					</div>
		
					<div class="form-group separator"></div>
		
					<div class="form-group">
						<label for="mobile">Mobile:</label>
						<input type="text" class="form-control" id="mobile" placeholder="Enter Mobile">
					</div>
		
					<div class="form-group separator"></div>
		
					<div class="form-group">
						<label for="pan">PAN:</label>
						<input type="text" class="form-control" id="pan" placeholder="Enter PAN">
					</div>
		
					<div class="form-group separator"></div>
		
					<button type="button" class="btn btn-default" id="customerInfoSearch">Search</button>
				</form>
			</div>
		</div>
	</div>

	<div class="panel panel-primary" style="margin: 5px 5px 10px;" id="customerList">
		<div class="panel-heading">
			<h3 class="panel-title">Customer List(s)</h3>
		</div>
	
		<div class="panel-body">
			<form>
				<table id="table" class="table table-striped table-bordered" data-pagination="true">
					<thead>
						<tr>
							<th>ID</th>
							<th>Name1</th>
							<th>Name2</th>
							<th>Name3</th>
							<th>Customer Type</th>
							<th>Contact Person</th>
							<th>Common Address</th>
							<th>Permanent Address</th>
							<th>Email</th>
							<th>Mobile1</th>
							<th>Mobile2</th>
							<th>Mobile3</th>
							<th>PAN</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
	
					</tbody>
				</table>
			</form>
		</div>
	</div>
</div>

<div class="panel panel-primary" style="margin: 10px 10px 20px;" id="flatDetails">

	<div class="panel-heading">
		<h3 class="panel-title">Flat Information</h3>
	</div>

	<div class="panel-body">
		<div class="panel panel-primary" style="margin: 5px 5px 10px;" id="flatSearch">

			<div class="panel-heading">
				<h3 class="panel-title">Search Criteria</h3>
			</div>

			<div class="panel-body">
				<form class="form-inline" id="flatSearchForm" method="post">
					<div class="form-group">
						<label for="flatNo">Flat No : </label> 
						<input type="text" class="form-control" id="flatNo" placeholder="Enter Flat No">
					</div>
					
					<div class="form-group separator"></div>

					<button type="button" class="btn btn-default" id="searchFlat">Search</button>
				</form>
			</div>
		</div>

		<div class="panel panel-primary" style="margin: 5px 5px 10px;" id="flatList">

			<div class="panel-heading">
				<h3 class="panel-title">Flat List(s)</h3>
			</div>

			<div class="panel-body">
				<form class="form-inline" id="flatListForm" method="post">
					<table id="flatTable" class="table table-striped table-bordered" data-pagination="true">
						<thead>
							<tr>
								<th>ID</th>
								<th>Project Name</th>
								<th>Flat No</th>
								<th>Flat Description</th>
								<th>Flat Facing</th>
								<th>Floor Name</th>
								<th style="width: 90px;">BuiltUp Area</th>
								<th style="width: 130px;">Super BuiltUp Area</th>
								<th style="width: 90px;">Carpet Area</th>
								<th>Flat Price</th>
								<th>Final Flat Price</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>

						</tbody>
					</table>
				</form>
			</div>
		</div>
		
		<div class="form-group panel-footer" id="btn">
			<button id="saveBtn" type="submit" class="btn btn-success">Save</button>
			<button id="backBtn" type="button" class="btn btn-primary">Back</button>
		</div>
		
	</div>
</div>

<script src="${pageContext.request.contextPath}/resources/js/pro/customerBooking.js"></script>