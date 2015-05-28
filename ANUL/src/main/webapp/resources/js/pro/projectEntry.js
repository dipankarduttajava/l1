$(document).ready(function(){
	
	/*$.getJSON("/projectMst/findAll.html",function(response){
		$.each(response,function(key,val){
			$("#table tbody").append(
					"<tr>" +
						"<td>" + val.projectName + "</td>"+
						"<td>" + val.address + "</td>"+
						"<td>" + val.noOfFloor + "</td>"+
						"<td>" + val.noOfFlat + "</td>"+
						"<td>" + val.noOfCarPark + "</td>"+
						"<td>" + val.noOfFlatAvlbl + "</td>"+
						"<td>" + val.noOfCarParkAvlbl + "</td>"+
						"<td>" + val.milestoneCompltd + "</td>"+
						"<td>" + val.chqInFavour + "</td>"+
					"</tr>");
		});
	});*/
	
	
	 $('#table').dataTable( {
	        "ajax": {
	            "url": contextPath + "/projectMst/findAll.html",
	            "dataSrc": ""
	        },
	        "columnDefs": [ {
	            "defaultContent": "<button>Click!</button>"
	        } ],
	        "columns": [
	            { "data": "id" },
	            { "data": "projectName" },
	            { "data": "address" },
	            { "data": "noOfFloor" },
	            { "data": "noOfFlat" },
	            { "data": "noOfCarPark" },
	            { "data": "noOfFlatAvlbl" },
	            { "data": "noOfCarParkAvlbl" },
	            { "data": "milestoneCompltd" },
	            { "data": "chqInFavour" },
	            {
	                data: null,
	                className: "center",
	                defaultContent: '<a href="#" class="editor_edit" style="color: red; font-weight:bold;">Edit</a> / <a href="" class="editor_remove" style="color: red; font-weight:bold;">Delete</a>'
	            }
	        ]
	    } );
	
	 $('#table').on( 'click', 'a.editor_remove', function (e) {
	        e.preventDefault();
	        var val = $(this).closest('tr').find('td').eq(0).html();
        	
        	var data = {"id": val};
        	
        	$.ajax({url: contextPath + "/projectMst/delete.html",
    			type: "POST",
    			data: JSON.stringify(data),
    			contentType: "application/json",
    			success: function(response){
    				
    				if(parseInt(response) > 0){
    					window.location.href = contextPath + "/pro/projectEntry.html?status=success";
    				}else{
    					alert("Deleteion not processed ");
    				}
    					
    			},
    			error: function(xhr){
    				window.location.href = contextPath + "/pro/projectEntry.html?status=error";		
    			}
    		});
	 });
	
	 $('#table').on( 'click', 'a.editor_edit', function (e) {
	        e.preventDefault();
	        
	        var id = $(this).closest('tr').find('td').eq(0).html();
        	var projectName = $(this).closest('tr').find('td').eq(1).html();
        	var address = $(this).closest('tr').find('td').eq(2).html();
        	var noOfFloor = $(this).closest('tr').find('td').eq(3).html();
        	var noOfFlat = $(this).closest('tr').find('td').eq(4).html();
        	var noOfCarPark = $(this).closest('tr').find('td').eq(5).html();
        	var chqInFavour = $(this).closest('tr').find('td').eq(9).html();
        	
        	$('#id').val(id);
        	$('#projectName').val(projectName);
        	$('#address').val(address);
        	$('#noOfFloor').val(noOfFloor);
        	$('#noOfFlat').val(noOfFlat);
        	$('#noOfCarPark').val(noOfCarPark);
        	$('#chqInFavour').val(chqInFavour);
	 });
	
	 
	$("#projectForm").formValidation({
		framework: 'bootstrap',
		icon: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			projectName: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Project Name'
					 }
				 }
			 },
			 address: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Address'
					 }
				 }
			 },
			 noOfFloor: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Number Of Floors'
					 },
					 integer: {
	                        message: 'The value is not an Integer/Numeric'
	                 }
				 }
			 },
			 noOfFlat: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Number Of Flats'
					 },
					 integer: {
	                        message: 'The value is not an Integer/Numeric'
	                 }
				 }
			 },
			 noOfCarPark: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Number Of Car Parks'
					 },
					 integer: {
	                        message: 'The value is not an Integer/Numeric'
	                 }
				 }
			 },
			 chqInFavour: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Cheque In Favour'
					 }
				 }
			 }
		}
    })
    .on('success.form.fv', function(e) {
        // Prevent form submission
        e.preventDefault();
        var id = $('#id').val();
        var projectName = $('#projectName').val();
		var address = $('#address').val();
		var noOfFloor = $('#noOfFloor').val();
		var noOfFlat = $('#noOfFlat').val();
		var noOfCarPark = $('#noOfCarPark').val();
		var chqInFavour = $('#chqInFavour').val();
		
		var data = {
				'id' : id,
				'projectName' : projectName,
				'address' : address,
				'noOfFloor' : noOfFloor,
				'noOfFlat' : noOfFlat,
				'noOfCarPark' : noOfCarPark,
				'chqInFavour' :chqInFavour
				};
		
		$.ajax({url: contextPath + "/projectMst/saveOrUpdate.html",
			type: "POST",
			data: JSON.stringify(data),
			contentType: "application/json",
			success: function(response){
				
			// window.location = response.redirect;
			/*if (response.redirect) {
			window.location.href = response.redirect;
			}*/
				window.location.href = contextPath + "/pro/projectEntry.html?status=success";	
			},
			error: function(xhr){
				//alert("An error occured: " + xhr.status + " " + xhr.statusText);
				window.location.href = contextPath + "/pro/projectEntry.html?status=error";	
			}
		});

        // Do whatever you want here ...
    });
	
	$("#reset").click(function(){
		 $('#projectName').val('');
		 $('#address').val('');
		 $('#noOfFloor').val('');
		 $('#noOfFlat').val('');
		 $('#noOfCarPark').val('');
		 $('#chqInFavour').val();
	});
	
});