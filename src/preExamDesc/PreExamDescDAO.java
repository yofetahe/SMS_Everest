package preExamDesc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;

public class PreExamDescDAO {
	
	public static List<PreExamDescBean> getQstDesc(PreExamDescBean ped){
		List<PreExamDescBean> pedb = new ArrayList<PreExamDescBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(PreExamDescQueries.getQstDesc);
			ps.setString(1, ped.getPeq_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				PreExamDescBean pdb = new PreExamDescBean();				
				
				pdb.setPee_id(rs.getString("pee_id"));
				pdb.setExplanation_dtl(rs.getString("explanation_dtl"));
				pdb.setSuggested_material(rs.getString("suggested_material"));
				pdb.setTi_id(rs.getString("ti_id"));
				pdb.setPee_status(rs.getString("pee_status"));
				pdb.setPee_image(rs.getString("pee_image"));
				pdb.setPee_image_path(rs.getString("pee_image_path"));
				
				pedb.add(pdb);
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
		
		return pedb;
	}
	
	public static List<PreExamDescBean> getQst(PreExamDescBean pd){
		List<PreExamDescBean> pedb = new ArrayList<PreExamDescBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(PreExamDescQueries.getQst);
			ps.setString(1, pd.getPeq_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				PreExamDescBean pdb = new PreExamDescBean();				
				
				pdb.setPeq_content(rs.getString("peq_content"));
												
				pedb.add(pdb);
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
		
		return pedb;
	}
	
	public static boolean saveQstDesc(PreExamDescBean pedb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(PreExamDescQueries.saveQstDesc);
			ps.setString(1, pedb.getExplanation_dtl());
			ps.setString(2, pedb.getSuggested_material());
			ps.setString(3, pedb.getPeq_id());
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
	
	public static boolean updateQstDesc(PreExamDescBean pedb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(PreExamDescQueries.updateQstDesc);
			ps.setString(1, pedb.getExplanation_dtl());
			ps.setString(2, pedb.getSuggested_material());
			ps.setString(3, pedb.getPee_status());
			ps.setString(4, pedb.getPee_id());
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
	
	public static boolean uploadQstDescPic(PreExamDescBean ped, String doc_name){
		boolean rslt = true;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		System.out.println(ped.getPee_id());
		try{
			String doc_path = "ExamDocument/DescriptionImages/" + doc_name;
			con = Connector.connect();
			ps = con.prepareStatement(PreExamDescQueries.updateQstDescDocInfo);
			ps.setString(1, doc_name);
			ps.setString(2, doc_path);
			ps.setString(3, ped.getPee_id());
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
	
	public static boolean uploadQstPic(PreExamDescBean ped, String doc_name){
		boolean rslt = true;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		System.out.println(ped.getPee_id());
		try{
			String doc_path = "ExamDocument/DescriptionImages/" + doc_name;
			con = Connector.connect();
			ps = con.prepareStatement(PreExamDescQueries.updateQstDescDocInfo);
			ps.setString(1, doc_name);
			ps.setString(2, doc_path);
			ps.setString(3, ped.getPeq_id());
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
