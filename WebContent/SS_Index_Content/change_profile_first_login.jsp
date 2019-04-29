<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<script type="text/javascript">

	function validateUsername(ua_id, oldUserName){
		
		var username = $('#username').val();
		var password = $('#password').val();
		var comfirm_password = $('#comfirmPassword').val();
		
		//checkPasswordStrength(password);		
		
		if(username == ""){
			document.getElementById("errMsg").innerHTML = "User name is required.";
		} else if(password == ""){
			document.getElementById("errMsg").innerHTML = "Password is required.";
		} else if(password == "pass*word"){
			document.getElementById("errMsg").innerHTML = "Use other than the default password.";
		} else if(password.length < 7){
			document.getElementById("errMsg").innerHTML = "Password is week.";
		} else if(comfirm_password != password){
			document.getElementById("errMsg").innerHTML = "Incorrect password. The two password is different.";
		} else {
			$("#profileChangeForm").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "chang_profile_first_time.action",				
				data : "ua_id=" + ua_id + "&username=" + username + "&password=" + password + "&oldUserName=" + oldUserName,
				success : function(response) {					
					var x = response.length;
					
					if(x == 1){
						ChangeAcUser(ua_id, username, password)
					}
					if(x == 3){
						document.getElementById("errMsg").innerHTML = "Username you selected is already taken<br/>by other system user. <br/>Please select some other username";
						duplicatedUserName(ua_id, username, password, oldUserName);
					}
					if(x > 3){
						$('#fullPageContent').html(response);
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
			url : "chang_profile_update_first_time_login.action",				
			data : "ua_id=" + ua_id + "&username=" + username + "&password=" + password,
			success : function(response) {
				
				$('#fullPageContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});		
	}
	
	function duplicatedUserName(ua_id, username, password, oldUserName){
		$("#profileChangeForm").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "chang_profile_duplicated_username.action",				
			data : "ua_id=" + ua_id + "&username=" + username + "&password=" + password + "&oldUserName=" + oldUserName,
			success : function(response) {
				
				$('#profileChangeForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});	
	}
	
	function checkPasswordStrength(password){
		var b = 0, sp_count = 0, s_ch_count = 0, c_ch_count = 0, num_count = 0;
		for(var e = 1; e <= password.length; e++){
			
			var ch = password.substring(b, e);
			
			var pw_pattern_sp_char = /^[@#$%^&*]{1,20}$/;
			var password_pattern_sp_char = pw_pattern_sp_char.test(ch);
			
			if(password_pattern_sp_char){
				sp_count = sp_count + 1;
			}
			
			var pw_pattern_small_char = /^[a-z]{1,20}$/;		
			var password_pattern_char = pw_pattern_small_char.test(ch);
			
			if(password_pattern_char){
				s_ch_count = s_ch_count + 1;
			}
			
			var pw_pattern_big_char = /^[A-Z]{1,20}$/;		
			var password_pattern_char = pw_pattern_big_char.test(ch);
			
			if(password_pattern_char){
				c_ch_count = c_ch_count + 1;
			}
			
			var pw_pattern_num = /^[0-9]{1,20}$/;		
			var password_pattern_num = pw_pattern_num.test(ch);
			
			if(password_pattern_num){
				num_count = num_count + 1;
			}
			
			b = e;
		}
		
		if(sp_count > 1 && s_ch_count > 1 && c_ch_count > 1 && num_count > 1){
			return "strong";
		}
	}
</script>

</head>
<body>
<div id="fullPageContent">
	<s:set var="oldUserName" value="oldUserName"/>
	<div style="padding: 30px;">
		<div class="content_background" style="min-height: 475px; width: auto;">		
			<div align="center" style="width: auto; height: 200px; padding-top: 30px; background-color: #e5e5e5; color: #3d6e9f; font-size: 24px; padding-left: 20px; padding-top: 5px;">
				<img alt="FIDEL" src="images/fidel_logo.png" width="120px"> <br/>  <br/>
				You login for the first time. Change the default password to work on the system.
			</div>
			<div id="errMsg" style="color: red; text-align: center; width: 100%;"></div>
			<div id="profileChangeForm">
				
				<s:include value="change_profile_form.jsp"/>
				
			</div>
		</div>
	</div>
	
</div>
</body>
</html>