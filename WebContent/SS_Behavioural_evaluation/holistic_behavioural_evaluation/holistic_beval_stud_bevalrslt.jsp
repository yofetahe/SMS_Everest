<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">
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
                $('#bevalRslt').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

<script type="text/javascript">
	function evaluationGradeList(bt_id){
		
		$("#evalGrgList").html("<img align=\"center\" src=\"images/30.GIF\"/>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_evalgradelist.action",				
			data : "bt_id=" + bt_id,
			success : function(response) {
				
				$('#evalGrgList').html(response);
				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function saveBevalResult(cl_id, si_id, qr_id){
		
		var bt_id = $('#btid').val();
		var bg_id = $('#bgid').val();
		
		if(bt_id == 0){
			document.getElementById('errMsg').innerHTML = "Trait must be selected.";
		} else if(bg_id == 0){
			document.getElementById('errMsg').innerHTML = "Grade must be given.";
		} else {
			$("#studRsltList").html("<img align=\"center\" src=\"images/loader.gif\"/>");
			$.ajax({
				type : "POST",
				url : "behaviouralevaluation_saveevalgrade.action",				
				data : "bt_id=" + bt_id + "&bg_id=" + bg_id + "&cl_id=" + cl_id + "&si_id=" + si_id + "&qr_id=" + qr_id,
				success : function(response) {
					
					$('#studRsltList').html(response);
					
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function bevalRsltEditForm(bsr_id, cl_id, si_id, qr_id, bt_id, bsr_status, bg_id){
		$("#saveEditForm").html("<img align=\"center\" src=\"images/loader.gif\"/>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_bevalrslteditform.action",				
			data : "bsr_id=" + bsr_id + "&cl_id=" + cl_id + "&si_id=" + si_id + "&qr_id=" + qr_id + "&bt_id=" + bt_id + "&bsr_status=" + bsr_status + "&bg_id=" + bg_id,
			success : function(response) {
				
				$('#saveEditForm').html(response);
				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function updateBevalResult(bsr_id, cl_id, si_id, qr_id){
		
		var bt_id = $('#btid').val();
		var bg_id = $('#bgid').val();
		var bsr_status = $('#bsrstatus').val();
		
		if(bt_id == 0){
			document.getElementById("errMsg").innerHTML = "Trait must be selected.";
		} else if(bg_id == 0){
			document.getElementById("errMsg").innerHTML = "Grade must be given.";
		} else {
			$("#studRsltList").html("<img align=\"center\" src=\"images/loader.gif\"/>");
			$.ajax({
				type : "POST",
				url : "behaviouralevaluation_updatebevalrslt.action",				
				data : "bsr_id=" + bsr_id + "&bt_id=" + bt_id + "&bg_id=" + bg_id + "&bsr_status=" + bsr_status + "&cl_id=" + cl_id + "&si_id=" + si_id + "&qr_id=" + qr_id,
				success : function(response) {
					
					$('#studRsltList').html(response);
					
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	var prev_bhcid = null;
	function listCategoryRelatedPoints(bhc_id, si_id, cl_id, at_id, ac_year){
		
		$("#cat_points").html("<img align=\"center\" src=\"images/loader.gif\"/>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_categorypointslist.action",				
			data : "bhc_id=" + bhc_id + "&si_id=" + si_id + "&cl_id=" + cl_id + "&at_id=" + at_id + "&ac_year=" + ac_year,
			success : function(response) {
				if(prev_bhcid == null){
					document.getElementById(bhc_id+"_cat").style.backgroundColor = '#f5f5f5';
					document.getElementById(bhc_id+"_cat").style.color = '#3d6e9f';
				} else {
					document.getElementById(prev_bhcid+"_cat").style.backgroundColor = '';
					//document.getElementById(prev_bhcid+"_cat").style.color = '#000000';
					document.getElementById(bhc_id+"_cat").style.backgroundColor = '#f5f5f5';
					document.getElementById(bhc_id+"_cat").style.color = '#3d6e9f';
				}
				$('#cat_points').html(response);
				prev_bhcid = bhc_id;
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
		
	}
</script>

</head>
<body>
	
<s:set var="cl_id" value="cl_id"/>
<s:set var="si_id" value="si_id"/>
<s:set var="at_id" value="at_id"/>
<s:set var="ac_year" value="ac_year"/>
<s:set var="at_name" value="at_name"/>

<div id="studRsltList">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="background-color: #f5f5f5;">
				<div class="subHeaderTitle" style="padding-bottom: 5px;">
					<table><tr><td>
					Student Name: <span style="color: #3d6e9f;"> <s:property value="fname"/> <s:property value="mname"/> <s:property value="gname"/> </span> &nbsp;&nbsp;&nbsp;
					Terms: <span style="color: #3d6e9f;"> <s:property value="at_name"/> </span> &nbsp;&nbsp;&nbsp;
					Academic Year: <span style="color: #3d6e9f;"> <s:property value="ac_year"/></span> &nbsp;&nbsp;&nbsp;
					</td></tr></table>
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px;">
			
				<table cellpadding="0" cellspacing="0">
					<tr>
						<s:iterator status="stat" value="related_catagory_list">
							<td>
								<s:set var="bhc_id" value="related_catagory_list[#stat.index].bhc_id"/>
								<div class="dashboard_inactivebtn" id="${bhc_id}_cat" style="width: 160px;" onclick="listCategoryRelatedPoints('${bhc_id}', '${si_id}', '${cl_id}', '${at_id}', '${ac_year}')">
									<s:property value="related_catagory_list[#stat.index].bhc_name"/>
								</div>
							</td>
							<td>&nbsp;</td>
						</s:iterator>
					</tr>
				</table>
			
			</td>
		</tr>
		<tr>
			<td>
				<div id="cat_points">   </div>
			</td>
		</tr>
	</table>
</div>
</body>
</html>