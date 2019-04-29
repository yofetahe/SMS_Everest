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

public class PDF_StudMarkPlanAndImp {
	
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
	private static Font tblContentFont = FontFactory.getFont(FONT, BaseFont.CP1257, BaseFont.EMBEDDED, 11, Font.NORMAL); 
		
	private static List<ReportBean> stud_mark_plan_imp_list;
	private static ReportBean rbean;
	
	public static void getStudMarkPlanAndImpPDF(List<ReportBean> stud_mark_plan_imp_list, String fileNamePath, ReportBean rbean){
		
		setStud_mark_plan_imp_list(stud_mark_plan_imp_list);
		setRbean(rbean);
		
		try{
			
			Document document = new Document();			
			PdfWriter.getInstance(document, new FileOutputStream(fileNamePath));
			document.setPageSize(PageSize.A4.rotate());
			document.open();
			addStudMarkPlanImpMetaData(document, fileNamePath);
			addStudMarkPlanImpContent(document);
			document.close();
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void addStudMarkPlanImpMetaData(Document document, String FileName){
		document.addTitle("Student Mark Plan And Imp");
		document.addSubject("Everest Student Mark Plan Report");
		document.addKeywords("java, PDF, iText" + FileName);
		document.addAuthor("Yofetahe");
		document.addCreator("Fidel");		
	}
	
	public static PdfPTable studMarkPlanImpHeader(){
		float[] colWidth = {5, 2, 2};
		PdfPTable tbl = new PdfPTable(colWidth);
		tbl.setWidthPercentage(80);
		
		PdfPCell cell = new PdfPCell(new Phrase("Cuca Gabaasa Madaallii Karooraa fi Raawwii Tooyya'insa Qabxii Barattotaa", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setFixedHeight(20f);
		cell.setBorder(0);
		tbl.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Kan Kutaa " + rbean.getCl_category(), headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(0);
		tbl.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Guca 01", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(0);
		tbl.addCell(cell);
		
		return tbl;
	}
	
	public static void addStudMarkPlanImpContent(Document document){
		
		PdfPTable table = new PdfPTable(40);
		table.setWidthPercentage(100);
		
		PdfPCell cell = new PdfPCell(studMarkPlanImpHeader());
		cell.setColspan(40);
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		////---------** Row 1 **---------////		
		insertTableHeader(table, "Baay'ina\nBarattootaa\nBarumsaaf\nGalmaa'anii", Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, 3, 2, 90, headerFont);
		insertTableHeader(table, "Baay'ina\nBarattootaa\nQorumsa\nFundhatanii", Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, 3, 2, 90, headerFont);
		
		insertTableHeader(table, "Gosa Barnootaa", Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, 1, 3, 90, headerFont);
		
		insertTableHeader(table, "Barattoota Qoraman Keessaa 50%\nkan Ta'an Qabxii 50-74 Akka\nGalmeessisan Taasisuu", Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, 9, 1, 0, headerFont);
		insertTableHeader(table, "Barattoota Qoraman Keessaa 35%\nKan Ta'an Qabxii 75-89 Akka\nGalmeessisan Taasisuu", Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, 9, 1, 0, headerFont);
		insertTableHeader(table, "Barattoota Qoraman Keessaa 35%\nKan Ta'an Qabxii 90 fi isaa 01 Akka\nGalmeessisan Taasisuu", Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, 9, 1, 0, headerFont);
		
		insertTableHeader(table, "Waliigala Barattoota\nQoraman Keessaa\nQabxii 50 fi Isaa 01\nFidan", Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, 3, 2, 90, headerFont);
		insertTableHeader(table, "Waliigala Barattoota\nQoraman Keessaa\nQabxii 50 fi Isaa 01 Kan\nGalmeessisan % dhaan", Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, 3, 2, 90, headerFont);
				
		////---------** Row 2 **---------////
		insertTableHeader(table, "Karoora\n50%", Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, 3, 1, 0, headerFont);
		insertTableHeader(table, "Raawwii", Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, 3, 1, 0, headerFont);
		insertTableHeader(table, "Raawwii\n%dhaan", Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, 3, 1, 0, headerFont);
		
		////---------****---------////
		insertTableHeader(table, "Karoora\n35%", Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, 3, 1, 0, headerFont);
		insertTableHeader(table, "Raawwii", Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, 3, 1, 0, headerFont);
		insertTableHeader(table, "Raawwii\n%dhaan", Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, 3, 1, 0, headerFont);
		
		////---------****---------////
		insertTableHeader(table, "Karoora\n15%", Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, 3, 1, 0, headerFont);
		insertTableHeader(table, "Raawwii", Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, 3, 1, 0, headerFont);
		insertTableHeader(table, "Raawwii\n%dhaan", Element.ALIGN_CENTER, Element.ALIGN_MIDDLE, 3, 1, 0, headerFont);
				
		////---------** Row 3 **---------////
		insertTableHeader(table, "Dhira", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Dubara", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Ida'ama", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		
		////---------****---------////
		insertTableHeader(table, "Dhira", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Dubara", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Ida'ama", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		
		////---------****---------////		
		insertTableHeader(table, "Dhira %", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Dubara %", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Ida'ama %", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		
		////---------****---------////
		insertTableHeader(table, "Dhira", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Dubara", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Ida'ama", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		
		////---------****---------////
		insertTableHeader(table, "Dhira", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Dubara", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Ida'ama", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		
		////---------****---------////		
		insertTableHeader(table, "Dhira %", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Dubara %", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Ida'ama %", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		
		////---------****---------////
		insertTableHeader(table, "Dhira", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Dubara", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Ida'ama", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		
		////---------****---------////
		insertTableHeader(table, "Dhira", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Dubara", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Ida'ama", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		
		////---------****---------////
		insertTableHeader(table, "Dhira %", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Dubara %", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Ida'ama %", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		
		////---------****---------////
		insertTableHeader(table, "Dhira", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Dubara", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Ida'ama", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		
		////---------****---------////
		insertTableHeader(table, "Dhira", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Dubara", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Ida'ama", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		
		////---------****---------////
		insertTableHeader(table, "Dhira", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Dubara", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Ida'ama", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		
		////---------****---------////
		insertTableHeader(table, "Dhira", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Dubara", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		insertTableHeader(table, "Ida'ama", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, headerFont);
		
		
		for(int i = 0; i < stud_mark_plan_imp_list.size(); i++){
					
			////---------** Row 3 **---------////
			insertTableHeader(table, stud_mark_plan_imp_list.get(i).getStud_bean().getNo_male(), Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, stud_mark_plan_imp_list.get(i).getStud_bean().getNo_female(), Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, stud_mark_plan_imp_list.get(i).getStud_bean().getTotal_number(), Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			
			////---------****---------////
			insertTableHeader(table, stud_mark_plan_imp_list.get(i).getStud_bean().getNo_male(), Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, stud_mark_plan_imp_list.get(i).getStud_bean().getNo_female(), Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, stud_mark_plan_imp_list.get(i).getStud_bean().getTotal_number(), Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			
			////---------****---------////
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getSub_bean().getSub_name()), Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			
			////---------****---------////		
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getMale_stat_percent_cat1()) + "%", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getFemale_stat_percent_cat1()) + "%", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getTotal_stat_percent_cat1()) + "%", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			
			////---------****---------////
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getMale_num_cat1()), Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getFemale_num_cat1()), Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getTotal_num_cat1()), Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			
			////---------****---------////
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getMale_percent_cat1()) + "%", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getFemale_percent_cat1()) + "%", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getTotal_percent_cat1()) + "%", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			
			////---------****---------////		
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getMale_stat_percent_cat2()) + "%", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getFemale_stat_percent_cat2()) + "%", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getTotal_stat_percent_cat2()) + "%", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			
			////---------****---------////
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getMale_num_cat2()), Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getFemale_num_cat2()), Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getTotal_num_cat2()), Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			
			////---------****---------////
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getMale_percent_cat2()) + "%", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getFemale_percent_cat2()) + "%", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getTotal_percent_cat2()) + "%", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			
			////---------****---------////
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getMale_stat_percent_cat3()) + "%", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getFemale_stat_percent_cat3()) + "%", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getTotal_stat_percent_cat3()) + "%", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			
			////---------****---------////
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getMale_num_cat3()), Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getFemale_num_cat3()), Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getTotal_num_cat3()), Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			
			////---------****---------////
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getMale_percent_cat3()) + "%", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getFemale_percent_cat3()) + "%", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getTotal_percent_cat3()) + "%", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			
			////---------****---------////
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getMale_grt_50()), Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getFemale_grt_50()), Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getTotal_grt_50()), Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			
			////---------****---------////
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getMale_percent_grt_50()) + "%", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getFemale_percent_grt_50()) + "%", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
			insertTableHeader(table, String.valueOf(stud_mark_plan_imp_list.get(i).getTotal_percent_grt_50()) + "%", Element.ALIGN_LEFT, Element.ALIGN_MIDDLE, 1, 1, 90, tblContentFont);
		}
		
		try {
			document.add(table);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	private static void insertTableHeader(PdfPTable table, String text, int halign, int valign, int colspan, int rowspan, int rotation, Font font){
		
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setRotation(rotation);
		cell.setColspan(colspan);
		cell.setRowspan(rowspan);
		cell.setHorizontalAlignment(halign);
		cell.setVerticalAlignment(valign);
		table.addCell(cell);
		
	}
	
	
	
	
	
	
	public static List<ReportBean> getStud_mark_plan_imp_list() {
		return stud_mark_plan_imp_list;
	}

	public static void setStud_mark_plan_imp_list(List<ReportBean> stud_mark_plan_imp_list) {
		PDF_StudMarkPlanAndImp.stud_mark_plan_imp_list = stud_mark_plan_imp_list;
	}

	public static ReportBean getRbean() {
		return rbean;
	}

	public static void setRbean(ReportBean rbean) {
		PDF_StudMarkPlanAndImp.rbean = rbean;
	}

}
