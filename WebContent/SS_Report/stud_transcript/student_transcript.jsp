<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<style type="text/css">
	#trans_header{
		border-bottom-color: black;
		border-bottom-style: double;
		border-bottom-width: thick;
		padding-bottom: 10px;
	}
	
	#trans_footer{
		border-top-color: black;
		border-top-style: double;
		border-top-width: thick;
		padding-top: 5px;
	}
	.qrt_num{
		text-align: center;
		width: 8%;
	}
	.table_header_content{
		text-align: center;
	}
	.remarksNotes{
		line-height: 20px;
	}
	.result_cell{
		text-align: center;
	}
	.blueColor{
		color: rgb(9, 180, 224);
	}
	.redColor{
		color: rgb(216, 51, 47);
	}
</style>


<!-- Datatable -->
        <style type="text/css" title="currentStyle">
            @import "datatable/datatable_2/media/css/demo_page.css";
            @import "datatable/datatable_2/media/css/demo_table.css";
            @import "media/css/TableTools.css";
        </style>
        <script type="text/javascript" charset="utf-8" src="datatable/datatable_2/media/js/jquery.js"></script>
        <script type="text/javascript" charset="utf-8" src="datatable/datatable_2/media/js/jquery.dataTables.js"></script>
        <script type="text/javascript" charset="utf-8" src="media/js/ZeroClipboard.js"></script>
        <script type="text/javascript" charset="utf-8" src="media/js/TableTools.js"></script>
        <script type="text/javascript" charset="utf-8">
            $(document).ready( function () {
                $('#studTranscript').dataTable( {
                    "sDom": 'T<"clear">rt'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

<div style="width: 100%;" align="center">
<!-- Sorry, Student Transcript result is under construction. -->

<table width="100%" cellpadding="0" cellspacing="0" id="studTranscript"><thead><tr><td>

	<div style="position: relative; min-height: 26cm; min-width: 18cm;">
	
		<div id="trans_header" style="width: 100%">
		
			<table cellpadding="0" cellspacing="0" width="100%" style="line-height: 20px;" border="0">
				<tr>
					<td align="right" style="padding-right: 5px;" rowspan="3" width="10%" valign="middle"> 
						<img alt="schoolLogo" src="images/everest_logo.jpg" width="60px;">
					</td>				
					<td align="left" style="font-size: 22px; font-weight: bold; padding-top: 5px; padding-left: 5px;"> 
						<%-- <s:text name='global.schoolnameoromiffa'/> --%>
						MANA BARUMSAA <span class="redColor">YUUZ</span> <span class="blueColor">EVARAST</span>
					</td>
					<td align="right" style="padding-right: 5px; font-size: 16px;" rowspan="3" width="40%" valign="middle"> 
						&nbsp;&nbsp;&nbsp; We Strive for success!
					</td>
				</tr>
				<tr>
					<td align="left" style="font-size: 24px; font-weight: bold; padding-top: 5px; padding-left: 5px;"> 
						<%-- <s:text name='global.schoolnameamharic'/>  --%>
						<span class="blueColor">&#4772;&#4712;&#4648;&#4661;&#4725;</span> <span class="redColor">&#4841;&#4829;</span> &#4768;&#4779;&#4851;&#4634;
					</td>
				</tr>
				<tr>
					<td align="left" style="font-size: 24px; font-weight: bold; padding-top: 5px; padding-left: 5px;"> 
						<%-- <s:text name='global.schoolname'/> --%>
						<span class="blueColor">EVEREST</span> <span class="redColor">YOUTH</span> <span>ACADEMY</span> 
					</td>
				</tr>					
			</table>
		</div>
		
		<div id="trans_body" style="width: 100%; padding-top: 5px; padding-bottom: 10px;">
			
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td style="text-align: right; padding-top: 5px;"> 
						Date: ________________________
					</td>
				</tr>
				<tr>
					<td style="text-align: right; padding-top: 10px;"> 
						Ref. No.: ________________________
					</td>
				</tr>					
			</table>
			
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td style="font-size: 30px; font-weight: bold; text-align: left;" colspan="2"> 
						STUDENT TRANSCRIPT
					</td>
					<td style="padding-top: 5px;" rowspan="4" align="right"> 
						<div style="border: thin; border-color: silver; border-style: solid; padding-bottom: 5px; padding-top: 5px; background-color: #f5f5f5;" align="center">
							<img alt="photo" src="imageRetrieveServlet?si_id=${stud_bean.si_id}" width="90px">
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding-top: 5px;" colspan="2" width="80%"> 
						<b>Student Name:</b> <u> &nbsp; <s:property value="studList[0].fname"/> <s:property value="studList[0].mname"/> <s:property value="studList[0].gname"/> &nbsp; </u>
					</td>
				</tr>
				<tr>
					<td style="padding-top: 5px;" align="left"> 
						<b>Sex:</b> <u> &nbsp; <s:set var="sex" value="studList[0].sex"/><s:if test="%{#sex == \"F\"}">Female</s:if><s:else>Male</s:else> &nbsp; </u>
					</td>
					<td style="padding-top: 5px;" align="left"> 
						<b>Age:</b> <u> &nbsp; <s:property value="studList[0].age"/> &nbsp; </u>
					</td>
				</tr>
				<tr>
					<td style="padding-top: 5px;" align="left"> 
						<b>Year of Admission:</b> <u> &nbsp; <c:out value="${value_from}"/> E.C &nbsp; </u>
					</td>
					<td style="padding-top: 5px;" align="left"> 
						<b>Year of Leaving:</b> <u> &nbsp; <c:out value="${value_to}"/> E.C &nbsp; </u>
					</td>
				</tr>					
			</table>
			
			<div style="padding-top: 2px;">&nbsp;</div>
			
			<table width="100%" cellpadding="0" cellspacing="0" border="1">
				<tr>
					<td rowspan="3" class="table_header_content">
						SUBJECT
					</td>					
					<td colspan="5" class="table_header_content">
						GRADE <c:out value="${category_1_grade}"/> <br/>
						YEAR: <c:out value="${value_from}"/> E.C
					</td>
					<td colspan="5" class="table_header_content">
						GRADE <c:out value="${category_2_grade}"/> <br/>
						YEAR: <c:out value="${value_to}"/> E.C
					</td>
				</tr>
				<tr>					
					<td colspan="5" style="text-align: center;">
						QUARTERS
					</td>
					<td colspan="5" style="text-align: center;">
						QUARTERS
					</td>
				</tr>
				<tr>					
					<td class="qrt_num"> I </td>
					<td class="qrt_num"> II </td>
					<td class="qrt_num"> III </td>
					<td class="qrt_num"> IV </td>
					<td class="qrt_num"> AV </td>
					
					<td class="qrt_num"> I </td>
					<td class="qrt_num"> II </td>
					<td class="qrt_num"> III </td>
					<td class="qrt_num"> IV </td>
					<td class="qrt_num"> AV </td>
				</tr>
				<c:forEach var="sub" items="${subList}" varStatus="stat">
					<tr>						
						<td style="line-height: 20px; padding-left: 5px;"> <c:out value="${sub.sub_name}"/> </td>
						
						<td class="result_cell"> <c:choose><c:when test="${gradenine_trans_rslt[stat.index].qrt_one_total == \"0.0\"}">-</c:when><c:otherwise> <c:out value="${gradenine_trans_rslt[stat.index].qrt_one_total}"/> </c:otherwise></c:choose> </td>
						<td class="result_cell"> <c:choose><c:when test="${gradenine_trans_rslt[stat.index].qrt_two_total == \"0.0\"}">-</c:when><c:otherwise> <c:out value="${gradenine_trans_rslt[stat.index].qrt_two_total}"/> </c:otherwise></c:choose> </td>
						<td class="result_cell"> <c:choose><c:when test="${gradenine_trans_rslt[stat.index].qrt_three_total == \"0.0\"}">-</c:when><c:otherwise> <c:out value="${gradenine_trans_rslt[stat.index].qrt_three_total}"/> </c:otherwise></c:choose> </td>
						<td class="result_cell"> <c:choose><c:when test="${gradenine_trans_rslt[stat.index].qrt_four_total == \"0.0\"}">-</c:when><c:otherwise> <c:out value="${gradenine_trans_rslt[stat.index].qrt_four_total}"/> </c:otherwise></c:choose> </td>
						<td class="result_cell"> <c:choose><c:when test="${gradenine_trans_rslt[stat.index].average_quarter_mark == \"NaN\"}">-</c:when><c:otherwise> <c:out value="${gradenine_trans_rslt[stat.index].average_quarter_mark}"/> </c:otherwise></c:choose> </td>
						
						<td class="result_cell"> <c:choose><c:when test="${gradeten_trans_rslt[stat.index].qrt_one_total == \"0.0\"}">-</c:when><c:otherwise> <c:out value="${gradeten_trans_rslt[stat.index].qrt_one_total}"/> </c:otherwise></c:choose> </td>
						<td class="result_cell"> <c:choose><c:when test="${gradeten_trans_rslt[stat.index].qrt_two_total == \"0.0\"}">-</c:when><c:otherwise> <c:out value="${gradeten_trans_rslt[stat.index].qrt_two_total}"/> </c:otherwise></c:choose> </td>
						<td class="result_cell"> <c:choose><c:when test="${gradeten_trans_rslt[stat.index].qrt_three_total == \"0.0\"}">-</c:when><c:otherwise> <c:out value="${gradeten_trans_rslt[stat.index].qrt_three_total}"/> </c:otherwise></c:choose> </td>
						<td class="result_cell"> <c:choose><c:when test="${gradeten_trans_rslt[stat.index].qrt_four_total == \"0.0\"}">-</c:when><c:otherwise> <c:out value="${gradeten_trans_rslt[stat.index].qrt_four_total}"/> </c:otherwise></c:choose> </td>
						<td class="result_cell"> <c:choose><c:when test="${gradeten_trans_rslt[stat.index].average_quarter_mark == \"NaN\"}">-</c:when><c:otherwise> <c:out value="${gradeten_trans_rslt[stat.index].average_quarter_mark}"/> </c:otherwise></c:choose> </td>
					</tr>
				</c:forEach>				
				<tr>						
					<td style="line-height: 20px; padding-left: 5px;"> Absent </td>
					
					<td>  </td>
					<td>  </td>
					<td>  </td>
					<td>  </td>
					<td>  </td>
					
					<td>  </td>
					<td>  </td>
					<td>  </td>
					<td>  </td>
					<td>  </td>
				</tr>
			</table>
			
			<table style="padding-top: 10px;">
				<tr>
					<td colspan="2" class="remarksNotes">
						<b>Result Status:</b> <s:property value="stud_bean.rslt_status"/> 
					</td>
				</tr>
				<tr>
					<td colspan="2" class="remarksNotes">
						<b>Remark:</b> The student has returned all school properties.
					</td>
				</tr>
				<tr>
					<td colspan="2" class="remarksNotes">
						<b>Note:</b> Any erasure alteration, deletion or absence of seal invalidates this transcript.
					</td>
				</tr>
				<tr>
					<td class="remarksNotes" style="line-height: 30px;">
						<b>Record Officer:</b> <u>&nbsp;&nbsp;ASHIREKA EBRAHIM&nbsp;&nbsp;</u>
					</td>
					<td>
						<b>Date of Issuance:</b> ____________________________
					</td>
				</tr>
				<tr>
					<td class="remarksNotes">
						<b>Principal's Name:</b> <u>&nbsp;&nbsp;GETU TEZERA&nbsp;&nbsp;</u>
					</td>
					<td>
						<b>Signature:</b> ________________________
					</td>
				</tr>
			</table>
			
		</div>
		
		<div id="trans_footer" style="font-size: 14px; bottom:0; position: absolute; text-align: center; width: 100%;">
			
			<table width="100%" cellpadding="0" cellspacing="0">			
				<tr>
					<td align="center"> 
						<s:text name='global.schooladdresstranscript'/> 
					</td>
				</tr>					
			</table>
			<div style="font-size: 8px;">
				Powered by: YamGet IT Solutions Plc | Fidel School Management System | www.yamget.com | +251-912-195853 
			</div>
		</div>
		
		
	</div>
	
</td></tr></thead><tr><td></td></tr></table>

</div>

</body>
</html>