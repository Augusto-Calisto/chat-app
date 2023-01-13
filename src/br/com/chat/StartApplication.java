package br.com.chat;
	
import br.com.chat.model.Server;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class StartApplication extends Application {
	
	public static void main(String[] args) {
		Thread threadServidor = new Thread(Server::rodar);

        threadServidor.start();
        
		launch(args);
		
		// TODO Tirar instancia do DB de estatica para de classe
	}
	
	@SuppressWarnings("exports")
	@Override
	public void start(Stage primaryStage) {
		 Platform.runLater(() -> {
			 criarTela();
			 criarTela();
	     });
	}
	
	private void criarTela() {
		try {			
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/template/login.fxml"));

			Scene scene = new Scene(fxmlLoader.load());
			
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			
			Stage primaryStage = new Stage();
			
			primaryStage.setTitle("CHAT APP");
			
			primaryStage.getIcons().addAll(new Image(getClass().getResource("/image/icone_app.png").toString()));
						
			primaryStage.setScene(scene);
			
			primaryStage.setResizable(false);
									
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}