<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script src="../js/sms_class_registration.js" type="text/javascript"></script>

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
                $('#cdetailList').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body style="margin: 0">

	<div id="cdtlList">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="height: 35px">
				<div class="menu_shadow" style="height: 35px; padding-top: 15px; background-position: bottom;">
					<div style="color: #3d6e9f; cursor: pointer;" onclick="addCDetail()"><img alt="click" src="images/next.gif" width="8px"> Add Class Detail</div>
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
				<div class="content_background" style="min-height: 440px">
					<table id="cdetailList" width="100%" cellpadding="0" cellspacing="1">
						<thead style="background-color: #f5f5f5; text-align: center;">
							<tr style="height: 30px;">
								<th>No</th>
								<th>Detail Name</th>
								<th>Status</th>
								<th>&nbsp;</th>
							</tr>
						</thead>
						<s:iterator status="stat" value="cd_rslt">
							<tr height="30px">
								<td align="center">
									<s:property value="#stat.index + 1"/>
									<s:set var="cd_id" value="cd_id"/>
								</td>
								<td>
									<s:property value="cd_rslt[#stat.index].cd_name"/>
									<s:set var="cd_name" value="cd_name"/>
								</td>
								<td>
									<s:property value="cd_rslt[#stat.index].cd_status"/>
									<s:set var="cd_status" value="cd_status"/>
								</td>
								<td align="center">
									<div style="color: #3d6e9f; cursor: pointer;" onclick="editCDetail('${cd_id}', '${cd_name}', '${cd_status}')">Edit</div>
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