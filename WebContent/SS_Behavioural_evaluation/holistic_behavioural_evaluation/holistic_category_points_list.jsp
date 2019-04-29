<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<script type="text/javascript">
	function saveStudHolisticEvaluation(bhcp_id, si_id, cl_id, at_id, ac_year){
		var comment = $('#'+bhcp_id+'_com').val();
		
		var error = bhcp_id+"_error";
		
		if(comment == ""){			
			document.getElementById(error).innerHTML = "Please put your comment."
		} else {
			
			$('#'+bhcp_id+'_suc').html("<img align=\"center\" src=\"images/32.gif\"/>");
			$.ajax({
				type : "POST",
				url : "behaviouralevaluation_savepointscomment.action",				
				data : "bhcp_id=" + bhcp_id + "&si_id=" + si_id + "&cl_id=" + cl_id + "&at_id=" + at_id + "&ac_year=" + ac_year + "&beval_comment=" + comment,
				success : function(response) {
					
					if(response == 1){
						document.getElementById(bhcp_id+'_suc').innerHTML = comment;
						document.getElementById(bhcp_id+'_com').style.display='none';
						document.getElementById(bhcp_id+'_btn').style.display='none';
						$('#'+bhcp_id+'_suc').html(comment);
					} else if(response == 3) {
						document.getElementById(error).innerHTML = "The comment is not saved. Please try again.";
					} else {
						$('#com_page').html(response);
					}
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function updateStudHolisticEvaluation(bhsr_id, bhcp_id, si_id, cl_id, at_id, ac_year){
		var comment = $('#'+bhcp_id+'_com').val();
		
		var error = bhcp_id+"_error";
		
		if(comment == ""){			
			document.getElementById(error).innerHTML = "Please put your comment."
		} else {
			
			$('#'+bhcp_id+'_suc').html("<img align=\"center\" src=\"images/30.gif\"/>");
			$.ajax({
				type : "POST",
				url : "behaviouralevaluation_updatepointscomment.action",				
				data : "bhsr_id=" + bhsr_id + "&bhcp_id=" + bhcp_id + "&si_id=" + si_id + "&cl_id=" + cl_id + "&at_id=" + at_id + "&ac_year=" + ac_year + "&beval_comment=" + comment,
				success : function(response) {
					
					if(response == 1){
						document.getElementById(bhcp_id+'_suc').innerHTML = comment;
						document.getElementById(bhcp_id+'_com').style.display='none';
						document.getElementById(bhcp_id+'_btn').style.display='none';
						$('#'+bhcp_id+'_suc').html(comment);
					} else if(response == 3) {
						document.getElementById(error).innerHTML = "The comment is not saved. Please try again.";
					} else {
						$('#com_page').html(response);
					}
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

<div id="com_page">
	<s:set var="bhc_id" value="bhc_id"/>
	<s:set var="si_id" value="si_id"/>
	<s:set var="cl_id" value="cl_id"/>
	<s:set var="at_id" value="at_id"/>
	<s:set var="ac_year" value="ac_year"/>
	<br/>
	<table width="100%">
		<s:iterator status="stat" value="uncommented_category_list">
			<tr style="height: 50px;">
				<td valign="top" width="30px;"> <s:property value="%{#stat.index + 1}"/> - </td>
				<td valign="top" width="160px;">
					<table>
						<s:property value="uncommented_category_list[#stat.index].bhp_content"/>
					</table>
				</td>
				<td valign="top" align="right">
					<table>
						<s:set var="bhcp_id" value="uncommented_category_list[#stat.index].bhcp_id"/>
						<s:set var="beval_comment" value="uncommented_category_list[#stat.index].beval_comment"/>
						<s:set var="comment_status" value="uncommented_category_list[#stat.index].comment_status"/>
						<div id="${bhcp_id}_error" style="color: #ff0000"></div>
					</table>
					
					<table>
						<div id="${bhcp_id}_suc">				
							<s:textarea id="${bhcp_id}_com" value="%{#beval_comment}" rows="2" style="width: 500px;"/>
						</div>
					</table>
					
				</td>
				<td align="left" valign="top">
					<s:if test="%{#comment_status == \"N\"}">
						<table>
							<s:submit value="Save" id="${bhcp_id}_btn" style="width: 100px; height: 50px;" onclick="saveStudHolisticEvaluation('${bhcp_id}', '${si_id}', '${cl_id}', '${at_id}', '${ac_year}')"/>						
						</table>
					</s:if>
					<s:else>
						<table>
							<s:set var="bhsr_id" value="uncommented_category_list[#stat.index].bhsr_id"/>
							<s:submit value="Update" id="${bhcp_id}_btn" style="width: 100px; height: 50px;" onclick="updateStudHolisticEvaluation('${bhsr_id}', '${bhcp_id}', '${si_id}', '${cl_id}', '${at_id}', '${ac_year}')"/>						
						</table>
					</s:else>
				</td>
			</tr>
		</s:iterator>
	</table>
</div>

</body>
</html>