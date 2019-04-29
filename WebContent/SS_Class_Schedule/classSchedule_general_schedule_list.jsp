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
                $('#teacherscheduletable1').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>
	
	<div class="save_update_btn" onclick="generateGeneralSchedulePDF()" style="height: 30px; width: 240px; padding-top: 1px; padding-left: 25px; cursor: pointer; text-align: center;">
		<table><tr><td>
			<img alt="PDF" src="images/pdf.gif" height="25px">
		</td><td valign="middle">
			Generate Schedule PDF
		</td></tr></table>
	</div>
	
	<div id="pdfSuccessMsg">&nbsp;</div>
	
	<table id="teacherscheduletable" width="100%" cellpadding="0" cellspacing="0">
		<s:iterator status="stt" value="generalSortedClassSchedule">
			<tr><td>			
			<table width="100%" style="padding-top: 10px; background-color: #fff;">
				<tr>
					<td colspan="3">
						<span style="font-weight: bold"> <s:property value="%{#stt.index + 1}"/> - <s:property value="generalSortedClassSchedule[#stt.index][1][1].class_name"/> <s:property value="generalSortedClassSchedule[#stt.index][1][1].cd_name"/></span>
					</td>
				</tr>
				<s:iterator status="stat" value="generalSortedClassSchedule[#stt.index]">
					<tr>
						<s:iterator status="st" value="generalSortedClassSchedule[#stt.index][#stat.index]">
							<s:if test="%{#stat.index == \"0\"}">
								<td style="background-color: #f5f5f5; height: 30px; padding-left: 5px; text-align: center; color: #3d6e9f; width: 15px; border-color: #f5f5f5;">
									<s:property value="generalSortedClassSchedule[#stt.index][#stat.index][#st.index].period_name"/>
								</td>
							</s:if>
							<s:elseif test="%{#st.index == \"0\"}">
								<td style="background-color: #f5f5f5; padding-left: 5px; text-align: center; color: #3d6e9f; width: 15px; border-color: #f5f5f5;">
									<s:property value="generalSortedClassSchedule[#stt.index][#stat.index][#st.index].day_name"/>
								</td>
							</s:elseif>
							<s:else>
								<td style="border-color: #f5f5f5; border-style: solid; border-width: thin; text-align: center; vertical-align: top; width: 15px;">
									<span style="font-weight: bold"><s:property value="generalSortedClassSchedule[#stt.index][#stat.index][#st.index].tfname"/> <s:property value="generalSortedClassSchedule[#stt.index][#stat.index][#st.index].tmname"/></span><br/>
									<s:property value="generalSortedClassSchedule[#stt.index][#stat.index][#st.index].sub_name"/>
								</td>
							</s:else>
						</s:iterator>
					</tr>
				</s:iterator>
			</table>
			</td></tr>
		</s:iterator>
	</table>

</body>
</html>