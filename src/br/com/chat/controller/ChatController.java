package br.com.chat.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.chat.entity.Usuario;
import javafx.application.Platform;
import javafx.fxml.Initializable;

public class ChatController implements Initializable {
	private Usuario usuarioAutenticado;
	
	@Override
	public void initialize(URL url, ResourceBundle resource) {
		Platform.runLater(() -> {
			System.out.println(usuarioAutenticado);
		});
	}
	
	@SuppressWarnings("exports")
	public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
	}
}