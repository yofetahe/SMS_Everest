/**
 * 
 */
/*===================== ADMIN MENU =====================*/

	/*----- admin template -----*/
	
	function addUsers(){
		
		$("#userAdminCont").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "useraction_list.action",				
			data : "",
			success : function(response) { 
				document.getElementById("usr").innerHTML = "<img alt=\"click\" src=\"images/next.gif\" width=\"8px\">&nbsp;Users";
				document.getElementById("usrrole").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users Role";
				document.getElementById("hroomtchr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Home Room Teacher";
				document.getElementById("annTerm").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Annual Term";
				document.getElementById("schEvent").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Events";
				document.getElementById("hldList").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Holidays";
				document.getElementById("exmSch").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Schedule";
				document.getElementById("curactivity").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Co-curriculum";
				document.getElementById("behEval").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Behavioural Evaluation";
				document.getElementById("studMat").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Student Material";
				document.getElementById("attType").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Attendance";
				document.getElementById("payment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Payment";
				document.getElementById("sch_info").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Info";
				document.getElementById("def_com").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Certificate Default Comment";
				document.getElementById("grading").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Grading";
				document.getElementById("spe_reg").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Special Registration";
				document.getElementById("id_card").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Generate ID Card";
				document.getElementById("class_allotment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Period Allotment";
				$('#userAdminCont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addUserRole(){
		
		$("#userAdminCont").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "useraction_rolelist.action",				
			data : "",
			success : function(response) { 
				document.getElementById("usr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users";
				document.getElementById("usrrole").innerHTML = "<img alt=\"click\" src=\"images/next.gif\" width=\"8px\">&nbsp;Users Role";
				document.getElementById("hroomtchr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Home Room Teacher";
				document.getElementById("annTerm").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Annual Term";
				document.getElementById("schEvent").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Events";
				document.getElementById("hldList").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Holidays";
				document.getElementById("exmSch").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Schedule";
				document.getElementById("curactivity").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Co-curriculum";
				document.getElementById("behEval").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Behavioural Evaluation";
				document.getElementById("studMat").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Student Material";
				document.getElementById("attType").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Attendance";
				document.getElementById("payment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Payment";
				document.getElementById("sch_info").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Info";
				document.getElementById("def_com").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Certificate Default Comment";
				document.getElementById("grading").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Grading";
				document.getElementById("spe_reg").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Special Registration";
				document.getElementById("id_card").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Generate ID Card";
				document.getElementById("class_allotment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Period Allotment";
				$('#userAdminCont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addHomeRoomTeacher(){
		
		$("#userAdminCont").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "useraction_hrteacher.action",				
			data : "",
			success : function(response) { 
				document.getElementById("usr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users";
				document.getElementById("usrrole").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users Role";
				document.getElementById("hroomtchr").innerHTML = "<img alt=\"click\" src=\"images/next.gif\" width=\"8px\">&nbsp;Home Room Teacher";
				document.getElementById("annTerm").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Annual Term";
				document.getElementById("schEvent").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Events";
				document.getElementById("hldList").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Holidays";
				document.getElementById("exmSch").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Schedule";
				document.getElementById("curactivity").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Co-curriculum";
				document.getElementById("behEval").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Behavioural Evaluation";
				document.getElementById("studMat").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Student Material";
				document.getElementById("attType").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Attendance";
				document.getElementById("payment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Payment";
				document.getElementById("sch_info").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Info";
				document.getElementById("def_com").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Certificate Default Comment";
				document.getElementById("grading").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Grading";
				document.getElementById("spe_reg").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Special Registration";
				document.getElementById("id_card").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Generate ID Card";
				document.getElementById("class_allotment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Period Allotment";
				$('#userAdminCont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addAnnualTerm(){
		
		$("#userAdminCont").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "annual_term_list.action",				
			data : "",
			success : function(response) { 
				document.getElementById("usr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users";
				document.getElementById("usrrole").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users Role";
				document.getElementById("hroomtchr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Home Room Teacher";
				document.getElementById("annTerm").innerHTML = "<img alt=\"click\" src=\"images/next.gif\" width=\"8px\">&nbsp;Annual Term";
				document.getElementById("schEvent").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Events";
				document.getElementById("hldList").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Holidays";
				document.getElementById("exmSch").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Schedule";
				document.getElementById("curactivity").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Co-curriculum";
				document.getElementById("behEval").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Behavioural Evaluation";
				document.getElementById("studMat").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Student Material";
				document.getElementById("attType").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Attendance";
				document.getElementById("payment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Payment";
				document.getElementById("sch_info").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Info";
				document.getElementById("def_com").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Certificate Default Comment";
				document.getElementById("grading").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Grading";
				document.getElementById("spe_reg").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Special Registration";
				document.getElementById("id_card").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Generate ID Card";
				document.getElementById("class_allotment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Period Allotment";
				$('#userAdminCont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function schoolEvent(){
		
		$("#userAdminCont").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "school_event_list.action",				
			data : "",
			success : function(response) { 
				document.getElementById("usr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users";
				document.getElementById("usrrole").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users Role";
				document.getElementById("hroomtchr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Home Room Teacher";
				document.getElementById("annTerm").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Annual Term";
				document.getElementById("schEvent").innerHTML = "<img alt=\"click\" src=\"images/next.gif\" width=\"8px\">&nbsp;School Events";
				document.getElementById("hldList").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Holidays";
				document.getElementById("exmSch").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Schedule";
				document.getElementById("curactivity").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Co-curriculum";
				document.getElementById("behEval").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Behavioural Evaluation";
				document.getElementById("studMat").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Student Material";
				document.getElementById("attType").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Attendance";
				document.getElementById("payment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Payment";
				document.getElementById("sch_info").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Info";
				document.getElementById("def_com").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Certificate Default Comment";
				document.getElementById("grading").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Grading";
				document.getElementById("spe_reg").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Special Registration";
				document.getElementById("id_card").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Generate ID Card";
				document.getElementById("class_allotment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Period Allotment";
				$('#userAdminCont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function holidayList(){
		
		$("#userAdminCont").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "holiday_list.action",				
			data : "",
			success : function(response) { 
				document.getElementById("usr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users";
				document.getElementById("usrrole").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users Role";
				document.getElementById("hroomtchr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Home Room Teacher";
				document.getElementById("annTerm").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Annual Term";
				document.getElementById("schEvent").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Events";
				document.getElementById("hldList").innerHTML = "<img alt=\"click\" src=\"images/next.gif\" width=\"8px\">&nbsp;Holidays";
				document.getElementById("exmSch").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Schedule";
				document.getElementById("curactivity").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Co-curriculum";
				document.getElementById("behEval").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Behavioural Evaluation";
				document.getElementById("studMat").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Student Material";
				document.getElementById("attType").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Attendance";
				document.getElementById("payment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Payment";
				document.getElementById("sch_info").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Info";
				document.getElementById("def_com").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Certificate Default Comment";
				document.getElementById("grading").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Grading";
				document.getElementById("spe_reg").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Special Registration";
				document.getElementById("id_card").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Generate ID Card";
				document.getElementById("class_allotment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Period Allotment";
				$('#userAdminCont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function examSchedule(){
		
		$("#userAdminCont").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "examschedule_list.action",				
			data : "",
			success : function(response) { 
				document.getElementById("usr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users";
				document.getElementById("usrrole").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users Role";
				document.getElementById("hroomtchr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Home Room Teacher";
				document.getElementById("annTerm").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Annual Term";
				document.getElementById("schEvent").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Events";
				document.getElementById("hldList").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Holidays";
				document.getElementById("exmSch").innerHTML = "<img alt=\"click\" src=\"images/next.gif\" width=\"8px\">&nbsp;Exam Schedule";
				document.getElementById("curactivity").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Co-curriculum";
				document.getElementById("behEval").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Behavioural Evaluation";
				document.getElementById("studMat").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Student Material";
				document.getElementById("attType").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Attendance";
				document.getElementById("payment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Payment";
				document.getElementById("sch_info").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Info";
				document.getElementById("def_com").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Certificate Default Comment";
				document.getElementById("grading").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Grading";
				document.getElementById("spe_reg").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Special Registration";
				document.getElementById("id_card").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Generate ID Card";
				document.getElementById("class_allotment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Period Allotment";
				$('#userAdminCont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function curriculumActivity(){
		
		$("#userAdminCont").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "curriculumactivity_list.action",				
			data : "",
			success : function(response) { 
				document.getElementById("usr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users";
				document.getElementById("usrrole").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users Role";
				document.getElementById("hroomtchr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Home Room Teacher";
				document.getElementById("annTerm").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Annual Term";
				document.getElementById("schEvent").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Events";
				document.getElementById("hldList").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Holidays";
				document.getElementById("exmSch").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Schedule";
				document.getElementById("curactivity").innerHTML = "<img alt=\"click\" src=\"images/next.gif\" width=\"8px\">&nbsp;Co-curriculum";
				document.getElementById("behEval").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Behavioural Evaluation";
				document.getElementById("studMat").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Student Material";
				document.getElementById("attType").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Attendance";
				document.getElementById("payment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Payment";
				document.getElementById("sch_info").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Info";
				document.getElementById("def_com").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Certificate Default Comment";
				document.getElementById("grading").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Grading";
				document.getElementById("spe_reg").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Special Registration";
				document.getElementById("id_card").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Generate ID Card";
				document.getElementById("class_allotment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Period Allotment";
				$('#userAdminCont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addBehaviouralEvaluation(){
		
		$("#userAdminCont").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_list.action",				
			data : "",
			success : function(response) { 
				document.getElementById("usr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users";
				document.getElementById("usrrole").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users Role";
				document.getElementById("hroomtchr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Home Room Teacher";
				document.getElementById("annTerm").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Annual Term";
				document.getElementById("schEvent").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Events";
				document.getElementById("hldList").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Holidays";
				document.getElementById("exmSch").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Schedule";
				document.getElementById("curactivity").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Co-curriculum";
				document.getElementById("behEval").innerHTML = "<img alt=\"click\" src=\"images/next.gif\" width=\"8px\">&nbsp;Behavioural Evaluation";
				document.getElementById("studMat").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Student Material";
				document.getElementById("attType").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Attendance";
				document.getElementById("payment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Payment";
				document.getElementById("sch_info").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Info";
				document.getElementById("def_com").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Certificate Default Comment";
				document.getElementById("grading").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Grading";
				document.getElementById("spe_reg").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Special Registration";
				document.getElementById("id_card").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Generate ID Card";
				document.getElementById("class_allotment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Period Allotment";
				$('#userAdminCont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addStudentMaterial(){
		
		$("#userAdminCont").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "studentMaterial_list.action",				
			data : "",
			success : function(response) { 
				document.getElementById("usr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users";
				document.getElementById("usrrole").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users Role";
				document.getElementById("hroomtchr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Home Room Teacher";
				document.getElementById("annTerm").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Annual Term";
				document.getElementById("schEvent").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Events";
				document.getElementById("hldList").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Holidays";
				document.getElementById("exmSch").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Schedule";
				document.getElementById("curactivity").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Co-curriculum";
				document.getElementById("behEval").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Behavioural Evaluation";
				document.getElementById("studMat").innerHTML = "<img alt=\"click\" src=\"images/next.gif\" width=\"8px\">&nbsp;Student Material";
				document.getElementById("attType").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Attendance";
				document.getElementById("payment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Payment";
				document.getElementById("sch_info").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Info";
				document.getElementById("def_com").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Certificate Default Comment";
				document.getElementById("grading").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Grading";
				document.getElementById("spe_reg").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Special Registration";
				document.getElementById("id_card").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Generate ID Card";
				document.getElementById("class_allotment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Period Allotment";
				$('#userAdminCont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	} 
	
	function attendanceType(){
		
		$("#userAdminCont").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "attendance_attendanceTypeList.action",				
			data : "",
			success : function(response) { 
				document.getElementById("usr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users";
				document.getElementById("usrrole").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users Role";
				document.getElementById("hroomtchr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Home Room Teacher";
				document.getElementById("annTerm").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Annual Term";
				document.getElementById("schEvent").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Events";
				document.getElementById("hldList").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Holidays";
				document.getElementById("exmSch").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Schedule";
				document.getElementById("curactivity").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Co-curriculum";
				document.getElementById("behEval").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Behavioural Evaluation";
				document.getElementById("studMat").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Student Material";
				document.getElementById("attType").innerHTML = "<img alt=\"click\" src=\"images/next.gif\" width=\"8px\">&nbsp;Attendance";
				document.getElementById("payment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Payment";
				document.getElementById("sch_info").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Info";
				document.getElementById("def_com").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Certificate Default Comment";
				document.getElementById("grading").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Grading";
				document.getElementById("spe_reg").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Special Registration";
				document.getElementById("id_card").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Generate ID Card";
				document.getElementById("class_allotment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Period Allotment";
				$('#userAdminCont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addPayment(){
		
		$("#userAdminCont").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "payment_admin.action",				
			data : "",
			success : function(response) { 
				document.getElementById("usr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users";
				document.getElementById("usrrole").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users Role";
				document.getElementById("hroomtchr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Home Room Teacher";
				document.getElementById("annTerm").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Annual Term";
				document.getElementById("schEvent").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Events";
				document.getElementById("hldList").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Holidays";
				document.getElementById("exmSch").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Schedule";
				document.getElementById("curactivity").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Co-curriculum";
				document.getElementById("behEval").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Behavioural Evaluation";
				document.getElementById("studMat").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Student Material";
				document.getElementById("attType").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Attendance";
				document.getElementById("payment").innerHTML = "<img alt=\"click\" src=\"images/next.gif\" width=\"8px\">&nbsp;Payment";
				document.getElementById("sch_info").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Info";
				document.getElementById("def_com").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Certificate Default Comment";
				document.getElementById("grading").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Grading";
				document.getElementById("spe_reg").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Special Registration";
				document.getElementById("id_card").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Generate ID Card";
				document.getElementById("class_allotment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Period Allotment";
				$('#userAdminCont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addSchoolInfo(){
		
		$("#userAdminCont").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "payment_school_info.action",				
			data : "",
			success : function(response) { 
				document.getElementById("usr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users";
				document.getElementById("usrrole").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users Role";
				document.getElementById("hroomtchr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Home Room Teacher";
				document.getElementById("annTerm").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Annual Term";
				document.getElementById("schEvent").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Events";
				document.getElementById("hldList").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Holidays";
				document.getElementById("exmSch").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Schedule";
				document.getElementById("curactivity").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Co-curriculum";
				document.getElementById("behEval").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Behavioural Evaluation";
				document.getElementById("studMat").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Student Material";
				document.getElementById("attType").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Attendance";
				document.getElementById("payment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Payment";
				document.getElementById("sch_info").innerHTML = "<img alt=\"click\" src=\"images/next.gif\" width=\"8px\">&nbsp;School Info";
				document.getElementById("def_com").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Certificate Default Comment";
				document.getElementById("grading").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Grading";
				document.getElementById("spe_reg").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Special Registration";
				document.getElementById("id_card").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Generate ID Card";
				document.getElementById("class_allotment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Period Allotment";
				$('#userAdminCont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function certDefaultComment(){
		
		$("#userAdminCont").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "cert_default_comment.action",				
			data : "",
			success : function(response) { 
				document.getElementById("usr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users";
				document.getElementById("usrrole").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users Role";
				document.getElementById("hroomtchr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Home Room Teacher";
				document.getElementById("annTerm").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Annual Term";
				document.getElementById("schEvent").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Events";
				document.getElementById("hldList").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Holidays";
				document.getElementById("exmSch").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Schedule";
				document.getElementById("curactivity").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Co-curriculum";
				document.getElementById("behEval").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Behavioural Evaluation";
				document.getElementById("studMat").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Student Material";
				document.getElementById("attType").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Attendance";
				document.getElementById("payment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Payment";
				document.getElementById("sch_info").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Info";
				document.getElementById("def_com").innerHTML = "<img alt=\"click\" src=\"images/next.gif\" width=\"8px\">&nbsp;Certificate Default Comment";
				document.getElementById("grading").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Grading";
				document.getElementById("spe_reg").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Special Registration";
				document.getElementById("id_card").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Generate ID Card";
				document.getElementById("class_allotment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Period Allotment";
				$('#userAdminCont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function examGrading(){
		
		$("#userAdminCont").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "exam_grading.action",				
			data : "",
			success : function(response) { 
				document.getElementById("usr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users";
				document.getElementById("usrrole").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users Role";
				document.getElementById("hroomtchr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Home Room Teacher";
				document.getElementById("annTerm").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Annual Term";
				document.getElementById("schEvent").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Events";
				document.getElementById("hldList").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Holidays";
				document.getElementById("exmSch").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Schedule";
				document.getElementById("curactivity").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Co-curriculum";
				document.getElementById("behEval").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Behavioural Evaluation";
				document.getElementById("studMat").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Student Material";
				document.getElementById("attType").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Attendance";
				document.getElementById("payment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Payment";
				document.getElementById("sch_info").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Info";
				document.getElementById("def_com").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Certificate Default Comment";
				document.getElementById("grading").innerHTML = "<img alt=\"click\" src=\"images/next.gif\" width=\"8px\">&nbsp;Exam Grading";
				document.getElementById("spe_reg").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Special Registration";
				document.getElementById("id_card").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Generate ID Card";
				document.getElementById("class_allotment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Period Allotment";
				$('#userAdminCont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function specialRegistration(){
		
		$("#userAdminCont").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "speical_registration.action",				
			data : "",
			success : function(response) { 
				document.getElementById("usr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users";
				document.getElementById("usrrole").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users Role";
				document.getElementById("hroomtchr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Home Room Teacher";
				document.getElementById("annTerm").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Annual Term";
				document.getElementById("schEvent").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Events";
				document.getElementById("hldList").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Holidays";
				document.getElementById("exmSch").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Schedule";
				document.getElementById("curactivity").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Co-curriculum";
				document.getElementById("behEval").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Behavioural Evaluation";
				document.getElementById("studMat").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Student Material";
				document.getElementById("attType").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Attendance";
				document.getElementById("payment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Payment";
				document.getElementById("sch_info").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Info";
				document.getElementById("def_com").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Certificate Default Comment";
				document.getElementById("grading").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Grading";
				document.getElementById("spe_reg").innerHTML = "<img alt=\"click\" src=\"images/next.gif\" width=\"8px\">&nbsp;Special Registration";
				document.getElementById("id_card").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Generate ID Card";
				document.getElementById("class_allotment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Period Allotment";
				$('#userAdminCont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function generateIDCard(){
		
		$("#userAdminCont").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "generate_id_card.action",				
			data : "",
			success : function(response) { 
				document.getElementById("usr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users";
				document.getElementById("usrrole").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users Role";
				document.getElementById("hroomtchr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Home Room Teacher";
				document.getElementById("annTerm").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Annual Term";
				document.getElementById("schEvent").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Events";
				document.getElementById("hldList").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Holidays";
				document.getElementById("exmSch").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Schedule";
				document.getElementById("curactivity").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Co-curriculum";
				document.getElementById("behEval").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Behavioural Evaluation";
				document.getElementById("studMat").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Student Material";
				document.getElementById("attType").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Attendance";
				document.getElementById("payment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Payment";
				document.getElementById("sch_info").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Info";
				document.getElementById("def_com").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Certificate Default Comment";
				document.getElementById("grading").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Grading";
				document.getElementById("spe_reg").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Special Registration";
				document.getElementById("id_card").innerHTML = "<img alt=\"click\" src=\"images/next.gif\" width=\"8px\">&nbsp;Generate ID Card";
				document.getElementById("class_allotment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Period Allotment";
				$('#userAdminCont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function classAllotment(){
		
		$("#userAdminCont").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "class_allotment.action",				
			data : "",
			success : function(response) { 
				document.getElementById("usr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users";
				document.getElementById("usrrole").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Users Role";
				document.getElementById("hroomtchr").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Home Room Teacher";
				document.getElementById("annTerm").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Annual Term";
				document.getElementById("schEvent").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Events";
				document.getElementById("hldList").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Holidays";
				document.getElementById("exmSch").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Schedule";
				document.getElementById("curactivity").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Co-curriculum";
				document.getElementById("behEval").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Behavioural Evaluation";
				document.getElementById("studMat").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Student Material";
				document.getElementById("attType").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Attendance";
				document.getElementById("payment").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Payment";
				document.getElementById("sch_info").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;School Info";
				document.getElementById("def_com").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Certificate Default Comment";
				document.getElementById("grading").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Exam Grading";
				document.getElementById("spe_reg").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Special Registration";
				document.getElementById("id_card").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Generate ID Card";
				document.getElementById("class_allotment").innerHTML = "<img alt=\"click\" src=\"images/next.gif\" width=\"8px\">&nbsp;Period Allotment";
				$('#userAdminCont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}