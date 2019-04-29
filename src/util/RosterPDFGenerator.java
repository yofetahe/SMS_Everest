package util;

import java.io.FileOutputStream;
import java.text.DecimalFormat;
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

import examClass.exam_result.ExamResultBean;
import reportClass.ReportBean;

public class RosterPDFGenerator {
	
	public static final String FONT = "C:/windows/fonts/ARIALUNI.TTF";
	public static final String CALIBRI_FONT = "C:/windows/fonts/calibri.ttf";
	
	private static Font customHeaderFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 15, Font.BOLD);
	BaseFont baseFont = customHeaderFont.getBaseFont();
	
	//private static Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
	private static Font headerFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 12, Font.BOLD);
		
	private static Font titleFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 9, Font.BOLD);
	
	//private static Font tblHeaderFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private static Font tblHeaderFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 9, Font.BOLD);
	
	//private static Font tblContentFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
	private static Font tblContentFont = FontFactory.getFont(FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 9, Font.NORMAL); 
	
	private static List<ReportBean> subject_list;
	private static List<ReportBean> quarter_stud_roster;
	private static ExamResultBean exrb;
	private static ReportBean rbean;
	
	public static void generatRosterPDF(ExamResultBean exrb, ReportBean rbean, String rosterFile, List<ReportBean> subject_list, List<ReportBean> quarter_stud_roster){
		
		setRbean(rbean);
		setExrb(exrb);
		setSubject_list(subject_list);
		setQuarter_stud_roster(quarter_stud_roster);
		
		try{
			
			Document document = new Document();			
			PdfWriter.getInstance(document, new FileOutputStream(rosterFile));
			document.setPageSize(PageSize.A4.rotate());
			document.open();
			addRosterMetaData(document, rosterFile);
			addRosterContent(document);
			document.close();
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void addRosterMetaData(Document document, String FileName){
		document.addTitle("Quarter Result Mark List");
		document.addSubject("Everest Student Mark List");
		document.addKeywords("java, PDF, iText" + FileName);
		document.addAuthor("Yofetahe");
		document.addCreator("Fidel");		
	}
	
	public static void addRosterContent(Document document){
		PdfPTable rosterContent = new PdfPTable(1);
		rosterContent.setWidthPercentage(100);
		
		PdfPCell cell = new PdfPCell(rosterTable());
		cell.setBorder(0);
		rosterContent.addCell(cell);
		
		try {
			document.add(rosterContent);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public static PdfPTable rosterTable(){
		
		int columnNum = subject_list.size() + 5;
		float[] columnWidths = new float[columnNum];
		columnWidths[0] = 5;
		columnWidths[1] = 33;
		columnWidths[2] = 5;		
		for(int i = 0, j = 3; i <= subject_list.size(); i++, j++){
			columnWidths[j] = 5;
		}
		columnWidths[subject_list.size() + 3] = 5;
		columnWidths[subject_list.size() + 4] = 5;		
		
		PdfPTable rstTable = new PdfPTable(columnWidths);
		
		PdfPCell cell = new PdfPCell(rosterHeader());
		cell.setColspan(columnNum);
		cell.setBorder(0);
		rstTable.addCell(cell);
				
		insertCell(rstTable, "Rank", Element.ALIGN_CENTER, 1, tblHeaderFont, "BH");
		insertCell(rstTable, "Student Name", Element.ALIGN_CENTER, 1, tblHeaderFont, "BH");
		insertCell(rstTable, "Sex", Element.ALIGN_CENTER, 1, tblHeaderFont, "BH");
		for(int i = 0; i < subject_list.size(); i++){
			insertCell(rstTable, subject_list.get(i).getSub_bean().getSub_name(), Element.ALIGN_LEFT, 1, tblHeaderFont, "BS");			
		}
		insertCell(rstTable, "Total", Element.ALIGN_CENTER, 1, tblHeaderFont, "BS");
		insertCell(rstTable, "Average", Element.ALIGN_CENTER, 1, tblHeaderFont, "BS");
		
		double pervious_total = 0.0;
		int pervious_rank = 0, same_rank_counter = 0;
		
		for(int i = 0; i < quarter_stud_roster.size(); i++){
			
			int rowNum = i + 1;
			
			if(pervious_total == 0.0){
					
				pervious_total = Double.parseDouble(quarter_stud_roster.get(i).getEx_rslt_bean().getTotal_mark());
				
				pervious_rank = 1;
				
				quarter_stud_roster.get(i).getEx_rslt_bean().setStud_rank(String.valueOf(pervious_rank));
					
			} else if(pervious_total != 0.0 && Double.parseDouble(quarter_stud_roster.get(i).getEx_rslt_bean().getTotal_mark()) == pervious_total){
					
				pervious_total = Double.parseDouble(quarter_stud_roster.get(i).getEx_rslt_bean().getTotal_mark());
				
				quarter_stud_roster.get(i).getEx_rslt_bean().setStud_rank(String.valueOf(pervious_rank));
				
				same_rank_counter++;
					
			} else {
					
				pervious_total = Double.parseDouble(quarter_stud_roster.get(i).getEx_rslt_bean().getTotal_mark());
					
				pervious_rank = pervious_rank + same_rank_counter + 1;
					
				quarter_stud_roster.get(i).getEx_rslt_bean().setStud_rank(String.valueOf(pervious_rank));
					
				same_rank_counter = 0;
			}
			
			insertCell(rstTable, String.valueOf(pervious_rank), Element.ALIGN_CENTER, 1, tblContentFont, "B");
			insertCell(rstTable, quarter_stud_roster.get(i).getStud_bean().getFname() + " " + quarter_stud_roster.get(i).getStud_bean().getMname() + " " + quarter_stud_roster.get(i).getStud_bean().getGname(), Element.ALIGN_LEFT, 1, tblContentFont, "B");
			insertCell(rstTable, quarter_stud_roster.get(i).getStud_bean().getSex(), Element.ALIGN_CENTER, 1, tblContentFont, "B");
			for(int j = 0; j < quarter_stud_roster.get(i).getSubject_total().size(); j++){
				insertCell(rstTable, quarter_stud_roster.get(i).getSubject_total().get(j), Element.ALIGN_CENTER, 1, tblContentFont, "B");
			}
			insertCell(rstTable, quarter_stud_roster.get(i).getEx_rslt_bean().getTotal_mark(), Element.ALIGN_CENTER, 1, tblContentFont, "B");
			
			DecimalFormat fmt = new DecimalFormat("###.##");
			String avg = fmt.format(Double.parseDouble(quarter_stud_roster.get(i).getEx_rslt_bean().getAverage_mark()));
			insertCell(rstTable, avg, Element.ALIGN_CENTER, 1, tblContentFont, "B");
		}
		
		return rstTable;
	}

	public static PdfPTable rosterHeader(){
		PdfPTable rstHeader = new PdfPTable(2);
		
		insertCell(rstHeader, SysConstant.SCHOOL_FULL_NAME, Element.ALIGN_CENTER, 2, customHeaderFont, "H");
		insertCell(rstHeader, "Teacher's Name: " + rbean.getStud_bean().getFullName(), Element.ALIGN_LEFT, 1, headerFont, "H");
		insertCell(rstHeader, "Date: " + exrb.getCur_date(), Element.ALIGN_RIGHT, 1, headerFont, "H");
		insertCell(rstHeader, "Student Roster", Element.ALIGN_LEFT, 1, headerFont, "H");
		insertCell(rstHeader, exrb.getCl_name() + " " + exrb.getCd_name() + " " + "Term - " + exrb.getAt_name()+", "+rbean.getStud_bean().getAc_year(), Element.ALIGN_RIGHT, 1, headerFont, "H");
		insertCell(rstHeader, "", Element.ALIGN_CENTER, 2, customHeaderFont, "H");
		
		return rstHeader;
	}
	
	
	private static void insertCell(PdfPTable table, String text, int align, int colspan, Font font, String part){
		
		PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
		
		cell.setHorizontalAlignment(align);
		
		if(part.equals("H")){
			cell.setBorder(0);	
		}
		if(part.equals("H")){
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		}
		if(part.equals("BS")){
			cell.setRotation(90);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		}
		
		cell.setColspan(colspan);
		
		if(text.trim().equalsIgnoreCase("")){
			cell.setMinimumHeight(15f);
		}
		
		table.addCell(cell);
	}

	
	
	

	public static List<ReportBean> getQuarter_stud_roster() {
		return quarter_stud_roster;
	}

	public static void setQuarter_stud_roster(List<ReportBean> quarter_stud_roster) {
		RosterPDFGenerator.quarter_stud_roster = quarter_stud_roster;
	}

	public static List<ReportBean> getSubject_list() {
		return subject_list;
	}

	public static void setSubject_list(List<ReportBean> subject_list) {
		RosterPDFGenerator.subject_list = subject_list;
	}

	public static ExamResultBean getExrb() {
		return exrb;
	}

	public static void setExrb(ExamResultBean exrb) {
		RosterPDFGenerator.exrb = exrb;
	}

	public static ReportBean getRbean() {
		return rbean;
	}

	public static void setRbean(ReportBean rbean) {
		RosterPDFGenerator.rbean = rbean;
	}
}
