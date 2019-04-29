<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<script type="text/javascript">
	
</script>

</head>
<body>

<div style="background-color: #f5f5f5; height: 28px; padding-top: 5px; width: 100%;">
	<s:select label="Teachers" list="tchr_rslt" listKey="ti_id" listValue="fname" headerKey="0" headerValue="---" onchange="viewSelectedTeacherSchedule(this.value)" style="width: 200px; height: 25px;"/>
</div>

<div id="teachers_schedule">

</div>

</body>
</html>