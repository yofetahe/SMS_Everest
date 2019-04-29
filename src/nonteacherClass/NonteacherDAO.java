package nonteacherClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;

public class NonteacherDAO {
	
	public static List<NonteacherBean> getNonteacherList(){
		
		List<NonteacherBean> rslt = new ArrayList<NonteacherBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(NonteacherQueries.getNonteacherList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				NonteacherBean ntb = new NonteacherBean();
				
				ntb.setNti_id(rs.getString("nti_id"));
				ntb.setNti_fname(rs.getString("nti_fname"));
				ntb.setNti_mname(rs.getString("nti_mname"));
				ntb.setNti_gname(rs.getString("nti_gname"));
				ntb.setNti_sex(rs.getString("nti_sex"));
				ntb.setNti_email(rs.getString("nti_email"));
				ntb.setNti_position(rs.getString("nti_position"));
				ntb.setNti_id_no(rs.getString("nti_id_no"));
				ntb.setNti_status(rs.getString("nti_status"));
				
				rslt.add(ntb);
			}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception e){
					
				}
			}
		}
		
		
		return rslt;
	}
	
	public static List<NonteacherBean> getActiveNonteacherList(){
		
		List<NonteacherBean> rslt = new ArrayList<NonteacherBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(NonteacherQueries.getActiveNonteacherList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				NonteacherBean ntb = new NonteacherBean();
				
				ntb.setNti_id(rs.getString("nti_id"));
				ntb.setFull_name(rs.getString("nti_fname") + " " + rs.getString("nti_mname") + " " + rs.getString("nti_gname"));
								
				rslt.add(ntb);
			}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception e){
					
				}
			}
		}		
		
		return rslt;
	}
	
	public static String nonteacherIdNumberGenrator(String fname, String mname, String gname){
		
		return fname.substring(0, 1).toUpperCase() + mname.substring(0, 1).toUpperCase() + gname.substring(0, 1).toUpperCase() + getLastNonteacherNumber();
	}

	public synchronized static boolean saveNonteacher(NonteacherBean ntb){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		String nti_id_no = nonteacherIdNumberGenrator(ntb.getNti_fname(), ntb.getNti_mname(), ntb.getNti_gname());
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(NonteacherQueries.saveNonteacher);
			ps.setString(1, ntb.getNti_fname());
			ps.setString(2, ntb.getNti_mname());
			ps.setString(3, ntb.getNti_gname());
			ps.setString(4, ntb.getNti_sex());
			ps.setString(5, ntb.getNti_email());
			ps.setString(6, ntb.getNti_position());			
			ps.setString(7, nti_id_no);
			rs = ps.executeUpdate();
			
			if(rs > 0){
				rslt = true;
			}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception e){
					
				}
			}
		}		
		
		return rslt;
	}
	
	public static String getLastNonteacherNumber(){
		String lastIdNo = "";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(NonteacherQueries.getLastNonteacherIdNo);
			rs = ps.executeQuery();
			
			while(rs.next()){
				lastIdNo = "000" + (Integer.parseInt(rs.getString(1)) + 1);
			}	
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception e){
					
				}
			}
		}
		
		if(lastIdNo == "" || lastIdNo.equals("")){
			lastIdNo = "0001";
		}
		
		return lastIdNo;
	}
	
	
	public static boolean updateNonteacher(NonteacherBean ntb){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(NonteacherQueries.updateNonteacher);
			ps.setString(1, ntb.getNti_fname());
			ps.setString(2, ntb.getNti_mname());
			ps.setString(3, ntb.getNti_gname());
			ps.setString(4, ntb.getNti_sex());
			ps.setString(5, ntb.getNti_email());
			ps.setString(6, ntb.getNti_position());
			ps.setString(7, ntb.getNti_status());
			ps.setString(8, ntb.getNti_id());
			rs = ps.executeUpdate();
			
			if(rs > 0){
				rslt = true;
			}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception e){
					
				}
			}
		}		
		
		return rslt;
	}
}
