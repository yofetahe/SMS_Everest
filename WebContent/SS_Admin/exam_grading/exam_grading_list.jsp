<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script src="../js/sms_admin.js" type="text/javascript"></script>

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
                $('#grdList').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

<div id="grd">
	<table cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td width="50%" valign="top">
			
				<table cellpadding="0" cellspacing="1" id="grdList" width="100%">
					<thead style="background-color: #f5f5f5; height: 30px; text-align: center;">
						<tr>
							<td width="10%">No</td>
							<td width="30%">Grade</td>
							<td width="35%">Description</td>
							<td>Status</td>
							<td>&nbsp;</td>
						</tr>
					</thead>
					<s:iterator status="stat" value="gradingList">
						<tr style="height: 30px">
							<td align="center">
								<s:property value="%{#stat.index + 1}"/>
								<s:set var="eg_id" value="gradingList[#stat.index].eg_id"/>
								<s:set var="eg_value" value="gradingList[#stat.index].eg_value"/>
								<s:set var="eg_desc" value="gradingList[#stat.index].eg_desc"/>
								<s:set var="eg_status" value="gradingList[#stat.index].eg_status"/>
							</td>
							<td>
								<s:property value="%{#eg_value}"/>
							</td>			
							<td>
								<s:property value="%{#eg_desc}"/>
							</td>
							<td align="center">
								<s:set var="eg_status" value="gradingList[#stat.index].eg_status"/>
								<s:if test="%{#eg_status == \"A\"}">Active</s:if>
								<s:else>Inactive</s:else>
							</td>
							<td style="color: #3d6e9f;" align="center">
								<div onclick="editGrading('${eg_id}', '${eg_value}', '${eg_desc}', '${eg_status}')" style="cursor: pointer">
									Edit
								</div>
							</td>
						</tr>
					</s:iterator>
				</table>
			
			</td>
			<td>&nbsp;</td>
			<td width="50%" valign="top" style="padding-left: 35px;" align="center">
			
				<div id="saveEditForm" class="formboarder">	
						
					<div class="formtitle">Save Grade</div>
						
					<div id="eMsg" style="color: #ff0000; width: 100%; text-align: center;"></div>
					<div style="color: #ff0000; width: 100%; text-align: center;"><s:actionmessage id="errMsg"/></div>
					
					<table>
						<tr>
							<td>							
								<s:textfield label="Grade" required="true" id="eg_value" style="width: 200px; height: 30px;"/>
								<s:textfield label="Grade Description" required="true" id="eg_desc" style="width: 200px; height: 30px;"/>		
								
								<s:submit onclick="saveGrade()" value="SAVE" align="center"/>
							</td>
						</tr>
					</table>
				</div>
				
			</td>
		</tr>
	</table>
</div>

</body>
</html>