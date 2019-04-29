<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script src="js/sms_behavioural_evaluation.js" type="text/javascript"></script>
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
                $('#bevalRslt').dataTable( {
                    //"sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>
	
<s:set var="cl_id" value="cl_id"/>
<s:set var="si_id" value="si_id"/>
<s:set var="qr_id" value="qr_id"/>
<s:set var="ac_year" value="ac_year"/>
<s:set var="at_name" value="at_name"/>
<s:set var="fname" value="fname"/>
<s:set var="mname" value="mname"/>
<s:set var="gname" value="gname"/>

<div id="studRsltList">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<div style="border-bottom-color: silver; border-bottom-style: solid; border-bottom-width: thin;">
					<table><tr><td>
					Student Name: <span style="color: #3d6e9f;"> <s:property value="fname"/> <s:property value="mname"/> <s:property value="gname"/> </span> &nbsp;&nbsp;&nbsp;&nbsp;
					Academic Year: <span style="color: #3d6e9f;"> <s:property value="ac_year"/></span>
					</td></tr></table>
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px;">
			
				<table width="100%" cellpadding="0" cellspacing="0"><tr><td width="59%" valign="top">
				
				<table id="bevalRsl" width="100%" cellpadding="0" cellspacing="1">
					<thead style="background-color: #f5f5f5;">
						<tr>
							<td width="10%" style="height: 35px;" align="center">No</td>
							<td width="50%" align="center">Trait Name</td>
							<td width="20%" align="center">Result Given</td>
							<td width="20%" align="center">Status</td>
						</tr>
					</thead>
					<s:iterator status="stat" value="stud_bevalrslt_list">
						<tr style="height: 40px;">
							<td align="center">
								<s:property value="%{#stat.index + 1}"/>
								<s:set var="bsr_id" value="stud_bevalrslt_list[#stat.index].bsr_id"/>
								<s:set var="bsr_status" value="stud_bevalrslt_list[#stat.index].bsr_status"/>
								<s:set var="bt_id" value="stud_bevalrslt_list[#stat.index].bt_id"/>
								<s:set var="bg_id" value="stud_bevalrslt_list[#stat.index].bg_id"/>
								<s:set var="bsr_status" value="stud_bevalrslt_list[#stat.index].bsr_status"/>
							</td>
							<td style="color: #3d6e9f;">
								<div style="cursor: pointer" onclick="bevalRsltEditForm('${bsr_id}', '${cl_id}', '${si_id}', '${qr_id}', '${bt_id}', '${bsr_status}', '${bg_id}')">
									<s:property value="stud_bevalrslt_list[#stat.index].bt_title"/>
								</div>
							</td>
							<td>
								<s:property value="stud_bevalrslt_list[#stat.index].bg_name"/>								
							</td>
							<td>
								<s:if test="%{#bsr_status == \"A\"}">Active</s:if>
								<s:else>Inactive</s:else>
							</td>
						</tr>
					</s:iterator>
				</table>
				
			</td></tr>
			
			<tr><td valign="top" style="padding-right: 5px; padding-top: 20px;">				
											
					<div id="saveEditForm" class="formboarder">
						<table width="100%"><tr><td>
							<div class="formtitle"> SAVE FORM </div>
							
							<div id="errMsg" style="color: #ff0000; width: auto; text-align: center;">&nbsp;</div>

							<s:set var="counter" value="0"/>
							
							<c:forEach var="btraitList" varStatus="stat" items="${btrait_list}">
								
								<div style="background-color: #f5f5f5; padding-left: 10px; min-height: 20px; padding-top: 10px;"> 
									#&nbsp;<c:out value="${btraitList.bt_title}"></c:out>
								</div>
								
								<form>
									<table><tr>
										<c:forEach var="bglist" items="${btrait_list[stat.index].evalgrd_list}">
											<td> <input type="radio" name="btid" id="${bglist.bg_id}" onclick="assignBevalResult(${bglist.bg_id}, ${btraitList.bt_id}, ${cl_id}, ${si_id}, ${qr_id})"> </td>
											<td> <c:out value="${bglist.bg_name}"/> </td>
										</c:forEach>
									</tr></table>
								</form>
								<c:set  var="counter" value="${counter + 1}"/>
							</c:forEach>
							
							
<%-- 							<s:iterator status="stat" value="btrait_list"> --%>								
<%-- 								<s:set var="bt_id" value="btrait_list[#stat.index].bt_id"/> --%>
<%-- 								<input type="text" id="btid_${stat.index}" value="${bt_id}" style="visibility: hidden;"> --%>								
<!-- 								<div style="background-color: #f5f5f5; padding-left: 10px; min-height: 20px; padding-top: 10px;">  -->
<%-- 									#&nbsp;<s:property value="btrait_list[#stat.index].bt_title"/>  --%>
<!-- 								</div> -->								
<!-- 								<table><tr><td style="padding-left: 10px;"> -->
<%-- 									<s:form> --%>										
<%-- 										<s:radio id="atid" label="Points - " required="true" name="bg_id" list="btrait_list[#stat.index].evalgrd_list"  --%>
<%-- 											onchange="assignBevalResult(this.value, --%>
<%-- 												document.getElementById('btid').value, --%>
<%-- 												document.getElementById('clid').value, --%>
<%-- 												document.getElementById('siid').value, --%>
<%-- 												document.getElementById('qrid').value)" listValue="bg_name" listKey="bg_id"></s:radio> --%>
<%-- 									</s:form> --%>
<!-- 								</td></tr></table> -->								
<%-- 								<s:set var="counter" value="%{#counter + 1}"/> --%>								
<%-- 							</s:iterator> --%>
							
							
						</td></tr>
						<tr><td align="center">
							<button onclick="saveBevalResultFromJSArray('${counter}', '${cl_id}', '${si_id}', '${qr_id}', '${fname}', '${mname}', '${gname}', '${at_name}', '${ac_year}')" class="btn">Save</button>						
						</td></tr></table>
					</div>


				</td></tr></table>
			
			</td>
		</tr>
	</table>
	
	<input type="text" id="clid" value="${cl_id}" style="visibility: hidden;">
	<input type="text" id="siid" value="${si_id}" style="visibility: hidden;">
	<input type="text" id="qrid" value="${qr_id}" style="visibility: hidden;">
	
</div>
</body>
</html>