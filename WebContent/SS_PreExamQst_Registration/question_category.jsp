<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<script type="text/javascript">
	function categoryQstList(pet_id){
		$("#qstList").html("<img align=\"center\" src=\"images/loader.gif\"/>");		
		$.ajax({
			type : "POST",
			url : "pe_sub_questionlist.action",				
			data : "pet_id=" + pet_id,
			success : function(response) {
				$('#qstList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	} 
	
	function addExamCategory(subcl_id, sub_id){
		$("#qstList").html("<img align=\"center\" src=\"images/loader.gif\"/>");		
		$.ajax({
			type : "POST",
			url : "pe_create_examcat.action",				
			data : "subcl_id=" + subcl_id + "&sub_id=" + sub_id,
			success : function(response) {
				$('#qstList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function listExamCategory(subcl_id, sub_id){
		$("#qstList").html("<img align=\"center\" src=\"images/loader.gif\"/>");		
		$.ajax({
			type : "POST",
			url : "pe_list_examcat.action",				
			data : "subcl_id=" + subcl_id + "&sub_id=" + sub_id,
			success : function(response) {
				$('#qstList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
</script>

</head>
<body>
	<s:set var="subclid" value="subcl_id"/>
	<s:set var="subid" value="sub_id"/>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td height="40px" style="background-color: #e5e5e5">
				
				<table>
					<tr>
						<td>
						
							<table><tr><td>
								<s:select label="Exam Category" list="ExamCat" listKey="pet_id" listValue="exam_level" headerValue="Please select exam type" headerKey="0" onchange="categoryQstList(this.value)"/>
							</td></tr></table>
							
						</td>
						<td width="60px">&nbsp;</td>
						<td>
							<div style="cursor: pointer; color: #3d6e9f; border:thin; border-color: #fff; border-style: solid; width: 200px; height: 30px; text-align: center; padding-top: 5px" onclick="addExamCategory('${subclid}', '${subid}')">
								Add Exam Category
							</div>
						</td>
						<td width="60px">&nbsp;</td>
						<td>
							<div style="cursor: pointer; color: #3d6e9f; border:thin; border-color: #fff; border-style: solid; width: 200px; height: 30px; text-align: center; padding-top: 5px" onclick="listExamCategory('${subclid}', '${subid}')">
								List Exam Category
							</div>
						</td>
					</tr>
				</table>
								
				
			</td>
		</tr>
		<tr>
			<td id="qstList">
				
			</td>
		</tr>
	</table>

</body>
</html>