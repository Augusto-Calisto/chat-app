package br.com.chat.controller;

import java.util.Optional;

import br.com.chat.dao.UsuarioDao;
import br.com.chat.entity.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	private UsuarioDao usuarioDao;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;
    
    public LoginController() {
    	usuarioDao = new UsuarioDao();
    }

    @SuppressWarnings("exports")
	@FXML
    public void logar(ActionEvent event) {
    	String username = txtUsername.getText();
        String senha = txtPassword.getText();

        Optional<Usuario> optional = usuarioDao.autenticar(username, senha);
        
        if(optional.isPresent()) {
        	Alert alertSucesso = new Alert(Alert.AlertType.INFORMATION);
        	
        	alertSucesso.setTitle("CHAT APP");

        	alertSucesso.setHeaderText("Autenticado com Sucesso");

        	alertSucesso.show();
        } else {
        	Alert alertErro = new Alert(Alert.AlertType.ERROR);
        	
        	alertErro.setTitle("CHAT APP");

        	alertErro.setHeaderText("Login ou senha invalido(s)");
        	        	
        	alertErro.show();
        }
    }

    @SuppressWarnings("exports")
	@FXML
    public void redirecionarParaTelaCadastrar(ActionEvent event) {
        
    }
}
