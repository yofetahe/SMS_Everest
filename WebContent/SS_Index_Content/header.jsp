<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<s:head />
<link rel="stylesheet" type="text/css" href="../css/sms_css.css">
<style type="text/css">
	#header_tab{
		cursor: pointer;
		width: 150px;
		background-color: rgba(255, 255, 255, 0.5);
		-moz-border-radius-topleft: 5px;
	    border-top-left-radius: 5px;
	    -moz-border-radius-topright: 5px;
	    border-top-right-radius: 5px;	    
	    -moz-border-radius-bottomleft: 5px;
	    border-bottom-left-radius: 5px;
	    -moz-border-radius-bottomright: 5px;
	    border-bottom-right-radius: 5px;
	}
</style>
<script type="text/javascript" >
	function logout(){		
		$.ajax({
			type : "POST",
			url : "logout.action",				
			data : "",
			success : function(response) {
				$('#indexContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function changeProfile(){
		$("#index_cont").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "chang_profile.action",				
			data : "",
			success : function(response) {				
				$('#index_cont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
</script>

<style type="text/css">
	a.divLink{
		color: #fff; text-decoration: none; 
		height: 22px; padding-top: 2px;		
		cursor: pointer;
	}
	
</style>
</head>
<body>
<!-- box-shadow: 0 0 15px #d7d7d7; -moz-box-shadow: 0 0 5px #595959; -webkit-box-shadow: 0 0 5px #595959; -->
	<table width="100%" cellpadding="0" cellspacing="0"> 
<!-- 		<tr> -->
<!-- 			<td height="85px" align="center" valign="top" > -->
<!-- 				<div style="width: 1220px; height: 85px;"> -->
<!-- 					header top part -->
<!-- 					<table width="100%" height="85px" cellpadding="0" cellspacing="0" border="0"> -->
<!-- 						<tr> -->
<!-- 							<td width="160px"  align="center" valign="top"> -->
<!-- 								<div style=" width: auto; height: 85px; vertical-align: middle; font-size: 12px; font-weight: bold; color: #fff" class="logo_background"><img alt="logo" src="images/fidel_logo.png" width="86px"><br/>SCHOOL MANAGEMENT <br/>SYSTEM</div> -->
<!-- 							</td> -->
<!-- 							<td width="20px;">&nbsp;</td> -->
<!-- 							<td width="380px" style="font-size: 22px; font-family:cursive; color: #ffffff; padding-left: 10px;" align="left"> -->
								
<!-- 								<table border="0" cellpadding="0" cellspacing="0"><tr><td align="center"> -->
<%-- 									<s:text name='global.schoolname'/> --%>
<!-- 								</td></tr> -->
<!-- 								<tr><td style="font-size: 12px; text-align: center;"> -->
<%-- 									--- <s:text name='global.schoolmoto'/> --- --%>
<!-- 								</td></tr></table> -->
								
<!-- 							</td> -->
<!-- 							<td align="right" valign="top" style="color: #fff; padding-right: 0px; opacity: 0.6; background-image: url('images/SMS_Banner.png'); background-repeat:no-repeat; background-position: right;"> -->
<!-- 								Quest for Excellence!!! -->
<!-- 							</td> -->
<!-- 						</tr> -->
<!-- 					</table> -->
<!-- 					header top part -->
<!-- 				</div>				 -->
<!-- 			</td> -->
<!-- 		</tr> -->
		
		<tr bgcolor="#8BD948">
			<td style="color: #000000; border-top-style: solid; border-top-width: 0px; border-bottom-style: solid; border-bottom-width: thin; border-color: #ffffff; color: #ffffff" height="30px" align="center">
				<div style="width: 95%; height: 50px;">
										
					<s:set var="dbStatus" value="dashboardStatus"/>
					<table align="right" style="padding-top: 10px;" width="100%">
						<tr>
							<td align="left" style="height: 20px; color: #fff; font-size: 16px; font-weight: bold;">
								<s:text name='global.schoolname'/>
							</td>
							<td align="center" width="10px">
								&nbsp;
							</td>
							<td align="right" style="min-width: 130px">
								Login as - ${sessionScope.userName}
							</td>
							<td align="center" width="10px">
								|
							</td>
							<td align="center" width="150px">
								<div>
									<a href="/<s:text name='global.sysname'/>/dashboard.action" class="divLink" onmouseover="this.style.color = '#3d6e9f'" onmouseout="this.style.color = '#ffffff'">
										Home
									</a>
								</div>
							</td>
							<td align="center" width="10px">
								|
							</td>
							<td align="center" width="150px">
								<div>
									<a href="/<s:text name='global.sysname'/>/logout.action" class="divLink" onmouseover="this.style.color = '#3d6e9f'" onmouseout="this.style.color = '#ffffff'">
										Logout
									</a>
								</div>
							</td>
							<td align="center" width="10px">
								|
							</td>
							<td align="center" width="150px">
								<div onclick="changeProfile()" class="divLink" onmouseover="this.style.color = '#3d6e9f'" onmouseout="this.style.color = '#ffffff'">
									<div style="height: 22px; padding-top: 2px; cursor: pointer;">
										Change Profile
									</div>
								</div>
							</td>
						</tr>
					</table>				
					
				</div>
			</td>
		</tr>
		<tr>
			<td style="background-color: #e5e5e5; line-height: 6px">
				&nbsp;
			</td>
		</tr>
	</table>
</body>
</html>