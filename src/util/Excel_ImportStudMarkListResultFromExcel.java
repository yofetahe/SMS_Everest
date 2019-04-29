package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import examClass.exam_result.ExamResultBean;
import examClass.exam_result.ExamResultDAO;
import examClass.exam_type.ExamBean;
import examClass.exam_type.ExamDAO;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import studentClass.StudentBean;
import studentClass.StudentDAO;

public class Excel_ImportStudMarkListResultFromExcel {
	
	private static String inputFile;
	
	private static String si_id;
	private static String result;
	private static String ac_year;
	private static String at_id;
	
	private static List<StudentBean> stud_rslt;
	private static List<ExamBean> clSubExamRelList;
	
	private static List<String> siid_list = new ArrayList<String>();
	private static List<String> stud_fullname = new ArrayList<String>();
	private static List<String> exam_type_name = new ArrayList<String>();
	private static List<String> exsubid = new ArrayList<String>();
	
	
	/**
	 * The method used to insert all the exam type result per specific class and subject at once.
	 * Assumption is the excel file holds the result of all exam type for all the students.
	 * 
	 * @param at_id
	 * @param ac_year
	 * @param path
	 * @param cl_id
	 * @param cd_id
	 * @param subcl_id
	 * @return
	 * @throws IOException
	 */
	public static boolean readFullStudMarkListExcelFile(String at_id, String ac_year, String path, String cl_id, String cd_id, String subcl_id, String row_num, String sheet_num) throws IOException{
		
		boolean rslt = false;
		
		stud_fullname.clear();
		siid_list.clear();
		exam_type_name.clear();
		exsubid.clear();
		
		ExamResultDAO.clearExamrslt();
		
		//*** Student list from the system ***//
		stud_rslt = StudentDAO.getListPerGradeDetail(cl_id, cd_id, ac_year);
		
		//*** exam type from the system ***//
		ExamResultBean exrslt = new ExamResultBean();
		exrslt.setSubcl_id(subcl_id);		
		clSubExamRelList = ExamDAO.getActivelyRelatedExamTypeListWithClassSubject(exrslt);
		
		setInputFile(path);
		setAt_id(at_id);
		setAc_year(ac_year);
		//setExsub_id(exsub_id);
		
		File inputWorkBook = new File(getInputFile());		
		Workbook w;
		
		try{
			if(Integer.parseInt(sheet_num) < 1){
				return rslt;
			}
			
			w = Workbook.getWorkbook(inputWorkBook);
			Sheet sheet = w.getSheet(Integer.parseInt(sheet_num)-1);
			
			List<String> rowFromTo = Arrays.asList(row_num.split("\\s*-\\s*"));
			
			//** check the rang. it must be two number only.
			if(checkRowInfo(rowFromTo, sheet.getRows())){
				return rslt;
			}
			
			///*** get stud name on the excel doc ***///
			for(int j = 1; j < 2; j++){
				for(int i = Integer.parseInt(rowFromTo.get(0))-1; i < Integer.parseInt(rowFromTo.get(1)); i++){
					Cell cell = sheet.getCell(j, i);
					CellType type = cell.getType();
					if(type == CellType.LABEL){
						stud_fullname.add(cell.getContents());	
					}
				}
			}
			
			///*** Compare the name on the system & excel and get the stud_id ***///
			for(int i = 0; i < stud_fullname.size(); i++){
				
				String excelFullName = stud_fullname.get(i).trim();
								
				for(int j = 0; j < stud_rslt.size(); j++){
					
					String fullName = stud_rslt.get(j).getFname() + " " + stud_rslt.get(j).getMname() + " " + stud_rslt.get(j).getGname();
					
					if(excelFullName.equalsIgnoreCase(fullName)){
						
						siid_list.add(stud_rslt.get(j).getSi_id());
					}
				}
			}
			
			///*** get exam type name on the excel doc ***///
			for(int j = 3; j < sheet.getColumns(); j++){
				for(int i = 0; i < sheet.getRows(); i++){
					Cell cell = sheet.getCell(j, i);
					CellType type = cell.getType();
					if(type == CellType.LABEL){
						exam_type_name.add(cell.getContents());	
					}
				}
			}
			
			///*** get exam type id from database ***///
			for(int i = 0; i < exam_type_name.size(); i++){
				
				for(int j = 0; j < clSubExamRelList.size(); j++){
					
					if(exam_type_name.get(i).equalsIgnoreCase(clSubExamRelList.get(j).getEt_name())){
						
						exsubid.add(clSubExamRelList.get(j).getExsub_id());
					}
				}
			}			
			
			//*** if the student id exist on the excel document ***//
//			for(int j = 2; j < 3; j++){
//				for(int i = 0; i < sheet.getRows(); i++){
//					Cell cell = sheet.getCell(j, i);
//					CellType type = cell.getType();
//					if(type == CellType.NUMBER){
//						siid_list.add(cell.getContents());	
//					}
//				}
//			}
			
			///*** add student result on ExamResultDAO.addStudExamRslt method ***///
			///*** it must check whether the stud_id list from the system
			///    and the number of student on the excel document must be equal
			int x = 0, y = 0;
			
			if(siid_list.size() == stud_fullname.size() && exsubid.size() == exam_type_name.size()){
				
				for(int j = 3; j < sheet.getColumns(); j++){
					
					for(int i = Integer.parseInt(rowFromTo.get(0))-1; i < Integer.parseInt(rowFromTo.get(1)); i++){
						
						Cell cell = sheet.getCell(j, i);
						CellType type = cell.getType();
	
						if(type == CellType.NUMBER || type == CellType.NUMBER_FORMULA){
							
							ExamResultBean exmrslt = new ExamResultBean();
							exmrslt.setSi_id(siid_list.get(y));
							exmrslt.setResult(cell.getContents());
							exmrslt.setExsub_id(exsubid.get(x));
							exmrslt.setAcademic_year(getAc_year());
							exmrslt.setAt_id(getAt_id());
							
							ExamResultDAO.addStudExamRsltFromExcel(exmrslt);
							
							y++;
						}
					}
					
					if(y == siid_list.size()){						
						x++;
					}
					
					if(x == exsubid.size()){
						rslt = true;
					}
					y = 0;
				}
			}
		} catch(BiffException ex){
			ex.printStackTrace();
		}
		
		return rslt;
	}
	
