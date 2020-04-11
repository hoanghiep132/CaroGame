package paint;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {



    @FXML
    private ColorPicker colorPicker;

    @FXML
    private ComboBox sizeBrush;

    @FXML
    private Canvas canvas;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private AnchorPane canvasPane;


    private MenuItem newItem;
    private MenuItem openItem;
    private MenuItem saveItem;
    private MenuItem exitItem;

    GraphicsContext brushTool;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sizeBrush.setItems(FXCollections.observableArrayList(10,15,20,30,50));
        sizeBrush.getSelectionModel().selectFirst();

        colorPicker.getCustomColors();

        brushTool = canvas.getGraphicsContext2D();
        canvas.setOnMouseDragged(e -> {
            double size = Integer.parseInt(sizeBrush.getValue().toString());
            double x = e.getX() - size/2;
            double y = e.getY() - size/2;

            brushTool.setFill(colorPicker.getValue());
            brushTool.fillRoundRect(x,y,size,size,size,size);
        });
    }



    public void openFile(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) mainPane.getScene().getWindow();
        //Desktop desktop = Desktop.getDesktop();
        FileChooser.ExtensionFilter fileFilter = new FileChooser.ExtensionFilter("Painting Files", "*.java");
        fileChooser.getExtensionFilters().add(fileFilter);
        File file = fileChooser.showOpenDialog(stage);
        fileChooser.setTitle("Open file");
        if(file != null){

        }

    }

    public void showSaveConfirmation(){
        Stage stage = new Stage();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Save Confirmation Alert");
        alert.setHeaderText("Do you want save file?");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(yesButton,noButton,cancelButton);
        Optional<ButtonType> option = alert.showAndWait();

        if(option.get() == yesButton){
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save file");
            fileChooser.showOpenDialog(stage);
        }else if (option.get() == noButton){
            //
        }else{

        }

    }

    @FXML
    public void newCanvas(ActionEvent event){
        brushTool.clearRect(0,0,1200,920);
    }

    public void exit(ActionEvent event){

    }
}
