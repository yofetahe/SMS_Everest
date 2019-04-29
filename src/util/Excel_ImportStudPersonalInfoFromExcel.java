package util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableCell;
import studentClass.StudentBean;
import studentClass.StudentDAO;
import studentClass.emergency_contact.EmergencyContactBean;

public class Excel_ImportStudPersonalInfoFromExcel {
	
	private static String inputFile;
	
	private static String loggedInUserId;
	
	public static boolean importStudentPersonalInfoFromExcel(String selectedColumn, String path, String row_num, String sheet_num, String loggedInUserId)throws IOException{
		
		boolean rslt = false;
		
		setInputFile(path);
		setLoggedInUserId(loggedInUserId);
		
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
			
			for(int i = Integer.parseInt(rowFromTo.get(0))-1; i < Integer.parseInt(rowFromTo.get(1)); i++){
				
				StudentBean stud = new StudentBean();
				
				EmergencyContactBean emergencyContactBean = new EmergencyContactBean();
				
				for(int j = 0; j < sheet.getColumns(); j++){
					
					for(int z = 0; z < colSelected.size(); z++){
						
						if(j == (Integer.parseInt(colSelected.get(z))-1)){						
							
							Cell cell = sheet.getCell(j, i);
							CellType type = cell.getType();
							
							//-- Column 1 - Student full name --//
							if(z == 0){
								List<String> fullName = Arrays.asList(cell.getContents().split("\\s* \\s*"));
								
								if(fullName.size() > 2){
									
									stud.setFname(fullName.get(0));
									stud.setMname(fullName.get(1));								
									stud.setGname(fullName.get(2));
								}
							}
							
							//-- Column 2 - Student mother name --//
							if(z == 1){
								String mothername = "" + cell.getContents();
								
								if(mothername.length() > 0){
									
									stud.setMother_name(cell.getContents());
								}
							}
							
							//-- Column 3 - Student sex --//
							if(z == 2){
								
								stud.setSex(cell.getContents());
							}
							
							//-- Column 4 - Student DOB --//
							if(z == 3){
								
								List<String> dob = Arrays.asList(cell.getContents().split("\\s*/\\s*"));
								
								if(dob.size() == 3){
									
									stud.setDob(dob.get(2) + "-" + dob.get(0) + "-" + dob.get(1));
								}
							}
							
							//-- Column 5 - Student POB --//
							if(z == 4){
								
								stud.setPob(cell.getContents());
							}
							
							//-- Column 6 - Student Nationality --//
							if(z == 5){
								
								stud.setNationality(cell.getContents());
							}
							
							///>>> SETTING EMERGENCY CONTACT OF STUDENT <<<///							
							//-- Column 7 - Student Emergency Contact name --//
							if(z == 6){
								
								emergencyContactBean.setContact_name(cell.getContents());
							}

							//-- Column 8 - Student Emergency Contact relation --//
							if(z == 7){
								
								emergencyContactBean.setRelationship(cell.getContents());
							}

							//-- Column 9 - Student Emergency Contact relation --//
							if(z == 8){
								
								emergencyContactBean.setMob_no(cell.getContents());								
							}
							///>>> END - SETTING EMERGENCY CONTACT OF STUDENT <<<///
						}							
					}
				}

				stud.setEmergencyContactBean(emergencyContactBean);
				
				if((stud.getFname() != null) && (stud.getMname() != null) && (stud.getGname() != null) && (stud.getMother_name() != null) && (stud.getSex() != null) && (stud.getDob() != null) && (stud.getPob() != null) && (stud.getNationality() != null) && (stud.getEmergencyContactBean().getContact_name() != null) && (stud.getEmergencyContactBean().getRelationship() != null) && (stud.getEmergencyContactBean().getMob_no() != null)){
					
					///*** Formatting the emergnecy contact info required by saveStudentInfo() method requirement ***///
					String[] contact_name = stud.getEmergencyContactBean().getContact_name().split(",");
					String[] relationship = stud.getEmergencyContactBean().getRelationship().split(",");
					String[] contact_mob = stud.getEmergencyContactBean().getMob_no().split(",");
					
					StringBuffer contact_info = new StringBuffer("");
					
					if(contact_name.length > 1){
						
						contact_info = new StringBuffer(contact_name[0] + "," + relationship[0] + "," + contact_mob[0] + "-" + contact_name[1] + "," + relationship[1] + "," + contact_mob[1]);
						
					} else {
						
						contact_info = new StringBuffer(contact_name[0] + "," + relationship[0] + "," + contact_mob[0]);
					}
					
					stud.setContact_information(contact_info.toString());
					///*** END - Formatting the emergnecy contact info required by saveStudentInfo() method requirement ***///
					
					rslt = StudentDAO.saveStudentInfo(stud, studentIdNumberGenerator(stud.getFname(), stud.getMname(), stud.getGname()), getLoggedInUserId());
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
		Excel_ImportStudPersonalInfoFromExcel.inputFile = inputFile;
	}

	public static String getLoggedInUserId() {
		return loggedInUserId;
	}

	public static void setLoggedInUserId(String loggedInUserId) {
		Excel_ImportStudPersonalInfoFromExcel.loggedInUserId = loggedInUserId;
	}

}
