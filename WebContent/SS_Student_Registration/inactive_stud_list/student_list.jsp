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
                $('#stud_list').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>
	
	<div id="can_student_list">
		
		<table id="stud_list" width="100%" cellpadding="0" cellspacing="2">
			<thead>
				<tr style="background-color: #f5f5f5; font-size: 14px; height: 30px">
					<th width="5%">No</th>
					<th width="30%">Student Name</th>
					<th width="8%">Sex</th>
					<th width="12%">Date of Birth</th>
					<th width="18%">Place of Birth</th>
					<th width="10%">Status</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<s:iterator status="stat" value="stud_rslt">
				<tr height="35px">
					<td align="center">
						<s:property value="%{#stat.index + 1}"/>
						<s:set var="siid" value="si_id"/>
						<s:set var="idno" value="id_no"/>
						<s:set var="fname" value="fname"/><s:set var="mname" value="mname"/><s:set var="gname" value="gname"/>
						<s:set var="sex" value="sex"/>
						<s:set var="mothername" value="mother_name"/>
						<s:set var="dob" value="dob"/>
						<s:set var="pob" value="pob"/>
						<s:set var="nationality" value="nationality"/>
						<s:set var="sistatus" value="si_status"/>
						<s:set var="photo_path" value="stud_rslt[#stat.index].photo_path"/>
						<s:set var="photo_name" value="stud_rslt[#stat.index].photo_name"/>
					</td>
					<td>
						<!-- onclick="student_edit('${idno}', '${siid}', '${fname}', '${mname}', '${gname}', '${sex}', '${dob}', '${pob}', '${nationality}', '${sistatus}', '${mothername}', '${photo_path}', '${photo_name}')" -->
						<div style="color: #3d6e9f; cursor: pointer">
							<s:property value="stud_rslt[#stat.index].fname"/> &nbsp; <s:property value="mname"/> &nbsp; <s:property value="gname"/>
						</div>									
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
						<s:set var="sistatus" value="si_status"/>
						<s:if test="%{#sistatus == \"A\"}">Active</s:if>
						<s:if test="%{#sistatus == \"I\"}">Inactive</s:if>
					</td>
					<td align="center">
						<div style="color: #3d6e9f; cursor: pointer" onclick="activateStudentsStatus('${siid}')"> Activate </div>								
					</td>						
				</tr>
			</s:iterator>
		</table>
	</div>
</body>
</html>