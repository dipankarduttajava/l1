$(document).ready(function() {
	
	$('#areaDiv').hide();
	
	$('#builtUpArea').hide();
	$('#sprBuiltUpArea').hide();
	
	$('#carpetArea').hide();
	$('#builtUpAreaLbl').hide();
	
	$('#sprBuiltUpAreaLbl').hide();
	$('#carpetAreaLbl').hide();
	
	$.getJSON(contextPath + "/projectMst/findAll.html",function(response){
		
		var projectName = $('#projectName');
		
		$.each(response,function(key,val){
			$('<option>').val(val.id).text(val.projectName).appendTo(projectName);
			
		});
	});

	$('#bookingDatePicker').datepicker({
		format : 'dd/mm/yyyy',
		autoclose: true
	}).on('changeDate', function(e) {
		$('#flatMstForm').formValidation('revalidateField', 'bookingDate');
	});
	
	$('#argmntForSaleDatePicker').datepicker({
		format : 'dd/mm/yyyy',
		autoclose: true
	}).on('changeDate', function(e) {
		$('#flatMstForm').formValidation('revalidateField', 'argmntForSale');
	});
	
	
	$("#areaFlag").on('change',function(){
		var areaFlag = $(this).val();
		$('#pricePerSqFt').val('');
		$('#builtUpArea').val('');
		$('#sprBuiltUpArea').val('');
		$('#carpetArea').val('');
		$('#flatPrice').val('');
		
		$('#areaDiv').show();
		
		if(areaFlag == "B"){
			
			$('#builtUpAreaDiv').show();
			$('#builtUpAreaLbl').show();
			$('#builtUpArea').show();
			
			$('#sprBuiltUpAreaDiv').hide();
			$('#sprBuiltUpAreaLbl').hide();
			$('#sprBuiltUpArea').hide();
			
			$('#carpetAreaDiv').hide();
			$('#carpetAreaLbl').hide();
			$('#carpetArea').hide();
			
		}else if(areaFlag == "S"){
			$('#builtUpAreaDiv').hide();
			$('#builtUpAreaLbl').hide();
			$('#builtUpArea').hide();			
			
			$('#sprBuiltUpAreaDiv').show();
			$('#sprBuiltUpAreaLbl').show();
			$('#sprBuiltUpArea').show();
			
			$('#carpetAreaDiv').hide();
			$('#carpetAreaLbl').hide();
			$('#carpetArea').hide();
			
		}else if(areaFlag == "C"){
			$('#builtUpAreaDiv').hide();
			$('#builtUpAreaLbl').hide();
			$('#builtUpArea').hide();
			
			$('#sprBuiltUpAreaDiv').hide();
			$('#sprBuiltUpAreaLbl').hide();
			$('#sprBuiltUpArea').hide();
			
			$('#carpetAreaDiv').show();
			$('#carpetAreaLbl').show();
			$('#carpetArea').show();
		}else{
			$('#areaDiv').hide();
		}
	});
	
	$("#pricePerSqFt").on('change',function(){
		
		var areaFlag = $('#areaFlag').val();
		var pricePerSqFt = $('#pricePerSqFt').val();
		
		if(areaFlag == "B"){
			var builtUpArea = $('#builtUpArea').val();
			
			if(builtUpArea != null){
				$('#flatPrice').val(builtUpArea * pricePerSqFt);
			}
		}
		
		if(areaFlag == "S"){
			var sprBuiltUpArea = $('#sprBuiltUpArea').val();
			
			if(sprBuiltUpArea != null){
				$('#flatPrice').val(sprBuiltUpArea * pricePerSqFt);
			}
		}
		
		if(areaFlag == "C"){
			var carpetArea = $('#carpetArea').val();
			
			if(carpetArea != null){
				$('#flatPrice').val(carpetArea * pricePerSqFt);
			}
		}
	});
	
	
	/* Save And Reset*/
	$("#flatMstForm").formValidation({
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
						 message: 'Please Select Project Name'
					 }
				 }
			 },
			 flatNo: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Flat Number'
					 }
				 }
			 },
			 flatDesc: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Flat Description'
					 }
				 }
			 },
			 areaFlag: {
				 validators: {
					 notEmpty: {
						 message: 'Please Choose Area Type'
					 }
				 }
			 },
			 builtUpArea: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Built-Up Area'
					 },
					 integer: {
	                        message: 'The value is not an integer'
	                 }
				 }
			 },
			 suprBuiltupArea:{
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Super Built-Up Area'
					 },
					 integer: {
	                        message: 'The value is not an integer'
	                 }
				 }
			 },
			 carpetArea: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Carpet Area'
					 },
					 integer: {
	                        message: 'The value is not an integer'
	                 }
				 }
			 },
			 pricePerSqFt: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Price Per Sq. Ft.'
					 },
					 integer: {
	                        message: 'The value is not an integer'
	                 }
				 }
			 },
			 flatPrice: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Flat Price'
					 },
					 integer: {
	                        message: 'The value is not an integer'
	                 }
				 }
			 },
			 totalPrice: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Total Price'
					 },
					 integer: {
	                        message: 'The value is not an integer'
	                 }
				 }
			 },
			 bookingStatus: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Booking Status'
					 }
				 }
			 },
			 bookingDate: {
				 validators: {
					 notEmpty: {
						 message: 'Please Select Booking Date'
					 }
				 }
			 },
			 argmntForSale: {
				 validators: {
					 notEmpty: {
						 message: 'Please Select Arrangement For Sale Date'
					 }
				 }
			 }
		}
    })
    .on('success.form.fv', function(e) {
        // Prevent form submission
        e.preventDefault();
        
        var id = $('#id').val();
        var projectName =  $('#projectName').val();
        var flatNo = $('#flatNo').val();
        var flatDesc = $('#flatDesc').val();
        var flatFacing = $('#flatFacing').val();
        var floorName = $('#floorName').val();
        var areaFlag = $('#areaFlag').val();
        var builtUpArea = $('#builtUpArea').val();
        var sprBuiltUpArea = $('#sprBuiltUpArea').val();
        var carpetArea = $('#carpetArea').val();
        var pricePerSqFt = $('#pricePerSqFt').val();
        var flatPrice = $('#flatPrice').val();
        
        /*
        var otherCharges = $('#otherCharges').val();
        var noOfCarPark = $('#noOfCarPark').val();
        var carParkValue = $('#carParkValue').val();
        var totalPrice = $('#totalPrice').val();
        var bookingStatus = $('#bookingStatus').val();
        var bookingDate = $('#bookingDate').val();
        var argmntForSale = $('#argmntForSale').val();
        var finalFlatPrice = $('#finalFlatPrice').val();
        */
		
		var data = {
					'id' : id,
					'projectMstId': projectName,
					'flatNo': flatNo,
					'flatDesc': flatDesc,
					'flatFacing': flatFacing,
					'floorName': floorName,
					'areaFlag' : areaFlag,
					'builtUpArea': builtUpArea, 
					'sprBuiltUpArea': sprBuiltUpArea,
					'carpetArea': carpetArea,
					'pricePerSqft': pricePerSqFt,
					'flatPrice': flatPrice,
					/*
					'otherChrgs': otherCharges, 
					'noOfCarPark': noOfCarPark, 
					'carParkValue': carParkValue, 
					'totalPrice': totalPrice, 
					'bookingStatus': bookingStatus,					
					'bookingDate': bookingDate, 
					'agrmntFSaleDate': argmntForSale,
					'finalFlatPrice': finalFlatPrice
					*/
					};
		
		/*alert(JSON.stringify(data));*/
		
		$.ajax({
			url: contextPath + "/flatMst/saveOrUpdateForFlatEntry.html",
			type: "POST",
			data: JSON.stringify(data),
			contentType: "application/json",
			success: function(response){
				if(response == "true"){
					window.location.href = contextPath + "/pro/flatMstEntry.html?status=success";
				}else{
					window.location.href = contextPath + "/pro/flatMstEntry.html?status=error";	
				}
				
			},
			error: function(xhr){
				window.location.href = contextPath + "/pro/flatMstEntry.html?status=error";	
			}
		});
    });
	
	
	$("#reset").click(function(){
		 $('#projectName').val('');
		 $('#flatNo').val('');
		 $('#flatDesc').val('');
		 $('#flatFacing').val('');
		 $('#floorName').val('');
		 $('#areaFlag').val('');
		 $('#builtUpArea').val('');
		 $('#sprBuiltupArea').val('');
		 $('#carpetArea').val('');
		 $('#pricePerSqFt').val('');
		 $('#flatPrice').val('');
		 /*
		 $('#otherCharges').val('');
		 $('#noOfCarPark').val('');
		 $('#carParkValue').val('');
		 $('#totalPrice').val('');
		 $('#bookingStatus').val('');
		 $('#bookingDate').val('');
		 $('#argmntForSale').val('');
		 */
	});
	
	
	$('#flatDispTable').dataTable({
        "ajax": {
            "url": contextPath + "/flatMst/findAll.html",
            "dataSrc": ""
        },
        "columnDefs": [{
            "defaultContent": "<button>Click!</button>"
        }],
        "columns": [
            { "data": "id" },
            { "data": "projectMstId" },
            { "data": "projectName" },
            { "data": "flatNo" },
            { "data": "flatDesc" },
            { "data": "flatFacing" },
            { "data": "floorName" },
            { "data": "areaFlag" },
            { "data": "builtUpArea" },
            { "data": "sprBuiltUpArea" },
            { "data": "carpetArea" },
            { "data": "pricePerSqft" },
            { "data": "flatPrice" },
            {
                data: null,
                className: "center",
                defaultContent: '<a href="#" class="editor_edit" style="color: red; font-weight:bold;">Edit</a> / <a href="" class="editor_remove" style="color: red; font-weight:bold;">Delete</a>'
            }
        ]
    });
	
	$('#flatDispTable').on( 'click', 'a.editor_edit', function (e) {
        e.preventDefault();
        
        var id = $(this).closest('tr').find('td').eq(0).html();
        var projectId = $(this).closest('tr').find('td').eq(1).html();
        /*var projectName = $(this).closest('tr').find('td').eq(1).html();*/
        var flatNo = $(this).closest('tr').find('td').eq(3).html();
        var flatDesc = $(this).closest('tr').find('td').eq(4).html();
        var flatFacing = $(this).closest('tr').find('td').eq(5).html();
        var floorName = $(this).closest('tr').find('td').eq(6).html();
        var areaFlag = $(this).closest('tr').find('td').eq(7).html();
        var builtUpArea = $(this).closest('tr').find('td').eq(8).html();
        var sprBuiltUpArea = $(this).closest('tr').find('td').eq(9).html();
        var carpetArea = $(this).closest('tr').find('td').eq(10).html();
        var pricePerSqFt = $(this).closest('tr').find('td').eq(11).html();
        var flatPrice = $(this).closest('tr').find('td').eq(12).html();
        
        $('#id').val(id);        
    	$('#projectName').val(projectId);
    	$('#flatNo').val(flatNo);
    	$('#flatDesc').val(flatDesc);
    	$('#flatFacing').val(flatFacing);
    	$('#floorName').val(floorName);
    	$('#areaFlag').val(areaFlag);
    	
    	if(areaFlag != null){
    		$('#areaDiv').show();
    		
    		if(areaFlag == "B" && builtUpArea != null){
        		$('#builtUpAreaDiv').show();
    			$('#builtUpAreaLbl').show();
    			$('#builtUpArea').show();
    			
    			$('#sprBuiltUpAreaDiv').hide();
    			$('#sprBuiltUpAreaLbl').hide();
    			$('#sprBuiltUpArea').hide();
    			
    			$('#carpetAreaDiv').hide();
    			$('#carpetAreaLbl').hide();
    			$('#carpetArea').hide();
    			
        		$('#builtUpArea').val(builtUpArea);
        		
        	}else if(areaFlag == "S" && sprBuiltUpArea != null){
        		$('#builtUpAreaDiv').hide();
    			$('#builtUpAreaLbl').hide();
    			$('#builtUpArea').hide();
    			
    			$('#sprBuiltUpAreaDiv').show();
    			$('#sprBuiltUpAreaLbl').show();
    			$('#sprBuiltUpArea').show();
    			
    			$('#carpetAreaDiv').hide();
    			$('#carpetAreaLbl').hide();
    			$('#carpetArea').hide();
    			
    			$('#sprBuiltUpArea').val(sprBuiltUpArea);
    			
        	}else if(areaFlag == "C" && carpetArea != null){
        		$('#builtUpAreaDiv').hide();
    			$('#builtUpAreaLbl').hide();
    			$('#builtUpArea').hide();
    			
    			$('#sprBuiltUpAreaDiv').hide();
    			$('#sprBuiltUpAreaLbl').hide();
    			$('#sprBuiltUpArea').hide();
    			
    			$('#carpetAreaDiv').show();
    			$('#carpetAreaLbl').show();
    			$('#carpetArea').show();
    			
        		$('#carpetArea').val(carpetArea);
        	}
    	}
    	
    	$('#pricePerSqFt').val(pricePerSqFt);
    	$('#flatPrice').val(flatPrice);
	});
	
	
	$('#flatDispTable').on( 'click', 'a.editor_remove', function (e) {
        e.preventDefault();
        
        var flatId = $(this).closest('tr').find('td').eq(0).html();
                
        $.ajax({url: contextPath + "/flatMst/delete.html?flatId=" + flatId,
			success: function(response){
				
				if(response == "true"){
					window.location.href = contextPath + "/pro/flatMstEntry.html?status=success";
				}else{
					alert("Deletion Not Successful");
				}
					
			},
			error: function(xhr){
				alert("An error occured: " + xhr.status + " " + xhr.statusText);
				window.location.href = contextPath + "/pro/flatMstEntry.html?status=error";		
			}
		});
	});
});