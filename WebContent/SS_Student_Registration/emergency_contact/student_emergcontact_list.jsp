<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script src="js/jquery-1.6.1.js" type="text/javascript"></script>
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
                $('#contact_list').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>
<div id="emergcont_list">
	<s:set var="siid" value="si_id"/>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="background-color: #f5f5f5; height: 50px; color: #3d6e9f">
				<div onclick="addNewEergencyContact('${siid}')" style="cursor: pointer">
					<img alt="click" src="images/next.gif" width="8px"> Add New Emergency Contact Address
				</div>
			</td>
		</tr>
		<tr>
			<td>
				&nbsp;
			</td>
		</tr>
		<tr>
			<td>
				<s:set var="successful_save" value="successful_save"/>
				<s:set var="successful_update" value="successful_update"/>
				<s:if test="%{#successful_save == \"true\"}"> <span style="color: #3d6e9f"> Successfully Saved. </span> </s:if>
				<s:if test="%{#successful_update == \"true\"}"> <span style="color: #3d6e9f"> Successfully Updated. </span> </s:if>			
				
				<table id="contact_list" width="100%" cellpadding="0" cellspacing="2">
					<thead>
						<tr style="background-color: #f5f5f5; height: 30px; font-size: 14px">
							<th>No</th>
							<th>Contact Person</th>
							<th>Relationship</th>
							<th>Mobile No</th>
							<th>Office Phone No</th>
							<th>Home Phone No</th>
							<th>Status</th>
						</tr>
					</thead>
					<s:iterator status="stat" value="emergcont_rslt">
						<tr style="height: 30px">
							<td align="center">
								<s:property value="#stat.index+1"/>
								<s:set var="secid" value="emergcont_rslt[#stat.index].sec_id"/>
								<s:set var="contname" value="emergcont_rslt[#stat.index].contact_name"/>
								<s:set var="rel" value="emergcont_rslt[#stat.index].relationship"/>
								<s:set var="mobno" value="emergcont_rslt[#stat.index].mob_no"/>
								<s:set var="officephone" value="emergcont_rslt[#stat.index].office_phone_no"/>
								<s:set var="homephone" value="emergcont_rslt[#stat.index].home_phone_no"/>
								<s:set var="status" value="emergcont_rslt[#stat.index].sec_status"/>  
							</td>
							<td>
								<div onclick="emergencyContactEdit('${siid}', '${secid}', '${contname}', '${rel}', '${mobno}', '${officephone}', '${homephone}', '${status}')" style="color: #3d6e9f; cursor: pointer">
									<s:property value="emergcont_rslt[#stat.index].contact_name"/>
								</div>
							</td>
							<td>
								<div onclick="emergencyContactEdit('${siid}', '${secid}', '${contname}', '${rel}', '${mobno}', '${officephone}', '${homephone}', '${status}')" style="color: #3d6e9f; cursor: pointer">
									<s:property value="emergcont_rslt[#stat.index].relationship"/>
								</div>
							</td>
							<td>
								<s:property value="emergcont_rslt[#stat.index].mob_no"/>
							</td>
							<td>
								<s:property value="emergcont_rslt[#stat.index].office_phone_no"/>
							</td>
							<td>
								<s:property value="emergcont_rslt[#stat.index].home_phone_no"/>
							</td>
							<td>
								<s:if test="%{#status == \"A\"}">Active</s:if>
								<s:if test="%{#status == \"I\"}">Inative</s:if>
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