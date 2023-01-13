package br.com.chat.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

import br.com.chat.dao.HistoricoDao;
import br.com.chat.entity.Usuario;

public class ServerThread {
    private HistoricoDao historicoDao;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public ServerThread(Socket socket) throws IOException {        
        historicoDao = new HistoricoDao();
        
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        
        new Thread(this::receberMensagem).start();
    }

    public void receberMensagem() {
        while(true) {
            try {
            	Object objeto = objectInputStream.readUnshared();
            	
            	if(objeto instanceof Conversa) {
            		Conversa conversa = (Conversa) objeto;
            		
            		if(conversa.hasDestinatario()) {
            			encaminharMensagem(conversa);
                    }
            	} else {
                    armazenarClienteConectado(objeto);
            	}
            } catch(IOException | ClassNotFoundException erro) {
                erro.getStackTrace();
            }
        }
    }
    
    public void encaminharMensagem(Conversa conversa) throws IOException {
        ServerThread cliente = Server.getClientesConectados().get(conversa.getIdUsuarioDestinatario());

        if(cliente != null) {
            historicoDao.salvarConversa(conversa);
            cliente.objectOutputStream.writeUnshared(conversa);
        }
    }

    private void armazenarClienteConectado(Object objeto) {
		Map<Integer, ServerThread> clientesConectados = Server.getClientesConectados();
		
		Usuario usuario = (Usuario) objeto;
		
		if(!clientesConectados.containsKey(usuario.getId())) {
            clientesConectados.put(usuario.getId(), this);
            System.out.println(clientesConectados);
        }
	}
}