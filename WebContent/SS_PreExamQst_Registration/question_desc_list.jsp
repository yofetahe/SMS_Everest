<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<script type="text/javascript">
	function question_desc_create(peq_id){
		$("#qstForm").html("<img align=\"center\" src=\"images/loader.gif\"/>");		
		$.ajax({
			type : "POST",
			url : "question_desc_crtform.action",		
			data : "peq_id=" + peq_id,
			success : function(response) {
				$('#qstForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}

	function editQDesc(pee_id, peq_id, indx){
		$("#qstForm").html("<img align=\"center\" src=\"images/loader.gif\"/>");		
		$.ajax({
			type : "POST",
			url : "question_desc_editform.action",		
			data : "pee_id=" + pee_id + "&peq_id=" + peq_id + "&indx=" + indx,
			success : function(response) {
				$('#qstForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function qstDescImgUpload(pee_id, peq_id){
		
		$("#qDescList").html("<img align=\"center\" src=\"images/loader.gif\"/>");		
		$.ajax({
			type : "POST",
			url : "question_desc_imageuploadfrm.action",		
			data : "pee_id=" + pee_id + "&peq_id=" + peq_id + "&uploadType=qstdesc",
			success : function(response) {
				$('#qDescList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function previewUploadedQstDescDoc(pee_id){
		alert(pee_id);
	}
</script>

</head>
<body>
	<s:set var="peq_id" value="peq_id"/>
	<table width="100%" cellpadding="0" cellspacing="0" style="padding-top: 5px" id="descList">
		<tr>
			<td height="30px" style="padding-top: 5px">
				<div class="horizontal_btn" onclick="question_desc_create('${peq_id}')" style="cursor: pointer; color: #3d6e9f; border:thin; border-color: #e5e5e5; border-style: solid; width: 200px; height: 30px; text-align: center; padding-top: 5px" > 
					Add Question Description
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 5px" id="preExQstList">
				<div> Question: <s:property value="qst[0].peq_content"/> </div>
				<div id="qDescList">
				
					<table width="100%" cellpadding="0" cellspacing="0" style="padding-top: 10px;">
						<thead>
							<tr><td colspan="4" style="background-color: #e5e5e5; height: 35px; color: #3d6e9f;">
								Question Description
							</td></tr>
						</thead>
						<s:iterator status="stat" value="qdList">
							<tr>							
								<td style="border-bottom-color: #e5e5e5; border-bottom-style: solid; border-bottom-width: thin; height: 35px">
									<s:set var="pee_id" value="qdList[#stat.index].pee_id"/>
									<s:property value="qdList[#stat.index].explanation_dtl"/>
								</td>
								<td style="border-bottom-color: #e5e5e5; border-bottom-style: solid; border-bottom-width: thin; height: 35px">
									<s:set var="pee_image" value="qdList[#stat.index].pee_image"/>								
									<s:if test="%{#pee_image == \"no\"}">
										<div onclick="qstDescImgUpload('${pee_id}', '${peq_id}')" style="color: #3d6e9f; cursor: pointer"> 
											Image Upload
										</div>
									</s:if>
									<s:else>
										<div onclick="previewUploadedQstDescDoc('${pee_id}')" style="cursor: pointer; color: #3d6e9f;">
											<img src="images/right_sign.png" width="12px">&nbsp;Preview
										</div>
									</s:else>
								</td>
								<td style="border-bottom-color: #e5e5e5; border-bottom-style: solid; border-bottom-width: thin; height: 35px">
									<s:set var="pee_status" value="qdList[#stat.index].pee_status"/>
									<s:if test="%{#pee_status == \"A\"}">Active</s:if>
									<s:else>Inactive</s:else>
								</td>
								<td align="center" width="5%" style="border-bottom-color: #e5e5e5; border-bottom-style: solid; border-bottom-width: thin; height: 35px">
									<s:set var="indx" value="%{#stat.index}"/>
									<div style="cursor: pointer; color: #3d6e9f;" onclick="editQDesc('${pee_id}', '${peq_id}', '${indx}')">Edit</div>
								</td>
							</tr>
						</s:iterator>
					</table>
				
				</div>
			</td>
		</tr>
	</table>
	
</body>
</html>