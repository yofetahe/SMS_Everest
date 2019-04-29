/**
 * 
 */

/*========================== SUBJECT REGISTRATION GENERAL ==============================*/

/*----- SUBJECT LIST JSP PAGE -----*/

function addSubject(sub_id, sub_name, sub_status){
		$("#subjectFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "subjectaction_createfrm.action",				
			data : "",
			success : function(response) {
				$('#subjectFrm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}

	function editSubject(sub_id, sub_name, sub_status){
		$("#subjectFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "subjectaction_editfrm.action",				
			data : "sub_id=" + sub_id + "&sub_name=" + sub_name + "&sub_status=" + sub_status,
			success : function(response) {
				$('#subjectFrm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addExamTypeSubRel(sub_id, sub_name){
		
		$("#subjectFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "subjectaction_examtypelist.action",				
			data : "sub_id=" + sub_id + "&sub_name=" + sub_name,
			success : function(response) {
				$('#subjectFrm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- SUBJECT EDIT JSP PAGE -----*/
	
function updateSubject(sub_id){
		
		var sub_name = $('#sub_name').val();
		var sub_status = $('#sub_status').val();
		
		var pattern = /^[A-Za-z ]{3,40}$/;
		var subname_pattern = pattern.test(sub_name);
		
		if(sub_name == ""){
			document.getElementById("errMsg").innerHTML = "Subjet Name is required.";
		} else if(subname_pattern == false){
			document.getElementById("errMsg").innerHTML = "Invalide Subjet Name.";
		} else {			
		
			$("#subjectFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "subjectaction_update.action",				
				data : "sub_id=" + sub_id + "&sub_name=" + sub_name + "&sub_status=" + sub_status,
				success : function(response) {
					$('#subjectFrm').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});	
		
		}
	}

	/*----- SUBJECT CREATE JSP PAGE -----*/

function saveSubject(){
	
	var sub_name = $('#sub_name').val();
	
	var pattern = /^[A-Za-z ]{3,40}$/;
	var subname_pattern = pattern.test(sub_name);
	
	if(sub_name == ""){
		document.getElementById("errMsg").innerHTML = "Subjet Name is required.";
	} else if(subname_pattern == false){
		document.getElementById("errMsg").innerHTML = "Invalide Subjet Name.";
	} else {
			
		$("#subjectFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "subjectaction_save.action",				
			data : "sub_name=" + sub_name,
			success : function(response) {
				$('#subjectFrm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});		
	}
}

/*========================== SUBJECT REGISTRATION GENERAL ==============================*/

/*----- EXAM TYPE LIST JSP PAGE -----*/

function addExamType(){
	$("#examtypeFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "examtypeaction_createfrm.action",				
		data : "",
		success : function(response) {
			$('#examtypeFrm').html(response);
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

function editExamType(et_id, et_name, et_type, et_status){
	$("#examtypeFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "examtypeaction_editfrm.action",				
		data : "et_id=" + et_id + "&et_name=" + et_name + "&et_status=" + et_status + "&et_type=" + et_type,
		success : function(response) {
			$('#examtypeFrm').html(response);
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

/*----- EXAM TYPE EDIT JSP PAGE -----*/

function updateExamType(et_id){
	
	var et_name = $('#et_name').val();
	var et_status = $('#et_status').val();
	
	if(et_name == ""){
		document.getElementById("errMsg").innerHTML = "Exam Type Name is required.";
	} else {
	
		$("#examtypeFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "examtypeaction_edit.action",				
			data : "et_id=" + et_id + "&et_name=" + et_name + "&et_status=" + et_status,
			success : function(response) {
				$('#examtypeFrm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	
	}
}


/*----- EXAM TYPE CREATE JSP PAGE -----*/

function saveExamType(){
	
	var et_name = $('#et_name').val();
	var et_type = $('#et_type').val();
	
	if(et_name == ""){
		document.getElementById("errMsg").innerHTML = "Exam Type Name is required.";
	} else {
	
		$("#examtypeFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "examtypeaction_create.action",				
			data : "et_name=" + et_name + "&et_type=" + et_type,
			success : function(response) {
				$('#examtypeFrm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	
	}
}
