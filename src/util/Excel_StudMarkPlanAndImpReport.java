package util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import reportClass.ReportBean;

public class Excel_StudMarkPlanAndImpReport {
	
	public static boolean generateExcelFile(String fileName, List<ReportBean> stud_mark_plan_imp_list){
		boolean rslt = false;
		
		try{
			
			File myDir = new File(SysConstant.CERT_PDF_PATH);
			myDir.getParentFile().mkdir();
			
			File file = new File(myDir, fileName+".xls");
			
			FileWriter fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);
			
			pw.println("Baay'ina Barattootaa Barumsaaf Galmaa'anii\t\t\t"
					+ "Baay'ina Barattootaa Qorumsa Fundhatanii\t\t\t"
					+ "Gosa Barnootaa\t"
					+ "Barattoota Qoraman Keessaa 50% kan Ta'an Qabxii 50-74 Akka Galmeessisan Taasisuu\t\t\t\t\t\t\t\t\t"
					+ "Barattoota Qoraman Keessaa 35% Kan Ta'an Qabxii 75-89 Akka Galmeessisan Taasisuu\t\t\t\t\t\t\t\t\t"
					+ "Barattoota Qoraman Keessaa 35% Kan Ta'an Qabxii 90 fi isaa 01 Akka Galmeessisan Taasisuu\t\t\t\t\t\t\t\t\t"
					+ "Waliigala Barattoota Qoraman Keessaa Qabxii 50 fi Isaa 01 Fidan\t\t\t"
					+ "Waliigala Barattoota Qoraman Keessaa Qabxii 50 fi Isaa 01 Kan Galmeessisan % dhaan\t\t\t"); 
			
			pw.println("\t\t\t"
					+ "\t\t\t"
					+ "\t"
					+ "Karoora 50%\t\t\t"
					+ "Raawwii\t\t\t"
					+ "Raawwii %dhaan\t\t\t"
					+ "Karoora 35%\t\t\t"
					+ "Raawwii\t\t\t"
					+ "Raawwii %dhaan\t\t\t"
					+ "Karoora 15%\t\t\t"
					+ "Raawwii\t\t\t"
					+ "Raawwii %dhaan\t\t\t"
					+ "\t\t\t"
					+ "\t\t\t");
			
			pw.println("Dhira\tDubara\tIda'ama\t"
					+ "Dhira\tDubara\tIda'ama\t"
					+ "\t"
					+ "Dhira %\tDubara %\tIda'ama %\t"
					+ "Dhira\tDubara\tIda'ama\t"
					+ "Dhira\tDubara\tIda'ama\t"
					+ "Dhira %\tDubara %\tIda'ama %\t"
					+ "Dhira\tDubara\tIda'ama\t"
					+ "Dhira\tDubara\tIda'ama\t"
					+ "Dhira %\tDubara %\tIda'ama %\t"
					+ "Dhira\tDubara\tIda'ama\t"
					+ "Dhira\tDubara\tIda'ama\t"
					+ "Dhira\tDubara\tIda'ama\t"
					+ "Dhira\tDubara\tIda'ama\t");
			
			for(int i = 0; i < stud_mark_plan_imp_list.size(); i++){
				
				pw.println(stud_mark_plan_imp_list.get(i).getStud_bean().getNo_male() + "\t" + stud_mark_plan_imp_list.get(i).getStud_bean().getNo_female() + "\t" + stud_mark_plan_imp_list.get(i).getStud_bean().getTotal_number() + "\t"
						+ stud_mark_plan_imp_list.get(i).getStud_bean().getNo_male() + "\t" + stud_mark_plan_imp_list.get(i).getStud_bean().getNo_female() + "\t" + stud_mark_plan_imp_list.get(i).getStud_bean().getTotal_number() + "\t"
						+ stud_mark_plan_imp_list.get(i).getSub_bean().getSub_name() + "\t"
						+ stud_mark_plan_imp_list.get(i).getMale_stat_percent_cat1() + "%" + "\t" + stud_mark_plan_imp_list.get(i).getFemale_stat_percent_cat1() + "%" + "\t" + stud_mark_plan_imp_list.get(i).getTotal_stat_percent_cat1() + "%" + "\t"
						+ stud_mark_plan_imp_list.get(i).getMale_num_cat1() + "\t" + stud_mark_plan_imp_list.get(i).getFemale_num_cat1() + "\t" + stud_mark_plan_imp_list.get(i).getTotal_num_cat1() + "\t"
						+ stud_mark_plan_imp_list.get(i).getMale_percent_cat1() + "%" + "\t" + stud_mark_plan_imp_list.get(i).getFemale_percent_cat1() + "%" + "\t" + stud_mark_plan_imp_list.get(i).getTotal_percent_cat1() + "%" + "\t"
						+ stud_mark_plan_imp_list.get(i).getMale_stat_percent_cat2() + "%" + "\t" + stud_mark_plan_imp_list.get(i).getFemale_stat_percent_cat2() + "%" + "\t" + stud_mark_plan_imp_list.get(i).getTotal_stat_percent_cat2() + "%" + "\t"
						+ stud_mark_plan_imp_list.get(i).getMale_num_cat2() + "\t" + stud_mark_plan_imp_list.get(i).getFemale_num_cat2() + "\t" + stud_mark_plan_imp_list.get(i).getTotal_num_cat2() + "\t"
						+ stud_mark_plan_imp_list.get(i).getMale_percent_cat2() + "%" + "\t" + stud_mark_plan_imp_list.get(i).getFemale_percent_cat2() + "%" + "\t" + stud_mark_plan_imp_list.get(i).getTotal_percent_cat2() + "%" + "\t"
						+ stud_mark_plan_imp_list.get(i).getMale_stat_percent_cat3() + "%" + "\t" + stud_mark_plan_imp_list.get(i).getFemale_stat_percent_cat3() + "%" + "\t" + stud_mark_plan_imp_list.get(i).getTotal_stat_percent_cat3() + "%" + "\t"
						+ stud_mark_plan_imp_list.get(i).getMale_num_cat3() + "\t" + stud_mark_plan_imp_list.get(i).getFemale_num_cat3() + "\t" + stud_mark_plan_imp_list.get(i).getTotal_num_cat3() + "\t"
						+ stud_mark_plan_imp_list.get(i).getMale_percent_cat3() + "%" + "\t" + stud_mark_plan_imp_list.get(i).getFemale_percent_cat3() + "%" + "\t" + stud_mark_plan_imp_list.get(i).getTotal_percent_cat3() + "%" + "\t"
						+ stud_mark_plan_imp_list.get(i).getMale_grt_50() + "\t" + stud_mark_plan_imp_list.get(i).getFemale_grt_50()+ "\t" + stud_mark_plan_imp_list.get(i).getTotal_grt_50() + "\t"
						+ stud_mark_plan_imp_list.get(i).getMale_percent_grt_50() + "%" + "\t" + stud_mark_plan_imp_list.get(i).getFemale_percent_grt_50() + "%" + "\t" + stud_mark_plan_imp_list.get(i).getTotal_percent_grt_50() + "%" + "\t");

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
