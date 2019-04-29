package teacherClass.emergency_contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;

public class TeacherEmergContDAO {

	public static List<TeacherEmergContBean> getListEmergCont(TeacherEmergContBean econt){
		List<TeacherEmergContBean> emergcont = new ArrayList<TeacherEmergContBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(TeacherEmergContQueries.getEmergCont);
			ps.setString(1, econt.getTi_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				TeacherEmergContBean emrgcont = new TeacherEmergContBean();
				
				emrgcont.setTec_id(rs.getString("TEC_ID"));
				emrgcont.setContact_name(rs.getString("CONT_NAME"));
				emrgcont.setRelationship(rs.getString("RELATIONSHIP"));
				emrgcont.setMobile_no(rs.getString("MOBILE_NO"));
				emrgcont.setHome_phone(rs.getString("HOME_PHONE_NO"));
				emrgcont.setOffice_phone(rs.getString("OFFICE_PHONE_NO"));
				emrgcont.setTec_status(rs.getString("TEC_STATUS"));
				
				emergcont.add(emrgcont);
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
		
		return emergcont;
	}
	
	public static boolean insertEmergCont(TeacherEmergContBean emrgcont){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(TeacherEmergContQueries.insertEmergCont);
			ps.setString(1, emrgcont.getContact_name());
			ps.setString(2, emrgcont.getRelationship());
			ps.setString(3, emrgcont.getMobile_no());
			ps.setString(4, emrgcont.getHome_phone());
			ps.setString(5, emrgcont.getOffice_phone());
			ps.setString(6, emrgcont.getTi_id());
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
	
	public static boolean updateEmergCont(TeacherEmergContBean emrgcont){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(TeacherEmergContQueries.updateEmergCont);
			ps.setString(1, emrgcont.getContact_name());
			ps.setString(2, emrgcont.getRelationship());
			ps.setString(3, emrgcont.getMobile_no());
			ps.setString(4, emrgcont.getHome_phone());
			ps.setString(5, emrgcont.getOffice_phone());
			ps.setString(6, emrgcont.getTec_status());
			ps.setString(7, emrgcont.getTec_id());
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
}
