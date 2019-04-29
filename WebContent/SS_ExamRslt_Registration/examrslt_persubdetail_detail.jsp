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
                $('#sub_rslt_detail').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>
	
	<table cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				Subject - <s:property value="sub_name"/>
			</td>
		</tr>
		<tr>
			<td>
				<div id="rsltEditForm">
				
					<table cellpadding="0" cellspacing="1" width="100%" id="sub_rslt_detail">
						<thead>
							<tr style="background-color: #f5f5f5; font-size: 14px; height: 30px">
								<th width="5%">No</th>
								<th width="35%">Exam Type</th>
								<th width="10%">Result</th>
								<th width="10%">Pass Mark</th>
								<th width="10%">Status</th>
								<th width="10%">&nbsp;</th>
								<th>&nbsp;</th>
							</tr>
						</thead>
						<s:iterator status="stat" value="markPerExamType">
							<tr>
								<td align="center" height="30px">
									<s:property value="#stat.index + 1"/>
									<s:set var="er_id" value="er_id"/>
								</td>
								<td>
									<s:property value="markPerExamType[#stat.index].et_name"/>
									<s:set var="et_id" value="markPerExamType[#stat.index].et_id"/>
								</td>
								<td>
									<s:set var="rslt" value="markPerExamType[#stat.index].result"/>
									<s:set var="maxRslt" value="markPerExamType[#stat.index].sub_totalmark"/>
									<s:property value="markPerExamType[#stat.index].result"/>/<s:property value="markPerExamType[#stat.index].sub_totalmark"/>
								</td>
								<td>
									<s:property value="markPerExamType[#stat.index].sub_passmark"/>
								</td>
								<td>
									<s:property value="markPerExamType[#stat.index].submark_status"/>
								</td>
								<td align="center" style="color: #3d6e9f">
									<div style="cursor: pointer;" onclick="editSubRslt('${er_id}', '${rslt}', '${maxRslt}')">
										Edit
									</div>
								</td>
								<td>&nbsp;</td>
							</tr>
						</s:iterator>
					</table>
				
				</div>
				
			</td>
		</tr>
	</table>

</body>
</html>