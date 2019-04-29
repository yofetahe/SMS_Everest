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
	
	var y_list = [	<s:iterator value="studentNumPerYear" status="stat">
					<s:if test="#stat.index > 0">,</s:if>
					'<s:property escapeJavaScript="true" value="studentNumPerYear[#stat.index].ac_year"/>'                 
					</s:iterator>]
	
	var m_list = [	<s:iterator value="studentNumPerYear" status="stat">
					<s:if test="#stat.index > 0">,</s:if>
					'<s:property escapeJavaScript="true" value="studentNumPerYear[#stat.index].no_male"/>'                 
					</s:iterator> ]
	
	var f_list = [	<s:iterator value="studentNumPerYear" status="stat">
					<s:if test="#stat.index > 0">,</s:if>
					'<s:property escapeJavaScript="true" value="studentNumPerYear[#stat.index].no_female"/>'                 
					</s:iterator> ]
		
	var ylist = y_list + "";
	var year_list = ylist.split(",");	
	
	var mlist = m_list + "";
 	var male_list = mlist.split(",").map(Number);

 	var flist = f_list + "";
 	var female_list= flist.split(",").map(Number);
	
    $('#db_content').highcharts({
        chart: {
            type: 'bar'
        },
        title: {
            text: 'Number of Students per Year'
        },
        xAxis: {
            categories: year_list
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