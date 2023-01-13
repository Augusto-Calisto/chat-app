package br.com.chat.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    public static final String HOST = "127.0.0.1";
    public static final int PORTA = 8773;
    private static Map<Integer, ServerThread> clientesConectados;
        
    @SuppressWarnings("resource")
	public static void rodar() {
        try {
        	ServerSocket serverSocket = new ServerSocket(PORTA);
        	
        	clientesConectados = new HashMap<>();
        	
            System.out.println("Server rodando na porta: " + PORTA);
            
            int id = 0;

            while(!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept(); // Esperando a requisicao de algum cliente

                // Este codigo e executado depois que um cliente e conectado
                //UsuarioSingleton usuarioSingleton = UsuarioSingleton.getUsuarioSingleton();

                //Usuario usuario = usuarioSingleton.getUsuario();

                //int id = usuario.getId();
                
                if(!clientesConectados.containsKey(id)) {
                    ServerThread serverThread = new ServerThread(socket);

                    clientesConectados.put(id, serverThread);

                    System.out.println(getClientesConectados());
                    
                    id++;
                }
            }
        } catch(IOException ioException) {
            ioException.getStackTrace();
        }
    }

    public static Map<Integer, ServerThread> getClientesConectados() {
        return clientesConectados;
    }
}