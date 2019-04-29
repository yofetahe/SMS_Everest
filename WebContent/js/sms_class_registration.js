/**
 * 
 */
/*===================== CLASS REGISTRATION =====================*/

	/*----- class list page -----*/
	
	function addClassFrm(){
		
		$("#classFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classaction_savefrm.action",				
			data : "",
			success : function(response) {
				$('#classFrm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}

	function editClass(cl_id, class_name, class_number, class_status){
		
		$("#classFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classaction_updatefrm.action",				
			data : "cl_id=" + cl_id + "&class_name=" + class_name + "&class_status=" + class_status + "&class_number=" + class_number,
			success : function(response) {
				$('#classFrm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function relateClassDetail(cl_id, class_name){
		
		$("#classFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classaction_cldetaillist.action",				
			data : "cl_id=" + cl_id + "&class_name=" + class_name,
			success : function(response) {
				$('#classFrm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addSubjectRel(cl_id, class_name){
		
		$("#classFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classaction_clsubrel.action",				
			data : "cl_id=" + cl_id + "&class_name=" + class_name,
			success : function(response) {
				$('#classFrm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- class edit page -----*/
	
	function updateClass(cl_id){
		
		var class_name = $('#class_name').val();
		var class_number = $('#class_number').val();
		var class_status = $('#class_status').val();
		
		if(class_name == ""){
			document.getElementById("errMsg").innerHTML = "Class name is required.";
		} else {
		
			$("#classFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "classaction_update.action",				
				data : "cl_id=" + cl_id + "&class_name=" + class_name + "&class_status=" + class_status + "&class_number=" + class_number,
				success : function(response) {
					$('#classFrm').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	function backToClassList(){
		
		$("#classFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classaction_classlist.action",				
			data : "",
			success : function(response) {
				$('#classFrm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- class create page -----*/
	
	function saveClass(){
		
		var class_name = $('#class_name').val();
		var class_number = $('#class_number').val();
		
		if(class_name == ""){
			document.getElementById("errMsg").innerHTML = "Class name is required.";
		} else if(class_number == ""){
			document.getElementById("errMsg").innerHTML = "Class number is required.";
		} else {
		
			$("#classFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "classaction_save.action",				
				data : "class_name=" + class_name + "&class_number=" + class_number,
				success : function(response) {
					$('#classFrm').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
//	function backToClassList(){
//		
//		$("#classFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
//		$.ajax({
//			type : "POST",
//			url : "classaction_classlist.action",				
//			data : "",
//			success : function(response) {
//				$('#classFrm').html(response);
//			},
//			error : function(e) {
//				alert('Error: ' + e);
//			}
//		});
//	}
	
	/*==================== SUBJECT EXAM TYPE ======================*/
	
	/*----- EXAM TYPE SUB LIST PAGE -----*/
	
	function addSubExamTypeRel(subcl_id, sub_name, cl_id, sub_id){
		
		$("#clsubrelfrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classaction_clsubExmRelCrtFrm.action",				
			data : "subcl_id=" + subcl_id + "&sub_name=" + sub_name + "&cl_id=" + cl_id + "&sub_id=" + sub_id,
			success : function(response) {
				$('#clsubrelfrm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function editClSubExamRel(exsub_id, total_mark, pass_mark, rel_status, cl_id, sub_id, sub_name){
		
		$("#clsubrelfrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		
		$.ajax({
			type : "POST",
			url : "classaction_clsubExmRelEditFrm.action",				
			data : "exsub_id=" + exsub_id + "&total_mark=" + total_mark + "&pass_mark=" + pass_mark + "&rel_status=" + rel_status + "&cl_id=" + cl_id + "&sub_id=" + sub_id + "&sub_name=" + sub_name,
			success : function(response) {
				$('#clsubrelfrm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
//	function addSubjectRel(cl_id, class_name){
//		
//		$("#classFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
//		$.ajax({
//			type : "POST",
//			url : "classaction_clsubrel.action",				
//			data : "cl_id=" + cl_id + "&class_name=" + class_name,
//			success : function(response) {
//				$('#classFrm').html(response);
//			},
//			error : function(e) {
//				alert('Error: ' + e);
//			}
//		});
//	}
	
	/*----- EXAM TYPE SUB EDIT PAGE -----*/
	
	function updateClSubExRel(exsub_id, cl_id, sub_id, sub_name){
		
		var rel_status = $('#rel_status').val();
		var total_mark = $('#total_mark').val();
		var pass_mark = $('#pass_mark').val();
		
		var pattern = /^[0-9]{1,3}$/;
		var totalmark_pattern = pattern.test(total_mark);
		var passmark_pattern = pattern.test(pass_mark);
		
		if(total_mark == ""){
			document.getElementById("errMsg").innerHTML = "Total Mark is required.";
		} 
// 		else if(totalmark_pattern == false){
// 			document.getElementById("errMsg").innerHTML = "Invalide Total Mark.";
// 		} 
		else if(pass_mark == ""){
			document.getElementById("errMsg").innerHTML = "Pass Mark is required.";
		} 
// 		else if(passmark_pattern == false){
// 			document.getElementById("errMsg").innerHTML = "Ivalide Pass Mark.";
// 		} 
		else if(Number(pass_mark) > Number(total_mark)){
			document.getElementById("errMsg").innerHTML = "Pass Mark couldn't exceed the total mark.";
		} else {
		
			$("#clsubrelfrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "classaction_clsubExmRelEdit.action",				
				data : "exsub_id=" + exsub_id + "&rel_status=" + rel_status + "&total_mark=" + total_mark + "&pass_mark=" + pass_mark + "&cl_id=" + cl_id + "&sub_id=" + sub_id + "&sub_name=" + sub_name,
				success : function(response) {
					$('#clsubrelfrm').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}

	/*----- EXAM TYPE SUB CREATE PAGE -----*/

	function saveClSubExRel(subcl_id, cl_id, sub_id){
		
		var et_id = $('#et_id').val();
		var total_mark = $('#total_mark').val();
		var pass_mark = $('#pass_mark').val();
		
		var pattern = /^[0-9]{1,3}$/;
		var totalmark_pattern = pattern.test(total_mark);
		var passmark_pattern = pattern.test(pass_mark);
		
		if(et_id == 0){
			document.getElementById("errMsg").innerHTML = "Exam type is required.";
		} else if(total_mark == ""){
			document.getElementById("errMsg").innerHTML = "Total Mark is required.";
		} 
	//		else if(totalmark_pattern == false){
	//			document.getElementById("errMsg").innerHTML = "Invalide Total Mark.";
	//		} 
		else if(pass_mark == ""){
			document.getElementById("errMsg").innerHTML = "Pass Mark is required.";
		} 
	//		else if(passmark_pattern == false){
	//			document.getElementById("errMsg").innerHTML = "Invalide Pass Mark.";
	//		} 
		else {
		
			$("#clsubrelfrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "classaction_clsubExmRelSave.action",				
				data : "subcl_id=" + subcl_id + "&et_id=" + et_id + "&total_mark=" + total_mark + "&pass_mark=" + pass_mark + "&cl_id=" + cl_id + "&sub_id=" + sub_id,
				success : function(response) {
					$('#clsubrelfrm').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	/*================ EXAM GRADING ==================*/
	
	/*----- EXAM GRADING LIST -----*/
	
	function addGradeFrm(subcl_id, sub_name) {
		
		$("#grdListCont").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classaction_gradeaddform.action",				
			data : "subcl_id=" + subcl_id + "&sub_name=" + sub_name,
			success : function(response) {
				$('#grdListCont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}

	function updateGradeFrm(subcl_id, sub_name, escg_id, eg_value, grade_from, grade_to, escg_status) {
		
		$("#grdListCont").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classaction_gradeupdateform.action",				
			data : "subcl_id=" + subcl_id + "&sub_name=" + sub_name + "&escg_id=" + escg_id + "&eg_value=" + eg_value + "&grade_from=" + grade_from + "&grade_to=" + grade_to + "&escg_status=" + escg_status,
			success : function(response) {
				$('#grdListCont').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function backToSubjectList(cl_id, class_name){
		
		$("#classFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classaction_clsubrel.action",				
			data : "cl_id=" + cl_id + "&class_name=" + class_name,
			success : function(response) {
				$('#classFrm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- EXAM GRADING EDIT -----*/
	
	function updateSubGrade(subcl_id, sub_name, escg_id){
		
		var eg_id = $('#eg_id').val();
		var grade_from = $('#grade_from').val();
		var grade_to = $('#grade_to').val();
		var escg_status = $('#escg_status').val();
		
		if(eg_id == 0){
			document.getElementById("errMsg").innerHTML = "Please select grade";
		} else if(grade_from == ""){
			document.getElementById("errMsg").innerHTML = "Please select grade";
		} else if(grade_to == ""){
			document.getElementById("errMsg").innerHTML = "Please select grade";
		} else if((grade_from*1) > grade_to){
			document.getElementById("errMsg").innerHTML = "Grade From must be lower than Grade To";
		} else if(grade_from > 100){
			document.getElementById("errMsg").innerHTML = "Grade From must be lower than Grade To";
		} else {
			$("#grdListCont").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "classaction_updategradesubrel.action",				
				data : "escg_id=" + escg_id + "&subcl_id=" + subcl_id + "&eg_id=" + eg_id + "&grade_from=" + grade_from + "&grade_to=" + grade_to + "&sub_name=" + sub_name + "&escg_status=" + escg_status,
				success : function(response) {
					$('#grdListCont').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
		
	}

	/*----- EXAM GRADING CREATE -----*/

	function saveSubGrade(subcl_id, sub_name){
		
		var eg_id = $('#eg_id').val();
		var grade_from = $('#grade_from').val();
		var grade_to = $('#grade_to').val();
		
		if(eg_id == 0){
			document.getElementById("errMsg").innerHTML = "Please select grade";
		} else if(grade_from == ""){
			document.getElementById("errMsg").innerHTML = "Please select grade";
		} else if(grade_to == ""){
			document.getElementById("errMsg").innerHTML = "Please select grade";
		} else if((grade_from*1) > grade_to){
			document.getElementById("errMsg").innerHTML = "Grade From must be lower than Grade To";
		} else if(grade_from > 100){
			document.getElementById("errMsg").innerHTML = "Grade From must be lower than Grade To";
		} else {
			$("#grdListCont").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "classaction_savegradesubrel.action",				
				data : "subcl_id=" + subcl_id + "&eg_id=" + eg_id + "&grade_from=" + grade_from + "&grade_to=" + grade_to + "&sub_name=" + sub_name,
				success : function(response) {
					$('#grdListCont').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}		
	}
	
	/*============== CLASS SUB REL =============*/
	
	/*----- CLASSS SUB TEMPLATE -----*/
	
//	function backToClassList(){
//		
//		$("#classFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
//		$.ajax({
//			type : "POST",
//			url : "classaction_classlist.action",				
//			data : "",
//			success : function(response) {
//				$('#classFrm').html(response);
//			},
//			error : function(e) {
//				alert('Error: ' + e);
//			}
//		});
//	}
	
	/*----- CLASS SUB LIST PAGE -----*/
	
	function addClassSubRel(cl_id, cl_name){
		
		$("#clsubrelfrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classaction_clsubrelCrtFrm.action",				
			data : "cl_id=" + cl_id + "&class_name=" + cl_name,
			success : function(response) {
				$('#clsubrelfrm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function editClSubRel(subcl_id, rel_status, cl_id, cl_name, sub_name, add_to_total, con_to_grade){
		
		$("#clsubrelfrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classaction_clsubrelEditFrm.action",				
			data : "subcl_id=" + subcl_id + "&rel_status=" + rel_status + "&cl_id=" + cl_id + "&class_name=" + cl_name + "&sub_name=" + sub_name + "&add_to_total=" + add_to_total + "&con_to_grade=" + con_to_grade,
			success : function(response) {
				$('#clsubrelfrm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function ExamTypeList(subcl_id, sub_name, cl_id, sub_id){
		
		$("#clsubrelfrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classaction_clsubExmRel.action",				
			data : "subcl_id=" + subcl_id + "&sub_name=" + sub_name + "&cl_id=" + cl_id + "&sub_id=" + sub_id,
			success : function(response) {
				$('#clsubrelfrm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	} 
	
	function GradingList(subcl_id, sub_name, cl_id, sub_id, cl_name){
		
		$("#clsubrelfrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classaction_gradinglist.action",				
			data : "subcl_id=" + subcl_id + "&sub_name=" + sub_name + "&cl_id=" + cl_id + "&sub_id=" + sub_id + "&cl_name=" + cl_name,
			success : function(response) {
				$('#clsubrelfrm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- CLASS SUB EDIT PAGE -----*/
	
	function updateClassSubRel(subcl_id, cl_id){
		
		var add_to_total = $('#added_to_total').val();
		var convert_to_grade = $('#conToGrade').val();
		var rel_status = $('#rel_status').val();
		
		$("#clsubrelfrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classaction_clsubrelEdit.action",				
			data : "subcl_id=" + subcl_id + "&rel_status=" + rel_status + "&cl_id=" + cl_id + "&add_to_total=" + add_to_total + "&con_to_grade=" + convert_to_grade,
			success : function(response) {
				$('#clsubrelfrm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- CLASS SUB CREATE PAGE -----*/
	
	function saveClassSubRel(cl_id){
		
		var sub_id = $('#sub_id').val();
		var addToTotal = $('#addToTotal').val();
		var conToGrade = $('#conToGrade').val();
		
		if(sub_id == 0){
			document.getElementById("errMsg").innerHTML = "Subject is required.";
		} else if(addToTotal == 0){
			document.getElementById("errMsg").innerHTML = "Please decided add to the total mark list.";
		} else if(conToGrade == 0){
			document.getElementById("errMsg").innerHTML = "Please decided convert to grade list.";
		} else {
			
			$("#clsubrelfrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "classaction_clsubrelCrt.action",				
				data : "cl_id=" + cl_id + "&sub_id=" + sub_id + "&add_to_total=" + addToTotal + "&con_to_grade=" + conToGrade,
				success : function(response) {
					$('#clsubrelfrm').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});		
		}
	}
	
	/*========================= CLASS DETAIL =========================*/
	
	/*----- CLASS DETAIL TEMPLATE -----*/
	
//	function backToClassList(){
//		
//		$("#classFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
//		$.ajax({
//			type : "POST",
//			url : "classaction_classlist.action",				
//			data : "",
//			success : function(response) {
//				$('#classFrm').html(response);
//			},
//			error : function(e) {
//				alert('Error: ' + e);
//			}
//		});
//	}
	
	/*----- CLASS DETAIL LIST -----*/
	
	function addClassDetail(cl_id){
		
		$("#cldetailFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classaction_classdetailfrm.action",				
			data : "cl_id=" + cl_id,
			success : function(response) {
				$('#cldetailFrm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}

	function editClassDetail(cl_id, clcd_id, cl_capacity, cl_status){
		
		$("#cldetailFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classaction_classdetaileditfrm.action",				
			data : "cl_id=" + cl_id + "&clcd_id=" + clcd_id + "&cl_capacity=" + cl_capacity + "&rel_status=" + cl_status,
			success : function(response) {
				$('#cldetailFrm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- CLASS DETAIL EDIT -----*/
	
	function ClassDetailList(cl_id){
		
		$("#cldetailFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classaction_cdetail.action",				
			data : "cl_id=" + cl_id,
			success : function(response) {
				$('#cldetailFrm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function updateClCdRel(clcd_id, cl_id){		
		
		var cl_capacity = $('#cl_capacity').val();
		var rel_status = $('#rel_status').val();
		
		var pattern = /^[0-9 ]{1,2}$/;
		var clcapacity_pattern = pattern.test(cl_capacity);
		
		if(cl_capacity == ""){
			document.getElementById("errMsg").innerHTML = "Class Capacity is required.";
		} else if(clcapacity_pattern == false){
			document.getElementById("errMsg").innerHTML = "Invalide Class Capacity.";
		} else {
		
			$("#cldetailFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "classaction_editcldetail.action",				
				data : "cl_id=" + cl_id + "&clcd_id=" + clcd_id + "&rel_status=" + rel_status + "&cl_capacity=" + cl_capacity,
				success : function(response) {
					$('#cldetailFrm').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});		
		}
	}
	
	/*----- CLASS DETAIL CREATE -----*/
	
//	function ClassDetailList(cl_id){
//		
//		$("#cldetailFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
//		$.ajax({
//			type : "POST",
//			url : "classaction_cdetail.action",				
//			data : "cl_id=" + cl_id,
//			success : function(response) {
//				$('#cldetailFrm').html(response);
//			},
//			error : function(e) {
//				alert('Error: ' + e);
//			}
//		});
//	}
	
	function saveClCdRel(cl_id){
		
		var cd_id = $('#cd_id').val();
		var cl_capacity = $('#cl_capacity').val();
		
		var pattern = /^[0-9 ]{1,2}$/;
		var clcapacity_pattern = pattern.test(cl_capacity);
		
		if(cd_id == 0){
			document.getElementById("errMsg").innerHTML = "Class Detail is required.";
		} else if(cl_capacity == ""){
			document.getElementById("errMsg").innerHTML = "Class capacity is required.";
		} else if(clcapacity_pattern == false){
			document.getElementById("errMsg").innerHTML = "Invalide Class Capacity.";
		} else {
		
			$("#cldetailFrm").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "classaction_savecldetail.action",				
				data : "cl_id=" + cl_id + "&cd_id=" + cd_id + "&cl_capacity=" + cl_capacity,
				success : function(response) {
					$('#cldetailFrm').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});		
		}
	}
	
	/*============================= CD REGISTRATION =============================*/
	
	/*----- CD LIST PAGE -----*/
	
	function editCDetail(cd_id, cd_name, cd_status){
		
		$("#cdtlList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classaction_cdetaileditfrm.action",				
			data : "cd_id=" + cd_id + "&cd_name=" + cd_name + "&cd_status=" + cd_status,
			success : function(response) {
				$('#cdtlList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addCDetail(){
		
		$("#cdtlList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "classaction_cdetailsavefrm.action",				
			data : "",
			success : function(response) {
				$('#cdtlList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- CD EDIT PAGE -----*/
	
	function updateCDetail(cd_id){
		
		var cd_name = $('#cd_name').val();
		var cd_status = $('#cd_status').val();
		
		if(cd_name == ""){
			document.getElementById("errMsg").innerHTML = "Class Detail is required.";
		} else {
		
			$("#cdtlList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "classaction_cdetailedit.action",				
				data : "cd_id=" + cd_id + "&cd_name=" + cd_name + "&cd_status=" + cd_status,
				success : function(response) {
					$('#cdtlList').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}

	/*----- CD CREATE -----*/
	
	function saveCDetail(){
		
		var cd_name = $('#cd_name').val();
		if(cd_name == ""){
			document.getElementById("errMsg").innerHTML = "Class Detail is required.";
		} else {
			$("#cdtlList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "classaction_cdetailsave.action",				
				data : "cd_name=" + cd_name,
				success : function(response) {
					$('#cdtlList').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}

