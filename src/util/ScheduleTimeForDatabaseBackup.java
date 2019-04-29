package util;

import java.util.Calendar;
import java.util.Timer;
import util.DatabaseBackup;

public class ScheduleTimeForDatabaseBackup {
		
	public static void main(String[] args){
		
		Calendar td = Calendar.getInstance();
		td.set(Calendar.HOUR_OF_DAY, 11);
		td.set(Calendar.MINUTE, 0);
		td.set(Calendar.SECOND, 0);
		
		Timer timer = new Timer();
		timer.schedule(new DatabaseBackup(), td.getTime());
	}	

}
