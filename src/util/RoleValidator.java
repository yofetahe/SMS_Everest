package util;

import java.util.List;

import adminClass.AdminBean;

public class RoleValidator {
	
	public static boolean validateRoleList(List<AdminBean> usrRoleList, String page){
		
		boolean rslt = false;
		
		for(int i = 0; i < usrRoleList.size(); i++){
			
			if(page.equalsIgnoreCase(usrRoleList.get(i).getM_name())){
				rslt = true;
			}
		}
		
		return rslt;
	}

}
