package examClass.exam_subject_rel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;

import cRoomClass.CRoomBean;
import examClass.exam_type.ExamBean;
import examClass.exam_type.ExamQueries;

public class ExamSubjectRelDAO {
	
	public static List<ExamSubjectRelBean> getExamSubjectRel(CRoomBean crm){
		List<ExamSubjectRelBean> exsub = new ArrayList<ExamSubjectRelBean>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamSubjectRelQueries.getExamTypeList);
			ps.setString(1, crm.getCl_id());
			ps.setString(2, crm.getSub_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				ExamSubjectRelBean exsub1 = new ExamSubjectRelBean();
				
				exsub1.setExsub_id(rs.getString("EXSUB_ID"));
				exsub1.setEt_id(rs.getString("ET_ID"));
				exsub1.setEt_name(rs.getString("ET_NAME"));
				exsub1.setTotal_mark(rs.getString("TOTAL_MARK"));
				exsub1.setPass_mark(rs.getString("PASS_MARK"));
				exsub1.setRel_status(rs.getString("REL_STATUS"));
				exsub1.setSubcl_id(rs.getString("SUBCL_ID"));
				
				exsub.add(exsub1);
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
		
		return exsub;
	}
	
	public static List<ExamSubjectRelBean> getExSubjectRel(ExamSubjectRelBean crm){
		List<ExamSubjectRelBean> exsub = new ArrayList<ExamSubjectRelBean>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamSubjectRelQueries.getExamTypeList);
			ps.setString(1, crm.getCl_id());
			ps.setString(2, crm.getSub_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				ExamSubjectRelBean exsub1 = new ExamSubjectRelBean();
				
				exsub1.setExsub_id(rs.getString("EXSUB_ID"));
				exsub1.setEt_id(rs.getString("ET_ID"));
				exsub1.setEt_name(rs.getString("ET_NAME"));
				exsub1.setTotal_mark(rs.getString("TOTAL_MARK"));
				exsub1.setPass_mark(rs.getString("PASS_MARK"));
				exsub1.setRel_status(rs.getString("REL_STATUS"));
				exsub1.setSubcl_id(rs.getString("SUBCL_ID"));
				
				exsub.add(exsub1);
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
		
		return exsub;
	}
	
	public static boolean validateSubjectExamTotalMark(ExamSubjectRelBean exsub){
		boolean rslt = false;
		int total_mark = 0;
		String total_sum = "";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamSubjectRelQueries.checkSubjectTotalMark);
			ps.setString(1, exsub.getSubcl_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				total_sum = rs.getString(1);
			}
			
			if(total_sum != null){
				total_mark = total_mark + Integer.parseInt(total_sum) + Integer.parseInt(exsub.getTotal_mark());
			} else {
				total_mark = total_mark + Integer.parseInt(exsub.getTotal_mark());
			}
			
			if(total_mark <= 100)
				rslt = true;
			
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
	
	public static boolean insertClSubExmRel(ExamSubjectRelBean exsub){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamSubjectRelQueries.insertClSubExmRel);
			ps.setString(1, exsub.getEt_id());
			ps.setString(2, exsub.getSubcl_id());
			ps.setString(3, exsub.getTotal_mark());
			ps.setString(4, exsub.getPass_mark());
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
	
	public static List<ExamBean> getUnselectedExamTypeList(ExamSubjectRelBean crm){
		List<ExamBean> exm = new ArrayList<ExamBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamQueries.getUnselectedExamTypeList);
			ps.setString(1, crm.getCl_id());
			ps.setString(2, crm.getSub_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				ExamBean ex = new ExamBean();
				
				ex.setEt_id(rs.getString("ET_ID"));
				ex.setEt_name(rs.getString("ET_NAME"));
				ex.setEt_status(rs.getString("ET_STATUS"));
				
				exm.add(ex);
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
		
		return exm;
	}
	
	public static boolean checkActiveExamSubjectRel(ExamSubjectRelBean exsub){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int count = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamSubjectRelQueries.checkActiveExamSubRelOtherThanCurrent);
			ps.setString(1, exsub.getCl_id());
			ps.setString(2, exsub.getSub_id());
			ps.setString(3, exsub.getExsub_id());
			ps.setString(4, exsub.getExsub_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				count++;
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
				} catch (Exception e) {
					
				}
			}
		}
		
		
		return rslt;
	}
	
	public static boolean updateClSubExmRel(ExamSubjectRelBean exsub){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamSubjectRelQueries.updateClSubExmRel);	
			ps.setString(1, exsub.getTotal_mark());
			ps.setString(2, exsub.getPass_mark());
			ps.setString(3, exsub.getRel_status());
			ps.setString(4, exsub.getExsub_id());
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
