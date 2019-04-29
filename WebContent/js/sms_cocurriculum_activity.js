/**
 * 
 */
/*======================= CO-CURRICULUM ACTIVITY ========================*/

	/*----- COCURRICULUM TEMPLATE -----*/
	
	function clubactivity_create(){
		
		$("#cocurActPage").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "cocuract_activitycreate.action",				
			data : "",
			success : function(response) {
				$('#cocurActPage').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function member_create(){
		
		$("#cocurActPage").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "cocuract_membercreate.action",				
			data : "",
			success : function(response) {
				$('#cocurActPage').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function clubactivitycontrol_create(){
		
		$("#cocurActPage").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "cocuract_clubactivitycontrolcreate.action",				
			data : "",
			success : function(response) {
				$('#cocurActPage').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}

	/*=========== MEMEBER REGISTRATION ============*/
	
	/*----- MEMEBER TEMPLATE PAGE -----*/
	
	function newMemberCreateForm(clbid, clbname){
		
		$("#memContent").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "cocuract_memcreateform.action",				
			data : "clbid=" + clbid + "&clbname=" + clbname,
			success : function(response) {
				$('#memContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- MEMEBER LIST PAGE -----*/
	
	function memberDataEdit(cmid, clbid){
		
		$("#memContent").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "cocuract_memeditform.action",				
			data : "clbid=" + clbid + "&cm_id=" + cmid,
			success : function(response) {
				$('#memContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function memberEvaluation(cmid, clbid){
		$("#memContent").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "cocuract_memevalform.action",				
			data : "clbid=" + clbid + "&cm_id=" + cmid,
			success : function(response) {
				$('#memContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- MEMEBER EVALUATION CREATE -----*/
	
	function saveClubMemberEvaluation(cm_id, clbid){
		
		var pereval = $('#eval').val();
		
		$("#memContent").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "cocuract_memevaluation.action",				
			data : "cm_evaluation=" + pereval + "&cm_id=" + cm_id  + "&clbid=" + clbid,
			success : function(response) {
				$('#memContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
		
	}
	
	/*----- MEMBER EDIT PAGE -----*/
	
	function updateClubMember(cm_id, clbid){
		
		var studId = $('#studId').val();
		var rsn = $('#rsn').val();
		var cm_status = $('#cm_status').val();
		
		if(studId == ""){
			document.getElementById("errMsg").innerHTML = "Student must be selected.";
		} else if(rsn == ""){
			document.getElementById("errMsg").innerHTML = "Student's reason must be filled.";
		} else {
		
			$("#memContent").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "cocuract_memupdate.action",				
				data : "si_id=" + studId + "&cm_reasontojoin=" + rsn + "&cm_id=" + cm_id  + "&cm_status=" + cm_status + "&clbid=" + clbid,
				success : function(response) {
					$('#memContent').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	/*----- MEMBER CREATE PAGE -----*/
	
	function saveClubMember(clbid){
		
		var cl_id = $('#cl_id').val();
		var cd_id = $('#cd_id').val();
		var studId = $('#studId').val();
		var rsn = $('#rsn').val();
		var pereval = $('#pereval').val();
		
		if(cl_id == "0" || cd_id == "0" || studId == "0"){
			document.getElementById("errMsg").innerHTML = "Student must be selected.";
		} else if(rsn == ""){
			document.getElementById("errMsg").innerHTML = "Student's reason must be filled.";
		} else {
		
			$("#memContent").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "cocuract_memsave.action",				
				data : "si_id=" + studId + "&cm_reasontojoin=" + rsn + "&cm_evaluation=" + pereval + "&clbid=" + clbid,
				success : function(response) {
					$('#memContent').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function getClassDetailList(cl_id){
		
		$("#cdlist").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/30.GIF\"/></div>");		
		$.ajax({
			type : "POST",
			url : "cocuract_classdetaillist.action",				
			data : "cl_id=" + cl_id,
			success : function(response) {
				$('#cdlist').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getStudentListUnderSelectedClass(cd_id){
		
		var cl_id = $("#cl_id").val();
		
		$("#studlist").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/30.GIF\"/></div>");		
		$.ajax({
			type : "POST",
			url : "cocuract_studentlist.action",				
			data : "cl_id=" + cl_id + "&cd_id=" + cd_id,
			success : function(response) {
				$('#studlist').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*============== CLUB ACTIVITY CONTROL ================*/
	
	/*----- CLUB ACTIVITY CONTROL TEMPLATE PAGE -----*/
	
	function saveClubHeadComment(ca_id){
		
		var clbhead_com = $('#clbhead_com_'+ca_id).val();
		
		if(clbhead_com == ""){
			document.getElementById("errMsg_"+ca_id).innerHTML = "Comment must be given.";
		} else {
			
			$("#actContent").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "cocuract_saveclbheadcomment.action",				
				data : "ca_id=" + ca_id + "&clubhead_comment=" + clbhead_com,
				success : function(response) {
					$('#clubActContTemp').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	function editFormClubHeadComment(ca_id, clbheadcom){
		
		document.getElementById("comEditForm_" + ca_id).innerHTML = "<table><textarea id=\"clbheadcom_" + ca_id + "\" cols=\"30\" rows=\"5\">" + clbheadcom + "</textarea></table>";
		document.getElementById("editBtn_"+ ca_id).innerHTML = "<span id=\"span_" + ca_id + "\" onclick=\"updateClubHeadComment('" + ca_id +"')\">Update</span>";
		document.getElementById("span_"+ca_id).style.className = "curActContBtn";
	}
	
	function updateClubHeadComment(ca_id){
		
		var clbheadcom = $('#clbheadcom_'+ca_id).val();

		$("#comEditForm_"+ca_id).html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "cocuract_updateclbheadcomment.action",				
			data : "ca_id=" + ca_id + "&clubhead_comment=" + clbheadcom,
			success : function(response) {
				$('#comEditForm_'+ca_id).html(clbheadcom);
				document.getElementById("editBtn_"+ ca_id).innerHTML = "<span id=\"span_" + ca_id + "\" onclick=\"editFormClubHeadComment('" + ca_id +"', '"+ clbheadcom +"')\">Edit</span>";
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*============== CLUB ACTIVITY CONTROL ================*/
	
	/*----- CLUB ACTIVITY TEMPLATE PAGE -----*/
	
	function newActivityCreateForm(){
		
		$("#actContent").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "cocuract_activitycreateform.action",				
			data : "",
			success : function(response) {
				$('#actContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- CLUB ACTIVITY LIST PAGE -----*/
	
	function editActivityContent(ca_id, clb_id){
		
		$("#actContent").html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "cocuract_activityupdateform.action",				
			data : "ca_id=" + ca_id + "&clb_id=" + clb_id,
			success : function(response) {
				$('#actContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- CLUB ACTIVITY EDIT -----*/
	
	function updateActivities(ca_id, clb_id){
		
		var act_title = $('#act_title').val();
		var act_desc = $('#act_desc').val();
		var date_from = $('#date_from').val();
		var date_to = $('#date_to').val();
		var acyear = $('#acyear').val();
		var ca_status = $('#ca_status').val();
		
		if(act_title == ""){
			document.getElementById("errMsg").innerHTML = "Activity title couldn't be null.";
		} else if(act_desc == ""){
			document.getElementById("errMsg").innerHTML = "Activity description couldn't be null.";
		} else if(date_from == ""){
			document.getElementById("errMsg").innerHTML = "Date from couldn't be null.";
		} else if(date_to == ""){
			document.getElementById("errMsg").innerHTML = "Date to couldn't be null.";
		} else if(acyear == "0"){
			document.getElementById("errMsg").innerHTML = "Academic year must be selected.";
		} else {
			
			$("#cocurActPage").html("<img align=\"center\" src=\"images/loader.gif\"/>");
			$.ajax({
				type : "POST",
				url : "cocuract_updateclubact.action",				
				data : "ca_id=" + ca_id + "&ca_activity=" + act_title + "&ca_activity_desc=" + act_desc + "&ca_datefrom=" + date_from + "&ca_dateto=" + date_to + "&academic_year=" + acyear + "&ca_status=" + ca_status + "&clb_id=" + clb_id,
				success : function(response) {
					$('#cocurActPage').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});	
		}
	}
	
	/*----- CLUB ACTIVITY CREATE -----*/
	
	function saveActivities(){
		
		var act_title = $('#act_title').val();
		var act_desc = $('#act_desc').val();
		var date_from = $('#date_from').val();
		var date_to = $('#date_to').val();
		var acyear = $('#acyear').val();
		
		if(act_title == ""){
			document.getElementById("errMsg").innerHTML = "Activity title couldn't be null.";
		} else if(act_desc == ""){
			document.getElementById("errMsg").innerHTML = "Activity description couldn't be null.";
		} else if(date_from == ""){
			document.getElementById("errMsg").innerHTML = "Date from couldn't be null.";
		} else if(date_to == ""){
			document.getElementById("errMsg").innerHTML = "Date to couldn't be null.";
		} else if(acyear == "0"){
			document.getElementById("errMsg").innerHTML = "Academic year must be selected.";
		} else {
			
			$("#cocurActPage").html("<img align=\"center\" src=\"images/loader.gif\"/>");
			$.ajax({
				type : "POST",
				url : "cocuract_saveclubact.action",				
				data : "ca_activity=" + act_title + "&ca_activity_desc=" + act_desc + "&ca_datefrom=" + date_from + "&ca_dateto=" + date_to + "&academic_year=" + acyear,
				success : function(response) {
					$('#cocurActPage').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});	
		}
	}
	