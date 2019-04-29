package preExamQstChoiceClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;

public class PreExamQChoiceDAO {
	
	public static List<PreExamQChoiceBean> getPreExamQChoiceList(String peb){
		List<PreExamQChoiceBean> qc = new ArrayList<PreExamQChoiceBean>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(PreExamQChoiceQueries.getQstChoice);
			ps.setString(1, peb);
			rs = ps.executeQuery();
			
			while(rs.next()){
				PreExamQChoiceBean pb = new PreExamQChoiceBean();				
				
				pb.setPec_id(rs.getString("pec_id"));
				pb.setPec_content(rs.getString("pec_content"));
				pb.setPec_correct(rs.getString("pec_correct"));
				pb.setPec_image(rs.getString("pec_image"));
				pb.setPec_status(rs.getString("pec_status"));
				pb.setPeq_id(rs.getString("peq_id"));
				
				qc.add(pb);
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
		
		return qc;
	}
	
	public static boolean saveQuestionChoice(PreExamQChoiceBean pqb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(PreExamQChoiceQueries.saveQstChoice);
			ps.setString(1, pqb.getPec_content());
			ps.setString(2, pqb.getPec_correct());
			ps.setString(3, pqb.getPeq_id());
			
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
	
	public static boolean updateQstChoice(PreExamQChoiceBean pqb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(PreExamQChoiceQueries.updateQstChoice);
			ps.setString(1, pqb.getPec_content());
			ps.setString(2, pqb.getPec_correct());
			ps.setString(3, pqb.getPec_status());
			ps.setString(4, pqb.getPec_id());
			
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
	
	public static boolean uploadQstPic(PreExamQChoiceBean pqb, String doc_name){
		boolean rslt = true;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			String doc_path = "ExamDocument/ChoiceImages/" + doc_name;
			con = Connector.connect();
			ps = con.prepareStatement(PreExamQChoiceQueries.updateQstChoiceDocInfo);
			ps.setString(1, doc_name);
			ps.setString(2, doc_path);
			ps.setString(3, pqb.getPec_id());
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
