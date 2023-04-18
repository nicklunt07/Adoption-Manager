
import java.util.List;
import java.util.stream.Collectors;
import java.util.*;

import javafx.application.Application;
import javafx.beans.Observable;
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
    int maxAge;
    String sex = "";
    

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane welcomePage = createWelcomePage(primaryStage);
        Scene scene = new Scene(welcomePage, 800, 600);       

        primaryStage.setTitle("Orphanage Manager");
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
        HBox center = new HBox();
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
        Label label = new Label("Orphan's Sex: ");
        RadioButton male = new RadioButton("Male"); 
        RadioButton female = new RadioButton("Female"); 
        ToggleGroup gender = new ToggleGroup();
        male.setToggleGroup(gender);
        female.setToggleGroup(gender);


        radioButton.getChildren().addAll(label, male, female);
        radioButton.setAlignment(Pos.CENTER_LEFT);

        gender.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            RadioButton buttonGender  = (RadioButton) gender.getSelectedToggle();
            sex = buttonGender.getText();
            System.out.println(sex);
        });
        
       
        // --- age slider ---
        VBox ageBox = new VBox();
        Label labelAge = new Label("Orphan's Max Age : " + maxAge); //wont display the maxAge part right...idk why!
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

        age.valueProperty().addListener((observable, oldValue, newValue) -> { 
            maxAge = newValue.intValue();
        });

        ageBox.setAlignment(Pos.CENTER);
        ageBox.getChildren().addAll(labelAge, age);

        // --- reveal button ---
       
        Button reveal = new Button("Click to Reveal Orphans under Filters");

     
   
      
     
       // ---------------------------------------------------------------------
        
       
        bottom.setSpacing(100);


        top.setAlignment(Pos.CENTER);
       

        HBox.setHgrow(top, Priority.ALWAYS);
        HBox.setHgrow(bottom, Priority.ALWAYS);
      

        VBox.setVgrow(top, Priority.ALWAYS);
        VBox.setVgrow(bottom, Priority.ALWAYS);
   
       

        VBox.setVgrow(mainPane, Priority.ALWAYS);
       bottom.setAlignment(Pos.CENTER);

        top.getChildren().addAll(radioButton, ageBox);
        bottom.getChildren().addAll(reveal);
        mainPane.setAlignment(Pos.CENTER);
        mainPane.getChildren().addAll(title,top, bottom);

           
        reveal.setOnAction(event -> {     
            displayOrphans(bottom, maxAge, reveal);
        });
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
    public List<Orphan> getOrphans(int maxAge){ //added param but not used yet
        int age = 18;
        List<Orphan> orphans = orphanage.getPersons().stream()
                .filter(person -> person instanceof Orphan)
                .map(person -> (Orphan)person)
                .filter(orphan -> orphan.getAge()<  maxAge)
                .collect(Collectors.toList());
        return orphans;
       // persons.stream().filter(person -> person instanceof Orphan). map(person -> (Orphan)person).collect(Collectors.toList());
    }

    /**
     * 
     * @param bottom
     * @param age
     */
    private void displayOrphans(HBox bottom, int age, Button reveal){
       bottom.getChildren().remove(reveal);
        
    //    Employee employee2 = new Employee("Adnan Khaleeli", 20, "Male", "Founder", orphanage);
    //    employee2.setSkill(Orphanage.skills.get("DishWashing"));

    //    Button employeeSkill = employee2.getSkill().skill();

        age = 7;
        
      
            VBox orphanBox = new VBox();
            orphanBox.setStyle(STYLESHEET_CASPIAN);
            

            Button button = new Button("Adopt");
           

            // Label label10 = new Label(employee2.toString());
            // orphanBox.getChildren().addAll(label10, button, employeeSkill);
            bottom.getChildren().add(orphanBox);
        
    }

    public void setOrphan() {

    }
}