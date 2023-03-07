package sample.modal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database;
import sample.Students;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class New implements Initializable {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtDepartment;

    @FXML
    private TextField txtYear;

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtNo;

    @FXML
    public void addNewStudent(ActionEvent actionEvent){
        Database.addStudent(new Students(txtNo.getText(),txtName.getText(),txtDepartment.getText(),txtYear.getText(),txtMail.getText()));
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
