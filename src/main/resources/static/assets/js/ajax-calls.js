
var id = localStorage.getItem("userId");

//I get a warning in console when i do logout about to remove the value , i must check it
$("#logout").click(function() {
	localStorage.clear();

});

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




////hide the table for change password
//
//$(document).ready(function()
//		{
//$("#hide").hide();
//		});


//load the data from the user
$(function() {

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
				$("#hide").hide();

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
				$("#hide").hide();

			}
		},
		error: function( jqXhr, textStatus, errorThrown ){
			alert( " fail connection" );
		}
	});
});

