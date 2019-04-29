package util;

public class ConvertNumberToWords {
	
//	public static void main(String[] args){
//		convertedNumberToWord("999567.55");
//	}
	
	public static String convertedNumberToWord(String value){
		
		String rslt = "";
		
		int dot = value.indexOf(".");
		
		String birr = value.substring(0, dot);
		String cent = value.substring(dot + 1);
		String birr_value = birr;
		int tenth_digit = 0;
		
		for(int i = 0; i < birr.length(); i++){			
			
			String digit_name = "";		
			
			if(birr_value.length() == 4 || birr_value.length() == 5){
				digit_name = " thousand";
			} else if(birr_value.length() == 3 || birr_value.length() == 6){
				digit_name = " hundred";
			}
			
			String digit = birr_value.substring(0, 1);
			
			if(birr_value.length() == 6){
				
				switch (Integer.parseInt(digit)) {
				case 1:
					rslt = rslt + " One" + digit_name;
					break;
				case 2:
					rslt = rslt + " Two" + digit_name;
					break;
				case 3:
					rslt = rslt + " Three" + digit_name;
					break;
				case 4:
					rslt = rslt + " Four" + digit_name;
					break;
				case 5:
					rslt = rslt + " Five" + digit_name;
					break;
				case 6:
					rslt = rslt + " Six" + digit_name;
					break;
				case 7:
					rslt = rslt + " Seven" + digit_name;
					break;
				case 8:
					rslt = rslt + " Eight" + digit_name;
					break;
				case 9:
					rslt = rslt + " Nine" + digit_name;
					break;
				default:
					rslt = rslt + digit_name;
					break;
				}
				
			} else if(birr_value.length() == 5){
				
				String num = birr_value.charAt(0) + "" + birr_value.charAt(2);				
				
				if(Integer.parseInt(num) >= 20){
					
					switch (Integer.parseInt(digit)) {
					case 1:
						//rslt = rslt + " One " + digit_name;
						break;
					case 2:
						rslt = rslt + " Twenty";
						break;
					case 3:
						rslt = rslt + " Thirty";
						break;
					case 4:
						rslt = rslt + " Forty";
						break;
					case 5:
						rslt = rslt + " Fifty";
						break;
					case 6:
						rslt = rslt + " Sixty";
						break;
					case 7:
						rslt = rslt + " Seventy";
						break;
					case 8:
						rslt = rslt + " Eighty";
						break;
					case 9:
						rslt = rslt + " Ninety";
						break;
					default:
						break;
					}
					
				} else {
					
					String next_num = birr_value.charAt(1)+ "" + birr_value.charAt(2);
					
					switch (Integer.parseInt(next_num)) {
					case 1:
						rslt = rslt + " Eleven" + digit_name;
						break;
					case 2:
						rslt = rslt + " Twelve" + digit_name;
						break;
					case 3:
						rslt = rslt + " Thirteen" + digit_name;
						break;
					case 4:
						rslt = rslt + " Forteen" + digit_name;
						break;
					case 5:
						rslt = rslt + " Fifteen" + digit_name;
						break;
					case 6:
						rslt = rslt + " Sixteen" + digit_name;
						break;
					case 7:
						rslt = rslt + " Seventeen" + digit_name;
						break;
					case 8:
						rslt = rslt + " Eighteen" + digit_name;
						break;
					case 9:
						rslt = rslt + " Nineteen" + digit_name;
						break;
					default:
						break;
					}
				}
				
			} else if(birr_value.length() == 4){
				
				switch (Integer.parseInt(digit)) {
				case 1:
					rslt = rslt + " One" + digit_name;
					break;
				case 2:
					rslt = rslt + " Two" + digit_name;
					break;
				case 3:
					rslt = rslt + " Three" + digit_name;
					break;
				case 4:
					rslt = rslt + " Four" + digit_name;
					break;
				case 5:
					rslt = rslt + " Five" + digit_name;
					break;
				case 6:
					rslt = rslt + " Six" + digit_name;
					break;
				case 7:
					rslt = rslt + " Seven" + digit_name;
					break;
				case 8:
					rslt = rslt + " Eight" + digit_name;
					break;
				case 9:
					rslt = rslt + " Nine" + digit_name;
					break;
				default:
					rslt = rslt + digit_name;
					break;
				}
				
			} else if(birr_value.length() == 3){				
				
				switch (Integer.parseInt(digit)) {
				case 1:
					rslt = rslt + " One" + digit_name;
					break;
				case 2:
					rslt = rslt + " Two" + digit_name;
					break;
				case 3:
					rslt = rslt + " Three" + digit_name;
					break;
				case 4:
					rslt = rslt + " Four" + digit_name;
					break;
				case 5:
					rslt = rslt + " Five" + digit_name;
					break;
				case 6:
					rslt = rslt + " Six" + digit_name;
					break;
				case 7:
					rslt = rslt + " Seven" + digit_name;
					break;
				case 8:
					rslt = rslt + " Eight" + digit_name;
					break;
				case 9:
					rslt = rslt + " Nine" + digit_name;
					break;
				}
				
			} else if(birr_value.length() == 2 && Integer.parseInt(birr_value) >= 20){
				
				switch (Integer.parseInt(digit)) {
				case 1:
					//rslt = rslt + " One " + digit_name;
					break;
				case 2:
					rslt = rslt + " Twenty";
					break;
				case 3:
					rslt = rslt + " Thirty";
					break;
				case 4:
					rslt = rslt + " Forty";
					break;
				case 5:
					rslt = rslt + " Fifty";
					break;
				case 6:
					rslt = rslt + " Sixty";
					break;
				case 7:
					rslt = rslt + " Seventy";
					break;
				case 8:
					rslt = rslt + " Eighty";
					break;
				case 9:
					rslt = rslt + " Ninety";
					break;
				default:
					break;
				}
				
			} else if(birr_value.length() == 2 && Integer.parseInt(birr_value) < 20){
				
				tenth_digit = 1;
				String last_digit = birr_value.substring(1, birr_value.length());
				
				switch (Integer.parseInt(last_digit)) {
				case 1:
					rslt = rslt + " Eleven";
					break;
				case 2:
					rslt = rslt + " Twelve";
					break;
				case 3:
					rslt = rslt + " Thirteen";
					break;
				case 4:
					rslt = rslt + " Forteen";
					break;
				case 5:
					rslt = rslt + " Fifteen";
					break;
				case 6:
					rslt = rslt + " Sixteen";
					break;
				case 7:
					rslt = rslt + " Seventeen";
					break;
				case 8:
					rslt = rslt + " Eighteen";
					break;
				case 9:
					rslt = rslt + " Nineteen";
					break;
				default:
					break;
				}
				
			} else if(birr_value.length() == 1 && tenth_digit == 0) {
				
				switch (Integer.parseInt(digit)) {
				case 1:
					rslt = rslt + " One";
					break;
				case 2:
					rslt = rslt + " Two";
					break;
				case 3:
					rslt = rslt + " Three";
					break;
				case 4:
					rslt = rslt + " Four";
					break;
				case 5:
					rslt = rslt + " Five";
					break;
				case 6:
					rslt = rslt + " Six";
					break;
				case 7:
					rslt = rslt + " Seven";
					break;
				case 8:
					rslt = rslt + " Eight";
					break;
				case 9:
					rslt = rslt + " Nine";
					break;
				default:
					break;
				}
				
			}
			
			birr_value = birr_value.substring(1, birr_value.length());
		}
		
		rslt = rslt + " Birr ";
		
		String cent_init = cent.substring(0, 1);
		
		if(cent.length() == 2 && Integer.parseInt(cent_init) == 1){
			
			String cent_last_digit = cent.substring(1, cent.length());
			
			switch (Integer.parseInt(cent_last_digit)) {
			case 1:
				rslt = rslt + "and Eleven cent";
				break;
			case 2:
				rslt = rslt + "and Twelve cent";
				break;
			case 3:
				rslt = rslt + "and Thirteen cent";
				break;
			case 4:
				rslt = rslt + "and Forteen cent";
				break;
			case 5:
				rslt = rslt + "and Fifteen cent";
				break;
			case 6:
				rslt = rslt + "and Sixteen cent";
				break;
			case 7:
				rslt = rslt + "and Seventeen cent";
				break;
			case 8:
				rslt = rslt + "and Eighteen cent";
				break;
			case 9:
				rslt = rslt + "and Nineteen cent";
				break;
			default:
				break;
			}			
			
		} else if(cent.length() == 2 && Integer.parseInt(cent_init) > 1){
			
			switch (Integer.parseInt(cent_init)) {
			case 1:
				//rslt = rslt + " One " + digit_name;
				break;
			case 2:
				rslt = rslt + "and Twenty";
				break;
			case 3:
				rslt = rslt + "and Thirty";
				break;
			case 4:
				rslt = rslt + "and Forty";
				break;
			case 5:
				rslt = rslt + "and Fifty";
				break;
			case 6:
				rslt = rslt + "and Sixty";
				break;
			case 7:
				rslt = rslt + "and Seventy";
				break;
			case 8:
				rslt = rslt + "and Eighty";
				break;
			case 9:
				rslt = rslt + "and Ninety";
				break;
			default:
				break;
			}
			
			
			String cent_last_digit = cent.substring(1, cent.length());
			
			switch (Integer.parseInt(cent_last_digit)) {
			case 1:
				rslt = rslt + " One cent";
				break;
			case 2:
				rslt = rslt + " Two cent";
				break;
			case 3:
				rslt = rslt + " Three cent";
				break;
			case 4:
				rslt = rslt + " Four cent";
				break;
			case 5:
				rslt = rslt + " Five cent";
				break;
			case 6:
				rslt = rslt + " Six cent";
				break;
			case 7:
				rslt = rslt + " Seven cent";
				break;
			case 8:
				rslt = rslt + " Eight cent";
				break;
			case 9:
				rslt = rslt + " Nine cent";
				break;
			default:
				break;
			}	
			
		} else if(cent.length() == 1) {
			
			switch (Integer.parseInt(cent_init)) {
			case 1:
				rslt = rslt + "and Ten cent";
				break;
			case 2:
				rslt = rslt + "and Twenty cent";
				break;
			case 3:
				rslt = rslt + "and Thirty cent";
				break;
			case 4:
				rslt = rslt + "and Forty cent";
				break;
			case 5:
				rslt = rslt + "and Fifty cent";
				break;
			case 6:
				rslt = rslt + "and Sixty cent";
				break;
			case 7:
				rslt = rslt + "and Seventy cent";
				break;
			case 8:
				rslt = rslt + "and Eighty cent";
				break;
			case 9:
				rslt = rslt + "and Ninety cent";
				break;
			default:
				break;
			}
			
		}
		
		rslt = rslt + " only";
		
		return rslt;
	}

}
