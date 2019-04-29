package util;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import cRoomClass.CRoomBean;
import examClass.exam_result.ExamResultBean;
import reportClass.ReportBean;
import subjectClass.SubjectBean;

public class PDF_FullYearAvgMarkList {

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
		
	private static List<List<ExamResultBean>> fullYearStudRoster = new ArrayList<List<ExamResultBean>>();
	private static List<ExamResultBean> studMarkList;
	private static List<SubjectBean> subList;
	private static ExamResultBean exrb;
	private static int noOfRows;
	private static List<ExamResultBean> orderedStudentAnnualTotalMark;
	private static List<ExamResultBean> studentTotalMarkPerQuarter;
	
	public static void generateAvgMarkListPDF(ExamResultBean exrb, List<ExamResultBean> studMarkList, List<SubjectBean> subList, String fullYearAvgFile, int rowNum, List<ExamResultBean> orderedStudentAnnualTotalMark, List<ExamResultBean> studentTotalMarkPerQuarter){
		
		setFullYearStudRoster(fullYearStudRoster);
		setStudMarkList(studMarkList);
		setSubList(subList);
		setExrb(exrb);
		setNoOfRows(rowNum);
		setOrderedStudentAnnualTotalMark(orderedStudentAnnualTotalMark);
		setStudentTotalMarkPerQuarter(studentTotalMarkPerQuarter);
		
		clearStudentsPerTotalMarkOnEachQuarter();
		sortStudentsPerTotalMarkOnEachQuarter();
		
		try{
			
			Document document = new Document();
			
			PdfWriter.getInstance(document, new FileOutputStream(fullYearAvgFile));	
			document.setPageSize(PageSize.A4.rotate());
			document.open();
			addMarkListMetaData(document, fullYearAvgFile);
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
		
		PdfPTable mrkListHeader = new PdfPTable(4);
		mrkListHeader.setWidthPercentage(100);
		
		PdfPCell cell = new PdfPCell(new Phrase(SysConstant.SCHOOL_FULL_NAME, customHeaderFont));
		cell.setColspan(4);
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		mrkListHeader.addCell(cell);
		
		cell = new PdfPCell(new Phrase(exrb.getAcademic_year() + " - Students Annual Roster", titleFont));
		cell.setColspan(0);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		mrkListHeader.addCell(cell);
		
		cell = new PdfPCell(new Phrase("" + exrb.getCl_name() + " " + exrb.getCd_name(), titleFont));
		cell.setColspan(0);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		mrkListHeader.addCell(cell);
		
		cell = new PdfPCell(new Phrase(exrb.getCur_date() + " E.C", headerFont));
		cell.setColspan(0);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		mrkListHeader.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Page 1", headerFont));
		cell.setColspan(0);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		mrkListHeader.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setColspan(4);
		cell.setBorder(0);
		cell.setFixedHeight(4f);
		mrkListHeader.addCell(cell);
		
		return mrkListHeader;
	}
	
	public static PdfPTable markListNullHeader(int page){
		
		PdfPTable mrkListHeader = new PdfPTable(4);
		mrkListHeader.setWidthPercentage(100);
		
		PdfPCell cell = new PdfPCell(new Phrase(SysConstant.SCHOOL_FULL_NAME, customHeaderFont));
		cell.setColspan(4);
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		mrkListHeader.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Students Annual Roster", titleFont));
		cell.setColspan(0);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		mrkListHeader.addCell(cell);
		
		cell = new PdfPCell(new Phrase("" + exrb.getCl_name() + " " + exrb.getCd_name(), titleFont));
		cell.setColspan(0);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		mrkListHeader.addCell(cell);
		
		cell = new PdfPCell(new Phrase(TodayDate_YYYYMMDD.getDayMonthYearFormat() + " E.C", headerFont));
		cell.setColspan(0);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		mrkListHeader.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" Page " + page, headerFont));
		cell.setColspan(0);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		mrkListHeader.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" "));
		cell.setColspan(4);
		cell.setBorder(0);
		cell.setFixedHeight(4f);
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
		
		cell = new PdfPCell(new Phrase("Hoom Room Teacher's Name: " + exrb.getHomeroom_teacher_name() + "       Signature: ____________________", titleFont));
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		mrkListFooter.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Director's Name - GETU TEZERA        Signature: _________________________ ", titleFont));
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		mrkListFooter.addCell(cell);
		
		cell = new PdfPCell(new Phrase("", titleFont));
		cell.setBorder(0);
		cell.setColspan(2);
		cell.setFixedHeight(10f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		mrkListFooter.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Term 1: _____________    Term 2: _____________    Term 3: _____________    Term 4: _____________ ", titleFont));
		cell.setBorder(0);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		mrkListFooter.addCell(cell);
								
		return mrkListFooter;
	}
	
	public static PdfPTable markListTable(){
				
		int columnWidth = 8 + subList.size();
		
		float[] columnWidths = new float[columnWidth];
		
		for(int i = 0; i < columnWidths.length; i++){
			
			if(i == 0){
				
				columnWidths[i] = 4;
			} else if(i == 1){
				
				columnWidths[i] = 25;
			} else if(i == 2 || i == 3 || i == 4){
				
				columnWidths[i] = 4;
			} else {
			
				columnWidths[i] = 7;
			}
		}		
		
		PdfPTable markListTbl = new PdfPTable(columnWidths);
		
		PdfPCell cell = new PdfPCell(markListHeader());
		cell.setColspan(columnWidth);
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		markListTbl.addCell(cell);
				
		insertCell(markListTbl, "No", Element.ALIGN_CENTER, 1, 2, tblHeaderFont, "HORNO");
		insertCell(markListTbl, "Student Name", Element.ALIGN_CENTER, 1, 2, tblHeaderFont, "HORSN");
		insertCell(markListTbl, "Sex", Element.ALIGN_CENTER, 1, 2, tblHeaderFont, "VER");
		insertCell(markListTbl, "Age", Element.ALIGN_CENTER, 1, 2, tblHeaderFont, "VER");
		insertCell(markListTbl, "Term", Element.ALIGN_CENTER, 1, 2, tblHeaderFont, "VER");
		
		insertCell(markListTbl, "Subject", Element.ALIGN_CENTER, subList.size(), 1, tblHeaderFont, "HORSUB");
		
		insertCell(markListTbl, "Sum", Element.ALIGN_CENTER, 1, 2, tblHeaderFont, "VER");
		insertCell(markListTbl, "Average", Element.ALIGN_CENTER, 1, 2, tblHeaderFont, "VER");
		insertCell(markListTbl, "Rank", Element.ALIGN_CENTER, 1, 2, tblHeaderFont, "VER");
		
		for(int i = 0; i < subList.size(); i++){
			
			insertCell(markListTbl, subList.get(i).getSub_name(), Element.ALIGN_CENTER, 1, 1, tblHeaderFont, "VER");		
		}
		
		String[] sub_total = new String[subList.size()];
		
		String stud_name = "", pervious_ann_type = "";
		
		int rowNum = 0, sub_counter = 0, add_sub_counter = 0, added_subject_count = 0, sem_counter = 0, active_sem = 0, sem_check = 0, page_counter = 0, page_number = 2, dropout_count = 0;
		
		double qrtTotal = 0.0;
		
		for(int i = 0; i < studMarkList.size(); i++){
			
			//sem_counter++;
			
			/* ----- 1. to add the name, sex and age of student ----- */
			
			if(i == 0){
				
				rowNum += 1;
				
				stud_name = studMarkList.get(i).getFname() + " " + studMarkList.get(i).getMname() + " " + studMarkList.get(i).getGname();
				
				insertCell(markListTbl, String.valueOf(rowNum), Element.ALIGN_CENTER, 1, 5, tblContentFont, "HOR");			
				
				insertCell(markListTbl, stud_name, Element.ALIGN_LEFT, 1, 5, tblContentFont, "HOR");
				
				insertCell(markListTbl, studMarkList.get(i).getStud().getSex(), Element.ALIGN_CENTER, 1, 5, tblContentFont, "HOR");
				
				insertCell(markListTbl, studMarkList.get(i).getStud().getAge(), Element.ALIGN_CENTER, 1, 5, tblContentFont, "HOR");
				
			} else if(i != 0 && !stud_name.equals(studMarkList.get(i).getFname() + " " + studMarkList.get(i).getMname() + " " + studMarkList.get(i).getGname())) {
				
				rowNum += 1;
				
				stud_name = studMarkList.get(i).getFname() + " " + studMarkList.get(i).getMname() + " " + studMarkList.get(i).getGname();
				
				insertCell(markListTbl, String.valueOf(rowNum), Element.ALIGN_CENTER, 1, 5, tblContentFont, "HOR");	

				insertCell(markListTbl, stud_name, Element.ALIGN_LEFT, 1, 5, tblContentFont, "HOR");
				
				insertCell(markListTbl, studMarkList.get(i).getStud().getSex(), Element.ALIGN_CENTER, 1, 5, tblContentFont, "HOR");
				
				insertCell(markListTbl, studMarkList.get(i).getStud().getAge(), Element.ALIGN_CENTER, 1, 5, tblContentFont, "HOR");				
			}
			
			/* ----- 2. to add the terms ----- */
			
			String at_type = studMarkList.get(i).getAt_id();
			
			if(pervious_ann_type.length() == 0 || !at_type.equals(pervious_ann_type)) {
				
				insertCell(markListTbl, studMarkList.get(i).getAt_id(), Element.ALIGN_CENTER, 1, 1, tblContentFont, "HOR");
				
				pervious_ann_type = at_type;
				
				sem_counter++;				
			} 
			
			if(studMarkList.get(i).getConvert_to_grade().equals("NO") && studMarkList.get(i).getSub_included_in_total_mark().equals("Yes")){
				
				qrtTotal += Double.parseDouble(studMarkList.get(i).getSubjectTotal_mark()== null?"0.0":studMarkList.get(i).getSubjectTotal_mark());
				
				add_sub_counter++;
			}
			
			
			/* ----- 3. to add each subject total mark ----- */
			
			insertCell(markListTbl, "".equals(studMarkList.get(i).getSubjectTotal_mark())?"-":studMarkList.get(i).getSubjectTotal_mark(), Element.ALIGN_CENTER, 1, 1, tblContentFont, "HOR");
			
			
			/* ----- 4. to add each subject total mark vertically per term ----- */
			
			if(studMarkList.get(i).getConvert_to_grade().equals("NO")){
				
				sub_total[sub_counter] = String.valueOf(Double.parseDouble((sub_total[sub_counter] == null || sub_total[sub_counter] == "-")?"0.0":sub_total[sub_counter]) + Double.parseDouble(studMarkList.get(i).getSubjectTotal_mark() == null?"0.0":studMarkList.get(i).getSubjectTotal_mark()));
				
				sem_check++;
			}			
			if(studMarkList.get(i).getConvert_to_grade().equals("YES")){
				
				sub_total[sub_counter] = "-";
			}						
			
			
			/* ----- 5. to get the total result and average of the term ----- */
			
			sub_counter++;
			//sem_check = 0;
			
			if(sub_counter == subList.size()){
				
				insertCell(markListTbl, String.valueOf(qrtTotal), Element.ALIGN_CENTER, 1, 1, tblContentFont, "HOR");
				
				insertCell(markListTbl, String.format("%,.2f", qrtTotal/add_sub_counter), Element.ALIGN_CENTER, 1, 1, tblContentFont, "HOR");
				
				insertCell(markListTbl, getQuarterRank(studMarkList.get(i).getSi_id(), at_type), Element.ALIGN_CENTER, 1, 1, tblContentFont, "HOR");
				
				added_subject_count = add_sub_counter;
				
				if(qrtTotal > 0.0){
					
					active_sem++;					
				}
				
				sub_counter = 0; 
				add_sub_counter = 0;
				qrtTotal = 0.0;
				page_counter++;
			}
			
			////**** Average Mark Total ****////
			if(sem_counter == 4 && sub_counter == 0){
								
				insertCell(markListTbl, "AV", Element.ALIGN_CENTER, 1, 1, tblContentFont, "HOR");
				
				double avg_total = 0.0;
				
				for(int x = 0; x < sub_total.length; x++){
					
					if(!sub_total[x].equals("-")){
						
						insertCell(markListTbl, String.format("%,.2f", Double.parseDouble(sub_total[x] == null?"0.0":sub_total[x])/active_sem), Element.ALIGN_CENTER, 1, 1, tblContentFont, "HOR");
											
						if(studMarkList.get(x).getSub_included_in_total_mark().equals("Yes")){
							
							avg_total += Double.parseDouble(sub_total[x] == null?"0.0":sub_total[x])/active_sem;							
						}
						
					} else  {
						
						insertCell(markListTbl, "", Element.ALIGN_CENTER, 1, 1, tblContentFont, "HOR");
					}
				}
				
				insertCell(markListTbl, String.format("%,.2f", avg_total), Element.ALIGN_CENTER, 1, 1, tblContentFont, "HOR");
				
				insertCell(markListTbl, String.format("%,.2f", avg_total/added_subject_count), Element.ALIGN_CENTER, 1, 1, tblContentFont, "HOR");
				
				for(int a = 0; a < orderedStudentAnnualTotalMark.size(); a++){
					
					if(orderedStudentAnnualTotalMark.get(a).getSi_id().equals(studMarkList.get(i).getSi_id())){
						
						dropout_count++;
						
						insertCell(markListTbl, "- " + orderedStudentAnnualTotalMark.get(a).getStud_rank() + " -", Element.ALIGN_CENTER, 1, 1, tblContentFont, "HOR");
						
						break;
					}
				}
				
				if(dropout_count == 0){
					
					insertCell(markListTbl, "DROP", Element.ALIGN_CENTER, 1, 1, tblContentFont, "HOR");
				}
				
				sem_counter = 0;
				
				active_sem = 0;
				
				dropout_count = 0;
				
				sub_total = new String[subList.size()];
			}
			
			
			////**** to add header every page ****////
			if(page_counter == 28 && studMarkList.size() - i > 28){
				
				cell = new PdfPCell(markListNullHeader(page_number));
				cell.setColspan(columnWidth);
				cell.setBorder(0);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				markListTbl.addCell(cell);
				
				insertCell(markListTbl, "No", Element.ALIGN_CENTER, 1, 2, tblHeaderFont, "HORNO");
				insertCell(markListTbl, "Student Name", Element.ALIGN_CENTER, 1, 2, tblHeaderFont, "HORSN");
				insertCell(markListTbl, "Sex", Element.ALIGN_CENTER, 1, 2, tblHeaderFont, "VER");
				insertCell(markListTbl, "Age", Element.ALIGN_CENTER, 1, 2, tblHeaderFont, "VER");
				insertCell(markListTbl, "Term", Element.ALIGN_CENTER, 1, 2, tblHeaderFont, "VER");
				
				insertCell(markListTbl, "Subject", Element.ALIGN_CENTER, subList.size(), 1, tblHeaderFont, "HORSUB");
				
				insertCell(markListTbl, "Sum", Element.ALIGN_CENTER, 1, 2, tblHeaderFont, "VER");
				insertCell(markListTbl, "Average", Element.ALIGN_CENTER, 1, 2, tblHeaderFont, "VER");
				insertCell(markListTbl, "Rank", Element.ALIGN_CENTER, 1, 2, tblHeaderFont, "VER");
				
				for(int j = 0; j < subList.size(); j++){
					
					insertCell(markListTbl, subList.get(j).getSub_name(), Element.ALIGN_CENTER, 1, 1, tblHeaderFont, "VER");		
				}
				
				page_counter = 0;
				
				page_number++;
			}
			
		}
		
		cell = new PdfPCell(markListFooter());
		cell.setColspan(columnWidth);
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		markListTbl.addCell(cell);
		
		return markListTbl;
	}
	
	
	static List<ExamResultBean> firstQrtSortingPerTotalMark = new ArrayList<ExamResultBean>();
	static List<ExamResultBean> secondQrtSortingPerTotalMark = new ArrayList<ExamResultBean>();
	static List<ExamResultBean> thirdQrtSortingPerTotalMark = new ArrayList<ExamResultBean>();
	static List<ExamResultBean> fourthQrtSortingPerTotalMark = new ArrayList<ExamResultBean>();
	
	private static void clearStudentsPerTotalMarkOnEachQuarter(){
		
		firstQrtSortingPerTotalMark.clear();
		secondQrtSortingPerTotalMark.clear();
		thirdQrtSortingPerTotalMark.clear();
		fourthQrtSortingPerTotalMark.clear();
	}
	
	private static void sortStudentsPerTotalMarkOnEachQuarter(){
		
		for(int i = 0; i < studentTotalMarkPerQuarter.size(); i++){
			
			if(studentTotalMarkPerQuarter.get(i).getAt_id().equals("1")){
				
				firstQrtSortingPerTotalMark.add(studentTotalMarkPerQuarter.get(i));
			}
			if(studentTotalMarkPerQuarter.get(i).getAt_id().equals("2")){
				
				secondQrtSortingPerTotalMark.add(studentTotalMarkPerQuarter.get(i));
			}
			if(studentTotalMarkPerQuarter.get(i).getAt_id().equals("3")){
				
				thirdQrtSortingPerTotalMark.add(studentTotalMarkPerQuarter.get(i));
			}
			if(studentTotalMarkPerQuarter.get(i).getAt_id().equals("4")){
				
				fourthQrtSortingPerTotalMark.add(studentTotalMarkPerQuarter.get(i));
			}
		}
		
		///*** First Quarter Student Ranking ***///
		if(firstQrtSortingPerTotalMark.size() > 0){
			
			Collections.sort(firstQrtSortingPerTotalMark, new Comparator<ExamResultBean>() {
				public int compare(ExamResultBean a, ExamResultBean b){
					int rslt = 0;
					
					if(Double.parseDouble(b.getTotal_mark()) > Double.parseDouble(a.getTotal_mark())){
						rslt = 1;
					}
					if(Double.parseDouble(b.getTotal_mark()) < Double.parseDouble(a.getTotal_mark())){
						rslt = -1;
					}
					if(Double.parseDouble(b.getTotal_mark()) == Double.parseDouble(a.getTotal_mark())){
						rslt = 0;
					}
					
					return rslt;
				}
			});
		}
		
		double pervious_total_1 = 0.0;
		int pervious_rank_1 = 0, same_rank_counter_1 = 0;
		
		for(int i = 0; i < firstQrtSortingPerTotalMark.size(); i++){
			
			if(pervious_total_1 == 0.0){
				
				pervious_total_1 = Double.parseDouble(firstQrtSortingPerTotalMark.get(i).getTotal_mark());
				
				pervious_rank_1 = 1;
				
				firstQrtSortingPerTotalMark.get(i).setStud_rank(String.valueOf(pervious_rank_1));
				
			} else if(pervious_total_1 != 0.0 && Double.parseDouble(firstQrtSortingPerTotalMark.get(i).getTotal_mark()) == pervious_total_1){
				
				pervious_total_1 = Double.parseDouble(firstQrtSortingPerTotalMark.get(i).getTotal_mark());
				
				firstQrtSortingPerTotalMark.get(i).setStud_rank(String.valueOf(pervious_rank_1));
				
				same_rank_counter_1++;
				
			} else {
				
				pervious_total_1 = Double.parseDouble(firstQrtSortingPerTotalMark.get(i).getTotal_mark());
				
				pervious_rank_1 = pervious_rank_1 + same_rank_counter_1 + 1;
				
				firstQrtSortingPerTotalMark.get(i).setStud_rank(String.valueOf(pervious_rank_1));
				
				same_rank_counter_1 = 0;
			}
		}
		///*** END - First Quarter Student Ranking ***///
		
		
		///*** Second Quarter Student Ranking ***///
		if(secondQrtSortingPerTotalMark.size() > 0){
			
			Collections.sort(secondQrtSortingPerTotalMark, new Comparator<ExamResultBean>() {
				public int compare(ExamResultBean a, ExamResultBean b){
					int rslt = 0;
					
					if(Double.parseDouble(b.getTotal_mark()) > Double.parseDouble(a.getTotal_mark())){
						rslt = 1;
					}
					if(Double.parseDouble(b.getTotal_mark()) < Double.parseDouble(a.getTotal_mark())){
						rslt = -1;
					}
					if(Double.parseDouble(b.getTotal_mark()) == Double.parseDouble(a.getTotal_mark())){
						rslt = 0;
					}
					
					return rslt;
				}
			});
		}
		double pervious_total_2 = 0.0;
		int pervious_rank_2 = 0, same_rank_counter_2 = 0;
		
		for(int i = 0; i < secondQrtSortingPerTotalMark.size(); i++){
			
			if(pervious_total_2 == 0.0){
				
				pervious_total_2 = Double.parseDouble(secondQrtSortingPerTotalMark.get(i).getTotal_mark());
				
				pervious_rank_2 = 1;
				
				secondQrtSortingPerTotalMark.get(i).setStud_rank(String.valueOf(pervious_rank_2));
				
			} else if(pervious_total_2 != 0.0 && Double.parseDouble(secondQrtSortingPerTotalMark.get(i).getTotal_mark()) == pervious_total_2){
				
				pervious_total_2 = Double.parseDouble(secondQrtSortingPerTotalMark.get(i).getTotal_mark());
				
				secondQrtSortingPerTotalMark.get(i).setStud_rank(String.valueOf(pervious_rank_2));
				
				same_rank_counter_2++;
				
			} else {
				
				pervious_total_2 = Double.parseDouble(secondQrtSortingPerTotalMark.get(i).getTotal_mark());
				
				pervious_rank_2 = pervious_rank_2 + same_rank_counter_2 + 1;
				
				secondQrtSortingPerTotalMark.get(i).setStud_rank(String.valueOf(pervious_rank_2));
				
				same_rank_counter_2 = 0;
			}
		}
		///*** END - Second Quarter Student Ranking ***///
		
		
		///*** Third Quarter Student Ranking ***///
		if(thirdQrtSortingPerTotalMark.size() > 0){
			
			Collections.sort(thirdQrtSortingPerTotalMark, new Comparator<ExamResultBean>() {
				public int compare(ExamResultBean a, ExamResultBean b){
					int rslt = 0;
					
					if(Double.parseDouble(b.getTotal_mark()) > Double.parseDouble(a.getTotal_mark())){
						rslt = 1;
					}
					if(Double.parseDouble(b.getTotal_mark()) < Double.parseDouble(a.getTotal_mark())){
						rslt = -1;
					}
					if(Double.parseDouble(b.getTotal_mark()) == Double.parseDouble(a.getTotal_mark())){
						rslt = 0;
					}
					
					return rslt;
				}
			});
		}
		double pervious_total_3 = 0.0;
		int pervious_rank_3 = 0, same_rank_counter_3 = 0;
		
		for(int i = 0; i < thirdQrtSortingPerTotalMark.size(); i++){
			
			if(pervious_total_3 == 0.0){
				
				pervious_total_3 = Double.parseDouble(thirdQrtSortingPerTotalMark.get(i).getTotal_mark());
				
				pervious_rank_3 = 1;
				
				thirdQrtSortingPerTotalMark.get(i).setStud_rank(String.valueOf(pervious_rank_3));
				
			} else if(pervious_total_3 != 0.0 && Double.parseDouble(thirdQrtSortingPerTotalMark.get(i).getTotal_mark()) == pervious_total_3){
				
				pervious_total_3 = Double.parseDouble(thirdQrtSortingPerTotalMark.get(i).getTotal_mark());
				
				thirdQrtSortingPerTotalMark.get(i).setStud_rank(String.valueOf(pervious_rank_3));
				
				same_rank_counter_3++;
				
			} else {
				
				pervious_total_3 = Double.parseDouble(thirdQrtSortingPerTotalMark.get(i).getTotal_mark());
				
				pervious_rank_3 = pervious_rank_3 + same_rank_counter_3 + 1;
				
				thirdQrtSortingPerTotalMark.get(i).setStud_rank(String.valueOf(pervious_rank_3));
				
				same_rank_counter_3 = 0;
			}
		}
		///*** END - Third Quarter Student Ranking ***///
		
		
		///*** Fourth Quarter Student Ranking ***///
		if(fourthQrtSortingPerTotalMark.size() > 0){
			
			Collections.sort(fourthQrtSortingPerTotalMark, new Comparator<ExamResultBean>() {
				public int compare(ExamResultBean a, ExamResultBean b){
					int rslt = 0;
					
					if(Double.parseDouble(b.getTotal_mark()) > Double.parseDouble(a.getTotal_mark())){
						rslt = 1;
					}
					if(Double.parseDouble(b.getTotal_mark()) < Double.parseDouble(a.getTotal_mark())){
						rslt = -1;
					}
					if(Double.parseDouble(b.getTotal_mark()) == Double.parseDouble(a.getTotal_mark())){
						rslt = 0;
					}
					
					return rslt;
				}
			});
		}
		double pervious_total_4 = 0.0;
		int pervious_rank_4 = 0, same_rank_counter_4 = 0;
		
		for(int i = 0; i < fourthQrtSortingPerTotalMark.size(); i++){
			
			if(pervious_total_4 == 0.0){
				
				pervious_total_4 = Double.parseDouble(fourthQrtSortingPerTotalMark.get(i).getTotal_mark());
				
				pervious_rank_4 = 1;
				
				fourthQrtSortingPerTotalMark.get(i).setStud_rank(String.valueOf(pervious_rank_4));
				
			} else if(pervious_total_4 != 0.0 && Double.parseDouble(fourthQrtSortingPerTotalMark.get(i).getTotal_mark()) == pervious_total_4){
				
				pervious_total_4 = Double.parseDouble(fourthQrtSortingPerTotalMark.get(i).getTotal_mark());
				
				fourthQrtSortingPerTotalMark.get(i).setStud_rank(String.valueOf(pervious_rank_4));
				
				same_rank_counter_4++;
				
			} else {
				
				pervious_total_4 = Double.parseDouble(fourthQrtSortingPerTotalMark.get(i).getTotal_mark());
				
				pervious_rank_4 = pervious_rank_4 + same_rank_counter_4 + 1;
				
				fourthQrtSortingPerTotalMark.get(i).setStud_rank(String.valueOf(pervious_rank_4));
				
				same_rank_counter_4 = 0;
			}
		}
		///*** END - Fourth Quarter Student Ranking ***///
	}

	private static String getQuarterRank(String si_id, String at_id){
		
		String rank = "";
		
		StringBuffer atid = new StringBuffer("");
		
		List<ExamResultBean> selectedList = new ArrayList<ExamResultBean>();
		
		if(at_id.equals("I")){
			
			selectedList = firstQrtSortingPerTotalMark;
		}
		if(at_id.equals("II")){
			
			selectedList = secondQrtSortingPerTotalMark;
		}
		if(at_id.equals("III")){
			
			selectedList = thirdQrtSortingPerTotalMark;
		}
		if(at_id.equals("IV")){
			
			selectedList = fourthQrtSortingPerTotalMark;
		}
		
		for(int i = 0; i < selectedList.size(); i++){
			
			atid = new StringBuffer(selectedList.get(i).getAt_id().equals("1")?"I":selectedList.get(i).getAt_id().equals("2")?"II":selectedList.get(i).getAt_id().equals("3")?"III":"IV");
			
			if(atid.toString().equals(at_id)){
				
				if(si_id.equals(selectedList.get(i).getSi_id())){
					
					rank = selectedList.get(i).getStud_rank();
					
					break;
				}
			}
		}
		
		return String.valueOf(rank.length() == 0?"-":rank);
	}
	
	private static void insertCell(PdfPTable table, String text, int align, int colspan, int rowspan, Font font, String textDirection){
		
		String val = text + "";
		
		PdfPCell cell = new PdfPCell(new Phrase(text == null?"":text, font));
		
		cell.setHorizontalAlignment(align);
		
		cell.setRowspan(rowspan);
		
		cell.setColspan(colspan);
		
		if(val.equalsIgnoreCase("")){
			cell.setMinimumHeight(12f);
		}
		
		if(textDirection.equals("VER")){
			cell.setRotation(90);	
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		}
		
		if(textDirection.equals("HOR")){
			//cell.setFixedHeight(15f);
		}
		
		cell.setMinimumHeight(12f);
		
		table.addCell(cell);
	}

	public static List<List<ExamResultBean>> getFullYearStudRoster() {
		return fullYearStudRoster;
	}

	public static void setFullYearStudRoster(List<List<ExamResultBean>> fullYearStudRoster) {
		PDF_FullYearAvgMarkList.fullYearStudRoster = fullYearStudRoster;
	}

	public static List<SubjectBean> getSubList() {
		return subList;
	}

	public static void setSubList(List<SubjectBean> subList) {
		PDF_FullYearAvgMarkList.subList = subList;
	}

	public static ExamResultBean getExrb() {
		return exrb;
	}

	public static void setExrb(ExamResultBean exrb) {
		PDF_FullYearAvgMarkList.exrb = exrb;
	}

	public static int getNoOfRows() {
		return noOfRows;
	}

	public static void setNoOfRows(int noOfRows) {
		PDF_FullYearAvgMarkList.noOfRows = noOfRows;
	}

	public static List<ExamResultBean> getOrderedStudentAnnualTotalMark() {
		return orderedStudentAnnualTotalMark;
	}

	public static void setOrderedStudentAnnualTotalMark(List<ExamResultBean> orderedStudentAnnualTotalMark) {
		PDF_FullYearAvgMarkList.orderedStudentAnnualTotalMark = orderedStudentAnnualTotalMark;
	}

	public static List<ExamResultBean> getStudMarkList() {
		return studMarkList;
	}

	public static void setStudMarkList(List<ExamResultBean> studMarkList) {
		PDF_FullYearAvgMarkList.studMarkList = studMarkList;
	}

	public static List<ExamResultBean> getStudentTotalMarkPerQuarter() {
		return studentTotalMarkPerQuarter;
	}

	public static void setStudentTotalMarkPerQuarter(List<ExamResultBean> studentTotalMarkPerQuarter) {
		PDF_FullYearAvgMarkList.studentTotalMarkPerQuarter = studentTotalMarkPerQuarter;
	}

}
