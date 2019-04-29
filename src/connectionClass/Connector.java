package connectionClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.NamingException;

import util.SysConstant;

public class Connector {
	
	public static Connection connect() throws NamingException,SQLException{
		
		Connection conn = null;
		
		String url = "";
		String unicode = "";
		
		try{
			
			url = "jdbc:mysql://127.0.0.1:3309/"+SysConstant.DATABASE_NAME;
			unicode = "?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrival=true";
			Class.forName("com.mysql.cj.jdbc.Driver");	
			conn = DriverManager.getConnection(url+""+unicode, SysConstant.DATABASE_USERNAME, SysConstant.DATABASE_PASSWORD);
						
//			url = "jdbc:mysql://localhost:3309/"+SysConstant.DATABASE_NAME;
//			unicode = "?useUnicode=yes&characterEncoding=UTF-8&rewriteBatchedStatements=true";
//			Class.forName("com.mysql.jdbc.Driver");			
//			conn = DriverManager.getConnection(url+unicode, SysConstant.DATABASE_USERNAME, SysConstant.DATABASE_PASSWORD);
						
		} catch (SQLException se) {
						
			//se.printStackTrace();
		    System.out.println(se.getMessage());
		    
		 } catch (Exception e) {
			 
			System.out.println(e.getMessage());
		 }
		
		return conn;		
	}

}