	/**
	 * The method used to insert specifically selected exam type result per specific class and subject at once.
	 * 
	 * @param at_id
	 * @param ac_year
	 * @param exsub_id
	 * @param selectedColumn
	 * @param path
	 * @param cl_id
	 * @param cd_id
	 * @param subcl_id
	 * @return
	 * @throws IOException
	 */
	public static boolean readSpecificColumnStudMarkListExcelFile(String at_id, String ac_year, String exsub_id, int selectedColumn, String path, String cl_id, String cd_id, String subcl_id, String row_num, String sheet_num) throws IOException{
		
		boolean rslt = false;
		
		stud_fullname.clear();
		siid_list.clear();
		exam_type_name.clear();
		exsubid.clear();
		
		ExamResultDAO.clearExamrslt();
		
		setInputFile(path);
		setAt_id(at_id);
		setAc_year(ac_year);
		//setExsub_id(exsub_id);

		//List<String> colSelected = Arrays.asList(selectedColumn.split("\\s*,\\s*"));
		List<String> rowFromTo = Arrays.asList(row_num.split("\\s*-\\s*"));
		
		File inputWorkBook = new File(getInputFile());		
		Workbook w;
		
		try{
			if(Integer.parseInt(sheet_num) < 1){
				return rslt;
			}
			
			w = Workbook.getWorkbook(inputWorkBook);
			Sheet sheet = w.getSheet(Integer.parseInt(sheet_num)-1);
			
			if(selectedColumn > sheet.getColumns()){
				return rslt;
			}
			
			//** check the rang. it must be two number only.
			if(checkRowInfo(rowFromTo, sheet.getRows())){
				return rslt;
			}
			
			//*** Student list from the system, came after the sheet for the performance efficiency ***//
			stud_rslt = StudentDAO.getListPerGradeDetail(cl_id, cd_id, ac_year);
			
			//*** exam type from the system ***//
			ExamResultBean exrslt = new ExamResultBean();
			exrslt.setSubcl_id(subcl_id);		
			clSubExamRelList = ExamDAO.getActivelyRelatedExamTypeListWithClassSubject(exrslt);
			
			///*** get exam type name of specifically selected column ***///
			String selectedColumnName = "";
			
			for(int j = selectedColumn - 1; j < selectedColumn; j++){
				for(int i = 0; i < sheet.getRows(); i++){
					Cell cell = sheet.getCell(j, i);
					CellType type = cell.getType();
					if(type == CellType.LABEL){
						selectedColumnName = cell.getContents();	
					}
				}
			}
			
			String columnExSubId = "";
			
			for(int j = 0; j < clSubExamRelList.size(); j++){					
				if(selectedColumnName.equalsIgnoreCase(clSubExamRelList.get(j).getEt_name())){
					columnExSubId = clSubExamRelList.get(j).getExsub_id();
				}
			}
			
			if(!columnExSubId.equals(exsub_id)){
				return rslt;
			}
			
			///*** get stud name on the excel doc ***///
			for(int j = 1; j < 2; j++){
				for(int i = Integer.parseInt(rowFromTo.get(0))-1; i < Integer.parseInt(rowFromTo.get(1)); i++){
					Cell cell = sheet.getCell(j, i);
					CellType type = cell.getType();
					if(type == CellType.LABEL){
						stud_fullname.add(cell.getContents());	
					}
				}
			}
			
			///*** Compare the name on the system & excel and get the stud_id ***///
			//String fname = "", mname = "", gname = "";
			for(int i = 0; i < stud_fullname.size(); i++){
				
				String excelFullName = stud_fullname.get(i).trim();
				
				for(int j = 0; j < stud_rslt.size(); j++){
					
					String fullName = stud_rslt.get(j).getFname() + " " + stud_rslt.get(j).getMname() + " " + stud_rslt.get(j).getGname();
					
					if(excelFullName.equalsIgnoreCase(fullName)){
						siid_list.add(stud_rslt.get(j).getSi_id());
					}
				}
			}
			
			///*** add student result on ExamResultDAO.addStudExamRslt method ***///
			///*** it must check whether the stud_id list from the system
			///    and the number of student on the excel document must be equal
			int x = 0, y = 0;
			
			if(siid_list.size() == stud_fullname.size() && selectedColumn <= sheet.getColumns()){
				
				for(int j = selectedColumn - 1; j < selectedColumn; j++){
					
					for(int i = Integer.parseInt(rowFromTo.get(0))-1; i < Integer.parseInt(rowFromTo.get(1)); i++){
						
						Cell cell = sheet.getCell(j, i);
						CellType type = cell.getType();
						
						if(type == CellType.NUMBER || type == CellType.NUMBER_FORMULA){
							
							ExamResultBean exmrslt = new ExamResultBean();
							
							exmrslt.setSi_id(siid_list.get(y));
							exmrslt.setResult(cell.getContents());
							exmrslt.setExsub_id(exsub_id);
							exmrslt.setAcademic_year(getAc_year());
							exmrslt.setAt_id(getAt_id());
							
							ExamResultDAO.addStudExamRsltFromExcel(exmrslt);
							
							y++;
						}
					}				
					if(y == siid_list.size()){
						x++;
						rslt = true;
					}					
					y = 0;
				}
			}
		} catch(BiffException ex){
			ex.printStackTrace();
		}
		
		return rslt;
	}
	/**
	 * 
	 * @param at_id
	 * @param ac_year
	 * @param exsub_id
	 * @param selectedColumn
	 * @param path
	 * @param cl_id
	 * @param cd_id
	 * @param subcl_id
	 * @param row_num
	 * @return
	 * @throws IOException
	 */
	public static boolean readMultipleColumnStudMarkListExcelFile(String at_id, String ac_year, String exsub_id, String selectedColumn, String path, String cl_id, String cd_id, String subcl_id, String row_num, String sheet_num) throws IOException{
		
		boolean rslt = false;
		
		stud_fullname.clear();
		siid_list.clear();
		exam_type_name.clear();
		exsubid.clear();
		
		ExamResultDAO.clearExamrslt();
		
		setInputFile(path);
		setAt_id(at_id);
		setAc_year(ac_year);
		//setExsub_id(exsub_id);

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
			
			//*** Student list from the system, came after the sheet for the performance efficiency ***//
			stud_rslt = StudentDAO.getListPerGradeDetail(cl_id, cd_id, ac_year);
			
			//*** exam type from the system ***//
			ExamResultBean exrslt = new ExamResultBean();
			exrslt.setSubcl_id(subcl_id);		
			clSubExamRelList = ExamDAO.getActivelyRelatedExamTypeListWithClassSubject(exrslt);
			
			///*** get exam type name of specifically selected column ***///
			List<String> selectedColumnName = new ArrayList<String>();
			
			for(int j = 0; j < sheet.getColumns(); j++){
				for(int x = 0; x < colSelected.size(); x++){					
					if(j == Integer.parseInt(colSelected.get(x))-1){
						
						for(int i = 0; i < 1; i++){
							Cell cell = sheet.getCell(j, i);
							CellType type = cell.getType();
							
							if(type == CellType.LABEL){
								
								selectedColumnName.add(cell.getContents().replaceAll("\\s+", ""));	
							}
						}
					}
				}
			}
			
			///*** get exam type id from database ***///
			List<String> columnExSubId = new ArrayList<String>();
			
			for(int j = 0; j < clSubExamRelList.size(); j++){
				
				for(int i = 0; i < selectedColumnName.size(); i++){
					
					if(selectedColumnName.get(i).equalsIgnoreCase(clSubExamRelList.get(j).getEt_name().replaceAll("\\s+", ""))){
						
						columnExSubId.add(clSubExamRelList.get(j).getExsub_id().replaceAll("\\s+", ""));
					}
				}
			}
			
			if(columnExSubId.size() == 0 || columnExSubId.size() < colSelected.size()){
				return rslt;
			}
			
			///*** get stud name on the excel doc ***///
			///*** Assumption - student name must be exist on the second column of the excel file ***///
			for(int j = 1; j < 2; j++){
				for(int i = Integer.parseInt(rowFromTo.get(0))-1; i < Integer.parseInt(rowFromTo.get(1)); i++){
					Cell cell = sheet.getCell(j, i);
					CellType type = cell.getType();
					if(type == CellType.LABEL){
						stud_fullname.add(cell.getContents());	
					}
				}
			}
			
			///*** Compare the name on the system & excel and get the stud_id ***///
			for(int i = 0; i < stud_fullname.size(); i++){
				
				String excelFullName = stud_fullname.get(i).trim();
				
				for(int j = 0; j < stud_rslt.size(); j++){
					
					String fullName = stud_rslt.get(j).getFname() + " " + stud_rslt.get(j).getMname() + " " + stud_rslt.get(j).getGname();
					
					if(excelFullName.equalsIgnoreCase(fullName)){
						siid_list.add(stud_rslt.get(j).getSi_id());
					}
				}
			}
			
			///*** add student result on ExamResultDAO.addStudExamRslt method ***///
			///*** it must check whether the stud_id list from the system
			///    and the number of student on the excel document must be equal
			int x = 0, y = 0;
			
			if(siid_list.size() == stud_fullname.size()){
				
				for(int j = 0; j < sheet.getColumns(); j++){
					for(int z = 0; z < colSelected.size(); z++){
						if(j == Integer.parseInt(colSelected.get(z))-1){
							for(int i = Integer.parseInt(rowFromTo.get(0))-1; i < Integer.parseInt(rowFromTo.get(1)); i++){
								
								Cell cell = sheet.getCell(j, i);
								CellType type = cell.getType();
								
								if(type == CellType.NUMBER || type == CellType.NUMBER_FORMULA){
									
									ExamResultBean exmrslt = new ExamResultBean();
									
									exmrslt.setSi_id(siid_list.get(y));
									exmrslt.setResult(cell.getContents());
									exmrslt.setExsub_id(columnExSubId.get(x));
									exmrslt.setAcademic_year(getAc_year());
									exmrslt.setAt_id(getAt_id());
									
									ExamResultDAO.addStudExamRsltForUpdateFromExcel(exmrslt);
									
									y++;
								}
							}	
						}
					}
					if(y == siid_list.size()){
						x++;
						rslt = true;
					}					
					y = 0;
				}
			}
		} catch(BiffException ex){
			ex.printStackTrace();
		}
		
