package adminClass.certificateDefaultComment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectionClass.Connector;

public class CertDefaultComDAO {
	
	public static List<CertDefaultComBean> getDefaultCommentList(){
		
		List<CertDefaultComBean> rslt = new ArrayList<CertDefaultComBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CertDefaultComQueries.getDefaultCommentList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				CertDefaultComBean cdc = new CertDefaultComBean();
				
				cdc.setEdc_id(rs.getString("edc_id"));
				cdc.setEdc_content(rs.getString("edc_content"));
				cdc.setRank_from(rs.getString("rank_from"));
				cdc.setRank_to(rs.getString("rank_to"));
				cdc.setEdc_status(rs.getString("edc_status"));
				
				rslt.add(cdc);
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
	
	public static boolean validateRankRang(CertDefaultComBean edc){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int count = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CertDefaultComQueries.validateRankRang);
			ps.setString(1, edc.getRank_from());
			ps.setString(2, edc.getRank_from());
			ps.setString(3, edc.getRank_to());
			ps.setString(4, edc.getRank_to());
			rs = ps.executeQuery();
			
			while(rs.next()){
				count++;
			}
			
			if(count > 0){
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

	public static boolean saveDefaultComment(CertDefaultComBean edc){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CertDefaultComQueries.insertDefaultComment);
			ps.setString(1, edc.getEdc_content());
			ps.setString(2, edc.getRank_from());
			ps.setString(3, edc.getRank_to());
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
	
	public static boolean updateDefaultComment(CertDefaultComBean edc){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CertDefaultComQueries.updateDefaultComment);
			ps.setString(1, edc.getEdc_content());
			ps.setString(2, edc.getRank_from());
			ps.setString(3, edc.getRank_to());
			ps.setString(4, edc.getEdc_status());
			ps.setString(5, edc.getEdc_id());
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
	
	public static List<CertDefaultComBean> getCommentPerRank(String qrt_avg){
		
		List<CertDefaultComBean> rslt = new ArrayList<CertDefaultComBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CertDefaultComQueries.getDefaultCommentPerRank);
			ps.setString(1, qrt_avg);
			ps.setString(2, qrt_avg);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				CertDefaultComBean cdc = new CertDefaultComBean();
				
				cdc.setEdc_content(rs.getString("edc_content"));				
				
				rslt.add(cdc);
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
