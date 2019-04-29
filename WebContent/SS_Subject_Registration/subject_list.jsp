<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
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
                $('#subList').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body style="margin-top: 0">

	<table cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td style="height: 50px">
				<div class="menu_shadow" style="height: 35px; padding-top: 15px; background-position: bottom;">
				<div style="color: #3d6e9f; cursor: pointer;" onclick="addSubject()"> 
					<img alt="click" src="images/next.gif" width="8px"> Add Subject
				</div>
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
			
			<!-- subject list -->
				<div class="content_background"  style="min-height: 434px">
					<table id="subList" width="100%" cellpadding="0" cellspacing="1">
						<thead style="background-color: #f5f5f5; text-align: center;">
							<tr style="height: 30px;">
								<th>No</th>
								<th>Subject</th>
								<th>Status</th>
								<th>&nbsp;</th>
							</tr>
						</thead>
						<s:iterator status="stat" value="subject_rslt">				
							<tr style="height: 30px">
								<td align="center">
									<s:property value="#stat.index + 1"/>
									<s:set var="sub_id" value="subject_rslt[#stat.index].sub_id"/>
								</td>
								<td>
									<s:property value="subject_rslt[#stat.index].sub_name"/>
									<s:set var="sub_name" value="subject_rslt[#stat.index].sub_name"/>
								</td>
								<td>
									<s:set var="sub_status" value="subject_rslt[#stat.index].sub_status"/>
									<s:if test="%{#sub_status == \"A\"}">Active</s:if>
									<s:else>Inactive</s:else>
								</td>
								<td align="center">
									<div style="color: #3d6e9f; cursor: pointer;" onclick="editSubject('${sub_id}', '${sub_name}', '${sub_status}')">Edit</div>
								</td>
							</tr>
						</s:iterator>
					</table>
				</div>
			<!-- subject list -->
						
			</td>
		</tr>
	</table>
	
				
			
</body>
</html>