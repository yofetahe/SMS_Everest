package teacherClass.assignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;

public class TeacherAssignDAO {

	public static List<TeacherAssignBean> getListAssignmentList(TeacherAssignBean asgn){
		List<TeacherAssignBean> assign = new ArrayList<TeacherAssignBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(TeacherAssignQueries.getAssignList);
			ps.setString(1, asgn.getTi_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				TeacherAssignBean ass = new TeacherAssignBean();
				
				ass.setTa_id(rs.getString("TA_ID"));
				ass.setSub_id(rs.getString("SUB_ID"));
				ass.setSub_name(rs.getString("SUB_NAME"));
				ass.setCl_id(rs.getString("CL_ID"));
				ass.setCl_name(rs.getString("cl_name"));
				ass.setYear(rs.getString("YEAR"));
				ass.setTa_status(rs.getString("TA_STATUS"));
				
				assign.add(ass);
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
		
		return assign;
	}
	
	public static boolean checkTeacherSubjectRel(TeacherAssignBean ass){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(TeacherAssignQueries.checkTeacherAssignment);
			ps.setString(1, ass.getSub_id());
			ps.setString(2, ass.getCl_id());
			ps.setString(3, ass.getTi_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
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
	
	public static boolean insertTeacherAssign(TeacherAssignBean ass){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(TeacherAssignQueries.insertAssignment);
			ps.setString(1, ass.getSub_id());
			ps.setString(2, ass.getCl_id());
			ps.setString(3, ass.getTi_id());
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
	
	public static boolean updateTeacherAssign(TeacherAssignBean ass){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(TeacherAssignQueries.updateAssignment);
			ps.setString(1, ass.getSub_id());
			ps.setString(2, ass.getCl_id());
			ps.setString(3, ass.getTa_status());
			ps.setString(4, ass.getTa_id());
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
	
	public static List<TeacherAssignBean> getSubjectListPerGrade(TeacherAssignBean asgn){
		List<TeacherAssignBean> sub = new ArrayList<TeacherAssignBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(TeacherAssignQueries.getSubjectListPerGrade);
			ps.setString(1, asgn.getCl_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				TeacherAssignBean ass = new TeacherAssignBean();
				
				ass.setSub_id(rs.getString("sub_id"));
				ass.setSub_name(rs.getString("sub_name"));
				
				sub.add(ass);
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
		
		return sub;
	}
}
