package paymentFineClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import util.ConvertNumberToWords;
import util.DateConvertor;
import util.ReturnCurrentEthiopianYear;
import util.TodayDate_YYYYMMDD;
import connectionClass.Connector;

import studentClass.StudentBean;

public class PaymentDAO {
	
	public static List<PaymentBean> monthSelected = new ArrayList<PaymentBean>();
	public static List<PaymentBean> monthSelectedForNormalClass = new ArrayList<PaymentBean>();
	public static List<PaymentBean> monthSelectedForTutorial = new ArrayList<PaymentBean>();
	public static List<PaymentBean> monthSelectedForSummer = new ArrayList<PaymentBean>();
	
	public static List<PaymentBean> getRegFeePaymentTable(List<StudentBean> studList){
		
		List<PaymentBean> pt = new ArrayList<PaymentBean>();
		
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try{
			
			con = Connector.connect();
			
			for(int i = 0; i < studList.size(); i++){
				
				ps = con.prepareStatement(PaymentQueries.getStudentRegFeePayment);
				ps.setString(1, studList.get(i).getSi_id());
				ps.setString(2, ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
				rs = ps.executeQuery();
				
				PaymentBean spt = new PaymentBean();
				
				spt.setSi_id(studList.get(i).getSi_id());
				spt.setFname(studList.get(i).getFname());
				spt.setMname(studList.get(i).getMname());
				spt.setGname(studList.get(i).getGname());
				
				while(rs.next()){
					
					spt.setReg_fee(rs.getString("reg_fee"));
				}
				
				pt.add(spt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception e){
					
				}
			}
		}
		
		return pt;
	}
	
	public static List<PaymentBean> getPaymentTable(List<StudentBean> studList, PaymentBean pb){
		
		//clearing month selected for month than one month payment
		clearSelectedMonth(pb);
				
		List<PaymentBean> pt = new ArrayList<PaymentBean>();
		
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try{
			
			con = Connector.connect();
			for(int i = 0; i < studList.size(); i++){
				
				ps = con.prepareStatement(PaymentQueries.getStudentPayment);
				ps.setString(1, studList.get(i).getSi_id());
				ps.setString(2, ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
				ps.setString(3, studList.get(i).getSi_id());
				ps.setString(4, ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
				rs = ps.executeQuery();
				
				PaymentBean spt = new PaymentBean();
				
				spt.setSi_id(studList.get(i).getSi_id());
				spt.setFname(studList.get(i).getFname());
				spt.setMname(studList.get(i).getMname());
				spt.setGname(studList.get(i).getGname());
				
				while(rs.next()){
					
					int j = Integer.parseInt(rs.getString("month_id"));
					
					if(j == 1){spt.setSep(rs.getString("month_id")); spt.setMonth_full("September");}
					else if(j == 2){spt.setOct(rs.getString("month_id")); spt.setMonth_full("October");}
					else if(j == 3){spt.setNov(rs.getString("month_id")); spt.setMonth_full("November");}
					else if(j == 4){spt.setDec(rs.getString("month_id")); spt.setMonth_full("December");}
					else if(j == 5){spt.setJan(rs.getString("month_id")); spt.setMonth_full("January");}
					else if(j == 6){spt.setFeb(rs.getString("month_id")); spt.setMonth_full("February");}
					else if(j == 7){spt.setMar(rs.getString("month_id")); spt.setMonth_full("March");}
					else if(j == 8){spt.setApr(rs.getString("month_id")); spt.setMonth_full("April");}
					else if(j == 9){spt.setMay(rs.getString("month_id")); spt.setMonth_full("May");}
					else if(j == 10){spt.setJun(rs.getString("month_id")); spt.setMonth_full("June");}
					else if(j == 11){spt.setJul(rs.getString("month_id"));}
					else if(j == 12){spt.setAug(rs.getString("month_id"));}
					
				}
				
//				if(ReturnCurrentEthiopianYear.getCurrentEthiopianYear().equals("2009")){
//					
//					spt.setSep("null".equals(spt.getSep() + "")? "0":spt.getSep());
//					spt.setOct("null".equals(spt.getOct() + "")? "0":spt.getOct());
//					spt.setNov("null".equals(spt.getNov() + "")? "0":spt.getNov());
//					spt.setDec("null".equals(spt.getDec() + "")? "0":spt.getDec());
//					spt.setJan("null".equals(spt.getJan() + "")? "0":spt.getJan());					
//				}
				
				pt.add(spt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception e){
					
				}
			}
		}
		
		return pt;
	}
	
	public static List<PaymentBean> getPaymentTypeList(){		
		List<PaymentBean> ptlist = new ArrayList<PaymentBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(PaymentQueries.getPaymentTypeList);
			rs = ps.executeQuery();
			
			while(rs.next()){				
				PaymentBean ptb = new PaymentBean();
				
				ptb.setPt_id(rs.getString("pt_id"));
				ptb.setPt_name(rs.getString("pt_name"));
				
				ptlist.add(ptb);
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
		
		return ptlist;
	}
	
	public static List<PaymentBean> getPaymentInfoList(){
		
		List<PaymentBean> ptlist = new ArrayList<PaymentBean>();		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(PaymentQueries.getPaymentInfoList);
			rs = ps.executeQuery();
			
			while(rs.next()){				
				PaymentBean ptb = new PaymentBean();
				
				ptb.setPc_id(rs.getString("pc_id"));
				ptb.setClass_id(rs.getString("cl_id"));
				ptb.setClass_name(rs.getString("class_name"));
				ptb.setPt_id(rs.getString("pt_id"));
				ptb.setPt_name(rs.getString("pt_name"));
				ptb.setPay_amount(rs.getString("pay_amount"));
				ptb.setPenality_max_date(rs.getString("penality_max_date"));
				ptb.setPenality_percent(rs.getString("penality_percent"));
				ptb.setAcademic_year(rs.getString("academic_year"));
				ptb.setPc_status(rs.getString("pc_status"));
				
				ptlist.add(ptb);
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
		
		return ptlist;
	}
	
	
	
	public static List<PaymentBean> getPaymentMonth(PaymentBean pyb){
		//clearing month selected for month than one month payment
		clearSelectedMonth(pyb);				
				
		List<PaymentBean> pt = new ArrayList<PaymentBean>();
		
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(PaymentQueries.getPaymentMonth);
			rs = ps.executeQuery();
			
			while(rs.next()){
				PaymentBean pb = new PaymentBean();
				
				pb.setMonth_id(rs.getString("month_id"));
				pb.setMonth_short(rs.getString("short_name"));
				pb.setMonth_full(rs.getString("month"));
				
				pt.add(pb);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception e){
					
				}
			}
		}
		
		return pt;
	}
	
	public static List<PaymentBean> getPaymentAmount(PaymentBean pyb){
		//clearing month selected for month than one month payment
		clearSelectedMonth(pyb);		
		
		//getting academic year --- starting from May to August the academic year consider the next year
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);
		
		//getting Ethiopian current day for penality calculation
		String eth_date = DateConvertor.gregToEthioDateConvertor(TodayDate_YYYYMMDD.getTodayDayMonthYearFormat());
		int ind = 0;
		for(int i = 0; i < eth_date.length(); i++){
			ind = eth_date.indexOf("-");
			break;
		}
		Integer eth_day = Integer.parseInt(eth_date.substring(0, ind));		
		
		//getting the selected month index
		String[] gm = new String[]{"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
		String[] m = new String[]{"sep", "oct", "nov", "dec", "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug"};
		String mth = pyb.getMonth();
				
		String curMth = gm[month];
		
		int selMonInd = 0;
		int curMonInd = 0;
		for(int i = 0; i < m.length; i++){
			if(mth.equalsIgnoreCase(m[i])){
				selMonInd = i;
			}
			if(curMth.equalsIgnoreCase(m[i])){
				curMonInd = i;
			}
		}					
		
		List<PaymentBean> pt = new ArrayList<PaymentBean>();
		
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(PaymentQueries.getPaymentAmount);
			ps.setString(1, pyb.getClass_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				PaymentBean pb = new PaymentBean();
				double payAmount = 0.0;
				double penality = 0.0;
				double totalPayment = 0.0;
				
				pb.setPc_id(rs.getString("pc_id"));
				pb.setPt_id(rs.getString("pt_id"));
				pb.setAcademic_year(rs.getString("academic_year"));
				pb.setPay_amount(rs.getString("pay_amount"));
				
				if(curMonInd < 11 && selMonInd > curMonInd){	
					
					payAmount = Double.parseDouble(pb.getPay_amount());
					pb.setPenality_max_date(rs.getString("penality_max_date"));
					pb.setPenality_percent(rs.getString("penality_percent"));
					
					pb.setPenality_amount(String.valueOf(0.0));
					totalPayment = payAmount + penality;
					pb.setTotal_payment(String.valueOf(totalPayment));
					
				} else if(curMonInd < 11 && selMonInd < curMonInd){
					
					payAmount = Double.parseDouble(pb.getPay_amount());
					penality = (payAmount * Double.parseDouble(rs.getString("penality_percent")))/100;					
					pb.setPenality_amount(String.valueOf(penality));
					totalPayment = payAmount + penality;					
					pb.setTotal_payment(String.valueOf(totalPayment));
					
				} else if(curMonInd < 11 && selMonInd == curMonInd && eth_day > Integer.parseInt(rs.getString("penality_max_date"))){
					
					payAmount = Double.parseDouble(pb.getPay_amount());
					penality = (payAmount * Double.parseDouble(rs.getString("penality_percent")))/100;	
					
					pb.setPenality_amount(String.valueOf(penality));
					totalPayment = payAmount + penality;					
					pb.setTotal_payment(String.valueOf(totalPayment));
					
				} else if(curMonInd < 11 && selMonInd == curMonInd && eth_day <= Integer.parseInt(rs.getString("penality_max_date"))){
					
					payAmount = Double.parseDouble(pb.getPay_amount());
					pb.setPenality_amount(String.valueOf(penality));
					totalPayment = payAmount + penality;					
					pb.setTotal_payment(String.valueOf(totalPayment));
					
				} else {
					
					payAmount = Double.parseDouble(pb.getPay_amount());
					pb.setPenality_max_date(rs.getString("penality_max_date"));
					pb.setPenality_percent(rs.getString("penality_percent"));
					
					pb.setPenality_amount(String.valueOf(0.0));
					totalPayment = payAmount + penality;
					pb.setTotal_payment(String.valueOf(totalPayment));
				}
				
				pt.add(pb);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception e){
					
				}
			}
		}
		
		return pt;
	}
	
	public static List<PaymentBean> getSpecialNeedPaymentAmount(PaymentBean pyb){
		//clearing month selected for month than one month payment
		clearSelectedMonth(pyb);		
		
		//getting academic year --- starting from May to August the academic year consider the next year
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);
		
		//getting Ethiopian current day for penality calculation
		String eth_date = DateConvertor.gregToEthioDateConvertor(day+"-"+month+"-"+year);
		int ind = 0;
		for(int i = 0; i < eth_date.length(); i++){
			ind = eth_date.indexOf("-");
			break;
		}
		Integer eth_day = Integer.parseInt(eth_date.substring(0, ind));		
		
		//getting the selected month index
		String[] gm = new String[]{"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
		String[] m = new String[]{"sep", "oct", "nov", "dec", "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug"};
		String mth = pyb.getMonth();
				
		String curMth = gm[month];
		
		int selMonInd = 0;
		int curMonInd = 0;
		for(int i = 0; i < m.length; i++){
			if(mth.equalsIgnoreCase(m[i])){
				selMonInd = i;
			}
			if(curMth.equalsIgnoreCase(m[i])){
				curMonInd = i;
			}
		}					
		
		List<PaymentBean> pt = new ArrayList<PaymentBean>();
		
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(PaymentQueries.getSpecialNeedPaymentAmount);
			ps.setString(1, pyb.getSi_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				PaymentBean pb = new PaymentBean();
				double payAmount = 0.0;
				double penality = 0.0;
				double totalPayment = 0.0;
				
				pb.setPc_id(rs.getString("pc_id"));
				pb.setSnc_id(rs.getString("snc_id"));
				pb.setAcademic_year(rs.getString("academic_year"));
				pb.setPay_amount(rs.getString("pay_amount"));
				
				if(curMonInd < 11 && selMonInd > curMonInd){	
					
					payAmount = Double.parseDouble(pb.getPay_amount());
					pb.setPenality_max_date(rs.getString("penality_max_date"));
					pb.setPenality_percent(rs.getString("penality_percent"));
					pb.setPenality_amount(String.valueOf(0.0));
					totalPayment = payAmount + penality;
					pb.setTotal_payment(String.valueOf(totalPayment));
					
				}else if(curMonInd < 11 && selMonInd < curMonInd){
					
					payAmount = Double.parseDouble(pb.getPay_amount());
					penality = (payAmount * Double.parseDouble(rs.getString("penality_percent")))/100;					
					pb.setPenality_amount(String.valueOf(penality));
					totalPayment = payAmount + penality;					
					pb.setTotal_payment(String.valueOf(totalPayment));
					
				} else if(curMonInd < 11 && selMonInd == curMonInd && eth_day > Integer.parseInt(rs.getString("penality_max_date"))){
					
					payAmount = Double.parseDouble(pb.getPay_amount());
					penality = (payAmount * Double.parseDouble(rs.getString("penality_percent")))/100;	
					
					pb.setPenality_amount(String.valueOf(penality));
					totalPayment = payAmount + penality;					
					pb.setTotal_payment(String.valueOf(totalPayment));
					
				} else if(curMonInd < 11 && selMonInd == curMonInd && eth_day <= Integer.parseInt(rs.getString("penality_max_date"))){
					
					payAmount = Double.parseDouble(pb.getPay_amount());
					pb.setPenality_amount(String.valueOf(penality));
					totalPayment = payAmount + penality;					
					pb.setTotal_payment(String.valueOf(totalPayment));
					
				} else {
					
					payAmount = Double.parseDouble(pb.getPay_amount());
					pb.setPenality_max_date(rs.getString("penality_max_date"));
					pb.setPenality_percent(rs.getString("penality_percent"));
					pb.setPenality_amount(String.valueOf(0.0));
					totalPayment = payAmount + penality;
					pb.setTotal_payment(String.valueOf(totalPayment));
				}
				
				pt.add(pb);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception e){
					
				}
			}
		}
		
