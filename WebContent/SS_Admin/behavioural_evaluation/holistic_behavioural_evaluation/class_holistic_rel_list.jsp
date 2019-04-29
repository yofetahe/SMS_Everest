<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<!-- Datatable -->
        <style type="text/css" title="currentStyle">
            @import "datatable/datatable_2/media/css/demo_page.css";
            @import "datatable/datatable_2/media/css/demo_table.css";
            @import "media/css/TableTools.css";
        </style>
        <script type="text/javascript" charset="utf-8" src="datatable/datatable_2/media/js/jquery.js"></script>
        <script type="text/javascript" charset="utf-8" src="datatable/datatable_2/media/js/jquery.dataTables.js"></script>
        <script type="text/javascript" charset="utf-8" src="media/js/ZeroClipboard.js"></script>
        <script type="text/javascript" charset="utf-8" src="media/js/TableTools.js"></script>
        <script type="text/javascript" charset="utf-8">
            $(document).ready( function () {
                $('#ClassCatList').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

<script type="text/javascript">

	function saveClassCatRel(cl_id){
		var bhc_id = $('#bhc_id').val();
		
		if(bhc_id == 0){
			document.getElementById("errMsg").innerHTML = "Please select category";
		} else {
		
			$("#clcatrel_page").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "behaviouralevaluation_saveclasscategoryrel.action",				
				data : "cl_id=" + cl_id + "&bhc_id=" + bhc_id,
				success : function(response) {					
					$('#clcatrel_page').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function editClassCatRel(bhccr_id, cl_id, bhc_name, bhccr_status){
		$("#saveEditForm").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_classcategoryrelupdateform.action",				
			data : "cl_id=" + cl_id + "&bhccr_id=" + bhccr_id + "&bhc_name=" + bhc_name + "&bhccr_status=" + bhccr_status,
			success : function(response) {					
				$('#saveEditForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
</script>


</head>
<body>

<s:set var="cl_id" value="cl_id"/>
<div id="clcatrel_page">
	<table width="100%" cellpadding="0" cellspacing="0" style="padding-top: 5px;">
		<tr>
			<td width="70%" valign="top">
			
				<table id="ClassCatList" width="100%" cellpadding="0" cellspacing="1">
					<thead style="background-color: #f5f5f5;">
						<tr style="height: 35px;">
							<th width="10%">No</th>
							<th width="50%">Category Name</th>
							<th width="25%">Relation Status</th>
							<th>&nbsp;</th>
						</tr>
					</thead>
					<s:iterator status="stat" value="related_catagory_list">
						<tr style="height: 30px;">
							<td align="center">
								<s:property value="%{#stat.index + 1}"/>
								<s:set var="bhccr_id" value="related_catagory_list[#stat.index].bhccr_id"/>
							</td>
							<td>
								<s:property value="related_catagory_list[#stat.index].bhc_name"/>
								<s:set var="bhc_name" value="related_catagory_list[#stat.index].bhc_name"/>								
							</td>
							<td>
								<s:set var="bhccr_status" value="related_catagory_list[#stat.index].bhccr_status"/>
								<s:if test="%{#bhccr_status == \"A\"}">Active</s:if>
								<s:else>Inactive</s:else>
							</td>
							<td align="center" style="color: #3d6e9f;">
								<div style="cursor: pointer;" onclick="editClassCatRel('${bhccr_id}', '${cl_id}', '${bhc_name}', '${bhccr_status}')">Edit</div>
							</td>			
						</tr>
					</s:iterator>
				</table>
				
			</td>
			<td width="2%">&nbsp;</td>
			<td valign="top">
			
				<div id="errMsg" style="color: #ff0000;"></div>
				<div style="color: #ff0000;"><s:actionmessage/> </div>
				
				<div id="saveEditForm" class="formboarder">
					<div class="formtitle">Save Form</div>
					<table align="center"><tr><td>
						<br/>
						Category<br/>
						<table cellpadding="0" cellspacing="0">
							<s:select list="unrelated_catagory_list" id="bhc_id" listKey="bhc_id" listValue="bhc_name" headerKey="0" headerValue="---" style="width: 205px; height: 30px;"/>
						</table>
						
						<table cellpadding="0" cellspacing="0" style="padding-top: 5px;">
							<s:submit value="Save" onclick="saveClassCatRel('${cl_id}')" style="width: 205px; height: 30px;"/> 
						</table>
					</td></tr></table>
					<br/>
				</div>

			</td>
		</tr>
	</table>
</div>

</body>
</html>