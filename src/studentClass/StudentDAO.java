package studentClass;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import cRoomClass.CRoomQueries;
import connectionClass.Connector;
import examClass.exam_result.ExamResultBean;
import examClass.exam_result.ExamResultQueries;
import studentClass.emergency_contact.EmergencyContactBean;
import studentClass.emergency_contact.EmergencyContactQueries;
import util.AgeCalculator;
import util.ReturnCurrentEthiopianYear;
import util.TodayDate_YYYYMMDD;

public class StudentDAO {

	public static boolean validateStudIdNumber(String stud_id) {
		boolean rslt = false;

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.validateStudIdNumber);
			ps.setString(1, stud_id);
			rs = ps.executeQuery();

			while (rs.next()) {
				rslt = true;
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

	public synchronized static String getLastStudentNumber() {
		String lstSiId = "";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.getLastSiId);
			rs = ps.executeQuery();
						
			while(rs.next()){
				if(Integer.parseInt(rs.getString("si_id")) + 1 < 100){
					lstSiId = "000" + (Integer.parseInt(rs.getString("si_id")) + 1);
				} else if(Integer.parseInt(rs.getString("si_id")) + 1 >= 100 && Integer.parseInt(rs.getString("si_id")) + 1 < 1000){
					lstSiId = "00" + (Integer.parseInt(rs.getString("si_id")) + 1);
				} else {
					lstSiId = "0" + (Integer.parseInt(rs.getString("si_id")) + 1);
				}
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
		
		if(lstSiId == "" || lstSiId.equals("")){
			lstSiId = "0001";
		}

		return lstSiId;
	}

	public static List<StudentBean> getUserRoleList(String utid) {
		List<StudentBean> url = new ArrayList<StudentBean>();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {

			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.getUserRoleList);
			ps.setString(1, utid);
			rs = ps.executeQuery();

			while (rs.next()) {
				StudentBean studbean = new StudentBean();

				studbean.setM_id(rs.getString("M_ID"));
				studbean.setM_name(rs.getString("M_NAME"));

				url.add(studbean);
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

		return url;
	}
	
	public static List<StudentBean> getStudentProfile(StudentBean std) {		

		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();

		// get student list
		List<StudentBean> studlist = new ArrayList<StudentBean>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.getStudentPerfile);
			ps.setString(1, std.getSi_id());
			rs = ps.executeQuery();

			while (rs.next()) {
				StudentBean studbean = new StudentBean();

				studbean.setSi_id(rs.getString("SI_ID"));
				studbean.setFname(rs.getString("FNAME"));
				studbean.setMname(rs.getString("MNAME"));
				studbean.setGname(rs.getString("GNAME"));
				studbean.setMother_name(rs.getString("MOTHER_NAME"));
				studbean.setSex(rs.getString("SEX"));
				studbean.setDob(rs.getString("DOB"));
				studbean.setPob(rs.getString("POB"));
				studbean.setNationality(rs.getString("NATIONALITY"));
				studbean.setId_no(rs.getString("ID_NO"));
				studbean.setSi_status(rs.getString("SI_STATUS"));
				studbean.setPhoto_path(rs.getString("PHOTO_PATH"));
				studbean.setPhoto_name(rs.getString("PHOTO_NAME"));

				studlist.add(studbean);
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

		return studlist;

	}
	
	public static List<StudentBean> getStudentProfileByAcademicYear(StudentBean std) {		

		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();

		// get student list
		List<StudentBean> studlist = new ArrayList<StudentBean>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.getStudentPerfileByAcademicYear);
			ps.setString(1, std.getSi_id());
			ps.setString(2, std.getAc_year());
			rs = ps.executeQuery();

			while (rs.next()) {
				StudentBean studbean = new StudentBean();

				studbean.setSi_id(rs.getString("SI_ID"));
				studbean.setFname(rs.getString("FNAME"));
				studbean.setMname(rs.getString("MNAME"));
				
				studbean.setGname(rs.getString("GNAME").equals("GrandFather")?"":rs.getString("GNAME"));
				
				studbean.setMother_name(rs.getString("MOTHER_NAME"));
				studbean.setSex(rs.getString("SEX"));
				studbean.setDob(rs.getString("DOB"));
				studbean.setPob(rs.getString("POB"));
				studbean.setNationality(rs.getString("NATIONALITY"));
				studbean.setId_no(rs.getString("ID_NO"));
				studbean.setSi_status(rs.getString("SI_STATUS"));
				studbean.setPhoto_path(rs.getString("PHOTO_PATH"));
				studbean.setPhoto_name(rs.getString("PHOTO_NAME"));
				studbean.setRslt_status(rs.getString("STUD_STATUS"));
				studbean.setAge(AgeCalculator.calculateAgeBasedOnGivenYear(rs.getString("DOB"), std.getAc_year()));
				
				studlist.add(studbean);
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

		return studlist;

	}

	public static List<StudentBean> getList(String ac_year) {		

		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();

		// get student list
		List<StudentBean> studlist = new ArrayList<StudentBean>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.allStudentList);
			if (ac_year == null) {
				ps.setString(1, yr);
			} else {
				ps.setString(1, ac_year);
			}
			rs = ps.executeQuery();

			while (rs.next()) {
				StudentBean studbean = new StudentBean();

				studbean.setSi_id(rs.getString("SI_ID"));
				studbean.setFname(rs.getString("FNAME"));
				studbean.setMname(rs.getString("MNAME"));
				studbean.setGname(rs.getString("GNAME"));
				studbean.setMother_name(rs.getString("MOTHER_NAME"));
				studbean.setSex(rs.getString("SEX"));
				studbean.setDob(rs.getString("DOB"));
				studbean.setPob(rs.getString("POB"));
				studbean.setNationality(rs.getString("NATIONALITY"));
				studbean.setId_no(rs.getString("ID_NO"));
				studbean.setSi_status(rs.getString("SI_STATUS"));
				studbean.setPhoto_path(rs.getString("PHOTO_PATH"));
				studbean.setPhoto_name(rs.getString("PHOTO_NAME"));

				studlist.add(studbean);
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

		return studlist;

	}
	
	public static List<StudentBean> getInactiveList(String ac_year) {		

		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();

		// get student list
		List<StudentBean> studlist = new ArrayList<StudentBean>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.allInactiveStudentList);
			if (ac_year == null) {
				ps.setString(1, yr);
			} else {
				ps.setString(1, ac_year);
			}
			rs = ps.executeQuery();

			while (rs.next()) {
				StudentBean studbean = new StudentBean();

				studbean.setSi_id(rs.getString("SI_ID"));
				studbean.setFname(rs.getString("FNAME"));
				studbean.setMname(rs.getString("MNAME"));
				studbean.setGname(rs.getString("GNAME"));
				studbean.setMother_name(rs.getString("MOTHER_NAME"));
				studbean.setSex(rs.getString("SEX"));
				studbean.setDob(rs.getString("DOB"));
				studbean.setPob(rs.getString("POB"));
				studbean.setNationality(rs.getString("NATIONALITY"));
				studbean.setId_no(rs.getString("ID_NO"));
				studbean.setSi_status(rs.getString("SI_STATUS"));
				studbean.setPhoto_path(rs.getString("PHOTO_PATH"));
				studbean.setPhoto_name(rs.getString("PHOTO_NAME"));

				studlist.add(studbean);
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

		return studlist;

	}
	
	public static List<StudentBean> getCandidateStudentList(){
		List<StudentBean> studlist = new ArrayList<StudentBean>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.allCandidateStudentList);
			rs = ps.executeQuery();

			while (rs.next()) {
				StudentBean studbean = new StudentBean();

				studbean.setSi_id(rs.getString("SI_ID"));
				studbean.setFname(rs.getString("FNAME"));
				studbean.setMname(rs.getString("MNAME"));
				studbean.setGname(rs.getString("GNAME"));
				studbean.setMother_name(rs.getString("MOTHER_NAME"));
				studbean.setSex(rs.getString("SEX"));
				studbean.setDob(rs.getString("DOB"));
				studbean.setPob(rs.getString("POB"));
				studbean.setNationality(rs.getString("NATIONALITY"));
				studbean.setId_no(rs.getString("ID_NO"));
				studbean.setSi_status(rs.getString("SI_STATUS"));
				studbean.setPhoto_path(rs.getString("PHOTO_PATH"));
				studbean.setPhoto_name(rs.getString("PHOTO_NAME"));

				studlist.add(studbean);
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

		return studlist;
	}
	
	public static boolean deleteCandidateStudent(String si_id){
		
		boolean rslt = false;
		
		Connection con = null;		
		PreparedStatement ps = null;
		int rs = 0;

		try {
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.deleteCandidateStudent);
			ps.setString(1, si_id);
			rs = ps.executeUpdate();

			if(rs > 0){
				rslt = true;
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
	
	public static List<StudentBean> getDropoutStudentList(){
		List<StudentBean> studlist = new ArrayList<StudentBean>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.allDropoutStudentList);
			rs = ps.executeQuery();

			while (rs.next()) {
				StudentBean studbean = new StudentBean();

				studbean.setSi_id(rs.getString("SI_ID"));
				studbean.setFname(rs.getString("FNAME"));
				studbean.setMname(rs.getString("MNAME"));
				studbean.setGname(rs.getString("GNAME"));
				studbean.setMother_name(rs.getString("MOTHER_NAME"));
				studbean.setSex(rs.getString("SEX"));
				studbean.setDob(rs.getString("DOB"));
				studbean.setPob(rs.getString("POB"));
				studbean.setNationality(rs.getString("NATIONALITY"));
				studbean.setId_no(rs.getString("ID_NO"));
				studbean.setSi_status(rs.getString("SI_STATUS"));
				studbean.setPhoto_path(rs.getString("PHOTO_PATH"));
				studbean.setPhoto_name(rs.getString("PHOTO_NAME"));

				studlist.add(studbean);
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

		return studlist;
	}
	
	
	public static boolean activateDropoutStudent(String si_id, String ac_year){
		
		Connection con = null;		
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		int rs = 0, rs2 = 0;
		
		boolean rslt = false;
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		try {
			
			con = Connector.connect();
			
			if(ac_year.equals(yr)){
				
				ps = con.prepareStatement(StudentQueries.activateDropoutStudentInfo);
				ps.setString(1, si_id);
				rs = ps.executeUpdate();
				
				ps2 = con.prepareStatement(StudentQueries.activateCurrentYearDropoutStudentReg);
				ps2.setString(1, si_id);
				ps2.setString(2, ac_year);						
				rs2 = ps2.executeUpdate();
				
			} else {
			
				ps = con.prepareStatement(StudentQueries.activateDropoutStudentInfo);
				ps.setString(1, si_id);
				rs = ps.executeUpdate();				
			}
			
			if(rs > 0){
				
				rslt = true;
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
	
	

	public static List<StudentBean> getListPerGrade(String class_id, String ac_year) {		

		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();

		// get student list per grade
		List<StudentBean> studlist = new ArrayList<StudentBean>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try {

			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.StudentListPerGrade);
			ps.setString(1, class_id);
			if (ac_year == null) {
				ps.setString(2, yr);
			} else {
				ps.setString(2, ac_year);
			}
			ps.setFetchSize(10);
			rs = ps.executeQuery();

			while (rs.next()) {
				StudentBean studbean = new StudentBean();

				studbean.setSi_id(rs.getString("SI_ID"));
				studbean.setFname(rs.getString("FNAME"));
				studbean.setMname(rs.getString("MNAME"));
				studbean.setGname(rs.getString("GNAME"));
				studbean.setMother_name(rs.getString("MOTHER_NAME"));
				studbean.setSex(rs.getString("SEX"));
				studbean.setDob(rs.getString("DOB"));
				studbean.setPob(rs.getString("POB"));
				studbean.setNationality(rs.getString("NATIONALITY"));
				studbean.setId_no(rs.getString("ID_NO"));
				studbean.setSi_status(rs.getString("SI_STATUS"));
				studbean.setPhoto_name(rs.getString("photo_name"));
				studbean.setPhoto_path(rs.getString("photo_path"));
				studbean.setCd_id(rs.getString("cd_id"));

				studlist.add(studbean);
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

		return studlist;

	}
	
	public static boolean validateExamDate(String exsub_id){
		
		boolean validation = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamResultQueries.getSubjectExamDate);
			ps.setString(1, exsub_id);
			ps.setInt(2, Integer.parseInt(yr));
			rs = ps.executeQuery();
			
			while(rs.next()){
								
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
				Date examDate = sdf.parse(rs.getString(1));
				Date dt = new Date();
				String td = sdf.format(dt);
				Date today = sdf.parse(td);
				
				if(today.compareTo(examDate) > 0){
					validation = true;
				}
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
		
		return validation;
		
	}

	public static List<ExamResultBean> getStudentListPerGrade(ExamResultBean exrslt) {

		List<ExamResultBean> studlist = new ArrayList<ExamResultBean>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		try {

			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.StudentListForExamRslt);
			ps.setString(1, exrslt.getCl_id());
			ps.setString(2, exrslt.getCd_id());
			ps.setInt(3, Integer.parseInt(yr));
			ps.setString(4, exrslt.getExsub_id());
			ps.setString(5, exrslt.getAt_id());
			ps.setInt(6, Integer.parseInt(yr));
			ps.setString(7, exrslt.getCl_id());
			ps.setString(8, exrslt.getCd_id());
			ps.setString(9, exrslt.getExsub_id());
			rs = ps.executeQuery();

			while (rs.next()) {
				ExamResultBean studbean = new ExamResultBean();

				studbean.setSi_id(rs.getString("SI_ID"));
				studbean.setFname(rs.getString("FNAME"));
				studbean.setMname(rs.getString("MNAME"));
				studbean.setGname(rs.getString("GNAME"));
				//studbean.setResult(rs.getString("RSLT"));
				studbean.setTotal_mark(rs.getString("TOTAL_MARK"));
				studbean.setPass_mark(rs.getString("PASS_MARK"));

				studlist.add(studbean);
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

		return studlist;

	}

	public static List<StudentBean> getListPerGradeDetail(String clid, String cdid, String ac_year) {
		// get student list per grade detail

		List<StudentBean> slistperdetail = new ArrayList<StudentBean>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.studentListPerClassDetail);
			ps.setString(1, clid);
			ps.setString(2, cdid);
			ps.setString(3, ac_year);
			//ps.setFetchSize(10);
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
	
	public static List<StudentBean> getListPerGradeDetailForAttendance(String clid, String cdid, String ac_year, String eduoff_filter_status) {
		// get student list per grade detail

		List<StudentBean> slistperdetail = new ArrayList<StudentBean>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		
		try {
			
			con = Connector.connect();
			
			if(eduoff_filter_status.equalsIgnoreCase("yes")){
				
				ps = con.prepareStatement(StudentQueries.studentListPerClassDetailForAttendanceFiltered);
			} else {
				
				ps = con.prepareStatement(StudentQueries.studentListPerClassDetailForAttendance);
			}
			
			ps.setString(1, clid);
			ps.setString(2, cdid);
			ps.setString(3, ac_year);
			ps.setFetchSize(10);
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
				
				List<EmergencyContactBean> emergencyContactBeanList = new ArrayList<EmergencyContactBean>();
				
				ps1 = con.prepareStatement(EmergencyContactQueries.getContactList);
				ps1.setString(1, studbean.getSi_id());
				rs1 = ps1.executeQuery();
				
				while(rs1.next()){
					EmergencyContactBean cont = new EmergencyContactBean();
					
					cont.setSec_id(rs1.getString("SEC_ID"));
					cont.setContact_name(rs1.getString("CONT_NAME"));
					cont.setRelationship(rs1.getString("RELATIONSHIP"));
					cont.setMob_no(rs1.getString("MOB_NO"));
					cont.setHome_phone_no(rs1.getString("HOME_PHONE_NO"));
					cont.setOffice_phone_no(rs1.getString("OFFICE_PHONE_NO"));
					cont.setSec_status(rs1.getString("SEC_STATUS"));
					
					emergencyContactBeanList.add(cont);
					
				}
				
				studbean.setEmergencyContactBeanList(emergencyContactBeanList);

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
	
	public static int countListPerGradeDetail(String clcd_id, String ac_year) {
		// get student list per grade detail

		int count = 0;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.countStudentListPerClassDetail);
			ps.setString(1, clcd_id);
			ps.setString(2, ac_year);
			
			rs = ps.executeQuery();

			while (rs.next()) {

				count = Integer.parseInt(rs.getString("count"));

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

		return count;
	}
	
	public static List<StudentBean> getStudContactInfoListPerGradeDetail(String clid, String cdid, String ac_year) {
		// get student list per grade detail

		List<StudentBean> slistperdetail = new ArrayList<StudentBean>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.studentContactInfoListPerClassDetail);
			ps.setString(1, clid);
			ps.setString(2, cdid);
			ps.setString(3, ac_year);
			ps.setFetchSize(10);
			rs = ps.executeQuery();

			while (rs.next()) {

				StudentBean studbean = new StudentBean();

				studbean.setSi_id(rs.getString("SI_ID"));
				studbean.setFname(rs.getString("FNAME"));
				studbean.setMname(rs.getString("MNAME"));
				studbean.setGname(rs.getString("GNAME"));
				studbean.setFullName(rs.getString("FNAME") + " "
						+ rs.getString("MNAME") + " " + rs.getString("GNAME"));
				studbean.setSex(rs.getString("sex"));

				EmergencyContactBean emrgbean = new EmergencyContactBean();
				emrgbean.setContact_name(rs.getString("cont_name"));
				emrgbean.setRelationship(rs.getString("relationship"));
				emrgbean.setMob_no(rs.getString("mob_no"));
				
				studbean.setEmergencyContactBean(emrgbean);

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
	
	public static List<StudentBean> getStudInfoWithEmergencyContactListPerGradeDetail(String clid, String cdid, String ac_year) {
		
		List<StudentBean> slistperdetail = new ArrayList<StudentBean>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.studentInfoWithContactInfoPerClassDetail);
			ps.setString(1, clid);
			ps.setString(2, cdid);
			ps.setString(3, ac_year);
			ps.setFetchSize(10);
			rs = ps.executeQuery();

			while (rs.next()) {

				StudentBean studbean = new StudentBean();

				studbean.setSi_id(rs.getString("SI_ID"));
				studbean.setFname(rs.getString("FNAME"));
				studbean.setMname(rs.getString("MNAME"));
				studbean.setGname(rs.getString("GNAME"));
				studbean.setFullName(rs.getString("FNAME") + " " + rs.getString("MNAME") + " " + rs.getString("GNAME"));
				studbean.setMother_name(rs.getString("mother_name"));
				studbean.setSex(rs.getString("sex"));
				studbean.setDob(rs.getString("dob"));
				studbean.setPob(rs.getString("pob"));
				studbean.setNationality(rs.getString("nationality"));

				EmergencyContactBean emrgbean = new EmergencyContactBean();
				emrgbean.setContact_name(rs.getString("cont_name"));
				emrgbean.setRelationship(rs.getString("relationship"));
				emrgbean.setMob_no(rs.getString("mob_no"));				
				studbean.setEmergencyContactBean(emrgbean);

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
	
	public static List<StudentBean> getStudWithoutPhotoContactInfoListPerGradeDetail(String clid, String cdid, String ac_year) {
		// get student list per grade detail

		List<StudentBean> slistperdetail = new ArrayList<StudentBean>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.studentWithoutPhotoContactInfoListPerClassDetail);
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
				studbean.setSex(rs.getString("sex"));

				EmergencyContactBean emrgbean = new EmergencyContactBean();
				emrgbean.setContact_name(rs.getString("cont_name"));
				emrgbean.setRelationship(rs.getString("relationship"));
				emrgbean.setMob_no(rs.getString("mob_no"));
				
				studbean.setEmergencyContactBean(emrgbean);

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
	
	public static List<StudentBean> getStudWithoutContactInfoListPerGradeDetail(String clid, String cdid, String ac_year) {
		// get student list per grade detail

		List<StudentBean> slistperdetail = new ArrayList<StudentBean>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.studentWithoutContactInfoListPerClassDetail);
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
				studbean.setSex(rs.getString("sex"));

				EmergencyContactBean emrgbean = new EmergencyContactBean();
				emrgbean.setContact_name(rs.getString("cont_name"));
				emrgbean.setRelationship(rs.getString("relationship"));
				emrgbean.setMob_no(rs.getString("mob_no"));
				
				studbean.setEmergencyContactBean(emrgbean);

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
	
	public static List<StudentBean> getListPerGradeDetailForCertificate(String clid, String cdid, String ac_year) {
		// get student list per grade detail

		List<StudentBean> slistperdetail = new ArrayList<StudentBean>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.studentListPerClassDetailForCertificate);
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

	public static List<StudentBean> getListPerGradeDetailYearSem(String clid,
			String cdid, String ac_year, String at_id) {
		// getting academic year --- starting from May to August the academic
		// year consider the next year
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());

		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);

		String yr = util.DateConvertor.dateConvertor(day + "-" + month + "-"
				+ year);

		// get student list per grade detail

		List<StudentBean> slistperdetail = new ArrayList<StudentBean>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.studentListPerClassDetail);
			ps.setString(1, clid);
			ps.setString(2, cdid);
			if (ac_year == null) {
				ps.setString(3, yr);
			} else {
				ps.setString(3, ac_year);
			}
			rs = ps.executeQuery();

			while (rs.next()) {

				StudentBean studbean = new StudentBean();

				studbean.setSi_id(rs.getString("SI_ID"));
				studbean.setFname(rs.getString("FNAME"));
				studbean.setMname(rs.getString("MNAME"));
				studbean.setGname(rs.getString("GNAME"));
				studbean.setMother_name(rs.getString("MOTHER_NAME"));
				studbean.setSex(rs.getString("SEX"));
				studbean.setDob(rs.getString("DOB"));
				studbean.setPob(rs.getString("POB"));
				studbean.setId_no(rs.getString("ID_NO"));
				studbean.setSi_status(rs.getString("SI_STATUS"));

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

	public static List<StudentClassBean> getClassList() {

		List<StudentClassBean> clist = new ArrayList<StudentClassBean>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.allClassList);
			rs = ps.executeQuery();

			while (rs.next()) {
				StudentClassBean sclass = new StudentClassBean();

				sclass.setClass_id(rs.getString("CL_ID"));
				sclass.setClass_name(rs.getString("CLASS_NAME"));

				clist.add(sclass);
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

		return clist;
	}
	
	public static List<StudentClassBean> getRelatedClassList(String ti_id) {

		List<StudentClassBean> clist = new ArrayList<StudentClassBean>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = Connector.connect();
			ps = con.prepareStatement(CRoomQueries.getActiveRelatedCroom);
			ps.setString(1, ti_id);
			ps.setString(2, ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear());
			rs = ps.executeQuery();

			while (rs.next()) {
				StudentClassBean sclass = new StudentClassBean();

				sclass.setClass_id(rs.getString("CL_ID"));
				sclass.setClass_name(rs.getString("CLASS_NAME"));

				clist.add(sclass);
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

		return clist;
	}

	
	/*
	 * very important assumption -- system administrator user type always exist on the first row.
	 */
	public static List<StudentClassDetailBean> getClassDetail(String clid, String loggedUserName) {

		List<StudentClassDetailBean> cldetail = new ArrayList<StudentClassDetailBean>();
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		String utid = "";
		
		try {

			con = Connector.connect();
//			ps1 = con.prepareStatement(StudentQueries.getLoggedinUserUserType);
//			ps1.setString(1, loggedUserName);
//			rs1 = ps1.executeQuery();
//			
//			while(rs1.next()){
//				utid = rs1.getString("ut_id");						
//			}
//			
//			if(utid.equalsIgnoreCase("2")){
//				loggedUserName = "";
//			}
			
			ps = con.prepareStatement( StudentQueries.classDetailList);
			ps.setString(1, clid);
			ps.setString(2, clid);
			ps.setInt(3, Integer.parseInt(yr));
			ps.setString(4, loggedUserName);
			rs = ps.executeQuery();

			while (rs.next()) {
				StudentClassDetailBean cldtl = new StudentClassDetailBean();

				cldtl.setCd_id(rs.getString("CD_ID"));
				cldtl.setCd_name(rs.getString("CD_NAME"));

				cldetail.add(cldtl);
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

		return cldetail;
	}
		
	public static List<StudentClassDetailBean> getClassDetailTeacherRelated(String clid, String ti_id, String loggedUserName) {

		List<StudentClassDetailBean> cldetail = new ArrayList<StudentClassDetailBean>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		try {

			con = Connector.connect();
			
			ps = con.prepareStatement(StudentQueries.classDetailTeacherRelatedList);
			ps.setString(1, clid);
			ps.setString(2, clid);
			ps.setString(3, ti_id);
			ps.setInt(4, Integer.parseInt(yr));
			rs = ps.executeQuery();

			while (rs.next()) {
				StudentClassDetailBean cldtl = new StudentClassDetailBean();

				cldtl.setCd_id(rs.getString("CD_ID"));
				cldtl.setCd_name(rs.getString("CD_NAME"));

				cldetail.add(cldtl);
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

		return cldetail;
	}
	
	
	
	public static boolean getStudentDropOutStatus(String siid, String acyear){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.getStudentDropOutStatus);
			ps.setString(1, siid);
			ps.setString(2, acyear);
			rs = ps.executeQuery();
			
			while(rs.next()){
				if(rs.getString("stud_status").equals("DropOut")){
					rslt = true;
				}
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
	
	public static boolean confirmStudentDropOut(String siid){
		boolean rslt = false;
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		int rs = 0, rs2 = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.updateStudentRegistrationInfo);
			ps.setString(1, "DropOut");
			ps.setString(2, siid);
			ps.setString(3, yr);
			rs = ps.executeUpdate();
			
			if(rs > 0){
				ps2 = con.prepareStatement(StudentQueries.updateStudentPersonalInfo);
				ps2.setString(1, siid);
				rs2 = ps2.executeUpdate();
			}
			
			if(rs2 > 0){
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
	
	

	public static Boolean updatePersonalInfo(StudentBean stud, String loggedInUser) {
		Boolean rslt = false;

		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;

		try {

			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.updateStudInfo);
			ps.setString(1, stud.getFname());
			ps.setString(2, stud.getMname());
			ps.setString(3, stud.getGname());
			ps.setString(4, stud.getMother_name());
			ps.setString(5, stud.getSex());
			ps.setString(6, stud.getDob());
			ps.setString(7, stud.getPob());
			ps.setString(8, stud.getNationality());
			ps.setString(9, stud.getSi_status());
			ps.setString(10, stud.getId_no());
			ps.setString(11, loggedInUser);
			ps.setString(12, TodayDate_YYYYMMDD.getToday());
			ps.setString(13, stud.getSi_id());
			rs = ps.executeUpdate();

			if (rs > 0) {
				rslt = true;
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

	public synchronized static boolean saveStudentInfo(StudentBean stud, String idno, String loggedInUser) {
		
		boolean rslt = false;

		Connection con = null;
		PreparedStatement ps = null;		
		ResultSet rs = null;
		
		int stud_info = 0;
		int emerg_contact = 0;
		int emerg_contact_1 = 0;
		int emerg_contact_2 = 0;

		try {

			con = Connector.connect();
			
			con.setAutoCommit(false);
			
			if(validateStudentInfo(stud)){
				return rslt;
			}
			
			ps = con.prepareStatement(StudentQueries.insertStudInfo);
			ps.setString(1, stud.getFname());
			ps.setString(2, stud.getMname());
			ps.setString(3, stud.getGname());
			ps.setString(4, stud.getMother_name());
			ps.setString(5, stud.getSex());
			ps.setString(6, stud.getDob());
			ps.setString(7, stud.getPob());
			ps.setString(8, stud.getNationality());
			ps.setString(9, idno);
			ps.setString(10, "default");
			ps.setString(11, "Photos/stud_photo/default.jpg");
			ps.setInt(12, 0);
			ps.setString(13, "");
			ps.setString(14, loggedInUser);
			ps.setString(15, TodayDate_YYYYMMDD.getToday());
			stud_info = ps.executeUpdate();

			if (stud_info > 0) {
				
				///*** search student by name ***///
				ps = con.prepareStatement(StudentQueries.checkStudInfo);
				ps.setString(1, stud.getFname());
				ps.setString(2, stud.getMname());
				ps.setString(3, stud.getGname());
				ps.setString(4, stud.getMother_name());
				ps.setString(5, stud.getSex());
				rs = ps.executeQuery();
				
				int si_id = 0;
				
				while(rs.next()){
					
					si_id = rs.getInt("si_id");
				}
				
				///*** Save parent information ***///
				String[] contact_info = stud.getContact_information().split("-");
				
				if(contact_info.length > 0){
					
					emerg_contact++;///to see if the contact info or not
					
					String[] contact_info_1 = contact_info[0].split(",");
					String[] contact_info_2 = contact_info.length == 2?contact_info[1].split(","):new String[]{};
					
					if(contact_info_1.length > 0){
												
						ps = con.prepareStatement(EmergencyContactQueries.insertEmergContact);
						ps.setString(1, contact_info_1[0]);//contname
						ps.setString(2, contact_info_1[1]);//rel
						ps.setString(3, contact_info_1[2]);//mobno
						if(contact_info_1.length > 3){
							ps.setString(4, contact_info_1[3]);//offphoneno
							ps.setString(5, contact_info_1[4]);//homephoneno
						} else {
							ps.setString(4, "");//offphoneno
							ps.setString(5, "");//homephoneno
						}
						ps.setInt(6, si_id);
						emerg_contact_1 = ps.executeUpdate();
					}
					if(contact_info_2.length > 0){
						
						ps = con.prepareStatement(EmergencyContactQueries.insertEmergContact);
						ps.setString(1, contact_info_2[0]);//contname
						ps.setString(2, contact_info_2[1]);//rel
						ps.setString(3, contact_info_2[2]);//mobno
						if(contact_info_2.length > 3){
							ps.setString(4, contact_info_2[3]);//offphoneno
							ps.setString(5, contact_info_2[4]);//homephoneno
						} else {
							ps.setString(4, "");//offphoneno
							ps.setString(5, "");//homephoneno
						}
						ps.setInt(6, si_id);
						emerg_contact_2 = ps.executeUpdate();
					}
				}				
			}
			
			if(stud_info > 0 && (emerg_contact > 0 && (emerg_contact_1 > 0 || emerg_contact_2 > 0))){
				
				con.commit();
				rslt = true;
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
	
	public synchronized static boolean saveStudentProfileInformation(StudentBean stud, File image, String idno, String loggedInUser) {
		
		boolean rslt = false;

		Connection con = null;
		PreparedStatement ps = null;		
		ResultSet rs = null;
		
		int stud_info = 0;
		int stud_photo = 0;
		int emerg_contact_1 = 0;
		int emerg_contact_2 = 0;

		try {

			con = Connector.connect();
			
			con.setAutoCommit(false);
			
			if(validateStudentInfo(stud)){
				return rslt;
			}
			
			ps = con.prepareStatement(StudentQueries.insertStudInfo);
			ps.setString(1, stud.getFname());
			ps.setString(2, stud.getMname());
			ps.setString(3, stud.getGname());
			ps.setString(4, stud.getMother_name());
			ps.setString(5, stud.getSex());
			ps.setString(6, stud.getDob());
			ps.setString(7, stud.getPob());
			ps.setString(8, stud.getNationality());
			ps.setString(9, idno);
			ps.setString(10, "default");
			ps.setString(11, "Photos/stud_photo/default.jpg");
			ps.setInt(12, stud.getBro_sis_number());
			ps.setString(13, stud.getSpecial_attention_info());
			ps.setString(14, loggedInUser);
			ps.setString(15, TodayDate_YYYYMMDD.getToday());
			stud_info = ps.executeUpdate();

			if (stud_info > 0) {
				
				///*** search student by name ***///
				ps = con.prepareStatement(StudentQueries.checkStudInfo);
				ps.setString(1, stud.getFname());
				ps.setString(2, stud.getMname());
				ps.setString(3, stud.getGname());
				ps.setString(4, stud.getMother_name());
				ps.setString(5, stud.getSex());
				rs = ps.executeQuery();
				
				int si_id = 0;
				
				while(rs.next()){
					
					si_id = rs.getInt("si_id");
				}
				
				///*** Saving student photo ***///
				try {
					
					FileInputStream fis = new FileInputStream(image);
					//photo = StudentDAO.saveStudentPhoto(stud, fis, (int)image.length());
					
					ps = con.prepareStatement(StudentQueries.saveStudPhotoInfo);
					ps.setString(1, String.valueOf(si_id));
					ps.setBinaryStream(2, fis, (int)image.length());
					stud_photo = ps.executeUpdate();
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
				///*** Saving parent information ***///
				if(stud.getEmergencyContactBean().getContact_name().length() > 0){
					
					ps = con.prepareStatement(EmergencyContactQueries.insertEmergContact);
					ps.setString(1, stud.getEmergencyContactBean().getContact_name());//contname
					ps.setString(2, stud.getEmergencyContactBean().getRelationship());//rel
					ps.setString(3, stud.getEmergencyContactBean().getMob_no());//mobno
					ps.setString(4, stud.getEmergencyContactBean().getOffice_phone_no());//offphoneno
					ps.setString(5, stud.getEmergencyContactBean().getHome_phone_no());//homephoneno
					ps.setInt(6, si_id);
					emerg_contact_1 = ps.executeUpdate();
				}
				if(stud.getEmergencyContactBean().getContact_name_2().length() > 0){
					
					ps = con.prepareStatement(EmergencyContactQueries.insertEmergContact);
					ps.setString(1, stud.getEmergencyContactBean().getContact_name_2());//contname
					ps.setString(2, stud.getEmergencyContactBean().getRelationship_2());//rel
					ps.setString(3, stud.getEmergencyContactBean().getMob_no_2());//mobno
					ps.setString(4, stud.getEmergencyContactBean().getOffice_phone_no_2());//offphoneno
					ps.setString(5, stud.getEmergencyContactBean().getHome_phone_no_2());//homephoneno
					ps.setInt(6, si_id);
					emerg_contact_2 = ps.executeUpdate();
				}				
			}
			
			if(stud_info > 0 && stud_photo > 0 && (emerg_contact_1 > 0 || emerg_contact_2 > 0)){
				
				con.commit();
				rslt = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {	}
			}
		}

		return rslt;
	}
	
	public static boolean validateStudentInfo(StudentBean stud){
		
		boolean rslt = false;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int counter = 0;

		try {

			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.checkStudInfo);
			ps.setString(1, stud.getFname());
			ps.setString(2, stud.getMname());
			ps.setString(3, stud.getGname());
			ps.setString(4, stud.getMother_name());
			ps.setString(5, stud.getSex());
			
			rs = ps.executeQuery();

			while(rs.next()) {
				counter++;
			}
			
			if(counter > 0){
				rslt = true;
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
	
	public static boolean checkStudentPhotoExistance(StudentBean stud){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.getStudentPhoto);
			ps.setString(1, stud.getSi_id());
			rs = ps.executeQuery();

			if(rs.next()) {
				rslt = true;
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
	
	public static boolean saveStudentPhoto(StudentBean std, FileInputStream fis, int fileLength){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;

		try {
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.saveStudPhotoInfo);
			ps.setString(1, std.getSi_id());
			ps.setBinaryStream(2, fis, fileLength);
			rs = ps.executeUpdate();

			if (rs > 0) {
				rslt = true;
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
	
	public static boolean updateStudentPhoto(StudentBean std, FileInputStream fis, int fileLength){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;

		try {
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.updateStudentPhotoInfo);			
			ps.setBinaryStream(1, fis, fileLength);
			ps.setString(2, std.getSi_id());
			rs = ps.executeUpdate();

			if (rs > 0) {
				rslt = true;
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
	
	public static byte[] getStudentPhoto(StudentBean stud){
		
		byte[] rslt = null;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.getStudentPhoto);
			ps.setString(1, stud.getSi_id());
			rs = ps.executeQuery();

			while(rs.next()) {
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				
				byte[] buf = new byte[1024];
				InputStream in = rs.getBinaryStream("stud_photo");
				int n = 0;
				while((n = in.read(buf)) >= 0){
					baos.write(buf, 0, n);
				}
				in.close();
				
				rslt = baos.toByteArray();
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
		
		if(rslt == null){
			
			File image = new File("C:\\sms_system_file\\everest\\Photos\\stud_photo\\default.jpeg");
			byte[] fileContent;
			try {
				fileContent = Files.readAllBytes(image.toPath());
				rslt = fileContent;
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
		
		return rslt;
	}

	public static boolean uploadPic(StudentBean std, String photo_name) {
		boolean rslt = true;

		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;

		try {
			
			String photo_path = "Photos/stud_photo/" + photo_name;
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.updateStudPhotoInfo);
			ps.setString(1, photo_name);
			ps.setString(2, photo_path);
			ps.setString(3, std.getSi_id());
			rs = ps.executeUpdate();

			if (rs > 0) {
				rslt = true;
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

	public static List<StudentBean> stud_db_rslt(List<StudentClassBean> slist) {
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();

		// getting student count per grade
		List<StudentBean> rslt = new ArrayList<StudentBean>();

		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String totalNum = "";

		try {
			con = Connector.connect();

			ps2 = con.prepareStatement(StudentQueries.getTotalNumberOfStudent);
			ps2.setString(1, yr);
			rs2 = ps2.executeQuery();
			while (rs2.next()) {
				totalNum = rs2.getString("total_no");
				break;
			}

			for (int i = 0; i < slist.size(); i++) {

				ps = con.prepareStatement(StudentQueries.getNoOfStudPerGrd);
				ps.setString(1, slist.get(i).getClass_id());
				ps.setString(2, yr);
				rs = ps.executeQuery();

				while (rs.next()) {
					StudentBean sb = new StudentBean();

					sb.setClass_id(slist.get(i).getClass_id());
					sb.setClass_name(slist.get(i).getClass_name());
					sb.setStud_count(rs.getString("total"));
					sb.setTotal_number(totalNum);
					double per = (Double.parseDouble(rs.getString("total")) / Double
							.parseDouble(totalNum)) * 100;
					sb.setStud_count_prc(String.valueOf(per));

					rslt.add(sb);
				}
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

	public static List<StudentBean> stud_db_persex() {
		
		// getting total number of students per sex
		List<StudentBean> rslt = new ArrayList<StudentBean>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			con = Connector.connect();
			
			ps = con.prepareStatement(StudentQueries.getCurrentYearStudentNoPerSex);			
			rs = ps.executeQuery();
				
			while (rs.next()) {
				StudentBean sb = new StudentBean();
				
				sb.setClass_name(rs.getString("class_name"));
				sb.setNo_male(rs.getString("male"));
				sb.setNo_female(rs.getString("female"));
				
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
	
	public static List<StudentBean> getStudentNumPerYear(){
		
		List<StudentBean> rslt = new ArrayList<StudentBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int count = 0;

		try {
			
			con = Connector.connect();
			
			ps = con.prepareStatement(StudentQueries.getStudentNumPerYear);			
			rs = ps.executeQuery();
				
			while (rs.next()) {
				count++;
				StudentBean sb = new StudentBean();
				
				sb.setAc_year(rs.getString("academic_year"));
				sb.setNo_male(rs.getString("male"));
				sb.setNo_female(rs.getString("female"));
				
				rslt.add(sb);
				if(count == 4){
					break;
				}
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
	
	public static List<StudentBean> getStudentNumPerClass(String ac_year){
		List<StudentBean> rslt = new ArrayList<StudentBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		if(ac_year == null){
			ac_year = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		}
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.getStudentNumPerClassSex);
			ps.setString(1, ac_year);
			rs = ps.executeQuery();
			
			while(rs.next()){
				StudentBean sb = new StudentBean();
				
				sb.setClass_id(rs.getString("cl_id"));
				sb.setClass_name(rs.getString("class_name"));
				sb.setNo_male(rs.getString("male"));
				sb.setNo_female(rs.getString("female"));
				sb.setTotal_number(String.valueOf(Integer.parseInt(rs.getString("male")) + Integer.parseInt(rs.getString("female"))));
				
				rslt.add(sb);
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){
					
				}
			}
		}
		
		return rslt;
	}
	
	public static List<StudentBean> getStudentListPerGradeDetail(StudentBean sb){
		List<StudentBean> rslt = new ArrayList<StudentBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.getStudentNumberPerCDetailAndSex);
			ps.setString(1, sb.getClass_id());
			ps.setString(2, sb.getAc_year());
			rs = ps.executeQuery();
			
			while(rs.next()){
				StudentBean stb = new StudentBean();
				
				stb.setClass_name(rs.getString("class_name"));
				stb.setCd_name(rs.getString("cd_name"));
				stb.setNo_male(rs.getString("male"));
				stb.setNo_female(rs.getString("female"));
				stb.setTotal_number(String.valueOf(Integer.parseInt(rs.getString("male")) + Integer.parseInt(rs.getString("female"))));
				
				rslt.add(stb);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){
					
				}
			}
		}
		
		return rslt;
	}
	
	public static String getStudentNumPerClassDetail(String cl_id, String cd_id, String ac_year){
		
		String studentNumPerClassDetail = "";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.getNumOfStudPerClassDetail);
			ps.setString(1, cl_id);
			ps.setString(2, cd_id);
			ps.setString(3, ac_year);
			rs = ps.executeQuery();
			
			while(rs.next()){
				studentNumPerClassDetail = rs.getString("total");
			}
			
		} catch(Exception ex){
			
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){}
			}
		}
		
		return studentNumPerClassDetail;
	}
	//
	public static int getStudentPreviousClassDetailId(String si_id, String cl_id, String ac_year){
		
		int previous_cd_id = -1;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentQueries.getStudentPreviousClassDetailId);
			ps.setString(1, si_id);
			ps.setString(2, cl_id);
			ps.setString(3, ac_year);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				previous_cd_id = Integer.parseInt(rs.getString("cd_id"));
			}
			
		} catch(Exception ex){
			
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){}
			}
		}
		
		return previous_cd_id;
	}
}
