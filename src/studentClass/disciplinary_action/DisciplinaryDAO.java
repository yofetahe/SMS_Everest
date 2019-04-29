package studentClass.disciplinary_action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;

public class DisciplinaryDAO {
	
	public static List<DisciplinaryBean> getDisActList(String siid){
		
		List<DisciplinaryBean> disact = new ArrayList<DisciplinaryBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(DisciplinaryQueries.getDisActList);
			ps.setString(1, siid);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				DisciplinaryBean dact = new DisciplinaryBean();
				
				dact.setSda_id(rs.getString("SDA_ID"));
				dact.setSda_type(rs.getString("SDA_TYPE"));
				dact.setSda_reason(rs.getString("SDA_REASON"));
				dact.setSda_date(rs.getString("SDA_DATE"));
				dact.setSda_status(rs.getString("SDA_STATUS"));
				
				disact.add(dact);
				
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
		
		return disact;
	}
	
	public static boolean saveDisAct(String type, String reason, String date, String siid){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(DisciplinaryQueries.insertDisAct);
			ps.setString(1, type);
			ps.setString(2, reason);
			ps.setString(3, date);
			ps.setString(4, siid);
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
	
	public static boolean updateDisAct(String type, String reason, String date, String siid, String sdaid, String status){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(DisciplinaryQueries.updateDisAct);
			ps.setString(1, type);
			ps.setString(2, reason);
			ps.setString(3, date);
			ps.setString(4, status);
			ps.setString(5, sdaid);
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
