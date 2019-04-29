<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

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
                $('#resultHitory').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>
	<div id="container">
		<table width="100%" id="resultHitory">
			<thead style="background-color: #f5f5f5">
				<tr>
					<th>No</th>
					<th>Subject</th>
					<th>Total Mark</th>
					<th>Minimum Pass Mark</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<s:iterator status="stat" value="classSubject_rslt">
				<tr>
					<td>
						<s:property value="#stat.index + 1"/>
					</td>
					<td>
						<s:property value="classSubject_rslt[#stat.index].sub_name"/>
					</td>
					<td>
						<s:property value="classSubject_rslt[#stat.index].total_mark"/>
					</td>
					<td>
						<s:property value="classSubject_rslt[#stat.index].total_pass_mark"/>
					</td>
					<td align="center" style="color: #3d6e9f; cursor: pointer;">
						Detail
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
</body>
</html>