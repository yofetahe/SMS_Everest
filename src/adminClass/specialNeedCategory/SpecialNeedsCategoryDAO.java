package adminClass.specialNeedCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;

public class SpecialNeedsCategoryDAO {
	
	public static List<SpecialNeedsCategoryBean> getSpecialNeedsCategoryList(){
		
		List<SpecialNeedsCategoryBean> rslt = new ArrayList<SpecialNeedsCategoryBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(SpecialNeedsCategoryQueries.getSpecialNeedsCategoryList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				SpecialNeedsCategoryBean sncb = new SpecialNeedsCategoryBean();
				
				sncb.setSnc_id(rs.getString("snc_id"));
				sncb.setSnc_name(rs.getString("snc_name"));
				sncb.setSnc_status(rs.getString("snc_status"));
				sncb.setPay_amount(rs.getString("pay_amount"));
				
				rslt.add(sncb);
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
	
	public static List<SpecialNeedsCategoryBean> getAllSpecialNeedsCategoryList(){
		
		List<SpecialNeedsCategoryBean> rslt = new ArrayList<SpecialNeedsCategoryBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(SpecialNeedsCategoryQueries.getAllSpecialNeedsCategoryList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				SpecialNeedsCategoryBean sncb = new SpecialNeedsCategoryBean();
				
				sncb.setSnc_id(rs.getString("snc_id"));
				sncb.setSnc_name(rs.getString("snc_name"));
				sncb.setPay_amount(rs.getString("pay_amount"));
				sncb.setPenality_max_date(rs.getString("penality_max_date"));
				sncb.setPenality_percent(rs.getString("penality_percent"));
				sncb.setSnc_status(rs.getString("snc_status"));
				
				rslt.add(sncb);
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
	
	public static boolean saveSpecialNeedsCategory(SpecialNeedsCategoryBean snc){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(SpecialNeedsCategoryQueries.saveSpecialNeedsCategory);
			ps.setString(1, snc.getSnc_name());
			ps.setString(2, snc.getPay_amount());
			ps.setString(3, snc.getPenality_max_date());
			ps.setString(4, snc.getPenality_percent());
			rs = ps.executeUpdate();
			
			if(rs > 0){
				rslt = true;
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
	
	public static boolean updateSpecialNeedsCategory(SpecialNeedsCategoryBean snc){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(SpecialNeedsCategoryQueries.updateSpecialNeedsCategory);
			ps.setString(1, snc.getSnc_name());
			ps.setString(2, snc.getPay_amount());
			ps.setString(3, snc.getPenality_max_date());
			ps.setString(4, snc.getPenality_percent());
			ps.setString(5, snc.getSnc_status());
			ps.setString(6, snc.getSnc_id());
			rs = ps.executeUpdate();
			
			if(rs > 0){
				rslt = true;
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
