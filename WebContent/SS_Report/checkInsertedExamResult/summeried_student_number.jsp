<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
                $('#checkStudMarkInsert').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>
<div style="width: 50%">
	<table id="checkStudMarkInsert" cellpadding="0" cellspacing="1" width="100%">
		<thead style="background-color: #f5f5f5; text-align: center;">
			<tr style="height: 30px;">
				<th> No </th>
				<th> Subject </th>
				<th> No. of Students </th>
			</tr>
		</thead>
		<s:iterator status="stnum" value="summerized_studmark_number">
						
					<tr>
						<td height="30px;">
							<s:property value="%{#stnum.index + 1}"/>
						</td>
						<td height="30px;">
							<s:property value="summerized_studmark_number[#stnum.index].sub_name"/>
						</td>
						<td align="center">
							<s:property value="summerized_studmark_number[#stnum.index].stud_count"/>
						</td>
					</tr>		
				
		</s:iterator>
	</table>
</div>

</body>
</html>