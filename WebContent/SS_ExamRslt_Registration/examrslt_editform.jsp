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

<script type="text/javascript">

	function editSubMark(er_id, rslt, maxRslt){
		
		$("#rsltEditForm").html("<img align=\"center\" src=\"images/loader.gif\"/>");
		$.ajax({
			type : "POST",
			url : "examrsltaction_editform.action",				
			data : "er_id=" + er_id + "&result=" + rslt + "&total_mark=" + maxRslt,
			success : function(response) {
				$('#rsltEditForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}

</script>

</head>
<body>

	

</body>
</html>