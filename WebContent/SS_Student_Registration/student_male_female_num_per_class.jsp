<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">

</head>
<body>
<div id="student_list">

<table border="0" width="100%" cellpadding="0" cellspacing="0" style="border-bottom: thin; border-bottom-color: silver; border-bottom-style: solid; border-bottom-width: thin;">
	<tr>
		<td style="height: 35px;" width="20%">
			<div style="color: #3d6e9f; cursor: pointer;" onclick="listAllStudents()">
			<img alt="arrow" src="images/next.gif" style="width: 6px;"> List All Students
			</div>
		</td>
		<td align="center">&nbsp;</td>
		<td style="height: 35px;" width="25%">
			<div style="color: #3d6e9f; cursor: pointer;" onclick="listAllInactiveStudents()">
			<img alt="arrow" src="images/next.gif" style="width: 6px;"> List All Inactive Students
			</div>
		</td>
		<td align="center" width="10px;">&nbsp;</td>
		<td style="height: 35px;">
			<div style="color: #3d6e9f; cursor: pointer;" onclick="candidateStudentsList()">
			<img alt="arrow" src="images/next.gif" style="width: 6px;"> New Candidate Students List
			</div>
		</td>
<!-- 		<td align="center" width="10px;">&nbsp;</td> -->
<!-- 		<td style="height: 35px;"> -->
<!-- 			<div style="color: #3d6e9f; cursor: pointer;" onclick="dropoutStudentsList()"> -->
<!-- 			<img alt="arrow" src="images/next.gif" style="width: 6px;"> Dropout Students List -->
<!-- 			</div> -->
<!-- 		</td> -->
	</tr>
</table>

<table width="100%" cellpadding="0" cellspacing="4">
	<s:set var="class" value="0"/>
	<s:set var="num_of_class" value="num_of_class"/>
	<s:iterator begin="1" end="3">
		<tr>
			<s:iterator begin="1" end="4">
			<td>
				<s:if test="%{#class < #num_of_class}">
				<div class="formboarder" style="width: 190px; height: 150px;">
					
					<table width="100%" cellpadding="0" cellspacing="0">
					<tr><td colspan="2" align="center" height="60px;">
					
						<div class="menu_shadow">
							<s:set var="total" value="studnum_perclass[#class].total_number"/>
							<span style="color: #3d6e9f"><b> <s:property value="grade_rslt[#class].class_name"/> </b></span> <br/>
							Total Number: <s:property value="%{#total}"/>
						</div>
						
					</td></tr>
					<tr><td align="center" style="padding-top: 10px;">
					
						<img alt="male" src="images/male_default.jpeg" width="55px"> <br/>
						<s:set var="male" value="studnum_perclass[#class].no_male"/>
						<s:property value="%{#male}"/>
						
					</td><td align="center" style="padding-top: 10px;">
					
						<img alt="female" src="images/female_default.jpeg" width="55px"> <br/>
						<s:set var="female" value="studnum_perclass[#class].no_female"/>
						<s:property value="%{#female}"/>
						
					</td></tr></table>
						
				</div>
				
				</s:if>			
			</td>
			<s:set var="class" value="%{#class + 1}"/>
			</s:iterator>
		</tr><tr><td height="2px;">&nbsp;</td></tr>
	</s:iterator>
</table>

</div>

</body>
</html>