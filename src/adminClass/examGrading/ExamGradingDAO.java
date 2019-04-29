package adminClass.examGrading;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;

public class ExamGradingDAO {
	
	public static List<ExamGradingBean> getExamGradingList(){
		List<ExamGradingBean> rslt = new ArrayList<ExamGradingBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamGradingQueries.getExamGradingList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				ExamGradingBean egb = new ExamGradingBean();
				
				egb.setEg_id(rs.getString("eg_id"));
				egb.setEg_value(rs.getString("eg_value"));
				egb.setEg_desc(rs.getString("eg_desc"));
				egb.setEg_status(rs.getString("eg_status"));
				
				rslt.add(egb);
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally{
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){
					
				}
			}
		}
		
		return rslt;
	}

	public static boolean saveExamGrading(ExamGradingBean egb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamGradingQueries.saveExamGrading);
			ps.setString(1, egb.getEg_value());
			ps.setString(2, egb.getEg_desc());
			rs = ps.executeUpdate();
			
			if(rs > 0){
				rslt = true;
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally{
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){
					
				}
			}
		}
		
		return rslt;
	}
	
	public static boolean updateExamGrading(ExamGradingBean egb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamGradingQueries.updateExamGrading);
			ps.setString(1, egb.getEg_value());
			ps.setString(2, egb.getEg_desc());
			ps.setString(3, egb.getEg_status());
			ps.setString(4, egb.getEg_id());
			rs = ps.executeUpdate();
			
			if(rs > 0){
				rslt = true;
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally{
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
