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

	<s:set var="uploadType" value="uploadType"/>
	<s:if test="%{#uploadType == \"qst\"}">
	
		<div style="padding-top: 10px; height: 35px;">
			<s:text name="Uploading an image relation to pre exam question"/> <br/>
		</div>
		<div style="border-top-color: #e5e5e5; border-top-style: solid; border-top-width: thin;">
			
			<s:set var="peq_id" value="peq_id"/>
			<s:form action="uploadQstDocument" enctype="multipart/form-data" method="post">			
				<s:hidden key="peq_id" name="peq_id" value="%{#peq_id}"></s:hidden>		
				<s:file name="fileUpload" label="Select document" size="30"/>
				<br/>
				<s:submit value="Upload" align="left"/>
			</s:form>
		</div>		
		
	</s:if>
	<s:elseif test="%{#uploadType == \"qstdesc\"}">
	
		<div style="padding-top: 10px; border-top-color: #e5e5e5; border-top-style: solid; border-top-width: thin; height: 35px;">
			<s:text name="Uploading an image relation to pre exam question description"/> <br/>
		</div>
		<div style="border-top-color: #e5e5e5; border-top-style: solid; border-top-width: thin;">
		
			<s:set var="pee_id" value="pee_id"/>
			<s:form action="uploadDescDocument" enctype="multipart/form-data" method="post">			
				<s:hidden key="pee_id" name="pee_id" value="%{#pee_id}"></s:hidden>		
				<s:file name="fileUpload" label="Select document" size="30"/>
				<br/>
				<s:submit value="Upload" align="left"/>
			</s:form>
		</div>		
	
	</s:elseif>
	<s:elseif test="%{#uploadType == \"qstchoice\"}">
	
		<div style="padding-top: 10px; border-top-color: #e5e5e5; border-top-style: solid; border-top-width: thin; height: 35px;">
			<s:text name="Uploading an image relation to pre exam question choice"/> <br/>
		</div>
		<div style="border-top-color: #e5e5e5; border-top-style: solid; border-top-width: thin;">
		
			<s:set var="pec_id" value="pec_id"/>
			<s:form action="uploadChoiceDocument" enctype="multipart/form-data" method="post">			
				<s:hidden key="pec_id" name="pec_id" value="%{#pec_id}"></s:hidden>		
				<s:file name="fileUpload" label="Select document" size="30"/>
				<br/>
				<s:submit value="Upload" align="left"/>
			</s:form>
		</div>
			
	</s:elseif>
	
</body>
</html>