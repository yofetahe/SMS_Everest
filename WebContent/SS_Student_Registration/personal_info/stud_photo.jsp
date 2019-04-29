<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<% 
		String si_id = request.getParameter("si_id");
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sms_everest", "root", "password");
		PreparedStatement ps = con.prepareStatement("SELECT stud_photo FROM student_photo_information WHERE si_id = ?");
		ps.setInt(1, Integer.parseInt(si_id));
		ResultSet rs = ps.executeQuery();
												
		byte[] readImg = null;
												
		if(rs.next()){
			readImg = rs.getBytes("stud_photo");
		}
												
		response.reset();
		response.setContentType("image/jpeg");
		response.getOutputStream().write(readImg, 0, readImg.length);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	%>

</body>
</html>