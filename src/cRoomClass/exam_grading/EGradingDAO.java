package cRoomClass.exam_grading;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;

public class EGradingDAO {
	
	public static List<EGradingBean> getExamGradingList(EGradingBean egb){
		
		List<EGradingBean> rslt = new ArrayList<EGradingBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(EGradingQueries.getExamGradingList);
			ps.setString(1, egb.getSubcl_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				EGradingBean egbb = new EGradingBean();
				
				egbb.setEscg_id(rs.getString("escg_id"));
				egbb.setEg_value(rs.getString("eg_value"));
				egbb.setGrade_from(rs.getString("grade_from"));
				egbb.setGrade_to(rs.getString("grade_to"));
				egbb.setEscg_status(rs.getString("escg_status"));
				
				rslt.add(egbb);
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
	
	public static List<EGradingBean> getGradingList(EGradingBean egb){
		List<EGradingBean> rslt = new ArrayList<EGradingBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(EGradingQueries.getGradingList);
			ps.setString(1, egb.getSubcl_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				EGradingBean egbb = new EGradingBean();
				
				egbb.setEg_id(rs.getString("eg_id"));
				egbb.setEg_value(rs.getString("eg_value"));
				
				rslt.add(egbb);
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
	
	public static boolean saveExamGradingRel(EGradingBean egb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(EGradingQueries.saveExamGradingRel);
			ps.setString(1, egb.getSubcl_id());
			ps.setString(2, egb.getEg_id());
			ps.setString(3, egb.getGrade_from());
			ps.setString(4, egb.getGrade_to());
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
	
	public static boolean updateExamGradingRel(EGradingBean egb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(EGradingQueries.updateExamGradingRel);
			ps.setString(1, egb.getGrade_from());
			ps.setString(2, egb.getGrade_to());
			ps.setString(3, egb.getEscg_status());
			ps.setString(4, egb.getEscg_id());
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
