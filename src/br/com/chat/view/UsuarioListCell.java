package br.com.chat.view;

import br.com.chat.entity.Usuario;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class UsuarioListCell extends ListCell<Usuario> {
	public UsuarioListCell() {
		super();
	}
	
	@Override
    protected void updateItem(Usuario usuario, boolean empty) {
        super.updateItem(usuario, empty);
        
        if(usuario != null && !empty) {        	            
            Image image = new Image(usuario.getFoto().getBytes());
            
            ImageView imageView = new ImageView(image);
            
            imageView.setFitWidth(30);
            
    		imageView.setFitHeight(30);
    		    		
        	HBox hBox = new HBox(imageView, new Text(usuario.getNome()));
        	
        	hBox.setSpacing(10.0);
        	
        	hBox.setAlignment(Pos.CENTER_LEFT);
        	        	
        	super.setGraphic(hBox);
        	
        } else {
            super.setGraphic(null);
            
            super.setText("");
        }
        
        super.setGraphicTextGap(5.0);
        
        super.setAlignment(Pos.CENTER);
        
		super.setContentDisplay(ContentDisplay.TOP);
		
		super.setPrefWidth(200);
    }
}
