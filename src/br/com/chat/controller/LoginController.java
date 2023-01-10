package br.com.chat.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @SuppressWarnings("exports")
	@FXML
    public void logar(ActionEvent event) {
       System.out.println(event);
    }

    @SuppressWarnings("exports")
	@FXML
    public void redirecionarParaTelaCadastrar(ActionEvent event) {
        
    }
}
