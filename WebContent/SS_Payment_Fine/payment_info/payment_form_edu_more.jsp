<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="../css/sms_css.css">
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
                $('#multipleMonthPayForm').dataTable( {
                    //"sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

	<div id="errorMsg" style="color: red"></div>

	<s:set var="payment_type" value="payment_type"/>
	<s:set var="si_id" value="si_id"/>
	
	<table width="80%"><tr><td>
		<div>
		<table id="multipleMonthPayForm" width="100%">
			<thead style="background-color: #f5f5f5; height: 30px; color: #000000" align="center">
				<tr>
					<td>No</td>
					<td>Month</td>
					<td>Pay Amount</td>
					<td>Penalty</td>
					<td>Total Payment</td>
				</tr>
			</thead>
			<s:iterator status="stat" value="payment_info_more_than_amonth">
				<tr height="30px">
					<td>
						<s:property value="%{#stat.index + 1}"/>
					</td>
					<td>
						<s:set var="ac_year" value="payment_info_more_than_amonth[#stat.index].academic_year"/>
						<s:set var="cl_id" value="payment_info_more_than_amonth[#stat.index].class_id"/>
						<s:property value="payment_info_more_than_amonth[#stat.index].month_full"/>
					</td>
					<td>
						<s:property value="payment_info_more_than_amonth[#stat.index].pay_amount"/>
					</td>
					<td>
						<s:property value="payment_info_more_than_amonth[#stat.index].penality_amount"/>
					</td>
					<td>
						<s:property value="payment_info_more_than_amonth[#stat.index].total_payment"/>
					</td>
				</tr>
			</s:iterator>
		</table>
		</div>
	</td></tr></table>
	
	<table width="60%" align="left"><tr><td>
		<table><tr><td>
			<s:textfield label="FM Reciept No" id="fm_receipt_no" required="true" name="fm_receipt_no" style="height: 25px; width: 200px"/>
			<s:textfield label="Customer VAT Reg. No" id="cust_vat_reg_no" style="height: 25px; width: 200px"/>
			<s:textfield label="Customer TIN" id="cust_tin" style="height: 25px; width: 200px"/>
			<s:select label="Mode of Payment" id="payment_mode" list="#{'Cash':'Cash', 'Cheque': 'Cheque'}" style="height: 28px; width: 204px" onchange="checkPaymentMode(this.value)"/>			
		</td></tr></table>
	</td></tr></table>
		
	<table id="chequeNo" width="100%" style="display: none; padding-left: 67px;" align="left" ><tr><td>						
		<s:textfield label="Cheque No." id="cheque_no" required="true" style="height: 25px; width: 200px;"/>
	</td></tr></table>
	
	<table width="80%"><tr><td align="left">
		<div class="save_update_btn" onclick="saveMulitpleMonthPayment('${ac_year}', '${cl_id}', '${payment_type}', '${si_id}')" style="height: 35px; text-align: center; color: #3d6e9f; width: 280px; padding-top: 8px; cursor: pointer;">
			Save
		</div>
	</td></tr></table>
	
</body>
</html>