<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">
<link rel="icon" type="image/png" href="images/fidel_logo.png">
<script src="js/jquery-1.6.1.js" type="text/javascript"></script>
<script type="text/javascript">
	function loginValidator(){
		
		var username = $('#username').val();
		var password = $('#password').val();
		
		if(username == "" || password == ""){
			document.getElementById("errMsg").innerHTML = "User name and password is mandatory."
		} else {
			
			$("#loginVal").html("<img align=\"center\" src=\"images/loader.gif\" width=\"100px\"/>");
			$.ajax({
				type : "POST",
				
				url : "indexpage.action",				
				data : "username=" + username + "&password=" + password,
				success : function(response) {
					$('#indexContent').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
			
		}
	}
	
	$(document).ready(function(){
		$('#password').keypress(function(e){
			var s = String.fromCharCode(e.which);
			if(s.toUpperCase() === s && s.toLowerCase() !== s && !e.shiftKey){
				//alert('caps is on');
				document.getElementById("commentMsg").innerHTML = "<img align=\"center\" src=\"images/information.jpeg\" width=\"30px\"/> Caps lock is on.";
			} else {
				document.getElementById("commentMsg").innerHTML = "";
			}
		});
	});
	
// 	$(document).ready(function(){
// 	$('#loginForm').keydown(function(){
// 		var key = e.which;
// 		alert(key + " key");
// 		if(key == 13){
// 			alert("in");
// 			$('#loginForm').submit();
// 		}
// 	});
// 	});
	
// 	$('form').keydown(function(e){
// 		if(e.keyCode == 13){
// 			$(this).closest('#loginForm').submit();
// 		}
// 	});

// 	$(document).ready(function(){
// 	$('#loginForm').keydown(function(e){
// 		var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
// 		if(key == 13){
// 			e.preventDefault();
// 			alert("test");
// 		}
// 	});
// 	});
	
// 	$('#loginForm').keypress(function(e){
// 		if(e.which == $.ui.keyCode.ENTER){
// 			alert("test");
// 		}
// 	});

</script>

</head>
<body style="vertical-align: middle; margin: 0px; background-image: url('images/new_bg_2.png'); background-repeat: repeat; ">
<div  id="indexContent">

<div style="padding-top: 200px;">
	<div style="width: 100%; box-shadow: 0px -4px 15px #e5e5e5; background-image:url('images/SimienMountains_Panorama.jpg'); background-color: #3d6e9f; height: 200px; vertical-align: middle;">
		<div>
			<table height="200px" cellpadding="0" cellspacing="0" align="center" border="0">
				<tr>
					<td width="80px"  align="center">
						<div style="height: 200px; width: auto; " class="logo_background" >
							<div style="padding-top: 40px;"><img alt="logo" src="images/fidel_logo.png" width="120px"></div>
						</div>
					</td>
					<td width="35%" style="font-size: 30px; font-family:Arial, cursive; font-weight: bold; color: #ffffff; padding-left: 10px;">
						SCHOOL <br/> MANAGEMENT <br/> SYSTEM
					</td>
					<td width="10%">&nbsp;</td>
					<td align="right" valign="middle" style="color: #000;">
						<div style="border: thin; border-color: silver; border-style: solid; min-height: 130px; width: 310px; background-color: #fff; opacity: 0.7; padding-top: 30px;">
							<div id="errMsg" style="color: red; width: 100%; text-align: center;">
								<s:actionerror/>
							</div>
							<div id="commentMsg" style="color: green; width: 100%; text-align: center;">
								<s:actionmessage id="actmsg"/>
							</div>
							<div id="loginVal" style="width: auto;" align="center">
								<table align="center" border="0">
									<tr>			
										<td align="center">
										
											<div id="loginForm" align="right">
												
												<table><s:textfield label="User Name" id="username" key="username" class="inputtext-format"/></table>
												
												<table><s:password label="Password" id="password" key="password" class="inputtext-format"/></table>
												
												<table><s:submit onclick="loginValidator()" align="right" value="LOGIN" class="btn"/></table>
																																	
											</div>
											
										</td>
									</tr>									
								</table>
							</div>
						</div>
					</td>
				</tr>
			</table>
			<table width="100%" height="10px">
				<tr>
					<td style="font-size: 14px; color: #549C15; padding-top: 8px" align="center">
					    &copy;Copyright 2013 - <span id="copyright"> <script > document.getElementById('copyright').appendChild(document.createTextNode(new Date().getFullYear())); </script> </span>, Powered by YamGet IT Solution. Version <s:text name='global.versionnum'></s:text>
					</td>
				</tr>					
				<tr>
					<td style="color: #549C15; font-size: 14px;" align="center">
						System Support - Call us: +251 91 166 2766/+251 912 19 5853, Email us: support@yamget.com
					</td>
				</tr>
				<tr>
					<td style="color: #549C15; font-size: 14px;" align="center">
						<a href="http://www.yamget.com" target="_blank" style="text-decoration: none;"> www.yamget.com </a>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
</div>
</body>
</html>