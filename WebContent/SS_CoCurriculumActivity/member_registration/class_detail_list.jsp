<%@ taglib prefix="s" uri="/struts-tags"%>

<s:select label="Class Detail" list="class_detail" id="cd_id" listKey="cd_id" listValue="cd_name" onchange="getStudentListUnderSelectedClass(this.value)" headerKey="0" headerValue="Please select grade detail" style="height: 25px; width: 205px;"/>
