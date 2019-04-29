<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

</head>
<body>

<script type="text/javascript">

$(function () { 
	
	var cur_eth_year = ['<s:property escapeJavaScript="true" value="academic_year"/>']
	
	var c_list = [ <s:iterator value="db_stud_persex" status="stat">
	<s:if test="#stat.index > 0">,</s:if>
	'<s:property escapeJavaScript="true" value="db_stud_persex[#stat.index].class_name"/>'                 
	</s:iterator> ]
	
	var m_list = [ <s:iterator value="db_stud_persex" status="stat">
	<s:if test="#stat.index > 0">,</s:if>
	'<s:property escapeJavaScript="true" value="db_stud_persex[#stat.index].no_male"/>'                 
	</s:iterator> ]
	
	var f_list = [ <s:iterator value="db_stud_persex" status="stat">
	<s:if test="#stat.index > 0">,</s:if>
	'<s:property escapeJavaScript="true" value="db_stud_persex[#stat.index].no_female"/>'                 
	</s:iterator> ]
		
	var clist = c_list + "";
	var cat_list = clist.split(",");	
	
	var mlist = m_list + "";
 	var male_list = mlist.split(",").map(Number);

 	var flist = f_list + "";
 	var female_list= flist.split(",").map(Number);
 	
 	var total_male = 0;
 	for(a in male_list){
 		total_male = parseInt(total_male) + parseInt(male_list[a]);
 	}
 	
 	var total_female = 0;
 	for(a in female_list){
 		total_female = parseInt(total_female) + parseInt(female_list[a]);
 	}
	
 	var total_num_student = parseInt(total_male) + parseInt(total_female);
	
    $('#db_content').highcharts({
        chart: {
            type: 'line'
        },
        title: {
            text: cur_eth_year + 'E.C - Number of Students per Grade (Total Number of Students are ' + total_num_student + ')'
        },
        xAxis: {
            categories: cat_list
        },
        yAxis: {
            title: {
                text: 'Students Number'
            }
        },
        series: [{
            name: 'Male',
            data: male_list
        }, {
            name: 'Female',
            data: female_list
        }]
    });
});

</script>

<div id="db_content" style="width: 100%; min-height: 400px">

</div>
	
</body>
</html>