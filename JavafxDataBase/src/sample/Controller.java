package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import javax.security.auth.RefreshFailedException;
import javax.security.auth.Refreshable;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller implements Initializable, Refreshable {
    @FXML
    private TableView<Students> table;

    @FXML
    private TableColumn<Students, String> no;

    @FXML
    private TableColumn<Students, String> nameSurname;

    @FXML
    private TableColumn<Students, String> department;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    private int currentId = -1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        no.setCellValueFactory(new PropertyValueFactory<Students,String>("no"));
        nameSurname.setCellValueFactory(new PropertyValueFactory<Students,String>("nameSurname"));
        department.setCellValueFactory(new PropertyValueFactory<Students,String>("department"));


        Callback<TableColumn<Students, String>, TableCell<Students, String>> cellFactory
                = //
                new Callback<TableColumn<Students, String>, TableCell<Students, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Students, String> param) {
                        final TableCell<Students, String> cell = new TableCell<Students, String>() {

                            final Button btn = new Button("1");
                            final Button btn2 = new Button("2");


                            @Override
                            public void updateItem(String item, boolean empty) {
                                final Group gr = new Group();

                                gr.getChildren().add(btn);
                                gr.getChildren().add(btn2);
                                gr.minWidth(100);
                                gr.setAutoSizeChildren(true);
                                gr.prefWidth(100);



                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {

                                    });
                                    btn2.setOnAction(event -> {
                                        Students students = getTableView().getItems().get(getIndex());
                                        System.out.println(students.getNameSurname()
                                                + "   " + students.getDepartment());
                                    });
                                    setGraphic(gr);
                                    setText(getTableView().getItems().get(getIndex()).getNo());
                                }
                            }
                        };
                        return cell;
                    }
                };
        no.setCellFactory(cellFactory);


        table.getItems().setAll(Database.init());
    }

    @FXML
    public void addStudent(ActionEvent actionEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modal/new.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateTable(ActionEvent actionEvent){
        try {
            table.getItems().setAll(Database.getStudents());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        table.refresh();
    }

    @FXML
    public void clickItem(MouseEvent event)
    {
        if (event.getClickCount() == 1) //Checking double click
        {
            currentId= Integer.parseInt(table.getSelectionModel().getSelectedItem().getNo());
        }
    }

    @Override
    public boolean isCurrent() {
        return false;
    }

    @Override
    public void refresh() throws RefreshFailedException {
        table.refresh();
    }

    @FXML
    public void deleteStudent(ActionEvent event){
        if(currentId!=-1){
            Database.deleteStudent(currentId);
            try {
                table.getItems().setAll(Database.getStudents());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


    }
}
