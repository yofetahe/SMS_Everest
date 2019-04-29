<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script src="js/jquery-1.6.1.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">

<style type="text/css">
.header_btn_active1{
	color: #3d6e9f; 
	cursor: pointer; 
	background-color: #f5f5f5;
	height: 25px;
	width: 110px;
	text-align: center;
	padding-top: 10px;
		
	-moz-border-radius-topleft: 5px;
    border-top-left-radius: 5px;
    -moz-border-radius-topright: 5px;
    border-top-right-radius: 5px;
}
</style>

</head>
<body>

	<table width="100%" cellpadding="0" cellspacing="0" style="padding-top: 0px;">		
		<tr>
			<td style="vertical-align: top;">
				<s:set var="classid" value="class_id"/>
				<s:set var="clname" value="class_name"/>
				<s:set var="ac_year" value="ac_year"/>
				<s:set var="regBtn" value="regBtnStatus"/> 
				
				<s:if test="%{#classid != null}">
					<table border="0" width="100%" style="border-bottom-style: solid; border-bottom-width: thin; border-bottom-color: #3d6e9f;" cellpadding="0" cellspacing="0"><tr><td>
						<table border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<s:iterator status="stat" value="class_detail">
									<td height="25px" align="center">
										<s:set var="cdid" value="class_detail[#stat.index].cd_id"/>
										<div class="header_btn_active1" id="${cdid}_cd" onclick="classDetail('${classid}', '${cdid}', '${ac_year}')">
											<s:property value="%{#clname}"/> - <s:property value="class_detail[#stat.index].cd_name"/>
										</div>
									</td>
									<td>&nbsp;</td>
								</s:iterator>
								<s:if test="%{#regBtn == \"on\"}">
									<td align="center">
										<div onclick="studentRegistration('${classid}', '${clname}')" class="header_btn_inactive">
											Registration
										</div>
									</td>
								</s:if>
							</tr>
						</table>
					</td></tr></table>
					<br/>
				</s:if>
			
				<!-- Student list -->
				<div id="student_list_perdetial">
					<s:include value="student_male_female_num_per_cdetail.jsp"/>
				</div>
				<!-- Student list -->
				
			</td>
		</tr>
	</table>
</body>
</html>