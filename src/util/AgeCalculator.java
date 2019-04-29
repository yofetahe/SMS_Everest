package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgeCalculator {
	
	public static String calculateAge(String dob){
		
		if(dob.equals("") || (!dob.equals("") && dob.length() < 10)){
			return "";
		} 
		
		long age = 0;
		
		int[] separator = new int[2];
        for (int i = 0, j = 1, y = 0; i < dob.length(); i++, j++) {
            if (dob.substring(i, j).equalsIgnoreCase("-")) {
                separator[y] = i;
                y++;
            }
        }
        
        int eth_year = Integer.parseInt(dob.substring(0, separator[0]).trim());
        String eth_month = dob.substring(separator[0] + 1, separator[1]).trim();
        int eth_day = Integer.parseInt(dob.substring(separator[1] + 1).trim());
        
        String dob_conv = String.valueOf(eth_day) + "-" + eth_month + "-" + String.valueOf(eth_year);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				
		if(dob.length() != 0){
			
			String today = TodayDate_YYYYMMDD.getTodayDayMonthYearFormat();
			
			String dob_1 = DateConvertor.dateConvertor_ethtogrig(dob_conv);
			
			int[] separator_1 = new int[2];
			
	        for (int i = 0, j = 1, y = 0; i < dob_1.length(); i++, j++) {
	        	
	            if (dob_1.substring(i, j).equalsIgnoreCase("-")) {
	                separator_1[y] = i;
	                y++;
	            }
	        }
	        
	        String dy = dob_1.substring(0, separator_1[0]);
	        String mth = "";	        
	        for(int i = 1; i < 13; i++){
	        	mth = (dob_1.substring(separator_1[0] + 1, separator_1[1]).equals("January"))?"1":(dob_1.substring(separator_1[0] + 1, separator_1[1]).equals("February"))?"2":(dob_1.substring(separator_1[0] + 1, separator_1[1]).equals("March"))?"3":(dob_1.substring(separator_1[0] + 1, separator_1[1]).equals("April"))?"4":(dob_1.substring(separator_1[0] + 1, separator_1[1]).equals("May"))?"5":(dob_1.substring(separator_1[0] + 1, separator_1[1]).equals("June"))?"6":(dob_1.substring(separator_1[0] + 1, separator_1[1]).equals("July"))?"7":(dob_1.substring(separator_1[0] + 1, separator_1[1]).equals("August"))?"8":(dob_1.substring(separator_1[0] + 1, separator_1[1]).equals("September"))?"9":(dob_1.substring(separator_1[0] + 1, separator_1[1]).equals("October"))?"10":(dob_1.substring(separator_1[0] + 1, separator_1[1]).equals("November"))?"11":"12";
	        }
	        String yr = dob_1.substring(separator_1[1] + 1);
	        
	        String dob_2 = dy + "-" + mth + "-" + yr;
			
			try {
				
				Date td = sdf.parse(today);
				
				Date dob_3 = sdf.parse(dob_2);
				
				age = ((td.getTime() - dob_3.getTime())/(24 * 60 * 60 * 1000))/365;
				
			} catch (ParseException e) {
				
				e.printStackTrace();
			}			
		}
		
		return String.valueOf(age);
	}
	
	public static String calculateAgeBasedOnGivenYear(String dob, String ac_year){
		
		long age = 0;
		
		int[] separator = new int[2];
        for (int i = 0, j = 1, y = 0; i < dob.length(); i++, j++) {
            if (dob.substring(i, j).equalsIgnoreCase("-")) {
                separator[y] = i;
                y++;
            }
        }
        
        int eth_year = Integer.parseInt(dob.substring(0, separator[0]).trim());
        String eth_month = dob.substring(separator[0] + 1, separator[1]).trim();
        int eth_day = Integer.parseInt(dob.substring(separator[1] + 1).trim());
        
        ///>>> If the DOB year given with two digit <<<///
        String current_year = ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear().substring(0, 1) + "00";        
        if(eth_year < 100 && eth_year > 50){
        	eth_year = eth_year + 1900;
        } else if(eth_year < 50){
        	eth_year = eth_year + 2000;
        }
        ///>>> If the DOB year given with two digitr <<<///
        
        String dob_conv = String.valueOf(eth_day) + "-" + eth_month + "-" + String.valueOf(eth_year);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				
		if(dob.length() != 0){
			
			String current_yr = ReturnCurrentEthiopianYear.getActualCurrentEthiopianYear();
			
			String today = "";
			
			if(Integer.parseInt(current_yr) == Integer.parseInt(ac_year)){
			
				today = TodayDate_YYYYMMDD.getTodayDayMonthYearFormat();
				
			} else {
				
				today = TodayDate_YYYYMMDD.getDefaultLastDateOfSelectedPassedYear(ac_year);
				
//				today = DateConvertor.dateConvertor_ethtogrig("7-10-" + ac_year);
//				
//				///>>> Today <<<///
//				int[] separator_2 = new int[2];
//		        for (int i = 0, j = 1, y = 0; i < today.length(); i++, j++) {
//		            if (today.substring(i, j).equalsIgnoreCase("-")) {
//		                separator_2[y] = i;
//		                y++;
//		            }
//		        }
//		        String p_dy = today.substring(0, separator_2[0]);
//		        String p_mth = "";
//		        for(int i = 1; i < 13; i++){
//		        	p_mth = (today.substring(separator_2[0] + 1, separator_2[1]).equals("January"))?"1":(today.substring(separator_2[0] + 1, separator_2[1]).equals("February"))?"2":(today.substring(separator_2[0] + 1, separator_2[1]).equals("March"))?"3":(today.substring(separator_2[0] + 1, separator_2[1]).equals("April"))?"4":(today.substring(separator_2[0] + 1, separator_2[1]).equals("May"))?"5":(today.substring(separator_2[0] + 1, separator_2[1]).equals("June"))?"6":(today.substring(separator_2[0] + 1, separator_2[1]).equals("July"))?"7":(today.substring(separator_2[0] + 1, separator_2[1]).equals("August"))?"8":(today.substring(separator_2[0] + 1, separator_2[1]).equals("September"))?"9":(today.substring(separator_2[0] + 1, separator_2[1]).equals("October"))?"10":(today.substring(separator_2[0] + 1, separator_2[1]).equals("November"))?"11":"12";
//		        }
//		        String p_yr = today.substring(separator_2[1] + 1);
//		        
//		        today = p_dy + "-" + p_mth + "-" + p_yr;
//		        ///>>> Today <<<///
			}
			
			String dob_1 = DateConvertor.dateConvertor_ethtogrig(dob_conv);
			
			int[] separator_1 = new int[2];
	        for (int i = 0, j = 1, y = 0; i < dob_1.length(); i++, j++) {
	            if (dob_1.substring(i, j).equalsIgnoreCase("-")) {
	                separator_1[y] = i;
	                y++;
	            }
	        }
	        
	        ///>>> DOB <<<///
	        String dy = dob_1.substring(0, separator_1[0]);
	        String mth = "";
	        for(int i = 1; i < 13; i++){
	        	mth = (dob_1.substring(separator_1[0] + 1, separator_1[1]).equals("January"))?"1":(dob_1.substring(separator_1[0] + 1, separator_1[1]).equals("February"))?"2":(dob_1.substring(separator_1[0] + 1, separator_1[1]).equals("March"))?"3":(dob_1.substring(separator_1[0] + 1, separator_1[1]).equals("April"))?"4":(dob_1.substring(separator_1[0] + 1, separator_1[1]).equals("May"))?"5":(dob_1.substring(separator_1[0] + 1, separator_1[1]).equals("June"))?"6":(dob_1.substring(separator_1[0] + 1, separator_1[1]).equals("July"))?"7":(dob_1.substring(separator_1[0] + 1, separator_1[1]).equals("August"))?"8":(dob_1.substring(separator_1[0] + 1, separator_1[1]).equals("September"))?"9":(dob_1.substring(separator_1[0] + 1, separator_1[1]).equals("October"))?"10":(dob_1.substring(separator_1[0] + 1, separator_1[1]).equals("November"))?"11":"12";
	        }
	        String yr = dob_1.substring(separator_1[1] + 1);
	        
	        String dob_2 = dy + "-" + mth + "-" + yr;
	        ///>>> DOB <<<///
	        	        
			try {
				
				Date td = sdf.parse(today);
				
				Date dob_3 = sdf.parse(dob_2);
				
				age = ((td.getTime() - dob_3.getTime())/(24 * 60 * 60 * 1000))/365;
				
			} catch (ParseException e) {
				
				e.printStackTrace();
			}			
		}
		
		return String.valueOf(age);
	}

}
