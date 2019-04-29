<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<style type="text/css">
.studInfo{
	border-bottom: thin; 
	border-bottom-color: black; 
	border-bottom-style: solid; 
	border-bottom-width: thin; 
	width: 100%; 
	text-align: left;
}
.blueColor{
	color: rgb(9, 180, 224);
}
.redColor{
	color: rgb(216, 51, 47);
}
</style>

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
                $('#annualRptCardCover').dataTable( {
                    "sDom": 'T<"clear">rt'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

<table width="100%" cellpadding="0" cellspacing="0" id="annualRptCardCover"><thead><tr><td>

	<table width="100%" cellpadding="0" cellspacing="0" height="18cm">
		<tr>
			<td width="49%" valign="top" align="center" style="padding-top: 10px;">
			
				<div style="position: relative; min-height: 18cm;">
					<table width="90%" cellpadding="0" rules="rows" cellspacing="0" style="border:1px; border-color: black; border-style: solid; border-width:1px; ">
						<tr height="30px;" style="font-weight: bold;">
							<td align="center" rowspan="2">Attitude</td>
							<td align="center" colspan="4">Tutors Comments</td>
						</tr>
						<tr height="30px;" style="font-weight: bold;">
							<td align="center">1st Qrt.</td>
							<td align="center">2nd Qrt.</td>
							<td align="center">3rd Qrt.</td>
							<td align="center">4th Qrt.</td>
						</tr>
						<s:iterator status="stat" value="evalList">
							<tr style="height: 30px;">
								<td style="padding-left: 10px;"> <s:property value="evalList[#stat.index].bt_title"/> </td>
								<td align="center"> <s:property value="firstQuarterEvaluation[#stat.index].bg_name"/> </td>
								<td align="center"> <s:property value="secondQuarterEvaluation[#stat.index].bg_name"/> </td>
								<td align="center"> <s:property value="thirdQuarterEvaluation[#stat.index].bg_name"/> </td>
								<td align="center"> <s:property value="fourthQuarterEvaluation[#stat.index].bg_name"/> </td>
							</tr>
						</s:iterator>
						<tr>
							<td style="height: 40px; padding-left: 10px;"> Tutor Signature </td>
							<td colspan="4"> &nbsp; </td>
						</tr>
					</table>
					<br/>
					<table width="90%" cellpadding="0" rules="none" cellspacing="0" style="border: 1px; border-color: black; border-style: solid; border-width: 1px;">
						<tr>
							<td style="padding-left: 10px;">
								<b>Key:</b><br/> A - Excellent, B+ - V.Good, B - Good, C - Fair, D - Needs Improvement
							</td>
						</tr>
					</table>
					<br/>
					<table width="90%" style="border-color: black; border-style: solid; border-width: 1px;">
						<tr>
							<td rowspan="3" valign="top" style="font-weight: bold; padding-left: 10px;">
								Grade System
							</td>
							<td> 95% - 100% </td>
							<td> Excellent </td>
							<td> 85% - 94%</td>
							<td> Very Good </td>
						</tr>
						<tr>
							<td> 75% - 84%</td>
							<td> Good </td>
							<td> 70% - 74%</td>
							<td> Satisfactory </td>
						</tr>
						<tr>
							<td> 65% - 69%</td>
							<td> Pass </td>
							<td> Below 65%</td>
							<td> Fail </td>
						</tr>
					</table>
					
					<div style="font-size: 14px; bottom:0; position: absolute; text-align: center; width: 100%;">
						For more information: 0911-23-3983/0911-04-6063/0913-64-4602<br/>
						Fidel School Management System, Powered By YamGet IT Solutions Plc, <br/> +251 91 166 2766, +251 91 219 5853
					</div>
				</div>
				
			</td>
			<td>&nbsp;</td>
			<td width="49%" valign="top">
			
				<table cellpadding="0" cellspacing="0" width="100%" style="line-height: 20px;">
					<tr>
						<td align="center" style="font-size: 22px; font-weight: bold; padding-top: 20px;"> 
							MANA BARUMSAA <span class="redColor">YUUZ</span> <span class="blueColor">EVARAST</span>
						</td>
					</tr>
					<tr>
						<td align="center" style="font-size: 24px; font-weight: bold; padding-top: 5px;"> 
							<span class="blueColor">EVEREST</span> <span class="redColor">YOUTH</span> <span>ACADEMY</span>
						</td>
					</tr>
					<tr>
						<td align="center" style="font-size: 28px; font-weight: bold; padding-top: 5px;"> 
							<span class="blueColor">&#4772;&#4712;&#4648;&#4661;&#4725;</span> <span class="redColor">&#4841;&#4829;</span> &#4768;&#4779;&#4851;&#4634;
						</td>
					</tr>
					<tr>
						<td align="center" style="padding-top: 5px;"> 
							<s:text name='global.schooladdresscertificate'/> 
						</td>
					</tr>
					<tr>
						<td align="center" style="padding-top: 20px;"> 
							<img alt="schoolLogo" src="images/everest_logo.jpg" width="180px;"> 
						</td>
					</tr>
					<tr>
						<td style="font-size: 30px; font-weight: bold; text-align: center; padding-top: 20px;"> 
							STUDENT'S REPORT CARD
						</td>
					</tr>					
				</table>
				
				<div align="center" style="padding-top: 20px;">
					<table width="95%" style="line-height: 30px;">
						<tr>
							<td colspan="2"> 
								<div class="studInfo"> <b>Student's Name:</b> <s:property value="fname"/> <s:property value="mname"/> <s:set var="gname" value="gname"/> <s:if test="%{#gname != \"GrandFather\"}"><s:property value="gname"/></s:if> </div>
							</td>
						</tr>
						<tr>
							<td width="50%"> <div class="studInfo"> <b>Sex:</b> <s:set var="sex" value="stud_personal_info[0].sex"/> <s:if test="%{#sex == \"F\"}">Female</s:if><s:else>Male</s:else> </div> </td>
							<td> <div class="studInfo"> <b>Age:</b> <s:property value="stud.age"/> </div> </td>
						</tr>
						<tr>
							<td> <div class="studInfo"> <b>Place of Birth:</b> <s:property value="stud_personal_info[0].pob"/> </div> </td>
							<td> <div class="studInfo"> <b>Date of Birth:</b> <s:property value="stud_personal_info[0].dob"/> E.C </div> </td>														
						</tr>
						<tr>
							<td colspan="2"> <div class="studInfo"> <b>Grade/Section:</b> <s:property value="cl_name"/><s:property value="cd_name"/> </div> </td>							
						</tr>
						<tr>
							<td colspan="2"> <div class="studInfo"> <b>Academic Year:</b> <s:property value="academic_year"/> E.C </div> </td>
						</tr>
						<tr>
							<td colspan="2"> 
							<div class="studInfo"> <b>Result Status:</b> 
							<s:property value="stud.rslt_status"/> 
							</div>  
							</td>
						</tr>
						
						<tr>
							<td style="padding-top: 10px;"> <div class="studInfo"> <b>Principal Signature:</b>  &nbsp; </div> </td>
							<td style="padding-top: 10px;"> <div class="studInfo"> <b>Date:</b> <s:property value="cur_date"/> </div>  </td>
						</tr>
					</table>
				</div>
				<div style="font-weight: bold; font-size: 16px; text-align: right; padding-top: 5px; padding-right: 5px; width: 97%;">
					We Strive For Success!!!
				</div>
			</td>
		</tr>
	</table>
</td></tr></thead><tr><td></td></tr></table>
</body>
</html>