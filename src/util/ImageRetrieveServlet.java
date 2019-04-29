package util;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentClass.StudentBean;
import studentClass.StudentDAO;

@WebServlet("/ImageRetrieveServlet")
public class ImageRetrieveServlet extends HttpServlet {
	
	private static final long serialVersionUID = -724197902919313479L;

	public void doGet(HttpServletRequest request, HttpServletResponse response){
		
		StudentBean sb = new StudentBean();
		sb.setSi_id(request.getParameter("id"));
		System.out.println(request.getParameter("id") + " stud id");
		byte[] readImg = StudentDAO.getStudentPhoto(sb);
		
		try {
			response.reset();
			response.setContentType("image/jpg");
			response.getOutputStream().write(readImg, 0, readImg.length);
			response.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
