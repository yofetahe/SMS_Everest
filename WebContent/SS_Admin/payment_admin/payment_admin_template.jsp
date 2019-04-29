<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="../css/sms_css.css">
<script src="../js/jquery-1.6.1.js" type="text/javascript"></script>
<script src="../js/admin.js" type="text/javascript"></script>

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
                $('#pay_stud_list').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>
	<div id="payAdmin">
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td style="padding-top: 1px">
					<div style="background-color: #f5f5f5; height: 35px; padding-top: 15px; background-position: bottom;">
						<div style="cursor: pointer; color: #3d6e9f;" onclick="paymentInfo_create()"><img alt="click" src="images/next.gif" width="8px"> &nbsp;Add New Payment Info</div>
					</div>
				</td>
			</tr>
			<tr>
				<td style="padding-top: 10px">
					
						<table id="pay_stud_list" width="100%">
							<thead style="background-color: #f5f5f5; height: 30px; color: #000000" align="center">
								<tr>
									<td width="5%">No</td>
									<td width="15%">Class</td>
									<td width="15%">Payment Type</td>
									<td width="15%">Payment Amount</td>
									<td width="15%">Days for Penalty</td>
									<td width="10%">Penalty Rate</td>
									<td width="10%">Academic Year</td>
									<td width="10%">Status</td>
									<td width="5%">&nbsp;</td>
								</tr>
							</thead>
							<s:iterator status="stat" value="payment_info">
								<tr height="30px">
									<td>
										<s:property value="%{#stat.index + 1}"/>
									</td>
									<td>
										<s:property value="payment_info[#stat.index].class_name"/>
									</td>
									<td>
										<s:property value="payment_info[#stat.index].pt_name"/>
									</td>
									<td>
										<s:property value="payment_info[#stat.index].pay_amount"/>
									</td>
									<td>
										<s:property value="payment_info[#stat.index].penality_max_date"/>
									</td>
									<td>
										<s:property value="payment_info[#stat.index].penality_percent"/>
									</td>
									<td>
										<s:property value="payment_info[#stat.index].academic_year"/>
									</td>
									<td>
										<s:set var="status" value="payment_info[#stat.index].pc_status"/>
										<s:if test="%{#status == \"A\"}">Active</s:if>
										<s:else>Inactive</s:else>
									</td>
									<td>
										<div onclick="paymentInfo_edit('${stat.index}')" style="cursor: pointer; color: #3d6e9f; text-align: center;">Edit</div>
									</td>
								</tr>
							</s:iterator>
						</table>
					
				</td>
			</tr>
		</table>
	</div>
</body>
</html>