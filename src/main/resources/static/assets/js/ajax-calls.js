
var id = localStorage.getItem("userId");

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

				window.location = '/Home.html';
			}
		},
		error: function( jqXhr, textStatus, errorThrown ){
			alert( " fail connection" );
		}
	});
}); 




$('#register').submit(function(event) {

	event.preventDefault();
	var formdata = $(register).serialize();
	$.ajax({

		type: 'POST',
		url: '/register',
		data: formdata,

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




//hide the table for change password
$("#hide").hide();




$("#changePassword").click(function() {

	if (id != null){
		$("#hide").show();
	}else{
		alert("You must log in")
	}
});

//load the data from the user
$(function() {

	var urls = '/user/' + id;
	$.ajax({
		type: 'GET',
		url: urls,
		success: function (humans){
			$("#gg").append("Welcome   <strong>"+humans.name+"</strong>!");
			$("#g1").append(humans.firstName);
			$("#g2").append(humans.lastName);
			$("#g3").append(humans.email);
			$("#g4").append(humans.telephone);
			$("#g5").append(humans.company);

		}
	});
}); 


$('#changePass').submit(function(event) {

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

//I get a warning in console when i do logout about to remove the value , i must check it
$("#logout").click(function() {
	localStorage.clear();

});
