<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<script src="js/jquery-1.6.1.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">

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
                $('#stud_list').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>
	<s:set var="rownum" value="1"/>
	<s:set var="clid" value="class_id"/>
	<s:set var="cd_id" value="cd_id"/>
	<s:set var="pt_id" value="pt_id"/>
	
	<s:hidden id="clid" value="%{#clid}"/>
		<table id="stud_list" width="100%" cellpadding="0" cellspacing="2">
			<thead style="background-color: #f5f5f5; height: 30px; color: #000000" align="center">
				<tr>
					<td height="30px;" width="5%">No</td>
					<td>Student Name</td>
					<td>Last Month Payment</td>
<!-- 					<td width="6%">SEP</td> -->
<!-- 					<td width="6%">OCT</td> -->
<!-- 					<td width="6%">NOV</td> -->
<!-- 					<td width="6%">DEC</td> -->
<!-- 					<td width="6%">JAN</td> -->
<!-- 					<td width="6%">FEB</td> -->
<!-- 					<td width="6%">MAR</td> -->
<!-- 					<td width="6%">APR</td> -->
<!-- 					<td width="6%">MAY</td> -->
<!-- 					<td width="6%">JUN</td> -->
				</tr>
			</thead>
			<s:iterator status="stat" value="payment_table">
				<tr height="30px">
					<td align="center">
						<s:property value="%{#stat.index + 1}"/>
						<s:set var="siid" value="payment_table[#stat.index].si_id"/>
						<s:set var="fname" value="payment_table[#stat.index].fname"/>
						<s:set var="mname" value="mname"/>
						<s:set var="gname" value="gname"/>									
					</td>
					<td>
						<div style="color: #3d6e9f; cursor: pointer" onclick="showStudentPaymentHistory('${siid}', '${fname}', '${mname}', '${gname}', '${clid}', '${cd_id}')">
							<s:property value="fname"/> &nbsp; <s:property value="mname"/> &nbsp; <s:property value="gname"/>
						</div>									
					</td>
					<td>
						<s:property value="month_full"/>															
					</td>
<!-- 					<td align="center"> -->
					
<%-- 						<s:set var="sep" value="payment_table[#stat.index].sep"/> --%>
<%-- 						<s:if test="%{#sep == 1}"> --%>
<%-- 							<div onclick="getPaidReceipt('${sep}', '${siid}', '${fname}', '${mname}', '${gname}', '${pt_id}')" style="cursor: pointer"> --%>
<!-- 								<img alt="checked" src="images/checked.png" width="14px"> -->
<%-- 							</div>	 --%>
<%-- 						</s:if>	 --%>
<%-- 						<s:else> --%>
<%-- 							<div onclick="selectedMonth('sep', '${siid}', '${fname}', '${mname}', '${gname}', '${pt_id}')" style="cursor: pointer"> --%>
<!-- 								<img alt="-" src="images/check_box.png" width="18px"/> -->
<%-- 							</div> --%>
<%-- 						</s:else> --%>
													
<!-- 					</td> -->
<!-- 					<td align="center"> -->
						
<%-- 						<s:set var="oct" value="payment_table[#stat.index].oct"/> --%>
<%-- 						<s:if test="%{#oct == 2}"> --%>
<%-- 							<div onclick="getPaidReceipt('${oct}', '${siid}', '${fname}', '${mname}', '${gname}', '${pt_id}')" style="cursor: pointer"> --%>
<!-- 								<img alt="checked" src="images/checked.png" width="14px"> -->
<%-- 							</div>	 --%>
<%-- 						</s:if>	 --%>
<%-- 						<s:else> --%>
<%-- 							<s:if test="%{#sep == 1 || #sep == 0}"> --%>
<%-- 								<div onclick="selectedMonth('oct', '${siid}', '${fname}', '${mname}', '${gname}', '${pt_id}')" style="cursor: pointer"> --%>
<!-- 									<img alt="-" src="images/check_box.png" width="18px"/> -->
<%-- 								</div> --%>
<%-- 							</s:if> --%>
<%-- 							<s:else>-</s:else>								 --%>
<%-- 						</s:else> --%>
						
<!-- 					</td> -->
<!-- 					<td align="center"> -->
					
