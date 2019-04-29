package adminClass.id_card_generation;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cRoomClass.CRoomBean;
import connectionClass.Connector;
import studentClass.StudentBean;
import studentClass.current_address.CurrentAddressBean;
import util.ReturnCurrentEthiopianYear;
import util.TodayDate_YYYYMMDD;

public class IDCardDAO {
	
	public static List<StudentBean> getStudListWithoutIDPerGradeDetail(String clid, String cdid, String ac_year) {
		
		List<StudentBean> slistperdetail = new ArrayList<StudentBean>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			con = Connector.connect();
			ps = con.prepareStatement(IDCardQueries.getStudListWithoutID);
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
				studbean.setFullName(rs.getString("FNAME") + " " + rs.getString("MNAME") + " " + rs.getString("GNAME"));
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
				
				studbean.setClass_name(rs.getString("class_name"));
				studbean.setCd_name(rs.getString("cd_name"));
				
//				CurrentAddressBean cab = new CurrentAddressBean();
//				cab.setSub_city(rs.getString("sub_city"));
//				cab.setKebele(rs.getString("kebele"));
//				cab.setHouse_no(rs.getString("house_no"));
//				cab.setHome_phone(rs.getString("home_phone_no"));
//				studbean.setCurrentAddressBean(cab);
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				
				byte[] buf = new byte[1024];
				InputStream in = rs.getBinaryStream("stud_photo");
				int n = 0;
				while((n = in.read(buf)) >= 0){
					baos.write(buf, 0, n);
				}
				in.close();
				
				studbean.setStud_photo(baos.toByteArray());

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
	
	public static List<StudentBean> getStudListWithIDPerGradeDetail(String clid, String cdid, String ac_year) {
		
		List<StudentBean> slistperdetail = new ArrayList<StudentBean>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			con = Connector.connect();
			ps = con.prepareStatement(IDCardQueries.getStudListWithID);
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
	
	public static boolean saveStudentIDCardInfo(List<StudentBean> stud_with_out_id_rslt, int num_of_page, String full_page_status){
		
		boolean rslt = false;
		
		int page_counter = 0;
		
		for(int i = 0, j = 0; i < stud_with_out_id_rslt.size(); i++, j++){
			
			Connection con = null;
			PreparedStatement ps = null;
			int rs = 0;
			
			try {
				
				con = Connector.connect();
				ps = con.prepareStatement(IDCardQueries.saveStudWithID);
				ps.setString(1, stud_with_out_id_rslt.get(i).getSi_id());
				ps.setString(2, "Active");
				ps.setString(3, TodayDate_YYYYMMDD.getToday());
				
				rs = ps.executeUpdate();

				if(rs > 0){
					///>>> check the save status
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
			
			if(full_page_status.equals("Yes") && j == 4){
				
				page_counter++;
				
				j = -1;
				
				if(page_counter == num_of_page){
					
					break;
				}				
			}
		}
		
		return rslt;
	}
	
	public static List<StudentBean> getRemainingStudListWithoutID(){
		
		List<StudentBean> slistperdetail = new ArrayList<StudentBean>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			con = Connector.connect();
			ps = con.prepareStatement(IDCardQueries.getRemainingStudListWithoutID);
			ps.setString(1, ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			rs = ps.executeQuery();

			while (rs.next()) {

				StudentBean studbean = new StudentBean();

				studbean.setSi_id(rs.getString("SI_ID"));
				studbean.setFname(rs.getString("FNAME"));
				studbean.setMname(rs.getString("MNAME"));
				studbean.setGname(rs.getString("GNAME"));
				studbean.setFullName(rs.getString("FNAME") + " " + rs.getString("MNAME") + " " + rs.getString("GNAME"));
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
				studbean.setClass_name(rs.getString("class_name"));
				studbean.setCd_name(rs.getString("cd_name"));
				
//				CurrentAddressBean cab = new CurrentAddressBean();
//				cab.setSub_city(rs.getString("sub_city"));
//				cab.setKebele(rs.getString("kebele"));
//				cab.setHouse_no(rs.getString("house_no"));
//				cab.setHome_phone(rs.getString("home_phone_no"));
//				studbean.setCurrentAddressBean(cab);
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				
				byte[] buf = new byte[1024];
				InputStream in = rs.getBinaryStream("stud_photo");
				int n = 0;
				while((n = in.read(buf)) >= 0){
					baos.write(buf, 0, n);
				}
				in.close();
				
				studbean.setStud_photo(baos.toByteArray());

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

}
