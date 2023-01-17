package br.com.chat.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Conexao {
	private static Logger logger = LogManager.getLogger(Conexao.class);
	
	private static final String URL = "jdbc:mysql://localhost:3306/chat_app?serverTimezone=UTC";
	private static final String USUARIO = "root";
	private static final String SENHA = "1234";
		
	private Connection connection;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
                        
            logger.info("Conexao estabelecida com o Banco de Dados");

        } catch(ClassNotFoundException | SQLException connectionError) {
        	logger.error(connectionError.getMessage());
        }

        return connection;
    }
}