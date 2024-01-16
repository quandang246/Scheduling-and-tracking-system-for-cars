/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stsc;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author ACER
 */
public class dashboardController implements Initializable {

    @FXML
    private AnchorPane topForm;

    @FXML
    private Button addMovies_btn;

    @FXML
    private Button addMovies_clearBtn;

    @FXML
    private TableColumn<moviesData, String> addMovies_col_date;

    @FXML
    private TableColumn<moviesData, String> addMovies_col_duration;

    @FXML
    private TableColumn<moviesData, String> addMovies_col_genre;

    @FXML
    private TableColumn<moviesData, String> addMovies_col_movieTitle;

    @FXML
    private DatePicker addMovies_date;

    @FXML
    private Button addMovies_deleteBtn;

    @FXML
    private TextField addMovies_duration;

    @FXML
    private AnchorPane addMovies_form;

    @FXML
    private TextField addMovies_genre;

    @FXML
    private ImageView addMovies_imageView;

    @FXML
    private Button addMovies_import;

    @FXML
    private Button addMovies_insertBtn;

    @FXML
    private TextField addMovies_movieTitle;

    @FXML
    private TextField addMovies_search;

    @FXML
    private TableView<moviesData> addMovies_tableView;

    @FXML
    private Button addMovies_updateBtn;

    @FXML
    private TableView<?> addScreening_tableView;

    @FXML
    private Button availableMovies_btn;

    @FXML
    private Button availableMovies_buyBtn;

    @FXML
    private Button availableMovies_clearBtn;

    @FXML
    private TableColumn<?, ?> availableMovies_col_genre;

    @FXML
    private TableColumn<?, ?> availableMovies_col_movieTitle;

    @FXML
    private TableColumn<?, ?> availableMovies_col_showingDate;

    @FXML
    private Label availableMovies_date;

    @FXML
    private AnchorPane availableMovies_form;

    @FXML
    private Label availableMovies_genre;

    @FXML
    private ImageView availableMovies_imageView;

    @FXML
    private Label availableMovies_movieTitle;

    @FXML
    private Label availableMovies_normalClass_price;

    @FXML
    private Spinner<?> availableMovies_normalClass_quantity;

    @FXML
    private Button availableMovies_receiptBtn;

    @FXML
    private Button availableMovies_selectMovie;

    @FXML
    private Label availableMovies_specialClass_price;

    @FXML
    private Spinner<?> availableMovies_specialClass_quantity;

    @FXML
    private TableView<?> availableMovies_tableView;

    @FXML
    private Label availableMovies_title;

    @FXML
    private Label availableMovies_total;

    @FXML
    private Button close;

    @FXML
    private Button customers_btn;

    @FXML
    private Button customers_clearBtn;

    @FXML
    private TableColumn<?, ?> customers_col_date;

    @FXML
    private TableColumn<?, ?> customers_col_genre;

    @FXML
    private TableColumn<?, ?> customers_col_movieTitle;

    @FXML
    private TableColumn<?, ?> customers_col_ticketNumber;

    @FXML
    private TableColumn<?, ?> customers_col_time;

    @FXML
    private Label customers_date;

    @FXML
    private Button customers_deleteBtn;

    @FXML
    private AnchorPane customers_form;

    @FXML
    private Label customers_genre;

    @FXML
    private Label customers_movieTitle;

    @FXML
    private TextField customers_search;

    @FXML
    private Label customers_ticketNumber;

    @FXML
    private Label customers_time;

    @FXML
    private Label dashboard_availableMovies;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_totalEarnToday;

    @FXML
    private Label dashboard_totalSoldTicket;

    @FXML
    private Button editScreening_btn;

    @FXML
    private TableColumn<?, ?> editScreening_col_current;

    @FXML
    private TableColumn<?, ?> editScreening_col_duration;

    @FXML
    private TableColumn<?, ?> editScreening_col_genre;

    @FXML
    private TableColumn<?, ?> editScreening_col_movieTitle;

    @FXML
    private ComboBox<?> editScreening_current;

