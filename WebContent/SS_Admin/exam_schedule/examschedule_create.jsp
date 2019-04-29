<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sj:head />
<s:head />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

</head>
<body>

	<div style=" width: auto; height: 35px; background-color: #f5f5f5; padding-top: 15px;">School Event Add Form</div>
	
	<div style="color: GREEN; padding: 5px; text-align: center;">
			<img alt="information" src="images/information.jpeg" width="20px;">
			If the new record has similar EXAM TYPE, ACADEMIC YEAR, QUARTER, CLASS, and SUBJECT with the existing data,
			then the system will consider it as a duplicated record. 
			Therefore, other than inserting a new record, it is better to update the existing one.
	</div>
	
	<div id="errMsg" style="color: red; text-align: center; width: 30%;"> <s:actionmessage/> </div>
	
	<table>
			<tr>
				<td style="text-align: right; font-style: italic;">Terms<span style="color: red">*</span>:</td>			
				<td>				
					<table><tr><td>
					<s:select list="annualTermList" id="at_id" headerValue="---" headerKey="0" listKey="at_id" listValue="at_name" style="width: 206px; height: 30px;"/>
					</td></tr></table>
				</td>
			</tr>
			<tr>
				<td style="text-align: right; font-style: italic;">Class<span style="color: red">*</span>:</td>
				<td>
					<table><tr><td>
					<s:select list="classList" id="cl_id" headerValue="---" headerKey="0" listKey="cl_id" listValue="class_name" onchange="subjectListUnderClass(this.value)" style="width: 206px; height: 30px;"/>
					</td></tr></table>
				</td>
			</tr>
			<tr>
				<td style="text-align: right; font-style: italic;">Subject<span style="color: red">*</span>:</td>
				<td id="subList">
					<table><tr><td>
					<s:select list="#{'',''}" id="sub_id" headerValue="---" headerKey="0" style="width: 206px; height: 30px;"/> 
					</td></tr></table>				
				</td>
			</tr>
			<tr>
				<td style="text-align: right; font-style: italic; vertical-align: top; padding-top: 10px;">Exam Type<span style="color: red">*</span>:</td>
				<td id="exTypeList" style="padding-left: 8px; padding-top: 10px; min-height: 40px;">
	<!-- 				<table><tr><td> -->
	<%-- 				<s:select list="#{'',''}" id="et_id" headerValue="---" headerKey="0" style="width: 206px; height: 30px;"/> --%>
	<!-- 				</td></tr></table> -->
				</td>
			</tr>
	<!-- 		<tr> -->
	<%-- 			<td style="text-align: right; font-style: italic;">Exam Date<span style="color: red">*</span>:</td> --%>
	<!-- 			<td style="padding-left: 5px;"> -->
	<%-- 				<table><tr><td><sj:datepicker id="es_greg_date" name="es_greg_date" --%>
	<%-- 										displayFormat="dd-MM-yy" readonly="true"  --%>
	<%-- 										minDate="today" changeMonth="true"  --%>
	<%-- 										changeYear="true" style="height: 25px; width: 200px"></sj:datepicker></td></tr></table> --%>
	<!-- 			</td> -->
	<!-- 		</tr> -->
			<tr>
				<td colspan="2" align="left" style="padding-left: 90px;">
					<table><tr><td>
						<s:submit onclick="saveExamSchedule()" value="Save"/>
					</td></tr></table>
				</td>
			</tr>
		</table>	
	

</body>
</html>