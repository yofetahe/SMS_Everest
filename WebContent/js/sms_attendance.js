/**
 * 
 */
/*===================== ATTENDANCE =====================*/

	/*----- absent student list page -----*/
	
	function AddAbsentReason(att_id, indx){
		
		var reason = $("#rs_"+indx).val();
		
		if(reason == ""){
			
			document.getElementById("msg_"+indx).innerHTML = "Reason must be filled";
			
		} else {
		
			$("#reason_"+indx).html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "attendance_abserntreason.action",				
				data : "att_id=" + att_id + "&attendacen_reason=" + reason,
				success : function(response) {				
					$('#reason_'+indx).html("Reason:-"+reason);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	/*----- attendance list -----*/
	
	var prev_clid = null;
	function studnet_list(cl_id, cl_name){
		
		$("#classScheduleList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "attendance_studentlistpergrade.action",				
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
				$('#classScheduleList').html(response);
				prev_clid = cl_id;
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- student list per grade -----*/
	
	var qr_id = -1;
	function selectedQurter(qrid){
		qr_id = qrid;
	}

	function studAbsent(si_id, row, col, cl_id, cd_id){
		
		document.getElementById("errMsg").innerHTML = "";
		
		var dateSelected = document.getElementById("at_date").value;
		var divId = row + "" + col;
		var at_type = document.getElementById("at_type").value;
				
		if(dateSelected == ""){
			document.getElementById("errMsg").innerHTML = "Date is required.";
		} else if(at_type == 0){
			document.getElementById("errMsg").innerHTML = "Attendance type is required.";
		} else if(qr_id == -1){
			document.getElementById("errMsg").innerHTML = "Quarter is required.";
		} else {
			$("#"+divId).html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "attendance_absentstudent.action",				
				data : "cl_id=" + cl_id + "&cd_id=" + cd_id + "&si_id=" + si_id + "&attendance_date=" + dateSelected + "&attype_id=" + at_type + "&at_id=" + qr_id,
				success : function(response) {					
					$("#"+divId).html("<span style=\"font-size: 30px; color: #ff0000;\">X</span>");
				},
				error : function(e) {
					//alert('Error: ' + e);
					$("#"+divId).html("<span style=\"color: #ff0000;\">It is not saved. Try again.</span>");
				}
			});
		}
	}
	
	function attendance_list(cl_id, cd_id){
		
		document.getElementById("errMsg").innerHTML = "";
		var dateSelected = document.getElementById("at_date").value;
		
		if(dateSelected == ""){
			document.getElementById("errMsg").innerHTML = "Date is required.";
		} else {
			$("#attendanceList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "attendance_absentstudentlist.action",				
				data : "cl_id=" + cl_id + "&cd_id=" + cd_id + "&attendance_date=" + dateSelected,
				success : function(response) {					
					$("#attendanceList").html(response);
				},
				error : function(e) {
					//alert('Error: ' + e);
					$("#attendanceList").html("<span style=\"color: #ff0000;\">The list is not ready. Try again.</span>");
				}
			});
		}
	}
	
	function aggregate_absent_list(cl_id, cd_id){
		
		$("#attendanceList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "attendance_aggregatestudentabsentlist.action",				
			data : "cl_id=" + cl_id + "&cd_id=" + cd_id + "&at_id=" + qr_id,
			success : function(response) {					
				$("#attendanceList").html(response);
			},
			error : function(e) {
				//alert('Error: ' + e);
				$("#attendanceList").html("<span style=\"color: #ff0000;\">The list is not ready. Try again.</span>");
			}
		});
	}
	
	var refCounter = 0;
	function refreshStudentList(cl_id, cd_id){
		
		if(refCounter > 0){
			$("#attendanceList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
			$.ajax({
				type : "POST",
				url : "attendance_attendancestudlist.action",				
				data : "cl_id=" + cl_id + "&cd_id=" + cd_id,
				success : function(response) {					
					$('#attendanceList').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
		refCounter = refCounter + 1;
	}
	
	/*----- student list page -----*/
	
	var prev_cdid = null;
	function classDetail(cl_id, cd_id){
		
		$("#attStudList").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "attendance_studlist.action",				
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
				$('#attStudList').html(response);
				prev_cdid = cd_id;
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	