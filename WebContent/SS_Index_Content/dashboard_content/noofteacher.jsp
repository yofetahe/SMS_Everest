<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	
	var m_total = [	<s:iterator value="teacherNumPerSex" status="stat">
					<s:if test="#stat.index > 0">,</s:if>
					'<s:property escapeJavaScript="true" value="teacherNumPerSex[#stat.index].male_num"/>'                 
					</s:iterator> ]
	
	var f_total = [	<s:iterator value="teacherNumPerSex" status="stat">
					<s:if test="#stat.index > 0">,</s:if>
					'<s:property escapeJavaScript="true" value="teacherNumPerSex[#stat.index].female_num"/>'                 
					</s:iterator> ]
	
	var total_teacher_num = parseInt(m_total) + parseInt(f_total);
	
	var male_percentage = (100 * parseInt(m_total))/total_teacher_num;
	
 	var female_percentage= (100 * parseInt(f_total))/total_teacher_num;
	
    $('#db_content').highcharts({
        chart: {
        	plotBackgroundColor: null,
        	plotBorderWidth: null,
        	plotShadow: false,
            type: 'pie'
        },
        title: {
            text: 'Number of Teachers per Sex (Total number of teachers are ' + total_teacher_num + '. ' + m_total + '-Males and ' + f_total + '-Females'  + ')'
        },
        tooltip: {
        	pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions:{
        	pie:{
        		allowPointSelect: true,
        		cursor: 'pointer',
        		dataLabels: {
        			enabled: true,
        			format: '<b>{point.name}</b>: {point.percentage:.1f}%',
        			style: {
        				color: (Highcharts.theme && highcharts.theme.contrastTextColor)||'black'
        			}
        		}
        	}
        },
        series: [{
            name: 'Male',
            colorByPoint: true,
            data: [{
            	name: "Male Teacher",
            	y: male_percentage
            }, {
            	name: "Female Teacher",
            	y: female_percentage,
            	sliced: true
            }]
        }]
    });
});

</script>

<div id="db_content" style="width: 100%; min-height: 400px">

</div>

</body>
</html>