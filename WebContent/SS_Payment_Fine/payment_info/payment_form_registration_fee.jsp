<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="/js/jquery-1.6.1.js" type="text/javascript"></script>
<title>SMS</title>

</head>
<body>

	<s:set var="clid" value="class_id"/>				 
	<s:set var="si_id" value="si_id"/>
			
	<s:set var="pc_id" value="pc_id"/>
<%-- 	<s:set var="snc_id" value="payment_amount[0].snc_id"/> --%>

	<table style="padding-top: 5px;">		
		<tr>
			<td style="border-bottom-color: #f5f5f5; border-bottom-style: solid; border-bottom-width: thin;">
				<table width="100%"><tr><td>
					
					Registration Fee Payment for <b> <s:property value="fname"/> <s:property value="mname"/> <s:property value="gname"/> </b>
				
				</td></tr></table>
			</td>
		</tr>
		<tr>
			<td>
				
			</td>
		</tr>
		<tr>
			<td>
				<div  id="errorMsg" style="color: red; width: 100%; text-align: center;">&nbsp;</div>
				<table width="100%"><tr><td>					
					
					<s:textfield label="Registration Fee Amount" id="payRate" key="pay_amount" readonly="true" style="height: 25px; width: 200px"/>
					<s:textfield label="Other Fee" id="other_fee" style="height: 25px; width: 200px"/>
					
					<s:textfield label="FM Reciept No" id="fm_receipt_no" required="true" name="fm_receipt_no" style="height: 25px; width: 200px"/>
					<s:textfield label="Customer VAT Reg. No" id="cust_vat_reg_no" style="height: 25px; width: 200px"/>
					<s:textfield label="Customer TIN" id="cust_tin" style="height: 25px; width: 200px"/>
					<s:select label="Mode of Payment" id="payment_mode" list="#{'Cash':'Cash', 'Cheque': 'Cheque'}" style="height: 28px; width: 205px" onchange="checkPaymentMode(this.value)"/>
										
					<table id="chequeNo" style="display: none;" align="right" ><tr><td>						
						<s:textfield label="Cheque No." id="cheque_no" required="true" style="height: 25px; width: 200px;"/>
					</td></tr></table>				
					
				</td></tr>
				<tr><td>
					<table align="right">
					<s:textfield label="Payment Date" id="payment_date" required="true" name="payment_date" style="height: 25px; width: 200px"/>
					</table>
				</td></tr>
				</table>
				<table width="80%"><tr><td style="padding-left: 60px;">
					<div class="save_update_btn" onclick="saveRegistrationFee('${si_id}', '${clid}', '${pc_id}')" style="height: 30px; text-align: center; color: #3d6e9f; width: 240px; padding-top: 8px; cursor: pointer;">
						Save
					</div>
				</td></tr></table>
			</td>
		</tr>
	</table>


</body>
</html>