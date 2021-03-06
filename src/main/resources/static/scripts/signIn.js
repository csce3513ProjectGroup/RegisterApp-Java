document.addEventListener("DOMContentLoaded", function(event) {
	// TODO: Anything you want to do when the page is loaded?
});

function validateForm() {
	var employeeId = document.getElementById("employeeId").value;
	var password = document.getElementById("password").value;

	if (employeeId != '' && password != ''){
		if (isNaN(employeeId)) {
			document.getElementById("errorMessage").innerHTML = "You can use only index"
			alert("You can use only index");
		}
		else {
			//TODO: invoke sign in
			//TODO: incorrect password error alert
			document.getElementById("errorMessage").innerHTML = "Sign in successful!!"
			//document.getElementById("Signin Button").href="/mainMenu"
			return true;
		}
	}
	else{
		document.getElementById("errorMessage").innerHTML = "Please do not keep blank Employee ID & Password"
		alert("Please do not keep blank Employee ID & Password");
		return false;
	}
}
