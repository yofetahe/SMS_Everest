package preExamQstClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;

public class PreExamDAO {
	
	public static List<PreExamBean> getExamCategory(PreExamBean subclid){
		
		List<PreExamBean> eCat = new ArrayList<PreExamBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(PreExamQueries.getExamCategoryList);
			ps.setString(1, subclid.getSubcl_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				PreExamBean peb = new PreExamBean();
				
				peb.setPet_id(rs.getString("pet_id"));
				peb.setTotal_time_allowed(rs.getString("total_time_allowed"));
				peb.setNumber_of_qst(rs.getString("number_of_qst"));
				peb.setExam_level(rs.getString("exam_level"));
				peb.setSubcl_id(rs.getString("subcl_id"));
				peb.setPet_status(rs.getString("pet_status"));
				
				eCat.add(peb);
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
		
		return eCat;
	}
	
	public static List<PreExamBean> getExamQstList(PreExamBean subclid){
		
		List<PreExamBean> qList = new ArrayList<PreExamBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(PreExamQueries.getExamQstList);
			ps.setString(1, subclid.getPet_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				PreExamBean peb = new PreExamBean();
				
				peb.setPeq_id(rs.getString("peq_id"));
				peb.setPeq_content(rs.getString("peq_content"));
				peb.setPeq_image(rs.getString("peq_image"));
				peb.setPeq_status(rs.getString("peq_status"));
				peb.setPet_id(rs.getString("pet_id"));
				peb.setTi_id(rs.getString("ti_id"));
				
				qList.add(peb);
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
		
		return qList;
	}
	
	public static List<PreExamBean> getExamCatList(PreExamBean peb){
		List<PreExamBean> ci = new ArrayList<PreExamBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(PreExamQueries.getExamCatList);
			ps.setString(1, peb.getSubcl_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				PreExamBean pb = new PreExamBean();				
				
				pb.setPet_id(rs.getString("pet_id"));
				pb.setTotal_time_allowed(rs.getString("total_time_allowed"));
				pb.setNumber_of_qst(rs.getString("number_of_qst"));
				pb.setExam_level(rs.getString("exam_level"));
				pb.setPet_status(rs.getString("pet_status"));
				
				ci.add(pb);
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
		
		return ci;
	}
	
	public static List<PreExamBean> getCatInfo(PreExamBean peb){
		List<PreExamBean> ci = new ArrayList<PreExamBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(PreExamQueries.getExamCatInfo);
			ps.setString(1, peb.getPet_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				PreExamBean pb = new PreExamBean();				
				
				pb.setTotal_time_allowed(rs.getString("total_time_allowed"));
				pb.setNumber_of_qst(rs.getString("number_of_qst"));
				pb.setExam_level(rs.getString("exam_level"));
				
				ci.add(pb);
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
		
		return ci;
	}

	public static boolean validateMaxQstNo(PreExamBean peb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String maxQst = "";
		String addedQst = "";
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(PreExamQueries.maxAllowedQst);
			ps.setString(1, peb.getPet_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				maxQst = rs.getString("number_of_qst");
			}
			
			ps = con.prepareStatement(PreExamQueries.totalSavedQst);
			ps.setString(1, peb.getPet_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				addedQst = rs.getString("totalQst");
			}
			
			if(Integer.parseInt(maxQst) > Integer.parseInt(addedQst)){
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

	public static boolean savePreExamQst(PreExamBean peb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			System.out.println(peb.getPeq_image());
			
			con = Connector.connect();
			ps = con.prepareStatement(PreExamQueries.saveExamQst);
			ps.setString(1, peb.getPeq_content());
			ps.setString(2, peb.getPet_id());
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
	
	public static boolean updatePreExamQst(PreExamBean peb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(PreExamQueries.updateExamQst);
			ps.setString(1, peb.getPeq_content());
			ps.setString(2, peb.getPeq_status());
			ps.setString(3, peb.getPet_id());
			ps.setString(4, peb.getPeq_id());
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
	
	public static boolean saveExamCategory(PreExamBean peb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(PreExamQueries.saveExamCat);
			ps.setString(1, peb.getTotal_time_allowed());
			ps.setString(2, peb.getNumber_of_qst());
			ps.setString(3, peb.getExam_level());
			ps.setString(4, peb.getSubcl_id());
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
	
	public static boolean updateExamCategory(PreExamBean peb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(PreExamQueries.updateExamCat);
			ps.setString(1, peb.getTotal_time_allowed());
			ps.setString(2, peb.getNumber_of_qst());
			ps.setString(3, peb.getExam_level());
			ps.setString(4, peb.getPet_status());
			ps.setString(5, peb.getPet_id());
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
	
	public static List<PreExamBean> getQuestion(PreExamBean peb){
		List<PreExamBean> qst = new ArrayList<PreExamBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(PreExamQueries.getQst);
			ps.setString(1, peb.getPeq_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				PreExamBean pb = new PreExamBean();				
				
				pb.setPeq_content(rs.getString("peq_content"));
				
				qst.add(pb);
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
		
		return qst;
	}
	
	public static boolean uploadQstPic(PreExamBean peb, String doc_name){
		boolean rslt = true;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			String doc_path = "ExamDocument/DescriptionImages/" + doc_name;
			con = Connector.connect();
			ps = con.prepareStatement(PreExamQueries.updateQstDocInfo);
			ps.setString(1, doc_name);
			ps.setString(2, doc_path);
			ps.setString(3, peb.getPeq_id());
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
