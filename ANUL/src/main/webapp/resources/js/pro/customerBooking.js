$(document).ready(function(){
	$('#flatDetails').hide();
	$('#flatSummary').hide();
	$('#btn').hide();
	$('#customerList').hide();
	$('#customerInfo').hide();
	$('#milestoneUpdate').hide();
	
	
	$('#milestoneUpdateDate').datepicker({
		format : 'dd/mm/yyyy',
		autoclose: true
	});
	
	// populate select projectMstId
	$.getJSON(contextPath + "/projectMst/findAll.html",function(response){
		
		var projectMstId = $('#projectMstId');		
		$.each(response,function(key,val){
			$('<option>').val(val.id).text(val.projectName).appendTo(projectMstId);
			
		});
	});
	
	// on project chnage populate milestone
	$("#projectMstId").on('change',function(){
		
		var projectId = $(this).val();
		
		
		$.getJSON(contextPath + "/projectMilestone/findFlatNextProjectSpcMilstoneByProjectId.html",{ "projectId" : projectId },function(response){
			
			var projectMilestoneId = $('#projectMilestoneId');	
			
			projectMilestoneId.find('option').remove();
			$('<option>').val('').text('Select One').appendTo(projectMilestoneId);
			
			$.each(response,function(key,val){
				$('<option>').val(val.milestoneSrlNo).text(val.milestoneDesc).appendTo(projectMilestoneId);
				
			});
		});
	});
	
	//customer info search button click
	$("#primarySearchForm").formValidation({
		framework: 'bootstrap',
		icon: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			projectMstId: {
				 validators: {
					 notEmpty: {
						 message: 'Please Select Project Name'
					 }
				 }
			 },
			 projectMilestoneId: {
				 validators: {
					 notEmpty: {
						 message: 'Please Select Project Milestone'
					 }
				 }
			 }
		}
    })
    .on('success.form.fv', function(e) {
        // Prevent form submission
        e.preventDefault();
       var projectId = $('#projectMstId').val();
       var milestoneId = $('#projectMilestoneId').val();
       
        if(milestoneId == 1 || milestoneId == 2){
        	$('#customerInfo').show();
            $('#primarySearch').hide();
        }else{
        	$('#milestoneUpdate').show();
            $('#primarySearch').hide();
        }
        // Do whatever you want here ...
    });
	
	
	
	//milestone update form
	$("#milestoneUpdateForm").formValidation({
		framework: 'bootstrap',
		icon: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			milestoneUpdateDate: {
				 validators: {
					 notEmpty: {
						 message: 'Please Select Date'
					 }
				 }
			 }
			
		}
    })
    .on('success.form.fv', function(e) {
        // Prevent form submission
        e.preventDefault();
       var projectId = $('#projectMstId').val();
       var milestoneId = $('#projectMilestoneId').val();
       var dateGiven = $('#milestoneUpdateDate').val();
              
       $.ajax({url: contextPath + "/projectMilestone/updateProjectMilestone.html",
			type: "GET",
			data : {"projectId" : projectId, "milestoneId" : milestoneId, "dateGiven" : dateGiven},
			success: function(response){
				if(response == "true"){
					window.location.href = contextPath + "/pro/customerBooking.html?status=success";	
				}else{
					window.location.href = contextPath + "/pro/customerBooking.html?status=error";
				}
				
			},
			error: function(xhr){
				/*alert("An error occured: " + xhr.status + " " + xhr.statusText);*/
				window.location.href = contextPath + "/pro/customerBooking.html?status=error";	
			}
		});
    });
	
	
	//on customer search clicking button
