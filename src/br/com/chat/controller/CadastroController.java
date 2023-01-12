package br.com.chat.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import br.com.chat.dao.UsuarioDao;
import br.com.chat.entity.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class CadastroController {
	private Usuario usuario;

	private UsuarioDao usuarioDao;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtUsername;

    @FXML
    private Label lblNomeDoArquivoAnexado;

    public CadastroController() {
        usuarioDao = new UsuarioDao();
        usuario = new Usuario();
    }

    @SuppressWarnings("exports")
	@FXML
    public void anexarFotoDePerfil(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione sua imagem de perfil");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpeg", "*.jpg"));

        File fotoDePerfil = fileChooser.showOpenDialog(null);

        if(fotoDePerfil != null) {
            lblNomeDoArquivoAnexado.setText(fotoDePerfil.getName());

            lblNomeDoArquivoAnexado.setVisible(true);

            byte[] bytesDaFoto = Files.readAllBytes(fotoDePerfil.toPath());

            String base64 = Base64.getEncoder().encodeToString(bytesDaFoto);

            usuario.getFoto().setImagem(base64);
        }
    }

    @SuppressWarnings("exports")
	@FXML
    public void cadastrarUsuario(ActionEvent event) {
        usuario.setNome(txtNome.getText());
        usuario.setUsername(txtUsername.getText().toLowerCase());
        usuario.setEmail(txtEmail.getText());
        usuario.setSenha(txtSenha.getText());

        int linhasAfetadas = usuarioDao.cadastrar(usuario);
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
    	alert.setTitle("CHAT APP");

        if(linhasAfetadas == 1) {
        	alert.setAlertType(Alert.AlertType.INFORMATION);
        	alert.setHeaderText("Cadastro realizado com sucesso");
        } else {
        	alert.setHeaderText("Erro ao cadastrar");
        }

        alert.show();
    }
}