$(document).ready(function(){
	
	//on load populate table
	$('#custDispTable').dataTable({
        "ajax": {
            "url": contextPath + "/customerMst/findAll.html",
            "dataSrc": ""
        },
        "columnDefs": [{
            "defaultContent": "<button>Click!</button>"
        }],
        "columns": [
            { "data": "id" },
            { "data": "name1" },
            { "data": "name2" },
            { "data": "name3" },
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
                defaultContent: '<a href="#" class="editor_edit" style="color: red; font-weight:bold;">Edit</a> / <a href="" class="editor_remove" style="color: red; font-weight:bold;">Delete</a>'
            }
        ]
    });
	
	
	//on save validation process
	$("#customerMstForm").formValidation({
		framework: 'bootstrap',
		icon: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			name1: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Name1'
					 }
				 }
			 },
			 name2: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Name2'
					 }
				 }
			 },
			 name3: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Name3'
					 }
				 }
			 },
			 contancPerson: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Contact Person'
					 }
				 }
			 },
			 commAdd: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Common Address'
					 }
				 }
			 },
			 permAdd: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Common Address'
					 }
				 }
			 },
			 eMail: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Email'
					 }
				 }
			 },
			 mobile1: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Mobile No'
					 }
				 }
			 },
			 pan: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter PAN'
					 }
				 }
			 }
		}
    })
    .on('success.form.fv', function(e) {
        // Prevent form submission
        e.preventDefault();
        var id = $('#id').val();
        var name1 = $('#name1').val();
		var name2 = $('#name2').val();
		var name3 = $('#name3').val();
		var contancPerson = $('#contancPerson').val();
		var commAdd = $('#commAdd').val();
		var permAdd = $('#permAdd').val();
        var eMail = $('#eMail').val();
		var mobile1 = $('#mobile1').val();
		var mobile2 = $('#mobile2').val();
		var mobile3 = $('#mobile3').val();
		var pan = $('#pan').val();
		
		var data = {'id' : id,'name1': name1, 'name2': name2, 'name3': name3, 'contancPerson': contancPerson, 'commAddress': commAdd, 'permAddress': permAdd, 'email': eMail, 'mobile1': mobile1, 'mobile2': mobile2, 'mobile3': mobile3,  'pan': pan};
		
		$.ajax({
			url: contextPath + "/customerMst/saveOrUpdate.html",
			type: "POST",
			data: JSON.stringify(data),
			contentType: "application/json",
			success: function(response){
				if(response == "true"){
					window.location.href = contextPath + "/pro/customerMstEntry.html?status=success";
				}else{
					window.location.href = contextPath + "/pro/customerMstEntry.html?status=error";
				}
			},
			error: function(xhr){
				window.location.href = contextPath + "/pro/customerMstEntry.html?status=error";
			}
		});
    });
	
	
	$('#custDispTable').on( 'click', 'a.editor_edit', function (e) {
        e.preventDefault();
        
        var id = $(this).closest('tr').find('td').eq(0).html();
    	var name1 = $(this).closest('tr').find('td').eq(1).html();
    	var name2 = $(this).closest('tr').find('td').eq(2).html();
    	var name3 = $(this).closest('tr').find('td').eq(3).html();
    	var contancPerson = $(this).closest('tr').find('td').eq(4).html();
    	var commAdd = $(this).closest('tr').find('td').eq(5).html();
    	var permAdd = $(this).closest('tr').find('td').eq(6).html();
    	var eMail = $(this).closest('tr').find('td').eq(7).html();
    	var mobile1 = $(this).closest('tr').find('td').eq(8).html();
    	var mobile2 = $(this).closest('tr').find('td').eq(9).html();
    	var mobile3 = $(this).closest('tr').find('td').eq(10).html();
    	var pan = $(this).closest('tr').find('td').eq(11).html();
    	
    	$('#id').val(id);
    	$('#name1').val(name1);
    	$('#name2').val(name2);
    	$('#name3').val(name3);
    	$('#contancPerson').val(contancPerson);
    	$('#commAdd').val(commAdd);
    	
    	$('#permAdd').val(permAdd);
    	$('#eMail').val(eMail);
    	$('#mobile1').val(mobile1);
    	$('#mobile2').val(mobile2);
    	$('#mobile3').val(mobile3);
    	$('#pan').val(pan);
	});
	
	
	$('#custDispTable').on( 'click', 'a.editor_remove', function (e) {
        e.preventDefault();
        var id = $(this).closest('tr').find('td').eq(0).html();
    	    	
    	$.ajax({url: contextPath + "/customerMst/delete.html?customerId=" + id,
			type: "GET",
			success: function(response){
				if(response == "true"){
					window.location.href = contextPath + "/pro/customerMstEntry.html?status=success";
				}else{
					window.location.href = contextPath + "/pro/customerMstEntry.html?status=error";
				}
			},
			error: function(xhr){
				window.location.href = contextPath + "/pro/customerMstEntry.html?status=error";
			}
		});
	});
});