<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">
<script type="text/javascript" src="js/jquery-1.6.1.js" ></script>

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
                $('#studroster').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

<style type="text/css">
#textRotate{
	width: 15px; 
	color: #3d6e9f; 
	-webkit-transform: rotate(-90deg); 	
	-moz-transform: rotate(-90deg); 
	-ms-transform: rotate(-90deg); 
	-o-transfrom: rotate(-90deg); 
	transform: rotate(-90deg);	
	filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=0.083); 
	text-align: center; 
	height: 100px;
}
</style>
</head>
<body>
	
	<s:set var="at_id" value="at_id"/>
	<s:set var="cl_id" value="class_bean.cl_id"/>
	<s:set var="cd_id" value="cdetail_bean.cd_id"/>
	<s:set var="ac_year" value="stud_bean.ac_year"/>

	<div class="save_update_btn" onclick="generateRoster('${at_id}', '${cl_id}', '${cd_id}', '${ac_year}')" style="height: 30px; width: 210px; padding-top: 1px; padding-left: 25px; cursor: pointer; text-align: center;">
		<table><tr><td>
			<img alt="PDF" src="images/pdf.gif" height="25px">
		</td><td valign="middle">
			Generate Roster
		</td></tr></table>
	</div>

	<table id="studroster" width="100%" cellpadding="0" cellspacing="1">
		<thead style="background-color: #f5f5f5; text-align: center;">
			<tr style="height: 30px; text-align: center;">
				<th width="5%">Rank</th>
				<th>Student Name</th>
				<th>Sex</th>
				<s:iterator status="stat" value="subject_list">
					<th style="width: 15px; color: #3d6e9f; -webkit-transform: rotate(-90deg); 	-moz-transform: rotate(-90deg); 
								-ms-transform: rotate(-90deg); -o-transfrom: rotate(-90deg); transform: rotate(-90deg);	
								filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=0.083); text-align: center; height: 100px;">
	
						<s:property value="subject_list[#stat.index].sub_bean.sub_name"/>
						
					</th>
				</s:iterator>
				<th style="color: #3d6e9f; -webkit-transform: rotate(-90deg); -moz-transform: rotate(-90deg); 
								-ms-transform: rotate(-90deg); -o-transfrom: rotate(-90deg); transform: rotate(-90deg);	
								filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=0.083); text-align: center;	height: 100px;"> 
						Total 
				</th>
				<th style="color: #3d6e9f; -webkit-transform: rotate(-90deg); -moz-transform: rotate(-90deg); 
								-ms-transform: rotate(-90deg); -o-transfrom: rotate(-90deg); transform: rotate(-90deg);	
								filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=0.083); text-align: center;	height: 100px;"> 
						Average 
				</th>
			</tr>
		</thead>
		<s:iterator status="stat" value="quarter_stud_roster">
			<tr style="height: 30px;">
				<td align="center">
					<s:property value="quarter_stud_roster[#stat.index].ex_rslt_bean.stud_rank"/>
				</td>
				<td>
					<s:property value="quarter_stud_roster[#stat.index].stud_bean.fname"/> <s:property value="quarter_stud_roster[#stat.index].stud_bean.mname"/> <s:property value="quarter_stud_roster[#stat.index].stud_bean.gname"/>
				</td>
				<td align="center">
					<s:property value="quarter_stud_roster[#stat.index].stud_bean.sex"/> 
				</td>
				<s:iterator status="st" value="quarter_stud_roster[#stat.index].subject_total">
					<td align="center">
						<s:property value="quarter_stud_roster[#stat.index].subject_total[#st.index]"/>						
					</td>
				</s:iterator>
				<td align="center">
					<s:set var="total_mark" value="quarter_stud_roster[#stat.index].ex_rslt_bean.total_mark"/>
					<fmt:formatNumber maxIntegerDigits="3" pattern="##.#" value="${total_mark}"/>
				</td>
				<td align="center">
					<s:set var="qrt_avg" value="quarter_stud_roster[#stat.index].ex_rslt_bean.average_mark"/>
					<fmt:formatNumber maxIntegerDigits="3" pattern="##.#">${qrt_avg}</fmt:formatNumber>
				</td>
			</tr>
		</s:iterator>
	</table>

</body>
</html>