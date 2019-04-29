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

import reportClass.ReportBean;

public class PDF_StudMarkListReport {
	
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
	private static Font tblContentFont = FontFactory.getFont(FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 10, Font.NORMAL); 
		
	private static List<ReportBean> stud_mark_list_report;
	private static ReportBean rbean;
	
	public static void getStudMarkListReportPDF(List<ReportBean> stud_mark_list_report, String fileNamePath, ReportBean rbean){
		
		setStud_mark_list_report(stud_mark_list_report);
		setRbean(rbean);
		
		try{
			
			Document document = new Document();			
			PdfWriter.getInstance(document, new FileOutputStream(fileNamePath));
			document.setPageSize(PageSize.A4.rotate());
			document.open();
			addStudMarkListReportMetaData(document, fileNamePath);
			addStudMarkListReportContent(document);
			document.close();
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void addStudMarkListReportMetaData(Document document, String FileName){
		document.addTitle("Student Mark Plan And Imp");
		document.addSubject("Everest Student Mark Plan Report");
		document.addKeywords("java, PDF, iText" + FileName);
		document.addAuthor("Yofetahe");
		document.addCreator("Fidel");		
	}
	
	public static PdfPTable tableHeader(){
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(80);
		
		PdfPCell cell = new PdfPCell(new Phrase("GUCA RAAWWIIN QABXII BARATTOOTAA SAMISTEERA 1FFAA BARA 2008 ITTI GUUTAMUU. M/B ____________________"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setFixedHeight(30f);
		cell.setBorder(0);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("KUTAA " + rbean.getCl_category()));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setFixedHeight(30f);
		cell.setBorder(0);
		table.addCell(cell);
		
		return table;
	}
	
	public static void addStudMarkListReportContent(Document document){
		
		float[] colWidth = {4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
		
		PdfPTable table = new PdfPTable(colWidth);
		table.setWidthPercentage(100);
		
		PdfPCell cell = new PdfPCell(tableHeader());
		cell.setColspan(19);
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		insertTableHeader(table, " ", Element.ALIGN_CENTER, 1, headerFont);
		insertTableHeader(table, ">=50", Element.ALIGN_CENTER, 3, headerFont);
		insertTableHeader(table, ">=50%", Element.ALIGN_CENTER, 3, headerFont);
		insertTableHeader(table, ">=75", Element.ALIGN_CENTER, 3, headerFont);
		insertTableHeader(table, ">=75%", Element.ALIGN_CENTER, 3, headerFont);
		insertTableHeader(table, ">=90", Element.ALIGN_CENTER, 3, headerFont);
		insertTableHeader(table, ">=90%", Element.ALIGN_CENTER, 3, headerFont);
		
		insertTableHeader(table, "G/BAR", Element.ALIGN_CENTER, 1, headerFont);
		
		insertTableHeader(table, "DHI", Element.ALIGN_CENTER, 1, headerFont);
		insertTableHeader(table, "DUB", Element.ALIGN_CENTER, 1, headerFont);
		insertTableHeader(table, "ID", Element.ALIGN_CENTER, 1, headerFont);		
		insertTableHeader(table, "DHI", Element.ALIGN_CENTER, 1, headerFont);
		insertTableHeader(table, "DUB", Element.ALIGN_CENTER, 1, headerFont);
		insertTableHeader(table, "ID", Element.ALIGN_CENTER, 1, headerFont);
		
		insertTableHeader(table, "DHI", Element.ALIGN_CENTER, 1, headerFont);
		insertTableHeader(table, "DUB", Element.ALIGN_CENTER, 1, headerFont);
		insertTableHeader(table, "ID", Element.ALIGN_CENTER, 1, headerFont);
		insertTableHeader(table, "DHI", Element.ALIGN_CENTER, 1, headerFont);
		insertTableHeader(table, "DUB", Element.ALIGN_CENTER, 1, headerFont);
		insertTableHeader(table, "ID", Element.ALIGN_CENTER, 1, headerFont);
		
		insertTableHeader(table, "DHI", Element.ALIGN_CENTER, 1, headerFont);
		insertTableHeader(table, "DUB", Element.ALIGN_CENTER, 1, headerFont);
		insertTableHeader(table, "ID", Element.ALIGN_CENTER, 1, headerFont);
		insertTableHeader(table, "DHI", Element.ALIGN_CENTER, 1, headerFont);
		insertTableHeader(table, "DUB", Element.ALIGN_CENTER, 1, headerFont);
		insertTableHeader(table, "ID", Element.ALIGN_CENTER, 1, headerFont);
		
		for(int i = 0; i < stud_mark_list_report.size()-1; i++){
			//insertTableHeader(table, stud_mark_list_report.get(i).getSub_bean().getSub_name(), Element.ALIGN_LEFT, 1, headerFont);
			cell = new PdfPCell(new Phrase(stud_mark_list_report.get(i).getSub_bean().getSub_name(), tblContentFont));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			//cell.setRotation(90);
			table.addCell(cell);
			
			insertTableHeader(table, String.valueOf(stud_mark_list_report.get(i).getMale_num_cat1()), Element.ALIGN_CENTER, 1, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_list_report.get(i).getFemale_num_cat1()), Element.ALIGN_CENTER, 1, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_list_report.get(i).getTotal_num_cat1()), Element.ALIGN_CENTER, 1, tblContentFont);		
			insertTableHeader(table, String.valueOf(stud_mark_list_report.get(i).getMale_percent_cat1() + "%"), Element.ALIGN_CENTER, 1, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_list_report.get(i).getFemale_percent_cat1() + "%"), Element.ALIGN_CENTER, 1, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_list_report.get(i).getTotal_percent_cat1() + "%"), Element.ALIGN_CENTER, 1, tblContentFont);
			
			insertTableHeader(table, String.valueOf(stud_mark_list_report.get(i).getMale_num_cat2()), Element.ALIGN_CENTER, 1, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_list_report.get(i).getFemale_num_cat2()), Element.ALIGN_CENTER, 1, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_list_report.get(i).getTotal_num_cat2()), Element.ALIGN_CENTER, 1, tblContentFont);		
			insertTableHeader(table, String.valueOf(stud_mark_list_report.get(i).getMale_percent_cat2() + "%"), Element.ALIGN_CENTER, 1, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_list_report.get(i).getFemale_percent_cat2() + "%"), Element.ALIGN_CENTER, 1, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_list_report.get(i).getTotal_percent_cat2() + "%"), Element.ALIGN_CENTER, 1, tblContentFont);
			
			insertTableHeader(table, String.valueOf(stud_mark_list_report.get(i).getMale_num_cat3()), Element.ALIGN_CENTER, 1, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_list_report.get(i).getFemale_num_cat3()), Element.ALIGN_CENTER, 1, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_list_report.get(i).getTotal_num_cat3()), Element.ALIGN_CENTER, 1, tblContentFont);		
			insertTableHeader(table, String.valueOf(stud_mark_list_report.get(i).getMale_percent_cat3() + "%"), Element.ALIGN_CENTER, 1, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_list_report.get(i).getFemale_percent_cat3() + "%"), Element.ALIGN_CENTER, 1, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_list_report.get(i).getTotal_percent_cat3() + "%"), Element.ALIGN_CENTER, 1, tblContentFont);
			
		}
		
		insertTableHeader(table, "", Element.ALIGN_CENTER, 1, tblContentFont);
		
		insertTableHeader(table, String.valueOf(stud_mark_list_report.get(stud_mark_list_report.size()-1).getGrt_50_total_male_cat1()), Element.ALIGN_CENTER, 1, tblContentFont);
		insertTableHeader(table, String.valueOf(stud_mark_list_report.get(stud_mark_list_report.size()-1).getGrt_50_total_female_cat1()), Element.ALIGN_CENTER, 1, tblContentFont);
		insertTableHeader(table, String.valueOf(stud_mark_list_report.get(stud_mark_list_report.size()-1).getGrt_50_total_cat1()), Element.ALIGN_CENTER, 1, tblContentFont);		
		insertTableHeader(table, String.valueOf(stud_mark_list_report.get(stud_mark_list_report.size()-1).getGrt_50_total_percent_male_cat1()) + "%", Element.ALIGN_CENTER, 1, tblContentFont);
		insertTableHeader(table, String.valueOf(stud_mark_list_report.get(stud_mark_list_report.size()-1).getGrt_50_total_percent_female_cat1()) + "%", Element.ALIGN_CENTER, 1, tblContentFont);
		insertTableHeader(table, String.valueOf(stud_mark_list_report.get(stud_mark_list_report.size()-1).getGrt_50_total_percent_cat1()) + "%", Element.ALIGN_CENTER, 1, tblContentFont);
		
		insertTableHeader(table, String.valueOf(stud_mark_list_report.get(stud_mark_list_report.size()-1).getGrt_75_total_male_cat2()), Element.ALIGN_CENTER, 1, tblContentFont);
		insertTableHeader(table, String.valueOf(stud_mark_list_report.get(stud_mark_list_report.size()-1).getGrt_75_total_female_cat2()), Element.ALIGN_CENTER, 1, tblContentFont);
		insertTableHeader(table, String.valueOf(stud_mark_list_report.get(stud_mark_list_report.size()-1).getGrt_75_total_cat2()), Element.ALIGN_CENTER, 1, tblContentFont);		
		insertTableHeader(table, String.valueOf(stud_mark_list_report.get(stud_mark_list_report.size()-1).getGrt_75_total_percent_male_cat2()) + "%", Element.ALIGN_CENTER, 1, tblContentFont);
		insertTableHeader(table, String.valueOf(stud_mark_list_report.get(stud_mark_list_report.size()-1).getGrt_75_total_percent_female_cat2()) + "%", Element.ALIGN_CENTER, 1, tblContentFont);
		insertTableHeader(table, String.valueOf(stud_mark_list_report.get(stud_mark_list_report.size()-1).getGrt_75_total_percent_cat2()) + "%", Element.ALIGN_CENTER, 1, tblContentFont);
		
		insertTableHeader(table, String.valueOf(stud_mark_list_report.get(stud_mark_list_report.size()-1).getGrt_90_total_male_cat3()), Element.ALIGN_CENTER, 1, tblContentFont);
		insertTableHeader(table, String.valueOf(stud_mark_list_report.get(stud_mark_list_report.size()-1).getGrt_90_total_female_cat3()), Element.ALIGN_CENTER, 1, tblContentFont);
		insertTableHeader(table, String.valueOf(stud_mark_list_report.get(stud_mark_list_report.size()-1).getGrt_90_total_cat3()), Element.ALIGN_CENTER, 1, tblContentFont);		
		insertTableHeader(table, String.valueOf(stud_mark_list_report.get(stud_mark_list_report.size()-1).getGrt_90_total_percent_male_cat3()) + "%", Element.ALIGN_CENTER, 1, tblContentFont);
		insertTableHeader(table, String.valueOf(stud_mark_list_report.get(stud_mark_list_report.size()-1).getGrt_90_total_percent_female_cat3()) + "%", Element.ALIGN_CENTER, 1, tblContentFont);
		insertTableHeader(table, String.valueOf(stud_mark_list_report.get(stud_mark_list_report.size()-1).getGrt_90_total_percent_cat3()) + "%", Element.ALIGN_CENTER, 1, tblContentFont);
		
		
		try {
			document.add(table);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertTableHeader(PdfPTable table, String text, int align, int colspan, Font font){
		
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setFixedHeight(30f);
		cell.setHorizontalAlignment(align);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setColspan(colspan);
		
		table.addCell(cell);
	}

	public static List<ReportBean> getStud_mark_list_report() {
		return stud_mark_list_report;
	}

	public static void setStud_mark_list_report(List<ReportBean> stud_mark_list_report) {
		PDF_StudMarkListReport.stud_mark_list_report = stud_mark_list_report;
	}

	public static ReportBean getRbean() {
		return rbean;
	}

	public static void setRbean(ReportBean rbean) {
		PDF_StudMarkListReport.rbean = rbean;
	}

}
