<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script src="../js/sms_cocurriculum_activity.js" type="text/javascript"></script>

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
<div id="clubActContTemp">
	<table id="actlist" width="100%" cellpadding="0" cellspacing="1">
		<thead style="background-color: #f5f5f5; height: 30px; text-align: center;">
			<tr>
				<td style="height: 30px;" width="5%">No</td>
				<td width="25%">Activity Title</td>
				<td width="35%">Activity Description</td>
				<td >&nbsp;</td>
				<td width="5%">&nbsp;</td>				
			</tr>
		</thead>
		<s:iterator status="stat" value="activityList">
			<tr style="height: 30px" valign="top">
				<td align="center">
					<s:property value="%{#stat.index + 1}"/>
					<s:set var="ca_id" value="activityList[#stat.index].ca_id"/>
				</td>
				<td>
					<s:property value="activityList[#stat.index].ca_activity"/>
				</td>
				<td>
					<s:property value="activityList[#stat.index].ca_activity_desc"/>
				</td>
				<td>
					<div id="comEditForm_%{#ca_id}">
						<s:set var="clbheadcom" value="activityList[#stat.index].clubhead_comment"/>
						<div id="errMsg_%{#ca_id}" style="color: #ff0000; width: auto; text-align: center;"></div>
						<s:set var="com_length" value="activityList[#stat.index].com_length"/>
						<s:if test="%{#com_length == \"1\"}">
							<table cellpadding="0" cellspacing="0">
								<s:textarea id="clbhead_com_%{#ca_id}" cols="30" rows="5"></s:textarea>
							</table>
						</s:if>
						<s:else>
							<s:property value="activityList[#stat.index].clubhead_comment"/>
						</s:else>
					</div>
				</td>				
				<td style="text-align: center; color: #3d6e9f;">
					<s:set var="editStatus" value="activityList[#stat.index].editStatus"/>
					<s:if test="%{#com_length == \"1\"}">
						<s:if test="%{#editStatus == true}">
							<div style="cursor: pointer" onclick="saveClubHeadComment('${ca_id}')">
								Save
							</div>
						</s:if>
						<s:else>
							<div style="color: #f5f5f5">
								Save
							</div>
						</s:else>
					</s:if>
					<s:else>
						<s:if test="%{#editStatus == true}">
							<div id="editBtn_%{#ca_id}">
								<div style="cursor: pointer" onclick="editFormClubHeadComment('${ca_id}', '${clbheadcom}')">
									Edit
								</div>
							</div>
						</s:if>
						<s:else>
							<div style="color: #f5f5f5">
								Edit
							</div>
						</s:else>
					</s:else>
				</td>
			</tr>
		</s:iterator>
	</table>
</div>
</body>
</html>