<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<script type="text/javascript">
	function saveExamQuestion(pet_id){
		var examQst = $('#examQst').val();
		
		if(examQst == ""){
			document.getElementById("errorMsg").innerHTML = "<span style=\"color:red\">The question is blank.</span>";
		} else {
			$("#qstForm").html("<img align=\"center\" src=\"images/loader.gif\"/>");		
			$.ajax({
				type : "POST",
				url : "pe_add_question.action",				
				data : "pet_id=" + pet_id + "&peq_content=" + examQst,
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
				<s:set var="petid" value="pet_id"/>
				<s:textarea label="Exam Question" id="examQst" style="width:200px"></s:textarea>
								
				<s:submit onclick="saveExamQuestion('${petid}')" value="Save"/>
			</td>
		</tr>
	</table>

</body>
</html>