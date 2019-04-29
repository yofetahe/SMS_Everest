package teacherClass.reward;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;

public class TeacherRewardDAO {
	
	public static List<TeacherRewardBean> getRewardList(TeacherRewardBean rw){
		List<TeacherRewardBean> rslt = new ArrayList<TeacherRewardBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(TeacherRewardQueries.getRewardList);
			ps.setString(1, rw.getTi_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				TeacherRewardBean rew = new TeacherRewardBean();
				
				rew.setTr_type(rs.getString("TR_TYPE"));
				rew.setTr_reason(rs.getString("TR_REASON"));
				rew.setTr_date(rs.getString("TR_DATE"));
				rew.setTr_status(rs.getString("TR_STATUS"));
				rew.setTr_id(rs.getString("TR_ID"));
				
				rslt.add(rew);
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
	
	public static boolean insertReward(TeacherRewardBean rw){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(TeacherRewardQueries.insertReward);
			ps.setString(1, rw.getTr_type());
			ps.setString(2, rw.getTr_reason());
			ps.setString(3, rw.getTr_date());
			ps.setString(4, rw.getTi_id());
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
	
	public static boolean updateReward(TeacherRewardBean rw){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(TeacherRewardQueries.updateReward);
			ps.setString(1, rw.getTr_type());
			ps.setString(2, rw.getTr_reason());
			ps.setString(3, rw.getTr_date());
			ps.setString(4, rw.getTr_status());
			ps.setString(5, rw.getTr_id());
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
