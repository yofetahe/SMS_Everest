<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">
<link rel="icon" type="image/png" href="images/fidel_logo.png">
<script src="js/jquery-1.6.1.js" type="text/javascript"></script>
<script src="js/sms_behavioural_evaluation.js" type="text/javascript"></script>

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

								<div id="classList">
									<table width="100%" style="min-height: 420px" cellpadding="0" cellspacing="0">
										<tr>
											<td width="2%" valign="top" style="padding-top: 1px">
												<%-- <s:include value="/SS_Index_Content/menu_indicator.jsp"/> --%>
											</td>
											<td width="1%">&nbsp;</td>
											<td valign="top" >
												<table width="100%" cellpadding="0" cellspacing="0">
													<tr>
														<td style="padding-top: 1px">
															<div class="menu_shadow" style="height: 35px; padding-top: 15px; background-position: bottom;">
																
																<table cellpadding="0" cellspacing="0"><tr><td>														
																	<span style="color: #3d6e9f; padding-left: 5px;">Behavioral Evaluation</span>
<%-- 																	<div id="beh" style="cursor: pointer;" onclick="behavioural_evaluation_temp()"><img alt="click" src="images/next.gif" width="8px"> &nbsp;Behavioral Evaluation</div> --%>
																																
																</td><td width="60px">&nbsp;</td><td>
																	
<%-- 																	<div id="hol" style="cursor: pointer;" onclick="holistic_behaviour_evaluation_temp()"><img alt="click" src="images/next_dim.gif" width="8px"> &nbsp;Holistic Behavioral Evaluation</div> --%>
																	
																</td></tr></table>
																
															</div>
														</td>
													</tr>
													<tr>
														<td style="padding-top: 10px">
															<div class="content_background" id="eval_content" style="min-height: 440px"> 
																<s:include value="beval_home.jsp"/>
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