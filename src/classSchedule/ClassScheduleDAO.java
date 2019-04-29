package classSchedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import adminClass.classPeriodAssignment.ClassPeriodAssignBean;
import adminClass.classPeriodAssignment.ClassPeriodAssignDAO;
import subjectClass.SubjectBean;
import subjectClass.SubjectQueries;
import util.ReturnCurrentEthiopianYear;
import connectionClass.Connector;

public class ClassScheduleDAO {
	
	static List<ClassScheduleBean> clScheduleData = new ArrayList<ClassScheduleBean>();
	
	public static List<ClassScheduleBean> getClassScheduleInfo(){
		
		List<ClassScheduleBean> csi = new ArrayList<ClassScheduleBean>();
		
		clScheduleData.clear();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ClassScheduleQueries.getClassSchInfo);
			rs = ps.executeQuery();
			
			while(rs.next()){
				ClassScheduleBean csb = new ClassScheduleBean();
				
				csb.setNoof_days(rs.getString("noof_days"));
				csb.setNoof_period(rs.getString("noof_period"));
				
				csi.add(csb);
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
		
		return csi;
	}
	
	public static List<ClassScheduleBean> getClassScheduleDetail(ClassScheduleBean csd){
		List<ClassScheduleBean> rslt = new ArrayList<ClassScheduleBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ClassScheduleQueries.getClassSchDetail);
			ps.setString(1, csd.getCl_id());
			ps.setString(2, csd.getCd_id());
			ps.setString(3, csd.getAcademic_year());
			rs = ps.executeQuery();
			
