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
                $('#stud_contact_info_list').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>
	
	<table id="stud_contact_info_list" width="100%" cellpadding="0" cellspacing="2">
		<thead>
			<tr style="background-color: #f5f5f5; font-size: 14px; height: 30px">
				<th> # </th>
				<th> Student Name </th>
				<th> Mother Name </th>
				<th> Sex </th>
				<th> DOB </th>
				<th> POB </th>
				<th> Nationality </th>
				<th> Contact Name </th>
				<th> Relationship </th>
				<th> Phone </th>		
			</tr>
		</thead>
		<s:iterator status="stat" value="stud_rslt">
			<tr height="35px">
				<td align="center">
				
					<s:property value="%{#stat.index + 1}"/>
					<s:set var="siid" value="si_id"/>
					<s:set var="sex" value="sex"/>
					<s:set var="fullname" value="fullName"/>
									
				</td>
				<td>																
					<div style="color: #3d6e9f; cursor: pointer">
					 	<s:property value="fullName"/>
					</div>												
				</td>
				<td>
					<s:property value="mother_name"/>
				</td>
				<td>					
					<s:if test="%{#sex == \"F\"}">Female</s:if>
					<s:if test="%{#sex == \"M\"}">Male</s:if>
				</td>
				<td>
					<s:property value="dob"/>
				</td>
				<td>
					<s:property value="pob"/>
				</td>								
				<td>
					<s:property value="nationality"/>
				</td>				
				<td>
					<s:property value="emergencyContactBean.contact_name"/>
				</td>
				<td>
					<s:property value="emergencyContactBean.relationship"/>
				</td>								
				<td>
					<s:property value="emergencyContactBean.mob_no"/>
				</td>							
			</tr>
		</s:iterator>
	</table>

</body>
</html>