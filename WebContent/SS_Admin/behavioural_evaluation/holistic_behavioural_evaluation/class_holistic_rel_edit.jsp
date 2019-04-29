<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<script type="text/javascript">
	function udpateClassCatRel(bhccr_id, cl_id){
		
		var bhccr_status = $('#bhccr_status').val();
		
		$("#clcatrel_page").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_updateclasscategoryrel.action",				
			data : "bhccr_id=" + bhccr_id + "&bhccr_status=" + bhccr_status + "&cl_id=" + cl_id,
			success : function(response) {					
				$('#clcatrel_page').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
		
	}
</script>

</head>
<body>
	
	<s:set var="bhccr_id" value="bhccr_id"/>
	<s:set var="cl_id" value="cl_id"/>
	
	<table align="center"><tr><td>	
		<div class="formtitle">Update Form</div>
		<br/>
			
		Category: <span style="color: #6e5d9e"> <s:property value="bhc_name"/> </span> <br/><br/>
		
		Class-Category Relation<br/>
		<table cellpadding="0" cellspacing="0">
			<s:select list="#{'A':'Active', 'I':'Inactive'}" id="bhccr_status" name="bhccr_status" style="width: 205px; height: 30px;"/>
		</table>
		
		<table cellpadding="0" cellspacing="0" style="padding-top: 5px;">
			<s:submit value="Update" onclick="udpateClassCatRel('${bhccr_id}', '${cl_id}')" style="width: 205px; height: 30px;"/> 
		</table>
	</td></tr></table>

</body>
</html>