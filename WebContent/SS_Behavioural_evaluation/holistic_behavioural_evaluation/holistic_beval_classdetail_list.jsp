<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<script type="text/javascript">
var prev_cdid = null;
function holisticClassDetail(cl_id, cd_id){
	$("#student_list_pergrade").html("<img align=\"center\" src=\"images/loader.gif\"/>");
	$.ajax({
		type : "POST",
		url : "behaviouralevaluation_holisticstudentlist.action",				
		data : "cl_id=" + cl_id + "&cd_id=" + cd_id,
		success : function(response) {
			if(prev_cdid == null){
				document.getElementById(cd_id+"_cd").style.color = "#ffffff";
				document.getElementById(cd_id+"_cd").style.backgroundColor = "#3d6e9f";
			} else {
				document.getElementById(prev_cdid+"_cd").style.backgroundColor = '#f5f5f5';
				document.getElementById(prev_cdid+"_cd").style.color = '#3d6e9f';
				document.getElementById(cd_id+"_cd").style.backgroundColor = '#3d6e9f';
				document.getElementById(cd_id+"_cd").style.color = '#ffffff';
			}
			$('#student_list_pergrade').html(response);
			prev_cdid = cd_id;
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}
</script>

</head>
<body>

	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<s:set var="cl_id" value="cl_id"/>
				<s:set var="clname" value="class_name"/>
				<table width="100%" style="border-bottom-style: solid; border-bottom-width: thin; border-bottom-color: #3d6e9f;" cellpadding="0" cellspacing="0"><tr><td>
				<table cellpadding="0" cellspacing="0" >
					<tr>
						<s:iterator status="stat" value="class_detail">
							<td width="80px" height="25px" align="center">
								<s:set var="cdid" value="class_detail[#stat.index].cd_id"/>
								<div class="header_btn_inactive" id="${cdid}_cd" onclick="holisticClassDetail('${cl_id}', '${cdid}')">
									<s:property value="%{#clname}"/> - <s:property value="class_detail[#stat.index].cd_name"/>
								</div>
							</td>
							<td>&nbsp;</td>
						</s:iterator>								
					</tr>
				</table>
				</td></tr></table>
			</td>
		</tr>
		<tr>
			<td>
				<div id="student_list_pergrade" style="padding-top: 10px;">
				
				</div>
			</td>
		</tr>
	</table>	

</body>
</html>