<%-- 						<s:set var="nov" value="payment_table[#stat.index].nov"/> --%>
<%-- 						<s:if test="%{#nov == 3}"> --%>
<%-- 							<div onclick="getPaidReceipt('${nov}', '${siid}', '${fname}', '${mname}', '${gname}', '${pt_id}')" style="cursor: pointer"> --%>
<!-- 								<img alt="checked" src="images/checked.png" width="14px"> -->
<%-- 							</div>	 --%>
<%-- 						</s:if>	 --%>
<%-- 						<s:else> --%>
<%-- 							<s:if test="%{#oct == 2 || #oct == 0}"> --%>
<%-- 								<div onclick="selectedMonth('nov', '${siid}', '${fname}', '${mname}', '${gname}', '${pt_id}')" style="cursor: pointer"> --%>
<!-- 									<img alt="-" src="images/check_box.png" width="18px"/> -->
<%-- 								</div> --%>
<%-- 							</s:if> --%>
<%-- 							<s:else>-</s:else> --%>
<%-- 						</s:else> --%>
							
<!-- 					</td>								 -->
<!-- 					<td align="center"> -->
					
<%-- 						<s:set var="dec" value="payment_table[#stat.index].dec"/> --%>
<%-- 						<s:if test="%{#dec == 4}"> --%>
<%-- 							<div onclick="getPaidReceipt('${dec}', '${siid}', '${fname}', '${mname}', '${gname}', '${pt_id}')" style="cursor: pointer"> --%>
<!-- 								<img alt="checked" src="images/checked.png" width="14px"> -->
<%-- 							</div>	 --%>
<%-- 						</s:if>	 --%>
<%-- 						<s:else> --%>
<%-- 							<s:if test="%{#nov == 3 || #nov == 0}"> --%>
<%-- 								<div onclick="selectedMonth('dec', '${siid}', '${fname}', '${mname}', '${gname}', '${pt_id}')" style="cursor: pointer"> --%>
<!-- 									<img alt="-" src="images/check_box.png" width="18px"/> -->
<%-- 								</div> --%>
<%-- 							</s:if> --%>
<%-- 							<s:else>-</s:else> --%>
<%-- 						</s:else> --%>
							
<!-- 					</td> -->
<!-- 					<td align="center"> -->
								
<%-- 						<s:set var="jan" value="payment_table[#stat.index].jan"/> --%>
<%-- 						<s:if test="%{#jan == 5}"> --%>
<%-- 							<div onclick="getPaidReceipt('${jan}', '${siid}', '${fname}', '${mname}', '${gname}', '${pt_id}')" style="cursor: pointer"> --%>
<!-- 								<img alt="checked" src="images/checked.png" width="14px"> -->
<%-- 							</div>	 --%>
<%-- 						</s:if>	 --%>
<%-- 						<s:else> --%>
<%-- 							<s:if test="%{#dec == 4 || #dec == 0}"> --%>
<%-- 								<div onclick="selectedMonth('jan', '${siid}', '${fname}', '${mname}', '${gname}', '${pt_id}')" style="cursor: pointer"> --%>
<!-- 									<img alt="-" src="images/check_box.png" width="18px"/> -->
<%-- 								</div> --%>
<%-- 							</s:if> --%>
<%-- 							<s:else>-</s:else> --%>
<%-- 						</s:else>		 --%>
														
<!-- 					</td> -->
<!-- 					<td align="center"> -->
					
<%-- 						<s:set var="feb" value="payment_table[#stat.index].feb"/> --%>
<%-- 						<s:if test="%{#feb == 6}"> --%>
<%-- 							<div onclick="getPaidReceipt('${feb}', '${siid}', '${fname}', '${mname}', '${gname}', '${pt_id}')" style="cursor: pointer"> --%>
<!-- 								<img alt="checked" src="images/checked.png" width="14px"> -->
<%-- 							</div>	 --%>
<%-- 						</s:if>	 --%>
<%-- 						<s:else> --%>
<%-- 							<s:if test="%{#jan == 5 || #jan == 0}"> --%>
<%-- 								<div onclick="selectedMonth('feb', '${siid}', '${fname}', '${mname}', '${gname}', '${pt_id}')" style="cursor: pointer"> --%>
<!-- 									<img alt="-" src="images/check_box.png" width="18px"/> -->
<%-- 								</div> --%>
<%-- 							</s:if> --%>
<%-- 							<s:else>-</s:else> --%>
<%-- 						</s:else> --%>
									
<!-- 					</td> -->
<!-- 					<td align="center"> -->
						
