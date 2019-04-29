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
                    //"sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>
	<s:set var="class_id" value="class_id"/>
	<s:set var="cd_id" value="cd_id"/>
	<s:property value="class_number"/>
	
<!-- 	<div id="gradeList" style="padding-bottom: 10px; border-bottom-color: #f5f5f5; border-bottom-style: solid; border-bottom-width: thin;"> -->
<!-- 		<table width="100%" cellpadding="0" cellspacing="0"><tr> -->
<%-- 			<s:iterator status="stat" value="grdList_rslt"> --%>
<%-- 				<s:set var="cl_id" value="grdList_rslt[#stat.index].cl_id"/> --%>
<%-- 				<td align="center"> <input type="checkbox" onclick="selectClassForTrascript('${cl_id}')"> <s:property value="grdList_rslt[#stat.index].class_name"/> </td><td>&nbsp;</td> --%>
<%-- 			</s:iterator> --%>
<!-- 		</tr></table>		 -->
<!-- 	</div> -->
	
	<table cellpadding="0" cellspacing="0" width="100%"><tr><td width="30%" valign="top">
		
		<div id="student_list_pergrade" style="padding-top: 10px;">
					
			<table id="stud_list" width="100%" cellpadding="0" cellspacing="2">
				<thead>
					<tr style="background-color: #f5f5f5; font-size: 14px; height: 30px">
						<th width="10%">No</th>
						<th>Student Name</th>
					</tr>
				</thead>
				<s:iterator status="stat" value="stud_rslt">
					<tr height="35px">
						<td align="center">
							<s:property value="%{#stat.index + 1}"/>
							<s:set var="siid" value="si_id"/>
						</td>
						<td>
							<div style="color: #3d6e9f; cursor: pointer" onclick="getStudentTranscript('${siid}')">
								<s:property value="stud_rslt[#stat.index].fname"/>&nbsp;<s:property value="mname"/>&nbsp;<s:property value="gname"/>
							</div>									
						</td>												
					</tr>
				</s:iterator>
			</table>
		</div>
	
	</td><td width="20px">&nbsp;</td><td valign="top">
	
		<div id="transcriptContent" style="width: 100%;"> </div>
		
	</td></tr></table>
</body>
</html>