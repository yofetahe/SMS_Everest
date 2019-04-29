<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script type="text/javascript">

	function validateUsername(ua_id){
		var username = $('#username').val();
		var password = $('#password').val();
		var old_password = $('#old_password').val();
		var comfirm_password = $('#comfirmPassword').val();
		
		var pw_pattern_no = /^[0-9]{1,20}$/;
		var pw_pattern = /^[A-Za-z0-9@#$%^&*]{3,20}$/;
		
		var password_pattern_sp = pw_pattern.test(password);
		var password_pattern_no = pw_pattern_no.test(password);
		
		if(username == ""){
			document.getElementById("errMsg").innerHTML = "User name is required.";
		} else if(password == ""){
			document.getElementById("errMsg").innerHTML = "Password is required.";
		} else if(old_password == ""){
			document.getElementById("errMsg").innerHTML = "Password is required.";
		} else if(password == old_password){
			document.getElementById("errMsg").innerHTML = "Your new password is the same as with the current password.";
		} else if(password == "pass*word"){
			document.getElementById("errMsg").innerHTML = "Use other than the default password.";
		} else if(password_pattern_sp == false || password_pattern_no == true || password == "password"){
			document.getElementById("errMsg").innerHTML = "Password is week.";
		} else if(comfirm_password != password){
			document.getElementById("errMsg").innerHTML = "Incorrect password. The two password is different.";
		} else {
			$("#profileChangeForm").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "chang_profile_validate_username.action",				
				data : "ua_id=" + ua_id + "&username=" + username + "&password=" + password + "&old_password=" + old_password,
				success : function(response) {					
					var x = response.length;
					if(x == 1){
						ChangeAcUser(ua_id, username, password)
					}
					if(x == 3){
						document.getElementById("errMsg").innerHTML = "<div style=\"width: auto; color: GREEN;\" align=\"center\"><img src=\"images/information.jpeg\" width=\"60px;\"/> <br/> Username you selected is already taken<br/>by other system user. <br/>Please select some other username </div>";
						$("#profileChangeForm").html("");
					}
					if(x == 4){
						document.getElementById("errMsg").innerHTML = "<div style=\"width: auto\" align=\"center\"><img src=\"images/error.jpeg\" width=\"60px;\"/> <br/> Your current password is not correct. </div>";
						$("#profileChangeForm").html("");
					}
				},
				error : function(e) {
					
				}
			});
		}
	}

	function ChangeAcUser(ua_id, username, password){
		
		$("#profileChangeForm").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "chang_profile_update.action",				
			data : "ua_id=" + ua_id + "&username=" + username + "&password=" + password,
			success : function(response) {					
				$('#fullPageContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});		
	}
</script>
</head>
<body>

<div style="padding-top: 20px; padding-left: 30px">
	<div class="content_background" style="min-height: 475px; width: auto;">		
		<div style="width: auto; height: 35px; background-color: #e5e5e5; color: #fff; font-size: 24px; padding-left: 20px; padding-top: 5px;">
			Profile Information Update Form
		</div>
		<div id="errMsg" style="color: red; padding-top: 20px; padding-left: 20px; text-align: center; width: 40%;"></div>
		<div id="profileChangeForm">
			<table style="padding-left: 20px">
				<tr>
					<td>
						<s:set var="name" value="loginUserInfo[0].name"/>
						<s:set var="ua_id" value="loginUserInfo[0].ua_id"/>
						
						<s:textfield label="Name" id="name" value="%{#name}" readonly="true" style="width: 200px; height: 25px;" required="true"/>
						<s:textfield label="Login User Name" id="username" style="width: 200px; height: 25px;" required="true"/>
						<s:password label="Current Password" value="old_password" id="old_password" style="width: 200px; height: 25px;" required="true"/>
						<s:password label="Password" value="password" id="password" style="width: 200px; height: 25px;" required="true"/>				
						<s:password label="Comfirm Password" value="password" id="comfirmPassword" style="width: 200px; height: 25px;" required="true"/>
						
					</td>
				</tr>
		<tr><td>
			<button onclick="validateUsername('${ua_id}')" class="btn">Change</button>						
		</td></tr>
			</table>
		</div>
	</div>
</div>

</body>
</html>