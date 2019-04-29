<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script type="text/javascript">
	function qstDescUpdate(pee_id, peq_id){
		var peq_desc = $('#qdes').val();
		var qSugBk = $('#qSugBk').val();
		var qdsStatus = $('#qdsStatus').val();
		
		$("#qstForm").html("<img align=\"center\" src=\"images/loader.gif\"/>");		
		$.ajax({
			type : "POST",
			url : "question_desc_update.action",		
			data : "pee_id=" + pee_id + "&explanation_dtl=" + peq_desc + "&suggested_material=" + qSugBk + "&pee_status=" + qdsStatus + "&peq_id=" + peq_id,
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
	
	<s:set var="pee_id" value="pee_id"/>
	<s:set var="peq_id" value="peq_id"/>
	<div style="padding-top: 5px; height: 30px; background-color: #e5e5e5; width: auto;"> 
		Question: <s:property value="qst[0].peq_content"/> 
	</div>
	<table>
		<tr>
			<td>
				<s:textarea label="Question Description" id="qdes" style="min-height: 200px; min-width: 400px; max-width: 400px;" name="explanation_dtl"></s:textarea>
				<s:textarea label="Suggested Book" id="qSugBk" style="min-height: 50px; min-width: 400px; max-width: 400px;" name="suggested_material"></s:textarea>
				<s:select label="Status" id="qdsStatus" list="#{'A':'Active', 'I':'Inactive'}" name="pee_status" style="height: 30px; width: 200px;"/>
				
				<s:submit onclick="qstDescUpdate('${pee_id}', '${peq_id}')" value="Update"/>
			</td>
		</tr>
	</table>

</body>
</html>