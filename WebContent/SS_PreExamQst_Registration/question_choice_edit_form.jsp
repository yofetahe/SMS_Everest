<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

	<script type="text/javascript">
		function updateChoice(pec_id, peq_id){
			var choice = $('#choice').val();
			var correctAnswer = $('#correctAnswer').val();
			var choiceImage = $('#choiceImage').val();
			var status = $('#status').val();
			
			if(choice == ""){
				document.getElementById('errorMsg').innerHTML="Choice is empty."
			} else {
				$("#qstForm").html("<img align=\"center\" src=\"images/loader.gif\"/>");		
				$.ajax({
					type : "POST",
					url : "pe_choice_update.action",				
					data : "pec_id=" + pec_id + "&pec_content=" + choice + "&pec_correct=" + correctAnswer + "&pec_image=" + choiceImage + "&pec_status=" + status + "&peq_id=" + peq_id,
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
				
				<s:set var="pec_id" value="pec_id"/>
				
				<s:textarea label="Choice" id="choice" style="width:200px" name="pec_content"></s:textarea>
				<s:select label="Correct Answer" id="correctAnswer" list="#{'Y':'Yes', 'N':'No'}" name="pec_correct" style="height: 25px; width: 206px"/>
				<s:select label="Status" id="status" list="#{'A':'Active', 'I':'Inactive'}" name="#pec_status" style="height: 25px; width: 206px"/>
				
				<s:submit onclick="updateChoice('${pec_id}', '${peq_id}')" value="Update"/>
			
			</td>
		</tr>
	</table>
	
</body>
</html>