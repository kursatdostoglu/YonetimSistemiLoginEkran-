
package yonetimsistemi.Login;

import Pojo.Adminler;
import com.gluonhq.charm.glisten.animation.BounceInUpTransition;
import com.gluonhq.charm.glisten.animation.BounceOutDownTransition;
import com.gluonhq.charm.glisten.control.Avatar;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import yonetimsistemi.BIN.AnchorPaneEdit;
import yonetimsistemi.BIN.DatabaseManager;
import yonetimsistemi.BIN.endUser;


public class Login extends AnchorPane {

    @FXML
    private HBox UserList;

    @FXML
    private Label UserName;
    
    @FXML
    private Avatar UserImage;
    
    @FXML
    private PasswordField password;
    
    @FXML
    private Label error;
    
    private Adminler selectUser;
    
    public Login() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        AnchorPaneEdit.fullAnchor(this);
        DatabaseManager<Pojo.Adminler> calisanlar = new DatabaseManager(Pojo.Adminler.class);
        calisanlar.gets().forEach((t) -> {
            Users users = new Users();
            users.setData(t);
            users.setOnMouseClicked((event) -> {
                selectUser(users.getData());
            });
            users.getUserImage().setOnMouseClicked((event) -> {
                selectUser(users.getData());
            });
            UserList.getChildren().add(users);
        });
        
        password.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (controls()) {
                    try {
                        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File("Data/endUser.nst")));
                        endUser user = new endUser();
                        user.setEndUser(selectUser);
                        outputStream.writeObject(user);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.setVisible(false);
                }else{
                    error.setVisible(true);
                    error.setText("Şifre Hatalı..");
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            error.setVisible(false);
                        }
                    };
                    
                    Timer timer = new Timer();
                    timer.schedule(task, 2000);
                }
            }
        });
        
        if (Files.exists(Paths.get("Data/endUser.nst"))) {
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File("Data/endUser.nst")));
                endUser user = (endUser) inputStream.readObject();
                selectUser(user.getEndUser());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    @FXML
    private void initialize() {

    }

    private boolean controls(){
        return password.getText().equals(selectUser.getPassword());
    }
    
    @FXML
    void SelectUser(MouseEvent event) {
        if (!UserList.isVisible()) {
            UserList.setVisible(true);
            BounceInUpTransition bidt = new BounceInUpTransition(UserList);
            bidt.setAutoReverse(true);
            bidt.play();
            
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    BounceOutDownTransition bidt = new BounceOutDownTransition(UserList);
                    bidt.setAutoReverse(true);
                    bidt.setOnFinished((events) -> {
                        UserList.setVisible(false);
                    });
                    bidt.play();
                }
            };
            Timer timer = new Timer();
            timer.schedule(task, 5000);
            
        } else {
            BounceOutDownTransition bidt = new BounceOutDownTransition(UserList);
            bidt.setAutoReverse(true);
            bidt.setOnFinished((events) -> {
                UserList.setVisible(false);
            });
            bidt.play();
        }
    }
    
    
    public void selectUser(Adminler selectUser){
        this.selectUser = selectUser;
        UserName.setText(selectUser.getCalisanlar().toString());
        UserImage.setImage(new Image(new ByteArrayInputStream(selectUser.getCalisanlar().getImage())));
    }
}
