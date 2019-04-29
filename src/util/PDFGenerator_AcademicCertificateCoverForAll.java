package util;

import java.awt.datatransfer.SystemFlavorMap;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import behEvaluationClass.BevalBean;
import examClass.exam_result.ExamResultBean;
import studentClass.StudentBean;

public class PDFGenerator_AcademicCertificateCoverForAll {
	
	//private static String FILE = "c:/sms_system_file/everest/temp/CertificatePdf.pdf";
	public static final String FONT = "C:/windows/fonts/ARIALUNI.TTF";
	public static final String FONT_2 = "C:/windows/fonts/ARLRDBD.TTF";
	public static final String CALIBRI_FONT = "C:/windows/fonts/calibri.ttf";
	public static final String NYALA = "C:/windows/fonts/nyala.ttf";
	
	private static Font schoolNameFontEng_1 = FontFactory.getFont(FONT_2, BaseFont.CP1257, BaseFont.EMBEDDED, 18, Font.BOLD);
	
	private static Font schoolNameFontEng_2 = FontFactory.getFont(FONT_2, BaseFont.CP1257, BaseFont.EMBEDDED, 18, Font.BOLD);
	
	private static Font schoolNameFontEng_3 = FontFactory.getFont(FONT_2, BaseFont.CP1257, BaseFont.EMBEDDED, 18, Font.BOLD);
	
	private static Font customHeaderFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 15, Font.BOLD);
	BaseFont baseFont = customHeaderFont.getBaseFont();
	
	//BaseFont unicode = BaseFont.createFont("C:/windows/fonts/nyala.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	
	//private static Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
	private static Font headerFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 10, Font.BOLD);
	
	private static Font headerFontAmh = FontFactory.getFont(NYALA, BaseFont.CP1257, BaseFont.EMBEDDED, 10, Font.BOLD);
	
	private static Font headerFontBig = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 14, Font.BOLD);
	
	private static Font schoolNameFontEng = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 20, Font.BOLD);
	
	private static Font schoolNameFontOro = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 16, Font.BOLD);
	
	//private static Font addressFont = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);
	private static Font addressFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 12, Font.BOLD);
	
	private static Font titleFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 10, Font.BOLD);
	
	//private static Font studInfFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
	private static Font studInfFont = FontFactory.getFont(FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 9, Font.NORMAL);
	
	//private static Font tblHeaderFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private static Font tblHeaderFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 9, Font.BOLD);
	
	//private static Font tblContentFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
	private static Font tblContentFont = FontFactory.getFont(FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 9, Font.NORMAL); 
	
	//private static Font tblContentBoldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private static Font tblContentBoldFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 9, Font.BOLD);
	
	//private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	private static Font redFont = FontFactory.getFont(FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 9, Font.NORMAL, BaseColor.RED);
	
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	
	//used for PDF page title
	private static Font smallFont = new Font(Font.FontFamily.TIMES_ROMAN, 5, Font.NORMAL, BaseColor.WHITE);
	
	private static ExamResultBean exrslt;
	private static List<StudentBean> stud_personal_info;
	private static List<BevalBean> firstQuarterEvaluation;
	private static List<BevalBean> secondQuarterEvaluation;
	private static List<BevalBean> thirdQuarterEvaluation;
	private static List<BevalBean> fourthQuarterEvaluation;
	
	private Document certDocument = new Document();
	
