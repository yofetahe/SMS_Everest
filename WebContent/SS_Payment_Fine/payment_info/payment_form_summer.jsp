<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

</head>
<body>
<div id="paymentForMoreMonth">
	<s:set var="clid" value="class_id"/>				 
	<s:set var="si_id" value="si_id"/>
	<s:set var="payment_type" value="payment_type"/>
	<s:set var="pay_amount" value="payment_amount[0].pay_amount"/>				
	<s:set var="mth" value="month"/>
	<s:set var="pc_id" value="payment_amount[0].pc_id"/>
	<s:set var="totalPayment" value="payment_amount[0].total_payment"/>
	<s:set var="penalityAmount" value="payment_amount[0].penality_amount"/>
	
	<div style="background-color: #f5f5f5; width: auto; height: 25px; padding-top: 5px; color: #3d6e9f;">
		<div style="cursor: pointer;" id="show" onclick="document.getElementById('moreMonthSelection').style.display='block', document.getElementById('hide').style.display='block', document.getElementById('show').style.display='none'">
			Show Additional Month
		</div>
		<div style="cursor: pointer; display: none;" id="hide"  onclick="document.getElementById('moreMonthSelection').style.display='none', document.getElementById('hide').style.display='none', document.getElementById('show').style.display='block'">
			Hide Additional Month
		</div>
	</div>
	<div style="height: 1px; background-color: #fff;">&nbsp;</div>
	<div id="moreMonthSelection" style="display: none; background-color: #f5f5f5; width: auto; min-height: 25px; padding-top: 5px; color: #3d6e9f;">
		<table width="100%"><tr>
		<td width="49%" style="vertical-align: top;">
		
			<s:set var="mcheck" value="0"/>					
			<table cellpadding="0" cellspacing="0" rules="rows" bordercolor="#fff" width="75%">
				<tr style="background-color: #3d6e9f; color: #fff; height: 35px">
					<td>
						Months
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<s:set var="inintialMonth_id" value="0"/>
				<s:iterator status="stat" value="payment_month">
					<s:set var="mshort" value="payment_month[#stat.index].month_short"/>					
					<s:set var="mlong" value="payment_month[#stat.index].month_full"/>											
					<s:if test="%{#mcheck == 0}">							
						<s:if test="%{#mshort == #mth}">
							<!-- a way to determine from which month the list should start -->					
							<s:set var="mcheck" value="%{#mcheck+1}"/>													
						</s:if>
					</s:if>
					<s:if test="%{#mcheck > 0}">
						<s:if test="%{#mshort == \"jul\" || #mshort == \"aug\"}">
							<tr>
								<td style="font-variant: small-caps;">
									<s:set var="month_id" value="payment_month[#stat.index].month_id"/>
									<!-- to get the initial month which help to control the starting point-->
									<s:if test="%{#inintialMonth_id == 0}">
										<s:set var="inintialMonth_id" value="%{#month_id}"/>
									</s:if>
									<s:property value="%{#mlong}"/>													
								</td>
								<td align="right" >
									<div id="${mshort}" onclick="payMonthSelectedForSummer('${month_id}', '${mshort}', '${mlong}', '${inintialMonth_id}', '${si_id}', '${pc_id}', '${clid}', '${payment_type}')" style="cursor: pointer;">
										Add
									</div>
								</td>
							</tr>
						</s:if>						
					</s:if>					
				</s:iterator>
			</table>
		
		</td>
		<td>&nbsp;</td>
		<td width="49%" id="selectedMonthForPay" style="vertical-align: top;">
			<div id="mthSelErrMsg">&nbsp;</div>
		</td>
		</tr></table>		
				
	</div>
	
	<div id="errorMsg" style="color: red"></div>			
	<table style="padding-top: 5px;">		
		<tr>
			<td style="border-bottom-color: #f5f5f5; border-bottom-style: solid; border-bottom-width: thin;">
				<table width="100%"><tr><td>
				
					<s:iterator status="stat" value="payment_month">				
						<s:set var="mshort" value="payment_month[#stat.index].month_short"/>
						<s:if test="%{#mshort == #mth}">
							<s:property value="payment_month[#stat.index].month_full"/>
							<s:set var="month_id" value="payment_month[#stat.index].month_id"/>
						</s:if>					
					</s:iterator>				
					
					Payment for <s:property value="fname"/> <s:property value="mname"/> <s:property value="gname"/>
				
				</td></tr></table>
			</td>
		</tr>
		<tr>
			<td>
				
			</td>
		</tr>
		<tr>
			<td>
				
				<table width="100%"><tr><td>					
					
					<s:textfield label="Payment Rate" id="payRate" key="pay_amount" readonly="true" style="height: 25px; width: 200px"/>
					<s:textfield label="Penality" id="payPenality" value="%{#penalityAmount}" readonly="true" style="height: 25px; width: 200px"/>
					<s:textfield label="Total Payment" id="totalPay" value="%{#totalPayment}" readonly="true" style="height: 25px; width: 200px"/>
					
					<s:textfield label="FM Reciept No" id="fm_receipt_no" required="true" name="fm_receipt_no" style="height: 25px; width: 200px"/>
					<s:textfield label="Customer VAT Reg. No" id="cust_vat_reg_no" style="height: 25px; width: 200px"/>
					<s:textfield label="Customer TIN" id="cust_tin" style="height: 25px; width: 200px"/>
					<s:select label="Mode of Payment" id="payment_mode" list="#{'Cash':'Cash', 'Cheque': 'Cheque'}" style="height: 28px; width: 205px" onchange="checkPaymentMode(this.value)"/>
					
					<table id="chequeNo" style="display: none;" align="right" ><tr><td>						
						<s:textfield label="Cheque No." id="cheque_no" required="true" style="height: 25px; width: 200px;"/>
					</td></tr></table>
				</td></tr>
		<tr><td>
			<button onclick="saveSummerPayment('${si_id}', '${pc_id}', '${month_id}', '${clid}', '${payment_type}')" class="btn">Save</button>						
		</td></tr></table>
			</td>
		</tr>
	</table>
</div>
</body>
</html>