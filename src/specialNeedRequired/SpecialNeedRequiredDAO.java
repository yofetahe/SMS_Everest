package specialNeedRequired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;
import util.ReturnCurrentEthiopianYear;
import util.TodayDate_YYYYMMDD;

public class SpecialNeedRequiredDAO {
	
	public static List<SpecialNeedRequiredBean> checkStudSpecialNeedRequirment(String si_id){
		
		List<SpecialNeedRequiredBean> rslt = new ArrayList<SpecialNeedRequiredBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(SpecialNeedRequiredQueries.getStudSpecialNeedRequirment);
			ps.setString(1, si_id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				SpecialNeedRequiredBean snrb = new SpecialNeedRequiredBean();
				
				snrb.setSsnr_id(rs.getString("ssnr_id"));
				snrb.setSnc_id(rs.getString("snc_id"));
				snrb.setSi_id(rs.getString("si_id"));
				snrb.setAc_year(rs.getString("ac_year"));
				snrb.setSsnr_status(rs.getString("ssnr_status"));
				
				rslt.add(snrb);
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

	public static boolean saveStudentSpecialNeedRequirement(SpecialNeedRequiredBean snrb, String loggedInUserId){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(SpecialNeedRequiredQueries.saveStudSpecialNeedRequirment);
			ps.setString(1, snrb.getSi_id());
			ps.setString(2, snrb.getSnc_id());
			ps.setString(3, ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			ps.setString(4, loggedInUserId);
			ps.setString(5, TodayDate_YYYYMMDD.getDayMonthYearFormat());
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
	
	public static boolean updateStudentSpecialNeedRequirement(SpecialNeedRequiredBean snrb, String loggedInUserId){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			
			if(snrb.getStud_special_need_status().equals("1")){
				ps = con.prepareStatement(SpecialNeedRequiredQueries.updateStudSpecialNeedRequirment);
				ps.setString(1, snrb.getSnc_id());
				ps.setString(2, ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
				ps.setString(3, loggedInUserId);
				ps.setString(4, TodayDate_YYYYMMDD.getDayMonthYearFormat());
				ps.setString(5, snrb.getSsnr_id());
				
			} else {
				ps = con.prepareStatement(SpecialNeedRequiredQueries.disableStudSpecialNeedRequirment);				
				ps.setString(1, loggedInUserId);
				ps.setString(2, TodayDate_YYYYMMDD.getDayMonthYearFormat());
				ps.setString(3, snrb.getSsnr_id());
			}
						
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
