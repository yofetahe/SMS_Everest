<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
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
<div id="disact_container">
	<s:set var="siid" value="si_id"/>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="background-color: #f5f5f5; height: 50px; cursor: pointer; color: #3d6e9f">
				<div onclick="addNewDisAct('${siid}')" >
					<img alt="click" src="images/next.gif" width="8px"> Add New Disciplinary Action
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
								<th>Action Type</th>
								<th>Action Reason</th>
								<th>Action Date</th>
								<th>Status</th>
							</tr>
						</thead>
						<s:iterator status="stat" value="disact_rslt">
							<tr style="height: 30px">
								<td align="center">
									<s:property value="#stat.index + 1"/>
									<s:set var="sdaid" value="disact_rslt[#stat.index].sda_id"/>
									<s:set var="sdatype" value="disact_rslt[#stat.index].sda_type"/>
									<s:set var="sdareason" value="disact_rslt[#stat.index].sda_reason"/>
									<s:set var="sdadate" value="disact_rslt[#stat.index].sda_date"/>
									<s:set var="sdastatus" value="disact_rslt[#stat.index].sda_status"/>
								</td>
								<td style="cursor: pointer; color: #3d6e9f">
									<div onclick="disaction_edit('${sdaid}', '${sdatype}', '${sdareason}', '${sdadate}', '${sdastatus}', '${siid}')">
										<s:property value="disact_rslt[#stat.index].sda_type"/>
									</div>
								</td>
								<td>
									<s:property value="disact_rslt[#stat.index].sda_reason"/>
								</td>
								<td>
									<s:property value="disact_rslt[#stat.index].sda_date"/>
								</td>
								<td>
									<s:set var="disact" value="disact_rslt[#stat.index].sda_status"/>
									<s:if test="%{#disact == \"A\"}">Active</s:if>
									<s:if test="%{#disact == \"I\"}">Inactive</s:if>
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