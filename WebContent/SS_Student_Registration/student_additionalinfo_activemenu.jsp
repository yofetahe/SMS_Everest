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

		<table width="100%" cellpadding="0" cellspacing="0">
			<tr height="25px">
				<td align="center" style="border-style: dotted; border-width: thin;">
					<div onclick="current_address_save('${sinfo_id}')" id="ca" style="cursor: pointer; height: 25px; padding-top: 3px">
						Current Address
					</div>
				</td>
			</tr>
			<tr height="25px">
				<td align="center" style="border-style: dotted; border-width: thin;">
					<div onclick="emergency_contact_save()" id="ec" style="cursor: pointer; height: 25px; padding-top: 3px">
						Emergency Contact
					</div>
				</td>
			</tr>
<!-- 			<tr height="25px"> -->
<!-- 				<td align="center" style="border-style: dotted; border-width: thin;"> -->
<%-- 					<div onclick="grade_history_save()" id="gh" style="cursor: pointer; height: 25px; padding-top: 3px"> --%>
<!-- 						Grade History -->
<%-- 					</div> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
			<tr height="25px">
				<td align="center" style="border-style: dotted; border-width: thin;">
					<div onclick="reward_save()" id="r" style="cursor: pointer; height: 25px; padding-top: 3px">
						Reward
					</div>
				</td>
			</tr>
			<tr height="25px">
				<td align="center" style="border-style: dotted; border-width: thin;">
					<div onclick="disciplinary_action_save()" id="da" style="cursor: pointer; height: 25px; padding-top: 3px">
						Disciplinary action
					</div>
				</td>
			</tr>
		</table>

</body>
</html>