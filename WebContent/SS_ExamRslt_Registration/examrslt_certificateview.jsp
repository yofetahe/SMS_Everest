<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

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
                $('#annualRptCard').dataTable( {
                    "sDom": 'T<"clear">rt'
                } );
            } );
        </script>
<!-- Datatable -->

<style type="text/css">
.studInfo{
	border-bottom: thin; 
	border-bottom-color: black; 
	border-bottom-style: solid; 
	border-bottom-width: thin; 
	width: 100%; 
	text-align: left;
}

.subjectNameRotate{
	height: 10px;
	color: #000; 
	-webkit-transform: rotate(-90deg); 	
	-moz-transform: rotate(-90deg); 
	-ms-transform: rotate(-90deg); 
	-o-transfrom: rotate(-90deg); 
/* 	transform: rotate(-90deg);	 */
/* 	filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=0.083);  */
	text-align: center; 
	font-weight: bold;
}
</style>

</head>
<body>
	<div style="padding-top: 10px;">
	
	<s:set var="si_id" value="si_id"/>
	
	<table width="100%" cellpadding="0" cellspacing="0" id="annualRptCard"><thead><tr><td>
	
		<div style="height: 18cm; padding-top: 10px;">
		
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td width="48%" valign="top" style="padding-left: 5px;">			
					
						<div style="width: 100%; font-weight: bold; font-size: 18px; text-align: center;">
							ANNUAL REPORT
						</div>
						<br/>
						<s:set var="lst" value="0"/>
						<table width="100%" cellpadding="0" cellspacing="1" rules="rows" style="border-color: black; border-style: solid; border-width: thin;">
							<tr style="background-color: #f5f5f5; vertical-align:middle; height: 50px; color: #000; font-weight: bold;">
								<td align="center" width="25%">Subject</td>
								<td align="center" width="15%">1st<br/> Term</td>
								<td align="center" width="15%">2nd<br/> Term</td>
								<td align="center" width="15%">3rd<br/> Term</td>
								<td align="center" width="15%">4th<br/> Term</td>
								<td align="center" width="15%">Average</td>
							</tr>
							<s:iterator status="stat" value="rsltList">
								<tr style="height: 35px;">
									<td style="padding-left: 5px;">
										<s:property value="rsltList[#stat.index].sub_name"/>
									</td>
									<td align="center">
									
										<s:set var="convert_to_grade" value="firstQuarterRslt[#stat.index].convert_to_grade"/>
										<s:if test="%{#convert_to_grade == \"NO\"}">
											<s:set var="f_quarttotal" value="firstQuarterRslt[#stat.index].quarter_total"/>
											<s:if test="%{#f_quarttotal < 65}"> <span style="color: RED; padding-left: 15px;"> <s:property value="%{#f_quarttotal}"/> * </span> </s:if>
											<s:else> <s:property value="%{#f_quarttotal}"/> </s:else>
										</s:if>
										<s:else>
											<s:property value="firstQuarterRslt[#stat.index].Grading_result"/>
										</s:else>
										
									</td>				
									<td align="center">	
																	
										<s:set var="convert_to_grade" value="secondQuarterRslt[#stat.index].convert_to_grade"/>
										<s:if test="%{#convert_to_grade == \"NO\"}">
											<s:set var="s_quarttotal" value="secondQuarterRslt[#stat.index].quarter_total"/>
											<s:if test="%{#s_quarttotal < 65}"> <span style="color: RED; padding-left: 15px;"> <s:property value="%{#s_quarttotal}"/> * </span> </s:if>
											<s:else> <s:property value="%{#s_quarttotal}"/> </s:else>
										</s:if>
										<s:else>
											<s:property value="secondQuarterRslt[#stat.index].Grading_result"/>
										</s:else>
										
									</td>
									<td align="center">
										
										<s:set var="convert_to_grade" value="thirdQuarterRslt[#stat.index].convert_to_grade"/>
										<s:if test="%{#convert_to_grade == \"NO\"}">
											<s:set var="t_quarttotal" value="thirdQuarterRslt[#stat.index].quarter_total"/>
											<s:if test="%{#t_quarttotal < 65}"> <span style="color: RED; padding-left: 15px;"> <s:property value="%{#t_quarttotal}"/> * </span> </s:if>
											<s:else> <s:property value="%{#t_quarttotal}"/> </s:else>
										</s:if>
										<s:else>
											<s:property value="thirdQuarterRslt[#stat.index].Grading_result"/>
										</s:else>
										
									</td>				
									<td align="center">
										
										<s:set var="convert_to_grade" value="fourthQuarterRslt[#stat.index].convert_to_grade"/>
										<s:if test="%{#convert_to_grade == \"NO\"}">
											<s:set var="fr_quarttotal" value="fourthQuarterRslt[#stat.index].quarter_total"/>
											<s:if test="%{#fr_quarttotal < 65}"> <span style="color: RED; padding-left: 15px;"> <s:property value="%{#fr_quarttotal}"/> * </span> </s:if>
											<s:else> <s:property value="%{#fr_quarttotal}"/> </s:else>
										</s:if>
										<s:else>
											<s:property value="fourthQuarterRslt[#stat.index].Grading_result"/>
										</s:else>
										
									</td>				
									<td align="center">
										<s:property value="fullYearAvgRslt[#stat.index].quarter_total"/>
									</td>
								</tr>
								<s:set var="lst" value="%{#stat.index}"/>
							</s:iterator>
							<tr style="height: 40px; font-weight: bold;">
								<td style="padding-left: 5px;">
									Total
								</td>
								<td align="center">
									<s:set var="firstQuarterTotal" value="firstQuarterRslt[#lst].quarter_grand_total"/>
									<s:if test="%{#firstQuarterTotal != \"NaN\"}">
										<fmt:formatNumber pattern="#,###.##">${firstQuarterTotal}</fmt:formatNumber>
									</s:if>
								</td>				
								<td align="center">
									<s:set var="secondQuarterTotal" value="secondQuarterRslt[#lst].quarter_grand_total"/>
									<s:if test="%{#secondQuarterTotal != \"Nan\"}">
										<fmt:formatNumber pattern="#,###.##">${secondQuarterTotal}</fmt:formatNumber>
									</s:if>
								</td>
								<td align="center">
									<s:set var="thirdQuarterTotal" value="thirdQuarterRslt[#lst].quarter_grand_total"/>
									<s:if test="%{#thirdQuarterTotal != \"NaN\"}">
										<fmt:formatNumber pattern="#,###.##">${thirdQuarterTotal}</fmt:formatNumber>									
									</s:if>
								</td>				
								<td align="center">
									<s:set var="fourthQuarterTotal" value="fourthQuarterRslt[#lst].quarter_grand_total"/>
									<s:if test="%{#fourthQuarterTotal != \"NaN\"}">
										<fmt:formatNumber pattern="#,###.##">${fourthQuarterTotal}</fmt:formatNumber>									
									</s:if>
								</td>				
								<td align="center">
									<s:set var="averageMarkTotal" value="fullYearAvgRslt[#lst+1].quarter_grand_total"/>
									<s:if test="%{#averageMarkTotal != \"NaN\"}">
										<fmt:formatNumber pattern="#,###.##">${averageMarkTotal}</fmt:formatNumber>
									</s:if>
								</td>
							</tr>
							<tr style="height: 40px; font-weight: bold;">
								<td style="padding-left: 5px;">
									Quarter Average 
								</td>
								<td align="center">
									<s:set var="firstQuarterAvg" value="firstQuarterRslt[#lst].average_quarter_mark"/>
									<s:if test="%{#firstQuarterAvg != \"NaN\"}">
										<fmt:formatNumber maxIntegerDigits="3" pattern="#.##">${firstQuarterAvg}</fmt:formatNumber>
									</s:if>
								</td>				
								<td align="center">
									<s:set var="secondQuarterAvg" value="secondQuarterRslt[#lst].average_quarter_mark"/>
									<s:if test="%{#secondQuarterAvg != \"NaN\"}">
										<fmt:formatNumber maxIntegerDigits="3" pattern="#.##">${secondQuarterAvg}</fmt:formatNumber>
									</s:if>
								</td>
								<td align="center">
									<s:set var="thirdQuarterAvg" value="thirdQuarterRslt[#lst].average_quarter_mark"/>
									<s:if test="%{#thirdQuarterAvg != \"NaN\"}">
										<fmt:formatNumber maxIntegerDigits="3" pattern="#.##">${thirdQuarterAvg}</fmt:formatNumber>
									</s:if>
								</td>				
								<td align="center">
									<s:set var="fourthQuarterAvg" value="fourthQuarterRslt[#lst].average_quarter_mark"/>
									<s:if test="%{#fourthQuarterAvg != \"NaN\"}">
										<fmt:formatNumber maxIntegerDigits="3" pattern="#.##">${fourthQuarterAvg}</fmt:formatNumber>
									</s:if>
								</td>				
								<td align="center">
									<s:set var="averageMarkAvg" value="fullYearAvgRslt[#lst+1].average_quarter_mark"/>
									<s:if test="%{#averageMarkAvg != \"NaN\"}">
										<fmt:formatNumber maxIntegerDigits="3" pattern="#.##">${averageMarkAvg}</fmt:formatNumber>
									</s:if>
								</td>
							</tr>
							<tr style="height: 40px; font-weight: bold;">
								<td style="padding-left: 5px;">
									Rank
								</td>
								<td align="center"> 
									<s:property value="fullYearStudRank[0].Quarter_rank"/> / <s:property value="row_num"/>								
								</td>
								<td align="center">
									<s:property value="fullYearStudRank[1].Quarter_rank"/> / <s:property value="row_num"/>
								</td>
								<td align="center">
									<s:property value="fullYearStudRank[2].Quarter_rank"/> / <s:property value="row_num"/>
								</td>
								<td align="center">
									<s:property value="fullYearStudRank[3].Quarter_rank"/> / <s:property value="row_num"/>
								</td>
								<td align="center">
									<s:property value="year_avg_rank"/> / <s:property value="row_num"/>
								</td>
							</tr>
						</table>
			
					</td>
					<td width="4%">&nbsp;</td>
					<td width="48%" valign="top" style="padding-right: 5px;">
						
						<div style="position: relative; height: 18cm; width: 100%">
						
							<div style="width: 100%; font-weight: bold; font-size: 18px; text-align: center;"> 
								TEACHER'S COMMENTS AND RECOMMENDATION
							</div>
							<br/>
							<table width="100%">
								<tr>
									<td class="headerTitleBottomBorder" align="left" width="10px">
										<b>First Quarter</b>
									</td>
								</tr><tr>
									<td>
										<div style="min-height: 50px">
											<s:set var="first_edc_content" value="firstQuarterDefaultComment[0].edc_content"/>
											<s:property value="%{#first_edc_content}"/>
											<s:set var="first_erc_content" value="firstQuarterTeacherComment[0].erc_content"/>
											<s:property value="%{#first_erc_content}"/>
										</div>
									</td>
								</tr>
							</table>
							
							<table width="100%">
								<tr>
									<td class="headerTitleBottomBorder" align="left" width="10px">
										<b>Second Quarter</b>
									</td>
								</tr><tr>
									<td style="min-height: 50px">
										<div style="min-height: 50px">
											<s:set var="second_edc_content" value="firstQuarterDefaultComment[0].edc_content"/>
											<s:property value="%{#second_edc_content}"/>
											<s:set var="second_erc_content" value="firstQuarterTeacherComment[0].erc_content"/>
											<s:property value="%{#second_erc_content}"/>
										</div>
									</td>
								</tr>
							</table>
							
							<table width="100%">
								<tr>
									<td class="headerTitleBottomBorder" align="left" width="10px">
										<b>Third Quarter</b>
									</td>
								</tr><tr>
									<td style="min-height: 150px">
										<div style="min-height: 50px">
											<s:set var="third_edc_content" value="thirdQuarterDefaultComment[0].edc_content"/>
											<s:property value="%{#third_edc_content}"/>
											<s:set var="third_erc_content" value="thirdQuarterTeacherComment[0].erc_content"/>
											<s:property value="%{#third_erc_content}"/>
										</div>
									</td>
								</tr>
							</table>
							
							<table width="100%">
								<tr>
									<td class="headerTitleBottomBorder" align="left" width="10px">
										<b>Fourth Quarter</b>
									</td>
								</tr><tr>
									<td style="min-height: 150px">
										<div style="min-height: 50px">
											<s:set var="fourth_edc_content" value="fourthQuarterDefaultComment[0].edc_content"/>
											<s:property value="%{#fourth_edc_content}"/>
											<s:set var="fourth_erc_content" value="fourthQuarterTeacherComment[0].erc_content"/>
											<s:property value="%{#fourth_erc_content}"/>
										</div>							
									</td>
								</tr>
							</table>
							
							<div style="bottom: 0; position: absolute; padding-bottom: 20px; width: 100%;">
								<table cellpadding="0" cellspacing="0" border="0" align="center">
									<tr> <td rowspan="5"> <img alt="photo" src="imageRetrieveServlet?si_id=${si_id}" width="90px;"> </td></tr>
								</table>
								<br/>
								<table width="100%">
									<tr>
										<td colspan="2" height="10px">
											<div class="studInfo" style="width: 100%; text-align: center;"> Result Status: &nbsp; 
											<s:property value="stud.rslt_status"/>  
											</div>
										</td>
									</tr>						
									<tr>
										<td colspan="2" height="30px">
											&nbsp;
										</td>
									</tr>
									<tr>
										<td width="60%" height="30px">								
											<div class="studInfo"> <b>Teacher's Name:</b> <s:property value="homeroom_teacher_name"/> </div> 
										</td>
										<td align="right">
											<div class="studInfo"> <b>Signature:</b> </div>
										</td>
									</tr>
								</table>
							</div>
							
						</div>
					</td>
				</tr>
			</table>
		
		</div>
		
	</td></tr></thead><tr><td></td></tr></table>

	</div>
</body>
</html>