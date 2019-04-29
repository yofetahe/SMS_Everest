package behEvaluationClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import connectionClass.Connector;
import util.ReturnCurrentEthiopianYear;

public class BevalDAO {
	
	public static List<BevalBean> getQuarterList(){
		List<BevalBean> rslt = new ArrayList<BevalBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalQueries.getQuarterList);
			rs = ps.executeQuery();
			
			while(rs.next()){
				BevalBean bb = new BevalBean();
				
				bb.setQr_id(rs.getString("qr_id"));
				bb.setQr_name(rs.getString("qr_name"));
				
				rslt.add(bb);
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
	
	public static List<BevalBean> getStudentBevalRsltList(BevalBean bvb){
		
		String yr = ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear();
		
		List<BevalBean> rslt = new ArrayList<BevalBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalQueries.getStudentBevalRslt);
			ps.setString(1, bvb.getCl_id());
			ps.setString(2, bvb.getSi_id());
			ps.setString(3, bvb.getAc_year());
			ps.setString(4, bvb.getQr_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				BevalBean bb = new BevalBean();
				
				bb.setBsr_id(rs.getString("bsr_id"));
				bb.setBt_id(rs.getString("bt_id"));
				bb.setBt_title(rs.getString("bt_title"));
				bb.setBg_id(rs.getString("bg_id"));
				bb.setBg_name(rs.getString("bg_name"));
				bb.setAc_year(rs.getString("academic_year"));
				bb.setQuarter(rs.getString("at_name"));
				bb.setBsr_status(rs.getString("bsr_status"));
				
				rslt.add(bb);
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
	
	public static List<BevalBean> getBevelTraitList(BevalBean beb){
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		List<BevalBean> rslt = new ArrayList<BevalBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
//		PreparedStatement g_ps = null;
//		ResultSet g_rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalQueries.getUnmarkedBevalTraitList);
			ps.setString(1, beb.getCl_id());
			ps.setString(2, beb.getCl_id());
			ps.setString(3, beb.getSi_id());
			ps.setString(4, yr);
			ps.setString(5, beb.getQr_id());
			rs = ps.executeQuery();
			
//			Map<String, String> eglist = null;
			
			while(rs.next()){
				BevalBean bb = new BevalBean();
				
				bb.setBt_id(rs.getString("bt_id"));
				bb.setBt_title(rs.getString("bt_title"));
				bb.setEvalgrd_list(getBevelGradeList(bb));
				
//				g_ps = con.prepareStatement(BevalQueries.getBevalGradeList);
//				g_ps.setString(1, bb.getBt_id());
//				g_rs = g_ps.executeQuery();			
//				
//				while(g_rs.next()){
//					System.out.println(rs.getString("bt_title") + " " + g_rs.getString("bg_id")+ " " + g_rs.getString("bg_name"));
//					eglist.put(g_rs.getString("bg_id"), g_rs.getString("bg_name"));
//					bb.setEval_grd_list(eglist);
//				}
				
				rslt.add(bb);
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
	
	public static List<BevalBean> getBevelGradeList(BevalBean bvb){
		List<BevalBean> rslt = new ArrayList<BevalBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalQueries.getBevalGradeList);
			ps.setString(1, bvb.getBt_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				BevalBean bb = new BevalBean();
				
				bb.setBg_id(rs.getString("bg_id"));
				bb.setBg_name(rs.getString("bg_name"));
				
				rslt.add(bb);
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
	
	public static List<BevalBean> getBevelTraitEditList(BevalBean beb){
		
		//getting academic year --- starting from May to August the academic year consider the next year
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
				
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);
						
		String yr = util.DateConvertor.dateConvertor(day+"-"+month+"-"+year);
		
		List<BevalBean> rslt = new ArrayList<BevalBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalQueries.getMarkedBevalTraitList);
			ps.setString(1, beb.getCl_id());
			ps.setString(2, beb.getCl_id());
			ps.setString(3, beb.getSi_id());
			ps.setString(4, yr);
			ps.setString(5, beb.getQr_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				BevalBean bb = new BevalBean();
				
				bb.setBt_id(rs.getString("bt_id"));
				bb.setBt_title(rs.getString("bt_title"));
				
				rslt.add(bb);
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
	
	public static List<BevalBean> getBevelGradeEditList(BevalBean bvb){
		List<BevalBean> rslt = new ArrayList<BevalBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalQueries.getBevalGradeGivenList);			
			ps.setString(1, bvb.getBt_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				BevalBean bb = new BevalBean();
				
				bb.setBg_id(rs.getString("bg_id"));
				bb.setBg_name(rs.getString("bg_name"));
				
				rslt.add(bb);
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
	
	public static boolean saveBevalGradeFromJS(BevalBean beb){
		
		
		String yr = ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear();
				
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalQueries.saveBevalGrade);
			
			String evalList = beb.getStudbevalrslt_list().toString();
			
			String[] studEvalMarkList = evalList.substring(1, evalList.length()-1).split(",");
			
			for(int i = 0; i < studEvalMarkList.length; i+=5){
				
				ps.setString(1, studEvalMarkList[i]);//si_id
				ps.setString(2, studEvalMarkList[i+1]);//cl_id
				ps.setString(3, studEvalMarkList[i+2]);//bt_id
				ps.setString(4, studEvalMarkList[i+3]);//bg_id
				ps.setString(5, studEvalMarkList[i+4]);//qr_id
				ps.setString(6, yr);			
				rs = ps.executeUpdate();
				
			}
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
	
	public static boolean saveBevalGrade(BevalBean beb){
		
								
		String yr = ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear();
				
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalQueries.saveBevalGrade);
			ps.setString(1, beb.getSi_id());
			ps.setString(2, beb.getCl_id());
			ps.setString(3, beb.getBt_id());
			ps.setString(4, beb.getBg_id());
			ps.setString(5, beb.getQr_id());
			ps.setString(6, yr);			
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
	
	public static boolean updateBevalGrade(BevalBean beb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(BevalQueries.updateBevalGrade);
			ps.setString(1, beb.getBt_id());
			ps.setString(2, beb.getBg_id());
			ps.setString(3, beb.getBsr_status());
			ps.setString(4, beb.getBsr_id());
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
