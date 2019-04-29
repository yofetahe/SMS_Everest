package util;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Paragraph;

import studentClass.StudentBean;
import studentClass.StudentDAO;
import studentClass.emergency_contact.EmergencyContactBean;

public class PDF_GenerateStudIDCard {
	
	public static final String FONT = "C:/windows/fonts/ARIALUNI.TTF";
	public static final String FONT_2 = "C:/windows/fonts/ARLRDBD.TTF";
	public static final String CALIBRI_FONT = "C:/windows/fonts/calibri.ttf";
	public static final String NYALA = "C:/windows/fonts/nyala.ttf";
	
	private static Font customHeaderFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 15, Font.BOLD);
	BaseFont baseFont = customHeaderFont.getBaseFont();
	
	private static Font addressFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 6, Font.NORMAL);
	
	private static Font addressFont_phone = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 7, Font.NORMAL);
	
	private static Font schoolNameFontEng_1 = FontFactory.getFont(FONT_2, BaseFont.CP1257, BaseFont.EMBEDDED, 5.5f, Font.BOLD);
	
	private static Font schoolNameFontEng_2 = FontFactory.getFont(FONT_2, BaseFont.CP1257, BaseFont.EMBEDDED, 5.5f, Font.BOLD);
	
	private static Font schoolNameFontEng_3 = FontFactory.getFont(FONT_2, BaseFont.CP1257, BaseFont.EMBEDDED, 5.5f, Font.BOLD);
			
	private static Font promotionFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 7, Font.NORMAL);
	
	private static Font infoFont = FontFactory.getFont(CALIBRI_FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 8, Font.NORMAL);
	
	private static Font tblContentFont = FontFactory.getFont(FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 7, Font.NORMAL); 
	
	private static List<StudentBean> stud_with_out_id_rslt;
	
	private static List<EmergencyContactBean> stud_contact_rslt;
	
	private static int num_of_page;
	
	private static String pdf_full_page_status;
	
	public static void generateIDCardPDF(String idCardFile, List<StudentBean> stud_with_out_id_rslt, List<EmergencyContactBean> stud_contact_rslt, int numofpage, String full_page_status){
		
		setStud_with_out_id_rslt(stud_with_out_id_rslt);
		setStud_contact_rslt(stud_contact_rslt);
		num_of_page = numofpage;
		pdf_full_page_status = full_page_status;
		
		try{
			
			Document document = new Document();
			
			PdfWriter.getInstance(document, new FileOutputStream(idCardFile));
			document.setPageSize(PageSize.A4);
			document.setMargins(36, 36, 18, 18); //left,right,top,bottom	
			document.open();
			addIDCardMetaData(document, idCardFile);
			addIDCardContent(document);
			document.close();
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void addIDCardMetaData(Document document, String FileName){
		document.addTitle("Student ID Card");
		document.addSubject("Everest Student ID Card");
		document.addKeywords("java, PDF, iText" + FileName);
		document.addAuthor("Yofetahe");
		document.addCreator("Fidel");		
	}
	
	public static final String IMG = "C:/sms_system_file/everest_kg/everest_logo_back.png";
	
	public static final String IMG2 = "C:/sms_system_file/everest_kg/everest_logo_back.png";
	
	static class ImageBackgroundEvent implements PdfPCellEvent{
		
		protected Image image;
		
		public ImageBackgroundEvent(Image image) {
			this.image = image;
		}

		@Override
		public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvas) {
			
			try{
				PdfContentByte cb = canvas[PdfPTable.BACKGROUNDCANVAS];
				//image.scaleAbsolute(position);
				image.setRotationDegrees(-90);
				image.scaleToFit(454, 144);
				
				cb.saveState();
				PdfGState state = new PdfGState();
				state.setFillOpacity(0.25f);
				cb.setGState(state);				
				image.setAbsolutePosition(position.RIGHT + 21, position.getBottom()+10);
				
				cb.addImage(image);
				cb.restoreState();
			} catch(DocumentException e){
				throw new ExceptionConverter(e);
			}
		}		
	}
	
	static class ImageBackgroundEventBack implements PdfPCellEvent{
		
		protected Image image;
		
		public ImageBackgroundEventBack(Image image) {
			this.image = image;
		}

		@Override
		public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvas) {
			
			try{
				PdfContentByte cb = canvas[PdfPTable.BACKGROUNDCANVAS];
				//image.scaleAbsolute(position);
				image.setRotationDegrees(90);
				//image.scaleToFit(454, 154);
				
				cb.saveState();
				PdfGState state = new PdfGState();
				state.setFillOpacity(0.02f);
				cb.setGState(state);				
				//image.setAbsolutePosition(position.RIGHT, position.getBottom());
				
				cb.addImage(image);
				cb.restoreState();
			} catch(DocumentException e){
				throw new ExceptionConverter(e);
			}
		}		
	}
	
	public static void addIDCardContent(Document document) throws IOException, DocumentException{
		
		float[] columnWidths = {5, 2, 5};
		
		PdfPTable idCardTbl = new PdfPTable(columnWidths);
		idCardTbl.setWidthPercentage(100);
				
		int page_counter = 0;
		
		for(int i = 0, j = 0; i < stud_with_out_id_rslt.size(); i++, j++){
			
			try {
		
				PdfPCell cell = new PdfPCell(addIDCardFront(stud_with_out_id_rslt.get(i).getFullName(), 
															stud_with_out_id_rslt.get(i).getId_no(), 
															stud_with_out_id_rslt.get(i).getDob(),
															stud_with_out_id_rslt.get(i).getSex(),
															stud_with_out_id_rslt.get(i).getClass_name(),
															stud_with_out_id_rslt.get(i).getCd_name(),
															stud_with_out_id_rslt.get(i).getPhoto_path(),
															stud_with_out_id_rslt.get(i).getSi_id(),
															stud_with_out_id_rslt.get(i).getStud_photo()));
				cell.setColspan(1);
				cell.setBorder(0);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(4f);
				cell.setBorder(0);
				cell.setPaddingTop(8f);
							
				Image image = Image.getInstance(IMG);			
				cell.setCellEvent(new ImageBackgroundEvent(image));
				cell.setFixedHeight(4);
				
				idCardTbl.addCell(cell);
				
				cell = new PdfPCell(new Phrase(""));
				cell.setColspan(1);
				cell.setBorder(1);
				idCardTbl.addCell(cell);
				
				cell = new PdfPCell(addIDCardBack(stud_with_out_id_rslt.get(i).getSi_id()));
				cell.setColspan(1);
				cell.setBorder(0);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);			
				cell.setPaddingLeft(7f);
				cell.setBorder(0);
				
	//			Image image2 = Image.getInstance(IMG2);			
	//			cell.setCellEvent(new ImageBackgroundEventBack(image2));
	//			cell.setFixedHeight(6);
				
				idCardTbl.addCell(cell);
				
				cell = new PdfPCell(new Phrase(""));
				cell.setColspan(3);
				cell.setFixedHeight(1f);
				cell.setBorder(0);
				idCardTbl.addCell(cell);
				
			} catch (BadElementException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(pdf_full_page_status.equals("Yes") && j == 4){
				
				page_counter++;
				
				j = -1;
				
				if(page_counter == num_of_page){
					
					break;
				}				
			}			
		}
		
		try {
			document.add(idCardTbl);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}
	
	public static PdfPTable addIDCardFront(String studName, String studIdNo, String studDob, String studSex, String studGrade, String studGDetail, String photoPath, String si_id, byte[] stud_photo){
		
		float[] columnWidth = {2, 3, 3, 3, 3, 3, 1, 15, 1, 3, 3, 3, 3};
		
		PdfPTable iDCardFron = new PdfPTable(columnWidth);
		iDCardFron.setWidthPercentage(95);
		iDCardFron.setHorizontalAlignment(Element.ALIGN_RIGHT);
		
		insertCell(iDCardFron, "", Element.ALIGN_CENTER, 1, 2, tblContentFont);		
		insertCell(iDCardFron, "Valid for " + ReturnCurrentEthiopianYear.getCurrentEthiopianYear() + " Academic Year", Element.ALIGN_CENTER, 1, 2, tblContentFont);
		//insertCell(iDCardFron, "Issue Date: " + TodayDate_YYYYMMDD.getDayMonthYearFormat(), Element.ALIGN_LEFT, 1, 2, tblContentFont);
		insertCell(iDCardFron, studGrade + "-" + studGDetail + "      Issue Date: " + TodayDate_YYYYMMDD.getDayMonthYearFormat() + "G.C", Element.ALIGN_CENTER, 1, 2, tblContentFont);		
		
		String sex = studSex.equals("M")?"Male":"Female";
		
		insertCell(iDCardFron, "Sex: " + sex + "   Date of Birth: " + TodayDate_YYYYMMDD.getDayMonthYearFormat(studDob) + "E.C", Element.ALIGN_CENTER, 1, 2, tblContentFont);
		insertCell(iDCardFron, "ID No.: "+ studIdNo + "/" + ReturnCurrentEthiopianYear.getCurrentEthiopianYear() + "EIA", Element.ALIGN_CENTER, 1, 2, tblContentFont);
		insertCell(iDCardFron, "Name: "+ studName, Element.ALIGN_CENTER, 1, 2, tblContentFont);
		insertCell(iDCardFron, "", Element.ALIGN_CENTER, 1, 2, tblContentFont);
		
		PdfPCell cell = new PdfPCell(getStudentPhoto(stud_photo));
		cell.setColspan(1);
		cell.setRowspan(2);
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPaddingTop(10f);
		iDCardFron.addCell(cell);
		
		cell = new PdfPCell(new Phrase(""));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setRowspan(2);
		cell.setFixedHeight(1f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setRotation(-90);		
		iDCardFron.addCell(cell);
		
		cell = new PdfPCell(getSchoolLogo());
		cell.setColspan(4);
		cell.setRowspan(1);
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setFixedHeight(30f);
		cell.setPaddingRight(2f);
		iDCardFron.addCell(cell);
				
		//insertCell(iDCardFron, "", Element.ALIGN_CENTER, 1, 1, addressFont);
		insertCell(iDCardFron, SysConstant.SCHOOL_WEB, Element.ALIGN_CENTER, 1, 1, addressFont);
		insertCell(iDCardFron, SysConstant.SCHOOL_ADDRESS_PHONE, Element.ALIGN_CENTER, 1, 1, addressFont_phone);
		
		cell = new PdfPCell(getSchoolNameWithDiffColor());
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setRowspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		iDCardFron.addCell(cell);
		
		cell = new PdfPCell(getAmhSchoolNameWithDiffColor());
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setRowspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		iDCardFron.addCell(cell);
		
		return iDCardFron;
	}
	
	private static PdfPTable getSchoolNameWithDiffColor(){
		
		PdfPTable nameTbl = new PdfPTable(1);
		
		schoolNameFontEng_1.setColor(new BaseColor(77, 172, 204));
		
		PdfPCell cell = new PdfPCell(new Phrase("EVEREST ", schoolNameFontEng_1));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setRowspan(1);
		cell.setRotation(-90);
		nameTbl.addCell(cell);
		
		schoolNameFontEng_2.setColor(new BaseColor(216, 51, 47));
		
		cell = new PdfPCell(new Phrase("YOUTH ", schoolNameFontEng_2));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setRowspan(1);
		cell.setRotation(-90);
		nameTbl.addCell(cell);
		
		cell = new PdfPCell(new Phrase("ACADEMY", schoolNameFontEng_3));
		cell.setBorder(0);
		cell.setColspan(1);
		cell.setRowspan(1);
		cell.setRotation(-90);
		nameTbl.addCell(cell);
		
		return nameTbl;
	}
	
	private static PdfPTable getAmhSchoolNameWithDiffColor(){
		
		PdfPTable nameTbl = new PdfPTable(1);
				
		BaseFont unicode;
		try {
			unicode = BaseFont.createFont(NYALA, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font_1 = new Font(unicode, 9, Font.BOLD, new BaseColor(77, 172, 204)); //(77, 172, 204)
			Font font_2 = new Font(unicode, 9, Font.BOLD, new BaseColor(216, 51, 47)); //(216, 51, 47)
			Font font_3 = new Font(unicode, 9, Font.BOLD, BaseColor.BLACK);
			
			PdfPCell cell = new PdfPCell(new Phrase(SysConstant.SCHOOL_NAME_AMH_1, font_1));
			cell.setBorder(0);
			cell.setColspan(1);
			cell.setRowspan(1);
			cell.setRotation(-90);
			nameTbl.addCell(cell);
			
			cell = new PdfPCell(new Phrase(SysConstant.SCHOOL_NAME_AMH_2, font_2));
			cell.setBorder(0);
			cell.setColspan(1);
			cell.setRowspan(1);
			cell.setRotation(-90);
			nameTbl.addCell(cell);
			
			cell = new PdfPCell(new Phrase(SysConstant.SCHOOL_NAME_AMH_3, font_3));
			cell.setBorder(0);
			cell.setColspan(1);
			cell.setRowspan(1);
			cell.setRotation(-90);
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
			
			Image schoolLogo = Image.getInstance("C:/sms_system_file/everest_kg/everest_logo.png");
			
			schoolLogo.scaleToFit(40f, 50f);
			schoolLogo.setAlignment(Element.ALIGN_CENTER);
			PdfPCell cell = new PdfPCell(schoolLogo);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setColspan(1);
			cell.setBorder(0);
			cell.setRotation(-90);
			cell.setFixedHeight(20f);
			logoTbl.addCell(cell);
			
		} catch(Exception e){}
		
		return logoTbl;
	}
	
	private static PdfPTable getStudentPhoto(byte[] stud_photo){
		
		PdfPTable photoTbl = new PdfPTable(1);
		
		try{
			
			//Image studPhoto = Image.getInstance("C:/sms_system_file/everest/" + photoPath);
			
			Image studPhoto = Image.getInstance(stud_photo);
			
			studPhoto.scaleToFit(1100f, 1100f);
			studPhoto.setAlignment(Element.ALIGN_CENTER);
			PdfPCell cell = new PdfPCell(studPhoto);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setColspan(1);
			cell.setBorder(0);
			cell.setRotation(-90);
			photoTbl.addCell(cell);
			
		} catch(Exception e){}
		
		return photoTbl;
	}
	
	private static PdfPTable getSchoolStamp(){
		
		PdfPTable stampTbl = new PdfPTable(1);
		
		try{
			
			Image schoolStamp = Image.getInstance("C:/sms_system_file/everest/Everest_Stamp.jpg");
			
			schoolStamp.scaleToFit(60f, 60f);
			schoolStamp.setAlignment(Element.ALIGN_CENTER);
			PdfPCell cell = new PdfPCell(schoolStamp);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setColspan(1);
			cell.setBorder(0);
			cell.setRotation(90);
			cell.setFixedHeight(20f);
			stampTbl.addCell(cell);
			
		} catch(Exception e){}
		
		return stampTbl;
	}
	
	public static PdfPTable addIDCardBack(String si_id){
		
		int count = 0;
		
		for(int i = 0; i < stud_contact_rslt.size(); i++){
			
			if(si_id.equals(stud_contact_rslt.get(i).getSi_id())){
			
				count++;
			}
			if(count == 2){
				break;
			}
		}
		
		int colWidth = 7 + (count * 2);
		
		float[] columnWidth = new float[colWidth]; //{5, ?, 5, 5, 5, 20, 5, 5};
		
		for(int i = 0; i < colWidth; i++){
			if(i == 0){
				columnWidth[i] = 3;
			} else if(i == colWidth-4 || i == colWidth-5 || i == colWidth-6){
				columnWidth[i] = 3;
			} else if(i == colWidth-3){
				columnWidth[i] = 16;
			} else if(i == colWidth-1 || i == colWidth-2){
				columnWidth[i] = 8;
			} else {
				columnWidth[i] = 3;
			}
		}
		
		PdfPTable iDCardBack = new PdfPTable(columnWidth);
		iDCardBack.setWidthPercentage(100);
		iDCardBack.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		//insertCellBack(iDCardBack, "", Element.ALIGN_LEFT, 1, 1, tblContentFont);
		
		insertCellBack(iDCardBack, "CONTACT PERSON", Element.ALIGN_CENTER, 1, 1, tblContentFont);
		
		int contact_counter = 0;
				
		for(int i = 0; i < stud_contact_rslt.size(); i++){
			
			if(si_id.equals(stud_contact_rslt.get(i).getSi_id())){
				
				insertCellBack(iDCardBack, stud_contact_rslt.get(i).getRelationship() + " Name: " + stud_contact_rslt.get(i).getContact_name(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
				insertCellBack(iDCardBack, "Mobile: " + stud_contact_rslt.get(i).getMob_no(), Element.ALIGN_CENTER, 1, 1, tblContentFont);
				//insertCellBack(iDCardBack, "", Element.ALIGN_LEFT, 1, 1, tblContentFont);
				
				contact_counter++;
			}
			
			if(contact_counter == 2){
				break;
			}
		}
		//ADDRESS
		insertCellBack(iDCardBack, " ", Element.ALIGN_CENTER, 1, 1, tblContentFont);
		
//		String w = woreda == null?"-":woreda;
//		String k = kebele == null?"-":kebele;
//		String h = HouseNo == null?"-":HouseNo;
//		String hp = home_phone_no == null?"-":home_phone_no;
//		Woreda: H.No.:Phone Number: 
		
		insertCellBack(iDCardBack, " ", Element.ALIGN_CENTER, 1, 1, tblContentFont);
		insertCellBack(iDCardBack, " ", Element.ALIGN_CENTER, 1, 1, tblContentFont);
				
		PdfPCell cell = new PdfPCell(getSchoolStamp());
		cell.setColspan(1);
		cell.setRowspan(1);
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setPaddingLeft(2f);
		cell.setFixedHeight(30f);
		iDCardBack.addCell(cell);
		
		cell = new PdfPCell(new Phrase("If you get this ID, please return with the address stated above or return to school.", infoFont));
		cell.setColspan(1);
		cell.setRowspan(1);
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setNoWrap(true);
		cell.setRotation(90);
		cell.setFixedHeight(160f);
		cell.setPaddingTop(20f);
		cell.setPaddingBottom(20f);
		iDCardBack.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Prepared by YamGet IT Solution. +251-911-662766/0912-195853", promotionFont));
		cell.setColspan(1);
		cell.setRowspan(1);
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setNoWrap(true);
		cell.setRotation(90);
		cell.setFixedHeight(152f);
		cell.setPaddingTop(25f);
		cell.setPaddingBottom(25f);
		iDCardBack.addCell(cell);
		
		return iDCardBack;
	}
	
	private static void insertCell(PdfPTable table, String text, int align, int colspan, int rowspan, Font font){
		
		PdfPCell cell = new PdfPCell(new Phrase(text == null?"":text, font));
		
		cell.setHorizontalAlignment(align);
		
		cell.setRowspan(rowspan);
		
		cell.setColspan(colspan);
		
		cell.setRotation(-90);
		
		cell.setBorder(0);
		
		table.addCell(cell);
	}
	
	private static void insertCellBack(PdfPTable table, String text, int align, int colspan, int rowspan, Font font){
		
		PdfPCell cell = new PdfPCell(new Phrase(text == null?"":text, font));
		
		cell.setHorizontalAlignment(align);
		
		cell.setRowspan(rowspan);
		
		cell.setColspan(colspan);
		
		cell.setRotation(90);
		
		cell.setBorder(0);
		
		table.addCell(cell);
	}
	
	public static List<StudentBean> getStud_with_out_id_rslt() {
		return stud_with_out_id_rslt;
	}

	public static void setStud_with_out_id_rslt(List<StudentBean> stud_with_out_id_rslt) {
		PDF_GenerateStudIDCard.stud_with_out_id_rslt = stud_with_out_id_rslt;
	}

	public static List<EmergencyContactBean> getStud_contact_rslt() {
		return stud_contact_rslt;
	}

	public static void setStud_contact_rslt(List<EmergencyContactBean> stud_contact_rslt) {
		PDF_GenerateStudIDCard.stud_contact_rslt = stud_contact_rslt;
	}

	public static int getNum_of_page() {
		return num_of_page;
	}

	public static void setNum_of_page(int num_of_page) {
		PDF_GenerateStudIDCard.num_of_page = num_of_page;
	}

	public static String getPdf_full_page_status() {
		return pdf_full_page_status;
	}

	public static void setPdf_full_page_status(String pdf_full_page_status) {
		PDF_GenerateStudIDCard.pdf_full_page_status = pdf_full_page_status;
	}
}
