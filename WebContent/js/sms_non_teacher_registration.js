/**
 * 
 */
/*=============== Non Academic Stuff Registration ===================*/

/*----- nonteacher list page -----*/

function saveNonTeacher(){
		var nti_fname = $('#fname').val();
		var nti_mname = $('#mname').val();
		var nti_gname = $('#gname').val();
		var nti_sex = $('#sex').val();
		var nti_email = $('#email').val();
		var nti_position = $('#pos').val();
		
		var emailValidator = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		
		if(nti_fname == ""){
			document.getElementById("errMsg").innerHTML = "First Name is required.";
		} else if(nti_mname == ""){
			document.getElementById("errMsg").innerHTML = "Middle Name is required.";
		} else if(nti_gname == ""){
			document.getElementById("errMsg").innerHTML = "Grand Father Name is required.";
		} else if(nti_sex == 0){
			document.getElementById("errMsg").innerHTML = "Sex is required.";
		} else if(nti_email != "" && !emailValidator.test(nti_email)){
			document.getElementById("errMsg").innerHTML = "Please insert a valid email address.";
		} else if(nti_position == ""){
			document.getElementById("errMsg").innerHTML = "Position is required.";
		} else {
		
			$("#teach_form").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "nonteacheraction_savenonteacher.action",				
				data : "nti_fname=" + nti_fname + "&nti_mname=" + nti_mname + "&nti_gname=" + nti_gname + "&nti_sex=" + nti_sex + "&nti_position=" + nti_position + "&nti_email=" + nti_email,
				success : function(response) {
					$('#teach_form').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function editNonTeacherInfo(nti_id, nti_fname, nti_mname, nti_gname, nti_position, nti_sex, nti_email, nti_id_no, nti_status){
		
		if(nti_fname == ""){
			document.getElementById("errMsg").innerHTML = "First Name is required.";
		} else if(nti_mname == ""){
			document.getElementById("errMsg").innerHTML = "Middle Name is required.";
		} else if(nti_gname == ""){
			document.getElementById("errMsg").innerHTML = "Grand Father Name is required.";
		} else if(nti_sex == 0){
			document.getElementById("errMsg").innerHTML = "Sex is required.";
		} else if(nti_position == ""){
			document.getElementById("errMsg").innerHTML = "Position is required.";
		} else {
		
			$("#saveUpdateForm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "nonteacheraction_updateFormNonteacher.action",				
				data : "nti_id=" + nti_id + "&nti_fname=" + nti_fname + "&nti_mname=" + nti_mname + "&nti_gname=" + nti_gname + "&nti_sex=" + nti_sex + "&nti_position=" + nti_position + "&nti_status=" + nti_status + "&nti_email=" + nti_email,
				success : function(response) {
					$('#saveUpdateForm').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	/*----- nonteacher edit page -----*/
	
function updateNonTeacher(nti_id){
		
		var nti_fname = $('#fname').val();
		var nti_mname = $('#mname').val();
		var nti_gname = $('#gname').val();
		var nti_sex = $('#sex').val();
		var nti_email = $('#email').val();
		var nti_position = $('#pos').val();
		var nti_status = $('#status').val();
		
		var emailValidator = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		
		if(nti_fname == ""){
			document.getElementById("errMsg").innerHTML = "First Name is required.";
		} else if(nti_mname == ""){
			document.getElementById("errMsg").innerHTML = "Middle Name is required.";
		} else if(nti_gname == ""){
			document.getElementById("errMsg").innerHTML = "Grand Father Name is required.";
		} else if(nti_sex == 0){
			document.getElementById("errMsg").innerHTML = "Sex is required.";
		} else if(nti_email != "" && !emailValidator.test(nti_email)){
			document.getElementById("errMsg").innerHTML = "Please insert a valid email address.";
		} else if(nti_position == ""){
			document.getElementById("errMsg").innerHTML = "Position is required.";
		} else {
		
			$("#teach_form").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "nonteacheraction_updateNonteacher.action",				
				data : "nti_id=" + nti_id + "&nti_fname=" + nti_fname + "&nti_mname=" + nti_mname + "&nti_gname=" + nti_gname + "&nti_sex=" + nti_sex + "&nti_position=" + nti_position + "&nti_status=" + nti_status + "&nti_email=" + nti_email,
				success : function(response) {
					$('#teach_form').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}