package util;

import java.io.File;

public class CheckFileExistance {
	
	private static String filePath;
	
	public static boolean fileExist(String path){
		
		boolean rslt = false;
		
		setFilePath(path);
		
		File file = new File(getFilePath());
		
		if(file.exists()){
			rslt = true;
		}
		
		return rslt;
	}

	public static String getFilePath() {
		return filePath;
	}

	public static void setFilePath(String filePath) {
		CheckFileExistance.filePath = filePath;
	}

}
