<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                $('#examtypelist').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body style="margin: 0">

	<table width="100%" cellpadding="0" cellspacing="0">		
		<tr>
			<td style="height: 40px">
				<div class="menu_shadow" style="height: 35px; padding-top: 15px; background-position: bottom;">
				<div style="color: #3d6e9f; cursor: pointer;" onclick="addExamType()"> <img alt="click" src="images/next.gif" width="8px"> Add Exam type</div>
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
				<div class="content_background"  style="min-height: 434px">
				<table id="examtypelist" width="100%" cellpadding="0" cellspacing="1">
					<thead style="background-color: #f5f5f5; text-align: center;">
						<tr style="height: 30px;">
							<th>No</th>
							<th>Exam Title</th>
							<th>Exam Type</th>
							<th>Status</th>
							<th>&nbsp;</th>
						</tr>
					</thead>
					<s:iterator status="stat" value="examtype_rslt">
						<tr height="30px">
							<td align="center">
								<s:property value="#stat.index + 1"/>
								<s:set var="et_id" value="examtype_rslt[#stat.index].et_id"/>
							</td>
							<td>
								<s:property value="examtype_rslt[#stat.index].et_name"/>
								<s:set var="et_name" value="examtype_rslt[#stat.index].et_name"/>
							</td>
							<td>
								<s:set var="et_type" value="examtype_rslt[#stat.index].et_type"/>
								<c:choose>
								<c:when test="${et_type == \"NF\"}">Not-Final</c:when>
								<c:otherwise>Final</c:otherwise>
								</c:choose>
							</td>
							<td>								
								<s:set var="et_status" value="et_status"/>
								
								<c:choose>
									<c:when test="${et_status == \"A\"}">Active</c:when>
									<c:otherwise>Inactive</c:otherwise>
								</c:choose>
							</td>
							<td align="center" style="color: #3d6e9f;">
								<div style="cursor: pointer;" onclick="editExamType('${et_id}', '${et_name}', '${et_type}', '${et_status}')">Edit</div>
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