<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script src="../js/sms_class_registration.js" type="text/javascript"></script>

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
                $('#grdList').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>


<div id="grdListCont">
	<s:set var="subcl_id" value="subcl_id"/>
	<s:set var="sub_name" value="sub_name"/>
	<s:set var="cl_id" value="cl_id"/>
	<s:set var="cl_name" value="cl_name"/>
	<div>
		Subject: <s:property value="sub_name"/>
	</div>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="height: 35px">
				<div class="menu_shadow" style="height: 35px; padding-top: 15px; background-position: bottom;">
					<table cellpadding="0" cellspacing="0"><tr><td>
						<div style="color: #3d6e9f; cursor: pointer" onclick="addGradeFrm('${subcl_id}', '${sub_name}')"> <img alt="click" src="images/next.gif" width="8px"> Add Grading Mark</div>
					</td><td style="width: 50px;">&nbsp;</td><td>
						<div style="color: #3d6e9f; cursor: pointer" onclick="backToSubjectList('${cl_id}', '${cl_name}')"> <img alt="click" src="images/next.gif" width="8px"> Back to subject list</div>
					</td></tr></table>
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
				
				<div class="content_background" style="min-height: 440px">
					
					<table id="grdList" cellpadding="0" cellspacing="1" width="100%">
						<thead style="background-color: #f5f5f5; text-align: center;">
							<tr style="height: 30px">
								<th>No</th>
								<th>Grade</th>
								<th>From</th>
								<th>To</th>
								<th>Status</th>
								<th>&nbsp;</th>
							</tr>
						</thead>
						<s:iterator status="stat" value="related_grading_list">
							<tr style="height: 30px">
								<td align="center"> 
									<s:property value="#stat.index + 1"/>
									<s:set var="escg_id" value="related_grading_list[#stat.index].escg_id"/> 
									<s:set var="eg_value" value="related_grading_list[#stat.index].eg_value"/>
									<s:set var="grade_from" value="related_grading_list[#stat.index].grade_from"/>
									<s:set var="grade_to" value="related_grading_list[#stat.index].grade_to"/>
									<s:set var="escg_status" value="related_grading_list[#stat.index].escg_status"/>
								</td>
								<td> <s:property value="related_grading_list[#stat.index].eg_value"/> </td>
								<td align="center"> <s:property value="related_grading_list[#stat.index].grade_from"/> </td>
								<td align="center"> <s:property value="related_grading_list[#stat.index].grade_to"/> </td>
								<td align="center"> 
									<s:if test="%{#escg_status == \"A\"}"> Active </s:if>
									<s:else>Inactive</s:else>
								</td>
								<td align="center"> 
									<div style="cursor: pointer; color: #3d6e9f;" onclick="updateGradeFrm('${subcl_id}', '${sub_name}', '${escg_id}', '${eg_value}', '${grade_from}', '${grade_to}', '${escg_status}')">
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