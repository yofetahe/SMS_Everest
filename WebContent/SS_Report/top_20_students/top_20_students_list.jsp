<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                $('#topRankStud').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

<table id="topRankStud" cellpadding="0" cellspacing="1" width="70%">
	<thead style="background-color: #f5f5f5; text-align: center;">
		<tr style="height: 30px;">
			<th width="10%"> Rank </th>
			<th> Grade </th>
			<th> Student Name </th>
			<th> Quarter Average </th>
			<th> Quarter Total </th>
		</tr>
	</thead>
	<s:iterator status="stat" value="top_twenty_stud">		
		<tr>
			<td align="center"> <s:property value="%{#stat.index + 1}"/> </td>
			<td height="30px;" align="center">
				<s:property value="top_twenty_stud[#stat.index].ex_rslt_bean.cl_name"/> <s:property value="top_twenty_stud[#stat.index].ex_rslt_bean.cd_name"/>
			</td>
			<td style="padding-left: 5px;">
				<s:property value="top_twenty_stud[#stat.index].ex_rslt_bean.fname"/> <s:property value="top_twenty_stud[#stat.index].ex_rslt_bean.mname"/> <s:property value="top_twenty_stud[#stat.index].ex_rslt_bean.gname"/>
			</td>
			<td align="center">
				<s:set var="stud_avg" value="top_twenty_stud[#stat.index].ex_rslt_bean.average_mark"/>
				<fmt:formatNumber maxIntegerDigits="3" pattern="##.#">${stud_avg}</fmt:formatNumber>
			</td>
			<td align="center">
				<s:property value="top_twenty_stud[#stat.index].ex_rslt_bean.total_mark"/>
			</td>
		</tr>
	</s:iterator>
</table>

</body>
</html>