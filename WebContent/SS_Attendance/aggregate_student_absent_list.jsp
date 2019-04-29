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
             @import "datatable/datatable_2/media/css/jquery.dataTables.css";
             @import "media/css/TableTools.css";
        </style>
        <script type="text/javascript" charset="utf-8" src="datatable/datatable_2/media/js/jquery.js"></script>
        <script type="text/javascript" charset="utf-8" src="datatable/datatable_2/media/js/jquery.dataTables.js"></script>
        <script type="text/javascript" charset="utf-8" src="media/js/ZeroClipboard.js"></script>
        <script type="text/javascript" charset="utf-8" src="media/js/TableTools.js"></script>
        <script type="text/javascript" charset="utf-8">
            $(document).ready( function () {
                $('#totalAbsent').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->
</head>
<body>
<div style="padding-top: 20px;">
	<table width="100%" id="totalAbsent" cellpadding="0" cellspacing="1" align="left">
		<thead style="background-color: #f5f5f5; height: 40px; text-align: center;">
			<tr>
				<td style="height: 30px;">No.</td>
				<td width="30%">Student Name</td>
				<td width="50%">Date List</td>
				<td width="15%">No of Days</td>
			</tr>
		</thead>
		<s:iterator status="stat" value="aggStudAbsent_list">
			<tr>
				<td align="center" style="height: 30px;">
					<s:property value="%{#stat.index + 1}"/>
				</td>
				<td>
					<s:property value="aggStudAbsent_list[#stat.index].firstName"/> <s:property value="aggStudAbsent_list[#stat.index].fatherName"/> <s:property value="aggStudAbsent_list[#stat.index].gfName"/>
				</td>
				<td>
					<s:property value="aggStudAbsent_list[#stat.index].dateList"/>
				</td>
				<td>
					<s:property value="aggStudAbsent_list[#stat.index].noofdays"/> day(s)
				</td>
			</tr>
		</s:iterator>
	</table>
</div>
</body>
</html>