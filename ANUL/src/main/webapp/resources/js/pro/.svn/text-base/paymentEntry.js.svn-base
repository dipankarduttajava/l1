$(document).ready(function(){
	
	var flatInvoiceComboId = 0;
	var flatPaySchdId = 0;
	
	$('#dispDiv').hide();
	$('#paymentDispDiv').hide();
	$('#btn').hide();
	
	$('#searchBtn').click(function(){
		$('#dispDiv').show();
		
		$('#dispTable').dataTable({
            "bDestroy": true
        }).fnDestroy();
		
		var invNo = $('#invNo').val();
		
		$('#dispTable').dataTable({
	        "ajax": {
	            "url": contextPath + "/flatInvoiceCombo/findByInvoiceNo.html",
	            "type" : "GET",
	            "data" : {"invoiceNo": invNo},
	            "dataSrc": ""
	        },
	        "columnDefs": [ {
	            "defaultContent": "<button>Click!</button>"
	        } ],
	        "columns": [
	            { "data": "id" },
	            { "data": "projectMstId" },
	            { "data": "flatPaySchdId" },
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
	            { "data": "projectMilestoneDesc" },	            
	            { "data": "invoiceNo" },
	            { "data": "invoiceDate" },
	            { "data": "invoiceAmt" },
	            {
	                data: null,
	                className: "center",
	                defaultContent: '<a href="#" class="editor_process" style="color: red; font-weight:bold;">Click Here</a>'
	            }
	        ]
	    });
	});
	
	$('#dispTable').on( 'click', 'a.editor_process', function (e) {
		$('#flatList').hide();
		$('#paymentDispDiv').show();
		$('#btn').show();
		
        e.preventDefault();                
        
        $('#dispTable').dataTable({
            "bDestroy": true
        }).fnDestroy();
        
        flatInvoiceComboId = $(this).closest('tr').find('td').eq(0).html();
        flatPaySchdId = $(this).closest('tr').find('td').eq(2).html();
        
		$(this).closest('tr').nextAll().remove();
		$(this).closest('tr').prevAll().remove();
		$(this).hide();
		$('#primarySearch').hide();
		$('#action').hide();
		$('.center').hide();
	});
	
	
	/* Adding Row */ 
    $('#addBtn').on( 'click', function () {   	
    	$('#paymentDispTable').dataTable().fnAddData([
    	          '<select class="form-control payMode"> <option value="">Select One</option> <option value="cash">Cash</option> <option value="dd">DD</option> <option value="chq">Cheque</option> </select>',
    	          '<input class="form-control chqddNo" type="text">',
    	          '<input class="form-control favourOf" type="text">',
    	          '<input class="form-control bank" type="text">',
    	          '<input class="form-control branch" type="text">',
    	          '<input class="form-control payDate" type="text">',
    	          '<input class="form-control payAmt" type="text">',
    	          '<a href="#" class="deleteRow" style="color: red; font-weight:bold;"> Delete </a>'
    	        ]);
    	});
    
    
    /* Remove Row */
    var payTable = $('#paymentDispTable').DataTable();
    
    $(document).on('click', 'a.deleteRow', function () {
    	payTable.row($(this).parents('tr')).remove().draw();
    });

    
    /* SAVE & UPDATE */
    $('#saveBtn').click(function(){
    	var table = [];    	
    	$('#paymentDispTable tbody tr').each(function(){
    		payMode = $(this).find('.payMode').val();
        	chqddNo = $(this).find('.chqddNo').val();
        	favourOf = $(this).find('.favourOf').val();
        	bank = $(this).find('.bank').val();
        	branch = $(this).find('.branch').val();
        	payDate = $(this).find('.payDate').val();
        	payAmt = $(this).find('.payAmt').val();
        	
        	
        	table.push({
        		"rcptMode" : payMode,
    			"chdNo" : chqddNo,
    			"favourOf" : favourOf,
    			"chddBank" : bank,
    			"chddBankBranch" : branch,
    			"chddDate" : payDate,
    			"chddAmount" : payAmt,
				"flatInvoiceComboId" : flatInvoiceComboId,
				"flatPaySchdId" : flatPaySchdId
        	});
		});
    	
    	var data = {'receiptDtlsDtos' : table };
    	/*alert(JSON.stringify(table));*/
    	    	
    	$.ajax({
    		url: contextPath + "/receiptDtls/saveReceiptDtls.html",
			type: "POST",
			data: JSON.stringify(data),
			contentType: "application/json",
			success: function(response){
				if(response == "true"){
					window.location.href = contextPath + "/pro/paymentEntry.html?status=success";	
				}else{
					window.location.href = contextPath + "/pro/paymentEntry.html?status=error";	
				}
			},
			error: function(xhr){
				alert("An error occured: " + xhr.status + " " + xhr.statusText);
				window.location.href = contextPath + "/pro/paymentEntry.html?status=error";	
			}
		});
	});
});

$(document).on('focus', '.payDate', function () {
	$(this).datepicker({
		format : 'dd/mm/yyyy',
		autoclose: true
	});
	return false;
});

/* Disabling Fields */
$(document).on('change', '.payMode', function () {
	var payMode = $(this).val();
	
	if(payMode == "cash"){
		$(this).closest('tr').find('td').find('.chqddNo').attr("disabled", "disabled");
		$(this).closest('tr').find('td').find('.favourOf').attr("disabled", "disabled");
		$(this).closest('tr').find('td').find('.bank').attr("disabled", "disabled");
		$(this).closest('tr').find('td').find('.branch').attr("disabled", "disabled");
	}else{
		$(this).closest('tr').find('td').find('.chqddNo').removeAttr("disabled");
		$(this).closest('tr').find('td').find('.favourOf').removeAttr("disabled");
		$(this).closest('tr').find('td').find('.bank').removeAttr("disabled");
		$(this).closest('tr').find('td').find('.branch').removeAttr("disabled");
	}
});