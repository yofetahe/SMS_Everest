<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script src="js/sms_system.js" type="text/javascript"></script>

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
                $('#qrtRptCard').dataTable( {
                    "sDom": 'T<"clear">rt'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

<s:set var="cl_id" value="cl_id"/>
<s:set var="cd_id" value="cd_id"/>
<s:set var="si_id" value="si_id"/>
<s:set var="at_id" value="at_id"/>
<s:set var="stud_rank" value="stud_rank"/>
<s:set var="academic_year" value="academic_year"/>

<s:set var="fname" value="fname"/>
<s:set var="mname" value="mname"/>
<s:set var="gname" value="gname"/>
<s:set var="cl_name" value="cl_name"/>
<s:set var="cd_name" value="cd_name"/>
<s:set var="homroom_teacher_name" value="homeroom_teacher_name"/>
<s:set var="at_name" value="quarterRsltView[0].at_name"/>
<s:set var="cur_date" value="cur_date"/>
<s:set var="erc_content" value="quarterTeacherComment[0].erc_content"/>
<s:set var="grand_quarter_total" value="grand_quarter_total"/>
<s:set var="average_quarter_mark" value="average_quarter_mark"/>


<div class="save_update_btn" onclick="emailStudentQuarterResultToParents('${cl_id}', '${cd_id}', '${si_id}', '${at_id}', '${stud_rank}', '${academic_year}', '${fname}', '${mname}', '${gname}', '${cl_name}', '${cd_name}', '${homroom_teacher_name}', '${at_name}', '${cur_date}', '${erc_content}', '${grand_quarter_total}', '${average_quarter_mark}')" style="height: 30px; width: 220px; padding-bottom: 5px; padding-left: 75px; cursor: pointer; text-align: center;">
	<table><tr><td>
		<img alt="email" src="images/email.png" height="30px">
	</td><td valign="middle">	
		Email to Parents
	</td></tr></table>
</div>

<div style="width: 100%; color: RED"> <s:actionmessage/> </div>

<table width="100%" cellpadding="0" cellspacing="0" id="qrtRptCard"><thead><tr><td>
	
	<table width="100%" cellpadding="0" cellspacing="0" style="padding-left: 5px; padding-right: 5px;">
		<tr>
			<td style="padding-bottom: 10px;" width="40%" align="left">
			
				<table cellpadding="0" cellspacing="0" align="left">
					<tr>
						<td width="17%" align="left" valign="top">
							<img alt="Everest" src="images/everest_logo.jpg" width="60px;">
						</td>
						<td width="80%">
						
							<table cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td align="center" style="font-size: 22px; font-weight: bold; height: 30px;"> <s:text name="global.schoolname"/> </td>
								</tr>
								<tr>
									<td align="center" style="font-weight: bold; height: 30px; font-size: 15px;"> P.O.Box 21628 Tel. +251-11-367 9030/11-367-9066 <br/> Alemgena, Ethiopia </td>
								</tr>
							</table>							  
							  
						</td>						
					</tr>
				</table>
				
			</td>
			<td>&nbsp;</td>
			<td width="56%" style="border-bottom: 2px; border-top: 2px; border-bottom-color: black; border-top-color: black; border-bottom-style: solid; border-top-style: solid; border-bottom-width: 2px; border-top-width: 2px;">
			
				<table width="100%" cellpadding="0" cellspacing="0" border="0" align="center">
					<tr> <td rowspan="4"> <img alt="photo" src="imageRetrieveServlet?si_id=${si_id}" width="70px;"> </td></tr>
					<tr style="height: 30px;">
						<td style="padding-left: 5px;" colspan="2"> <span style="font-weight: bold;"> Student's Name: </span> <span style="text-decoration: none;"> <s:property value="fname"/> <s:property value="mname"/> <s:if test="%{#gname != \"GrandFather\"}"> <s:property value="gname"/> </s:if> </span> </td>
					</tr><tr style="height: 30px;">	
						<td style="padding-left: 5px;"> <span style="font-weight: bold;"> Class: </span><span style="text-decoration: none;"> <s:property value="cl_name"/> <s:property value="cd_name"/> </span> </td>
						<td style="padding-left: 5px;"> <span style="font-weight: bold;"> Term: </span> <span style="text-decoration: none;"> <s:property value="at_name"/> </span> </td>
					</tr><tr style="height: 30px;">
						<td style="padding-left: 5px;"> <span style="font-weight: bold;"> Tutor's Name: </span><span style="text-decoration: none;"> <s:property value="homeroom_teacher_name"/> </span> </td>
						<td style="padding-left: 5px;"> <span style="font-weight: bold;"> Date: </span> <span style="text-decoration: none;"> <s:property value="cur_date"/> </span> </td>
					</tr>
				</table>
			
			</td>
		</tr>
		
		<tr>
			<td style="padding-top: 3px;" colspan="3">
			
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td width="40%" valign="top">
						
							<table width="100%" cellpadding="0" rules="rows" cellspacing="0" style="border:1px; border-color: black; border-style: solid; border-width:1px; ">
								<tr height="30px;" style="font-weight: bold;">
									<td align="center">Attitude</td>
									<td align="center">Tutors Comments</td>
								</tr>
								<s:iterator status="stat" value="quarterEvaluation">
									<tr style="min-height: 24px;">
										<td style="padding-left: 10px;"> <s:property value="quarterEvaluation[#stat.index].bt_title"/> </td>
										<td align="center"> <s:property value="quarterEvaluation[#stat.index].bg_name"/> </td>
									</tr>
								</s:iterator>
								<tr>
									<td style="height: 40px; padding-left: 10px;"> Tutor Signature </td>
									<td> &nbsp; </td>
								</tr>
							</table>
							<br/>
							<table width="100%" cellpadding="0" rules="none" cellspacing="0" style="border: 1px; border-color: black; border-style: solid; border-width: 1px;">
								<tr>
									<td style="padding-left: 10px;">
										Key:<br/> A - Excellent, B+ - V.Good, B - Good, C - Fair, D - Needs Improvement
									</td>
								</tr>
							</table>
							<br/>
							<table width="100%" style="border-color: black; border-style: solid; border-width: 1px; padding-left: 10px;">
								<tr>
									<td style="height: 60px;" valign="top">
										<span style="font-weight: bold;">Class Teacher's/Director's comment:</span><br/>
										<s:set var="edc_content" value="getDefaultComment[0].edc_content"/>
										<s:property value="%{#edc_content}"/>
										<s:set var="erc_content" value="quarterTeacherComment[0].erc_content"/>
										<s:property value="%{#erc_content}"/>
									</td>
								</tr>
								<tr><td>&nbsp;</td></tr>
								<tr>
									<td style="height: 60px;" valign="top">
										<span style="font-weight: bold;">Parent's comment:</span> <br/>
										
										<table cellpadding="0" cellspacing="0" rules="rows" width="100%" style="border-bottom: 1px; border-bottom-color: black; border-bottom-style: solid; border-bottom-width: 1px;">
											<tr style="height: 25px;"><td>&nbsp;</td></tr>
											<tr style="height: 25px;"><td>&nbsp;</td></tr>
											<tr style="height: 25px;"><td>&nbsp;</td></tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<br/>
										Parent Signature:________________________________
									</td>
								</tr>
							</table>
						
						</td>
						<td width="10px;">&nbsp;</td>
						<td align="center" width="56%" valign="top">
						
							<table width="100%" cellpadding="0" cellspacing="0" style="padding-bottom: 4px;">
								<tr><td style="font-size: 20px; font-weight: bold; height: 40px; text-decoration: none; border-color: black; border-style: solid; border-width: 1px; border-bottom-style: solid; border-top-style: solid; text-align: center;">
									Terminal Report For <s:if test="%{#cl_id >= \"9\"}"> High School </s:if><s:else> Grade Level </s:else>
								</td></tr>
							</table>
							
							<s:set var="at_id" value="quarterRsltView[0].at_id"/>
							<s:set var="grand_total" value="0"/>
							<s:set var="studentRank" value="stud_rank"/>
							
							<table width="100%" cellpadding="0" cellspacing="1" border="0" rules="rows" style="border: 1px; border-color: black; border-style: solid; border-width: 1px; padding-bottom: 14px;">
								<tr style="font-weight: bold;">
									<td colspan="4" align="center" style="border-right: thin; border-right-color: black; border-right-style: solid; border-right-width: thin;">
										<s:property value="at_name"/>
									</td>
									<s:if test="%{#at_id == \"2\"}">
										<td width="17%" valign="top" align="center" rowspan="2">1st Quarter <br/> Total Mark</td>
										<td valign="top" style="padding-left: 20px;" rowspan="2"> Change <br/> Status</td>
									</s:if>
									<s:if test="%{#at_id == \"3\"}">
										<td width="17%" valign="top" align="center" rowspan="2">1st Q. <br/> Total <br/> Mark</td>
										<td width="17%" valign="top" align="center" rowspan="2">2nd Q. <br/> Total <br/> Mark</td>
										<td width="21%" valign="top" align="center" rowspan="2" style="background-color: #f5f5f5;"> 1st &amp; 2nd Q. <br/> Average <br/> Mark </td>
										<td valign="top" style="padding-left: 20px;" rowspan="2"> <div align="center" style="width: 100%;"> Change <br/> Status </div> </td>
									</s:if>
								</tr>
								<tr style="font-weight: bold; height: 40px;">
									<td width="20%" style="padding-left: 10px;">Subject</td>
									<td width="10%" valign="top" align="center">Cumulative <br/>Assessment</td>
									<td width="15%" valign="top" align="center">Exam <br/>Mark</td>
									<td width="15%" valign="top" align="center" style="background-color: #f5f5f5;">Total Mark</td>
									<!-- <td valign="top" align="center">Subject Teachers <br/>Comment and signature</td> -->									
								</tr>
								<s:iterator status="stat" value="quarterRsltView">
									<tr>
										<td height="25px;" style="padding-left: 10px;">
											<s:set var="sub_id" value="quarterRsltView[#stat.index].sub_id"/>
											<s:property value="quarterRsltView[#stat.index].sub_name"/>
										</td>
										<td align="center">
											<s:property value="quarterRsltView[#stat.index].cummulative_mark"/>
										</td>
										<td align="center">
											<s:property value="quarterRsltView[#stat.index].final_exam_mark"/>
										</td>
										<td align="center" >
											<s:set var="con_to_grade" value="quarterRsltView[#stat.index].convert_to_grade"/>
											<s:if test="%{#con_to_grade == \"NO\"}">
												<s:set var="quarter_total" value="quarterRsltView[#stat.index].Quarter_total"/>
												<fmt:parseNumber var="qrt_total" value="${quarter_total}"/>
												<s:if test="%{#quarter_total < 65}"> <span style="color: RED; padding-left: 15px;"> <s:property value="%{#quarter_total}"/> * </span> </s:if>
												<s:else> <s:property value="%{#quarter_total}"/> </s:else>
											</s:if>
											<s:else>
												<s:property value="quarterRsltView[#stat.index].grading_result"/>
											</s:else>
										</td>
										<s:if test="%{#at_id == \"2\"}">
											<s:iterator status="st" value="firstQuarterRslt">
												<s:if test="%{#sub_id == firstQuarterRslt[#st.index].sub_id}">
													<td align="center">
														<s:set var="pre_con_to_grade" value="firstQuarterRslt[#st.index].convert_to_grade"/>
														<s:if test="%{#pre_con_to_grade == \"NO\"}">
															<s:set var="pre_quarter_total" value="firstQuarterRslt[#st.index].Quarter_total"/>
															<fmt:parseNumber var="pre_qrt_total" value="${pre_quarter_total}"/>
															<s:if test="%{#pre_quarter_total < 65}"> <span style="color: RED; padding-left: 15px;"> <s:property value="%{#pre_quarter_total}"/> * </span> </s:if>
															<s:else> <s:property value="%{#pre_quarter_total}"/> </s:else>
														</s:if>
														<s:else>
															<s:property value="firstQuarterRslt[#st.index].grading_result"/>
														</s:else>
													</td>
													<td>
														<s:if test="%{#pre_con_to_grade == \"NO\"}">																														
															<c:choose>
																<c:when test="${pre_qrt_total > qrt_total}"> 
																	<c:set var="dec_range" value="${pre_qrt_total - qrt_total}"/>
																	<img alt="V" src="images/decrease_images.jpeg" style="height: 20px; width: 20px; padding-left: 30px;"> <c:if test="${dec_range >= 10}"> <span style="color: RED;">(***)</span> </c:if> <c:if test="${dec_range < 10 && dec_range >= 5}"> <span style="color: RED;">(**)</span> </c:if> <c:if test="${dec_range < 5 && dec_range > 0}"> <span style="color: RED;">(*)</span> </c:if>
																</c:when>
																<c:when test="${pre_qrt_total < qrt_total}"> 
																	<img alt="V" src="images/up_images.jpeg" style="height: 20px; width: 30px; padding-left: 23px;"> 
																</c:when>
																<c:otherwise>  </c:otherwise>
															</c:choose>
														</s:if>
													</td>
												</s:if>												
											</s:iterator>											
										</s:if>
										<s:if test="%{#at_id == \"3\"}">
											<s:iterator status="st" value="firstQuarterRslt">
												<s:if test="%{#sub_id == firstQuarterRslt[#st.index].sub_id}">
													<td align="center">
														<s:set var="first_con_to_grade" value="firstQuarterRslt[#st.index].convert_to_grade"/>
														<s:if test="%{#first_con_to_grade == \"NO\"}">
															<s:set var="first_quarter_total" value="firstQuarterRslt[#st.index].Quarter_total"/>
															<fmt:parseNumber var="first_qrt_total" value="${first_quarter_total}"/>
															<s:if test="%{#first_quarter_total < 65}"> <span style="color: RED; padding-left: 15px;"> <s:property value="%{#first_quarter_total}"/> * </span> </s:if>
															<s:else> <s:property value="%{#first_quarter_total}"/> </s:else>
														</s:if>
														<s:else>
															<s:property value="firstQuarterRslt[#st.index].grading_result"/>
														</s:else>
													</td>
													<td align="center">
														<s:set var="second_con_to_grade" value="secondQuarterRslt[#st.index].convert_to_grade"/>
														<s:if test="%{#second_con_to_grade == \"NO\"}">
															<s:set var="second_quarter_total" value="secondQuarterRslt[#st.index].Quarter_total"/>
															<fmt:parseNumber var="second_qrt_total" value="${second_quarter_total}"/>
															<s:if test="%{#second_quarter_total < 65}"> <span style="color: RED; padding-left: 15px;"> <s:property value="%{#second_quarter_total}"/> * </span> </s:if>
															<s:else> <s:property value="%{#second_quarter_total}"/> </s:else>
														</s:if>
														<s:else>
															<s:property value="secondQuarterRslt[#st.index].grading_result"/>
														</s:else>
													</td>
													<td align="center" >
														<s:if test="%{#second_con_to_grade == \"NO\"}">
															${(first_qrt_total + second_qrt_total)/2}
														</s:if>
													</td>
													<td>
														<s:if test="%{#second_con_to_grade == \"NO\"}">																														
															<c:choose>
																<c:when test="${(first_qrt_total + second_qrt_total)/2 > qrt_total}">
																	<c:set var="avg_rslt" value="${(first_qrt_total + second_qrt_total)/2}"></c:set>
																	<c:set var="dec_range" value="${avg_rslt - qrt_total}"/>
																	<img alt="V" src="images/decrease_images.jpeg" style="height: 20px; width: 20px; padding-left: 10px;"> <c:if test="${dec_range >= 10}"> <span style="color: RED;">(***)</span> </c:if> <c:if test="${dec_range < 10 && dec_range >= 5}"> <span style="color: RED;">(**)</span> </c:if> <c:if test="${dec_range < 5 && dec_range > 0}"> <span style="color: RED;">(*)</span> </c:if>
																</c:when>
																<c:when test="${(first_qrt_total + second_qrt_total)/2 < qrt_total}"> 
																	<img alt="V" src="images/up_images.jpeg" style="height: 20px; width: 30px; padding-left: 3px;"> 
																</c:when>
																<c:otherwise>  </c:otherwise>
															</c:choose>
														</s:if>
													</td>
												</s:if>												
											</s:iterator>											
										</s:if>
									</tr>
								</s:iterator>
								<tr style="font-weight: bold;">
									<td colspan="3" height="25px;" style="padding-left: 10px;"> Sum </td>
									<td align="center"> <s:property value="grand_quarter_total"/> </td>
									<s:if test="%{#at_id == \"2\"}">
										<td> &nbsp; </td>
										<td> &nbsp; </td>
									</s:if>
									<s:if test="%{#at_id == \"3\"}">
										<td> &nbsp; </td>
										<td> &nbsp; </td>
										<td> &nbsp; </td>
										<td> &nbsp; </td>
									</s:if>
								</tr>
								<tr style="font-weight: bold;">
									<td colspan="3" height="25px;" style="padding-left: 10px;"> Average </td>
									<td align="center"> 
										<s:set var="average_quarter_mark" value="average_quarter_mark"/>
										<s:if test="%{#average_quarter_mark != \"NaN\"}">											
											<fmt:formatNumber pattern="#.##">${average_quarter_mark}</fmt:formatNumber>
										</s:if> 
									</td>
									<s:if test="%{#at_id == \"2\"}">
										<td> &nbsp; </td>
										<td> &nbsp; </td>
									</s:if>
									<s:if test="%{#at_id == \"3\"}">
										<td> &nbsp; </td>
										<td> &nbsp; </td>
										<td> &nbsp; </td>
										<td> &nbsp; </td>
									</s:if>
								</tr>
								<c:if test="${studentRank != ''}">
									<tr style="font-weight: bold;">
										<td colspan="3" height="25px;" style="padding-left: 10px;"> Rank </td>
										<td align="center"> <s:property value="stud_rank"/> / <s:property value="stud.total_number"/> </td>
										<s:if test="%{#at_id == \"2\"}">
											<td> &nbsp; </td>
											<td> &nbsp; </td>
										</s:if>
										<s:if test="%{#at_id == \"3\"}">
											<td> &nbsp; </td>
											<td> &nbsp; </td>
											<td> &nbsp; </td>
											<td> &nbsp; </td>
										</s:if>
									</tr>
								</c:if>
							</table>
							<div style="height: 4px;">&nbsp;</div>
							<table width="100%" cellpadding="0" cellspacing="0" rules="rows" style="border-color: black; border-style: solid; border-width: 1px; padding-bottom: 4px;">
								<s:iterator status="stat" value="getAbsentDayList">
									<tr>
										<td colspan="2">
											<table cellpadding="0" cellspacing="0"><tr>
											<td style="font-weight: bold; height: 30px; padding-left: 10px;">Number of Absent Days:</td>
											<td style="padding-left: 10px;">
												<s:property value="getAbsentDayList[#stat.index].noOfAbsentDay"/>
											</td>
											</tr></table>
										</td>
									</tr>
									<tr>
										<td style="font-weight: bold; width: 15%; height: 30px; padding-left: 10px;">Days List:</td>
										<td style="padding-left: 10px;">
											<s:property value="getAbsentDayList[#stat.index].absentDayList"/>
										</td>
									</tr>
								</s:iterator>
							</table>
							
							<table width="100%" cellpadding="0" cellspacing="0" style="padding-bottom: 4px; padding-top: 5px;">
								<tr><td style="height: 50px; padding-left: 10px; text-decoration: none; border-color: black; border-style: solid; border-width: 1px; border-bottom-style: solid; border-top-style: solid;">
									<b>Change Status Key:</b> <img alt="V" src="images/up_images.jpeg" style="height: 20px; width: 20px;"> - Increase, <img alt="V" src="images/decrease_images.jpeg" style="height: 20px; width: 20px;"> - Decrease, (***) - decrease by more than 10 marks, (**) - decrease by 5 to 10 marks, (*) - decrease by 1 to 5 marks
								</td></tr>
							</table>
							
							<table width="100%" style="border-color: black; border-style: solid; border-width: 1px;">
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
							
						</td>
					</tr>
				</table>
			
			</td>
		</tr>
	</table>
	
</td></tr></thead><tr><td></td></tr></table>

</body>
</html>