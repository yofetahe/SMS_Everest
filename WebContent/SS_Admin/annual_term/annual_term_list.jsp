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
                $('#annualTermList').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>
<div id="annualTermInfo">
	<div style="background-color: #f5f5f5; color: #3d6e9f; height: 30px; width: 100%; padding-top: 10px; cursor: pointer;">
		<div onclick="addNewAnnualTerm()" style="width: 200px"><img alt="click" src="images/next.gif" width="8px">&nbsp;Add New Annual Term</div>
	</div>
	<div style="height: 10px"></div>
	
	<table id="annualTermList" cellpadding="0" cellspacing="1" width="100%">
		<thead style="background-color: #f5f5f5; height: 30px; text-align: center;">
			<tr>
				<td>No</td>
				<td>Name</td>
				<td>Academic Year</td>
				<td>Status</td>
				<td>&nbsp;</td>
			</tr>
		</thead>
		<s:iterator status="stat" value="annualTermList">
			<tr style="height: 30px">
				<td align="center">
					<s:property value="%{#stat.index + 1}"/>
				</td>
				<td>
					<s:property value="annualTermList[#stat.index].at_name"/>
				</td>
				<td>
					<s:property value="annualTermList[#stat.index].academic_year"/>
				</td>
				<td>
					<s:set var="status" value="annualTermList[#stat.index].at_status"/>
					<s:if test="%{#status == \"A\"}">Active</s:if>
					<s:else>Inactive</s:else>
				</td>
				<td align="center" style="color: #3d6e9f;">
					<div style="cursor: pointer;" onclick="editAnnualTerm('${stat.index}')">Edit</div>
				</td>
			</tr>
		</s:iterator>
	</table>
	
</div>
</body>
</html>