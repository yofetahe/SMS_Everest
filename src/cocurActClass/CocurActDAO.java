package cocurActClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;
import util.ReturnCurrentEthiopianYear;

public class CocurActDAO {
	
	public static String getClidByLoginUser(String username){
		
		String clid = "";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CocurActQueries.getClidByLoginUser);
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			while(rs.next()){
				clid = rs.getString("responsibility_id");
				break;
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
					ps.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return clid;
	}
	
	public static boolean checkUserType(String username){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CocurActQueries.checkUserNameUserType);
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			int count = 0;
			while(rs.next()){
				
				if(rs.getString("ut_id").equalsIgnoreCase("1")){
					count++;
				}
			}
			
			if(count > 0){
				rslt = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
					ps.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static List<CocurActBean> getActivityList(CocurActBean cab){
		List<CocurActBean> rslt = new ArrayList<CocurActBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		try{
			
			con = Connector.connect();
			
			if(cab.getClb_id().length() == 0){
				ps = con.prepareStatement(CocurActQueries.getAllCocurActivity);
				ps.setInt(1, Integer.parseInt(yr));
				rs = ps.executeQuery();
			} else {
				ps = con.prepareStatement(CocurActQueries.getCocurActivity);
				ps.setString(1, cab.getClb_id());
				ps.setInt(2, Integer.parseInt(yr));
				rs = ps.executeQuery();
			}
			
			while(rs.next()){
				
				
				CocurActBean cb = new CocurActBean();
				
				cb.setCa_id(rs.getString("ca_id"));
				cb.setCa_activity(rs.getString("ca_activity"));
				cb.setCa_activity_desc(rs.getString("ca_activity_desc"));
				cb.setCa_datefrom(rs.getString("ca_datefrom"));
				cb.setCa_dateto(rs.getString("ca_dateto"));
				cb.setClb_id(rs.getString("clb_id"));
				cb.setAcademic_year(rs.getString("academic_year"));
				cb.setClubhead_comment(rs.getString("clubhead_comment"));
				
				if(cb.getClubhead_comment() == null){
					cb.setCom_length("1");
				} else {
					cb.setCom_length("0");
				}
				
				cb.setCa_status(rs.getString("ca_status"));
				
				if(cab.getClb_id().length() == 0){
					cb.setEditStatus(false);
				} else {
					cb.setEditStatus(true);
				}
				
				rslt.add(cb);				
			}
						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
					ps.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static boolean saveClubActivity(CocurActBean cab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CocurActQueries.saveCocurActivity);
			ps.setString(1, cab.getClb_id());
			ps.setString(2, cab.getCa_activity());
			ps.setString(3, cab.getCa_activity_desc());
			ps.setString(4, cab.getCa_datefrom());
			ps.setString(5, cab.getCa_dateto());
			ps.setString(6, cab.getAcademic_year());
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
					ps.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static boolean updateClubActivity(CocurActBean cab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CocurActQueries.updateCocurActivity);
			ps.setString(1, cab.getCa_activity());
			ps.setString(2, cab.getCa_activity_desc());
			ps.setString(3, cab.getCa_datefrom());
			ps.setString(4, cab.getCa_dateto());
			ps.setString(5, cab.getAcademic_year());
			ps.setString(6, cab.getCa_status());
			ps.setString(7, cab.getCa_id());
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
					ps.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static List<CocurActBean> getMemberList(String clbid){
		
		List<CocurActBean> rslt = new ArrayList<CocurActBean>();
				
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CocurActQueries.getMemberList);
			ps.setString(1, clbid);
			ps.setInt(2, Integer.parseInt(yr));
			ps.setString(3, clbid);
			ps.setInt(4, Integer.parseInt(yr));
			rs = ps.executeQuery();
			
			while(rs.next()){
				CocurActBean cab = new CocurActBean();
				
				cab.setCm_id(rs.getString("cm_id"));
				cab.setMc_id(rs.getString("mc_id"));
				cab.setM_id(rs.getString("m_id"));
				cab.setMem_name(rs.getString("fname") + " " + rs.getString("mname") + " " + rs.getString("gname"));
				cab.setClbid(rs.getString("clb_id"));
				cab.setClbname(rs.getString("clb_name"));
				cab.setCm_reasontojoin(rs.getString("cm_reasontojoin"));
				cab.setCm_evaluation(rs.getString("cm_evaluation"));
				cab.setCm_status(rs.getString("cm_status"));
				cab.setParticipant_label(rs.getString("label"));
				
				rslt.add(cab);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
					ps.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static boolean saveClubMembers(CocurActBean cab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CocurActQueries.saveClubMember);
			ps.setString(1, cab.getSi_id());
			ps.setString(2, cab.getClbid());
			ps.setString(3, cab.getCm_reasontojoin());
			ps.setString(4, cab.getCm_evaluation());
			ps.setString(5, cab.getCm_acyear());
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
					ps.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static boolean addClubMemberEvaluation(CocurActBean cab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		String cmEval = "";
		
		try{
			if(cab.getCm_evaluation().length() != 0){
				cmEval = cab.getCm_evaluation();
			} 			
			
			con = Connector.connect();
			ps = con.prepareStatement(CocurActQueries.addClubMemberEvaluation);
			
			ps.setString(1, cmEval);			
			ps.setString(2, cab.getCm_id());
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
					ps.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static boolean updateClubMember(CocurActBean cab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		System.out.println(cab.getCm_id() + " " + cab.getCm_status() + " " + cab.getCm_reasontojoin());
		try{			
			
			con = Connector.connect();
			ps = con.prepareStatement(CocurActQueries.updateClubMember);
			
			ps.setString(1, cab.getCm_reasontojoin());
			ps.setString(2, cab.getCm_status());
			ps.setString(3, cab.getCm_id());
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
					ps.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static boolean saveClubHeadComment(CocurActBean cab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CocurActQueries.updateClubActivitiesClubHeadCom);
			
			ps.setString(1, cab.getClubhead_comment());
			ps.setString(2, cab.getCa_id());
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
					ps.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	

}
