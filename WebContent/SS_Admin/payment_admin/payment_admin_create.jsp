<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

</head>
<body>
	
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="padding-top: 1px">
				<div style="background-color: #f5f5f5; height: 35px; padding-top: 15px; background-position: bottom;">
					<div style="cursor: pointer; color: #3d6e9f;" onclick="paymentAdmin()"><img alt="click" src="images/next.gif" width="8px"> &nbsp;Back to Payment Info List</div>
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
				
				
					<s:fielderror fieldName="errMsg" name="errMsg" id="errMsg"/>
					<div id="errorMsg" style="width: 400px; color: #ff0000; text-align: center;"></div>					
					<table>
						<tr>
							<td>
								<s:select label="Grade" id="cl_id" list="grade_rslt" headerValue="Please select grade" headerKey="0" listValue="class_name" listKey="class_id" required="true" style="height: 25px; width: 206px;"/>
								<s:select label="Payment Type" id="pt_id" list="payment_type_list" headerValue="Please select payment type" headerKey="0" listKey="pt_id" required="true" listValue="pt_name" style="height: 25px; width: 206px;"/>
								<s:textfield label="Payment Amount" id="pay_amount" required="true" style="height: 25px; width: 200px;"/>
								<s:textfield label="Days for Penalty" id="penality_max_date" required="true" style="height: 25px; width: 200px;"/>
								<s:textfield label="Penalty Rate" id="penality_percent" required="true" style="height: 25px; width: 200px;"/>
								<s:textfield label="Academic Year" id="academic_year" required="true" style="height: 25px; width: 200px;"/>
								
								<s:submit onclick="savePaymentInfo()" value="Save"/>
							</td>
						</tr>
					</table>
					
				
			</td>
		</tr>
	</table>
	
</body>
</html>