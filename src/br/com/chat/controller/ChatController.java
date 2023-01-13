package br.com.chat.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import br.com.chat.dao.HistoricoDao;
import br.com.chat.dao.UsuarioDao;
import br.com.chat.entity.Usuario;
import br.com.chat.model.Badge;
import br.com.chat.model.Client;
import br.com.chat.model.Conversa;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChatController implements Initializable {
	private UsuarioDao usuarioDao;

    private HistoricoDao historicoDao;

    private Client client;

    private Conversa conversa;
    
    private Usuario usuarioAutenticado;

    @FXML
    private TextArea mensagem;

    @FXML
    private TextField txtPesquisarContato;

    @FXML
    private VBox trocaDeMensagens;

    @FXML
    private ImageView fotoDaPessoaSelecionada;

    @FXML
    private Label lblNomeDoUsuarioAutenticado;

    @FXML
    private Label nomeDaPessoaSelecionada;

    @FXML
    private ImageView fotoDePerfil;

    @FXML
    private ImageView banner;

    @FXML
    private ScrollPane spConversa;

    @FXML
    private AnchorPane painelDaConversa;

    @FXML
    private ListView<Usuario> listChatWindow;

    public ChatController() {
        usuarioDao = new UsuarioDao();
        
        historicoDao = new HistoricoDao();
        
        conversa = new Conversa();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	Platform.runLater(() -> {
    		try {
    			trocaDeMensagens.heightProperty().addListener((observable, oldValue, newValue) -> {
    	        	spConversa.setVvalue(newValue.doubleValue());
    	        });
    			
    			lblNomeDoUsuarioAutenticado.setText(usuarioAutenticado.getUsername());
		
	            conversa.setIdUsuarioRemetente(usuarioAutenticado.getId());
	            
	            buscarAmigosUsuarioAutenticado();
		
	            client = new Client(conversa, trocaDeMensagens);
	
	            client.conectarAoServidor(usuarioAutenticado);
	
	            carregarFotoDoUsuarioAutenticado(usuarioAutenticado.getId());
	            
    		} catch(IOException e) {
    			e.printStackTrace();
    	    }
    	});
    }

    private void buscarAmigosUsuarioAutenticado() {
    	List<Usuario> listaDeAmigos = usuarioDao.buscarTodosAmigos(usuarioAutenticado.getId());
    	
        listChatWindow.setItems(FXCollections.observableArrayList(listaDeAmigos));

        listChatWindow.setOnMouseClicked(event -> {
            Usuario usuarioSelecionado = listChatWindow.getSelectionModel().getSelectedItem();

            conversa.setIdUsuarioDestinatario(usuarioSelecionado.getId());

            banner.setVisible(false);

            List<Conversa> conversas = historicoDao.getHistoricoConversas(usuarioAutenticado.getId(), usuarioSelecionado.getId());

            trocaDeMensagens.getChildren().setAll(new HBox());
            
            if(!conversas.isEmpty()) {
                Badge.montarHistoricoConversa(trocaDeMensagens, usuarioAutenticado.getId(), conversas);
            }

            painelDaConversa.setVisible(true);

            ByteArrayInputStream fotoDoUsuarioSelecionado = usuarioSelecionado.getFoto().getBytes();

            fotoDaPessoaSelecionada.setImage(new Image(fotoDoUsuarioSelecionado));

            nomeDaPessoaSelecionada.setText(usuarioSelecionado.getNome());
        });
	}

    @SuppressWarnings("exports")
	@FXML
    public void enviarMensagem(ActionEvent event) {
        String texto = mensagem.getText();

        if(!texto.isEmpty()) {
            Badge.addMensagemEnviada(trocaDeMensagens, texto);

            conversa.setTexto(texto);

            client.enviarMensagem();

            mensagem.clear();
        }
    }

    private void carregarFotoDoUsuarioAutenticado(int id) {
        ByteArrayInputStream bytesFoto = usuarioAutenticado.getFoto().getBytes();
        fotoDePerfil.setImage(new Image(bytesFoto));
    }

    @SuppressWarnings("exports")
	@FXML
    public void pesquisarContato(ActionEvent event) {
        System.out.println("Contato pesquisado: " + txtPesquisarContato.getText());
    }

    @SuppressWarnings("exports")
	@FXML
    public void sairDoAplicativo(ActionEvent event) {
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    	
        alert.setTitle("CHAT APP");
        
        alert.setHeaderText("Deseja sair da aplicação?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK) {
            Platform.exit();
        } else {
            alert.hide();
        }
    }
	
	@SuppressWarnings("exports")
	public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
	}
}