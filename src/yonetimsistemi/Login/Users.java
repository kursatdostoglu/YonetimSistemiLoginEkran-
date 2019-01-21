
package yonetimsistemi.Login;

import Pojo.Adminler;
import com.gluonhq.charm.glisten.control.Avatar;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import yonetimsistemi.BIN.ICard;



public class Users<T extends Adminler> extends VBox implements ICard<T>{
    
    @FXML
    private Avatar UserImage;

    @FXML
    private Label UserName;
    
    private T t;
    
    public Users() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Users.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    @FXML
    private void initialize() {
        
    }

    @Override
    public T getData() {
       return t;
    }

    @Override
    public void setData(T t) {
        this.t = t;
        UserName.setText(t.getCalisanlar().getAd());
        UserImage.setImage(new Image(new ByteArrayInputStream(t.getCalisanlar().getImage())));
    }

    public Avatar getUserImage() {
        return UserImage;
    }
    
}
