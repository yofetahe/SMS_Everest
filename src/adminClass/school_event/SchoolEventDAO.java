package adminClass.school_event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import connectionClass.Connector;
import util.DateConvertor;
import util.ReturnCurrentEthiopianYear;

public class SchoolEventDAO {
	
	public static List<SchoolEventBean> seventList(){
		List<SchoolEventBean> se = new ArrayList<SchoolEventBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(SchoolEventQueries.getSchEventList);
			ps.setInt(1, Integer.parseInt(yr));
			rs = ps.executeQuery();
			
			while(rs.next()){
				SchoolEventBean seb = new SchoolEventBean();
				
				seb.setSe_id(rs.getString("se_id"));
				seb.setSe_name(rs.getString("se_name"));
				seb.setSe_description(rs.getString("se_description"));
				seb.setSe_ethio_date(rs.getString("se_ethio_date"));
				seb.setSe_greg_date(rs.getString("se_greg_date"));
				seb.setSe_fiscalyear(rs.getString("se_fiscalyear"));
				seb.setSe_status(rs.getString("se_status"));
				
				se.add(seb);
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
		
		return se;
	}
	
	public static List<SchoolEventBean> dbSEventList(){
		List<SchoolEventBean> se = new ArrayList<SchoolEventBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(SchoolEventQueries.dbSchEventList);
			ps.setString(1, ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			rs = ps.executeQuery();
			
			while(rs.next()){
				SchoolEventBean seb = new SchoolEventBean();
				
				seb.setSe_id(rs.getString("se_id"));
				seb.setSe_name(rs.getString("se_name"));
				seb.setSe_description(rs.getString("se_description"));
				seb.setSe_ethio_date(rs.getString("se_ethio_date"));
				seb.setSe_greg_date(rs.getString("se_greg_date"));
				seb.setSe_fiscalyear(rs.getString("se_fiscalyear"));
				seb.setSe_status(rs.getString("se_status"));
				
				se.add(seb);
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
		
		return se;
	}
	
	public static boolean saveSchoolEvent(SchoolEventBean seb){
		boolean rslt = false;
		
		String ethioDate = DateConvertor.gregToEthioDateConvertor(seb.getSe_greg_date());
		String year = ethioDate.substring(ethioDate.length()-4, ethioDate.length());
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(SchoolEventQueries.saveSchoolEvent);
			ps.setString(1, seb.getSe_name());
			ps.setString(2, seb.getSe_description());
			ps.setString(3, ethioDate);
			ps.setString(4, seb.getSe_greg_date());
			ps.setInt(5, Integer.parseInt(year));
			rs = ps.executeUpdate();
			
			if(rs > 0){
				rslt = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		
		return rslt;
	}
	
	public static boolean updateSchoolEvent(SchoolEventBean seb){
		boolean rslt = false;
		
		String ethioDate = DateConvertor.gregToEthioDateConvertor(seb.getSe_greg_date());
		String year = ethioDate.substring(ethioDate.length()-4, ethioDate.length());
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(SchoolEventQueries.updateSchoolEvent);
			ps.setString(1, seb.getSe_name());
			ps.setString(2, seb.getSe_description());
			ps.setString(3, ethioDate);
			ps.setString(4, seb.getSe_greg_date());
			ps.setInt(5, Integer.parseInt(year));
			ps.setString(6, seb.getSe_status());
			ps.setString(7, seb.getSe_id());
			rs = ps.executeUpdate();
			
			if(rs > 0){
				rslt = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		
		return rslt;
	}

}
