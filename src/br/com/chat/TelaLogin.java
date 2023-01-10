package br.com.chat;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class TelaLogin extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@SuppressWarnings("exports")
	@Override
	public void start(Stage primaryStage) {
		try {			
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/template/login.fxml"));

			Scene scene = new Scene(fxmlLoader.load());
			
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			
			primaryStage.setTitle("CHAT APP");
			
			primaryStage.getIcons().addAll(new Image(getClass().getResource("/image/icone_app.png").toString()));
						
			primaryStage.setScene(scene);
						
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}