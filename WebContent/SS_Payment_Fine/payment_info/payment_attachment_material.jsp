<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

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
                $('#pay_attachment').dataTable( {
                    "sDom": 'T<"clear">'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

<div style="width: 60%;">

<table id="pay_attachment">
<thead><tr><td>

<div style="height: 18.5cm !important; background-color: #fff; border: thin; border-color: black; border-style:solid; border-width: thin; padding: 5px;">
<table width="100%" style="background-color: #fff;">
	<thead>
		<tr>
			<td>
			
				<table width="100%">
					<tr>
						<td>
							<table><tr><td align="center">
								<img alt="<s:text name='global.schoolname'/>" src="images/<s:text name='global.schoollogo'/>" style="width: 40px;"> <br/>
								<span style="font-weight: bold; font-size: 20px;"> <s:property value="payment_receipt_info[0].school_name"/> </span> <br/>
								<span style="font-size: 14px;"> TIN: <s:property value="payment_receipt_info[0].tin_num"/> </span>
							</td></tr></table>
						</td>
						<td align="right" style="font-size: 12px;">
						
							<s:set var="tel" value="payment_receipt_info[0].telephone"/> 
							<s:set var="fax" value="payment_receipt_info[0].fax"/> 
							<s:set var="web" value="payment_receipt_info[0].web"/> 
							<s:set var="email" value="payment_receipt_info[0].email"/> 
							<s:set var="pob" value="payment_receipt_info[0].pobox"/> 
							
							<s:if test="%{#tel != \"\"}">
								Tel: <s:property value="payment_receipt_info[0].telephone"/> <br/>
							</s:if>
							<s:if test="%{#fax != \"\"}">
								Fax: <s:property value="payment_receipt_info[0].fax"/> <br/>
							</s:if>
							<s:if test="%{#web != \"\"}">
								Web: <s:property value="payment_receipt_info[0].web"/> <br/>
							</s:if>
							<s:if test="%{#email != \"\"}">
								E-mail: <s:property value="payment_receipt_info[0].email"/> <br/>
							</s:if>
							<s:if test="%{#pob != \"\"">
								P.O.Box: <s:property value="payment_receipt_info[0].pobox"/> <br/>
							</s:if>
							<span style="padding-right: 5px;">
								Addis Ababa, Ethiopia
							</span>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" style="padding-top: 10px;">
						
						----------------------------------<b>   Payment Attachment   </b>----------------------------------
						</td>
					</tr>
				</table>
				
			</td>
		</tr>
	</thead>
	<tr>
		<td style="padding-top: 10px;">
		
			<div>
			
			<table width="100%" border="0" style="border: thin; border-style: dotted; border-color: silver; border-width: thin; font-size: 14px;">
				<tr>
					<td style="font-weight: bold;">
						Student Name: 
					</td>
					<td>
						<s:property value="payment_receipt_info[0].fname"/> <s:property value="payment_receipt_info[0].mname"/> <s:property value="payment_receipt_info[0].gname"/>		
					</td>
					<td style="font-weight: bold;">
						Student Grade: 
					</td>
					<td>
						<s:property value="payment_receipt_info[0].class_name"/>
					</td>
				</tr>
				<tr>
					<td style="font-weight: bold;">
						FS Number:
					</td>
					<td>
						<s:property value="payment_receipt_info[0].fiscal_machine_no"/>
					</td>
					<td style="font-weight: bold;">
						Payment Date:
					</td>
					<td>
						<s:property value="payment_receipt_info[0].payment_date"/>
					</td>
				</tr>
				<tr>
					<td style="font-weight: bold;">
						FM Receipt Number:
					</td>
					<td>
						<s:property value="payment_receipt_info[0].fm_receipt_no"/>
					</td>
					<td style="font-weight: bold;">
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td style="font-weight: bold;">
						Customer VAT Reg. No.:
					</td>
					<td>
						<s:property value="payment_receipt_info[0].cust_vat_reg_no"/>
					</td>
					<td style="font-weight: bold;">
						Customer TIN:
					</td>
					<td>
						<s:set var="cust_tin" value="payment_receipt_info[0].cust_tin"/>
						<s:if test="%{#cust_tin != \"0\"}"> <s:property value="%{#cust_tin}"/> </s:if>
					</td>
				</tr>
			</table>
			
			</div>
		</td>
	</tr>
	<tr>
		<td style="padding-top: 10px;">
			<div>
			
			<table width="100%">
				<tr>
					<td style="border-bottom-color: silver; border-bottom-style: solid; border-bottom-width: thin; font-size: 14px;">
						<s:set var="pay_type" value="payment_receipt_info[0].payment_type"/>
						<s:if test="%{#pay_type == \"1\"}"> <b>Payment for </b>: Normal Class Fee </s:if>
						<s:if test="%{#pay_type == \"2\"}"> <b>Payment for </b>: Tutorial Class Fee </s:if>
						<s:if test="%{#pay_type == \"3\"}"> <b>Payment for </b>: Summer Class Fee </s:if>
						<s:if test="%{#pay_type == \"4\"}"> <b>Payment for </b>: Material Fee </s:if>
						<br/>						
					</td>
				</tr>
				<tr>
					<td style="padding-top: 15px;">
						<s:set var="sub_total" value="0"/>
						<table width="100%" style="border: thin; border-style: dotted; border-color: silver;  border-width: thin; font-size: 14px;">
							<tr height="30px;" >
								<td style="font-weight:bold; border-bottom-color: black; border-bottom-style: dotted; border-bottom-width: thin;" align="center">No</td>
								<td style="font-weight:bold; border-bottom-color: black; border-bottom-style: dotted; border-bottom-width: thin;">Description</td>
								<td style="font-weight:bold; border-bottom-color: black; border-bottom-style: dotted; border-bottom-width: thin;" align="right">Price</td>
							</tr>
							<s:iterator status="stat" value="selected_material_list">
								<tr>
									<td align="center"> 
										<s:property value="#stat.index + 1"/>
									</td>
									<td> <s:property value="selected_material_list[#stat.index].ptm_name"/> </td>
									<td align="right"> <s:property value="selected_material_list[#stat.index].material_pay_amount"/> </td>									
								</tr>
							</s:iterator>
<!-- 							<tr style="border-top: thin; border-top-color: silver; border-top-style: solid;">								 -->
<!-- 								<td colspan="2" align="right" style="font-weight: bold;">Sub Total</td> -->
<%-- 								<td align="right"> <s:property value="payment_sub_total"/> </td> --%>
<!-- 							</tr> -->
							<tr>								
								<td colspan="2" height="30px;" align="right" style="font-weight: bold; border-top: thin; border-top-color: black; border-top-style: dotted; border-top-width: thin;">Grand Total</td>
								<td align="right" style="border-top: thin; border-top-color: black; border-top-style: dotted; border-top-width: thin; padding-right: 5px;"> <s:property value="payment_grand_total"/> </td>
							</tr>							
						</table>
						
						<br/>
						
						<table width="100%" cellpadding="0" cellspacing="0" style="font-size: 14px;">
							<tr height="30px;">
								<td width="120px;"> Amount in words: </td>
								<td style="background-color: #f5f5f5; border: thin; border-color: black; border-style: dotted; border-width: thin; padding-left: 10px;"> 
									<s:property value="payment_grand_total_in_word"/> 
								</td>
							</tr>
						</table>
						
					</td>
				</tr>
			</table>
			
			</div>
		</td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td> -->
		
<!-- 			<br/> -->
<!-- 			<table width="100%" style="font-size: 14px;"><tr><td> -->
<!-- 			 Note: <br/> ______________________________________________________________<br/> -->
<!-- 			</td></tr></table> -->
			
<!-- 		</td> -->
<!-- 	</tr> -->
	<tr>
		<td style="padding-top: 10px;">		
			
				<table align="left" style="font-size: 14px;">
					<tr>
						<td align="center" width="120px;">					
							Mode of Payment - 
						</td>
						<td align="center" width="100px;">
							<s:set var="payment_mode" value="payment_receipt_info[0].payment_mode"/>
							<table><tr><td>
								<s:if test="%{#payment_mode == \"Cash\"}"> <img alt="checked" src="images/checked.png" style="width: 15px;"> </s:if><s:else> <img alt="check_box" src="images/check_box.png" style="width: 15px;"> </s:else> 
							</td><td valign="middle">
								Cash
							</td></tr></table>
						</td>
						<td align="center">
							Cheque No.: <s:property value="payment_receipt_info[0].cheque_no"/>							
						</td>
					</tr>
				</table>
				<br/><br/><br/>
				<table width="100%" style="font-size: 14px;">
					<tr>
						<td align="center">					
							______________________________<br/>
							Prepared by	
						</td>
						<td align="center">
							______________________________<br/>
							Cashier Signature
						</td>
					</tr>
				</table>
			
		</td>
	</tr>
</table>

<div style="width: 100%; display: inline-block; vertical-align: bottom;">
	<table cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
			
				<br/><br/>
				<table width="100%"><tr><td align="center">
				INVALID WITHOUT FISCAL OR REFUND RECEIPT ATTACHED
				</td></tr></table>
				
			</td>
		</tr>
		<tr>
			<td style="border-top-color: black; border-top-style:double; border-top-width: medium; padding-top: 10px;">
				
				<table width="100%"><tr><td align="left" style="font-size: 10px;">
				Fidel SMS, Addis Ababa-Ethiopia
				</td></tr></table>
				
			</td>
		</tr>
	</table>
</div>

</div>

</td></tr></thead>
<tr><td align="center">&nbsp;</td></tr>
</table>

</div>

</body>
</html>