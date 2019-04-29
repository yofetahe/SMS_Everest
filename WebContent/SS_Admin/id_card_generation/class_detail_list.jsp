<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

</head>
<body>

	<table width="100%" cellpadding="0" cellspacing="0" style="padding-top: 0px;">		
		<tr>
			<td style="vertical-align: top;">
			
				<s:set var="classid" value="class_bean.cl_id"/>
				<s:set var="clname" value="class_bean.class_name"/>
				<s:set var="ac_year" value="ac_year"/> 
				
				<s:if test="%{#classid != null}">
					<table width="100%" style="border-bottom-style: solid; border-bottom-width: thin; border-bottom-color: #3d6e9f;" cellpadding="0" cellspacing="0"><tr><td>
						<table cellpadding="0" cellspacing="0" >
							<tr>
								<s:iterator status="stat" value="class_detail">
									<td width="80px" height="25px" align="center">
										<s:set var="cdid" value="class_detail[#stat.index].cd_id"/>
										<div class="header_btn_inactive" id="${cdid}_cd" onclick="classDetailForIDCard('${classid}', '${cdid}', '${ac_year}')">
											<s:property value="%{#clname}"/> - <s:property value="class_detail[#stat.index].cd_name"/>
										</div>
									</td>
									<td>&nbsp;</td>
								</s:iterator>								
							</tr>
						</table>
					</td></tr></table>
					<br/>
				</s:if>
			
				<!-- Student list -->				
				<div id="student_list_perdetial">
					
				</div>
				<!-- Student list -->
				
			</td>
		</tr>
	</table>

</body>
</html>