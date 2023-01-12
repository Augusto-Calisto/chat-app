package br.com.chat.model;

import java.util.List;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Badge {
    public static void addMensagemEnviada(VBox vBox, String mensagem) {
        HBox hBox = new HBox();
        
        hBox.setAlignment(Pos.CENTER_RIGHT);
        
        hBox.setPadding(new Insets(5, 5, 5, 10));

        Text texto = new Text(mensagem);
        
        TextFlow textFlow = new TextFlow(texto);
        
        textFlow.setStyle("-fx-color: rgb(239, 242, 255); " + "-fx-background-color: rgb(15, 125, 242); " + "-fx-background-radius: 20px;");

        textFlow.setPadding(new Insets(5, 5, 5, 10));
        
        texto.setFill(Color.color(0.934, 0.945, 0.996));

        hBox.getChildren().add(textFlow);

        Platform.runLater(() -> {
        	vBox.getChildren().add(hBox);
        });
    }

    public static void addMensagemRecebida(VBox vBox, String mensagem) {
        HBox hBox = new HBox();
        
        hBox.setAlignment(Pos.CENTER_LEFT);
        
        hBox.setPadding(new Insets(5, 5, 5, 10));
        
        Text texto = new Text(mensagem);
        
        TextFlow textFlow = new TextFlow(texto);
        
        textFlow.setStyle("-fx-color: rgb(233, 233, 235); " + "-fx-background-color: gray; " + "-fx-background-radius: 20px;");

        textFlow.setPadding(new Insets(5, 5, 5, 10));
        
        texto.setFill(Color.color(0.934, 0.945, 0.996));

        hBox.getChildren().add(textFlow);

        Platform.runLater(() -> {
        	vBox.getChildren().add(hBox);
        });
    }

    public static void montarHistoricoConversa(VBox vBox, int idUsuarioRemetente, List<Conversa> conversas) {
        for(Conversa conversa : conversas) {
            if(idUsuarioRemetente == conversa.getIdUsuarioRemetente()) {
                addMensagemEnviada(vBox, conversa.getTexto());
            } else {
                addMensagemRecebida(vBox, conversa.getTexto());
            }
        }
    }
}