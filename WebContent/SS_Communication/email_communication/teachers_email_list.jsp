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
                $('#teach_email_list').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

<div id="teachEmailList">

<table width="70%" cellpadding="0" cellspacing="0">
<tr><td valign="top">

	<s:set var="from" value="from"/>
	<s:set var="password" value="password"/>
	<s:set var="subject" value="subject"/>
	<s:set var="body" value="body"/>
	
	<table id="teach_email_list" cellpadding="0" cellspacing="1" width="100%">
		<thead style="background-color: #f5f5f5;">
			<tr style="height: 30px;" align="center">
				<th>No</th>
				<th>Teacher Name</th>
				<th>E-mail address</th>
				<th>
					<div style="color: #3d6e9f; cursor: pointer;" onclick="addAllEmailIntoList('${from}', '${password}', '${subject}', '${body}')">Add All</div>
				</th>
			</tr>
		</thead>
		<s:iterator status="stat" value="teachersEmailList">
			<tr style="height: 30px;">
				<td align="center">
					<s:property value="%{#stat.index + 1}"/>
					<s:set var="index" value="%{#stat.index}"/>
				</td>
				<td>
					<s:property value="teachersEmailList[#stat.index].fullName"/>
				</td>
				<td>
					<s:set var="email" value="teachersEmailList[#stat.index].email"/>
					<s:property value="%{#email}"/>
				</td>
				<td align="center">
					<div id="${index}_e">
					<div style="color: #3d6e9f; cursor: pointer;" onclick="addEmailIntoList('${email}', '${stat.index}')">
						<img alt="check_box" src="images/check_box.png">
					</div>
					</div>
				</td>
			</tr>
		</s:iterator>
	</table>

	<table width="100%"><tr><td align="center">
		<div class="save_update_btn" onclick="emailToSelectedList('${from}', '${password}', '${subject}', '${body}')" style="background-color: #f5f5f5; cursor: pointer; color: #3d6e9f; width: 300px; height: 25px; padding-top: 5px; text-align: center;">
			Email To Selected
		</div>
	</td></tr></table>	

</td></tr></table>

</div>

</body>
</html>