$('#customerInfoSearch').click(function(){
		
		$('#customerList').show();
		
		var name = $('#name').val();
		var mobile = $('#mobile').val();
		var pan = $('#pan').val();
		var milestoneId = $('#projectMilestoneId').val();
		
		$('#table').dataTable({
            "bDestroy": true
        }).fnDestroy();
		
		if(milestoneId == 1){
			$('#table').dataTable({
		        "ajax": {
		            "url": contextPath + "/customerMst/findCustomerByNameMobilePan.html",
		            "type" : "POST",
		            "data" : {"name": name , "mobile" : mobile , "pan" : pan},
		            "dataSrc": ""
		        },
		        "columnDefs": [ {
		            "defaultContent": "<button>Click!</button>"
		        } ],
		        "columns": [
		            { "data": "id" },
		            { "data": "name1" },
		            { "data": "name2" },
		            { "data": "name3" },
		            { "data": "custType" },
		            { "data": "contancPerson" },
		            { "data": "commAddress" },
		            { "data": "permAddress" },
		            { "data": "email" },
		            { "data": "mobile1" },
		            { "data": "mobile2" },
		            { "data": "mobile3" },
		            { "data": "pan" },
		            {
		                data: null,
		                className: "center",
		                defaultContent: '<a href="#" class="editor_process" style="color: red; font-weight:bold;">Click Here</a>'
		            }
		        ]
		    });
		}else if(milestoneId == 2){
			
			$('#table').dataTable({
		        "ajax": {
		            "url": contextPath + "/customerMst/findCustomerForAggrementByNameMobilePan.html",
		            "type" : "POST",
		            "data" : {"name": name , "mobile" : mobile , "pan" : pan},
		            "dataSrc": ""
		        },
		        "columnDefs": [ {
		            "defaultContent": "<button>Click!</button>"
		        } ],
		        "columns": [
		            { "data": "id" },
		            { "data": "name1" },
		            { "data": "name2" },
		            { "data": "name3" },
		            { "data": "custType" },
		            { "data": "contancPerson" },
		            { "data": "commAddress" },
		            { "data": "permAddress" },
		            { "data": "email" },
		            { "data": "mobile1" },
		            { "data": "mobile2" },
		            { "data": "mobile3" },
		            { "data": "pan" },
		            {
		                data: null,
		                className: "center",
		                defaultContent: '<a href="#" class="editor_process" style="color: red; font-weight:bold;">Click Here</a>'
		            }
		        ]
		    });
		}
	});
	
	
	//search flat 
	$('#searchFlat').click(function(){
		$('#flatList').show();
		var milestoneId = $('#projectMilestoneId').val();
		var customerId = $('#table').find('tbody').find('tr').find('td').eq(0).text();
		var projectId = $('#projectMstId').val();
		var flatNo = $('#flatNo').val();
		
		if(milestoneId == 1){
			
			$('#flatTable').dataTable({
	            "bDestroy": true
	        }).fnDestroy();
			
			 $('#flatTable').dataTable({
			        "ajax": {
			            "url": contextPath + "/flatMst/findAvailableByProjectIdFlatNo.html",
			            "type" : "GET" ,
			            "data" : {"projectId" : projectId , "flatNo" : flatNo },
			            "dataSrc": ""
			        },
			        "columnDefs": [ {
			            "defaultContent": "<button>Click!</button>"
			        } ],
			        "columns": [
			            { "data": "id" },
			            { "data": "projectName" },
			            { "data": "flatNo" },
			            { "data": "flatDesc" },
			            { "data": "flatFacing" },
			            { "data": "floorName" },
			            { "data": "builtUpArea" },
			            { "data": "sprBuiltUpArea" },
			            { "data": "carpetArea" },
			            { "data": "flatPrice" },
			            { "data": "finalFlatPrice" },
			            {
			                data: null,
			                className: "center",
			                defaultContent: '<a href="#" class="flat_edit" style="color: red; font-weight:bold;">Click Here</a>'
			            }
			        ]
			    });
		}else if(milestoneId == 2){
			$('#flatTable').dataTable({
	            "bDestroy": true
	        }).fnDestroy();
			
			 $('#flatTable').dataTable( {
			        "ajax": {
			            "url": contextPath + "/flatMst/findForAggrementByCustomerIdFlatNo.html",
			            "type" : "GET" ,
			            "data" : {"customerId" : customerId , "flatNo" : flatNo },
			            "dataSrc": ""
			        },
			        "columnDefs": [ {
			            "defaultContent": "<button>Click!</button>"
			        } ],
			        "columns": [
			            { "data": "id" },
			            { "data": "projectName" },
			            { "data": "flatNo" },
			            { "data": "flatDesc" },
			            { "data": "flatFacing" },
			            { "data": "floorName" },
			            { "data": "builtUpArea" },
			            { "data": "sprBuiltUpArea" },
			            { "data": "carpetArea" },
			            { "data": "flatPrice" },
			            { "data": "finalFlatPrice" },
			            {
			                data: null,
			                className: "center",
			                defaultContent: '<a href="#" class="flat_edit" style="color: red; font-weight:bold;">Click Here</a>'
			            }
			        ]
			    });
		}
	});
	
	
	$('#flatTable').on('click','a.flat_edit',function(e){
		e.preventDefault();
		
		$('#flatTable').dataTable({
            "bDestroy": true
        }).fnDestroy();
		
		 $(this).closest('tr').nextAll().remove();
         $(this).closest('tr').prevAll().remove();
         $(this).hide();
         $('#flatSearch').hide();
         
         $('#btn').show();
	     
	});
	
	
	$('#table').on( 'click', 'a.editor_process', function (e) {
		$('#flatList').hide();
        e.preventDefault();
        
        var milestoneId = $('#projectMilestoneId').val();
        
        
        $('#table').dataTable({
            "bDestroy": true
        }).fnDestroy();
        
        	 $(this).closest('tr').nextAll().remove();
             $(this).closest('tr').prevAll().remove();
             $(this).hide();
             $('#searchCrit').hide();
         	 $('#flatDetails').show();
	});
	
	$('#backBtn').click(function(){
		window.history.back();
		return false;
	});
	
	
	$('#saveBtn').click(function(){
		var custId = $('#table').find('tbody').find('tr').find('td').eq(0).text();
		var flatId =  $('#flatTable').find('tbody').find('tr').find('td').eq(0).text();
		var projectId = $('#projectMstId').val();
		var milestoneId = $('#projectMilestoneId').val();
		
		if(milestoneId == 1){
			$.ajax({url: contextPath + "/customerMst/saveCustomerBooking.html",
				type: "GET",
				data: { "customerId" : custId, "flatId" : flatId, "projectId" : projectId },
				success: function(response){
					//alert("XXX");
					window.location.href = contextPath + "/pro/customerBooking.html?status=success";	
				},
				error: function(xhr){
					//alert("An error occured: " + xhr.status + " " + xhr.statusText);
					window.location.href = contextPath + "/pro/customerBooking.html?status=error";	
				}
			});
		}else if(milestoneId == 2){
			$.ajax({url: contextPath + "/customerMst/saveCustomerAggrement.html",
				type: "GET",
				data: { "customerId" : custId, "flatId" : flatId, "projectId" : projectId },
				success: function(response){
					//alert("XXX");
					window.location.href = contextPath + "/pro/customerBooking.html?status=success";	
				},
				error: function(xhr){
					//alert("An error occured: " + xhr.status + " " + xhr.statusText);
					window.location.href = contextPath + "/pro/customerBooking.html?status=error";	
				}
			});
		}
	});
});