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
                $('#payment_col').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>
<div id="collResult" style="width: 80%; padding-top: 10px;">

	<s:set var="rp_type" value="report_type"/>
	<s:set var="date_from" value="col_date_from"/>
	<s:set var="date_to" value="col_date_to"/>
	<s:set var="month_from" value="col_month_from"/>
	<s:set var="month_to" value="col_month_to"/>
	<s:set var="total_collection" value="grand_total"/>

	<s:if test="%{#rp_type == \"Daily\"}">
		<div class="save_update_btn" onclick="emailCollection('${date_from}', '${date_to}', '${total_collection}')" style="height: 30px; width: 180px; padding-bottom: 5px; padding-left: 25px; cursor: pointer; text-align: center;">
			<table><tr><td>
				<img alt="email" src="images/email.png" height="30px">
			</td><td valign="middle">	
				Email Collection
			</td></tr></table>
		</div>
	</s:if>
	
	<table id="payment_col" width="100%" ><thead><tr><td align="center" style="font-weight: bold; font-size: 20px; height: 60px; vertical-align: middle;">
		<s:text name="global.schoolname"/> <br/>
				
		<span style="font-size: 14px;">
		<s:if test="%{#rp_type == \"Daily\"}">
			<s:if test="%{#date_from == #date_to}">
				<s:property value="col_date_from"/>
			</s:if><s:else>
				<s:property value="col_date_from"/> - <s:property value="col_date_to"/>
			</s:else>
		</s:if>
		<s:elseif test="%{#rp_type == \"Monthly\"}">
			<s:if test="%{#month_from == #month_to}">
				<s:property value="col_month_from"/>
			</s:if><s:else>
				<s:property value="col_month_from"/> - <s:property value="col_month_to"/>
			</s:else>			
		</s:elseif>
		Collection
		</span>
	</td></tr></thead><tr><td>
	
	<s:set var="row_num" value="0"/>
	
	<table id="payment_col" width="100%" cellpadding="0" cellspacing="1" rules="rows" bgcolor="#fff">
		<thead style="background-color: #f5f5f5; text-align: center;">
			<tr height="30px;">
				<th> No. </th>
				<th> Receipt No. </th>
				<th> Student Name </th>
				<th> Grade </th>
				<th> Date </th>
				<th> Purpose </th>
				<th> Amount </th>
			</tr>
		</thead>
		<s:iterator status="stat" value="payment_collection">
			<tr style="height: 30px;">
				<td align="center">
					<s:set var="row_num" value="%{#stat.index + 1}"/>
					<s:property value="%{#row_num}"/>
				</td>
				<td align="center">
					<s:property value="payment_collection[#stat.index].payment_bean.fm_receipt_no"/>
				</td>
				<td>
					<s:property value="payment_collection[#stat.index].payment_bean.fname"/> <s:property value="payment_collection[#stat.index].payment_bean.mname"/> <s:property value="payment_collection[#stat.index].payment_bean.gname"/>
				</td>
				<td>
					<s:property value="payment_collection[#stat.index].payment_bean.class_name"/> <s:property value="payment_collection[#stat.index].payment_bean.cd_name"/>
				</td>
				<td align="center">
					<s:property value="payment_collection[#stat.index].payment_bean.payment_date"/>
				</td>
				<td align="center">
					<s:property value="payment_collection[#stat.index].payment_bean.pt_name"/>
				</td>
				<td align="right">
					<s:property value="payment_collection[#stat.index].payment_bean.pay_amount"/> 
				</td>
			</tr>			
		</s:iterator>
		<tr style="height: 30px;">
				<td align="center" style="color:background; font-size: 1px;"> <s:property value="%{#row_num + 1}"/> </td>
				<td> &nbsp; </td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td align="center">&nbsp;</td>
				<td style="font-weight: bold;" align="center">
					Total Collection
				</td>
				<td align="right" style="font-weight: bold;">
					<s:property value="grand_total"/> 
				</td>
			</tr>
	</table>
	</td></tr></table>
</div>
</body>
</html>