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
                $('#selStud').dataTable( {
                    //"sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

	<div id="regSuccuss">
	
	<s:set var="selclid" value="selcl_id"/>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td align="center" style="border-bottom-style: solid; border-bottom-width: thin; border-top-style: solid; border-top-width: thin; border-color: silver; background-color: #f5f5f5;">
				<div id="errMsg" style="color: red; width: auto; text-align: center;"></div>
				<table cellpadding="5">
					<tr><td>						
						<s:select label="Please select the section" headerValue="Section List" headerKey="-1" list="section_list" listKey="sec_id" listValue="sec_name"  onchange="sectionSelected( this.value )" style="width: 156px; height: 20px;"/>
						<s:textfield label="Academic Year" id="acYear" name="academic_year" readonly="true" style="width: 150px; height: 20px;"/>
					</td></tr>
				</table>
				 
			</td>
		</tr>		
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
				<s:set var="rawcount" value="0"/>
				<div id="container">
					<table width="100%" id="selStud">
						<thead>
							<tr style="background-color: #f5f5f5; font-size: 14px; height: 30px">
								<td align="center">No</td>
								<td>Student Name</td>
								<td></td>
							</tr>
						</thead>
						<s:iterator status="stat" value="selStud_rslt">
							<tr style="height: 35px;">
								<td align="center">
									<s:property value="#stat.index + 1"/>
									<s:set var="inde" value="#stat.index"/>
									<s:set var="studid" value="selStud_rslt[#stat.index].selstud_id"/>
								</td>
								<td>
									<s:property value="selStud_rslt[#stat.index].selstud_name"/>									
								</td>
								<td align="center" style="color: #3d6e9f; cursor: pointer;">
									<div id="${studid}_add" onclick="removedStudentListFromSelectedStudList('${inde}', '${studid}', '${selclid}')">
										Remove
									</div>
								</td>			
							</tr>
							<s:set var="rawcount" value="%{#rawcount + 1}"/>
						</s:iterator>
					</table>	
				</div>
				
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td style="background-color: #f5f5f5; cursor: pointer; color: #3d6e9f; height: 30px" align="center">
				<div onclick="registerStudent('${selclid}', '${rawcount}')">
					Register
				</div>
			</td>
		</tr>
	</table>
	
	</div>
</body>
</html>