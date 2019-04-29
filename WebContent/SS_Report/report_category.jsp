<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<link rel="stylesheet" type="text/css" href="css/sms_css.css">
<script src="js/jquery-1.6.1.js" type="text/javascript"></script>
<script type="text/javascript" src="js/sms_report.js" ></script>

</head>
<body>

	<table cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<table width="100%" style="border-bottom-style: solid; border-bottom-width: thin; border-bottom-color: #3d6e9f;" cellpadding="0" cellspacing="0"><tr><td>
				
					<table cellpadding="0" cellspacing="0" >
						<tr>
							<td>&nbsp;</td>
							<td width="140px" height="25px" align="center">
								<div id="stud" class="header_btn_inactive" style="width: 100%; background-color: #e5e5e5;" onclick="studentReport()">
									Student
								</div>
							</td>							
							<td>&nbsp;</td>							
							<td width="140px" height="25px" align="center">
								<div id="exam" class="header_btn_inactive" style="width: 100%; background-color: #e5e5e5;" onclick="examReport()">
									Examination
								</div>
							</td>
							<td>&nbsp;</td>
							<td width="140px" height="25px" align="center">
								<div id="eduoff" class="header_btn_inactive" style="width: 100%; background-color: #e5e5e5;" onclick="eduOfficeReport()">
									Education Office
								</div>
							</td>
							<td>&nbsp;</td>
							<td width="140px" height="25px" align="center">
								<div id="finance" class="header_btn_inactive" style="width: 100%; background-color: #e5e5e5;" onclick="financeReport()">
									Finance
								</div>
							</td>							
							<td>&nbsp;</td>
							<td width="140px" height="25px" align="center">
								<div id="format" class="header_btn_inactive" style="width: 100%; background-color: #e5e5e5;" onclick="formatReport()">
									Formats
								</div>
							</td>						
							<td>&nbsp;</td>												
						</tr>
					</table>
				
				</td></tr></table>
			</td>
		</tr>
		<tr>
			<td>
				<div id="stud_report_titles" style="padding-top:5px; display: none; height: 30px; border-bottom: thin; border-bottom-color: #3d6e9f; border-bottom-style: solid; border-bottom-width: thin;"> 
					<table><tr>
					<td> <div onclick="getEachClassQuarterMarkList()" style="cursor: pointer;">Quarter Mark List</div></td><td width="5px;" align="center">|</td>
					<td> <div onclick="getStudentsRoster()" style="cursor: pointer;">Students Roster</div></td><td width="5px;" align="center">|</td>
					<td> <div onclick="getStudentsRosterFullYear()" style="cursor: pointer;">Students Roster (Full Year)</div></td><td width="5px;" align="center">|</td>
					<td> <div onclick="getStudentsTranscript()" style="cursor: pointer;">Students Transcript</div></td><td width="5px;" align="center">|</td>
					<td> <div onclick="getTopNsStudPerClass()" style="cursor: pointer;">Top N Ranking Students</div></td><td width="5px;" align="center">|</td>
					<td> <div onclick="getBelowAverageStudentsReport()" style="cursor: pointer;">Below Average Students</div></td><td width="5px;" align="center">|</td>
<!-- 					<td> <div onclick="getDropoutStudents()" style="cursor: pointer;">Drop out Student</div></td><td width="5px;" align="center">|</td> -->
					</tr></table> 
				</div>
				<div id="exam_report_titles" style="padding-top:5px; display: none; height: 30px; border-bottom: thin; border-bottom-color: #3d6e9f; border-bottom-style: solid; border-bottom-width: thin;"> 
					<table><tr>
					<td> <div onclick="getCheckInsertedExamResult()" style="cursor: pointer;">Check Inserted Exam Result</div></td><td width="5px;" align="center">|</td>
					<td> <div onclick="getExamProgram()" style="cursor: pointer;">Exam Program</div></td><td width="5px;" align="center">|</td>
					<td> <div onclick="getExamSchedule()" style="cursor: pointer;">Exam Schedule</div></td><td width="5px;" align="center">|</td>					
					</tr></table> 
				</div>
				<div id="eduoff_report_titles" style="padding-top:5px; display: none; height: 30px; border-bottom: thin; border-bottom-color: #3d6e9f; border-bottom-style: solid; border-bottom-width: thin;"> 
					<table><tr>
					<td> <div onclick="getStudMarkPlanAndImp()" style="cursor: pointer;">Student Mark Plan and Implementation</div></td><td width="5px;" align="center">|</td>
					<td> <div onclick="getStudMarkPerSemester()" style="cursor: pointer;">Student Mark Per Semester</div></td><td width="5px;" align="center">|</td>
					</tr></table>
				</div>
				<div id="format_titles" style="padding-top:5px; display: none; height: 30px; border-bottom: thin; border-bottom-color: #3d6e9f; border-bottom-style: solid; border-bottom-width: thin;"> 
					<table><tr>
					<td> <div onclick="getStudentAttendanceFormat()" style="cursor: pointer;">Attendance Format</div></td><td width="5px;" align="center">|</td>
					<td> <div onclick="getStudMarkListFormat()" style="cursor: pointer;">Student Mark List Format</div></td><td width="5px;" align="center">|</td>
					<td> <div style="cursor: pointer;"> Student Leave Certificate </div></td><td width="5px;" align="center">|</td>
					</tr></table>
				</div>
				<div id="finance_report_titles" style="padding-top:5px; display: none; height: 30px; border-bottom: thin; border-bottom-color: #3d6e9f; border-bottom-style: solid; border-bottom-width: thin;"> 
					<table><tr>
					<td> <div onclick="getUnpaidStudentList()" style="cursor: pointer;">Unpaid Student</div></td><td width="5px;" align="center">|</td>
					<td> <div onclick="getPaymentCollection()" style="cursor: pointer;">Payment Collections</div></td><td width="5px;" align="center">|</td>					
					</tr></table>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div id="infoList" class="content_background" style="min-height: 395px" style="padding-top: 10px;">
					<div id="report">
					
					</div>					
				</div>
			</td>
		</tr>
	</table>

</body>
</html>