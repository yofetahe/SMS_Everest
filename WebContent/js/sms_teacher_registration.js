/**
 * 
 */
/*============================= TEACHER REGISTRATION GENERAL =============================*/

/*----- teachers list jsp page -----*/

function addNewTeacher(){
	
	$("#teach_form").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "teacheraction_create.action",				
		data : "",
		success : function(response) {
			$('#teach_form').html(response);
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

function editTeacherInfo(tiid, fname, mname, gname, sex, dob, pob, nat, id_no, ti_status, photo_name, photo_path){
	
	$("#teach_form").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "teacheraction_edit.action",				
		data : "ti_id=" + tiid + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&sex=" + sex + "&dob=" + dob + "&pob=" + pob + "&nationality=" + nat + "&id_no=" + id_no + "&ti_status=" + ti_status + "&photo_name=" + photo_name + "&photo_path=" + photo_path,
		success : function(response) {
			$('#teach_form').html(response);
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

function teacherAssignment(){
	alert("teacherAssignment");
}


/*----- teacher edit jsp page -----*/

function updateTeacher(tiid){
	var fname = $('#fname').val();
	var mname = $('#mname').val();
	var gname = $('#gname').val();
	var sex = $('#sex').val();
	var dob = $('#dob').val();
	var pob = $('#pob').val();
	var nat = $('#nat').val();
	var idno = $('#idno').val();
	var tistatus = $('#tistatus').val();
	
	var pattern = /^[A-Za-z //]{3,20}$/;
	var fname_pattern = pattern.test(fname);
	var mname_pattern = pattern.test(mname);
	var gname_pattern = pattern.test(gname);
	
	var valDob = validateDOB(dob);
	
	if(fname == "" || mname == "" || gname == ""){
		document.getElementById("errMsg").innerHTML = "Full Name is required";
	} else if(fname_pattern == false || mname_pattern == false || gname_pattern == false){
		document.getElementById("errMsg").innerHTML = "Invalide Full Name content";
	} else if(sex == "0"){
		document.getElementById("errMsg").innerHTML = "Sex is required.";
	} else if(dob == ""){
		document.getElementById("errMsg").innerHTML = "Date of birth is required.";
	} else if(valDob == "invalideDate"){
		document.getElementById("errMsg").innerHTML = "Invalide Date of birth.";
	} else if(valDob == "ageProblem"){
		document.getElementById("errMsg").innerHTML = "Age is below the expected level(22).";
	} else if(nat == ""){
		document.getElementById("errMsg").innerHTML = "Nationality is required.";
	} else if(idno == ""){
		document.getElementById("errMsg").innerHTML = "ID Number is required.";
	} else {
	
		$("#teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "teacheraction_update.action",				
			data : "ti_id=" + tiid + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&sex=" + sex + "&dob=" + dob + "&pob=" + pob + "&nationality=" + nat + "&id_no=" + idno + "&ti_status=" + tistatus,
			success : function(response) {
				$('#teachFroms').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	
	}
}

function validateDOB(dob){
	//dob format validator
	var year = 0;
	var month_dob = "";
	var month = 0;
	var day = 0;
	var j = 0;		
	for(var i = 0; i < 1; i++){
		j = dob.indexOf("-");
		year = dob.substring(0,j);			
		month_dob = dob.substring(j+1, dob.length);
	}
	
	for(var i = 0; i < 1; i++){
		j = month_dob.indexOf("-");
		month = month_dob.substring(0, j);
		day = month_dob.substring(j+1);
	}
	
	// Age validator
	var now = new Date();
	if(month < 5){
		age = now.getFullYear() - 7 - year;
	} else {
		age = now.getFullYear() - 8 - year;
	}
	
	if((year%4 == 0 && month == 13 && day > 6) || (year%4 != 0 && month == 13 && day > 5)){
		return "invalideDate";
	} else if(month > 13 || day > 30){
		return "invalideDate";
	} else if(age < 22) {
		return "ageProblem";
	}
}

/*----- teacher create jsp page -----*/

function saveTeacher(){
	var fname = $('#fname').val();
	var mname = $('#mname').val();
	var gname = $('#gname').val();
	var sex = $('#sex').val();
	var dob = $('#dob').val();
	var pob = $('#pob').val();
	var nat = $('#nat').val();
	var idno = $('#idno').val();
	
	var pattern = /^[A-Za-z ]{3,20}$/;
	var fname_pattern = pattern.test(fname);
	var mname_pattern = pattern.test(mname);
	var gname_pattern = pattern.test(gname);
	
	if(fname == "" || mname == "" || gname == ""){
		document.getElementById("errMsg").innerHTML = "Full Name is required";
	} else if(fname_pattern == false || mname_pattern == false || gname_pattern == false){
		document.getElementById("errMsg").innerHTML = "Invalide Full Name content";
	} else if(sex == "0"){
		document.getElementById("errMsg").innerHTML = "Sex is required.";
	} else if(dob == ""){
		document.getElementById("errMsg").innerHTML = "Date of birth is required.";
	} else if(validateDOB(dob) == "invalideDate"){
		document.getElementById("errMsg").innerHTML = "Invalide Date of birth.";
	} else if(validateDOB(dob) == "ageProblem"){
		document.getElementById("errMsg").innerHTML = "Age is below the expected level(22).";
	} else if(nat == ""){
		document.getElementById("errMsg").innerHTML = "Nationality is required.";
	} else {
	
		$("teach_form").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "teacheraction_save.action",				
			data : "fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&sex=" + sex + "&dob=" + dob + "&pob=" + pob + "&id_no=" + idno + "&nationality=" + nat,
			success : function(response) {
				$('#teach_form').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	
	}
}

function validateDOB(dob){
	//dob format validator
	var year = 0;
	var month_dob = "";
	var month = 0;
	var day = 0;
	var j = 0;		
	for(var i = 0; i < 1; i++){
		j = dob.indexOf("-");
		year = dob.substring(0,j);			
		month_dob = dob.substring(j+1, dob.length);
	}
	
	for(var i = 0; i < 1; i++){
		j = month_dob.indexOf("-");
		month = month_dob.substring(0, j);
		day = month_dob.substring(j+1);
	}
	
	// Age validator
	var now = new Date();
	if(month < 5){
		age = now.getFullYear() - 7 - year;
	} else {
		age = now.getFullYear() - 8 - year;
	} 
	
	if((year%4 == 0 && month == 13 && day > 6) || (year%4 != 0 && month == 13 && day > 5)){
		return "invalideDate";
	} else if(month > 13 || day > 30){
		return "invalideDate";
	} else if(age < 22) {
		return "ageProblem";
	}
}

/*----- teachers menu jsp page -----*/

function personal_info(tiid){
	
	$("#teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "teacheraction_perinfo_update.action",				
		data : "ti_id=" + tiid,
		success : function(response) {
			document.getElementById("pi").style.backgroundColor = '#e5e5e5';
			document.getElementById("eb").style.backgroundColor = '';
			document.getElementById("we").style.backgroundColor = '';
			document.getElementById("at").style.backgroundColor = '';
			document.getElementById("ca").style.backgroundColor = '';
			document.getElementById("ec").style.backgroundColor = '';
			document.getElementById("r").style.backgroundColor = '';
			document.getElementById("da").style.backgroundColor = '';
			$('#teachFroms').html(response);				
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
} 

function assign_to_subject(tiid){
	$("#teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "teacheraction_assignto.action",				
		data : "ti_id=" + tiid,
		success : function(response) {
			document.getElementById("pi").style.backgroundColor = '';
			document.getElementById("eb").style.backgroundColor = '';
			document.getElementById("we").style.backgroundColor = '';
			document.getElementById("at").style.backgroundColor = '#e5e5e5';
			document.getElementById("ca").style.backgroundColor = '';
			document.getElementById("ec").style.backgroundColor = '';
			document.getElementById("r").style.backgroundColor = '';
			document.getElementById("da").style.backgroundColor = '';
			$('#teachFroms').html(response);				
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

function current_address(tiid){
	
	$("#teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "teacheraction_curadd_update.action",				
		data : "ti_id=" + tiid,
		success : function(response) {
			document.getElementById("pi").style.backgroundColor = '';
			document.getElementById("eb").style.backgroundColor = '';
			document.getElementById("we").style.backgroundColor = '';
			document.getElementById("at").style.backgroundColor = '';
			document.getElementById("ca").style.backgroundColor = '#e5e5e5';
			document.getElementById("ec").style.backgroundColor = '';
			document.getElementById("r").style.backgroundColor = '';
			document.getElementById("da").style.backgroundColor = '';
			$('#teachFroms').html(response);				
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

function emergency_contact(tiid){
	
	$("#teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "teacheraction_emrgcont_savefrm.action",				
		data : "ti_id=" + tiid,
		success : function(response) {
			document.getElementById("pi").style.backgroundColor = '';
			document.getElementById("eb").style.backgroundColor = '';
			document.getElementById("we").style.backgroundColor = '';
			document.getElementById("at").style.backgroundColor = '';
			document.getElementById("ca").style.backgroundColor = '';
			document.getElementById("ec").style.backgroundColor = '#e5e5e5';
			document.getElementById("r").style.backgroundColor = '';
			document.getElementById("da").style.backgroundColor = '';
			$('#teachFroms').html(response);				
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

function reward(tiid){
	$("#teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "teacheraction_reward_list.action",				
		data : "ti_id=" + tiid,
		success : function(response) {
			document.getElementById("pi").style.backgroundColor = '';
			document.getElementById("eb").style.backgroundColor = '';
			document.getElementById("we").style.backgroundColor = '';
			document.getElementById("at").style.backgroundColor = '';
			document.getElementById("ca").style.backgroundColor = '';
			document.getElementById("ec").style.backgroundColor = '';
			document.getElementById("r").style.backgroundColor = '#e5e5e5';
			document.getElementById("da").style.backgroundColor = '';
			$('#teachFroms').html(response);				
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

function disciplinary_action(tiid){
	$("#teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "teacheraction_disact_list.action",				
		data : "ti_id=" + tiid,
		success : function(response) {
			document.getElementById("pi").style.backgroundColor = '';
			document.getElementById("eb").style.backgroundColor = '';
			document.getElementById("we").style.backgroundColor = '';
			document.getElementById("at").style.backgroundColor = '';
			document.getElementById("ca").style.backgroundColor = '';
			document.getElementById("ec").style.backgroundColor = '';
			document.getElementById("r").style.backgroundColor = '';
			document.getElementById("da").style.backgroundColor = '#e5e5e5';
			$('#teachFroms').html(response);				
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

function education_background(tiid){
	$("#teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "teacheraction_edu_background.action",				
		data : "ti_id=" + tiid,
		success : function(response) {
			document.getElementById("pi").style.backgroundColor = '';
			document.getElementById("eb").style.backgroundColor = '#e5e5e5';
			document.getElementById("we").style.backgroundColor = '';
			document.getElementById("at").style.backgroundColor = '';
			document.getElementById("ca").style.backgroundColor = '';
			document.getElementById("ec").style.backgroundColor = '';
			document.getElementById("r").style.backgroundColor = '';
			document.getElementById("da").style.backgroundColor = '';
			$('#teachFroms').html(response);				
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

function work_experience(tiid){
	$("#teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "teacheraction_work_experience.action",				
		data : "ti_id=" + tiid,
		success : function(response) {
			document.getElementById("pi").style.backgroundColor = '';
			document.getElementById("eb").style.backgroundColor = '';
			document.getElementById("we").style.backgroundColor = '#e5e5e5';
			document.getElementById("at").style.backgroundColor = '';
			document.getElementById("ca").style.backgroundColor = '';
			document.getElementById("ec").style.backgroundColor = '';
			document.getElementById("r").style.backgroundColor = '';
			document.getElementById("da").style.backgroundColor = '';
			$('#teachFroms').html(response);				
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

function upload_photo(ti_id){
	$("#teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "teacheraction_photoupload.action",				
		data : "ti_id=" + ti_id,
		success : function(response) {
			$('#teachFroms').html(response);
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

/*============================= WORK EXPERIENCE =============================*/

/*----- work experience list jsp page -----*/

function addNewTeacherWorkExp(ti_id){
	
	$("#teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "teacheraction_workexp_addform.action",				
		data : "ti_id=" + ti_id,
		success : function(response) {
			$('#teachFroms').html(response);
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

function editTeacherWorkExp(twe_id, ti_id, name_of_institute, job_title, date_from, date_to, twe_status){
	
	$("#teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "teacheraction_workexp_updateform.action",				
		data : "twe_id=" + twe_id + "&ti_id=" + ti_id + "&name_of_institute=" + name_of_institute + "&job_title=" + job_title + "&date_from=" + date_from + "&date_to=" + date_to + "&twe_status=" + twe_status,
		success : function(response) {
			$('#teachFroms').html(response);
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

/*----- work expeirence edit jsp page -----*/

function updateWorkExp(ti_id, twe_id){

	var name_of_institute = $('#name_of_institute').val();
	var job_title = $('#job_title').val();
	var date_from = $('#date_from').val();
	var date_to = $('#date_to').val();
	var twe_status = $('#twe_status').val();
	
	if(name_of_institute == ""){
		document.getElementById("errMsg").innerHTML = "Institute name is required.";
	} else if(job_title == ""){
		document.getElementById("errMsg").innerHTML = "Job title is required.";
	} else if(date_from == ""){
		document.getElementById("errMsg").innerHTML = "Date from is required.";
	} else if(date_to == ""){
		document.getElementById("errMsg").innerHTML = "Date to is required.";
	} else {

		$("#teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "teacheraction_workexp_update.action",				
			data : "twe_id=" + twe_id + "&ti_id=" + ti_id + "&name_of_institute=" + name_of_institute + "&job_title=" + job_title + "&date_from=" + date_from + "&date_to=" + date_to + "&twe_status=" + twe_status,
			success : function(response) {
				$('#teachFroms').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	
	}
}

/*------ work experience create jsp page -----*/

function saveWorkExp(ti_id){

	var institute_name = $('#institute_name').val();
	var job_title = $('#job_title').val();
	var date_from = $('#date_from').val();
	var date_to = $('#date_to').val();
	
	if(institute_name == ""){
		document.getElementById("errMsg").innerHTML = "Institute name is required.";
	} else if(job_title == ""){
		document.getElementById("errMsg").innerHTML = "Job title is required.";
	} else if(date_from == ""){
		document.getElementById("errMsg").innerHTML = "Date from is required.";
	} else if(date_to == ""){
		document.getElementById("errMsg").innerHTML = "Date to is required.";
	} else {

		$("#teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "teacheraction_workexp_save.action",				
			data : "ti_id=" + ti_id + "&name_of_institute=" + institute_name + "&job_title=" + job_title + "&date_from=" + date_from + "&date_to=" + date_to,
			success : function(response) {
				$('#teachFroms').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	
	}
}

/*============================= REWARD =============================*/

/*----- reward list jsp page -----*/

function addNewReward(tiid){
	$("teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "teacheraction_reward_save_form.action",				
		data : "ti_id=" + tiid,
		success : function(response) {
			$('#teachFroms').html(response);				
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

function editReward(tr_id, tr_type, tr_reason, tr_date, tr_status, tiid){
	$("teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "teacheraction_reward_update_form.action",				
		data : "tr_id=" + tr_id + "&tr_type=" + tr_type + "&tr_reason=" + tr_reason + "&tr_date=" + tr_date + "&tr_status=" + tr_status + "&ti_id=" + tiid,
		success : function(response) {
			$('#teachFroms').html(response);				
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

/*----- reward edit jsp page -----*/

function rewardUpdate(tr_id, ti_id){
	
	var tr_type = $('#tr_type').val();
	var tr_reason = $('#tr_reason').val();
	var tr_date = $('#tr_date').val();
	var tr_status = $('#tr_status').val();
	
	if(tr_type == ""){
		document.getElementById("errMsg").innerHTML = "Reward type is required.";
	} else if(tr_reason == ""){
		document.getElementById("errMsg").innerHTML = "Reward reason is required.";
	} else if(tr_date == ""){
		document.getElementById("errMsg").innerHTML = "Reward date is required.";
	} else {
	
		$("teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "teacheraction_reward_update.action",				
			data : "tr_id=" + tr_id + "&tr_type=" + tr_type + "&tr_reason=" + tr_reason + "&tr_date=" + tr_date + "&tr_status=" + tr_status + "&ti_id=" + ti_id,
			success : function(response) {
				$('#teachFroms').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	
	}
}

/*----- reward create jsp page -----*/

function rewardSave(tiid){
	
	var tr_type = $('#tr_type').val();
	var tr_reason = $('#tr_reason').val();
	var tr_date = $('#tr_date').val();
	
	if(tr_type == ""){
		document.getElementById("errMsg").innerHTML = "Reward type is required.";
	} else if(tr_reason == ""){
		document.getElementById("errMsg").innerHTML = "Reward reason is required.";
	} else if(tr_date == ""){
		document.getElementById("errMsg").innerHTML = "Reward date is required.";
	} else {
	
		$("teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "teacheraction_reward_save.action",				
			data : "ti_id=" + tiid + "&tr_type=" + tr_type + "&tr_reason=" + tr_reason + "&tr_date=" + tr_date,
			success : function(response) {
				$('#teachFroms').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	
	}
}

/*============================= EMERGENCY CONTACT =============================*/

/*----- emergency contact edit jsp page -----*/

function saveTeachEmrgCont(tiid, frmtype){
	
	var contname = $('#contname').val();
	var rship = $('#rship').val();
	var mobileno = $('#mobileno').val();
	var homephone = $('#homephone').val();
	var officephone = $('#officephone').val();
	
	var pattern = /^[A-Za-z //]{3,20}$/;
	var contname_pattern = pattern.test(contname);
	var rship_pattern = pattern.test(rship);
	
	var pattern_phone = /^[0-9 ]{10,15}$/;
	var mobileno_pattern = pattern_phone.test(mobileno);
	var homephone_pattern = pattern_phone.test(homephone);		
	var officephone_pattern = pattern_phone.test(officephone);
	
	if(contname == ""){
		document.getElementById("errMsg").innerHTML = "Contact name is required";
	} else if(contname_pattern == false){
		document.getElementById("errMsg").innerHTML = "Invalide contact name content.";
	} else if(rship == ""){
		document.getElementById("errMsg").innerHTML = "Relationship is required";
	} else if(rship_pattern == false){
		document.getElementById("errMsg").innerHTML = "Invalide relationship content";
	} else if(mobileno_pattern == false && mobileno != ""){
		document.getElementById("errMsg").innerHTML = "Invalide mobile number.";
	} else if(homephone_pattern == false && homephone != ""){
		document.getElementById("errMsg").innerHTML = "Invalide home phone number.";
	} else if(officephone_pattern == false && officephone != ""){
		document.getElementById("errMsg").innerHTML = "Invalide office phone number.";
	} else if(mobileno == "" && homephone == "" && officephone == ""){
		document.getElementById("errMsg").innerHTML = "At least one of the three number is required";
	} else {
	
		$("emergcontFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "teacheraction_emrgcont_save.action",				
			data : "ti_id=" + tiid + "&contact_name=" + contname + "&relationship=" + rship + "&mobile_no=" + mobileno + "&home_phone=" + homephone + "&office_phone=" + officephone + "&frmtype=" + frmtype,
			success : function(response) {
				$('#emergcontFrm').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	
	}
}

function updateTeachEmrgCont(tec_id, frmtype){
	
	var contname = $('#contname').val();
	var rship = $('#rship').val();
	var mobileno = $('#mobileno').val();
	var homephone = $('#homephone').val();
	var officephone = $('#officephone').val();
	var tec_status = $('#tec_status').val();
	
	var pattern = /^[A-Za-z //]{3,20}$/;
	var contname_pattern = pattern.test(contname);
	var rship_pattern = pattern.test(rship);
	
	var pattern_phone = /^[0-9 ]{10,15}$/;
	var mobileno_pattern = pattern_phone.test(mobileno);
	var homephone_pattern = pattern_phone.test(homephone);		
	var officephone_pattern = pattern_phone.test(officephone);
	
	if(contname == ""){
		document.getElementById("errMsg").innerHTML = "Contact name is required";
	} else if(contname_pattern == false){
		document.getElementById("errMsg").innerHTML = "Invalide contact name content.";
	} else if(rship == ""){
		document.getElementById("errMsg").innerHTML = "Relationship is required";
	} else if(rship_pattern == false){
		document.getElementById("errMsg").innerHTML = "Invalide relationship content";
	} else if(mobileno_pattern == false && mobileno != ""){
		document.getElementById("errMsg").innerHTML = "Invalide mobile number.";
	} else if(homephone_pattern == false && homephone != ""){
		document.getElementById("errMsg").innerHTML = "Invalide home phone number.";
	} else if(officephone_pattern == false && officephone != ""){
		document.getElementById("errMsg").innerHTML = "Invalide office phone number.";
	} else if(mobileno == "" && homephone == "" && officephone == ""){
		document.getElementById("errMsg").innerHTML = "At least one of the three number is required";
	} else {
	
		$("emergcontFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "teacheraction_emrgcont_update.action",				
			data : "tec_id=" + tec_id + "&contact_name=" + contname + "&relationship=" + rship + "&mobile_no=" + mobileno + "&home_phone=" + homephone + "&office_phone=" + officephone + "&frmtype=" + frmtype + "&tec_status=" + tec_status,
			success : function(response) {
				$('#emergcontFrm').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	
	}
	
}

/*============================= EDUCATIONAL BACKGROUND =============================*/

/*----- educational background list jsp page -----*/

function addNewTeacherEduBackground(ti_id){
	
	$("#teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "teacheraction_edubackground_addform.action",				
		data : "ti_id=" + ti_id,
		success : function(response) {
			$('#teachFroms').html(response);
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

function editTeacherEduBackground(teb_id, ti_id, name_of_institiute, field_of_study, graduation_date, award, teb_status){
	
	$("#teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "teacheraction_edubackground_updateform.action",				
		data : "teb_id=" + teb_id + "&ti_id=" + ti_id + "&name_of_institiute=" + name_of_institiute + "&field_of_study=" + field_of_study + "&graduation_date=" + graduation_date + "&award=" + award + "&teb_status=" + teb_status,
		success : function(response) {
			$('#teachFroms').html(response);
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

/*----- educational background edit jsp page -----*/

function updateEduBackground(ti_id, teb_id){

	var institute_name = $('#institute_name').val();
	var field_of_study = $('#field_of_study').val();
	var graduation_date = $('#graduation_date').val();
	var award = $('#award').val();
	var teb_status = $('#teb_status').val();
	
	if(institute_name == ""){
		document.getElementById("errMsg").innerHTML = "Institute name is required.";
	} else if(field_of_study == ""){
		document.getElementById("errMsg").innerHTML = "Field of study is required.";
	} else if(graduation_date == ""){
		document.getElementById("errMsg").innerHTML = "Graduation date is required.";
	} else if(award == 0){
		document.getElementById("errMsg").innerHTML = "Award is required.";
	} else {

		$("#teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "teacheraction_edubackground_update.action",				
			data : "teb_id=" + teb_id + "&ti_id=" + ti_id + "&name_of_institiute=" + institute_name + "&field_of_study=" + field_of_study + "&graduation_date=" + graduation_date + "&award=" + award + "&teb_status=" + teb_status,
			success : function(response) {
				$('#teachFroms').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	
	}
}

/*----- educational background create jsp page -----*/

function saveEduBackground(ti_id){

	var institute_name = $('#institute_name').val();
	var field_of_study = $('#field_of_study').val();
	var graduation_date = $('#graduation_date').val();
	var award = $('#award').val();
	
	if(institute_name == ""){
		document.getElementById("errMsg").innerHTML = "Institute name is required.";
	} else if(field_of_study == ""){
		document.getElementById("errMsg").innerHTML = "Field of study is required.";
	} else if(graduation_date == ""){
		document.getElementById("errMsg").innerHTML = "Graduation date is required.";
	} else if(award == 0){
		document.getElementById("errMsg").innerHTML = "Award is required.";
	} else {

		$("#teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "teacheraction_edubackground_save.action",				
			data : "ti_id=" + ti_id + "&name_of_institiute=" + institute_name + "&field_of_study=" + field_of_study + "&graduation_date=" + graduation_date + "&award=" + award,
			success : function(response) {
				$('#teachFroms').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	
	}
}

/*============================= DISCIPLINARY ACTION =============================*/

/*----- disciplinary action list jsp page -----*/

function addDisAct(ti_id){
	$("teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "teacheraction_disact_creat_form.action",				
		data : "ti_id=" + ti_id,
		success : function(response) {
			$('#teachFroms').html(response);				
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

function editDisact(ti_id, tda_id, tda_type, tda_reason, tda_date, tda_status){
	$("teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "teacheraction_disact_edit_form.action",				
		data : "ti_id=" + ti_id + "&tda_id=" + tda_id + "&tda_type=" + tda_type + "&tda_reason=" + tda_reason + "&tda_date=" + tda_date + "&tda_status=" + tda_status,
		success : function(response) {
			$('#teachFroms').html(response);				
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

/*----- disciplinary action edit jsp page -----*/

function updateDisAct(ti_id, tda_id){
	var tda_type = $('#tda_type').val();
	var tda_reason = $('#tda_reason').val();
	var tda_date = $('#tda_date').val();
	var tda_status = $('#tda_status').val();
	
	if(tda_type == ""){
		document.getElementById("errMsg").innerHTML = "Disciplinary action type is required.";
	} else if(tda_reason == ""){
		document.getElementById("errMsg").innerHTML = "Disciplinary action reason is required.";
	} else if(tda_date == ""){
		document.getElementById("errMsg").innerHTML = "Disciplinary action date is required.";
	} else {			
	
		$("teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "teacheraction_disact_update.action",				
			data : "ti_id=" + ti_id + "&tda_id=" + tda_id + "&tda_type=" + tda_type + "&tda_reason=" + tda_reason + "&tda_date=" + tda_date + "&tda_status=" + tda_status,
			success : function(response) {
				$('#teachFroms').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	
	}
}

/*----- disciplinary action create jsp page -----*/

function saveDisAct(ti_id){
	var tda_type = $('#tda_type').val();
	var tda_reason = $('#tda_reason').val();
	var tda_date = $('#tda_date').val();
	
	if(tda_type == ""){
		document.getElementById("errMsg").innerHTML = "Disciplinary action type is required.";
	} else if(tda_reason == ""){
		document.getElementById("errMsg").innerHTML = "Disciplinary action reason is required.";
	} else if(tda_date == ""){
		document.getElementById("errMsg").innerHTML = "Disciplinary action date is required.";
	} else {
			
		$("teachFroms").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "teacheraction_disact_save.action",				
			data : "ti_id=" + ti_id + "&tda_type=" + tda_type + "&tda_reason=" + tda_reason + "&tda_date=" + tda_date,
			success : function(response) {
				$('#teachFroms').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	
	}
}

/*============================= CURRENT ADDRESS =============================*/

/*----- current address edit jsp page -----*/

function saveTeachCurrAdd(tiid, frmtype){
	
	var subcity = $('#subcity').val();
	var kebele = $('#kebele').val();
	var house_no = $('#houseno').val();
	var house_phone_no = $('#housephoneno').val();
	var mobileno = $('#mobileno').val();
	var email = $('#email').val();
	
	var emailValidator = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	
	var pattern_phone = /^[0-9 ]{9,15}$/;
	var house_phone_pattern = pattern_phone.test(house_phone_no);
	var mobileno_pattern = pattern_phone.test(mobileno);
	
	if(subcity == ""){
		document.getElementById("errMsg").innerHTML = "Subcity is required.";
	} else if(kebele == ""){
		document.getElementById("errMsg").innerHTML = "Kebele is required.";
	} else if(house_phone_pattern == false && house_phone_no != ""){
		document.getElementById("errMsg").innerHTML = "Invalide house phone number number.";
	} else if(mobileno != "" && mobileno_pattern == false){
		document.getElementById("errMsg").innerHTML = "Invalide mobile number.";
	} else if(house_phone_no == "" && mobileno == ""){
		document.getElementById("errMsg").innerHTML = "Either house phone number or mobile number is required.";
	} else if(!emailValidator.test(email)){
		document.getElementById("errMsg").innerHTML = "Please insert a valid email address.";
	} else {
		
		$("#curaddFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "teacheraction_curadd_save.action",				
			data : "ti_id=" + tiid + "&sub_city=" + subcity + "&kebele=" + kebele + "&house_no=" + house_no + "&house_phone_no=" + house_phone_no + "&mobile_no=" + mobileno + "&frmtype=" + frmtype + "&email=" + email,
			success : function(response) {
				$('#curaddFrm').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	
	}
}

function updateTeachCurrAdd(tca_id, frmtype){
	
	var subcity = $('#subcity').val();
	var kebele = $('#kebele').val();
	var house_no = $('#houseno').val();
	var house_phone_no = $('#housephoneno').val();
	var mobileno = $('#mobileno').val();
	var email = $('#email').val();
	var tca_status = $('#tca_status').val();
	
	var emailValidator = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	
	var pattern_phone = /^[0-9 ]{9,15}$/;
	var house_phone_pattern = pattern_phone.test(house_phone_no);
	var mobileno_pattern = pattern_phone.test(mobileno);
	
	if(subcity == ""){
		document.getElementById("errMsg").innerHTML = "Subcity is required.";
	} else if(kebele == ""){
		document.getElementById("errMsg").innerHTML = "Kebele is required.";
	} else if(house_phone_pattern == false && house_phone_no != ""){
		document.getElementById("errMsg").innerHTML = "Invalide house phone number number.";
	} else if(mobileno != "" && mobileno_pattern == false){
		document.getElementById("errMsg").innerHTML = "Invalide mobile number.";
	} else if(house_phone_no == "" && mobileno == ""){
		document.getElementById("errMsg").innerHTML = "Either house phone number or mobile number is required.";
	} else if(!emailValidator.test(email)){
		document.getElementById("errMsg").innerHTML = "Please insert a valid email address.";
	} else {
	
		$("#curaddFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "teacheraction_curaddupdate.action",				
			data : "tca_id=" + tca_id + "&sub_city=" + subcity + "&kebele=" + kebele + "&house_no=" + house_no + "&house_phone_no=" + house_phone_no + "&mobile_no=" + mobileno + "&frmtype=" + frmtype + "&ica_status=" + tca_status + "&email=" + email,
			success : function(response) {
				$('#curaddFrm').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	
	}		
}

/*============================= CLASS ASSIGNMENT =============================*/

/*----- class assignment list jsp page -----*/

function teacherAssignment(ti_id){		
	$("#assignContainer").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "teacheraction_assignsavefrm.action",				
		data : "ti_id=" + ti_id,
		success : function(response) {
			$('#assignContainer').html(response);
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

function editTeacherAssignment(ti_id, ta_id, sub_id, cl_id, ta_status){
	
	$("#assignContainer").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "teacheraction_assignupdatefrm.action",				
		data : "ti_id=" + ti_id + "&ta_id=" + ta_id + "&sub_id=" + sub_id + "&cl_id=" + cl_id + "&ta_status=" + ta_status,
		success : function(response) {
			$('#assignContainer').html(response);
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

/*----- class assignment edit jsp page -----*/

function updateTeachAssign(ta_id, ti_id){
	
	var sub = $('#sub').val();
	var cl = $('#cl').val();
	var status = $('#status').val();
	
	$("#assignContainer").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "teacheraction_assignupdate.action",				
		data : "ti_id=" + ti_id + "&ta_id=" + ta_id + "&sub_id=" + sub + "&cl_id=" + cl + "&ta_status=" + status,
		success : function(response) {
			$('#assignContainer').html(response);
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

/*----- class assignment create jsp page -----*/

function saveTeachAssign(ti_id){
	var sub = $('#sub').val();
	var cllist = $('#cllist').val();
	var year = $('#year').val();
	
	$("#assignContainer").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "teacheraction_assignsave.action",				
		data : "ti_id=" + ti_id + "&sub_id=" + sub + "&cl_id=" + cllist + "&year=" + year,
		success : function(response) {
			$('#assignContainer').html(response);
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

function getSubjectList(cl_id){
	$.ajax({
		type : "POST",
		url : "teacheraction_subjectlist.action",				
		data : "cl_id=" + cl_id,
		success : function(response) {
			$('#subListPerGrade').html(response);
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}