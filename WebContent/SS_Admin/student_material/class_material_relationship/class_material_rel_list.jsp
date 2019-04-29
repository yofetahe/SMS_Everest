<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script src="../../js/admin.js" type="text/javascript"></script>

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
                $('#materialClassRelList').dataTable( {
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
			
				<table id="materialClassRelList" cellpadding="0" cellspacing="1" width="100%">
					<thead style="background-color: #f5f5f5; height: 30px; text-align: center;">
						<tr>
							<td>No</td>
							<td>Class Name</td>
							<td>Material Name</td>
							<td>Amount</td>
							<td>Status</td>
							<td>&nbsp;</td>
						</tr>
					</thead>
					<s:iterator status="stat" value="materialClassRelList">
						<tr style="height: 30px">
							<td align="center"> 
								<s:property value="%{#stat.index + 1}"/>
								<s:set var="pmc_id" value="materialClassRelList[#stat.index].pmc_id"/>
								<s:set var="ptm_id" value="materialClassRelList[#stat.index].ptm_id"/>
								<s:set var="cl_id" value="materialClassRelList[#stat.index].cl_id"/>
								<s:set var="pay_amount" value="materialClassRelList[#stat.index].payment_amount"/>
							</td>
							<td> <s:property value="materialClassRelList[#stat.index].cl_name"/> </td>
							<td> <s:property value="materialClassRelList[#stat.index].ptm_name"/> </td>
							<td> <s:property value="materialClassRelList[#stat.index].payment_amount"/> </td>
							<td> 
								<s:set var="status" value="materialClassRelList[#stat.index].pmc_status"/>
								<s:if test="%{#status == \"A\"}">Active</s:if>
								<s:else>Inactive</s:else>
							</td>
							<td align="center">
								<div style="color: #3d6e9f; cursor: pointer;" onclick="editMaterialClassRel('${pmc_id}', '${ptm_id}', '${cl_id}', '${pay_amount}', '${status}')">
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
						Class<span style="color: #ff0000">*</span>
						<table cellpadding="0" cellspacing="0">
							<s:select list="classList" id="cl_id" listKey="cl_id" listValue="class_name" headerKey="0" headerValue="---" style="width: 205px; height: 30px;"/>
						</table>
						Material Name<span style="color: #ff0000">*</span>
						<table cellpadding="0" cellspacing="0">
							<s:select list="activeMatList" id="ptm_id" listKey="ptm_id" listValue="ptm_name" headerKey="0" headerValue="---" style="width: 205px; height: 30px;"/>
						</table>
						Payment Amount<span style="color: #ff0000">*</span>
						<table cellpadding="0" cellspacing="0">
							<s:textfield id="pay_amount" style="width: 200px; height: 20px;"/>
						</table>
						<table cellpadding="0" cellspacing="0" style="padding-top: 5px;">
							<s:submit onclick="saveMaterialClassRel()" value="Save" style="width: 205px; height: 30px;"/>
						</table>
					</td></tr></table>
				</div>
				
			</td>
		</tr>
	</table>	
</div>

</body>
</html>