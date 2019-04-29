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
import java.util.concurrent.ThreadLocalRandom;

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

public class PDFGenerator_AcademicCertificateForAll {
	
	//private static String FILE = "c:/sms_system_file/everest/temp/CertificatePdf.pdf";
	public static final String FONT = "C:/windows/fonts/ARIALUNI.TTF";
	public static final String CALIBRI_FONT = "C:/windows/fonts/calibri.ttf";
	
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
	
	
//	private static List<BevalBean> qrtEvaluation;
//	private static List<CertDefaultComBean> defaultComment;
//	private static List<ExamResultBean> qrtTeacherComment;
//	private static List<ExamResultBean> quarterRsltView;
//	private static List<ExamResultBean> getAbsentDayList;
	
	private static ExamResultBean exrslt;
	private static List<String> subjectList;
	
	private static List<ExamResultBean> firstQuarterRslt;	
	private static List<ExamResultBean> secondQuarterRslt;
	private static List<ExamResultBean> thirdQuarterRslt;	
	private static List<ExamResultBean> fourthQuarterRslt;
	
	private static List<ExamResultBean> firstQuarterTeacherComment;
	private static List<ExamResultBean> secondQuarterTeacherComment;
	private static List<ExamResultBean> thirdQuarterTeacherComment;
	private static List<ExamResultBean> fourthQuarterTeacherComment;
	
	private static List<CertDefaultComBean> firstQuarterDefaultComment;
	private static List<CertDefaultComBean> secondQuarterDefaultComment;
	private static List<CertDefaultComBean> thirdQuarterDefaultComment;
	private static List<CertDefaultComBean> fourthQuarterDefaultComment;
	
