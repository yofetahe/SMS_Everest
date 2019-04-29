<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script src="../js/jquery-1.6.1.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">

</head>
<body>
	
	<table width="100%" cellpadding="0" cellspacing="0" style="padding-top: 0px;">		
		<tr>
			<td style="vertical-align: top;">
				<s:set var="classid" value="class_id"/>
				<s:set var="clname" value="class_name"/>
				
				<table width="100%" style="border-bottom-style: solid; border-bottom-width: thin; border-bottom-color: #3d6e9f;" cellpadding="0" cellspacing="0"><tr><td>
						<table cellpadding="0" cellspacing="0" >
							<tr>
								<s:iterator status="stat" value="class_detail">
									<td width="80px" height="25px" align="center">
										<s:set var="cdid" value="class_detail[#stat.index].cd_id"/>
										<div class="header_btn_inactive" id="${cdid}_cd" onclick="classDetailForPayment('${classid}', '${cdid}')">
											<s:property value="%{#clname}"/> - <s:property value="class_detail[#stat.index].cd_name"/>
										</div>
									</td>
									<td>&nbsp;</td>
								</s:iterator>								
							</tr>
						</table>
				</td></tr></table>
				
				<!-- Student list -->				
				<div id="student_list_perdetial" style="padding-top: 5px;">
					
				</div>
				<!-- Student list -->
				
			</td>
		</tr>
	</table>
</body>
</html>