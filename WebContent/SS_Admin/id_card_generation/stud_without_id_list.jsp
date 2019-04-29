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
                $('#stud_with_out_id_rslt').dataTable( {
                    //"sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

	<s:set var="cl_id" value="class_bean.cl_id"/>
	<s:set var="cd_id" value="cdetail_bean.cd_id"/>

	<div style="width: 100%; height: 30px; vertical-align: middle; padding-top: 10px; background-color: #f5f5f5; text-align: center; font-weight: bold;">
		Student without ID Card
	</div>
	<br/>
	<div style="color: GREEN; text-align: center;">
		<s:actionmessage/>
	</div>
	<div id="stud_with_out_id">
			
		<table id="stud_with_out_id_rslt" width="100%" cellpadding="0" cellspacing="2">
			<thead>
				<tr style="background-color: #f5f5f5; font-size: 14px; height: 30px">
					<th width="10%">No</th>
					<th>Student Name</th>
					<th width="15%">&nbsp;</th>
				</tr>
			</thead>
			<s:iterator status="stat" value="stud_with_out_id_rslt">
				<tr height="35px">
					<td align="center">
						<s:property value="%{#stat.index + 1}"/>
						<s:set var="siid" value="si_id"/>						
					</td>
					<td>
						<div style="color: #3d6e9f; cursor: pointer">
							<s:property value="stud_with_out_id_rslt[#stat.index].fname"/> &nbsp; <s:property value="mname"/> &nbsp; <s:property value="gname"/>
						</div>									
					</td>
					<td align="left">
						<div>
							<s:property value="stud_with_out_id_rslt[#stat.index].id_no"/>
						</div>
					</td>			
				</tr>
			</s:iterator>
		</table>
	</div>
	<br/><br/>
	<div onclick="generateIDCardForStudent('${cl_id}', '${cd_id}')" style="background-color: #f5f5f5; cursor: pointer; padding-top: 10px; color: #3d6e9f; height: 30px" align="center"> 
		Generate ID Card 
	</div>

</body>
</html>