	private static List<ExamResultBean> fullYearAvgRslt;
	private static List<ExamResultBean> fullYearStudRank;
	
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
		document.addSubject("Everest Student Certificate");
		document.addKeywords("java, PDF, iText" + FileName);
		document.addAuthor("Yofetahe");
		document.addCreator("Fidel");		
	}
	
	public void generateCertficatePdf(ExamResultBean exrslt, List<ExamResultBean> firstQuarterRslt, List<ExamResultBean> secondQuarterRslt, List<ExamResultBean> thirdQuarterRslt, List<ExamResultBean> fourthQuarterRslt, 
			List<ExamResultBean> firstQuarterTeacherComment, List<ExamResultBean> secondQuarterTeacherComment, List<ExamResultBean> thirdQuarterTeacherComment, List<ExamResultBean> fourthQuarterTeacherComment, 
			List<CertDefaultComBean> firstQuarterDefaultComment, List<CertDefaultComBean> secondQuarterDefaultComment, List<CertDefaultComBean> thirdQuarterDefaultComment, List<CertDefaultComBean> fourthQuarterDefaultComment, 
			List<ExamResultBean> fullYearAvgRslt, List<ExamResultBean> fullYearStudRank){
		
		try{
			
			setExrslt(exrslt);
						
			setFirstQuarterRslt(firstQuarterRslt);
			setSecondQuarterRslt(secondQuarterRslt);
			setThirdQuarterRslt(thirdQuarterRslt);
			setFourthQuarterRslt(fourthQuarterRslt);
			
			setFirstQuarterTeacherComment(firstQuarterTeacherComment);
			setSecondQuarterTeacherComment(secondQuarterTeacherComment);
			setThirdQuarterTeacherComment(thirdQuarterTeacherComment);
			setFourthQuarterTeacherComment(fourthQuarterTeacherComment);
			
			setFirstQuarterDefaultComment(firstQuarterDefaultComment);
			setSecondQuarterDefaultComment(secondQuarterDefaultComment);
			setThirdQuarterDefaultComment(thirdQuarterDefaultComment);
			setFourthQuarterDefaultComment(fourthQuarterDefaultComment);
			
			setFullYearAvgRslt(fullYearAvgRslt);
			setFullYearStudRank(fullYearStudRank);
			
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
	
	private static PdfPTable certTableLeftColumn(){
		
		PdfPTable certRightColumnTable = new PdfPTable(1);
		certRightColumnTable.setWidthPercentage(100);
		
		PdfPCell cell = new PdfPCell(new Phrase("ANNUAL REPORT", titleFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPaddingBottom(4);
		cell.setColspan(1);
		cell.setFixedHeight(20f);
		cell.setBorder(0);
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
				
		return certRightColumnTable;
	}
	
	private static PdfPTable certTableRightColumn(){
		
		PdfPTable certLeftColumnTable = new PdfPTable(1);
		certLeftColumnTable.getDefaultCell().setBorder(0);
		certLeftColumnTable.setWidthPercentage(100);
		
		PdfPCell cell = new PdfPCell(getStudentComment());
		cell.setColspan(1);
		cell.setBorder(0);
		cell.setPaddingLeft(5);
		cell.setMinimumHeight(130);
		certLeftColumnTable.addCell(cell);
		
		return certLeftColumnTable;
	}
		
	public static PdfPTable getStudentComment(){
		
		int msg_counter = 0;
		
		PdfPTable studCommentTable = new PdfPTable(2);
		studCommentTable.getDefaultCell().setBorder(0);
		studCommentTable.setWidthPercentage(90);
		
		PdfPCell cell = new PdfPCell(new Phrase("TEACHER'S COMMENT AND RECOMMENDATION", titleFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(0);
		cell.setColspan(2);
		studCommentTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(0);
		cell.setColspan(2);
		cell.setFixedHeight(10f);
		studCommentTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("First Quarter", headerFont));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(0);
		cell.setColspan(2);
		cell.setFixedHeight(20f);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0.5f);
		studCommentTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(0);
		cell.setColspan(2);
		cell.setFixedHeight(10f);
		studCommentTable.addCell(cell);
		
		if(firstQuarterTeacherComment.size() > 0 && firstQuarterDefaultComment.size() == 0){
			
			cell = new PdfPCell(new Phrase(firstQuarterTeacherComment.get(0).getErc_content(), tblContentFont));
			cell.setBorder(0);
			cell.setFixedHeight(25f);
			cell.setColspan(2);
			studCommentTable.addCell(cell);
			
		} else if(firstQuarterTeacherComment.size() == 0 && firstQuarterDefaultComment.size() > 0){
			
			int firstQuarter_comment_index = ThreadLocalRandom.current().nextInt(0, firstQuarterDefaultComment.size());
			
			cell = new PdfPCell(new Phrase(firstQuarterDefaultComment.get(firstQuarter_comment_index).getEdc_content(), tblContentFont));
			cell.setBorder(0);
			cell.setFixedHeight(25f);
			cell.setColspan(2);
			studCommentTable.addCell(cell);
			
		} else if(firstQuarterTeacherComment.size() > 0 && firstQuarterDefaultComment.size() > 0){
			
			int firstQuarter_comment_index = ThreadLocalRandom.current().nextInt(0, firstQuarterDefaultComment.size());
			
			cell = new PdfPCell(new Phrase(firstQuarterTeacherComment.get(0).getErc_content() + " " + firstQuarterDefaultComment.get(firstQuarter_comment_index).getEdc_content(), tblContentFont));
			cell.setBorder(0);
			cell.setFixedHeight(25f);
			cell.setColspan(2);
			studCommentTable.addCell(cell);
		}
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(0);
		cell.setColspan(2);
		cell.setFixedHeight(20f);
		studCommentTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Second Quarter", headerFont));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(0);
		cell.setColspan(2);
		cell.setFixedHeight(20f);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0.5f);
		studCommentTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(0);
		cell.setColspan(2);
		cell.setFixedHeight(10f);
		studCommentTable.addCell(cell);
		
		if(secondQuarterTeacherComment.size() > 0 && secondQuarterDefaultComment.size() == 0){
			
			cell = new PdfPCell(new Phrase(secondQuarterTeacherComment.get(0).getErc_content(), tblContentFont));
			cell.setBorder(0);
			cell.setFixedHeight(25f);
			cell.setColspan(2);
			studCommentTable.addCell(cell);
			
		} else if(secondQuarterTeacherComment.size() == 0 && secondQuarterDefaultComment.size() > 0){
			
			int secondQuarter_comment_index = ThreadLocalRandom.current().nextInt(0, secondQuarterDefaultComment.size());
			
			cell = new PdfPCell(new Phrase(secondQuarterDefaultComment.get(secondQuarter_comment_index).getEdc_content(), tblContentFont));
			cell.setBorder(0);
			cell.setFixedHeight(25f);
			cell.setColspan(2);
			studCommentTable.addCell(cell);
			
		} else if(secondQuarterTeacherComment.size() > 0 && secondQuarterDefaultComment.size() > 0){
			
			int secondQuarter_comment_index = ThreadLocalRandom.current().nextInt(0, secondQuarterDefaultComment.size());
			
			cell = new PdfPCell(new Phrase(secondQuarterTeacherComment.get(0).getErc_content() + " " + secondQuarterDefaultComment.get(secondQuarter_comment_index).getEdc_content(), tblContentFont));
			cell.setBorder(0);
			cell.setFixedHeight(25f);
			cell.setColspan(2);
			studCommentTable.addCell(cell);
		}
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(0);
		cell.setColspan(2);
		cell.setFixedHeight(15f);
		studCommentTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Third Quarter", headerFont));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(0);
		cell.setColspan(2);
		cell.setFixedHeight(20f);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0.5f);
		studCommentTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(0);
		cell.setColspan(2);
		cell.setFixedHeight(10f);
		studCommentTable.addCell(cell);
		
		if(thirdQuarterTeacherComment.size() > 0 && thirdQuarterDefaultComment.size() == 0){
			
			cell = new PdfPCell(new Phrase(thirdQuarterTeacherComment.get(0).getErc_content(), tblContentFont));
			cell.setBorder(0);
			cell.setFixedHeight(25f);
			cell.setColspan(2);
			studCommentTable.addCell(cell);
			
		} else if(thirdQuarterTeacherComment.size() == 0 && thirdQuarterDefaultComment.size() > 0){

			int thirdQuarter_comment_index = ThreadLocalRandom.current().nextInt(0, thirdQuarterDefaultComment.size());
			
			cell = new PdfPCell(new Phrase(thirdQuarterDefaultComment.get(thirdQuarter_comment_index).getEdc_content(), tblContentFont));
			cell.setBorder(0);
			cell.setFixedHeight(25f);
			cell.setColspan(2);
			studCommentTable.addCell(cell);
			
		} else if(thirdQuarterTeacherComment.size() > 0 && thirdQuarterDefaultComment.size() > 0){
			
			int thirdQuarter_comment_index = ThreadLocalRandom.current().nextInt(0, thirdQuarterDefaultComment.size());
			
			cell = new PdfPCell(new Phrase(thirdQuarterTeacherComment.get(0).getErc_content() + " " + thirdQuarterDefaultComment.get(thirdQuarter_comment_index).getEdc_content(), tblContentFont));
			cell.setBorder(0);
			cell.setFixedHeight(25f);
			cell.setColspan(2);
			studCommentTable.addCell(cell);
		}
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(0);
		cell.setColspan(2);
		cell.setFixedHeight(15f);
		studCommentTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Fourth Quarter", headerFont));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(0);
		cell.setColspan(2);
		cell.setFixedHeight(20f);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0.5f);
		studCommentTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(0);
		cell.setColspan(2);
		cell.setFixedHeight(10f);
		studCommentTable.addCell(cell);
		
		if(fourthQuarterTeacherComment.size() > 0 && fourthQuarterDefaultComment.size() == 0){
			
			cell = new PdfPCell(new Phrase(fourthQuarterTeacherComment.get(0).getErc_content(), tblContentFont));
			cell.setBorder(0);
			cell.setFixedHeight(25f);
			cell.setColspan(2);
			studCommentTable.addCell(cell);
			
		} else if(fourthQuarterTeacherComment.size() == 0 && fourthQuarterDefaultComment.size() > 0){

			int fourthQuarter_comment_index = ThreadLocalRandom.current().nextInt(0, fourthQuarterDefaultComment.size());
			
			cell = new PdfPCell(new Phrase(fourthQuarterDefaultComment.get(fourthQuarter_comment_index).getEdc_content(), tblContentFont));
			cell.setBorder(0);
			cell.setFixedHeight(25f);
			cell.setColspan(2);
			studCommentTable.addCell(cell);
			
		}  else if(fourthQuarterTeacherComment.size() > 0 && fourthQuarterDefaultComment.size() > 0){

			int fourthQuarter_comment_index = ThreadLocalRandom.current().nextInt(0, fourthQuarterDefaultComment.size());
			
			cell = new PdfPCell(new Phrase(fourthQuarterTeacherComment.get(0).getErc_content() + " " + fourthQuarterDefaultComment.get(fourthQuarter_comment_index).getEdc_content(), tblContentFont));
			cell.setBorder(0);
			cell.setFixedHeight(25f);
			cell.setColspan(2);
			studCommentTable.addCell(cell);
		}
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(0);
		cell.setColspan(2);
		cell.setFixedHeight(15f);
		studCommentTable.addCell(cell);
		
		StudentBean s = new StudentBean();
		s.setSi_id(exrslt.getSi_id());
		
		cell = new PdfPCell(getStudentPhoto(StudentDAO.getStudentPhoto(s)));
		cell.setBorder(0);
		cell.setColspan(2);
		//cell.setFixedHeight(20f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		studCommentTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Result Status: " + exrslt.getStud().getRslt_status(), tblContentFont));
		cell.setBorder(0);
		cell.setColspan(2);
		cell.setFixedHeight(20f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		studCommentTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(0);
		cell.setColspan(2);
		cell.setFixedHeight(40f);
		studCommentTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Teacher's Name: " + exrslt.getHomeroom_teacher_name(), tblContentFont));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setPaddingBottom(5f);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderWidthBottom(1);
		studCommentTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Teacher's Signature ", tblContentFont));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setPaddingBottom(5f);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderWidthBottom(1);
		studCommentTable.addCell(cell);
		
		return studCommentTable;
	}
	
	public static PdfPTable getStudentQuarterResult() {
		
		int columnNum = 6;
				
		PdfPTable studResultTable = new PdfPTable(columnNum);
		try {
			studResultTable.setWidths(new int[]{6, 3, 3, 3, 3, 3});			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		studResultTable.setWidthPercentage(100);
				
		PdfPCell cell = new PdfPCell(new Phrase("Subject", tblHeaderFont));
		cell.setPaddingLeft(5);
		cell.setFixedHeight(30f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0.5f);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0.5f);
		studResultTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("1st\nTerm", tblHeaderFont));
		cell.setPaddingTop(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0.5f);
		studResultTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("2nd\nTerm", tblHeaderFont));
		cell.setPaddingTop(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0.5f);
		studResultTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("3rd\nTerm", tblHeaderFont));
		cell.setPaddingTop(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0.5f);
		studResultTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("4th\nTerm", tblHeaderFont));
		cell.setPaddingTop(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0.5f);
		studResultTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Average\nMark", tblHeaderFont));
		cell.setPaddingTop(5);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorderWidthRight(0.5f);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0.5f);
		studResultTable.addCell(cell);
		
		int listsize = firstQuarterRslt.size();
		List<ExamResultBean> selectedQrtList = firstQuarterRslt;
		
