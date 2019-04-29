<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<script type="text/javascript">
	function updateCategoryPointRel(bhcp_id, bhc_id, bhc_name){
		
		var bhcp_status = $('#bhcp_status').val();
		
		$("#cprel_page").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_catpointrelupdate.action",				
			data : "bhcp_id=" + bhcp_id + "&bhcp_status=" + bhcp_status + "&bhc_id=" + bhc_id + "&bhc_name=" + bhc_name,
			success : function(response) {					
				$('#cprel_page').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
</script>

</head>
<body>

	<s:set var="bhcp_id" value="bhcp_id"/>
	<s:set var="bhc_id" value="bhc_id"/>
	<s:set var="bhc_name" value="bhc_name"/>
	
	<div class="formtitle">Points List</div>
	<br/>
	<table><tr><td style="padding-left: 5px;">
		Points Content: <s:property value="bhp_content"/>
	</td></tr></table>
	<table align="center">
		<s:select id="bhcp_status" list="#{'A':'Active', 'I':'Inactive'}" name="bhcp_status" style="width: 305px; height: 25px;"/>
	</table>
	<table align="center">
		<s:submit value="Update" onclick="updateCategoryPointRel('${bhcp_id}', '${bhc_id}', '${bhc_name}')" style="width: 205px; height: 30px;"/>
	</table>

</body>
</html>