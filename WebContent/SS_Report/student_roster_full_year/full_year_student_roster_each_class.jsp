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
                $('#fullyearstudroster').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

	<div style="width: auto; text-align: center; color: green">
		PDF Successfully Created!!!
	</div>

<%-- 	<s:set var="index" value="0"/> --%>
<%-- 	<s:set var="index_1" value="0"/> --%>
<%-- 	<s:set var="display_name" value=""/> --%>
	
<!-- 	<table id="fullyearstudroster" width="100%" border="0"> -->
<!-- 		<thead> -->
<!-- 			<tr> -->
<!-- 				<th> -->
<!-- 					Student Name -->
<!-- 				</th> -->
<!-- 				<th> -->
<!-- 					Sex -->
<!-- 				</th> -->
<!-- 				<th> -->
<!-- 					Age -->
<!-- 				</th> -->
<!-- 				<th> -->
<!-- 					Term -->
<!-- 				</th> -->
<%-- 				<s:iterator status="sb_stat" value="subList"> --%>
<!-- 					<th style="width: 15px; color: #3d6e9f; -webkit-transform: rotate(-90deg); 	-moz-transform: rotate(-90deg); -ms-transform: rotate(-90deg); -o-transfrom: rotate(-90deg); transform: rotate(-90deg);
<!-- 									filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=0.083); text-align: center; height: 100px;"> -->
<%-- 						<s:property value="subList[#sb_stat.index].sub_name"/> --%>
<!-- 					</th> -->
<%-- 				</s:iterator> --%>
<!-- 				<th width="0px;">&nbsp;</th> -->
<!-- 				<th> -->
<!-- 					Sum -->
<!-- 				</th> -->
<!-- 				<th> -->
<!-- 					Average -->
<!-- 				</th> -->
<!-- 			</tr> -->
<!-- 		</thead> -->
<%-- 		<s:iterator status="stud_stat" value="fullYearStudRoster"> --%>
<!-- 			<tr> -->
<!-- 				<td> -->
<%-- 					<s:set var="fname" value="fullYearStudRoster[#stud_stat.index][0].fname"/>  --%>
<%-- 					<s:set var="mname" value="fullYearStudRoster[#stud_stat.index][0].mname"/>  --%>
<%-- 					<s:set var="gname" value="fullYearStudRoster[#stud_stat.index][0].gname"/> --%>
					
<%-- 					<s:if test="%{#stud_stat.index == \"0\"}"> --%>
<%-- 						<s:property value="fullYearStudRoster[#stud_stat.index][0].fname"/> <s:property value="fullYearStudRoster[#stud_stat.index][0].mname"/> <s:property value="fullYearStudRoster[#stud_stat.index][0].gname"/> --%>
<%-- 						<s:set var="display_name" value="%{#fname + \" \" + #mname + \" \" + #gname}"/> --%>
<%-- 					</s:if> --%>
<%-- 					<s:elseif test="%{#stud_stat.index != \"0\" && #display_name != (fullYearStudRoster[#stud_stat.index][0].fname + \" \" + fullYearStudRoster[#stud_stat.index][0].mname + \" \" + fullYearStudRoster[#stud_stat.index][0].gname)}"> --%>
<%-- 						<s:property value="fullYearStudRoster[#stud_stat.index][0].fname"/> <s:property value="fullYearStudRoster[#stud_stat.index][0].mname"/> <s:property value="fullYearStudRoster[#stud_stat.index][0].gname"/> --%>
<%-- 						<s:set var="display_name" value="%{#fname + \" \" + #mname + \" \" + #gname}"/> --%>
<%-- 					</s:elseif> --%>
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 					<s:property value="fullYearStudRoster[#stud_stat.index][0].stud.sex"/> --%>
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 					<s:property value="fullYearStudRoster[#stud_stat.index][0].stud.age"/> --%>
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 					<s:property value="fullYearStudRoster[#stud_stat.index][0].at_id"/> --%>
<!-- 				</td> -->
<%-- 				<s:iterator status="rslt_stat" value="fullYearStudRoster[#stud_stat.index]"> --%>
					
<!-- 					<td> -->
<%-- 						<s:property value="fullYearStudRoster[#stud_stat.index][#rslt_stat.index].subjectTotal_mark"/> --%>
<!-- 					</td> -->
<%-- 					<s:set var="index_1" value="%{#index_1 + 1}"/> --%>
					
<%-- 				</s:iterator> --%>
<!-- 				<td> -->
<%-- 					<s:property value="fullYearStudRoster[#stud_stat.index][#index_1-1].quarter_grand_total"/> --%>
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 					<s:property value="fullYearStudRoster[#stud_stat.index][#index_1-1].averageMarkTotal"/> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<%-- 			<s:set var="index_1" value="0"/> --%>
<%-- 			<s:set var="index" value="%{#index + 1}"/> --%>
<%-- 		</s:iterator> --%>
<!-- 	</table> -->

</body>
</html>