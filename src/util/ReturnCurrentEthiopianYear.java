package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ReturnCurrentEthiopianYear {

	public static String getCurrentEthiopianYear() {
		// getting academic year --- starting from May to August the
		// academic year consider the next year
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());

		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);

		String curDate = day + "-" + month + "-" + year;
		String yr = util.DateConvertor.dateConvertor(curDate);
		
		if((month > 5 && month <= 7) || (month == 8 && day < 11)){
			yr = String.valueOf(Integer.parseInt(yr) + 1);
		}
		
		return yr;
	}
	
	public static String getActualCurrentEthiopianYear() {
		// getting academic year --- starting from May to August the
		// academic year consider the next year
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());

		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);

		String curDate = day + "-" + month + "-" + year;
		String yr = util.DateConvertor.dateConvertor(curDate);
		
		return yr;
	}

}
