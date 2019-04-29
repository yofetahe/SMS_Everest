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
                $('#chList').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

<script type="text/javascript">
	function question_choice_create(peq_id){
		$("#chListDiv").html("<img align=\"center\" src=\"images/loader.gif\"/>");		
		$.ajax({
			type : "POST",
			url : "pe_choice_create.action",				
			data : "peq_id=" + peq_id,
			success : function(response) {
				$('#chListDiv').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function updateChoice(pec_id, indx, peq_id){
		
		$("#chListDiv").html("<img align=\"center\" src=\"images/loader.gif\"/>");		
		$.ajax({
			type : "POST",
			url : "pe_choice_editform.action",				
			data : "pec_id=" + pec_id + "&indx=" + indx + "&peq_id=" + peq_id,
			success : function(response) {
				$('#chListDiv').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function uploadQuestionChoiceImage(pec_id){
		
		$("#chListDiv").html("<img align=\"center\" src=\"images/loader.gif\"/>");		
		$.ajax({
			type : "POST",
			url : "pe_choice_uploadfrm.action",			
			data : "pec_id=" + pec_id  + "&uploadType=qstchoice",
			success : function(response) {
				$('#chListDiv').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function previewUploadedQChoiceDoc(pec_id){
		alert(pec_id);
	}
</script>

</head>
<body>
	Question - <s:property value="qst[0].peq_content"/>
	<s:set var="peq_id" value="peq_id"/>
	<div id="chListDiv">
		<table width="100%" cellpadding="0" cellspacing="0" style="padding-top: 4px" id="choiceList">
			<tr>
				<td style="border-bottom: thin; border-bottom-color: #e5e5e5; border-bottom-style: solid; line-height: 5px;">&nbsp;</td>
			</tr>
			<tr>
				<td height="30px" style="padding-top: 5px">
					<div class="horizontal_btn" onclick="question_choice_create('${peq_id}')" style="cursor: pointer; color: #3d6e9f; border:thin; border-color: #e5e5e5; border-style: solid; width: 200px; height: 30px; text-align: center; padding-top: 5px" > 
						Add Question Choice
					</div>
				</td>
			</tr>
			<tr>
				<td style="padding-top: 5px" id="preExQstList">
				
					<table width="100%" id="chList">
						<thead style="background-color: #e5e5e5; height: 30px; color: #000000" align="center">
							<tr>
								<th>No</th>
								<th>Choice</th>
								<th>Correct</th>
								<th>Status</th>
								<th>&nbsp;</th>
							</tr>
						</thead>
						<s:iterator status="stat" value="qstChoiceList">
							<tr>
								<td style="height: 30px; text-align: center;" width="5%">
									<s:property value="%{#stat.index + 1}"/>
									<s:set var="pec_id" value="qstChoiceList[#stat.index].pec_id"/>
								</td>
								<td>
									<div onclick="updateChoice('${pec_id}', '${stat.index}', '${peq_id}')" style="color:#3d6e9f; cursor: pointer">
										<s:property value="qstChoiceList[#stat.index].pec_content"/>
									</div>
								</td>
								<td width="15%">
									<s:set var="corAns" value="qstChoiceList[#stat.index].pec_correct"/>
									<s:if test="%{#corAns == \"Y\"}">Yes</s:if>
									<s:else>No</s:else>
								</td>
								<td width="15%">
									<s:set var="status" value="qstChoiceList[#stat.index].pec_status"/>
									<s:if test="%{#status == \"A\"}">Active</s:if>
									<s:else>Inactive</s:else>
								</td>
								<td align="center" width="15%">									
									<s:set var="pec_image" value="qstChoiceList[#stat.index].pec_image"/>
									<s:if test="%{#pec_image == \"no\"}"> 
										<div onclick="uploadQuestionChoiceImage('${pec_id}')" style="cursor: pointer; color: #3d6e9f;">Upload Image</div>
									</s:if>
									<s:else>
										<div onclick="previewUploadedQChoiceDoc('${peq_id}')" style="cursor: pointer; color: #3d6e9f;">
											<img src="images/right_sign.png" width="12px">&nbsp;Preview
										</div>
									</s:else>									
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