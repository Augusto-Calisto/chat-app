module chat_app {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires java.sql;
	requires fontawesomefx;
    requires jfoenix;
	requires log4j.api;
    
    exports br.com.chat;
    exports br.com.chat.controller;
    
    opens br.com.chat.controller to javafx.fxml;
	opens br.com.chat to javafx.graphics, javafx.fxml;
}
