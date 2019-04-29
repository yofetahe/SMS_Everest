<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head/>
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
                $('#absStud').dataTable( {
                    //"sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>
<div style="padding-top: 20px;">
	<table id="absStud" width="100%" cellpadding="0" cellspacing="1" align="left">
	 	<thead style="background-color: #f5f5f5; height: 30px; text-align: center;">
		 	<tr>
		 		<td width="40px" style="height: 30px">No.</td>
		 		<td>Student Name</td>
		 		<td>&nbsp;</td>
		 	</tr>
	 	</thead>
	 	<s:iterator status="stat" value="abstStu_list">
	 		<tr >
	 			<td align="center" style="height: 35px">
	 				<s:property value="#stat.index + 1"/>
	 				<s:set var="att_id" value="abstStu_list[#stat.index].att_id"/>
	 			</td>
	 			<td width="25%">
	 				 <s:property value="abstStu_list[#stat.index].firstName"/> <s:property value="abstStu_list[#stat.index].fatherName"/> <s:property value="abstStu_list[#stat.index].gfName"/>
	 			</td>
	 			<td>
	 				<div id="reason_%{#stat.index}">
		 				<s:set var="att_reason" value="abstStu_list[#stat.index].attendacen_reason"/>
		 				<s:if test="%{#att_reason == \"N\"}">
		 					<div id="msg_${#stat.index}" style="color: #ff0000"></div> 					
		 					<table cellpadding="0" cellspacing="0"><tr><td>
		 					<table cellpadding="0" cellspacing="0"><s:textfield id="rs_%{#stat.index}" style="width: 400px; height: 20px;"></s:textfield> </table>
		 					</td><td >
		 						<div style="cursor: pointer; color: #3d6e9f;" onclick="AddAbsentReason('${att_id}', '${stat.index}')">Add Reason</div>
		 					</td></tr></table>	 					
		 					
		 				</s:if>
		 				<s:else>		 					
		 					Reason:- <s:property value="abstStu_list[#stat.index].attendacen_reason"/>
		 				</s:else>
	 				</div>
	 			</td>
	 		</tr>
	 	</s:iterator>
	</table>
</div>
</body>
</html>