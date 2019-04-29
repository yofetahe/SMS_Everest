/**
 * 
 */
/*===================== ADMIN =====================*/

/*===== Annual TERM =====*/

	/*----- annual term create -----*/
	
	function saveAnnualTerm(){
		
		var annTermName = $('#annTermName').val();
		
		if(annTermName == ""){
			document.getElementById("errMsg").innerHTML = "Annual Term Name is required.";
		} else {
		
			$("#annualTermInfo").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "annualtermaction_save.action",				
				data : "at_name=" + annTermName,
				success : function(response) {				
					$('#annualTermInfo').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	/*----- annual term edit -----*/
	
	function updateAnnualTerm(at_id){
		
		var annTermName = $('#annTermName').val();
		var atStatus = $('#atStatus').val();
		
		if(annTermName == ""){
			document.getElementById("errMsg").innerHTML = "Annual Term Name is required.";
		} else {
		
			$("#annualTermInfo").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "annualtermaction_update.action",				
				data : "at_id=" + at_id + "&at_name=" + annTermName + "&at_status=" + atStatus,
				success : function(response) {				
					$('#annualTermInfo').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	/*----- annual term list -----*/
	
	function editAnnualTerm(indx){
		
		$("#annualTermInfo").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "annualtermaction_editform.action",				
			data : "indx=" + indx,
			success : function(response) {				
				$('#annualTermInfo').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addNewAnnualTerm(){
		
		$("#annualTermInfo").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "annualtermaction_createform.action",				
			data : "",
			success : function(response) {				
				$('#annualTermInfo').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*===== EXAM SCHEDULE =====*/
	
	/*----- exam schedule create page -----*/
	
	function saveExamSchedule(){
		
		var cl_id = $('#cl_id').val();
		var sub_id = $('#sub_id').val();
		var et_id = $('#et_id').val();
		var es_greg_date = $('#es_greg_date').val();
		var at_id = $('#at_id').val();
		
		if(cl_id == 0){
			document.getElementById("errMsg").innerHTML = "Class is required.";
		} else if(sub_id == 0){
			document.getElementById("errMsg").innerHTML = "Subject is required.";
		} else if(et_id == 0){
			document.getElementById("errMsg").innerHTML = "Exam type is required.";
		} else if(es_greg_date == ""){
			document.getElementById("errMsg").innerHTML = "Exam date is required.";
		} else if(at_id == 0){
			document.getElementById("errMsg").innerHTML = "Term is required.";
		} else {
		
			$("#examScheInfo").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "examscheduleaction_save.action",
				data : "cl_id=" + cl_id + "&sub_id=" + sub_id + "&et_id=" + et_id + "&es_greg_date=" + es_greg_date + "&at_id=" + at_id,
				success : function(response) {				
					$('#examScheInfo').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	function subjectListUnderClass(cl_id){
		$("#subList").html("<img align=\"center\" src=\"images/30.GIF\"/>");
		$.ajax({
			type : "POST",
			url : "subjectListUnderClass.action",				
			data : "cl_id=" + cl_id,
			success : function(response) {				
				$('#subList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function examListPerSubject(sub_id){
		var cl_id = $('#cl_id').val();
		
		$("#exTypeList").html("<img align=\"center\" src=\"images/30.GIF\"/>");
		$.ajax({
			type : "POST",
			url : "examTypePerSubjectUnderClass.action",				
			data : "cl_id=" + cl_id + "&sub_id=" + sub_id,
			success : function(response) {				
				$('#exTypeList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function selectExamType(indx, et_name, et_id){		
		
		var at_id = $('#at_id').val();
		var cl_id = $('#cl_id').val();
		var sub_id = $('#sub_id').val();
		var exam_date = $('#exdate_' + indx).val();
		
		var from_hour = $('#from_hour_'+indx).val();
		var from_min = $('#from_min_'+indx).val();
		
		var to_hour = $('#to_hour_'+indx).val();
		var to_min = $('#to_min_'+indx).val();
		
		if(from_hour == "" || to_hour == "" || from_min == "" || to_min == ""){
			document.getElementById("errMsg").innerHTML = "Exam starting and end hour required.";
		} else if((from_hour != "" && from_hour > 12) || (from_min != "" && from_min > 60) || (to_hour 	!= "" && to_hour > 12) || (to_min != "" && to_min > 60)){
			document.getElementById("errMsg").innerHTML = "Invalid hour format";
		} else if(exam_date == ""){
			document.getElementById("errMsg").innerHTML = "Please select exam date for " + et_name;
		} else {
			
			var time_from = from_hour+":"+from_min;
			var time_to = to_hour+":"+to_min;
			
			$.ajax({
				type : "POST",
				url : "examscheduleaction_addselectedettype.action",				
				data : "cl_id=" + cl_id + "&sub_id=" + sub_id + "&et_id=" + et_id + "&es_greg_date=" + exam_date + "&at_id=" + at_id + "&time_from=" + time_from + "&time_to=" + time_to,
				success : function(response) {				
					//$('#exTypeList').html(response);
					$('#extype_'+indx).html("<div style=\"cursor: pointer;\" onclick=\"disselectExamType(" + indx + ", " + et_name + ", " + et_id + ")\">" + 
					"<table cellpadding=\"0\" cellspacing=\"0\" ><tr><td style=\"vertical-align: middle;\">" +
					"<img alt=\"checked\" src=\"images/checked.png\" width=\"17px;\"> </td><td>" + et_name + "</td></tr></table> </div>");
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function disselectExamType(){
		alert("dis");
	}
	
	/*----- exam schedule edit page -----*/
	
	function updateExamSchedule(time_from, time_to){
		alert(time_from + " " + time_to);
		var es_id = $('#es_id').val();
		var cl_id = $('#cl_id').val();
		var sub_id = $('#sub_id').val();
		var et_id = $('#et_id').val();
		var es_greg_date = $('#es_greg_date').val();
		var es_status = $('#es_status').val();
		
		var from_hour = $('#from_hour').val();
		var from_min = $('#from_min').val();
		
		var to_hour = $('#to_hour').val();
		var to_min = $('#to_min').val();
		
		var start_hour = "";
		var end_hour = "";
		
		if(from_hour == "" && from_min == ""){
			start_hour = time_from;
		} else if(from_hour != "" && from_min != ""){
			start_hour = from_hour + ":" + from_min;
		}
		if(to_hour == "" && to_min == ""){
			end_hour = time_to;
		} else if(to_hour != "" && to_min != ""){
			end_hour = to_hour + ":" + to_min;
		}
		
		if(cl_id == 0){
			document.getElementById("errMsg").innerHTML = "Class is required.";
		} else if(sub_id == 0){
			document.getElementById("errMsg").innerHTML = "Subject is required.";
		} else if(et_id == 0){
			document.getElementById("errMsg").innerHTML = "Exam type is required.";
		} else if(es_greg_date == ""){
			document.getElementById("errMsg").innerHTML = "Exam date is required.";
		} else if((from_hour == "" && from_min != "") || (from_hour != "" && from_min == "") || (to_hour == "" && to_min != "") || (to_hour != "" && to_min == "")){
			document.getElementById("errMsg").innerHTML = "Invalid Time format";
		} else if((from_hour != "" && from_hour > 12) || (from_min != "" && from_min > 60) || (to_hour 	!= "" && to_hour > 12) || (to_min != "" && to_min > 60)){
			document.getElementById("errMsg").innerHTML = "Invalid Time format";
		} else {
		
			$("#examScheInfo").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "examscheduleaction_update.action",				
				data : "es_id=" + es_id + "&cl_id=" + cl_id + "&sub_id=" + sub_id + "&et_id=" + et_id + "&es_greg_date=" + es_greg_date + "&es_status=" + es_status + "&time_from=" + start_hour + "&time_to=" + end_hour,
				success : function(response) {				
					$('#examScheInfo').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
//	function subjectListUnderClass(cl_id){
//		$("#subList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
//		$.ajax({
//			type : "POST",
//			url : "subjectListUnderClass.action",				
//			data : "cl_id=" + cl_id,
//			success : function(response) {				
//				$('#subList').html(response);
//			},
//			error : function(e) {
//				alert('Error: ' + e);
//			}
//		});
//	}
	
//	function examListPerSubject(sub_id){
//		var cl_id = $('#cl_id').val();
//		
//		$("#exTypeList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
//		$.ajax({
//			type : "POST",
//			url : "examTypePerSubjectUnderClass.action",				
//			data : "cl_id=" + cl_id + "&sub_id=" + sub_id,
//			success : function(response) {				
//				$('#exTypeList').html(response);
//			},
//			error : function(e) {
//				alert('Error: ' + e);
//			}
//		});
//	}
	
	/*----- exam schedule list -----*/
	
	function addNewExamSchedule(){
		
		$("#examScheInfo").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "exscheduleaction_createform.action",				
			data : "",
			success : function(response) {				
				$('#examScheInfo').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}

	function editExamSchedule(indx,cl_id){
		
		$("#examScheInfo").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "exscheduleaction_editform.action",				
			data : "indx=" + indx + "&cl_id=" + cl_id,
			success : function(response) {				
				$('#examScheInfo').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function filterExamSchedule(cl_id){
		
		var ac_year = document.getElementById("ac_year").value;
		var at_id = document.getElementById("at_id").value;
		
		$("#examsch_filter").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "exscheduleaction_filterlist.action",				
			data : "academic_year=" + ac_year + "&cl_id=" + cl_id + "&at_id=" + at_id,
			success : function(response) {				
				$('#examsch_filter').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*===== HOLIDAY =====*/
	
	/*----- holiday create page -----*/
	
	function saveHoliday(){
		
		var h_name = $('#h_name').val();
		var work_status = $('#work_status').val();
		var h_greg_date = $('#h_greg_date').val();
		
		if(h_name == ""){
			document.getElementById("errMsg").innerHTML = "Holiday name is required.";
		} else if(h_greg_date == ""){
			document.getElementById("errMsg").innerHTML = "Holiday date is required.";
		} else {
		
			$("#holidayInfo").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "holidayaction_save.action",				
				data : "h_name=" + h_name + "&work_status=" + work_status + "&h_greg_date=" + h_greg_date,
				success : function(response) {				
					$('#holidayInfo').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	/*----- holiday edit page -----*/
	
	function updateHoliday(){
		
		var h_id = $('#h_id').val();
		var h_name = $('#h_name').val();
		var work_status = $('#work_status').val();
		var h_greg_date = $('#h_greg_date').val();
		var h_status = $('#h_status').val();
		
		if(h_name == ""){
			document.getElementById("errMsg").innerHTML = "Holiday name is required.";
		} else if(h_greg_date == ""){
			document.getElementById("errMsg").innerHTML = "Holiday date is required.";
		} else {
		
			$("#holidayInfo").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "holidayaction_update.action",				
				data : "h_id=" + h_id + "&h_name=" + h_name + "&work_status=" + work_status + "&h_greg_date=" + h_greg_date + "&h_status=" + h_status,
				success : function(response) {				
					$('#holidayInfo').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	/*----- holiday list page -----*/
	
	function addNewHoliday(){
		$("#holidayInfo").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "holidayaction_createform.action",				
			data : "",
			success : function(response) {				
				$('#holidayInfo').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}

	function editHoliday(indx){
		
		$("#holidayInfo").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "holidayaction_editform.action",				
			data : "indx=" + indx,
			success : function(response) {				
				$('#holidayInfo').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*===== SCHOOL EVENT =====*/
	
	/*----- school event create -----*/
	
	function saveSchoolEvent(){
		
		var se_name = $('#se_name').val();
		var se_description = $('#se_desc').val();
		var se_greg_date = $('#se_greg_date').val();
		
		if(se_name == ""){
			document.getElementById("errMsg").innerHTML = "School event is required.";
		} else if(se_description == ""){
			document.getElementById("errMsg").innerHTML = "School event description is required.";
		} else if(se_greg_date == ""){
			document.getElementById("errMsg").innerHTML = "School event date is required.";
		} else {
		
			$("#schoolEventInfo").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "schooleventaction_save.action",				
				data : "se_name=" + se_name + "&se_description=" + se_description + "&se_greg_date=" + se_greg_date,
				success : function(response) {				
					$('#schoolEventInfo').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	/*----- school event edit -----*/
	
	function updateSchoolEvent(){
		
		var se_id = $('#se_id').val();
		var se_name = $('#se_name').val();
		var se_description = $('#se_desc').val();
		var se_greg_date = $('#se_greg_date').val();
		var se_status = $('#se_status').val();
		
		if(se_name == ""){
			document.getElementById("errMsg").innerHTML = "School event is required.";
		} else if(se_description == ""){
			document.getElementById("errMsg").innerHTML = "School event description is required.";
		} else if(se_greg_date == ""){
			document.getElementById("errMsg").innerHTML = "School event date is required.";
		} else {
		
			$("#schoolEventInfo").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "schooleventaction_update.action",				
				data : "se_id=" + se_id + "&se_name=" + se_name + "&se_description=" + se_description + "&se_greg_date=" + se_greg_date + "&se_status=" + se_status,
				success : function(response) {				
					$('#schoolEventInfo').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	/*----- school event list -----*/
	
	function addNewSchoolEvent(){
		
		$("#schoolEventInfo").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "schooleventaction_createform.action",				
			data : "",
			success : function(response) {				
				$('#schoolEventInfo').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}

	function editSchoolEvent(indx){
		
		$("#schoolEventInfo").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "schooleventaction_editform.action",				
			data : "indx=" + indx,
			success : function(response) {				
				$('#schoolEventInfo').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*===== USER =====*/
	
	/*----- user create -----*/
	
	function saveAcUser(){
		
		var nti_id = $('#nti_id').val();
		var username = $('#username').val();
		var password = $('#password').val();
		var userType = $('#utype').val();
		
		var pattern = /^[A-Za-z ]{3,20}$/;
		var name_pattern = pattern.test(name);
		
		var pw_pattern_no = /^[0-9]{1,20}$/;
		var pw_pattern = /^[A-Za-z0-9@#$%^&*]{3,20}$/;
		
		var password_pattern_sp = pw_pattern.test(password);
		var password_pattern_no = pw_pattern_no.test(password);
		
		if(nti_id == "0"){
			document.getElementById("errMsg").innerHTML = "Name is required.";
		} else if(username == ""){
			document.getElementById("errMsg").innerHTML = "Username is required.";
		} else if(password == ""){
			document.getElementById("errMsg").innerHTML = "Password is required.";
		} else if(password_pattern_sp == false || password_pattern_no == true || password == "password"){
			document.getElementById("errMsg").innerHTML = "Password is week.";
		} else if(userType == 0){
			document.getElementById("errMsg").innerHTML = "User type is required.";
		} else {
			
			$("#usrList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "useracademicaction_save.action",				
				data : "formType=noneacademic&nti_id=" + nti_id + "&username=" + username + "&password=" + password + "&utId=" + userType,
				success : function(response) {				
					$('#usrList').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function saveTeachUser(){
		
		var tiid = $('#tiid').val();
		var username = $('#username').val();
		var password = $('#password').val();
		var utype = $('#teacherutype').val();
		
		var pattern = /^[A-Za-z ]{3,20}$/;		
		var pw_pattern_no = /^[0-9]{1,20}$/;
		var pw_pattern = /^[A-Za-z0-9@#$%^&*]{3,20}$/;
		var password_pattern_sp = pw_pattern.test(password);
		var password_pattern_no = pw_pattern_no.test(password);
		
		if(tiid == 0){
			document.getElementById("errMsg").innerHTML = "Teacher must be selected.";
		} else if(username == ""){
			document.getElementById("errMsg").innerHTML = "Username is required.";
		} else if(password == ""){
			document.getElementById("errMsg").innerHTML = "Password is required.";
		} else if(password_pattern_sp == false || password_pattern_no == true || password == "password"){
			document.getElementById("errMsg").innerHTML = "Password is week.";
		} else if(utype == 0){
			document.getElementById("errMsg").innerHTML = "User type is required.";
		} else {
			
			$("#usrList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "userteacheraction_save.action",				
				data : "formType=teacher&username=" + username + "&password=" + password + "&ti_id=" + tiid + "&userType=" + utype,
				success : function(response) {				
					$('#usrList').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	/*----- user edit -----*/
	
	function updateAcUser(ua_id){
		
		var name = $('#name').val();
		var utype = $('#utype').val();
		var status = $('#status').val();
		
		if(utype == 0){
			document.getElementById("errMsg").innerHTML = "User type is required.";
		} else {		
			$("#usrList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "useracademicaction_update.action",				
				data : "formType=noneacademic&name=" + name + "&utId=" + utype + "&ua_status=" + status + "&ua_id=" + ua_id,
				success : function(response) {				
					$('#usrList').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function updateTeacherUser(ua_id, ti_id){
		//var ti_id = $('#tiid').val();
		var teach_status = $('#teach_status').val();
		var utype = $('#utype').val();	
		
		if(utype == 0){
			document.getElementById("errMsg").innerHTML = "User type is required.";
		} else {
			$("#usrList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "userteacheraction_update.action",				
				data : "formType=noneacademic&ti_id=" + ti_id + "&ua_id=" + ua_id + "&ua_status=" + teach_status + "&userType=" + utype,
				success : function(response) {				
					$('#usrList').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	/*----- user list -----*/
	
	function noneAcademicUser(){
		$("#usrList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "useracademicaction_list.action",				
			data : "",
			success : function(response) {
				document.getElementById("nonAcd").className = "header_btn_active";
				document.getElementById("tech").className = "header_btn_inactive";
				document.getElementById("stud").className = "header_btn_inactive";
				$('#usrList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function teacherUser(){
		$("#usrList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "userteacheraction_list.action",				
			data : "",
			success : function(response) {
				document.getElementById("nonAcd").className = "header_btn_inactive";
				document.getElementById("tech").className = "header_btn_active";
				document.getElementById("stud").className = "header_btn_inactive";
				$('#usrList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function studentUser(){
		$("#usrList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "userstudentaction_list.action",				
			data : "",
			success : function(response) {
				document.getElementById("nonAcd").className = "header_btn_inactive";
				document.getElementById("tech").className = "header_btn_inactive";
				document.getElementById("stud").className = "header_btn_active";
				$('#usrList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*===== USER NON-ACADEMIC USER =====*/
	
	/*----- user non academic user list page -----*/
	
	function editAcdUser(indx){
		
		$("#usrList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "useracademicaction_editform.action",				
			data : "formType=noneacademic&indx=" + indx,
			success : function(response) {				
				$('#usrList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addNoneAcNewUser(){		
		$("#usrList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "useracademicaction_createform.action",				
			data : "formType=noneacademic",
			success : function(response) {				
				$('#usrList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function resetNoneTeacherUserPassword(ua_id, indx, name){
		
		$("#usrList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "useracademicaction_passwordresetform.action",				
			data : "formType=noneacademic&ua_id=" + ua_id + "&indx=" + indx + "&name=" + name,
			success : function(response) {				
				$('#usrList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- user reset password form page -----*/
	
	function resetUsersPassword(ua_id, formType, name){
		
		$("#usrList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		
		if(formType == "teacher"){
		
			$.ajax({
				type : "POST",
				url : "useracademicaction_resetUsersPassword.action",				
				data : "formType=teacher&ua_id=" + ua_id + "&name=" + name,
				success : function(response) {				
					$('#usrList').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
		if(formType == "noneacademic"){
			
			$.ajax({
				type : "POST",
				url : "useracademicaction_restnonteacher_password.action",				
				data : "formType=teacher&ua_id=" + ua_id + "&name=" + name,
				success : function(response) {				
					$('#usrList').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}

	/*----- user student user list page -----*/

	function editStudentUser(){
		
		$("#usrList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "useracademicaction_editform.action",				
			data : "formType=student",
			success : function(response) {				
				$('#usrList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addStudNewUser(){
		$("#usrList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "useracademicaction_createform.action",				
			data : "formType=student",
			success : function(response) {				
				$('#usrList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- user teacher user list page -----*/
	
	function editTeacherUser(ua_id, indx, ti_id, ua_status, ut_id, fullName){
		
		$("#usrList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "useracademicaction_editform.action",				
			data : "formType=teacher&ua_id=" + ua_id + "&indx=" + indx + "&ti_id=" + ti_id + "&ua_status=" + ua_status + "&utId=" + ut_id + "&name=" + fullName,
			success : function(response) {				
				$('#usrList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addTeachNewUser(){
		$("#usrList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "useracademicaction_createform.action",				
			data : "formType=teacher",
			success : function(response) {				
				$('#usrList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function resetTeacherUserPassword(ua_id, indx, ti_id, ua_status, ut_id, name){
		
		$("#usrList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "useracademicaction_passwordresetform.action",				
			data : "formType=teacher&ua_id=" + ua_id + "&indx=" + indx + "&ti_id=" + ti_id + "&ua_status=" + ua_status + "&utId=" + ut_id + "&name=" + name,
			success : function(response) {				
				$('#usrList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*===== USER ROLE =====*/
	
	/*----- user role create -----*/
	
	function saveUserRole(ut_id){
		
		var urole = $('#roleName').val();
		var relatedWith = $('#relatedWith').val();
		
		if(urole == ""){
			document.getElementById("errorMsg").innerHTML = "<span style=\"color:red\">Role name is required.</span>";
		} else if(relatedWith == 0){
			document.getElementById("errorMsg").innerHTML = "<span style=\"color:red\">Please select the relation</span>";
		}else {		
			$("#userRoleList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "userroleaction_save.action",				
				data : "ut_name=" + urole + "&related_with=" + relatedWith,
				success : function(response) {				
					$('#userRoleList').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	/*----- user role edit -----*/
	
	function updateUserRole(ut_id){
		
		var urole = $('#roleName').val();
		var ustatus = $('#roleStatus').val();
		var relatedWith = $('#relatedWith').val();
		
		if(urole == ""){
			document.getElementById("errorMsg").innerHTML = "<span style=\"color:red\">Role name is required.</span>";
		} else {
			$("#userRoleList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "userroleaction_update.action",				
				data : "utId=" + ut_id + "&ut_name=" + urole + "&ut_status=" + ustatus + "&related_with=" + relatedWith,
				success : function(response) {				
					$('#userRoleList').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	/*----- user role list page -----*/
	
	function editUserRole(ut_id, indx){
		
		$("#userRoleList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "userroleaction_editform.action",				
			data : "utId=" + ut_id + "&indx=" + indx,
			success : function(response) {				
				$('#userRoleList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addNewUserRole(){
		
		$("#userRoleList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "userroleaction_createform.action",				
			data : "",
			success : function(response) {				
				$('#userRoleList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function relateWithModule(ut_id, ut_name){
		
		$("#userRoleList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "userroleaction_modulelist.action",				
			data : "utId=" + ut_id + "&ut_name=" + ut_name,
			success : function(response) {				
				$('#userRoleList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*===== USER ROLE MODULE =====*/
	
	/*----- user role module edit -----*/
	
	function updateUTypeModuleRel(utm_id, ut_id, ut_name){
		
		var m_id = $('#m_id').val();
		var rel_status = $('#rel_status').val();
		
		$("#ModUteypRel").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "userroleaction_updatemoduleutyperel.action",				
			data : "utm_id=" + utm_id + "&utId=" + ut_id + "&m_id=" + m_id + "&mrel_status=" + rel_status + "&ut_name=" + ut_name,
			success : function(response) {				
				$('#userRoleList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- user role module list -----*/
	
	function editUserRoleModuleRel(utm_id, ut_id, m_name, m_id, mrel_status, ut_name){
		
		$("#ModUteypRel").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "userroleaction_updateformmodule.action",				
			data : "utm_id=" + utm_id + "&utId=" + ut_id + "&m_name=" + m_name + "&m_id=" + m_id + "&mrel_status=" + mrel_status + "&ut_name=" + ut_name,
			success : function(response) {				
				$('#ModUteypRel').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function saveUTypeModuleRel(ut_id, ut_name){
		
		var m_id = $('#m_id').val()
		if(m_id == 0){
			document.getElementById("errMsg").innerHTML = "<div style=\"color:red\">Please select the module first!!!</div>";
		} else {
			$("#ModUteypRel").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "userroleaction_savemodulelist.action",				
				data : "utId=" + ut_id + "&m_id=" + m_id + "&ut_name=" + ut_name,
				success : function(response) {				
					$('#userRoleList').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	/*===== ATTENDANCE =====*/
	
	/*----- attendance type template page -----*/
	
	function saveAttendanceType(){
		
		var attype_description = $('#attype_description').val();
		var attype_code = $('#attype_code').val();
		
		if(attype_description == ""){
			document.getElementById("eMsg").innerHTML = "Attendance type is required.";
		} else if(attype_code == ""){
			document.getElementById("eMsg").innerHTML = "Attendance type code is required.";
		}else {
			$("#saveEditForm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "attendance_saveAttendanceType.action",				
				data : "attype_description=" + attype_description + "&attype_code=" + attype_code,
				success : function(response) {
					$('#matListPage').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function editAttendanceType(attype_id, attype_description, attype_code, attype_status){
		$("#saveEditForm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "attendance_updateFormAttendanceType.action",				
			data : "attype_id=" + attype_id + "&attype_description=" + attype_description + "&attype_code=" + attype_code + "&attype_status=" + attype_status,
			success : function(response) {
				$('#saveEditForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- attendance type update page -----*/
	
	function updateAttendanceType(attype_id){
		
		var attype_description = $('#attype_description').val();
		var attype_code = $('#attype_code').val();
		var attype_status = $('#attype_status').val();
		
		if(attype_description == ""){
			document.getElementById("eMsg").innerHTML = "Attendance type is required.";
		} else if(attype_code == ""){
			document.getElementById("eMsg").innerHTML = "Attendance type code is required.";
		}else {
			$("#saveEditForm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "attendance_updateAttendanceType.action",				
				data : "attype_id=" + attype_id + "&attype_description=" + attype_description + "&attype_code=" + attype_code + "&attype_status=" + attype_status,
				success : function(response) {
					$('#matListPage').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	/*===== BEHAVIOUR EVALUATION =====*/
	
	/*----- behavioral evaluation template -----*/
	
	function traitsList(){
		$("#infoList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_traitslist.action",				
			data : "",
			success : function(response) {
				document.getElementById("trait").className = "header_btn_active";
				document.getElementById("grade").className = "header_btn_inactive";
				document.getElementById("cltRel").className = "header_btn_inactive";
				document.getElementById("clholRel").className = "header_btn_inactive";
				document.getElementById("holcat").className = "header_btn_inactive";
				document.getElementById("holpoint").className = "header_btn_inactive";
				$('#infoList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function evalGradeList(){
		$("#infoList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_gradelist.action",				
			data : "",
			success : function(response) {
				document.getElementById("trait").className = "header_btn_inactive";
				document.getElementById("grade").className = "header_btn_active";
				document.getElementById("cltRel").className = "header_btn_inactive";
				document.getElementById("clholRel").className = "header_btn_inactive";
				document.getElementById("holcat").className = "header_btn_inactive";
				document.getElementById("holpoint").className = "header_btn_inactive";
				$('#infoList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function classTraitRelationship(){
		$("#infoList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_classtraitrel.action",				
			data : "",
			success : function(response) {
				document.getElementById("trait").className = "header_btn_inactive";
				document.getElementById("grade").className = "header_btn_inactive";
				document.getElementById("cltRel").className = "header_btn_active";
				document.getElementById("clholRel").className = "header_btn_inactive";
				document.getElementById("holcat").className = "header_btn_inactive";
				document.getElementById("holpoint").className = "header_btn_inactive";
				$('#infoList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function classHolisticRelationship(){
		$("#infoList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_classholisticrel.action",				
			data : "",
			success : function(response) {
				document.getElementById("trait").className = "header_btn_inactive";
				document.getElementById("grade").className = "header_btn_inactive";
				document.getElementById("cltRel").className = "header_btn_inactive";
				document.getElementById("clholRel").className = "header_btn_active";
				document.getElementById("holcat").className = "header_btn_inactive";
				document.getElementById("holpoint").className = "header_btn_inactive";
				$('#infoList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function holisticCatList(){
		$("#infoList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_holisticcatlist.action",				
			data : "",
			success : function(response) {
				document.getElementById("trait").className = "header_btn_inactive";
				document.getElementById("grade").className = "header_btn_inactive";
				document.getElementById("cltRel").className = "header_btn_inactive";
				document.getElementById("clholRel").className = "header_btn_inactive";
				document.getElementById("holcat").className = "header_btn_active";
				document.getElementById("holpoint").className = "header_btn_inactive";
				$('#infoList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function holisticPointList(){
		$("#infoList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_holisticpointlist.action",				
			data : "",
			success : function(response) {
				document.getElementById("trait").className = "header_btn_inactive";
				document.getElementById("grade").className = "header_btn_inactive";
				document.getElementById("cltRel").className = "header_btn_inactive";
				document.getElementById("clholRel").className = "header_btn_inactive";
				document.getElementById("holcat").className = "header_btn_inactive";
				document.getElementById("holpoint").className = "header_btn_active";
				$('#infoList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- behavioral evaluation grade list -----*/
	
	function saveBevalGrade(){
		var bg_name = $('#bg_name').val();
		var bg_desc = $('#bg_desc').val();
		
		if(bg_name == ""){
			document.getElementById("eMsg").innerHTML = "Behavioural evaluation grade must be filed.";
		} else if(bg_desc == ""){
			document.getElementById("eMsg").innerHTML = "Behavioural evaluation grade description must be filed.";
		} else {
		
			$("#bgpage").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "behaviouralevaluation_gradesave.action",				
				data : "bg_name=" + bg_name + "&bg_desc=" + bg_desc,
				success : function(response) {					
					$('#bgpage').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	function bevalEditForm(bg_id){
		$("#saveEditForm").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_gradeeditform.action",				
			data : "bg_id=" + bg_id,
			success : function(response) {					
				$('#saveEditForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function updateBevalGrade(bg_id){
		var bg_name = $('#bg_name').val();
		var bg_desc = $('#bg_desc').val();
		var bg_status = $('#bg_status').val();
		
		if(bg_name == ""){
			document.getElementById("eMsg").innerHTML = "Behavioural evaluation grade must be filed.";
		} else if(bg_desc == ""){
			document.getElementById("eMsg").innerHTML = "Behavioural evaluation description must be filed.";
		} else {
		
			$("#bgpage").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "behaviouralevaluation_gradeupdate.action",				
				data : "bg_name=" + bg_name + "&bg_desc=" + bg_desc + "&bg_id=" + bg_id + "&bg_status=" + bg_status,
				success : function(response) {				
					$('#bgpage').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});		
		}
	}
	
	/*----- behavioral evaluation trait list page -----*/
	
	function saveBevalTrait(){
		var bt_title = $('#bt_title').val();
		var bt_desc = $('#bt_desc').val();
		
		if(bt_title == ""){
			document.getElementById("eMsg").innerHTML = "Behavioural evaluation title must be filed.";
		} else if(bt_desc == ""){
			document.getElementById("eMsg").innerHTML = "Behavioural evaluation description must be filed.";
		} else {
		
			$("#btpage").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "behaviouralevaluation_traitsave.action",				
				data : "bt_title=" + bt_title + "&bt_desc=" + bt_desc,
				success : function(response) {					
					$('#btpage').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	function bevalTraitEditForm(bt_id){
		
		$("#saveEditForm").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_traiteditform.action",				
			data : "bt_id=" + bt_id,
			success : function(response) {					
				$('#saveEditForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function updateBevalTrait(bt_id){
		
		var bt_title = $('#bt_title').val();
		var bt_desc = $('#bt_desc').val();
		var bt_status = $('#bt_status').val();
		
		if(bt_title == ""){
			document.getElementById("eMsg").innerHTML = "Behavioural evaluation title must be filed.";
		} else if(bt_desc == ""){
			document.getElementById("eMsg").innerHTML = "Behavioural evaluation description must be filed.";
		} else {
		
			$("#btpage").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "behaviouralevaluation_traitupdate.action",				
				data : "bt_title=" + bt_title + "&bt_desc=" + bt_desc + "&bt_id=" + bt_id + "&bt_status=" + bt_status,
				success : function(response) {				
					$('#btpage').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});		
		}
	}
	
	function traitGradeRel(bt_id, bt_title){
		
		$("#btpage").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_traitgraderel.action",				
			data : "bt_title=" + bt_title + "&bt_id=" + bt_id,
			success : function(response) {				
				$('#btpage').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- behavioral evaluation trait grade rel list page -----*/
	
	function saveBevalTraitGradeRel(bt_id){
		
		var bg_id = $('#bg_id').val();
		
		if(bg_id == null){			
			document.getElementById("errMsg").innerHTML = "Please select grade.";
		} else {
			
			$("#btbgrelpage").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "behaviouralevaluation_savetraitsgraderel.action",				
				data : "bt_id=" + bt_id + "&bg_id=" + bg_id,
				success : function(response) {
					$('#btbgrelpage').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function traitGradeRelEditForm(btbg_id, bt_id){
		
		$("#saveEditForm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_editformtraitsgraderel.action",				
			data : "btbg_id=" + btbg_id + "&bt_id=" + bt_id,
			success : function(response) {
				$('#saveEditForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function updateBevalTraitGradeRel(btbg_id, bt_id){
		
		var btbg_rel = $('#btbg_rel').val();
		
		$("#btpage").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_updatetraitsgraderel.action",				
			data : "btbg_id=" + btbg_id + "&bt_id=" + bt_id + "&btbg_rel=" + btbg_rel,
			success : function(response) {
				$('#btpage').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- class trait rel folder - class trait rel template page -----*/
	
	var prev_clid = null;
	function traitListPerGradeSelected(cl_id){
		$("#traitList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_classtraitlist.action",				
			data : "cl_id=" + cl_id,
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
				$('#traitList').html(response);
				prev_clid = cl_id;
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- class strait rel folder - class trait rel list page -----*/
	
	function saveTraitClassRel(cl_id){
		
		var bt_id = $('#bt_id').val();
		
		if(bt_id == 0){
			document.getElementById("errMsg").value = "Please select trait";
		} else {
		
			$("#traitInfo").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "behaviouralevaluation_saveclasstraitrel.action",				
				data : "cl_id=" + cl_id + "&bt_id=" + bt_id,
				success : function(response) {
					
					$('#traitInfo').html(response);
					
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function editClassTraitRel(btc_id, cl_id){
		
		$("#saveEditForm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_editformclasstraitrel.action",				
			data : "btc_id=" + btc_id + "&cl_id=" + cl_id,
			success : function(response) {
				
				$('#saveEditForm').html(response);
				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function updateTraitClassRel(btc_id, cl_id){
		
		var rel_status = $('#rel_status').val();
		
		$("#traitInfo").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_updateclasstraitrel.action",				
			data : "btc_id=" + btc_id + "&cl_id=" + cl_id + "&rel_status=" + rel_status,
			success : function(response) {
				
				$('#traitInfo').html(response);
				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
		
	}	
	
	/*===== CERTIFICATE DEFAULT COMMENT =====*/
	
	/*----- cert default comment list page -----*/
	
	function saveDefaultComment(){
		
		var edc_com = $('#edc_com').val();
		var rank_from = $('#rank_from').val();
		var rank_to = $('#rank_to').val();
		
		if(edc_com == ""){
			document.getElementById("eMsg").innerHTML = "Comment is required.";
		} else if(rank_from == ""){
			document.getElementById("eMsg").innerHTML = "Rank from is required.";
		} else if(rank_to == ""){
			document.getElementById("eMsg").innerHTML = "Rank to is required.";
		} else {
			$("#defCom").html("<div width=\"100%\" align=\"center\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "defaultComment_save.action",				
				data : "edc_content=" + edc_com + "&rank_from=" + rank_from + "&rank_to=" + rank_to,
				success : function(response) {				
					$('#defCom').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function editDefaultComment(edc_id, edc_content, rank_from, rank_to, edc_status){
		
		$("#saveEditForm").html("<div width=\"100%\" align=\"center\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "defaultComment_updatefrom.action",				
			data : "edc_id=" + edc_id + "&edc_content=" + edc_content + "&rank_from=" + rank_from + "&rank_to=" + rank_to + "&edc_status=" + edc_status,
			success : function(response) {				
				$('#saveEditForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- cert default comment edit page -----*/
	
	function updateDefaultComment(edc_id){
		
		var edc_com = $('#edc_com').val();
		var rank_from = $('#rank_from').val();
		var rank_to = $('#rank_to').val();
		var edc_status = $('#edc_status').val();
		
		if(edc_com == ""){
			document.getElementById("eMsg").innerHTML = "Comment is required.";
		} else if(rank_from == ""){
			document.getElementById("eMsg").innerHTML = "Rank from is required.";
		} else if(rank_to == ""){
			document.getElementById("eMsg").innerHTML = "Rank to is required.";
		} else {
			$("#defCom").html("<div width=\"100%\" align=\"center\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "defaultComment_update.action",				
				data : "edc_id=" + edc_id + "&edc_content=" + edc_com + "&rank_from=" + rank_from + "&rank_to=" + rank_to + "&edc_status=" + edc_status,
				success : function(response) {				
					$('#defCom').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}	
	
	/*===== CLASS ALLOTMENT =====*/

	/*----- class allotment template -----*/
	
	function getClSubPeriodAllotmentPerYear(ac_year){
		
		$("#classSubPeriodAllotmentList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "adminaction_classSubjectPeriodAllotmentByYear.action",				
			data : "academic_year=" + ac_year,
			success : function(response) {				
				$('#classSubPeriodAllotmentList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	var subjectPeriodAllotment = new Array();

	function getSubjectListPerClass(cl_id){
		
		subjectPeriodAllotment = [];
		
		$("#subjectPeriodList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "adminaction_subjectListForm.action",				
			data : "croomBean.cl_id=" + cl_id,
			success : function(response) {				
				$('#subjectPeriodList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addSubjectPeriodAllotment(period_num, sub_id){
		
		var counter = subjectPeriodAllotment.length;
		var checker = 0;
		
		for(var i = 0; i < counter; i++){
			
			if(sub_id == subjectPeriodAllotment[i][0]){
				
				subjectPeriodAllotment[i] = new Array(sub_id, period_num);
				checker++;
				break;
			}
		}
		
		if(checker == 0){
			subjectPeriodAllotment[counter] = new Array(sub_id, period_num);
		}
	}
	
	function saveClassPeriodAllotment(sub_counter, cl_id){
		
		var academic_year = $('#academic_year').val();
		
		if(Number(sub_counter) > Number(subjectPeriodAllotment.length)){
			
			document.getElementById("errorMessage").innerHTML = "There is missing subject-period allotment.";
			
		} else {
			
			$("#subjectPeriodList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "adminaction_saveClassPeriodAllotment.action",				
				data : "croomBean.cl_id=" + cl_id + "&academic_year=" + academic_year + "&subjectPeriodAllotment=" + subjectPeriodAllotment,
				success : function(response) {				
					$('#subjectPeriodList').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function editAllotedNumOfPeriod(csp_id){
		
		$("#periodAllotmentForm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "adminaction_editAllotedNumOfPeriod.action",				
			data : "csp_id=" + csp_id,
			success : function(response) {				
				$('#periodAllotmentForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function updateClassPeriodAllotment(csp_id){
		
		var period_per_week = $('#period_per_week').val();
		
		$("#userAdminCont").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "adminaction_updateClassPeriodAllotment.action",				
			data : "csp_id=" + csp_id + "&period_per_week=" + period_per_week,
			success : function(response) {				
				$('#userAdminCont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
		
	}
		
	/*===== CURRICULUM ACTIVITY =====*/
	
	/*----- co-curriculum activity template page -----*/
	
	function departmentList(){
		$("#infoList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "cocuract_departmentlist.action",				
			data : "",
			success : function(response) {
				document.getElementById("dep").className = "header_btn_active";
				document.getElementById("club").className = "header_btn_inactive";
				document.getElementById("trole").className = "header_btn_inactive";
				$('#infoList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function clubList(){
		$("#infoList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "cocuract_clublist.action",				
			data : "",
			success : function(response) {
				document.getElementById("dep").className = "header_btn_inactive";
				document.getElementById("club").className = "header_btn_active";
				document.getElementById("trole").className = "header_btn_inactive";
				$('#infoList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function teachersRole(){
		$("#infoList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "cocuract_teacherrolelist.action",				
			data : "",
			success : function(response) {
				document.getElementById("dep").className = "header_btn_inactive";
				document.getElementById("club").className = "header_btn_inactive";
				document.getElementById("trole").className = "header_btn_active";
				$('#infoList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- cocuract teacher role page -----*/
	
	function saveCocurActTeacherRole(){
		var tiid = $('#tchList').val();
		var roleid = $('#tchRoleList').val();
		var respid = $('#tchRespList').val();
		var acyear = $('#acyear').val();
		
		if(tiid == "0"){
			document.getElementById("eMsg").innerHTML = "Teacher must be selected.";
		} else if(roleid == "0"){
			document.getElementById("eMsg").innerHTML = "Role must be selected.";
		} else if(respid == "0"){
			document.getElementById("eMsg").innerHTML = "Responsibility must be selected.";
		} else if(acyear == "0"){
			document.getElementById("eMsg").innerHTML = "Academic year must be selected.";
		} else {
		
			$("#deppage").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "cocuract_teacherrolesave.action",				
				data : "ti_id=" + tiid + "&role_id=" + roleid + "&responsibility_id=" + respid + "&academic_year=" + acyear,
				success : function(response) {				
					$('#deppage').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	function depOrClubList(selRole){
		
		$("#tchRespList").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "cocuract_deporclublist.action",				
			data : "role_id=" + selRole,
			success : function(response) {				
				$('#tchRespList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function tchrRespEditForm(trid){
		$("#saveEditForm").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "cocuract_tchroleupdateform.action",				
			data : "tr_id=" + trid,
			success : function(response) {				
				$('#saveEditForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function updateCocurActTeacherRole(tr_id){
		var tiid = $('#tchList').val();
		var roleid = $('#tchRoleList').val();
		var respid = $('#tchRespList').val();
		var acyear = $('#acyear').val();
		var trstatus = $('#trstatus').val();
		
		if(tiid == "0"){
			document.getElementById("eMsg").innerHTML = "Teacher must be selected.";
		} else if(roleid == "0"){
			document.getElementById("eMsg").innerHTML = "Role must be selected.";
		} else if(respid == "0"){
			document.getElementById("eMsg").innerHTML = "Department or Club must be selected.";
		} else if(acyear == "0"){
			document.getElementById("eMsg").innerHTML = "Academic year must be selected.";
		} else {
		
			$("#deppage").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "cocuract_tchrroleupdate.action",				
				data : "tr_id=" + tr_id + "&ti_id=" + tiid + "&role_id=" + roleid + "&responsibility_id=" + respid + "&academic_year=" + acyear + "&tr_status=" + trstatus,
				success : function(response) {				
					$('#deppage').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	/*----- cocuract department page -----*/
	
	function saveCocurActDepartment(){
		var depname = $('#depname').val();
		var depdesc = $('#depdesc').val();
		
		if(depname == ""){
			document.getElementById("eMsg").innerHTML = "Department name must be filed.";
		} else if(depdesc == ""){
			document.getElementById("eMsg").innerHTML = "Department description must be filed.";
		} else {
		
			$("#deppage").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "cocuract_departmentsave.action",				
				data : "dep_name=" + depname + "&dep_desc=" + depdesc,
				success : function(response) {				
					$('#deppage').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	function departmentEditForm(depid){
		$("#saveEditForm").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "cocuract_departmentupdateform.action",				
			data : "dep_id=" + depid,
			success : function(response) {				
				$('#saveEditForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function updateCocurActDepartment(dep_id){
		var depname = $('#depname').val();
		var depdesc = $('#depdesc').val();
		var depstatus = $('#depstatus').val();
		
		if(depname == ""){
			document.getElementById("eMsg").innerHTML = "Department name must be filed.";
		} else if(depdesc == ""){
			document.getElementById("eMsg").innerHTML = "Department description must be filed.";
		} else {
		
			$("#deppage").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "cocuract_departmentupdate.action",				
				data : "dep_id=" + dep_id +"&dep_name=" + depname + "&dep_desc=" + depdesc + "&dep_status=" + depstatus,
				success : function(response) {				
					$('#deppage').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	/*----- cocuract club page -----*/
	
	function saveCocurActClub(){
		var clbname = $('#clbname').val();
		var clbdesc = $('#clbdesc').val();
		var depid = $('#depid').val();
		
		if(clbname == ""){
			document.getElementById("eMsg").innerHTML = "Club name must be filed.";
		} else if(clbdesc == ""){
			document.getElementById("eMsg").innerHTML = "Club description must be filed.";
		} else if(depid == "0"){
			document.getElementById("eMsg").innerHTML = "Department must be selected.";
		} else {
		
			$("#deppage").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "cocuract_clubsave.action",				
				data : "clb_name=" + clbname + "&clb_desc=" + clbdesc + "&dep_id=" + depid,
				success : function(response) {				
					$('#deppage').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	function clubEditForm(clbid){
		$("#saveEditForm").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "cocuract_clubupdateform.action",				
			data : "clb_id=" + clbid,
			success : function(response) {				
				$('#saveEditForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function updateCocurActClub(clb_id){
		var clbname = $('#clbname').val();
		var clbdesc = $('#clbdesc').val();
		var clbstatus = $('#clbstatus').val();
		var depid = $('#depid').val();
		
		if(clbname == ""){
			document.getElementById("eMsg").innerHTML = "Club name must be filed.";
		} else if(clbdesc == ""){
			document.getElementById("eMsg").innerHTML = "Club description must be filed.";
		} else if(depid == "0"){
			document.getElementById("eMsg").innerHTML = "Department must be selected.";
		} else {
		
			$("#deppage").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "cocuract_clubupdate.action",				
				data : "clb_id=" + clb_id +"&clb_name=" + clbname + "&clb_desc=" + clbdesc + "&clb_status=" + clbstatus + "&dep_id=" + depid,
				success : function(response) {				
					$('#deppage').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	/*===== EXAM GRADING =====*/
	
	/*----- exam grading list -----*/
	
	function saveGrade(){
		var eg_value = $('#eg_value').val();
		var eg_desc = $('#eg_desc').val();
		
		if(eg_value == ""){
			document.getElementById("eMsg").innerHTML = "Grade value is required.";
		} else if(eg_desc == ""){
			document.getElementById("eMsg").innerHTML = "Grade description is required.";
		} else {
			$("#grd").html("<div width=\"100%\" align=\"center\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "examgrade_save.action",				
				data : "eg_value=" + eg_value + "&eg_desc=" + eg_desc,
				success : function(response) {				
					$('#grd').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function editGrading(eg_id, eg_value, eg_desc, eg_status){
		
		$("#saveEditForm").html("<div width=\"100%\" align=\"center\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "examgrade_updateform.action",				
			data : "eg_id=" + eg_id + "&eg_value=" + eg_value + "&eg_desc=" + eg_desc + "&eg_status=" + eg_status,
			success : function(response) {				
				$('#saveEditForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- exam grading edit -----*/
	
	function updateGrade(eg_id){
		var eg_value = $('#eg_value').val();
		var eg_desc = $('#eg_desc').val();
		var eg_status = $('#eg_status').val();
		
		if(eg_value == ""){
			document.getElementById("eMsg").innerHTML = "Grade value is required.";
		} else if(eg_desc == ""){
			document.getElementById("eMsg").innerHTML = "Grade description is required.";
		} else {
			$("#grd").html("<div width=\"100%\" align=\"center\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "examgrade_update.action",				
				data : "eg_id=" + eg_id + "&eg_value=" + eg_value + "&eg_desc=" + eg_desc + "&eg_status=" + eg_status,
				success : function(response) {				
					$('#grd').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	/*===== HOME ROOM TEACHER ASSIGNMENT =====*/
	
	/*----- home room teacher assign template page -----*/
	
	function filterClassDetail(cl_id){
		$("#cdid").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "useraction_classdetailList.action",				
			data : "class_id=" + cl_id,
			success : function(response) {				
				$('#cdid').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function saveAssignment(){
		var tiid = $('#tiid').val();
		var clid = $('#clid').val();
		var cdid = $('#cdid').val();
		
		if(tiid == "0"){
			document.getElementById("eMsg").innerHTML = "Teacher must be selected.";
		} else if(clid == "0"){
			document.getElementById("eMsg").innerHTML = "Class must be selected.";
		} else if(cdid == "0"){
			document.getElementById("eMsg").innerHTML = "Section must be selected.";
		} else {
		
			$("#assignpage").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "useraction_savetcherassign.action",				
				data : "ti_id=" + tiid + "&class_id=" + clid + "&cd_id=" + cdid,
				success : function(response) {				
					$('#assignpage').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	function tassignEditForm(thra_id){
		
		$("#saveEditForm").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "useraction_edittcherassign.action",				
			data : "thra_id=" + thra_id,
			success : function(response) {				
				$('#saveEditForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- home room teacher assign edit page -----*/
	
	function updateAssignment(thra_id){
		
		var tiid = $('#tiid').val();
		var clid = $('#clid').val();
		var cdid = $('#cdid').val();
		var thra_status = $('#thra_status').val();
		
		if(tiid == "0"){
			document.getElementById("eMsg").innerHTML = "Teacher must be selected.";
		} else if(clid == "0"){
			document.getElementById("eMsg").innerHTML = "Class must be selected.";
		} else if(cdid == "0"){
			document.getElementById("eMsg").innerHTML = "Section must be selected.";
		} else {
		
			$("#assignpage").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "useraction_updatetcherassign.action",				
				data : "thra_id=" + thra_id + "&ti_id=" + tiid + "&class_id=" + clid + "&cd_id=" + cdid + "&thra_status=" + thra_status,
				success : function(response) {				
					$('#assignpage').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	/*===== ID CARD GENERATION =====*/
	
	/*----- id card generation template page -----*/
	
	var prev_clid = null;
	function gradeSelectedForIDCard(cl_id, clname){
		
		var ac_year = $("#academicYear").val();
		
		$("#student_list").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "generate_id_card_classgradelist.action",				
			data : "class_bean.cl_id=" + cl_id + "&class_bean.class_name=" + clname,
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

	function generateIdCardForRemaining(){
		
		$("#student_list").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "generate_id_card_forremainingstud.action",				
			data : "",
			success : function(response) {			
				$('#student_list').html(response);			
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- class detail list -----*/
	
	var prev_cdid = null;
	function classDetailForIDCard(clid, cd_id, ac_year){
		
		$("#student_list_perdetial").html("<div width=\"100%\" align=\"center\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "generate_id_card_studentlistperclassdetail.action",				
			data : "class_bean.cl_id=" + clid + "&cdetail_bean.cd_id=" + cd_id + "&ac_year=" + ac_year,
			success : function(response) {
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
	
	/*----- stud list remaining page -----*/
	
	function generateRemainingIDCard(cl_id, cd_id){
		
		$("#stud_with_out_id").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "generate_id_card_generateidcard_forremainingstudent.action",
			data : "pdf_full_page_status=No",
			success : function(response) {			
				$('#stud_with_out_id').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- stud list page -----*/
	
	function listStudentDontHavePhoto(cl_id, cd_id){
		
		$("#studentListPerGrade").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_getStudentDontHavePhotoContactInfo.action",				
			data : "class_bean.cl_id=" + cl_id + "&cdetail_bean.cd_id=" + cd_id,
			success : function(response) {
				$('#studentListPerGrade').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	} 
	
	function listStudentDontHaveContactInfo(cl_id, cd_id){
		
		$("#studentListPerGrade").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentaction_getStudentDontHaveContactInfo.action",				
			data : "class_bean.cl_id=" + cl_id + "&cdetail_bean.cd_id=" + cd_id,
			success : function(response) {
				$('#studentListPerGrade').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- stud with out id list -----*/
	
	function generateIDCardForStudent(cl_id, cd_id){
		
		$("#stud_with_out_id").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "generate_id_card_generatestudentidcard.action",				
			data : "class_bean.cl_id=" + cl_id + "&cdetail_bean.cd_id=" + cd_id +"&pdf_full_page_status=Yes",
			success : function(response) {			
				$('#stud_with_out_id').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*===== PAYMENT ADMIN =====*/
	
	/*----- payment admin template page -----*/
	
	function paymentInfo_create(){
		$("#payAdmin").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "paymentinfo_create.action",				
			data : "",
			success : function(response) {
				$('#payAdmin').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function paymentInfo_edit(indx){
		$("#payAdmin").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "paymentinfo_edit.action",				
			data : "indx=" + indx,
			success : function(response) {
				$('#payAdmin').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- payment admin edit page -----*/
	
	function updatePaymentInfo(pc_id){
		
		var cl_id = $('#cl_id').val();
		var pt_id = $('#pt_id').val();
		var pay_amount = $('#pay_amount').val();
		var penality_max_date = $('#penality_max_date').val();
		var penality_percent = $('#penality_percent').val();
		var academic_year = $('#academic_year').val();
		var pc_status = $('#pc_status').val();
		
		if(cl_id == 0 || pt_id == 0 || pay_amount == "" || penality_max_date == "" || penality_percent == "" || academic_year == ""){
			document.getElementById("errorMsg").innerHTML = "All fields are required";
		} else {
		
			$("#payAdmin").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "payment_info_update.action",				
				data : "pc_id=" + pc_id + "&class_id=" + cl_id + "&pt_id=" + pt_id + "&pay_amount=" + pay_amount + "&penality_max_date=" + penality_max_date + "&penality_percent=" + penality_percent + "&academic_year=" + academic_year + "&pc_status=" + pc_status,
				success : function(response) {
					$('#payAdmin').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});		
		}
	}
	
	/*----- payment admin create -----*/
	
	function savePaymentInfo(){
		var cl_id = $('#cl_id').val();
		var pt_id = $('#pt_id').val();
		var pay_amount = $('#pay_amount').val();
		var penality_max_date = $('#penality_max_date').val();
		var penality_percent = $('#penality_percent').val();
		var academic_year = $('#academic_year').val();
		
		if(cl_id == 0 || pt_id == 0 || pay_amount == "" || penality_max_date == "" || penality_percent == "" || academic_year == ""){
			document.getElementById("errorMsg").innerHTML = "All fields are required";
		} else {
		
			$("#payAdmin").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "payment_info_save.action",				
				data : "class_id=" + cl_id + "&pt_id=" + pt_id + "&pay_amount=" + pay_amount + "&penality_max_date=" + penality_max_date + "&penality_percent=" + penality_percent + "&academic_year=" + academic_year,
				success : function(response) {
					$('#payAdmin').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});		
		}
	}
	
	
	/*===== SCHOOL INFORMATION =====*/
	
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
		
		$("#school_information").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "payment_school_info_save.action",				
			data : "school_name=" + school_name + "&school_slogan=" + school_slogan + "&tin_num=" + tin_num + "&telephone=" + telephone + "&fax=" + fax + "&web=" + web + "&email=" + email + "&pobox=" + pobox + "&fiscal_machine_no=" + fiscal_machine_no,
			success : function(response) {				
				$('#school_information').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*===== SPECIAL REGISTRATION =====*/
	
	/*----- special reg template page -----*/
	
	var prev_clid = null;
	function gradeSelectedForSpecialReg(cl_id, clname){
		//var ac_year = $("#academicYear").val();
		
		$("#student_list").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "speicalreg_classgradelist.action",				
			data : "class_bean.cl_id=" + cl_id + "&class_bean.class_name=" + clname,
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
	
	/*----- speical reg stud list page -----*/
	
	function selectStudent(si_id){
		
		$("#regSuccuss").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "speicalreg_regaddselectedstudent.action",				
			data : "stud_bean.si_id=" + si_id,
			success : function(response) {			
				//$('#regSuccuss').html(response);
				document.getElementById(si_id+"_id").innerHTML = "<div onclick=\"removeStudent(" + si_id + ")\"><img alt=\"add\" src=\"images/checked.png\" style=\"width: 16px;\"></div>";
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}

	function removeStudent(si_id){
		
		$("#regSuccuss").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "speicalreg_regremoveselectedstudent.action",				
			data : "stud_bean.si_id=" + si_id,
			success : function(response) {			
				//$('#regSuccuss').html(response);
				document.getElementById(si_id+"_id").innerHTML = "<div onclick=\"removeStudent(" + si_id + ")\"><img alt=\"add\" src=\"images/check_box.png\" style=\"width: 16px;\"></div>";
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function registerStudentSpecially(cl_id, cd_id){
		
		$("#student_list_perdetial").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "speicalreg_registerselectedstudent.action",				
			data : "class_bean.cl_id=" + cl_id + "&cdetail_bean.cd_id=" + cd_id,
			success : function(response) {			
				$('#student_list_perdetial').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- registered stud list -----*/
	
	function selectRegisteredStudent(si_id){
		
		$("#regSuccuss").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "speicalreg_regaddregisteredstudent.action",				
			data : "stud_bean.si_id=" + si_id,
			success : function(response) {			
				//$('#regSuccuss').html(response);
				document.getElementById(si_id+"_id").innerHTML = "<div onclick=\"removeSelectionStudent(" + si_id + ")\"><img alt=\"add\" src=\"images/checked.png\" style=\"width: 16px;\"></div>";
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}

	function removeSelectionStudent(si_id){
		
		$("#regSuccuss").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "speicalreg_regremoveregisteredstudentselection.action",				
			data : "stud_bean.si_id=" + si_id,
			success : function(response) {			
				//$('#regSuccuss').html(response);
				document.getElementById(si_id+"_id").innerHTML = "<div onclick=\"selectRegisteredStudent(" + si_id + ")\"><img alt=\"add\" src=\"images/check_box.png\" style=\"width: 16px;\"></div>";
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function removeStudentFromSpecialRegistration(cl_id, cd_id){
		
		$("#student_list_perdetial").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "speicalreg_removestudentfromspecialreg.action",				
			data : "class_bean.cl_id=" + cl_id + "&cdetail_bean.cd_id=" + cd_id,
			success : function(response) {			
				$('#student_list_perdetial').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- class detail list page -----*/
	
	var prev_cdid = null;
	function classDetail(clid, cd_id, ac_year){
		
		$("#student_list_perdetial").html("<div width=\"100%\" align=\"center\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "speicalreg_studentlistperclassdetail.action",				
			data : "class_bean.cl_id=" + clid + "&cdetail_bean.cd_id=" + cd_id + "&ac_year=" + ac_year,
			success : function(response) {
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
	
	/*===== STUDENT MATERIAL =====*/
	
	/*----- student material template -----*/
	
	function materialList(){
		$("#matInfoList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentMaterial_materialList.action",				
			data : "",
			success : function(response) {
				document.getElementById("matList").className = "header_btn_active";
				document.getElementById("matClassRel").className = "header_btn_inactive";
				$('#matInfoList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function materialClassRelation(){
		$("#matInfoList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentMaterial_classMaterialRelList.action",				
			data : "",
			success : function(response) {
				document.getElementById("matClassRel").className = "header_btn_active";
				document.getElementById("matList").className = "header_btn_inactive";
				$('#matInfoList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- student material update page -----*/
	
	function updateStudentMaterial(ptm_id){
		
		var ptm_name = $('#ptm_name').val();
		var ptm_desc = $('#ptm_desc').val();
		var ptm_status = $('#ptm_status').val();
		
		if(ptm_name == ""){
			document.getElementById("eMsg").innerHTML = "Material name is required.";
		} else {
		
			$("#saveEditForm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "studentMaterial_material_update.action",				
				data : "ptm_id=" + ptm_id + "&ptm_name=" + ptm_name + "&ptm_desc=" + ptm_desc + "&ptm_status=" + ptm_status,
				success : function(response) {
					$('#matListPage').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	/*----- student material list page -----*/
	
	function saveMaterial(){
		
		var ptm_name = $('#ptm_name').val();
		var ptm_desc = $('#ptm_desc').val();
		
		if(ptm_name == ""){
			document.getElementById("eMsg").innerHTML = "Material name is required.";
		} else {
			$("#saveEditForm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "studentMaterial_save_material.action",				
				data : "ptm_name=" + ptm_name + "&ptm_desc=" + ptm_desc,
				success : function(response) {
					$('#matListPage').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function editStudentMaterial(ptm_id, ptm_name, ptm_desc, ptm_status){
		$("#saveEditForm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentMaterial_material_update_form.action",				
			data : "ptm_id=" + ptm_id + "&ptm_name=" + ptm_name + "&ptm_desc=" + ptm_desc + "&ptm_status=" + ptm_status,
			success : function(response) {
				$('#saveEditForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*===== class material relationship =====*/
	
	/*----- class material rel list page -----*/
	
	function saveMaterialClassRel(){
		
		var ptm_id = $('#ptm_id').val();
		var cl_id = $('#cl_id').val();
		var pay_amount = $('#pay_amount').val();
		
		if(ptm_id == "0" || cl_id == "0" || pay_amount == ""){
			document.getElementById("eMsg").innerHTML = "All are required.";
		} else {
		
			$("#saveEditForm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "studentMaterial_save_materialclass_rel.action",				
				data : "ptm_id=" + ptm_id + "&cl_id=" + cl_id + "&payment_amount=" + pay_amount,
				success : function(response) {
					$('#matListPage').html(response);
				},
				error : function(e) {
					//alert('Error: ' + e);
				}
			});
		}
	}

	function editMaterialClassRel(pmc_id, ptm_id, cl_id, pay_amount, pmc_status){
		
		$("#saveEditForm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentMaterial_matclassrel_update_form.action",				
			data : "pmc_id=" + pmc_id + "&ptm_id=" + ptm_id + "&cl_id=" + cl_id + "&payment_amount=" + pay_amount + "&pmc_status=" + pmc_status,
			success : function(response) {
				$('#saveEditForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- class material rel update page -----*/
	
	function updateMaterialClassRel(pmc_id){
		
		var cl_id = $('#cl_id').val();
		var ptm_id = $('#ptm_id').val();
		var pay_amount = $('#pay_amount').val();
		var pmc_status = $('#pmc_status').val();
		
		if(pay_amount == ""){
			document.getElementById("eMsg").innerHTML = "Pay amount is required.";
		} else {
		
			$("#saveEditForm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "studentMaterial_matclassrel_update.action",				
				data : "pmc_id=" + pmc_id + "&cl_id=" + cl_id + "&ptm_id=" + ptm_id + "&payment_amount=" + pay_amount + "&pmc_status=" + pmc_status,
				success : function(response) {
					$('#matListPage').html(response);
				},
				error : function(e) {
					//alert('Error: ' + e);
				}
			});
		}
	}
	
	
	