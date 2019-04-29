package adminClass.behavioural_evaluation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;

public class BehEval_DAO {
	
	public static List<BehEval_Bean> getTraitList(){
		List<BehEval_Bean> rslt = new ArrayList<BehEval_Bean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BehEval_Queries.getTraitList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				BehEval_Bean beb = new BehEval_Bean();
				
				beb.setBt_id(rs.getString("bt_id"));
				beb.setBt_title(rs.getString("bt_title"));
				beb.setBt_desc(rs.getString("bt_desc"));
				beb.setBt_status(rs.getString("bt_status"));
				
				rslt.add(beb);
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
	
	public static boolean saveBevalTrait(BehEval_Bean beb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BehEval_Queries.saveTrait);
			ps.setString(1, beb.getBt_title());
			ps.setString(2, beb.getBt_desc());
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
	
	public static boolean updateBevalTrait(BehEval_Bean beb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BehEval_Queries.updateTrait);
			ps.setString(1, beb.getBt_title());
			ps.setString(2, beb.getBt_desc());
			ps.setString(3, beb.getBt_status());
			ps.setString(4, beb.getBt_id());
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
	
	public static List<BehEval_Bean> getGradeList(){
		List<BehEval_Bean> rslt = new ArrayList<BehEval_Bean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BehEval_Queries.getGradeList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				BehEval_Bean beb = new BehEval_Bean();
				
				beb.setBg_id(rs.getString("bg_id"));
				beb.setBg_name(rs.getString("bg_name"));
				beb.setBg_desc(rs.getString("bg_desc"));
				beb.setBg_status(rs.getString("bg_status"));
				
				rslt.add(beb);
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
	
	public static boolean saveBevalGrade(BehEval_Bean beb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BehEval_Queries.saveGrade);
			ps.setString(1, beb.getBg_name());
			ps.setString(2, beb.getBg_desc());
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
	
	public static boolean updateBevalGrade(BehEval_Bean beb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BehEval_Queries.updateGrade);
			ps.setString(1, beb.getBg_name());
			ps.setString(2, beb.getBg_desc());
			ps.setString(3, beb.getBg_status());
			ps.setString(4, beb.getBg_id());
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
	
	public static List<BehEval_Bean> getTraitGradeRelList(BehEval_Bean bb){
		List<BehEval_Bean> rslt = new ArrayList<BehEval_Bean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BehEval_Queries.getTraitGradeRelList);
			ps.setString(1, bb.getBt_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				BehEval_Bean beb = new BehEval_Bean();
				
				beb.setBtbg_id(rs.getString("btbg_id"));
				beb.setBg_id(rs.getString("bg_id"));
				beb.setBg_name(rs.getString("bg_name"));
				beb.setBg_desc(rs.getString("bg_desc"));
				beb.setBtbg_rel(rs.getString("rel_status"));
				
				rslt.add(beb);
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
	
	public static List<BehEval_Bean> getUnrelatedGradeList(BehEval_Bean bb){
		List<BehEval_Bean> rslt = new ArrayList<BehEval_Bean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BehEval_Queries.getUnrelatedGradeList);
			ps.setString(1, bb.getBt_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				BehEval_Bean beb = new BehEval_Bean();
				
				beb.setBg_id(rs.getString("bg_id"));
				beb.setBg_name(rs.getString("bg_name"));
				beb.setBg_desc(rs.getString("bg_desc"));
				beb.setBg_status(rs.getString("bg_status"));
				
				rslt.add(beb);
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
	
	public static boolean saveBehaviouralEvaluationTraitGradeRel(BehEval_Bean beb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BehEval_Queries.saveTraitGradeRel);
			ps.setString(1, beb.getBt_id());
			ps.setString(2, beb.getBg_id());
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
	
	public static boolean updateBehaviouralEvaluationTraitGradeRel(BehEval_Bean beb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BehEval_Queries.updateTraitGradeRel);
			ps.setString(1, beb.getBtbg_rel());
			ps.setString(2, beb.getBtbg_id());
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
	
	public static List<BehEval_Bean> getTraitListPerGrade(BehEval_Bean bb){
		List<BehEval_Bean> rslt = new ArrayList<BehEval_Bean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BehEval_Queries.getRelatedTraitList);
			ps.setString(1, bb.getCl_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				BehEval_Bean beb = new BehEval_Bean();
				
				beb.setBtc_id(rs.getString("btc_id"));
				beb.setBt_title(rs.getString("bt_title"));
				beb.setRel_status(rs.getString("rel_status"));
				
				rslt.add(beb);
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
	
	public static List<BehEval_Bean> getUnrelatedTraitList(BehEval_Bean bb){
		List<BehEval_Bean> rslt = new ArrayList<BehEval_Bean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BehEval_Queries.getUnelatedTraitList);
			ps.setString(1, bb.getCl_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				BehEval_Bean beb = new BehEval_Bean();
				
				beb.setBt_id(rs.getString("bt_id"));
				beb.setBt_title(rs.getString("bt_title"));
				
				rslt.add(beb);
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
	
	public static boolean saveClassTraitRel(BehEval_Bean beb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BehEval_Queries.saveClassTraitRel);
			ps.setString(1, beb.getCl_id());
			ps.setString(2, beb.getBt_id());
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
	
	public static boolean updateClassTraitRel(BehEval_Bean beb){

		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BehEval_Queries.updateClassTraitRel);
			ps.setString(1, beb.getRel_status());
			ps.setString(2, beb.getBtc_id());
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