    @FXML
    private Button editScreening_delete;

    @FXML
    private AnchorPane editScreening_form;

    @FXML
    private ImageView editScreening_imageView;

    @FXML
    private TextField editScreening_search;

    @FXML
    private Label editScreening_title;

    @FXML
    private Button editScreening_update;

    @FXML
    private Button minimize;

    @FXML
    private Button signout;

    @FXML
    private Label username;

    private Image image;

    private double x = 0;
    private double y = 0;

    /*Database tools*/
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    /*On action cho nut import*/
    public void importImage() {

        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new ExtensionFilter("Image File", "*png", "*jpg"));

        Stage stage = (Stage) addMovies_form.getScene().getWindow();
        File file = open.showOpenDialog(stage);

        if (file != null) {

            image = new Image(file.toURI().toString(), 144, 177, false, true);

            addMovies_imageView.setImage(image);
            /*dong nay de get the image path*/
            getData.path = file.getAbsolutePath();
        }

    }

    public void insertAddMovies() {

        String sql1 = "SELECT * FROM movie WHERE movieTitle = '"
                + addMovies_movieTitle.getText() + "'";

        connect = database.connectDb();

        Alert alert;

        try {

            statement = connect.createStatement();
            result = statement.executeQuery(sql1);

            if (result.next()) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText(addMovies_movieTitle.getText() + " was already exist!");
                alert.showAndWait();

            } else {

                if (addMovies_movieTitle.getText().isEmpty()
                        || addMovies_genre.getText().isEmpty()
                        || addMovies_duration.getText().isEmpty()
                        || addMovies_imageView.getImage() == null
                        || addMovies_date.getValue() == null) {

                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all blank fields!");
                    alert.showAndWait();

                } else {

                    String sql
                            = "INSERT INTO movie (movieTitle,genre,duration,image,date) VALUES (?,?,?,?,?)";

                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addMovies_movieTitle.getText());
                    prepare.setString(2, addMovies_genre.getText());
                    prepare.setString(3, addMovies_duration.getText());
                    prepare.setString(4, uri);
                    prepare.setString(5, String.valueOf(addMovies_date.getValue()));

                    prepare.execute();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully add new movie!");
                    alert.showAndWait();

                    clearAddMoviesList();
                    showAddMoviesList();
                }

            }
            /* neu insert title giong voi title da ton tai tren database*/

        } catch (Exception e) {e.printStackTrace();}
    }
    
    public void updateAddMovies(){
        
        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");
        
        String sql = "UPDATE movie SET movieTitle = '" + addMovies_movieTitle.getText() 
                + "', genre = '" + addMovies_genre.getText() 
                + "', duration = '" + addMovies_duration.getText()
                + "', image = '" + uri + "', date = " + addMovies_date.getValue() + "'";
        
        connect = database.connectDb();
        
        try{
            
            statement = connect.createStatement();
            
            Alert alert;
            
            if(addMovies_movieTitle.getText().isEmpty() 
                    || addMovies_genre.getText().isEmpty()
                    || addMovies_duration.getText().isEmpty()
                    || addMovies_imageView.getImage() == null
                    || addMovies_date.getValue() == null){
                
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the movie first");
                alert.showAndWait();
                
            }else{
                
                statement.execute(sql);
                
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the movie first");
                alert.showAndWait();
            }
            
        }catch(Exception e){e.printStackTrace();}
    }

    /*ham nay gan cho nut clear trong onaction*/
    public void clearAddMoviesList() {

        addMovies_movieTitle.setText("");
        addMovies_genre.setText("");
        addMovies_imageView.setImage(null);
        addMovies_date.setValue(null);

    }

    /*create moviesData va bang movies tren database roi thuc hien cac function*/
    public ObservableList<moviesData> addMoviesList() {

        ObservableList<moviesData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM movie";

        connect = database.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            moviesData movD;

            while (result.next()) {
                movD = new moviesData(result.getString("movieTitle"),
                        result.getString("genre"), result.getString("duration"),
                        result.getString("image"), result.getDate("date"));

                listData.add(movD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<moviesData> listAddMovies;

    public void showAddMoviesList() {
        listAddMovies = addMoviesList();

        addMovies_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        addMovies_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        addMovies_col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        addMovies_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        addMovies_tableView.setItems(listAddMovies);

    }

    /*trong table view gan onmouseclicked thanh selectAddMoviesList de khi an vao thi no ra hinh minh nhap tren database*/
    public void selectAddMoviesList() {

        moviesData movD = addMovies_tableView.getSelectionModel().getSelectedItem();
        int num = addMovies_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addMovies_movieTitle.setText(movD.getTitle());
        addMovies_genre.setText(movD.getGenre());
        addMovies_duration.setText(movD.getDuration());

        String getDate = String.valueOf(movD.getDate());

        String uri = "file:" + movD.getImage();

        image = new Image(uri, 144, 177, false, true);
        /*kich thuoc cua imageView de hinh no hien thi ra dung kich thuoc imageView*/
        addMovies_imageView.setImage(image);

    }

    /*cho nut signout*/
    public void logout() {

        signout.getScene().getWindow().hide();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            root.setOnMousePressed((MouseEvent event) -> {

                x = event.getSceneX();
                y = event.getSceneY();

            });

            root.setOnMouseDragged((MouseEvent event) -> {

                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);

            });

            stage.initStyle(StageStyle.TRANSPARENT);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*cho phep khi nhan 5 nut btn thi no hien ra tung trang day*/
    public void switchForm(ActionEvent event) {

        if (event.getSource() == dashboard_btn) {

            dashboard_form.setVisible(true);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customers_form.setVisible(false);

            dashboard_btn.setStyle("-fx-background-color:#c5273f");
            addMovies_btn.setStyle("-fx-background-color:transparent");
            availableMovies_btn.setStyle("-fx-background-color:transparent");
            editScreening_btn.setStyle("-fx-background-color:transparent");
            customers_btn.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == addMovies_btn) {

            dashboard_form.setVisible(false);
            addMovies_form.setVisible(true);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customers_form.setVisible(false);

            addMovies_btn.setStyle("-fx-background-color:#c5273f");
            dashboard_btn.setStyle("-fx-background-color:transparent");
            availableMovies_btn.setStyle("-fx-background-color:transparent");
            editScreening_btn.setStyle("-fx-background-color:transparent");
            customers_btn.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == availableMovies_btn) {

            dashboard_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(true);
            editScreening_form.setVisible(false);
            customers_form.setVisible(false);

            availableMovies_btn.setStyle("-fx-background-color:#c5273f");
            dashboard_btn.setStyle("-fx-background-color:transparent");
            addMovies_btn.setStyle("-fx-background-color:transparent");
            editScreening_btn.setStyle("-fx-background-color:transparent");
            customers_btn.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == editScreening_btn) {

            dashboard_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(true);
            customers_form.setVisible(false);

            editScreening_btn.setStyle("-fx-background-color:#c5273f");
            dashboard_btn.setStyle("-fx-background-color:transparent");
            addMovies_btn.setStyle("-fx-background-color:transparent");
            availableMovies_btn.setStyle("-fx-background-color:transparent");
            customers_btn.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == customers_btn) {

            dashboard_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customers_form.setVisible(true);

            customers_btn.setStyle("-fx-background-color:#c5273f");
            dashboard_btn.setStyle("-fx-background-color:transparent");
            addMovies_btn.setStyle("-fx-background-color:transparent");
            availableMovies_btn.setStyle("-fx-background-color:transparent");
            editScreening_btn.setStyle("-fx-background-color:transparent");

        }
    }

    /*hien thi username tren dashboard*/
    public void displayUsername() {

        username.setText(getData.username);

    }

    public void close() {

        System.exit(0);

    }

    public void minimize() {

        Stage stage = (Stage) topForm.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        displayUsername();
        /*phai co ham showAddMoviesList nay, neu khong thi du lieu tren database se khong hien thi tren tableView*/
        showAddMoviesList();

    }

}
