/**
 * 
 */
/*======================= STUDENT REGISTRATION GENERAL =======================*/

/*----- STUDENT TEMPLATE JSP PAGE -----*/

	function student_create(){
		
		$("#stud_form").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_create.action",				
			data : "",
			success : function(response) {
				//alert(response.trim().length);
				if(response.trim().length == 3503){
					$('#indexContent').html(response);
				} else {
					$('#stud_form').html(response);
				}
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function filterStudentPerYear(acyear){
		$("#studpergrade").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_filterbyyear.action",				
			data : "ac_year=" + acyear,
			success : function(response) {
				$('#studpergrade').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- STUDENT MALE FEMALE NUM PER CLASS PAGE -----*/
	
	function listAllStudents(){
		
		var ac_year = $("#academicYear").val();
		
		$("#student_list").html("<div width=\"100%\" align=\"center\"> <img src=\"images/loader.gif\"/> <br/> <img src=\"images/information.jpeg\" width=\"20px;\"/> <span style=\"color: GREEN\"> It is processing huge data, it might take longer time.</span> </div>");
		$.ajax({
			type : "POST",
			url : "studentaction_allstudentlist.action",				
			data : "ac_year=" + ac_year,
			success : function(response) {
				$('#student_list').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}

	function listAllInactiveStudents(){
		
		var ac_year = $("#academicYear").val();
		
		$("#student_list").html("<div width=\"100%\" align=\"center\"> <img src=\"images/loader.gif\"/> <br/> <img src=\"images/information.jpeg\" width=\"20px;\"/> <span style=\"color: GREEN\"> It is processing huge data, it might take longer time.</span> </div>");
		$.ajax({
			type : "POST",
			url : "studentaction_allinactivestudentlist.action",				
			data : "ac_year=" + ac_year,
			success : function(response) {
				$('#student_list').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}

	function candidateStudentsList(){
		
		$("#student_list").html("<div width=\"100%\" align=\"center\"> <img src=\"images/loader.gif\"/> <br/> <img src=\"images/information.jpeg\" width=\"20px;\"/> <span style=\"color: GREEN\"> It is processing huge data, it might take longer time.</span> </div>");
		$.ajax({
			type : "POST",
			url : "studentaction_candidatestudentlist.action",				
			data : "",
			success : function(response) {
				$('#student_list').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function deleteRegisteredCandidate(si_id){
		
		$("#student_list").html("<div width=\"100%\" align=\"center\"> <img src=\"images/loader.gif\"/> <br/> <img src=\"images/information.jpeg\" width=\"20px;\"/> <span style=\"color: GREEN\"> It is processing huge data, it might take longer time.</span> </div>");
		$.ajax({
			type : "POST",
			url : "studentaction_deletecandidatestudent.action",				
			data : "si_id=" + si_id,
			success : function(response) {
				$('#student_list').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- STUDENT MALE FEMALE NUM PER CLASS DETAIL PAGE -----*/
	
	function listAllClassStudents(class_id){
		
		var ac_year = $("#academicYear").val();
		
		$("#student_list_perdetial").html("<div width=\"100%\" align=\"center\"> <img src=\"images/loader.gif\"/> <br/> <img src=\"images/information.jpeg\" width=\"20px;\"/> <span style=\"color: GREEN\"> It is processing huge data, it might take longer time.</span> </div>");
		$.ajax({
			type : "POST",
			url : "studentaction_allstudentlist_pergrade.action",				
			data : "class_id=" + class_id + "&ac_year=" + ac_year,
			success : function(response) {
				$('#student_list_perdetial').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- STUDENT LIST PAGE -----*/
	
	var prev_cdid = null;
	function classDetail(clid, cd_id, ac_year){
		
		$("#student_list_perdetial").html("<div width=\"100%\" align=\"center\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_classdetail.action",				
			data : "class_id=" + clid + "&cd_id=" + cd_id + "&ac_year=" + ac_year,
			success : function(response){
				if(prev_cdid == null){
					document.getElementById(cd_id+"_cd").style.color = "#ffffff";
					document.getElementById(cd_id+"_cd").style.backgroundColor = "#3d6e9f";
				} else {
					document.getElementById(prev_cdid+"_cd").style.backgroundColor = '#e5e5e5';
					document.getElementById(prev_cdid+"_cd").style.color = '#3d6e9f';
					document.getElementById(cd_id+"_cd").style.backgroundColor = '#3d6e9f';
					document.getElementById(cd_id+"_cd").style.color = '#ffffff';
				}
				$('#student_list_perdetial').html(response);
				prev_cdid = cd_id;
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	
	
	/*----- STUDENT LIST FOR REGISTRATION PAGE - to clear studentListForRegistration list -----*/
	
	var studentListForRegistration = new Array();
	
	/*----- END - STUDENT LIST FOR REGISTRATION PAGE - to clear studentListForRegistration list -----*/
	
	
	
	function studentRegistration(clid, clname){
		
		studentListForRegistration = [];
		
		var ac_year = $("#academicYear").val();
		
		$("#student_list").html("<div width=\"100%\" align=\"center\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_studentlist.action",				
			data : "cl_id=" + clid + "&cl_name=" + clname + "&academic_year=" + ac_year,
			success : function(response) {
				$('#student_list').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- STUDENT LIST PER GRADE PAGE -----*/
	
	function student_edit_per_grade(id_no, si_id, fname, mname, gname, sex, dob, pob, nationality, sistatus, mothername, photo_path, photo_name, class_id, cd_id, cdid){
		
		var group_cd_id = cd_id;
		var individual_cd_id = cdid;
		
		var cd_id_tobeused = "";
		
		if(group_cd_id != ""){
			cd_id_tobeused = cd_id;
		} else {
			cd_id_tobeused = cdid;
		}
		
		$("#student_list_pergrade").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_edit.action",				
			data : "id_no=" + id_no + "&si_id=" + si_id + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&sex=" + sex + "&dob=" + dob + "&pob=" + pob + "&nationality=" + nationality + "&si_status=" + sistatus + "&mother_name=" + mothername + "&photo_path=" + photo_path + "&photo_name=" + photo_name + "&class_id=" + class_id + "&cd_id=" + cd_id_tobeused,
			success : function(response) {
				
				$('#student_list_pergrade').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function listStudentContactInfo(cl_id, cd_id){
		
		var ac_year = $("#academicYear").val();
		
		$("#student_list_pergrade").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_getStudentContactInfo.action",				
			data : "class_id=" + cl_id + "&cd_id=" + cd_id + "&ac_year=" + ac_year,
			success : function(response) {
				$('#student_list_pergrade').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function studentInfoWithEmergencyContact(cl_id, cd_id){
		
		var ac_year = $("#academicYear").val();
		
		$("#student_list_pergrade").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_getStudentInfoWithEmergencyContact.action",				
			data : "class_id=" + cl_id + "&cd_id=" + cd_id + "&ac_year=" + ac_year,
			success : function(response) {
				$('#student_list_pergrade').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function importStudentContactInfoFromExcelForm(cl_id, cd_id){
		
		$("#student_list_pergrade").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_importStudentContactInfoFromExcelForm.action",				
			data : "class_id=" + cl_id + "&cd_id=" + cd_id,
			success : function(response) {
				$('#student_list_pergrade').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- STUDENT LIST ALL PAGE -----*/
	
	var prev_clid = null;
	
	function gradeSelected(cl_id, clname){
		
		var ac_year = $("#academicYear").val();
		
		$("#student_list").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_pergradelist.action",				
			data : "class_id=" + cl_id + "&class_name=" + clname + "&ac_year=" + ac_year,
			success : function(response) {
				
				if(prev_clid == null){
					document.getElementById(cl_id).style.backgroundColor = '#e5e5e5';
					document.getElementById(cl_id).style.color = '#3d6e9f';
				} else {
					document.getElementById(prev_clid).style.backgroundColor = '';
					document.getElementById(prev_clid).style.color = '#000000';
					document.getElementById(cl_id).style.backgroundColor = '#e5e5e5';
					document.getElementById(cl_id).style.color = '#3d6e9f';
				}
				
				$('#student_list').html(response);
				prev_clid = cl_id;
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- STUDENT EDIT PAGE -----*/
	
	function upload_photo(si_id, class_id){
		$("#student_additionalinfo").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_photoupload.action",				
			data : "si_id=" + si_id + "&class_id=" + class_id,
			success : function(response) {
				$('#student_additionalinfo').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- STUDENT DROPOUT FORM PAGE -----*/
	
	function studentDropOut(siid, fname, mname, gname, dropout_status){
		
		$("#dropout_div").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_dropout.action",				
			data : "si_id=" + siid + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&dropout_status=" + dropout_status,
			success : function(response) {
				$('#dropout_div').html(response);					
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- STUDENT DROPOUT CONFIRMATION PAGE -----*/
	
	function cancleDropOut(siid, fname, mname, gname, dropout_status){
		
		$("#dropout_div").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_cancledropout.action",				
			data : "si_id=" + siid + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&dropout_status=" + dropout_status,
			success : function(response) {
				$('#dropout_div').html(response);					
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function confirmDropOut(siid){
		
		$("#dropout_div").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_confirmdropout.action",				
			data : "si_id=" + siid ,
			success : function(response) {
				$('#dropout_div').html(response);					
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- STUDENT ADDITIONAL INFO SAVE MENU PAGE -----*/
	
	function student_info_save(){
		$("#student_additionalinfo_save").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_editform_save.action",				
			data : "",
			success : function(response) {
				document.getElementById("si").style.backgroundColor = '#e5e5e5';
				document.getElementById("ca").style.backgroundColor = '#ffffff';
				document.getElementById("ec").style.backgroundColor = '#ffffff';
				document.getElementById("gh").style.backgroundColor = '#ffffff';
				document.getElementById("r").style.backgroundColor = '#ffffff';
				document.getElementById("da").style.backgroundColor = '#ffffff';
				$('#student_additionalinfo_save').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function current_address_save(){
		$("#student_additionalinfo_save").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_currentaddress_save.action",				
			data : "",
			success : function(response) {
				document.getElementById("si").style.backgroundColor = '#ffffff';
				document.getElementById("ca").style.backgroundColor = '#e5e5e5';
				document.getElementById("ec").style.backgroundColor = '#ffffff';
				document.getElementById("gh").style.backgroundColor = '#ffffff';
				document.getElementById("r").style.backgroundColor = '#ffffff';
				document.getElementById("da").style.backgroundColor = '#ffffff';
				$('#student_additionalinfo_save').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function emergency_contact_save(){
		$("#student_additionalinfo_save").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_emergcontact_create.action",				
			data : "",
			success : function(response) {
				document.getElementById("si").style.backgroundColor = '#ffffff';
				document.getElementById("ca").style.backgroundColor = '#ffffff';
				document.getElementById("ec").style.backgroundColor = '#e5e5e5';
				document.getElementById("gh").style.backgroundColor = '#ffffff';
				document.getElementById("r").style.backgroundColor = '#ffffff';
				document.getElementById("da").style.backgroundColor = '#ffffff';
				$('#student_additionalinfo_save').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});	
	}
	
	function grade_history_save(){
		$("#student_additionalinfo_save").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_grdhistory_save.action",				
			data : "",
			success : function(response) {
				document.getElementById("si").style.backgroundColor = '#ffffff';
				document.getElementById("ca").style.backgroundColor = '#ffffff';
				document.getElementById("ec").style.backgroundColor = '#ffffff';
				document.getElementById("gh").style.backgroundColor = '#e5e5e5';
				document.getElementById("r").style.backgroundColor = '#ffffff';
				document.getElementById("da").style.backgroundColor = '#ffffff';
				$('#student_additionalinfo_save').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});	
	}
	
	function reward_save(){
		$("#student_additionalinfo_save").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_reward.action",				
			data : "",
			success : function(response) {
				document.getElementById("si").style.backgroundColor = '#ffffff';
				document.getElementById("ca").style.backgroundColor = '#ffffff';
				document.getElementById("ec").style.backgroundColor = '#ffffff';
				document.getElementById("gh").style.backgroundColor = '#ffffff';
				document.getElementById("r").style.backgroundColor = '#e5e5e5';
				document.getElementById("da").style.backgroundColor = '#ffffff';
				$('#student_additionalinfo_save').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});	
	}
	
	function disciplinary_action_save(){
		$("#student_additionalinfo_save").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_disaction_create.action",				
			data : "",
			success : function(response) {
				document.getElementById("si").style.backgroundColor = '#ffffff';
				document.getElementById("ca").style.backgroundColor = '#ffffff';
				document.getElementById("ec").style.backgroundColor = '#ffffff';
				document.getElementById("gh").style.backgroundColor = '#ffffff';
				document.getElementById("r").style.backgroundColor = '#ffffff';
				document.getElementById("da").style.backgroundColor = '#e5e5e5';
				$('#student_additionalinfo_save').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- STUDENT ADDITIONAL INFO MENU PAGE -----*/
	
	function student_info_edit(id_no, si_id, fname, mname, gname, sex, dob, pob, sistatus, mothername, class_id, cd_id){
		
		$("#student_additionalinfo").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_editform.action",				
			data : "id_no=" + id_no + "&si_id=" + si_id + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&sex=" + sex + "&dob=" + dob + "&pob=" + pob + "&si_status=" + sistatus + "&mother_name=" + mothername + "&class_id=" + class_id + "&cd_id=" + cd_id,
			//data : "si_id=" + si_id,
			success : function(response) {
				document.getElementById("si").style.backgroundColor = '#e5e5e5';
				document.getElementById("ca").style.backgroundColor = '';
				document.getElementById("ec").style.backgroundColor = '';
				//document.getElementById("gh").style.backgroundColor = '';
				document.getElementById("r").style.backgroundColor = '';
				document.getElementById("da").style.backgroundColor = '';
				$('#student_additionalinfo').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function current_address(siid){
		
		$("#student_additionalinfo").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_currentaddress.action",				
			data : "si_id=" + siid,
			success : function(response) {
				document.getElementById("si").style.backgroundColor = '';
				document.getElementById("ca").style.backgroundColor = '#e5e5e5';
				document.getElementById("ec").style.backgroundColor = '';
				//document.getElementById("gh").style.backgroundColor = '';
				document.getElementById("r").style.backgroundColor = '';
				document.getElementById("da").style.backgroundColor = '';
				$('#student_additionalinfo').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function emergency_contact(siid){
		$("#student_additionalinfo").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_emergcontact.action",				
			data : "si_id=" + siid,
			success : function(response) {
				document.getElementById("si").style.backgroundColor = '';
				document.getElementById("ca").style.backgroundColor = '';
				document.getElementById("ec").style.backgroundColor = '#e5e5e5';
				//document.getElementById("gh").style.backgroundColor = '';
				document.getElementById("r").style.backgroundColor = '';
				document.getElementById("da").style.backgroundColor = '';
				$('#student_additionalinfo').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function grade_history(siid){
		$("#student_additionalinfo").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_grdhistory.action",				
			data : "si_id=" + siid,
			success : function(response) {
				document.getElementById("si").style.backgroundColor = '';
				document.getElementById("ca").style.backgroundColor = '';
				document.getElementById("ec").style.backgroundColor = '';
				//document.getElementById("gh").style.backgroundColor = '#e5e5e5';
				document.getElementById("r").style.backgroundColor = '';
				document.getElementById("da").style.backgroundColor = '';
				$('#student_additionalinfo').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function reward(siid){
		$("#student_additionalinfo").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_reward_list.action",				
			data : "si_id=" + siid,
			success : function(response) {
				document.getElementById("si").style.backgroundColor = '';
				document.getElementById("ca").style.backgroundColor = '';
				document.getElementById("ec").style.backgroundColor = '';
				//document.getElementById("gh").style.backgroundColor = '';
				document.getElementById("r").style.backgroundColor = '#e5e5e5';
				document.getElementById("da").style.backgroundColor = '';
				$('#student_additionalinfo').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function disciplinary_action(siid){
		$("#student_additionalinfo").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_disaction.action",				
			data : "si_id=" + siid,
			success : function(response) {
				document.getElementById("si").style.backgroundColor = '';
				document.getElementById("ca").style.backgroundColor = '';
				document.getElementById("ec").style.backgroundColor = '';
				//document.getElementById("gh").style.backgroundColor = '';
				document.getElementById("r").style.backgroundColor = '';
				document.getElementById("da").style.backgroundColor = '#e5e5e5';
				$('#student_additionalinfo').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- IMPORT STUDENT CONTACT INFO FROM EXCEL PAGE -----*/
	
	function importStudContactInfoFromExcel(cl_id, cd_id){
		
		var file_path = $('#file_path').val();
		var sheetNum = $('#sheetNum').val();
		var columnNum = $('#columnNum').val();
		var rowNum = $('#rowNum').val();
		
		var relationship = $('#relationship').val();
		
		if(file_path == ""){
			
			document.getElementById("errMsg").innerHTML = "File path is required.";
		} else if(sheetNum == ""){
			
			document.getElementById("errMsg").innerHTML = "Sheet Number is required.";
		} else if(rowNum == ""){
			
			document.getElementById("errMsg").innerHTML = "Row Number is required.";
		} else if(columnNum == ""){
			
			document.getElementById("errMsg").innerHTML = "Column Number is required.";			
		} else if(relationship == 0){
			
			document.getElementById("errMsg").innerHTML = "Relationship is required.";
		} else {
			
			$("#student_list_pergrade").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "studentaction_importStudContactInfoFromExcel.action",				
				data : "examResultBean.file_path=" + file_path + "&examResultBean.column_num=" + columnNum + "&examResultBean.row_num=" + rowNum + "&examResultBean.sheet_num=" + sheetNum + "&class_id=" + cl_id + "&cd_id=" + cd_id + "&emergencyContactBean.relationship=" + relationship,
				success : function(response) {
					$('#student_list_pergrade').html(response);				
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});			
		}		
	}

	/*======================= STUDENT CLASS REGISTRATION =======================*/
	
	/*----- STUDENT LIST SELECTED PAGE -----*/
	
	var section = "";
	
	var removedStudentList = new Array();
	
	function removedStudentListFromSelectedStudList(inde, stud_id, selclid){
		
		var counter = studentListForRegistration.length;
		
		removedStudentList[counter] = new Array(stud_id);
		
		document.getElementById(stud_id + "_add").style.display = 'none';
	}
	
//	function removeSelectedStud(inde, stud_id, selclid){
//		
//		$("#selStudList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
//		$.ajax({
//			type : "POST",
//			url : "studentaction_removestudentlist.action",				
//			data : "inde=" + inde + "&selcl_id=" + selclid,
//			success : function(response) {
//				document.getElementById(stud_id + "_add").style.display = 'block';
//				$('#selStudList').html(response);
//			},
//			error : function(e) {
//				alert('Error: ' + e);
//			}
//		});
//	}
	
	function sectionSelected(sec){
		section = sec;
	}

	function registerStudent(selclid, rawcount){
		
		var acyear = $('#acYear').val();
		
		if(rawcount == 0){
			document.getElementById("errMsg").innerHTML = "There is no added student in the list";
		} else if(section == ''){
			document.getElementById("errMsg").innerHTML = "Section is mandatory field.";
		} else {
			$("#studentRegistrationContent").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "studentaction_regstudentlist.action",				
				data : "sec_id=" + section + "&selcl_id=" + selclid + "&academic_year=" + acyear + "&removeStudentFromRegistrationList=" + removedStudentList,
				success : function(response) {			
					$('#studentRegistrationContent').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	/*----- STUDENT LIST FOR REGISTRATION PAGE -----*/
	
	//moved to line 146 - to clear the list on register button click
	//var studentListForRegistration = new Array();
	
	function selectStudentsForRegistration(stud_id, stud_name, cl_id){
				
		var counter = studentListForRegistration.length;
		
		var checker = 0;
		
		for(var i = 0; i < counter; i++){
			
			if(studentListForRegistration[i][0] == stud_id){
				
				studentListForRegistration.splice(i, 1);				
				checker++;
				break;
			}
		}
		
		if(checker == 0){
		
			studentListForRegistration[counter] = new Array(stud_id, stud_name, cl_id);
		}
	}
	
	function addSelectedStudentsForRegistration(cl_id){
		
		$("#selStudList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_selectedstudentlistforregistration.action",				
			data : "studentListForRegistration=" + studentListForRegistration + "&selcl_id=" + cl_id,
			success : function(response) {				
				$('#selStudList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function selectStudent(){
		
		$("#selStudList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_selstudentlist.action",				
			data : "si_id=" + stud_id + "&stud_name=" + stud_name + "&selcl_id=" + clid,
			success : function(response) {
				document.getElementById(stud_id + "_add").style.display = 'none';
				$('#selStudList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*======================= STUDENT REWARD =======================*/
	
	/*----- STUDENT REWARD LIST PAGE -----*/

	function rewardEdit(srid, srtype, srreason, srdate, srstatus, si_id){
		
		$("#rewardlist_container").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_reward_edit.action",				
			data : "sr_id=" + srid + "&sr_type=" + srtype + "&sr_reason=" + srreason + "&sr_date=" + srdate + "&sr_status=" + srstatus + "&si_id=" + si_id,
			success : function(response) {
				$('#rewardlist_container').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addNewReward(siid){
		$("#rewardlist_container").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_reward.action",				
			data : "si_id=" + siid,
			success : function(response) {
				$('#rewardlist_container').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- STUDENT REWARD EDIT PAGE -----*/
	
	function updateReward(siid, srid){
		
		var rw_type = $('#rw_type').val();
		var rw_reason = $('#rw_reason').val();
		var rw_date = $('#rw_date').val();
		var rw_status = $('#rw_status').val();
		
		if(rw_type == ""){
			document.getElementById("errMsg").innerHTML = "Reward type is required.";
		} else if(rw_reason == ""){
			document.getElementById("errMsg").innerHTML = "Reward reason is required.";
		} else if(rw_date == ""){
			document.getElementById("errMsg").innerHTML = "Reward date is required.";
		} else {
		
			$("#reward_updateform").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "studentaction_reward_update.action",				
				data : "sr_type=" + rw_type + "&sr_reason=" + rw_reason + "&sr_date=" + rw_date + "&sr_status=" + rw_status + "&si_id=" + siid + "&sr_id=" + srid,
				success : function(response) {
					$('#reward_updateform').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}

	/*----- STUDENT REWARD CREATE PAGE -----*/

	function saveReward(siid){
		var rw_type = $('#rw_type').val();
		var rw_reason = $('#rw_reason').val();
		var rw_date = $('#rw_date').val();
		
		if(rw_type == ""){
			document.getElementById("errMsg").innerHTML = "Reward type is required.";
		} else if(rw_reason == ""){
			document.getElementById("errMsg").innerHTML = "Reward reason is required.";
		} else if(rw_date == ""){
			document.getElementById("errMsg").innerHTML = "Reward date is required.";
		} else {
		
			$("#reward_saveform").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "studentaction_reward_save.action",				
				data : "sr_type=" + rw_type + "&sr_reason=" + rw_reason + "&sr_date=" + rw_date + "&si_id=" + siid,
				success : function(response) {
					$('#reward_saveform').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	/*======================= STUDENT PERSONAL INFO =======================*/
	
	/*----- STUDENT PERSONAL INFO EDIT PAGE -----*/
	
	function changeClassDetail(si_id, class_id, cd_id){
		
		var new_cdid = $('#cd_id').val();
		if(cd_id == new_cdid){
			document.getElementById("cdErrMsg").innerHTML = "The same class room is selected.";
		} else {
		
			$("#cl_change").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "studentaction_changeclassroom.action",				
				data : "si_id=" + si_id + "&cd_id=" + new_cdid + "&cl_id=" + class_id,
				success : function(response) {
					$('#cl_change').html(response);					
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	var spNeedReq = "";
	function specialNeedRequired(value){
		spNeedReq = value;
	}
	
	function saveSpecialNeedRequirement(si_id){
		var snc_id = $('#snc_id').val();
		
		if(snc_id == ""){
			document.getElementById("errMsg").innerHTML = "Special need category is required.";
		} else {
			$("#specialNeed").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "studentaction_specialneedrequired.action",				
				data : "si_id=" + si_id + "&snc_id=" + snc_id,
				success : function(response) {
					$('#specialNeed').html(response);					
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function updateSpecialNeedRequirement(si_id, ssnr_id){
		
		var snc_id = $('#snc_id').val();
		if(spNeedReq == ""){
			spNeedReq = 1;
		}
		
		if(spNeedReq == 1 && snc_id == ""){
			document.getElementById("errMsg").innerHTML = "Special need category is required.";
		} else {
			$("#specialNeed").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "studentaction_specialneedrequiredupdate.action",				
				data : "ssnr_id=" + ssnr_id + "&snc_id=" + snc_id + "&stud_special_need_status=" + spNeedReq,
				success : function(response) {
					$('#specialNeed').html(response);					
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	/*----- STUDENT PERSONAL INFO CREATE PAGE -----*/
	
	function clearSaveStudentInfoCss(){
		document.getElementById("fname").style.borderColor = "";
		document.getElementById("mname").style.borderColor = "";
		document.getElementById("gname").style.borderColor = "";
		document.getElementById("mothername").style.borderColor = "";
		document.getElementById("sex").style.borderColor = "";
		document.getElementById("dob").style.borderColor = "";
		document.getElementById("dob").style.borderColor = "";
		document.getElementById("pob").style.borderColor = "";
		document.getElementById("nat").style.borderColor = "";
	}
	
	function saveStudentInfo(){
		
		clearSaveStudentInfoCss();
		
		document.getElementById("saveButton").style.display = "none";
		
		var counter = 0;
		
		///*** Students Personal Information ***///	
		var fname = $('#fname').val();
		var mname = $('#mname').val();
		var gname = $('#gname').val();
		var mother_name = $('#mothername').val();
		var sex = $('#sex').val();
		var dob = $('#dob').val();
		var pob = $('#pob').val();
		var nat = $('#nat').val();
		var idno = $('#idno').val();
		
		var pattern = /^[A-Za-z ]{3,300}$/;
		var fname_pattern = pattern.test(fname);
		var mname_pattern = pattern.test(mname);
		var gname_pattern = pattern.test(gname);
		var mothername_patter = pattern.test(mother_name);	
		
		var valDob = validateDOB(dob);
		
		if(fname == "" || fname_pattern == false){
			
			document.getElementById("fname").style.borderColor = "#ff0000";
			$('#fname').attr('placeholder', 'First name is required or invalid first name.');
			counter++;
		}
		if(mname == "" || mname_pattern == false){
			
			document.getElementById("mname").style.borderColor = "#ff0000";
			$('#mname').attr('placeholder', 'Middle name is required or invalid middle name.');
			counter++;
		}
		if(gname == "" || gname_pattern == false){
			
			document.getElementById("gname").style.borderColor = "#ff0000";
			$('#gname').attr('placeholder', 'Last name is required or invalid last name');
			counter++;
		}		 
		if(mother_name == "" || mothername_patter == false){
			
			document.getElementById("mothername").style.borderColor = "#ff0000";
			$('#mothername').attr('placeholder', 'Mother name is invalid.');
			counter++;
		}
		if(sex == "0"){
			
			document.getElementById("sex").style.borderColor = "#ff0000";
			$('#sex').attr('placeholder', 'Sex is required.');
			counter++;
		} 
		if(dob == ""){
			
			document.getElementById("dob").style.borderColor = "#ff0000";
			$('#dob').attr('placeholder', 'Date of birth is required.');
			counter++;
		}
		if(valDob == "invalideDate"){
			
			document.getElementById("dob").style.borderColor = "#ff0000";
			$('#dob').attr('placeholder', 'Invalid Date of Birth.');
			counter++;
		} 
//		if(valDob == "ageProblem"){
//			document.getElementById("errMsg").innerHTML = "The minimum age is 3 years.";
//		} 
		if(pob == ""){
			
			document.getElementById("pob").style.borderColor = "#ff0000";
			$('#pob').attr('placeholder', 'Place of birth is required.');
			counter++;
		}
		if(nat == ""){
			
			document.getElementById("nat").style.borderColor = "#ff0000";
			$('#nat').attr('placeholder', 'Nationality is required.');
			counter++;
		}
		
		
		///*** Parent/Guardian/Emergency Contact(1 & 2) ***///
		var contact_name = $('#contact_name').val();
		var relationship = $('#relationship').val();
		var mob_no = $('#mob_no').val();
		var offphone_no = $('#offphone_no').val();
		var homephone_no = $('#homephone_no').val();
		
		var contact_name_2 = $('#contact_name_2').val();
		var relationship_2 = $('#relationship_2').val();
		var mob_no_2 = $('#mob_no_2').val();
		var offphone_no_2 = $('#offphone_no_2').val();
		var homephone_no_2 = $('#homephone_no_2').val();
		
		var pattern_cn = /^[A-Za-z/ ]{3,20}$/;
		var contact_name_pattern = pattern_cn.test(contact_name);
		var contact_name_pattern_2 = pattern_cn.test(contact_name_2);
		
		///>>> Parent 1
		if(contact_name != "" && contact_name_pattern == false){
			
			document.getElementById("contact_name").style.borderColor = "#ff0000";
			$('#contact_name').attr('placeholder', 'Invalide Contact Name');
			counter++;
		}
		if(contact_name != "" && relationship == "0"){
			
			document.getElementById("relationship").style.borderColor = "#ff0000";
			counter++;
		} 
		if(contact_name != "" && (mob_no == "" && offphone_no == "" && homephone_no == "")){
			
			$('#mob_no').attr('placeholder', 'At leaste one phone number is required.');
			document.getElementById("mob_no").style.borderColor = "#ff0000";
			counter++;
		}
		if(contact_name != "" && ((mob_no != "" && mob_no.length < 10) || (offphone_no != "" && offphone_no.length < 10) || (homephone_no != "" && homephone_no.length < 10))){
			
			$('#mob_no').attr('placeholder', 'Invalid phone number');
			document.getElementById("mob_no").style.borderColor = "#ff0000";
			counter++;
		}
		
		///>>> Parent 2
		if(contact_name_2 != "" && contact_name_pattern_2 == false){
			
			document.getElementById("contact_name_2").style.borderColor = "#ff0000";
			$('#contact_name_2').attr('placeholder', 'Invalide Contact Name');
			counter++;
		}
		if(contact_name_2 != "" && relationship_2 == "0"){
			
			document.getElementById("relationship_2").style.borderColor = "#ff0000";
			counter++;
		} 
		if(contact_name_2 != "" && (mob_no_2 == "" && offphone_no_2 == "" && homephone_no_2 == "")){
			
			$('#mob_no_2').attr('placeholder', 'At leaste one phone number is required.');
			document.getElementById("mob_no_2").style.borderColor = "#ff0000";
			counter++;
		}
		if(contact_name_2 != "" && ((mob_no_2 != "" && mob_no_2.length < 10) || (offphone_no_2 != "" && offphone_no_2.length < 10) || (homephone_no_2 != "" && homephone_no_2.length < 10))){
			
			$('#mob_no_2').attr('placeholder', 'Invalid phone number');
			document.getElementById("mob_no_2").style.borderColor = "#ff0000";
			counter++;
		}
		
		
		
		var contact_info_1 = new Array();
		if(contact_name != ""){
			
			contact_info_1 = new Array(contact_name + ',' + relationship + ',' + mob_no + ',' + offphone_no + ',' + homephone_no + ',');
		}
		var contact_info_2 = new Array();
		if(contact_name_2 != ""){
			
			contact_info_2 = new Array(contact_name_2 + ',' + relationship_2 + ',' + mob_no_2 + ',' + offphone_no_2 + ',' + homephone_no_2 + ',');
		}
		
		var contact_info = new Array(contact_info_1 + '-' + contact_info_2);
		
		
		
		///*** Student's number of brother and sister ***///
		var broSisNumber = $('#broSisNumber').val();
		
		///*** Special attention information ***///
		//var specialAttentionMessage = $('#specialAttentionMessage').val();
		
		
		
		if(counter == 0){
			
			$("#info_saveform").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "studentaction_save.action",				
				data : "fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&mother_name=" + mother_name + "&sex=" + sex + "&dob=" + dob + "&pob=" + pob + "&id_no=" + idno + "&nationality=" + nat + "&contact_information=" + contact_info,
				success : function(response) {
					$('#studSaveForm').html(response);
					display_active_menu();
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		} else {
			
			document.getElementById("saveButton").style.display = "block";
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
		} else if(age < 2) {
			return "ageProblem";
		} 
	}
	
	function display_active_menu(){
		
		$.ajax({
			type : "POST",
			url : "studentaction_activemenu.action",				
			data : "",
			success : function(response) {
				$('#menu_active').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addStudPersonalInfoFromExcel(){
		
		$("#info_saveform").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_addstudpersonalinfofromexcel.action",				
			data : "",
			success : function(response) {
				$('#info_saveform').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- PERSONAL INFO EDIT FORM -----*/
	
function updateStudentInfo(siid, stud_edit_status){
		
		var fname = $('#fname').val();
		var mname = $('#mname').val();
		var gname = $('#gname').val();
		var mother_name = $('#mother_name').val();
		var sex = $('#sex').val();
		var dob = $('#dob').val();
		var pob = $('#pob').val();
		var nat = $('#nat').val();
		var status = $('#status').val();
		var idno = $('#idno').val();

		var pattern = /^[A-Za-z/ ]{3,300}$/;
		var fname_pattern = pattern.test(fname);
		var mname_pattern = pattern.test(mname);
		var gname_pattern = pattern.test(gname);
		var mothername_patter = pattern.test(mother_name);
		
		if(fname.trim() == ""){
			document.getElementById("errMsg").innerHTML = "Full Name is required.";
			document.getElementById("fname").style.borderColor = "#ff0000";
		} else if(mname.trim() == ""){
			document.getElementById("errMsg").innerHTML = "Full Name is required.";
			document.getElementById("mname").style.borderColor = "#ff0000";
		} else if(gname.trim() == ""){
			document.getElementById("errMsg").innerHTML = "Full Name is required.";
			document.getElementById("gname").style.borderColor = "#ff0000";
		} else if(fname_pattern == false || mname_pattern == false || gname_pattern == false){
			document.getElementById("errMsg").innerHTML = "Name is invalide.";
		} else if(mothername_patter == false){
			document.getElementById("errMsg").innerHTML = "Mother name is invalide.";
		} else if(mother_name == ""){
			document.getElementById("errMsg").innerHTML = "Mother name is required.";
		} else if(sex == "0"){
			document.getElementById("errMsg").innerHTML = "Sex is required.";
		} else if(dob == ""){
			document.getElementById("errMsg").innerHTML = "Date of birth is required.";
		} else if(validateDOB(dob) == "invalideDate"){
			document.getElementById("errMsg").innerHTML = "Invalide Date of Birth";
		} else if(validateDOB(dob) == "ageProblem"){
			document.getElementById("errMsg").innerHTML = "The age is below the required level(3).";
		} else if(pob == ""){
			document.getElementById("errMsg").innerHTML = "Place of birth is required.";
		} else if(nat == ""){
			document.getElementById("errMsg").innerHTML = "Nationality is required.";
		} else {
			$("#info_updateform").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "studentaction_update.action",				
				data : "si_id=" + siid + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&mother_name=" + mother_name + "&sex=" + sex + "&dob=" + dob + "&pob=" + pob + "&si_status=" + status + "&id_no=" + idno + "&nationality=" + nat + "&stud_edit_status=" + stud_edit_status,
				success : function(response) {
					$('#info_updateform').html(response);					
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
		} else if(age < 2) {
			return "ageProblem";
		} 
	}
	
	/*----- IMPORT STUDENT PERSONAL INFO FROM EXCEL -----*/
	
	function importStudPersonalInfoFromExcel(){
		
		var file_path = $('#file_path').val();
		var sheetNum = $('#sheetNum').val();
		var columnNum = $('#columnNum').val();
		var rowNum = $('#rowNum').val();
		
		if(file_path == ""){
			
			document.getElementById("errMsg").innerHTML = "File path is required.";
		} else if(sheetNum == ""){
			
			document.getElementById("errMsg").innerHTML = "Sheet Number is required.";
		} else if(rowNum == ""){
			
			document.getElementById("errMsg").innerHTML = "Row Number is required.";
		} else if(columnNum == ""){
			
			document.getElementById("errMsg").innerHTML = "Column Number is required.";			
		} else {
			
			$("#info_saveform").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "studentaction_importStudPersonalInfoFromExcel.action",				
				data : "examResultBean.file_path=" + file_path + "&examResultBean.column_num=" + columnNum + "&examResultBean.row_num=" + rowNum + "&examResultBean.sheet_num=" + sheetNum,
				success : function(response) {
					$('#info_saveform').html(response);				
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});			
		}		
	}
		
	/*======================= INACTIVE STUDENT LIST =======================*/
	
	/*----- STUDENT LIST (INACTIVE) -----*/
	
	function student_edit_inactivestudent(id_no, si_id, fname, mname, gname, sex, dob, pob, nationality, sistatus, mothername, photo_path, photo_name){
		
		$("#can_student_list").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_candidateedit.action",				
			data : "id_no=" + id_no + "&si_id=" + si_id + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&sex=" + sex + "&dob=" + dob + "&pob=" + pob + "&nationality=" + nationality + "&si_status=" + sistatus + "&mother_name=" + mothername + "&photo_path=" + photo_path + "&photo_name=" + photo_name + "&stud_edit_status=candidate",
			success : function(response) {
				$('#can_student_list').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}

	function activateStudentsStatus(si_id){
		
		var ac_year = $('#academicYear').val();
			
		$("#student_list").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_activateStudentsStatus.action",				
			data : "ac_year=" + ac_year + "&si_id=" + si_id,
			success : function(response) {
				$('#student_list').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*======================= STUDENT GRADE HISTORY =======================*/
	
	/*----- STUDENT GRADE HISTORY LIST -----*/
	
	var classid = null;
	function grdAttended(clid, siid){
		
		$("#subject_list").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_grdhistory_sbjlist.action",				
			data : "clid=" + clid + "&si_id=" + siid,
			success : function(response) {
				if(classid == null){
					document.getElementById(clid).style.backgroundColor = '#3d6e9f';
					document.getElementById(clid).style.color = '#ffffff';
				} else {
					document.getElementById(classid).style.backgroundColor = 'silver';
					document.getElementById(clid).style.backgroundColor = '#3d6e9f';
					document.getElementById(clid).style.color = '#ffffff';
				}
				classid = clid;
				$('#subject_list').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*======================= STUDENT FAMILY INFO =======================*/
	
	
	
	/*======================= STUDENT EMERGENCY CONTACT =======================*/
	
	/*----- STUDENT EMERGENCY CONTACT LIST PAGE -----*/
	
	function emergencyContactEdit(si_id, secid, contname, rel, mobno, officeno, homeno, status){
		
		$("#emergcont_list").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_emergcontact_edit.action",				
			data : "si_id=" + si_id + "&sec_id=" + secid + "&contact_name=" + contname + "&relationship=" + rel + "&mob_no=" + mobno + "&office_phone_no=" + officeno + "&home_phone_no=" + homeno + "&sec_status=" + status,
			success : function(response) {
				$('#emergcont_list').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addNewEergencyContact(siid){
		
		$("#emergcont_list").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_emergcontact_create.action",				
			data : "si_id=" + siid,
			success : function(response) {
				$('#emergcont_list').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- STUDENT EMERGENCY CONTACT EDIT -----*/
	
	function updateEmrgContact(secid, si_id){
		
		var contact_name = $('#contact_name').val();
		var relationship = $('#relationship').val();
		var mob_no = $('#mob_no').val();
		var offphone_no = $('#offphone_no').val();
		var homephone_no = $('#homephone_no').val();
		var status = $('#status').val();		

		var pattern = /^[A-Za-z/ ]{3,20}$/;
		var contact_name_pattern = pattern.test(contact_name);
		var pattern_rel = /^[A-Za-z]{3,20}$/;
		var relation_pattern = pattern_rel.test(relationship);
		
		if(contact_name == ""){
			document.getElementById("errMsg").innerHTML = "Contact Name is required.";
		} else if(contact_name_pattern == false){
			document.getElementById("errMsg").innerHTML = "Invalide Contact Name";
		} else if(relationship == ""){
			document.getElementById("errMsg").innerHTML = "Relationship is required.";
		} else if(relation_pattern == false){
			document.getElementById("errMsg").innerHTML = "Invalide relationship content.";
		} else if(mob_no == "" && offphone_no == "" && homephone_no == ""){
			document.getElementById("errMsg").innerHTML = "At leaste one phone number is required.";
		} else {
			
			$("#cadd_updateform").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "studentaction_emergcont_update.action",				
				data : "si_id=" + si_id + "&contact_name=" + contact_name + "&relationship=" + relationship + "&mob_no=" + mob_no + "&office_phone_no=" + offphone_no + "&home_phone_no=" + homephone_no + "&sec_status=" + status + "&sec_id=" + secid,
				success : function(response) {
					$('#cadd_updateform').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
			
		}
	}

	/*----- STUDENT EMERGENCY CONTACT CREATE -----*/

	function saveEmergContact(siid){
		
		var contact_name = $('#contact_name').val();
		var relationship = $('#relationship').val();
		var mob_no = $('#mob_no').val();
		var offphone_no = $('#offphone_no').val();
		var homephone_no = $('#homephone_no').val();
		
		var pattern = /^[A-Za-z/ ]{3,20}$/;
		var contact_name_pattern = pattern.test(contact_name);
		var pattern_rel = /^[A-Za-z]{3,20}$/;
		var relation_pattern = pattern_rel.test(relationship);
		
		if(contact_name == ""){
			document.getElementById("errMsg").innerHTML = "Contact Name is required.";
		} else if(contact_name_pattern == false){
			document.getElementById("errMsg").innerHTML = "Invalide Contact Name";
		} else if(relationship == ""){
			document.getElementById("errMsg").innerHTML = "Relationship is required.";
		} else if(relation_pattern == false){
			document.getElementById("errMsg").innerHTML = "Invalide relationship content.";
		} else if(mob_no == "" && offphone_no == "" && homephone_no == ""){
			document.getElementById("errMsg").innerHTML = "At leaste one phone number is required.";
		} else {
		
			$("#cadd_saveform").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "studentaction_emergcontact_save.action",				
				data : "contact_name=" + contact_name + "&relationship=" + relationship + "&mob_no=" + mob_no + "&office_phone_no=" + offphone_no + "&home_phone_no=" + homephone_no + "&si_id=" + siid,
				success : function(response) {
					$('#cadd_saveform').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
			
		}
	}
	
	/*======================= DROPOUT STUDENT LIST =======================*/
	
	/*----- STUDENT LIST (DROPOUT) -----*/
	
	function student_edit_to_dropout(id_no, si_id, fname, mname, gname, sex, dob, pob, nationality, sistatus, mothername, photo_path, photo_name){
		
		$("#can_student_list").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_candidateedit.action",				
			data : "id_no=" + id_no + "&si_id=" + si_id + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&sex=" + sex + "&dob=" + dob + "&pob=" + pob + "&nationality=" + nationality + "&si_status=" + sistatus + "&mother_name=" + mothername + "&photo_path=" + photo_path + "&photo_name=" + photo_name + "&stud_edit_status=candidate",
			success : function(response) {
				$('#can_student_list').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}	
	
	/*======================= STUDENT DISCIPLINARY ACTION =======================*/
	
	/*----- STUDENT DISCIPLINARY ACTION LIST PAGE -----*/
	
	function disaction_edit(sdaid, sdatype, sdareason, sdadate, sdastatus, siid){
		$("#disact_container").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_disaction_edit.action",				
			data : "sda_id=" + sdaid + "&sda_type=" + sdatype + "&sda_reason=" + sdareason + "&sda_date=" + sdadate + "&sda_status=" + sdastatus + "&si_id=" + siid,
			success : function(response) {
				$('#disact_container').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addNewDisAct(siid){
		$("#disact_container").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_disaction_create.action",				
			data : "si_id=" + siid,
			success : function(response) {
				$('#disact_container').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- STUDENT DISCIPLINARY ACTION EDIT -----*/
	
function updateDisAct(siid, da_id){
		
		var da_type = $('#da_type').val();
		var da_reason = $('#da_reason').val();
		var da_date = $('#da_date').val();
		var da_status = $('#da_status').val();
		
		if(da_type == ""){
			document.getElementById("errMsg").innerHTML = "Disciplinary action type is required.";
		} else if(da_reason == ""){
			document.getElementById("errMsg").innerHTML = "Disciplinary action reason is required.";
		} else if(da_date == ""){
			document.getElementById("errMsg").innerHTML = "Disciplinary action date is required.";
		} else {			
		
			$("#disact_editform").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "studentaction_disact_update.action",				
				data : "sda_id=" + da_id + "&sda_type=" + da_type + "&sda_reason=" + da_reason + "&sda_date=" + da_date + "&sda_status=" + da_status + "&si_id=" + siid,
				success : function(response) {
					$('#disact_editform').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
			
		}
	}

	/*----- STUDENT DISCIPLINARY ACTION CREATE PAGE -----*/

	function saveDisAct(siid){
		var da_type = $('#da_type').val();
		var da_reason = $('#da_reason').val();
		var da_date = $('#da_date').val();
		
		if(da_type == ""){
			document.getElementById("errMsg").innerHTML = "Disciplinary action type is required.";
		} else if(da_reason == ""){
			document.getElementById("errMsg").innerHTML = "Disciplinary action reason is required.";
		} else if(da_date == ""){
			document.getElementById("errMsg").innerHTML = "Disciplinary action date is required.";
		} else {
		
			$("#disact_saveform").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "studentaction_disact_save.action",				
				data : "sda_type=" + da_type + "&sda_reason=" + da_reason + "&sda_date=" + da_date + "&si_id=" + siid,
				success : function(response) {
					$('#disact_saveform').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}	
	
	/*======================= STUDENT CURRENT ADDRESS =======================*/
	
	/*----- STUDENT CURRENT ADDRESS EDIT PAGE -----*/

	function updateCurAddress(siid, sca_id){
		
		var sub_city = $('#sub_city').val();
		var kebele = $('#kebele').val();
		var house_no = $('#house_no').val();
		var house_phone = $('#house_phone').val();
		var email = $('#email').val();
		var email_2 = $('#email_2').val();
		
		var emailValidator = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		
		var pattern_phone = /^[0-9 ]{9,10}$/;
		var houseno_pattern = pattern_phone.test(house_phone);
		
		if(sub_city == ""){
			document.getElementById("errMsg").innerHTML = "Sub-city is required.";
		} else if(kebele == ""){
			document.getElementById("errMsg").innerHTML = "Kebele is required.";
		} else if(house_no == ""){
			document.getElementById("errMsg").innerHTML = "House number is required.";
		} else if(house_phone != "" && houseno_pattern == false){
			document.getElementById("errMsg").innerHTML = "Invalide phone number.";
		} else if(!emailValidator.test(email) || !emailValidator.test(email_2)){
			document.getElementById("errMsg").innerHTML = "Please insert a valide email address.";
		} else {
		
			$("#cadd_updateform").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "studentaction_currentaddress_update.action",				
				data : "sub_city=" + sub_city + "&kebele=" + kebele + "&house_no=" + house_no + "&home_phone=" + house_phone + "&si_id=" + siid + "&sca_id=" + sca_id + "&email=" + email + "&email_2=" + email_2,
				success : function(response) {
					$('#cadd_updateform').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	/*----- STUDENT CURRENT ADDRESS CREATE PAGE -----*/
	
	function saveCurAddress(siid){
		
		var sub_city = $('#sub_city').val();
		var kebele = $('#kebele').val();
		var house_no = $('#house_no').val();
		var house_phone = $('#house_phone').val();
		var email = $('#email').val();
		var email_2 = $('#email_2').val();
		
		var emailValidator = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		
		var pattern_phone = /^[0-9 ]{9,10}$/;
		var housephno_pattern = pattern_phone.test(house_phone);
		
		if(sub_city == ""){
			document.getElementById("errMsg").innerHTML = "Sub-city is required.";
		} else if(kebele == ""){
			document.getElementById("errMsg").innerHTML = "Kebele is required.";
		} else if(house_no == ""){
			document.getElementById("errMsg").innerHTML = "House number is required.";
		} else if(house_phone != "" && housephno_pattern == false){
			document.getElementById("errMsg").innerHTML = "Invalide phone number.";
		} else if((email != "" && !emailValidator.test(email)) || (email_2 != "" && !emailValidator.test(email_2))){
			document.getElementById("errMsg").innerHTML = "Please insert a valide email address.";
		} else {
		
			$("#cadd_saveform").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "studentaction_curaddress_save.action",				
				data : "sub_city=" + sub_city + "&kebele=" + kebele + "&house_no=" + house_no + "&home_phone=" + house_phone + "&si_id=" + siid + "&email=" + email + "&email_2=" + email_2,
				success : function(response) {
					$('#cadd_saveform').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	
	/*======================= STUDENT CANDIDATE LIST =======================*/
	
	/*----- STUDENT LIST (CANDIDATE) -----*/
	
	function student_edit_candidate(id_no, si_id, fname, mname, gname, sex, dob, pob, nationality, sistatus, mothername, photo_path, photo_name){
		
		$("#can_student_list").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_candidateedit.action",				
			data : "id_no=" + id_no + "&si_id=" + si_id + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&sex=" + sex + "&dob=" + dob + "&pob=" + pob + "&nationality=" + nationality + "&si_status=" + sistatus + "&mother_name=" + mothername + "&photo_path=" + photo_path + "&photo_name=" + photo_name + "&stud_edit_status=candidate",
			success : function(response) {
				$('#can_student_list').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	