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
	<s:set var="pt_id" value="pt_id"/>
	
	<s:hidden id="clid" value="%{#clid}"/>
		<table id="stud_list" width="100%" cellpadding="0" cellspacing="2">
			<thead style="background-color: #f5f5f5; height: 30px; color: #000000" align="center">
				<tr>
					<td height="30px;">No</td>
					<td width="35%">Student Name</td>
					<td width="6%">SEP</td>
					<td width="6%">OCT</td>
					<td width="6%">NOV</td>
					<td width="6%">DEC</td>
					<td width="6%">JAN</td>
					<td width="6%">FEB</td>
					<td width="6%">MAR</td>
					<td width="6%">APR</td>
					<td width="6%">MAY</td>
					<td width="6%">JUN</td>
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
						<div style="color: #3d6e9f;">
							<s:property value="fname"/> &nbsp; <s:property value="mname"/> &nbsp; <s:property value="gname"/>
						</div>									
					</td>
					<td align="center">
					
						<s:set var="sep" value="payment_table[#stat.index].sep"/>
						<s:if test="%{#sep == 1}">
							<img alt="checked" src="images/checked.png" width="14px">	
						</s:if>	
						<s:else>
							-
						</s:else>
													
					</td>
					<td align="center">
					
						<s:set var="oct" value="payment_table[#stat.index].oct"/>
						<s:if test="%{#oct == 2}">
							<img alt="checked" src="images/checked.png" width="14px">	
						</s:if>	
						<s:else>
							-
						</s:else>
						
					</td>
					<td align="center">
					
						<s:set var="nov" value="payment_table[#stat.index].nov"/>
						<s:if test="%{#nov == 3}">
							<img alt="checked" src="images/checked.png" width="14px">	
						</s:if>	
						<s:else>
							-
						</s:else>
							
					</td>								
					<td align="center">
					
						<s:set var="dec" value="payment_table[#stat.index].dec"/>
						<s:if test="%{#dec == 4}">
							<img alt="checked" src="images/checked.png" width="14px">	
						</s:if>	
						<s:else>
							-
						</s:else>
							
					</td>
					<td align="center">
								
						<s:set var="jan" value="payment_table[#stat.index].jan"/>
						<s:if test="%{#jan == 5}">
							<img alt="checked" src="images/checked.png" width="14px">	
						</s:if>	
						<s:else>
							-
						</s:else>		
														
					</td>
					<td align="center">
					
						<s:set var="feb" value="payment_table[#stat.index].feb"/>
						<s:if test="%{#feb == 6}">
							<img alt="checked" src="images/checked.png" width="14px">	
						</s:if>	
						<s:else>
							-
						</s:else>
									
					</td>
					<td align="center">
						
						<s:set var="mar" value="payment_table[#stat.index].mar"/>
						<s:if test="%{#mar == 7}">
							<img alt="checked" src="images/checked.png" width="14px">	
						</s:if>	
						<s:else>
							-
						</s:else>
					</td>								
					<td align="center">
								
						<s:set var="apr" value="payment_table[#stat.index].apr"/>
						<s:if test="%{#apr == 8}">
							<img alt="checked" src="images/checked.png" width="14px">	
						</s:if>	
						<s:else>
							-
						</s:else>
						
					</td>
					<td align="center">
					
						<s:set var="may" value="payment_table[#stat.index].may"/>
						<s:if test="%{#may == 9}">
							<img alt="checked" src="images/checked.png" width="14px">	
						</s:if>	
						<s:else>
							-
						</s:else>
						
					</td>								
					<td align="center">
					
						<s:set var="jun" value="payment_table[#stat.index].jun"/>
						<s:if test="%{#jun == 10}">
							<img alt="checked" src="images/checked.png" width="14px">	
						</s:if>	
						<s:else>
							-
						</s:else>
						
					</td>							
				</tr>
				<s:set var="rownum" value="%{#rownum + 1}"/>
			</s:iterator>
		</table>
		
</body>
</html>