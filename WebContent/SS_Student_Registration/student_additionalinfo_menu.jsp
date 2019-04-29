<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">

</head>
<body>

	<s:set var="id_no" value="id_no"/>
	<s:set var="siid" value="si_id"/>
	<s:set var="fname" value="fname"/><s:set var="mname" value="mname"/><s:set var="gname" value="gname"/>
	<s:set var="sex" value="sex"/>
	<s:set var="mothername" value="mother_name"/>
	<s:set var="dob" value="dob"/>
	<s:set var="pob" value="pob"/>
	<s:set var="sistatus" value="si_status"/>
	<s:set var="class_id" value="class_id"/>
									
	<table border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr height="30px">
			<td align="left">
				<div onclick="student_info_edit('${id_no}', '${siid}', '${fname}', '${mname}', '${gname}', '${sex}', '${dob}', '${pob}', '${sistatus}', '${mothername}', '${class_id}', '${cd_id}')" id="si" class="menu_button" style="background-color: #f5f5f5">
					Student Info
				</div>
			</td>
		</tr>
		<tr>
			<td style="line-height: 5px">&nbsp;</td>
		</tr>
<!-- 		<tr height="30px"> -->
<!-- 			<td align="left"> -->
<%-- 				<div onclick="student_family_info_edit('${idno}', '${siid}', '${fname}', '${mname}', '${gname}', '${sex}', '${dob}', '${pob}', '${sistatus}', '${mothername}')" id="si" class="menu_button"> --%>
<!-- 					Family Info -->
<%-- 				</div> --%>
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td style="line-height: 5px">&nbsp;</td> -->
<!-- 		</tr> -->
		<tr height="25px">
			<td align="left">
				<div onclick="current_address('${siid}')" id="ca" class="menu_button">
					Current Address
				</div>
			</td>
		</tr>
		<tr>
			<td style="line-height: 5px">&nbsp;</td>
		</tr>
		<tr height="25px">
			<td align="left">
				<div onclick="emergency_contact('${siid}')" id="ec" class="menu_button">
					Emergency Contact
				</div>
			</td>
		</tr>
		<tr>
			<td style="line-height: 5px">&nbsp;</td>
		</tr>
<!-- 		<tr height="25px"> -->
<!-- 			<td align="left"> -->
<%-- 				<div onclick="grade_history('${siid}')" id="gh" class="menu_button"> --%>
<!-- 					Grade History -->
<%-- 				</div> --%>
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td style="line-height: 5px">&nbsp;</td> -->
<!-- 		</tr> -->
		<tr height="25px">
			<td align="left">
				<div onclick="reward('${siid}')" id="r" class="menu_button">
					Reward
				</div>
			</td>
		</tr>
		<tr>
			<td style="line-height: 5px">&nbsp;</td>
		</tr>
		<tr height="25px">
			<td align="left">
				<div onclick="disciplinary_action('${siid}')" id="da" class="menu_button">
					Disciplinary action
				</div>
			</td>
		</tr>
	</table>
</body>
</html>