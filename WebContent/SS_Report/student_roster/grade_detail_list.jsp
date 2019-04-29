<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<script type="text/javascript">
function subjectList(cd_id){
	
	document.getElementById('studRoster').innerHTML = "";
	
// 	var at_id = $('#at_id').val();
// 	var cl_id = $('#cl_id').val();
	
// 	$("#subList").html("<img align=\"center\" src=\"images/30.GIF\"/>");
// 	$.ajax({
// 		type : "POST",
// 		url : "report_clsublist.action",				
// 		data : "at_id=" + at_id + "&cl_id=" + cl_id + "&cd_id=" + cd_id,
// 		success : function(response) {
					
// // 			document.getElementById('subList').innerHTML = "";
// // 			document.getElementById('examList').innerHTML = "";
// // 			document.getElementById('studList').innerHTML = "";	
			
// 			$('#subList').html(response);				
// 		},
// 		error : function(e) {
// 			alert('Error: ' + e);
// 		}
// 	});
} 
</script>

</head>
<body>

<s:select label="Grade" id="cd_id" headerValue="Select grade" headerKey="0" list="gradeDetail" listValue="cd_name" onchange="subjectList(this.value)" listKey="cd_id" style="width: 110px; height: 25px"/>

</body>
</html>