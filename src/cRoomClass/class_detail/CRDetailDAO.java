package cRoomClass.class_detail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cRoomClass.CRoomQueries;
import connectionClass.Connector;

public class CRDetailDAO {
	
	public static List<CRDetailBean> getClassDetailList(CRDetailBean crd){
		List<CRDetailBean> crdt = new ArrayList<CRDetailBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CRDetailQueries.getClassDetailList);
			ps.setString(1, crd.getCl_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				CRDetailBean crdtl = new CRDetailBean();
				
				crdtl.setClcd_id(rs.getString("CLCD_ID"));
				crdtl.setCd_name(rs.getString("cd_name"));
				crdtl.setRel_status(rs.getString("rel_status"));
				crdtl.setCl_capacity(rs.getString("cl_capacity"));
				
				crdt.add(crdtl);
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
		
		return crdt;
	}
	
	public static List<CRDetailBean> getAllClassDetail(CRDetailBean crd){
		List<CRDetailBean> crdt = new ArrayList<CRDetailBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CRDetailQueries.getAllClassDetailList);
			ps.setString(1, crd.getCl_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				CRDetailBean cd = new CRDetailBean();
				
				cd.setCd_id(rs.getString("CD_ID"));
				cd.setCd_name(rs.getString("CD_NAME"));
				cd.setCd_status(rs.getString("CD_STATUS"));
				
				crdt.add(cd);
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
		
		return crdt;
	}
	
	public static boolean checkClassDetail(CRDetailBean crd){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CRDetailQueries.checkClassDetail);
			ps.setString(1, crd.getCl_id());
			ps.setString(2, crd.getCd_id());
			rs = ps.executeQuery();
			
			int x = 0;
			while(rs.next()){
				x++;
			}
			if(x > 0){
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
	
	public static boolean insertClassDetail(CRDetailBean crd){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CRDetailQueries.insertClassDetail);
			ps.setString(1, crd.getCl_id());
			ps.setString(2, crd.getCd_id());
			ps.setString(3, crd.getCl_capacity());
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
	
	public static boolean updateClassDetail(CRDetailBean crd){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CRDetailQueries.updateClassDetail);
			ps.setString(1, crd.getCl_capacity());
			ps.setString(2, crd.getRel_status());
			ps.setString(3, crd.getClcd_id());
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
	
	public static String getClassDetailName(String cd_id){
		String classDetailName = "";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(CRDetailQueries.getClassDetailName);
			ps.setString(1, cd_id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				classDetailName = rs.getString("cd_name");
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
		
		return classDetailName;		
	}

}
