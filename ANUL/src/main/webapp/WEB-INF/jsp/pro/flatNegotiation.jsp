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
		<form class="form-inline" id="flatSearchForm" method="POST">
			<div class="form-group">
				<label for="projectName">Project Name : </label>
				<select name="projectName" id="projectName" class="form-control">
					<option value="">Select One</option>
				</select>
			</div>
			
			<div class="form-group separator"></div>
			
			<div class="form-group">
				<label for="flatNo">Flat Number : </label>
				<input id="flatNo" name="flatNo" class="form-control" type="text">
			</div>
			
			<div class="form-group separator"></div>
			
			<button type="button" class="btn btn-default" id="searchBtn">Search</button>
		</form>
	</div>
</div>

<div id="flatDispDiv" class="panel panel-primary" style="margin: 10px 10px 20px;">

	<div class="panel-heading">
		<h3 class="panel-title">Flat Detail(s)</h3>
	</div>

	<div class="panel-body">
		<form>
			<table id="flatDispTable" class="table table-striped table-bordered" data-pagination="true">
				<thead>
					<tr align="center">
						<th>ID</th>
						<th>Project ID</th>
						<th>Project Name</th>
						<th>Flat No</th>
						<th>Flat Description</th>
						<th>Flat Facing</th>
						<th>Floor Name</th>
						<th>Area Flag</th>
						<th style="width: 90px;">BuiltUp Area</th>
						<th style="width: 130px;">Super BuiltUp Area</th>
						<th style="width: 90px;">Carpet Area</th>
						<th>Price Per Sq. Ft.</th>
						<th>Flat Price</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</form>
	</div>
</div>

<div id="flatDetailDiv"  class="panel panel-primary" style="margin: 10px 10px 20px;">
	<div class="panel-heading">
		<h3 class="panel-title">Flat Master Detail</h3>
	</div>
	
	<div class="panel-body">
		<form class="form-horizontal" id="flatMstForm" method="POST">
			<input type="hidden" name="id" id="id" value="" />
			<input type="hidden" name="projectId" id="projectId" value="" />
			
			<div class="row" style="padding-bottom: 10px;">
				<div class="col-md-4">
					<div class="form-group">
						<label for="projectName" class="col-sm-3">Projects Name : </label>
						<div class="col-sm-9">
							<input type="text" name="projectName" class="form-control" id="projectName" disabled="disabled"/>
						</div>
					</div>						
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label for="flatNo" class="col-sm-3">Flat No. : </label>
						<div class="col-sm-9">
							<input type="text" name="flatNo" class="form-control" id="flatNo" disabled="disabled"/>
						</div>
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label for="flatDesc" class="col-sm-3">Flat Description : </label>
						<div class="col-sm-9">
							<input type="text" name="flatDesc" class="form-control" id="flatDesc" disabled="disabled"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row" style="padding-bottom: 10px;">
				<div class="col-md-4">
					<div class="form-group">
						<label for="flatFacing" class="col-sm-3">Flat Facing : </label>
						<div class="col-sm-9">
							<input type="text" name="flatFacing" class="form-control" id="flatFacing" disabled="disabled"/>
						</div>
					</div>						
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label for="floorName" class="col-sm-3">Floor Name : </label>
						<div class="col-sm-9">
							<input type="text" name="floorName" class="form-control" id="floorName" disabled="disabled"/>
						</div>
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label for="areaFlag" class="col-sm-3">Area Flag : </label>
						<div class="col-sm-9">
							<select name="areaFlag" id="areaFlag" class="form-control" disabled="disabled">
								<option value="">Select One</option>
								<option value="B">Built Up Area</option>
								<option value="S">Super Built-Up Area</option>
								<option value="C">Carpet Area</option>
							</select>
						</div>
					</div>						
				</div>
			</div>
			
			<div id="areaDiv" class="row" style="padding-bottom: 10px;">			
				<div class="col-md-4">
					<div id="builtUpAreaDiv" class="form-group">
						<label id="builtUpAreaLbl" for="builtUpArea" class="col-sm-3">Built Up Area : </label>
						<div class="col-sm-9">
							<input type="text" name="builtUpArea" class="form-control" id="builtUpArea" disabled="disabled"/>
						</div>
					</div>
					
					<div id="sprBuiltUpAreaDiv" class="form-group">
						<label id="sprBuiltUpAreaLbl" for="sprBuiltUpArea" class="col-sm-3">Super Built-Up Area : </label>
						<div class="col-sm-9">
							<input type="text" name="sprBuiltUpArea" class="form-control" id="sprBuiltUpArea" disabled="disabled"/>
						</div>
					</div>
					
					<div id="carpetAreaDiv" class="form-group">
						<label id="carpetAreaLbl" for="carpetArea" class="col-sm-3">Carpet Area : </label>
						<div class="col-sm-9">
							<input type="text" name="carpetArea" class="form-control" id="carpetArea" disabled="disabled"/>
						</div>
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label for="pricePerSqFt" class="col-sm-3">Price Per Sq. Ft. : </label>
						<div class="col-sm-9">
							<input type="text" name="pricePerSqFt" class="form-control" id="pricePerSqFt" disabled="disabled"/>
						</div>
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label for="flatPrice" class="col-sm-3">Flat Price : </label>
						<div class="col-sm-9">
							<input type="text" name="flatPrice" class="form-control" id="flatPrice" disabled="disabled" />
						</div>
					</div>						
				</div>
			</div>
			
			<div class="row" style="padding-bottom: 10px;">
				<div class="col-md-4">
					<div class="form-group">
						<label for="paySchdType" class="col-sm-3">Pay Schedule Type : </label>
						<div class="col-sm-9">
							<select name="paySchdType" id="paySchdType" class="form-control">
								<option value="">Select One</option>
								<option value="A">Automatic</option>
								<option value="M">Manual</option>
							</select>
						</div>
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label for="finalFlatPrice" class="col-sm-3">Final Flat Price : </label>
						<div class="col-sm-9">
							<input type="text" name="finalFlatPrice" class="form-control" id="finalFlatPrice" />
						</div>
					</div>						
				</div>
			</div>
			
			<div class="form-group panel-footer">
				<button id="saveBtn" type="submit" class="btn btn-success">Save</button>
				<button type="button" id="reset" class="btn btn-primary">Reset</button>
			</div>
		</form>
	</div>
</div>

<script src="${pageContext.request.contextPath}/resources/js/pro/flatNegotiation.js"></script>