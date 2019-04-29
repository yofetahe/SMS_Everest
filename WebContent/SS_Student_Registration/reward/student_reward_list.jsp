<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script src="js/jquery-1.6.1.js" type="text/javascript"></script>
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
                $('#disact_list').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

<div id="rewardlist_container">
	<s:set var="siid" value="si_id"/>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="background-color: #f5f5f5; height: 50px; color: #3d6e9f; cursor: pointer;">
				<div onclick="addNewReward('${siid}')">
					<img alt="click" src="images/next.gif" width="8px"> Add New Reward
				</div>
			</td>
		</tr>
		<tr>
			<td>
				&nbsp;
			</td>
		</tr>
		<tr>
			<td>
				<s:set var="successful_save" value="successful_save"/>
				<s:set var="successful_update" value="successful_update"/>
				<s:if test="%{#successful_save == \"true\"}"> <span style="color: #3d6e9f"> Successfully Saved. </span> </s:if>
				<s:if test="%{#successful_update == \"true\"}"> <span style="color: #3d6e9f"> Successfully Updated. </span> </s:if>			
				
					<table id="disact_list" width="100%" cellpadding="0" cellspacing="2">
						<thead>
							<tr style="background-color: #f5f5f5; font-size: 14px; height: 30px">
								<th>No</th>
								<th>Reward Type</th>
								<th>Reward Reason</th>
								<th>Reward Date</th>
								<th>Status</th>
							</tr>
						</thead>
						<s:iterator status="stat" value="rwd_rslt">
							<tr style="height: 30px">
								<td>
									<s:property value="#stat.index+1"/>
									<s:set var="srid" value="rwd_rslt[#stat.index].sr_id"/>
									<s:set var="srtype" value="rwd_rslt[#stat.index].sr_type"/>
									<s:set var="srreason" value="rwd_rslt[#stat.index].sr_reason"/>
									<s:set var="srdate" value="rwd_rslt[#stat.index].sr_date"/>
									<s:set var="srstatus" value="rwd_rslt[#stat.index].sr_status"/>
								</td>
								<td style="color: #3d6e9f">
									<div onclick="rewardEdit('${srid}', '${srtype}', '${srreason}', '${srdate}', '${srstatus}', '${siid}')" style="cursor: pointer">
										<s:property value="rwd_rslt[#stat.index].sr_type"/>
									</div>
								</td>
								<td>
									<s:property value="rwd_rslt[#stat.index].sr_reason"/>
								</td>
								<td>
									<s:property value="rwd_rslt[#stat.index].sr_date"/>
								</td>
								<td>
									<s:if test="%{#srstatus == \"A\"}">Active</s:if>
									<s:if test="%{#srstatus == \"I\"}">Inactive</s:if>
								</td>
							</tr>
						</s:iterator>
					</table>
			</td>
		</tr>
	</table>
</div>

</body>
</html>