		return rslt;
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
	
	public static List<StudentBean> checkStudNameFromMarkListExcelFile(String at_id, String ac_year, String exsub_id, String path, String cl_id, String cd_id, String subcl_id, String row_num, String sheet_num) throws IOException{
		
		List<StudentBean> rslt = new ArrayList<StudentBean>();
		
		stud_fullname.clear();
		siid_list.clear();
		exam_type_name.clear();
		exsubid.clear();
		
		ExamResultDAO.clearExamrslt();
		
		setInputFile(path);
		setAt_id(at_id);
		setAc_year(ac_year);

		List<String> rowFromTo = Arrays.asList(row_num.split("\\s*-\\s*"));
		
		File inputWorkBook = new File(getInputFile());		
		Workbook w;
		
		try{
			
			if(Integer.parseInt(sheet_num) < 1){
				return rslt;
			}
			
			w = Workbook.getWorkbook(inputWorkBook);
			Sheet sheet = w.getSheet(Integer.parseInt(sheet_num)-1);
			
			//** check the rang. it must be two number only.
			if(checkRowInfo(rowFromTo, sheet.getRows())){
				return rslt;
			}
			
			//*** Student list from the system, came after the sheet for the performance efficiency ***//
			stud_rslt = StudentDAO.getListPerGradeDetail(cl_id, cd_id, ac_year);
			
			///*** get stud name on the excel doc ***///
			///*** Assumption - student name must be exist on the second column of the excel file ***///
			for(int j = 1; j < 2; j++){
				for(int i = Integer.parseInt(rowFromTo.get(0))-1; i < Integer.parseInt(rowFromTo.get(1)); i++){
					Cell cell = sheet.getCell(j, i);
					CellType type = cell.getType();
					if(type == CellType.LABEL){
						stud_fullname.add(cell.getContents());	
					}
				}
			}
			
			///*** Compare the name on the system & excel and get the stud_id ***///
			int rowCounter = 0;
			String fullName = "";
			String excelFullName = "";
			
			
			
			for(int i = 0; i < stud_fullname.size(); i++){
				
				excelFullName = stud_fullname.get(i).trim();
				
				for(int j = 0; j < stud_rslt.size(); j++){
					
					fullName = stud_rslt.get(j).getFname() + " " + stud_rslt.get(j).getMname() + " " + stud_rslt.get(j).getGname();
					
					if(excelFullName.equalsIgnoreCase(fullName)){
						rowCounter++;
					}
				}
				
				if(rowCounter == 0){
					StudentBean stb = new StudentBean();
					stb.setFullName(excelFullName);
					rslt.add(stb);
				}
				
				rowCounter = 0;
			}
			
		} catch(BiffException ex){
			ex.printStackTrace();
		}
		
		return rslt;
		
	}
	
//	fname = excelFullName.substring(0, excelFullName.indexOf(" "));
//	
//	excelFullName = excelFullName.substring(excelFullName.indexOf(" "));
//	excelFullName = excelFullName.substring(3);
//	
//	if(excelFullName.indexOf(" ") > 0){
//		mname = excelFullName.substring(0, excelFullName.indexOf(" "));
//		excelFullName = excelFullName.substring(excelFullName.indexOf(" "));
//	}
//	
//	if(excelFullName.length() > 3){
//		gname = excelFullName.substring(3);
//	}
//	
//	String exFullName = fname + "" + mname; // + "" + gname;
	
//	public static void saveExamResult(){
//		ExamResultDAO.saveStudExamRsltFromExcel("1");
//	}	
//	public static void main(String[] args) throws IOException{
//		
//	}

