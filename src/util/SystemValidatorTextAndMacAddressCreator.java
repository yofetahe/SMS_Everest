package util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class SystemValidatorTextAndMacAddressCreator {
	
	public static boolean generateValidationText(String school_name){
		
		boolean rslt = false;
		
		boolean school_sys_code =  systemUniquenessValidation(school_name);
		
		if(school_sys_code){
		
			String mac_add = MacAddressGenerator.getMacAddress();
			
			try{
				
				File myDir = new File(SysConstant.SECURITY_FILE_PATH);
				myDir.getParentFile().mkdir();
				
				File file = new File(myDir, "computerMACAddress.txt");
				
				FileWriter fw = new FileWriter(file);
				PrintWriter pw = new PrintWriter(fw);
				pw.println(mac_add); 
				pw.flush(); 
				pw.close();
				
				if(file.exists()){
					rslt = true;
				}
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		return rslt;
	}
	
	/*
	 * text file generator --- the file writer will be generated at the beginning of system installation only.
	 */
	public static boolean systemUniquenessValidation(String school_name){
		boolean rslt = false;
		
		StringBuilder schoolCode = encodeSchoolName(school_name);
		
		try{
			
			File myDir = new File(SysConstant.SECURITY_FILE_PATH);
			myDir.getParentFile().mkdir();
			
			File file = new File(myDir, "smsSystemValidator.txt");
			
			FileWriter fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(schoolCode); 
			pw.flush(); 
			pw.close();
			
			if(file.exists()){
				rslt = true;
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		} 
		
		return rslt;
	}
	
	/*
	 * Generate textual computer code
	 */
	public static StringBuilder encodeSchoolName(String name){
		
		String nameGiven = name.toLowerCase().trim();
		StringBuilder encodeName = new StringBuilder();
		
		char[] alph = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
		
		for(int i = 0; i < name.length(); i++){
			for(int j = 0; j < 26; j++){
				if(nameGiven.charAt(i) == alph[j] && j%2 == 0){
					encodeName.append(j + "-");
				} else if(nameGiven.charAt(i) == alph[j] && j%2 != 0){
					encodeName.append(i+j);
				}
			}
		}
		
		return encodeName;
	}

} 