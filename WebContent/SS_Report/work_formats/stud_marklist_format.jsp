<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-grid-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS_Mark_List</title>

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
                $('#markListFmt').dataTable( {
                	"sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

	<div class="save_update_btn" onclick="generateMarkListFormat('${cl_id}', '${cd_id}', '${ac_year}')" style="height: 30px; width: 240px; padding-top: 1px; padding-left: 25px; cursor: pointer; text-align: center;">
		<table><tr><td>
			<img alt="PDF" src="images/pdf.gif" height="25px">
		</td><td valign="middle">
			Generate Mark List Format
		</td></tr></table>
	</div>
	<br/>
	
	<table id="markListFmt" cellpadding="0" cellspacing="1" width="100%">
		<thead>
			<tr style="background-color: #f5f5f5; font-size: 14px; height: 30px">
				<th> No </th>
				<th> Student Name </th>
				<th> Sex </th>
				<s:iterator status="examtype" value="examtype_list">
					<th> <s:property value="examtype_list[#examtype.index].et_name"/> </th>					
				</s:iterator>
			</tr>
		</thead>
		<s:iterator status="stat" value="stud_rslt">
			<tr height="35px">
				<td align="center"> <s:property value="%{#stat.index + 1}"/> </td>
				<td> <s:property value="stud_rslt[#stat.index].fullName"/> </td>
				<td align="center"> <s:property value="stud_rslt[#stat.index].sex"/> </td>
				<s:iterator status="examtype" value="examtype_list">
					<td> &nbsp; </td>
				</s:iterator>
			</tr>
		</s:iterator>
	</table>
	
	<br/>

</body>
</html>