//		if(listsize < secondQuarterRslt.size()){
//			listsize = secondQuarterRslt.size();
//			selectedQrtList = secondQuarterRslt;
//		}
//		if(listsize < thirdQuarterRslt.size()){
//			listsize = thirdQuarterRslt.size();
//			selectedQrtList = thirdQuarterRslt;
//		}
//		if(listsize < fourthQuarterRslt.size()){
//			listsize = fourthQuarterRslt.size();
//			selectedQrtList = fourthQuarterRslt;
//		}
		
		for(int i = 0, a = 0, b = 0, c = 0, d = 0; i < listsize; i++){
			
			cell = new PdfPCell(new Phrase(selectedQrtList.get(i).getSub_name(), tblContentFont));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setPaddingTop(5);
			cell.setPaddingLeft(5);
			cell.setFixedHeight(26f);
			cell.setBorderWidthLeft(0.5f);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthBottom(0.5f);
			cell.setBorderWidthTop(0);
			studResultTable.addCell(cell);
			
			String selectedQrtListSubject = ((selectedQrtList.get(i).getSub_name()+"").length() == 0 || "null".equals(selectedQrtList.get(i).getSub_name()))?"":selectedQrtList.get(i).getSub_name();
			
			String firstQrtSubject = (firstQuarterRslt.get(i).getSub_name()+"").length() == 0?"":firstQuarterRslt.get(i).getSub_name();
			boolean isSubjectExist_1 = (selectedQrtListSubject != null && firstQrtSubject != null);
			
			String secondQrtSubject = (secondQuarterRslt.get(i).getSub_name()+"").length() == 0?"":secondQuarterRslt.get(i).getSub_name();
			boolean isSubjectExist_2 = (selectedQrtListSubject != null && secondQrtSubject != null);
			
			String thirdQrtSubject = (thirdQuarterRslt.get(i).getSub_name()+"").length() == 0?"":thirdQuarterRslt.get(i).getSub_name();
			boolean isSubjectExist_3 = (selectedQrtListSubject != null && thirdQrtSubject != null);
			
			String fourthQrtSubject = (fourthQuarterRslt.get(i).getSub_name()+"").length() == 0?"":fourthQuarterRslt.get(i).getSub_name();
			boolean isSubjectExist_4 = (selectedQrtListSubject != null && fourthQrtSubject != null);
			
			////*** first quarter result list ***////			
			if(firstQuarterRslt.size() != 1 && i < firstQuarterRslt.size() && isSubjectExist_1 && selectedQrtListSubject.equals(firstQrtSubject)){
				
				String con_to_grade_1 = firstQuarterRslt.get(i).getConvert_to_grade();
				
				if(con_to_grade_1.equalsIgnoreCase("no")){
					
					Double quarter_total = Double.parseDouble(firstQuarterRslt.get(i).getQuarter_total() == null?"0.0":firstQuarterRslt.get(i).getQuarter_total()); ///>>> PARSE
					
					if(quarter_total > 0.0 && quarter_total < 65.0){
						
						cell = new PdfPCell(new Phrase(String.valueOf(quarter_total) + "*", redFont));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setBorderWidthRight(0);
						cell.setBorderWidthLeft(0);
						cell.setBorderWidthBottom(0.5f);
						cell.setBorderWidthTop(0);
						cell.setPaddingTop(5);
						studResultTable.addCell(cell);
						
					} else {
						
						cell = new PdfPCell(new Phrase(String.valueOf(quarter_total).equals("0.0")?"-":String.valueOf(quarter_total), tblContentFont));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setBorderWidthRight(0);					
						cell.setBorderWidthLeft(0);
						cell.setBorderWidthBottom(0.5f);
						cell.setBorderWidthTop(0);
						cell.setPaddingTop(5);
						studResultTable.addCell(cell);
					}
					
					a++;
					
				} else {
					
					String grd = "" + firstQuarterRslt.get(i).getGrading_result();
					
					cell = new PdfPCell(new Phrase(grd.length() != 0?firstQuarterRslt.get(i).getGrading_result():" ", tblContentFont));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorderWidthRight(0);
					cell.setBorderWidthLeft(0);
					cell.setBorderWidthBottom(0.5f);
					cell.setBorderWidthTop(0);
					cell.setPaddingTop(5);
					studResultTable.addCell(cell);
					
					a++;
				}
				
			} else {
				
				///*** if the total mark of the subject doestn't exist
				cell = new PdfPCell(new Phrase(" ", redFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorderWidthRight(0);
				cell.setBorderWidthLeft(0);
				cell.setBorderWidthBottom(0.5f);
				cell.setBorderWidthTop(0);
				cell.setPaddingTop(5);
				studResultTable.addCell(cell);
			}
			
			////*** second quarter result list ***////
			if(secondQuarterRslt.size() != 1 && i < secondQuarterRslt.size() && isSubjectExist_2 && selectedQrtListSubject.equals(secondQrtSubject)){
				
				String con_to_grade_2 = secondQuarterRslt.get(i).getConvert_to_grade();
				
				if(con_to_grade_2.equalsIgnoreCase("no")){
					
					Double quarter_total = Double.parseDouble(secondQuarterRslt.get(i).getQuarter_total() == null?"0.0":secondQuarterRslt.get(i).getQuarter_total()); 
					
					if(quarter_total > 0.0 && quarter_total < 65.0){
						
						cell = new PdfPCell(new Phrase(String.valueOf(quarter_total) + "*", redFont));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setBorderWidthRight(0);
						cell.setBorderWidthLeft(0);
						cell.setBorderWidthBottom(0.5f);
						cell.setBorderWidthTop(0);
						cell.setPaddingTop(5);
						studResultTable.addCell(cell);
						
					} else {
						
						cell = new PdfPCell(new Phrase(String.valueOf(quarter_total).equals("0.0")?"-":String.valueOf(quarter_total), tblContentFont));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setBorderWidthRight(0);
						cell.setBorderWidthLeft(0);
						cell.setBorderWidthBottom(0.5f);
						cell.setBorderWidthTop(0);
						cell.setPaddingTop(5);
						studResultTable.addCell(cell);
					}
				} else {
					
					String grd = "" + secondQuarterRslt.get(i).getGrading_result();
					
					cell = new PdfPCell(new Phrase(grd.length() != 0?secondQuarterRslt.get(i).getGrading_result():" ", tblContentFont));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorderWidthRight(0);
					cell.setBorderWidthLeft(0);
					cell.setBorderWidthBottom(0.5f);
					cell.setBorderWidthTop(0);
					cell.setPaddingTop(5);
					studResultTable.addCell(cell);
				}
			} else {
				///*** if the total mark of the subject doestn't exist
				cell = new PdfPCell(new Phrase(" ", redFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorderWidthRight(0);
				cell.setBorderWidthLeft(0);
				cell.setBorderWidthBottom(0.5f);
				cell.setBorderWidthTop(0);
				cell.setPaddingTop(5);
				studResultTable.addCell(cell);
			}
			
			////*** third quarter result list ***////
			if(thirdQuarterRslt.size() != 1 && i < thirdQuarterRslt.size() && isSubjectExist_3 && selectedQrtListSubject.equals(thirdQrtSubject)){
				
				String con_to_grade_3 = thirdQuarterRslt.get(i).getConvert_to_grade();
				
				if(con_to_grade_3.equalsIgnoreCase("no")){
					
					Double quarter_total = Double.parseDouble(thirdQuarterRslt.get(i).getQuarter_total() == null?"0.0":thirdQuarterRslt.get(i).getQuarter_total()); 
					
					if(quarter_total > 0.0 && quarter_total < 65.0){
						
						cell = new PdfPCell(new Phrase(String.valueOf(quarter_total) + "*", redFont));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setBorderWidthRight(0);
						cell.setBorderWidthLeft(0);
						cell.setBorderWidthBottom(0.5f);
						cell.setBorderWidthTop(0);
						cell.setPaddingTop(5);
						studResultTable.addCell(cell);
						
					} else {
						
						cell = new PdfPCell(new Phrase(String.valueOf(quarter_total).equals("0.0")?"-":String.valueOf(quarter_total), tblContentFont));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setBorderWidthRight(0);
						cell.setBorderWidthLeft(0);
						cell.setBorderWidthBottom(0.5f);
						cell.setBorderWidthTop(0);
						cell.setPaddingTop(5);
						studResultTable.addCell(cell);
					}
				} else {
					
					String grd = "" + thirdQuarterRslt.get(i).getGrading_result();
					
					cell = new PdfPCell(new Phrase(grd.length() != 0?thirdQuarterRslt.get(i).getGrading_result():" ", tblContentFont));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorderWidthRight(0);
					cell.setBorderWidthLeft(0);
					cell.setBorderWidthBottom(0.5f);
					cell.setBorderWidthTop(0);
					cell.setPaddingTop(5);
					studResultTable.addCell(cell);
				}
			} else {
				///*** if the total mark of the subject doestn't exist
				cell = new PdfPCell(new Phrase(" ", redFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorderWidthRight(0);
				cell.setBorderWidthLeft(0);
				cell.setBorderWidthBottom(0.5f);
				cell.setBorderWidthTop(0);
				cell.setPaddingTop(5);
				studResultTable.addCell(cell);
			}
			
			////*** fourth quarter result list ***////			
			if(fourthQuarterRslt.size() != 1 && i < fourthQuarterRslt.size() && isSubjectExist_4 && selectedQrtListSubject.equals(fourthQrtSubject)){
				
				String con_to_grade_4 = fourthQuarterRslt.get(i).getConvert_to_grade();
				
				if(con_to_grade_4.equalsIgnoreCase("no")){
					
					Double quarter_total = Double.parseDouble(fourthQuarterRslt.get(i).getQuarter_total() == null?"0.0":fourthQuarterRslt.get(i).getQuarter_total()); 
					
					if(quarter_total > 0.0 && quarter_total < 65.0){
						
						cell = new PdfPCell(new Phrase(String.valueOf(quarter_total) + "*", redFont));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setBorderWidthRight(0);
						cell.setBorderWidthLeft(0);
						cell.setBorderWidthBottom(0.5f);
						cell.setBorderWidthTop(0);
						studResultTable.addCell(cell);
						
					} else {
						
						cell = new PdfPCell(new Phrase(String.valueOf(quarter_total).equals("0.0")?"-":String.valueOf(quarter_total), tblContentFont));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setBorderWidthRight(0);
						cell.setBorderWidthLeft(0);
						cell.setBorderWidthBottom(0.5f);
						cell.setBorderWidthTop(0);
						studResultTable.addCell(cell);
					}
				} else {
					
					String grd = "" + fourthQuarterRslt.get(i).getGrading_result();
					
					cell = new PdfPCell(new Phrase(grd.length() != 0?fourthQuarterRslt.get(i).getGrading_result():" ", tblContentFont));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setBorderWidthLeft(0);
					cell.setBorderWidthRight(0);
					cell.setBorderWidthBottom(0.5f);
					cell.setBorderWidthTop(0);
					studResultTable.addCell(cell);
				}
			} else {
				///*** if the total mark of the subject doestn't exist
				cell = new PdfPCell(new Phrase(" ", redFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorderWidthRight(0);
				cell.setBorderWidthLeft(0);
				cell.setBorderWidthBottom(0.5f);
				cell.setBorderWidthTop(0);
				cell.setPaddingTop(5);
				studResultTable.addCell(cell);
			}
			
			////*** average mark result list ***////			
			if(i < fullYearAvgRslt.size()-1){
				
				cell = new PdfPCell(new Phrase(String.valueOf(fullYearAvgRslt.get(i).getQuarter_total()), tblContentFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorderWidthRight(0.5f);
				cell.setBorderWidthLeft(0);
				cell.setBorderWidthBottom(0.5f);
				cell.setBorderWidthTop(0);
				cell.setPaddingTop(5);
				studResultTable.addCell(cell);
				
			} else {
				
				cell = new PdfPCell(new Phrase(" ", redFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorderWidthRight(0.5f);
				cell.setBorderWidthLeft(0);
				cell.setBorderWidthBottom(0.5f);
				cell.setBorderWidthTop(0);
				cell.setPaddingTop(5);
				studResultTable.addCell(cell);
			}			
		}
		
		int count_1 = firstQuarterRslt.size()-1;
		int count_2 = secondQuarterRslt.size()-1;
		int count_3 = thirdQuarterRslt.size()-1;
		int count_4 = fourthQuarterRslt.size()-1;
		int count_5 = fullYearAvgRslt.size()-1;
		
		
		////*** Total
		cell = new PdfPCell(new Phrase("Total", tblContentBoldFont));
		cell.setPaddingTop(5);
		cell.setPaddingLeft(5);
		cell.setFixedHeight(26f);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0.5f);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0);
		studResultTable.addCell(cell);
		
		DecimalFormat fmt1 = new DecimalFormat("###.##");
		
		String total_mark_first = fullYearAvgRslt.get(count_1).getQuarter_grand_total() == null?"0.0":firstQuarterRslt.get(count_1).getQuarter_grand_total().equalsIgnoreCase("0.0")?"":firstQuarterRslt.get(count_1).getQuarter_grand_total();
		//fmt1.format(Double.parseDouble(total_mark_first))
		
		cell = new PdfPCell(new Phrase(total_mark_first, tblContentBoldFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPaddingTop(5);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0);
		studResultTable.addCell(cell);
		
		String total_mark_second = fullYearAvgRslt.get(count_2).getQuarter_grand_total() == null?"0.0":secondQuarterRslt.get(count_2).getQuarter_grand_total().equalsIgnoreCase("0.0")?"":secondQuarterRslt.get(count_2).getQuarter_grand_total();
		//fmt1.format(Double.parseDouble(total_mark_second))
		
		cell = new PdfPCell(new Phrase(total_mark_second, tblContentBoldFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPaddingTop(5);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0);
		studResultTable.addCell(cell);
		
		String total_mark_third = fullYearAvgRslt.get(count_3).getQuarter_grand_total() == null?"0.0":thirdQuarterRslt.get(count_3).getQuarter_grand_total().equalsIgnoreCase("0.0")?"":thirdQuarterRslt.get(count_3).getQuarter_grand_total();
		//fmt1.format(Double.parseDouble(total_mark_third))
		
		cell = new PdfPCell(new Phrase(total_mark_third, tblContentBoldFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPaddingTop(5);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0);
		studResultTable.addCell(cell);
		
		String total_mark_fourth = fullYearAvgRslt.get(count_4).getQuarter_grand_total() == null?"0.0":fourthQuarterRslt.get(count_4).getQuarter_grand_total().equalsIgnoreCase("0.0")?"":fourthQuarterRslt.get(count_4).getQuarter_grand_total();
		//fmt1.format(Double.parseDouble(total_mark_fourth))
		
		cell = new PdfPCell(new Phrase(total_mark_fourth, tblContentBoldFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPaddingTop(5);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0);
		studResultTable.addCell(cell);
		
		String total_avg_mark = fullYearAvgRslt.get(count_5).getQuarter_grand_total() == null?"0.0":fullYearAvgRslt.get(count_5).getQuarter_grand_total().equalsIgnoreCase("0.0")?"0.0":fullYearAvgRslt.get(count_5).getQuarter_grand_total();
		//fmt1.format(Double.parseDouble(total_avg_mark))
		
		cell = new PdfPCell(new Phrase(total_avg_mark, tblContentBoldFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPaddingTop(5);
		cell.setBorderWidthRight(0.5f);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0);
		studResultTable.addCell(cell);
				
		////*** Average
		cell = new PdfPCell(new Phrase("Average", tblContentBoldFont));
		cell.setPaddingTop(5);
		cell.setPaddingLeft(5);
		cell.setFixedHeight(26f);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0.5f);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0);
		studResultTable.addCell(cell);
		
		DecimalFormat fmt = new DecimalFormat("###.##");
		String avg_1 = fmt.format(Double.parseDouble(firstQuarterRslt.get(count_1).getAverage_quarter_mark()));
		String avg_2 = fmt.format(Double.parseDouble(secondQuarterRslt.get(count_2).getAverage_quarter_mark()));
		String avg_3 = fmt.format(Double.parseDouble(thirdQuarterRslt.get(count_3).getAverage_quarter_mark()));
		String avg_4 = fmt.format(Double.parseDouble(fourthQuarterRslt.get(count_4).getAverage_quarter_mark()));
		String avg_5 = fmt.format(Double.parseDouble(fullYearAvgRslt.get(count_5).getAverage_quarter_mark()));
		
		cell = new PdfPCell(new Phrase(avg_1, tblContentBoldFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPaddingTop(5);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0);
		studResultTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(avg_2, tblContentBoldFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPaddingTop(5);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0);
		studResultTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(avg_3, tblContentBoldFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPaddingTop(5);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0);
		studResultTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(avg_4, tblContentBoldFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPaddingTop(5);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0);
		studResultTable.addCell(cell);
		
		cell = new PdfPCell(new Phrase(avg_5, tblContentBoldFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPaddingTop(5);
		cell.setBorderWidthRight(0.5f);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0);
		studResultTable.addCell(cell);
		
		//*** Rank
		cell = new PdfPCell(new Phrase("Rank", tblContentBoldFont));
		cell.setPaddingTop(5);
		cell.setPaddingLeft(5);
		cell.setFixedHeight(26f);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0.5f);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0);
		studResultTable.addCell(cell);
		
		String stud_rank_first = fullYearStudRank.get(0).getQuarter_rank().length() != 0?fullYearStudRank.get(0).getQuarter_rank() + "/" + exrslt.getStud().getTotal_number():"";
		
		cell = new PdfPCell(new Phrase(stud_rank_first, tblContentBoldFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPaddingTop(5);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0);
		studResultTable.addCell(cell);
		
		String stud_rank_second = fullYearStudRank.get(1).getQuarter_rank().length() != 0?fullYearStudRank.get(1).getQuarter_rank() + "/" + exrslt.getStud().getTotal_number():"";
		
		cell = new PdfPCell(new Phrase(stud_rank_second, tblContentBoldFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPaddingTop(5);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0);
		studResultTable.addCell(cell);
		
		String stud_rank_third = fullYearStudRank.get(2).getQuarter_rank().length() != 0?fullYearStudRank.get(2).getQuarter_rank() + "/" + exrslt.getStud().getTotal_number():"";
		
		cell = new PdfPCell(new Phrase(stud_rank_third, tblContentBoldFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPaddingTop(5);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0);
		studResultTable.addCell(cell);
		
		String stud_rank_fourth = fullYearStudRank.get(3).getQuarter_rank().length() != 0?fullYearStudRank.get(3).getQuarter_rank() + "/" + exrslt.getStud().getTotal_number():"";
		
		cell = new PdfPCell(new Phrase(stud_rank_fourth, tblContentBoldFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPaddingTop(5);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0);
		studResultTable.addCell(cell);
		
		String stud_avg_rank = fullYearStudRank.get(4).getQuarter_rank().length() != 0?fullYearStudRank.get(4).getQuarter_rank() + "/" + exrslt.getStud().getTotal_number():"";
		
		cell = new PdfPCell(new Phrase(stud_avg_rank, tblContentBoldFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPaddingTop(5);
		cell.setBorderWidthRight(0.5f);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderWidthTop(0);
		studResultTable.addCell(cell);
				
		return studResultTable;
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
	
	private static PdfPTable getStudentPhoto(byte[] stud_photo){
		
		PdfPTable photoTbl = new PdfPTable(1);
		
		try{
			
			//Image studPhoto = Image.getInstance("C:/sms_system_file/everest/" + photoPath);
			
			Image studPhoto = Image.getInstance(stud_photo);
			
			studPhoto.scaleToFit(100f, 100f);
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
	
	private static void addEmptyLine(Paragraph paragraph, int number){
		for(int i = 0; i < number; i++){
			paragraph.add(new Paragraph(" "));
		}
	}

	
	public static ExamResultBean getExrslt() {
		return exrslt;
	}

	public static void setExrslt(ExamResultBean exrslt) {
		PDFGenerator_AcademicCertificateForAll.exrslt = exrslt;
	}

	public static List<ExamResultBean> getFirstQuarterRslt() {
		return firstQuarterRslt;
	}

	public static void setFirstQuarterRslt(List<ExamResultBean> firstQuarterRslt) {
		PDFGenerator_AcademicCertificateForAll.firstQuarterRslt = firstQuarterRslt;
	}

	public static List<ExamResultBean> getSecondQuarterRslt() {
		return secondQuarterRslt;
	}

	public static void setSecondQuarterRslt(List<ExamResultBean> secondQuarterRslt) {
		PDFGenerator_AcademicCertificateForAll.secondQuarterRslt = secondQuarterRslt;
	}

	public static List<ExamResultBean> getThirdQuarterRslt() {
		return thirdQuarterRslt;
	}

	public static void setThirdQuarterRslt(List<ExamResultBean> thirdQuarterRslt) {
		PDFGenerator_AcademicCertificateForAll.thirdQuarterRslt = thirdQuarterRslt;
	}

	public static List<ExamResultBean> getFourthQuarterRslt() {
		return fourthQuarterRslt;
	}

	public static void setFourthQuarterRslt(List<ExamResultBean> fourthQuarterRslt) {
		PDFGenerator_AcademicCertificateForAll.fourthQuarterRslt = fourthQuarterRslt;
	}

	public static List<ExamResultBean> getFirstQuarterTeacherComment() {
		return firstQuarterTeacherComment;
	}

	public static void setFirstQuarterTeacherComment(List<ExamResultBean> firstQuarterTeacherComment) {
		PDFGenerator_AcademicCertificateForAll.firstQuarterTeacherComment = firstQuarterTeacherComment;
	}

	public static List<ExamResultBean> getSecondQuarterTeacherComment() {
		return secondQuarterTeacherComment;
	}

	public static void setSecondQuarterTeacherComment(List<ExamResultBean> secondQuarterTeacherComment) {
		PDFGenerator_AcademicCertificateForAll.secondQuarterTeacherComment = secondQuarterTeacherComment;
	}

	public static List<ExamResultBean> getThirdQuarterTeacherComment() {
		return thirdQuarterTeacherComment;
	}

	public static void setThirdQuarterTeacherComment(List<ExamResultBean> thirdQuarterTeacherComment) {
		PDFGenerator_AcademicCertificateForAll.thirdQuarterTeacherComment = thirdQuarterTeacherComment;
	}

	public static List<ExamResultBean> getFourthQuarterTeacherComment() {
		return fourthQuarterTeacherComment;
	}

	public static void setFourthQuarterTeacherComment(List<ExamResultBean> fourthQuarterTeacherComment) {
		PDFGenerator_AcademicCertificateForAll.fourthQuarterTeacherComment = fourthQuarterTeacherComment;
	}

	public static List<CertDefaultComBean> getFirstQuarterDefaultComment() {
		return firstQuarterDefaultComment;
	}

	public static void setFirstQuarterDefaultComment(List<CertDefaultComBean> firstQuarterDefaultComment) {
		PDFGenerator_AcademicCertificateForAll.firstQuarterDefaultComment = firstQuarterDefaultComment;
	}

	public static List<CertDefaultComBean> getSecondQuarterDefaultComment() {
		return secondQuarterDefaultComment;
	}

	public static void setSecondQuarterDefaultComment(List<CertDefaultComBean> secondQuarterDefaultComment) {
		PDFGenerator_AcademicCertificateForAll.secondQuarterDefaultComment = secondQuarterDefaultComment;
	}

	public static List<CertDefaultComBean> getThirdQuarterDefaultComment() {
		return thirdQuarterDefaultComment;
	}

	public static void setThirdQuarterDefaultComment(List<CertDefaultComBean> thirdQuarterDefaultComment) {
		PDFGenerator_AcademicCertificateForAll.thirdQuarterDefaultComment = thirdQuarterDefaultComment;
	}

	public static List<CertDefaultComBean> getFourthQuarterDefaultComment() {
		return fourthQuarterDefaultComment;
	}

	public static void setFourthQuarterDefaultComment(List<CertDefaultComBean> fourthQuarterDefaultComment) {
		PDFGenerator_AcademicCertificateForAll.fourthQuarterDefaultComment = fourthQuarterDefaultComment;
	}

	public static List<ExamResultBean> getFullYearAvgRslt() {
		return fullYearAvgRslt;
	}

	public static void setFullYearAvgRslt(List<ExamResultBean> fullYearAvgRslt) {
		PDFGenerator_AcademicCertificateForAll.fullYearAvgRslt = fullYearAvgRslt;
	}

	public static List<ExamResultBean> getFullYearStudRank() {
		return fullYearStudRank;
	}

	public static void setFullYearStudRank(List<ExamResultBean> fullYearStudRank) {
		PDFGenerator_AcademicCertificateForAll.fullYearStudRank = fullYearStudRank;
	}

	public static List<String> getSubjectList() {
		return subjectList;
	}

	public static void setSubjectList(List<String> subjectList) {
		PDFGenerator_AcademicCertificateForAll.subjectList = subjectList;
	}

}

//class RotateEvent extends PdfPageEventHelper{
//	public void onStartPage(PdfWriter writer, Document document){
//		writer.addPageDictEntry(PdfName.ROTATE, PdfPage.LANDSCAPE);
//	}
//}
