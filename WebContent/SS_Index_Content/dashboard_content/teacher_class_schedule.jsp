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
                $('#teacherscheduletable').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>
	
	<s:set var="noofcopies" value="noofcopies"/>
	
	<table width="100%" cellpadding="0" cellspacing="0">
		<thead>
		<tr><td>&nbsp;</td></tr>
		</thead>
		<tr><td>&nbsp;</td></tr>
<%-- 		<s:iterator begin="0" end="%{#noofcopies-1}"> --%>
			<tr><td>
			<table width="100%" style="padding-top: 10px; background-color: #fff;">
				<s:iterator status="stat" value="sortedClassSchedulePerTeacher">
					<tr>
						<s:iterator status="st" value="sortedClassSchedulePerTeacher[#stat.index]">
							<s:if test="%{#stat.index == \"0\"}">
								<td style="background-color: #e5e5e5; height: 50px; padding-left: 5px; text-align: center; color: #3d6e9f; width: 15px; border-color: #e5e5e5;">
									<s:property value="sortedClassSchedulePerTeacher[#stat.index][#st.index].period_name"/>
								</td>
							</s:if>
							<s:elseif test="%{#st.index == \"0\"}">
								<td style="background-color: #e5e5e5; height: 50px; padding-left: 5px; text-align: center; color: #3d6e9f; width: 15px; border-color: #e5e5e5;">
									<s:property value="sortedClassSchedulePerTeacher[#stat.index][#st.index].day_name"/>
								</td>
							</s:elseif>
							<s:else>
								<td style="border-color: #e5e5e5; border-style: solid; border-width: thin; text-align: center; vertical-align: top; padding-top: 10px; width: 15px;">
									<s:property value="sortedClassSchedulePerTeacher[#stat.index][#st.index].sub_name"/><br/>
									<span style="font-weight: bold"><s:property value="sortedClassSchedulePerTeacher[#stat.index][#st.index].class_name"/> <s:property value="sortedClassSchedulePerTeacher[#stat.index][#st.index].cd_name"/></span>
								</td>
							</s:else>
						</s:iterator>
					</tr>
				</s:iterator>
			</table>
			</td></tr>
<%-- 		</s:iterator> --%>
	</table>

</body>
</html>