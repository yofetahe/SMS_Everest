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
                $('#classList12').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body style="margin: 0">

	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="height: 35px">
				<div class="menu_shadow" style="height: 35px; padding-top: 15px; background-position: bottom;">
				<div style="color: #3d6e9f; cursor: pointer" onclick="addClassFrm()"> <img alt="click" src="images/next.gif" width="8px"> Add Class</div>
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
			
				<div class="content_background" style="min-height: 440px">		
					<table id="classList12" width="100%" cellpadding="0" cellspacing="1">
						<thead style="background-color: #f5f5f5; text-align: center;">
							<tr style="height: 30px;">
								<th>Class #</th>
								<th>Class</th>
								<th>Status</th>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
							</tr>
						</thead>
						<s:iterator status="stat" value="class_rslt">
							<tr style="height: 30px">
								<td align="center">
									<s:property value="class_rslt[#stat.index].class_number"/>
									<s:set var="class_number" value="class_rslt[#stat.index].class_number"/>
									<s:set var="cl_id" value="class_rslt[#stat.index].cl_id"/>
								</td>
								<td>
									<s:property value="class_rslt[#stat.index].class_name"/>
									<s:set var="class_name" value="class_rslt[#stat.index].class_name"/>
								</td>
								<td>
									<s:property value="class_rslt[#stat.index].class_status"/>
									<s:set var="class_status" value="class_rslt[#stat.index].class_status"/>
								</td>
								<td align="center">
									<div style="color: #3d6e9f; cursor: pointer;" onclick="relateClassDetail('${cl_id}', '${class_name}')">Add Detail</div>
								</td>
								<td align="center">
									<div style="color: #3d6e9f; cursor: pointer;" onclick="addSubjectRel('${cl_id}', '${class_name}')">Add Subject</div>
								</td>
								<td align="center">
									<div style="color: #3d6e9f; cursor: pointer;" onclick="editClass('${cl_id}', '${class_name}', '${class_number}', '${class_status}')">Edit</div>
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