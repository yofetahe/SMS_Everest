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
                $('#acUserList').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

<table width="100%" cellpadding="0" cellspacing="0">
	<tr>
		<td width="70%">
		
			<div style="background-color: #f5f5f5; color: #3d6e9f; height: 30px; width: 100%; padding-top: 10px; cursor: pointer;">
				<div onclick="addTeachNewUser()" style="width: 110px"><img alt="click" src="images/next.gif" width="8px">&nbsp;Add New User</div>
			</div>
			<div style="height: 10px"></div>
			
			<div style="color: #3d6e9f; width: 100%; text-align: center;"><s:actionmessage/></div>
		
			<table id="acUserList" width="100%" cellpadding="0" cellspacing="1">
				<thead style="background-color: #f5f5f5; height: 40px; text-align: center;">
					<tr>
						<td style="height: 30px;">No</td>
						<td>User Name</td>
						<td>User Role</td>
						<td>Status</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</thead>
				<s:iterator status="stat" value="teachUserList">
					<tr style="height: 30px">
						<td width="8%" align="center">
							<s:property value="#stat.index + 1"/>
						</td>
						<td>
							<s:property value="teachUserList[#stat.index].name"/>
							<s:set var="name" value="teachUserList[#stat.index].name"/>
							<s:set var="ti_id" value="teachUserList[#stat.index].ti_id"/>
							<s:set var="ua_id" value="teachUserList[#stat.index].ua_id"/>
						</td>
						<td>
							<s:set var="ut_id" value="teachUserList[#stat.index].utId"/>
							<s:property value="teachUserList[#stat.index].ut_name"/>
						</td>
						<td>
							<s:set var="ua_status" value="teachUserList[#stat.index].ua_status"/>
							<s:if test="%{#ua_status == \"A\"}">Active</s:if>
							<s:else>Inactive</s:else>
						</td>
						<td width="10%" align="center">
							<div onclick="editTeacherUser('${ua_id}', '${stat.index}', '${ti_id}', '${ua_status}', '${ut_id}', '${name}')" style="color: #3d6e9f; cursor: pointer;">
								Edit
							</div>
						</td>
						<td width="20%" align="center">
							<div onclick="resetTeacherUserPassword('${ua_id}', '${stat.index}', '${ti_id}', '${ua_status}', '${ut_id}', '${name}')" style="color: #3d6e9f; cursor: pointer;">
								Reset Password
							</div>
						</td>
					</tr>
				</s:iterator>
			</table>
		
		</td>
		<td>&nbsp;</td>
		<td width="29%">
			<div id="">
			
			</div>		
		</td>		
	</tr>
</table>
					
</body>
</html>