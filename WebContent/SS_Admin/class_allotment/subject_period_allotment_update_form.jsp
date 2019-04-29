<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<table width="100%">
		<tr>
			<td align="right" style="padding-right: 50px;">
				<s:set var="ac_year" value="academic_year"/>
				<table><s:textfield id="academic_year" label="Academic Year" readonly="true" value="%{academic_year}" class="inputtext-format"/></table>
				<table><s:textfield id="cl_id" label="Grade" readonly="true" value="%{assignPeriodList[0].croomBean.class_name}" class="inputtext-format"/></table>
				<table><s:textfield id="sub_id" label="Subject" readonly="true" value="%{assignPeriodList[0].subjectBean.sub_name}" class="inputtext-format"/></table>
				<table><s:textfield id="period_per_week" label="Period Alloted" value="%{assignPeriodList[0].period_per_week}" class="inputtext-format"/></table>
				
				<table> <s:submit onclick="updateClassPeriodAllotment('%{assignPeriodList[0].csp_id}')" value="Update" class="btn"/> </table>
			</td>
		</tr>
	</table>

</body>
</html>