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
                $('#studforreg').dataTable( {
                    //"sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>
	<s:set var="clid" value="cl_id"/>
	
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" style="border-bottom-style: solid; border-bottom-width: thin; border-bottom-color: silver; background-color: #f5f5f5; height: 50px;">
				Student Registration for <s:property value="cl_name"/>  
			</td>
		</tr>
		<tr>
			<td colspan="3">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td>
			
			<div id="studentRegistrationContent">
			
				<table width="100%"><tr><td width="49%" valign="top">
					<!-- student list for registration -->
					
					<div id="container">
						<table width="100%" id="studforreg">
							<thead>
								<tr style="background-color: #f5f5f5; font-size: 14px; height: 30px">
									<th align="center">No</th>
									<th>Student Name</th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<s:iterator status="stat" value="studList">
								<tr style="height: 35px;">
									<td align="center">
										<s:property value="#stat.index + 1"/>
										<s:set var="stud_id" value="studList[#stat.index].si_id"/>
										<s:set var="stud_name" value="studList[#stat.index].stud_name"/>
									</td>
									<td>
										<s:property value="studList[#stat.index].stud_name"/>
									</td>
									<td>
										<s:property value="studList[#stat.index].regStudStatus"/>
									</td>
									<td align="center" style="color: #3d6e9f; cursor: pointer;">
	<%-- 									<div id="${stud_id}_add" onclick="selectStudent('${stud_id}', '${stud_name}', '${clid}')"> --%>
	<!-- 										Add -->
	<%-- 									</div> --%>
										<input type="checkbox"  onclick="selectStudentsForRegistration('${stud_id}', '${stud_name}', '${clid}')">
									</td>
								</tr>
							</s:iterator>
						</table>
						<div style="width: 100%; padding-top: 40px;" align="center">
							<button type="submit" class="btn btn-default" onclick="addSelectedStudentsForRegistration('${clid}')"> ADD SELECTED STUDENTS </button>
						</div>
					</div>
					
					<!-- student list for registration -->
				</td>
				<td>&nbsp;</td>
				<td width="48%" valign="top">
					<!-- student selected for registration -->
					
					<div id="selStudList">
					
					</div>
					
					<!-- student selected for registration -->
				</td></tr></table>
			
			</div>
			</td>
		</tr>
	</table>
	
</body>
</html>