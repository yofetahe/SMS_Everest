package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import connectionClass.Connector;

public class SystemValidator {
	
	//public static final String MACADD = "08-00-27-00-FC-5A";
	public static String MACADD = "";
	
	public static boolean systemValidation(){
		
		boolean rslt = false;
		
		boolean text_val = systemUniquenessValidation();
		
		String mac_add = MacAddressGenerator.getMacAddress();
		
		List<String> macList = MacAddressGenerator.getMacAddressesList();
		
		try{
			
			File myDir = new File(SysConstant.SECURITY_FILE_PATH);
			myDir.getParentFile().mkdir();
			
			File file = new File(myDir, "computerMACAddress.txt");
			
//			FileWriter fw = new FileWriter(file);
//			PrintWriter pw = new PrintWriter(fw);
//			pw.println(mac_add); 
//			pw.flush(); 
//			pw.close();
			
			FileReader fr = new FileReader(file); 
			BufferedReader br = new BufferedReader(fr);
			MACADD = br.readLine();
			br.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		boolean physicalAddress_1 = false, physicalAddress_2 = false;
		
		for(int i = 0; i < macList.size(); i++){
			if(macList.get(i).equals(MACADD)){
				physicalAddress_1 = true;
			}
			if(SysConstant.MACADD_CONSTANT_1.equals(macList.get(i)) || 
					SysConstant.MACADD_CONSTANT_2.equals(macList.get(i)) || 
					SysConstant.MACADD_CONSTANT_3.equals(macList.get(i)) || 
					SysConstant.MACADD_CONSTANT_4.equals(macList.get(i)) || 
					SysConstant.MACADD_CONSTANT_5.equals(macList.get(i)) || 
					SysConstant.MACADD_CONSTANT_6.equals(macList.get(i))){
				
				physicalAddress_2 = true;
			}
		}
		
		if(text_val && physicalAddress_1 && physicalAddress_2){
			rslt = true;
		}
		
        return rslt;
	}
	
	/*
	 * text file generator --- the file writer will be generated at the beginning of system installation only.
	 */
	public static boolean systemUniquenessValidation(){
		boolean rslt = false;
		
		StringBuilder schoolCode = encodeSchoolName(SysConstant.SCHOOL_NAME);
		
		try{
			
			File myDir = new File(SysConstant.SECURITY_FILE_PATH);
			myDir.getParentFile().mkdir();
			
			File file = new File(myDir, "smsSystemValidator.txt");
			
//			FileWriter fw = new FileWriter(file);
//			PrintWriter pw = new PrintWriter(fw);
//			pw.println(schoolCode); 
//			pw.flush(); 
//			pw.close();
			
			FileReader fr = new FileReader(file); 
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			br.close();
			
			if(line.equals(schoolCode.toString())){
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
	
	/*
	 * Generate the table list exist on the database. 
	 */
	public static void datebaseSecurity(){
		
		DatabaseBackup.backupDataWithDatabase(SysConstant.DUMP_EXE_PATH, SysConstant.HOST, SysConstant.PORT, SysConstant.DATABASE_USERNAME, SysConstant.DATABASE_PASSWORD, SysConstant.SYS_NAME, SysConstant.DATABASE_BACKUP_FOLDER);
		
		try {
			
			Connection c = Connector.connect();
			
			DatabaseMetaData md = c.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);
			while(rs.next()){
				//System.out.println(rs.getString(3));
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	///>>> check whether the file moved with out permission or not <<<///
		public static boolean checkSystemFileExistance(){
			boolean rslt = true;
			
			File fl = new File(SysConstant.SYS_INSTALL_FILE_PATH);
			if(fl.exists()){
				rslt = false;
			}
			
			return rslt;
		}
	
	public static boolean fromUrlDatabaseBackup(){
		
		boolean rslt = DatabaseBackup.backupDataWithDatabase(SysConstant.DUMP_EXE_PATH, SysConstant.HOST, SysConstant.PORT, SysConstant.DATABASE_USERNAME, SysConstant.DATABASE_PASSWORD, SysConstant.DATABASE_NAME, SysConstant.DATABASE_BACKUP_FOLDER);
		
		return rslt;
	}
}