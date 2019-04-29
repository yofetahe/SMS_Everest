package adminClass.special_registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;
import studentClass.StudentBean;
import util.ReturnCurrentEthiopianYear;

public class SpeRegistrationDAO {
	
	public static List<StudentBean> getListPerGradeDetail(String clid, String cdid, String ac_year) {
		// get student list per grade detail

		List<StudentBean> slistperdetail = new ArrayList<StudentBean>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			con = Connector.connect();
			ps = con.prepareStatement(SpeRegistrationQueries.unregStudentListPerClassDetail);
			ps.setString(1, clid);
			ps.setString(2, cdid);
			ps.setString(3, ac_year);
			
			rs = ps.executeQuery();

			while (rs.next()) {

				StudentBean studbean = new StudentBean();

				studbean.setSi_id(rs.getString("SI_ID"));
				studbean.setFname(rs.getString("FNAME"));
				studbean.setMname(rs.getString("MNAME"));
				studbean.setGname(rs.getString("GNAME"));
				studbean.setFullName(rs.getString("FNAME") + " "
						+ rs.getString("MNAME") + " " + rs.getString("GNAME"));
				studbean.setMother_name(rs.getString("MOTHER_NAME"));
				studbean.setSex(rs.getString("SEX"));
				studbean.setDob(rs.getString("DOB"));
				studbean.setPob(rs.getString("POB"));
				studbean.setNationality(rs.getString("NATIONALITY"));
				studbean.setId_no(rs.getString("ID_NO"));
				studbean.setSi_status(rs.getString("SI_STATUS"));
				studbean.setRslt_status(rs.getString("stud_status"));
				studbean.setPhoto_name(rs.getString("photo_name"));
				studbean.setPhoto_path(rs.getString("photo_path"));

				slistperdetail.add(studbean);

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

		return slistperdetail;
	}
	
	public static List<StudentBean> getRegisteredListPerGradeDetail(SpeRegistrationBean srb) {
		// get student list per grade detail

		List<StudentBean> slistperdetail = new ArrayList<StudentBean>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			con = Connector.connect();
			ps = con.prepareStatement(SpeRegistrationQueries.regStudentListPerClassDetail);
			ps.setString(1, srb.getClass_bean().getCl_id());
			ps.setString(2, srb.getCdetail_bean().getCd_id());
			ps.setString(3, ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			
			rs = ps.executeQuery();

			while (rs.next()) {

				StudentBean studbean = new StudentBean();

				studbean.setSi_id(rs.getString("SI_ID"));
				studbean.setFname(rs.getString("FNAME"));
				studbean.setMname(rs.getString("MNAME"));
				studbean.setGname(rs.getString("GNAME"));
				studbean.setFullName(rs.getString("FNAME") + " "
						+ rs.getString("MNAME") + " " + rs.getString("GNAME"));
				studbean.setMother_name(rs.getString("MOTHER_NAME"));
				studbean.setSex(rs.getString("SEX"));
				studbean.setDob(rs.getString("DOB"));
				studbean.setPob(rs.getString("POB"));
				studbean.setNationality(rs.getString("NATIONALITY"));
				studbean.setId_no(rs.getString("ID_NO"));
				studbean.setSi_status(rs.getString("SI_STATUS"));
				studbean.setRslt_status(rs.getString("stud_status"));
				studbean.setPhoto_name(rs.getString("photo_name"));
				studbean.setPhoto_path(rs.getString("photo_path"));

				slistperdetail.add(studbean);

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

		return slistperdetail;
	}
	
	private static List<SpeRegistrationBean> addedStudList = new ArrayList<SpeRegistrationBean>();
	
	public static void speAddSelectedStudent(SpeRegistrationBean srb){
		addedStudList.add(srb);
	}
	
	public static void speRemoveSelectedStudent(SpeRegistrationBean srb){
		
		for(int i = 0; i < addedStudList.size(); i++){
			if(srb.getStud_bean().getSi_id().equals(addedStudList.get(i).getStud_bean().getSi_id())){
				addedStudList.remove(i);
			}
		}
	}
	
	public static boolean speRegisterSelectedStudent(){
		boolean rslt = false;
		if(addedStudList.size() == 0){
			return rslt;
		}
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0, counter = 0;
		
		try{
			
			con = Connector.connect();
			
			for(int i = 0; i < addedStudList.size(); i++){

				ps = con.prepareStatement(SpeRegistrationQueries.StudentSpecialRegistration);
				ps.setString(1, addedStudList.get(i).getStud_bean().getSi_id());
				rs = ps.executeUpdate();
				
				if(rs > 0){
					counter++;
				}
				rs = 0;
			}
			
			if(counter == addedStudList.size()){
				rslt = true;
			}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception e){}
			}
		}
		
		return rslt;
	}
	
	
	private static List<SpeRegistrationBean> addedRegStudList = new ArrayList<SpeRegistrationBean>();
	
	public static void speAddRegisteredStudent(SpeRegistrationBean srb){
		addedRegStudList.add(srb);
	}
	
	public static void speRemoveSelectedRegisteredStudent(SpeRegistrationBean srb){
		
		for(int i = 0; i < addedRegStudList.size(); i++){
			if(srb.getStud_bean().getSi_id().equals(addedRegStudList.get(i).getStud_bean().getSi_id())){
				addedRegStudList.remove(i);
			}
		}
	}
	
	public static boolean speRemoveStudentSpecialRegistration(){
		boolean rslt = false;
		if(addedRegStudList.size() == 0){
			return rslt;
		}
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0, counter = 0;
		
		try{
			
			con = Connector.connect();
			
			for(int i = 0; i < addedRegStudList.size(); i++){

				ps = con.prepareStatement(SpeRegistrationQueries.RemoveStudentSpecialRegistration);
				ps.setString(1, addedRegStudList.get(i).getStud_bean().getSi_id());
				rs = ps.executeUpdate();
				
				if(rs > 0){
					counter++;
				}
				rs = 0;
			}
			
			if(counter == addedRegStudList.size()){
				rslt = true;
			}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception e){}
			}
		}
		
		return rslt;
	}

}
