<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script src="js/jquery-1.6.1.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.8.18.custom.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/styles.css">
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<script src="js/script.js"></script>
<STYLE>
	body, input{
		font-family: Calibri, Arial;
	}
	#accordion {
		list-style: none;
		padding: 0 0 0 0;
		width:auto;	
	}
	#accordion li{
		display: block;
		background-color: #E5E5E5;
 		/* font-weight: bold; */
		font-style:italic;
		font-size: 14px;
		margin: 1px;
		cursor: pointer;
		padding: 5 5 5 7px;
		list-style: circle;
		border-top-color: #fff;
		border-bottom-color: #fff;
		border-top-style: solid;
		border-bottom-style: solid;
		border-top-width: thin;
		border-bottom-width: thin;
		height: 40px;
		padding-top: 20px;
		padding-left: 5px;		
		color: #3d6e9f;
	}
	#accordion ul {
		list-style: none;
		padding: 0 0 0 0;
		display: none;
	}
	#accordion ul li{
		font-weight: normal;
		cursor: auto;
		background-color: #fff;
		padding: 0 0 0 7px;
		padding-top: 10px;
		padding-bottom: 5px;
	}
/* 	#accordion a { */
/* 		text-decoration: none; */
/* 	} */
/* 	#accordion a:hover { */
/* 		text-decoration: underline; */
/* 	} */

</STYLE>

