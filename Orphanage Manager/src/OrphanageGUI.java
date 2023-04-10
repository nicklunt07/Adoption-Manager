import java.awt.Color;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.paint.*;


public class OrphanageGUI extends Application {

    public static void main(String[] args) {
        Application.launch();
    }

    public void start(final Stage mainStage) {
        VBox root = new VBox();
        setUpControls(root);
        Scene scene = new Scene(root, 700, 500);
        setStage(mainStage, scene);
    }

    private void setUpControls(VBox mainPane) {
        Font font = Font.font("Times New Roman", FontWeight.BOLD, 20);
        
      
        HBox top = new HBox(30);
        HBox bottom = new HBox();

        top.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        bottom.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
     

        top.setFillHeight(true);
        bottom.setFillHeight(true);

        HBox titleBox = new HBox();
        Label title = new Label("Adoption Selection");
        title.setFont(font);
        title.setAlignment(Pos.CENTER);
        titleBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleBox.getChildren().add(title);
        titleBox.setAlignment(Pos.CENTER);
        title.setStyle("-fx-background-color: #8B0000;");
        title.setTextFill(Paint.valueOf("#FFD700"));

        VBox radioButton = new VBox();
        Label label = new Label("Choose your Gender");
        RadioButton male = new RadioButton("Male"); 
        RadioButton female = new RadioButton("Female"); 
        ToggleGroup gender = new ToggleGroup();
        male.setToggleGroup(gender);
        female.setToggleGroup(gender);


        radioButton.getChildren().addAll(label, male, female);
        radioButton.setAlignment(Pos.CENTER_LEFT);
        
        VBox ageBox = new VBox();
        Label labelAge = new Label("Orphan's age");
        Slider age = new Slider();
        age.setMin(0);
        age.setMax(30);
        age.setValue(15);
        age.setShowTickLabels(true);
        age.setShowTickMarks(true);
        age.setMajorTickUnit(10);
        age.setMinorTickCount(5);
        age.setBlockIncrement(10);
        age.setOrientation(Orientation.HORIZONTAL);
  

        ageBox.setAlignment(Pos.CENTER);
        ageBox.getChildren().addAll(labelAge, age);




       // Adding nodes to HBox top
       top.getChildren().addAll(radioButton, ageBox);
       mainPane.setAlignment(Pos.CENTER);
       // ---------------------------------------------------------------------

        Label label2 = new Label("This is one orphan");
        Label label3 = new Label("This is another orphan");
        Label label4 = new Label("This is a third orphan");

        label2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        label3.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        label4.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

       
        
       

        VBox orphan1 = new VBox();
        VBox orphan2 = new VBox();
        VBox orphan3 = new VBox();

        



        // Adding nodes to HBox bottom
        bottom.getChildren().addAll(orphan1, orphan2, orphan3);
        bottom.setSpacing(100);

        orphan1.getChildren().add(label2);
        orphan2.getChildren().add(label3);
        orphan3.getChildren().add(label4);

        top.setAlignment(Pos.CENTER);
        orphan1.setAlignment(Pos.CENTER);
        orphan2.setAlignment(Pos.CENTER);
        orphan3.setAlignment(Pos.CENTER);

        HBox.setHgrow(top, Priority.ALWAYS);
        HBox.setHgrow(bottom, Priority.ALWAYS);
        HBox.setHgrow(orphan1, Priority.ALWAYS);
        HBox.setHgrow(orphan2, Priority.ALWAYS);
        HBox.setHgrow(orphan3, Priority.ALWAYS);


        VBox.setVgrow(top, Priority.ALWAYS);
        VBox.setVgrow(bottom, Priority.ALWAYS);
        VBox.setVgrow(orphan1, Priority.ALWAYS);
        VBox.setVgrow(orphan2, Priority.ALWAYS);
        VBox.setVgrow(orphan3, Priority.ALWAYS);
       

        VBox.setVgrow(mainPane, Priority.ALWAYS);
        mainPane.getChildren().addAll(title,top, bottom);

    }

    private void setStage(Stage mainStage, Scene scene) {
        mainStage.setTitle("Orphange");
        mainStage.setScene(scene);
        mainStage.show();

    }
}
