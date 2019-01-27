
var id = localStorage.getItem("userId");

$("#logout").click(function() {
	localStorage.clear();

});


function toggleAlert(clasz, display){
    $(".alert")
        .removeClass("display")
        .removeClass("alert-info")
        .removeClass("alert-success")
        .removeClass("alert-danger")
        .addClass(clasz);
    if(display){
        $(".alert").addClass("display")
    }
    if(clasz === "alert-success"){
        $(".alert > span").text('Profile saved');
    }else if(clasz === "alert-danger"){
        $(".alert > span").text('Profile reset');
    }
}

$('#login').submit(function(event) {

	event.preventDefault();
	var formdata = $(login).serialize();
	$.ajax({

		type: 'POST',
		url: '/login',
		data: formdata,

		success: function(data){

			if(data.status == "FAILED"){
				alert(data.message);	
			}
			if(data.status == "SUCCESS") {
				var id = data.userId;
				localStorage.setItem("userId", id);

				window.location = '/Profile.html';
			}
		},
		error: function( jqXhr, textStatus, errorThrown ){
			alert( " fail connection" );
		}
	});
}); 




$('#register').submit(function(event) {

	event.preventDefault();

	var $registerForm = $(register);

	var formInputs = {
		password: $registerForm.find('#password').val(),
		confPassword: $registerForm.find('#conf_password').val(),
	};
	var error = validateRegisterForm(formInputs);
	if (error.length > 0) {
		$registrationError = $('#registration_error');
		$registrationError.addClass('display');
		$registrationError.find(' > span ').text('Error: ' + error);
		
		return;
	}

	$.ajax({

		type: 'POST',
		url: '/register',
		data: $registerForm.serialize(),

		success: function(data){     

			if(data.status == "FAILED"){

				window.location = '/Register.html';
				alert(data.message);
			}
			if(data.status == "SUCCESS") {
				alert(data.message)

				window.location = '/Login.html';
			}
		},
		error: function( jqXhr, textStatus, errorThrown ){
			alert( " fail connection" );
			window.location = '/Register.html';
		}
	});
});  



//load the data from the user
$(function() {
	var id = localStorage.getItem("userId");
	if (id !=null) {
		
		var urls = '/user/' + id;
		$.ajax({
			type: 'GET',
			url: urls,
			success: function (user){
				$("#g1").append(user.firstName);
				$("#g2").append(user.lastName);
				$("#g3").append(user.email);
				$("#g4").append(user.telephone);
				$("#g5").append(user.company);

			}
		});
	}
	
}); 


$("#changePassword").click(function() {

	if (id = null){
		alert("You must log in")
	}
});


$('#changePass').submit(function(event) {
	
	var id = localStorage.getItem("userId");

	event.preventDefault();
	var formdata = $(changePass).serialize();

	var urls = '/changePass/' + id;
	$.ajax({

		type: 'POST',
		url: urls ,
		data: formdata,

		success: function(data){
			if(data.status == "FAILED"){

				alert(data.message);	
			}
			if(data.status == "SUCCESS") {

				alert(data.message);
				window.location = '/Profile.html'
			}
		},
		error: function( jqXhr, textStatus, errorThrown ){
			alert( " fail connection" );
		}
	});
});

$("#editProfile").click(function() {

	if (id = null){
		alert("You must log in")
	}
});


$('#editInfo').submit(function(event) {
	
	var id = localStorage.getItem("userId");
	
	event.preventDefault();
	var formdata = $(editInfo).serialize();

	var urls = '/update/' + id;
	$.ajax({

		type: 'POST',
		url: urls ,
		data: formdata,

		success: function(data){
			if(data.status == "FAILED"){

				alert(data.message);
				
			}
			if(data.status == "SUCCESS") {

				alert(data.message);
				window.location = '/Profile.html'
			}
		},
		error: function( jqXhr, textStatus, errorThrown ){
			alert( " fail connection" );
		}
	});
});

