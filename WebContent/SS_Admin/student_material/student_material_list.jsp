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
                $('#materialList').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>
<div id="matListPage">
	<table cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td valign="top">
			
				<table id="materialList" cellpadding="0" cellspacing="1" width="100%">
					<thead style="background-color: #f5f5f5; height: 30px; text-align: center;">
						<tr>
							<td>No</td>
							<td>Material Name</td>
							<td>Material Description</td>
							<td>Status</td>
							<td>&nbsp;</td>
						</tr>
					</thead>
					<s:iterator status="stat" value="materialList">
						<tr style="height: 30px">
							<td align="center"> 
								<s:property value="%{#stat.index + 1}"/>
								<s:set var="ptm_id" value="materialList[#stat.index].ptm_id"/> 
								<s:set var="ptm_name" value="materialList[#stat.index].ptm_name"/>
								<s:set var="ptm_desc" value="materialList[#stat.index].ptm_desc"/>
							</td>
							<td> <s:property value="materialList[#stat.index].ptm_name"/> </td>
							<td> <s:property value="materialList[#stat.index].ptm_desc"/> </td>
							<td> 
								<s:set var="status" value="materialList[#stat.index].ptm_status"/>
								<s:if test="%{#status == \"A\"}">Active</s:if>
								<s:else>Inactive</s:else>
							</td>
							<td align="center">
								<div style="color: #3d6e9f; cursor: pointer;" onclick="editStudentMaterial('${ptm_id}', '${ptm_name}', '${ptm_desc}', '${status}')">
									Edit
								</div>
							</td>
						</tr>
					</s:iterator>
				</table>
			
			</td>
			<td>&nbsp;</td>
			<td width="40%" valign="top" align="center">
				<div id="eMsg" style="color: #ff0000; width: 100%; text-align: center;"></div>
				<div id="saveEditForm" class="formboarder" style="width: 250px">					
					<table align="center"><tr><td>
						<div class="formtitle">Save Form</div>
						<br/>
						Material Name
						<table cellpadding="0" cellspacing="0">
							<s:textfield id="ptm_name" style="width: 200px; height: 30px;"/>
						</table>
						Material Description
						<table cellpadding="0" cellspacing="0">
							<s:textarea id="ptm_desc" rows="10px" style="width: 200px;"/>
						</table>
						<table cellpadding="0" cellspacing="0" style="padding-top: 5px;">
							<s:submit onclick="saveMaterial()" value="Save" style="width: 205px; height: 30px;"/>
						</table>
					</td></tr></table>
				</div>
				
			</td>
		</tr>
	</table>	
</div>
</body>
</html>