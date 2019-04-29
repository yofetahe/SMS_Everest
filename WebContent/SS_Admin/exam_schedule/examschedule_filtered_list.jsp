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
                $('#schoolEventList').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>
<s:set var="cl_id" value="cl_id"/>
<table id="schoolEventList" cellpadding="0" cellspacing="1" width="100%">
		<thead style="background-color: #f5f5f5; height: 50px; text-align: center;">
			<tr>
				<td width="5%">No</td>
				<td width="12%">Grade</td>
				<td width="12%">Subject</td>
				<td width="12%">Exam Type</td>				
				<td width="12%">Date <br/>(Ethiopian Calendar)</td>
				<td width="12%">Date <br/>(Gregorian Calendar)</td>	
				<td width="12%">Exam Hour</td>
				<td>Term</td>			
				<td width="7%">Status</td>
				<td width="7%">&nbsp;</td>
			</tr>
		</thead>
		<s:iterator status="stat" value="exSchList">
			<tr style="height: 30px">
				<td align="center">
					<s:property value="%{#stat.index + 1}"/>
				</td>
				<td>
					<s:property value="exSchList[#stat.index].cl_name"/>
				</td>
				<td>
					<s:property value="exSchList[#stat.index].sub_name"/>
				</td>
				<td>
					<s:property value="exSchList[#stat.index].et_name"/>
				</td>
				<td>
					<s:property value="exSchList[#stat.index].es_ethio_date"/>
				</td>
				<td>
					<s:property value="exSchList[#stat.index].es_greg_date"/>
				</td>
				<td align="center">
					<s:property value="exSchList[#stat.index].time_from"/> - <s:property value="exSchList[#stat.index].time_to"/>
				</td>
				<td align="center">
					<s:property value="exSchList[#stat.index].at_name"/>
				</td>
				<td>
					<s:set var="status" value="exSchList[#stat.index].es_status"/>
					<s:if test="%{#status == \"A\"}">Active</s:if>
					<s:else>Inactive</s:else>
				</td>
				<td align="center" style="color: #3d6e9f;">
					<div style="cursor: pointer;" onclick="editExamSchedule('${stat.index}', '${cl_id}')">Edit</div>
				</td>
			</tr>
		</s:iterator>
	</table>
	<div style="height: 30px;">&nbsp;</div>
</body>
</html>