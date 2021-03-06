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
                $('#traitList').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

<div id="btpage">
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td style="width: 30%; padding-left: 5px;" valign="top">
				<div id="eMsg" style="color: #ff0000; width: 100%; text-align: center;"></div>
				<div style="color: #ff0000; width: 100%; text-align: center;"><s:fielderror id="errMsg"/></div>
				<div id="saveEditForm" class="formboarder">
					<div class="formtitle">Save Form</div>					
					<table align="center"><tr><td>
						<br/>
						Evaluate On Name
						<table cellpadding="0" cellspacing="0">
							<s:textfield id="bt_title" style="width: 200px; height: 30px;"/>
						</table>
						Evaluate On Description
						<table cellpadding="0" cellspacing="0">
							<s:textarea id="bt_desc" rows="10px" style="width: 200px;"/>
						</table>
						<table cellpadding="0" cellspacing="0" style="padding-top: 5px;">
							<s:submit onclick="saveBevalTrait()" value="Save" style="width: 205px; height: 30px;"/>
						</table>
					</td></tr></table>
					<br/>
				</div>
			
			</td>
			<td style="width: 2%">&nbsp;</td>
			<td valign="top">
				
				<div>
					<table id="traitList" width="100%" cellpadding="0" cellspacing="1">
						<thead style="background-color: #f5f5f5;">
							<tr style="height: 35px;">
								<th width="5%">No</th>
								<th width="15%">Trait Name</th>
								<th width="40%">Trait Description</th>
								<th width="10%">Status</th>
								<th width="20%">&nbsp;</th>
								<th width="10%">&nbsp;</th>
							</tr>
						</thead>
						<s:iterator status="stat" value="traitList">
							<tr style="height: 40px;">
								<td align="center">
									<s:property value="%{#stat.index + 1}"/>
									<s:set var="btid" value="traitList[#stat.index].bt_id"/>
								</td>
								<td>
									<s:property value="traitList[#stat.index].bt_title"/>
									<s:set var="bt_title" value="traitList[#stat.index].bt_title"/>
								</td>
								<td>
									<s:property value="traitList[#stat.index].bt_desc"/>
								</td>
								<td>
									<s:set var="depstatus" value="traitList[#stat.index].bt_status"/>
									<s:if test="%{#depstatus == \"A\"}">Active</s:if>
									<s:else>Inactive</s:else>
								</td>
								<td  style="text-align: center;">
									<div style="color: #3d6e9f; cursor: pointer;" onclick="traitGradeRel('${btid}', '${bt_title}')">Relate with Grade</div>
								</td>
								<td style="text-align: center;">
									<div style="color: #3d6e9f; cursor: pointer;" onclick="bevalTraitEditForm('${btid}')">Edit</div>
								</td>
							</tr>
						</s:iterator>
					</table>
				</div>

			</td>
		</tr>
	</table>
</div>

</body>
</html>