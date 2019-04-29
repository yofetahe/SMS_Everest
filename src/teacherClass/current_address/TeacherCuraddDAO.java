package teacherClass.current_address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;

public class TeacherCuraddDAO {

	public static List<TeacherCuraddBean> getTeachCuradd(TeacherCuraddBean tchr){
		List<TeacherCuraddBean> tcuradd = new ArrayList<TeacherCuraddBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(TeacherCuraddQueries.getTchCuradd);
			ps.setString(1, tchr.getTi_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				TeacherCuraddBean tca = new TeacherCuraddBean();
				
				tca.setTca_id(rs.getString("TCA_ID"));
				tca.setSub_city(rs.getString("SUB_CITY"));
				tca.setKebele(rs.getString("KEBELE"));
				tca.setHouse_no(rs.getString("HOUSE_NO"));
				tca.setHouse_phone_no(rs.getString("HOUSE_PHONE_NO"));
				tca.setMobile_no(rs.getString("MOBILE_NO"));
				tca.setEmail(rs.getString("EMAIL"));
				tca.setIca_status(rs.getString("TCA_STATUS"));
				
				tcuradd.add(tca);
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
		
		return tcuradd;
	}
	
	public static boolean insertTeacherCuradd(TeacherCuraddBean tchr){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(TeacherCuraddQueries.insertTchCuradd);
			ps.setString(1, tchr.getSub_city());
			ps.setString(2, tchr.getKebele());
			ps.setString(3, tchr.getHouse_no());
			ps.setString(4, tchr.getHouse_phone_no());
			ps.setString(5, tchr.getMobile_no());
			ps.setString(6, tchr.getEmail());
			ps.setString(7, tchr.getTi_id());
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
	
	public static boolean updateTeacherCuradd(TeacherCuraddBean tchr){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(TeacherCuraddQueries.updateTchCuradd);
			ps.setString(1, tchr.getSub_city());
			ps.setString(2, tchr.getKebele());
			ps.setString(3, tchr.getHouse_no());
			ps.setString(4, tchr.getHouse_phone_no());
			ps.setString(5, tchr.getMobile_no());
			ps.setString(6, tchr.getEmail());
			ps.setString(7, tchr.getIca_status());
			ps.setString(8, tchr.getTca_id());
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
	
	public static List<TeacherCuraddBean> getTeachEmailAddress(){
		
		List<TeacherCuraddBean> rslt = new ArrayList<TeacherCuraddBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(TeacherCuraddQueries.getTeachersEmailAddress);
			rs = ps.executeQuery();
			
			while(rs.next()){
				TeacherCuraddBean tcb = new TeacherCuraddBean();
				
				tcb.setFullName(rs.getString("fname") + " " + rs.getString("mname") + " " + rs.getString("gname"));
				tcb.setEmail(rs.getString("email"));
				
				rslt.add(tcb);
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
}
