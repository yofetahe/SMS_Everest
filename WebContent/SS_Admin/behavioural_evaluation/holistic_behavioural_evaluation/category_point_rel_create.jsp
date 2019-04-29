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
                $('#CPRelCreate').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->


</head>
<body>

	<div id="saveEditForm" class="formboarder">
		<div class="formtitle">Save Form</div>
		<table align="center"><tr><td>
			<br/>
			<table align="center">
				<s:select list="unrelated_point_list" headerValue="---" headerKey="0" listValue="bhp_content" listKey="bhp_id" style="width: 305px; height: 40px;"/>
			</table>
			<table align="center">
				<s:submit value="Save" onclick="saveCategoryPointRel('${btc_id}', '${cl_id}')" style="width: 205px; height: 30px;"/>
			</table>
		</td></tr></table>	
		<br/>		
	</div>

</body>
</html>