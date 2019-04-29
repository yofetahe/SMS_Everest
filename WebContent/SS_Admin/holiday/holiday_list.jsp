<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
                $('#schoolEventList').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>
<div id="holidayInfo">
	<div style="background-color: #f5f5f5; color: #3d6e9f; height: 30px; width: 100%; padding-top: 10px; cursor: pointer;">
		<div onclick="addNewHoliday()" style="width: 200px"><img alt="click" src="images/next.gif" width="8px">&nbsp;Add New Holiday</div>
	</div>
	<div style="height: 10px"></div>
	
	<table id="schoolEventList" cellpadding="0" cellspacing="1" width="100%">
		<thead style="background-color: #f5f5f5; height: 30px; text-align: center;">
			<tr>
				<td width="5%">No</td>
				<td width="30%">Holiday Name</td>				
				<td width="15%">Date <br/>(Ethiopian Calendar)</td>
				<td width="15%">Date <br/>(Gregorian Calendar)</td>
				<td width="15%">Working Status</td>				
				<td width="10%">Status</td>
				<td width="10%">&nbsp;</td>
			</tr>
		</thead>
		<s:iterator status="stat" value="holidayList">
			<tr style="height: 30px">
				<td align="center">
					<s:property value="%{#stat.index + 1}"/>
				</td>
				<td>
					<s:property value="holidayList[#stat.index].h_name"/>
				</td>
				<td>
					<s:property value="holidayList[#stat.index].h_ethio_date"/>
				</td>
				<td>
					<s:property value="holidayList[#stat.index].h_greg_date"/>
				</td>
				<td>
					<s:property value="holidayList[#stat.index].work_status"/>
				</td>
				<td>
					<s:set var="status" value="holidayList[#stat.index].h_status"/>
					<s:if test="%{#status == \"A\"}">Active</s:if>
					<s:else>Inactive</s:else>
				</td>
				<td align="center" style="color: #3d6e9f;">
					<div style="cursor: pointer;" onclick="editHoliday('${stat.index}')">Edit</div>
				</td>
			</tr>
		</s:iterator>
	</table>
	
</div>
</body>
</html>