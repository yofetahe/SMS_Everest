package studentClass.emergency_contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;
import studentClass.StudentBean;

public class EmergencyContactDAO {
	
	public static List<EmergencyContactBean> getEmergContList(String si_id){
		
		List<EmergencyContactBean> emrgcont = new ArrayList<EmergencyContactBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(EmergencyContactQueries.getContactList);
			ps.setString(1, si_id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				EmergencyContactBean cont = new EmergencyContactBean();
				
				cont.setSec_id(rs.getString("SEC_ID"));
				cont.setContact_name(rs.getString("CONT_NAME"));
				cont.setRelationship(rs.getString("RELATIONSHIP"));
				cont.setMob_no(rs.getString("MOB_NO"));
				cont.setHome_phone_no(rs.getString("HOME_PHONE_NO"));
				cont.setOffice_phone_no(rs.getString("OFFICE_PHONE_NO"));
				cont.setSec_status(rs.getString("SEC_STATUS"));
				
				emrgcont.add(cont);
				
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
				
		return emrgcont;
	}
	
	public static Boolean updateEmergContact(String contname, String rel, String mobno, String homephoneno, String offphoneno, String status, String sec_id){
		Boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(EmergencyContactQueries.updateEmergContact);
			ps.setString(1, contname);
			ps.setString(2, rel);
			ps.setString(3, mobno);
			ps.setString(4, offphoneno);
			ps.setString(5, homephoneno);
			ps.setString(6, status);
			ps.setString(7, sec_id);
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
	
	public static Boolean saveEmergContact(String contname, String rel, String mobno, String homephoneno, String offphoneno, String siid){
		
		///>>> check emergency contact info existance <<<///
		if(checkEmergencyContactBySiid(siid, rel)){
			return true;
		}
		///>>> check emergency contact info existance <<<///
		
		Boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(EmergencyContactQueries.insertEmergContact);
			ps.setString(1, contname);
			ps.setString(2, rel);
			ps.setString(3, mobno);
			ps.setString(4, offphoneno);
			ps.setString(5, homephoneno);
			ps.setString(6, siid);
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
	
	public static boolean checkEmergencyContactBySiid(String si_id, String rel){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(EmergencyContactQueries.checkEmergencyContactBySiid);
			ps.setString(1, si_id);
			ps.setString(2, rel);
			rs = ps.executeQuery();
			
			while(rs.next()){
				if(Integer.parseInt(rs.getString("count")) > 0){
					rslt = true;
				}
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
	
	public static List<EmergencyContactBean> getAllEmergContListPerClass(String cl_id, String cd_id){
		
		List<EmergencyContactBean> emrgcont = new ArrayList<EmergencyContactBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(EmergencyContactQueries.getAllContactListPerClass);
			ps.setString(1, cl_id);
			ps.setString(2, cd_id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				EmergencyContactBean cont = new EmergencyContactBean();
				
				cont.setSec_id(rs.getString("SEC_ID"));
				cont.setContact_name(rs.getString("CONT_NAME"));
				cont.setRelationship(rs.getString("RELATIONSHIP"));
				cont.setMob_no(rs.getString("MOB_NO"));
				cont.setHome_phone_no(rs.getString("HOME_PHONE_NO"));
				cont.setOffice_phone_no(rs.getString("OFFICE_PHONE_NO"));
				cont.setSec_status(rs.getString("SEC_STATUS"));
				cont.setSi_id(rs.getString("SI_ID"));
				
				emrgcont.add(cont);
				
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
				
		return emrgcont;
	}
	
	public static List<EmergencyContactBean> getAllEmergContListForRemaining(List<StudentBean> stud_with_out_id_rslt){
		
		List<EmergencyContactBean> emrgcont = new ArrayList<EmergencyContactBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			con = Connector.connect();
			
			for(int i = 0; i < stud_with_out_id_rslt.size(); i++){
							
				ps = con.prepareStatement(EmergencyContactQueries.getAllContactListForRemaining);
				ps.setString(1, stud_with_out_id_rslt.get(i).getSi_id());
				rs = ps.executeQuery();
				
				while(rs.next()){
					EmergencyContactBean cont = new EmergencyContactBean();
					
					cont.setSec_id(rs.getString("SEC_ID"));
					cont.setContact_name(rs.getString("CONT_NAME"));
					cont.setRelationship(rs.getString("RELATIONSHIP"));
					cont.setMob_no(rs.getString("MOB_NO"));
					cont.setHome_phone_no(rs.getString("HOME_PHONE_NO"));
					cont.setOffice_phone_no(rs.getString("OFFICE_PHONE_NO"));
					cont.setSec_status(rs.getString("SEC_STATUS"));
					cont.setSi_id(rs.getString("SI_ID"));
					
					emrgcont.add(cont);
					
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
				
		return emrgcont;
	}

}
