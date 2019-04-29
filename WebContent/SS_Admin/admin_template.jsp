<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">
<link rel="icon" type="image/png" href="images/fidel_logo.png">
<script src="js/jquery-1.6.1.js" type="text/javascript"></script>
<script src="js/sms_admin_menu.js" type="text/javascript"></script>
<script src="js/sms_admin.js" type="text/javascript"></script>

</head>
<body style="margin: 0; background-image: url('images/new_bg_2.png'); background-repeat: repeat;">

	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<div class="menu_shadow">
				<s:include value="../SS_Index_Content/header.jsp"/>
				</div>
			</td>
		</tr>
		<tr>
			<td align="center">
				<!-- body part -->
				<div style="width: 95%; min-height: 450px">					
					<table width="100%" height="450px" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td width="13%" valign="top" style="padding-top: 1px;">
								<div>
								<s:include value="../SS_Index_Content/menu.jsp"/>
								</div>
							</td>
							<td valign="top" style="padding-top: 0px">
								<div id="index_cont"> 
								
								<%-- //////////// Template body //////////////////// --%>
								
									<div id="subjectList">
										<table width="100%" style="min-height: 420px" cellpadding="0" cellspacing="0">
											<tr>
												<td width="2%" valign="top" style="padding-top: 1px">
													<%-- <s:include value="/SS_Index_Content/menu_indicator.jsp"/> --%>
												</td>
												<td width="1%">&nbsp;</td>
												<td valign="top">
												
													<table width="100%" cellpadding="0" cellspacing="0">
														<tr>
															<td style="padding: 2px;">
															
																<div class="menu_shadow" style="min-height: 70px; padding-top: 5px;  background-position: bottom;">
																	<table cellpadding="0" cellspacing="0">
																		<tr>
																			<td>
																			
																				<table><tr><td>
																					<div class="adminHeaderButton" style="width: 100px;" onclick="addUsers()" id="usr">
																						<img alt="click" src="images/next_dim.gif" width="8px">&nbsp;Users
																					</div>
																				</td><td>
																					<div style="width: 20px;">&nbsp;</div>
																				</td><td>
																					<div class="adminHeaderButton" style="width: 100px;" onclick="addUserRole()" id="usrrole">
																						<img alt="click" src="images/next_dim.gif" width="8px">&nbsp;Users Role 
																					</div>
																				</td><td>
																					<div style="width: 20px;">&nbsp;</div>
																				</td><td>
																					<div class="adminHeaderButton" style="width: 120px;"  onclick="addAnnualTerm()" id="annTerm">
																						<img alt="click" src="images/next_dim.gif" width="8px">&nbsp;Annual Term 
																					</div>
																				</td><td>
																					<div style="width: 20px;">&nbsp;</div>
																				</td><td>
																					<div class="adminHeaderButton" style="width: 120px;"  onclick="schoolEvent()" id="schEvent">
																						<img alt="click" src="images/next_dim.gif" width="8px">&nbsp;School Events 
																					</div>
																				</td><td>
																					<div style="width: 20px;">&nbsp;</div>
																				</td><td>
																					<div class="adminHeaderButton" style="width: 100px;"  onclick="holidayList()" id="hldList">
																						<img alt="click" src="images/next_dim.gif" width="8px">&nbsp;Holidays 
																					</div>
																				</td><td>
																					<div style="width: 20px;">&nbsp;</div>
																				</td><td>
																					<div class="adminHeaderButton" style="width: 140px;"  onclick="examSchedule()" id="exmSch">
																						<img alt="click" src="images/next_dim.gif" width="8px">&nbsp;Exam Schedule 
																					</div>
																				</td><td>
																					<div style="width: 20px;">&nbsp;</div>
																				</td><td>
																					<div class="adminHeaderButton" style="width: 140px;"  onclick="attendanceType()" id="attType">
																						<img alt="click" src="images/next_dim.gif" width="8px">&nbsp;Attendance 
																					</div>
																				</td></tr></table>
																			
																			</td>
																		</tr>
																		<tr >
																			<td style="padding-top: 5px;">
																			
																				<table><tr><td>
																					<div class="adminHeaderButton" style="width: 120px;"  onclick="curriculumActivity()" id="curactivity">
																						<img alt="click" src="images/next_dim.gif" width="8px">&nbsp;Co-Curriculum
																					</div>
																				</td><td>
																					<div style="width: 20px;">&nbsp;</div>
																				</td><td>
																					<div class="adminHeaderButton" style="width: 180px;"  onclick="addHomeRoomTeacher()" id="hroomtchr">
																						<img alt="click" src="images/next_dim.gif" width="8px">&nbsp;Home Room Teacher
																					</div>
																				</td><td>
																					<div style="width: 20px;">&nbsp;</div>
																				</td><td>
																					<div class="adminHeaderButton" style="width: 180px;"  onclick="addBehaviouralEvaluation()" id="behEval">
																						<img alt="click" src="images/next_dim.gif" width="8px">&nbsp;Behavioural Evaluation 
																					</div>
																				</td><td>
																					<div style="width: 20px;">&nbsp;</div>
																				</td><td>
																					<div class="adminHeaderButton" style="width: 140px;"  onclick="addStudentMaterial()" id="studMat">
																						<img alt="click" src="images/next_dim.gif" width="8px">&nbsp;Student Material
																					</div>
																				</td><td>
																					<div style="width: 20px;">&nbsp;</div>
																				</td><td>
																					<div class="adminHeaderButton" style="width: 120px;"  onclick="addPayment()" id="payment">
																						<img alt="click" src="images/next_dim.gif" width="8px">&nbsp;Payment
																					</div>
																				</td><td>
																					<div style="width: 20px;">&nbsp;</div>
																				</td><td>
																					<div class="adminHeaderButton" style="width: 120px;"  onclick="addSchoolInfo()" id="sch_info">
																						<img alt="click" src="images/next_dim.gif" width="8px">&nbsp;School Info
																					</div>
																				</td>
																				</tr></table>
																			
																			</td>
																		</tr>
																		<tr>
																			<td style="padding-top: 5px;">
																			
																				<table><tr>
																				<td>
																					<div class="adminHeaderButton" style="width: 240px;"  onclick="certDefaultComment()" id="def_com">
																						<img alt="click" src="images/next_dim.gif" width="8px">&nbsp;Certificate Default Comment
																					</div>
																				</td><td>
																					<div style="width: 20px;">&nbsp;</div>
																				</td><td>
																					<div class="adminHeaderButton" style="width: 140px;"  onclick="examGrading()" id="grading">
																						<img alt="click" src="images/next_dim.gif" width="8px">&nbsp;Exam Grading
																					</div>
																				</td><td>
																					<div style="width: 20px;">&nbsp;</div>
																				</td><td>
																					<div class="adminHeaderButton" style="width: 180px;"  onclick="specialRegistration()" id="spe_reg">
																						<img alt="click" src="images/next_dim.gif" width="8px">&nbsp;Special Registration
																					</div>
																				</td><td>
																					<div style="width: 20px;">&nbsp;</div>
																				</td><td>
																					<div class="adminHeaderButton" style="width: 180px;"  onclick="generateIDCard()" id="id_card">
																						<img alt="click" src="images/next_dim.gif" width="8px">&nbsp;Generate ID Card
																					</div>
																				</td><td>
																					<div style="width: 20px;">&nbsp;</div>
																				</td><td>
																					<div class="adminHeaderButton" style="width: 180px;"  onclick="classAllotment()" id="class_allotment">
																						<img alt="click" src="images/next_dim.gif" width="8px">&nbsp;Class Allotment
																					</div>
																				</td><td>
																					<div style="width: 20px;">&nbsp;</div>
																				</td>
																				</tr></table>
																				
																			</td>
																		</tr>
																		<tr><td>&nbsp;</td></tr>
																	</table>
																</div>
																
															</td>
														</tr>
														<tr>
															<td style="padding-top: 10px">
																<div class="content_background"  style="min-height: 434px">
																	<div id="userAdminCont">
																		
																	</div>
																</div>
															</td>
														</tr>
													</table>												
												
												</td>
											</tr>											
										</table>
									</div>
								<%-- //////////// Template body //////////////////// --%>
								
								</div>
							</td>
						</tr>
						<tr><td colspan="2">&nbsp;</td></tr>						
					</table>					
				</div>
				<!-- body part -->
			</td>
		</tr>
		<tr>
			<td>
				<s:include value="../SS_Index_Content/footer.jsp"/>
			</td>
		</tr>
	</table>
</body>
</html>