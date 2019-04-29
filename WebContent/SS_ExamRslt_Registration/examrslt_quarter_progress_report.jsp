<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                $('#qrtRptCard').dataTable( {
                    "sDom": 'T<"clear">rt'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

<table border="0" cellpadding="0" cellspacing="0" id="qrtRptCard"><thead><tr><td style="padding: 15px;" align="center">

<div style="width:100%; padding: 20px; padding-top: 30px; padding-bottom: 70px; border: fuchsia; border-color: silver; border-style: double; border-width: thick;" align="center">

	<table width="900px" cellpadding="0" cellspacing="0" style="padding-left: 5px; padding-right: 5px;" border="0">
		<tr>
			<td align="center">
			
				<br/>
				
				<table cellpadding="0" cellspacing="0" align="center" border="0">
					<tr>
						<td align="right" valign="top">
							<img alt="Everest" src="images/Champions_logo.jpg" width="140px;">
						</td>
						<td>
						
							<table cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td align="center" style="font-size: 24px; font-weight: bold; height: 30px;"> <s:text name="global.schoolname"/> </td>
								</tr>
								<tr>
									<td align="center" style="font-weight: bold; height: 30px;"> Tel. +251-94-772-1515/96-518-8787 <br/> Addis Ababa, Ethiopia </td>
								</tr>
							</table>							  
							  
						</td>						
					</tr>
				</table>
				
				<br/>
				
			</td>
		</tr>
		<tr>
			<td align="center">
			
				<table cellpadding="0" cellspacing="0">
					<tr><td style="font-size: 28px; font-weight: bold; height: 50px; text-decoration: underline; text-align: center;">
						Student's Progress Report
					</td></tr>
				</table>
				<br/>
			</td>
		</tr>
		<tr>
			<td style="border-bottom: thin; border-top: thin; border-bottom-color: silver; border-top-color: silver; border-bottom-style: solid; border-top-style: solid; border-bottom-width: thin; border-top-width: thin;">
				<br/>
				<table width="100%" cellpadding="0" cellspacing="0" border="0">
					<tr style="height: 30px;">
						<td colspan="2"> <span style="font-weight: bold;"> Student's Name: </span> <span style="text-decoration: underline;"> <s:property value="fname"/> <s:property value="mname"/> <s:property value="gname"/> </span> </td>
					</tr><tr style="height: 30px;">
						<td> <span style="font-weight: bold;"> Class: </span><span style="text-decoration: underline;"> <s:property value="cl_name"/> <s:property value="cd_name"/> </span> </td>
						<td> <span style="font-weight: bold;"> Home Room Teacher's Name: </span><span style="text-decoration: underline;"> <s:property value="homeroom_teacher_name"/> </span> </td>
					</tr><tr style="height: 30px;">	
						<td> <span style="font-weight: bold;"> Term: </span> <span style="text-decoration: underline;"> <s:property value="quarterRsltView[0].at_name"/> </span> </td>
						<td> <span style="font-weight: bold;"> Date: </span> <span style="text-decoration: underline;"> <s:property value="cur_date"/> </span> </td>
					</tr>
				</table>
				<br/>
			</td>
		</tr>
		<tr>
			<td height="40px;">&nbsp;</td>
		</tr>
		<tr>
			<td>
			
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top">
							

							<table width="100%" cellpadding="0" cellspacing="1" border="0" rules="rows" style="border: thin; border-color: gray; border-style: solid; border-width: thin;">
								<tr style="font-weight: bold; height: 40px;">
									<td width="60%" style="padding-left: 10px;">Subject</td>
									<td width="40%" align="center">Result</td>
								</tr>
								<s:iterator status="stat" value="quarterRsltView">
									<tr>
										<td height="35px;" style="padding-left: 10px;">
											<s:property value="quarterRsltView[#stat.index].sub_name"/>
										</td>
										<td align="center">
											<s:set var="con_to_grade" value="quarterRsltView[#stat.index].convert_to_grade"/>
											<s:if test="%{#con_to_grade == \"NO\"}">
												<s:property value="quarterRsltView[#stat.index].Quarter_total"/>
											</s:if>
											<s:else>
												<s:property value="quarterRsltView[#stat.index].grading_result"/>
											</s:else>
										</td>
									</tr>
								</s:iterator>
								<tr>
									<td height="35px;" style="padding-left: 10px;">Conduct</td>
									<td align="center">  </td>									
								</tr>								
							</table>


						</td>
						<td style="width: 50px;">&nbsp;</td>
						<td width="30%" valign="top">
													
							<table width="100%" cellpadding="0" rules="none" cellspacing="0" style="border: thin; border-color: gray; border-style: solid; border-width: thin;">
								<tr>
									<td style="padding-left: 10px;">
										<b>Result Key:</b> <br/>A+ - 46 - 50<br/> A - 40 - 45<br/> B - 35 - 39<br/> C - 29 - 34<br/> NI - Less than 29
									</td>
								</tr>
								<tr><td>&nbsp;</td></tr>
								<tr>
									<td style="padding-left: 10px;">
										<b>Conduct Key:</b><br/> A+ - Advanced<br/> A - Excellent<br/> B - V.Good<br/> C - Good<br/> NI - Needs Improvement
									</td>
								</tr>
							</table>
						
						</td>
					</tr>
				</table>
				
				<br/><br/>
				
				<table width="100%" style="border-color: silver; border-style: solid; border-width: thin; padding-top: 30px; padding-bottom: 30px;">
					<tr>
						<td style="height: 120px;" valign="top" colspan="5">
							<span style="font-weight: bold;">Class Teacher's/Director's comment:</span><br/><br/>
							<s:set var="erc_content" value="quarterTeacherComment[0].erc_content"/>
							<s:property value="%{#erc_content}"/>
						</td>
					</tr>					
					<tr>
						<td>
							Teacher's Signature:_____________________ 							
						</td>
						<td>&nbsp;</td>
						<td>
							Principal's Signature:_____________________
						</td>
						<td>&nbsp;</td>
						<td>
							Date_____________________
						</td>
					</tr>
				</table>
			
			</td>
		</tr>
	</table>

</div>

</td></tr></thead><tr><td></td></tr></table>

</body>
</html>