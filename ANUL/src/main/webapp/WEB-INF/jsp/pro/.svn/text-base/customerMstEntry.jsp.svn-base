<%@include file="../../taglib.jsp"%>

<c:if test="${param.status == 'error'}">
	<div class="alert alert-danger alert-error" style="margin-left: 10px; margin-right: 10px; margin-top: 10px;">
		<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>Error..!!</strong>
		Process Unsuccessful.
	</div>
</c:if>

<c:if test="${param.status == 'success'}">
	<div class="alert alert-success" style="margin-left: 10px; margin-right: 10px; margin-top: 10px;">
		<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>Success..!!</strong>
		Process Successfully Completed.
	</div>
</c:if>

<div class="panel panel-primary" style="margin: 10px 10px 20px;">
	<div class="panel-heading">
		<h3 class="panel-title">Customer Master Entry</h3>
	</div>
	
	<div class="panel-body">
		<form class="form-horizontal" id="customerMstForm" method="POST">
			<input type="hidden" name="id" id="id" value="" />
			<div class="row" style="padding-bottom: 10px;">
				<div class="col-md-4">
					<div class="form-group">
						<label for="name1" class="col-sm-3">Name 1 : </label>
						<div class="col-sm-9">
							<input type="text" name="name1" class="form-control" id="name1" />
						</div>
					</div>						
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label for="name2" class="col-sm-3">Name 2 : </label>
						<div class="col-sm-9">
							<input type="text" name="name2" class="form-control" id="name2" />
						</div>
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label for="name3" class="col-sm-3">Name 3 : </label>
						<div class="col-sm-9">
							<input type="text" name="name3" class="form-control" id="name3" />
						</div>
					</div>
				</div>
			</div>
			
			<div class="row" style="padding-bottom: 10px;">
				<div class="col-md-4">
					<div class="form-group">
						<label for="contancPerson" class="col-sm-3">Contanc Person : </label>
						<div class="col-sm-9">
							<input type="text" name="contancPerson" class="form-control" id="contancPerson" />
						</div>
					</div>						
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label for="commAdd" class="col-sm-3">Common Address : </label>
						<div class="col-sm-9">
							<textarea rows="6" name="commAdd" class="form-control" id="commAdd" style="resize: none;"></textarea>
						</div>
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label for="permAdd" class="col-sm-3">Permanent Address : </label>
						<div class="col-sm-9">
							<textarea rows="6" name="permAdd" class="form-control" id="permAdd" style="resize: none;"></textarea>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row" style="padding-bottom: 10px;">
				<div class="col-md-4">
					<div class="form-group">
						<label for="eMail" class="col-sm-3">E-Mail : </label>
						<div class="col-sm-9">
							<input type="email" name="eMail" class="form-control" id="eMail" />
						</div>
					</div>						
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label for="mobile1" class="col-sm-3">Mobile 1 : </label>
						<div class="col-sm-9">
							<input type="text" name="mobile1" class="form-control" id="mobile1" />
						</div>
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label for="mobile2" class="col-sm-3">Mobile 2 : </label>
						<div class="col-sm-9">
							<input type="text" name="mobile2" class="form-control" id="mobile2" />
						</div>
					</div>
				</div>
			</div>
			
			<div class="row" style="padding-bottom: 10px;">
				<div class="col-md-4">
					<div class="form-group">
						<label for="mobile3" class="col-sm-3">Mobile 3 : </label>
						<div class="col-sm-9">
							<input type="text" name="mobile3" class="form-control" id="mobile3" />
						</div>
					</div>						
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label for="pan" class="col-sm-3">PAN : </label>
						<div class="col-sm-9">
							<input type="text" name="pan" class="form-control" id="pan" />
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

<div class="panel panel-primary" style="margin: 10px 10px 20px;">

	<div class="panel-heading">
		<h3 class="panel-title">Customer Master Details</h3>
	</div>

	<div class="panel-body">
		<form>
			<table id="custDispTable" class="table table-striped table-bordered" data-pagination="true">
				<thead>
					<tr align="center">
						<th>ID</th>
						<th>Name 1</th>
						<th>Name 2</th>
						<th>Name 3</th>
						<th>Contanc Person</th>
						<th>Common Address</th>
						<th>Permanent Address</th>
						<th>E-Mail</th>
						<th>Mobile 1</th>
						<th>Mobile 2</th>
						<th>Mobile 3</th>
						<th>PAN</th>
						<th>ACTION</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</form>
	</div>
</div>
<script src="${pageContext.request.contextPath}/resources/js/pro/customerMstEntry.js"></script>