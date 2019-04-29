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
                $('#defComList').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

<div id="defCom">
	<table cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td width="65%" valign="top">
			
				<table cellpadding="0" cellspacing="1" id="defComList" width="100%">
					<thead style="background-color: #f5f5f5; height: 30px; text-align: center;">
						<tr>
							<td width="7%">No</td>
							<td>Comment Content</td>
							<td width="15%">Rank From</td>
							<td width="15%">Rank To</td>
							<td width="10%">Status</td>
							<td width="10%">&nbsp;</td>
						</tr>
					</thead>
					<s:iterator status="stat" value="defCommentList">
						<tr style="height: 50px">
							<td align="center">
								<s:property value="%{#stat.index + 1}"/>
								<s:set var="edc_id" value="defCommentList[#stat.index].edc_id"/>
								<s:set var="edc_content" value="defCommentList[#stat.index].edc_content"/>
								<s:set var="rank_from" value="defCommentList[#stat.index].rank_from"/>
								<s:set var="rank_to" value="defCommentList[#stat.index].rank_to"/>
								<s:set var="edc_status" value="defCommentList[#stat.index].edc_status"/>
							</td>
							<td>
								<s:property value="defCommentList[#stat.index].edc_content"/>
							</td>			
							<td align="center">
								<s:property value="defCommentList[#stat.index].rank_from"/>
							</td>
							<td align="center">
								<s:property value="defCommentList[#stat.index].rank_to"/>
							</td>
							<td align="center">
								<s:set var="edc_status" value="defCommentList[#stat.index].edc_status"/>
								<s:if test="%{#edc_status == \"A\"}">Active</s:if>
								<s:else>Inactive</s:else>
							</td>
							<td style="color: #3d6e9f;" align="center">
								<div onclick="editDefaultComment('${edc_id}', '${edc_content}', '${rank_from}', '${rank_to}', '${edc_status}')" style="cursor: pointer">
									Edit
								</div>
							</td>
						</tr>
					</s:iterator>
				</table>
			
			</td>
			<td>&nbsp;</td>
			<td width="30%" valign="top" style="padding-right: 5px;" align="center">
			
				<div id="saveEditForm" class="formboarder">	
						
					<div class="formtitle">Save Comment</div>
						
					<div id="eMsg" style="color: #ff0000; width: 100%; text-align: center;"></div>
					<div style="color: #ff0000; width: 100%; text-align: center;"><s:actionmessage id="errMsg"/></div>
					
					<table>
						<tr>
							<td>							
								<s:textarea label="Comment" required="true" id="edc_com" rows="5" cols="23"/>
								<s:textfield label="Rank From" required="true" id="rank_from" style="width: 200px; height: 30px;"/>
								<s:textfield label="Rank To" required="true" id="rank_to" style="width: 200px; height: 30px;"/>						
								
								<s:submit onclick="saveDefaultComment()" value="Save" align="center"/>
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