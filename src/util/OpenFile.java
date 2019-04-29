package util;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class OpenFile {
	
	public static void openExistingPDFFile(String fileName){
		
		try {
			////*** Method One ***////
//			if(fileName.toString().endsWith(".pdf")){
//				Process p = Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler" + fileName);
//				p.waitFor();				
//			}
			
			////*** Method Two ***////
			File certificateFile = new File(fileName);
			
			if(certificateFile.exists()){
				
				if(Desktop.isDesktopSupported()){
					
					Desktop.getDesktop().open(certificateFile);	
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
//		catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	
	///>>> bat File for security purpose <<<///
	public static void openBatFile(){
		
		String fileName = "C:\\sms_system_file\\security\\sms_license.bat";
		
		try {
				
			File batFile = new File(fileName);
			if(batFile.exists()){
				
				if(Desktop.isDesktopSupported()){
					Desktop.getDesktop().open(batFile);	
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public static void openExistingExcelFile(String path, String fileName){
		
		try {
			
			////*** Method Two ***////
			File excelFile = new File(path + "" + fileName + ".xls");
			System.out.println(excelFile.exists() + " excel file");
			if(excelFile.exists()){
				
				if(Desktop.isDesktopSupported()){
					Desktop.getDesktop().open(excelFile);	
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
