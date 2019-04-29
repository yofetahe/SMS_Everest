<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script type="text/javascript">
	function updateBevalHolisticCategory(bhc_id){
		
		var bhc_name = $('#bhc_name').val();
		var bhc_desc = $('#bhc_desc').val();
		var bhc_status = $('#bhc_status').val();
		
		if(bhc_name == ""){
			document.getElementById("eMsg").innerHTML = "Category name must be filed.";
		} else if(bhc_desc == ""){
			document.getElementById("eMsg").innerHTML = "Category description must be filed.";
		} else {
		
			$("#hcat_page").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "behaviouralevaluation_holcategoryupdate.action",				
				data : "bhc_id=" + bhc_id + "&bhc_name=" + bhc_name + "&bhc_desc=" + bhc_desc + "&bhc_status=" + bhc_status,
				success : function(response) {					
					$('#hcat_page').html(response);
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
	<s:set var="bhc_id" value="bhc_id"/>
	<table align="center"><tr><td>
		<div class="formtitle">Update Form</div>
		<br/>
		Holistic Category Name
		<table cellpadding="0" cellspacing="0">
			<s:textfield id="bhc_name" name="bhc_name" style="width: 200px; height: 30px;"/>
		</table>
		Holistic Category Description
		<table cellpadding="0" cellspacing="0">
			<s:textarea id="bhc_desc" name="bhc_desc" rows="10px" style="width: 200px;"/>
		</table>
		Holistic Category Status
		<table cellpadding="0" cellspacing="0">
			<s:select id="bhc_status" name="bhc_status" list="#{'A':'Active', 'I':'Inactive'}" rows="10px" style="width: 205px; height: 30px;"/>
		</table>
		<table cellpadding="0" cellspacing="0" style="padding-top: 5px;">
			<s:submit onclick="updateBevalHolisticCategory('${bhc_id}')" value="Update" style="width: 205px; height: 30px;"/>
		</table>
	</td></tr></table>

</body>
</html>