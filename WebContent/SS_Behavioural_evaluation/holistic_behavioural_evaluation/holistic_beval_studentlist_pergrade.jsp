<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

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
                $('#studList').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

<script type="text/javascript">
	function addStudentHolisticEvaluationResult(cl_id, si_id, fname, mname, gname){
		
		var at_id = $('#at_id').val();
		
		if(at_id == 0){
			document.getElementById("errMsg").innerHTML = "Please select quarter.";
		} else {
			////// semester-id is saved on the column of quarter-id
			$("#student_list_pergrade").html("<img align=\"center\" src=\"images/loader.gif\"/>");
			$.ajax({
				type : "POST",
				url : "behaviouralevaluation_studentholisticbevallist.action",				
				data : "cl_id=" + cl_id + "&si_id=" + si_id + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&at_id=" + at_id,
				success : function(response) {
					
					$('#student_list_pergrade').html(response);
					
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});		
		}
	}
	
	function getStudentHolisticEvaluationResult(cl_id, si_id, fname, mname, gname){
		
		var at_id = $('#at_id').val();
		
		if(at_id == 0){
			document.getElementById("errMsg").innerHTML = "Please select quarter.";
		} else {
			////// semester-id is saved on the column of quarter-id
			$("#student_list_pergrade").html("<img align=\"center\" src=\"images/loader.gif\"/>");
			$.ajax({
				type : "POST",
				url : "behaviouralevaluation_getstudentholisticbevalresult.action",				
				data : "cl_id=" + cl_id + "&si_id=" + si_id + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&at_id=" + at_id,
				success : function(response) {
					
					$('#student_list_pergrade').html(response);					
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});		
		}
	}
</script>

</head>
<body>

	<s:set var="cl_id" value="cl_id"/>
	
	<div style="background-color: #f5f5f5; width: auto; height: 40px;">
		<table><tr><td>
			Terms <span style="color: #ff0000">*</span>:
		</td><td>
			<table><s:select list="sem_list" id="at_id" listKey="at_id" listValue="at_name" headerKey="0" headerValue="---" style="width: 205px; height: 25px;"/></table>
		</td></tr></table>	 
	</div>
	
	<div style="height: 5px;">&nbsp;</div>
	
	<div id="errMsg" style="color: #ff0000">&nbsp;</div>
	<table id="studList" width="100%" cellpadding="0" cellspacing="1">
		<thead style="background-color: #f5f5f5;">
			<tr>
				<td width="5%" align="center" height="35px">No</td>
				<td width="30%" align="center">Student Name</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</thead>
		<s:iterator status="stat" value="stud_rslt">
			<tr style="height: 40px;">
				<td align="center">
					<s:property value="%{#stat.index + 1}"/>
					<s:set var="si_id" value="stud_rslt[#stat.index].si_id"/>
				</td>
				<td>
					<s:property value="stud_rslt[#stat.index].fname"/> <s:property value="stud_rslt[#stat.index].mname"/> <s:property value="stud_rslt[#stat.index].gname"/>
					<s:set var="fname" value="stud_rslt[#stat.index].fname"/>
					<s:set var="mname" value="stud_rslt[#stat.index].mname"/>
					<s:set var="gname" value="stud_rslt[#stat.index].gname"/>
				</td>
				<td style="color: #3d6e9f;">
					<div style="cursor: pointer;" onclick="addStudentHolisticEvaluationResult('${cl_id}', '${si_id}', '${fname}', '${mname}', '${gname}')">
						Add Holistic Evaluation
					</div>
				</td>
				<td style="color: #3d6e9f;">
					<div style="cursor: pointer;" onclick="getStudentHolisticEvaluationResult('${cl_id}', '${si_id}', '${fname}', '${mname}', '${gname}')">
						Show Holistic Evaluation
					</div>
				</td>
			</tr>
		</s:iterator>
	</table>

</body>
</html>