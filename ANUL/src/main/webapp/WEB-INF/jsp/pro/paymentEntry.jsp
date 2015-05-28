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
		<form class="form-inline" id="paymentSearchForm" method="POST">
			<div class="form-group">
				<label for="invNo">Invoice Number : </label>
				<input id="invNo" name="invNo" class="form-control" type="text">
			</div>
			
			<div class="form-group separator"></div>
			
			<button type="button" class="btn btn-default" id="searchBtn">Search</button>
		</form>
	</div>
</div>

<div id="dispDiv" class="panel panel-primary" style="margin: 10px 10px 20px;">

	<div class="panel-heading">
		<h3 class="panel-title">Flat/Project/Milestone Detail(s)</h3>
	</div>

	<div class="panel-body">
		<form>
			<table id="dispTable" class="table table-striped table-bordered" data-pagination="true">
				<thead>
					<tr align="center">
						<th>ID</th>
						<th>Project ID</th>
						<th>Flat Pay Schedule ID</th>
						<th>Project Name</th>
						<th>Flat No</th>
						<th>Flat Description</th>
						<th>Flat Facing</th>
						<th>Floor Name</th>
						<th>Area Flag</th>
						<th style="width: 90px;">BuiltUp Area</th>
						<th style="width: 100px;">Super BuiltUp Area</th>
						<th style="width: 90px;">Carpet Area</th>
						<th>Price Per Sq. Ft.</th>
						<th>Flat Price</th>
						<th>Milestone</th>
						<th>Invoice No</th>
						<th>Invoice Date</th>
						<th>Invoice Amt</th>
						<th id="action">Action</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</form>
	</div>
</div>

<div id="paymentDispDiv" class="panel panel-primary" style="margin: 10px 10px 20px;">

	<div class="panel-heading">
		<h3 class="panel-title">Payment Detail(s)</h3>
	</div>

	<div class="panel-body">
		<form class="form-inline">
			<button type="button" class="btn btn-default" id="addBtn">Add Row</button>
			<hr>
			<table id="paymentDispTable" class="table table-striped table-bordered" data-pagination="true">
				<thead>
					<tr align="center">
						<th>Cash/DD/Cheque</th>
						<th>DD/Cheque No.</th>
						<th>Favour Of</th>
						<th>Bank</th>
						<th>Branch</th>
						<th>Cash/DD/Cheque Date</th>
						<th>Cash/DD/Cheque Amount</th>
						<th id="paymentAction">Action</th>
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

<script src="${pageContext.request.contextPath}/resources/js/pro/paymentEntry.js"></script>