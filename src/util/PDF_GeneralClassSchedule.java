package util;

import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import classSchedule.ClassScheduleBean;

public class PDF_GeneralClassSchedule {
	
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
	
	private static List<List<List<ClassScheduleBean>>> generalSortedClassSchedule;
	
	public static void generateGeneralClassSchedulePDF(List<List<List<ClassScheduleBean>>> generalSortedClassSchedule, String fileName){
		
		setGeneralSortedClassSchedule(generalSortedClassSchedule);
		
		try{
			
			Document document = new Document();
			
			PdfWriter.getInstance(document, new FileOutputStream(fileName));			
			document.open();
			addMarkListMetaData(document, fileName);
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
		
		PdfPCell cell = new PdfPCell(headerTable());
		cell.setBorder(0);
		mrkListContent.addCell(cell);
		
		///>>> PDF Content Generator <<<///
		float[] columnWidths = {4, 4, 4, 4, 4, 4, 4, 4};
		
		int counter = 1;
				
		for(int i = 0; i < getGeneralSortedClassSchedule().size(); i++){
			
			PdfPTable classScheduleTbl = new PdfPTable(columnWidths);
			classScheduleTbl.setWidthPercentage(100);
			
			String tableTitle = getGeneralSortedClassSchedule().get(i).get(1).get(1).getClass_name() + " - " + getGeneralSortedClassSchedule().get(i).get(1).get(1).getCd_name();
			
			insertCell(classScheduleTbl, tableTitle, Element.ALIGN_CENTER, 8, 1, customHeaderFont);
						
			for(int j = 0; j < getGeneralSortedClassSchedule().get(i).size(); j++){
				
				for(int z = 0; z < getGeneralSortedClassSchedule().get(i).get(j).size(); z++){
					
					if(j == 0 && z == 0){
						
						insertCell(classScheduleTbl, " ", Element.ALIGN_CENTER, 1, 1, tblContentFont);
						
					} else if(j == 0 && z != 0){
						
						insertCell(classScheduleTbl, getGeneralSortedClassSchedule().get(i).get(j).get(z).getPeriod_name(), Element.ALIGN_LEFT, 1, 1, tblHeaderFont);
						
					} else if(z == 0){
						
						insertCell(classScheduleTbl, getGeneralSortedClassSchedule().get(i).get(j).get(z).getDay_name(), Element.ALIGN_LEFT, 1, 1, tblHeaderFont);
						
					} else {
						
						String contValue = getGeneralSortedClassSchedule().get(i).get(j).get(z).getTfname() + " " + getGeneralSortedClassSchedule().get(i).get(j).get(z).getTmname() + " (" + getGeneralSortedClassSchedule().get(i).get(j).get(z).getSub_name() + ")";
						
						insertCell(classScheduleTbl, contValue, Element.ALIGN_LEFT, 1, 1, tblContentFont);
					}
				}				
			}
			
			insertCell(classScheduleTbl, "", Element.ALIGN_CENTER, 8, 1, customHeaderFont);
						
			if(counter == 4){
				document.newPage();
				counter = 0;
			}
			
			counter++;
			
			mrkListContent.addCell(classScheduleTbl);
		}		
		///>>> PDF Content Generator <<<///
		
		cell = new PdfPCell(footerTable());
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		mrkListContent.addCell(cell);
		
		try {
			document.add(mrkListContent);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public static PdfPTable headerTable(){
		
		PdfPTable mrkListHeader = new PdfPTable(1);
		
		PdfPCell cell = new PdfPCell(new Phrase(SysConstant.SCHOOL_FULL_NAME, customHeaderFont));
		cell.setColspan(3);
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		mrkListHeader.addCell(cell);
				
		return mrkListHeader;
	}
	
	public static PdfPTable footerTable(){
		
		PdfPTable mrkListFooter = new PdfPTable(2);
		
		PdfPCell cell = new PdfPCell(new Phrase(" "));
		cell.setColspan(2);
		cell.setBorder(0);
		cell.setFixedHeight(10f);
		mrkListFooter.addCell(cell);		
								
		return mrkListFooter;
	}
	
	public static PdfPTable classScheduleTable(){
		
		float[] columnWidths = {4, 4, 4, 4, 4, 4, 4, 4};
		
		PdfPTable classScheduleTbl = new PdfPTable(columnWidths);
		classScheduleTbl.setWidthPercentage(100);
		
		int counter = 1;
				
		for(int i = 0; i < getGeneralSortedClassSchedule().size(); i++){
			
			String tableTitle = getGeneralSortedClassSchedule().get(i).get(1).get(1).getClass_name() + " - " + getGeneralSortedClassSchedule().get(i).get(1).get(1).getCd_name();
			
			insertCell(classScheduleTbl, tableTitle, Element.ALIGN_CENTER, 8, 1, customHeaderFont);
						
			for(int j = 0; j < getGeneralSortedClassSchedule().get(i).size(); j++){
				
				for(int z = 0; z < getGeneralSortedClassSchedule().get(i).get(j).size(); z++){
					
					if(j == 0 && z == 0){
						
						insertCell(classScheduleTbl, "---", Element.ALIGN_CENTER, 1, 1, tblContentFont);
						
					} else if(j == 0 && z != 0){
						
						insertCell(classScheduleTbl, getGeneralSortedClassSchedule().get(i).get(j).get(z).getPeriod_name(), Element.ALIGN_LEFT, 1, 1, tblHeaderFont);
						
					} else if(z == 0){
						
						insertCell(classScheduleTbl, getGeneralSortedClassSchedule().get(i).get(j).get(z).getDay_name(), Element.ALIGN_LEFT, 1, 1, tblHeaderFont);
						
					} else {
						
						String contValue = getGeneralSortedClassSchedule().get(i).get(j).get(z).getTfname() + " " + getGeneralSortedClassSchedule().get(i).get(j).get(z).getTmname() + " (" + getGeneralSortedClassSchedule().get(i).get(j).get(z).getSub_name() + ")";
						
						insertCell(classScheduleTbl, contValue, Element.ALIGN_LEFT, 1, 1, tblContentFont);
					}
				}
				
			}
			
			insertCell(classScheduleTbl, "", Element.ALIGN_CENTER, 8, 1, customHeaderFont);
						
			if(counter == 4){
				for(int a = 0; a < 4; a++){
				insertCell(classScheduleTbl, "", Element.ALIGN_LEFT, 8, 1, tblContentFont);
				}
				counter = 0;
			}
			
			counter++;			
		}
		
		return classScheduleTbl;
	}
	
	private static void insertCell(PdfPTable table, String text, int align, int colspan, int rowspan, Font font){
		
		String val = text + "";
		
		PdfPCell cell = new PdfPCell(new Phrase(text == null?"":text, font));
		
		cell.setHorizontalAlignment(align);
		
		if(val.contains("Grade")){
			cell.setBorder(0);
		} else {
			cell.setBorder(1);
		}
		
		cell.setRowspan(rowspan);
		
		cell.setColspan(colspan);
				
		cell.setMinimumHeight(13f);
		
		table.addCell(cell);
	}

	public static List<List<List<ClassScheduleBean>>> getGeneralSortedClassSchedule() {
		return generalSortedClassSchedule;
	}

	public static void setGeneralSortedClassSchedule(List<List<List<ClassScheduleBean>>> generalSortedClassSchedule) {
		PDF_GeneralClassSchedule.generalSortedClassSchedule = generalSortedClassSchedule;
	}

	

}
