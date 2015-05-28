$(document).ready(function(){
	
	//on load populate table
	$('#userDispTable').dataTable({
        "ajax": {
            "url": contextPath + "/userMst/findAll.html",
            "dataSrc": ""
        },
        "columnDefs": [{
            "defaultContent": "<button>Click!</button>"
        }],
        "columns": [
            { "data": "id" },
            { "data": "userName" },
            { "data": "userType" },
            { "data": "loginId" },
            { "data": "password" },
            {
                data: null,
                className: "center",
                defaultContent: '<a href="#" class="editor_edit" style="color: red; font-weight:bold;">Edit</a> / <a href="" class="editor_remove" style="color: red; font-weight:bold;">Delete</a>'
            }
        ]
    });
	
	
	
	//on save
	$("#userMstForm").formValidation({
		framework: 'bootstrap',
		icon: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			userName: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter User Name'
					 }
				 }
			 },
			 password: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Password'
					 },
					 identical: {
		                    field: 'confirm',
		                    message: 'The password and its confirm are not the same'
		             }
				 }
			 },
			 confirm: {
				 validators: {
					 notEmpty: {
						 message: 'Please Enter Confirm Password'
					 },
					 identical: {
		                    field: 'password',
		                    message: 'The password and its confirm are not the same'
		             }
					 
				 }
			 }
		}
    })
    .on('success.form.fv', function(e) {
        // Prevent form submission
        e.preventDefault();
        var id = $('#id').val();
        var userName = $('#userName').val();
		var password = $('#password').val();
		var loginId = $('#loginId').val();
		var userType = "S";
		
		var data = {
				'id' : id,
				'userName' : userName,
				'password' : password,
				'userType' : userType,
				'loginId'  : loginId
				};
		
		$.ajax({url: contextPath + "/userMst/saveOrUpdate.html",
			type: "POST",
			data: JSON.stringify(data),
			contentType: "application/json",
			success: function(response){
				
			// window.location = response.redirect;
			/*if (response.redirect) {
			window.location.href = response.redirect;
			}*/
				window.location.href = contextPath + "/pro/userMstEntry.html?status=success";	
			},
			error: function(xhr){
				//alert("An error occured: " + xhr.status + " " + xhr.statusText);
				window.location.href = contextPath + "/pro/userMstEntry.html?status=error";	
			}
		});

        // Do whatever you want here ...
    });
	
	$('#userDispTable').on( 'click', 'a.editor_edit', function (e) {
        e.preventDefault();
        
        var id = $(this).closest('tr').find('td').eq(0).html();
    	var userName = $(this).closest('tr').find('td').eq(1).html();
    	var loginId = $(this).closest('tr').find('td').eq(3).html();
    	var password = $(this).closest('tr').find('td').eq(4).html();
    	
    	$('#id').val(id);
    	$('#userName').val(userName);
    	$('#loginId').val(loginId);
    	$('#password').val(password);
	});
	
	$('#userDispTable').on( 'click', 'a.editor_remove', function (e) {
        e.preventDefault();
        var val = $(this).closest('tr').find('td').eq(0).html();
    	
    	var data = {"id": val};
    	
    	alert(JSON.stringify(data));
	});
});