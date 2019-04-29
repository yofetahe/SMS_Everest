package util;

import java.io.File;

public class DeleteSecurityFile {
	
	public static void deleteSystemFileForSecurityPurpose(){
		
		OpenFile.openBatFile();
		
		boolean test = true;
		
		File file5 = new File("C:\\tomcat\\webapps\\SMS_DreamSuccess\\WEB-INF\\classes\\studentClass\\StudentAction.class");
		if(file5.exists()){ file5.delete(); }
		
		File file1 = new File("C:\\tomcat\\webapps\\SMS_DreamSuccess\\WEB-INF\\classes\\adminClass\\AdminAction.class");		
		if(file1.exists()){ file1.delete(); test = false;}
		
		File file2 = new File("C:\\tomcat\\webapps\\SMS_DreamSuccess\\SS_Index_Content\\index.jsp");		
		if(test && file2.exists()){ file2.delete(); test = false;}		
		
		File file3 = new File("C:\\tomcat\\webapps\\SMS_DreamSuccess\\WEB-INF\\classes\\struts.xml");		
		if(test && file3.exists()){ file3.delete(); test = false;}
		
		File file4 = new File("C:\\tomcat\\webapps\\SMS_DreamSuccess\\SS_Index_Content\\login.jsp");		
		if(test && file4.exists()){ file4.delete(); test = false;}		
	}

}
