<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

	<script type="text/javascript">
		function saveChoice(peq_id){
			var choice = $('#choice').val();
			var correctAnswer = $('#correctAnswer').val();
			
			if(choice == ""){
				document.getElementById('errorMsg').innerHTML="Choice is empty.";
			} else if(correctAnswer == 0){
				document.getElementById('errorMsg').innerHTML="Check Correct Answer";
			} else {
				$("#qstForm").html("<img align=\"center\" src=\"images/loader.gif\"/>");		
				$.ajax({
					type : "POST",
					url : "pe_choice_save.action",				
					data : "peq_id=" + peq_id + "&pec_content=" + choice + "&pec_correct=" + correctAnswer,
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
	<s:set var="peq_id" value="peq_id"/>
	<div id="errorMsg"></div>
	
	<table>
		<tr>
			<td>
				<s:textarea label="Choice" id="choice" style="width:200px"></s:textarea>
				<s:select label="Correct Answer" id="correctAnswer" headerKey="0" headerValue="Correct Answer" list="#{'Y':'Yes', 'N':'No'}" style="height: 25px; width: 206px"/>
			</td>
		</tr>
		<tr><td>
			<button onclick="saveChoice('${peq_id}')" class="btn">Save</button>						
		</td></tr>
	</table>
	
</body>
</html>