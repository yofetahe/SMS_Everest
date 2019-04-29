package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import studentClass.StudentBean;
import studentClass.StudentDAO;
import studentClass.emergency_contact.EmergencyContactBean;
import studentClass.emergency_contact.EmergencyContactDAO;

public class Excel_ImportStudEmergencyContactFromExcel {

	private static String inputFile;
	
	private static String loggedInUserId;
	
	private static List<StudentBean> stud_rslt;
	
	private static String relationship;
	
	public static boolean importStudentEmergencyContactInfoFromExcel(String selectedColumn, String path, String row_num, String sheet_num, String loggedInUserId, List<StudentBean> stud_rslt, String relationship)throws IOException{
		
		boolean rslt = false;
		
		setInputFile(path);
		setLoggedInUserId(loggedInUserId);
		setStud_rslt(stud_rslt);
		setRelationship(relationship);
		
		List<String> colSelected = Arrays.asList(selectedColumn.split("\\s*,\\s*"));
		List<String> rowFromTo = Arrays.asList(row_num.split("\\s*-\\s*"));
		
		File inputWorkBook = new File(getInputFile());		
		Workbook w;
		
		try{
			if(Integer.parseInt(sheet_num) < 1){
				return rslt;
			}
			
			w = Workbook.getWorkbook(inputWorkBook);
			Sheet sheet = w.getSheet(Integer.parseInt(sheet_num)-1);
			
			///*** check the value of the selected column number ***///
			if(checkColumnInfo(colSelected, sheet.getColumns())){
				return rslt;
			}
			
			//** check the rang. it must be two number only.
			if(checkRowInfo(rowFromTo, sheet.getRows())){
				return rslt;
			}
			
			//** control the number of column selected. for father it must be 2
			if(getRelationship().equals("Father") && colSelected.size() != 2){
				return rslt;
			}
			
			//** control the number of column selected. for mother it must be 3
			if(getRelationship().equals("Mother") && colSelected.size() != 3){
				return rslt;
			}
			
			for(int i = Integer.parseInt(rowFromTo.get(0))-1; i < Integer.parseInt(rowFromTo.get(1)); i++){
				
				EmergencyContactBean estud = new EmergencyContactBean();
				
				for(int j = 0; j < sheet.getColumns(); j++){
					
					for(int z = 0; z < colSelected.size(); z++){
						
						List<String> fullName = new ArrayList<String>();
						
						if(j == (Integer.parseInt(colSelected.get(z))-1)){
							
							for(int s = 0; s < stud_rslt.size(); s++){
							
								Cell cell = sheet.getCell(j, i);
								//CellType type = cell.getType();
									
								if(getRelationship().equals("Father")){
										
									if(z == 0){
								
										fullName = Arrays.asList(cell.getContents().split("\\s* \\s*"));
										
										if(fullName.size() == 3){
											
											if(fullName.get(0).equals(stud_rslt.get(s).getFname()) && fullName.get(1).equals(stud_rslt.get(s).getMname()) && fullName.get(2).equals(stud_rslt.get(s).getGname())){
												estud.setSi_id(stud_rslt.get(s).getSi_id());
											}
											
											estud.setContact_name(fullName.get(1) + " " + fullName.get(2));												
										}
										estud.setRelationship("Father");
									}
									
									if(z == 1){
										
										//0911662766 +251911662766
										String phone = cell.getContents();
										
										if((phone+"").length() == 9 && phone.subSequence(0, 1) != "0"){
											estud.setMob_no("0" + phone.substring(0, 2) + " " + phone.substring(2, 5) + " " + phone.substring(5));
										}
										if((phone+"").length() > 9){
											estud.setMob_no(phone.substring(0, 2) + " " + phone.substring(2, 5) + " " + phone.substring(5));
										}
									}
								}
										
								if(getRelationship().equals("Mother")) {
									
									if(z == 0){
								
										fullName = Arrays.asList(cell.getContents().split("\\s* \\s*"));
										
										if(fullName.size() == 3){
										
											if(fullName.get(0).equals(stud_rslt.get(s).getFname()) && fullName.get(1).equals(stud_rslt.get(s).getMname()) && fullName.get(2).equals(stud_rslt.get(s).getGname())){
												estud.setSi_id(stud_rslt.get(s).getSi_id());
											}
										}
									}									
									if(z == 1){
										
										estud.setContact_name(cell.getContents());
										estud.setRelationship("Mother");
									}
									if(z == 2){
										
										String phone = cell.getContents();
										
										if((phone+"").length() == 9 && phone.subSequence(0, 1) != "0"){
											estud.setMob_no("0" + phone.substring(0, 2) + " " + phone.substring(2, 5) + " " + phone.substring(5));
										}
										if((phone+"").length() > 9){
											estud.setMob_no(phone.substring(0, 2) + " " + phone.substring(2, 5) + " " + phone.substring(5));
										}
									}
								}														
							}
						}	
					}
				}
				
				if(estud.getSi_id() != null && (estud.getContact_name() != null) && (estud.getMob_no() != null)){
					
					rslt = EmergencyContactDAO.saveEmergContact(estud.getContact_name(), estud.getRelationship(), estud.getMob_no(), "", "", estud.getSi_id());
				}
			}
		} catch(BiffException ex){
			ex.printStackTrace();
		}
		
		return rslt;
	}
	
