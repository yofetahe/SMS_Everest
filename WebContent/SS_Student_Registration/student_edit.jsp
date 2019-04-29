<%@page import="java.io.File"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.InputStream"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

</head>
<body>

	<s:set var="cd_id" value="cd_id"/>

	<table cellpadding="0" cellspacing="0" width="100%">
<!-- 		<tr> -->
<!-- 			<td style="height: 50px; padding-top: 1px"> -->
<!-- 				<div class="menu_shadow" style="background-color: #f5f5f5; height: 35px; padding-top: 15px"> -->
<%-- 					<a href="/<s:text name='global.sysname'/>/student.action" style="text-decoration: none; color: #000000"> --%>
<%-- 						<div style="cursor: pointer"><img alt="click" src="images/next.gif" width="8px"> Back to List</div> --%>
<!-- 					</a> -->
<!-- 				</div> -->
<!-- 			</td> -->
<!-- 		</tr> -->
		<tr>
			<td style="padding-top: 10px">						
				<div class="content_background">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top" width="15%">
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td align="left">
										<div id="photo_display" style="width: 100%; background-color: silver" align="center">
										
											<s:set var="fname" value="fname"/>
											<s:set var="mname" value="mname"/>
											<s:set var="photo_path" value="photo_path"/>
											<s:set var="si_id" value="si_id"/>
											<s:set var="class_id" value="class_id"/>
											<s:set var="photo_name" value="photo_name"/>
											<s:set var="stud_name" value="%{#fname + ' ' + #mname}"/>
											
											<img alt="<s:property value="%{#stud_name}"/> photo" src="imageRetrieveServlet?si_id=${si_id}" width="154px" />
																	
											<div onclick="upload_photo('${si_id}', '${class_id}')" style="width: auto; text-align: center; background-color: #3d6e9f; width: auto; color: #fff; cursor: pointer; height: 30px;">
												<s:property value="fname"/> &nbsp; <s:property value="mname"/>
											</div>
											
										</div>
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;
									</td>
								</tr>
								<tr>
									<td>
										<s:include value="student_additionalinfo_menu.jsp"/>
									</td>
								</tr>
							</table>							
						</td>
						<td width="2%">
							&nbsp;
						</td>
						<td valign="top">
							<div id="student_additionalinfo">
								<s:include value="personal_info/student_personalinfo_edit.jsp"/>
							</div>
						</td>
					</tr>
				</table>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>