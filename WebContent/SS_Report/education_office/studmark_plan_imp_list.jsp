<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="/css/sms_css.css">
<style type="text/css">
.headerTitleRotate{
	height: 70px;
	color: #3d6e9f; 
	-webkit-transform: rotate(-90deg); 	
	-moz-transform: rotate(-90deg); 
	-ms-transform: rotate(-90deg); 
	-o-transfrom: rotate(-90deg); 
/* 	transform: rotate(-90deg);	 */
/* 	filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=0.083);  */
	text-align: center; 
	
}

.subHeaderTitleRotate{
	height: 100px;
	color: #3d6e9f; 
	-webkit-transform: rotate(-90deg); 	
	-moz-transform: rotate(-90deg); 
	-ms-transform: rotate(-90deg); 
	-o-transfrom: rotate(-90deg); 
/* 	transform: rotate(-90deg);	 */
/* 	filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=0.083);  */
	text-align: center; 
	
}

.subjectNameRotate{
	height: 100px;
	color: #3d6e9f; 
	-webkit-transform: rotate(-90deg); 	
	-moz-transform: rotate(-90deg); 
	-ms-transform: rotate(-90deg); 
	-o-transfrom: rotate(-90deg); 
/* 	transform: rotate(-90deg);	 */
/* 	filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=0.083);  */
	text-align: center; 
	
}
</style>

</head>
<body>

	<table cellpadding="0" cellspacing="0" border="0" style="border: thin; border-color: black; border-style: solid; border-width: 1px;">
		<tr>
			<td rowspan="2" colspan="3" class="headerTitleRotate">
				Baay
			</td>
			<td rowspan="2" colspan="3" class="headerTitleRotate">
				Baay'ina
			</td>
			<td rowspan="3" class="headerTitleRotate">
				Gosa Barnoota
			</td>
			<td colspan="9">
				50 - 74
			</td>
			<td colspan="9">
				75 - 89
			</td>
			<td colspan="9">
				> 90
			</td>
			<td rowspan="2" colspan="3" class="headerTitleRotate">
				Walii >50
			</td>
			<td rowspan="2" colspan="3" class="headerTitleRotate">
				Walii >50 %
			</td>
		</tr>
		<tr>
			<td colspan="3">
				Karoora 50%
			</td>
			<td colspan="3">
				Raawwii
			</td>
			<td colspan="3">
				Raawwii %
			</td>
			<td colspan="3">
				Karoora 50%
			</td>
			<td colspan="3">
				Raawwii
			</td>
			<td colspan="3">
				Raawwii %
			</td>
			<td colspan="3">
				Karoora 50%
			</td>
			<td colspan="3">
				Raawwii
			</td>
			<td colspan="3">
				Raawwii %
			</td>
		</tr>
		<tr>
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			
			<td class="subHeaderTitleRotate">Dhira 50%</td>
			<td class="subHeaderTitleRotate">Dubara 50%</td>
			<td class="subHeaderTitleRotate">Ida'ama 50%</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
			<td class="subHeaderTitleRotate">Dhira</td>
		</tr>
		<s:iterator status="stat" value="stud_mark_plan_imp_list">
			<tr style="font-size: 11px;">
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].stud_bean.no_male"/> </td>
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].stud_bean.no_female"/> </td>
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].stud_bean.total_number"/> </td>
				
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].stud_bean.no_male"/> </td>
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].stud_bean.no_female"/> </td>
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].stud_bean.total_number"/> </td>
				
				<td class="subjectNameRotate" style="font-size: 12px; width: 5px;"> <s:property value="stud_mark_plan_imp_list[#stat.index].sub_bean.sub_name"/> </td>
				
				<td>-</td>
				<td>-</td>
				<td>-</td>
				
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].male_num_cat1"/> </td>
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].female_num_cat1"/> </td>
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].total_num_cat1"/> </td>
				
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].male_percent_cat1"/> % </td>
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].female_percent_cat1"/> % </td>
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].total_percent_cat1"/> % </td>
				
				
				<td>-</td>
				<td>-</td>
				<td>-</td>
				
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].male_num_cat2"/> </td>
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].female_num_cat2"/> </td>
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].total_num_cat2"/> </td>
				
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].male_percent_cat2"/> % </td>
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].female_percent_cat2"/> % </td>
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].total_percent_cat2"/> % </td>
				
				
				<td>-</td>
				<td>-</td>
				<td>-</td>
				
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].male_num_cat3"/> </td>
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].female_num_cat3"/> </td>
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].total_num_cat3"/> </td>
				
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].male_percent_cat3"/> % </td>
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].female_percent_cat3"/> % </td>
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].total_percent_cat3"/> % </td>
				
				
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].male_grt_50"/> </td>
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].female_grt_50"/> </td>
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].total_grt_50"/> </td>
				
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].male_percent_grt_50"/> % </td>
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].female_percent_grt_50"/> % </td>
				<td> <s:property value="stud_mark_plan_imp_list[#stat.index].total_percent_grt_50"/> % </td>
			</tr>
		</s:iterator>
	</table>


</body>
</html>