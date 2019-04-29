<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
</head>
<body>
	
	<s:set var="btc_id" value="btc_id"/>
	<s:set var="cl_id" value="cl_id"/>
	<table align="center"><tr><td>	
		<div class="formtitle">Update Form</div>
		<br/>
		
		Trait Name: <s:property value="bt_title"/>
		<br/><br/>
		Traits Class Relation Status<br/>
		<table cellpadding="0" cellspacing="0">
			<s:select list="#{'A':'Active', 'I':'Inactive'}" id="rel_status" name="rel_status" style="width: 205px; height: 30px;"/>
		</table>
		
	</td></tr>
	<tr><td>
		<button onclick="updateTraitClassRel('${btc_id}', '${cl_id}')">Update</button>						
	</td></tr></table>

</body>
</html>