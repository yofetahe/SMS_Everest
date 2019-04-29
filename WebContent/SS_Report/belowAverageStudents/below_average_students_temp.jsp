<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script type="text/javascript" src="js/sms_report.js"></script>
</head>
<body>

	<div style="width: 100%; background-color: #f5f5f5;">
		<table><tr>
			<td>
				<table>
				<s:set var="ac_year" value="stud_bean.ac_year"/>
					<%-- <s:textfield label="Year" id="ac_year" value="%{#ac_year}"  readonly="true" style="width: 50px; height: 20px"/> --%>
					<s:select label="Year" id="ac_year" list="acyear_list" style="width: 76px; height: 25px;"/>
				</table>
			</td><td>&nbsp;</td>
			<td>
				<div id="qrtListError" style="color: RED;"></div>
				<table>
					<s:select label="Quarter" id="at_id" list="sem_list" listKey="at_id" listValue="at_name" headerKey="0" headerValue="---" onchange="classRoomListForBelowAverage(this.value)" style="width: 106px; height: 25px;"/>
				</table>
			</td><td>&nbsp;</td>
			<td>
				<table><tr><td>
					<div id="clListError" style="color: RED;"></div>
					<div id="classList">
						
					</div>
				</td></tr></table>
			</td><td>&nbsp;</td>
			<td>
				<table><tr><td>
					<div id="clGrdListError" style="color: RED;"></div>
					<div id="gradeList">
									
					</div>
				</td></tr></table>
			</td><td>&nbsp;</td>
			<td>
				<table><tr><td>
					<div id="averageListError" style="color: RED;"></div>
					<div id="averageList">
									
					</div>
				</td></tr></table>
			</td><td>&nbsp;</td>			
			<td>
				<div id="officeUse"> <img alt="check" src="images/check_box.png" onclick="filterStudentForOffice()" style="cursor: pointer;" width="16px"> </div>
			</td><td>&nbsp;</td>
			<td>
				<div onclick="generateBelowAverageStudentList()" class="adminHeaderButton" style="cursor: pointer; width: 100px;">GENERATE</div>
			</td><td>&nbsp;</td>			
		</tr></table>	
	</div>
	<br/>
	<div id="belowAverageList">
	
	</div>

</body>
</html>