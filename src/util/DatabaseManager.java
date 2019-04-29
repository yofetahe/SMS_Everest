package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 * This class <code>DatabaseManager</code> facilitates services related to database
 * like fetching a database connection, closing result set, statements, connections etc.
 * 
 * @author Chetan Ahirrao
 * */
public final class DatabaseManager {
	

	
	/**
	 * This method fetches a connection using DataSource object and returns the same.
	 * 
	 * @return Connection
	 * @see Connection
	 * */	
	public static Connection getConnection() throws NamingException,SQLException {
		
		Connection connection = null;		
		
		Context initContext = new InitialContext();
		
		Context envContext  = (Context)initContext.lookup("java:/comp/env");	//Lookup java context 
		
		DataSource ds = (DataSource)envContext.lookup("jdbc/sms");			//Lookup oracledb context
		
		connection = ds.getConnection();										//Fetch connection from datasource
		
		return connection;
	}

	

	/**
	 * This method closes result set, preparedstatement and connection.
	 * It also handles the SQL Exceptions if any arises while closing result set or preparedstatement or connection.
	 * 
	 * @param	resultSet	- Result set
	 * @param	preparedStatement	-	praparedstatement
	 * @param	connection	-	connection.
	 * 
	 * @see ResultSet
	 * @see PreparedStatement
	 * @see Connection
	 * */
	public static void close(ResultSet resultSet,PreparedStatement preparedStatement,Connection connection) {
		
		try{			
			
			if(resultSet!=null && resultSet.isClosed()!=true){
				resultSet.close();
			}
		} catch(SQLException e){
			
		}
		
		try{			
			
			if(preparedStatement!=null && preparedStatement.isClosed()!=true){
				preparedStatement.close();

			}
		} catch(SQLException e){
			
		}
		
		try{			
			if(connection!=null) {
				try {
					connection.setAutoCommit(true);
				}
				catch(SQLException e){					
					
				}
			}
			
			if(connection!=null && connection.isClosed()!=true){				
				connection.close();				
			}
		} catch(SQLException e){
			
		}
		
	}

}