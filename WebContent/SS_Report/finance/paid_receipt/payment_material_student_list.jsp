<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="../css/sms_css.css">
<script src="../js/jquery-1.6.1.js" type="text/javascript"></script>
<script src="/js/jquery-1.6.1.js" type="text/javascript"></script>
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
	<s:set var="rownum" value="1"/>
	<s:set var="clid" value="class_id"/>
	<s:set var="pt_id" value="pt_id"/>
	
	<s:hidden id="clid" value="%{#clid}"/>
		<table id="stud_list" width="100%" cellpadding="0" cellspacing="2">
			<thead style="background-color: #f5f5f5; color: #000000" align="center">
				<tr height="30px;">
					<td width="5%" height="30px;">No</td>
					<td width="35%">Student Name</td>					
					<td width="40%">&nbsp;</td>					
					<td>&nbsp;</td>
				</tr>
			</thead>
			<s:iterator status="stat" value="stud_rslt">
				<tr height="30px">
					<td align="center">
						<s:property value="%{#stat.index + 1}"/>
						<s:set var="siid" value="stud_rslt[#stat.index].si_id"/>
						<s:set var="fname" value="fname"/>
						<s:set var="mname" value="mname"/>
						<s:set var="gname" value="gname"/>									
					</td>
					<td>
						<div style="color: #3d6e9f;">
							<s:property value="fname"/> &nbsp; <s:property value="mname"/> &nbsp; <s:property value="gname"/>
						</div>									
					</td>
					<td align="center">
						<div onclick="getPurchesedMaterialList('${siid}', '${fname}', '${mname}', '${gname}', '${clid}', '${pt_id}')" style="color: #3d6e9f; cursor: pointer;">
							Get Paid Receipt
						</div>														
					</td>
					<td>&nbsp;</td>				
				</tr>
				<s:set var="rownum" value="%{#rownum + 1}"/>
			</s:iterator>
		</table>
				
</body>
</html>