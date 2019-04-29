<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <s:select list="examList" id="et_id" headerValue="----" headerKey="0" listKey="et_id" listValue="et_name" style="width: 206px; height: 30px;"/> --%>

<div> 
	<table ><tr><td>
	<sj:datepicker  displayFormat="dd-MM-yy" readonly="true" minDate="today" changeMonth="true" changeYear="true" style="width: 200px"></sj:datepicker>
	</td></tr></table> 
</div>

<div id="errMsg" style="color: RED"></div>
<s:iterator status="stat" value="examList">
	<div style="padding-bottom: 10px; border-bottom: thin; border-bottom-color: gray; border-bottom-style: solid; border-bottom-width: thin;">
		<s:set var="et_name" value="examList[#stat.index].et_name"/>
		<s:set var="et_id" value="examList[#stat.index].et_id"/>
		<div>
			<table><tr><td>
				<table>
				<s:textfield label="Time From" id="from_hour_%{#stat.index+1}" style="width: 15px; height: 20px;"/>
				</table>
			</td><td>
				<table>
				<s:textfield label="" id="from_min_%{#stat.index+1}" style="width: 25px; height: 20px;"/>
				</table>
			</td><td>
				<table>
				<s:textfield label="Time To" id="to_hour_%{#stat.index+1}" style="width: 15px; height: 20px;"/>
				</table>
			</td><td>
				<table>
				<s:textfield label="" id="to_min_%{#stat.index+1}" style="width: 25px; height: 20px;"/>
				</table>
			</td><td valign="top" style="padding-top: 3px; font-style: italic;">Date:</td><td>
				<div> 
					<table><tr><td>
						<input type="text" id="exdate_${stat.index+1}" placeholder="dd-mm-yyyy" class="inputtext-format"/>						
					</td></tr></table>					
				</div>
			</td></tr></table>
		</div>
		<div id="extype_${stat.index+1}">
			<div style="cursor: pointer;" onclick="selectExamType('${stat.index+1}', '${et_name}', '${et_id}')"> 
				<table><tr><td style="vertical-align: middle;">
				<img alt="check" src="images/check_box.png" width="17px;"> </td><td> <s:property value="examList[#stat.index].et_name"/> 
				</td></tr></table> 
			</div>
		</div>
	</div>
	
</s:iterator>
