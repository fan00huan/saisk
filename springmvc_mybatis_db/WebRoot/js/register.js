// JQUERY
$(document).ready(function() {
	$("#id_btn_clear").click(function() {
		$("#id_username").val("");
		$("#id_password").val("");
	});

});

function returnToLogin() {
	//提交form
	document.registForm.action = "login.action";
	document.registForm.submit();
}


function returnToLoginById() {
	//提交form
	debugger;
	$("#registForm").attr("action","login.action");
	$("#registForm").submit();
}

