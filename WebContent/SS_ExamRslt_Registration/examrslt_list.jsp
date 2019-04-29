<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script src="js/jquery-1.6.1.js" type="text/javascript"></script>
<script src="js/sms_examrslt_registration.js" type="text/javascript"></script>
</head>
<body>
	<div style="background-color: #f5f5f5; height: 35px; padding-top: 6px; padding-left: 5px;">
		<table cellpadding="0" cellspacing="0"><tr><td>		
			<table cellpadding="0" cellspacing="0"><tr><td style="padding-top: 3px;">																		
				<s:select label="Academic Year" id="academicYear" list="acyear_list" style="width: 150px; height: 25px" onchange="refreshStudentMarkList()"/>
			</td></tr></table>
		</td>
		<td width="10px">&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;</td>
		<td id="semList">			
			<table cellpadding="0" cellspacing="0"><tr><td style="padding-top: 3px;">
				<s:select label="Quarter" id="semisterList" headerValue="---" headerKey="0" list="sem_list" listValue="at_name" listKey="at_id" style="width: 150px; height: 25px" onchange="refreshStudentMarkList()"/>
			</td></tr></table>		
		</td>
		<td width="10px">&nbsp;</td>
		<td id="semList">			

			<table><tr><td> 
				<div id="ck_box">
					<img alt="" src="images/check_box.png" style="width: 20px;" onclick="onCertificateResult()">
				</div>
			</td><td> 
				Certificate
			</td><td>
				<input id="certOnOff" style="width: 8px; visibility: hidden;" value="0">
			</td></tr></table>
			
		</td>
		<td width="10px">&nbsp;</td>
		<td id="semList">			

			<table><tr><td> 
				With Rank
			</td><td> 
				<select id="certificateRank" style="width: 50px; height: 25px" >
					<option>Yes</option>
					<option>No</option>
				</select>
			</td></tr></table>
			
		</td>
		<td width="10px">&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;</td>
		<td id="clLIst">
			
		</td>
		</tr></table>		
	</div>
	<table width="100%" cellpadding="0" cellspacing="0" style="padding-top: 10px">
		<tr>
			<td style="padding-top: 0px">
			
				<table cellpadding="0" cellspacing="0" width="100%">
					<tr>
<!-- 						<td width="15%" valign="top"> -->
							
<!-- 							<table border="0" width="100%" cellpadding="0" cellspacing="0"> -->
<!-- 								<tr> -->
<!-- 									<td style="background-color: #3d6e9f; color: #ffffff; height: 35px; text-align: center;"> -->
<!-- 										Class Category -->
<!-- 									</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td style="line-height: 3px">&nbsp;</td> -->
<!-- 								</tr> -->
<%-- 								<s:iterator status="stat" value="grade_rslt"> --%>
<!-- 									<tr> -->
<!-- 										<td align="center" onmouseover="this.style.color = '#3d6e9f'" onmouseout="this.style.color = '#000000'"> -->
<%-- 											<s:set var="clid" value="grade_rslt[#stat.index].class_id"/> --%>
<%-- 											<s:set var="clname" value="grade_rslt[#stat.index].class_name"/> --%>
<%-- 											<div class="menu_button" id="${clid}" onclick="examrslt_gradeSelected('${clid}', '${clname}')"> --%>
<%-- 												<s:property value="grade_rslt[#stat.index].class_name"/>																 --%>
<%-- 											</div> --%>
<!-- 										</td> -->
<!-- 									</tr> -->
<!-- 									<tr> -->
<!-- 										<td style="line-height: 3px">&nbsp;</td> -->
<!-- 									</tr>														 -->
<%-- 								</s:iterator> --%>
<!-- 							</table> -->
						
<!-- 						</td> -->
<!-- 						<td width="30px"> -->
<!-- 							&nbsp; -->
<!-- 						</td> -->
						<td valign="top">
							<div id="errMsg" style="color: red"></div>
							<div id="studRsltList">
								
							</div>
						</td>
					</tr>
				</table>			
				
			</td>
		</tr>
	</table>

</body>
</html>