<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">
<script src="js/jquery-1.6.1.js" type="text/javascript"></script>

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
<div id="btpage">
<div class="subHeaderTitle" style="padding-left: 5px;">
	Adding Evaluation Point for <s:property value="bt_title"/>
	<s:set var="bt_id" value="bt_id"/>
</div>
<div id="btbgrelpage" style="padding-top: 10px;">
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td style="width: 30%; padding-left: 5px;" valign="top">
				<div id="eMsg" style="color: #ff0000; width: 100%; text-align: center;"></div>
				<div style="color: #ff0000; width: 100%; text-align: center;"><s:fielderror id="errMsg"/></div>
				<div id="saveEditForm" class="formboarder">
					<div class="formtitle">Save Form</div>				
					<table align="center"><tr><td>						
						<br/>
						Grade Name
						<table cellpadding="0" cellspacing="0">
							<s:select id="bg_id" list="unrelatedGradeList" listKey="bg_id" listValue="bg_name" headerKey="0" headerValue="---" style="width: 205px; height: 30px;"/>
						</table>						
						
					</td></tr>
					<tr><td style="padding-top: 5px;">
						<button onclick="saveBevalTraitGradeRel('${bt_id}')">Save</button>						
					</td></tr></table>
					<br/>
				</div>
			
			</td>
			<td style="width: 2%">&nbsp;</td>
			<td valign="top">
				
				<div>
					<table id="grdList" width="100%" cellpadding="0" cellspacing="1">
						<thead style="background-color: #f5f5f5;">
							<tr style="height: 35px;">
								<th width="5%">No</th>
								<th width="25%">Grade Name</th>
								<th width="40%">Grade Description</th>
								<th width="10%">Relation Status</th>
								<th width="10%">&nbsp;</th>
							</tr>
						</thead>
						<s:iterator status="stat" value="traitGradeRelList">
							<tr style="height: 40px;">
								<td align="center">
									<s:property value="%{#stat.index + 1}"/>
									<s:set var="btbg_id" value="traitGradeRelList[#stat.index].btbg_id"/>
								</td>
								<td>
									<s:property value="traitGradeRelList[#stat.index].bg_name"/>
								</td>
								<td>
									<s:property value="traitGradeRelList[#stat.index].bg_desc"/>
								</td>
								<td>
									<s:set var="btbgrelstatus" value="traitGradeRelList[#stat.index].btbg_rel"/>
									<s:if test="%{#btbgrelstatus == \"A\"}">Active</s:if>
									<s:else>Inactive</s:else>
								</td>
								<td style="text-align: center;">
									<div style="color: #3d6e9f; cursor: pointer;" onclick="traitGradeRelEditForm('${btbg_id}', '${bt_id}')">Edit</div>
								</td>
							</tr>
						</s:iterator>
					</table>
				</div>

			</td>
		</tr>
	</table>
</div>
</div>
</body>
</html>