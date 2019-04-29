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
                $('#catList').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

<script type="text/javascript">
	function editExamCatList(indx, subcl_id, pet_id){
		
		$("#exCatList").html("<img align=\"center\" src=\"images/loader.gif\"/>");		
		$.ajax({
			type : "POST",
			url : "pe_updatefrm_examcat.action",				
			data : "indx=" + indx + "&subcl_id=" + subcl_id + "&pet_id=" + pet_id,
			success : function(response) {
				$('#exCatList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
</script>

</head>
<body>
	<s:set var="subclid" value="subcl_id"/>
	<div style="padding-top: 15px" id="exCatList">
		<table id="catList" width="100%" >
			<thead style="background-color: #e5e5e5; height: 30px; color: #000000" align="center">
				<tr>
					<td>No</td>
					<td>Category Name</td>
					<td>NO Of Questions</td>
					<td>Total Time Allowed</td>
					<td>Status</td>
				</tr>
			</thead>
			<s:iterator status="stat" value="examCatList">
				<tr>
					<td style="height: 30px; text-align: center;">
						<s:property value="%{#stat.index + 1}"/>
						<s:set var="pet_id" value="pet_id"/>
					</td>
					<td>
						<div style="color: #3d6e9f; cursor: pointer;" onclick="editExamCatList('${stat.index}', '${subclid}', '${pet_id}')">
							<s:property value="examCatList[#stat.index].exam_level"/>
						</div>
					</td>
					<td>
						<s:property value="examCatList[#stat.index].number_of_qst"/>
					</td>
					<td>
						<s:property value="examCatList[#stat.index].total_time_allowed"/>
					</td>
					<td>
						<s:property value="examCatList[#stat.index].pet_status"/>
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
</body>
</html>