
import java.util.List;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class OrphangeGUI extends Application {
    Orphanage orphanage = new Orphanage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane welcomePage = createWelcomePage(primaryStage);
        Scene scene = new Scene(welcomePage, 800, 600);       

        //primaryStage.setTitle("Orphanage Manager - Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupControls( VBox mainPane) {
        // Menu bar setup
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        Menu helpMenu = new Menu("Help");
    
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> System.exit(0));
    
        fileMenu.getItems().add(exitItem);
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        //mainPane.setClip(menuBar);
    
        // Tab pane setup
        TabPane tabPane = new TabPane();

        Tab childrenTab = new Tab("Children");
        Tab staffTab = new Tab("Staff");
        Tab roomsTab = new Tab("Rooms");

        childrenTab.setClosable(false);
        staffTab.setClosable(false);
        roomsTab.setClosable(false);

        tabPane.getTabs().addAll(childrenTab, staffTab, roomsTab);

        // Add the menu bar and tab pane to the mainPane (VBox)
        mainPane.getChildren().addAll(menuBar, tabPane);
        
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
        age.setMax(18);
        age.setValue(15);
        age.setShowTickLabels(true);
        age.setShowTickMarks(true);
        age.setMajorTickUnit(2);
        age.setMinorTickCount(1);
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

       /*
        * Orphanage orphanage = new Orphanage("Friendship Orphan Home");
        orphanage.addOrphans();
        for(Orphan orphan : orphanage.getOrphans()){
            VBox orphanBox = new VBox();
            orphanBox.setStyle("-fx-background-color: #E0E0E0");
            orphanBox.setPrefWidth(300);
            orphanBox.setPrefHeight(200);
            Label orpLabel = new Label(orphan.toString());
            orphanBox.getChildren().add(orpLabel);
            bottom.getChildren().add(orphanBox);
            
            Button button = new Button("adopt");
            orphanBox.getChildren().add(button);
        }
        */

        for(Orphan orphan : getOrphans()){
            VBox orphanBox = new VBox();
            orphanBox.setStyle(STYLESHEET_CASPIAN);
            orphanBox.setPrefWidth(350);
            orphanBox.setPrefHeight(200);
            Label label10 = new Label(orphan.toString());
            orphanBox.getChildren().add(label10);
            bottom.getChildren().add(orphanBox);
        }
        
       

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
    

   

    private Pane createWelcomePage(Stage primaryStage) {
        VBox welcomeLayout = new VBox(20);
        welcomeLayout.setAlignment(Pos.CENTER);
        welcomeLayout.setPadding(new Insets(20));
        welcomeLayout.setStyle("-fx-background-color: #B0E0E6;");

        Label nameLabel = new Label("Orphanage Name");
        nameLabel.setStyle("-fx-font-size: 24;");

        Label motiveLabel = new Label("An orphanage is a place where the cries of the innocent are heard, and the love of the forgotten is felt. - Anthony T. Hincks");
        motiveLabel.setWrapText(true);
        motiveLabel.setMaxWidth(400);

        Button enterButton = new Button("Enter");

        // Add an event listener to the button to navigate to the main application window
        enterButton.setOnAction(e -> {
            VBox root = new VBox();
            setupControls(root);
            primaryStage.setScene(new Scene(root, 800, 600));
        });

        welcomeLayout.getChildren().addAll(nameLabel, motiveLabel, enterButton);

        return welcomeLayout;
    }

    /**
     * @author Adnan Khaleeli
     * @author Sushanth Ambati
     * @return orphans
     */
    public List<Orphan> getOrphans(){
        int age = 5;
        List<Orphan> orphans = orphanage.getPersons().stream()
                .filter(person -> person instanceof Orphan)
                .map(person -> (Orphan)person)
                .filter(orphan -> orphan.getAge()<age)
                .collect(Collectors.toList());
        return orphans;
       // persons.stream().filter(person -> person instanceof Orphan). map(person -> (Orphan)person).collect(Collectors.toList());
    }

    
    
    
    
}
