/**
 * 
 */
/*=================== EMAIL COMMUNICATION GENERAL ====================*/

	/*----- EMAIL COM TEMPLATE PAGE -----*/
	function studRsltList(){		
		$("#examrslt_displaytype").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "examrsltaction_studrsltlisttemp.action",		
			data : "",
			success : function(response) {
				document.getElementById('rsltAddFromArraw').style.display = "none";
				document.getElementById('rsltListArraw').style.display = "block";
				$('#examrslt_displaytype').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function studRsltAddFrm(){
		$("#examrslt_displaytype").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "examrsltaction_studrsltaddfrmtemp.action",				
			data : "",
			success : function(response) {				
				document.getElementById('rsltAddFromArraw').style.display = "block";
				document.getElementById('rsltListArraw').style.display = "none";
				$('#examrslt_displaytype').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	
	/*----- EMAIL FORM -----*/
	
	function sendAnEmail(){
		
		var from = $('#from').val();
		var to = $('#to').val();
		var subject = $('#subject').val();
		var body = $('#body').val();
		
		var emailValidator = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
				
		if(from == ""){
			document.getElementById("errMsg").innerHTML = "From is required";
		} else if(to == ""){
			document.getElementById("errMsg").innerHTML = "Receiver address is required";
		} else if(!emailValidator.test(to)){
			document.getElementById("errMsg").innerHTML = "Please insert valide email address";
		} else if(subject == ""){
			document.getElementById("errMsg").innerHTML = "Subject is required";
		} else if(body == ""){
			document.getElementById("errMsg").innerHTML = "Message body is required";
		} else {
		
			$("#emailBody").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "communication_sendAnEmail.action",				
				data : "from=" + from + "&to=" + to + "&subject=" + subject + "&body=" + body,
				success : function(response) {
					$('#emailBody').html(response);
				},
				error : function(e) {
					alert('Error : ' + e);
				}
			});
		}
		
	}
	
	function getSavedEmails(){
		var from = $('#from').val();	
		var subject = $('#subject').val();
		var body = $('#body').val();
		
		$("#emailBody").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "communication_getemails.action",				
			data : "from=" + from + "&subject=" + subject + "&body=" + body,
			success : function(response) {
				$('#emailBody').html(response);
			},
			error : function(e) {
				alert('Error : ' + e);
			}
		});
		
	}
	
	/*----- EMAIL LIST TEMP PAGE -----*/
	
	function studentsList(){
		$("#emailList").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "communication_getClassList.action",				
			data : "",
			success : function(response) {
				document.getElementById("stud").className = "header_btn_active";
				document.getElementById("teach").className = "header_btn_inactive";
				document.getElementById("nteach").className = "header_btn_inactive";
				$('#emailList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function teachersList(from, password, subject, body){
		$("#emailList").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "communication_getTeacherEmailList.action",				
			data : "from=" + from + "&password=" + password + "&subject=" + subject + "&body=" + body,
			success : function(response) {
				document.getElementById("stud").className = "header_btn_inactive";
				document.getElementById("teach").className = "header_btn_active";
				document.getElementById("nteach").className = "header_btn_inactive";
				$('#emailList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function nonacademicStuff(){
		$("#emailList").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "communication_getNonAcademicStuffEmailList.action",				
			data : "",
			success : function(response) {
				document.getElementById("stud").className = "header_btn_inactive";
				document.getElementById("teach").className = "header_btn_inactive";
				document.getElementById("nteach").className = "header_btn_active";
				$('#emailList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- GRADE LIST PER CLASS PAGE -----*/
	
	var prev_cdid = null;
	function parentListPerClassDetail(cl_id, cd_id, cl_name, cd_name){
		
		$("#stud_parent_list").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "communication_parentsperclassdetail.action",				
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
				$('#stud_parent_list').html(response);
				prev_cdid = cd_id;
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});		
	}
	
	/*----- NON ACADEMIC STUFF EMAIL LIST -----*/
	
	function addEmailIntoList(email, index){
		
		$.ajax({
			type : "POST",
			url : "communication_addToEmailList.action",				
			data : "to=" + email,
			success : function(response) {
				document.getElementById(index+"_e").innerHTML = "<img alt=\"\" src=\"images/checked.png\" style=\"width: 20px; cursor: pointer\" onclick=\"removeEmailFromList('"+email+"', '"+index+"')\"> ";				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}	
	
	function removeEmailFromList(email, index){		
		$.ajax({
			type : "POST",
			url : "communication_removeFromEmailList.action",				
			data : "to=" + email,
			success : function(response) {
				document.getElementById(index+"_e").innerHTML = "<img alt=\"\" src=\"images/check_box.png\" style=\"width: 20px; cursor: pointer\" onclick=\"addEmailIntoList('"+email+"', '"+index+"')\"> ";				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addAllNonAcademicEmailIntoList(from, password, subject, body){
		
		$("#emailBody").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "communication_selectAllNonAcademicEmails.action",				
			data : "from=" + from + "&password=" + password + "&subject=" + subject + "&body=" + body,
			success : function(response) {
				//document.getElementById(index+"_e").innerHTML = "<img alt=\"\" src=\"images/check_box.png\" style=\"width: 20px; cursor: pointer\" onclick=\"addEmailIntoList('"+email+"', '"+index+"')\"> ";
				$('#emailBody').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
		
	function emailToSelectedList(from, password, subject, body){
		$("#emailBody").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "communication_emailToSelectedList.action",				
			data : "from=" + from + "&password=" + password + "&subject=" + subject + "&body=" + body,
			success : function(response) {
				//document.getElementById(index+"_e").innerHTML = "<img alt=\"\" src=\"images/check_box.png\" style=\"width: 20px; cursor: pointer\" onclick=\"addEmailIntoList('"+email+"', '"+index+"')\"> ";
				$('#emailBody').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*---- STUDENT CLASS LIST PAGE -----*/
	
	var prev_clid = null;
	function gradeSelected(cl_id, cl_name){
			
		//document.getElementById("errMsg").innerHTML = "";
		$("#classDetail").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "communication_pergradelist.action",				
			data : "cl_id=" + cl_id + "&cl_name=" + cl_name,
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
				$('#classDetail').html(response);
				prev_clid = cl_id;
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});	
	}
	
	/*----- STUDENT PARENT LIST -----*/
	
//	function addEmailIntoList(email, index){
//		
//		$.ajax({
//			type : "POST",
//			url : "communication_addToEmailList.action",				
//			data : "to=" + email,
//			success : function(response) {
//				document.getElementById(index+"_e").innerHTML = "<img alt=\"\" src=\"images/checked.png\" style=\"width: 20px; cursor: pointer\" onclick=\"removeEmailFromList('"+email+"', '"+index+"')\"> ";				
//			},
//			error : function(e) {
//				alert('Error: ' + e);
//			}
//		});
//	}	
	
//	function removeEmailFromList(email, index){
//		
//		$.ajax({
//			type : "POST",
//			url : "communication_removeFromEmailList.action",				
//			data : "to=" + email,
//			success : function(response) {
//				document.getElementById(index+"_e").innerHTML = "<img alt=\"\" src=\"images/check_box.png\" style=\"width: 20px; cursor: pointer\" onclick=\"addEmailIntoList('"+email+"', '"+index+"')\"> ";				
//			},
//			error : function(e) {
//				alert('Error: ' + e);
//			}
//		});
//	}
	
	function addAllParentEmailIntoList(from, password, subject, body, cl_id, cd_id){
		
		$("#emailBody").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "communication_selectAllParentsEmails.action",				
			data : "from=" + from + "&password=" + password + "&subject=" + subject + "&body=" + body + "&cl_id=" + cl_id + "&cd_id=" + cd_id,
			success : function(response) {
				//document.getElementById(index+"_e").innerHTML = "<img alt=\"\" src=\"images/check_box.png\" style=\"width: 20px; cursor: pointer\" onclick=\"addEmailIntoList('"+email+"', '"+index+"')\"> ";
				$('#emailBody').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
		
//	function emailToSelectedList(from, password, subject, body){
//		$("#emailBody").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
//		$.ajax({
//			type : "POST",
//			url : "communication_emailToSelectedList.action",				
//			data : "from=" + from + "&password=" + password + "&subject=" + subject + "&body=" + body,
//			success : function(response) {
//				//document.getElementById(index+"_e").innerHTML = "<img alt=\"\" src=\"images/check_box.png\" style=\"width: 20px; cursor: pointer\" onclick=\"addEmailIntoList('"+email+"', '"+index+"')\"> ";
//				$('#emailBody').html(response);
//			},
//			error : function(e) {
//				alert('Error: ' + e);
//			}
//		});
//	}
	
	/*----- TEACHERS EMAIL LIST -----*/
	
//	function addEmailIntoList(email, index){
//		
//		$.ajax({
//			type : "POST",
//			url : "communication_addToEmailList.action",				
//			data : "to=" + email,
//			success : function(response) {
//				document.getElementById(index+"_e").innerHTML = "<img alt=\"\" src=\"images/checked.png\" style=\"width: 20px; cursor: pointer\" onclick=\"removeEmailFromList('"+email+"', '"+index+"')\"> ";				
//			},
//			error : function(e) {
//				alert('Error: ' + e);
//			}
//		});
//	}	
	
//	function removeEmailFromList(email, index){
//		
//		$.ajax({
//			type : "POST",
//			url : "communication_removeFromEmailList.action",				
//			data : "to=" + email,
//			success : function(response) {
//				document.getElementById(index+"_e").innerHTML = "<img alt=\"\" src=\"images/check_box.png\" style=\"width: 20px; cursor: pointer\" onclick=\"addEmailIntoList('"+email+"', '"+index+"')\"> ";				
//			},
//			error : function(e) {
//				alert('Error: ' + e);
//			}
//		});
//	}
	
	function addAllEmailIntoList(from, password, subject, body){
		
		$("#emailList").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "communication_selectAllEmails.action",				
			data : "from=" + from + "&password=" + password + "&subject=" + subject + "&body=" + body,
			success : function(response) {
				//document.getElementById(index+"_e").innerHTML = "<img alt=\"\" src=\"images/check_box.png\" style=\"width: 20px; cursor: pointer\" onclick=\"addEmailIntoList('"+email+"', '"+index+"')\"> ";
				$('#emailList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
		
//	function emailToSelectedList(from, password, subject, body){
//		$("#emailBody").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
//		$.ajax({
//			type : "POST",
//			url : "communication_emailToSelectedList.action",				
//			data : "from=" + from + "&password=" + password + "&subject=" + subject + "&body=" + body,
//			success : function(response) {
//				//document.getElementById(index+"_e").innerHTML = "<img alt=\"\" src=\"images/check_box.png\" style=\"width: 20px; cursor: pointer\" onclick=\"addEmailIntoList('"+email+"', '"+index+"')\"> ";
//				$('#emailBody').html(response);
//			},
//			error : function(e) {
//				alert('Error: ' + e);
//			}
//		});
//	}
