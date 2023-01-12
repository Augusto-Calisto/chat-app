package br.com.chat.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import br.com.chat.dao.HistoricoDao;

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
                Conversa conversa = (Conversa) objectInputStream.readUnshared();

                if(conversa.hasDestinatario()) {
                    encaminharMensagem(conversa);
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
}