package studentClass.reward;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;

public class RewardDAO {
	
	public static List<RewardBean> getRewardList(String si_id){
		
		List<RewardBean> rw = new ArrayList<RewardBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{		
			
			con = Connector.connect();
			ps = con.prepareStatement(RewardQueries.rewardList);
			ps.setString(1, si_id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				RewardBean rwrd = new RewardBean();
				
				rwrd.setSr_id(rs.getString("SR_ID"));
				rwrd.setSr_type(rs.getString("SR_TYPE"));
				rwrd.setSr_reason(rs.getString("SR_REASON"));
				rwrd.setSr_date(rs.getString("SR_DATE"));
				rwrd.setSr_status(rs.getString("SR_STATUS"));
				
				rw.add(rwrd);
				
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
		
		return rw;
		
	}
	
	public static Boolean saveStudReward(String sr_type, String sr_reason, String sr_date, String si_id){
		Boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			con = Connector.connect();
			ps = con.prepareStatement(RewardQueries.insertReward);
			ps.setString(1, sr_type);
			ps.setString(2, sr_reason);
			ps.setString(3, "2013-11-16");
			ps.setString(4, si_id);
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
	
	public static Boolean updateStudReward(String sr_type, String sr_reason, String sr_date, String sr_status, String sr_id){
		Boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(RewardQueries.updateReward);
			ps.setString(1, sr_type);
			ps.setString(2, sr_reason);
			ps.setString(3, "2013-11-16");
			ps.setString(4, sr_status);
			ps.setString(5, sr_id);
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
