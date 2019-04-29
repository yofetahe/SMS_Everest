package util;

public class ReturnMonthNameInAmharic {
	
	public static String getEthiopianMonthName(int index){
		String rslt = "";
		
		switch (index) {
		case 1:
			rslt = "MESKEREM";
			break;
		case 2:
			rslt = "TIKEMT";
			break;
		case 3:
			rslt = "HIDAR";
			break;
		case 4:
			rslt = "TAHSAS";
			break;
		case 5:
			rslt = "TIR";
			break;
		case 6:
			rslt = "YEKATIT";
			break;
		case 7:
			rslt = "MEGABIT";
			break;
		case 8:
			rslt = "MIYAZIA";
			break;
		case 9:
			rslt = "GINBOT";
			break;
		case 10:
			rslt = "SENE";
			break;
		case 11:
			rslt = "HAMLE";
			break;
		case 12:
			rslt = "NEHASE";
			break;
		case 13:
			rslt = "PUAGUME";
			break;
		
		default:
			break;
		}
		
		
		return rslt;
	}

}
