package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TodayDate_YYYYMMDD {
	
	public static String getToday(){
		
		// getting academic year --- starting from May to August the
		// academic year consider the next year
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());

		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);

		String curDate = year + "-" + (month + 1) + "-" + day;
		
		return curDate;
	}
	
	public static String getTodayDayMonthYearFormat(){
		// getting academic year --- starting from May to August the
		// academic year consider the next year
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());

		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);

		String curDate = day + "-" + month + "-" + year;
		
		return curDate;
	}
	
	public static String getDayMonthYearFormat(){
		// getting academic year --- starting from May to August the
		// academic year consider the next year
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());

		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);

		String curDate = day + "-" + (month+1) + "-" + year;
		
		return curDate;
	}
	
	public static String getDayMonthYearFormat(String date){
		// getting academic year --- starting from May to August the
		// academic year consider the next year
		String curDate = "";
		
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dt = sdf.parse(date);
			Calendar cal = new GregorianCalendar();
			cal.setTime(dt);
	
			int month = cal.get(Calendar.MONTH);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int year = cal.get(Calendar.YEAR);
	
			curDate = day + "-" + (month+1) + "-" + year;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return curDate;
	}
	
	public static String getHour(){
		String time = "";
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		
		String hour = String.valueOf(cal.get(Calendar.HOUR));
		String min = cal.get(Calendar.MINUTE) > 10?String.valueOf(cal.get(Calendar.MINUTE)):"0"+String.valueOf(cal.get(Calendar.MINUTE));
		String sec = cal.get(Calendar.SECOND) > 10?String.valueOf(cal.get(Calendar.SECOND)):"0"+String.valueOf(cal.get(Calendar.SECOND));
		int tzone = cal.get(Calendar.AM_PM);
		String t_zone = "";

		if(tzone == 0){ 
			t_zone = " AM";
		} else if(tzone == 1){
			t_zone = " PM";
		}
		
		time = hour + ":" + min + ":" + sec + t_zone;
		
		return time;
	}
	
	public static String getNameOfDay(String date){
		String dayName = "";
		String pdate = date;
		
		DateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date dt = new Date();
		try {
			dt = sdf.parse(pdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = new GregorianCalendar();
		cal.setTime(dt);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		
		switch(dayOfWeek){
			case 1: dayName = "Sunday";
			break;
			case 2: dayName = "Monday";
			break;
			case 3: dayName = "Tuesday";
			break;
			case 4: dayName = "Wednesday";
			break;
			case 5: dayName = "Thursday";
			break;
			case 6: dayName = "Friday";
			break;
			case 7: dayName = "Saturday";
			break;			
		}
		
		return dayName;
	}
	
	public static String getDefaultLastDateOfSelectedPassedYear(String ac_year){
		
		String today = DateConvertor.dateConvertor_ethtogrig("7-11-" + ac_year);
		
		///>>> Today <<<///
		int[] separator_2 = new int[2];
        for (int i = 0, j = 1, y = 0; i < today.length(); i++, j++) {
            if (today.substring(i, j).equalsIgnoreCase("-")) {
                separator_2[y] = i;
                y++;
            }
        }
        String p_dy = today.substring(0, separator_2[0]);
        String p_mth = "";
        for(int i = 1; i < 13; i++){
        	p_mth = (today.substring(separator_2[0] + 1, separator_2[1]).equals("January"))?"1":(today.substring(separator_2[0] + 1, separator_2[1]).equals("February"))?"2":(today.substring(separator_2[0] + 1, separator_2[1]).equals("March"))?"3":(today.substring(separator_2[0] + 1, separator_2[1]).equals("April"))?"4":(today.substring(separator_2[0] + 1, separator_2[1]).equals("May"))?"5":(today.substring(separator_2[0] + 1, separator_2[1]).equals("June"))?"6":(today.substring(separator_2[0] + 1, separator_2[1]).equals("July"))?"7":(today.substring(separator_2[0] + 1, separator_2[1]).equals("August"))?"8":(today.substring(separator_2[0] + 1, separator_2[1]).equals("September"))?"9":(today.substring(separator_2[0] + 1, separator_2[1]).equals("October"))?"10":(today.substring(separator_2[0] + 1, separator_2[1]).equals("November"))?"11":"12";
        }
        String p_yr = today.substring(separator_2[1] + 1);
        
        today = p_dy + "-" + p_mth + "-" + p_yr;
        ///>>> Today <<<///
        
        return today;
	}
	
	public static int getCurrentEthiopianMonth(){
		
		String ethio_current_date = DateConvertor.gregToEthioDateConvertor(getDayMonthYearFormat());
		
		String[] eth_date = ethio_current_date.split("-");
		
		String current_eth_month_name = eth_date[1];
		
		String[] eth_months = new String[]{"Meskerem", "Tikemet", "Hidar", "Tahesas", "Tir", "Yekatit", "Megabit", "Miayazia", "Genbot", "Sene", "Hamle", "Nehase", "Pagume"};
		
		int month = 0;
		
		for(int i = 0; i < eth_months.length; i++){
			
			if(current_eth_month_name.equals(eth_months[i])){
				
				month = i + 1;
			}
		}

		return month;
	}

}
