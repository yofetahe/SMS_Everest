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
                $('#markList').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

	<table cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td style="background-color: #f5f5f5; padding-left: 5px; height: 70px; border-bottom-color: silver; border-bottom-style: solid; border-bottom-width: thin;">
				Name - <span style="color: #3d6e9f"> <s:property value="fname"/> &nbsp; <s:property value="mname"/> &nbsp; <s:property value="gname"/> </span> <br/>
				Class - <span style="color: #3d6e9f"> <s:property value="cl_name"/> - <s:property value="cd_name"/> </span> <br/>
				Rank - <span style="color: #3d6e9f"> <s:property value="stud_rank"/> </span> <br/>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 3px;">
				<s:set var="cl_id" value="cl_id"/>
				<s:set var="si_id" value="si_id"/>
				
				<div id="sub_detail">
				
				<table cellpadding="0" cellspacing="1" width="100%" id="markList">
					<thead >
						<tr style="background-color: #f5f5f5; font-size: 14px; height: 30px">
							<th width="5%">No</th>
							<th width="30%">Subject</th>
							<th width="10%">Mark</th>
							<th width="10%">Pass Mark</th>
							<th width="10%">Status</th>
							<th width="10%">&nbsp;</th>
							<th>&nbsp;</th>
						</tr>
					</thead>
					<s:iterator status="stat" value="studentMarkList">				
						<tr style="height: 30px">
							<td align="center">
								<s:property value="#stat.index + 1"/>
								<s:set var="sub_id" value="studentMarkList[#stat.index].sub_id"/>
								<s:property value="studentMarkList[#stat.index].exsub_id"/>
							</td>
							<td>
								<s:property value="studentMarkList[#stat.index].sub_name"/>
								<s:set var="sub_name" value="studentMarkList[#stat.index].sub_name"/>
							</td>
							<td>
								<s:set var="mrk" value="studentMarkList[#stat.index].subjectTotal_mark"/>
								<s:property value="studentMarkList[#stat.index].subjectTotal_mark"/>/<s:property value="studentMarkList[#stat.index].grandTotal_mark"/>
							</td>
							<td>
								<s:property value="studentMarkList[#stat.index].Pass_mark"/>
							</td>
							<td>
								<s:property value="studentMarkList[#stat.index].mark_status"/> 
							</td>
							<td style="text-align: center">
								<div style="color: #3d6e9f; width: 100%; cursor: pointer;" onclick="SubExamRsltDetail('${cl_id}', '${si_id}', '${sub_id}', '${sub_name}')">
									Detail
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