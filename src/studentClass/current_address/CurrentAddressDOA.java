package studentClass.current_address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;
import email_communication.EmailComBean;
import util.ReturnCurrentEthiopianYear;

public class CurrentAddressDOA {
	
	public static List<CurrentAddressBean> getCurAdd(String si_id){
		
		List<CurrentAddressBean> curraddlist = new ArrayList<CurrentAddressBean>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CurrentAddressQueries.getCurAddressPerStud);
			ps.setString(1, si_id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				CurrentAddressBean cadd = new CurrentAddressBean();
				
				cadd.setSca_id(rs.getString("SCA_ID"));
				cadd.setSub_city(rs.getString("SUB_CITY"));
				cadd.setKebele(rs.getString("KEBELE"));
				cadd.setHouse_no(rs.getString("HOUSE_NO"));
				cadd.setHome_phone(rs.getString("HOME_PHONE_NO"));
				cadd.setEmail(rs.getString("EMAIL"));
				cadd.setEmail_2(rs.getString("EMAIL_2"));
				cadd.setStatus(rs.getString("SCA_STATUS"));
				
				curraddlist.add(cadd);
				
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
		
		return curraddlist;
		
	}
	
	public static Boolean checkCurAddress(CurrentAddressBean cur){
		Boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CurrentAddressQueries.checkCurrentAddress);
			ps.setString(1, cur.getSi_id());
			rs = ps.executeQuery();
			
			int count = 0;
			while(rs.next()){
				count++;
			}
			if(count > 0){
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
	
	public static Boolean saveCurAddress(CurrentAddressBean curadd){
		Boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CurrentAddressQueries.saveCurrentAddress);
			ps.setString(1, curadd.getSub_city());
			ps.setString(2, curadd.getKebele());
			ps.setString(3, curadd.getHouse_no());
			ps.setString(4, curadd.getHome_phone());
			ps.setString(5, curadd.getEmail());
			ps.setString(6, curadd.getEmail_2());
			ps.setString(7, curadd.getSi_id());
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
	
	public static Boolean updateCurAddress(CurrentAddressBean curadd){
		Boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CurrentAddressQueries.updateCurAdd);
			ps.setString(1, curadd.getSub_city());
			ps.setString(2, curadd.getKebele());
			ps.setString(3, curadd.getHouse_no());
			ps.setString(4, curadd.getHome_phone());
			ps.setString(5, curadd.getEmail());
			ps.setString(6, curadd.getEmail_2());
			ps.setString(7, curadd.getSca_id());
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
	
	public static List<CurrentAddressBean> getParentsEmailAccount(EmailComBean ecb){
		
		List<CurrentAddressBean> rslt = new ArrayList<CurrentAddressBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CurrentAddressQueries.getParentsEmailAddress);
			ps.setString(1, ecb.getCl_id());
			ps.setString(2, ecb.getCd_id());
			ps.setString(3, ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			rs = ps.executeQuery();
			
			while(rs.next()){
				CurrentAddressBean cadd = new CurrentAddressBean();
				
				cadd.setFullName(rs.getString("fname") + " " + rs.getString("mname") + " " + rs.getString("gname"));
				cadd.setEmail(rs.getString("EMAIL"));
				
				rslt.add(cadd);				
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

}
