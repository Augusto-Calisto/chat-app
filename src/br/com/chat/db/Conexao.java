package br.com.chat.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
	private static final Logger LOGGER = Logger.getLogger(Conexao.class.getName());
	
	private static final String URL = "jdbc:mysql://localhost:3306/chat_app?serverTimezone=UTC";
	private static final String USUARIO = "root";
	private static final String SENHA = "1234";
	
	private static Connection connection;
	  
    static {
        try {
        	LOGGER.addHandler(new FileHandler("connection_db.log", true));
        	
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
            
        } catch(ClassNotFoundException | SQLException | SecurityException | IOException error) {
        	LOGGER.log(Level.SEVERE, error.getMessage());
        }
    }
    
    public static Connection getConnection() {
        return connection;
    }
}
