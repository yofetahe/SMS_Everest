<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/sms_css.css">
<script src="../js/jquery-1.6.1.js" type="text/javascript"></script>
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
                $('#traitClassList').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

<s:set var="cl_id" value="cl_id"/>
<div id="traitInfo">
	<table width="100%" cellpadding="0" cellspacing="0" style="padding-top: 5px;">
		<tr>
			<td width="60%" valign="top">
			
				<table id="traitClassList" width="100%" cellpadding="0" cellspacing="1">
					<thead style="background-color: #f5f5f5;">
						<tr style="height: 35px;">
							<th width="10%">No</th>
							<th width="50%">Traits Name</th>
							<th width="25%">Relation Status</th>
							<th>&nbsp;</th>
						</tr>
					</thead>
					<s:iterator status="stat" value="traitListPerClass">
						<tr style="height: 30px;">
							<td align="center">
								<s:property value="%{#stat.index + 1}"/>
								<s:set var="btc_id" value="traitListPerClass[#stat.index].btc_id"/>
							</td>
							<td>
								<s:property value="traitListPerClass[#stat.index].bt_title"/>
								<s:set var="bt_title" value="traitListPerClass[#stat.index].bt_title"/>								
							</td>
							<td>
								<s:set var="relStatus" value="traitListPerClass[#stat.index].rel_status"/>
								<s:if test="%{#relStatus == \"A\"}">Active</s:if>
								<s:else>Inactive</s:else>
							</td>
							<td align="center" style="color: #3d6e9f;">
								<div style="cursor: pointer;" onclick="editClassTraitRel('${btc_id}', '${cl_id}')">Edit</div>
							</td>			
						</tr>
					</s:iterator>
				</table>
				
			</td>
			<td width="2%">&nbsp;</td>
			<td valign="top">
				<div id="errMsg" style="color: #ff0000;"></div>
				<div id="saveEditForm" class="formboarder">
					<div class="formtitle">Save Form</div>
					<table align="center"><tr><td>
						<br/>
						Traits<br/>
						<table cellpadding="0" cellspacing="0">
							<s:select list="unrelatedTraitList" id="bt_id" listKey="bt_id" listValue="bt_title" headerKey="0" headerValue="---" style="width: 205px; height: 30px;"/>
						</table>
						
					</td></tr>
					<tr><td style="padding-top: 5px;">
						<button onclick="saveTraitClassRel('${cl_id}')">Update</button>						
					</td></tr></table>
					<br/>
				</div>

			</td>
		</tr>
	</table>
</div>
</body>
</html>