package studentClass.grade_history;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;

public class GradeHistoryDAO {
	
	public static List<GradeHistoryBean> getGrdHistory(String si_id){
		
		List<GradeHistoryBean> grdhstList = new ArrayList<GradeHistoryBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(GradeHistoryQueries.getGrdHistoryList);
			ps.setString(1, si_id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				GradeHistoryBean grdhist = new GradeHistoryBean();
				
				grdhist.setClass_name(rs.getString("CLASS_NAME"));
				grdhist.setCl_id(rs.getString("CL_ID"));
				
				grdhstList.add(grdhist);
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
		
		return grdhstList;
	}

	public static List<GradeHistorySubjectBean> getGrdHstySub (String clid, String siid){
		
		List<GradeHistorySubjectBean> grdhissub = new ArrayList<GradeHistorySubjectBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(GradeHistoryQueries.getSubPerGrd);
			ps.setString(1, clid);
			//ps.setString(2, siid);
			rs = ps.executeQuery();
			
			while(rs.next()){
				GradeHistorySubjectBean subpergrd = new GradeHistorySubjectBean();
				
				subpergrd.setSub_id(rs.getString("SUB_ID"));
				subpergrd.setSub_name(rs.getString("SUB_NAME"));
				subpergrd.setSub_status(rs.getString("SUB_STATUS"));
				subpergrd.setTotal_mark(rs.getString("TOTAL_MARK"));
				subpergrd.setTotal_pass_mark(rs.getString("TOTAL_PASS_MARK"));				
				
				grdhissub.add(subpergrd);
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
		
		return grdhissub;
	}
}
