package teacherClass.work_experience;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;
import teacherClass.edu_background.EduBackgroundBean;
import teacherClass.edu_background.EduBackgroundQueries;

public class WorkExperienceDAO {
	
	public static List<WorkExperienceBean> getWorkExpList(WorkExperienceBean wee){
		
		List<WorkExperienceBean> rslt = new ArrayList<WorkExperienceBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(WorkExperienceQueries.getEduBackgroundList);
			ps.setString(1, wee.getTi_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				WorkExperienceBean we = new WorkExperienceBean();
				
				we.setTwe_id(rs.getString("twe_id"));
				we.setName_of_institute(rs.getString("name_of_institute"));
				we.setJob_title(rs.getString("job_title"));
				we.setDate_from(rs.getString("date_from"));
				we.setDate_to(rs.getString("date_to"));
				we.setTwe_status(rs.getString("twe_status"));
				
				rslt.add(we);
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
	
	public static boolean saveWorkExp(WorkExperienceBean wee){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(WorkExperienceQueries.saveWorkExp);
			ps.setString(1, wee.getName_of_institute());
			ps.setString(2, wee.getJob_title());
			ps.setString(3, wee.getDate_from());
			ps.setString(4, wee.getDate_to());
			ps.setString(5, wee.getTi_id());
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
	
	public static boolean updateWorkExp(WorkExperienceBean wee){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(WorkExperienceQueries.updateWorkExp);
			ps.setString(1, wee.getName_of_institute());
			ps.setString(2, wee.getJob_title());
			ps.setString(3, wee.getDate_from());
			ps.setString(4, wee.getDate_to());
			ps.setString(5, wee.getTwe_status());
			ps.setString(6, wee.getTwe_id());
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

}
