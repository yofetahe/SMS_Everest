package adminClass.student_material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;

public class StudentMaterialDAO {
	
	public static List<StudentMaterialBean> getMaterialList(){
		List<StudentMaterialBean> rslt = new ArrayList<StudentMaterialBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentMaterialQueries.getMaterialList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				StudentMaterialBean sm = new StudentMaterialBean();
				
				sm.setPtm_id(rs.getString("ptm_id"));
				sm.setPtm_name(rs.getString("ptm_name"));
				sm.setPtm_desc(rs.getString("ptm_desc"));
				sm.setPtm_status(rs.getString("ptm_status"));
				
				rslt.add(sm);
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
	
	public static boolean saveStudentMaterial(StudentMaterialBean smb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentMaterialQueries.saveStudentMaterial);
			ps.setString(1, smb.getPtm_name());
			ps.setString(2, smb.getPtm_desc());
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
	
	public static boolean updateStudentMaterial(StudentMaterialBean smb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentMaterialQueries.updateStudentMaterial);
			ps.setString(1, smb.getPtm_name());
			ps.setString(2, smb.getPtm_desc());
			ps.setString(3, smb.getPtm_status());
			ps.setString(4, smb.getPtm_id());
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
	
	public static List<StudentMaterialBean> getMaterialClassRelList(){
		List<StudentMaterialBean> rslt = new ArrayList<StudentMaterialBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentMaterialQueries.getMaterialClassRelList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				StudentMaterialBean smb = new StudentMaterialBean();
				
				smb.setPmc_id(rs.getString("pmc_id"));
				smb.setPtm_id(rs.getString("ptm_id"));
				smb.setPtm_name(rs.getString("ptm_name"));
				smb.setCl_id(rs.getString("cl_id"));
				smb.setCl_name(rs.getString("class_name"));
				smb.setPayment_amount(rs.getString("pay_amount"));
				smb.setPmc_status(rs.getString("pmc_status"));
				
				rslt.add(smb);
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
	
	public static List<StudentMaterialBean> getActiveMaterialList(){
		
		List<StudentMaterialBean> rslt = new ArrayList<StudentMaterialBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(StudentMaterialQueries.getActiveMaterialList);
			rs = ps.executeQuery();
			
			while(rs.next()){
			
				StudentMaterialBean smb = new StudentMaterialBean();
				
				smb.setPtm_id(rs.getString("ptm_id"));
				smb.setPtm_name(rs.getString("ptm_name"));
				
				rslt.add(smb);
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
	
	public static boolean saveMaterialClassRel(StudentMaterialBean smb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		int rs = 0, count = 0;
		
		try{
			
			con = Connector.connect();
			
			ps2 = con.prepareStatement(StudentMaterialQueries.checkMaterialClassRel);
			ps2.setString(1, smb.getPtm_id());
			ps2.setString(2, smb.getCl_id());
			rs2 = ps2.executeQuery();
			
			while(rs2.next()){
				count++;
			}
			if(count == 0){
				ps = con.prepareStatement(StudentMaterialQueries.saveMaterialClassRel);
				ps.setString(1, smb.getPtm_id());
				ps.setString(2, smb.getCl_id());
				ps.setString(3, smb.getPayment_amount());
				rs = ps.executeUpdate();
				
				if(rs > 0){
					rslt = true;
				}
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
	
	public static boolean updateMaterialClassRel(StudentMaterialBean smb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		int rs = 0, count = 0;
		
		try{
			
			con = Connector.connect();
			
			ps2 = con.prepareStatement(StudentMaterialQueries.checkActiveMaterialClassRel);
			ps2.setString(1, smb.getPtm_id());
			ps2.setString(2, smb.getCl_id());
			ps2.setString(3, smb.getPmc_status());
			rs2 = ps2.executeQuery();
			
			while(rs2.next()){
				count++;
			}
			if(count == 0 ){
				ps = con.prepareStatement(StudentMaterialQueries.updateMaterialClassRel);
				ps.setString(1, smb.getPtm_id());
				ps.setString(2, smb.getCl_id());
				ps.setString(3, smb.getPayment_amount());
				ps.setString(4, smb.getPmc_status());
				ps.setString(5, smb.getPmc_id());
				
				rs = ps.executeUpdate();
				
				if(rs > 0){
					rslt = true;
				}
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
