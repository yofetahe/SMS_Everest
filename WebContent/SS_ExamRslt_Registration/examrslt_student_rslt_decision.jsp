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

	<s:set var="cl_id" value="cl_id"/>
	<s:set var="cd_id" value="cd_id"/>
	<s:set var="academic_year" value="academic_year"/>

	<div id="errorMessage" style="color: RED; width: 40%;"></div>

	<table style="padding-top: 20px;" width="100%">
		<tr>
			<td colspan="2" style="height: 40px; vertical-align: middle; border-bottom: thin; border-bottom-color: black; border-bottom-style: solid; border-bottom-width: thin;">
				Final Result Decision
			</td>
		</tr>
		<tr>
			<td>
				<div style="width: 40%; color: #3d6e9f; text-align: center;">
					Minimum mark is included in the range. <br/> Eg. Between 65 and 100 mean 65 is in the range.
				</div>
				<div>
					<table>
						<tr>
							<td>
								Pass Student Rank - From
							</td>
							<td>
								<input id="pass_rank_from" type="text" style="width: 206px; height: 21px">
							</td>
						</tr>
						<tr>
							<td>
								Pass Student Rank - To
							</td>
							<td>
								<input id="pass_rank_to" type="text" style="width: 206px; height: 21px">
							</td>
						</tr>
						<tr>
							<td>
								Decision
							</td>
							<td>
								<input id="pass_decision_type" type="text" style="width: 206px; height: 21px" value="Passed" readonly="readonly">								
							</td>
						</tr>
					</table>
					
<!-- 					<table> -->
<!-- 						<tr> -->
<!-- 							<td> -->
<!-- 								Fail Student Rank - From -->
<!-- 							</td> -->
<!-- 							<td> -->
<!-- 								<input id="fail_rank_from" type="text" style="width: 206px; height: 21px"> -->
<!-- 							</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td> -->
<!-- 								Fail Student Rank - To -->
<!-- 							</td> -->
<!-- 							<td> -->
<!-- 								<input id="fail_rank_to" type="text" style="width: 206px; height: 21px"> -->
<!-- 							</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td> -->
<!-- 								Decision -->
<!-- 							</td> -->
<!-- 							<td> -->
<!-- 								<input id="fail_decision_type" type="text" style="width: 206px; height: 21px" value="Failed" readonly="readonly"> -->
<!-- 							</td> -->
<!-- 						</tr> -->
<!-- 					</table> -->
					
					<table>
						<tr>
							<td align="center">
								<button onclick="saveStudentFinalResultStatus('${cl_id}', '${cd_id}', '${academic_year}')" class="btn">SAVE</button>
							</td>
							<td align="center">
								<button onclick="updateStudentFinalResultStatus('${cl_id}', '${cd_id}', '${academic_year}')" class="btn">UPDATE</button>
							</td>
						</tr>
					</table>
				</div>
				
			</td>
		</tr>
	</table>

</body>
</html>