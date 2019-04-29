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

	<div style="width: auto; height: 35px; background-color: #f5f5f5; padding-top: 15px;">School Event Add Form</div>
	<div id="errMsg" style="color: red; text-align: center; width: 30%;"></div>
	<table>
		<tr>
			<td style="text-align: right; font-style: italic;">Class<span style="color: red">*</span>:</td>
			<td>
				<table><tr><td>
				<s:hidden id="es_id" name="es_id"/>
				<s:select list="classList" id="cl_id" headerValue="---" headerKey="0" listKey="cl_id" listValue="class_name" name="cl_id" onchange="subjectListUnderClass(this.value)" style="width: 206px; height: 30px;"/>
				</td></tr></table>
			</td>
		</tr>
		<tr>
			<td style="text-align: right; font-style: italic;">Subject<span style="color: red">*</span>:</td>
			<td id="subList">
				<table><tr><td>
				<s:select list="subjectList" id="sub_id" headerValue="---" headerKey="0" listKey="sub_id" listValue="sub_name" name="sub_id" style="width: 206px; height: 30px;"/> 
				</td></tr></table>				
			</td>
		</tr>
		<tr>
			<td style="text-align: right; font-style: italic;">Exam Type<span style="color: red">*</span>:</td>
			<td id="exTypeList">
				<table><tr><td>
				<s:select list="examList" id="et_id" headerValue="---" headerKey="0" listKey="et_id" listValue="et_name" name="et_id" style="width: 206px; height: 30px;"/>
				</td></tr></table>
			</td>
		</tr>
		<tr>
			<td style="text-align: right; font-style: italic;">Exam Date<span style="color: red">*</span>:</td>
			<td>
				<table><tr><td><sj:datepicker id="es_greg_date" name="es_greg_date"
										displayFormat="dd-MM-yy" readonly="true" 
										minDate="today" changeMonth="true" 
										changeYear="true" style="height: 25px; width: 200px"></sj:datepicker></td></tr></table>
			</td>
		</tr>
		<tr>
			<td valign="top">Exam Hour</td>
			<td>
				<s:set var="time_from" value="time_from"/>
				<s:set var="time_to" value="time_to"/>
				
				<s:property value="time_from"/> - <s:property value="time_to"/>
				<table><tr><td align="right">
					<table>
					<s:textfield label="Time From" id="from_hour" style="width: 15px; height: 20px;"/>
					</table>
				</td><td>
					<table>
					<s:textfield label="" id="from_min" style="width: 25px; height: 20px;"/>
					</table>
				</td></tr><tr><td align="right">
					<table>
					<s:textfield label="Time To" id="to_hour" style="width: 15px; height: 20px;"/>
					</table>
				</td><td>
					<table>
					<s:textfield label="" id="to_min" style="width: 25px; height: 20px;"/>
					</table>
				</td></tr></table>
			</td>
		</tr>
		<tr>
			<td style="text-align: right; font-style: italic;">Status<span style="color: red">*</span>:</td>
			<td>
				<table><tr><td>
					<s:select list="#{'A':'Active', 'I':'Inactive'}" id="es_status" name="es_status" style="width: 206px; height: 30px;"/>
				</td></tr></table>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<table><tr><td>
					<button onclick="updateExamSchedule('${time_from}', '${time_to}')" class="btn">Update</button>
				</td></tr></table>
			</td>
		</tr>
	</table>

</body>
</html>