//	public static void main(String[] args){
//		try{
//			
//			Document document = new Document();
//			
//			PdfWriter.getInstance(document, new FileOutputStream(FILE));
//			
//			document.open();
//			addMetaData(document);
//			addContent(document);
//			document.close();
//			
//		} catch(Exception e){
//			e.printStackTrace();
//		}
//	}
	
	public void openCertDocument(String FileName){
		
		try{
			
			PdfWriter.getInstance(certDocument, new FileOutputStream(FileName));
			//writer.setPageEvent(new RotateEvent()); //// to rotate the pdf page horizontal with the content
			certDocument.setPageSize(PageSize.A4.rotate());
			certDocument.open();
			addCertificateMetaData(certDocument, FileName);

		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void addCertificateMetaData(Document document, String FileName){
		document.addTitle("Full Year Result Certificate");
		document.addSubject("Everest Student Certificate Cover");
		document.addKeywords("java, PDF, iText" + FileName);
		document.addAuthor("Yofetahe");
		document.addCreator("Fidel");		
	}
	
	public void generateCertficateCoverPdf(ExamResultBean exrslt, List<BevalBean> firstQuarterEvaluation, List<BevalBean> secondQuarterEvaluation, List<BevalBean> thirdQuarterEvaluation, List<BevalBean> fourthQuarterEvaluation, List<StudentBean> stud_personal_info){
		
		try{
			
			setExrslt(exrslt);
			setStud_personal_info(stud_personal_info);
			setFirstQuarterEvaluation(firstQuarterEvaluation);
			setSecondQuarterEvaluation(secondQuarterEvaluation);
			setThirdQuarterEvaluation(thirdQuarterEvaluation);
			setFourthQuarterEvaluation(fourthQuarterEvaluation);
			
			addCertificateContent(certDocument);
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void closeCertDocument(){
		try{
			
			certDocument.close();
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void addCertificateContent(Document certdoc) throws DocumentException{
		
		Anchor anchor = new Anchor("SMS_EVEREST", smallFont);
		
		Chapter catPart = new Chapter(new Paragraph(anchor), Integer.parseInt(ReturnCurrentEthiopianYear.getCurrentEthiopianYear()));
		
		Paragraph preface = new Paragraph();
		addEmptyLine(preface, 1);
		
		createCertificateTable(catPart);
			
		certdoc.add(catPart);
		
	}
	
	private static void createCertificateTable(Chapter catPart) throws BadElementException{
		
		float[] columnWidths = {12f, 1f, 12f};
		
		PdfPTable table = new PdfPTable(columnWidths);
		table.getDefaultCell().setBorder(0);
		table.setWidthPercentage(100);
				
		PdfPCell cell = new PdfPCell();
		cell.addElement(certTableLeftColumn());
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(0);
		cell.setColspan(1);
		table.addCell(cell);		
		
		insertCell(table, " ", Element.ALIGN_CENTER, 1, headerFont);
		
		cell = new PdfPCell();
		cell.addElement(certTableRightColumn());
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setPaddingTop(4);
		table.addCell(cell);
		
		catPart.add(table);
	}
	
	////**** left column ****////
	private static PdfPTable certTableLeftColumn(){
		
		PdfPTable certRightColumnTable = new PdfPTable(1);
		certRightColumnTable.setWidthPercentage(100);
		
		PdfPCell cell = new PdfPCell(behaviouralEvaluation());
		cell.setColspan(1);
		cell.setBorder(0);
		certRightColumnTable.addCell(cell);

		cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(35f);
		certRightColumnTable.addCell(cell);
		
		cell = new PdfPCell(getStudentEvaluationKey());
		cell.setBorder(0);
		cell.setColspan(1);
		certRightColumnTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(35f);
		certRightColumnTable.addCell(cell);
		
		cell = new PdfPCell(getGradingSystemInfo());
		cell.setBorder(0);
		cell.setColspan(1);
		certRightColumnTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(85f);
		certRightColumnTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(SysConstant.SCHOOL_ADDRESS_CERTIFICATE_BACK, titleFont));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(15f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		certRightColumnTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Fidel School Management System, Developed by YamGet IT Solutions Plc.", titleFont));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(15f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		certRightColumnTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("+251 91 166 2766, +251 91 219 5853, www.yamget.com", titleFont));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(15f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		certRightColumnTable.addCell(cell);
		
		return certRightColumnTable;
	}
	
	private static PdfPTable behaviouralEvaluation(){
		
		float[] columnWidths = {12f, 4f, 4f, 4f, 4f};
		
		PdfPTable certRightColumnTable = new PdfPTable(columnWidths);
		certRightColumnTable.setWidthPercentage(100);
		
		PdfPCell cell = new PdfPCell(new Phrase("Attitude", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);		
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPaddingBottom(4);
		cell.setColspan(1);
		cell.setRowspan(2);
		cell.setFixedHeight(40f);
		cell.setBorder(0);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderWidthBottom(1);
		cell.setBorderColorTop(BaseColor.BLACK);
		cell.setBorderWidthTop(1);
		certRightColumnTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Tutors Comments", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(0);
		cell.setColspan(4);
		cell.setFixedHeight(20f);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderWidthBottom(1);
		cell.setBorderColorTop(BaseColor.BLACK);
		cell.setBorderWidthTop(1);
		certRightColumnTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("1st. Qrt", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(20f);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderWidthBottom(1);
		certRightColumnTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("2nd. Qrt", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(20f);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderWidthBottom(1);
		certRightColumnTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("3rd. Qrt", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(20f);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderWidthBottom(1);
		certRightColumnTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("4th. Qrt", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(20f);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderWidthBottom(1);
		certRightColumnTable.addCell(cell);
		
		int row = firstQuarterEvaluation.size();
		List<BevalBean> avList = firstQuarterEvaluation;
		
		if(row < secondQuarterEvaluation.size()){
			
			row = secondQuarterEvaluation.size();
			avList = secondQuarterEvaluation;
		}
		if(row < thirdQuarterEvaluation.size()){
			
			row = thirdQuarterEvaluation.size();
			avList = thirdQuarterEvaluation;
		}
		if(row < fourthQuarterEvaluation.size()){
			
			row = fourthQuarterEvaluation.size();
			avList = fourthQuarterEvaluation;
		}
		
		for(int i = 0; i < row; i++){
			
			cell = new PdfPCell(new Phrase(avList.get(i).getBt_title(), titleFont));
			cell.setBorder(0);
			cell.setColspan(1);
			cell.setNoWrap(false);
			cell.setMinimumHeight(20f);
			cell.setBorderColorBottom(BaseColor.BLACK);
			cell.setBorderWidthBottom(1);
			certRightColumnTable.addCell(cell);
			
			if(firstQuarterEvaluation.size() > 0){
				cell = new PdfPCell(new Phrase(firstQuarterEvaluation.size() > 0?firstQuarterEvaluation.get(i).getBg_name():"-", titleFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(0);
				cell.setColspan(1);
				cell.setBorderColorBottom(BaseColor.BLACK);
				cell.setBorderWidthBottom(1);
				certRightColumnTable.addCell(cell);
			} else {
				cell = new PdfPCell(new Phrase("", titleFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(0);
				cell.setColspan(1);
				cell.setBorderColorBottom(BaseColor.BLACK);
				cell.setBorderWidthBottom(1);
				certRightColumnTable.addCell(cell);
			}
			
			if(secondQuarterEvaluation.size() > 0){
				cell = new PdfPCell(new Phrase(secondQuarterEvaluation.size() > 0?secondQuarterEvaluation.get(i).getBg_name():"-", titleFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(0);
				cell.setColspan(1);
				cell.setBorderColorBottom(BaseColor.BLACK);
				cell.setBorderWidthBottom(1);
				certRightColumnTable.addCell(cell);
			} else {
				cell = new PdfPCell(new Phrase("", titleFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(0);
				cell.setColspan(1);
				cell.setBorderColorBottom(BaseColor.BLACK);
				cell.setBorderWidthBottom(1);
				certRightColumnTable.addCell(cell);
			}
			
			if(thirdQuarterEvaluation.size() > 0){
				cell = new PdfPCell(new Phrase(thirdQuarterEvaluation.size() > 0?thirdQuarterEvaluation.get(i).getBg_name():"-", titleFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(0);
				cell.setColspan(1);
				cell.setBorderColorBottom(BaseColor.BLACK);
				cell.setBorderWidthBottom(1);
				certRightColumnTable.addCell(cell);
			} else {
				cell = new PdfPCell(new Phrase("", titleFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(0);
				cell.setColspan(1);
				cell.setBorderColorBottom(BaseColor.BLACK);
				cell.setBorderWidthBottom(1);
				certRightColumnTable.addCell(cell);
			}
			
			if(fourthQuarterEvaluation.size() > 0){
				cell = new PdfPCell(new Phrase(new Phrase(fourthQuarterEvaluation.size() > 0?fourthQuarterEvaluation.get(i).getBg_name():"-", titleFont)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(0);
				cell.setColspan(1);
				cell.setBorderColorBottom(BaseColor.BLACK);
				cell.setBorderWidthBottom(1);
				certRightColumnTable.addCell(cell);	
			} else {
				cell = new PdfPCell(new Phrase(new Phrase("", titleFont)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(0);
				cell.setColspan(1);
				cell.setBorderColorBottom(BaseColor.BLACK);
				cell.setBorderWidthBottom(1);
				certRightColumnTable.addCell(cell);
			}
		}
		
		cell = new PdfPCell(new Phrase("Tutors Signature:", headerFont));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(20f);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderWidthBottom(1);
		certRightColumnTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(""));
		cell.setBorder(0);
		cell.setColspan(4);
		cell.setFixedHeight(20f);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderWidthBottom(1);
		certRightColumnTable.addCell(cell);
				
		return certRightColumnTable;
	}
	
	private static PdfPTable getGradingSystemInfo(){
		
		PdfPTable gradingSystemInfoTable = new PdfPTable(3);
		gradingSystemInfoTable.setWidthPercentage(100);
		try {
			gradingSystemInfoTable.setWidths(new int[]{4, 4, 4});
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		PdfPCell cell = new PdfPCell(new Phrase("Grading System", tblHeaderFont));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setRowspan(2);
		cell.setBorder(0);
		cell.setColspan(3);
		cell.setFixedHeight(20f);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderWidthBottom(1);
		gradingSystemInfoTable.addCell(cell);
				
		cell = new PdfPCell(new Phrase("95% - 100%   Excellent", tblContentFont));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(1);
		cell.setBorder(0);
		gradingSystemInfoTable.addCell(cell);
				
		cell = new PdfPCell(new Phrase("85% - 94%   Very Good", tblContentFont));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(1);
		cell.setBorder(0);
		gradingSystemInfoTable.addCell(cell);
				
		cell = new PdfPCell(new Phrase("75% - 84%    Good", tblContentFont));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(1);
		cell.setBorder(0);
		gradingSystemInfoTable.addCell(cell);
				
		cell = new PdfPCell(new Phrase("70% - 74%   Satisfactory", tblContentFont));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(1);
		cell.setBorder(0);
		gradingSystemInfoTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("65% - 69%    Pass", tblContentFont));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setRowspan(1);
		cell.setBorder(0);
		gradingSystemInfoTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Below 65%   Failed", tblContentFont));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setRowspan(1);
		cell.setBorder(0);
		gradingSystemInfoTable.addCell(cell);
				
		return gradingSystemInfoTable;
	}
	
	public static PdfPTable getStudentEvaluationKey(){
		
		PdfPTable studEvalKeyTable = new PdfPTable(1);
		studEvalKeyTable.setWidthPercentage(100);
		
		PdfPCell cell = new PdfPCell(new Phrase("Key: ", tblHeaderFont));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(20f);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderWidthBottom(1);
		studEvalKeyTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("A - Excellent, B+ - V.Good, B - Good, C - Fair, D - Needs Improvement", tblContentFont));
		cell.setBorder(0);
		cell.setColspan(1);
		studEvalKeyTable.addCell(cell);
		
		return studEvalKeyTable;
	}
	
	////**** right column ****////
	private static PdfPTable certTableRightColumn(){
		
		PdfPTable certLeftColumnTable = new PdfPTable(1);
		certLeftColumnTable.getDefaultCell().setBorder(0);
		certLeftColumnTable.setWidthPercentage(100);
		
		PdfPCell cell = new PdfPCell(getSchoolName());
		cell.setColspan(1);
		cell.setBorder(0);
		cell.setPaddingRight(5);
		certLeftColumnTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(6f);
		certLeftColumnTable.addCell(cell);
		
		cell = new PdfPCell(getSchoolLogo());
		cell.setColspan(1);
		cell.setBorder(0);
		cell.setPaddingRight(5);
		certLeftColumnTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(6f);
		certLeftColumnTable.addCell(cell);
		
		cell = new PdfPCell(getStudentInfo());
		cell.setColspan(1);
		cell.setBorder(0);
		cell.setPaddingRight(5);
		certLeftColumnTable.addCell(cell);
		
		return certLeftColumnTable;
	}
	
	private static PdfPTable getSchoolName(){
		
		PdfPTable headerTbl = new PdfPTable(1);
		
//		insertCell(headerTbl, SysConstant.SCHOOL_FULL_NAME_ORO, Element.ALIGN_CENTER, 1, schoolNameFontOro);
//		BaseFont unicode;
//		try {
//			unicode = BaseFont.createFont(NYALA, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//			Font font = new Font(unicode, 21, Font.NORMAL, new BaseColor(0, 0, 0));
//			
//			PdfPCell cell = new PdfPCell(new Phrase(SysConstant.SCHOOL_FULL_NAME_AMH, font));
//			cell.setBorder(0);
//			cell.setColspan(1);
//			cell.setFixedHeight(25f);
//			cell.setHorizontalAlignment(Element.ALIGN_CENTER);			
//			headerTbl.addCell(cell);
//			
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}		
//		insertCell(headerTbl, SysConstant.SCHOOL_FULL_NAME_CAP, Element.ALIGN_CENTER, 1, schoolNameFontEng);
		
		PdfPCell cell = new PdfPCell(getOroSchoolNameWithDiffColor());
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setRowspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		headerTbl.addCell(cell);
		
		cell = new PdfPCell(getSchoolNameWithDiffColor());
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setRowspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		headerTbl.addCell(cell);
		
		cell = new PdfPCell(getAmhSchoolNameWithDiffColor());
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setRowspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		headerTbl.addCell(cell);
		
		insertCell(headerTbl, SysConstant.SCHOOL_ADDRESS, Element.ALIGN_CENTER, 1, addressFont);
		insertCell(headerTbl, SysConstant.SCHOOL_EMAIL, Element.ALIGN_CENTER, 1, addressFont);
		insertCell(headerTbl, SysConstant.SCHOOL_WEB, Element.ALIGN_CENTER, 1, addressFont);
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(6f);
		headerTbl.addCell(cell);
		
		return headerTbl;
	}
	
	private static PdfPTable getSchoolLogo(){
		
		PdfPTable logoTbl = new PdfPTable(1);
		
		try{
			
			Image schoolLogo = Image.getInstance("C:/sms_system_file/everest/everest_logo.jpg");;
			//schoolLogo.scaleAbsolute(10f, 20f);
			schoolLogo.scaleToFit(122f, 244f);
			schoolLogo.setAlignment(Element.ALIGN_CENTER);
			PdfPCell cell = new PdfPCell(schoolLogo);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setColspan(1);
			cell.setBorder(0);			
			logoTbl.addCell(cell);
			
		} catch(Exception e){}
		
		return logoTbl;
	}
	
	private static PdfPTable getStudentInfo(){
		
		PdfPTable studInfoTbl = new PdfPTable(2);
		studInfoTbl.setWidthPercentage(100);
		
		PdfPCell cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(0);
		cell.setColspan(2);
		cell.setFixedHeight(6f);
		studInfoTbl.addCell(cell);
		
		cell = new PdfPCell(new Phrase("STUDENT'S REPORT CARD", headerFontBig));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(0);
		cell.setColspan(2);
		cell.setFixedHeight(25f);
		studInfoTbl.addCell(cell);
		
//		cell = new PdfPCell(new Phrase(" "));
//		cell.setBorder(0);
//		cell.setColspan(2);
//		cell.setFixedHeight(10f);
//		cell.setBorderColorBottom(BaseColor.BLACK);
//		cell.setBorderWidthBottom(1);
//		studInfoTbl.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Student Name: " + stud_personal_info.get(0).getFname() + " " + stud_personal_info.get(0).getMname() + " " + (stud_personal_info.get(0).getGname().equals("GrandFather")?"":stud_personal_info.get(0).getGname()), titleFont));
		cell.setBorder(0);
		cell.setPaddingTop(5f);
		cell.setColspan(2);
		cell.setFixedHeight(25f);
		cell.setBorderColorTop(BaseColor.BLACK);
		cell.setBorderWidthTop(1);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderWidthBottom(1);
		studInfoTbl.addCell(cell);
		
		String sx = "";
		if(stud_personal_info.get(0).getSex().equalsIgnoreCase("M")){
			sx = "Male";
		} else {
			sx = "Female";
		}
				
		cell = new PdfPCell(new Phrase("Sex: " + sx, titleFont));
		cell.setPaddingTop(5f);
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(25f);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderWidthBottom(1);
		studInfoTbl.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Age: " + exrslt.getStud().getAge(), titleFont));
		cell.setPaddingTop(5f);
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(25f);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderWidthBottom(1);
		studInfoTbl.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Place of Birth: " + stud_personal_info.get(0).getPob(), titleFont));
		cell.setPaddingTop(5f);
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(25f);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderWidthBottom(1);
		studInfoTbl.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Date of Birth: " + stud_personal_info.get(0).getDob(), titleFont));
		cell.setPaddingTop(5f);
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(25f);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderWidthBottom(1);
		studInfoTbl.addCell(cell);
						
		cell = new PdfPCell(new Phrase("" + exrslt.getCl_name() + "" + exrslt.getCd_name(), titleFont));
		cell.setPaddingTop(5f);
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(25f);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderWidthBottom(1);
		studInfoTbl.addCell(cell);
				
		cell = new PdfPCell(new Phrase("Academic Year: " + exrslt.getAcademic_year() + " E.C", titleFont));
		cell.setPaddingTop(5f);
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(25f);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderWidthBottom(1);
		studInfoTbl.addCell(cell);
				
		cell = new PdfPCell(new Phrase("Result Status: " + exrslt.getStud().getRslt_status(), titleFont));
		cell.setPaddingTop(5f);
		cell.setBorder(0);
		cell.setColspan(2);
		cell.setFixedHeight(25f);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderWidthBottom(1);
		studInfoTbl.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Principal Signature: ", titleFont));
		cell.setPaddingTop(5f);
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(25f);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderWidthBottom(1);
		studInfoTbl.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Date: " + exrslt.getCur_date(), titleFont));
		cell.setPaddingTop(5f);
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(25f);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderWidthBottom(1);
		studInfoTbl.addCell(cell);
		
		cell = new PdfPCell(new Phrase("We Strive For Success!!!", titleFont));
		cell.setPaddingTop(5f);
		cell.setBorder(0);
		cell.setColspan(2);
		cell.setFixedHeight(25f);		
		cell.setBorderWidthBottom(0);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		studInfoTbl.addCell(cell);
		
		return studInfoTbl;
	}
	
	private static PdfPTable getSchoolNameWithDiffColor(){
		
		float[] columnWidths = {8f, 5f, 8f};
		
		PdfPTable nameTbl = new PdfPTable(columnWidths);
		
		schoolNameFontEng_1.setColor(new BaseColor(77, 172, 204));
		
		PdfPCell cell = new PdfPCell(new Phrase("EVEREST ", schoolNameFontEng_1));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setRowspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameTbl.addCell(cell);
		
		schoolNameFontEng_2.setColor(new BaseColor(216, 51, 47));
		
		cell = new PdfPCell(new Phrase("YOUTH ", schoolNameFontEng_2));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setRowspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		nameTbl.addCell(cell);
		
		cell = new PdfPCell(new Phrase("ACADEMY", schoolNameFontEng_3));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setRowspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameTbl.addCell(cell);
		
		return nameTbl;
	}
	
	private static PdfPTable getAmhSchoolNameWithDiffColor(){
		
		float[] columnWidths = {4f, 1f, 4f};
		
		PdfPTable nameTbl = new PdfPTable(columnWidths);
		
		BaseFont unicode;
		
		try {
			
			unicode = BaseFont.createFont(NYALA, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font_1 = new Font(unicode, 24, Font.BOLD, new BaseColor(77, 172, 204)); //(77, 172, 204)
			Font font_2 = new Font(unicode, 24, Font.BOLD, new BaseColor(216, 51, 47)); //(216, 51, 47)
			Font font_3 = new Font(unicode, 24, Font.BOLD, BaseColor.BLACK);
			
			PdfPCell cell = new PdfPCell(new Phrase(SysConstant.SCHOOL_NAME_AMH_1, font_1));
			cell.setBorder(0);
			cell.setColspan(1);
			cell.setRowspan(1);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			nameTbl.addCell(cell);
			
			cell = new PdfPCell(new Phrase(SysConstant.SCHOOL_NAME_AMH_2, font_2));
			cell.setBorder(0);
			cell.setColspan(1);
			cell.setRowspan(1);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			nameTbl.addCell(cell);
			
			cell = new PdfPCell(new Phrase(SysConstant.SCHOOL_NAME_AMH_3, font_3));
			cell.setBorder(0);
			cell.setColspan(1);
			cell.setRowspan(1);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			nameTbl.addCell(cell);
			
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return nameTbl;
	}
	
	private static PdfPTable getOroSchoolNameWithDiffColor(){
		
		float[] columnWidths = {14f, 4f, 8f};
		
		PdfPTable nameTbl = new PdfPTable(columnWidths);
				
		schoolNameFontEng_1.setColor(new BaseColor(77, 172, 204));
			
		PdfPCell cell = new PdfPCell(new Phrase(SysConstant.SCHOOL_NAME_ORO_1, schoolNameFontEng_3));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setRowspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		nameTbl.addCell(cell);
		
		schoolNameFontEng_2.setColor(new BaseColor(216, 51, 47));
		
		cell = new PdfPCell(new Phrase(SysConstant.SCHOOL_NAME_ORO_2, schoolNameFontEng_2));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setRowspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		nameTbl.addCell(cell);
		
		cell = new PdfPCell(new Phrase(SysConstant.SCHOOL_NAME_ORO_3, schoolNameFontEng_1));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setRowspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameTbl.addCell(cell);
			
		return nameTbl;
	}
	
	private static void insertCell(PdfPTable table, String text, int align, int colspan, Font font){
		
		PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
		
		cell.setHorizontalAlignment(align);
		
		cell.setBorder(0);	
		
		cell.setColspan(colspan);
		
		if(text.trim().equalsIgnoreCase("")){
			cell.setMinimumHeight(10f);
		}
		
		table.addCell(cell);
	}
		
	
	private static void addEmptyLine(Paragraph paragraph, int number){
		for(int i = 0; i < number; i++){
			paragraph.add(new Paragraph(" "));
		}
	}

	public static List<BevalBean> getFirstQuarterEvaluation() {
		return firstQuarterEvaluation;
	}

	public static void setFirstQuarterEvaluation(List<BevalBean> firstQuarterEvaluation) {
		PDFGenerator_AcademicCertificateCoverForAll.firstQuarterEvaluation = firstQuarterEvaluation;
	}

	public static List<BevalBean> getSecondQuarterEvaluation() {
		return secondQuarterEvaluation;
	}

	public static void setSecondQuarterEvaluation(List<BevalBean> secondQuarterEvaluation) {
		PDFGenerator_AcademicCertificateCoverForAll.secondQuarterEvaluation = secondQuarterEvaluation;
	}

	public static List<BevalBean> getThirdQuarterEvaluation() {
		return thirdQuarterEvaluation;
	}

	public static void setThirdQuarterEvaluation(List<BevalBean> thirdQuarterEvaluation) {
		PDFGenerator_AcademicCertificateCoverForAll.thirdQuarterEvaluation = thirdQuarterEvaluation;
	}

	public static List<BevalBean> getFourthQuarterEvaluation() {
		return fourthQuarterEvaluation;
	}

	public static void setFourthQuarterEvaluation(List<BevalBean> fourthQuarterEvaluation) {
		PDFGenerator_AcademicCertificateCoverForAll.fourthQuarterEvaluation = fourthQuarterEvaluation;
	}

	public static ExamResultBean getExrslt() {
		return exrslt;
	}

	public static void setExrslt(ExamResultBean exrslt) {
		PDFGenerator_AcademicCertificateCoverForAll.exrslt = exrslt;
	}

	public static List<StudentBean> getStud_personal_info() {
		return stud_personal_info;
	}

	public static void setStud_personal_info(List<StudentBean> stud_personal_info) {
		PDFGenerator_AcademicCertificateCoverForAll.stud_personal_info = stud_personal_info;
	}

}

//class RotateEvent extends PdfPageEventHelper{
//	public void onStartPage(PdfWriter writer, Document document){
//		writer.addPageDictEntry(PdfName.ROTATE, PdfPage.LANDSCAPE);
//	}
//}
