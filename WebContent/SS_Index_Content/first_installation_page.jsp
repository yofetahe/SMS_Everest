<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sj:head />
<s:head />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">
<script src="js/jquery-1.6.1.js" type="text/javascript"></script>
<script type="text/javascript">
	function saveSchoolInformation(){
		var school_name = $('#sch_name').val();
		var school_slogan = $('#sch_slogan').val();
		var tin_num = $('#tin_num').val();
		var telephone = $('#telephone').val();
		var fax = $('#fax').val();
		var web = $('#web').val();
		var email = $('#email').val();
		var pobox = $('#pobox').val();
		var fiscal_machine_no = $('#fiscal_machine_no').val();
		
		if(school_name == ""){
			document.getElementById("errMsg").innerHTML = "School name is required.";
		} else if(tin_num == ""){
			document.getElementById("errMsg").innerHTML = "Tin number is required.";
		} else if(fiscal_machine_no == ""){
			document.getElementById("errMsg").innerHTML = "Fiscal Machine Number is required.";
		} else {
		
			$("#indexContent").html("<img align=\"center\" src=\"images/loader.gif\"/>");
			$.ajax({
				type : "POST",
				url : "installation_school_information.action",				
				data : "school_name=" + school_name + "&school_slogan=" + school_slogan + "&tin_num=" + tin_num + "&telephone=" + telephone + "&fax=" + fax + "&web=" + web + "&email=" + email + "&pobox=" + pobox + "&fiscal_machine_no=" + fiscal_machine_no,
				success : function(response) {				
					$('#indexContent').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
</script>

</head>
<body style="margin: 0; background-image: url('images/new_bg_2.png'); background-repeat: repeat;" >

<div id="indexContent" align="center">
<div style="font-weight: bold; color: #3d6e9f; text-align: center; padding-top: 80px; font-size: 30px;">
	SCHOOL INFORMATION
</div>
<div class="formboarder" style="width: 400px; ">
	<div id="errMsg" style="color: #ff0000" align="center"></div>	
	<table align="center">
		<tr>
			<td>
				
				<s:textfield label="School Name" id="sch_name" name="school_name" style="width: 200px; height: 30px;"/>
				<s:textfield label="School Slogan" id="sch_slogan" name="school_slogan" style="width: 200px; height: 30px;"/>
				<s:textfield label="TIN Number" id="tin_num" name="tin_num" style="width: 200px; height: 30px;"/>
				<s:textfield label="Telephone" id="telephone" name="telephone" style="width: 200px; height: 30px;"/>
				<s:textfield label="Fax" id="fax" name="fax" style="width: 200px; height: 30px;"/>
				<s:textfield label="Web" id="web" name="web" style="width: 200px; height: 30px;"/>
				<s:textfield label="E-mail" id="email" name="email" style="width: 200px; height: 30px;"/>
				<s:textfield label="P.O.Box" id="pobox" name="pobox" style="width: 200px; height: 30px;"/>
				<s:textfield label="Fisical Machine Number" id="fiscal_machine_no" name="fiscal_machine_no" style="width: 200px; height: 30px;"/>
				
				<s:submit label="Save" onclick="saveSchoolInformation()" align="center"/>
			</td>
		</tr>
	</table>
</div>

</div>

</body>
</html>