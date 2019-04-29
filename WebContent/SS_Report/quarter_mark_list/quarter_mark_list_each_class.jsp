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
                $('#studMarkList').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

	<div id="markList">
	
		<s:set var="at_id" value="at_id"/>
		<s:set var="cl_id" value="class_bean.cl_id"/>
		<s:set var="cd_id" value="cdetail_bean.cd_id"/>
		<s:set var="sub_id" value="sub_bean.sub_id"/>
		<s:set var="ac_year" value="stud_bean.ac_year"/>
	
		<div class="save_update_btn" onclick="generateStudentMarkList('${at_id}', '${cl_id}', '${cd_id}', '${sub_id}', '${ac_year}')" style="height: 30px; width: 210px; padding-top: 1px; padding-left: 25px; cursor: pointer; text-align: center;">
			<table><tr><td>
				<img alt="PDF" src="images/pdf.gif" height="25px">
			</td><td valign="middle">
				Generate Mark List
			</td></tr></table>
		</div>
	
		<table id="studMarkList" width="100%" cellpadding="0" cellspacing="1">
			<thead style="background-color: #f5f5f5; text-align: center;">
				<tr style="height: 30px;">
					<th>No</th>
					<th>Student Name</th>
					<th>Sex</th>
					
					<s:iterator status="stat" value="examtype_list">
						<th> <s:property value="examtype_list[#stat.index].et_name"/> </th>
					</s:iterator>
					
<!-- 					<th>Exam 1</th> -->
<!-- 					<th>Exam 2</th> -->
<!-- 					<th>Work Sheet</th> -->
<!-- 					<th>Activity</th> -->
<!-- 					<th>Total</th> -->
<!-- 					<th>Final</th> -->
<!-- 					<th>Total</th> -->
				</tr>
			</thead>
			<s:iterator status="stat" value="quarter_mark_list">
				<tr style="height: 30px">
					<td align="center"> <s:property value="%{#stat.index + 1}"/> </td>
					<td> <s:property value="quarter_mark_list[#stat.index].stud_bean.fname"/> <s:property value="quarter_mark_list[#stat.index].stud_bean.mname"/> <s:property value="quarter_mark_list[#stat.index].stud_bean.gname"/> </td>
					<td align="center"> <s:property value="quarter_mark_list[#stat.index].stud_bean.sex"/> </td>

					<s:iterator status="st" value="quarter_mark_list[#stat.index].ex_rslt_bean.allExamResult">
						<td align="center"> <s:property value="quarter_mark_list[#stat.index].ex_rslt_bean.allExamResult[#st.index]"/> </td>
					</s:iterator>
					
<%-- 					<td align="center"> <s:property value="quarter_mark_list[#stat.index].exam_one"/> </td> --%>
<%-- 					<td align="center"> <s:property value="quarter_mark_list[#stat.index].exam_two"/> </td> --%>
<%-- 					<td align="center"> <s:property value="quarter_mark_list[#stat.index].worksheet"/> </td> --%>
<%-- 					<td align="center"> <s:property value="quarter_mark_list[#stat.index].activity"/> </td> --%>
<%-- 					<td align="center"> <s:property value="quarter_mark_list[#stat.index].sub_total"/> </td> --%>
<%-- 					<td align="center"> <s:property value="quarter_mark_list[#stat.index].finalExam"/> </td> --%>
<%-- 					<td align="center"> <s:property value="quarter_mark_list[#stat.index].grand_total"/> </td> --%>
					
				</tr>
			</s:iterator>
		</table>
		<div style="height: 20px;">&nbsp;</div>
	</div>

</body>
</html>