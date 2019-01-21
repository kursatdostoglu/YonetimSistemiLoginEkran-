
package yonetimsistemi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import yonetimsistemi.Login.Login;


public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane Main;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Login login = new Login();
        Main.getChildren().add(login);
    }
    
}
