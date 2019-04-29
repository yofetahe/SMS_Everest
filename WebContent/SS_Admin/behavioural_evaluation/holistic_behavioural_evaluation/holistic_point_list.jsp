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
                $('#hCatList').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

<script type="text/javascript">

	function saveBevalHolisticPoint(){
		var bhp_content = $('#bhp_content').val();
		var bhp_desc = $('#bhp_desc').val();
		
		if(bhp_content == ""){
			document.getElementById("eMsg").innerHTML = "Category name must be filed.";
		} else if(bhp_desc == ""){
			document.getElementById("eMsg").innerHTML = "Category description must be filed.";
		} else {
		
			$("#hp_page").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "behaviouralevaluation_holpointsave.action",				
				data : "bhp_content=" + bhp_content + "&bhp_desc=" + bhp_desc,
				success : function(response) {					
					$('#hp_page').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	function bevalHolisticPointEditForm(bhp_id, bhp_content, bhp_desc, bhp_status){
		$("#saveEditForm").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_holisticpointeditform.action",				
			data : "bhp_id=" + bhp_id + "&bhp_content=" + bhp_content + "&bhp_desc=" + bhp_desc + "&bhp_status=" + bhp_status,
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

<div id="hp_page">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="width: 30%; padding-left: 5px;" valign="top">
			
				<div id="eMsg" style="color: #ff0000; width: 100%; text-align: center;"></div>
				<div style="color: #ff0000; width: 100%; text-align: center;"><s:actionerror id="errMsg"/></div>
				<div id="saveEditForm" class="formboarder">
					<div class="formtitle">Save Form</div>					
					<table align="center"><tr><td>						
						<br/>
						Holistic Point Name
						<table cellpadding="0" cellspacing="0">
							<s:textfield id="bhp_content" style="width: 200px; height: 30px;"/>
						</table>
						Holistic Point Description
						<table cellpadding="0" cellspacing="0">
							<s:textarea id="bhp_desc" rows="10px" style="width: 200px;"/>
						</table>
						<table cellpadding="0" cellspacing="0" style="padding-top: 5px;">
							<s:submit onclick="saveBevalHolisticPoint()" value="Save" style="width: 205px; height: 30px;"/>
						</table>
					</td></tr></table>
				</div>
			
			</td>
			<td>&nbsp;</td>
			<td valign="top">
			
				<div>
					<table id="hCatList" width="100%" cellpadding="0" cellspacing="1">
						<thead style="background-color: #f5f5f5;">
							<tr style="height: 35px;">
								<th width="5%">No</th>
								<th width="45%">H. Point Content</th>
								<th width="40%">H. Point Description</th>
								<th width="10%">Status</th>
							</tr>
						</thead>
						<s:iterator status="stat" value="holistic_point_list">
							<tr style="height: 40px;">
								<td align="center">
									<s:property value="%{#stat.index + 1}"/>
									<s:set var="bhp_id" value="holistic_point_list[#stat.index].bhp_id"/>
									<s:set var="bhp_content" value="holistic_point_list[#stat.index].bhp_content"/>
									<s:set var="bhp_desc" value="holistic_point_list[#stat.index].bhp_desc"/>
									<s:set var="bhp_status" value="holistic_point_list[#stat.index].bhp_status"/>
								</td>
								<td>									
									<div style="color: #3d6e9f; cursor: pointer;" onclick="bevalHolisticPointEditForm('${bhp_id}', '${bhp_content}', '${bhp_desc}', '${bhp_status}')">
										<s:property value="holistic_point_list[#stat.index].bhp_content"/>										
									</div>
								</td>
								<td>
									<s:property value="holistic_point_list[#stat.index].bhp_desc"/>
								</td>
								<td>							
									<s:if test="%{#bhp_status == \"A\"}">Active</s:if>
									<s:else>Inactive</s:else>
								</td>
							</tr>
						</s:iterator>
					</table>
				</div>
			
			</td>
		</tr>
	</table>
</div>

</body>
</html>