package gui;

import com.ubb.cms.Conference;
import com.ubb.cms.User;
import com.ubb.cms.utils.UserTag;
import exception.ServiceException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Raul on 22/05/2017.
 */
public class LoginView extends BaseView{
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button    logInButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void buttonHandler() {
        //System.out.println(username.getText());
        //System.out.println(password.getText());
        for (Conference conference : controller.getAllConferences()) {
            System.out.println(conference);
        }

        //System.out.println("trece de getConferences");
        try {
            User currentUser = controller.login(username.getText(), password.getText());
            UserTag tag = currentUser.getTag();
            System.out.println(tag);
            switch (tag) {

                case Admin:
                    switchToView("UserView.fxml", "userView.css", "Admin", currentUser);
                    break;
                case Reviewer:
                    break;
                case Author:
                    String title = "Author: " + currentUser.getUsername();
                    switchToView("author.fxml", "author.css", title, currentUser);
                    break;
                case SessionChair:
                    String chairTitle = "Session chair:" + currentUser.getUsername();
                    switchToView("create.fxml", "create.css", chairTitle, currentUser);
                    break;
                case Participant:
                    break;
            }
        } catch (ServiceException ex) {
            ShowAlert.showAlert(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void changeToSignUp() {
        try {
            switchToView("signup.fxml", "signup.css", "Sign up");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
