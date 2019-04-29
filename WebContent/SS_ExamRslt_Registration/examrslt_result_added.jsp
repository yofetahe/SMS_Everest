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
                $('#srList').dataTable( {
	                "sDom": 'T<"clear">lfrtip'
	            } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

	<s:set var="cl_id" value="cl_id"/>
	<s:set var="cd_id" value="cd_id"/>
	<s:set var="exsub_id" value="exsub_id"/>
	<s:set var="subcl_id" value="subcl_id"/>
<div style="color: GREEN; width: 100%; text-align: center;"> <s:actionmessage/> </div>
<table id="srList" width="100%" cellpadding="0" cellspacing="1" border="0">
						<thead style="background-color: #f5f5f5; height: 30px; text-align: center;">
							<tr>
								<td width="5%">No</td>
								<td width="40%">Student Name</td>
								<td width="15%">Mark</td>
								<td width="15%">&nbsp;</td>
							</tr>
						</thead>
						<s:iterator status="stat" value="studRsltList">
							<tr height="30px">
								<td align="center">
									<s:property value="#stat.index + 1"/>
									<s:set var="er_id" value="studRsltList[#stat.index].er_id"/>
									<s:set var="rslt" value="studRsltList[#stat.index].subjectTotal_mark"/>
									<s:set var="maxRslt" value="studRsltList[#stat.index].sub_totalmark"/>									
								</td>
								<td>
									<s:property value="studRsltList[#stat.index].fname"/>&nbsp;<s:property value="studRsltList[#stat.index].mname"/>&nbsp;<s:property value="studRsltList[#stat.index].gname"/>
								</td>
								<td>
									<s:property value="studRsltList[#stat.index].subjectTotal_mark"/>/<s:property value="studRsltList[#stat.index].sub_totalmark"/>
								</td>
								<td style="color: #3d6e9f; text-align: center;">
									<div style="cursor: pointer;" onclick="editSubMark('${er_id}', '${rslt}', '${maxRslt}', '${cl_id}', '${exsub_id}', '${cd_id}')">
										Edit
									</div>
								</td>
							</tr>
						</s:iterator>
					</table>

</body>
</html>