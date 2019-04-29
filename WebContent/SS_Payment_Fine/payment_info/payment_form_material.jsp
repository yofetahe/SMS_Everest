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
                $('#paid_mat_list').dataTable( {
                    "sDom": ''
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

<!-- 				<table width="100%"><tr><td align="right">					 -->
<!-- 					<table> -->
<%-- 					<s:select id="ptm_id" label="Material List" list="material_list" listKey="ptm_id" listValue="ptm_name" name="ptm_name" headerValue="---" headerKey="0" style="height: 28px; width: 206px" onchange="getSelectedMaterialPrice(this.value, '${clid}')"/> --%>
<!-- 					</table>					 -->
<!-- 				</td></tr> -->
<!-- 				<tr><td align="right"> -->
<!-- 					<table id="payAmount"></table> -->
<!-- 				</td></tr> -->
<!-- 				<tr><td> -->
<%-- 					<s:submit align="center" onclick="saveMaterialPayment('${si_id}', '${clid}', '${payment_type}')" value="Save"/> --%>
<!-- 				</td></tr></table> -->

	<table cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td width="50%" style="vertical-align: top;">				
				<div align="center" class="adminHeaderButton" style="width: 100%; padding-top: 6px;"> 
					Unpaid Materials List
				</div>
				
				<table id="mat_list" cellpadding="0" cellspacing="1" width="95%">
					<thead style="background-color: #f5f5f5; height: 30px; color: #000000" align="center">
						<tr height="30px;">
							<th width="14%">No</th>
							<th>Material</th>
							<th>Material Cost</th>
							<th>&nbsp;</th>
						</tr>
					</thead>
					<s:iterator status="stat" value="material_list">
						<tr height="30px">
							<td align="center"> 
								<s:property value="%{#stat.index + 1}"/>
								<s:set var="ptm_id" value="material_list[#stat.index].ptm_id"/>
								<s:set var="pcm_id" value="material_list[#stat.index].pcm_id"/>
								<s:set var="material_pay_amount" value="material_list[#stat.index].material_pay_amount"/>
								<s:set var="ptm_name" value="material_list[#stat.index].ptm_name"/>
							</td>
							<td> <s:property value="material_list[#stat.index].ptm_name"/> </td>
							<td> <s:property value="material_list[#stat.index].material_pay_amount"/> </td>
							<td align="left" >
								
								<table><tr><td>
<!-- 									<table> -->
<%-- 										<s:textfield id="${ptm_id}index" style="width:10px; visibility: hidden;" value="0"/>										 --%>
<!-- 									</table> -->
									<input id="${ptm_id}index" type="text" value="0" style="width:10px; visibility: hidden;"/>
								</td><td>
									<div id="${ptm_id}check" onclick="materialStatus('${ptm_id}', '${si_id}', '${clid}', '${payment_type}', '${material_pay_amount}', '${pcm_id}', '${ptm_name}')"> 
										<img alt="-" src="images/check_box.png" width="18px"/> 
									</div>
								</td></tr></table>																
								 																
							</td>
						</tr>
					</s:iterator>
				</table>
				<div id="errorMessage" style="width: 100%; color: #ff0000" align="center"></div>
				
				<table width="100%" align="left"><tr><td>
					<table><tr><td>
						<s:textfield label="FM Reciept No" id="fm_receipt_no" name="fm_receipt_no" required="true" style="height: 25px; width: 200px"/>
						<s:textfield label="Customer VAT Reg. No" id="cust_vat_reg_no" style="height: 25px; width: 200px"/>
						<s:textfield label="Customer TIN" id="cust_tin" style="height: 25px; width: 200px"/>
						<s:select label="Mode of Payment" id="payment_mode" list="#{'Cash':'Cash', 'Cheque': 'Cheque'}" style="height: 28px; width: 204px" onchange="checkPaymentMode(this.value)"/>			
					</td></tr></table>
				</td></tr></table>
					
				<table id="chequeNo" width="100%" style="display: none; padding-left: 67px;" align="left" ><tr><td>						
					<s:textfield label="Cheque No." id="cheque_no" required="true" style="height: 25px; width: 200px;"/>
				</td></tr></table>
				
				<table width="100%">
					<tr>
						<td align="center">
							<div class="save_update_btn" style="background-color: #f5f5f5; cursor: pointer; color: #3d6e9f; width: 300px; height: 25px; padding-top: 5px;">
								<div onclick="saveSelectedMaterialPayment('${si_id}', '${clid}', '${payment_type}', '${fname}', '${mname}', '${gname}')"> Save </div>
							</div>
						</td>
					</tr>
				</table>
				
			</td>
			<td>&nbsp;</td>
			<td style="vertical-align: top;">
			
				<div align="center" class="adminHeaderButton" style="width: 100%; padding-top: 6px;"> 
					Paid Materials List 
				</div>
				
				<table id="paid_mat_list" cellpadding="0" cellspacing="1" width="95%" align="right">
					<thead style="background-color: #f5f5f5; height: 30px; color: #000000" align="center">
						<tr height="30px;">
							<th width="15%">No</th>
							<th>Material</th>
							<th>Material Cost</th>
							<th>&nbsp;</th>
						</tr>
					</thead>
					<s:iterator status="stat" value="paid_material_list">
						<tr height="30px">
							<td align="center"> 
								<s:property value="%{#stat.index + 1}"/>
								<s:set var="ptm_id" value="paid_material_list[#stat.index].ptm_id"/>
								<s:set var="pcm_id" value="paid_material_list[#stat.index].pcm_id"/>
								<s:set var="material_pay_amount" value="paid_material_list[#stat.index].material_pay_amount"/>
							</td>
							<td> <s:property value="paid_material_list[#stat.index].ptm_name"/> </td>
							<td> <s:property value="paid_material_list[#stat.index].material_pay_amount"/> </td>
							<td align="center" >								
								<img alt="-" src="images/checked.png" width="18px"/>		
							</td>
						</tr>
					</s:iterator>
				</table>
				
			</td>
		</tr>
	</table>

</body>
</html>