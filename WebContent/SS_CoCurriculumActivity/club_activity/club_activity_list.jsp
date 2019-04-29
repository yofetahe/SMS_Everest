<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head/>
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
                $('#actlist').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

	<table id="actlist" width="100%" cellpadding="0" cellspacing="1">
		<thead style="background-color: #f5f5f5; height: 30px; text-align: center;">
			<tr>
				<td style="height: 30px;" width="5%">No</td>
				<td width="25%">Activity Title</td>
				<td width="35%">Activity Description</td>
				<td width="10%">Date from</td>
				<td width="10%">Date to</td>
				<td width="10%">Activity Status</td>
				<td>&nbsp;</td>				
			</tr>
		</thead>
		<s:iterator status="stat" value="activityList">
			<tr style="height: 30px" valign="top">
				<td align="center">
					<s:property value="%{#stat.index + 1}"/>
					<s:set var="clb_id" value="activityList[#stat.index].clb_id"/>
					<s:set var="ca_id" value="activityList[#stat.index].ca_id"/>
				</td>
				<td>
					<s:property value="activityList[#stat.index].ca_activity"/>
				</td>
				<td>
					<s:property value="activityList[#stat.index].ca_activity_desc"/>
				</td>
				<td>
					<s:property value="activityList[#stat.index].ca_datefrom"/>
				</td>
				<td>
					<s:property value="activityList[#stat.index].ca_dateto"/>
				</td>
				<td>
					<s:set var="ca_status" value="activityList[#stat.index].ca_status"/>
					<s:if test="%{#ca_status == \"A\"}">Active</s:if>
					<s:else>Inactive</s:else>
					<s:set var="editStatus" value="activityList[#stat.index].editStatus"/>
				</td>				
				<td style="text-align: center; color: #3d6e9f;">
					<s:if test="%{#editStatus == true}">
						<div style="cursor: pointer" onclick="editActivityContent('${ca_id}', '${clb_id}')">
							Edit
						</div>
					</s:if>
					<s:else>
						<div style="color: #f5f5f5">
							Edit
						</div>
					</s:else>
				</td>
			</tr>
		</s:iterator>
	</table>
	
</body>
</html>