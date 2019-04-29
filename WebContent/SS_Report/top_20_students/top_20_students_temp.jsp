<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script type="text/javascript" src="js/sms_report.js"></script>
</head>
<body>

	<div style="width: 100%; background-color: #f5f5f5;">
		<table><tr>
			<td>
				<table>
				<s:set var="ac_year" value="stud_bean.ac_year"/>
				<s:textfield label="Year" id="ac_year" value="%{#ac_year}"  readonly="true" style="width: 50px; height: 20px"/>
				</table>
			</td><td>&nbsp;</td>
			<td>
				<div id="qrtListError" style="color: RED;"></div>
				<table>
					<s:select label="Quarter" id="at_id" list="sem_list" listKey="at_id" listValue="at_name" headerKey="0" headerValue="---" style="width: 106px; height: 25px;"/>
				</table>
			</td><td>&nbsp;</td>			
			<td>
				<div onclick="getTopTwentyStudentsPerClass()" class="adminHeaderButton" style="cursor: pointer; width: 100px;">GENERATE</div>
			</td><td>&nbsp;</td>
		</tr></table>	
	</div>
	<br/>
	<div id="topFiveStud">
	
	</div>

</body>
</html>