<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script src="../js/sms_admin.js" type="text/javascript"></script>

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
                $('#hrtAssign').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

<div id="assignpage">
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td style="width: 30%; padding-left: 5px; vertical-align: top;">
			
				<div id="saveEditForm" class="formboarder">	
					
					<div class="formtitle">New Assignment Form</div>
					
					<div id="eMsg" style="color: #ff0000; width: 100%; text-align: center;"></div>
					<div style="color: #ff0000; width: 100%; text-align: center;"><s:fielderror id="errMsg"/></div>
					<table align="center" style="padding-top: 20px; padding-bottom: 20px;">
						<s:select label="Teachers" id="tiid" list="teacherList" listKey="ti_id" listValue="tname" headerValue="---" headerKey="0" style="width: 200px; height: 30px;"/>
						<s:select label="Class" id="clid" list="classList" listKey="cl_id" listValue="class_name" headerValue="---" headerKey="0" onchange="filterClassDetail(this.value)" style="width: 200px; height: 30px;"/>
						<s:select label="Class Detail" id="cdid" list="#{'':''}" headerValue="---" headerKey="0" style="width: 200px; height: 30px;"/>
						
						<s:submit onclick="saveAssignment()" value="Assign" align="center"/>
					</table>
					
				</div>
			
			</td>
			<td style="width: 5%">&nbsp;</td>
			<td>
				
				<div>
					<table id="hrtAssign" width="100%" cellpadding="0" cellspacing="1">
						<thead style="background-color: #f5f5f5;">
							<tr style="height: 35px;">
								<th>No</th>
								<th>Teacher Name</th>
								<th>Grade</th>
								<th>Section</th>
								<th>Status</th>
								<th>&nbsp;</th>
							</tr>
						</thead>
						<s:iterator status="stat" value="assignTeacherList">
							<tr style="height: 40px;">
								<td align="center">
									<s:property value="%{#stat.index + 1}"/>
									<s:set var="thra_id" value="assignTeacherList[#stat.index].thra_id"/>
								</td>
								<td>
									<s:property value="assignTeacherList[#stat.index].tname"/>
								</td>
								<td>
									<s:property value="assignTeacherList[#stat.index].class_name"/>
								</td>								
								<td align="center">
									<s:property value="assignTeacherList[#stat.index].cd_name"/>
								</td> 
								<td>
									<s:set var="assignStatus" value="assignTeacherList[#stat.index].assign_status"/>
									<s:if test="%{#assignStatus == \"A\"}">Active</s:if>
									<s:else>Inactive</s:else>
								</td>
								<td align="center">
									<div style="color: #3d6e9f; cursor: pointer;" onclick="tassignEditForm('${thra_id}')">
										Edit
									</div>
								</td>
							</tr>
						</s:iterator>
					</table>
				</div>

			</td>
		</tr>
	</table>
</div>

</body>
</html>