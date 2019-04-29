<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/sms_css.css">
<title>SMS</title>
<script type="text/javascript">
	function updateExamCategory(subcl_id, sub_id, pet_id){
		var exam_level = $('#exam_level').val();
		var number_of_qst = $('#number_of_qst').val();
		var total_time_allowed = $('#total_time_allowed').val();
		var status = $('#status').val();
		
		if(exam_level == "" || number_of_qst == "" || total_time_allowed == ""){
			document.getElementById("errorMsg").innerHTML = "<span styls=\"color:red\">Please fill all text box!!!</span>";
		} else {
			
			$("#exCatList").html("<img align=\"center\" src=\"images/loader.gif\"/>");		
			$.ajax({
				type : "POST",
				url : "pe_update_examcat.action",				
				data : "subcl_id=" + subcl_id + "&sub_id=" + sub_id + "&exam_level=" + exam_level + "&number_of_qst=" + number_of_qst + "&total_time_allowed=" + total_time_allowed + "&pet_id=" + pet_id + "&pet_status=" + status,
				success : function(response) {
					$('#exCatList').html(response);
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
	
	<s:set var="pet_id" value="pet_id"/>
	<s:set var="examlevel" value="exam_level"/>
	<s:set var="noofquestion" value="number_of_qst"/>
	<s:set var="totaltimeallowed" value="total_time_allowed"/>
	<s:set var="petstatus" value="pet_status"/>
	
	
	<s:set var="subclid" value="subcl_id"/>
	<s:set var="subid" value="sub_id"/>
	<div id="errorMsg" style="color: red;"></div>
	<div class="form_title">Editing Exam Category</div>
	<table style="padding-top: 10px">
		<tr>
			<td>
				<s:textfield label="Category Name" id="exam_level" style="height: 25px; width: 200px" value="%{#examlevel}"/>
				<s:textfield label="Number of Questions" id="number_of_qst" style="height: 25px; width: 200px" value="%{#noofquestion}"/>
				<s:textfield label="Total Time Allowed" id="total_time_allowed" style="height: 25px; width: 200px" value="%{#totaltimeallowed}"/>
				<s:select label="Status" id="status" list="#{'A':'Active', 'I':'Inactive'}" style="height: 25px; width: 206px" value="%{#petstatus}"/>
				
			</td>
		</tr>
		<tr><td>
			<button onclick="updateExamCategory('${subclid}', '${subid}', '${pet_id}')" class="btn">Update</button>						
		</td></tr>
	</table>
	
</body>
</html>