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
                $('#CPRelList').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

<script type="text/javascript">
	function saveCategoryPointRel(bhc_id){
		
		var bhp_id = $('#bhp_id').val();
		
		if(bhp_id == "0"){
			document.getElementById("eMsg").innerHTML = "Point must be filed.";
		} else {
		
			$("#cprel_page").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "behaviouralevaluation_catpointrelsave.action",				
				data : "bhc_id=" + bhc_id + "&bhp_id=" + bhp_id,
				success : function(response) {					
					$('#cprel_page').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	function categoryPointsRelEditForm(bhcp_id, bhp_content, bhcp_status, bhc_id, bhc_name){
		$("#saveEditForm").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_catpointrelupdateform.action",				
			data : "bhcp_id=" + bhcp_id + "&bhp_content=" + bhp_content + "&bhcp_status=" + bhcp_status + "&bhc_id=" + bhc_id + "&bhc_name=" + bhc_name,
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

<div id="cprel_page">

	<s:set var="bhc_id" value="bhc_id"/>
	<s:set var="bhc_name" value="bhc_name"/>
	
	<div style="background-color: #f5f5f5; height: 30px; padding-top: 10px;">
		Points related with category - <span style="color: #3d6e9f"><s:property value="bhc_name"/></span>
	</div>

	<table>
		<tr>
			<td style="width: 70%; padding-left: 5px;" valign="top">
				
				<div>
					<table id="CPRelList" width="100%" cellpadding="0" cellspacing="1">
						<thead style="background-color: #f5f5f5;">
							<tr style="height: 35px;">
								<th width="5%">No</th>
								<th width="45%">H. Point Content</th>
								<th width="10%">Status</th>
							</tr>
						</thead>
						<s:iterator status="stat" value="related_point_list">
							<tr style="height: 40px;">
								<td align="center">
									<s:property value="%{#stat.index + 1}"/>
									<s:set var="bhcp_id" value="related_point_list[#stat.index].bhcp_id"/>
									<s:set var="bhp_content" value="related_point_list[#stat.index].bhp_content"/>
									<s:set var="bhcp_status" value="related_point_list[#stat.index].bhcp_status"/>
								</td>
								<td>									
									<div style="color: #3d6e9f; cursor: pointer;" onclick="categoryPointsRelEditForm('${bhcp_id}', '${bhp_content}', '${bhcp_status}', '${bhc_id}', '${bhc_name}')">
										<s:property value="related_point_list[#stat.index].bhp_content"/>										
									</div>
								</td>
								<td>							
									<s:if test="%{#bhcp_status == \"A\"}">Active</s:if>
									<s:else>Inactive</s:else>
								</td>
							</tr>
						</s:iterator>
					</table>
				</div>
			
			</td>
			<td>&nbsp;</td>
			<td style="width: 30%; padding-left: 5px;" valign="top">
			
				<div id="eMsg" style="color: #ff0000; width: 100%; text-align: center;"></div>
				<div style="color: #ff0000; width: 100%; text-align: center;"><s:actionerror id="errMsg"/></div>
				
				<div id="saveEditForm" class="formboarder">	
					<div class="formtitle">Points List</div>
					<table align="center"><tr><td>
						<br/>
						<table align="center">
							<s:select id="bhp_id" list="unrelated_point_list" headerValue="---" headerKey="0" listValue="bhp_content" listKey="bhp_id" style="width: 305px; height: 40px;"/>
						</table>
						<table align="center">
							<s:submit value="Save" onclick="saveCategoryPointRel('${bhc_id}')" style="width: 205px; height: 30px;"/>
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