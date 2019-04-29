<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
                $('#nonteach_list').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>
	<table width="100%" cellpadding="0" cellspacing="0" style="padding-top: 0px">
		<tr>
			<td style="height: 35px">
				
				<div class="menu_shadow" style="height: 35px; padding-top: 15px;">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td align="left">
								<div style="color: #3d6e9f;"> <img src="images/next.gif" width="8px"/> Non Academic Stuff Member </div>
							</td>							
						</tr>
					</table>
				</div>
								
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">

				<div class="content_background"  style="min-height: 434px">
					<table cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td width="60%" valign="top">
								
								<table id="nonteach_list" width="100%" cellpadding="0" cellspacing="1">
									<thead>
										<tr style="background-color: #f5f5f5; height: 30px; font-size:14px; color: #000000" align="center">
											<th width="5%">No</th>
											<th width="35%">Name</th>
											<th width="25%">Position</th>
											<th width="10%">Sex</th>
											<th width="15%">ID No</th>
											<th width="10%">Status</th>
										</tr>
									</thead>
									<s:iterator status="stat" value="nontchr_list">
										<tr height="30px">
											<td align="center">
												<s:property value="#stat.index + 1"/>
												<s:set var="nti_id" value="nontchr_list[#stat.index].nti_id" />
												<s:set var="sex" value="nontchr_list[#stat.index].nti_sex"/>
												<s:set var="fname" value="nontchr_list[#stat.index].nti_fname"/>
												<s:set var="mname" value="nontchr_list[#stat.index].nti_mname"/>
												<s:set var="gname" value="nontchr_list[#stat.index].nti_gname"/>
												<s:set var="id_no" value="nontchr_list[#stat.index].nti_id_no"/>
												<s:set var="email" value="nontchr_list[#stat.index].nti_email"/>
												<s:set var="status" value="nontchr_list[#stat.index].nti_status"/>
												<s:set var="position" value="nontchr_list[#stat.index].nti_position"/>
											</td>
											<td style="color: #3d6e9f">
												<div onclick="editNonTeacherInfo('${nti_id}', '${fname}','${mname}', '${gname}', '${position}', '${sex}', '${email}', '${id_no}', '${status}')" style="cursor: pointer">
													<s:property value="%{#fname}"/> <s:property value="%{#mname}"/> <s:property value="%{#gname}"/>
												</div> 
											</td>
											<td>
												<s:property value="%{#position}"/> 
											</td>
											<td>									
												<s:if test="%{#sex == \"M\"}">Male</s:if>
												<s:if test="%{#sex == \"F\"}">Female</s:if>
											</td>
											<td>
												<s:property value="%{#id_no}"/> 
											</td>
											<td>
												<s:if test="%{#status == \"A\"}">Active</s:if>
												<s:if test="%{#status == \"I\"}">Inactive</s:if> 
											</td>
										</tr>
									</s:iterator>
								</table>
								
							</td>
							<td width="1%"></td>
							<td width="39%" valign="top" align="center">
								<div id="saveUpdateForm">
									<div style="width: 85%; color: #ffffff; background-color: #f5f5f5; height: 35px; padding-top: 10px;" align="center">Save Form</div>
									<div style="padding-left: 10px; width: 350px">
										<div id="errMsg" style="color: red; text-align: center; width: 100%;">&nbsp;</div>
										<table width="100%" border="0">
											<tr>
												<td>							
													<s:textfield id="fname" label="Frist Name" required="true" style="height: 25px; width: 200px"/>
													<s:textfield id="mname" label="Middle Name" required="true" style="height: 25px; width: 200px"/>
													<s:textfield id="gname" label="G.Father Name" required="true" style="height: 25px; width: 200px"/>
													<s:select id="sex" label="Gender" headerValue="Please select gender" required="true" headerKey="0" list="#{'M':'Male', 'F':'Femal'}" style="height: 30px; width: 206px"/>
													<s:textfield id="email" label="Email" style="height: 25px; width: 200px"/>
													<s:textfield id="pos" label="Position" required="true" style="height: 25px; width: 200px"/>
																									
													<s:submit align="center" onclick="saveNonTeacher()" value="Save" style="width: 50%; height: 35px;"/>							
												</td>
											</tr>
										</table>
									</div>
								</div>
							</td>
						</tr>
					</table>
					
				</div>
				
			</td>			
		</tr>
	</table>

</body>
</html>