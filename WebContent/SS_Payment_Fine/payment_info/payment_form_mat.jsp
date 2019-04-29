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
                $('#mat_list').dataTable( {
                    "sDom": ''
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>
	<div id="paymentForMoreMonth">
		<s:set var="clid" value="class_id"/>				 
		<s:set var="si_id" value="si_id"/>
		<s:set var="payment_type" value="payment_type"/>
		<s:set var="fname" value="fname"/>
		<s:set var="mname" value="mname"/>
		<s:set var="gname" value="gname"/>
		
		<div id="errorMsg" style="color: red"></div>			
		<table style="padding-top: 5px;" width="100%" cellpadding="0" cellspacing="0">		
			<tr>
				<td style="border-bottom-color: #f5f5f5; border-bottom-style: solid; border-bottom-width: thin;">
					<table width="100%"><tr><td>
						
						Payment for <s:property value="fname"/> <s:property value="mname"/> <s:property value="gname"/>
					
					</td></tr></table>
				</td>
			</tr>
			<tr>
				<td>
					
				</td>
			</tr>
			<tr>
				<td>
					
					<s:include value="payment_form_material.jsp"></s:include>
					
				</td>
			</tr>
		</table>
	</div>
</body>
</html>