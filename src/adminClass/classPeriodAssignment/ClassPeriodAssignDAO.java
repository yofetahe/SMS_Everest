package adminClass.classPeriodAssignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cRoomClass.CRoomBean;
import connectionClass.Connector;
import subjectClass.SubjectBean;
import subjectClass.SubjectQueries;
import util.ReturnCurrentEthiopianYear;

public class ClassPeriodAssignDAO {
	
	public static List<ClassPeriodAssignBean> getClassPeriodAssigned(String cl_id, String academic_year){
		
		List<ClassPeriodAssignBean> rslt = new ArrayList<ClassPeriodAssignBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ClassPeriodAssignQueries.getClassPeriodAssigned);
			ps.setString(1, cl_id);
			ps.setString(2, academic_year);
			rs = ps.executeQuery();
			
			while(rs.next()){
				ClassPeriodAssignBean cpa = new ClassPeriodAssignBean();
				
				cpa.setSub_id(rs.getInt("sub_id"));
				cpa.setPeriod_per_week(rs.getInt("period_per_week"));
				
				SubjectBean subbean = new SubjectBean();
				subbean.setSub_name(rs.getString("sub_name"));
				cpa.setSubjectBean(subbean);
				
				rslt.add(cpa);
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){}
			}
		}		
		
		return rslt;
		
	}
	
	public static List<ClassPeriodAssignBean> getGeneralPeriondAssignment(String academic_year){
		
		List<ClassPeriodAssignBean> rslt = new ArrayList<ClassPeriodAssignBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ClassPeriodAssignQueries.getGeneralPeriondAssignment);
			ps.setString(1, academic_year);
			rs = ps.executeQuery();
			
			while(rs.next()){
				ClassPeriodAssignBean cpab = new ClassPeriodAssignBean();
				
				cpab.setCsp_id(rs.getInt("csp_id"));
				cpab.setPeriod_per_week(rs.getInt("period_per_week"));
				cpab.setAcademic_year(rs.getInt("academic_year"));
				
				CRoomBean cr = new CRoomBean();
				cr.setClass_name(rs.getString("class_name"));
				cpab.setCroomBean(cr);
				
				SubjectBean sb = new SubjectBean();
				sb.setSub_name(rs.getString("sub_name"));
				cpab.setSubjectBean(sb);
								
				rslt.add(cpab);
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception ex){}
			}
		}
		
		return rslt;
	}
	
	public static List<ClassPeriodAssignBean> getSubjectListPerClassByClassId(String clid){
		
		List<ClassPeriodAssignBean> subList = new ArrayList<ClassPeriodAssignBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ClassPeriodAssignQueries.getSubListPerClass);
			ps.setString(2, clid);
			ps.setString(1, ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				ClassPeriodAssignBean pab = new ClassPeriodAssignBean();
				
				SubjectBean sb = new SubjectBean();
				
				sb.setSub_id(rs.getString("SUB_ID"));
				sb.setSub_name(rs.getString("SUB_NAME"));
				sb.setSub_status(rs.getString("SUB_STATUS"));				
				sb.setSubcl_id(rs.getString("subcl_id"));
				
				pab.setSubjectBean(sb);
				pab.setPeriod_per_week(rs.getInt("period_per_week"));
				
				subList.add(pab);
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
		
		return subList;
	}
	
	public static boolean saveClassPeriodAllotment(ClassPeriodAssignBean cpab){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0, counter = 0;
		
		String[] allotment = cpab.getSubjectPeriodAllotment()[0].split(",");
		
		try{
			con = Connector.connect();
			
			con.setAutoCommit(false);
			
			for(int i = 0; i < allotment.length; i+=2){
			
				ps = con.prepareStatement(ClassPeriodAssignQueries.saveClassPeriodAllotment);
				ps.setString(1, cpab.getCroomBean().getCl_id());
				ps.setString(2, allotment[i]);//sub_id
				ps.setString(3, allotment[i+1]);//period_per_week
				ps.setInt(4, cpab.getAcademic_year());
				rs = ps.executeUpdate();
				
				if(rs > 0){
					counter++;
				}
			}
			
			if(counter == allotment.length/2){
				con.commit();
				rslt = true;
			}
		
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
					ps.close();
				} catch(Exception ex){}
			}
		}
		
		return rslt;
	}
	
	public static List<ClassPeriodAssignBean> getClassPeriodAllotmentByCsp_id(int csp_id){
		
		List<ClassPeriodAssignBean> allotment = new ArrayList<ClassPeriodAssignBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ClassPeriodAssignQueries.getClassPeriodAllotmentByCsp_id);
			ps.setInt(1, csp_id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				ClassPeriodAssignBean pab = new ClassPeriodAssignBean();
				
				SubjectBean sb = new SubjectBean();				
				sb.setSub_name(rs.getString("SUB_NAME"));				
				pab.setSubjectBean(sb);
				
				CRoomBean croomBean = new CRoomBean();
				croomBean.setClass_name(rs.getString("class_name"));
				pab.setCroomBean(croomBean);
				
				pab.setCsp_id(csp_id);
				pab.setPeriod_per_week(rs.getInt("period_per_week"));
				pab.setAcademic_year(rs.getInt("academic_year"));
				
				allotment.add(pab);
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
		
		return allotment;
	}
	
	public static boolean updateClassPeriodAllotment(ClassPeriodAssignBean cpab){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			con = Connector.connect();

			ps = con.prepareStatement(ClassPeriodAssignQueries.updateClassPeriodAllotment);
			ps.setInt(1, cpab.getPeriod_per_week());
			ps.setInt(2, cpab.getCsp_id());
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
					ps.close();
				} catch(Exception ex){}
			}
		}
		
		return rslt;
	}
}
