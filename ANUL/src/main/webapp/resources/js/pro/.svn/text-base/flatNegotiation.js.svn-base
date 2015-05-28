$(document).ready(function(){
	$('#flatDispDiv').hide();
	$('#flatDetailDiv').hide();
	
	$.getJSON(contextPath + "/projectMst/findAll.html",function(response){
		
		var projectName = $('#flatSearchForm #projectName');
		
		$.each(response,function(key,val){
			$('<option>').val(val.id).text(val.projectName).appendTo(projectName);
		});
	});
	
	$('#searchBtn').click(function(){
		$('#flatDispDiv').show();
		$('#flatDetailDiv').hide();
		
		var projectId = $('#flatSearchForm #projectName').val();
		var flatNo = $('#flatSearchForm #flatNo').val();
		
		$('#flatDispTable').dataTable({
            "bDestroy": true
        }).fnDestroy();
		
		$('#flatDispTable').dataTable({
	        "ajax": {
	            "url": contextPath + "/flatMst/findByProjectIdFlatNo.html",
	            "type" : "GET" ,
	            "data" : {"projectId" : projectId , "flatNo" : flatNo },
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
	                defaultContent: '<a href="#" class="flat_edit" style="color: red; font-weight:bold;">Click Here</a>'
	            }
	        ]
	    });
	});
	

	$('#flatDispTable').on('click', 'a.flat_edit', function(e) {
		e.preventDefault();
	
		$('#flatDispDiv').hide();
		$('#primarySearch').hide();
		$('#flatDetailDiv').show();
		
		var id = $(this).closest('tr').find('td').eq(0).html();
		var projectId = $(this).closest('tr').find('td').eq(1).html();
		var projectName = $(this).closest('tr').find('td').eq(2).html();
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
		$('#flatMstForm #projectId').val(projectId);
    	$('#flatMstForm #projectName').val(projectName);
    	$('#flatMstForm #flatNo').val(flatNo);
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
	
	$("#flatMstForm").formValidation({
		framework: 'bootstrap',
		icon: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			finalFlatPrice: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Final Flat Price'
					 },
					 integer: {
	                        message: 'Please Enter Correct Value'
	            	}
				}
			},
			paySchdType: {
				 validators: {
					 notEmpty: {
						 message: 'Please Select Pay Schedule Type'
					 }
				}
			}
		}
	})
	.on('success.form.fv', function(e) {
		e.preventDefault();
		
		var id = $('#id').val();
        var projectId =  $('#flatMstForm #projectId').val();
        var flatNo = $('#flatMstForm #flatNo').val();
        var flatDesc = $('#flatMstForm #flatDesc').val();
        var flatFacing = $('#flatMstForm #flatFacing').val();
        var floorName = $('#flatMstForm #floorName').val();
        var areaFlag = $('#flatMstForm #areaFlag').val();
        var builtUpArea = $('#flatMstForm #builtUpArea').val();
        var sprBuiltUpArea = $('#flatMstForm #sprBuiltUpArea').val();
        var carpetArea = $('#flatMstForm #carpetArea').val();
        var pricePerSqFt = $('#flatMstForm #pricePerSqFt').val();
        var flatPrice = $('#flatMstForm #flatPrice').val();
        var paySchdType = $('#flatMstForm #paySchdType').val();
        var finalFlatPrice = $('#flatMstForm #finalFlatPrice').val();
        
        var data = {
				'id' : id,
				'projectMstId': projectId,
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
				'paySchdType' : paySchdType,
				'finalFlatPrice': finalFlatPrice
				};
        
        /*alert(JSON.stringify(data));*/
        
       $.ajax({
			url: contextPath + "/flatMst/saveOrUpdateForFlatNegotiation.html",
			type: "POST",
			data: JSON.stringify(data),
			contentType: "application/json",
			success: function(response){
				if(response == "true"){
					window.location.href = contextPath + "/pro/flatNegotiation.html?status=success";
				}else{
					window.location.href = contextPath + "/pro/flatNegotiation.html?status=error";	
				}
			},
			error: function(xhr){
				window.location.href = contextPath + "/pro/flatNegotiation.html?status=error";	
			}
		});
	});
});