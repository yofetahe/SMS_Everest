<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">

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
                $('#dptList').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

<div id="deppage">
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td style="width: 30%; padding-left: 5px;" valign="top">
				<div id="eMsg" style="color: #ff0000; width: 100%; text-align: center;"></div>
				<div style="color: #ff0000; width: 100%; text-align: center;"><s:fielderror id="errMsg"/></div>
				<div id="saveEditForm" class="formboarder" align="center">					
					
						<div class="formtitle">Save Form</div>
						<br/>
						Department Name
						<table cellpadding="0" cellspacing="0">
						<s:textfield id="depname" name="dep_name" style="width: 200px; height: 30px;"/>
						</table>
						About the Department
						<table cellpadding="0" cellspacing="0">
						<s:textarea id="depdesc" name="dep_desc" rows="10px" style="width: 200px;"/>
						</table>
						<table cellpadding="0" cellspacing="0" style="padding-top: 5px;">
						<s:submit onclick="saveCocurActDepartment()" value="Save" style="width: 205px; height: 30px;"/>
						</table>
					
				</div>
			
			</td>
			<td style="width: 2%">&nbsp;</td>
			<td valign="top">
				
				<div>
					<table id="dptList" width="100%" cellpadding="0" cellspacing="1">
						<thead style="background-color: #f5f5f5;">
							<tr style="height: 35px;">
								<th width="5%">No</th>
								<th width="25%">Department Name</th>
								<th width="50%">About the Department</th>
								<th width="10%">Status</th>
								<th width="10%">&nbsp;</th>
							</tr>
						</thead>
						<s:iterator status="stat" value="depList">
							<tr style="height: 40px;">
								<td align="center">
									<s:property value="%{#stat.index + 1}"/>
									<s:set var="depid" value="depList[#stat.index].dep_id"/>
								</td>
								<td>
									<s:property value="depList[#stat.index].dep_name"/>
								</td>
								<td>
									<s:property value="depList[#stat.index].dep_desc"/>
								</td>
								<td>
									<s:set var="depstatus" value="depList[#stat.index].dep_status"/>
									<s:if test="%{#depstatus == \"A\"}">Active</s:if>
									<s:else>Inactive</s:else>
								</td>
								<td style="text-align: center;">
									<div style="color: #3d6e9f; cursor: pointer;" onclick="departmentEditForm('${depid}')">Edit</div>
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