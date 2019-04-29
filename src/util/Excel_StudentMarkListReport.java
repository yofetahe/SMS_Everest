package util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import reportClass.ReportBean;

public class Excel_StudentMarkListReport {
	
	public static boolean generateExcelFile(String fileName, List<ReportBean> stud_mark_list_report){
		boolean rslt = false;
		
		try{
			
			File myDir = new File(SysConstant.CERT_PDF_PATH);
			myDir.getParentFile().mkdir();
			
			File file = new File(myDir, fileName+".xls");
			
			FileWriter fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);
			
			pw.println(" \t>=50\t\t\t>50%\t\t\t>=75\t\t\t>=75%\t\t\t>=90\t\t\t>=90%"); 
			pw.println("G/BAR\tDHI\tDUB\tID\tDHI\tDUB\tID\tDHI\tDUB\tID\tDHI\tDUB\tID\tDHI\tDUB\tID\tDHI\tDUB\tID");
			
			for(int i = 0; i < stud_mark_list_report.size()-1; i++){
				pw.println(stud_mark_list_report.get(i).getSub_bean().getSub_name() + "\t" + stud_mark_list_report.get(i).getMale_num_cat1() + "\t" + stud_mark_list_report.get(i).getFemale_num_cat1() + "\t" + stud_mark_list_report.get(i).getTotal_num_cat1() + "\t" + 
																							stud_mark_list_report.get(i).getMale_percent_cat1() + "%" + "\t" + stud_mark_list_report.get(i).getFemale_percent_cat1() + "%" + "\t" + stud_mark_list_report.get(i).getTotal_percent_cat1() + "%" + "\t" + 
																							stud_mark_list_report.get(i).getMale_num_cat2() + "\t" + stud_mark_list_report.get(i).getFemale_num_cat2() + "\t" + stud_mark_list_report.get(i).getTotal_num_cat2() + "\t" + 
																							stud_mark_list_report.get(i).getMale_percent_cat2() + "%" + "\t" + stud_mark_list_report.get(i).getFemale_percent_cat2() + "%" + "\t" + stud_mark_list_report.get(i).getTotal_percent_cat2() + "%" + "\t" + 
																							stud_mark_list_report.get(i).getMale_num_cat3() + "\t" + stud_mark_list_report.get(i).getFemale_num_cat3() + "\t" + stud_mark_list_report.get(i).getTotal_num_cat3() + "\t" + 
																							stud_mark_list_report.get(i).getMale_percent_cat3() + "%" + "\t" + stud_mark_list_report.get(i).getFemale_percent_cat3() + "%" + "\t" + stud_mark_list_report.get(i).getTotal_percent_cat3() + "%");
			}
			
			for(int i = stud_mark_list_report.size()-1; i < stud_mark_list_report.size(); i++){
				pw.println(" " + "\t" + stud_mark_list_report.get(i).getGrt_50_total_male_cat1() + "\t" + stud_mark_list_report.get(i).getGrt_50_total_female_cat1() + "\t" + stud_mark_list_report.get(i).getGrt_50_total_cat1() + "\t" + 
						stud_mark_list_report.get(i).getGrt_50_total_percent_male_cat1() + "%" + "\t" + stud_mark_list_report.get(i).getGrt_50_total_percent_female_cat1() + "%" + "\t" + stud_mark_list_report.get(i).getGrt_50_total_percent_cat1() + "%" + "\t" + 
						stud_mark_list_report.get(i).getGrt_75_total_male_cat2() + "\t" + stud_mark_list_report.get(i).getGrt_75_total_female_cat2() + "\t" + stud_mark_list_report.get(i).getGrt_75_total_cat2() + "\t" + 
						stud_mark_list_report.get(i).getGrt_75_total_percent_male_cat2() + "%" + "\t" + stud_mark_list_report.get(i).getGrt_75_total_percent_female_cat2() + "%" + "\t" + stud_mark_list_report.get(i).getGrt_75_total_percent_cat2() + "%" + "\t" + 
						stud_mark_list_report.get(i).getGrt_90_total_male_cat3() + "\t" + stud_mark_list_report.get(i).getGrt_90_total_female_cat3() + "\t" + stud_mark_list_report.get(i).getGrt_90_total_cat3() + "\t" + 
						stud_mark_list_report.get(i).getGrt_90_total_percent_male_cat3() + "%" + "\t" + stud_mark_list_report.get(i).getGrt_90_total_percent_female_cat3() + "%" + "\t" + stud_mark_list_report.get(i).getGrt_90_total_percent_cat3() + "%");
			}
			
			
			pw.flush(); 
			pw.close();
			
			if(file.exists()){
				rslt = true;
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		
		return rslt;
	}
	
	

}
