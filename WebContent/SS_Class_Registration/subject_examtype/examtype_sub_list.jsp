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
                $('#examtypelist').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->


</head>
<body>
	<s:set var="subcl_id" value="subcl_id"/>
	<s:set var="sub_name" value="sub_name"/>
	<s:set var="cl_id" value="cl_id"/>
	<s:set var="sub_id" value="sub_id"/>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="height: 40px">
				Exam type list for - <s:property value="sub_name"/> subject
			</td>
		</tr>
		<tr>
			<td>
				<div class="menu_shadow" style="background-color: #f5f5f5; height: 35px; padding-top: 15px;  background-position: bottom;">
					<table cellpadding="0" cellspacing="0"><tr><td>
						<div style="color: #3d6e9f; cursor: pointer;" onclick="addSubjectRel('${cl_id}', '${sub_name}')">
						<img alt="click" src="images/next.gif" width="8px"> Back to Subject list
						</div>	
					</td><td width="50px;">&nbsp;</td><td>						
						<div style="color: #3d6e9f; cursor: pointer;" onclick="addSubExamTypeRel('${subcl_id}', '${sub_name}', '${cl_id}', '${sub_id}')">
						<img alt="click" src="images/next.gif" width="8px"> Add Exam type
						</div>						
					</td></tr></table>
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
				<div class="content_background" style="min-height: 380px">
				<table id="examtypelist" width="100%" cellpadding="0" cellspacing="1">
					<thead style="background-color: #f5f5f5; height: 30px; text-align: center;">
						<tr>
							<td>No</td>
							<td>Exam Type</td>
							<td>Total Mark</td>
							<td>Pass Mark</td>
							<td>Status</td>
							<td>&nbsp;</td>
						</tr>
					</thead>
					<s:iterator status="stat" value="examtype_subrel_rslt">
						<tr height="30px">
							<td>
								<s:property value="#stat.index + 1"/>
								<s:set var="exsub_id" value="exsub_id"/>
							</td>
							<td>
								<s:property value="examtype_subrel_rslt[#stat.index].et_name"/>
							</td>
							<td>
								<s:property value="examtype_subrel_rslt[#stat.index].total_mark"/>
								<s:set var="total_mark" value="examtype_subrel_rslt[#stat.index].total_mark"/>
							</td>
							<td>
								<s:property value="examtype_subrel_rslt[#stat.index].pass_mark"/>
								<s:set var="pass_mark" value="examtype_subrel_rslt[#stat.index].pass_mark"/>
							</td>
							<td>								
								<s:set var="rel_status" value="examtype_subrel_rslt[#stat.index].rel_status"/>
								<s:if test="%{#rel_status == \"A\"}">Active</s:if>
								<s:if test="%{#rel_status == \"U\"}">Inactive</s:if>
							</td>
							<td align="center" style="color: #3d6e9f;">
								<div style="cursor: pointer;" onclick="editClSubExamRel('${exsub_id}', '${total_mark}', '${pass_mark}', '${rel_status}', '${cl_id}', '${sub_id}', '${sub_name}')">Edit</div>
							</td>
						</tr>
					</s:iterator>
				</table>
				</div>
			</td>
		</tr>
	</table>

</body>
</html>