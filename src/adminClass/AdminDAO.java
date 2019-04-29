package adminClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import connectionClass.Connector;
import studentClass.StudentBean;
import util.PasswordEncription;
import util.ReturnCurrentEthiopianYear;
import util.SystemValidator;

public class AdminDAO {
	
	public static boolean InvalidSysUserChangePassword(AdminBean ab){
		boolean rslt = false;
		
		SystemValidator.datebaseSecurity();
		
		return rslt;
	}
	
	public static String getUserAccountIdByUsername(String username){
		
		String rslt = "";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.getUserAccountIdByUsername);
			ps.setString(1, username);			
			rs = ps.executeQuery();
			
			while(rs.next()){
				rslt = rs.getString("ua_id");
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
	
	public static List<AdminBean> validateUsers(AdminBean ab){
		
		List<AdminBean> rslt = new ArrayList<AdminBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			
			ps = con.prepareStatement(AdminQueries.validateUser);
			ps.setString(1, ab.getUsername().toString());
			rs = ps.executeQuery();
			
			//String pw = "";
			//int count = 0;
			
			while(rs.next()){
				AdminBean adb = new AdminBean();
				
				//pw = ab.getPassword();
				adb.setUa_id(rs.getString("ua_id"));				
				adb.setPassword(PasswordEncription.decrypt(rs.getString("password")));
				
				if(!rs.getString("ti_id").equals("N")){
					adb.setLoggedin_user_type("THCR");
					adb.setTi_id(rs.getString("ti_id"));
				} else if(!rs.getString("nti_id").equals("N")){
					adb.setLoggedin_user_type("NTHCR");
					adb.setNti_id(rs.getString("nti_id"));
				}
				//count++;
				//break;
				
				rslt.add(adb);
			}
			
//			if(count > 0){
//				String pw1 = rs.getString("password");
//				if(PasswordEncription.decrypt(pw1).equals(pw)){
//					rslt = true;
//				}
//			}
			
			
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
	
	public static List<AdminBean> getAllUserRoleListForSuperAdmin(){
		
		List<AdminBean> rslt = new ArrayList<AdminBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.getAllUserRoleListForSuperAdmin);	
			rs = ps.executeQuery();
			
			while(rs.next()){
				AdminBean ab = new AdminBean();
				
				ab.setM_name(rs.getString("m_name"));
				ab.setUtId(rs.getString("m_id"));
				//ab.setTi_id(rs.getString("ti_id"));
				
				rslt.add(ab);
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
	
	public static List<AdminBean> getLoginUserRoleList(String username){
		List<AdminBean> rslt = new ArrayList<AdminBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			if(username.equals("smssuperadmin")) {
				ps = con.prepareStatement(AdminQueries.getAllUserRoleListForSuperAdmin);
			}else {
				ps = con.prepareStatement(AdminQueries.getLoginUserRoleList);
				ps.setString(1, username);
			}
			rs = ps.executeQuery();
						
			while(rs.next()){
				AdminBean ab = new AdminBean();
				
				ab.setM_name(rs.getString("m_name"));
				ab.setUtId(rs.getString("ut_id"));
				//ab.setTi_id(rs.getString("ti_id"));
				
				rslt.add(ab);
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
	
	public static List<AdminBean> getLoginUserTeacherId(String username){
		List<AdminBean> rslt = new ArrayList<AdminBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.getLoginUserTeacherId);	
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			while(rs.next()){
				AdminBean ab = new AdminBean();
				
				ab.setTi_id(rs.getString("ti_id"));
				
				rslt.add(ab);
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
	
	public static List<AdminBean> getNoneAcademicUserList(){
		List<AdminBean> rslt = new ArrayList<AdminBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.getNoneAcademicUserList);			
			rs = ps.executeQuery();
			
			while(rs.next()){
				AdminBean ab = new AdminBean();
				
				ab.setUa_id(rs.getString("ua_id"));
				ab.setName(rs.getString("nti_fname") + " " + rs.getString("nti_mname") + " " + rs.getString("nti_gname"));
				ab.setUserType(rs.getString("ut_name"));
				ab.setUtId(rs.getString("ut_id"));
				ab.setUa_status(rs.getString("ua_status"));				
				
				rslt.add(ab);
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
	
	public static List<AdminBean> getTeachUserList(){
		List<AdminBean> rslt = new ArrayList<AdminBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.getTechUserList);			
			rs = ps.executeQuery();
			
			while(rs.next()){
				AdminBean ab = new AdminBean();
				
				ab.setTi_id(rs.getString("ti_id"));
				ab.setUa_id(rs.getString("ua_id"));
				ab.setUtId(rs.getString("ut_id"));
				ab.setUt_name(rs.getString("ut_name"));
				String name = rs.getString("fname") + " " + rs.getString("mname") + " " + rs.getString("gname");
				ab.setName(name);
				ab.setUa_status(rs.getString("ua_status"));
				
				rslt.add(ab);
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
	
	public static List<AdminBean> getStudUserList(){
		List<AdminBean> rslt = new ArrayList<AdminBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.getStudUserList);			
			rs = ps.executeQuery();
			
			while(rs.next()){
				AdminBean ab = new AdminBean();
				
				ab.setUa_id(rs.getString("ua_id"));
				String name = rs.getString("fname") + " " + rs.getString("mname") + " " + rs.getString("gname");
				ab.setName(name);
				ab.setUserType(rs.getString("ut_name"));
				ab.setUtId(rs.getString("ut_id"));
				
				rslt.add(ab);
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
	
	public static List<AdminBean> getUserTypeList(){
		List<AdminBean> rslt = new ArrayList<AdminBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.getUserTypeList);			
			rs = ps.executeQuery();
			
			while(rs.next()){
				AdminBean ab = new AdminBean();
				
				ab.setUtId(rs.getString("ut_id"));				
				ab.setUt_name(rs.getString("ut_name"));
				
				rslt.add(ab);
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
	
	public static List<AdminBean> getUserTypeTeacherList(){
		List<AdminBean> rslt = new ArrayList<AdminBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.getUserTypeTeacherList);			
			rs = ps.executeQuery();
			
			while(rs.next()){
				AdminBean ab = new AdminBean();
				
				ab.setUtId(rs.getString("ut_id"));				
				ab.setUt_name(rs.getString("ut_name"));
				
				rslt.add(ab);
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
	
	public static boolean checkingExistingUserName(AdminBean ab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int counter = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.checkingExistedUserName);
			ps.setString(1, ab.getUsername());
			ps.setString(2, ab.getUa_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				counter++;
			}
			if(counter > 1){
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
	
	public static boolean validateExistingUserName(AdminBean ab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int counter = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.checkingExistedUserName);
			ps.setString(1, ab.getUsername());
			ps.setString(2, ab.getUa_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				counter++;
			}
			if(counter > 0){
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
	
	public static boolean saveNoneAcUser(AdminBean ab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		int rs = 0;
		ResultSet rs2 = null;
		int rs3 = 0;
		
		try{
			
			con = Connector.connect();
			
			ps = con.prepareStatement(AdminQueries.saveNoneAcUser);
			ps.setString(1, ab.getUsername());
			ps.setString(2, PasswordEncription.encrypt(ab.getPassword()));
			ps.setString(3, ab.getNti_id());
			rs = ps.executeUpdate();
			
			if(rs > 0){
				ps2 = con.prepareStatement(AdminQueries.getLastSavedNoneAcUser);
				rs2 = ps2.executeQuery();
				
				String ua_id = "";
				
				while(rs2.next()){
					ua_id = rs2.getString(1);
				}
				
				ps3 = con.prepareStatement(AdminQueries.saveUserAndUserTypeRelation);
				ps3.setString(1, ua_id);
				ps3.setString(2, ab.getUtId());
				rs3 = ps3.executeUpdate();
				
				if(rs3 > 0){
					rslt = true;
				}
				
				/*
				 * There must be a sql delete statement in case if all the three statments couldn't be executed. 
				 * (delete what is saved in user_account) 
				 */
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
	
	public static boolean updateNoneAcUser(AdminBean ab){
		boolean rslt = false;
		
		Connection con = null;
		
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		PreparedStatement ps4 = null;
		PreparedStatement ps5 = null;
		PreparedStatement ps6 = null;
		PreparedStatement ps7 = null;
		
		int rs = 0;
		ResultSet rs2 = null;
		int rs3 = 0;
		int rs4 = 0;
		ResultSet rs5 = null;
		ResultSet rs6 = null;
		int rs7 = 0;
		
		try{
			
			con = Connector.connect();
			/*
			 * getting name of the user
			 */
			ps2 = con.prepareStatement(AdminQueries.checkNoneAcUserNameChange);
			ps2.setString(1, ab.getUa_id());
			rs2 = ps2.executeQuery();
			
			String pre_name = "";
			String actUtId = "";
			while(rs2.next()){
				pre_name = rs2.getString(1) + " " + rs2.getString(2) + " " + rs2.getString(3);
				actUtId = rs2.getString(4);
			}

			/*
			 * getting an id of the previous user and user type relationship
			 */
			ps5 = con.prepareStatement(AdminQueries.getPreviousUserAndUserTypeRelId);
			ps5.setString(1, ab.getUa_id());
			rs5 = ps5.executeQuery();
			
			String preUserAndUTypeId = "";
			
			while(rs5.next()){
				preUserAndUTypeId = rs5.getString(1);
				break;
			}
			
			/*
			 * search the previous active user and user type relation and make that inactive
			 * and create new active relationship
			 */
			if(pre_name.equalsIgnoreCase(ab.getName()) && !actUtId.equalsIgnoreCase(ab.getUtId())){
				/*
				 * make inactive old relationship
				 */
				ps3 = con.prepareStatement(AdminQueries.updateOldUAccountUTypeRel);
				ps3.setString(1, preUserAndUTypeId);
				rs3 = ps3.executeUpdate();
				
				if(rs3 > 0){
					/*
					 * check whether there is previously created inactive relationship or not
					 */
					ps6 = con.prepareStatement(AdminQueries.checkPreviousInactiveRelationship);
					ps6.setString(1, ab.getUa_id());
					ps6.setString(2, ab.getUtId());
					rs6 = ps6.executeQuery();
					
					int prevRelCounter = 0;
					String prevInRelId = "";
					
					while(rs6.next()){
						prevRelCounter++;
						prevInRelId = rs6.getString(1);
					}
					
					if(prevRelCounter > 0){
						
						/*
						 * activate the old inactive relationship instead of creating the same new relationship 
						 */
						ps7 = con.prepareStatement(AdminQueries.activateOldInactiveUAccountUTypeRel);
						ps7.setString(1, prevInRelId);
						rs7 = ps7.executeUpdate();
						
						if(rs7 > 0){
							rslt = true;
						}
						
					} else {
					
						/*
						 * add new relationship
						 */
						ps4 = con.prepareStatement(AdminQueries.saveNewUAccountUTypeRel);
						ps4.setString(1, ab.getUa_id());
						ps4.setString(2, ab.getUtId());
						rs4 = ps4.executeUpdate();
						
						if(rs4 > 0){
							rslt = true;
						}
					
					}
				}
				
			} else if(pre_name.equalsIgnoreCase(ab.getName()) && actUtId.equalsIgnoreCase(ab.getUtId())){
				
				/*
				 * update the status of the user
				 */
				ps = con.prepareStatement(AdminQueries.updateNoneAcUserStatus);
				ps.setString(1, PasswordEncription.encrypt("pass*word"));
				ps.setString(2, ab.getUa_status());
				ps.setString(3, ab.getUa_id());
				rs = ps.executeUpdate();
				
				if(rs > 0){
					rslt = true;
				}
				
			} else {
				
				if(actUtId.equalsIgnoreCase(ab.getUtId())){
					
					/*
					 * update the new name only with out the relationship
					 */
					ps = con.prepareStatement(AdminQueries.updateNoneAcUser);				
					ps.setString(1, ab.getName());
					ps.setString(2, ab.getUa_id());
					rs = ps.executeUpdate();
					
					if(rs > 0){
						rslt = true;
					}
					
				} else {
					/*
					 * update the new name
					 */
					ps = con.prepareStatement(AdminQueries.updateNoneAcUser);				
					ps.setString(1, ab.getName());
					ps.setString(2, ab.getUa_id());
					rs = ps.executeUpdate();
					
					if(rs > 0){
						/*
						 * make inactive old relationship
						 */
						ps3 = con.prepareStatement(AdminQueries.updateOldUAccountUTypeRel);
						ps3.setString(1, preUserAndUTypeId);
						rs3 = ps3.executeUpdate();
						
						if(rs3 > 0){
							/*
							 * check whether there is previously created inactive relationship or not
							 */
							ps6 = con.prepareStatement(AdminQueries.checkPreviousInactiveRelationship);
							ps6.setString(1, ab.getUa_id());
							ps6.setString(2, ab.getUtId());
							rs6 = ps6.executeQuery();
							
							int prevRelCounter = 0;
							String prevInRelId = "";
							
							while(rs6.next()){
								prevRelCounter++;
								prevInRelId = rs6.getString(1);
							}
							
							if(prevRelCounter > 0){
								
								/*
								 * activate the old inactive relationship instead of creating the same new relationship 
								 */
								ps7 = con.prepareStatement(AdminQueries.activateOldInactiveUAccountUTypeRel);
								ps7.setString(1, prevInRelId);
								rs7 = ps7.executeUpdate();
								
								if(rs7 > 0){
									rslt = true;
								}
								
							} else {
							
								/*
								 * add new relationship
								 */
								ps4 = con.prepareStatement(AdminQueries.saveNewUAccountUTypeRel);
								ps4.setString(1, ab.getUa_id());
								ps4.setString(2, ab.getUtId());
								rs4 = ps4.executeUpdate();
								
								if(rs4 > 0){
									rslt = true;
								}
							
							}
						}
					}
				}
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
					ps.close();
					ps2.close();
					ps3.close();
					ps4.close();
					ps5.close();
					ps6.close();
					ps7.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		return rslt;
	}
	
	public static List<AdminBean> getTeacherList(){
		List<AdminBean> rslt = new ArrayList<AdminBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.getActiveTeacherList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				AdminBean ab = new AdminBean();
				
				ab.setTi_id(rs.getString("ti_id"));
				String name = rs.getString("fname") + " " + rs.getString("mname") + " " + rs.getString("gname");
				ab.setTname(name);
				
				rslt.add(ab);
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
	
	public static boolean saveTeacherUser(AdminBean ab){
		boolean rslt = false;
		
		Connection con = null;
		
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		
		int rs = 0;
		ResultSet rs2 = null;
		int rs3 = 0;
		
		if(checkingExistingUserName(ab)){			
			return rslt;
		}
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.saveTeacherUser);
			ps.setString(1, ab.getUsername());
			ps.setString(2, PasswordEncription.encrypt(ab.getPassword()));
			ps.setString(3, ab.getTi_id());
			rs = ps.executeUpdate();
			
			if(rs > 0){
				
				ps2 = con.prepareStatement(AdminQueries.getLastSavedTeacherUser);
				rs2 = ps2.executeQuery();
				
				String ua_id = "";
				
				while(rs2.next()){
					ua_id = rs2.getString(1);
				}
				
				ps3 = con.prepareStatement(AdminQueries.saveUserAndUserTypeRelation);
				ps3.setString(1, ua_id);
				ps3.setString(2, ab.getUserType());
				rs3 = ps3.executeUpdate();
				
				if(rs3 > 0){
					rslt = true;
				}
				
				/*
				 * There must be a sql delete statement in case if all the three statments couldn't be executed. 
				 * (delete what is saved in user_account) 
				 */
				
				if(!ab.getUserType().equalsIgnoreCase("2")){
					
				}
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
	
	public static boolean updateTeacherUser(AdminBean ab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		PreparedStatement ps4 = null;
		PreparedStatement ps5 = null;
		PreparedStatement ps6 = null;
		
		int rs = 0;
		ResultSet rs2 = null;
		int rs3 = 0;
		ResultSet rs4 = null;
		int rs5 = 0;
		ResultSet rs6 = null;		
		
		
		try{
			
			con = Connector.connect();
			
			ps2 = con.prepareStatement(AdminQueries.getUserType);
			ps2.setString(1, ab.getUa_id());
			ps2.setString(2, ab.getTi_id());
			rs2 = ps2.executeQuery();
			
			String utid = "";
			while(rs2.next()){
				utid = rs2.getString(1);
			}
			
			if(utid.equalsIgnoreCase(ab.getUserType())){
				/*
				 * update the status of teacher user
				 */
				ps = con.prepareStatement(AdminQueries.updateTeacherUserStatus);
				ps.setString(1, PasswordEncription.encrypt("pass*word"));
				ps.setString(2, ab.getUa_status());
				ps.setString(3, ab.getUa_id());
				rs = ps.executeUpdate();
				
				if(rs > 0){
					rslt = true;
				}
				
			} else {
				/*
				 * getting the user account and user type relation id
				 */
				ps4 = con.prepareStatement(AdminQueries.getOldTeacherUserAndUserTypeRel);
				ps4.setString(1, ab.getUa_id());
				rs4 = ps4.executeQuery();
				
				String uaut_id = "";
				while(rs4.next()){
					uaut_id = rs4.getString(1);
				}
				
				/*
				 * check whether there is inactive with the same user account and user type data
				 */
				ps6 = con.prepareStatement(AdminQueries.checkPrevInactiveTuserAndUTypeRel);
				ps6.setString(1, ab.getUa_id());
				ps6.setString(2, ab.getUserType());
				rs6 = ps6.executeQuery();
				
				String prevTUandUtypeRelId = "";
				
				while(rs6.next()){
					prevTUandUtypeRelId = rs6.getString(1);
				}
				
				/*
				 * update previous active teacher user and user type relationship
				 */
				ps3 = con.prepareStatement(AdminQueries.updateOldTeacherUserAndUserTypeRel);
				ps3.setString(1, uaut_id);
				rs3 = ps3.executeUpdate();
				
				if(rs3 > 0){
					
					if(prevTUandUtypeRelId.equalsIgnoreCase("")){
					
						/*
						 * save the new teacher user and user type relationship
						 */
						ps5 = con.prepareStatement(AdminQueries.saveNewUAccountUTypeRel);
						ps5.setString(1, ab.getUa_id());
						ps5.setString(2, ab.getUserType());
						rs5 = ps5.executeUpdate();
						
						if(rs5 > 0){
							rslt = true;
						}
					} else {
						
						ps3 = con.prepareStatement(AdminQueries.updateOldInactiveTeacherUserAndUserTypeRel);
						ps3.setString(1, prevTUandUtypeRelId);
						rs3 = ps3.executeUpdate();
						
					}
					
				}
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
	
	public static List<AdminBean> getAllUserRoleList(){
		List<AdminBean> rslt = new ArrayList<AdminBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.getAllUserList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				AdminBean ab = new AdminBean();
				
				ab.setUtId(rs.getString("ut_id"));
				ab.setUt_name(rs.getString("ut_name"));
				ab.setRelated_with(rs.getString("related_with"));
				ab.setUt_status(rs.getString("ut_status"));
				
				rslt.add(ab);
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
	
	public static boolean updateUserRoleList(AdminBean ab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.updateUserList);
			ps.setString(1, ab.getUt_name());
			ps.setString(2, ab.getUt_status());
			ps.setString(3, ab.getRelated_with());
			ps.setString(4, ab.getUtId());
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
	
	public static boolean saveUserRoleList(AdminBean ab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.saveUserList);
			ps.setString(1, ab.getUt_name());
			ps.setString(2, ab.getRelated_with());
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
	
	public static List<AdminBean> getLogingUserFullName(String username){
		List<AdminBean> rslt = new ArrayList<AdminBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.getLoginUserFullName);
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			while(rs.next()){
				AdminBean ab = new AdminBean();
				
				ab.setUa_id(rs.getString("ua_id"));
				
				if(!rs.getString("ti_id").equals("N") && rs.getString("si_id").equals("N") && rs.getString("nti_id").equals("N")){					
					ps2 = con.prepareStatement(AdminQueries.getTeacherName);
					ps2.setString(1, rs.getString("ti_id"));
					rs2 = ps2.executeQuery();
					while(rs2.next()){
						ab.setName(rs2.getString("fname") + " " + rs2.getString("mname") + " " + rs2.getString("gname"));
					}
				} else if(rs.getString("ti_id").equals("N") && !rs.getString("si_id").equals("N") && rs.getString("nti_id").equals("N")){					
					ps2 = con.prepareStatement(AdminQueries.getStudentName);
					ps2.setString(1, rs.getString("si_id"));
					rs2 = ps2.executeQuery();
					while(rs2.next()){
						ab.setName(rs2.getString("fname") + " " + rs2.getString("mname") + " " + rs2.getString("gname"));
					}					
				} else if(rs.getString("ti_id").equals("N") && rs.getString("si_id").equals("N") && !rs.getString("nti_id").equals("N")){
					ps2 = con.prepareStatement(AdminQueries.getNonteacherName);
					ps2.setString(1, rs.getString("nti_id"));
					rs2 = ps2.executeQuery();
					while(rs2.next()){
						ab.setName(rs2.getString("nti_fname") + " " + rs2.getString("nti_mname") + " " + rs2.getString("nti_gname"));
					}					
				}
				
				rslt.add(ab);
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
	
	public static boolean validateOldPassword(AdminBean ab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.validateUserOldPassword);
			ps.setString(1, PasswordEncription.encrypt(ab.getOld_password()));
			ps.setString(2, ab.getUa_id());
			rs = ps.executeQuery();
			
			if(rs.next()){
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
	
	public static boolean updateUserProfile(AdminBean ab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		if(checkingExistingUserName(ab)){
			return rslt;
		}
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.updateUserProfileInfo);
			ps.setString(1, ab.getUsername());
			ps.setString(2, PasswordEncription.encrypt(ab.getPassword()));
			ps.setString(3, ab.getUa_id());
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
	
	public static List<AdminBean> getAnnualTermList(){
		List<AdminBean> rslt = new ArrayList<AdminBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.getAnnualTermList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				AdminBean ab = new AdminBean();
				
				ab.setAt_id(rs.getString("at_id"));
				ab.setAt_name(rs.getString("at_name"));
				ab.setAt_status(rs.getString("at_status"));
				ab.setAcademic_year(rs.getString("academic_year"));
				
				rslt.add(ab);
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
	
	public static boolean saveAnnualTermList(AdminBean ab){
		//getting academic year --- starting from May to August the academic year consider the next year
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
				
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);
		
		String yr = util.DateConvertor.dateConvertor(day+"-"+month+"-"+year);
				
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.saveAnnualTerm);
			ps.setString(1, ab.getAt_name());
			ps.setString(2, yr);
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
	
	public static boolean updateAnnualTermList(AdminBean ab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.updateAnnualTerm);
			ps.setString(1, ab.getAt_name());
			ps.setString(2, ab.getAt_status());
			ps.setString(3, ab.getAt_id());
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
	
	public static List<AdminBean> getModuleListPerRole(AdminBean ab){
		List<AdminBean> rslt = new ArrayList<AdminBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.getModuleListPerGrade);
			ps.setString(1, ab.getUtId());
			rs = ps.executeQuery();
			
			while(rs.next()){
				AdminBean adb = new AdminBean();
				
				adb.setUtm_id(rs.getString("utm_id"));
				adb.setM_id(rs.getString("m_id"));
				adb.setM_name(rs.getString("m_name"));
				adb.setMrel_status(rs.getString("rel_status"));
				
				rslt.add(adb);
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
	
	public static List<AdminBean> getRemainingModuleList(AdminBean ab){
		List<AdminBean> rslt = new ArrayList<AdminBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.getRemainingModuleList);
			ps.setString(1, ab.getUtId());
			rs = ps.executeQuery();
			
			while(rs.next()){
				AdminBean adb = new AdminBean();
				
				adb.setM_id(rs.getString("m_id"));
				adb.setM_name(rs.getString("m_name"));
				
				rslt.add(adb);
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
	
	public static boolean saveModuleRoleRel(AdminBean ab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.saveModuleUtypeRel);
			ps.setString(1, ab.getUtId());
			ps.setString(2, ab.getM_id());
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
	
	public static boolean updateModuleRoleRel(AdminBean ab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.updateModuleUtypeRel);
			ps.setString(1, ab.getUtId());
			ps.setString(2, ab.getM_id());
			ps.setString(3, ab.getMrel_status());
			ps.setString(4, ab.getUtm_id());
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
	
	public static List<AdminBean> getUnassignedTeacherList(){
		List<AdminBean> rslt = new ArrayList<AdminBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.getUnassignedHomeRooomTeacherList);
			ps.setString(1, yr);
			rs = ps.executeQuery();
			
			while(rs.next()){
				AdminBean ab = new AdminBean();
				
				ab.setTi_id(rs.getString("ti_id"));
				String name = rs.getString("fname") + " " + rs.getString("mname") + " " + rs.getString("gname");
				ab.setTname(name);
				
				rslt.add(ab);
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
	
	public static List<AdminBean> getAssignedHomeReoomTeacherList(){
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		List<AdminBean> rslt = new ArrayList<AdminBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.getAssignedHomeRoomTeacherList);
			ps.setString(1, yr);
			rs = ps.executeQuery();
			
			while(rs.next()){
				AdminBean ab = new AdminBean();
				
				ab.setThra_id(rs.getString("thra_id"));
				ab.setTi_id(rs.getString("ti_id"));
				ab.setTname(rs.getString("fname") + " " + rs.getString("mname") + " " + rs.getString("gname"));
				ab.setClass_id(rs.getString("cl_id"));
				ab.setClass_name(rs.getString("class_name"));
				ab.setCd_id(rs.getString("cd_id"));
				ab.setCd_name(rs.getString("cd_name"));
				ab.setAssign_status(rs.getString("thra_status"));
				
				rslt.add(ab);
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
	
	public static boolean saveAssignedTeacherList(AdminBean ab){		
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
				
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		
		int rs = 0;
		ResultSet rs2 = null;
		
		try{
			
			con = Connector.connect();
			
			ps2 = con.prepareStatement(AdminQueries.checkClassRoomTeacherAssignment);			
			ps2.setString(1, ab.getClass_id());
			ps2.setString(2, ab.getCd_id());
			ps2.setString(3, yr);
			rs2 = ps2.executeQuery();
			
			int counter = 0;
			
			while(rs2.next()){
				counter++;
			}
			
			if(counter == 0){
				ps = con.prepareStatement(AdminQueries.saveHomeRoomTeacherAssignment);
				ps.setString(1, ab.getTi_id());
				ps.setString(2, ab.getClass_id());
				ps.setString(3, ab.getCd_id());
				ps.setString(4, yr);
				rs = ps.executeUpdate();
				
				if(rs > 0){
					rslt = true;
				}
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
	
	public static List<AdminBean> getTeacherAssignmentInfo(AdminBean ab){
		List<AdminBean> rslt = new ArrayList<AdminBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.getTeacherAssignmentInfo);
			ps.setString(1,ab.getThra_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				AdminBean adb = new AdminBean();
				
				adb.setThra_id(rs.getString("thra_id"));
				adb.setTi_id(rs.getString("ti_id"));
				adb.setTname(rs.getString("fname")+ " " + rs.getString("mname") + " " + rs.getString("gname"));
				adb.setClass_id(rs.getString("cl_id"));
				adb.setClass_name(rs.getString("class_name"));
				adb.setCd_id(rs.getString("cd_id"));
				adb.setCd_name(rs.getString("cd_name"));
				adb.setThra_status(rs.getString("thra_status"));
				
				rslt.add(adb);
			}
			
		}catch(Exception e){
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
	
	public static boolean updateTeacherAssignment(AdminBean ab){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.updateTeacherAssignment);
			ps.setString(1, ab.getClass_id());
			ps.setString(2, ab.getCd_id());
			ps.setString(3, ab.getThra_status());
			ps.setString(4, ab.getThra_id());
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

	public static String getAssignedHomeReoomTeacherName(String cl_id, String cd_id){
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		String rslt = "";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.getHomeRoomTeacherName);
			ps.setString(1, cl_id);
			ps.setString(2, cd_id);
			ps.setString(3, yr);
			rs = ps.executeQuery();
			
			while(rs.next()){
				rslt = rs.getString("fname") + " " + rs.getString("mname") + " " + rs.getString("gname");				
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
	
	public static boolean resetUserPassword(AdminBean ab){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		String fullName = ab.getName();
		String[] name = fullName.split(" ");
		
		String defaultUsername = name[0].substring(0, 1)+ "" + name[1];
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(AdminQueries.resetUserPassword);
			ps.setString(1, defaultUsername.toLowerCase());
			ps.setString(2, PasswordEncription.encrypt("pass*word"));
			ps.setString(3, ab.getUa_id());
			
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
