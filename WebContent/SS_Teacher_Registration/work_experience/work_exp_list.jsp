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
                $('#teach_eduback').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

<script src="js/sms_teacher_registration.js" type="text/javascript"></script>

</head>
<body>

<div id="teachFroms">
	<s:set var="ti_id" value="ti_id"/>
	<div style="background-color: #f5f5f5; height: 35px; padding-top: 15px;">
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td align="left">
					<div onclick="addNewTeacherWorkExp('${ti_id}')" style="color: #3d6e9f; cursor: pointer"> <img src="images/next.gif" width="8px"/> Add New Teacher Work Experience</div>
				</td>
				<td width="120px">
					&nbsp;
				</td>
				<td align="left">
					&nbsp;
				</td>
			</tr>
		</table>
	</div>
	
	<br/>
	
	<table id="teach_eduback" width="100%" cellpadding="0" cellspacing="1">
		<thead>
			<tr style="background-color: #f5f5f5; height: 30px; font-size:14px; color: #000000" align="center">
				<th>No</th>
				<th>Name of Institute</th>
				<th>Job Title</th>
				<th>Date From</th>
				<th>Date To</th>
				<th>Status</th>
			</tr>
		</thead>
		<s:iterator status="stat" value="workexp_list">
			<tr>
				<td align="center">
					<s:property value="%{#stat.index + 1}" /> 
					<s:set var="twe_id" value="workexp_list[#stat.index].twe_id" />
					<s:set var="name_of_institute" value="workexp_list[#stat.index].name_of_institute" />
					<s:set var="job_title" value="workexp_list[#stat.index].job_title" />
					<s:set var="date_from" value="workexp_list[#stat.index].date_from" />
					<s:set var="date_to" value="workexp_list[#stat.index].date_to" />
					<s:set var="twe_status" value="workexp_list[#stat.index].twe_status" />
				</td>
				<td>
					<div onclick="editTeacherWorkExp('${twe_id}', '${ti_id}', '${name_of_institute}', '${job_title}', '${date_from}', '${date_to}', '${twe_status}')" style="cursor: pointer; color: #3d6e9f;">
						<s:property value="workexp_list[#stat.index].name_of_institute" />
					</div>
				</td>
				<td>
					<s:property value="workexp_list[#stat.index].job_title" />
				</td>
				<td>
					<s:property value="workexp_list[#stat.index].date_from" />
				</td>
				<td>
					<s:property value="workexp_list[#stat.index].date_to" />
				</td>
				<td>
					<s:if test="%{#twe_status == \"A\"}">Active</s:if>
					<s:else>Inactive</s:else>
				</td>
			</tr>
		</s:iterator>
	</table>
	
</div>

</body>
</html>