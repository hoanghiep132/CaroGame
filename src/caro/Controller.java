package caro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private ArrayList<Coordinate> list;


    @FXML
    private AnchorPane subAnchor;

    @FXML
    private GridPane gridPane;

    @FXML
    private TextField sizeField;

    private final int rows = 15;
    private final int columns = 15;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = new ArrayList<>();
        gridPane.getStyleClass().add("game-grid");
//        if(!"".equals(sizeField.getText())){
//            try{
//                rows = Integer.parseInt(sizeField.getText());
//                columns = Integer.parseInt(sizeField.getText());
//            }catch (NumberFormatException ex){
//
//            }
//        }
//        for (int i = 0; i < rows; i++) {
//            ColumnConstraints colConst = new ColumnConstraints();
//            colConst.setPercentWidth(100.0 / rows);
//            gridPane.getColumnConstraints().add(colConst);
//        }
//        for (int i = 0; i < columns; i++) {
//            RowConstraints rowConst = new RowConstraints();
//            rowConst.setPercentHeight(100.0 / columns);
//            gridPane.getRowConstraints().add(rowConst);
//        }

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                Pane pane = new Pane();
                pane.setPrefSize(900/columns,900/rows);
                pane.getStyleClass().add("game-grid-cell");
                pane.setBorder(new Border(new BorderStroke(Color.BLACK,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                gridPane.add(pane,i,j);
            }
        }


//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < columns; j++) {
//                Pane pane = new Pane();
//                int finalJ = j;
//                int finalI = i;
//                pane.setOnMousePressed(e -> {
//                    boolean check = true;
//                    if(list.size() > 0){
//                        for (Coordinate c:list) {
//                            if(c.getX() == finalI && c.getY() == finalJ){
//                                check = false;
//                            }
//                        }
//                    }
//                    if(check == true){
//                        if(list.size() % 2 == 0) {
//                            pane.getChildren().add(Anims.getAtoms1(1));
//                        }else {
//                            pane.getChildren().add(Anims.getAtoms2(1));
//                        }
//                        System.out.println(finalI + "\t" + finalJ);
//                        list.add(new Coordinate(finalI,finalJ));
//                    }
//                    if(list.size() > 10){
//                        if(victory()){
//                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                            alert.setHeaderText("Player " + ((list.size()%2==0)?"1":"2") + " win");
//                        }
//                    }
//                });
//                pane.setBorder(new Border(new BorderStroke(Color.BLACK,
//                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//                gridPane.add(pane, i, j);
//            }
//        }

    }

    @FXML
    public void markUp(MouseEvent event){

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Pane pane = new Pane();
                int finalJ = j;
                int finalI = i;
                pane.setOnMousePressed(e -> {
                    boolean check = true;
                    if(list.size() > 0){
                        for (Coordinate c:list) {
                            if(c.getX() == finalI && c.getY() == finalJ){
                                check = false;
                            }
                        }
                    }
                    if(check == true){
                        if(list.size() % 2 == 0) {
                            pane.getChildren().add(Anims.getAtoms1(1));
                        }else {
                            pane.getChildren().add(Anims.getAtoms2(1));
                        }
                        list.add(new Coordinate(finalI,finalJ));
                    }
                    if(list.size() >= 9){
                        if(victory()){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("CONGRATULATION!");
                            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                            stage.getIcons().add((list.size() % 2 == 0)?new Image("caro/img/blueCircle.png"):new Image("caro/img/redCircle.png"));
                            alert.setHeight(400);
                            alert.setWidth(600);
                            alert.setHeaderText(null);
                            alert.setContentText("Player " + ((list.size()%2==0)?"2":"1") + " is winner");
                            alert.showAndWait();
//                            for(int m = 0; m < 10; m++){
//                                for (int n = 0; n < 10; n++){
//                                    Pane pane1 = new Pane();
//                                    pane1.setBackground(new Background(new BackgroundFill(Color.web("WHITE"), CornerRadii.EMPTY, Insets.EMPTY)));
//                                    pane1.setBorder(new Border(new BorderStroke(Color.BLACK,
//                                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//                                    gridPane.add(pane1, m, n);
//                                }
//                            }
//                            list.removeAll(list);
                        }
                    }
                });
                pane.setBorder(new Border(new BorderStroke(Color.BLACK,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                gridPane.add(pane, i, j);
            }
        }
    }


    private boolean victory(){
        ArrayList<Coordinate> arrayVertical = new ArrayList<>();
        ArrayList<Coordinate> arrayHorizontal = new ArrayList<>();
        ArrayList<Coordinate> arrayDiagonalRight = new ArrayList<>();
        ArrayList<Coordinate> arrayDiagonalLeft = new ArrayList<>();
        int size = list.size() - 1;
        Coordinate origin = list.get(size);
        for(int i = size - 2; i >= 0; i -= 2){
            int x = list.get(i).getX();
            int y = list.get(i).getY();
            if(x == origin.getX() && Math.abs(y - origin.getY()) == 1){
                arrayVertical.add(new Coordinate(x,y));
            }
            if(y == origin.getY() && Math.abs(x - origin.getX()) == 1){
                arrayHorizontal.add(new Coordinate(x,y));
            }
            if( ((x - origin.getX()) == (y - origin.getY())) && ( Math.abs(x - origin.getX()) == 1)){
                arrayDiagonalLeft.add(new Coordinate(x,y));
            }
            if( (x - origin.getX()) == -(y - origin.getY()) && ( Math.abs(x - origin.getX()) == 1)){
                arrayDiagonalRight.add(new Coordinate(x,y));
            }
        }

        if(!arrayVertical.isEmpty()){
            if(arrayVertical.size() == 1){
                for(int i = 0; i < 3; i++){
                    int sub = arrayVertical.size();
                    for(int j = size - 2; j >= 0; j -= 2 ){
                        int x = list.get(j).getX();
                        int y = list.get(j).getY();
                        if(x == origin.getX() &&  (((arrayVertical.get(arrayVertical.size() -1 )).getY() - y) == (origin.getY() - arrayVertical.get(0).getY()))){
                            arrayVertical.add(new Coordinate(x,y));
                            break;
                        }
                    }
                    if(sub == arrayVertical.size()){
                        break;
                    }
                }
                if(arrayVertical.size() == 4){
                    return true;
                }
            }else{
                for(int i = 0; i < 2; i++){
                    int sub = arrayHorizontal.size();
                    for(int j = size - 2; j >= 0; j -= 2 ){
                        int x = list.get(j).getX();
                        int y = list.get(j).getY();
                        if(x == origin.getX() && ((Math.abs(arrayVertical.get(0).getY() - y) == 1 &&  x != arrayVertical.get((arrayVertical.size() > 2)?2:1).getY())
                                || (Math.abs(arrayVertical.get(1).getY() - y) == 1 &&  x != arrayVertical.get((arrayVertical.size() > 2)?2:0).getY())
                                || ((Math.abs(arrayVertical.get(arrayVertical.size() - 1).getY() - y) == 1)
                                    && (y != arrayVertical.get(0).getY() && y != arrayVertical.get(1).getY() )))){
                            arrayVertical.add(new Coordinate(x,y));
                            break;
                        }
                    }
                    if(sub == arrayVertical.size()){
                        break;
                    }
                }
                if(arrayVertical.size() == 4){
                    return true;
                }
            }
        }


        if(!arrayHorizontal.isEmpty()){
            if(arrayHorizontal.size() == 1){
                for(int i = 0; i < 3; i++){
                    int sub = arrayHorizontal.size();
                    for(int j = size - 2; j >= 0; j -= 2 ){
                        int x = list.get(j).getX();
                        int y = list.get(j).getY();
                        if(y == origin.getY() &&  (((arrayHorizontal.get(arrayHorizontal.size() - 1)).getX() - x) == (origin.getX() - arrayHorizontal.get(0).getX()))){
                            arrayHorizontal.add(new Coordinate(x,y));
                            break;
                        }
                    }
                    if(sub == arrayHorizontal.size()){
                        break;
                    }
                }
                if(arrayHorizontal.size() == 4){
                    return true;
                }
            }else{
                for(int i = 0; i < 2; i++){
                    int sub = arrayHorizontal.size();
                    for(int j = size - 2; j >= 0; j -= 2 ){
                        int x = list.get(j).getX();
                        int y = list.get(j).getY();
                        if((y == origin.getY()) && ((Math.abs(arrayHorizontal.get(0).getX() - x) == 1 &&  x != arrayHorizontal.get((arrayHorizontal.size() > 2)?2:1).getX() )
                                || (Math.abs(arrayHorizontal.get(1).getX() - x) == 1 &&  x != arrayHorizontal.get((arrayHorizontal.size() > 2)?2:0).getX())
                                || ((Math.abs(arrayHorizontal.get(arrayHorizontal.size() - 1).getX() - x) == 1)
                                    && x != arrayHorizontal.get(0).getX() && x != arrayHorizontal.get(1).getX() ))) {
                            arrayHorizontal.add(new Coordinate(x,y));
                            break;
                        }
                    }
                    if(sub == arrayHorizontal.size()){
                        break;
                    }
                }
                if(arrayHorizontal.size() == 4){
                    return true;
                }
            }
        }


        if(!arrayDiagonalLeft.isEmpty()){
            if(arrayDiagonalLeft.size() == 1){
                for(int i = 0; i < 3; i++){
                    int sub = arrayDiagonalLeft.size();
                    for(int j = size - 2; j >= 0; j -= 2 ){
                        int x = list.get(j).getX();
                        int y = list.get(j).getY();
                        if(((arrayDiagonalLeft.get(arrayDiagonalLeft.size() - 1).getX() - x) == (arrayDiagonalLeft.get(arrayDiagonalLeft.size() - 1).getY() - y))
                                && ((arrayDiagonalLeft.get(arrayDiagonalLeft.size() - 1).getX() - x) == origin.getX() - arrayDiagonalLeft.get(0).getX())){
                            arrayDiagonalLeft.add(new Coordinate(x, y));
                            break;
                        }
                    }
                    if(sub == arrayDiagonalLeft.size()){
                        break;
                    }
                }
                if(arrayDiagonalLeft.size() == 4){
                    return true;
                }
            }else{
                for(int i = 0; i < 2; i++){
                    int sub = arrayDiagonalLeft.size();
                    for(int j = size - 2; j >= 0; j -= 2 ){
                        int x = list.get(j).getX();
                        int y = list.get(j).getY();
                        if(((arrayDiagonalLeft.get(0).getX() - x) == (arrayDiagonalLeft.get(0).getY() - y) && (Math.abs(arrayDiagonalLeft.get(0).getX() - x) == 1))
                                || (((arrayDiagonalLeft.get(1).getX() - x) == (arrayDiagonalLeft.get(1).getY() - y)) && (Math.abs(arrayDiagonalLeft.get(1).getX() - x) == 1))
                                || ((arrayDiagonalLeft.get(arrayDiagonalLeft.size() - 1).getX() - x) == (arrayDiagonalLeft.get(arrayDiagonalLeft.size() - 1).getY() - y)
                                && ( (Math.abs(arrayDiagonalLeft.get(arrayDiagonalLeft.size() - 1).getX() - x) == 1))
                                && (x != arrayDiagonalLeft.get(0).getX() && x != arrayDiagonalLeft.get(1).getX())) ){
                            if(arrayDiagonalLeft.size() > 2){
                                if(x != arrayDiagonalLeft.get(0).getX() && x != arrayDiagonalLeft.get(1).getX()
                                    && x != arrayDiagonalLeft.get(2).getX() ){
                                    arrayDiagonalLeft.add(new Coordinate(x,y));
                                    break;
                                }
                            }else {
                                arrayDiagonalLeft.add(new Coordinate(x,y));
                                break;
                            }
                        }
                    }
                    if(sub == arrayDiagonalLeft.size()){
                        break;
                    }
                }
                if(arrayDiagonalLeft.size() == 4){
                    return true;
                }
            }
        }


        if(!arrayDiagonalRight.isEmpty()){
            if(arrayDiagonalRight.size() == 1){
                int sub = arrayDiagonalRight.size();
                for(int i = 0; i < 3; i++){
                    for(int j = size - 2; j >= 0; j -= 2 ){
                        int x = list.get(j).getX();
                        int y = list.get(j).getY();
                        if(((arrayDiagonalRight.get(arrayDiagonalRight.size() - 1).getX() - x) == -(arrayDiagonalRight.get(arrayDiagonalRight.size() - 1).getY() - y))
                            && ((arrayDiagonalRight.get(arrayDiagonalRight.size()-1).getX() - x) == (origin.getX() - arrayDiagonalRight.get(0).getX()))){
                            arrayDiagonalRight.add(new Coordinate(x,y));
                            break;
                        }
                    }
                    if(sub == arrayDiagonalRight.size()){
                        break;
                    }
                }
                if(arrayDiagonalRight.size() == 4){
                    return true;
                }
            }else{
                for(int i = 0; i < 2; i++){
                    int sub = arrayDiagonalRight.size();
                    for(int j = size - 2; j >= 0; j -= 2 ){
                        int x = list.get(j).getX();
                        int y = list.get(j).getY();
                        if(((arrayDiagonalRight.get(0).getX() - x) == -(arrayDiagonalRight.get(0).getY() - y) && (Math.abs(arrayDiagonalRight.get(0).getX() - x) == 1))
                                || (((arrayDiagonalRight.get(1).getX() - x) == -(arrayDiagonalRight.get(1).getY() - y)) && (Math.abs(arrayDiagonalRight.get(1).getX() - x) == 1))
                                || ((arrayDiagonalRight.get(arrayDiagonalRight.size() - 1).getX() - x) == -(arrayDiagonalRight.get(arrayDiagonalRight.size() - 1).getY() - y)
                                        &&( (Math.abs(arrayDiagonalRight.get(arrayDiagonalRight.size() - 1).getX() - x) == 1))) ){
                            if(arrayDiagonalRight.size() > 2){
                                if(x != arrayDiagonalRight.get(0).getX() && x != arrayDiagonalRight.get(1).getX()
                                    && x != arrayDiagonalRight.get(2).getX()){
                                    arrayDiagonalRight.add(new Coordinate(x,y));
                                    break;
                                }
                            }else{
                                arrayDiagonalRight.add(new Coordinate(x,y));
                                break;
                            }
                        }
                    }
                    if(sub == arrayDiagonalRight.size()){
                        break;
                    }
                }
                if(arrayDiagonalRight.size() == 4){
                   return true;
                }
            }
        }

        return false;
    }


    public void reset(ActionEvent event){
        for(int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                Pane pane = new Pane();
                pane.setBackground(new Background(new BackgroundFill(Color.web("WHITE"), CornerRadii.EMPTY, Insets.EMPTY)));
                pane.setBorder(new Border(new BorderStroke(Color.BLACK,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                gridPane.add(pane, i, j);
            }
        }
        list.removeAll(list);
    }

    public void undo(ActionEvent event){
        int x = list.get(list.size()-1).getX();
        int y = list.get(list.size()-1).getY();
        for(int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if(i == x && j == y){
                    Pane pane = new Pane();
                    pane.setBackground(new Background(new BackgroundFill(Color.web("WHITE"), CornerRadii.EMPTY, Insets.EMPTY)));
                    pane.setBorder(new Border(new BorderStroke(Color.BLACK,
                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                    gridPane.add(pane, i, j);
                }
            }
        }
        list.remove(list.size()-1);
    }



}
