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
                $('#teachdisact_list').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

<script src="js/sms_teacher_registration.js" type="text/javascript"></script>

</head>
<body>
	<s:set var="ti_id" value="ti_id"/>
	<table cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td style="background-color: #f5f5f5; height: 50px">
				<div onclick="addDisAct('${ti_id}')" style="cursor: pointer;"><img src="images/next.gif" width="8px"/> Add Disciplinary Action</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
				<div id="container">
				<table id="teachdisact_list" cellpadding="0" cellspacing="1" width="100%">
					<thead style="background-color: #f5f5f5">
						<tr style="height: 30px">
							<th>No</th>
							<th>DisAct Type</th>
							<th>DisAct Reason</th>
							<th>DisAct Date</th>
							<th>DisAct Status</th>
							<th></th>
						</tr>
					</thead>
					<s:iterator status="stat" value="disact_rslt">
						<tr style="height: 30px">
							<td>
								<s:property value="#stat.index + 1"/>
								<s:set var="tda_id" value="disact_rslt[#stat.index].tda_id"/>
							</td>
							<td>
								<s:property value="disact_rslt[#stat.index].tda_type"/>
								<s:set var="tda_type" value="disact_rslt[#stat.index].tda_type"/>
							</td>
							<td>
								<s:property value="disact_rslt[#stat.index].tda_reason"/>
								<s:set var="tda_reason" value="disact_rslt[#stat.index].tda_reason"/>
							</td>
							<td>
								<s:property value="disact_rslt[#stat.index].tda_date"/>
								<s:set var="tda_date" value="disact_rslt[#stat.index].tda_date"/>
							</td>
							<td>							
								<s:set var="tda_status" value="disact_rslt[#stat.index].tda_status"/>
								<s:if test="%{#tda_status == \"A\"}">Active</s:if>
								<s:if test="%{#tda_status == \"I\"}">Inactive</s:if>
							</td>
							<td align="center" style="color: #3d6e9f">
								<div style="cursor: pointer" onclick="editDisact('${ti_id}', '${tda_id}', '${tda_type}', '${tda_reason}', '${tda_date}', '${tda_status}')">Edit</div>
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