<script type="text/javascript">
	function student(){
		
		$("#index_cont").html("<div width=\"auto\" align=\"center\"><img src=\"../images/loader.gif\" width=\"100px\"/></div>");
		
		$.ajax({
			type : "post",
			url : "studentaction.action",				
			data : "",
			async : true,
			success : function(response) {
				alert("test");
				$('#index_cont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function teacher(){
		
		$("#index_cont").html("<div width=\"auto\" align=\"center\"><img src=\"images/loader.gif\" width=\"100px\"/></div>");
		$.ajax({
			type : "POST",
			url : "teacher",				
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
		
		$("#index_cont").html("<div width=\"auto\" align=\"center\"><img src=\"images/loader.gif\" width=\"100px\"/></div>");
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
		$("#index_cont").html("<div width=\"auto\" align=\"center\"><img src=\"images/loader.gif\" width=\"100px\"/></div>");
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
		$("#index_cont").html("<div width=\"auto\" align=\"center\"><img src=\"images/loader.gif\" width=\"100px\"/></div>");
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
		$("#index_cont").html("<div width=\"auto\" align=\"center\"><img src=\"images/loader.gif\" width=\"100px\"/></div>");
		$.ajax({
			type : "POST",
			url : "preexam.action",				
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

<style type="text/css">
	a{
		text-decoration: none; 
		color: #000000;
		display: block;
	}
	a:hover{
		color: '#8BD948';
	}
	a>img{
		vertical-align: top;
		display: inline-block;
	}
	a>span{
		margin: 7px 0px 0px 7px;
		display: inline-block;
	}
</style>

</head>
<body style="margin-top: 0px; background-color: #000;" >
	
	<s:iterator status="stat" value="usrRoleList">
	
		<s:set var="mname" value="usrRoleList[#stat.index].m_name"/>
				
		<c:choose>
			<c:when test="${mname == \"Student\"}"> <c:set var="stud_tab" value="1"/> </c:when>
			<c:when test="${mname == \"Teacher\"}"> <c:set var="teach_tab" value="1"/> </c:when>
			<c:when test="${mname == \"Non_Teacher\"}"> <c:set var="nonteach_tab" value="1"/> </c:when>
			<c:when test="${mname == \"Subject and Exam\"}"> <c:set var="sub_tab" value="1"/> </c:when>
			
			<c:when test="${mname == \"Class\"}"> <c:set var="class_tab" value="1"/> </c:when>
			<c:when test="${mname == \"Exam Result\"}"> <c:set var="examr_tab" value="1"/> </c:when>
			<c:when test="${mname == \"Exam Question\"}"> <c:set var="examq_tab" value="1"/> </c:when>
			<c:when test="${mname == \"Payment and Fin\"}"> <c:set var="pay_tab" value="1"/> </c:when>
			
			<c:when test="${mname == \"Report\"}"> <c:set var="report_tab" value="1"/> </c:when>
			<c:when test="${mname == \"Admin\"}"> <c:set var="admin_tab" value="1"/> </c:when>
			<c:when test="${mname == \"Class Schedule\"}"> <c:set var="classSch_tab" value="1"/> </c:when>
			<c:when test="${mname == \"Attendance\"}"> <c:set var="attendance_tab" value="1"/> </c:when>
			
			<c:when test="${mname == \"Co-curriculum Activity\"}"> <c:set var="cocuract_tab" value="1"/> </c:when>
			<c:when test="${mname == \"Behavioural Evaluation\"}"> <c:set var="beval_tab" value="1"/> </c:when>
			<c:when test="${mname == \"Email\"}"> <c:set var="email_tab" value="1"/> </c:when>
		</c:choose>
									
	</s:iterator>
	
	<div align="center" class="logo_background">
		<div style=" width: auto; vertical-align: middle; font-size: 12px; font-weight: bold;">
			<img alt="logo" src="images/fidel_logo.png" width="46px"><br/>
			<span style="color:  #3d6e9f; font-size: 24px; font-weight: bold;">FIDEL</span>
			<br/>SCHOOL MANAGEMENT <br/>SYSTEM
		</div>
	</div>
	<div align="center" style="border-top-color: #3d6e9f; border-top-style: solid; border-top-width: thin;">
		<img alt="" src="images/down.png" width="40px" height="10px">
	</div>
	
	<s:set var="utid" value="usrRoleList[0].utId"/>
	<div style="margin-top: 0px;">
		<ul id="accordion">
			<li style="text-align: left;">RECORDS</li>
			<ul>		
				<li>				
					<c:choose>
						<c:when test="${stud_tab == 1}">
							<a href="/<s:text name='global.sysname'/>/student.action">
								<img alt="" src="images/student.png" width="30px"><span> Student </span>								
							</a>
						</c:when>
						<c:otherwise>
							<div style="width: auto; height: auto; color: silver">
								<table><tr><td><img alt="" src="images/student_dim.png" width="30px"></td><td> Student </td></tr></table>
							</div>
						</c:otherwise>
					</c:choose>		
				</li>
				<li>
					<c:choose>
						<c:when test="${teach_tab == 1}">
							<a href="/<s:text name='global.sysname'/>/teacher.action" style="text-decoration: none; color: #000000">
								<img alt="" src="images/teachers.png" width="30px"> <span> Teacher </span>
							</a>
						</c:when>
						<c:otherwise>
							<div style="width: auto; height: auto; color: silver">
								<table><tr><td> <img alt="" src="images/teachers_dim.png" width="30px"> </td><td> Teacher </td></tr></table>
							</div>
						</c:otherwise>
					</c:choose>									
				</li>
				<li>
					<c:choose>
						<c:when test="${nonteach_tab == 1}">
							<a href="/<s:text name='global.sysname'/>/nonteacher.action" style="text-decoration: none; color: #000000">
								<img alt="" src="images/teachers.png" width="30px"> <span> Non-Academic </span>
							</a>
						</c:when>
						<c:otherwise>
							<div style="width: auto; height: auto; color: silver">
								<table><tr><td> <img alt="" src="images/teachers_dim.png" width="30px"> </td><td> Non-Academic </td></tr></table>
							</div>
						</c:otherwise>
					</c:choose>									
				</li>				
			</ul>
			<li style="text-align: left;">TEACHERS ACTIVITY</li>
			<ul>
				<li>
					<c:choose>
						<c:when test="${attendance_tab == 1}">
							<a href="/<s:text name='global.sysname'/>/attendance.action" style="text-decoration: none; color: #000000">
								<img alt="" src="images/daily_activity.png" width="30px"> <span> Attendance </span>
							</a>
						</c:when>
						<c:otherwise>
							<div style="width: auto; height: auto; color: silver" >
								<table><tr><td> <img alt="" src="images/daily_activity_dim.png" width="30px"> </td><td> Attendance </td></tr></table>
							</div>
						</c:otherwise>
					</c:choose>	
				</li>
				<li>
					<c:choose>
						<c:when test="${examr_tab == 1}">
							<a href="/<s:text name='global.sysname'/>/exam.action" style="text-decoration: none; color: #000000">
								<img alt="" src="images/exam.png" width="30px"> <span> Exam Result </span>
							</a>
						</c:when>
						<c:otherwise>
							<div style="width: auto; height: auto; color: silver">
								<table><tr><td> <img alt="" src="images/exam_dim.png" width="30px"> </td><td> Exam Result </td></tr></table>
							</div>
						</c:otherwise>
					</c:choose>
				</li>
				<li>
					<c:choose>
						<c:when test="${beval_tab == 1}">
							<a href="/<s:text name='global.sysname'/>/beheval.action" style="text-decoration: none; color: #000000">
								<img alt="" src="images/exam.png" width="30px"> <span> Behavioral Eval. </span>
							</a>
						</c:when>
						<c:otherwise>
							<div style="width: auto; height: auto; color: silver; text-align: left;">
								<table><tr><td> <img alt="" src="images/exam_dim.png" width="30px"> </td><td> Behavioral Eval. </td></tr></table>
							</div>
						</c:otherwise>
					</c:choose>
				</li>
				<li>
					<c:choose>
						<c:when test="${cocuract_tab == 1}">
							<a href="/<s:text name='global.sysname'/>/cocuract.action" style="text-decoration: none; color: #000000">
								<img alt="" src="images/exam_pre.png" width="30px"> <span> Co-curriculum Act. </span>
							</a>
						</c:when>
						<c:otherwise>
							<div style="width: auto; height: auto; color: silver; text-align: left;" >
								<table><tr><td> <img alt="" src="images/exam_pre_dim.png" width="30px"> </td><td> Co-curriculum Act. </td></tr></table>
							</div>
						</c:otherwise>
					</c:choose>
				</li>
<!-- 				<li> -->
<%-- 					<s:if test="%{#examq_tab == 1}"> --%>
<%-- 						<a href="/<s:text name='global.sysname'/>/preexam.action" style="text-decoration: none; color: #000000"> --%>
<!-- 							<div style="width: auto; height: auto;" onmouseover="this.style.color = '#8BD948'" onmouseout="this.style.color = '#000000'">								  -->
<!-- 								<table><tr><td> <img alt="" src="images/exam_pre.png" width="30px"> </td><td> Exam Question </td></tr></table> -->
<!-- 							</div> -->
<!-- 						</a> -->
<%-- 					</s:if> --%>
<%-- 					<s:else> --%>
<!-- 						<div style="width: auto; height: auto; color: silver" > -->
<!-- 							<table><tr><td> <img alt="" src="images/exam_pre_dim.png" width="30px"> </td><td> Exam Question </td></tr></table> -->
<!-- 						</div> -->
<%-- 					</s:else> --%>
<!-- 				</li> -->
			</ul>
			<li style="text-align: left;">FINANCE</li>
			<ul>
				<li>
					<c:choose>
						<c:when test="${pay_tab == 1}">
							<a href="/<s:text name='global.sysname'/>/pay.action" style="text-decoration: none; color: #000000">
								<img alt="" src="images/payment.png" width="30px"> <span> Payment and Fin </span>
							</a>
						</c:when>
						<c:otherwise>
							<div style="width: auto; height: auto; color: silver">
								<table><tr><td> <img alt="" src="images/payment_dim.png" width="30px"> </td><td> Payment and Fin </td></tr></table>
							</div>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
			<li style="text-align: left;">BASIC INFORMATION</li>
			<ul>
				<li>
					<c:choose>
						<c:when test="${sub_tab == 1}">
							<a href="/<s:text name='global.sysname'/>/subject.action" style="text-decoration: none; color: #000000">
								<img alt="" src="images/subject.png" width="30px"> <span> Subject/Exam </span>
							</a>
						</c:when>
						<c:otherwise>
							<div style="width: auto; height: auto; color: silver">
								<table><tr><td> <img alt="" src="images/subject_dim.png" width="30px"> </td><td> Subject/Exam </td></tr></table>
							</div>
						</c:otherwise>
					</c:choose>
				</li>
				<li>
					<c:choose>
						<c:when test="${class_tab == 1}">
							<a href="/<s:text name='global.sysname'/>/class.action" style="text-decoration: none; color: #000000">
								<img alt="" src="images/classroom.png" width="30px"> <span> Class </span>
							</a>
						</c:when>
						<c:otherwise>
							<div style="width: auto; height: auto; color: silver" >
								<table><tr><td> <img alt="" src="images/classroom_dim.png" width="30px"> </td><td> Class </td></tr></table>
							</div>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
			<li style="text-align: left;">REPORTS</li>
			<ul>
				<li>
					<c:choose>
						<c:when test="${report_tab == 1}">
							<a href="/<s:text name='global.sysname'/>/rpt.action" style="text-decoration: none; color: #000000">
								<img alt="" src="images/report.png" width="30px"> <span> Report </span>
							</a>
						</c:when>
						<c:otherwise>
							<div style="width: auto; height: auto; color: silver">
								<table><tr><td> <img alt="" src="images/report_dim.png" width="30px"> </td><td> Report </td></tr></table>
							</div>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
			<li style="text-align: left;">COMMUNICATION</li>
			<ul>
				<li>
					<c:choose>
						<c:when test="${email_tab == 1}">
							<a href="/<s:text name='global.sysname'/>/email.action" style="text-decoration: none; color: #000000">
								<img alt="" src="images/email.png" width="30px"> <span> E-mail </span>
							</a>
						</c:when>
						<c:otherwise>
							<div style="width: auto; height: auto; color: silver">
								<table><tr><td> <img alt="" src="images/email_dim.png" width="30px"> </td><td> E-mail </td></tr></table>
							</div>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
			<li style="text-align: left;">MANAGE</li>
			<ul>
				<li>
					<c:choose>
						<c:when test="${classSch_tab == 1}">
							<a href="/<s:text name='global.sysname'/>/class_schedule.action" style="text-decoration: none; color: #000000">
								<img alt="" src="images/daily_activity.png" width="30px"> <span> Class Schedule </span>
							</a>
						</c:when>
						<c:otherwise>
							<div style="width: auto; height: auto; color: silver" >
								<table><tr><td> <img alt="" src="images/daily_activity_dim.png" width="30px"> </td><td> Class Schedule </td></tr></table>
							</div>
						</c:otherwise>
					</c:choose>
				</li>
				<li>
					<c:choose>
						<c:when test="${admin_tab == 1}">
							<a href="/<s:text name='global.sysname'/>/admin.action" style="text-decoration: none; color: #000000">
								<img alt="" src="images/admin.png" width="30px"> <span> Admin Data </span>
							</a>
						</c:when>
						<c:otherwise>
							<div style="width: auto; height: auto; color: silver">
								<table><tr><td> <img alt="" src="images/admin_dim.png" width="30px"> </td><td> Admin Data </td></tr></table>
							</div>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
		</ul>			
	</div>
	
	<SCRIPT type="text/javascript">
	
	$("#accordion > li").click(function(){
	
		if(false == $(this).next().is(':visible')) {
			$('#accordion > ul').slideUp(300);
		}
		$(this).next().slideToggle(300);
	});
	
	//$('#accordion > ul:eq(0)').show();
	
	</SCRIPT>

<%-- <div style="background-color: #e5e5e5;">
		<s:set var="utid" value="usrRoleList[0].utId"/>
		
		<table cellpadding="0" cellspacing="0" width="100%" border="0">
			
			<s:if test="%{#stud_tab == 1}">
				<tr>
					<td height="50px" style=" cursor: pointer; padding-left: 5px">					 
						<s:a href="/SMS_MarkInt/student.action" style="text-decoration: none; color: #000000">
							<s:bean name="utid" var="ui_id">
								<s:param name="ut_id" value="%{#utid}"/>
							</s:bean>
							<div style="width: auto; height: auto;" onmouseover="this.style.color = '#ffffff'" onmouseout="this.style.color = '#000000'">
								<table><tr><td><img alt="" src="images/student.png" width="30px" style="padding-top: 5px;"></td><td style="padding-top: 10px;"> Student </td></tr></table>
							</div>
						</s:a>						
					</td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td height="50px" style=" cursor: pointer; padding-left: 5px">
						<div style="width: auto; height: auto; color: silver">
							<table><tr><td><img alt="" src="images/student_dim.png" width="30px" style="padding-top: 5px;"></td><td style="padding-top: 10px;"> Student </td></tr></table>
						</div>
					</td>
				</tr>
			</s:else>
			
			<s:if test="%{#teach_tab == 1}">
				<tr>
					<td height="50px" style="border-top-style: solid; border-top-width: thin; border-color: #ffffff; cursor: pointer; padding-left: 5px">
						<a href="/SMS_MarkInt/teacher.action" style="text-decoration: none; color: #000000">
							<div style="width: auto; height: auto;" onmouseover="this.style.color = '#ffffff'" onmouseout="this.style.color = '#000000'">								
								<table><tr><td> <img alt="" src="images/teachers.png" width="30px" style="padding-top: 5px"> </td><td style="padding-top: 10px;"> Teacher </td></tr></table>
							</div>
						</a>
					</td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td height="50px" style="border-top-style: solid; border-top-width: thin; border-color: #ffffff; cursor: pointer; padding-left: 5px">
						<div style="width: auto; height: auto; color: silver">
							<table><tr><td> <img alt="" src="images/teachers_dim.png" width="30px" style="padding-top: 5px"> </td><td style="padding-top: 10px;"> Teacher </td></tr></table>
						</div>
					</td>
				</tr>
			</s:else>
			
			<s:if test="%{#sub_tab == 1}">
				<tr>
					<td height="50px" style="border-top-style: solid; border-top-width: thin; border-color: #ffffff; cursor: pointer; padding-left: 5px">
						<a href="/SMS_MarkInt/subject.action" style="text-decoration: none; color: #000000">
							<div style="width: auto; height: auto;" onmouseover="this.style.color = '#ffffff'" onmouseout="this.style.color = '#000000'">								 
								<table><tr><td> <img alt="" src="images/subject.png" width="30px" style="padding-top: 5px"> </td><td style="padding-top: 10px;"> Subject/Exam </td></tr></table>
							</div>
						</a>
					</td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td height="50px" style="border-top-style: solid; border-top-width: thin; border-color: #ffffff; cursor: pointer; padding-left: 5px">
						<div style="width: auto; height: auto; color: silver">
							<table><tr><td> <img alt="" src="images/subject_dim.png" width="30px" style="padding-top: 5px"> </td><td style="padding-top: 10px;"> Subject/Exam </td></tr></table>
						</div>
					</td>
				</tr>
			</s:else>
			
			<s:if test="%{#class_tab == 1}">
				<tr>
					<td height="50px" style="border-top-style: solid; border-top-width: thin; border-color: #ffffff; cursor: pointer; padding-left: 5px">
						<a href="/SMS_MarkInt/class.action" style="text-decoration: none; color: #000000">
							<div style="width: auto; height: auto;" onmouseover="this.style.color = '#ffffff'" onmouseout="this.style.color = '#000000'">								 
								<table><tr><td> <img alt="" src="images/classroom.png" width="30px" style="padding-top: 5px"> </td><td style="padding-top: 10px;"> Class </td></tr></table>
							</div>
						</a>
					</td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td height="50px" style="border-top-style: solid; border-top-width: thin; border-color: #ffffff; cursor: pointer; padding-left: 5px">
						<div style="width: auto; height: auto; color: silver" >
							<table><tr><td> <img alt="" src="images/classroom_dim.png" width="30px" style="padding-top: 5px"> </td><td style="padding-top: 10px;"> Class </td></tr></table>
						</div>
					</td>
				</tr>
			</s:else>
			
			<s:if test="%{#classSch_tab == 1}">
				<tr>
					<td height="50px" style="border-top-style: solid; border-top-width: thin; border-color: #ffffff; cursor: pointer; padding-left: 5px">
						<a href="/SMS_MarkInt/class_schedule.action" style="text-decoration: none; color: #000000">
							<div style="width: auto; height: auto;" onmouseover="this.style.color = '#ffffff'" onmouseout="this.style.color = '#000000'">								 
								<table><tr><td> <img alt="" src="images/daily_activity.png" width="30px" style="padding-top: 5px"> </td><td style="padding-top: 10px;"> Class Schedule </td></tr></table>
							</div>
						</a>
					</td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td height="50px" style="border-top-style: solid; border-top-width: thin; border-color: #ffffff; cursor: pointer; padding-left: 5px">
						<div style="width: auto; height: auto; color: silver" >
							<table><tr><td> <img alt="" src="images/daily_activity_dim.png" width="30px" style="padding-top: 5px"> </td><td style="padding-top: 10px;"> Class Schedule </td></tr></table>
						</div>
					</td>
				</tr>
			</s:else>
			
			<s:if test="%{#attendance_tab == 1}">
				<tr>
					<td height="50px" style="border-top-style: solid; border-top-width: thin; border-color: #ffffff; cursor: pointer; padding-left: 5px">
						<a href="/SMS_MarkInt/attendance.action" style="text-decoration: none; color: #000000">
							<div style="width: auto; height: auto;" onmouseover="this.style.color = '#ffffff'" onmouseout="this.style.color = '#000000'">								 
								<table><tr><td> <img alt="" src="images/daily_activity.png" width="30px" style="padding-top: 5px"> </td><td style="padding-top: 10px;"> Attendance </td></tr></table>
							</div>
						</a>
					</td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td height="50px" style="border-top-style: solid; border-top-width: thin; border-color: #ffffff; cursor: pointer; padding-left: 5px">
						<div style="width: auto; height: auto; color: silver" >
							<table><tr><td> <img alt="" src="images/daily_activity_dim.png" width="30px" style="padding-top: 5px"> </td><td style="padding-top: 10px;"> Attendance </td></tr></table>
						</div>
					</td>
				</tr>
			</s:else>
			
			<s:if test="%{#examr_tab == 1}">
				<tr>
					<td height="50px" style="border-top-style: solid; border-top-width: thin; border-color: #ffffff; cursor: pointer; padding-left: 5px">
						<a href="/SMS_MarkInt/exam.action" style="text-decoration: none; color: #000000">
							<div style="width: auto; height: auto;" onmouseover="this.style.color = '#ffffff'" onmouseout="this.style.color = '#000000'">								 
								<table><tr><td> <img alt="" src="images/exam.png" width="30px" style="padding-top: 5px"> </td><td style="padding-top: 10px;"> Exam Result </td></tr></table>
							</div>
						</a>
					</td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td height="50px" style="border-top-style: solid; border-top-width: thin; border-color: #ffffff; cursor: pointer; padding-left: 5px">
						<div style="width: auto; height: auto; color: silver">
							<table><tr><td> <img alt="" src="images/exam_dim.png" width="30px" style="padding-top: 5px"> </td><td style="padding-top: 10px;"> Exam Result </td></tr></table>
						</div>
					</td>
				</tr>
			</s:else>
			
			<s:if test="%{#examq_tab == 1}">
				<tr>
					<td height="50px" style="border-top-style: solid; border-top-width: thin; border-color: #ffffff; cursor: pointer; padding-left: 5px">
						<a href="/SMS_MarkInt/preexam.action" style="text-decoration: none; color: #000000">
							<div style="width: auto; height: auto;" onmouseover="this.style.color = '#ffffff'" onmouseout="this.style.color = '#000000'">								 
								<table><tr><td> <img alt="" src="images/exam_pre.png" width="30px" style="padding-top: 5px"> </td><td style="padding-top: 10px;"> Exam Question </td></tr></table>
							</div>
						</a>					
					</td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td height="50px" style="border-top-style: solid; border-top-width: thin; border-color: #ffffff; cursor: pointer; padding-left: 5px">
						<div style="width: auto; height: auto; color: silver" >
							<table><tr><td> <img alt="" src="images/exam_pre_dim.png" width="30px" style="padding-top: 5px"> </td><td style="padding-top: 10px;"> Exam Question </td></tr></table>
						</div>				
					</td>
				</tr>
			</s:else>
			
			<s:if test="%{#cocuract_tab == 1}">
				<tr>
					<td height="50px" style="border-top-style: solid; border-top-width: thin; border-color: #ffffff; cursor: pointer; padding-left: 5px">
						<a href="/SMS_MarkInt/cocuract.action" style="text-decoration: none; color: #000000">
							<div style="width: auto; height: auto;" onmouseover="this.style.color = '#ffffff'" onmouseout="this.style.color = '#000000'">								 
								<table><tr><td> <img alt="" src="images/exam_pre.png" width="30px" style="padding-top: 5px"> </td><td style="padding-top: 10px;"> Co-curriculum Activity </td></tr></table>
							</div>
						</a>					
					</td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td height="50px" style="border-top-style: solid; border-top-width: thin; border-color: #ffffff; cursor: pointer; padding-left: 5px">
						<div style="width: auto; height: auto; color: silver" >
							<table><tr><td> <img alt="" src="images/exam_pre_dim.png" width="30px" style="padding-top: 5px"> </td><td style="padding-top: 10px;"> Co-curriculum Activity </td></tr></table>
						</div>				
					</td>
				</tr>
			</s:else>
			
			<s:if test="%{#pay_tab == 1}">
				<tr>
					<td height="50px" style="border-top-style: solid; border-top-width: thin; border-color: #ffffff; cursor: pointer; padding-left: 5px">
						<a href="/SMS_MarkInt/pay.action" style="text-decoration: none; color: #000000">
							<div style="width: auto; height: auto;" onmouseover="this.style.color = '#ffffff'" onmouseout="this.style.color = '#000000'">								 
								<table><tr><td> <img alt="" src="images/payment.png" width="30px" style="padding-top: 5px"> </td><td style="padding-top: 10px;"> Payment and Fin </td></tr></table>
							</div>
						</a>
					</td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td height="50px" style="border-top-style: solid; border-top-width: thin; border-color: #ffffff; cursor: pointer; padding-left: 5px">
						<div style="width: auto; height: auto; color: silver">
							<table><tr><td> <img alt="" src="images/payment_dim.png" width="30px" style="padding-top: 5px"> </td><td style="padding-top: 10px;"> Payment and Fin </td></tr></table>
						</div>
					</td>
				</tr>
			</s:else>
			
			<s:if test="%{#report_tab == 1}">
				<tr>
					<td height="50px" style="border-top-style: solid; border-top-width: thin; border-color: #ffffff; cursor: pointer; padding-left: 5px">
						<a href="/SMS_MarkInt/rpt.action" style="text-decoration: none; color: #000000">
							<div style="width: auto; height: auto;" onmouseover="this.style.color = '#ffffff'" onmouseout="this.style.color = '#000000'">								 
								<table><tr><td> <img alt="" src="images/report.png" width="30px" style="padding-top: 5px"> </td><td style="padding-top: 10px;"> Report </td></tr></table>
							</div>
						</a>
					</td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td height="50px" style="border-top-style: solid; border-top-width: thin; border-color: #ffffff; cursor: pointer; padding-left: 5px">
						<div style="width: auto; height: auto; color: silver">
							<table><tr><td> <img alt="" src="images/report_dim.png" width="30px" style="padding-top: 5px"> </td><td style="padding-top: 10px;"> Report </td></tr></table>
						</div>
					</td>
				</tr>
			</s:else>
			
			<s:if test="%{#admin_tab == 1}">
				<tr>
					<td height="50px" style="border-bottom-style: solid; border-top-style: solid; border-bottom-width: thin; border-top-width: thin; border-color: #ffffff; cursor: pointer;  padding-left: 5px">
						<a href="/SMS_MarkInt/admin.action" style="text-decoration: none; color: #000000">
							<div style="width: auto; height: auto;" onmouseover="this.style.color = '#ffffff'" onmouseout="this.style.color = '#000000'">								 
								<table><tr><td> <img alt="" src="images/admin.png" width="30px" style="padding-top: 5px"> </td><td style="padding-top: 10px;"> Admin </td></tr></table>
							</div>
						</a>
					</td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td height="50px" style="border-bottom-style: solid; border-top-style: solid; border-bottom-width: thin; border-top-width: thin; border-color: #ffffff; cursor: pointer;  padding-left: 5px">
						<div style="width: auto; height: auto; color: silver">
							<table><tr><td> <img alt="" src="images/admin_dim.png" width="30px" style="padding-top: 5px"> </td><td style="padding-top: 10px;"> Admin </td></tr></table>
						</div>
					</td>
				</tr>
			</s:else>
			
		</table>
	</div> --%>
</body>
</html>