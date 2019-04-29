package teacherClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;
import studentClass.StudentBean;
import studentClass.StudentQueries;

public class TeacherDAO {
	
	public static List<TeacherBean> getTeacherLilst(){
		
		List<TeacherBean> rslt = new ArrayList<TeacherBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(TeacherQueries.getTeacherList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				TeacherBean tch = new TeacherBean();
				
				tch.setTi_id(rs.getString("TI_ID"));
				tch.setFname(rs.getString("FNAME"));
				tch.setMname(rs.getString("MNAME"));
				tch.setGname(rs.getString("GNAME"));
				tch.setSex(rs.getString("SEX"));
				tch.setDob(rs.getString("DOB"));
				tch.setPob(rs.getString("POB"));
				tch.setNationality(rs.getString("NATIONALITY"));
				tch.setId_no(rs.getString("ID_NO"));
				tch.setTi_status(rs.getString("TI_STATUS"));
				tch.setPhoto_name(rs.getString("PHOTO_NAME"));
				tch.setPhoto_path(rs.getString("PHOTO_PATH"));
				
				rslt.add(tch);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
		
	}
	
	public static List<TeacherBean> getActiveTeacherLilst(){
		
		List<TeacherBean> rslt = new ArrayList<TeacherBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(TeacherQueries.getActiveTeacherList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				TeacherBean tch = new TeacherBean();
				
				tch.setTi_id(rs.getString("TI_ID"));
				tch.setFname(rs.getString("FNAME")+ " " + rs.getString("MNAME"));
				tch.setMname(rs.getString("MNAME"));
				tch.setGname(rs.getString("GNAME"));
				tch.setSex(rs.getString("SEX"));
				tch.setDob(rs.getString("DOB"));
				tch.setPob(rs.getString("POB"));
				tch.setNationality(rs.getString("NATIONALITY"));
				tch.setId_no(rs.getString("ID_NO"));
				tch.setTi_status(rs.getString("TI_STATUS"));
				tch.setPhoto_name(rs.getString("PHOTO_NAME"));
				tch.setPhoto_path(rs.getString("PHOTO_PATH"));
				
				rslt.add(tch);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
		
	}
	
	public synchronized static String getLastTeacherNumber(){
		
		String lastIdNo = "";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(TeacherQueries.getLastTeacherId);
			rs = ps.executeQuery();
			
			while(rs.next()){
				lastIdNo = "000" + (Integer.parseInt(rs.getString("ti_id")) + 1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		if(lastIdNo == "" || lastIdNo.equals("")){
			lastIdNo = "0001";
		}
		
		return lastIdNo;
	}
	
	public synchronized static boolean saveTeacher(TeacherBean tcher){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(TeacherQueries.insertTeacherInfo);
			ps.setString(1, tcher.getFname());
			ps.setString(2, tcher.getMname());
			ps.setString(3, tcher.getGname());
			ps.setString(4, tcher.getSex());
			ps.setString(5, tcher.getDob());
			ps.setString(6, tcher.getPob());
			ps.setString(7, tcher.getNationality());
			ps.setString(8, tcher.getId_no());
			ps.setString(9, "default");
			ps.setString(10, "Photos/teach_photo/default.jpg");
			rs = ps.executeUpdate();
			
			if(rs > 0){
				rslt = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static boolean updateTeacher(TeacherBean tcher){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(TeacherQueries.updateTeacherInfo);
			ps.setString(1, tcher.getFname());
			ps.setString(2, tcher.getMname());
			ps.setString(3, tcher.getGname());
			ps.setString(4, tcher.getSex());
			ps.setString(5, tcher.getDob());
			ps.setString(6, tcher.getPob());
			ps.setString(7, tcher.getNationality());
			ps.setString(8, tcher.getId_no());
			ps.setString(9, tcher.getTi_status());
			ps.setString(10, tcher.getTi_id());
			rs = ps.executeUpdate();
			
			if(rs > 0){
				rslt = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		return rslt;
	}
	
	public static List<TeacherBean> getTeacherPerInfo(TeacherBean tchr){
		List<TeacherBean> rslt = new ArrayList<TeacherBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(TeacherQueries.getTeachPerInfo);
			ps.setString(1, tchr.getTi_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				TeacherBean t = new TeacherBean();
				
				t.setTi_id(rs.getString("TI_ID"));
				t.setFname(rs.getString("FNAME"));
				t.setMname(rs.getString("MNAME"));
				t.setGname(rs.getString("GNAME"));
				t.setSex(rs.getString("SEX"));
				t.setDob(rs.getString("DOB"));
				t.setPob(rs.getString("POB"));
				t.setPob(rs.getString("NATIONALITY"));
				t.setId_no(rs.getString("ID_NO"));
				t.setTi_status(rs.getString("TI_STATUS"));
				
				rslt.add(t);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static boolean uploadPic(TeacherBean tchr, String photo_name){
		boolean rslt = true;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			String photo_path = "Photos/teach_photo/" + photo_name;
			con = Connector.connect();
			ps = con.prepareStatement(TeacherQueries.updateTeachPhotoInfo);
			ps.setString(1, photo_name);
			ps.setString(2, photo_path);
			ps.setString(3, tchr.getTi_id());
			rs = ps.executeUpdate();
			
			if(rs > 0){
				rslt = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}

	public static List<TeacherBean> getTeacherNumPerSex(){
		
		List<TeacherBean> rslt = new ArrayList<TeacherBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			con = Connector.connect();
			
			ps = con.prepareStatement(TeacherQueries.getStudentNumPerYear);			
			rs = ps.executeQuery();
				
			while (rs.next()) {
				TeacherBean sb = new TeacherBean();
				
				sb.setMale_num(rs.getString("male"));
				sb.setFemale_num(rs.getString("female"));
				
				rslt.add(sb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {

				}
			}
		}
		
		return rslt;
	}
}
