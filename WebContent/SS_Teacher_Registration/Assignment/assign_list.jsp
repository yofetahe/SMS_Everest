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
                $('#assignList').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

<script src="js/sms_teacher_registration.js" type="text/javascript"></script>

</head>
<body>

<s:set var="ti_id" value="ti_id"/>

<div id="assignContainer">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="background-color: #f5f5f5; height: 50px">
				<div onclick="teacherAssignment('${ti_id}')" style="color: #3d6e9f; cursor: pointer"> <img src="images/next.gif" width="8px"/> Add Teacher Assignment</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
				
				<div id="container">
					<table id="assignList" cellpadding="0" cellspacing="1" width="100%">
						<thead style="background-color: #f5f5f5; height: 30px;" align="center">
							<tr height="30px">
								<td>No</td>
								<td>Grade</td>
								<td>Subject</td>
								<td>Status</td>
								<td>&nbsp;</td>
							</tr>
						</thead>
						<s:iterator status="stat" value="assign_rslt">
							<tr height="30px">
								<td align="center">
									<s:property value="#stat.index + 1"/>
									<s:set var="ta_id" value="assign_rslt[#stat.index].ta_id"/>									
								</td>
								<td>
									<s:property value="assign_rslt[#stat.index].cl_name"/>
									<s:set var="cl_id" value="assign_rslt[#stat.index].cl_id"/>
								</td>
								<td>
									<s:property value="assign_rslt[#stat.index].sub_name"/>
									<s:set var="sub_id" value="assign_rslt[#stat.index].sub_id"/>
								</td>
								<td>
									<s:set var="ta_status" value="assign_rslt[#stat.index].ta_status"/>
									<s:if test="%{#ta_status == \"A\"}">Active</s:if>
									<s:else>Inactive</s:else>
								</td>
								<td align="center">
									<div style="color: #3d6e9f; cursor: pointer;" onclick="editTeacherAssignment('${ti_id}', '${ta_id}', '${sub_id}', '${cl_id}', '${ta_status}')">
										Edit
									</div>
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