	public static String getSi_id() {
		return si_id;
	}
	public static void setSi_id(String si_id) {
		Excel_ImportStudMarkListResultFromExcel.si_id = si_id;
	}
	public static String getResult() {
		return result;
	}
	public static void setResult(String result) {
		Excel_ImportStudMarkListResultFromExcel.result = result;
	}	
	public static String getAc_year() {
		return ac_year;
	}
	public static void setAc_year(String ac_year) {
		Excel_ImportStudMarkListResultFromExcel.ac_year = ac_year;
	}
	public static String getAt_id() {
		return at_id;
	}
	public static void setAt_id(String at_id) {
		Excel_ImportStudMarkListResultFromExcel.at_id = at_id;
	}
	public static String getInputFile() {
		return inputFile;
	}
	public static void setInputFile(String inputFile){
		Excel_ImportStudMarkListResultFromExcel.inputFile = inputFile;
	}
	public static List<String> getSiid_list() {
		return siid_list;
	}
	public static void setSiid_list(List<String> siid_list) {
		Excel_ImportStudMarkListResultFromExcel.siid_list = siid_list;
	}
	public static List<String> getExsubid() {
		return exsubid;
	}
	public static void setExsub_id(List<String> exsubid) {
		Excel_ImportStudMarkListResultFromExcel.exsubid = exsubid;
	}
	public static List<String> getStud_fullname() {
		return stud_fullname;
	}
	public static void setStud_fullname(List<String> stud_fullname) {
		Excel_ImportStudMarkListResultFromExcel.stud_fullname = stud_fullname;
	}

	public static List<String> getExam_type_name() {
		return exam_type_name;
	}

	public static void setExam_type_name(List<String> exam_type_name) {
		Excel_ImportStudMarkListResultFromExcel.exam_type_name = exam_type_name;
	}

	public static List<StudentBean> getStud_rslt() {
		return stud_rslt;
	}

	public static void setStud_rslt(List<StudentBean> stud_rslt) {
		Excel_ImportStudMarkListResultFromExcel.stud_rslt = stud_rslt;
	}

	public static List<ExamBean> getClSubExamRelList() {
		return clSubExamRelList;
	}

	public static void setClSubExamRelList(List<ExamBean> clSubExamRelList) {
		Excel_ImportStudMarkListResultFromExcel.clSubExamRelList = clSubExamRelList;
	}	
}
