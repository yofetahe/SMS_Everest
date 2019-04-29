<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script type="text/javascript">
	function saveExamCategory(subcl_id, sub_id){
		var exam_level = $('#exam_level').val();
		var number_of_qst = $('#number_of_qst').val();
		var total_time_allowed = $('#total_time_allowed').val();
		
		if(exam_level == "" || number_of_qst == "" || total_time_allowed == ""){
			document.getElementById("errorMsg").innerHTML = "<span styls=\"color:red\">Please fill all text box!!!</span>";
		} else {
			
			$("#qst_list").html("<img align=\"center\" src=\"images/loader.gif\"/>");		
			$.ajax({
				type : "POST",
				url : "pe_save_examcat.action",				
				data : "subcl_id=" + subcl_id + "&sub_id=" + sub_id + "&exam_level=" + exam_level + "&number_of_qst=" + number_of_qst + "&total_time_allowed=" + total_time_allowed,
				success : function(response) {
					$('#qst_list').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
			
		}
	}
</script>
</head>
<body>

	<s:set var="subclid" value="subcl_id"/>
	<s:set var="subid" value="sub_id"/>
	<div id="errorMsg" style="color: red;"></div>
	<table>
		<tr>
			<td>
				<s:textfield label="Category Name" id="exam_level" style="height: 25px; width: 200px"/>
				<s:textfield label="Number of Questions" id="number_of_qst" style="height: 25px; width: 200px"/>
				<s:textfield label="Total Time Allowed" id="total_time_allowed" style="height: 25px; width: 200px"/>
				
			</td>
		</tr>
		
		<tr><td>
			<button onclick="saveExamCategory('${subclid}', '${subid}')" class="btn">Save</button>						
		</td></tr>
	</table>
	
</body>
</html>