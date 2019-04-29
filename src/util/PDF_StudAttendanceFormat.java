package util;

import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfTable;

import cRoomClass.CRoomBean;
import reportClass.ReportBean;
import studentClass.StudentBean;

public class PDF_StudAttendanceFormat {
	
	public static final String FONT = "C:/windows/fonts/ARIALUNI.TTF";
	public static final String CALIBRI_FONT = "C:/windows/fonts/calibri.ttf";
	
	private static Font customHeaderFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 25, Font.BOLD);
	BaseFont baseFont = customHeaderFont.getBaseFont();
	
	//private static Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
	private static Font headerFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 10, Font.BOLD);
		
	private static Font titleFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 9, Font.BOLD);
	
	//private static Font tblHeaderFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private static Font tblHeaderFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 9, Font.BOLD);
	
	//private static Font tblContentFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
	private static Font tblContentFont = FontFactory.getFont(FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 8, Font.NORMAL);
	
	private static Font tblMonthNameFont = FontFactory.getFont(FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 6, Font.BOLD);
		
	private static List<StudentBean> stud_rslt;
	private static ReportBean rbean;
	
	public static void getStudAttendanceFormatPDF(List<StudentBean> stud_rslt, String markListFile, ReportBean rbean){
		
		setStud_rslt(stud_rslt);
		setRbean(rbean);
		
		try{
			
			Document document = new Document();
			document.setPageSize(PageSize.A4.rotate());
			
			PdfWriter.getInstance(document, new FileOutputStream(markListFile));			
			document.open();
			addMarkListMetaData(document, markListFile);
			addMarkListContent(document);
			document.close();
			
		} catch(Exception e){
			e.printStackTrace();
		}				
	}
	
	public static void addMarkListMetaData(Document document, String FileName){
		document.addTitle("Student Attendance Format");
		document.addSubject("Everest Student Attendance");
		document.addKeywords("java, PDF, iText" + FileName);
		document.addAuthor("Yofetahe");
		document.addCreator("Fidel");		
	}
	
	public static void addMarkListContent(Document document){
		
		PdfPTable mrkListContent = new PdfPTable(1);
		mrkListContent.setWidthPercentage(100);
		
		PdfPCell cell = new PdfPCell(markListHeader());
		cell.setBorder(0);
		mrkListContent.addCell(cell);
		
		cell = new PdfPCell(markListTable());
		cell.setBorder(0);
		mrkListContent.addCell(cell);
		
		try {
			document.add(mrkListContent);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public static PdfPTable markListHeader(){
		
		PdfPTable mrkListHeader = new PdfPTable(1);
		
		PdfPCell cell = new PdfPCell(new Phrase("", customHeaderFont));
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setFixedHeight(20);
		mrkListHeader.addCell(cell);
		
		cell = new PdfPCell(new Phrase(SysConstant.SCHOOL_FULL_NAME_CAP, customHeaderFont));
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		mrkListHeader.addCell(cell);
		
		cell = new PdfPCell(new Phrase(ReturnCurrentEthiopianYear.getCurrentEthiopianYear() + " E.C STUDENTS ATTENDANCE SHEET", customHeaderFont));
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		mrkListHeader.addCell(cell);
		
		cell = new PdfPCell(new Phrase("" + rbean.getClass_bean().getClass_name() + " " + rbean.getCdetail_bean().getCd_name(), customHeaderFont));
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		mrkListHeader.addCell(cell);
				
		return mrkListHeader;
	}
		
	public static PdfPTable markListTable(){
		
		float[] columnWidths = new float[36];//{4, 30, 4, 4, 7, 5, 3, ..., 3};
		for(int i = 0; i < 36; i++){
			if(i == 0){
				columnWidths[i] = 4;
			}
			if(i == 1){
				columnWidths[i] = 25;
			}
			if(i == 2 || i == 3){
				columnWidths[i] = 4;
			}
			if(i == 4){
				columnWidths[i] = 10;
			}
			if(i == 5){
				columnWidths[i] = 8;
			}
			if(i > 5){
				columnWidths[i] = 3;
			}
		}
		
		PdfPTable markListTbl = new PdfPTable(columnWidths);
				
		insertCell(markListTbl, "No", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
		insertCell(markListTbl, "Student Name", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
		insertCell(markListTbl, "Sex", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
		insertCell(markListTbl, "Age", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
		insertCell(markListTbl, "Parent's Phone Number", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
		insertCell(markListTbl, "Months", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
		
		for(int i = 1; i <= 30; i++){
			insertCell(markListTbl, String.valueOf(i), Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
		}
		
		int rowCounter = 0;
		
		//>>> table content
		for(int i = 0; i < stud_rslt.size(); i++){
			
			int rowNum = i + 1;
			insertCell(markListTbl, String.valueOf(rowNum), Element.ALIGN_CENTER, 1, 1, tblContentFont);			
			insertCell(markListTbl, stud_rslt.get(i).getFname() + " " + stud_rslt.get(i).getMname() + " " + stud_rslt.get(i).getGname(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(markListTbl, stud_rslt.get(i).getSex(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
			
			String dob = stud_rslt.get(i).getDob() + "";			
			insertCell(markListTbl, dob.equals(null)?"":AgeCalculator.calculateAge(stud_rslt.get(i).getDob()), Element.ALIGN_CENTER, 1, 1, tblContentFont);
			
			PdfPCell cell = new PdfPCell(parentsPhone(i));
			cell.setBorderWidthRight(0);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthTop(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			markListTbl.addCell(cell);
			
			cell = new PdfPCell(monthsName());
			cell.setBorder(0);
			cell.setColspan(31);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			markListTbl.addCell(cell);
			
			rowCounter++;
			
			if(rowCounter == 4){
				
				insertCell(markListTbl, "No", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
				insertCell(markListTbl, "Student Name", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
				insertCell(markListTbl, "Sex", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
				insertCell(markListTbl, "Age", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
				insertCell(markListTbl, "Parent's Phone Number", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
				insertCell(markListTbl, "Months", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
				
				for(int x = 1; x <= 30; x++){
					insertCell(markListTbl, String.valueOf(x), Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
				}
				
				rowCounter = 0;
			}								
		}
		
		return markListTbl;
	}
	
	private static PdfPTable parentsPhone(int index){
		
		PdfPTable phoneNumber = new PdfPTable(1);
		
		for(int i = 0; i < stud_rslt.get(index).getEmergencyContactBeanList().size(); i++){
			
			PdfPCell cell = new PdfPCell(new Phrase(stud_rslt.get(index).getEmergencyContactBeanList().get(i).getRelationship(), tblContentFont));
			cell.setBorder(1);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			phoneNumber.addCell(cell);
			
			cell = new PdfPCell(new Phrase(stud_rslt.get(index).getEmergencyContactBeanList().get(i).getMob_no(), tblContentFont));
			cell.setBorder(1);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			phoneNumber.addCell(cell);
			
			cell = new PdfPCell(new Phrase("", tblContentFont));
			cell.setBorder(1);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			phoneNumber.addCell(cell);
		}
		
//		PdfPCell cell = new PdfPCell(new Phrase("Mother's", tblContentFont));
//		cell.setBorder(1);
//		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//		phoneNumber.addCell(cell);
//		
//		cell = new PdfPCell(new Phrase("0911662766", tblContentFont));
//		cell.setBorder(1);
//		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//		phoneNumber.addCell(cell);
//		
//		cell = new PdfPCell(new Phrase("", tblContentFont));
//		cell.setBorder(1);
//		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//		phoneNumber.addCell(cell);
//		
//		cell = new PdfPCell(new Phrase("Father's", tblContentFont));
//		cell.setBorder(1);
//		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//		phoneNumber.addCell(cell);
//		
//		cell = new PdfPCell(new Phrase("---", tblContentFont));
//		cell.setBorder(1);
//		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//		phoneNumber.addCell(cell);
		
		return phoneNumber;
	}
	
	private static PdfPTable monthsName(){
		
		float[] columnWidths = new float[31];//{4, 30, 4, 4, 7, 5, 3, ..., 3};
		for(int i = 0; i < 31; i++){
			
			if(i == 0){
				columnWidths[i] = 8;
			}
			if(i > 0){
				columnWidths[i] = 3;
			}
		}
		
		PdfPTable months = new PdfPTable(columnWidths);
		
		insertCell(months, "SEP", Element.ALIGN_CENTER, 1, 1, tblMonthNameFont);
		for(int j = 0; j < 30; j++){
			insertCell(months, "", Element.ALIGN_LEFT, 1, 1, tblMonthNameFont);
		}
		insertCell(months, "OCT", Element.ALIGN_CENTER, 1, 1, tblMonthNameFont);
		for(int j = 0; j < 30; j++){
			insertCell(months, "", Element.ALIGN_LEFT, 1, 1, tblMonthNameFont);
		}
		insertCell(months, "NOV", Element.ALIGN_CENTER, 1, 1, tblMonthNameFont);
		for(int j = 0; j < 30; j++){
			insertCell(months, "", Element.ALIGN_LEFT, 1, 1, tblMonthNameFont);
		}
		insertCell(months, "DEC", Element.ALIGN_CENTER, 1, 1, tblMonthNameFont);
		for(int j = 0; j < 30; j++){
			insertCell(months, "", Element.ALIGN_LEFT, 1, 1, tblMonthNameFont);
		}
		insertCell(months, "JAN", Element.ALIGN_CENTER, 1, 1, tblMonthNameFont);
		for(int j = 0; j < 30; j++){
			insertCell(months, "", Element.ALIGN_LEFT, 1, 1, tblMonthNameFont);
		}
		insertCell(months, "FEB", Element.ALIGN_CENTER, 1, 1, tblMonthNameFont);
		for(int j = 0; j < 30; j++){
			insertCell(months, "", Element.ALIGN_LEFT, 1, 1, tblMonthNameFont);
		}
		insertCell(months, "MAR", Element.ALIGN_CENTER, 1, 1, tblMonthNameFont);
		for(int j = 0; j < 30; j++){
			insertCell(months, "", Element.ALIGN_LEFT, 1, 1, tblMonthNameFont);
		}
		insertCell(months, "APR", Element.ALIGN_CENTER, 1, 1, tblMonthNameFont);
		for(int j = 0; j < 30; j++){
			insertCell(months, "", Element.ALIGN_LEFT, 1, 1, tblMonthNameFont);
		}
		insertCell(months, "MAY", Element.ALIGN_CENTER, 1, 1, tblMonthNameFont);
		for(int j = 0; j < 30; j++){
			insertCell(months, "", Element.ALIGN_LEFT, 1, 1, tblMonthNameFont);
		}
		insertCell(months, "JUN", Element.ALIGN_CENTER, 1, 1, tblMonthNameFont);
		for(int j = 0; j < 30; j++){
			insertCell(months, "", Element.ALIGN_LEFT, 1, 1, tblMonthNameFont);
		}
		
		return months;
	}
	
	private static void insertCell(PdfPTable table, String text, int align, int colspan, int rowspan, Font font){
		
		String val = text + "";
		
		PdfPCell cell = new PdfPCell(new Phrase(text == null?"":text, font));
		
		cell.setHorizontalAlignment(align);
		
		//cell.setBorder(0);
		cell.setRowspan(rowspan);
		
		cell.setColspan(colspan);
		
		if(val.equalsIgnoreCase("")){
			cell.setMinimumHeight(13f);
		}
		
		cell.setMinimumHeight(12f);
		
		table.addCell(cell);
	}


	public static ReportBean getRbean() {
		return rbean;
	}

	public static void setRbean(ReportBean rbean) {
		PDF_StudAttendanceFormat.rbean = rbean;
	}

	public static List<StudentBean> getStud_rslt() {
		return stud_rslt;
	}

	public static void setStud_rslt(List<StudentBean> stud_rslt) {
		PDF_StudAttendanceFormat.stud_rslt = stud_rslt;
	}
}