		return pt;
	}
	
	public static boolean saveMonthlyPayment(PaymentBean pb, String loggedInUser){
		
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		String today = TodayDate_YYYYMMDD.getToday();
		
		try{
			
			con = Connector.connect();
			
			String snc_value = ""+pb.getSnc_id();
			System.out.println(snc_value + " " + pb.getSnc_id());
			if(snc_value.equals("null") || snc_value.length() == 0){
				
				ps = con.prepareStatement(PaymentQueries.saveMonthlyPayment);
				ps.setString(1, pb.getSi_id());
				ps.setString(2, pb.getPc_id());
				ps.setString(3, pb.getPenality_amount());
				ps.setString(4, pb.getTotal_payment());
				ps.setString(5, today);
				
				ps.setInt(6, pb.getFm_receipt_no());
				ps.setString(7, pb.getCust_vat_reg_no());
				ps.setInt(8, pb.getCust_tin());
				ps.setString(9, pb.getPayment_mode());
				ps.setString(10, pb.getCheque_no());
				
				ps.setString(11, pb.getMonth_id());
				ps.setString(12, ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
				ps.setString(13, loggedInUser);
				ps.setString(14, TodayDate_YYYYMMDD.getToday());
				
				//ps.setString(15, "");
				
			} else if(!snc_value.equals("null") || snc_value.length() != 0){
				
				ps = con.prepareStatement(PaymentQueries.saveMonthlySpecialPayment);
				ps.setString(1, pb.getSi_id());
				ps.setString(2, pb.getSnc_id());
				ps.setString(3, pb.getPenality_amount());
				ps.setString(4, pb.getTotal_payment());
				ps.setString(5, today);
				
				ps.setInt(6, pb.getFm_receipt_no());
				ps.setString(7, pb.getCust_vat_reg_no());
				ps.setInt(8, pb.getCust_tin());
				ps.setString(9, pb.getPayment_mode());
				ps.setString(10, pb.getCheque_no());
				
				ps.setString(11, pb.getMonth_id());
				ps.setString(12, ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
				ps.setString(13, loggedInUser);
				ps.setString(14, TodayDate_YYYYMMDD.getToday());
				
				ps.setString(15, pb.getPc_id());
			}
			rs = ps.executeUpdate();
			
			if(rs > 0){
				rslt = true;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			clearSelectedMonth(pb);
			
			if(con != null){
				try{
					con.close();
				} catch(Exception e){
					
				}
			}
		}
		
		return rslt;		
	}
	
	public static boolean saveRegistrationPayment(PaymentBean pb, String loggedInUser){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		String today = TodayDate_YYYYMMDD.getToday();
		
		try{
			
			con = Connector.connect();
			
			ps = con.prepareStatement(PaymentQueries.saveRegistrationFee);
			ps.setString(1, pb.getSi_id());
			ps.setString(2, pb.getPc_id());
			
			ps.setString(3, pb.getTotal_payment());
			ps.setString(4, today);
			
			ps.setInt(5, pb.getFm_receipt_no());
			ps.setString(6, pb.getCust_vat_reg_no());
			ps.setInt(7, pb.getCust_tin());
			ps.setString(8, pb.getPayment_mode());
			ps.setString(9, pb.getCheque_no());
			
			ps.setString(10, ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			ps.setString(11, loggedInUser);
			ps.setString(12, TodayDate_YYYYMMDD.getToday());
						
			rs = ps.executeUpdate();
			
			if(rs > 0){
				
				if(pb.getOther_payment().length() == 0){
					
					rslt = true;
				} else {

					ps = con.prepareStatement(PaymentQueries.saveOtherFee);
					ps.setString(1, pb.getSi_id());
					
					ps.setString(2, pb.getOther_payment());
					ps.setString(3, today);
					
					ps.setInt(4, pb.getFm_receipt_no());
					ps.setString(5, pb.getCust_vat_reg_no());
					ps.setInt(6, pb.getCust_tin());
					ps.setString(7, pb.getPayment_mode());
					ps.setString(8, pb.getCheque_no());
					
					ps.setString(9, ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
					ps.setString(10, loggedInUser);
					ps.setString(11, TodayDate_YYYYMMDD.getToday());
								
					rs = ps.executeUpdate();
					
					if(rs > 0){
						rslt = true;
					}
				}
			}
		
		} catch (Exception e) {
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
	
	public static List<PaymentBean> collectMonthSelectedForNormalClassPayment(PaymentBean pb){	
		int checker = 0;
		for(int i = 0; i < monthSelectedForNormalClass.size(); i++){
			if(Integer.parseInt(pb.getMonth_id())== Integer.parseInt(monthSelectedForNormalClass.get(i).getMonth_id())){
				checker++;
			}
		}
		if(checker == 0){
			PaymentBean pyb = new PaymentBean();
			pyb.setMonth_id(pb.getMonth_id());
			pyb.setMonth_short(pb.getMonth_short());
			pyb.setMonth_full(pb.getMonth_full());
			pyb.setPayment_type(pb.getPayment_type());
			monthSelectedForNormalClass.add(pyb);			
		}
		
		return monthSelectedForNormalClass;
	}
	
	public static List<PaymentBean> collectMonthSelectedForTutorialPayment(PaymentBean pb){	
		int checker = 0;
		for(int i = 0; i < monthSelectedForTutorial.size(); i++){
			if(Integer.parseInt(pb.getMonth_id())== Integer.parseInt(monthSelectedForTutorial.get(i).getMonth_id())){
				checker++;
			}
		}
		if(checker == 0){
			PaymentBean pyb = new PaymentBean();
			pyb.setMonth_id(pb.getMonth_id());
			pyb.setMonth_short(pb.getMonth_short());
			pyb.setMonth_full(pb.getMonth_full());
			pyb.setPayment_type(pb.getPayment_type());
			monthSelectedForTutorial.add(pyb);			
		}
		
		return monthSelectedForTutorial;
	}
	
	public static List<PaymentBean> collectMonthSelectedForSummerPayment(PaymentBean pb){	
		int checker = 0;
		for(int i = 0; i < monthSelectedForSummer.size(); i++){
			if(Integer.parseInt(pb.getMonth_id())== Integer.parseInt(monthSelectedForSummer.get(i).getMonth_id())){
				checker++;
			}
		}
		if(checker == 0){
			PaymentBean pyb = new PaymentBean();
			pyb.setMonth_id(pb.getMonth_id());
			pyb.setMonth_short(pb.getMonth_short());
			pyb.setMonth_full(pb.getMonth_full());
			pyb.setPayment_type(pb.getPayment_type());
			monthSelectedForSummer.add(pyb);			
		}
		
		return monthSelectedForSummer;
	}
	
	public static void deleteMonthSelectedForPayment(PaymentBean pb){
		clearSelectedMonth(pb);		
	}
	
	public static List<PaymentBean> getTutorialPaymentTable(List<StudentBean> studList){
		List<PaymentBean> pt = new ArrayList<PaymentBean>();
		
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try{
			
			con = Connector.connect();
			for(int i = 0; i < studList.size(); i++){
				ps = con.prepareStatement(PaymentQueries.getStudentTutorialPayment);
				ps.setString(1, studList.get(i).getSi_id());
				ps.setString(2, ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
				rs = ps.executeQuery();
				
				PaymentBean spt = new PaymentBean();
				
				spt.setSi_id(studList.get(i).getSi_id());
				spt.setFname(studList.get(i).getFname());
				spt.setMname(studList.get(i).getMname());
				spt.setGname(studList.get(i).getGname());
				
				while(rs.next()){
												
					int j = Integer.parseInt(rs.getString("month_id"));
						
					if(j == 1){spt.setSep(rs.getString("month_id"));}
					else if(j == 2){spt.setOct(rs.getString("month_id"));}
					else if(j == 3){spt.setNov(rs.getString("month_id"));}
					else if(j == 4){spt.setDec(rs.getString("month_id"));}
					else if(j == 5){spt.setJan(rs.getString("month_id"));}
					else if(j == 6){spt.setFeb(rs.getString("month_id"));}
					else if(j == 7){spt.setMar(rs.getString("month_id"));}
					else if(j == 8){spt.setApr(rs.getString("month_id"));}
					else if(j == 9){spt.setMay(rs.getString("month_id"));}
					else if(j == 10){spt.setJun(rs.getString("month_id"));}
					else if(j == 11){spt.setJul(rs.getString("month_id"));}
					else if(j == 12){spt.setAug(rs.getString("month_id"));}
				}
				
				pt.add(spt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception e){
					
				}
			}
		}
		
		return pt;
	}
	
	public static List<PaymentBean> getTutorialPaymentAmount(PaymentBean pyb){
		
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);
				
		//getting the selected month index
		String[] gm = new String[]{"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
		String[] m = new String[]{"sep", "oct", "nov", "dec", "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug"};
		String mth = pyb.getMonth();
				
		String curMth = gm[month];
		
		int selMonInd = 0;
		int curMonInd = 0;
		for(int i = 0; i < m.length; i++){
			if(mth.equalsIgnoreCase(m[i])){
				selMonInd = i;
			}
			if(curMth.equalsIgnoreCase(m[i])){
				curMonInd = i;
			}
		}
		
		//getting Ethiopian current day for penality calculation
		String eth_date = DateConvertor.gregToEthioDateConvertor(day+"-"+month+"-"+year);
		int ind = 0;
		for(int i = 0; i < eth_date.length(); i++){
			ind = eth_date.indexOf("-");
			break;
		}
		Integer eth_day = Integer.parseInt(eth_date.substring(0, ind));
		
		List<PaymentBean> pt = new ArrayList<PaymentBean>();
		
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try{
			con = Connector.connect();
			ps = con.prepareStatement(PaymentQueries.getTutorialPaymentAmount);
			ps.setString(1, pyb.getClass_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				PaymentBean pb = new PaymentBean();
				double payAmount = 0.0;
				double penality = 0.0;
				double totalPayment = 0.0;
				
				pb.setPc_id(rs.getString("pc_id"));
				pb.setPt_id(rs.getString("pt_id"));
				pb.setPay_amount(rs.getString("pay_amount"));
				pb.setPenality_max_date(rs.getString("penality_max_date"));
				pb.setPenality_percent(rs.getString("penality_percent"));
				pb.setAcademic_year(rs.getString("academic_year"));
								
				if(curMonInd < 11 && selMonInd > curMonInd){	
					
					payAmount = Double.parseDouble(pb.getPay_amount());
					pb.setPenality_max_date(rs.getString("penality_max_date"));
					pb.setPenality_percent(rs.getString("penality_percent"));
					pb.setPenality_amount(String.valueOf(0.0));
					totalPayment = payAmount + penality;
					pb.setTotal_payment(String.valueOf(totalPayment));
					
				}else if(curMonInd < 11 && selMonInd < curMonInd){
					
					payAmount = Double.parseDouble(pb.getPay_amount());
					penality = (payAmount * Double.parseDouble(rs.getString("penality_percent")))/100;					
					pb.setPenality_amount(String.valueOf(penality));
					totalPayment = payAmount + penality;					
					pb.setTotal_payment(String.valueOf(totalPayment));
					
				} else if(curMonInd < 11 && selMonInd == curMonInd && eth_day > Integer.parseInt(rs.getString("penality_max_date"))){
					
					payAmount = Double.parseDouble(pb.getPay_amount());
					penality = (payAmount * Double.parseDouble(rs.getString("penality_percent")))/100;	
					
					pb.setPenality_amount(String.valueOf(penality));
					totalPayment = payAmount + penality;					
					pb.setTotal_payment(String.valueOf(totalPayment));
					
				} else if(curMonInd < 11 && selMonInd == curMonInd && eth_day <= Integer.parseInt(rs.getString("penality_max_date"))){
					
					payAmount = Double.parseDouble(pb.getPay_amount());
					pb.setPenality_amount(String.valueOf(penality));
					totalPayment = payAmount + penality;					
					pb.setTotal_payment(String.valueOf(totalPayment));
					
				} else {
					
					payAmount = Double.parseDouble(pb.getPay_amount());
					pb.setPenality_max_date(rs.getString("penality_max_date"));
					pb.setPenality_percent(rs.getString("penality_percent"));
					pb.setPenality_amount(String.valueOf(0.0));
					totalPayment = payAmount + penality;
					pb.setTotal_payment(String.valueOf(totalPayment));
				}
				pt.add(pb);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception e){
					
				}
			}
		}
		
		return pt;
	}
	
	public static List<PaymentBean> getSummerPaymentTable(List<StudentBean> studList){
		List<PaymentBean> pt = new ArrayList<PaymentBean>();
		
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try{
			
			con = Connector.connect();
			for(int i = 0; i < studList.size(); i++){
				ps = con.prepareStatement(PaymentQueries.getStudentSummerPayment);
				ps.setString(1, studList.get(i).getSi_id());
				ps.setString(2, ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear());
				rs = ps.executeQuery();
				
				PaymentBean spt = new PaymentBean();
				
				spt.setSi_id(studList.get(i).getSi_id());
				spt.setFname(studList.get(i).getFname());
				spt.setMname(studList.get(i).getMname());
				spt.setGname(studList.get(i).getGname());
				
				while(rs.next()){
												
					int j = Integer.parseInt(rs.getString("month_id"));
						
					if(j == 1){spt.setSep(rs.getString("month_id"));}
					else if(j == 2){spt.setOct(rs.getString("month_id"));}
					else if(j == 3){spt.setNov(rs.getString("month_id"));}
					else if(j == 4){spt.setDec(rs.getString("month_id"));}
					else if(j == 5){spt.setJan(rs.getString("month_id"));}
					else if(j == 6){spt.setFeb(rs.getString("month_id"));}
					else if(j == 7){spt.setMar(rs.getString("month_id"));}
					else if(j == 8){spt.setApr(rs.getString("month_id"));}
					else if(j == 9){spt.setMay(rs.getString("month_id"));}
					else if(j == 10){spt.setJun(rs.getString("month_id"));}
					else if(j == 11){spt.setJul(rs.getString("month_id"));}
					else if(j == 12){spt.setAug(rs.getString("month_id"));}
				}
				
				pt.add(spt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception e){
					
				}
			}
		}
		
		return pt;
	}
	
	public static List<PaymentBean> getSummerPaymentAmount(PaymentBean pyb){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);		
			
		//getting the selected month index
		//getting the selected month index
		String[] gm = new String[]{"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
		String[] m = new String[]{"sep", "oct", "nov", "dec", "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug"};
		String mth = pyb.getMonth();
				
		String curMth = gm[month];
		
		int selMonInd = 0;
		int curMonInd = 0;
		for(int i = 0; i < m.length; i++){
			if(mth.equalsIgnoreCase(m[i])){
				selMonInd = i;
			}
			if(curMth.equalsIgnoreCase(m[i])){
				curMonInd = i;
			}
		}
		
		//getting Ethiopian current day for penality calculation
		String eth_date = DateConvertor.gregToEthioDateConvertor(day+"-"+month+"-"+year);
		int ind = 0;
		for(int i = 0; i < eth_date.length(); i++){
			ind = eth_date.indexOf("-");
			break;
		}
		Integer eth_day = Integer.parseInt(eth_date.substring(0, ind));
		
		List<PaymentBean> pt = new ArrayList<PaymentBean>();
		
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try{
			con = Connector.connect();
			ps = con.prepareStatement(PaymentQueries.getSummerPaymentAmount);
			ps.setString(1, pyb.getClass_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				PaymentBean pb = new PaymentBean();
				double payAmount = 0.0;
				double penality = 0.0;
				double totalPayment = 0.0;
				
				pb.setPc_id(rs.getString("pc_id"));
				pb.setPt_id(rs.getString("pt_id"));
				pb.setPay_amount(rs.getString("pay_amount"));
				pb.setPenality_max_date(rs.getString("penality_max_date"));
				pb.setPenality_percent(rs.getString("penality_percent"));
				pb.setAcademic_year(rs.getString("academic_year"));
				
				if(selMonInd > curMonInd){	
					payAmount = Double.parseDouble(pb.getPay_amount());
					pb.setPenality_max_date(rs.getString("penality_max_date"));
					pb.setPenality_percent(rs.getString("penality_percent"));
					pb.setPenality_amount(String.valueOf(0.0));
					totalPayment = payAmount + penality;
					pb.setTotal_payment(String.valueOf(totalPayment));
					
				}else if(selMonInd < curMonInd){
					
					payAmount = Double.parseDouble(pb.getPay_amount());
					penality = (payAmount * Double.parseDouble(rs.getString("penality_percent")))/100;					
					pb.setPenality_amount(String.valueOf(penality));
					totalPayment = payAmount + penality;					
					pb.setTotal_payment(String.valueOf(totalPayment));
					
				} else if(selMonInd == curMonInd && eth_day > Integer.parseInt(rs.getString("penality_max_date"))){
					
					payAmount = Double.parseDouble(pb.getPay_amount());
					penality = (payAmount * Double.parseDouble(rs.getString("penality_percent")))/100;	
					
					pb.setPenality_amount(String.valueOf(penality));
					totalPayment = payAmount + penality;					
					pb.setTotal_payment(String.valueOf(totalPayment));
					
				} else if(selMonInd == curMonInd && eth_day <= Integer.parseInt(rs.getString("penality_max_date"))){
					
					payAmount = Double.parseDouble(pb.getPay_amount());
					pb.setPenality_amount(String.valueOf(penality));
					totalPayment = payAmount + penality;					
					pb.setTotal_payment(String.valueOf(totalPayment));
					
				}
				
				pt.add(pb);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null){
				try{
					con.close();
				} catch(Exception e){
					
				}
			}
		}
		
		return pt;
	}
	
//	public static List<PaymentBean> getMaterialPaymentTable(List<StudentBean> studList, int mat_size){
//		List<PaymentBean> pt = new ArrayList<PaymentBean>();
//		
//		Connection con = null;
//		ResultSet rs = null;
//		PreparedStatement ps = null;
//		
//		try{
//			
//			con = Connector.connect();
//			for(int i = 0; i < studList.size(); i++){
//				ps = con.prepareStatement(PaymentQueries.getStudentMaterialPayment);
//				ps.setString(1, studList.get(i).getSi_id());
//				rs = ps.executeQuery();
//				
//				PaymentBean spt = new PaymentBean();
//				
//				spt.setSi_id(studList.get(i).getSi_id());
//				spt.setFname(studList.get(i).getFname());
//				spt.setMname(studList.get(i).getMname());
//				spt.setGname(studList.get(i).getGname());
//				
//				while(rs.next()){
//					if(mat_size == 1){
//						spt.setMat1(rs.getString("sm_id"));
//						spt.setMat1_id(rs.getString("sm_id"));
//					}
//					
//				}
//				
//				pt.add(spt);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if(con != null){
//				try{
//					con.close();
//				} catch(Exception e){
//					
//				}
//			}
//		}
//		
//		return pt;
//	}
	
	public static List<PaymentBean> getMaterialPaymentAmount(PaymentBean pyb){
		List<PaymentBean> rslt = new ArrayList<PaymentBean>();
		
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try{
			con = Connector.connect();
			ps = con.prepareStatement(PaymentQueries.getSummerPaymentAmount);
			ps.setString(1, pyb.getClass_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				PaymentBean pb = new PaymentBean();
				double payAmount = 0.0;
				double penality = 0.0;
				double totalPayment = 0.0;
				
				pb.setPc_id(rs.getString("pc_id"));
				pb.setPt_id(rs.getString("pt_id"));
				pb.setPay_amount(rs.getString("pay_amount"));
				pb.setPenality_max_date(rs.getString("penality_max_date"));
				pb.setPenality_percent(rs.getString("penality_percent"));
				pb.setAcademic_year(rs.getString("academic_year"));
				
				payAmount = Double.parseDouble(pb.getPay_amount());
				pb.setPenality_amount(String.valueOf(penality));
				totalPayment = payAmount + penality;					
				pb.setTotal_payment(String.valueOf(totalPayment));
				
				rslt.add(pb);
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
	
	public static List<PaymentBean> selectedMaterialList = new ArrayList<PaymentBean>();
	
	public static boolean addSelectedMaterialPayment(PaymentBean pb){
		boolean rslt = false;
		int size = selectedMaterialList.size();
		
		PaymentBean pyb = new PaymentBean();
		
		pyb.setPcm_id(pb.getPcm_id());
		pyb.setPtm_id(pb.getPtm_id());
		pyb.setSi_id(pb.getSi_id());
		pyb.setClass_id(pb.getClass_id());
		pyb.setMaterial_pay_amount(pb.getMaterial_pay_amount());
		pyb.setPtm_name(pb.getPtm_name());
		
		selectedMaterialList.add(pyb);
		
		if(size < selectedMaterialList.size()){
			rslt = true;
		}
		
		return rslt;
	}
	
	public static boolean removeSelectedMaterialPayment(PaymentBean pb){
		boolean rslt = false;
		int size = selectedMaterialList.size();
		
		for(int i = 0; i < selectedMaterialList.size(); i++){
			if(pb.getPcm_id().equals(selectedMaterialList.get(i).getPcm_id()) && pb.getSi_id().equals(selectedMaterialList.get(i).getSi_id())){
				selectedMaterialList.remove(i);
			}
		}
		
		if(size > selectedMaterialList.size()){
			rslt = true;
		}
		
		return rslt;
	}
	
	public static boolean saveSelectedMaterialPayment(PaymentBean pb, String loggedInUser){
		boolean rslt = false;		
				
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0, count = 0;
		
		try{
			con = Connector.connect();
			for(int i = 0; i < selectedMaterialList.size(); i++){
				ps = con.prepareStatement(PaymentQueries.saveSelectedMaterialPayment);
				ps.setString(1, selectedMaterialList.get(i).getSi_id());
				ps.setString(2, selectedMaterialList.get(i).getPcm_id());
				ps.setString(3, selectedMaterialList.get(i).getMaterial_pay_amount());
				ps.setString(4, TodayDate_YYYYMMDD.getToday());
				
				ps.setInt(5, pb.getFm_receipt_no());
				ps.setString(6, pb.getCust_vat_reg_no());
				ps.setInt(7, pb.getCust_tin());
				ps.setString(8, pb.getPayment_mode());
				ps.setString(9, pb.getCheque_no());
				
				ps.setString(10, loggedInUser);
				ps.setString(11, TodayDate_YYYYMMDD.getToday());
				ps.setString(12, ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
				
				rs = ps.executeUpdate();
				
				if(rs > 0){
					count++;
				}
			}
			
			rslt = true;
			
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
	
	public static List<PaymentBean> getSelectedMaterialList(){
		return selectedMaterialList;
	}
	
	public static boolean paymentAdminInfoSave(PaymentBean pb){
		boolean rslt = false;
		
		boolean rslt_validate = validatePaymetnAdminInfo(pb);
		
		if(!rslt_validate){
			
			Connection con = null;
			PreparedStatement ps = null;
			int rs = 0;
			
			try{
				
				con = Connector.connect();
				ps = con.prepareStatement(PaymentQueries.savePaymentAdminInfo);
				ps.setString(1, pb.getClass_id());
				ps.setString(2, pb.getPt_id());
				ps.setString(3, pb.getPay_amount());
				ps.setString(4, pb.getPenality_max_date());
				ps.setString(5, pb.getPenality_percent());
				ps.setString(6, pb.getAcademic_year());
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
					} catch(Exception e){
						
					}
				}
			}
		} 
		
		return rslt;
	}
	
	public static boolean paymentAdminInfoUpdate(PaymentBean pb){
		boolean rslt = false;
		
		boolean rslt_validate = validatePaymetnAdminInfo(pb);
		
		if(!rslt_validate || pb.getPc_status().equalsIgnoreCase("I")){
			
			Connection con = null;
			PreparedStatement ps = null;
			int rs = 0;
			
			try{
				
				con = Connector.connect();
				ps = con.prepareStatement(PaymentQueries.updatePaymentAdminInfo);
				ps.setString(1, pb.getClass_id());
				ps.setString(2, pb.getPt_id());
				ps.setString(3, pb.getPay_amount());
				ps.setString(4, pb.getPenality_max_date());
				ps.setString(5, pb.getPenality_percent());
				ps.setString(6, pb.getAcademic_year());
				ps.setString(7, pb.getPc_status());
				ps.setString(8, pb.getPc_id());
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
					} catch(Exception e){
						
					}
				}
			}
		} 
		
		return rslt;
	}
	
	public static boolean validatePaymetnAdminInfo(PaymentBean pb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int counter = 0;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(PaymentQueries.selectPaymentAdminInfo);
			ps.setString(1, pb.getClass_id());
			ps.setString(2, pb.getPt_id());
			ps.setString(3, pb.getAcademic_year());
			ps.setString(4, pb.getPc_id());
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				counter += 1;
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
				} catch(Exception e){
					
				}
			}
		}
		
		return rslt;
	}
	
	public static List<PaymentBean> payInfoMoreThanAMonth = new ArrayList<PaymentBean>();
	
	public static List<PaymentBean> paymentInfoMoreThanAmonth(PaymentBean pb){
		
		if(pb.getPayment_type().equals("1")){
			
			monthSelected = monthSelectedForNormalClass;
			
		} else if(pb.getPayment_type().equals("2")){
			
			monthSelected = monthSelectedForTutorial;
			
		} else if(pb.getPayment_type().equals("3")){
			
			monthSelected = monthSelectedForSummer;
		}
		
		//getting academic year --- starting from May to August the academic year consider the next year
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
				
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);
				
		//getting the selected month index
		String[] gm = new String[]{"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
		String[] m = new String[]{"sep", "oct", "nov", "dec", "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug"};
		String mth = pb.getMonth();
		String curMth = gm[month];
		int curMonInd = 0;
		for(int i = 0; i < m.length; i++){			
			if(curMth.equalsIgnoreCase(m[i])){
				curMonInd = i;
			}
		}
		
		//getting Ethiopian current day for penality calculation
		String eth_date = DateConvertor.gregToEthioDateConvertor(day+"-"+month+"-"+year);
		int ind = 0;
		for(int i = 0; i < eth_date.length(); i++){
			ind = eth_date.indexOf("-");
			break;
		}
		Integer eth_day = Integer.parseInt(eth_date.substring(0, ind));
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String pType = pb.getPayment_type();
		
		try{
			
			con = Connector.connect();
			if(pType.equalsIgnoreCase("1")){
				
				ps = con.prepareStatement(PaymentQueries.getPaymentAmount);	
				
			} else if(pType.equalsIgnoreCase("2")){
				
				ps = con.prepareStatement(PaymentQueries.getTutorialPaymentAmount);
				
			} else if(pType.equalsIgnoreCase("3")){
				
				ps = con.prepareStatement(PaymentQueries.getSummerPaymentAmount);
			}
			ps.setString(1, pb.getClass_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				for(int i = 0; i < monthSelected.size(); i++){
					
					if(monthSelected.get(i).getPayment_type().equals(pType)){
						
						double payAmount = 0.0;
						double penality = 0.0;
						double totalPayment = 0.0;
						
						PaymentBean pbean = new PaymentBean();
						
						pbean.setMonth_id(monthSelected.get(i).getMonth_id());					
						pbean.setMonth_full(monthSelected.get(i).getMonth_full());
						
						pbean.setSi_id(pb.getSi_id());
						pbean.setPayment_type(pb.getPayment_type());
						pbean.setClass_id(pb.getClass_id());
						
						pbean.setPc_id(pb.getPc_id());
						pbean.setSnc_id(pb.getSnc_id());
						
						pbean.setAcademic_year(rs.getString("academic_year"));
						
						pbean.setPt_id(rs.getString("pt_id"));
						pbean.setAcademic_year(rs.getString("academic_year"));
						pbean.setPay_amount(rs.getString("pay_amount"));
						
						if(curMonInd < 11 && Integer.parseInt(monthSelected.get(i).getMonth_id())-1 > curMonInd){	
							
							payAmount = Double.parseDouble(pbean.getPay_amount());
							pbean.setPenality_max_date(rs.getString("penality_max_date"));
							pbean.setPenality_percent(rs.getString("penality_percent"));
							pbean.setPenality_amount(String.valueOf(0.0));
							totalPayment = payAmount + penality;					
							pbean.setTotal_payment(String.valueOf(totalPayment));
							
						} else if(curMonInd < 11 && Integer.parseInt(monthSelected.get(i).getMonth_id())-1 < curMonInd){
							
							payAmount = Double.parseDouble(pbean.getPay_amount());
							penality = (payAmount * Double.parseDouble(rs.getString("penality_percent")))/100;					
							pbean.setPenality_amount(String.valueOf(penality));
							totalPayment = payAmount + penality;					
							pbean.setTotal_payment(String.valueOf(totalPayment));
							
						} else if(curMonInd < 11 && Integer.parseInt(monthSelected.get(i).getMonth_id())-1 == curMonInd && eth_day > Integer.parseInt(rs.getString("penality_max_date"))){
							
							payAmount = Double.parseDouble(pbean.getPay_amount());
							penality = (payAmount * Double.parseDouble(rs.getString("penality_percent")))/100;					
							pbean.setPenality_amount(String.valueOf(penality));
							totalPayment = payAmount + penality;					
							pbean.setTotal_payment(String.valueOf(totalPayment));
							
						} else if(curMonInd < 11 && Integer.parseInt(monthSelected.get(i).getMonth_id())-1 == curMonInd && eth_day < Integer.parseInt(rs.getString("penality_max_date"))){
							
							payAmount = Double.parseDouble(pbean.getPay_amount());
							//penality = (payAmount * Double.parseDouble(rs.getString("penality_percent")))/100;					
							pbean.setPenality_amount(String.valueOf(penality));
							totalPayment = payAmount + penality;					
							pbean.setTotal_payment(String.valueOf(totalPayment));
							
						} else{
							
							payAmount = Double.parseDouble(pbean.getPay_amount());
							pbean.setPenality_max_date(rs.getString("penality_max_date"));
							pbean.setPenality_percent(rs.getString("penality_percent"));
							pbean.setPenality_amount(String.valueOf(0.0));
							totalPayment = payAmount + penality;					
							pbean.setTotal_payment(String.valueOf(totalPayment));
						}
						
						payInfoMoreThanAMonth.add(pbean);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}	
		
		return payInfoMoreThanAMonth;
	}
	
	public static List<PaymentBean> specialPaymentInfoMoreThanAmonth(PaymentBean pb){
		
		if(pb.getPayment_type().equals("1")){
			
			monthSelected = monthSelectedForNormalClass;
			
		} else if(pb.getPayment_type().equals("2")){
			
			monthSelected = monthSelectedForTutorial;
			
		} else if(pb.getPayment_type().equals("3")){
			
			monthSelected = monthSelectedForSummer;
		}
		
		//getting academic year --- starting from May to August the academic year consider the next year
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
				
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);
				
		//getting the selected month index
		String[] gm = new String[]{"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
		String[] m = new String[]{"sep", "oct", "nov", "dec", "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug"};
		String mth = pb.getMonth();
		String curMth = gm[month];
		int curMonInd = 0;
		for(int i = 0; i < m.length; i++){			
			if(curMth.equalsIgnoreCase(m[i])){
				curMonInd = i;
			}
		}
		
		//getting Ethiopian current day for penality calculation
		String eth_date = DateConvertor.gregToEthioDateConvertor(day+"-"+month+"-"+year);
		int ind = 0;
		for(int i = 0; i < eth_date.length(); i++){
			ind = eth_date.indexOf("-");
			break;
		}
		Integer eth_day = Integer.parseInt(eth_date.substring(0, ind));
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String pType = pb.getPayment_type();
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(PaymentQueries.getSpecialNeedPaymentAmount);			
			ps.setString(1, pb.getSi_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				for(int i = 0; i < monthSelected.size(); i++){
					double payAmount = 0.0;
					double penality = 0.0;
					double totalPayment = 0.0;
					
					PaymentBean pbean = new PaymentBean();
					
					pbean.setMonth_id(monthSelected.get(i).getMonth_id());					
					pbean.setMonth_full(monthSelected.get(i).getMonth_full());
					
					pbean.setSi_id(pb.getSi_id());
					pbean.setPayment_type(pb.getPayment_type());
					pbean.setClass_id(pb.getClass_id());
					pbean.setSnc_id(pb.getSnc_id());
					
					pbean.setPc_id(rs.getString("pc_id"));
					//pbean.setPt_id(rs.getString("pt_id"));
					pbean.setAcademic_year(rs.getString("academic_year"));
					pbean.setPay_amount(rs.getString("pay_amount"));
										
					if(Integer.parseInt(monthSelected.get(i).getMonth_id())-1 > curMonInd){	
						
						payAmount = Double.parseDouble(pbean.getPay_amount());
						pbean.setPenality_max_date(rs.getString("penality_max_date"));
						pbean.setPenality_percent(rs.getString("penality_percent"));
						pbean.setPenality_amount(String.valueOf(0.0));
						totalPayment = payAmount + penality;					
						pbean.setTotal_payment(String.valueOf(totalPayment));
						
					}else if(Integer.parseInt(monthSelected.get(i).getMonth_id())-1 < curMonInd){
						
						payAmount = Double.parseDouble(pbean.getPay_amount());
						penality = (payAmount * Double.parseDouble(rs.getString("penality_percent")))/100;					
						pbean.setPenality_amount(String.valueOf(penality));
						totalPayment = payAmount + penality;					
						pbean.setTotal_payment(String.valueOf(totalPayment));
						
					} else if(Integer.parseInt(monthSelected.get(i).getMonth_id())-1 == curMonInd && eth_day > Integer.parseInt(rs.getString("penality_max_date"))){
						
						payAmount = Double.parseDouble(pbean.getPay_amount());
						penality = (payAmount * Double.parseDouble(rs.getString("penality_percent")))/100;					
						pbean.setPenality_amount(String.valueOf(penality));
						totalPayment = payAmount + penality;					
						pbean.setTotal_payment(String.valueOf(totalPayment));
						
					} else if(Integer.parseInt(monthSelected.get(i).getMonth_id())-1 == curMonInd && eth_day < Integer.parseInt(rs.getString("penality_max_date"))){
						
						payAmount = Double.parseDouble(pbean.getPay_amount());
						//penality = (payAmount * Double.parseDouble(rs.getString("penality_percent")))/100;					
						pbean.setPenality_amount(String.valueOf(penality));
						totalPayment = payAmount + penality;					
						pbean.setTotal_payment(String.valueOf(totalPayment));
						
					}
					
					payInfoMoreThanAMonth.add(pbean);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(con != null){
				try{
					con.close();
				} catch (Exception e) {
					
				}
			}
		}	
		
		return payInfoMoreThanAMonth;
	}
	
	public static boolean paymentInfoMoreThanAmonthSubmit(PaymentBean pb, String loggedInUser){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		int counter = 0;
		
		String today = TodayDate_YYYYMMDD.getToday();
		
		try{
			
			con = Connector.connect();
			
			for(int i = 0; i < payInfoMoreThanAMonth.size(); i++){
				
				ps = con.prepareStatement(PaymentQueries.saveMonthlyPayment);
				ps.setString(1, payInfoMoreThanAMonth.get(i).getSi_id());
				ps.setString(2, payInfoMoreThanAMonth.get(i).getPc_id());
				ps.setString(3, payInfoMoreThanAMonth.get(i).getPenality_amount());
				ps.setString(4, payInfoMoreThanAMonth.get(i).getTotal_payment());
				ps.setString(5, today);
				
				ps.setString(6, pb.getFiscal_machine_no());
				ps.setString(7, pb.getCust_vat_reg_no());
				ps.setInt(8, pb.getCust_tin());
				ps.setString(9, pb.getPayment_mode());
				ps.setString(10, pb.getCheque_no());
				
				ps.setString(11, payInfoMoreThanAMonth.get(i).getMonth_id());
				ps.setString(12, ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
				ps.setString(13, loggedInUser);
				ps.setString(14, TodayDate_YYYYMMDD.getToday());

				//ps.setString(15, payInfoMoreThanAMonth.get(i).getSnc_id());
				
				rs = ps.executeUpdate();
				
				if(rs > 0){
					counter++;
				}
			}
			if(counter == payInfoMoreThanAMonth.size()){
				rslt = true;
			}
		} catch (Exception e) {
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
	
	public static List<PaymentBean> getPaymentReceiptInfoMoreThanAmonth(){
		
		return payInfoMoreThanAMonth;
	}
	
	public static List<PaymentBean> getMaterialList(PaymentBean pyb){
		List<PaymentBean> rslt = new ArrayList<PaymentBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(PaymentQueries.getMaterialList);
			ps.setString(1, pyb.getClass_id());
			ps.setString(2, pyb.getSi_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				PaymentBean pb = new PaymentBean();
				
				pb.setPtm_id(rs.getString("ptm_id"));
				pb.setPtm_name(rs.getString("ptm_name"));
				pb.setMaterial_pay_amount(rs.getString("pay_amount"));
				pb.setPcm_id(rs.getString("pmc_id"));
				
				rslt.add(pb);
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
	
	public static List<PaymentBean> getPaidMaterialList(PaymentBean pyb){
		List<PaymentBean> rslt = new ArrayList<PaymentBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			ps = con.prepareStatement(PaymentQueries.getPaidMaterialList);
			ps.setString(1, pyb.getSi_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				PaymentBean pb = new PaymentBean();
				
				pb.setPtm_id(rs.getString("ptm_id"));
				pb.setPtm_name(rs.getString("ptm_name"));
				pb.setMaterial_pay_amount(rs.getString("pay_amount"));
				pb.setPcm_id(rs.getString("pmc_id"));
				
				rslt.add(pb);
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
	
	public static List<PaymentBean> getMaterialPaymentReceiptInfo(PaymentBean pb){
		
		List<PaymentBean> rslt = new ArrayList<PaymentBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		String school_name = "", slogan = "", tin_num = "", telephone = "", fax = "", email = "", web = "", pobox = "", fiscal_machine_no = "";
		
		try{
			
			con = Connector.connect();
			
			ps2 = con.prepareStatement(PaymentQueries.getSchoolInformation);
			rs2 = ps2.executeQuery();
			
			while(rs2.next()){
				
				school_name = rs2.getString("school_name");
				slogan = rs2.getString("slogan");
				tin_num = rs2.getString("tin_num");
				telephone = rs2.getString("telephone");
				fax = rs2.getString("fax");
				email = rs2.getString("email");
				web = rs2.getString("web");
				pobox = rs2.getString("pobox");
				fiscal_machine_no = rs2.getString("fiscal_machine_no");
				
			}			
			
			ps = con.prepareStatement(PaymentQueries.getReceiptStudentClassInformation);
			ps.setString(1, pb.getSi_id());
			ps.setString(2, pb.getClass_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				PaymentBean pyb = new PaymentBean();
				
				pyb.setSchool_name(school_name);
				pyb.setSchool_slogan(slogan);
				pyb.setTin_num(tin_num);
				pyb.setTelephone(telephone);
				pyb.setFax(fax);
				pyb.setEmail(email);
				pyb.setWeb(web);
				pyb.setPobox(pobox);
				pyb.setFiscal_machine_no(fiscal_machine_no);
				
				pyb.setFname(rs.getString("fname"));
				pyb.setMname(rs.getString("mname"));
				pyb.setGname(rs.getString("gname"));
				pyb.setClass_name(rs.getString("class_name"));
				pyb.setPayment_type(pb.getPayment_type());
				pyb.setPayment_date(TodayDate_YYYYMMDD.getDayMonthYearFormat());
				
				pyb.setFm_receipt_no(pb.getFm_receipt_no());
				pyb.setCust_vat_reg_no(pb.getCust_vat_reg_no());
				pyb.setCust_tin(pb.getCust_tin());
				pyb.setPayment_mode(pb.getPayment_mode());
				pyb.setCheque_no(pb.getCheque_no());
								
				rslt.add(pyb);
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
	
	public static List<PaymentBean> getPaymentReceiptInfo(PaymentBean pb){
		
		List<PaymentBean> rslt = new ArrayList<PaymentBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		
		double amount = 0.0, sub_total = 0.0, vat = 0.0, grand_total = 0.0;
		String month_name = "";
		String school_name = "", slogan = "", tin_num = "", telephone = "", fax = "", email = "", web = "", pobox = "", fiscal_machine_no = "";
		
		try{
			
			con = Connector.connect();
			
			ps2 = con.prepareStatement(PaymentQueries.getSchoolInformation);
			rs2 = ps2.executeQuery();
			
			while(rs2.next()){
				
				school_name = rs2.getString("school_name");
				slogan = rs2.getString("slogan");
				tin_num = rs2.getString("tin_num");
				telephone = rs2.getString("telephone");
				fax = rs2.getString("fax");
				email = rs2.getString("email");
				web = rs2.getString("web");
				pobox = rs2.getString("pobox");
				fiscal_machine_no = rs2.getString("fiscal_machine_no");
				
			}
			
			ps1 = con.prepareStatement(PaymentQueries.getSelectedMonth);
			ps1.setString(1, pb.getMonth_id());
			rs1 = ps1.executeQuery();
			
			while(rs1.next()){
				month_name = rs1.getString("month");
			}
			
			ps = con.prepareStatement(PaymentQueries.getReceiptStudentClassInformation);
			ps.setString(1, pb.getSi_id());
			ps.setString(2, pb.getClass_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				PaymentBean pyb = new PaymentBean();
				
				pyb.setSchool_name(school_name);
				pyb.setSchool_slogan(slogan);
				pyb.setTin_num(tin_num);
				pyb.setTelephone(telephone);
				pyb.setFax(fax);
				pyb.setEmail(email);
				pyb.setWeb(web);
				pyb.setPobox(pobox);
				pyb.setFiscal_machine_no(fiscal_machine_no);
				
				pyb.setFname(rs.getString("fname"));
				pyb.setMname(rs.getString("mname"));
				pyb.setGname(rs.getString("gname"));
				pyb.setClass_name(rs.getString("class_name"));
				pyb.setPayment_type(pb.getPayment_type());
				pyb.setPayment_date(TodayDate_YYYYMMDD.getDayMonthYearFormat());
				
				pyb.setMonth(month_name);
				
				amount = Double.parseDouble(pb.getTotal_payment())- Double.parseDouble(pb.getPenality_amount());
				
				pyb.setPay_amount(String.valueOf(amount));
				pyb.setPenality_amount(pb.getPenality_amount());
				pyb.setTotal_payment(pb.getTotal_payment());				
				
				sub_total = amount;
				pyb.setPayment_sub_total(String.valueOf(sub_total));
				
//				vat = Double.parseDouble(pb.getTotal_payment()) * 0.15;
//				pyb.setVat(String.valueOf(vat));
				
				grand_total = sub_total;
				pyb.setPayment_grand_total(String.valueOf(grand_total));
				
				pyb.setPayment_grand_total_in_word(ConvertNumberToWords.convertedNumberToWord(String.valueOf(grand_total)));
				
				pyb.setFm_receipt_no(pb.getFm_receipt_no());
				pyb.setCust_vat_reg_no(pb.getCust_vat_reg_no());
				pyb.setCust_tin(pb.getCust_tin());
				pyb.setPayment_mode(pb.getPayment_mode());
				pyb.setCheque_no(pb.getCheque_no());
				
				rslt.add(pyb);
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
	
	public static List<PaymentBean> getRegistrationPaymentReceiptInfo(PaymentBean pb){
		
		List<PaymentBean> rslt = new ArrayList<PaymentBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		double grand_total = 0.0;
		String school_name = "", slogan = "", tin_num = "", telephone = "", fax = "", email = "", web = "", pobox = "", fiscal_machine_no = "";
		
		try{
			
			con = Connector.connect();
			
			ps2 = con.prepareStatement(PaymentQueries.getSchoolInformation);
			rs2 = ps2.executeQuery();
			
			while(rs2.next()){
				
				school_name = rs2.getString("school_name");
				slogan = rs2.getString("slogan");
				tin_num = rs2.getString("tin_num");
				telephone = rs2.getString("telephone");
				fax = rs2.getString("fax");
				email = rs2.getString("email");
				web = rs2.getString("web");
				pobox = rs2.getString("pobox");
				fiscal_machine_no = rs2.getString("fiscal_machine_no");				
			}
						
			ps = con.prepareStatement(PaymentQueries.getReceiptStudentClassInformation);
			ps.setString(1, pb.getSi_id());
			ps.setString(2, pb.getClass_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				PaymentBean pyb = new PaymentBean();
				
				pyb.setSchool_name(school_name);
				pyb.setSchool_slogan(slogan);
				pyb.setTin_num(tin_num);
				pyb.setTelephone(telephone);
				pyb.setFax(fax);
				pyb.setEmail(email);
				pyb.setWeb(web);
				pyb.setPobox(pobox);
				pyb.setFiscal_machine_no(fiscal_machine_no);
				
				pyb.setFname(rs.getString("fname"));
				pyb.setMname(rs.getString("mname"));
				pyb.setGname(rs.getString("gname"));
				pyb.setClass_name(rs.getString("class_name"));
				pyb.setPayment_type(pb.getPayment_type());
				pyb.setPayment_date(TodayDate_YYYYMMDD.getDayMonthYearFormat());
				
				pyb.setPay_amount(pb.getTotal_payment());				
				pyb.setTotal_payment(pb.getTotal_payment());
				pyb.setOther_payment(pb.getOther_payment());
				
				if(pb.getOther_payment().length() == 0){
					grand_total = Double.parseDouble(pb.getTotal_payment());
					pyb.setOther_payment("0.00");
				} else {
					grand_total = Double.parseDouble(pb.getTotal_payment()) + Double.parseDouble(pb.getOther_payment());
				}
				
				pyb.setPayment_grand_total(String.valueOf(grand_total));
				
				pyb.setPayment_grand_total_in_word(ConvertNumberToWords.convertedNumberToWord(String.valueOf(grand_total)));
				
				pyb.setFm_receipt_no(pb.getFm_receipt_no());
				pyb.setCust_vat_reg_no(pb.getCust_vat_reg_no());
				pyb.setCust_tin(pb.getCust_tin());
				pyb.setPayment_mode(pb.getPayment_mode());
				pyb.setCheque_no(pb.getCheque_no());
				
				rslt.add(pyb);
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

	public static List<PaymentBean> getPaidRegistrationFeeReceiptInfo(PaymentBean pb){
		
		List<PaymentBean> rslt = new ArrayList<PaymentBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		double grand_total = 0.0;
		String school_name = "", slogan = "", tin_num = "", telephone = "", fax = "", email = "", web = "", pobox = "", fiscal_machine_no = "";
		
		try{
			
			con = Connector.connect();
			
			ps2 = con.prepareStatement(PaymentQueries.getSchoolInformation);
			rs2 = ps2.executeQuery();
			
			while(rs2.next()){
				
				school_name = rs2.getString("school_name");
				slogan = rs2.getString("slogan");
				tin_num = rs2.getString("tin_num");
				telephone = rs2.getString("telephone");
				fax = rs2.getString("fax");
				email = rs2.getString("email");
				web = rs2.getString("web");
				pobox = rs2.getString("pobox");
				fiscal_machine_no = rs2.getString("fiscal_machine_no");				
			}
			
			ps = con.prepareStatement(PaymentQueries.getPaidRegistrationFeeReceiptInfo);
			ps.setString(1, pb.getSi_id());
			ps.setString(2, pb.getClass_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				PaymentBean pyb = new PaymentBean();
				
				pyb.setSchool_name(school_name);
				pyb.setSchool_slogan(slogan);
				pyb.setTin_num(tin_num);
				pyb.setTelephone(telephone);
				pyb.setFax(fax);
				pyb.setEmail(email);
				pyb.setWeb(web);
				pyb.setPobox(pobox);
				pyb.setFiscal_machine_no(fiscal_machine_no);
				
				pyb.setFname(rs.getString("fname"));
				pyb.setMname(rs.getString("mname"));
				pyb.setGname(rs.getString("gname"));
				pyb.setClass_name(rs.getString("class_name"));
				
				pyb.setPayment_date(TodayDate_YYYYMMDD.getDayMonthYearFormat());
				
				pyb.setPay_amount(rs.getString("total_payment"));				
				pyb.setTotal_payment(rs.getString("total_payment"));
				
				grand_total = grand_total + Double.parseDouble(rs.getString("total_payment"));				
				
				pyb.setPayment_grand_total(String.valueOf(grand_total));
				
				pyb.setPayment_grand_total_in_word(ConvertNumberToWords.convertedNumberToWord(String.valueOf(grand_total)));
				
				pyb.setFm_receipt_no(rs.getInt("fm_receipt_no"));
				pyb.setCust_vat_reg_no(rs.getString("cust_vat_reg_no"));
				pyb.setCust_tin(rs.getInt("cust_tin"));
				pyb.setPayment_mode(rs.getString("payment_mode"));
				pyb.setCheque_no(rs.getString("cheque_no"));
				
				rslt.add(pyb);
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
	
	public static List<PaymentBean> getPaidReceiptInfo(PaymentBean pb){
		
		List<PaymentBean> rslt = new ArrayList<PaymentBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		
		double amount = 0.0, sub_total = 0.0, vat = 0.0, grand_total = 0.0;
		String month_name = "";
		String school_name = "", slogan = "", tin_num = "", telephone = "", fax = "", email = "", web = "", pobox = "", fiscal_machine_no = "", fm_receipt_no = "";
		
		try{
			
			con = Connector.connect();
			
			ps2 = con.prepareStatement(PaymentQueries.getSchoolInformation);
			rs2 = ps2.executeQuery();
			
			while(rs2.next()){
				
				school_name = rs2.getString("school_name");
				slogan = rs2.getString("slogan");
				tin_num = rs2.getString("tin_num");
				telephone = rs2.getString("telephone");
				fax = rs2.getString("fax");
				email = rs2.getString("email");
				web = rs2.getString("web");
				pobox = rs2.getString("pobox");
				fiscal_machine_no = rs2.getString("fiscal_machine_no");
				
			}
			
			ps1 = con.prepareStatement(PaymentQueries.getFMReceiptNo);			
			ps1.setString(1, pb.getSi_id());
			ps1.setString(2, pb.getMonth_id());
			rs1 = ps1.executeQuery();
			
			while(rs1.next()){
				fm_receipt_no = rs1.getString("fm_receipt_no");
			}
			
			if(fm_receipt_no.length() == 0){
				
				fm_receipt_no = pb.getFiscal_machine_no();
			}
			
			ps = con.prepareStatement(PaymentQueries.getPaidReceiptStudentClassInformation);
			ps.setString(1, pb.getSi_id());
			ps.setString(2, pb.getClass_id());
			ps.setString(3, fm_receipt_no);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				PaymentBean pyb = new PaymentBean();
				
				pyb.setSchool_name(school_name);
				pyb.setSchool_slogan(slogan);
				pyb.setTin_num(tin_num);
				pyb.setTelephone(telephone);
				pyb.setFax(fax);
				pyb.setEmail(email);
				pyb.setWeb(web);
				pyb.setPobox(pobox);
				pyb.setFiscal_machine_no(fiscal_machine_no);
				
				pyb.setFname(rs.getString("fname"));
				pyb.setMname(rs.getString("mname"));
				pyb.setGname(rs.getString("gname"));
				pyb.setClass_name(rs.getString("class_name"));
				pyb.setPayment_type(rs.getString("pt_id"));
				pyb.setPayment_date(rs.getString("payment_date"));
				
				if(rs.getString("month_id")== null && rs.getString("pc_id") != null){
					pyb.setMonth_full("Registration Fee");
				} else if(rs.getString("month_id") == null && rs.getString("pc_id") == null && rs.getString("ptm_id") == null){
					pyb.setMonth_full("Other Fee");
				} else if(rs.getString("month_id") == null && rs.getString("ptm_id") != null){
					pyb.setMonth_full(rs.getString("ptm_name"));
				} else {
					pyb.setMonth_full(rs.getString("pt_name") + " - " + rs.getString("month"));
				}
				
				String penality = "" + rs.getString("penality_amount");
				if(rs.getString("penality_amount") != null && penality.length() > 0){
					amount = Double.parseDouble(rs.getString("total_payment"))- Double.parseDouble(rs.getString("penality_amount"));
				} else {
					amount = Double.parseDouble(rs.getString("total_payment"));
				}
				
				pyb.setPay_amount(String.valueOf(amount));
				pyb.setPenality_amount(rs.getString("penality_amount"));
				pyb.setTotal_payment(String.valueOf(amount));				
				
				sub_total = amount;
				pyb.setPayment_sub_total(String.valueOf(sub_total));
				
//				vat = Double.parseDouble(pb.getTotal_payment()) * 0.15;
//				pyb.setVat(String.valueOf(vat));
				
				grand_total = sub_total;
				pyb.setPayment_grand_total(String.valueOf(grand_total));
				
				pyb.setPayment_grand_total_in_word(ConvertNumberToWords.convertedNumberToWord(String.valueOf(grand_total)));
				
				pyb.setFm_receipt_no(Integer.parseInt(rs.getString("fm_receipt_no")));
				pyb.setCust_vat_reg_no(rs.getString("cust_vat_reg_no"));
				pyb.setCust_tin(Integer.parseInt(rs.getString("cust_tin")));
				pyb.setPayment_mode(rs.getString("payment_mode"));
				pyb.setCheque_no(rs.getString("cheque_no"));
				
				rslt.add(pyb);
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
	
	public static List<PaymentBean> getPaymentReceiptInfoMoreMonths(PaymentBean pb){
		
		List<PaymentBean> rslt = new ArrayList<PaymentBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		String school_name = "", slogan = "", tin_num = "", telephone = "", fax = "", email = "", web = "", pobox = "", fiscal_machine_no = "";
		
		try{
			
			con = Connector.connect();
			
			ps2 = con.prepareStatement(PaymentQueries.getSchoolInformation);
			rs2 = ps2.executeQuery();
			
			while(rs2.next()){
				
				school_name = rs2.getString("school_name");
				slogan = rs2.getString("slogan");
				tin_num = rs2.getString("tin_num");
				telephone = rs2.getString("telephone");
				fax = rs2.getString("fax");
				email = rs2.getString("email");
				web = rs2.getString("web");
				pobox = rs2.getString("pobox");
				fiscal_machine_no = rs2.getString("fiscal_machine_no");
				
			}
			
			
			
			ps = con.prepareStatement(PaymentQueries.getReceiptStudentClassInformation);
			ps.setString(1, pb.getSi_id());
			ps.setString(2, pb.getClass_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				PaymentBean pyb = new PaymentBean();
				
				pyb.setSchool_name(school_name);
				pyb.setSchool_slogan(slogan);
				pyb.setTin_num(tin_num);
				pyb.setTelephone(telephone);
				pyb.setFax(fax);
				pyb.setEmail(email);
				pyb.setWeb(web);
				pyb.setPobox(pobox);
				pyb.setFiscal_machine_no(fiscal_machine_no);
				
				pyb.setFname(rs.getString("fname"));
				pyb.setMname(rs.getString("mname"));
				pyb.setGname(rs.getString("gname"));
				pyb.setClass_name(rs.getString("class_name"));
				pyb.setPayment_type(pb.getPayment_type());
				pyb.setPayment_date(TodayDate_YYYYMMDD.getDayMonthYearFormat());
				
				pyb.setFm_receipt_no(pb.getFm_receipt_no());
				pyb.setCust_vat_reg_no(pb.getCust_vat_reg_no());
				pyb.setCust_tin(pb.getCust_tin());
				pyb.setPayment_mode(pb.getPayment_mode());
				pyb.setCheque_no(pb.getCheque_no());
				
				rslt.add(pyb);
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
	
	public static List<PaymentBean> getSchoolInformation(){
		List<PaymentBean> rslt = new ArrayList<PaymentBean>();
		
		Connection con = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		
		try{
			
			con = Connector.connect();
			
			ps2 = con.prepareStatement(PaymentQueries.getSchoolInformation);
			rs2 = ps2.executeQuery();
			
			while(rs2.next()){
				
				PaymentBean pyb = new PaymentBean();
				
				pyb.setSchool_name(rs2.getString("school_name"));
				pyb.setSchool_slogan(rs2.getString("slogan"));
				pyb.setTin_num(rs2.getString("tin_num"));
				pyb.setTelephone(rs2.getString("telephone"));
				pyb.setFax(rs2.getString("fax"));
				pyb.setEmail(rs2.getString("email"));
				pyb.setWeb(rs2.getString("web"));
				pyb.setPobox(rs2.getString("pobox"));
				pyb.setFiscal_machine_no(rs2.getString("fiscal_machine_no"));
				
				rslt.add(pyb);
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
	
	public static boolean insertSchoolInformation(PaymentBean pb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			
			ps = con.prepareStatement(PaymentQueries.insertSchoolInformation);
			ps.setString(1, pb.getSchool_name());
			ps.setString(2, pb.getSchool_slogan());
			ps.setString(3, pb.getTin_num());
			ps.setString(4, pb.getTelephone());
			ps.setString(5, pb.getFax());
			ps.setString(6, pb.getWeb());
			ps.setString(7, pb.getEmail());			
			ps.setString(8, pb.getPobox());
			ps.setString(9, pb.getFiscal_machine_no());
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
	
	public static boolean saveSchoolInformation(PaymentBean pb){
		boolean rslt = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		int rs = 0;
		
		try{
			
			con = Connector.connect();
			
			ps = con.prepareStatement(PaymentQueries.saveSchoolInformation);
			ps.setString(1, pb.getSchool_slogan());
			ps.setString(2, pb.getTelephone());
			ps.setString(3, pb.getFax());
			ps.setString(4, pb.getWeb());
			ps.setString(5, pb.getEmail());
			ps.setString(6, pb.getPobox());
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
	
	public static void clearSelectedPaidMaterialList(){
		selectedMaterialList.clear();
	}
	
	///*** clear the selected month ***///
	
	public static void clearSelectedMonth(PaymentBean pb){
		
		if(pb.getPayment_type().equals("1")){
			
			monthSelectedForNormalClass.clear();
			
		} else if(pb.getPayment_type().equals("2")){
			
			monthSelectedForTutorial.clear();
			
		} else if(pb.getPayment_type().equals("3")){
			
			monthSelectedForSummer.clear();
		}
		
		//monthSelected.clear();
		for(int i = 0; i < monthSelected.size(); i++){
			
			if(pb.getPayment_type().equals(monthSelected.get(i).getPayment_type())){
				
				monthSelected.remove(i);
			}
		}
		
		payInfoMoreThanAMonth.clear();
	}
	
	public static int getLastFMReceiptNo(){
		int fm_receipt_no = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			
			ps = con.prepareStatement(PaymentQueries.getLastInsertedFMReceiptNo);			
			rs = ps.executeQuery();
			
			while(rs.next()){
				fm_receipt_no = rs.getInt("fm_receipt_no") + 1;
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
		
		return fm_receipt_no;
	}
	
	public static List<PaymentBean> getRegistrationFeePaymentAmount(PaymentBean pb){
		
		String regFee = "";
		List<PaymentBean> rslt = new ArrayList<PaymentBean>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			
			con = Connector.connect();
			
			ps = con.prepareStatement(PaymentQueries.getClassRegistrationFee);
			ps.setString(1, pb.getClass_id());
			ps.setString(2, pb.getPt_id());
			ps.setString(3, ReturnCurrentEthiopianYear.getCurrentEthiopianYear());
			rs = ps.executeQuery();
			
			while(rs.next()){
				PaymentBean pbean = new PaymentBean();
				pbean.setPc_id(rs.getString("pc_id"));
				pbean.setPay_amount(rs.getString("pay_amount"));
				rslt.add(pbean);
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