			while(rs.next()){
				ClassScheduleBean csb = new ClassScheduleBean();
				
				csb.setTi_id(rs.getString("ti_id"));
				csb.setTfname(rs.getString("fname"));
				csb.setTmname(rs.getString("mname"));
				csb.setTgname(rs.getString("gname"));
				csb.setSub_id(rs.getString("sub_id"));
				csb.setSub_name(rs.getString("sub_name"));
				csb.setWeek_day(rs.getString("week_day"));
				csb.setPeriod(rs.getString("period"));
				csb.setCs_id(rs.getString("cs_id"));
				csb.setClass_name(rs.getString("class_name"));
				csb.setCd_name(rs.getString("cd_name"));
				
				rslt.add(csb);
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
	
	public static List<ClassScheduleBean> getSelectedSubList(ClassScheduleBean csb){
		List<ClassScheduleBean> sb = new ArrayList<ClassScheduleBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(SubjectQueries.getSelectedSubject);
			ps.setString(1, csb.getCl_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				ClassScheduleBean sub = new ClassScheduleBean();
				
				sub.setSub_id(rs.getString("SUB_ID"));
				sub.setSub_name(rs.getString("SUB_NAME"));
				
				sb.add(sub);
				
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
		
		return sb;
	}	
	
	public static List<ClassScheduleBean> getSubList(ClassScheduleBean csb){
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		List<ClassScheduleBean> sb = new ArrayList<ClassScheduleBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ClassScheduleQueries.getTeacherList);
			ps.setString(1, csb.getCl_id());
			ps.setString(2, csb.getSub_id());			
			ps.setString(3, csb.getPeriod());
			ps.setString(4, yr);
			ps.setString(5, csb.getWeek_day());
			rs = ps.executeQuery();
			
			while(rs.next()){
				ClassScheduleBean sub = new ClassScheduleBean();
				
				String fullname = rs.getString("fname") + " " + rs.getString("mname") + " " + rs.getString("gname");
				sub.setFullName(fullname);
				sub.setTi_id(rs.getString("ti_id"));
				sub.setTa_id(rs.getString("ta_id"));
				
				sb.add(sub);				
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
		
		return sb;
	}
	
	public static List<ClassScheduleBean> collectClassScheduleData(ClassScheduleBean csb){
		
		List<ClassScheduleBean> rslt = new ArrayList<ClassScheduleBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		boolean alreadyExist = false;
		
		try{
			
			con = Connector.connect();
			
			//checking whether the data is already exit or not
			ps2 = con.prepareStatement(ClassScheduleQueries.checkClassSCheduleInfo);
			ps2.setString(1, csb.getWeek_day());
			ps2.setString(2, csb.getPeriod());
			ps2.setString(3, csb.getCd_id());
			ps2.setString(4, csb.getAcademic_year());
			ps2.setString(5, csb.getCl_id());
			rs2 = ps2.executeQuery();
			
			while(rs2.next()){
				alreadyExist = true;
			}
			
			ps = con.prepareStatement(ClassScheduleQueries.getSelectedTeacherAndSubject);
			ps.setString(1, csb.getTa_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				ClassScheduleBean sub = new ClassScheduleBean();
				
				String fullname = rs.getString("fname") + " " + rs.getString("mname") + " " + rs.getString("gname");
				sub.setFullName(fullname);
				sub.setSub_name(rs.getString("sub_name"));
				
				if(alreadyExist){
					sub.setCheckExistance("true");
				}
				
				rslt.add(sub);
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
		
		ClassScheduleBean cs = new ClassScheduleBean();
		
		cs.setTa_id(csb.getTa_id());
		cs.setCd_id(csb.getCd_id());
		cs.setAcademic_year(csb.getAcademic_year());
		cs.setWeek_day(csb.getWeek_day());
		cs.setPeriod(csb.getPeriod());
		cs.setIndx(csb.getIndx());
		
		clScheduleData.add(cs);
		
		return rslt;
	}
	
	public static List<Integer> saveClassScheduleInfo(ClassScheduleBean csb){
		
		List<Integer> in = new ArrayList<Integer>();
		List<Integer> indxToBeRemove = new ArrayList<Integer>();
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		int rs2 = 0;
		
		try{
			
			con = Connector.connect();
			
			for(int i = 0; i < clScheduleData.size(); i++){
				
				Integer wd = Integer.parseInt(clScheduleData.get(i).getWeek_day());
				Integer sb = Integer.parseInt(csb.getSaveBtnIndex());
				
				//checking whether the data is one day data or not
				if(wd == sb){
					
					//checking whether the data is already exit or not
					ps = con.prepareStatement(ClassScheduleQueries.checkClassSCheduleInfo);
					ps.setString(1, clScheduleData.get(i).getWeek_day());
					ps.setString(2, clScheduleData.get(i).getPeriod());
					ps.setString(3, clScheduleData.get(i).getCd_id());
					ps.setString(4, clScheduleData.get(i).getAcademic_year());
					ps.setString(5, clScheduleData.get(i).getCl_id());
					rs = ps.executeQuery();
					
					int counter = 0;
					while(rs.next()){
						counter++;
					}
					
					if(counter > 0){
						in.add(Integer.parseInt(clScheduleData.get(i).getIndx()));
						indxToBeRemove.add(i);
					} else {
						//saving the data
						ps2 = con.prepareStatement(ClassScheduleQueries.saveClassScheduleInfo);
						ps2.setString(1, clScheduleData.get(i).getWeek_day());
						ps2.setString(2, clScheduleData.get(i).getPeriod());
						ps2.setString(3, clScheduleData.get(i).getTa_id());
						ps2.setString(4, clScheduleData.get(i).getCd_id());
						ps2.setString(5, clScheduleData.get(i).getAcademic_year());
						rs2 = ps2.executeUpdate();
						
						if(rs2 > 0){
							indxToBeRemove.add(i);
						}
					}
				}
			}
			for(int x = 0; x < indxToBeRemove.size(); x++){
				clScheduleData.remove(indxToBeRemove.get(x));
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
		
		return in;
	}
	
	public static boolean removeAddedInfo(ClassScheduleBean csb){
		
		boolean rslt = false;
		
		if(clScheduleData.size() == 0){
			
			rslt = true;
		}
		
		for(int i = 0; i < clScheduleData.size(); i++){
			
			if(Integer.parseInt(csb.getPeriod()) == Integer.parseInt(clScheduleData.get(i).getPeriod()) && Integer.parseInt(csb.getWeek_day()) == Integer.parseInt(clScheduleData.get(i).getWeek_day())){
				
				clScheduleData.remove(i);
				rslt = true;
			}
		}		
		return rslt;
	}
	
	public static String updateClassScheduleInfo(ClassScheduleBean csb){
		String rslt = "";		
		Connection con = null;
		PreparedStatement ps = null;
		int rst = 0;
		
		try{
			con = Connector.connect();
			ps = con.prepareStatement(ClassScheduleQueries.updateClassScheduleInfo);
			ps.setString(1, csb.getTa_id());
			ps.setString(2, csb.getCs_id());
			rst = ps.executeUpdate();
			if(rst > 0){
				rslt = csb.getTa_id();
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
	
	public static List<ClassScheduleBean> getClassSchedulePerTeacher(String ti_id){
		List<ClassScheduleBean> rslt = new ArrayList<ClassScheduleBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String yr = ReturnCurrentEthiopianYear.getCurrentEthiopianYear();
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(ClassScheduleQueries.teachersDashBoardInfo);
			ps.setString(1, ti_id);
			ps.setString(2, yr);
			rs = ps.executeQuery();
			
			while(rs.next()){
				ClassScheduleBean sub = new ClassScheduleBean();
				
				sub.setPeriod(rs.getString("period"));
				sub.setClass_name(rs.getString("class_name"));
				sub.setCd_name(rs.getString("cd_name"));
				sub.setWeek_day(rs.getString("week_day"));
				sub.setSub_name(rs.getString("sub_name"));
				
				rslt.add(sub);
				
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
	
public static List<List<String>> getSortedClassScheduleSubjectOnly(List<ClassScheduleBean> cs_info, List<ClassScheduleBean> cs_detail){
		
		List<List<String>> rslt = new ArrayList<List<String>>();
		
		int counter = 0;
		
		List<String> rw_header = new ArrayList<String>();
		
		for(int i = 0; i <= Integer.parseInt(cs_info.get(0).getNoof_period()); i++){
			
			if(i == 0){
				rw_header.add("");
				continue;
			}
			switch (i) {
			case 1:
				rw_header.add("Period 1");
				break;
			case 2:
				rw_header.add("Period 2");
				break;
			case 3:
				rw_header.add("Period 3");
				break;
			case 4:
				rw_header.add("Period 4");
				break;
			case 5:
				rw_header.add("Period 5");
				break;
			case 6:
				rw_header.add("Period 6");
				break;
			case 7:
				rw_header.add("Period 7");
				break;
			default:
				break;
			}
			
			
		}
		
		rslt.add(rw_header);		
		
		for(int j = 1; j <= Integer.parseInt(cs_info.get(0).getNoof_days()); j++){
			
			List<String> rw_list = new ArrayList<String>();			
			
			for(int i = 0; i <= Integer.parseInt(cs_info.get(0).getNoof_period()); i++){
				
				if(i == 0){
					switch (j) {
					case 1:
						rw_list.add("MON");
						break;
					case 2:
						rw_list.add("TUE");
						break;
					case 3:
						rw_list.add("WED");
						break;
					case 4:
						rw_list.add("THU");
						break;
					case 5:
						rw_list.add("FRI");
						break;

					default:
						break;
					}
					
					continue;
				}
				
				for(int x = 0; x < cs_detail.size(); x++){
				
					if(i == Integer.parseInt(cs_detail.get(x).getPeriod()) && j == Integer.parseInt(cs_detail.get(x).getWeek_day())){
						rw_list.add(cs_detail.get(x).getSub_name() + "-" + cs_detail.get(x).getTfname().substring(0, 1) + cs_detail.get(x).getTmname().substring(0, 1) + cs_detail.get(x).getTgname().substring(0, 1));
					} else {
						counter++;
					}
				}
				if(counter == cs_detail.size()){
					rw_list.add("-");
				}
				counter = 0;
			}
			
			rslt.add(rw_list);
		}
				
		return rslt;
	}
	
	public static List<List<ClassScheduleBean>> getSortedClassScheduleDetail(List<ClassScheduleBean> cs_info, List<ClassScheduleBean> cs_detail){
		
		List<List<ClassScheduleBean>> rslt = new ArrayList<List<ClassScheduleBean>>();
		
		int counter = 0;
		
		List<ClassScheduleBean> rslt_in = new ArrayList<ClassScheduleBean>();
		
		for(int i = 0; i <= Integer.parseInt(cs_info.get(0).getNoof_period()); i++){
			//System.out.println(i + " period");
			ClassScheduleBean rw_header = new ClassScheduleBean();
			if(i == 0){
				rw_header.setPeriod_name("");
				rslt_in.add(rw_header);
				continue;
			}
			switch (i) {
			case 1:
				rw_header.setPeriod_name("Period 1");
				rslt_in.add(rw_header);
				break;
			case 2:
				rw_header.setPeriod_name("Period 2");
				rslt_in.add(rw_header);
				break;
			case 3:
				rw_header.setPeriod_name("Period 3");
				rslt_in.add(rw_header);
				break;
			case 4:
				rw_header.setPeriod_name("Period 4");
				rslt_in.add(rw_header);
				break;
			case 5:
				rw_header.setPeriod_name("Period 5");
				rslt_in.add(rw_header);
				break;
			case 6:
				rw_header.setPeriod_name("Period 6");
				rslt_in.add(rw_header);
				break;
			case 7:
				rw_header.setPeriod_name("Period 7");
				rslt_in.add(rw_header);
				break;			
			}	
			
		}
		
		rslt.add(rslt_in);
				
		for(int j = 1; j <= Integer.parseInt(cs_info.get(0).getNoof_days()); j++){
			List<ClassScheduleBean> rslt_row = new ArrayList<ClassScheduleBean>();						
			
			for(int i = 0; i <= Integer.parseInt(cs_info.get(0).getNoof_period()); i++){
				ClassScheduleBean rw_list = new ClassScheduleBean();
				if(i == 0){
					switch (j) {
					case 1:
						rw_list.setDay_name("MON");
						break;
					case 2:
						rw_list.setDay_name("TUE");
						break;
					case 3:
						rw_list.setDay_name("WED");
						break;
					case 4:
						rw_list.setDay_name("THU");
						break;
					case 5:
						rw_list.setDay_name("FRI");
						break;					
					}
				}
				
				for(int x = 0; x < cs_detail.size(); x++){
				
					if(i == Integer.parseInt(cs_detail.get(x).getPeriod()) && j == Integer.parseInt(cs_detail.get(x).getWeek_day())){
						rw_list.setSub_name(cs_detail.get(x).getSub_name());
						rw_list.setSub_id(cs_detail.get(x).getSub_id());
						rw_list.setCs_id(cs_detail.get(x).getCs_id());
						rw_list.setFullName(cs_detail.get(x).getTfname() + " " + cs_detail.get(x).getTmname());
					} else {
						counter++;
					}
				}
				if(counter == cs_detail.size()){
					rw_list.setSub_name("-");
				}
				counter = 0;
				
				rslt_row.add(rw_list);
			}
			
			rslt.add(rslt_row);
		}
				
		return rslt;
	}
	
	public static List<List<ClassScheduleBean>> getSortedClassSchedulePerTeacher(List<ClassScheduleBean> classSchedulePerTeacher, List<ClassScheduleBean> cs_info){
		
		List<List<ClassScheduleBean>> rslt = new ArrayList<List<ClassScheduleBean>>();
		
		int counter = 0;
		
		List<ClassScheduleBean> rslt_in = new ArrayList<ClassScheduleBean>();
		
		for(int i = 0; i <= Integer.parseInt(cs_info.get(0).getNoof_period()); i++){
			//System.out.println(i + " period");
			ClassScheduleBean rw_header = new ClassScheduleBean();
			if(i == 0){
				rw_header.setPeriod_name("");
				rslt_in.add(rw_header);
				continue;
			}
			switch (i) {
			case 1:
				rw_header.setPeriod_name("Period 1");
				rslt_in.add(rw_header);
				break;
			case 2:
				rw_header.setPeriod_name("Period 2");
				rslt_in.add(rw_header);
				break;
			case 3:
				rw_header.setPeriod_name("Period 3");
				rslt_in.add(rw_header);
				break;
			case 4:
				rw_header.setPeriod_name("Period 4");
				rslt_in.add(rw_header);
				break;
			case 5:
				rw_header.setPeriod_name("Period 5");
				rslt_in.add(rw_header);
				break;
			case 6:
				rw_header.setPeriod_name("Period 6");
				rslt_in.add(rw_header);
				break;
			case 7:
				rw_header.setPeriod_name("Period 7");
				rslt_in.add(rw_header);
				break;			
			}	
			
		}
		
		rslt.add(rslt_in);
				
		for(int j = 1; j <= Integer.parseInt(cs_info.get(0).getNoof_days()); j++){
			
			List<ClassScheduleBean> rslt_row = new ArrayList<ClassScheduleBean>();						
			
			for(int i = 0; i <= Integer.parseInt(cs_info.get(0).getNoof_period()); i++){
				ClassScheduleBean rw_list = new ClassScheduleBean();
				if(i == 0){
					switch (j) {
					case 1:
						rw_list.setDay_name("MON");
						break;
					case 2:
						rw_list.setDay_name("TUE");
						break;
					case 3:
						rw_list.setDay_name("WED");
						break;
					case 4:
						rw_list.setDay_name("THU");
						break;
					case 5:
						rw_list.setDay_name("FRI");
						break;					
					}
				}
				
				for(int x = 0; x < classSchedulePerTeacher.size(); x++){
				
					if(i == Integer.parseInt(classSchedulePerTeacher.get(x).getPeriod()) && j == Integer.parseInt(classSchedulePerTeacher.get(x).getWeek_day())){
						rw_list.setSub_name(classSchedulePerTeacher.get(x).getSub_name());
						rw_list.setSub_id(classSchedulePerTeacher.get(x).getSub_id());
						rw_list.setClass_name(classSchedulePerTeacher.get(x).getClass_name());
						rw_list.setCd_name(classSchedulePerTeacher.get(x).getCd_name());
					} else {
						counter++;
					}
				}
				if(counter == classSchedulePerTeacher.size()){
					rw_list.setSub_name("-");
				}
				counter = 0;
				
				rslt_row.add(rw_list);
			}
			
			rslt.add(rslt_row);
		}
				
		return rslt;
	}
	
	public static List<List<ClassScheduleBean>> getGeneralSortedClassSchedule(List<ClassScheduleBean> cs_detail, List<ClassScheduleBean> cs_info){
		
		List<List<ClassScheduleBean>> rslt = new ArrayList<List<ClassScheduleBean>>();
		
		int counter = 0;
		
		List<ClassScheduleBean> rslt_in = new ArrayList<ClassScheduleBean>();
		
		for(int i = 0; i <= Integer.parseInt(cs_info.get(0).getNoof_period()); i++){
			//System.out.println(i + " period");
			ClassScheduleBean rw_header = new ClassScheduleBean();
			if(i == 0){
				rw_header.setPeriod_name("");
				rslt_in.add(rw_header);
				continue;
			}
			switch (i) {
			case 1:
				rw_header.setPeriod_name("Period 1");
				rslt_in.add(rw_header);
				break;
			case 2:
				rw_header.setPeriod_name("Period 2");
				rslt_in.add(rw_header);
				break;
			case 3:
				rw_header.setPeriod_name("Period 3");
				rslt_in.add(rw_header);
				break;
			case 4:
				rw_header.setPeriod_name("Period 4");
				rslt_in.add(rw_header);
				break;
			case 5:
				rw_header.setPeriod_name("Period 5");
				rslt_in.add(rw_header);
				break;
			case 6:
				rw_header.setPeriod_name("Period 6");
				rslt_in.add(rw_header);
				break;
			case 7:
				rw_header.setPeriod_name("Period 7");
				rslt_in.add(rw_header);
				break;			
			}	
			
		}
		
		rslt.add(rslt_in);
				
		for(int j = 1; j <= Integer.parseInt(cs_info.get(0).getNoof_days()); j++){
			List<ClassScheduleBean> rslt_row = new ArrayList<ClassScheduleBean>();						
			
			for(int i = 0; i <= Integer.parseInt(cs_info.get(0).getNoof_period()); i++){
				ClassScheduleBean rw_list = new ClassScheduleBean();
				if(i == 0){
					switch (j) {
					case 1:
						rw_list.setDay_name("MON");
						break;
					case 2:
						rw_list.setDay_name("TUE");
						break;
					case 3:
						rw_list.setDay_name("WED");
						break;
					case 4:
						rw_list.setDay_name("THU");
						break;
					case 5:
						rw_list.setDay_name("FRI");
						break;					
					}
				}
				
				for(int x = 0; x < cs_detail.size(); x++){
				
					if(i == Integer.parseInt(cs_detail.get(x).getPeriod()) && j == Integer.parseInt(cs_detail.get(x).getWeek_day())){
						
						rw_list.setClass_name(cs_detail.get(x).getClass_name());
						rw_list.setCd_name(cs_detail.get(x).getCd_name());
						
						rw_list.setSub_name(cs_detail.get(x).getSub_name());
						rw_list.setSub_id(cs_detail.get(x).getSub_id());
						
						rw_list.setTfname(cs_detail.get(x).getTfname());
						rw_list.setTmname(cs_detail.get(x).getTmname());
						rw_list.setTgname(cs_detail.get(x).getTgname());
						
					} else {
						counter++;
					}
				}
				if(counter == cs_detail.size()){
					rw_list.setSub_name("-");
				}
				counter = 0;
				
				rslt_row.add(rw_list);
			}
			
			rslt.add(rslt_row);
		}
				
		return rslt;
	}
	
	public static List<List<ClassPeriodAssignBean>> getSuggestedClassSchedule(ClassScheduleBean csb, List<ClassPeriodAssignBean> period_list){
		
		List<List<ClassPeriodAssignBean>> rslt = new ArrayList<List<ClassPeriodAssignBean>>();
		
		
		///>>> required
		///*** subject list per class
		///*** subject assignment per week for a class
		///+++ 1st action - pick a week day
		///+++ 2nd action - pick a period
		
		String subjectName = "";
		int period_per_week = 0, period = 0, day = 0;
		
		String[][] csch = new String[5][7];
		
		int[] day_array = new int[]{0, 1, 2, 3, 4};
		int[] period_array = new int[]{0, 1, 2, 3, 4, 5, 6};
		
		
		///>>> get the subject and period per week
		for(int t = 0; t < period_list.size(); t++){
			
			subjectName = period_list.get(t).getSubjectBean().getSub_name();
			period_per_week = period_list.get(t).getPeriod_per_week();
			
			///>>> it needs random pick up for week days and periods
			if(period_per_week < 5){
				
				for(int i = 0; i < period_per_week; ){
					
					day = day_array[ThreadLocalRandom.current().nextInt(0, day_array.length)];
					
					int trial_counter = 0, ii = 0;
					
					for(int j = 0; j < period_array.length; ){
						
						period = period_array[ThreadLocalRandom.current().nextInt(0, period_array.length)];	
						
						if(csch[day][period] == null){
							
							////>>> to check not to assign a subject more than once per day
							int check_subject_per_day = 0;
							
							for(int x = 0; x < period_array.length; x++){
								
								if(csch[day][x] != null && csch[day][x].equals(subjectName)){
									
									check_subject_per_day++;
								}
							}
							
							if(check_subject_per_day == 0){
								
								csch[day][period] = subjectName;
								
								j++;
								ii++;								
							}
						}
						
						///>>> if all the slots are already taken. To break infinite loop.
						trial_counter++;
						
						if(trial_counter >= 10){
							
							break;
						}
					}
					
					if(ii > 0){
						i++;
					}
				}
			}
			
			///>>> it needs random pick up for period only
			if(period_per_week == 5){
				
				int trial_counter_5 = 0;
				
				for(int i = 0; i < day_array.length; ){
					
					period = period_array[ThreadLocalRandom.current().nextInt(0, period_array.length)];
					
					if(csch[i][period] == null){
						
						csch[i][period] = subjectName;
						
						i++;
					}
					
					///>>> if all the slots are already taken. To break infinite loop.
					trial_counter_5++;
					
					if(trial_counter_5 == 100){
						
						trial_counter_5 = 0;
						break;
					}
				}
			}
			
			///>>> it needs random pick up for double periods and week days
			if(period_per_week > 5){
				
				int trial_counter_g5 = 0;
				///>>> assign subjects for each days
				for(int i = 0; i < day_array.length; ){
					
					period = period_array[ThreadLocalRandom.current().nextInt(0, period_array.length)];
					
					if(csch[i][period] == null){
					
						csch[i][period] = subjectName;
						
						i++;
					}
					
					///>>> if all the slots are already taken. To break infinite loop.
					trial_counter_g5++;
					
					if(trial_counter_g5 == 100){
						
						break;
					}
				}
				
				///>>> assigning double class
				for(int i = 0; i < period_per_week-5; ){
					
					day = day_array[ThreadLocalRandom.current().nextInt(0, day_array.length)];
					period = period_array[ThreadLocalRandom.current().nextInt(0, period_array.length)];
					
					////>>> && (period < 7 && csch[day][period+1] != null && !csch[day][period+1].equals(subjectName)) && (period > 0 && csch[day][period-1] != null && !csch[day][period-1].equals(subjectName))
					if(csch[day][period] == null){
						
						csch[day][period] = subjectName;
						
						i++;
					}
				}				
			}
		}
		
		
		
		for(int i = 0; i < 5; i++){
			
			List<ClassPeriodAssignBean> rslt_inner = new ArrayList<ClassPeriodAssignBean>();
			
			for(int j = 0; j < 7; j++){
				
				ClassPeriodAssignBean rw_content = new ClassPeriodAssignBean();
				
				SubjectBean sbean = new SubjectBean();
				
				sbean.setSub_name(csch[i][j] == null?" ### ":csch[i][j]);
				
				rw_content.setSubjectBean(sbean);
				
				rslt_inner.add(rw_content);
			}
			
			rslt.add(rslt_inner);
		}
		
		return rslt;
	}

}
