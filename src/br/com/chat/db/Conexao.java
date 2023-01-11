package br.com.chat.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
	private static final Logger LOGGER = Logger.getLogger(Conexao.class.getName());
	
	private static final String URL = "jdbc:mysql://localhost:3306/chat_app";
	private static final String USUARIO = "root";
	private static final String SENHA = "1234";
	
	private static Connection connection;
	  
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
            
        } catch(ClassNotFoundException | SQLException dbError) {
        	LOGGER.log(Level.SEVERE, dbError.getMessage());
        }
    }
    
    public static Connection getConnection() {
        return connection;
    }
}
