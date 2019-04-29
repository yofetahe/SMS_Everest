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
                $('#cldetail').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body style="margin: 0">
	<s:set var="cl_id" value="cl_id"/>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="height: 50px">
				<div class="menu_shadow" style="height: 35px; padding-top: 15px;  background-position: bottom;">
				<div style="color: #3d6e9f; cursor: pointer;" onclick="addClassDetail('${cl_id}')"><img alt="click" src="images/next.gif" width="8px"> Add New Class Detail</div>
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
				<div class="content_background" style="min-height: 345px">
				<table cellpadding="0" cellspacing="1" id="cldetail" width="100%">
					<thead style="background-color: #f5f5f5; height: 30px; text-align: center;">
						<tr>
	 						<td>No</td>
							<td>Class Detail</td>
							<td>Class Capacity</td>
							<td>Status</td>
							<td>&nbsp;</td>
						</tr>
					</thead>
					<s:iterator status="stat" value="cld_rslt">
						<tr style="height: 30px">
							<td align="center">
								<s:property value="#stat.index + 1"/>
								<s:set var="clcd_id" value="clcd_id"/>
							</td>
							<td>
								<s:property value="cld_rslt[#stat.index].cd_name"/>								
							</td>
							<td>
								<s:property value="cld_rslt[#stat.index].cl_capacity"/>
								<s:set var="cl_capacity" value="cld_rslt[#stat.index].cl_capacity"/>
							</td>
							<td>
								<s:property value="cld_rslt[#stat.index].rel_status"/>
								<s:set var="cl_status" value="cld_rslt[#stat.index].rel_status"/>
							</td>
							<td align="center">
								<div style="cursor: pointer; color: #3d6e9f" onclick="editClassDetail('${cl_id}', '${clcd_id}', '${cl_capacity}', '${cl_status}')">Edit</div>
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