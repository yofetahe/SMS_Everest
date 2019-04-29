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
                $('#classSubList').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>
	<s:set var="cl_id" value="cl_id"/>
	<s:set var="cl_name" value="class_name"/>
	<table width="100%" cellpadding="0" cellspacing="0">		
		<tr>
			<td style="height: 50px">
				<div class="menu_shadow" style="height: 35px; padding-top: 15px; background-position: bottom;">
				<div style="color: #3d6e9f; cursor: pointer;" onclick="addClassSubRel('${cl_id}', '${cl_name}')"><img alt="click" src="images/next.gif" width="8px"> Add Subject</div>
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
				<div class="content_background" style="min-height: 380px">				
				<table id="classSubList" width="100%" cellpadding="0" cellspacing="1">
					<thead style="background-color: #f5f5f5; text-align: center;">
						<tr style="height: 30px;">
							<th>No</th>
							<th>Subject</th>
							<th>Status</th>
							<th>&nbsp;</th>
							<th>&nbsp;</th>
							<th>&nbsp;</th>
						</tr>
					</thead>
					<s:iterator status="stat" value="classsub_rslt">
						<tr height="30px">
							<td align="center">
								<s:property value="#stat.index + 1"/>
								<s:set var="subcl_id" value="subcl_id"/>
							</td>
							<td>
								<s:property value="classsub_rslt[#stat.index].sub_name"/>
								<s:set var="sub_name" value="classsub_rslt[#stat.index].sub_name"/>
								<s:set var="sub_id" value="classsub_rslt[#stat.index].sub_id"/>
								<s:set var="add_to_total" value="classsub_rslt[#stat.index].add_to_total"/>
								<s:set var="con_to_grade" value="classsub_rslt[#stat.index].con_to_grade"/>
							</td>
							<td>
								<s:set var="rel_status" value="classsub_rslt[#stat.index].rel_status"/>
								<s:if test="%{#rel_status == \"A\"}">Active</s:if>
								<s:if test="%{#rel_status == \"U\"}">Inactive</s:if>
							</td>
							<td align="center">
								<div style="cursor: pointer; color: #3d6e9f" onclick="editClSubRel('${subcl_id}', '${rel_status}', '${cl_id}', '${cl_name}', '${sub_name}', '%{add_to_total}', '%{con_to_grade}')">Edit</div>
							</td>
							<td align="center">
								<div style="cursor: pointer; color: #3d6e9f" onclick="ExamTypeList('${subcl_id}', '${sub_name}', '${cl_id}', '${sub_id}')">Exam Type</div>
							</td>
							<td align="center" width="80px;">
								<s:if test="%{#con_to_grade == \"YES\"}">
								<div style="cursor: pointer; color: #3d6e9f" onclick="GradingList('${subcl_id}', '${sub_name}', '${cl_id}', '${sub_id}', '${cl_name}')">Grading</div>
								</s:if>
							</td>
						</tr>
					</s:iterator>
				</table>
				</div>			
			</td>
		</tr>
	</table>

</body>
</html>