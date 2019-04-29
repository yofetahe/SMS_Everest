<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="../css/sms_css.css">

</head>
<body>
	<s:set var="clid" value="class_id"/>				 
	<s:set var="si_id" value="si_id"/>
	<s:set var="payment_type" value="payment_type"/>
	<s:set var="pc_id" value="pc_id"/>
	<s:set var="snc_id" value="snc_id"/>

	<table cellpadding="0" cellspacing="0" rules="rows" bordercolor="#fff" width="75%">
		<tr style="background-color: #3d6e9f; color: #fff; height: 35px">
			<td>Month Selected</td>
			<td>&nbsp;</td>
		</tr>
		<s:iterator status="stat" value="payment_month_selected">
			<tr>
				<td style="font-variant: small-caps;">
					<s:set var="month_id" value="payment_month_selected[#stat.index].month_id"/>
					<s:property value="payment_month_selected[#stat.index].month_full"/>
				</td>
				<td align="right" style="color: #3d6e9f; cursor: pointer;">
					&nbsp;
				</td>
			</tr>
		</s:iterator>
		<tr bgcolor="#f5f5f5">
			<td align="center" style="cursor: pointer;padding-top: 5px;">
				<div onclick="addAdditionalMonthPayment('${si_id}', '${pc_id}', '${clid}', '${payment_type}', '${snc_id}')" class="save_update_btn" style="background-color: #fff; width: 120px;">
					Select
				</div>
			</td>
			<td style="cursor: pointer; padding-top: 5px;" align="center">
				<div class="save_update_btn" style="background-color: #fff; width: 120px;">
					<div onclick="removeSelectedPaymentMonth('${payment_type}')"> Remove All</div>
				</div>
			</td>
		</tr>
	</table>

</body>
</html>