<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">
<script src="js/jquery-1.6.1.js" type="text/javascript"></script>
<script src="../js/sms_admin.js" type="text/javascript"></script>

</head>
<body>

	<table cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<table width="100%" style="border-bottom-style: solid; border-bottom-width: thin; border-bottom-color: #3d6e9f;" cellpadding="0" cellspacing="0"><tr><td>
				
					<table cellpadding="0" cellspacing="0" >
						<tr>
							<td>&nbsp;</td>
							<td width="210px" height="25px" align="center">
								<div id="cltRel" class="header_btn_inactive" style="width: 100%" onclick="classTraitRelationship()">
									Class and Eval. On Relationship
								</div>
							</td>							
							<td>&nbsp;</td>							
							<td width="140px" height="25px" align="center">
								<div id="trait" class="header_btn_inactive" style="width: 100%" onclick="traitsList()">
									Evaluate On
								</div>
							</td>
							<td>&nbsp;</td>
							<td width="140px" height="25px" align="center">
								<div id="grade" class="header_btn_inactive" style="width: 100%" onclick="evalGradeList()">
									Evaluation Grade
								</div>
							</td>
							
							<td width="20px">&nbsp;</td>
							
							<td width="210px" height="25px" align="center">
								<div id="clholRel" class="header_btn_inactive" style="width: 100%" onclick="classHolisticRelationship()">
									Class and Holistic Relationship
								</div>
							</td>							
							<td>&nbsp;</td>							
							<td width="140px" height="25px" align="center">
								<div id="holcat" class="header_btn_inactive" style="width: 100%" onclick="holisticCatList()">
									Holistic Category
								</div>
							</td>
							<td>&nbsp;</td>
							<td width="140px" height="25px" align="center">
								<div id="holpoint" class="header_btn_inactive" style="width: 100%" onclick="holisticPointList()">
									Holistic Points
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
				<div id="infoList" style="padding-top: 10px;">
				
					
				</div>
			</td>
		</tr>
	</table>

</body>
</html>