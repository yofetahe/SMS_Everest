package util;

import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import cRoomClass.CRoomBean;
import examClass.exam_type.ExamBean;
import reportClass.ReportBean;

public class PDF_MarkListGenerator {
	
	public static final String FONT = "C:/windows/fonts/ARIALUNI.TTF";
	public static final String CALIBRI_FONT = "C:/windows/fonts/calibri.ttf";
	
	private static Font customHeaderFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 15, Font.BOLD);
	BaseFont baseFont = customHeaderFont.getBaseFont();
	
	//private static Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
	private static Font headerFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 10, Font.BOLD);
		
	private static Font titleFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 9, Font.BOLD);
	
	//private static Font tblHeaderFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private static Font tblHeaderFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 9, Font.BOLD);
	
	//private static Font tblContentFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
	private static Font tblContentFont = FontFactory.getFont(FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 8, Font.NORMAL); 
		
	private static List<ReportBean> quarter_mark_list;
	private static List<CRoomBean> exam_type_total_mark_list;
	private static List<ExamBean> examtype_list;
	private static ReportBean rbean;
	
	public static void generateMarkListPDF(ReportBean rbean, List<ReportBean> quarter_mark_list, String markListFile, List<CRoomBean> exam_type_total_mark_list, List<ExamBean> examtype_list){
		
		setQuarter_mark_list(quarter_mark_list);
		setExam_type_total_mark_list(exam_type_total_mark_list);
		setExamtype_list(examtype_list);
		setRbean(rbean);
		
		try{
			
			Document document = new Document();
			
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
		document.addTitle("Quarter Result Mark List");
		document.addSubject("Everest Student Mark List");
		document.addKeywords("java, PDF, iText" + FileName);
		document.addAuthor("Yofetahe");
		document.addCreator("Fidel");		
	}
	
	public static void addMarkListContent(Document document){
		
		PdfPTable mrkListContent = new PdfPTable(1);
		mrkListContent.setWidthPercentage(100);
		
//		PdfPCell cell = new PdfPCell(markListHeader());
//		cell.setBorder(0);
//		mrkListContent.addCell(cell);
		
		PdfPCell cell = new PdfPCell(markListTable());
		cell.setBorder(0);
		mrkListContent.addCell(cell);
		
		try {
			document.add(mrkListContent);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public static PdfPTable markListHeader(){
		
		PdfPTable mrkListHeader = new PdfPTable(3);
		
		PdfPCell cell = new PdfPCell(new Phrase(SysConstant.SCHOOL_FULL_NAME, customHeaderFont));
		cell.setColspan(3);
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		mrkListHeader.addCell(cell);
		
		cell = new PdfPCell(new Phrase(rbean.getStud_bean().getAc_year() + " E.C " + rbean.getEx_rslt_bean().getAt_name() + " Mark List", headerFont));
		cell.setColspan(3);
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		mrkListHeader.addCell(cell);
		
		cell = new PdfPCell(new Phrase("" + rbean.getEx_rslt_bean().getCl_name() + " " + rbean.getEx_rslt_bean().getCd_name(), titleFont));
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		mrkListHeader.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Subject: " + rbean.getEx_rslt_bean().getSub_name(), titleFont));
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		mrkListHeader.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Date: " + rbean.getEx_rslt_bean().getCur_date(), titleFont));
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		mrkListHeader.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setColspan(3);
		cell.setBorder(0);
		cell.setFixedHeight(10f);
		mrkListHeader.addCell(cell);
		
		return mrkListHeader;
	}
	
	public static PdfPTable markListFooter(){
		
		PdfPTable mrkListFooter = new PdfPTable(2);
		
		PdfPCell cell = new PdfPCell(new Phrase(" "));
		cell.setColspan(2);
		cell.setBorder(0);
		cell.setFixedHeight(10f);
		mrkListFooter.addCell(cell);		
		
		cell = new PdfPCell(new Phrase("Teacher's Name: " + rbean.getEx_rslt_bean().getSubject_teacher(), titleFont));
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		mrkListFooter.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Signature: _________________________ ", titleFont));
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		mrkListFooter.addCell(cell);
								
		return mrkListFooter;
	}
	
	public static PdfPTable markListTable(){
		
		float[] columnWidths = new float[getExamtype_list().size() + 4];
		columnWidths[0] = 4;
		columnWidths[1] = 30;
		columnWidths[2] = 4;
		columnWidths[3] = 4;
		
		for(int i = 0; i < getExamtype_list().size(); i++){
			columnWidths[i+4] = 7;
		}
		
		PdfPTable markListTbl = new PdfPTable(columnWidths);
		
		PdfPCell cell = new PdfPCell(markListHeader());
		cell.setColspan(getExamtype_list().size() + 5);
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		markListTbl.addCell(cell);
				
		insertCell(markListTbl, "No", Element.ALIGN_CENTER, 1, 2, tblHeaderFont);
		insertCell(markListTbl, "Student Name", Element.ALIGN_CENTER, 1, 2, tblHeaderFont);
		insertCell(markListTbl, "Sex", Element.ALIGN_CENTER, 1, 2, tblHeaderFont);
		insertCell(markListTbl, "Age", Element.ALIGN_CENTER, 1, 2, tblHeaderFont);
		
		for(int i = 0; i < getExamtype_list().size(); i++){
			
			insertCell(markListTbl, getExamtype_list().get(i).getEt_name(), Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
		}
		
//		insertCell(markListTbl, "Exam 1", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
//		insertCell(markListTbl, "Exam 2", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
//		insertCell(markListTbl, "Work\nSheet", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
//		insertCell(markListTbl, "Activity", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
//		insertCell(markListTbl, "Total", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
//		insertCell(markListTbl, "Final", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
//		insertCell(markListTbl, "Total", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
		
		int total = Integer.parseInt(exam_type_total_mark_list.get(0).getTotal_mark()) + Integer.parseInt(exam_type_total_mark_list.get(1).getTotal_mark()) + Integer.parseInt(exam_type_total_mark_list.get(2).getTotal_mark()) + Integer.parseInt(exam_type_total_mark_list.get(3).getTotal_mark());
		
		for(int i = 0; i < exam_type_total_mark_list.size(); i++){
			
			insertCell(markListTbl, exam_type_total_mark_list.get(i).getTotal_mark() + "%", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
		}
//		insertCell(markListTbl, exam_type_total_mark_list.get(0).getTotal_mark() + "%", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
//		insertCell(markListTbl, exam_type_total_mark_list.get(1).getTotal_mark() + "%", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
//		insertCell(markListTbl, exam_type_total_mark_list.get(2).getTotal_mark() + "%", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
//		insertCell(markListTbl, exam_type_total_mark_list.get(3).getTotal_mark() + "%", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
//		insertCell(markListTbl, String.valueOf(total)  + "%", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
//		insertCell(markListTbl, exam_type_total_mark_list.get(4).getTotal_mark() + "%", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
//		insertCell(markListTbl, "100%", Element.ALIGN_CENTER, 1, 1, tblHeaderFont);
				
		for(int i = 0; i < quarter_mark_list.size(); i++){
			
			int rowNum = i + 1;
			insertCell(markListTbl, String.valueOf(rowNum), Element.ALIGN_CENTER, 1, 1, tblContentFont);			
			insertCell(markListTbl, quarter_mark_list.get(i).getStud_bean().getFname() + " " + quarter_mark_list.get(i).getStud_bean().getMname() + " " + quarter_mark_list.get(i).getStud_bean().getGname(), Element.ALIGN_LEFT, 1, 1, tblContentFont);
			insertCell(markListTbl, quarter_mark_list.get(i).getStud_bean().getSex(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
			
			String dob = quarter_mark_list.get(i).getStud_bean().getDob() + "";
			
			insertCell(markListTbl, dob.equals(null)?"":AgeCalculator.calculateAgeBasedOnGivenYear(quarter_mark_list.get(i).getStud_bean().getDob(), rbean.getStud_bean().getAc_year()), Element.ALIGN_CENTER, 1, 1, tblContentFont);
			
			for(int j = 0; j < quarter_mark_list.get(i).getEx_rslt_bean().getAllExamResult().size(); j++){
				
				insertCell(markListTbl, quarter_mark_list.get(i).getEx_rslt_bean().getAllExamResult().get(j), Element.ALIGN_CENTER, 1, 1, tblContentFont);
			}
			
//			insertCell(markListTbl, quarter_mark_list.get(i).getExam_one(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
//			insertCell(markListTbl, quarter_mark_list.get(i).getExam_two(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
//			insertCell(markListTbl, quarter_mark_list.get(i).getWorksheet(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
//			insertCell(markListTbl, quarter_mark_list.get(i).getActivity(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
//			insertCell(markListTbl, quarter_mark_list.get(i).getSub_total(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
//			insertCell(markListTbl, quarter_mark_list.get(i).getFinalExam(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
//			insertCell(markListTbl, quarter_mark_list.get(i).getGrand_total(), Element.ALIGN_CENTER, 1, 1, tblContentFont);			
		}
		
		cell = new PdfPCell(markListFooter());
		cell.setColspan(11);
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		markListTbl.addCell(cell);
		
		return markListTbl;
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
		
		cell.setMinimumHeight(13f);
		
		table.addCell(cell);
	}

	public static List<ReportBean> getQuarter_mark_list() {
		return quarter_mark_list;
	}

	public static void setQuarter_mark_list(List<ReportBean> quarter_mark_list) {
		PDF_MarkListGenerator.quarter_mark_list = quarter_mark_list;
	}

	public static ReportBean getRbean() {
		return rbean;
	}

	public static void setRbean(ReportBean rbean) {
		PDF_MarkListGenerator.rbean = rbean;
	}

	public static List<CRoomBean> getExam_type_total_mark_list() {
		return exam_type_total_mark_list;
	}

	public static void setExam_type_total_mark_list(List<CRoomBean> exam_type_total_mark_list) {
		PDF_MarkListGenerator.exam_type_total_mark_list = exam_type_total_mark_list;
	}

	public static List<ExamBean> getExamtype_list() {
		return examtype_list;
	}

	public static void setExamtype_list(List<ExamBean> examtype_list) {
		PDF_MarkListGenerator.examtype_list = examtype_list;
	}
}
