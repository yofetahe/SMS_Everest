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
                $('#classList12').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

<script type="text/javascript">
	

</script>

</head>
<body style="margin: 0">
	<s:set var="classid" value="cl_id"/>
	
	<table width="100%" style="border-bottom-style: solid; border-bottom-width: thin; border-bottom-color: #3d6e9f;" cellpadding="0" cellspacing="0"><tr><td>
		<table cellpadding="0" cellspacing="0" >
			<tr>
				<s:iterator status="stat" value="class_detail">
					<td width="80px" height="25px" align="center">
						<s:set var="cdid" value="class_detail[#stat.index].cd_id"/>
						<div class="header_btn_inactive" id="${cdid}_cd" onclick="classDetail('${classid}', '${cdid}')">
							<s:property value="cl_name"/> - <s:property value="class_detail[#stat.index].cd_name"/>
						</div>
					</td>
					<td>&nbsp;</td>
				</s:iterator>								
			</tr>
		</table>
	</td></tr></table>
		
	<div id="attStudList">
		
	</div>
					
</body>
</html>