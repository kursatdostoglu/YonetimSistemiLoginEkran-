
package yonetimsistemi;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import yonetimsistemi.BIN.Database;


public class YonetimSistemi extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Database database = new Database();
        database.Connection();
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        stage.setOnCloseRequest((event) -> {
            database.Disconnection();
            Platform.exit();
            System.exit(0);
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
