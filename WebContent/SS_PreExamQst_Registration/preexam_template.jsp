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
<script type="text/javascript">
	
	function subject_list(classid){
		$("#qst_body").html("<img align=\"center\" src=\"images/loader.gif\"/>");		
		$.ajax({
			type : "POST",
			url : "pe_sublist.action",				
			data : "cl_id=" + classid,
			success : function(response) {
				$('#qst_body').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
</script>

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
				<div style="width: 1220px; min-height: 450px">					
					<table width="100%" height="450px" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td width="13%" valign="top" style="padding-top: 1px;">
								<div align="center" style="background-color: #e5e5e5; border-bottom-color: #fff; border-bottom-style: solid; border-bottom-width: thin;">
									<img alt="" src="images/down.png" width="40px" height="10px">
								</div>
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
												
													<div id="stud_form">
										
														<table width="100%" cellpadding="0" cellspacing="0">
															<tr>
																<td style="padding-top: 1px">
																	<div class="menu_shadow" style="background-color: #e5e5e5; height: 45px; padding-top: 5px; vertical-align: middle;   background-position: bottom;">
																		
																		<table border="0"><tr>
																			<td><s:select label="Grade" list="grdList" headerValue="Please select grade" headerKey="0" listKey="cl_id" listValue="class_name" style="height: 25px; width: 200px;" onchange="subject_list(this.value)" /></td>
																		</tr></table>
																			
																	</div>
																</td>
															</tr>
															<tr>
																<td style="padding-top: 10px">
																	<div class="content_background" style="min-height: 434px" id="qst_body">
																		
																	</div>
																</td>
															</tr>
														</table>
													
													</div>
												
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