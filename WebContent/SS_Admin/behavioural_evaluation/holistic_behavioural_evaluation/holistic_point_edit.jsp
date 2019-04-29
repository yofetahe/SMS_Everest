<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script type="text/javascript">
	function updateBevalHolisticPoint(bhp_id){
		
		var bhp_content = $('#bhp_content').val();
		var bhp_desc = $('#bhp_desc').val();
		var bhp_status = $('#bhp_status').val();
		
		if(bhp_content == ""){
			document.getElementById("eMsg").innerHTML = "Category name must be filed.";
		} else if(bhp_desc == ""){
			document.getElementById("eMsg").innerHTML = "Category description must be filed.";
		} else {
		
			$("#hp_page").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "behaviouralevaluation_holpointupdate.action",				
				data : "bhp_id=" + bhp_id + "&bhp_content=" + bhp_content + "&bhp_desc=" + bhp_desc + "&bhp_status=" + bhp_status,
				success : function(response) {					
					$('#hp_page').html(response);
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
	<s:set var="bhp_id" value="bhp_id"/>
	<table align="center"><tr><td>
		<div class="formtitle">Update Form</div>
		<br/>
		Holistic Point Content
		<table cellpadding="0" cellspacing="0">
			<s:textfield id="bhp_content" name="bhp_content" style="width: 200px; height: 30px;"/>
		</table>
		Holistic Point Description
		<table cellpadding="0" cellspacing="0">
			<s:textarea id="bhp_desc" name="bhp_desc" rows="10px" style="width: 200px;"/>
		</table>
		Holistic Point Status
		<table cellpadding="0" cellspacing="0">
			<s:select id="bhp_status" name="bhp_status" list="#{'A':'Active', 'I':'Inactive'}" rows="10px" style="width: 205px; height: 30px;"/>
		</table>
		<table cellpadding="0" cellspacing="0" style="padding-top: 5px;">
			<s:submit onclick="updateBevalHolisticPoint('${bhp_id}')" value="Update" style="width: 205px; height: 30px;"/>
		</table>
	</td></tr></table>

</body>
</html>