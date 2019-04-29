<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<s:head />
<link rel="stylesheet" type="text/css" href="css/sms_css.css">

</head>
<body>
	
	<div id="emailBody" style="width: 100%">
	
		<div id="errMsg" style="color: RED; text-align: left; padding-left: 270px;">
			<s:actionmessage/>
		</div>
		
		<s:text var="gmailaccount" name='global.schoolgmailaccount'/>
		
		<table align="center">
			<tr>
				<td align="right">
					<table>
					<s:textfield label="From(gmail account username)"  value="%{#gmailaccount}" readonly="true" id="from" name="from" style="height: 30px; width: 220px;"/>
					</table>
					
					<table>
					<s:textfield label="To" id="to" name="to" style="height: 30px; width: 220px;"/>						
					</table>
					
					<table>
					<s:textfield label="Subject" id="subject" name="subject" style="height: 30px; width: 220px;"/>
					</table>
					
					<table>
					<s:textarea label="Body(Content)" id="body" name="body" rows="8" cols="30"/>
					</table>
					
					<table>
						<s:submit value="Send" onclick="sendAnEmail()" style="height: 30px; width: 100px;"/>
					</table>
				</td>
				<td style="padding-top: 35px; vertical-align: top;">					
										
					<table cellpadding="0" cellspacing="0"><tr><td align="right">
						<img alt="" src="images/previous_dim.gif"> 
					</td><td align="left">
						<div class="save_update_btn" style="color: #3d6e9f; border-color:silver; border-style:solid; border-width: thin; cursor: pointer; height: 30px; width: 100px; padding-top: 8px; text-align: center;" onclick="getSavedEmails()"> 
							Get e-mails
						</div>
					</td><td>&nbsp;</td><td>
						<div class="save_update_btn" style="color: #3d6e9f; background-color: transparent; border-color:silver; border-style:solid; border-width: thin; height: 50px; width: 300px; padding-top: 8px; text-align: center;"> 
							<span style="font-weight: bold; font-size: 12px;">NOTICE</span> - if you insert two or more e-mails,<br/> it must be separated by comma.
						</div>
					</td>					
					</tr></table>
					
				</td>
			</tr>
		</table>
	</div>

</body>
</html>