package examClass.exam_type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cRoomClass.CRoomBean;

import connectionClass.Connector;
import examClass.exam_result.ExamResultBean;
import util.ReturnCurrentEthiopianYear;

public class ExamDAO {
	
	public static List<ExamBean> getExamTypeList(){
		List<ExamBean> extype = new ArrayList<ExamBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamQueries.getExamTypeList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				ExamBean ex = new ExamBean();
				
				ex.setEt_id(rs.getString("ET_ID"));
				ex.setEt_name(rs.getString("ET_NAME"));
				ex.setEt_type(rs.getString("ET_TYPE"));
				ex.setEt_status(rs.getString("ET_STATUS"));
				
				extype.add(ex);
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
		
		return extype;
	}
	
	public static List<ExamBean> getActiveExamTypeList(){
		List<ExamBean> extype = new ArrayList<ExamBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamQueries.getActiveExamTypeList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				ExamBean ex = new ExamBean();
				
				ex.setEt_id(rs.getString("ET_ID"));
				ex.setEt_name(rs.getString("ET_NAME"));
				ex.setEt_status(rs.getString("ET_STATUS"));
				
				extype.add(ex);
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
		
		return extype;
	}
	
	public static boolean insertExamType(ExamBean ex){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamQueries.insertExamType);
			ps.setString(1, ex.getEt_name());
			ps.setString(2, ex.getEt_type());
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
	
	public static boolean updateExamType(ExamBean ex){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamQueries.updateExamType);
			ps.setString(1, ex.getEt_name());
			ps.setString(2, ex.getEt_type());
			ps.setString(3, ex.getEt_status());
			ps.setString(4, ex.getEt_id());
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
	
	public static List<ExamBean> getUnselectedExamTypeList(CRoomBean crm){
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
	
	public static List<ExamBean> getSelectedExamTypeList(ExamResultBean crm){
		List<ExamBean> exm = new ArrayList<ExamBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamQueries.getSelectedExamTypeList);
			ps.setString(1, crm.getSubcl_id());
			ps.setString(2, crm.getAt_id());
			ps.setString(3, crm.getAcademic_year());
			ps.setString(4, crm.getCl_id());
			ps.setString(5, crm.getSubcl_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				ExamBean ex = new ExamBean();
				
				ex.setExsub_id(rs.getString("EXSUB_ID"));
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
	
	public static List<ExamBean> getActivelyRelatedExamTypeListWithClassSubject(ExamResultBean crm){
		
		List<ExamBean> exm = new ArrayList<ExamBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamQueries.getActivelyRelatedExamWithClassSubject);
			ps.setString(1, crm.getSubcl_id());			
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				ExamBean ex = new ExamBean();
				
				ex.setExsub_id(rs.getString("EXSUB_ID"));
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
	
	public static List<ExamBean> getActivelyRelatedExamTypeListWithClassSubjectForMarkList(ExamResultBean crm){
		
		List<ExamBean> exm = new ArrayList<ExamBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ExamQueries.getActivelyRelatedExamWithClassSubject);
			ps.setString(1, crm.getSubcl_id());			
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				ExamBean ex = new ExamBean();
				
				ex.setExsub_id(rs.getString("EXSUB_ID"));
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

}
