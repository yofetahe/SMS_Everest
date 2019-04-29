/**
 * 
 */

/*=========================== REPORT GENERAL ===========================*/
			
	/*----- REPORT'S TABS -----*/
	
	var srpt = 0;
	var trpt = 0;
	var frpt = 0;
	var edurpt = 0;
	var frmrpt = 0;
	
	//Student Report Tab JS
	function studentReport(){
		if(srpt == 0){
			showStudetnReport();			
		} else if(srpt == 1){
			hideStudetnReport()
		}
	}	
	function showStudetnReport(){
		srpt = 1;
		trpt = 0;
		frpt = 0;
		edurpt = 0;
		frmrpt = 0;
		$('#exam_report_titles').hide('slow');
		$('#eduoff_report_titles').hide('slow');
		$('#stud_report_titles').show('slow');
		$('#finance_report_titles').hide('slow');
		$('#format_titles').hide('slow');
	}	
	function hideStudetnReport(){
		srpt = 0;
		$('#stud_report_titles').hide('slow');
	}	
	
	//Exam Report Tab JS
	function examReport(){
		if(trpt == 0){
			showExamReport();			
		} else if(trpt == 1){
			hideExamReport()
		}
	}	
	function showExamReport(){
		trpt = 1;
		srpt = 0;
		frpt = 0;
		edurpt = 0;
		frmrpt = 0;
		$('#stud_report_titles').hide('slow');
		$('#eduoff_report_titles').hide('slow');
		$('#exam_report_titles').show('slow');
		$('#finance_report_titles').hide('slow');
		$('#format_titles').hide('slow');
	}	
	function hideExamReport(){
		trpt = 0;
		$('#exam_report_titles').hide('slow');
	}	
	
	//Education office Report Tab JS
	function eduOfficeReport(){
		if(edurpt == 0){
			showEduOfficeReport();			
		} else if(edurpt == 1){
			hideEduOfficeReport()
		}
	}	
	function showEduOfficeReport(){
		edurpt = 1;
		trpt = 0;
		srpt = 0;
		frpt = 0;
		frmrpt = 0;
		$('#stud_report_titles').hide('slow');
		$('#exam_report_titles').hide('slow');
		$('#eduoff_report_titles').show('slow');
		$('#finance_report_titles').hide('slow');
		$('#format_titles').hide('slow');
	}	
	function hideEduOfficeReport(){
		edurpt = 0;
		$('#eduoff_report_titles').hide('slow');
	}
	
	//Finance Report Tab JS
	function financeReport(){
		if(frpt == 0){
			showFinanceReport();			
		} else if(frpt == 1){
			hideFinanceReport();
		}
	}	
	function showFinanceReport(){
		frpt = 1;
		trpt = 0;
		srpt = 0;
		edurpt = 0;
		frmrpt = 0;
		$('#stud_report_titles').hide('slow');
		$('#exam_report_titles').hide('slow');
		$('#eduoff_report_titles').hide('slow');
		$('#finance_report_titles').show('slow');
		$('#format_titles').hide('slow');
	}	
	function hideFinanceReport(){
		frpt = 0;
		$('#finance_report_titles').hide('slow');
	}
	
	//Format Report Tab JS
	function formatReport(){
		if(frmrpt == 0){
			showFormatReport();			
		} else if(frmrpt == 1){
			hideFormatReport();
		}
	}
	function showFormatReport(){		
		frmrpt = 1;
		frpt = 0;
		trpt = 0;
		srpt = 0;
		edurpt = 0;
		$('#stud_report_titles').hide('slow');
		$('#exam_report_titles').hide('slow');
		$('#eduoff_report_titles').hide('slow');
		$('#finance_report_titles').hide('slow');
		$('#format_titles').show('slow');
	}	
	function hideFormatReport(){
		frmrpt = 0;
		$('#format_titles').hide('slow');
	}
	
	/*----- REPORT CATEGORY PAGES -----*/

	function getExamProgram(){
		
		$("#report").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "report_examschedule.action",				
			data : "",
			success : function(response) {
				$('#report').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getEachClassQuarterMarkList(){
		
		$("#report").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "report_quarterMarkList.action",				
			data : "",
			success : function(response) {
				$('#report').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getStudentsRoster(){
		
		$("#report").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "report_studentroster.action",				
			data : "",
			success : function(response) {
				$('#report').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getStudentsRosterFullYear(){
		
		$("#report").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "report_studentrosterfullyear.action",				
			data : "",
			success : function(response) {
				$('#report').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getStudentsTranscript(){
		
		$("#report").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "report_studenttranscript.action",				
			data : "",
			success : function(response) {
				$('#report').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getTopNsStudPerClass(){
		
		$("#report").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "report_topNsStudent.action",				
			data : "",
			success : function(response) {
				$('#report').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getBelowAverageStudentsReport(){
		
		$("#report").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "report_belowAverageStudents.action",				
			data : "",
			success : function(response) {
				$('#report').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getTopTwentyStudPerClass(){
		
		$("#report").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "report_top20student.action",				
			data : "",
			success : function(response) {
				$('#report').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getStudMarkPlanAndImp(){
		
		$("#report").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "report_studMarkPlanAndImp.action",				
			data : "",
			success : function(response) {
				$('#report').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getStudMarkPerSemester(){
		
		$("#report").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "report_studMarkListTemp.action",				
			data : "",
			success : function(response) {
				$('#report').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getStudMarkListFormat(){
		
		$("#report").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "report_studMarkListFormatTemp.action",				
			data : "",
			success : function(response) {
				$('#report').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function generateMarkListFormat(){
		alert();
	}
	
	function getCheckInsertedExamResult(){
		
		$("#report").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "report_checkInsertedExamResult.action",				
			data : "",
			success : function(response) {
				$('#report').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}

	function getStudentAttendanceFormat(){
		
		$("#report").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "report_studAttendanceFormatTemp.action",				
			data : "",
			success : function(response) {
				$('#report').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	
	
	
	
	
	/*========================== WORK FORMATS ==========================*/
	
	/*----- FORMATS TEMPLATE PAGE -----*/
	
	var filter = 'No';
	function filterStudentForOffice(){
		filter = 'Yes';
		document.getElementById("officeUse").innerHTML = "<img alt=\"check\" src=\"images/checked.png\" onclick=\"unfilterStudentForOffice()\" style=\"cursor: pointer;\" width=\"16px\">";
	}
	
	function unfilterStudentForOffice(){
		filter = 'No';
		document.getElementById("officeUse").innerHTML = "<img alt=\"check\" src=\"images/check_box.png\" onclick=\"filterStudentForOffice()\" style=\"cursor: pointer;\" width=\"16px\">";
	}

	function generateStudentMarkListFormat(){
		
		var at_id = $('#at_id').val();
		var cl_id = $('#cl_id').val();
		var cd_id = $('#cd_id').val();
		var sub_id = $('#subcl_id').val();
		var ac_year = $('#ac_year').val();
		
		if(at_id == "0"){ 
			document.getElementById("qrtListError").innerHTML = "Quarter Required.";
		} else if(cl_id == "0"){
			document.getElementById("clListError").innerHTML = "Class Required.";
		} else if(cd_id == "0"){
			document.getElementById("clGrdListError").innerHTML = "Detail Required.";
		} else if(sub_id == "0"){
			document.getElementById("subListError").innerHTML = "Subject Required.";
		} else {
		
			$("#markList").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "report_getStudentMarkListFormat.action",				
				data : "at_id=" + at_id + "&class_bean.cl_id=" + cl_id + "&cdetail_bean.cd_id=" + cd_id + "&sub_bean.subcl_id=" + sub_id + "&stud_bean.ac_year=" + ac_year,
				success : function(response) {
					$('#markList').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function classRoomList(at_id){
		
		$("#classList").html("<img align=\"center\" src=\"images/30.GIF\"/>");
		$.ajax({
			type : "POST",
			url : "report_classlist.action",				
			data : "at_id=" + at_id,
			success : function(response) {
				
				$('#classList').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- CLASS LIST PAGE -----*/
	
	function gradeDetailListForMarkListFormat(cl_id){
		
		document.getElementById('markList').innerHTML = "";
		
		$("#grdList").html("<img align=\"center\" src=\"images/30.GIF\"/>");
		$.ajax({
			type : "POST",
			url : "report_grdlist.action",				
			data : "cl_id=" + cl_id,
			success : function(response) {
				
//	 			document.getElementById('grdList').innerHTML = "";
//	 			document.getElementById('subList').innerHTML = "";
//	 			document.getElementById('examList').innerHTML = "";
//	 			document.getElementById('studList').innerHTML = "";
				
				$('#grdList').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*========================== TOP FIVE STUD PER CLASS ==========================*/
	
	/*----- TOP FIVE PER CLASS TEMP PAGE -----*/
	
	var selectedQrtList = new Array();
	var selectedGradeList = new Array();
	var overAllTopRanking = "No";
	
	function selectQuarterForTopRankingReport(at_id){
		
		var size = selectedQrtList.length;
		
		var count = 0;
		var index = -1;
		
		for(var i = 0; i < size; i++){
			
			if(selectedQrtList[i] == at_id){					
				count++;
			}
		
			if(count > 0){
				index = i;
				break;
			}		
		}
		
		var s = selectedQrtList.length;
		
		if(count == 0){
			selectedQrtList[size] = new Array(at_id);			
		}
		if(count > 0) {
			selectedQrtList.splice(i, 1);
		}
	}
	
	function selectClassIdForTopRankingReport(cl_id){
		
		var size = selectedGradeList.length;
		
		var count = 0;
		var index = -1;
		
		for(var i = 0; i < size; i++){
			
			if(Number(selectedGradeList[i]) == Number(cl_id)){				
				count++;
			}
		
			if(count > 0){				
				index = i;
				break;
			}		
		}
		
		var s = selectedGradeList.length;
		
		if(count == 0){			
			selectedGradeList[s] = new Array(cl_id);
		} 
		if(count > 0) {
			selectedGradeList.splice(index, 1);	
		}
	}
	
	function overAllTopRankingReport(){
	
		if(overAllTopRanking == "No"){
			
			overAllTopRanking = "Yes";
			
		} else {
			
			overAllTopRanking = "No";
		}
	}
	
	function getTopNsRankStudentsList(){
		
		var counter = 0;
		
		var ac_year = $('#ac_year').val();
		var topof = $('#topof').val();
		
		if(topof == ""){ 
			document.getElementById("topofError").innerHTML = "Top of value is required.";
			counter++;
		} 
		if(selectedQrtList.length == 0){
			document.getElementById("qrtListError").innerHTML = "Quarter is required.";
			counter++;
		}
		if(selectedGradeList.length == 0){
			document.getElementById("gradeError").innerHTML = "Grade is required.";
			counter++;
		} 
		if(counter == 0) {
			
			$("#topFiveStud").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/> <br/> <span style=\"color: #3d6e9f;\"> It is processing a huge data, it might took more than a minute. </span> </div>");
			$.ajax({
				type : "POST",
				url : "report_getTopNsStudent.action",				
				data : "stud_bean.ac_year=" + ac_year + "&selectedGradeList=" + selectedGradeList + "&selectedQuarterList=" + selectedQrtList + "&topOf=" + topof + "&overAllTopRanking=" + overAllTopRanking,
				success : function(response) {
					$('#topFiveStud').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}	
	
	/*========================== TOP 20 STUDENTS ==========================*/
	
	/*----- TOP 20 STUDENTS TEMP -----*/
	
	function getTopTwentyStudentsPerClass(){
		
		var ac_year = $('#ac_year').val();
		var at_id = $('#at_id').val();
		
		if(at_id == "0"){ 
			document.getElementById("qrtListError").innerHTML = "Quarter Required.";
		} else {
			$("#topFiveStud").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "report_gettop20student.action",				
				data : "at_id=" + at_id + "&stud_bean.ac_year=" + ac_year,
				success : function(response) {
					$('#topFiveStud').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	
	/*========================== STUDENTS ROSTER FULL YEAR ==========================*/
	
	/*----- STUDENT ROSTER FULL YEAR TMEPLATE PAGE -----*/
	
//	var filter = 'No';
//	function filterStudentForOffice(){
//		filter = 'Yes';
//		document.getElementById("officeUse").innerHTML = "<img alt=\"check\" src=\"images/checked.png\" onclick=\"unfilterStudentForOffice()\" style=\"cursor: pointer;\" width=\"16px\">";
//	}
//	
//	function unfilterStudentForOffice(){
//		filter = 'No';
//		document.getElementById("officeUse").innerHTML = "<img alt=\"check\" src=\"images/check_box.png\" onclick=\"filterStudentForOffice()\" style=\"cursor: pointer;\" width=\"16px\">";
//	}
	
	function generateFullYearStudentRoster(){
		
		var cl_id = $('#cl_id').val();
		var cd_id = $('#cd_id').val();
		var ac_year = $('#ac_year').val();
		
		if(cl_id == "0"){
			document.getElementById("clListError").innerHTML = "Class Required.";
		} else if(cd_id == "0"){
			document.getElementById("clGrdListError").innerHTML = "Detail Required.";
		} else {
			
			$("#studRoster").html("<div width=\"100%\" align=\"center\"> <img src=\"images/loader.gif\"/> <br/> <img src=\"images/information.jpeg\" width=\"20px;\"/> <span style=\"color: GREEN\"> It is processing huge data, it might take 5 to 8 min.</span> </div>");
			$.ajax({
				type : "POST",
				url : "report_getFullYearStudentRosterList.action",				
				data : "class_bean.cl_id=" + cl_id + "&cdetail_bean.cd_id=" + cd_id + "&stud_bean.ac_year=" + ac_year + "&eduoff_filter_status=" + filter,
				success : function(response) {
					$('#studRoster').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function gradeDetailList(cl_id){
		
		document.getElementById('studRoster').innerHTML = "";
		
		var at_id = $('#at_id').val();
		
		$("#grdList").html("<img align=\"center\" src=\"images/30.GIF\"/>");
		$.ajax({
			type : "POST",
			url : "report_studroster_grdlist.action",				
			data : "at_id=" + at_id + "&cl_id=" + cl_id,
			success : function(response) {
				
				$('#grdList').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
		
	/*========================== STUDENT ROSTER ==========================*/
	
	/*----- STUDENT ROSTER TEMPLATE PAGE -----*/
	
	function classRoomList(at_id){
		
		$("#classList").html("<img align=\"center\" src=\"images/30.GIF\"/>");
		$.ajax({
			type : "POST",
			url : "report_studroster_classlist.action",				
			data : "at_id=" + at_id,
			success : function(response) {
				
				$('#classList').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
//	var filter = 'No';
//	function filterStudentForOffice(){
//		filter = 'Yes';
//		document.getElementById("officeUse").innerHTML = "<img alt=\"check\" src=\"images/checked.png\" onclick=\"unfilterStudentForOffice()\" style=\"cursor: pointer;\" width=\"16px\">";
//	}
//	
//	function unfilterStudentForOffice(){
//		filter = 'No';
//		document.getElementById("officeUse").innerHTML = "<img alt=\"check\" src=\"images/check_box.png\" onclick=\"filterStudentForOffice()\" style=\"cursor: pointer;\" width=\"16px\">";
//	}
	
	function generateStudentRoster(){
		
		var ac_year = $('#ac_year').val();
		var at_id = $('#at_id').val();
		var cl_id = $('#cl_id').val();
		var cd_id = $('#cd_id').val();
		
		if(at_id == "0"){ 
			document.getElementById("qrtListError").innerHTML = "Quarter Required.";
		} else if(cl_id == "0"){
			document.getElementById("clListError").innerHTML = "Class Required.";
		} else if(cd_id == "0"){
			document.getElementById("clGrdListError").innerHTML = "Detail Required.";
		} else {
			
			$("#studRoster").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "report_getStudentRosterList.action",				
				data : "at_id=" + at_id + "&class_bean.cl_id=" + cl_id + "&cdetail_bean.cd_id=" + cd_id + "&stud_bean.ac_year=" + ac_year + "&eduoff_filter_status=" + filter,
				success : function(response) {
					$('#studRoster').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	/*----- STUDENT ROSER FOR EACH CLASS PAGE -----*/
	
	function generateRoster(at_id, cl_id, cd_id, ac_year){
		
		$("#studRoster").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "report_getPDFStudentRosterList.action",				
			data : "at_id=" + at_id + "&class_bean.cl_id=" + cl_id + "&cdetail_bean.cd_id=" + cd_id + "&stud_bean.ac_year=" + ac_year + "&eduoff_filter_status=" + filter,
			success : function(response) {
				$('#studRoster').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- CLASS LIST PAGE -----*/
	
	function gradeDetailListForStudRoster(cl_id){
		
		document.getElementById('studRoster').innerHTML = "";
		
		var at_id = $('#at_id').val();
		
		$("#grdList").html("<img align=\"center\" src=\"images/30.GIF\"/>");
		$.ajax({
			type : "POST",
			url : "report_studroster_grdlist.action",				
			data : "at_id=" + at_id + "&cl_id=" + cl_id,
			success : function(response) {
				
				$('#grdList').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*========================== STUDENT TRANSCRIPT ==========================*/
	
	/*----- STUDENT TRANSCRIPT TEMPLATE PAGE -----*/
	
//	var filter = 'No';
//	function filterStudentForOffice(){
//		filter = 'Yes';
//		document.getElementById("officeUse").innerHTML = "<img alt=\"check\" src=\"images/checked.png\" onclick=\"unfilterStudentForOffice()\" style=\"cursor: pointer;\" width=\"16px\">";
//	}
//	
//	function unfilterStudentForOffice(){
//		filter = 'No';
//		document.getElementById("officeUse").innerHTML = "<img alt=\"check\" src=\"images/check_box.png\" onclick=\"filterStudentForOffice()\" style=\"cursor: pointer;\" width=\"16px\">";
//	}
	
	function generateAllStudentTransacript(){
		
		var cl_id = $('#cl_id').val();
		var cd_id = $('#cd_id').val();
		var ac_year = $('#ac_year').val();
		
		if(cl_id == "0"){
			document.getElementById("clListError").innerHTML = "Class Required.";
		} else if(cd_id == "0"){
			document.getElementById("clGrdListError").innerHTML = "Detail Required.";
		} else {
			
// 			$("#studRoster").html("<div width=\"100%\" align=\"center\"> <img src=\"images/loader.gif\"/> <br/> <img src=\"images/information.jpeg\" width=\"20px;\"/> <span style=\"color: GREEN\"> It is processing huge data, it might take 5 to 8 min.</span> </div>");
// 			$.ajax({
// 				type : "POST",
// 				url : "report_getFullYearStudentRosterList.action",				
// 				data : "class_bean.cl_id=" + cl_id + "&cdetail_bean.cd_id=" + cd_id + "&stud_bean.ac_year=" + ac_year + "&eduoff_filter_status=" + filter,
// 				success : function(response) {
// 					$('#studRoster').html(response);
// 				},
// 				error : function(e) {
// 					alert('Error: ' + e);
// 				}
// 			});
		}
	}
	
	function gradeDetailListForTranscript(cl_id){
		
		document.getElementById('studTranscriptContent').innerHTML = "";
		
		
		$("#grdList").html("<img align=\"center\" src=\"images/30.GIF\"/>");
		$.ajax({
			type : "POST",
			url : "report_studroster_grdlist.action",				
			data : "cl_id=" + cl_id,
			success : function(response) {
				
				$('#grdList').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getStudentLisPerClassDetailForTranscript(){
		
		var ac_year = $('#ac_year').val();
		var cl_id = $('#cl_id').val();
		var cd_id = $('#cd_id').val();		
		
		if(cl_id == "0"){
			
			document.getElementById("clListError").innerHTML = "Class Required.";
			
		} else if(cd_id == "0"){
			
			document.getElementById("clGrdListError").innerHTML = "Detail Required.";
			
		} else {
			
			$("#studTranscriptContent").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "report_getStudentLisPerClassDetail.action",				
				data : "stud_bean.ac_year=" + ac_year + "&stud_bean.class_id=" + cl_id + "&stud_bean.cd_id=" + cd_id,
				success : function(response) {					
					$('#studTranscriptContent').html(response);				
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	/*----- STUDENT LIST PER GRADE -----*/
	
	function getStudentTranscript(si_id){
		
		var ac_year = $('#ac_year').val();
		var cl_id = $('#cl_id').val();
		var cd_id = $('#cd_id').val();
		
		$("#transcriptContent").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "report_getStudentTranscript.action",				
			data : "stud_bean.si_id=" + si_id + "&stud_bean.ac_year=" + ac_year + "&stud_bean.class_id=" + cl_id + "&cdetail_bean.cd_id=" + cd_id,
			success : function(response) {					
				$('#transcriptContent').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function selectClassForTrascript(cl_id){
		alert(cl_id);
	}
	
	/*========================== STUDENT ATTENDANCE ==========================*/
	
	/*----- STUD ATTENDANCE TEMPLATE PAGE -----*/
	
	function gradeDetailListForAttendance(cl_id){
		
		document.getElementById('studAttendanceFormat').innerHTML = "";
		
		var at_id = $('#at_id').val();
		
		$("#grdList").html("<img align=\"center\" src=\"images/30.GIF\"/>");
		$.ajax({
			type : "POST",
			url : "report_studroster_grdlist.action",				
			data : "at_id=" + at_id + "&cl_id=" + cl_id,
			success : function(response) {
				
				$('#grdList').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}

//	var filter = 'No';
//	function filterStudentForOffice(){
//		filter = 'Yes';
//		document.getElementById("officeUse").innerHTML = "<img alt=\"check\" src=\"images/checked.png\" onclick=\"unfilterStudentForOffice()\" style=\"cursor: pointer;\" width=\"16px\">";
//	}
//
//	function unfilterStudentForOffice(){
//		filter = 'No';
//		document.getElementById("officeUse").innerHTML = "<img alt=\"check\" src=\"images/check_box.png\" onclick=\"filterStudentForOffice()\" style=\"cursor: pointer;\" width=\"16px\">";
//	}

	function getStudentAttendanceFormatPerClass(){
		
		var ac_year = $('#ac_year').val();
		var cl_id = $('#cl_id').val();
		var cd_id = $('#cd_id').val();
				
		if(cl_id == "0"){
			document.getElementById("clListError").innerHTML = "Class Required.";
		} else if(cd_id == "0"){
			document.getElementById("clGrdListError").innerHTML = "Detail Required.";
		} else {
			
			$("#studAttendanceFormat").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "report_getStudentAttendanceForm.action",				
				data : "class_bean.cl_id=" + cl_id + "&cdetail_bean.cd_id=" + cd_id + "&stud_bean.ac_year=" + ac_year + "&eduoff_filter_status=" + filter,
				success : function(response) {
					$('#studAttendanceFormat').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	/*----- STUD ATTENDANCE FORMAT -----*/
	
	function generateStudentAttendanceFormat(cl_id, cd_id, ac_year, eduoff_filter_status){
		
		$("#studAttendanceFormat").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "report_getPDFStudentAttendanceForm.action",				
			data : "class_bean.cl_id=" + cl_id + "&cdetail_bean.cd_id=" + cd_id + "&stud_bean.ac_year=" + ac_year + "&eduoff_filter_status=" + eduoff_filter_status,
			success : function(response) {
				$('#studAttendanceFormat').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
		
	/*========================== QUARTER MARK LIST ==========================*/
	
	/*----- QUARTER MARK LIST TEMP PAGE -----*/
	
//	var filter = 'No';
//	function filterStudentForOffice(){
//		filter = 'Yes';
//		document.getElementById("officeUse").innerHTML = "<img alt=\"check\" src=\"images/checked.png\" onclick=\"unfilterStudentForOffice()\" style=\"cursor: pointer;\" width=\"16px\">";
//	}
//	
//	function unfilterStudentForOffice(){
//		filter = 'No';
//		document.getElementById("officeUse").innerHTML = "<img alt=\"check\" src=\"images/check_box.png\" onclick=\"filterStudentForOffice()\" style=\"cursor: pointer;\" width=\"16px\">";
//	}

	function generateQuarterMarkList(){
		
		var at_id = $('#at_id').val();
		var cl_id = $('#cl_id').val();
		var cd_id = $('#cd_id').val();
		var sub_id = $('#subcl_id').val();
		var ac_year = $('#ac_year').val();
		
		if(at_id == "0"){ 
			document.getElementById("qrtListError").innerHTML = "Quarter Required.";
		} else if(cl_id == "0"){
			document.getElementById("clListError").innerHTML = "Class Required.";
		} else if(cd_id == "0"){
			document.getElementById("clGrdListError").innerHTML = "Detail Required.";
		} else if(sub_id == "0"){
			document.getElementById("subListError").innerHTML = "Subject Required.";
		} else {
		
			$("#markList").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "report_getClassQuarterMarkList.action",				
				data : "at_id=" + at_id + "&class_bean.cl_id=" + cl_id + "&cdetail_bean.cd_id=" + cd_id + "&sub_bean.sub_id=" + sub_id + "&stud_bean.ac_year=" + ac_year + "&eduoff_filter_status=" + filter,
				success : function(response) {
					$('#markList').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function classRoomListForQrtMarkList(at_id){
		
		$("#classList").html("<img align=\"center\" src=\"images/30.GIF\"/>");
		$.ajax({
			type : "POST",
			url : "report_classlist.action",				
			data : "at_id=" + at_id,
			success : function(response) {
				
				$('#classList').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- QUARTER MARK LIST EACH CLASS PAGE -----*/
	
	function generateStudentMarkList(at_id, cl_id, cd_id, sub_id, ac_year){
		
		$("#markList").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "report_getPDFClassQuarterMarkList.action",				
			data : "at_id=" + at_id + "&class_bean.cl_id=" + cl_id + "&cdetail_bean.cd_id=" + cd_id + "&sub_bean.sub_id=" + sub_id + "&stud_bean.ac_year=" + ac_year + "&eduoff_filter_status=" + filter,
			success : function(response) {
				$('#markList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});		
	}

	/*----- GRADE DETAIL LIST PAGE -----*/
	
	function subjectListForQrtMarkList(cd_id){
		
		document.getElementById('markList').innerHTML = "";
		
		var ac_year = $('#ac_year').val();
		var at_id = $('#at_id').val();
		var cl_id = $('#cl_id').val();
		
		$("#subList").html("<img align=\"center\" src=\"images/30.GIF\"/>");
		$.ajax({
			type : "POST",
			url : "report_clsublist.action",				
			data : "at_id=" + at_id + "&cl_id=" + cl_id + "&cd_id=" + cd_id + "&academic_year=" + ac_year,
			success : function(response) {
						
//	 			document.getElementById('subList').innerHTML = "";
//	 			document.getElementById('examList').innerHTML = "";
//	 			document.getElementById('studList').innerHTML = "";	
				
				$('#subList').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- CLASS LIST PAGE -----*/
	
	function gradeDetailListForQrtMarkList(cl_id){
		
		document.getElementById('markList').innerHTML = "";
		
		var at_id = $('#at_id').val();
		
		$("#grdList").html("<img align=\"center\" src=\"images/30.GIF\"/>");
		$.ajax({
			type : "POST",
			url : "report_grdlist.action",				
			data : "at_id=" + at_id + "&cl_id=" + cl_id,
			success : function(response) {
				
//	 			document.getElementById('grdList').innerHTML = "";
//	 			document.getElementById('subList').innerHTML = "";
//	 			document.getElementById('examList').innerHTML = "";
//	 			document.getElementById('studList').innerHTML = "";
				
				$('#grdList').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
		
	/*========================== FINANCE ==========================*/
	
	function getUnpaidStudentList(){
		$("#report").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "report_unpaidStudentListTemp.action",				
			data : "",
			success : function(response) {
				$('#report').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
//	function getPaidReceipt(){
//		$("#report").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
//		$.ajax({
//			type : "POST",
//			url : "report_paidReceiptTemp.action",				
//			data : "",
//			success : function(response) {
//				$('#report').html(response);
//			},
//			error : function(e) {
//				alert('Error: ' + e);
//			}
//		});
//	}
	
	function getPaymentCollection(){
		$("#report").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "report_paymentCollectionTemp.action",				
			data : "",
			success : function(response) {
				$('#report').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function selectCollectionReportType(col_rpt_type){
		
		if(col_rpt_type == 1){
			document.getElementById("col_date").style.display = "block";
			document.getElementById("col_month").style.display = "none";
			document.getElementById("col_report").innerHTML = "";
		}
		if(col_rpt_type == 2){
			document.getElementById("col_date").style.display = "none";
			document.getElementById("col_month").style.display = "block";
			document.getElementById("col_report").innerHTML = "";
		}
		if(col_rpt_type == 0){
			document.getElementById("col_date").style.display = "none";
			document.getElementById("col_month").style.display = "none";
			document.getElementById("col_report").innerHTML = "";
		}
	}
	
	/*----- paid receipt - paid receipt template page -----*/
	
	var prev_clid = null;
	function gradeSelectedForPaidReceipt(cl_id, clname){
		$("#student_list").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "report_class_detail.action",				
			data : "class_id=" + cl_id + "&class_name=" + clname,
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
	
	/*----- paid receipt folder - payment class detail page -----*/
	
	var prev_cdid = null;
	function classDetailForPaidReceipt(clid, cd_id){
		
		$("#student_list_perdetial").html("<div width=\"100%\" align=\"center\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "report_pergrade_studlist.action",				
			data : "class_id=" + clid + "&cd_id=" + cd_id,
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
	
	/*----- paid receipt folder - payment material student list page -----*/
	
	function selectedMaterial(month, siid, fname, mname, gname){
		var clid = $('#clid').val();
		
		$("#student_list_payment").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
		$.ajax({
			type : "POST",
			url : "payment_form_mat.action",				
			data : "si_id=" + siid + "&month=" + month + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&payment_type=" + 4 + "&class_id=" + clid,
			success : function(response) {
				$('#student_list_payment').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function displayMaterialPaymentForm(siid, fname, mname, gname, clid, pt_id){
		$("#student_list_payment").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
		$.ajax({
			type : "POST",
			url : "payment_form_mat.action",				
			data : "si_id=" + siid + "&pt_id=" + pt_id + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&payment_type=" + pt_id + "&class_id=" + clid,
			success : function(response) {
				$('#student_list_payment').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- paid receipt folder - payment student list page -----*/
	
	function selectedMonth(month, siid, fname, mname, gname, pt_id){
		var clid = $('#clid').val();
		
		$("#student_list_payment").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
		$.ajax({
			type : "POST",
			url : "payment_form_edu.action",				
			data : "si_id=" + siid + "&month=" + month + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&payment_type=" + 1 + "&class_id=" + clid,
			success : function(response) {
				$('#student_list_payment').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- paid receipt folder - payment student page -----*/
	
	function eduPayment(clid, cd_id){
		$("#student_list_payment").html("<div width=\"100%\" align=\"center\"><img src=\"images/loader.gif\"/></div>");		
		$.ajax({
			type : "POST",
			url : "report_student_list.action",				
			data : "class_id=" + clid + "&pt_id=1" + "&cd_id=" + cd_id,
			success : function(response) {
				document.getElementById("eduPay").style.backgroundColor = '#3d6e9f';
				document.getElementById("eduPay").style.color = '#fff';
				
				document.getElementById("tutPay").style.backgroundColor = 'transparent';
				document.getElementById("tutPay").style.color = '#3d6e9f';
				
				document.getElementById("sumPay").style.backgroundColor = 'transparent';
				document.getElementById("sumPay").style.color = '#3d6e9f';
				
				document.getElementById("matPay").style.backgroundColor = 'transparent';
				document.getElementById("matPay").style.color = '#3d6e9f';
				
				$('#student_list_payment').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function tutPayment(clid, cd_id){
		
		$("#student_list_payment").html("<div width=\"100%\" align=\"center\"><img src=\"images/loader.gif\"/></div>");		
		$.ajax({
			type : "POST",
			url : "report_tutorial_student_list.action",				
			data : "class_id=" + clid + "&pt_id=2" + "&cd_id=" + cd_id,
			success : function(response) {
				document.getElementById("eduPay").style.backgroundColor = 'transparent';
				document.getElementById("eduPay").style.color = '#3d6e9f';
				
				document.getElementById("tutPay").style.backgroundColor = '#3d6e9f';
				document.getElementById("tutPay").style.color = '#fff';
				
				document.getElementById("sumPay").style.backgroundColor = 'transparent';
				document.getElementById("sumPay").style.color = '#3d6e9f';
				
				document.getElementById("matPay").style.backgroundColor = 'transparent';
				document.getElementById("matPay").style.color = '#3d6e9f';
				
				$('#student_list_payment').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function sumPayment(clid, cd_id){
		$("#student_list_payment").html("<div width=\"100%\" align=\"center\"><img src=\"images/loader.gif\"/></div>");		
		$.ajax({
			type : "POST",
			url : "report_summer_student_list.action",				
			data : "class_id=" + clid + "&pt_id=3" + "&cd_id=" + cd_id,
			success : function(response) {
				document.getElementById("eduPay").style.backgroundColor = 'transparent';
				document.getElementById("eduPay").style.color = '#3d6e9f';
				
				document.getElementById("tutPay").style.backgroundColor = 'transparent';
				document.getElementById("tutPay").style.color = '#3d6e9f';
				
				document.getElementById("sumPay").style.backgroundColor = '#3d6e9f';
				document.getElementById("sumPay").style.color = '#fff';
				
				document.getElementById("matPay").style.backgroundColor = 'transparent';
				document.getElementById("matPay").style.color = '#3d6e9f';
				
				$('#student_list_payment').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function matPayment(clid, cd_id){
		$("#student_list_payment").html("<div width=\"100%\" align=\"center\"><img src=\"images/loader.gif\"/></div>");		
		$.ajax({
			type : "POST",
			url : "report_material_student_list.action",				
			data : "class_id=" + clid  + "&pt_id=4" + "&cd_id=" + cd_id,
			success : function(response) {
				document.getElementById("eduPay").style.backgroundColor = 'transparent';
				document.getElementById("eduPay").style.color = '#3d6e9f';
				
				document.getElementById("tutPay").style.backgroundColor = 'transparent';
				document.getElementById("tutPay").style.color = '#3d6e9f';
				
				document.getElementById("sumPay").style.backgroundColor = 'transparent';
				document.getElementById("sumPay").style.color = '#3d6e9f';
				
				document.getElementById("matPay").style.backgroundColor = '#3d6e9f';
				document.getElementById("matPay").style.color = '#fff';
				
				$('#student_list_payment').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- paid receipt folder - payment summer student list page -----*/
	
//	function selectedMonth(month, siid, fname, mname, gname){
//		var clid = $('#clid').val();
//		
//		$("#student_list_payment").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
//		$.ajax({
//			type : "POST",
//			url : "payment_form_sum.action",				
//			data : "si_id=" + siid + "&month=" + month + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&payment_type=" + 3 + "&class_id=" + clid,
//			success : function(response) {
//				$('#student_list_payment').html(response);
//			},
//			error : function(e) {
//				alert('Error: ' + e);
//			}
//		});
//	}
	
	/*----- paid receipt folder - payment tutorial student list page -----*/
	
//	function selectedMonth(month, siid, fname, mname, gname){
//		var clid = $('#clid').val();
//		
//		$("#student_list_payment").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
//		$.ajax({
//			type : "POST",
//			url : "payment_form_tut.action",				
//			data : "si_id=" + siid + "&month=" + month + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&payment_type=" + 2 + "&class_id=" + clid,
//			success : function(response) {
//				$('#student_list_payment').html(response);
//			},
//			error : function(e) {
//				alert('Error: ' + e);
//			}
//		});
//	}
	
	
	/*----- payment collection -----*/
	
	function generateCollectionReport(){
		var col_type = $('#col_type').val();
		var col_date_from = "";
		var col_date_to = "";
		var col_month_from = "";
		var col_month_to = "";
		var acyear = "";
		
		if(col_type == "0"){
			document.getElementById("errMsg").innerHTML = "Please select collection type."
		} else {
			document.getElementById("errMsg").innerHTML = ""
			if(col_type == "1"){
				
				col_date_from = $('#coldate_from').val();
				col_date_to = $('#coldate_to').val();
				
				if(col_date_from == "" || col_date_to == ""){
					document.getElementById("errMsg").innerHTML = "Date range is required."
				} else {
				
					$("#col_report").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
					$.ajax({
						type : "POST",
						url : "report_dailyPaymentCollection.action",				
						data : "col_date_from=" + col_date_from + "&col_date_to=" + col_date_to,
						success : function(response) {
							$('#col_report').html(response);
						},
						error : function(e) {
							alert('Error: ' + e);
						}
					});
				
				}
				
			} else if(col_type == "2"){
				
				col_month_from = $('#colmonth_from').val();
				col_month_to = $('#colmonth_to').val();
				acyear = $('#acyear').val();
				
				if(col_month_from == "" && col_month_to == ""){
					document.getElementById("errMsg").innerHTML = "Month range is required."
				} else if(acyear == ""){
					document.getElementById("errMsg").innerHTML = "Academic year is required."
				} else {
				
					$("#col_report").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
					$.ajax({
						type : "POST",
						url : "report_monthlyPaymentCollection.action",				
						data : "col_month_from=" + col_month_from + "&col_month_to=" + col_month_to + "&payment_bean.ac_year=" + acyear,
						success : function(response) {
							$('#col_report').html(response);
						},
						error : function(e) {
							alert('Error: ' + e);
						}
					});
				
				}
			}
		
		}
	}
	
	function emailCollection(date_from, date_to, grand_total){
		
		$("#collResult").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "communication_emailCollection.action",				
			data : "col_date_from=" + date_from + "&col_date_to=" + date_to + "&grand_total=" + grand_total,
			success : function(response) {
				$('#collResult').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- unpaid student folder - unpaid students template -----*/
	
	function generateUnpaidReport(){
		
		var cl_id = $('#class_id').val();
		var month_from = $('#month_from').val();
		var month_to = $('#month_to').val();
		var ac_year = $('#ac_year').val();
		
		if(cl_id == 0){
			document.getElementById("errMsg").innerHTML = "Class is required."
		} else if(month_from == 0 || month_to == 0){
			document.getElementById("errMsg").innerHTML = "Month range is required."
		} else if(ac_year == ""){
			document.getElementById("errMsg").innerHTML = "Academic year is required."
		} else {
			
			$("#unpaid_list").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "report_unpaidStudentList.action",				
				data : "class_bean.cl_id=" + cl_id + "&col_month_from=" + month_from  + "&col_month_to=" + month_to + "&payment_bean.ac_year=" + ac_year,
				success : function(response) {
					$('#unpaid_list').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
		
	}
	
	
	/*========================== EXAM SCHEDULE ==========================*/
	
	/*----- report exam schedule page -----*/
	
	function generateExamScheduleReport(){
		
		var at_id = $('#at_id').val();
		var et_id = $('#et_id').val();
		
		if(at_id == "0"){
			document.getElementById("errMsg").innerHTML = "Quarter is required.";
		} else if(et_id == "0"){
			document.getElementById("errMsg").innerHTML = "Exam Type is required.";
		} else {
			
			$("#report_rslt").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "report_examprogramreport.action",
				data : "at_id=" + at_id + "&et_id=" + et_id,
				success : function(response) {
					$('#report_rslt').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	/*========================== EDUCATION OFFICE ==========================*/
	
	/*----- student mark plan implement template -----*/
	
	function classRoomListForEducationOffice(at_id){
		
		document.getElementById("studMarkPlanAndImp").innerHTML = "";
		
		$("#classList").html("<img align=\"center\" src=\"images/30.GIF\"/>");
		$.ajax({
			type : "POST",
			url : "report_eduoff_classlist.action",				
			data : "at_id=" + at_id,
			success : function(response) {
				document.getElementById("advanceFilterBtn").innerHTML = "";				
				$('#classList').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
//	var filter = 'No';
//	function filterStudentForOffice(){
//		filter = 'Yes';
//		document.getElementById("officeUse").innerHTML = "<img alt=\"check\" src=\"images/checked.png\" onclick=\"unfilterStudentForOffice()\" style=\"cursor: pointer;\" width=\"16px\">";
//	}
//	
//	function unfilterStudentForOffice(){
//		filter = 'No';
//		document.getElementById("officeUse").innerHTML = "<img alt=\"check\" src=\"images/check_box.png\" onclick=\"filterStudentForOffice()\" style=\"cursor: pointer;\" width=\"16px\">";
//	}
	
	function showAdvanceFilterButton(){
		document.getElementById("advanceFilterBtn").innerHTML = "<div onclick=\"listSubjectForAdvanceFilter()\" class=\"adminHeaderButton\" style=\"cursor: pointer; width: 150px;\">FILTER SUBJECT</div>";
	}
	
	function listSubjectForAdvanceFilter(){
		
		var cl_id = $('#cl_id').val();
		
		if(cl_id == "0"){
			document.getElementById("clListError").innerHTML = "Class Required.";
		} else {
			$("#studMarkPlanAndImp").html("<img align=\"center\" src=\"images/30.GIF\"/>");
			$.ajax({
				type : "POST",
				url : "report_clsublistfilter.action",				
				data : "at_id=" + at_id + "&cl_id=" + cl_id,
				success : function(response) {			
					$('#studMarkPlanAndImp').html(response);				
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function generateStudentMarkPlanAndImp(){
		
		var at_id = $('#at_id').val();
		var cl_id = $('#cl_id').val();
		var ac_year = $('#ac_year').val();
		
		if(at_id == "0"){ 
			document.getElementById("qrtListError").innerHTML = "Quarter Required.";
		} else if(cl_id == "0"){
			document.getElementById("clListError").innerHTML = "Class Required.";
		} else {
			
			$("#studMarkPlanAndImp").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "report_getStudentMarkPlanAndImpList.action",				
				data : "at_id=" + at_id + "&class_bean.cl_id=" + cl_id + "&stud_bean.ac_year=" + ac_year + "&eduoff_filter_status=" + filter,
				success : function(response) {
					$('#studMarkPlanAndImp').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function generateExcelQuarterMarkList(){
		
		var at_id = $('#at_id').val();
		var cl_id = $('#cl_id').val();
		var ac_year = $('#ac_year').val();
		
		if(at_id == "0"){ 
			document.getElementById("qrtListError").innerHTML = "Quarter Required.";
		} else if(cl_id == "0"){
			document.getElementById("clListError").innerHTML = "Class Required.";
		} else {
			
			$("#studMarkPlanAndImp").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "report_getExcelStudentMarkPlanAndImpList.action",				
				data : "at_id=" + at_id + "&class_bean.cl_id=" + cl_id + "&stud_bean.ac_year=" + ac_year + "&eduoff_filter_status=" + filter,
				success : function(response) {
					$('#studMarkPlanAndImp').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}

	/*----- student mark list template page -----*/
	
function classRoomListForEducationOfficeImpl(at_id){
		
		document.getElementById("studMarkPlanAndImp").innerHTML = "";
		
		$("#classList").html("<img align=\"center\" src=\"images/30.GIF\"/>");
		$.ajax({
			type : "POST",
			url : "report_eduoff_classlist.action",				
			data : "at_id=" + at_id,
			success : function(response) {
				document.getElementById("advanceFilterBtn").innerHTML = "";			
				$('#classList').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
//	var filter = 'No';
//	function filterStudentForOffice(){
//		filter = 'Yes';
//		document.getElementById("officeUse").innerHTML = "<img alt=\"check\" src=\"images/checked.png\" onclick=\"unfilterStudentForOffice()\" style=\"cursor: pointer;\" width=\"16px\">";
//	}
//	
//	function unfilterStudentForOffice(){
//		filter = 'No';
//		document.getElementById("officeUse").innerHTML = "<img alt=\"check\" src=\"images/check_box.png\" onclick=\"filterStudentForOffice()\" style=\"cursor: pointer;\" width=\"16px\">";
//	}
	
	function showAdvanceFilterButton(){
		document.getElementById("advanceFilterBtn").innerHTML = "<div onclick=\"listSubjectForAdvanceFilter()\" class=\"adminHeaderButton\" style=\"cursor: pointer; width: 150px;\">FILTER SUBJECT</div>";
	}
	
	function listSubjectForAdvanceFilter(){
		
		var cl_id = $('#cl_id').val();
		
		if(cl_id == "0"){
			document.getElementById("clListError").innerHTML = "Class Required.";
		} else {
			$("#studMarkPlanAndImp").html("<img align=\"center\" src=\"images/30.GIF\"/>");
			$.ajax({
				type : "POST",
				url : "report_clsublistfilter.action",				
				data : "at_id=" + at_id + "&cl_id=" + cl_id,
				success : function(response) {			
					$('#studMarkPlanAndImp').html(response);				
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function generateStudentMarkListReportForEducationOfficeImpl(){
		
		var at_id = $('#at_id').val();
		var cl_id = $('#cl_id').val();
		var ac_year = $('#ac_year').val();
		
		if(at_id == "0"){ 
			document.getElementById("qrtListError").innerHTML = "Quarter Required.";
		} else if(cl_id == "0"){
			document.getElementById("clListError").innerHTML = "Class Required.";
		} else {
			
			$("#studMarkPlanAndImp").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "report_getStudentMarkListReport.action",				
				data : "at_id=" + at_id + "&class_bean.cl_id=" + cl_id + "&stud_bean.ac_year=" + ac_year + "&eduoff_filter_status=" + filter,
				success : function(response) {
					$('#studMarkPlanAndImp').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function generateExcelQuarterMarkListForEducationOfficeImpl(){
		var at_id = $('#at_id').val();
		var cl_id = $('#cl_id').val();
		var ac_year = $('#ac_year').val();
		
		if(at_id == "0"){ 
			document.getElementById("qrtListError").innerHTML = "Quarter Required.";
		} else if(cl_id == "0"){
			document.getElementById("clListError").innerHTML = "Class Required.";
		} else {
			
			$("#studMarkPlanAndImp").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "report_getStudentExcelMarkListReport.action",				
				data : "at_id=" + at_id + "&class_bean.cl_id=" + cl_id + "&stud_bean.ac_year=" + ac_year + "&eduoff_filter_status=" + filter,
				success : function(response) {
					$('#studMarkPlanAndImp').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}

	
	
	/*========================== CHECK INSERTED EXAM RESULT ==========================*/
	
	/*----- check inserted exam result page -----*/
	
//	var filter = 'No';
//	function filterStudentForOffice(){
//		filter = 'Yes';
//		document.getElementById("officeUse").innerHTML = "<img alt=\"check\" src=\"images/checked.png\" onclick=\"unfilterStudentForOffice()\" style=\"cursor: pointer;\" width=\"16px\">";
//	}
//	
//	function unfilterStudentForOffice(){
//		filter = 'No';
//		document.getElementById("officeUse").innerHTML = "<img alt=\"check\" src=\"images/check_box.png\" onclick=\"filterStudentForOffice()\" style=\"cursor: pointer;\" width=\"16px\">";
//	}

	function generateInsertedMarkList(){
		
		var at_id = $('#at_id').val();
		var cl_id = $('#cl_id').val();
		var cd_id = $('#cd_id').val();
		var et_id = $('#et_id').val();
		var ac_year = $('#ac_year').val();
		
		if(at_id == "0"){ 
			document.getElementById("qrtListError").innerHTML = "Quarter Required.";
		} else if(cl_id == "0"){
			document.getElementById("clListError").innerHTML = "Class Required.";
		} else if(cd_id == "0"){
			document.getElementById("clGrdListError").innerHTML = "Detail Required.";
		} else if(et_id == "0"){
			document.getElementById("subListError").innerHTML = "Exam type Required.";
		} else {
			
			$("#markList").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "report_getCheckInsertedMarkRsltList.action",				
				data : "at_id=" + at_id + "&class_bean.cl_id=" + cl_id + "&cdetail_bean.cd_id=" + cd_id + "&et_id=" + et_id + "&stud_bean.ac_year=" + ac_year,
				success : function(response) {
					$('#markList').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function ckexamrslt_classRoomList(at_id){
		
		$("#classList").html("<img align=\"center\" src=\"images/30.GIF\"/>");
		$.ajax({
			type : "POST",
			url : "report_checkrslt_classlist.action",				
			data : "at_id=" + at_id,
			success : function(response) {
				
				$('#classList').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- class list page -----*/
	
	function ckexamrslt_gradeDetailList(cl_id){
		
		document.getElementById('markList').innerHTML = "";
		
		var at_id = $('#at_id').val();
		
		$("#grdList").html("<img align=\"center\" src=\"images/30.GIF\"/>");
		$.ajax({
			type : "POST",
			url : "report_checkrslt_grdlist.action",				
			data : "at_id=" + at_id + "&cl_id=" + cl_id,
			success : function(response) {
				
//	 			document.getElementById('grdList').innerHTML = "";
//	 			document.getElementById('subList').innerHTML = "";
//	 			document.getElementById('examList').innerHTML = "";
//	 			document.getElementById('studList').innerHTML = "";
				
				$('#grdList').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- grade detail list page -----*/
	
	function ckexamrslt_subjectList(cd_id){
		
		document.getElementById('markList').innerHTML = "";
		
		var at_id = $('#at_id').val();
		var cl_id = $('#cl_id').val();
		
		$("#subList").html("<img align=\"center\" src=\"images/30.GIF\"/>");
		$.ajax({
			type : "POST",
			url : "report_clsublist.action",				
			data : "at_id=" + at_id + "&cl_id=" + cl_id + "&cd_id=" + cd_id,
			success : function(response) {
						
//	 			document.getElementById('subList').innerHTML = "";
//	 			document.getElementById('examList').innerHTML = "";
//	 			document.getElementById('studList').innerHTML = "";	
				
				$('#subList').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	} 

	function ckexamrslt_examTypeList(cd_id){
		
		document.getElementById('markList').innerHTML = "";
		
		$("#subList").html("<img align=\"center\" src=\"images/30.GIF\"/>");
		$.ajax({
			type : "POST",
			url : "report_checkrslt_examtypelist.action",				
			data : "",
			success : function(response) {
				
				$('#subList').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	} 
	
	
	
	function classRoomListForBelowAverage(at_id){
		
		$("#classList").html("<img align=\"center\" src=\"images/30.GIF\"/>");
		$.ajax({
			type : "POST",
			url : "report_belowaverage_classlist.action",				
			data : "at_id=" + at_id,
			success : function(response) {
				
				$('#classList').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}

	function gradeDetailListForBelowAverage(cl_id){
		
		document.getElementById('belowAverageList').innerHTML = "";
		
		var at_id = $('#at_id').val();
		
		$("#gradeList").html("<img align=\"center\" src=\"images/30.GIF\"/>");
		$.ajax({
			type : "POST",
			url : "report_belowaverage_grdlist.action",				
			data : "at_id=" + at_id + "&cl_id=" + cl_id,
			success : function(response) {
			
				$('#gradeList').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function averageListForBelowAverage(){
		
		$("#averageList").html("<img align=\"center\" src=\"images/30.GIF\"/>");
		$.ajax({
			type : "POST",
			url : "report_belowaverage_averagelist.action",				
			data : "",
			success : function(response) {			
				$('#averageList').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function generateBelowAverageStudentList(){
		
		var ac_year = $('#ac_year').val();
		var at_id = $('#at_id').val();
		var cl_id = $('#cl_id').val();
		var cd_id = $('#cd_id').val();
		var average_value = $('#average_list').val();
		
		
		if(at_id == "0"){ 
			document.getElementById("qrtListError").innerHTML = "Quarter Required.";
		} else if(cl_id == "0"){
			document.getElementById("clListError").innerHTML = "Class Required.";
		} else if(cd_id == "0"){
			document.getElementById("clGrdListError").innerHTML = "Detail Required.";
		} else {
			
			$("#belowAverageList").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "report_getBelowAverageStudentMarkList.action",				
				data : "at_id=" + at_id + "&cl_id=" + cl_id + "&cd_id=" + cd_id + "&average_mark=" + average_value + "&academic_year=" + ac_year,
				success : function(response) {
					$('#belowAverageList').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}		
	}