<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<script type="text/javascript">
	function updateExamQuestion(pet_id, peq_id){
		var examQst = $('#examQst').val();
		var status = $('#status').val();
		
		if(examQst == ""){
			document.getElementById("errorMsg").innerHTML = "<span style=\"color:red\">The question is blank.</span>";
		} else {
			$("#qstForm").html("<img align=\"center\" src=\"images/loader.gif\"/>");		
			$.ajax({
				type : "POST",
				url : "pe_update_question.action",				
				data : "pet_id=" + pet_id + "&peq_content=" + examQst + "&peq_id=" + peq_id + "&peq_status=" + status,
				success : function(response) {
					$('#qstForm').html(response);
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
	<div id="errorMsg"></div>	
	<table>
		<tr>
			<td>
				<s:set var="peqid" value="peq_id"/>
				<s:set var="petid" value="pet_id"/>
				
				<s:textarea label="Exam Question" id="examQst" style="width:200px" name="peq_content"></s:textarea>
				<s:select label="Status" list="#{'A':'Active', 'I':'Inactive'}" name="peq_status" id="status" style="height: 25px; width: 206px"></s:select>
				
				<s:submit onclick="updateExamQuestion('${petid}', '${peqid}')" value="Update"/>
			</td>
		</tr>
	</table>

</body>
</html>