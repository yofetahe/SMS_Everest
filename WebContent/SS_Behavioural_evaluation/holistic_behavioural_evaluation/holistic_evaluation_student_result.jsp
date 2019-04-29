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
                $('#evalRslt').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

<s:set var="cl_id" value="cl_id"/>
<s:set var="si_id" value="si_id"/>
<s:set var="at_id" value="at_id"/>
<s:set var="ac_year" value="ac_year"/>
<s:set var="at_name" value="at_name"/>

<div id="studRsltList">

<table id="evalRslt" cellpadding="0" cellspacing="0" width="100%"><thead><tr><td>

	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="background-color: #f5f5f5;">
				<div class="subHeaderTitle" style="padding-bottom: 5px;">
					<table><tr><td>
					Student Name: <span style="color: #3d6e9f;"> <s:property value="fname"/> <s:property value="mname"/> <s:property value="gname"/> </span> &nbsp;&nbsp;&nbsp;
					Terms: <span style="color: #3d6e9f;"> <s:property value="at_name"/> </span> &nbsp;&nbsp;&nbsp;
					Academic Year: <span style="color: #3d6e9f;"> <s:property value="ac_year"/></span> &nbsp;&nbsp;&nbsp;
					</td></tr></table>
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px;">
				
				<table width="100%" cellpadding="0" cellspacing="0">
					<s:iterator status="stat" value="related_catagory_list">
						<tr>
							<td style="background-color: #f5f5f5; height: 50px; font-weight: bold;">
								<s:property value="related_catagory_list[#stat.index].bhc_name"/>								
								<s:set var="bhc_id" value="related_catagory_list[#stat.index].bhc_id"/>								
							</td>
						</tr>
						<tr><td>&nbsp;</td></tr>
						<s:iterator status="st" value="stud_holist_beval_comment">
							<s:set var="com_bhc_id" value="stud_holist_beval_comment[#st.index].bhc_id"/>
							<s:if test="%{#bhc_id == #com_bhc_id}" >
								<tr>
									<td style="font-weight: bold; color: #3d6e9f; height: 30px;">
									<s:property value="stud_holist_beval_comment[#st.index].bhp_content"/>
									</td>
								</tr>
								<tr>
									<td valign="top" style="height: 30px;">
									<img src="images/next.gif" style="height: 8px;"/> <s:property value="stud_holist_beval_comment[#st.index].beval_comment"/>
									</td>
								</tr>
							</s:if>							
						</s:iterator>						
					</s:iterator>
				</table>
			
			</td>
		</tr>
		<tr>
			<td>
				<div id="cat_points">   </div>
			</td>
		</tr>
	</table>
	
	</td></tr></thead><tr><td>&nbsp;
	</td></tr></table>
	
</div>

</body>
</html>