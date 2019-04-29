<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script src="../js/admin.js" type="text/javascript"></script>

</head>
<body>

<div id="school_information">
	<div style="color: #ff0000; width: 100%; text-align: center;">
		<s:actionerror id="info_error"/>
	</div>
	<table align="center">
		<tr>
			<td>
				
				<s:textfield label="School Name" id="sch_name" readonly="true" name="school_name" style="width: 200px; height: 30px;"/>
				<s:textfield label="School Slogan" id="sch_slogan" name="school_slogan" style="width: 200px; height: 30px;"/>
				<s:textfield label="TIN Number" id="tin_num" readonly="true" name="tin_num" style="width: 200px; height: 30px;"/>
				<s:textfield label="Telephone" id="telephone" name="telephone" style="width: 200px; height: 30px;"/>
				<s:textfield label="Fax" id="fax" name="fax" style="width: 200px; height: 30px;"/>
				<s:textfield label="Web" id="web" name="web" style="width: 200px; height: 30px;"/>
				<s:textfield label="E-mail" id="email" name="email" style="width: 200px; height: 30px;"/>
				<s:textfield label="P.O.Box" id="pobox" name="pobox" style="width: 200px; height: 30px;"/>
				<s:textfield label="Fisical Machine Number" readonly="true" id="fiscal_machine_no" name="fiscal_machine_no" style="width: 200px; height: 30px;"/>
				
				<s:submit label="Save" onclick="saveSchoolInformation()"/>
			</td>
		</tr>
	</table>
</div>

</body>
</html>