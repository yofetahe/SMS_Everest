/**
 * 
 */

function emailStudentQuarterResultToParents(cl_id, cd_id, si_id, at_id, stud_rank, academic_year, fname, mname, gname, cl_name, cd_name, homeroom_teacher_name, at_name, cur_date, erc_content, grand_quarter_total, average_quarter_mark){
	
	$("#exam_studlist").html("<div width=\"100%\" align=\"center\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "communication_emailstudentquarterresult.action",		
		data : "si_id=" + si_id + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&cl_id=" + cl_id + "&cd_id=" + cd_id + "&cl_name=" + cl_name + "&cd_name=" + cd_name + "&academic_year=" + academic_year + "&at_id=" + at_id + "&at_name=" + at_name + "&stud_rank=" + stud_rank + "&erc_content=" + erc_content + "&cur_date=" + cur_date + "&homeroom_teacher_name=" + homeroom_teacher_name + "&grand_quarter_total=" + grand_quarter_total + "&average_quarter_mark=" + average_quarter_mark,
		success : function(response) {
			$('#exam_studlist').html(response);				
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

function emailQuarterResultToAllStudentParents(cl_id, cd_id, at_id, cl_name, cd_name){
	
	$("#studPerGrade").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
	$.ajax({
		type : "POST",
		url : "communication_emailallstudentquarterresult_atonce.action",				
		data : "cl_id=" + cl_id + "&cd_id=" + cd_id + "&at_id=" + at_id + "&cl_name=" + cl_name + "&cd_name=" + cd_name,
		success : function(response) {
			$('#studPerGrade').html(response);
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}

function generateAllStudentCertificate(cl_id, cd_id, at_id, cl_name, cd_name, academic_year){
	
	var certificateRank = $("#certificateRank").val();
	
	$("#studPerGrade").html("<div width=\"100%\" align=\"center\"><img align=\"center\" src=\"images/loader.gif\"/> <br/> <span style=\"color: GREEN;\"> It might take longer time. It is processing huge data.</span> </div>");
	$.ajax({
		type : "POST",
		url : "examrsltaction_generateallstudentcertificate.action",				
		data : "cl_id=" + cl_id + "&cd_id=" + cd_id + "&at_id=" + at_id + "&cl_name=" + cl_name + "&cd_name=" + cd_name + "&academic_year=" + academic_year + "&stud_rank=" + certificateRank,
		success : function(response) {
			$('#studPerGrade').html(response);
		},
		error : function(e) {
			alert('Error: ' + e);
		}
	});
}