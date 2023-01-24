package br.com.chat.controller;

import java.io.IOException;
import java.util.Optional;

import br.com.chat.dao.UsuarioDao;
import br.com.chat.entity.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    public void logar(ActionEvent event) throws IOException {
    	String username = txtUsername.getText();
        String senha = txtPassword.getText();

        Optional<Usuario> optional = usuarioDao.autenticar(username, senha);
        
        if(optional.isPresent()) {
        	Node node = (Node) event.getSource();
        	 
            Stage stage = (Stage) node.getScene().getWindow();
                          
            stage.close();
        	
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/template/chat.fxml"));
            
            Scene scene = new Scene(fxmlLoader.load());
            
            ChatController chatController = fxmlLoader.getController();
            
            chatController.setUsuarioAutenticado(optional.get());

            Stage tela = new Stage();

            tela.setTitle("CHAT APP");
            
            tela.getIcons().addAll(new Image(getClass().getResource("/image/icone_app.png").toString()));

            tela.setResizable(false);

            tela.setScene(scene);
            
            tela.show();
        	
        } else {
        	Alert alertErro = new Alert(Alert.AlertType.ERROR);
        	
        	alertErro.setTitle("CHAT APP");

        	alertErro.setHeaderText("Login ou senha invalido(s)");
        	        	
        	alertErro.show();
        }
    }

    @SuppressWarnings("exports")
	@FXML
    public void redirecionarParaTelaCadastrar(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/template/cadastrar.fxml"));

         Scene scene = new Scene(fxmlLoader.load());

         Stage tela = new Stage();

         tela.setTitle("CHATAPP");

         tela.getIcons().addAll(new Image(getClass().getResource("/image/icone_app.png").toString()));

         tela.setResizable(false);
         
         tela.initModality(Modality.APPLICATION_MODAL);

         tela.setScene(scene);

         tela.show();
    }
}
