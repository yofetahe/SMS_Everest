<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
                $('#periodPerWeek').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

	<table id="periodPerWeek" cellpadding="0" cellspacing="1" width="100%">
		<thead style="background-color: #f5f5f5; text-align: center;">
			<tr style="height: 30px;">
				<th>No.</th>
				<th>Class Name</th>
				<th>Subject Name</th>
				<th>Period Per Week</th>
				<th>Academic Year</th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		<s:iterator status="stat" value="assignPeriodList">
			<tr style="height: 30px">
				<td align="center"> <s:property value="%{#stat.index + 1}"/> </td>
				<td> <s:property value="assignPeriodList[#stat.index].croomBean.class_name"/> </td>
				<td> <s:property value="assignPeriodList[#stat.index].subjectBean.sub_name"/> </td>
				<td> <s:property value="assignPeriodList[#stat.index].period_per_week"/> </td>
				<td> <s:property value="assignPeriodList[#stat.index].academic_year"/> </td>
				<td align="center">
					<s:set var="csp_id" value="assignPeriodList[#stat.index].csp_id"/> 
					<div style="color: #3d6e9f; cursor: pointer;" onclick="editAllotedNumOfPeriod('${csp_id}')"> Edit </div> 
				</td>
			</tr>
		</s:iterator>
	</table>

</body>
</html>