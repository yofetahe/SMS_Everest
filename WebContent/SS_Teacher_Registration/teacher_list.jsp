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
                $('#teach_list').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>
	<table width="100%" cellpadding="0" cellspacing="0" style="padding-top: 0px">
		<tr>
			<td style="height: 35px">
				<div class="menu_shadow" style=" height: 35px; padding-top: 15px;">
				<table cellpadding="0" cellspacing="0">
					<tr>
						<td align="left">
							<div onclick="addNewTeacher()" style="color: #3d6e9f; cursor: pointer"> <img src="images/next.gif" width="8px"/> Add New Teacher</div>
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
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
				<div class="content_background"  style="min-height: 434px">
					<table id="teach_list" width="100%" cellpadding="0" cellspacing="1">
						<thead>
							<tr style="background-color: #f5f5f5; height: 30px; font-size:14px; color: #000000" align="center">
								<th>No</th>
								<th width="30%">Name</th>
								<th width="10%">Sex</th>
								<th width="20%">Place of Birth</th>
								<th width="15%">Date of Birth</th>
								<th width="10%">ID No</th>
								<th width="10%">Status</th>
							</tr>
						</thead>
						<s:iterator status="stat" value="tchr_rslt">
							<tr height="30px">
								<td align="center">
									<s:property value="#stat.index + 1"/>
									<s:set var="ti_id" value="tchr_rslt[#stat.index].ti_id" />
									<s:set var="sex" value="tchr_rslt[#stat.index].sex"/>
									<s:set var="fname" value="tchr_rslt[#stat.index].fname"/>
									<s:set var="mname" value="tchr_rslt[#stat.index].mname"/>
									<s:set var="gname" value="tchr_rslt[#stat.index].gname"/>
									<s:set var="dob" value="tchr_rslt[#stat.index].dob"/>
									<s:set var="pob" value="tchr_rslt[#stat.index].pob"/>
									<s:set var="nat" value="tchr_rslt[#stat.index].nationality"/>
									<s:set var="id_no" value="tchr_rslt[#stat.index].id_no"/>
									<s:set var="ti_status" value="tchr_rslt[#stat.index].ti_status"/>
									<s:set var="photo_name" value="tchr_rslt[#stat.index].photo_name"/>
									<s:set var="photo_path" value="tchr_rslt[#stat.index].photo_path"/>
								</td>
								<td style="color: #3d6e9f">
									<div onclick="editTeacherInfo('${ti_id}', '${fname}','${mname}', '${gname}', '${sex}', '${dob}', '${pob}', '${nat}', '${id_no}', '${ti_status}', '${photo_name}', '${photo_path}')" style="cursor: pointer">
										<s:property value="tchr_rslt[#stat.index].fname"/> <s:property value="tchr_rslt[#stat.index].mname"/> <s:property value="tchr_rslt[#stat.index].gname"/>
									</div> 
								</td>
								<td>									
									<s:if test="%{#sex == \"M\"}">Male</s:if>
									<s:if test="%{#sex == \"F\"}">Female</s:if>
								</td>
								<td>
									<s:property value="tchr_rslt[#stat.index].pob"/> 
								</td>
								<td>
									<s:property value="tchr_rslt[#stat.index].dob"/> 
								</td>
								<td>
									<s:property value="tchr_rslt[#stat.index].id_no"/> 
								</td>
								<td>
									<s:if test="%{#ti_status == \"A\"}">Active</s:if>
									<s:if test="%{#ti_status == \"I\"}">Inactive</s:if> 
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