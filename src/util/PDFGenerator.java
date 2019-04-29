package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
//import com.sun.rowset.internal.InsertRow;

import adminClass.certificateDefaultComment.CertDefaultComBean;
import behEvaluationClass.BevalBean;
import examClass.exam_result.ExamResultBean;
import studentClass.StudentBean;
import studentClass.StudentDAO;

public class PDFGenerator {
	
	//private static String FILE = "c:/sms_system_file/everest/temp/CertificatePdf.pdf";
	public static final String FONT = "C:/windows/fonts/ARIALUNI.TTF";
	public static final String FONT_2 = "C:/windows/fonts/ARLRDBD.TTF";
	public static final String CALIBRI_FONT = "C:/windows/fonts/calibri.ttf";
	public static final String NYALA = "C:/windows/fonts/nyala.ttf";
	

	private static Font schoolNameFontEng_1 = FontFactory.getFont(FONT_2, BaseFont.CP1257, BaseFont.EMBEDDED, 15, Font.BOLD);
	
	private static Font schoolNameFontEng_2 = FontFactory.getFont(FONT_2, BaseFont.CP1257, BaseFont.EMBEDDED, 15, Font.BOLD);
	
	private static Font schoolNameFontEng_3 = FontFactory.getFont(FONT_2, BaseFont.CP1257, BaseFont.EMBEDDED, 15, Font.BOLD);
	
	
	private static Font customHeaderFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 15, Font.BOLD);
	BaseFont baseFont = customHeaderFont.getBaseFont();
	
	//private static Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
	private static Font headerFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 9, Font.BOLD);
	
	//private static Font addressFont = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);
	private static Font addressFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 9, Font.BOLD);
	
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
	private static List<BevalBean> qrtEvaluation;
	private static List<CertDefaultComBean> defaultComment;
	private static List<ExamResultBean> qrtTeacherComment;
	private static List<ExamResultBean> quarterRsltView;
	private static List<ExamResultBean> getAbsentDayList;
	private static List<ExamResultBean> firstQuarterRslt;
	private static List<ExamResultBean> secondQuarterRslt;
	
	private static String certRankPrintOption;
	
	private static byte[] stud_photo;
	
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
		document.addTitle("Quarter Result Certificate");
		document.addSubject("Everest Student Certificate");
		document.addKeywords("java, PDF, iText" + FileName);
		document.addAuthor("Yofetahe");
		document.addCreator("Fidel");		
	}
	
	public void generateCertficatePdf(ExamResultBean exrslt, List<BevalBean> quarterEvaluation, List<ExamResultBean> qrtTeacherComment, List<CertDefaultComBean> getDefaultComment, List<ExamResultBean> quarterRsltView, List<ExamResultBean> getAbsentDayList, List<ExamResultBean> firstQuarterRslt, List<ExamResultBean> secondQuarterRslt, String certRankPrintOption){
		
		try{
			
			setExrslt(exrslt);
			setQrtEvaluation(quarterEvaluation);
			setQrtTeacherComment(qrtTeacherComment);
			setDefaultComment(getDefaultComment);
			setQuarterRsltView(quarterRsltView);
			setGetAbsentDayList(getAbsentDayList);
			setFirstQuarterRslt(firstQuarterRslt);
			setSecondQuarterRslt(secondQuarterRslt);
			setCertRankPrintOption(certRankPrintOption);
			
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
		
		float[] columnWidths = {8f, 1f, 12f};
		
		PdfPTable table = new PdfPTable(columnWidths);
		table.getDefaultCell().setBorder(0);
		table.setWidthPercentage(100);
				
		PdfPCell cell = new PdfPCell();
		cell.addElement(newCertificateHeader());		
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(0);
		cell.setColspan(3);
		table.addCell(cell);
		
//		cell = new PdfPCell();
//		cell.addElement(getCertificateStudentInfo());		
//		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//		cell.setBorder(0);
//		cell.setColspan(3);
//		table.addCell(cell);
		
		cell = new PdfPCell();
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
	
	private static PdfPTable newCertificateHeader(){
		
		//float[] columnWidths = {8f, 12f};
		float[] columnWidths = {8f, 1f, 12f};
		
		PdfPTable headerTable = new PdfPTable(columnWidths);		
		//headerTable.setHorizontalAlignment(Element.ALIGN_CENTER);
		headerTable.setWidthPercentage(100);
		
		PdfPCell cell = new PdfPCell();
		cell.addElement(getCertificateHeader());		
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(0);
		cell.setColspan(1);
		headerTable.addCell(cell);
		
		insertCell(headerTable, " ", Element.ALIGN_CENTER, 1, headerFont);
		
		cell = new PdfPCell();
		cell.addElement(getCertificateStudentInfo());		
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(0);
		cell.setColspan(1);
		headerTable.addCell(cell);
		
		return headerTable;
	}
	
	private static PdfPTable getCertificateHeader(){
		
		float[] columnWidths = {2, 16};
		
		PdfPTable headerTable = new PdfPTable(columnWidths);		
		headerTable.setHorizontalAlignment(Element.ALIGN_CENTER);
		headerTable.setWidthPercentage(100);
		PdfPCell cell = new PdfPCell();
		
		cell = new PdfPCell();
		cell.addElement(getSchoolLogo());
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setColspan(1);
		cell.setRowspan(3);
		cell.setBorder(0);
		headerTable.addCell(cell);
		
		cell = new PdfPCell(getSchoolNameWithDiffColor());
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(1);
		cell.setBorder(0);
		headerTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(SysConstant.SCHOOL_ADDRESS, addressFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(1);
		cell.setBorder(0);
		headerTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(SysConstant.SCHOOL_LOCATION, addressFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(1);
		cell.setBorder(0);
		headerTable.addCell(cell);
		
		return headerTable;
	}
	
	
	
	
	private static PdfPTable getSchoolName(){
		
		PdfPTable headerTbl = new PdfPTable(1);
		
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
	
	
	
	
	
	
	
	private static PdfPTable getSchoolLogo(){
		PdfPTable logoTbl = new PdfPTable(1);
		
		try{
			
			Image schoolLogo = Image.getInstance("C:/sms_system_file/everest/everest_logo.jpg");;
			//schoolLogo.scaleAbsolute(10f, 20f);
			schoolLogo.scaleToFit(35f, 65f);
			schoolLogo.setAlignment(Element.ALIGN_RIGHT);
			PdfPCell cell = new PdfPCell(schoolLogo);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setColspan(1);
			cell.setBorder(0);			
			logoTbl.addCell(cell);
			
		} catch(Exception e){}
		
		return logoTbl;
	}
	
	private static PdfPTable getCertificateStudentInfo(){
		
		float[] columnWidths = {12f, 18f, 18f};
		
		PdfPTable infoTable = new PdfPTable(columnWidths);		
		infoTable.setWidthPercentage(100);
		
		String gfather = "";
		if(!exrslt.getGname().equalsIgnoreCase("grandfather")){
			gfather = exrslt.getGname();
		}
		
		StudentBean s = new StudentBean();
		s.setSi_id(exrslt.getSi_id());
		
		PdfPCell cell = new PdfPCell(getStudentPhoto(StudentDAO.getStudentPhoto(s)));
		cell.setColspan(1);
		cell.setRowspan(3);
		cell.setBorder(0);
		cell.setBorderWidthTop(0.5f);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderColorTop(BaseColor.BLACK);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPaddingTop(2f);
		infoTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Student's Name: " + exrslt.getFname() + " " + exrslt.getMname() + " " + gfather, studInfFont));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(2);
		cell.setBorder(0);
		cell.setBorderWidthTop(0.5f);
		//cell.setBorderWidthBottom(1f);
		cell.setBorderColorTop(BaseColor.BLACK);
		//cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setFixedHeight(20f);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		infoTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Class: " + exrslt.getCl_name() + " " + exrslt.getCd_name(), studInfFont));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(1);
		cell.setBorder(0);
		cell.setBorderWidthTop(0.5f);
		//cell.setBorderWidthBottom(1f);
		cell.setBorderColorTop(BaseColor.BLACK);
		//cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setFixedHeight(20f);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		infoTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Term: " + exrslt.getAt_name(), studInfFont));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(1);
		cell.setBorder(0);
		cell.setBorderWidthTop(0.5f);
		//cell.setBorderWidthBottom(1f);
		cell.setBorderColorTop(BaseColor.BLACK);
		//cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setFixedHeight(20f);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		infoTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Tutor's Name: " + exrslt.getHomeroom_teacher_name(), studInfFont));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(1);
		cell.setBorder(0);
		cell.setBorderWidthTop(0.5f);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderColorTop(BaseColor.BLACK);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setFixedHeight(20f);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		infoTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Date: " + exrslt.getCur_date(), studInfFont));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(1);
		cell.setBorder(0);
		cell.setBorderWidthTop(0.5f);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderColorTop(BaseColor.BLACK);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setFixedHeight(20f);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		infoTable.addCell(cell);
		
		return infoTable;
		
	}
	
	private static PdfPTable getStudentPhoto(byte[] stud_photo){
		
		PdfPTable photoTbl = new PdfPTable(1);
		
		try{
			
			//Image studPhoto = Image.getInstance("C:/sms_system_file/everest/" + photoPath);
			
			Image studPhoto = Image.getInstance(stud_photo);
			
			studPhoto.scaleToFit(55f, 55f);
			studPhoto.setAlignment(Element.ALIGN_CENTER);
			PdfPCell cell = new PdfPCell(studPhoto);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setColspan(1);
			cell.setBorder(0);
			//cell.setRotation(-90);
			photoTbl.addCell(cell);
			
		} catch(Exception e){}
		
		return photoTbl;
	}
	
	private static PdfPTable certTableLeftColumn(){
		
		PdfPTable certLeftColumnTable = new PdfPTable(1);
		certLeftColumnTable.setWidthPercentage(100);
		
		PdfPCell cell = new PdfPCell();
		cell.addElement(getStudentEvaluation());
		cell.setColspan(1);
		cell.setBorder(0);
		certLeftColumnTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(0);
		cell.setFixedHeight(6f);
		cell.setColspan(1);
		certLeftColumnTable.addCell(cell);
		
		cell = new PdfPCell(getStudentEvaluationKey());
		cell.setColspan(1);
		cell.setPaddingLeft(5);
		certLeftColumnTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(0);
		cell.setFixedHeight(6f);
		cell.setColspan(1);
		certLeftColumnTable.addCell(cell);
		
		cell = new PdfPCell(getStudentComment());
		cell.setColspan(1);
		cell.setPaddingLeft(5);
		cell.setMinimumHeight(130);
		certLeftColumnTable.addCell(cell);
		
		return certLeftColumnTable;
	}
	
	private static PdfPTable certTableRightColumn(){
		
		PdfPTable certRightColumnTable = new PdfPTable(1);
		certRightColumnTable.setWidthPercentage(100);
		
		PdfPCell cell = new PdfPCell(new Phrase("Terminal Report For Grade Level", titleFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPaddingBottom(4);
		cell.setColspan(1);
		cell.setFixedHeight(20f);
		certRightColumnTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(4f);
		certRightColumnTable.addCell(cell);
		
		cell = new PdfPCell(getStudentQuarterResult());
		cell.setBorder(0);
		cell.setColspan(1);
		certRightColumnTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(4f);
		certRightColumnTable.addCell(cell);
		
		cell = new PdfPCell(getStudentAbsentInfo());
		cell.setColspan(1);
		cell.setBorder(0);
		certRightColumnTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(4f);
		certRightColumnTable.addCell(cell);
		
		cell = new PdfPCell(getChangeStatusDescription());
		cell.setColspan(1);
		certRightColumnTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setFixedHeight(4f);
		certRightColumnTable.addCell(cell);
		
		cell = new PdfPCell(getGradingSystemInfo());
		cell.setColspan(1);
		certRightColumnTable.addCell(cell);
		
		return certRightColumnTable;
	}
	
	public static PdfPTable getStudentEvaluation(){
		PdfPTable studEvalTable = new PdfPTable(2);
		try {
			studEvalTable.setWidths(new int[]{4, 2});
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		studEvalTable.setWidthPercentage(100);
		studEvalTable.setSpacingAfter(0);
		studEvalTable.setSpacingBefore(0);
		
		PdfPCell cell = new PdfPCell(new Phrase("Attitude", tblHeaderFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setFixedHeight(20f);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0.5f);
		studEvalTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Tutors Comments", tblHeaderFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setFixedHeight(20f);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0.5f);
		studEvalTable.addCell(cell);
		
		studEvalTable.setHeaderRows(1);
		
		for(int i = 0; i < qrtEvaluation.size(); i++){
			
			cell = new PdfPCell(new Phrase(qrtEvaluation.get(i).getBt_title(), tblContentFont));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			//cell.setFixedHeight(20f);
			cell.setMinimumHeight(20f);
			cell.setPaddingLeft(5);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0);
			studEvalTable.addCell(cell);
			
			cell = new PdfPCell(new Phrase(qrtEvaluation.get(i).getBg_name(), tblContentFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			//cell.setFixedHeight(20f);
			cell.setMinimumHeight(20f);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0);
			studEvalTable.addCell(cell);
		}
		
		cell = new PdfPCell(new Phrase("Tutor's Signature", tblContentFont));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setPaddingLeft(5);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0);
		cell.setFixedHeight(20f);
		studEvalTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0);
		studEvalTable.addCell(cell);
		
		return studEvalTable;
	}
	
	public static PdfPTable getStudentEvaluationKey(){
		PdfPTable studEvalKeyTable = new PdfPTable(2);
		studEvalKeyTable.setWidthPercentage(100);
		try {
			studEvalKeyTable.setWidths(new int[]{1, 4});
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		PdfPCell cell = new PdfPCell(new Phrase("Key: ", tblHeaderFont));
		cell.setBorder(0);
		cell.setRowspan(2);
		studEvalKeyTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("A - Excellent, B+ - V.Good, B - Good, C - Fair", tblContentFont));
		cell.setBorder(0);		
		studEvalKeyTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("D - Needs Improvement", tblContentFont));
		cell.setBorder(0);		
		studEvalKeyTable.addCell(cell);
		
		return studEvalKeyTable;
	}
	
	public static PdfPTable getStudentComment(){
		PdfPTable studCommentTable = new PdfPTable(1);
		studCommentTable.setWidthPercentage(90);
		
		PdfPCell cell = new PdfPCell(new Phrase("Class Teacher's/Director's Comment", headerFont));
		cell.setBorder(0);
		studCommentTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(0);
		cell.setFixedHeight(6f);
		studCommentTable.addCell(cell);
		
		if(qrtTeacherComment.size() > 0){
			
			cell = new PdfPCell(new Phrase(qrtTeacherComment.get(0).getErc_content(), tblContentFont));
			cell.setBorder(0);
			studCommentTable.addCell(cell);
		}
		if(defaultComment.size() > 0){
			
			cell = new PdfPCell(new Phrase(defaultComment.get(0).getEdc_content(), tblContentFont));
			cell.setBorder(0);
			studCommentTable.addCell(cell);
		}
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(0);
		cell.setFixedHeight(6f);
		studCommentTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Parent's Comment", headerFont));
		cell.setBorder(0);
		studCommentTable.addCell(cell);
		
		cell = new PdfPCell(getParentCommentLine());
		cell.setFixedHeight(70f);
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		cell.setBorder(0);
		studCommentTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Parent Signature _____________________________", tblContentFont));
		cell.setBorder(0);
		cell.setPaddingBottom(5f);
		studCommentTable.addCell(cell);
		
		return studCommentTable;
	}
	
	public static PdfPTable getParentCommentLine(){
		PdfPTable parentCom = new PdfPTable(1);
		parentCom.setWidthPercentage(90);
		
		PdfPCell cell = new PdfPCell(new Phrase(""));
		cell.setFixedHeight(18);
		cell.setBorderWidthTop(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthBottom(0.5f);
		parentCom.addCell(cell);
		
		cell = new PdfPCell(new Phrase(""));
		cell.setFixedHeight(18);
		cell.setBorderWidthTop(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthBottom(0.5f);
		parentCom.addCell(cell);
		
		cell = new PdfPCell(new Phrase(""));
		cell.setFixedHeight(18);
		cell.setBorderWidthTop(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthBottom(0.5f);
		parentCom.addCell(cell);
		
		cell = new PdfPCell(new Phrase(""));
		cell.setBorder(0);
		parentCom.addCell(cell);
		
		return parentCom;
	}
	
	public static PdfPTable getStudentQuarterResult() {
		
		int columnNum = 0;
		if(Integer.parseInt(exrslt.getAt_id()) == 3){
			columnNum = 8;
		} else if(Integer.parseInt(exrslt.getAt_id()) == 2){
			columnNum = 6;
		} else {
			columnNum = 4;
		}
		
		PdfPTable studResultTable = new PdfPTable(columnNum);
		try {
			if(columnNum == 6){
				studResultTable.setWidths(new int[]{3, 3, 2, 3, 3, 3});
			} else if(columnNum == 8){
				studResultTable.setWidths(new int[]{3, 3, 2, 3, 3, 3, 3, 3});
			} else {
				studResultTable.setWidths(new int[]{3, 3, 3, 3});
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		studResultTable.setWidthPercentage(100);
		
		PdfPCell cell = new PdfPCell(new Phrase(exrslt.getAt_name(), tblHeaderFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderWidthRight(0);
		//cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0.5f);
		cell.setColspan(4);
		studResultTable.addCell(cell);
		
		if(Integer.parseInt(exrslt.getAt_id()) == 2){
			
			cell = new PdfPCell(new Phrase("1st Quarter\nTotal Mark", tblHeaderFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthLeft(0.5f);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0.5f);
			cell.setRowspan(2);
			studResultTable.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Change\nStatus", tblHeaderFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorderWidthRight(0.5f);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0.5f);
			cell.setRowspan(2);
			studResultTable.addCell(cell);
		}
		
		if(Integer.parseInt(exrslt.getAt_id()) == 3){
			
			cell = new PdfPCell(new Phrase("1st Q.\nTotal\nMark", tblHeaderFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthLeft(0.5f);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0.5f);
			cell.setRowspan(2);
			studResultTable.addCell(cell);
			
			cell = new PdfPCell(new Phrase("2nd Q.\nTotal\nMark", tblHeaderFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0.5f);
			cell.setRowspan(2);
			studResultTable.addCell(cell);
			
			cell = new PdfPCell(new Phrase("1st & 2nd Q.\nAverage\nMark", tblHeaderFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0.5f);
			cell.setRowspan(2);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			studResultTable.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Change\nStatus", tblHeaderFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorderWidthRight(0.5f);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0.5f);
			cell.setRowspan(2);
			studResultTable.addCell(cell);
		}
		
		cell = new PdfPCell(new Phrase("Subject", tblHeaderFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthBottom(0.5f);
		//cell.setBorderWidthTop(0.5f);
		studResultTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Cummulative\nAssesment", tblHeaderFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		//cell.setBorderWidthTop(0.5f);
		studResultTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Exam\nMark", tblHeaderFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		//cell.setBorderWidthTop(0.5f);
		studResultTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Total Mark", tblHeaderFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		if(Integer.parseInt(exrslt.getAt_id()) != 1){
			cell.setBorderWidthRight(0);
		}
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		//cell.setBorderWidthTop(0.5f);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		studResultTable.addCell(cell);
		
		//studResultTable.setHeaderRows(1);
		
		for(int i = 0; i < quarterRsltView.size(); i++){
			
			cell = new PdfPCell(new Phrase(quarterRsltView.get(i).getSub_name(), tblContentFont));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(5);
			cell.setFixedHeight(18f);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0);
			studResultTable.addCell(cell);
			
			cell = new PdfPCell(new Phrase(quarterRsltView.get(i).getCummulative_mark(), tblContentFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(18f);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0);
			studResultTable.addCell(cell);
			
			cell = new PdfPCell(new Phrase(quarterRsltView.get(i).getFinal_exam_mark(), tblContentFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(18f);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0);
			studResultTable.addCell(cell);
			
			String con_to_grade = quarterRsltView.get(i).getConvert_to_grade();
			if(con_to_grade.equalsIgnoreCase("no")){
				Double quarter_total = Double.parseDouble(quarterRsltView.get(i).getQuarter_total()); 
				if(quarter_total < 65.0){
					
					cell = new PdfPCell(new Phrase(String.valueOf(quarter_total) + "*", redFont));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					if(Integer.parseInt(exrslt.getAt_id()) != 1){
						cell.setBorderWidthRight(0);
					}
					cell.setBorderWidthLeft(0);
					cell.setBorderWidthBottom(0.5f);
					cell.setBorderWidthTop(0);
					studResultTable.addCell(cell);
				} else {
					
					cell = new PdfPCell(new Phrase(String.valueOf(quarter_total), tblContentFont));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					if(Integer.parseInt(exrslt.getAt_id()) != 1){
						cell.setBorderWidthRight(0);
					}
					cell.setBorderWidthLeft(0);
					cell.setBorderWidthBottom(0.5f);
					cell.setBorderWidthTop(0);
					studResultTable.addCell(cell);
				}
			} else {
				
				String grd = "" + quarterRsltView.get(i).getGrading_result();
				
				cell = new PdfPCell(new Phrase(grd.length() != 0?quarterRsltView.get(i).getGrading_result():" ", tblContentFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				if(Integer.parseInt(exrslt.getAt_id()) != 1){
					cell.setBorderWidthRight(0);
				}
				cell.setBorderWidthLeft(0);
				cell.setBorderWidthBottom(0.5f);
				cell.setBorderWidthTop(0);
				studResultTable.addCell(cell);
			}
			
			/**
			 * This will be accessed on the 2nd quarter.
			 * It generates 1st quarter column and the change status.
			 * Change status compare the 1st quarter result with the 2nd quarter result.
			 */			
			if(Integer.parseInt(exrslt.getAt_id()) == 2){
				int first_counter = 0;
				for(int j = 0; j < firstQuarterRslt.size(); j++){
					if(quarterRsltView.get(i).getSub_id().equals(firstQuarterRslt.get(j).getSub_id())){
						
						String first_con_to_grade = firstQuarterRslt.get(j).getConvert_to_grade();
						
						if(first_con_to_grade.equalsIgnoreCase("no")){
							String first_quarter_total = firstQuarterRslt.get(j).getQuarter_total();
							Double first_qrt_total = Double.parseDouble(first_quarter_total);
							
							if(first_qrt_total < 65.0){
							
								cell = new PdfPCell(new Phrase(String.valueOf(first_qrt_total) + "*", redFont));
								cell.setHorizontalAlignment(Element.ALIGN_CENTER);
								cell.setBorderWidthRight(0);
								cell.setBorderWidthLeft(0);
								cell.setBorderWidthBottom(0.5f);
								cell.setBorderWidthTop(0);
								studResultTable.addCell(cell);
								
							} else {
								
								cell = new PdfPCell(new Phrase(String.valueOf(first_qrt_total), tblContentFont));
								cell.setHorizontalAlignment(Element.ALIGN_CENTER);
								cell.setBorderWidthRight(0);
								cell.setBorderWidthLeft(0);
								cell.setBorderWidthBottom(0.5f);
								cell.setBorderWidthTop(0);
								studResultTable.addCell(cell);
							}
							
							if(first_qrt_total > Double.parseDouble(quarterRsltView.get(i).getQuarter_total())){
								
								Double diff = first_qrt_total - Double.parseDouble(quarterRsltView.get(i).getQuarter_total());
								cell = new PdfPCell();
								cell.addElement(getDecreaseStatus(diff));
								cell.setHorizontalAlignment(Element.ALIGN_CENTER);
								cell.setBorderWidthRight(0.5f);
								cell.setBorderWidthLeft(0);
								cell.setBorderWidthBottom(0.5f);
								cell.setBorderWidthTop(0);
								studResultTable.addCell(cell);

							} else if(Double.parseDouble(quarterRsltView.get(i).getQuarter_total()) > first_qrt_total) {
								
								cell = new PdfPCell();
								cell.addElement(getIncreaseStatus());
								cell.setPaddingLeft(7);
								cell.setHorizontalAlignment(Element.ALIGN_CENTER);
								cell.setBorderWidthRight(0.5f);
								cell.setBorderWidthLeft(0);
								cell.setBorderWidthBottom(0.5f);
								cell.setBorderWidthTop(0);
								studResultTable.addCell(cell);
								
							} else {
								
								cell = new PdfPCell(new Phrase(" ", tblContentFont));
								cell.setHorizontalAlignment(Element.ALIGN_CENTER);
								cell.setBorderWidthRight(0.5f);
								cell.setBorderWidthLeft(0);
								cell.setBorderWidthBottom(0.5f);
								cell.setBorderWidthTop(0);
								studResultTable.addCell(cell);
							}
							
	//						cell = new PdfPCell(new Phrase("-", tblContentFont));
	//						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	//						studResultTable.addCell(cell);
							
						} else {
							String grd = "" + firstQuarterRslt.get(j).getGrading_result();
							
							cell = new PdfPCell(new Phrase(grd.length() !=0?firstQuarterRslt.get(j).getGrading_result():" ", tblContentFont));
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setBorderWidthRight(0);
							cell.setBorderWidthLeft(0);
							cell.setBorderWidthBottom(0.5f);
							cell.setBorderWidthTop(0);
							studResultTable.addCell(cell);
							
							cell = new PdfPCell(new Phrase(" ", tblContentFont));
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setBorderWidthRight(0.5f);
							cell.setBorderWidthLeft(0);
							cell.setBorderWidthBottom(0.5f);
							cell.setBorderWidthTop(0);
							studResultTable.addCell(cell);
						}
					} else {
						first_counter++;
					}
					
					if(first_counter == firstQuarterRslt.size()){
						
						cell = new PdfPCell(new Phrase("-", tblContentFont));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setBorderWidthRight(0);
						cell.setBorderWidthLeft(0);
						cell.setBorderWidthBottom(0.5f);
						cell.setBorderWidthTop(0);
						studResultTable.addCell(cell);
						
						cell = new PdfPCell(new Phrase(" ", tblContentFont));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setBorderWidthRight(0.5f);
						cell.setBorderWidthLeft(0);
						cell.setBorderWidthBottom(0.5f);
						cell.setBorderWidthTop(0);
						studResultTable.addCell(cell);
						
						first_counter = 0;
					}
				}
				
//				cell = new PdfPCell(new Phrase("-", tblContentFont));
//				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//				studResultTable.addCell(cell);
//				
//				cell = new PdfPCell(new Phrase("-", tblContentFont));
//				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//				studResultTable.addCell(cell);
			}
			
			
			
			/**
			 * This will be accessed on the 3rd quarter.
			 * It generates both 1st and 2nd quarter column and the change status.
			 * Change status compare the average of the 1st and 2nd quarter result and compare with the 3rd quarter result.
			 */
			if(Integer.parseInt(exrslt.getAt_id()) == 3){
				
				int first_counter = 0, second_counter = 0;
				
				Double first_qrt_total = 0.0, second_qrt_total = 0.0;				
				
				for(int j = 0; j < firstQuarterRslt.size(); j++){
					
					if(quarterRsltView.get(i).getSub_id().equals(firstQuarterRslt.get(j).getSub_id())){
						
						String first_con_to_grade = firstQuarterRslt.get(j).getConvert_to_grade();
						
						if(first_con_to_grade.equalsIgnoreCase("no")){
							
							String first_quarter_total = firstQuarterRslt.get(j).getQuarter_total();
							
							first_qrt_total = Double.parseDouble(first_quarter_total);
							
							if(first_qrt_total < 65.0){
							
								cell = new PdfPCell(new Phrase(String.valueOf(first_qrt_total) + "*", redFont));
								cell.setHorizontalAlignment(Element.ALIGN_CENTER);
								cell.setBorderWidthRight(0);
								cell.setBorderWidthLeft(0);
								cell.setBorderWidthBottom(0.5f);
								cell.setBorderWidthTop(0);
								studResultTable.addCell(cell);
								
							} else {
								
								cell = new PdfPCell(new Phrase(String.valueOf(first_qrt_total), tblContentFont));
								cell.setHorizontalAlignment(Element.ALIGN_CENTER);
								cell.setBorderWidthRight(0);
								cell.setBorderWidthLeft(0);
								cell.setBorderWidthBottom(0.5f);
								cell.setBorderWidthTop(0);
								studResultTable.addCell(cell);
							}
														
						} else {
							
							String grd = "" + firstQuarterRslt.get(j).getGrading_result();
							
							cell = new PdfPCell(new Phrase(grd.length() !=0?firstQuarterRslt.get(j).getGrading_result():" ", tblContentFont));
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setBorderWidthRight(0);
							cell.setBorderWidthLeft(0);
							cell.setBorderWidthBottom(0.5f);
							cell.setBorderWidthTop(0);
							studResultTable.addCell(cell);
							
//							cell = new PdfPCell(new Phrase(" ", tblContentFont));
//							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//							cell.setBorderWidthRight(0.5f);
//							cell.setBorderWidthLeft(0);
//							cell.setBorderWidthBottom(0.5f);
//							cell.setBorderWidthTop(0);
//							studResultTable.addCell(cell);
						}
					} else {
						first_counter++;
					}
					
					if(first_counter == firstQuarterRslt.size()){
						
						cell = new PdfPCell(new Phrase("-", tblContentFont));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setBorderWidthRight(0);
						cell.setBorderWidthLeft(0);
						cell.setBorderWidthBottom(0.5f);
						cell.setBorderWidthTop(0);
						studResultTable.addCell(cell);
						
//						cell = new PdfPCell(new Phrase(" ", tblContentFont));
//						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//						cell.setBorderWidthRight(0.5f);
//						cell.setBorderWidthLeft(0);
//						cell.setBorderWidthBottom(0.5f);
//						cell.setBorderWidthTop(0);
//						studResultTable.addCell(cell);
						
						first_counter = 0;
					}
				}
				
				for(int j = 0; j < secondQuarterRslt.size(); j++){
					
					if(quarterRsltView.get(i).getSub_id().equals(secondQuarterRslt.get(j).getSub_id())){
						
						String second_con_to_grade = secondQuarterRslt.get(j).getConvert_to_grade();
						
						if(second_con_to_grade.equalsIgnoreCase("no")){
							
							String second_quarter_total = secondQuarterRslt.get(j).getQuarter_total();
							
							second_qrt_total = Double.parseDouble(second_quarter_total);
							
							if(second_qrt_total < 65.0){
							
								cell = new PdfPCell(new Phrase(String.valueOf(second_qrt_total) + "*", redFont));
								cell.setHorizontalAlignment(Element.ALIGN_CENTER);
								cell.setBorderWidthRight(0);
								cell.setBorderWidthLeft(0);
								cell.setBorderWidthBottom(0.5f);
								cell.setBorderWidthTop(0);
								studResultTable.addCell(cell);
								
							} else {
								
								cell = new PdfPCell(new Phrase(String.valueOf(second_qrt_total), tblContentFont));
								cell.setHorizontalAlignment(Element.ALIGN_CENTER);
								cell.setBorderWidthRight(0);
								cell.setBorderWidthLeft(0);
								cell.setBorderWidthBottom(0.5f);
								cell.setBorderWidthTop(0);
								studResultTable.addCell(cell);
							}
							
							cell = new PdfPCell(new Phrase(String.valueOf((first_qrt_total + second_qrt_total)/2), tblContentFont));
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setBorderWidthRight(0);
							cell.setBorderWidthLeft(0);
							cell.setBorderWidthBottom(0.5f);
							cell.setBorderWidthTop(0);
							studResultTable.addCell(cell);
							
							if((first_qrt_total + second_qrt_total)/2 > Double.parseDouble(quarterRsltView.get(i).getQuarter_total())){
								
								Double diff = (first_qrt_total + second_qrt_total)/2 - Double.parseDouble(quarterRsltView.get(i).getQuarter_total());
								cell = new PdfPCell();
								cell.addElement(getDecreaseStatus(diff));
								cell.setHorizontalAlignment(Element.ALIGN_CENTER);
								cell.setBorderWidthRight(0.5f);
								cell.setBorderWidthLeft(0);
								cell.setBorderWidthBottom(0.5f);
								cell.setBorderWidthTop(0);
								studResultTable.addCell(cell);

							} else if(Double.parseDouble(quarterRsltView.get(i).getQuarter_total()) > (first_qrt_total + second_qrt_total)/2) {
								
								cell = new PdfPCell();
								cell.addElement(getIncreaseStatus());
								cell.setPaddingLeft(7);
								cell.setHorizontalAlignment(Element.ALIGN_CENTER);
								cell.setBorderWidthRight(0.5f);
								cell.setBorderWidthLeft(0);
								cell.setBorderWidthBottom(0.5f);
								cell.setBorderWidthTop(0);
								studResultTable.addCell(cell);
								
							} else {
								
								cell = new PdfPCell(new Phrase(" ", tblContentFont));
								cell.setHorizontalAlignment(Element.ALIGN_CENTER);
								cell.setBorderWidthRight(0.5f);
								cell.setBorderWidthLeft(0);
								cell.setBorderWidthBottom(0.5f);
								cell.setBorderWidthTop(0);
								studResultTable.addCell(cell);
							}
							
	//						cell = new PdfPCell(new Phrase("-", tblContentFont));
	//						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	//						studResultTable.addCell(cell);
							
						} else {
							
							String grd = "" + secondQuarterRslt.get(j).getGrading_result();
							
							cell = new PdfPCell(new Phrase(grd.length() !=0?secondQuarterRslt.get(j).getGrading_result():" ", tblContentFont));
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setBorderWidthRight(0);
							cell.setBorderWidthLeft(0);
							cell.setBorderWidthBottom(0.5f);
							cell.setBorderWidthTop(0);
							studResultTable.addCell(cell);
							
							cell = new PdfPCell(new Phrase(" ", tblContentFont));
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setBorderWidthRight(0);
							cell.setBorderWidthLeft(0);
							cell.setBorderWidthBottom(0.5f);
							cell.setBorderWidthTop(0);
							studResultTable.addCell(cell);
							
							cell = new PdfPCell(new Phrase(" ", tblContentFont));
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setBorderWidthRight(0.5f);
							cell.setBorderWidthLeft(0);
							cell.setBorderWidthBottom(0.5f);
							cell.setBorderWidthTop(0);
							studResultTable.addCell(cell);
						}
					} else {
						second_counter++;
					}
					
					if(second_counter == secondQuarterRslt.size()){
						
						cell = new PdfPCell(new Phrase("-", tblContentFont));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setBorderWidthRight(0);
						cell.setBorderWidthLeft(0);
						cell.setBorderWidthBottom(0.5f);
						cell.setBorderWidthTop(0);
						studResultTable.addCell(cell);
						
						cell = new PdfPCell(new Phrase(" ", tblContentFont));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setBorderWidthRight(0);
						cell.setBorderWidthLeft(0);
						cell.setBorderWidthBottom(0.5f);
						cell.setBorderWidthTop(0);
						studResultTable.addCell(cell);
						
						cell = new PdfPCell(new Phrase(" ", tblContentFont));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setBorderWidthRight(0.5f);
						cell.setBorderWidthLeft(0);
						cell.setBorderWidthBottom(0.5f);
						cell.setBorderWidthTop(0);
						studResultTable.addCell(cell);
						
						second_counter = 0;
					}
				}
				
//				cell = new PdfPCell(new Phrase("-", tblContentFont));
//				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//				studResultTable.addCell(cell);
//				
//				cell = new PdfPCell(new Phrase("-", tblContentFont));
//				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//				studResultTable.addCell(cell);
			}
			
		}
				
		
		
		cell = new PdfPCell(new Phrase("Sum", tblContentBoldFont));
		cell.setColspan(3);
		cell.setPaddingLeft(5);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0.5f);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0);
		studResultTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(exrslt.getGrand_quarter_total(), tblContentBoldFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		if(Integer.parseInt(exrslt.getAt_id()) != 1){
			cell.setBorderWidthRight(0);
		}
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0);
		studResultTable.addCell(cell);
		
		if(Integer.parseInt(exrslt.getAt_id()) == 2){
			
			cell = new PdfPCell(new Phrase(" "));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0);
			studResultTable.addCell(cell);
			
			cell = new PdfPCell(new Phrase(" "));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorderWidthRight(0.5f);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0);
			studResultTable.addCell(cell);
		}
		
		if(Integer.parseInt(exrslt.getAt_id()) == 3){
			cell = new PdfPCell(new Phrase(" "));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0);
			studResultTable.addCell(cell);
			
			cell = new PdfPCell(new Phrase(" "));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0);
			studResultTable.addCell(cell);
			
			cell = new PdfPCell(new Phrase(" "));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0);
			studResultTable.addCell(cell);
			
			cell = new PdfPCell(new Phrase(" "));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorderWidthRight(0.5f);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0);
			studResultTable.addCell(cell);
		}
		
		cell = new PdfPCell(new Phrase("Average", tblContentBoldFont));
		cell.setColspan(3);
		cell.setPaddingLeft(5);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0.5f);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0);
		studResultTable.addCell(cell);
		
		DecimalFormat fmt = new DecimalFormat("###.##");
		String avg = fmt.format(Double.parseDouble(exrslt.getAverage_quarter_mark()));
		
		cell = new PdfPCell(new Phrase(avg, tblContentBoldFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		if(Integer.parseInt(exrslt.getAt_id()) != 1){
		cell.setBorderWidthRight(0);
		}
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0);
		studResultTable.addCell(cell);
		
		if(Integer.parseInt(exrslt.getAt_id()) == 2){
			cell = new PdfPCell(new Phrase(" "));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0);
			studResultTable.addCell(cell);
			
			cell = new PdfPCell(new Phrase(" "));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorderWidthRight(0.5f);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0);
			studResultTable.addCell(cell);
		}
		
		if(Integer.parseInt(exrslt.getAt_id()) == 3){
			cell = new PdfPCell(new Phrase(" "));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0);
			studResultTable.addCell(cell);
			
			cell = new PdfPCell(new Phrase(" "));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0);
			studResultTable.addCell(cell);
			
			cell = new PdfPCell(new Phrase(" "));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0);
			studResultTable.addCell(cell);
			
			cell = new PdfPCell(new Phrase(" "));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorderWidthRight(0.5f);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0);
			studResultTable.addCell(cell);
		}
		
		if(getCertRankPrintOption().equals("Yes")) {
			cell = new PdfPCell(new Phrase("Rank", tblContentBoldFont));
			cell.setColspan(3);
			cell.setPaddingLeft(5);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthLeft(0.5f);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0);
			studResultTable.addCell(cell);		
		
			cell = new PdfPCell(new Phrase(exrslt.getStud_rank() + "/" + exrslt.getStud().getTotal_number(), tblContentBoldFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			if(Integer.parseInt(exrslt.getAt_id()) != 1){
				cell.setBorderWidthRight(0);
			}
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0);
			studResultTable.addCell(cell);
			
			if(Integer.parseInt(exrslt.getAt_id()) == 2){
				cell = new PdfPCell(new Phrase(" "));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorderWidthRight(0);
				cell.setBorderWidthLeft(0);
				cell.setBorderWidthBottom(0.5f);
				cell.setBorderWidthTop(0);
				studResultTable.addCell(cell);
				
				cell = new PdfPCell(new Phrase(" "));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorderWidthRight(0.5f);
				cell.setBorderWidthLeft(0);
				cell.setBorderWidthBottom(0.5f);
				cell.setBorderWidthTop(0);
				studResultTable.addCell(cell);
			}
			
			if(Integer.parseInt(exrslt.getAt_id()) == 3){
				cell = new PdfPCell(new Phrase(" "));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorderWidthRight(0);
				cell.setBorderWidthLeft(0);
				cell.setBorderWidthBottom(0.5f);
				cell.setBorderWidthTop(0);
				studResultTable.addCell(cell);
				
				cell = new PdfPCell(new Phrase(" "));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorderWidthRight(0);
				cell.setBorderWidthLeft(0);
				cell.setBorderWidthBottom(0.5f);
				cell.setBorderWidthTop(0);
				studResultTable.addCell(cell);
				
				cell = new PdfPCell(new Phrase(" "));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorderWidthRight(0);
				cell.setBorderWidthLeft(0);
				cell.setBorderWidthBottom(0.5f);
				cell.setBorderWidthTop(0);
				studResultTable.addCell(cell);
				
				cell = new PdfPCell(new Phrase(" "));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorderWidthRight(0.5f);
				cell.setBorderWidthLeft(0);
				cell.setBorderWidthBottom(0.5f);
				cell.setBorderWidthTop(0);
				studResultTable.addCell(cell);
			}
		}
		
		return studResultTable;
	}
	
	private static PdfPTable getIncreaseStatus(){
		PdfPTable increaseTbl = new PdfPTable(2);
		
		try{
			
			Image increaseImage = Image.getInstance("C:/sms_system_file/everest/up_images.jpeg");
			increaseImage.scaleToFit(20f, 20f);
			PdfPCell cell = new PdfPCell(increaseImage);			
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setColspan(1);
			cell.setBorder(0);
			increaseTbl.addCell(cell);
			
			cell = new PdfPCell(new Phrase(" "));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setBorder(0);
			increaseTbl.addCell(cell);
			
		} catch(Exception e){}
		
		return increaseTbl;
	}
	
	private static PdfPTable getDecreaseStatus(Double diff){
		PdfPTable decreaseTbl = new PdfPTable(2);
		
		try{
			
			Image decreaseImage = Image.getInstance("C:/sms_system_file/everest/decrease_images.jpeg");			
			decreaseImage.scaleToFit(12f, 12f);
			PdfPCell cell = new PdfPCell(decreaseImage);			
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setColspan(1);
			cell.setBorder(0);
			decreaseTbl.addCell(cell);
			
			if(diff >= 10){
				cell = new PdfPCell(new Phrase("(***)", redFont));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorder(0);
				decreaseTbl.addCell(cell);
			} else if(diff < 10 && diff >= 5){
				cell = new PdfPCell(new Phrase("(**)", redFont));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorder(0);
				decreaseTbl.addCell(cell);
			} else {
				cell = new PdfPCell(new Phrase("(*)", redFont));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorder(0);
				decreaseTbl.addCell(cell);
			}
			
		} catch(Exception e){}
				
		return decreaseTbl;
	}
	
	private static PdfPTable getStudentAbsentInfo(){
		PdfPTable studAbsentTable = new PdfPTable(1);
		studAbsentTable.setWidthPercentage(100);
		
		PdfPCell cell = new PdfPCell();
		cell.addElement(getNumberOfAbsent());
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		studAbsentTable.addCell(cell);
		
		cell = new PdfPCell();
		cell.addElement(getDaysListOfAbsent());
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		studAbsentTable.addCell(cell);
		
		return studAbsentTable;
	}
	
	private static PdfPTable getNumberOfAbsent(){
		PdfPTable studAbsentNumTable = new PdfPTable(2);
		try {
			studAbsentNumTable.setWidths(new int[]{2, 5});
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		studAbsentNumTable.setWidthPercentage(100);
		
		PdfPCell cell = new PdfPCell(new Phrase("Number of Absent Days: ", tblHeaderFont));
		cell.setBorder(0);
		cell.setPaddingLeft(5);
		studAbsentNumTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(getAbsentDayList.size() != 0?getAbsentDayList.get(0).getNoOfAbsentDay():"", tblContentFont));
		cell.setBorder(0);
		studAbsentNumTable.addCell(cell);
		
		return studAbsentNumTable;
	}
	
	private static PdfPTable getDaysListOfAbsent(){
		
		PdfPTable studAbsentDaysListTable = new PdfPTable(2);
		try {
			studAbsentDaysListTable.setWidths(new int[]{1, 4});
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		studAbsentDaysListTable.setWidthPercentage(100);
		
		PdfPCell cell = new PdfPCell(new Phrase("Days List: ", tblHeaderFont));
		cell.setBorder(0);
		cell.setPaddingLeft(5);
		studAbsentDaysListTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(getAbsentDayList.size() != 0?getAbsentDayList.get(0).getAbsentDayList():"", tblContentFont));
		cell.setBorder(0);
		studAbsentDaysListTable.addCell(cell);
				
		return studAbsentDaysListTable;
	}
	
	private static PdfPTable getChangeStatusDescription(){
				
		PdfPTable changeStatusDescTable = new PdfPTable(2);
		try {
			changeStatusDescTable.setWidths(new int[]{1, 4});
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		changeStatusDescTable.setWidthPercentage(100);
		
		PdfPCell cell = new PdfPCell(new Phrase("Change Status Key: ", tblHeaderFont));
		cell.setPaddingLeft(5);
		cell.setFixedHeight(35f);
		cell.setBorder(0);
		changeStatusDescTable.addCell(cell);
		
		cell = new PdfPCell();
		cell.addElement(getChangeStatusContent());
		cell.setFixedHeight(35f);
		cell.setBorder(0);
		changeStatusDescTable.addCell(cell);
		
		return changeStatusDescTable;
	}
	
	private static PdfPTable getChangeStatusContent(){
		
		PdfPTable contTbl = new PdfPTable(5);
		try {
			contTbl.setWidths(new int[]{1, 5, 1, 5, 12});
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		contTbl.setWidthPercentage(100);
		
		try {
			Image increaseImage = Image.getInstance("C:/sms_system_file/everest/up_images.jpeg");
			increaseImage.scaleToFit(15f, 15f);
			
			PdfPCell cell = new PdfPCell();
			cell.addElement(increaseImage);
			cell.setBorder(0);
			contTbl.addCell(cell);
			
			cell = new PdfPCell(new Phrase("- Increase", tblContentFont));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setBorder(0);
			contTbl.addCell(cell);
			
			Image decreaseImage = Image.getInstance("C:/sms_system_file/everest/decrease_images.jpeg");
			decreaseImage.scaleToFit(10f, 10f);
			
			cell = new PdfPCell();
			cell.addElement(decreaseImage);
			cell.setBorder(0);
			contTbl.addCell(cell);
			
			cell = new PdfPCell(new Phrase("- Decrease", tblContentFont));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setBorder(0);
			contTbl.addCell(cell);
			
			cell = new PdfPCell(new Phrase("(*) - decrease by 1 to 5 marks", tblContentFont));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setBorder(0);
			contTbl.addCell(cell);
			
			cell = new PdfPCell(new Phrase("(**) - decrease by 5 to 10 marks, (***) - decrease by more than 10 marks", tblContentFont));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setColspan(5);
			cell.setBorder(0);
			contTbl.addCell(cell);
			
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return contTbl;
	}
	
	private static PdfPTable getGradingSystemInfo(){
		PdfPTable gradingSystemInfoTable = new PdfPTable(4);
		gradingSystemInfoTable.setWidthPercentage(100);
		try {
			gradingSystemInfoTable.setWidths(new int[]{3, 4, 4, 4});
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		PdfPCell cell = new PdfPCell(new Phrase("Grading System", tblHeaderFont));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setRowspan(2);
		cell.setBorder(0);
		cell.setPaddingLeft(5);
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
	
	
	
	/////************* Original PDF Generation Example *************/////
	public static void addMetaData(Document document){
		document.addTitle("Quarter Result Certificate");
		document.addSubject("Everest Student Certificate");
		document.addKeywords("java, PDF, iText");
		document.addAuthor("yof");
		document.addCreator("Fidel");
	}
	
	public static void addTitlePage(Document document) throws DocumentException{
		
		Paragraph preface = new Paragraph();
		addEmptyLine(preface, 1);
		preface.add(new Paragraph("Title of the document", headerFont));
		addEmptyLine(preface, 1);
		preface.add(new Paragraph("Report generated by: " + System.getProperty("user.name") +  ", " + new Date(), smallFont));
		addEmptyLine(preface, 3);
		preface.add(new Paragraph("This document is a perliminary version and anot sjubject to your license agreement or any other agreement with yofetahe", redFont));
		document.add(preface);
		document.newPage();
	}
	
	public static void addContent(Document document) throws DocumentException{
		
		///*** creating a new page
		Anchor anchor = new Anchor("First Chapter", headerFont);
		anchor.setName("First Chapter");		
		Chapter catPart = new Chapter(new Paragraph(anchor), 1);		
		
		Paragraph subPara = new Paragraph("Subcategory 1", subFont);
		Section subCatPart = catPart.addSection(subPara);
		catPart.addSection("Hello");
		
		subPara = new Paragraph("Subcategory 2", subFont);
		subCatPart = catPart.addSection(subPara);
		subCatPart.add(new Paragraph("Paragraph 1"));
		subCatPart.add(new Paragraph("Paragraph 2"));
		subCatPart.add(new Paragraph("Paragraph 3"));
		
		createList(subCatPart);
		
		Paragraph paragraph = new Paragraph();
		
		addEmptyLine(paragraph, 5);
		
		subCatPart.add(paragraph);
		
		createTable(subCatPart);
		
		document.add(catPart);
		
		///*** creating a new page
		anchor = new Anchor("Second Chapter", headerFont);
		anchor.setName("Second Chapter");		
		catPart = new Chapter(new Paragraph(anchor), 1);
		
		subPara = new Paragraph("Subcategory", subFont);
		
		subCatPart = catPart.addSection(subPara);
		
		subCatPart.add(new Paragraph("This is a very important message"));
		
		document.add(catPart);
	}
	
	private static void createTable(Section subCatPart) throws BadElementException{
		
		PdfPTable table = new PdfPTable(3);
		
		PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));		
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Table Header 2"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Table Header 3"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		table.setHeaderRows(1);
		table.addCell("1.0");
		table.addCell("1.1");
		table.addCell("1.2");
		table.addCell("2.1");
		table.addCell("2.2");
		table.addCell("2.3");
		
		subCatPart.add(table);
	}
	
	private static void createList(Section subCatPart){
		
//		List list = new List(true, false, 10);
//		list.add(new ListItem("First Point"));
//		list.add(new ListItem("Second Point"));
//		list.add(new ListItem("Third Point"));
//		subCatPart.add(list);
	}
	
	private static void addEmptyLine(Paragraph paragraph, int number){
		for(int i = 0; i < number; i++){
			paragraph.add(new Paragraph(" "));
		}
	}

	public static List<BevalBean> getQrtEvaluation() {
		return qrtEvaluation;
	}

	public static void setQrtEvaluation(List<BevalBean> qrtEvaluation) {
		PDFGenerator.qrtEvaluation = qrtEvaluation;
	}

	public static List<CertDefaultComBean> getDefaultComment() {
		return defaultComment;
	}

	public static void setDefaultComment(List<CertDefaultComBean> defaultComment) {
		PDFGenerator.defaultComment = defaultComment;
	}

	public static List<ExamResultBean> getQrtTeacherComment() {
		return qrtTeacherComment;
	}

	public static void setQrtTeacherComment(List<ExamResultBean> qrtTeacherComment) {
		PDFGenerator.qrtTeacherComment = qrtTeacherComment;
	}

	public static List<ExamResultBean> getQuarterRsltView() {
		return quarterRsltView;
	}

	public static void setQuarterRsltView(List<ExamResultBean> quarterRsltView) {
		PDFGenerator.quarterRsltView = quarterRsltView;
	}

	public static ExamResultBean getExrslt() {
		return exrslt;
	}

	public static void setExrslt(ExamResultBean exrslt) {
		PDFGenerator.exrslt = exrslt;
	}

	public static List<ExamResultBean> getGetAbsentDayList() {
		return getAbsentDayList;
	}

	public static void setGetAbsentDayList(List<ExamResultBean> getAbsentDayList) {
		PDFGenerator.getAbsentDayList = getAbsentDayList;
	}

	public static List<ExamResultBean> getFirstQuarterRslt() {
		return firstQuarterRslt;
	}

	public static void setFirstQuarterRslt(List<ExamResultBean> firstQuarterRslt) {
		PDFGenerator.firstQuarterRslt = firstQuarterRslt;
	}

	public static List<ExamResultBean> getSecondQuarterRslt() {
		return secondQuarterRslt;
	}

	public static void setSecondQuarterRslt(List<ExamResultBean> secondQuarterRslt) {
		PDFGenerator.secondQuarterRslt = secondQuarterRslt;
	}

	public static byte[] getStud_photo() {
		return stud_photo;
	}

	public static void setStud_photo(byte[] stud_photo) {
		PDFGenerator.stud_photo = stud_photo;
	}

	public static String getCertRankPrintOption() {
		return certRankPrintOption;
	}

	public static void setCertRankPrintOption(String certRankPrintOption) {
		PDFGenerator.certRankPrintOption = certRankPrintOption;
	}

}

class RotateEvent extends PdfPageEventHelper{
	public void onStartPage(PdfWriter writer, Document document){
		writer.addPageDictEntry(PdfName.ROTATE, PdfPage.LANDSCAPE);
	}
}
