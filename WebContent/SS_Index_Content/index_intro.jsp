<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<script src="js/jquery-1.6.1.js" type="text/javascript"></script>
<script type="text/javascript">
	function student_intro(){
		$("#indexContent").html("<img align=\"center\" src=\"images/loader.gif\"/>");
		$.ajax({
			type : "POST",
			url : "studentaction_listintro.action",				
			data : "",
			async : true,
			success : function(response) {
				$('#indexContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function teacher(){
		
		$("#index_cont").html("<img align=\"center\" src=\"images/loader.gif\"/>");
		$.ajax({
			type : "POST",
			url : "teacheraction_list.action",				
			data : "",
			async : true,
			success : function(response) {
				$('#index_cont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function subject(){
		
		$("#index_cont").html("<img align=\"center\" src=\"images/loader.gif\"/>");
		$.ajax({
			type : "POST",
			url : "subjectaction_list.action",				
			data : "",
			async : true,
			success : function(response) {
				$('#index_cont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function grade_reg(){
		$("#index_cont").html("<img align=\"center\" src=\"images/loader.gif\"/>");
		$.ajax({
			type : "POST",
			url : "classaction_list.action",				
			data : "",
			async : true,
			success : function(response) {
				$('#index_cont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
		
	function exam_result(){
		$("#index_cont").html("<img align=\"center\" src=\"images/loader.gif\"/>");
		$.ajax({
			type : "POST",
			url : "examrsltaction_list.action",				
			data : "",
			async : true,
			success : function(response) {
				$('#index_cont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function exam_question(){
		alert("exam_question");
	}
		
	function payment(){
		alert("payment");
	}
	
	function report(){
		alert("report");
	}
	
	function admin(){
		alert("admin");
	}
</script>

</head>
<body style="margin: 0">
<div id="indexContent">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<s:include value="header.jsp"/>
			</td>
		</tr>
		<tr>
			<td align="center">
				<!-- body part -->
				<div style="width: 1200px; min-height: 450px">					
					<table height="450px" cellpadding="0" cellspacing="40" border="0">
						<tr>
							<td onclick="student_intro()" height="100px" width="200px" valign="middle" align="center" style="border-style: dotted; border-width: thin; border-color: #3d6e9f; cursor: pointer; font-weight: bold; font-size: 16px">
								<img alt="" src="images/student.jpeg" width="60px" style="padding-top: 5px"> <br/> Student
							</td>
							<td onclick="teacher_intro()" height="100px" width="200px" valign="middle" align="center" style="border-style: dotted; border-width: thin; border-color: #3d6e9f; cursor: pointer; font-weight: bold; font-size: 16px">
								<img alt="" src="images/teachers.jpeg" width="60px" style="padding-top: 5px"> <br/> Teacher
							</td>
							<td onclick="subject_intro()" height="100px" width="200px" valign="middle" align="center" style="border-style: dotted; border-width: thin; border-color: #3d6e9f; cursor: pointer; font-weight: bold; font-size: 16px">
								<img alt="" src="images/subject.jpeg" width="60px" style="padding-top: 5px"> <br/> Subject/Exam
							</td>
							<td onclick="class_intro()" height="100px" width="200px" valign="middle" align="center" style="border-style: dotted; border-width: thin; border-color: #3d6e9f; cursor: pointer; font-weight: bold; font-size: 16px">
								<img alt="" src="images/classroom.jpeg" width="80px" style="padding-top: 5px"> <br/> Class
							</td>
							<td onclick="exam_result_intro()" height="100px" width="200px" valign="middle" align="center" style="border-style: dotted; border-width: thin; border-color: #3d6e9f; cursor: pointer; font-weight: bold; font-size: 16px">
								<img alt="" src="images/exam.jpeg" width="80px" style="padding-top: 5px"> <br/> Exam Result
							</td>														
						</tr>
						<tr>
							<td onclick="exam_question_intro()" height="100px" width="200px" valign="middle" align="center" style="border-style: dotted; border-width: thin; border-color: #3d6e9f; cursor: pointer; font-weight: bold; font-size: 16px">
								<img alt="" src="images/exam.jpeg" width="80px" style="padding-top: 5px"> <br/> Exam Question
							</td>
							<td onclick="payment_intro()" height="100px" width="200px" valign="middle" align="center" style="border-style: dotted; border-width: thin; border-color: #3d6e9f; cursor: pointer; font-weight: bold; font-size: 16px">
								<img alt="" src="images/payment.jpeg" width="80px" style="padding-top: 5px"> <br/> Payment and Fin
							</td>
							<td onclick="report_intro()" height="100px" width="200px" valign="middle" align="center" style="border-style: dotted; border-width: thin; border-color: #3d6e9f; cursor: pointer; font-weight: bold; font-size: 16px">
								<img alt="" src="images/report.jpeg" width="80px" style="padding-top: 5px"> <br/> Report
							</td>
							<td onclick="admin_intro()" height="100px" width="200px" valign="middle" align="center" style="border-style: dotted; border-width: thin; border-color: #3d6e9f; cursor: pointer; font-weight: bold; font-size: 16px">
								<img alt="" src="images/admin.jpeg" width="80px" style="padding-top: 5px"> <br/> Admin
							</td>
							<td height="100px" width="200px" valign="middle" align="center" style="border-style: dotted; border-width: thin; border-color: #3d6e9f; cursor: pointer; font-weight: bold; font-size: 16px">
								&nbsp;
							</td>														
						</tr>												
					</table>					
				</div>
				<!-- body part -->
			</td>
		</tr>
		<tr>
			<td>
				<s:include value="footer.jsp"/>
			</td>
		</tr>
	</table>
</div>
</body>
</html>