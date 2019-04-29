<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script src="js/jquery-1.6.1.js" type="text/javascript"></script>

</head>
<body>
	<s:set var="cl_id" value="cl_id"/>
	<s:set var="exsub_id" value="exsub_id"/>
	<s:set var="subcl_id" value="subcl_id"/>
	
		
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td width="49%" valign="top">
				<div style="width: 100%; color: #ffffff; height: 30px; background-color: #f5f5f5; text-align: center; font-size: 16px; font-size: 16px; padding-top: 5px;">Exam Result Add Form</div>
				<div id="studrsltFrm" style="padding-top: 5px">
					
					<s:set var="exsub_id" value="exsub_id"/>
					
<!-- 					<table border="0" style="border-bottom-color: #f5f5f5; border-bottom-style: solid; border-bottom-width: thin;" width="100%" cellpadding="0" cellspacing="0"> -->
<!-- 					<tr><td align="left" style="vertical-align: top;"> -->
<!-- 						<table cellpadding="0" cellspacing="0"><tr><td> -->
<!-- 						query extract the list different from full year -->
<%-- 						<s:select label="Semister" id="semisterList" list="sem_list" headerKey="0" headerValue="----" listValue="at_name" listKey="at_id" style="width: 150px; height: 25px" onchange="refreshExamCreateFrm('${exsub_id}', '${cl_id}', '${subcl_id}')"/> --%>
<!-- 						</td></tr></table> -->
<!-- 					</td>					 -->
<!-- 					<td align="left" style="vertical-align: top;"> -->
<!-- 						<table cellpadding="0" cellspacing="0"><tr><td> -->
<%-- 						<s:textfield label="Year" id="ac_year" key="academic_year" readonly="true" style="width: 100px; height: 20px"/> --%>
<!-- 						</td></tr></table>						 -->
<!-- 					</td></tr> -->
<!-- 					<tr><td colspan="3" style="line-height: 5px">&nbsp;</td></tr> -->
<!-- 					</table> -->
					
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<div id="rsltError" style="color: red; font-weight: bold; padding-bottom: 10px; width: auto; text-align: center"></div>
								<div id="examrsltcreatetable">
									<s:include value="examrslt_createtable_list.jsp"/>
								</div>
							</td>
						</tr>
					</table>	
				</div>
			
			</td>
			<td>&nbsp;</td>
			<td width="49%" valign="top">			
				<div style="width: 100%; color: #ffffff; height: 30px; background-color: #f5f5f5; text-align: center; font-size: 16px; padding-top: 5px">Registed Exam Result List</div>
				<div id="rsltEditForm" style="padding-top: 5px">
					
					<s:include value="examrslt_result_added.jsp"/>
					
				</div>
			
			</td>
		</tr>
	</table>
	
</body>
</html>