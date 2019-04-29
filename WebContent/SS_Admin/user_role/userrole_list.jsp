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
                $('#uRoleList').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>
<div id="userRoleList">
	<div style="background-color: #f5f5f5; color: #3d6e9f; height: 30px; width: 100%; padding-top: 10px; cursor: pointer;">
		<div onclick="addNewUserRole()" style="width: 160px"><img alt="click" src="images/next.gif" width="8px">&nbsp;Add New User Role</div>
	</div>
	<div style="height: 10px"></div>
	
	<table id="uRoleList" width="100%" cellpadding="0" cellspacing="1">
		<thead style="background-color: #f5f5f5; height: 30px; text-align: center;">
			<tr>
				<td>No</td>
				<td>Role Type Name</td>
				<td>Role Related with</td>
				<td>Role status</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</thead>
		<s:iterator status="stat" value="allUserRoleList">
			<tr style="height: 30px">
				<td width="5%" align="center">
					<s:property value="#stat.index + 1"/>
					<s:set var="ut_id" value="allUserRoleList[#stat.index].utId"/>
					<s:set var="ut_name" value="allUserRoleList[#stat.index].ut_name"/>
				</td>
				<td>
					<s:property value="allUserRoleList[#stat.index].ut_name"/>
				</td>
				<td>
					<s:property value="allUserRoleList[#stat.index].related_with"/>
				</td>
				<td>
					<s:set var="status" value="allUserRoleList[#stat.index].ut_status"/>
					<s:if test="%{#status == \"A\"}">Active</s:if>
					<s:else>Inactive</s:else>
				</td>
				<td style="text-align: center;">
					<div onclick="relateWithModule('${ut_id}', '${ut_name}')" style="cursor: pointer; color: #3d6e9f">Relate with Module</div>
				</td>
				<td align="center">
					<div onclick="editUserRole('${ut_id}', '${stat.index}')" style="color:#3d6e9f; cursor:pointer;">
						Edit
					</div>
				</td>
			</tr>
		</s:iterator>
	</table>
</div>
</body>
</html>