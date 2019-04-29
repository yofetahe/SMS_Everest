package adminClass.holiday;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DateConvertor;
import util.ReturnCurrentEthiopianYear;
import connectionClass.Connector;

public class holidayDAO {
	
	public static List<holidayBean> getHolidayList(){
		List<holidayBean> hb = new ArrayList<holidayBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(holidayQueries.getHolidayList);
			ps.setInt(1, Integer.parseInt(yr));
			rs = ps.executeQuery();
			
			while(rs.next()){
				holidayBean h = new holidayBean();
				
				h.setH_id(rs.getString("h_id"));
				h.setH_name(rs.getString("h_name"));
				h.setH_ethio_date(rs.getString("h_ethio_date"));
				h.setH_greg_date(rs.getString("h_greg_date"));
				h.setH_fiscalyear(rs.getString("h_fiscalyear"));
				h.setWork_status(rs.getString("work_status"));
				h.setH_status(rs.getString("h_status"));
				
				hb.add(h);
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
		
		return hb;
	}
	
	public static List<holidayBean> dbGetHolidayList(){
		List<holidayBean> hb = new ArrayList<holidayBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(holidayQueries.getHolidayList);
			ps.setString(1, yr);
			rs = ps.executeQuery();
			
			while(rs.next()){
				holidayBean h = new holidayBean();
				
				h.setH_id(rs.getString("h_id"));
				h.setH_name(rs.getString("h_name"));
				h.setH_ethio_date(rs.getString("h_ethio_date"));
				h.setH_greg_date(rs.getString("h_greg_date"));
				h.setH_fiscalyear(rs.getString("h_fiscalyear"));
				h.setWork_status(rs.getString("work_status"));
				h.setH_status(rs.getString("h_status"));
				
				hb.add(h);
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
		
		return hb;
	}
	
	public static boolean saveholiday(holidayBean hb){
		boolean rslt = false;
		
		String ethioDate = DateConvertor.gregToEthioDateConvertor(hb.getH_greg_date());
		String year = ethioDate.substring(ethioDate.length()-4, ethioDate.length());
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(holidayQueries.saveHoliday);
			ps.setString(1, hb.getH_name());
			ps.setString(2, ethioDate);
			ps.setString(3, hb.getH_greg_date());
			ps.setInt(4, Integer.parseInt(year));
			ps.setString(5, hb.getWork_status());
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
	
	public static boolean updateHoliday(holidayBean hb){
		boolean rslt = false;
		
		String ethioDate = DateConvertor.gregToEthioDateConvertor(hb.getH_greg_date());
		String year = ethioDate.substring(ethioDate.length()-4, ethioDate.length());
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(holidayQueries.updateHoliday);
			ps.setString(1, hb.getH_name());
			ps.setString(2, ethioDate);
			ps.setString(3, hb.getH_greg_date());
			ps.setInt(4, Integer.parseInt(year));
			ps.setString(5, hb.getWork_status());
			ps.setString(6, hb.getH_status());
			ps.setString(7, hb.getH_id());
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
