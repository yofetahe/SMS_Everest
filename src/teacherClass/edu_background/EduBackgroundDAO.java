package teacherClass.edu_background;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;

public class EduBackgroundDAO {
	
	public static List<EduBackgroundBean> getEduBackgroundList(EduBackgroundBean ebb){
		
		List<EduBackgroundBean> rslt = new ArrayList<EduBackgroundBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(EduBackgroundQueries.getEduBackgroundList);
			ps.setString(1, ebb.getTi_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				EduBackgroundBean eb = new EduBackgroundBean();
				
				eb.setTeb_id(rs.getString("teb_id"));
				eb.setField_of_study(rs.getString("field_of_study"));
				eb.setName_of_institiute(rs.getString("name_of_institiute"));
				eb.setGraduation_date(rs.getString("graduation_date"));
				eb.setAward(rs.getString("award"));
				eb.setTeb_status(rs.getString("teb_status"));
				
				rslt.add(eb);
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
	
	public static boolean saveEduBackground(EduBackgroundBean ebb){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(EduBackgroundQueries.saveEduBackground);
			ps.setString(1, ebb.getField_of_study());
			ps.setString(2, ebb.getName_of_institiute());
			ps.setString(3, ebb.getGraduation_date());
			ps.setString(4, ebb.getAward());
			ps.setString(5, ebb.getTi_id());
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
	
	public static boolean updateEduBackground(EduBackgroundBean ebb){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(EduBackgroundQueries.updateEduBackground);
			ps.setString(1, ebb.getField_of_study());
			ps.setString(2, ebb.getName_of_institiute());
			ps.setString(3, ebb.getGraduation_date());
			ps.setString(4, ebb.getAward());
			ps.setString(5, ebb.getTeb_status());
			ps.setString(6, ebb.getTeb_id());
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
