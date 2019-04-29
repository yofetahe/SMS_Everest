<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script type="text/javascript">
	function qstDescSave(peq_id){
		var peq_desc = $('#qdes').val();
		var qSugBk = $('#qSugBk').val();
		$("#qstForm").html("<img align=\"center\" src=\"images/loader.gif\"/>");		
		$.ajax({
			type : "POST",
			url : "question_desc_save.action",		
			data : "peq_id=" + peq_id + "&explanation_dtl=" + peq_desc + "&suggested_material=" + qSugBk,
			success : function(response) {
				$('#qstForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
</script>
</head>
<body>
	
	<s:set var="peq_id" value="peq_id"/>
	<div style="padding-top: 5px; height: 30px; background-color: #e5e5e5; width: auto;"> 
		Question: <s:property value="qst[0].peq_content"/> 
	</div>
	<table>
		<tr>
			<td>
				<s:textarea label="Question Description" id="qdes" style="height: 200px; width: 400px;"></s:textarea>
				<s:textarea label="Suggested Book" id="qSugBk" style="height: 50px; width: 400px;"></s:textarea>
				<s:submit onclick="qstDescSave('${peq_id}')" value="Save"/>
			</td>
		</tr>
	</table>

</body>
</html>