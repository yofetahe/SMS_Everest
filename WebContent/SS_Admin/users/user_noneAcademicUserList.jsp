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
		<td width="60%">
		
			<div style="background-color: #f5f5f5; color: #3d6e9f; height: 30px; width: 100%; padding-top: 10px; cursor: pointer;">
				<div onclick="addNoneAcNewUser()" style="width: 110px"><img alt="click" src="images/next.gif" width="8px">&nbsp;Add New User</div>
			</div>
			<div style="height: 10px"></div>
		
			<table id="acUserList" width="100%" cellpadding="0" cellspacing="1">
				<thead style="background-color: #f5f5f5; height: 30px; text-align: center;">
					<tr>
						<td>No</td>
						<td>User Name</td>
						<td>User Role</td>
						<td>Status</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</thead>
				<s:iterator status="stat" value="noneAcUserList">
					<tr style="height: 30px">
						<td width="8%" align="center">
							<s:property value="#stat.index + 1"/>
							<s:set var="ua_id" value="noneAcUserList[#stat.index].ua_id"/>
							<s:set var="name" value="noneAcUserList[#stat.index].name"/>
						</td>
						<td>
							<s:property value="noneAcUserList[#stat.index].name"/>
						</td>
						<td width="25%">
							<s:property value="noneAcUserList[#stat.index].userType"/>
						</td>
						<td>
							<s:set var="status" value="noneAcUserList[#stat.index].ua_status"/>
							<s:if test="%{#status == \"A\"}">Active</s:if>
							<s:else>Inactive</s:else>
						</td>
						<td width="10%" align="center">
							<div onclick="editAcdUser('${stat.index}')" style="color: #3d6e9f; cursor: pointer;">
								Edit
							</div>
						</td>
						<td width="20%" align="center">
							<div onclick="resetNoneTeacherUserPassword('${ua_id}', '${stat.index}', '${name}')" style="color: #3d6e9f; cursor: pointer;">
								Reset Password
							</div>
						</td>
					</tr>
				</s:iterator>
			</table>
		
		</td>
		<td>&nbsp;</td>
		<td width="39%">
			<div id="">
			
			</div>		
		</td>		
	</tr>
</table>
					
</body>
</html>