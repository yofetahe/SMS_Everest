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
					<div onclick="addNewTeacherEduBackground('${ti_id}')" style="color: #3d6e9f; cursor: pointer"> <img src="images/next.gif" width="8px"/> Add New Teacher Education Background</div>
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
				<th>Field of Study</th>
				<th>Graduation Date</th>
				<th>Award</th>
				<th>Status</th>
			</tr>
		</thead>
		<s:iterator status="stat" value="edubackground_list">
			<tr>
				<td align="center">
					<s:property value="%{#stat.index + 1}" /> 
					<s:set var="teb_id" value="edubackground_list[#stat.index].teb_id" />
					<s:set var="name_of_institiute" value="edubackground_list[#stat.index].name_of_institiute" />
					<s:set var="field_of_study" value="edubackground_list[#stat.index].field_of_study" />
					<s:set var="graduation_date" value="edubackground_list[#stat.index].graduation_date" />
					<s:set var="award" value="edubackground_list[#stat.index].award" />
					<s:set var="teb_status" value="edubackground_list[#stat.index].teb_status" />
				</td>
				<td>
					<div onclick="editTeacherEduBackground('${teb_id}', '${ti_id}', '${name_of_institiute}', '${field_of_study}', '${graduation_date}', '${award}', '${teb_status}')" style="cursor: pointer; color: #3d6e9f;">
						<s:property value="edubackground_list[#stat.index].name_of_institiute" />
					</div>
				</td>
				<td>
					<s:property value="edubackground_list[#stat.index].field_of_study" />
				</td>
				<td>
					<s:property value="edubackground_list[#stat.index].graduation_date" />
				</td>
				<td>
					<s:property value="edubackground_list[#stat.index].award" />
				</td>
				<td>
					<s:if test="%{#teb_status == \"A\"}">Active</s:if>
					<s:else>Inactive</s:else>
				</td>
			</tr>
		</s:iterator>
	</table>
	
</div>

</body>
</html>