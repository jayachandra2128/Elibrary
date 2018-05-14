function passwordValidate()
	{
			pwd = document.getElementById("pwd").value;
			alert("yes");

	      	var testpwd = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()]).{6,20}$/;
	      	if(testpwd.test(pwd))
	      	{
	      		document.getElementById("p").innerHTML = "Password strong";
	      		return true;
                    }
                  
	      	else
	      	{
      			document.getElementById("p").innerHTML = "Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters.";
      			return false;
      		}
	}