<%-- 						<s:set var="mar" value="payment_table[#stat.index].mar"/> --%>
<%-- 						<s:if test="%{#mar == 7}"> --%>
<%-- 							<div onclick="getPaidReceipt('${mar}', '${siid}', '${fname}', '${mname}', '${gname}', '${pt_id}')" style="cursor: pointer"> --%>
<!-- 								<img alt="checked" src="images/checked.png" width="14px"> -->
<%-- 							</div>	 --%>
<%-- 						</s:if>	 --%>
<%-- 						<s:else> --%>
<%-- 							<s:if test="%{#feb == 6}"> --%>
<%-- 								<div onclick="selectedMonth('mar', '${siid}', '${fname}', '${mname}', '${gname}', '${pt_id}')" style="cursor: pointer"> --%>
<!-- 									<img alt="-" src="images/check_box.png" width="18px"/> -->
<%-- 								</div> --%>
<%-- 							</s:if> --%>
<%-- 							<s:else>-</s:else> --%>
<%-- 						</s:else> --%>
<!-- 					</td>								 -->
<!-- 					<td align="center"> -->
								
<%-- 						<s:set var="apr" value="payment_table[#stat.index].apr"/> --%>
<%-- 						<s:if test="%{#apr == 8}"> --%>
<%-- 							<div onclick="getPaidReceipt('${apr}', '${siid}', '${fname}', '${mname}', '${gname}', '${pt_id}')" style="cursor: pointer"> --%>
<!-- 								<img alt="checked" src="images/checked.png" width="14px"> -->
<%-- 							</div>	 --%>
<%-- 						</s:if>	 --%>
<%-- 						<s:else> --%>
<%-- 							<s:if test="%{#mar == 7}"> --%>
<%-- 								<div onclick="selectedMonth('apr', '${siid}', '${fname}', '${mname}', '${gname}', '${pt_id}')" style="cursor: pointer"> --%>
<!-- 									<img alt="-" src="images/check_box.png" width="18px"/> -->
<%-- 								</div> --%>
<%-- 							</s:if> --%>
<%-- 							<s:else>-</s:else> --%>
<%-- 						</s:else> --%>
						
<!-- 					</td> -->
<!-- 					<td align="center"> -->
					
<%-- 						<s:set var="may" value="payment_table[#stat.index].may"/> --%>
<%-- 						<s:if test="%{#may == 9}"> --%>
<%-- 							<div onclick="getPaidReceipt('${may}', '${siid}', '${fname}', '${mname}', '${gname}', '${pt_id}')" style="cursor: pointer"> --%>
<!-- 								<img alt="checked" src="images/checked.png" width="14px"> -->
<%-- 							</div>	 --%>
<%-- 						</s:if>	 --%>
<%-- 						<s:else> --%>
<%-- 							<s:if test="%{#apr == 8}"> --%>
<%-- 								<div onclick="selectedMonth('may', '${siid}', '${fname}', '${mname}', '${gname}', '${pt_id}')" style="cursor: pointer"> --%>
<!-- 									<img alt="-" src="images/check_box.png" width="18px"/> -->
<%-- 								</div> --%>
<%-- 							</s:if> --%>
<%-- 							<s:else>-</s:else> --%>
<%-- 						</s:else> --%>
						
<!-- 					</td>								 -->
<!-- 					<td align="center"> -->
					
<%-- 						<s:set var="jun" value="payment_table[#stat.index].jun"/> --%>
<%-- 						<s:if test="%{#jun == 10}"> --%>
<%-- 							<div onclick="getPaidReceipt('${jun}', '${siid}', '${fname}', '${mname}', '${gname}', '${pt_id}')" style="cursor: pointer"> --%>
<!-- 								<img alt="checked" src="images/checked.png" width="14px"> -->
<%-- 							</div>	 --%>
<%-- 						</s:if>	 --%>
<%-- 						<s:else> --%>
<%-- 							<s:if test="%{#may == 9}"> --%>
<%-- 								<div onclick="selectedMonth('jun', '${siid}', '${fname}', '${mname}', '${gname}', '${pt_id}')" style="cursor: pointer"> --%>
<!-- 									<img alt="-" src="images/check_box.png" width="18px"/> -->
<%-- 								</div> --%>
<%-- 							</s:if> --%>
<%-- 							<s:else>-</s:else> --%>
<%-- 						</s:else> --%>
						
<!-- 					</td>							 -->
				</tr>
				<s:set var="rownum" value="%{#rownum + 1}"/>
			</s:iterator>
		</table>
		
</body>
</html>