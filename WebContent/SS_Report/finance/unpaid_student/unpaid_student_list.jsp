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
                $('#unpaid_stud_list').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>
<div style="width: 80%;">
	<table id="unpaid_stud_list" width="100%" cellpadding="0" cellspacing="1">
		<thead style="background-color: #f5f5f5; text-align: center;">
			<tr>
				<td height= "30px;">No</td>
				<td>Class</td>
				<td>Student Name</td>				
			</tr>
		</thead>
		<s:iterator status="stat" value="unpaid_stud_list">
			<tr height="30px;">
				<td align="center"> <s:property value="%{#stat.index + 1}"/> </td>
				<td> <s:property value="unpaid_stud_list[#stat.index].payment_bean.class_name"/> - <s:property value="unpaid_stud_list[#stat.index].payment_bean.cd_name"/> </td>
				<td> <s:property value="unpaid_stud_list[#stat.index].payment_bean.fname"/> <s:property value="unpaid_stud_list[#stat.index].payment_bean.mname"/> <s:property value="unpaid_stud_list[#stat.index].payment_bean.gname"/> </td>
			</tr>	
		</s:iterator>
	</table>
</div>
</body>
</html>