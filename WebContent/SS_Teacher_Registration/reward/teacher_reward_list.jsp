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
                $('#teachreward_list').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

<script src="js/sms_teacher_registration.js" type="text/javascript"></script>

</head>
<body>
	<s:set var="tiid" value="ti_id"/>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="background-color: #f5f5f5; height: 50px">
				<div style="cursor: pointer" onclick="addNewReward('${tiid}')"> <img src="images/next.gif" width="8px"/> Add New Reward</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
				<div id="container">
					<table id="teachreward_list" width="100%" cellpadding="0" cellspacing="1">
						<thead style="background-color: #f5f5f5">
							<tr height="30px">
								<th>No</th>
								<th>Reward Type</th>
								<th>Reward Reason</th>
								<th>Date</th>
								<th>Status</th>
								<th></th>
							</tr>
						</thead>
						<s:iterator status="stat" value="reward_rslt">
							<tr height="30px">
								<td>
									<s:property value="#stat.index + 1"/>
									<s:set var="tr_id" value="reward_rslt[#stat.index].tr_id"/>
									<s:set var="tr_type" value="reward_rslt[#stat.index].tr_type"/>
									<s:set var="tr_reason" value="reward_rslt[#stat.index].tr_reason"/>
									<s:set var="tr_date" value="reward_rslt[#stat.index].tr_date"/>
									<s:set var="tr_status" value="reward_rslt[#stat.index].tr_status"/>
								</td>
								<td>
									<s:property value="reward_rslt[#stat.index].tr_type"/>
								</td>
								<td>
									<s:property value="reward_rslt[#stat.index].tr_reason"/>
								</td>
								<td>
									<s:property value="reward_rslt[#stat.index].tr_date"/>
								</td>
								<td>
									<s:set var="trstatus" value="reward_rslt[#stat.index].tr_status"/>
									<s:if test="%{#trstatus == \"A\"}">Active</s:if>
									<s:if test="%{#trstatus == \"I\"}">Inactive</s:if>
								</td>
								<td style="color: #3d6e9f" align="center">
									<div style="cursor: pointer;" onclick="editReward('${tr_id}', '${tr_type}', '${tr_reason}', '${tr_date}', '${tr_status}', '${tiid}')">Edit</div>
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