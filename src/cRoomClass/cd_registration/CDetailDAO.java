package cRoomClass.cd_registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;

public class CDetailDAO {
	
	public static List<CDetailBean> getCDetailList(){
		List<CDetailBean> cd = new ArrayList<CDetailBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CDetailQueries.getCDetailList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				CDetailBean cdtl = new CDetailBean();
				
				cdtl.setCd_id(rs.getString("CD_ID"));
				cdtl.setCd_name(rs.getString("CD_NAME"));
				cdtl.setCd_status(rs.getString("CD_STATUS"));
				
				cd.add(cdtl);
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
		
		return cd;
	}
	
	public static boolean updateCDetail(CDetailBean cd){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CDetailQueries.updateCDetail);
			ps.setString(1, cd.getCd_name());
			ps.setString(2, cd.getCd_status());
			ps.setString(3, cd.getCd_id());
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
	
	public static boolean saveCDetail(CDetailBean cd){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CDetailQueries.insertCDetail);
			ps.setString(1, cd.getCd_name());
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
