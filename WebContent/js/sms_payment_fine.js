/**
 * 
 */

/*======================== PAYMENT GENERAL =========================*/

	/*----- PAYMENT TEMPLATE PAGE -----*/

	function paymentInfo(){
		
		document.getElementById("todaysCollection").style.display = "none";
		document.getElementById("paymentInformation").style.display = "block";
		
		$("#payment_content").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "payment_info.action",				
			data : "",
			success : function(response) {
				$('#payment_content').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function paymentAdmin(){
		$("#payment_content").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "payment_admin.action",				
			data : "",
			success : function(response) {
				$('#payment_content').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function todaysCollection(){
		
		document.getElementById("todaysCollection").style.display = "block";
		document.getElementById("paymentInformation").style.display = "none";
		
		$("#payment_content").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "payment_todaysCollection.action",				
			data : "",
			success : function(response) {
				$('#payment_content').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*============ COLLECTION =============*/
	
	/*----- TODAYS COLLECTION PAGE -----*/
	
	function emailTodaysCollection(total_collection){
		$("#collBody").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "payment_emailTodaysCollection.action",				
			data : "report_bean.grand_total=" + total_collection,
			success : function(response) {
				$('#collBody').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*============ PAYMENT ADMIN =============*/
	
	/*----- payment admin template -----*/
	
	function paymentInfo_create(){
		$("#payAdmin").html("<img align=\"center\" src=\"../images/loader.gif\"/>");
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
		$("#payAdmin").html("<img align=\"center\" src=\"../images/loader.gif\"/>");
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
	
	/*----- payment admin edit -----*/
	
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
	
	/*============ PAYMENT INFO =============*/
	
	/*----- payment info template page -----*/
	
	var prev_clid = null;
	function gradeSelected(cl_id, clname){
		$("#student_list").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "payment_class_detail.action",				
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
	
	/*----- payment class detail page -----*/

	var preMonthSelectedNormalClass = 0;
	var preMonthSelectedSummer = 0;
	var preMonthSelectedTutorial = 0;
	
	var prev_cdid = null;
	function classDetailForPayment(clid, cd_id){
		
		preMonthSelectedNormalClass = 0;
		preMonthSelectedSummer = 0;
		preMonthSelectedTutorial = 0;
		
		$("#student_list_perdetial").html("<div width=\"100%\" align=\"center\"><img align=\"center\" src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "payment_pergrade_studlist.action",				
			data : "class_id=" + clid + "&cd_id=" + cd_id + "&payment_type=1",
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
	
	/*----- payment form edu more page -----*/
	
	function saveMulitpleMonthPayment(ac_year, cl_id, payment_type, si_id){
		
		var fm_receipt_no = $('#fm_receipt_no').val();
		var cust_vat_reg_no = $('#cust_vat_reg_no').val();
		var cust_tin = $('#cust_tin').val();
		var payment_mode = $('#payment_mode').val();
		
		var cheque_no = ""
		if(payment_mode == "Cheque"){
			cheque_no = $('#cheque_no').val();
		}
		
		if(fm_receipt_no == ""){
			document.getElementById("errorMsg").innerHTML = "Fisical machine receipt no is required.";
		} else if(payment_mode == "Cheque" && cheque_no == ""){
			document.getElementById("errorMsg").innerHTML = "Cheque No is required.";
		} else {
			
			if(payment_type == 1 || payment_type == 2){
				
				$("#student_list_payment").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
				$.ajax({
					type : "POST",
					url : "payment_save_edu_mulitpleMonth.action",				
					data : "class_id=" + cl_id + "&payment_type=" + payment_type + "&si_id=" + si_id + "&fiscal_machine_no=" + fm_receipt_no + "&cust_vat_reg_no=" + cust_vat_reg_no + "&cust_tin=" + cust_tin + "&payment_mode=" + payment_mode + "&cheque_no=" + cheque_no,
					success : function(response) {
						$('#student_list_payment').html(response);
					},
					error : function(e) {
						alert('Error: ' + e);
					}
				});
				
			} else if(payment_type == 3){
				
				$("#student_list_payment").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
				$.ajax({
					type : "POST",
					url : "payment_save_edu_mulitpleMonth_summer.action",				
					data : "class_id=" + cl_id + "&payment_type=" + payment_type + "&si_id=" + si_id  + "&fm_receipt_no=" + fm_receipt_no + "&cust_vat_reg_no=" + cust_vat_reg_no + "&cust_tin=" + cust_tin + "&payment_mode=" + payment_mode + "&cheque_no=" + cheque_no,
					success : function(response) {
						$('#student_list_payment').html(response);
					},
					error : function(e) {
						alert('Error: ' + e);
					}
				});
			}
		
		}
	}
	
	/*----- payment form edu page -----*/
	
	function saveEducationPayment(si_id, pc_id, month_id, clid, payment_type, snc_id){
		
		var payPenality = $('#payPenality').val();
		var totalPay = $('#totalPay').val();
		
		var fm_receipt_no = $('#fm_receipt_no').val();
		var cust_vat_reg_no = $('#cust_vat_reg_no').val();
		var cust_tin = $('#cust_tin').val();
		var payment_mode = $('#payment_mode').val();
		
		var cheque_no = ""
		if(payment_mode == "Cheque"){
			cheque_no = $('#cheque_no').val();
		}
		
		if(totalPay == ""){
			document.getElementById("errorMsg").innerHTML = "Payment information isnot set up!!!";
		} else if(fm_receipt_no == ""){
			document.getElementById("errorMsg").innerHTML = "Fisical machine receipt no is required.";
		} else if(payment_mode == "Cheque" && cheque_no == ""){
			document.getElementById("errorMsg").innerHTML = "Cheque No is required.";
		} else {
			
			$("#student_list_payment").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
			$.ajax({
				type : "POST",
				url : "payment_save_edu.action",				
				data : "si_id=" + si_id + "&month_id=" + month_id + "&pc_id=" + pc_id + "&penality_amount=" + payPenality + "&total_payment=" + totalPay + "&class_id=" + clid + "&payment_type=" + payment_type + "&snc_id=" + snc_id + "&fm_receipt_no=" + fm_receipt_no + "&cust_vat_reg_no=" + cust_vat_reg_no + "&cust_tin=" + cust_tin + "&payment_mode=" + payment_mode + "&cheque_no=" + cheque_no,
				success : function(response) {
					$('#student_list_payment').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});		
		}
	}
	
	
	function payMonthSelectedForNormalClass(month_id, mshort, mlong, initialMonth_id, si_id, pc_id, clid, payment_type, snc_id){
		
		if(month_id != initialMonth_id && preMonthSelectedNormalClass == 0){
			document.getElementById("mthSelErrMsg").innerHTML = "Please start from the first month.";
		} else if(preMonthSelectedNormalClass != 0 && preMonthSelectedNormalClass != month_id - 1){			
			document.getElementById("mthSelErrMsg").innerHTML = "Consicutive month must be selected";						
		} else {			
			$('#selectedMonthForPay').html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");		
			$.ajax({
				type : "POST",
				url : "payment_month_selected_normalclass.action",				
				data : "month_short=" + mshort + "&month_id=" + month_id + "&month_full=" + mlong + "&si_id=" + si_id + "&pc_id=" + pc_id + "&class_id=" + clid + "&payment_type=" + payment_type + "&snc_id=" + snc_id,
				success : function(response) {
					$('#selectedMonthForPay').html(response);
					//document.getElementById(mshort).style.display = "none";	
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
			preMonthSelectedNormalClass = month_id;
		}
	}
	
	function removeSelectedPaymentMonth(payment_type){
		
		preMonthSelectedNormalClass = 0;
		preMonthSelectedSummer = 0;
		preMonthSelectedTutorial = 0;
		
		$("#selectedMonthForPay").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
		$.ajax({
			type : "POST",
			url : "payment_month_remove.action",				
			data : "payment_type=" + payment_type,
			success : function(response) {				
				$('#selectedMonthForPay').html("");
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function checkPaymentMode(mode){
		if(mode == "Cheque"){
			document.getElementById("chequeNo").style.display = "block";			
		}
		if(mode == "Cash"){
			document.getElementById("chequeNo").style.display = "none";
		}
	}
	
	/*----- payment form mat page -----*/
	
	function saveMaterialPayment(si_id, clid, payment_type){
		
		var ptm_id = $('#ptm_id').val();
		var pay_amount = $('#payRate').val();
		
		if(ptm_id == 0){
			document.getElementById("errorMsg").innerHTML = "Please select the material.";
		} else {
			$("#student_list_payment").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
			$.ajax({
				type : "POST",
				url : "payment_save_mat.action",				
				data : "si_id=" + si_id + "&pay_amount=" + pay_amount + "&class_id=" + clid + "&payment_type=" + payment_type,
				success : function(response) {
					$('#student_list_payment').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});		
		}
	}
	
	function materialStatus(ptm_id, si_id, clid, payment_type, material_pay_amount, pcm_id, ptm_name){
		// 1 means selected and 0 mean disselected		
		var indx = $('#'+ ptm_id + 'index').val();
		var id = ptm_id + 'index';
		
		if(indx == 0){
			document.getElementById(id).value = "1";
			selectMaterial(ptm_id, si_id, clid, payment_type, material_pay_amount, pcm_id, ptm_name);
		} else {
			document.getElementById(id).value = "0";
			disselectMaterial(ptm_id, si_id, clid, payment_type, material_pay_amount, pcm_id, ptm_name);			
		}
	}
	
	var rowCount = 0;
	function selectMaterial(ptm_id, si_id, clid, payment_type, material_pay_amount, pcm_id, ptm_name){
		$.ajax({
			type : "POST",
			url : "payment_add_selected_mat.action",				
			data : "ptm_id=" + ptm_id + "&si_id=" + si_id + "&class_id=" + clid + "&payment_type=" + payment_type + "&material_pay_amount=" + material_pay_amount + "&pcm_id=" + pcm_id + "&ptm_name=" + ptm_name,
			success : function(response) {
				rowCount = rowCount + 1;
				document.getElementById(ptm_id + "check").innerHTML = "<img alt=\"\" src=\"images/checked.png\" style=\"width: 20px;\"> ";
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});		
	}
	
	function disselectMaterial(ptm_id, si_id, clid, payment_type, material_pay_amount, pcm_id, ptm_name){
		
		$.ajax({
			type : "POST",
			url : "payment_remove_selected_mat.action",				
			data : "ptm_id=" + ptm_id + "&si_id=" + si_id + "&class_id=" + clid + "&payment_type=" + payment_type + "&material_pay_amount=" + material_pay_amount + "&pcm_id=" + pcm_id + "&ptm_name=" + ptm_name,
			success : function(response) {
				rowCount = rowCount - 1;
				document.getElementById(ptm_id + "check").innerHTML = "<img alt=\"\" src=\"images/check_box.png\" style=\"width: 20px;\"> ";
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
		
	}
	
	function saveSelectedMaterialPayment(si_id, clid, payment_type, fname, mname, gname){
		
		var fm_receipt_no = $('#fm_receipt_no').val();
		var cust_vat_reg_no = $('#cust_vat_reg_no').val();
		var cust_tin = $('#cust_tin').val();
		var payment_mode = $('#payment_mode').val();
		
		var cheque_no = ""
		if(payment_mode == "cheque"){
			cheque_no = $('#cheque_no').val();
		}
		
		if(rowCount ==  0){
			document.getElementById("errorMessage").innerHTML = "There is no selected material.";
		} else if(fm_receipt_no == ""){
			document.getElementById("errorMessage").innerHTML = "Fisical machine receipt no is required.";
		} else if(payment_mode == "Cheque" && cheque_no == ""){
			document.getElementById("errorMessage").innerHTML = "Cheque No is required.";
		} else {
			$.ajax({
				type : "POST",
				url : "payment_save_selected_material.action",				
				data : "si_id=" + si_id + "&class_id=" + clid + "&payment_type=" + payment_type + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&fm_receipt_no=" + fm_receipt_no + "&cust_vat_reg_no=" + cust_vat_reg_no + "&cust_tin=" + cust_tin + "&payment_mode=" + payment_mode + "&cheque_no=" + cheque_no,
				success : function(response) {
					$('#student_list_payment').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function getSelectedMaterialPrice(ptm_id, cl_id){
		
		$("#payAmount").html("<img align=\"center\" src=\"images/30.GIF\"/>");		
		$.ajax({
			type : "POST",
			url : "payment_material_cost.action",				
			data : "ptm_id=" + ptm_id + "&class_id=" + cl_id,
			success : function(response) {				
				$('#payAmount').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- payment form material -----*/
	
	function checkPaymentMode(mode){
		if(mode == "Cheque"){
			document.getElementById("chequeNo").style.display = "block";			
		}
		if(mode == "Cash"){
			document.getElementById("chequeNo").style.display = "none";
		}
	}
	
	
	/*----- payment form registration fee -----*/
	
	function saveRegistrationFee(si_id, clid, pc_id){
		
		var pay_amount = $('#payRate').val();
		var other_payment = $('#other_fee').val();
		
		var fm_receipt_no = $('#fm_receipt_no').val();
		var cust_vat_reg_no = $('#cust_vat_reg_no').val();
		var cust_tin = $('#cust_tin').val();
		var payment_mode = $('#payment_mode').val();
		
		var cheque_no = ""
		if(payment_mode == "Cheque"){
			cheque_no = $('#cheque_no').val();
		}
		
		if(pay_amount == ""){
			document.getElementById("errorMsg").innerHTML = "Payment information isnot set up!!!";
		} else if(fm_receipt_no == ""){
			document.getElementById("errorMsg").innerHTML = "Fisical machine receipt no is required.";
		} else if(payment_mode == "Cheque" && cheque_no == ""){
			document.getElementById("errorMsg").innerHTML = "Cheque No is required.";
		} else {
			
			$("#student_list_payment").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
			$.ajax({
				type : "POST",
				url : "payment_save_payRegistrationFee.action",				
				data : "si_id=" + si_id + "&pc_id=" + pc_id +  "&class_id=" + clid + "&total_payment=" + pay_amount + "&fm_receipt_no=" + fm_receipt_no + "&cust_vat_reg_no=" + cust_vat_reg_no + "&cust_tin=" + cust_tin + "&payment_mode=" + payment_mode + "&cheque_no=" + cheque_no + "&other_payment=" + other_payment,
				success : function(response) {
					$('#student_list_payment').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
//	function checkPaymentMode(mode){
//		if(mode == "Cheque"){
//			document.getElementById("chequeNo").style.display = "block";			
//		}
//		if(mode == "Cash"){
//			document.getElementById("chequeNo").style.display = "none";
//		}
//	}

	/*----- payment form summer page -----*/

	function saveSummerPayment(si_id, pc_id, month_id, clid, payment_type){
		
		var payPenality = $('#payPenality').val();
		var totalPay = $('#totalPay').val();
		
		var fm_receipt_no = $('#fm_receipt_no').val();
		var cust_vat_reg_no = $('#cust_vat_reg_no').val();
		var cust_tin = $('#cust_tin').val();
		var payment_mode = $('#payment_mode').val();
		
		var cheque_no = ""
		if(payment_mode == "cheque"){
			cheque_no = $('#cheque_no').val();
		}
		
		if(totalPay == ""){
			document.getElementById("errorMsg").innerHTML = "Payment information isnot set up!!!";
		} else if(fm_receipt_no == ""){
			document.getElementById("errorMsg").innerHTML = "Fisical machine receipt no is required.";
		} else if(payment_mode == "Cheque" && cheque_no == ""){
			document.getElementById("errorMsg").innerHTML = "Cheque No is required.";
		} else {
			
			$("#student_list_payment").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
			$.ajax({
				type : "POST",
				url : "payment_save_sum.action",				
				data : "si_id=" + si_id + "&month_id=" + month_id + "&pc_id=" + pc_id + "&penality_amount=" + payPenality + "&total_payment=" + totalPay + "&class_id=" + clid + "&payment_type=" + payment_type + "&fm_receipt_no=" + fm_receipt_no + "&cust_vat_reg_no=" + cust_vat_reg_no + "&cust_tin=" + cust_tin + "&payment_mode=" + payment_mode + "&cheque_no=" + cheque_no,
				success : function(response) {
					$('#student_list_payment').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});		
		}
	}
	
	var preMonthSelectedSummer = 0;
	function payMonthSelectedForSummer(month_id, mshort, mlong, initialMonth_id, si_id, pc_id, clid, payment_type){
		
		if(month_id != initialMonth_id && preMonthSelectedSummer == 0){
			document.getElementById("mthSelErrMsg").innerHTML = "Please start from the first month.";
		} else if(preMonthSelectedSummer != 0 && preMonthSelectedSummer != month_id - 1){			
			document.getElementById("mthSelErrMsg").innerHTML = "Consicutive month must be selected";						
		} else {			
			$('#selectedMonthForPay').html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");		
			$.ajax({
				type : "POST",
				url : "payment_month_selected_summer.action",				
				data : "month_short=" + mshort + "&month_id=" + month_id + "&month_full=" + mlong + "&si_id=" + si_id + "&pc_id=" + pc_id + "&class_id=" + clid + "&payment_type=" + payment_type,
				success : function(response) {
					$('#selectedMonthForPay').html(response);
					//document.getElementById(mshort).style.display = "none";	
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
			preMonthSelectedSummer = month_id;
		}
	}
	
//	function removeSelectedPaymentMonth(){
//		preMonthSelected = 0;
//		$("#selectedMonthForPay").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
//		$.ajax({
//			type : "POST",
//			url : "payment_month_remove.action",				
//			data : "",
//			success : function(response) {				
//				$('#selectedMonthForPay').html("");
//			},
//			error : function(e) {
//				alert('Error: ' + e);
//			}
//		});
//	}
//	
//	function checkPaymentMode(mode){
//		if(mode == "Cheque"){
//			document.getElementById("chequeNo").style.display = "block";			
//		}
//		if(mode == "Cash"){
//			document.getElementById("chequeNo").style.display = "none";
//		}
//	}
	
	/*----- payment form tutorial page -----*/
	
	function saveTutorialPayment(si_id, pc_id, month_id, clid, payment_type){
		
		var payPenality = $('#payPenality').val();
		var totalPay = $('#totalPay').val();
		
		var fm_receipt_no = $('#fm_receipt_no').val();
		var cust_vat_reg_no = $('#cust_vat_reg_no').val();
		var cust_tin = $('#cust_tin').val();
		var payment_mode = $('#payment_mode').val();
		
		var cheque_no = ""
		if(payment_mode == "cheque"){
			cheque_no = $('#cheque_no').val();
		}
		
		if(totalPay == ""){
			document.getElementById("errorMsg").innerHTML = "Payment information must be set first.";
		} else if(fm_receipt_no == ""){
			document.getElementById("errorMsg").innerHTML = "Fisical machine receipt no is required.";
		} else if(payment_mode == "Cheque" && cheque_no == ""){
			document.getElementById("errorMsg").innerHTML = "Cheque No is required.";
		} else {
			$("#student_list_payment").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
			$.ajax({
				type : "POST",
				url : "payment_save_tut.action",				
				data : "si_id=" + si_id + "&month_id=" + month_id + "&pc_id=" + pc_id + "&penality_amount=" + payPenality + "&total_payment=" + totalPay + "&class_id=" + clid + "&payment_type=" + payment_type  + "&fm_receipt_no=" + fm_receipt_no + "&cust_vat_reg_no=" + cust_vat_reg_no + "&cust_tin=" + cust_tin + "&payment_mode=" + payment_mode + "&cheque_no=" + cheque_no + "&snc_id=",
				success : function(response) {
					$('#student_list_payment').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});		
		}
	}
	
	var preMonthSelectedTutorial = 0;
	function payMonthSelectedForTutorial(month_id, mshort, mlong, initialMonth_id, si_id, pc_id, clid, payment_type){
		
		if(month_id != initialMonth_id && preMonthSelectedTutorial == 0){
			
			document.getElementById("mthSelErrMsg").innerHTML = "Please start from the first month.";
			
		} else if(preMonthSelectedTutorial != 0 && preMonthSelectedTutorial != month_id - 1){	
			
			document.getElementById("mthSelErrMsg").innerHTML = "Consicutive month must be selected";
			
		} else {			
			
			$('#selectedMonthForPay').html("<div style=\"width: auto\" align=\"center\"><img src=\"images/loader.gif\"/></div>");		
			$.ajax({
				type : "POST",
				url : "payment_month_selected_tutorial.action",				
				data : "month_short=" + mshort + "&month_id=" + month_id + "&month_full=" + mlong + "&si_id=" + si_id + "&pc_id=" + pc_id + "&class_id=" + clid + "&payment_type=" + payment_type,
				success : function(response) {
					$('#selectedMonthForPay').html(response);
					//document.getElementById(mshort).style.display = "none";	
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
			preMonthSelectedTutorial = month_id;
		}
	}
	
//	function removeSelectedPaymentMonth(){
//		preMonthSelected = 0;
//		$("#selectedMonthForPay").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
//		$.ajax({
//			type : "POST",
//			url : "payment_month_remove.action",				
//			data : "",
//			success : function(response) {				
//				$('#selectedMonthForPay').html("");
//			},
//			error : function(e) {
//				alert('Error: ' + e);
//			}
//		});
//	}
//	
//	function checkPaymentMode(mode){
//		if(mode == "Cheque"){
//			document.getElementById("chequeNo").style.display = "block";			
//		}
//		if(mode == "Cash"){
//			document.getElementById("chequeNo").style.display = "none";
//		}
//	}
	
	/*----- payment material student list -----*/
	
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
	
	/*----- payment regfee student list -----*/
	
	function getPaidRegistrationReceipt(siid, fname, mname, gname){
		
		var clid = $('#clid').val();
		
		$("#student_list_payment").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
		$.ajax({
			type : "POST",
			url : "payment_get_regfee_paid_receipt.action",				
			data : "si_id=" + siid + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&class_id=" + clid,
			success : function(response) {
				$('#student_list_payment').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
		
	function payRegistrationFee(siid, fname, mname, gname){
		
		var clid = $('#clid').val();
		
		$("#student_list_payment").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
		$.ajax({
			type : "POST",
			url : "payment_payRegistrationFee.action",				
			data : "si_id=" + siid + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&pt_id=" + 5 +  "&class_id=" + clid,
			success : function(response) {
				$('#student_list_payment').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- payment selected month -----*/
	
	function addAdditionalMonthPayment(si_id, pc_id, clid, payment_type, snc_id){
		
		$("#paymentForMoreMonth").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
		$.ajax({
			type : "POST",
			url : "payment_save_edu_morethanamonth.action",				
			data : "si_id=" + si_id + "&pc_id=" + pc_id + "&class_id=" + clid + "&payment_type=" + payment_type + "&snc_id=" + snc_id,
			success : function(response) {
				$('#paymentForMoreMonth').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- payment student history per month -----*/
	
	function showStudentPaymentHistory(si_id, fname, mname, gname, cl_id, cd_id){
		
		$("#student_list_payment").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
		$.ajax({
			type : "POST",
			url : "payment_showStudentPaymentHistory.action",				
			data : "si_id=" + si_id + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&class_id=" + cl_id + "&cd_id=" + cd_id + "&payment_type=1",
			success : function(response) {
				$('#student_list_payment').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getPaidReceipt(month, siid, fname, mname, gname, pt_id){
		
		var clid = $('#clid').val();
		
		$("#student_list_payment").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
		$.ajax({
			type : "POST",
			url : "payment_get_edu_paid_receipt.action",				
			data : "si_id=" + siid + "&month_id=" + month + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&payment_type=" + 1 + "&class_id=" + clid,
			success : function(response) {
				$('#student_list_payment').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
		
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
	
	/*----- payment student list -----*/
	
//	function showStudentPaymentHistory(si_id, fname, mname, gname, cl_id, cd_id){
//		
//		$("#student_list_payment").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
//		$.ajax({
//			type : "POST",
//			url : "payment_showStudentPaymentHistory.action",				
//			data : "si_id=" + si_id + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&class_id=" + cl_id + "&cd_id=" + cd_id,
//			success : function(response) {
//				$('#student_list_payment').html(response);
//			},
//			error : function(e) {
//				alert('Error: ' + e);
//			}
//		});
//	}
	
//	function getPaidReceipt(month, siid, fname, mname, gname, pt_id){
//		
//		var clid = $('#clid').val();
//		
//		$("#student_list_payment").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
//		$.ajax({
//			type : "POST",
//			url : "payment_get_edu_paid_receipt.action",				
//			data : "si_id=" + siid + "&month_id=" + month + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&payment_type=" + 1 + "&class_id=" + clid,
//			success : function(response) {
//				$('#student_list_payment').html(response);
//			},
//			error : function(e) {
//				alert('Error: ' + e);
//			}
//		});
//	}
		
//	function selectedMonth(month, siid, fname, mname, gname, pt_id){
//		
//		var clid = $('#clid').val();
//		
//		$("#student_list_payment").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
//		$.ajax({
//			type : "POST",
//			url : "payment_form_edu.action",				
//			data : "si_id=" + siid + "&month=" + month + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&payment_type=" + 1 + "&class_id=" + clid,
//			success : function(response) {
//				$('#student_list_payment').html(response);
//			},
//			error : function(e) {
//				alert('Error: ' + e);
//			}
//		});
//	}
	
	/*----- payment student page -----*/
	
	function registrationFee(clid, cd_id){
		
		$("#student_list_payment").html("<div width=\"100%\" align=\"center\"><img src=\"images/loader.gif\"/></div>");
		$.ajax({
			type : "POST",
			url : "payment_regfee_student_list.action",				
			data : "class_id=" + clid + "&cd_id=" + cd_id,
			success : function(response) {
				document.getElementById("regFee").style.backgroundColor = '#3d6e9f';
				document.getElementById("regFee").style.color = '#fff';
				
				document.getElementById("eduPay").style.backgroundColor = 'transparent';
				document.getElementById("eduPay").style.color = '#3d6e9f';
				
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

	function eduPayment(clid, cd_id){
		
		preMonthSelectedNormalClass = 0;
		
		$("#student_list_payment").html("<div width=\"100%\" align=\"center\"><img src=\"images/loader.gif\"/></div>");		
		$.ajax({
			type : "POST",
			url : "payment_student_list.action",				
			data : "class_id=" + clid + "&pt_id=1" + "&cd_id=" + cd_id + "&payment_type=1",
			success : function(response) {
				document.getElementById("regFee").style.backgroundColor = 'transparent';
				document.getElementById("regFee").style.color = '#3d6e9f';
				
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
		
		preMonthSelectedTutorial = 0;
		
		$("#student_list_payment").html("<div width=\"100%\" align=\"center\"><img src=\"images/loader.gif\"/></div>");		
		$.ajax({
			type : "POST",
			url : "payment_tutorial_student_list.action",				
			data : "class_id=" + clid + "&pt_id=2" + "&cd_id=" + cd_id,
			success : function(response) {
				document.getElementById("regFee").style.backgroundColor = 'transparent';
				document.getElementById("regFee").style.color = '#3d6e9f';
				
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
		
		preMonthSelectedSummer = 0;
		
		$("#student_list_payment").html("<div width=\"100%\" align=\"center\"><img src=\"images/loader.gif\"/></div>");		
		$.ajax({
			type : "POST",
			url : "payment_summer_student_list.action",				
			data : "class_id=" + clid + "&pt_id=3" + "&cd_id=" + cd_id,
			success : function(response) {
				document.getElementById("regFee").style.backgroundColor = 'transparent';
				document.getElementById("regFee").style.color = '#3d6e9f';
				
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
			url : "payment_material_student_list.action",				
			data : "class_id=" + clid  + "&pt_id=4" + "&cd_id=" + cd_id,
			success : function(response) {
				document.getElementById("regFee").style.backgroundColor = 'transparent';
				document.getElementById("regFee").style.color = '#3d6e9f';
				
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
	
	/*----- payment summer student list page -----*/
	
	function selectedMonthForSummer(month, siid, fname, mname, gname){
		var clid = $('#clid').val();
		
		$("#student_list_payment").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
		$.ajax({
			type : "POST",
			url : "payment_form_sum.action",				
			data : "si_id=" + siid + "&month=" + month + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&payment_type=" + 3 + "&class_id=" + clid,
			success : function(response) {
				$('#student_list_payment').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*----- payment tutorial student list -----*/
	
//	function getPaidReceipt(month, siid, fname, mname, gname, pt_id){
//		
//		var clid = $('#clid').val();
//		
//		$("#student_list_payment").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
//		$.ajax({
//			type : "POST",
//			url : "payment_get_edu_paid_receipt.action",				
//			data : "si_id=" + siid + "&month_id=" + month + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&payment_type=" + 1 + "&class_id=" + clid,
//			success : function(response) {
//				$('#student_list_payment').html(response);
//			},
//			error : function(e) {
//				alert('Error: ' + e);
//			}
//		});
//	}
		
	function selectedMonthForTutorial(month, siid, fname, mname, gname){
		var clid = $('#clid').val();
		
		$("#student_list_payment").html("<div align=\"center\" width=\"100%\"><img align=\"center\" src=\"images/loader.gif\"/></div>");		
		$.ajax({
			type : "POST",
			url : "payment_form_tut.action",				
			data : "si_id=" + siid + "&month=" + month + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&payment_type=" + 2 + "&class_id=" + clid,
			success : function(response) {
				$('#student_list_payment').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	