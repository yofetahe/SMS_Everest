<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<script type="text/javascript">
	function examList(subcl_id){
		
		document.getElementById('markList').innerHTML = "";
		
// 		var at_id = $('#at_id').val();
// 		var cl_id = $('#cl_id').val();
// 		var cd_id = $('#cd_id').val();
		
// 		$("#examList").html("<img align=\"center\" src=\"images/30.GIF\"/>");
// 		$.ajax({
// 			type : "POST",
// 			url : "examrsltaction_clsubexamlist.action",				
// 			data : "at_id=" + at_id + "&cl_id=" + cl_id + "&cd_id=" + cd_id + "&subcl_id=" + subcl_id,
// 			success : function(response) {
				
// // 				document.getElementById('examList').innerHTML = "";
// // 				document.getElementById('studList').innerHTML = "";
				
// 				$('#examList').html(response);
// 			},
// 			error : function(e) {
// 				alert('Error: ' + e);
// 			}
// 		});
	}
</script>

</head>
<body>
				
	<s:select label="Subject" id="subcl_id" headerValue="Select subject" headerKey="0" list="clSubRelList" listValue="sub_name" listKey="subcl_id" onchange="examList(this.value)" style="width: 110px; height: 25px"/>
	
</body>
</html>