<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>

	<s:set var="sinfo_id" value="si_id"/>
	<table border="0" width="100%" cellpadding="0" cellspacing="2">
		<tr height="25px">
			<td align="center" style="border-style: dotted; border-width: thin;">
				<div onclick="student_info_save()" id="si" style="cursor: pointer; height: 25px; padding-top: 3px; background-color: #f5f5f5">
					Student Info <s:property value="si_id"/>
				</div>
			</td>
		</tr>
		<tr>
			<td>			
				<div id="menu_active">
					 <table  width="100%" cellpadding="0" cellspacing="0">
						<tr height="25px">
							<td align="center" style="border-style: dotted; border-width: thin;">
								<div id="ca" style="height: 25px; padding-top: 3px; color: gray">
									Current Address
								</div>
							</td>
						</tr>
						<tr height="25px">
							<td align="center" style="border-style: dotted; border-width: thin;">
								<div id="ec" style="color: gray; height: 25px; padding-top: 3px">
									Emergency Contact
								</div>
							</td>
						</tr>
						<tr height="25px">
							<td align="center" style="border-style: dotted; border-width: thin;">
								<div id="gh" style="color: gray; height: 25px; padding-top: 3px">
									Grade History
								</div>
							</td>
						</tr>
						<tr height="25px">
							<td align="center" style="border-style: dotted; border-width: thin;">
								<div id="r" style="color: gray; height: 25px; padding-top: 3px">
									Reward
								</div>
							</td>
						</tr>
						<tr height="25px">
							<td align="center" style="border-style: dotted; border-width: thin;">
								<div id="da" style="color: gray; height: 25px; padding-top: 3px">
									Disciplinary action
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