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

	function saveBevalHolisticCategory(){
		var bhc_name = $('#bhc_name').val();
		var bhc_desc = $('#bhc_desc').val();
		
		if(bhc_name == ""){
			document.getElementById("eMsg").innerHTML = "Category name must be filed.";
		} else if(bhc_desc == ""){
			document.getElementById("eMsg").innerHTML = "Category description must be filed.";
		} else {
		
			$("#hcat_page").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "behaviouralevaluation_holcategorysave.action",				
				data : "bhc_name=" + bhc_name + "&bhc_desc=" + bhc_desc,
				success : function(response) {					
					$('#hcat_page').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	function categoryPointRel(bhc_id, bhc_name){
		$("#hcat_page").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_categorypointrel.action",				
			data : "bhc_id=" + bhc_id + "&bhc_name=" + bhc_name,
			success : function(response) {					
				$('#hcat_page').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function bevalHolisticEditForm(bhc_id, bhc_name, bhc_desc, bhc_status){
		$("#saveEditForm").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_holisticeditform.action",				
			data : "bhc_id=" + bhc_id + "&bhc_name=" + bhc_name + "&bhc_desc=" + bhc_desc + "&bhc_status=" + bhc_status,
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

<div id="hcat_page">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="width: 30%; padding-left: 5px;" valign="top">
			
				<div id="eMsg" style="color: #ff0000; width: 100%; text-align: center;"></div>
				<div style="color: #ff0000; width: 100%; text-align: center;"><s:actionerror id="errMsg"/></div>
				<div id="saveEditForm" class="formboarder">
					<div class="formtitle">Save Form</div>					
					<table align="center"><tr><td>
						<br/>
						Holistic Category Name
						<table cellpadding="0" cellspacing="0">
							<s:textfield id="bhc_name" style="width: 200px; height: 30px;"/>
						</table>
						Holistic Category Description
						<table cellpadding="0" cellspacing="0">
							<s:textarea id="bhc_desc" rows="10px" style="width: 200px;"/>
						</table>
						<table cellpadding="0" cellspacing="0" style="padding-top: 5px;">
							<s:submit onclick="saveBevalHolisticCategory()" value="Save" style="width: 205px; height: 30px;"/>
						</table>
					</td></tr></table>
					<br/>
				</div>
			
			</td>
			<td>&nbsp;</td>
			<td valign="top">
			
				<div>
					<table id="hCatList" width="100%" cellpadding="0" cellspacing="1">
						<thead style="background-color: #f5f5f5;">
							<tr style="height: 35px;">
								<th width="5%">No</th>
								<th width="25%">H. Category Name</th>
								<th width="40%">H. Category Description</th>
								<th width="10%">Status</th>
								<th width="20%">&nbsp;</th>
							</tr>
						</thead>
						<s:iterator status="stat" value="holistic_cat_list">
							<tr style="height: 40px;">
								<td align="center">
									<s:property value="%{#stat.index + 1}"/>
									<s:set var="bhc_id" value="holistic_cat_list[#stat.index].bhc_id"/>
									<s:set var="bhc_name" value="holistic_cat_list[#stat.index].bhc_name"/>
									<s:set var="bhc_desc" value="holistic_cat_list[#stat.index].bhc_desc"/>
									<s:set var="bhc_status" value="holistic_cat_list[#stat.index].bhc_status"/>
								</td>
								<td>									
									<div style="color: #3d6e9f; cursor: pointer;" onclick="bevalHolisticEditForm('${bhc_id}', '${bhc_name}', '${bhc_desc}', '${bhc_status}')">
										<s:property value="holistic_cat_list[#stat.index].bhc_name"/>										
									</div>
								</td>
								<td>
									<s:property value="holistic_cat_list[#stat.index].bhc_desc"/>
								</td>
								<td>							
									<s:if test="%{#bhc_status == \"A\"}">Active</s:if>
									<s:else>Inactive</s:else>
								</td>
								<td  style="text-align: center;">
									<div style="color: #3d6e9f; cursor: pointer;" onclick="categoryPointRel('${bhc_id}', '${bhc_name}')">Relate with Points</div>
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