	public static String studentIdNumberGenerator(String fname, String mname, String gname) {
		
		String newStudId = fname.substring(0, 1).toUpperCase() + mname.substring(0, 1).toUpperCase() + gname.substring(0, 1).toUpperCase() + StudentDAO.getLastStudentNumber();
		
		return newStudId;
	}
		
	public static boolean checkColumnInfo(List<String> colSelected, int maxColumn){
		boolean rslt = false;
		
		///*** check the value of the selected column number ***///
		int colCounter = 0;
		for(int i = 0; i < colSelected.size(); i++){				
			if(Integer.parseInt(colSelected.get(i)) > maxColumn){
				colCounter++;
			}
		}
		
		if(colCounter > 0){
			rslt = true;
		}
		
		return rslt;
	}
	
	public static boolean checkRowInfo(List<String> rowFromTo, int maxRow){
		boolean rslt = false;
		
		//** check the rang. it must be two number only.
		if(rowFromTo.size() != 2){
			return true;
		}
		
		///*** Check the value of the row range given. 
		///    if one of the two exceed the max number of row, it will be invalid
		int rowCounter = 0;
		for(int i = 0; i < rowFromTo.size(); i++){
			if(Integer.parseInt(rowFromTo.get(i)) > maxRow){
				rowCounter++;
			}
		}
		
		if(rowCounter > 0 || Integer.parseInt(rowFromTo.get(0)) > Integer.parseInt(rowFromTo.get(1))){
			rslt = true;
		}
		
		return rslt;
	}

	public static String getInputFile() {
		return inputFile;
	}

	public static void setInputFile(String inputFile) {
		Excel_ImportStudEmergencyContactFromExcel.inputFile = inputFile;
	}

	public static String getLoggedInUserId() {
		return loggedInUserId;
	}

	public static void setLoggedInUserId(String loggedInUserId) {
		Excel_ImportStudEmergencyContactFromExcel.loggedInUserId = loggedInUserId;
	}

	public static List<StudentBean> getStud_rslt() {
		return stud_rslt;
	}

	public static void setStud_rslt(List<StudentBean> stud_rslt) {
		Excel_ImportStudEmergencyContactFromExcel.stud_rslt = stud_rslt;
	}

	public static String getRelationship() {
		return relationship;
	}

	public static void setRelationship(String relationship) {
		Excel_ImportStudEmergencyContactFromExcel.relationship = relationship;
	}
}
