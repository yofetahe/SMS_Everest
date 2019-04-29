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

</head>
<body>
	<table width="100%" cellpadding="0" cellspacing="0" style="padding-top: 0px" border="0">		
		<tr>
			<td>
				<s:set var="clid" value="class_id"/>
				<s:set var="cd_id" value="cd_id"/>
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td width="12%">
							<div id="regFee" class="normal_btn_inactive" onclick="registrationFee('${clid}', '${cd_id}')"> 
								Registration Fee
							</div>
						</td>
						<td width="1%">&nbsp;</td>
						<td width="12%">
							<div id="eduPay" class="normal_btn_active" style="background-color: #3d6e9f" onclick="eduPayment('${clid}', '${cd_id}')"> 
								Education Payment
							</div>
						</td>
						<td width="1%">&nbsp;</td>
						<td width="14%">
							<div id="tutPay" class="normal_btn_inactive" onclick="tutPayment('${clid}', '${cd_id}')"> 
								Tutorial Payment
							</div>
						</td>
						<td width="1%">&nbsp;</td>
						<td width="14%">
							<div id="sumPay" class="normal_btn_inactive" onclick="sumPayment('${clid}', '${cd_id}')">
								Summer Payment
							</div>
						</td>
						<td width="1%">&nbsp;</td>
						<td width="12%">
							<div id="matPay" class="normal_btn_inactive" onclick="matPayment('${clid}', '${cd_id}')">
								Material
							</div>
						</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				<div style="height: 5px"></div>
				<!-- Student list -->				
				<div id="student_list_payment">
					<s:include value="payment_student_list.jsp"/>
				</div>
				<!-- Student list -->
			</td>
		</tr>
	</table>
</body>
</html>