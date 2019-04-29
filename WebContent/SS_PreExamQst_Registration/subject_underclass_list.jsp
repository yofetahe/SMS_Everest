<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<script type="text/javascript">
	var presub_id = null;
	function questionListPerSub(subid, subclid){
		$("#qst_list").html("<img align=\"center\" src=\"images/loader.gif\"/>");		
		$.ajax({
			type : "POST",
			url : "pe_sub_qstcatlist.action",				
			data : "sub_id=" + subid + "&subcl_id=" + subclid,
			success : function(response) {
				
				if(presub_id == null){
					document.getElementById(subid).style.backgroundColor = '#e5e5e5';
					document.getElementById(subid).style.color = '#3d6e9f';
				} else {
					document.getElementById(presub_id).style.backgroundColor = '';
					document.getElementById(presub_id).style.color = '#000';
					document.getElementById(subid).style.backgroundColor = '#e5e5e5';
					document.getElementById(subid).style.color = '#3d6e9f';
				}
				
				$('#qst_list').html(response);
				presub_id = subid;
				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
</script>
</head>
<body>
	
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td width="15%" valign="top">
				
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td style="background-color: #3d6e9f; color: #ffffff; height: 40px; text-align: center;">
							Subject List
						</td>
					</tr>
					<tr><td style="line-height: 4px">&nbsp;</td></tr>
					<s:iterator status="stat" value="subList">
						<tr height="30px">
							<td align="center" onmouseover="this.style.color = '#3d6e9f'" onmouseout="this.style.color = '#000000'">
								<s:set var="subid" value="subList[#stat.index].sub_id"/>
								<s:set var="subclid" value="subList[#stat.index].subcl_id"/>
								<div id="${subid}" class="menu_button" onclick="questionListPerSub('${subid}', '${subclid}')">
									<s:property value="subList[#stat.index].sub_name"/>									
								</div>
							</td>
						</tr>
						<tr><td style="line-height: 4px">&nbsp;</td></tr>
					</s:iterator>
				</table>
			
			</td>
			<td width="1%">&nbsp;</td>
			<td id="qst_list" style="vertical-align: top;" width="84%">
			
			</td>
		</tr>
	</table>
 
</body>
</html>