package br.com.chat.model;

import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;
import java.net.UnknownHostException;

import br.com.chat.entity.Usuario;
import br.com.chat.view.Badge;

public class Client {
    private Socket socket;
    private Conversa conversa;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private VBox vBox;

    public Client(Conversa conversa, VBox vBox) {
        this.conversa = conversa;
        this.vBox = vBox;
    }

    public void conectarAoServidor(Usuario usuario) throws UnknownHostException, IOException {
	    socket = new Socket(Server.HOST, Server.PORTA);
	
	    objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
	
	    objectInputStream = new ObjectInputStream(socket.getInputStream());
	    
	    objectOutputStream.writeUnshared(usuario); // <---->
	
	    new Thread(this::receberMensagem).start();
	
	    new Thread(this::enviarMensagem).start();
    }

    public void enviarMensagem() {
        try {
            objectOutputStream.writeUnshared(conversa);
        } catch(IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    public void receberMensagem() {
        try {
            while(true) {
                Conversa conversa = (Conversa) objectInputStream.readUnshared();
                Badge.addMensagemRecebida(vBox, conversa.getTexto());
            }
        } catch(IOException | ClassNotFoundException ioException) {
            System.out.println("Erro");
        }
    }
}