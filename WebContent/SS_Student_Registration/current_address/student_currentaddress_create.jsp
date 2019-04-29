<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sj:head />
<s:head />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
	<table>
		<tr>
			<td style="background-color: #f5f5f5; height: 50px; cursor: pointer;">				
				<img alt="click" src="images/next.gif" width="8px"> Adding Students Current Address				
			</td>
		</tr>
		<tr>
			<td id="cadd_saveform">
				<div id="errMsg" style="color: red; text-align: center;"></div>
			</td>
		</tr>		
		<tr>
			<td>
				<table><tr><td align="right">
				
					<s:set var="siid" value="si_id"/>
					<table>
					<s:textfield id="sub_city" label="Sub City" required="true" key="sub_city" style="height: 25px; width: 200px"/>
					</table>
					<table>
					<s:textfield id="kebele" label="Kebele" required="true" key="kebele" style="height: 25px; width: 200px"/>
					</table>
					<table>
					<s:textfield id="house_no" label="House No" required="true" key="house_no" style="height: 25px; width: 200px"/>
					</table>
					<table>
					<s:textfield id="house_phone" label="House Phone Number (+251)" key="home_phone" style="height: 25px; width: 200px"/>
					</table>
					<table>
					<s:textfield id="email" label="First Email" key="email" style="height: 25px; width: 200px"/>
					</table>
					<table>
					<s:textfield id="email_2" label="Second Email" key="email" style="height: 25px; width: 200px"/>
					</table>
				</td></tr>
		<tr><td>
			<button onclick="saveCurAddress('${siid}')" class="btn">Save</button>						
		</td></tr></table>
			</td>
		</tr>
	</table>
</body>
</html>