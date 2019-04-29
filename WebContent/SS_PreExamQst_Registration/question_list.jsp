<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<!-- Datatable -->
        <style type="text/css" title="currentStyle">
            @import "datatable/datatable_2/media/css/demo_page.css";
            @import "datatable/datatable_2/media/css/demo_table.css";
            @import "media/css/TableTools.css";
        </style>
        <script type="text/javascript" charset="utf-8" src="datatable/datatable_2/media/js/jquery.js"></script>
        <script type="text/javascript" charset="utf-8" src="datatable/datatable_2/media/js/jquery.dataTables.js"></script>
        <script type="text/javascript" charset="utf-8" src="media/js/ZeroClipboard.js"></script>
        <script type="text/javascript" charset="utf-8" src="media/js/TableTools.js"></script>
        <script type="text/javascript" charset="utf-8">
            $(document).ready( function () {
                $('#qList').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

<script type="text/javascript">
	function question_create(petid){		
		$("#qstForm").html("<img align=\"center\" src=\"images/loader.gif\"/>");		
		$.ajax({
			type : "POST",
			url : "question_create.action",				
			data : "pet_id=" + petid,
			success : function(response) {
				$('#qstForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function editQuestion(indx, petid){
		$("#preExQstList").html("<img align=\"center\" src=\"images/loader.gif\"/>");		
		$.ajax({
			type : "POST",
			url : "question_update.action",				
			data : "indx=" + indx + "&pet_id=" + petid,
			success : function(response) {
				$('#preExQstList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addQuestionChoise(peq_id){
		$("#qstForm").html("<img align=\"center\" src=\"images/loader.gif\"/>");		
		$.ajax({
			type : "POST",
			url : "question_choise_list.action",				
			data : "peq_id=" + peq_id,
			success : function(response) {
				$('#qstForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addQuestionDesc(peq_id){
		$("#qstForm").html("<img align=\"center\" src=\"images/loader.gif\"/>");		
		$.ajax({
			type : "POST",
			url : "question_desc_list.action",		
			data : "peq_id=" + peq_id,
			success : function(response) {
				$('#qstForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function uploadQuestionImage(peq_id){
		$("#preExQstList").html("<img align=\"center\" src=\"images/loader.gif\"/>");		
		$.ajax({
			type : "POST",
			url : "question_imageuploadfrm.action",		
			data : "peq_id=" + peq_id + "&uploadType=qst",
			success : function(response) {
				$('#preExQstList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function previewUploadedQstDoc(peq_id){
		alert(peq_id);
	}

</script>

</head>
<body>
	<div id="qstForm">
		<s:set var="petid" value="pet_id"/>
		<div style="color: red"><s:fielderror id="errorMsg"/></div>
		<div style="color: #3d6e9f; width: 100%; background-color: #e5e5e5;">
			&nbsp;
			Number of Question - <s:property value="catInfo[0].number_of_qst"/>
			&nbsp;&nbsp;&nbsp;
			Total Time Allowed - <s:property value="catInfo[0].total_time_allowed"/>
		</div>
		<table width="100%" cellpadding="0" cellspacing="0" style="padding-top: 4px">
			<tr>
				<td height="30px" >
					<div class="horizontal_btn" style="cursor: pointer; color: #3d6e9f; border:thin; border-color: #e5e5e5; border-style: solid; width: 200px; height: 30px; text-align: center; padding-top: 5px" onclick="question_create('${petid}')"> 
						Add Question 
					</div>
				</td>
			</tr>
			<tr><td style="border-bottom: thin; border-bottom-color: #e5e5e5; border-bottom-style: solid; line-height: 5px">&nbsp;</td></tr>
			<tr>
				<td style="padding-top: 5px" id="preExQstList">
					
					<table width="100%" id="qList">
						<thead style="background-color: #e5e5e5; height: 30px; color: #000000" align="center">
							<tr>
								<td>
									No
								</td>
								<td width="50%">
									Question
								</td>
								<td>
									Status
								</td>
								<td>
									&nbsp;
								</td>
								<td>
									&nbsp;
								</td>
								<td>
									&nbsp;
								</td>
							</tr>
						</thead>
						<s:iterator status="stat" value="qstList">
							<tr>
								<td style="height: 30px; text-align: center;">
									<s:property value="%{#stat.index + 1}"/>
									<s:set var="peq_id" value="qstList[#stat.index].peq_id"/>
								</td>
								<td>
									<div onclick="editQuestion('${stat.index}', '${petid}')" style="color: #3d6e9f; cursor: pointer;">
										<s:property value="qstList[#stat.index].peq_content"/>
									</div>
								</td>
								<td>
									<s:set var="peq_status" value="qstList[#stat.index].peq_status"/>
									<s:if test="%{#peq_status == \"A\"}">Active</s:if>
									<s:else>Inactive</s:else>
								</td>
								<td align="center">
									
									<s:set var="peq_image" value="qstList[#stat.index].peq_image"/>
									<s:if test="%{#peq_image == \"no\"}">
										<div onclick="uploadQuestionImage('${peq_id}')" style="cursor: pointer; color: #3d6e9f;">
											Upload Image
										</div> 
									</s:if>
									<s:else>
										<div onclick="previewUploadedQstDoc('${peq_id}')" style="cursor: pointer; color: #3d6e9f;">
											<img src="images/right_sign.png" width="12px">&nbsp;Preview
										</div> 
									</s:else>
									
								</td>
								<td align="center">
									<div onclick="addQuestionChoise('${peq_id}')" style="cursor: pointer; color: #3d6e9f;">
										Choice
									</div>
								</td>							
								<td align="center">
									<div onclick="addQuestionDesc('${peq_id}')" style="cursor: pointer; color: #3d6e9f;">
										Description
									</div>
								</td>
							</tr>
						</s:iterator>
					</table>
					
				</td>
			</tr>
		</table>
	</div>
</body>
</html>