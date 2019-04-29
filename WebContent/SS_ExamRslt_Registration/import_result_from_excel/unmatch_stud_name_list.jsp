<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
</head>
<body>

	<div style="width: 100%; height: 30px; padding-top: 10px; background-color: #f5f5f5; text-align: center; font-weight: bold;">
		List of Student Name Don't match
	</div>
			
	<div>&nbsp;</div>
	
	<div>
		<table width="100%" cellpadding="0" cellspacing="1" rules="rows">
			<thead>
				<tr style="height: 40px; background-color: #f5f5f5;">
					<th align="center">No</th>
					<th>Student Name</th>
				</tr>
			</thead>
			<s:iterator status="stat" value="unmatchStudNameList">
				<tr style="height: 30px;">
					<td align="center"> <s:property value="%{#stat.index + 1}"/> </td>
					<td> <s:property value="unmatchStudNameList[#stat.index].fullName"/> </td>
				</tr>			
			</s:iterator>
		</table>
	</div>


</body>
</html>