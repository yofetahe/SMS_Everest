package behHolisticEvaluationClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;

public class BevalHolisticDAO {
	
	public static List<BevalHolisticBean> getHolisticCategoryList(){
		
		List<BevalHolisticBean> rslt = new ArrayList<BevalHolisticBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalHolisticQueries.getHolisticCategoryList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				BevalHolisticBean bhb = new BevalHolisticBean();
				
				bhb.setBhc_id(rs.getString("bhc_id"));
				bhb.setBhc_name(rs.getString("bhc_name"));
				bhb.setBhc_desc(rs.getString("bhc_desc"));
				bhb.setBhc_status(rs.getString("bhc_status"));
				
				rslt.add(bhb);
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

	public static boolean saveHolisticCategory(BevalHolisticBean bhb){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalHolisticQueries.saveHolisticCategory);
			ps.setString(1, bhb.getBhc_name());
			ps.setString(2, bhb.getBhc_desc());
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
	
	public static boolean updateHolisticCategory(BevalHolisticBean bhb){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalHolisticQueries.updateHolisticCategory);
			ps.setString(1, bhb.getBhc_name());
			ps.setString(2, bhb.getBhc_desc());
			ps.setString(3, bhb.getBhc_status());
			ps.setString(4, bhb.getBhc_id());
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
	
	
	public static List<BevalHolisticBean> getHolisticPointList(){
		
		List<BevalHolisticBean> rslt = new ArrayList<BevalHolisticBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalHolisticQueries.getHolisticPointList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				BevalHolisticBean bhb = new BevalHolisticBean();
				
				bhb.setBhp_id(rs.getString("bhp_id"));
				bhb.setBhp_content(rs.getString("bhp_content"));
				bhb.setBhp_desc(rs.getString("bhp_desc"));
				bhb.setBhp_status(rs.getString("bhp_status"));
				
				rslt.add(bhb);
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
	
	public static boolean saveHolisticPoint(BevalHolisticBean bhb){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalHolisticQueries.saveHolisticPoint);
			ps.setString(1, bhb.getBhp_content());
			ps.setString(2, bhb.getBhp_desc());
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
	
	public static boolean updateHolisticPoint(BevalHolisticBean bhb){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalHolisticQueries.updateHolisticPoint);
			ps.setString(1, bhb.getBhp_content());
			ps.setString(2, bhb.getBhp_desc());
			ps.setString(3, bhb.getBhp_status());
			ps.setString(4, bhb.getBhp_id());
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
	
	public static List<BevalHolisticBean> getRelatedPointList(BevalHolisticBean bhbb){
		
		List<BevalHolisticBean> rslt = new ArrayList<BevalHolisticBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalHolisticQueries.getRelatedPointList);
			ps.setString(1, bhbb.getBhc_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				BevalHolisticBean bhb = new BevalHolisticBean();
				
				bhb.setBhcp_id(rs.getString("bhcp_id"));
				bhb.setBhp_content(rs.getString("bhp_content"));
				bhb.setBhcp_status(rs.getString("bhcp_status"));
				
				rslt.add(bhb);
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
	
public static List<BevalHolisticBean> getActiveRelatedPointList(BevalHolisticBean bhbb){
		
		List<BevalHolisticBean> rslt = new ArrayList<BevalHolisticBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalHolisticQueries.getActiveRelatedPointList);
			ps.setString(1, bhbb.getBhc_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				BevalHolisticBean bhb = new BevalHolisticBean();
				
				bhb.setBhcp_id(rs.getString("bhcp_id"));
				bhb.setBhp_content(rs.getString("bhp_content"));
				bhb.setBhcp_status(rs.getString("bhcp_status"));
				
				rslt.add(bhb);
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
	
	
	public static List<BevalHolisticBean> getUnrelatedPointList(BevalHolisticBean bhbb){
		
		List<BevalHolisticBean> rslt = new ArrayList<BevalHolisticBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalHolisticQueries.getUnrelatedPointList);
			ps.setString(1, bhbb.getBhc_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				BevalHolisticBean bhb = new BevalHolisticBean();
				
				bhb.setBhp_id(rs.getString("bhp_id"));
				bhb.setBhp_content(rs.getString("bhp_content"));
				
				rslt.add(bhb);
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
	
	public static boolean saveCategoryPointRel(BevalHolisticBean bhb){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalHolisticQueries.saveCategoryPointRel);
			ps.setString(1, bhb.getBhc_id());
			ps.setString(2, bhb.getBhp_id());
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
	
	
	public static boolean updateCategoryPointRel(BevalHolisticBean bhb){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalHolisticQueries.updateCategoryPointRel);
			ps.setString(1, bhb.getBhcp_status());
			ps.setString(2, bhb.getBhcp_id());
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
	
	public static List<BevalHolisticBean> getRelatedCategoryList(BevalHolisticBean bhbb){
		
		List<BevalHolisticBean> rslt = new ArrayList<BevalHolisticBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalHolisticQueries.getRelatedCategoryList);
			ps.setString(1, bhbb.getCl_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				BevalHolisticBean bhb = new BevalHolisticBean();
				
				bhb.setBhccr_id(rs.getString("bhccr_id"));
				bhb.setBhc_id(rs.getString("bhc_id"));
				bhb.setCl_id(rs.getString("cl_id"));
				bhb.setBhccr_status(rs.getString("bhccr_status"));
				bhb.setBhc_name(rs.getString("bhc_name"));
				
				rslt.add(bhb);
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
	
	public static List<BevalHolisticBean> getUnrelatedCategoryList(BevalHolisticBean bhbb){
		
		List<BevalHolisticBean> rslt = new ArrayList<BevalHolisticBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalHolisticQueries.getUnrelatedCategoryList);
			ps.setString(1, bhbb.getCl_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				BevalHolisticBean bhb = new BevalHolisticBean();
				
				bhb.setBhc_id(rs.getString("bhc_id"));
				bhb.setBhc_name(rs.getString("bhc_name"));
				
				rslt.add(bhb);
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
	
	public static List<BevalHolisticBean> getUncommentedCategoryPointList(BevalHolisticBean bhbb){
		
		List<BevalHolisticBean> rslt = new ArrayList<BevalHolisticBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalHolisticQueries.getUncommentedRelatedPointList);
			ps.setString(1, bhbb.getAt_id());
			ps.setString(2, bhbb.getAt_id());
			ps.setString(3, bhbb.getBhc_id());			
			rs = ps.executeQuery();
			
			while(rs.next()){
				BevalHolisticBean bhb = new BevalHolisticBean();
				
				bhb.setBhcp_id(rs.getString("bhcp_id"));
				bhb.setBhp_content(rs.getString("bhp_content"));
				bhb.setBeval_comment(rs.getString("beval_comment"));
				bhb.setBhsr_id(rs.getString("bhsr_id"));
				
				String com = rs.getString("beval_comment");				
				if(com==null){
					bhb.setComment_status("N");
				} else {
					bhb.setComment_status("Y");
				}
				
				rslt.add(bhb);
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
	
	public static boolean saveClassCategoryRel(BevalHolisticBean bhb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalHolisticQueries.saveClassCategoryRel);
			ps.setString(1, bhb.getBhc_id());
			ps.setString(2, bhb.getCl_id());
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
	
	public static boolean updateClassCategoryRel(BevalHolisticBean bhb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalHolisticQueries.updateClassCategoryRel);
			ps.setString(1, bhb.getBhccr_status());
			ps.setString(2, bhb.getBhccr_id());
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
	
	public static boolean savePointsCommentGiven(BevalHolisticBean bhb){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalHolisticQueries.saveStudPointCommentGiven);
			ps.setString(1, bhb.getSi_id());
			ps.setString(2, bhb.getCl_id());
			ps.setString(3, bhb.getBhcp_id());
			ps.setString(4, bhb.getBeval_comment());
			ps.setString(5, bhb.getAc_year());
			ps.setString(6, bhb.getAt_id());
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
	
	public static boolean updatePointsCommentGiven(BevalHolisticBean bhb){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalHolisticQueries.updateStudPointCommentGiven);
			ps.setString(1, bhb.getBeval_comment());
			ps.setString(2, bhb.getBhsr_id());
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
	
	public static List<BevalHolisticBean> getStudentsHolisticComment(BevalHolisticBean bhbb){
		
		List<BevalHolisticBean> rslt = new ArrayList<BevalHolisticBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalHolisticQueries.getStudentsBehaviouralHolisticComment);
			ps.setString(1, bhbb.getSi_id());
			ps.setString(2, bhbb.getCl_id());
			ps.setString(3, bhbb.getAt_id());
			ps.setString(4, bhbb.getAc_year());
			rs = ps.executeQuery();
			
			while(rs.next()){
				BevalHolisticBean bhb = new BevalHolisticBean();
				
				bhb.setBhsr_id(rs.getString("bhsr_id"));
				bhb.setBhc_id(rs.getString("bhc_id"));
				bhb.setBhc_name(rs.getString("bhc_name"));
				bhb.setBhp_content(rs.getString("bhp_content"));
				bhb.setBeval_comment(rs.getString("beval_comment"));
				
				rslt.add(bhb);
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
