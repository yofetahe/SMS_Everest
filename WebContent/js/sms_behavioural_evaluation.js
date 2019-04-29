/**
 * 
 */
/*===================== BEHAVIOURAL EVALUATION =====================*/

	/*----- beval template page -----*/
	
	function behavioural_evaluation_temp(){
		
		$("#eval_content").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_evaluationtemp.action",				
			data : "",
			success : function(response) { 
				document.getElementById("beh").innerHTML = "<img alt=\"click\" src=\"images/next.gif\" width=\"8px\">&nbsp;Behavioral Evaluation";
				document.getElementById("hol").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Holistic Behavioral Evaluation";
				$('#eval_content').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}

	function holistic_behaviour_evaluation_temp(){
		
		$("#eval_content").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_holisticevaluationtemp.action",				
			data : "",
			success : function(response) { 
				document.getElementById("beh").innerHTML = "<img alt=\"click\" src=\"images/next_dim.gif\" width=\"8px\">&nbsp;Behavioral Evaluation";
				document.getElementById("hol").innerHTML = "<img alt=\"click\" src=\"images/next.gif\" width=\"8px\">&nbsp;Holistic Behavioral Evaluation";
				$('#eval_content').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- beval home page -----*/
	
	var prev_clid = null;
	function bevalPerGradeSelected(cl_id, class_name){
		$("#gradeDetailList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_classdetaillist.action",				
			data : "cl_id=" + cl_id + "&class_name=" + class_name,
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
				$('#gradeDetailList').html(response);
				prev_clid = cl_id;
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- beval student list per grade page -----*/
	
	function refreshStudentEvalResultView(){
		
		document.getElementById("student_side_list_pergrade").innerHTML = "";
	}
	
	function getStudentEvaluationResult(cl_id, si_id, fname, mname, gname){
		
		document.getElementById("errMsg").innerHTML = "";
		
		var at_id = $('#at_id').val();
		
		if(at_id == 0){
			document.getElementById("errMsg").innerHTML = "Please select quarter.";
		} else {
			////// semester-id is saved on the column of quarter-id
			$("#student_side_list_pergrade").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "behaviouralevaluation_studentbevallist.action",				
				data : "cl_id=" + cl_id + "&si_id=" + si_id + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&at_id=" + at_id + "&qr_id=" + at_id,
				success : function(response) {
					
					$('#student_side_list_pergrade').html(response);
					
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	/*----- beval student beval rslt page -----*/
	
	function evaluationGradeList(bt_id){
		
		$("#evalGrgList").html("<img align=\"center\" src=\"images/30.GIF\"/>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_evalgradelist.action",				
			data : "bt_id=" + bt_id,
			success : function(response) {
				
				$('#evalGrgList').html(response);
				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	var studEvalRslt = new Array();
	
	function assignBevalResult(bg_id, bt_id, cl_id, si_id, qr_id){
		
		var size = studEvalRslt.length;
		
		var count = 0;
		
		for(var i = 0; i < studEvalRslt.length; i++){
			
			if(studEvalRslt[i][2] == bt_id){
					
					count++;
			}
		
			if(count > 0){
				studMark.splice(i, 1);				
				count = 0;
			}		
		}
		
		studEvalRslt[size] = new Array(si_id, cl_id, bt_id, bg_id, qr_id);
	}
	
	function saveBevalResultFromJSArray(counter, cl_id, si_id, qr_id, fname, mname, gname, at_name, ac_year){
		
		if(counter == 0){
			
			document.getElementById('errMsg').innerHTML = "Grades are already given or no evaluation points.";
			
		} else if(counter != studEvalRslt.length){
			
			document.getElementById('errMsg').innerHTML = "All evaluation grades must be given.";
			
		} else {
			
			$("#student_side_list_pergrade").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "behaviouralevaluation_saveevalgradefromjs.action",				
				data : "cl_id=" + cl_id + "&si_id=" + si_id + "&qr_id=" + qr_id + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&at_name=" + at_name + "&ac_year=" + ac_year + "&studbevalrslt_list=" + studEvalRslt,
				success : function(response) {
					
					$('#student_side_list_pergrade').html(response);
					
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
		
	}
	
	function saveBevalResult(cl_id, si_id, qr_id, fname, mname, gname, at_name, ac_year){
		
		var bt_id = $('#btid').val();
		var bg_id = $('#bgid').val();
		
		if(bt_id == 0){
			document.getElementById('errMsg').innerHTML = "Trait must be selected.";
		} else if(bg_id == 0){
			document.getElementById('errMsg').innerHTML = "Grade must be given.";
		} else {
			$("#studRsltList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "behaviouralevaluation_saveevalgrade.action",				
				data : "bt_id=" + bt_id + "&bg_id=" + bg_id + "&cl_id=" + cl_id + "&si_id=" + si_id + "&qr_id=" + qr_id + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&at_name=" + at_name + "&ac_year=" + ac_year,
				success : function(response) {
					
					$('#studRsltList').html(response);
					
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function bevalRsltEditForm(bsr_id, cl_id, si_id, qr_id, bt_id, bsr_status, bg_id){
		
		$("#saveEditForm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_bevalrslteditform.action",				
			data : "bsr_id=" + bsr_id + "&cl_id=" + cl_id + "&si_id=" + si_id + "&qr_id=" + qr_id + "&bt_id=" + bt_id + "&bsr_status=" + bsr_status + "&bg_id=" + bg_id,
			success : function(response) {
				
				$('#saveEditForm').html(response);
				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function updateBevalResult(bsr_id, cl_id, si_id, qr_id){
		
		var bt_id = $('#btid').val();
		var bg_id = $('#bgid').val();
		var bsr_status = $('#bsrstatus').val();
		
		if(bt_id == 0){
			document.getElementById("errMsg").innerHTML = "Trait must be selected.";
		} else if(bg_id == 0){
			document.getElementById("errMsg").innerHTML = "Grade must be given.";
		} else {
			$("#studRsltList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "behaviouralevaluation_updatebevalrslt.action",				
				data : "bsr_id=" + bsr_id + "&bt_id=" + bt_id + "&bg_id=" + bg_id + "&bsr_status=" + bsr_status + "&cl_id=" + cl_id + "&si_id=" + si_id + "&qr_id=" + qr_id,
				success : function(response) {
					
					$('#studRsltList').html(response);
					
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	/*----- beval class detail list page -----*/
	
	var prev_cdid = null;
	function classDetail(cl_id, cd_id){
		
		$("#student_list_pergrade").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "behaviouralevaluation_studentlist.action",				
			data : "cl_id=" + cl_id + "&cd_id=" + cd_id,
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
				$('#student_list_pergrade').html(response);
				prev_cdid = cd_id;
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	