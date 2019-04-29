<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		<table width="100%"><tr>
			<td>
				<table>
				<s:set var="ac_year" value="stud_bean.ac_year"/>
				<s:textfield label="Year" id="ac_year" value="%{#ac_year}" readonly="true" style="width: 100px; height: 20px"/>
				</table>
			</td><td>&nbsp;</td>
			<td>
				<div id="topofError" style="color: RED;"></div>
				<table>
					<s:textfield id="topof" label="Top of" required="true" style="height: 20px; width: 100px"/>
				</table>
			</td><td>&nbsp;</td>
			<td>
				<div id="qrtListError" style="color: RED;"></div>
				<table width="100%"><tr><td width="90px;"><b>QUARTERS</b> - </td>
					<s:iterator status="stat" value="sem_list">
						<td>
							<s:set var="at_id" value="sem_list[#stat.index].at_id"/>
							<input type="checkbox" onclick="selectQuarterForTopRankingReport('${at_id}')">	<s:property value="sem_list[#stat.index].at_name"/>
						</td>
					</s:iterator>
				</tr></table>								
			</td>
			<td rowspan="2" valign="bottom" align="center">
				<div onclick="getTopNsRankStudentsList()" class="adminHeaderButton" style="cursor: pointer; width: 100px;" align="center">GENERATE</div>
			</td>
		</tr><tr>
			<td colspan="5">
				<div id="gradeError" style="color: RED;"></div>
				<table width="100%"><tr>
					<td width="90px;"> <input type="checkbox" onclick="overAllTopRankingReport()"> OVER ALL </td>
					<td>&nbsp;</td>
					<td width="50px;"> <b>GRADES</b> </td>
					<s:iterator status="stat" value="grdList_rslt">
						<td>
							<s:set var="cl_id" value="grdList_rslt[#stat.index].cl_id"/>
							<input type="checkbox" onclick="selectClassIdForTopRankingReport('${cl_id}')">	<s:property value="grdList_rslt[#stat.index].class_name"/>
						</td>
					</s:iterator>
				</tr></table>				
			</td>
		</tr></table>	
	</div>
	<br/>
	<div id="topFiveStud">
	
	</div>

</body>
</html>