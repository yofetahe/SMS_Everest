/**
 * 
 */
/*======================== CLASS SCHEDULE GENERAL ======================*/

	/*----- class schedule academic program page -----*/

	function editSelectedSchedule(prd, wday, cl_id, cd_id, cs_id, ac_year){		
		
		var id = wday + "" + prd + "" + prd;
		
		$("#"+id).html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classschedule_editinfo.action",				
			data : "period=" + prd + "&week_day=" + wday + "&cl_id=" + cl_id + "&cd_id=" + cd_id + "&cs_id=" + cs_id + "&academic_year=" + ac_year,
			
			success : function(response) {
				$("#"+id).html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- class schedule general schedule list page -----*/
	
	function generateGeneralSchedulePDF(){
		
		$("#pdfSuccessMsg").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classschedule_generalschedule_pdf.action",				
			data : "",
			success : function(response) {				
				$('#pdfSuccessMsg').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- class schedule list page -----*/
	
	function addClassScheduleFrm(){
		//alert("test");
	}
	
	var prev_clid = null;
	function clSchedule_gradeSelected(cl_id, cl_name){
		
		$("#classScheduleList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classschedule_pergradelist.action",				
			data : "cl_id=" + cl_id + "&class_name=" + cl_name,
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
				$('#classScheduleList').html(response);
				prev_clid = cl_id;
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function teacher_clSchedule(){
		$("#classScheduleList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classschedule_teachers_schedule.action",				
			data : "",
			success : function(response) {				
				$('#classScheduleList').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function general_clSchedule(){
		$("#classScheduleList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classschedule_general_schedule.action",				
			data : "",
			success : function(response) {				
				$('#classScheduleList').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- class schedule list per grade create form page -----*/
	
	function teacherForSelectedSub(sub_id, prd, wdays, cl_id, cd_id){
		
		var id = wdays + "" + prd;
		
		$("#t"+id).html("<img align=\"center\" src=\"images/30.GIF\"/>");
		$.ajax({
			type : "POST",
			url : "classschedule_teacherforsubject.action",				
			data : "sub_id=" + sub_id + "&cl_id=" + cl_id + "&period=" + prd + "&cd_id=" + cd_id + "&week_day=" + wdays,
			success : function(response) {
				$("#t"+id).html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addScheduleProgram(ta_id, prd, wdays, cd_id, cl_id){
		
		var acyear = document.getElementById("ac_year").value;		
		var id = wdays + "" + prd + "" + prd;
		var index = prd;		
					
		$("#"+id).html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classschedule_classSchedule.action",				
			data : "period=" + prd + "&cd_id=" + cd_id + "&week_day=" + wdays + "&ta_id=" + ta_id + "&academic_year=" + acyear + "&cl_id=" + cl_id + "&indx=" + index,
			success : function(response) {
				$("#"+id).html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
		
	}
	
	function editSelectedSchedule_assign(prd, wday, cl_id, cd_id){
		
		var id = wday + "" + prd + "" + prd;
		$("#"+id).html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classschedule_removeinfo.action",				
			data : "period=" + prd + "&week_day=" + wday + "&cl_id=" + cl_id + "&cd_id=" + cd_id,
			success : function(response) {
				$("#"+id).html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function saveAcademiceCalendarSchedule(wday, prd, cd_id){
		
		var id = wday + "" + prd + "" + wday;
		$("#"+id).html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classschedule_saveinfo.action",				
			data : "saveBtnIndex=" + wday + "&cd_id=" + cd_id,
			success : function(response) {
				$("#"+id).html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function subjectAssignmentSuggestion(cl_id, cd_id){
		
		$("#suggestedClassSchedule").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classschedule_subjectAssignmentSuggestion.action",				
			data : "cl_id=" + cl_id + "&cd_id=" + cd_id,
			success : function(response) {
				$('#suggestedClassSchedule').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- class schedule list per grade detail page -----*/
	
function classScheduleAddForm(cl_id, cd_id){
		
		var todayDate=new Date();
        var year=todayDate.getFullYear();
        var date=todayDate.getDate();
        var month=todayDate.getMonth();
        var m_names = new Array("January", "February", "March", 
                "April", "May", "June", "July", "August", "September", 
                "October", "November", "December");
        var dt = date + "-" + m_names[month] + "-" + year;
        
		$("#classschedule_form").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classschedule_listpergrdetail_crtform.action",				
			data : "cl_id=" + cl_id + "&cd_id=" + cd_id + "&cur_date=" + dt,
			success : function(response) {
				$('#classschedule_form').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
		
	function academicCalendarPerYear(year, cl_id, cd_id){
		
		$("#academic_program").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classschedule_academicprogram_peryear.action",				
			data : "cl_id=" + cl_id + "&cd_id=" + cd_id + "&cur_date=" + year,
			success : function(response) {
				$('#academic_program').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function subjectAssignmentSuggestion(cl_id, cd_id){
		
		$("#suggestedClassSchedule").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classschedule_subjectAssignmentSuggestion.action",				
			data : "cl_id=" + cl_id + "&cd_id=" + cd_id,
			success : function(response) {
				$('#suggestedClassSchedule').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- class schedule per grade list page -----*/
	
	function onSchedulePrintout(){
		document.getElementById("ck_box").innerHTML = "<img alt=\"\" src=\"images/checked.png\" style=\"width: 16px;\" onclick=\"offSchedulePrintout()\"> ";
		document.getElementById("printOnOff").value = "1";
		document.getElementById("noofcopies").style.display = "block";
	}
	
	function offSchedulePrintout(){
		document.getElementById("ck_box").innerHTML = "<img alt=\"\" src=\"images/check_box.png\" style=\"width: 16px;\" onclick=\"onSchedulePrintout()\"> ";
		document.getElementById("printOnOff").value = "0";
		document.getElementById("noofcopies").style.display = "none";
	}
	
	function selectClassScheduleOption(cl_id, cd_id, cl_name, cd_name){
		var check = document.getElementById("printOnOff").value;
		var noofcopies = $('#copiesno').val();
		
		if(check == 0){
			classschedule_perclassDetail(cl_id, cd_id, cl_name, cd_name);
		} else {
			classschedule_perclassDetail_printout(cl_id, cd_id, cl_name, cd_name, noofcopies);
		}
	}

	var prev_cdid = null;
	function classschedule_perclassDetail(cl_id, cd_id, cl_name, cd_name){
		var todayDate=new Date();
        var year=todayDate.getFullYear();
        var date=todayDate.getDate();
        var month=todayDate.getMonth();
        var m_names = new Array("January", "February", "March", 
                "April", "May", "June", "July", "August", "September", 
                "October", "November", "December");
        var dt = date + "-" + m_names[month] + "-" + year;
        
		$("#classschedule_form").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classschedule_listpergrdetail.action",				
			data : "cl_id=" + cl_id + "&cd_id=" + cd_id + "&cur_date=" + dt,
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
				$('#classschedule_form').html(response);
				prev_cdid = cd_id;
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function classschedule_perclassDetail_printout(cl_id, cd_id, cl_name, cd_name, noofcopies){
		$("#classschedule_form").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classschedule_listpergrdetail_forprint.action",				
			data : "cl_id=" + cl_id + "&cd_id=" + cd_id + "&noofcopies=" + noofcopies,
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
				$('#classschedule_form').html(response);
				prev_cdid = cd_id;
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function clearPrintOutView(){
		document.getElementById('classschedule_form').innerHTML = "";
	}
	
	/*----- class schedule teachers schedule page -----*/
	
	function viewSelectedTeacherSchedule(ti_id){
		$("#teachers_schedule").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classschedule_viewSelectedTeacherSchedule.action",				
			data : "ti_id=" + ti_id,
			success : function(response) {								
				$('#teachers_schedule').html(response);
				prev_cdid = cd_id;
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- class schedule update form page -----*/
	
	function teacherForSelectedSub(sub_id, prd, wdays, cl_id, cd_id){		
		var id = wdays + "" + prd;		
		$("#t"+id).html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classschedule_teacherforsubject.action",				
			data : "sub_id=" + sub_id + "&cl_id=" + cl_id + "&period=" + prd + "&cd_id=" + cd_id + "&week_day=" + wdays,
			success : function(response) {
				$("#t"+id).html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function updateSelectedSchedule(prd, wdays, cl_id, cd_id, cs_id, ac_year){		
		var ta_id = $("#taid").val();
		var sub_id = $("#subid").val();
		var id = wdays + "" + prd + "" + prd;		
		$("#"+id).html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classschedule_updateinfo.action",				
			data : "sub_id=" + sub_id + "&cl_id=" + cl_id + "&ta_id=" + ta_id + "&cd_id=" + cd_id + "&week_day=" + wdays + "&period=" + prd + "&cs_id=" + cs_id  + "&academic_year=" + ac_year,
			success : function(response) {
				$("#"+id).html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}