<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

</head>
<body>

	<table width="100%" cellpadding="0" cellspacing="0" style="padding-top: 3px">					
		<tr>
			<td style="background-color: #f5f5f5; color: #3d6e9f" height="50px" valign="middle">
			
			<!-- filter form -->
				<table>
					<tr>
						<td>
							<table><tr><td>
								<div>
								<s:textfield label="Year" id="ac_year" name="academic_year" readonly="true" style="width: 40px; height: 22px"/>
								</div>
							</td></tr></table>
						</td>
						<td>
							<table><tr><td>
								<div>
								<s:select label="Quarter" id="at_id" headerValue="Select Quarter" headerKey="0" list="sem_list" listValue="at_name" listKey="at_id" style="width: 110px; height: 25px" onchange="classRoomList(this.value)"/>
								</div>
							</td></tr></table>
						</td>
						<td>
							<table><tr><td>
								<div id="classList">
								
								</div>
							</td></tr></table>
						</td>
						<td>
							<table><tr><td>
								<div id="grdList">
									
								</div>
							</td></tr></table>
						</td>
						<td>
							<table><tr><td>
								<div id="subList">
									
								</div>
							</td></tr></table>
						</td>
						<td>
							<table><tr><td>
								<div id="examList">
									
								</div>
							</td></tr></table>
						</td>
					</tr>
				</table>
				<!-- filter form -->				
			
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
			
				<div id="studList">
				
				</div>
			
			</td>
		</tr>
	</table>

</body>
</html>