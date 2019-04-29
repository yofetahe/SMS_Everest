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
	
	function selectSubject(sub_id){
		
		$.ajax({
			type : "POST",
			url : "report_selectSubject.action",				
			data : "sub_bean.sub_id=" + sub_id,
			success : function(response) {
				
				document.getElementById(sub_id + "_ck").innerHTML = "<img alt=\"check\" src=\"images/checked.png\" onclick=\"disselectSubject(" + sub_id + ")\" style=\"cursor: pointer;\" width=\"16px\">";
				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function disselectSubject(sub_id){
		
		$.ajax({
			type : "POST",
			url : "report_disselectSubject.action",				
			data : "sub_bean.sub_id=" + sub_id,
			success : function(response) {
				
				document.getElementById(sub_id + "_ck").innerHTML = "<img alt=\"check\" src=\"images/check_box.png\" onclick=\"selectSubject()\" style=\"cursor: pointer;\" width=\"14px\">";
				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
</script>

</head>
<body>				

	<table>
		<s:iterator status="stat" value="clSubRelList">
			<tr>
				<td>
					<s:set var="sub_id" value="clSubRelList[#stat.index].sub_id"/>					
					<div id="${sub_id}_ck"> <img alt="check" src="images/check_box.png" onclick="selectSubject('${sub_id}')" style="cursor: pointer; width: 14px;"> </div>
				</td>
				<td>
					<s:property value="clSubRelList[#stat.index].sub_name"/>
				</td>
			</tr>
		</s:iterator>
	</table>
	
</body>
</html>