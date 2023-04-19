
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OrphangeGUI extends Application {
    Orphanage orphanage = new Orphanage();
    int maxAge;
    String sex = "Male";
    Orphan orphanSelected;
    List<Orphan> possibleOrphans;
    private int counter = 0;
    private boolean pressed = false;

    VBox orphanBox = new VBox();

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

    private void setupControls(VBox mainPane) {
        Button reveal = new Button("Click to Reveal Orphans under Filters");
        mainPane.setStyle("-fx-background-color: #0A1C2E");
        // Menu bar setup
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        Menu helpMenu = new Menu("Help");

        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> System.exit(0));

        fileMenu.getItems().add(exitItem);
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        // mainPane.setClip(menuBar);

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
       // title.setStyle("-fx-background-color: #B15B37;");
        title.setTextFill(Paint.valueOf("#FFD700"));

        VBox radioButton = new VBox();
        Label label = new Label("Orphan's Sex: ");
        label.setTextFill(Paint.valueOf("#FFD700"));
        RadioButton male = new RadioButton("Male");
        male.setTextFill(Paint.valueOf("#FFD700"));
        RadioButton female = new RadioButton("Female");
        female.setTextFill(Paint.valueOf("#FFD700"));
        ToggleGroup gender = new ToggleGroup();
        male.setToggleGroup(gender);
        female.setToggleGroup(gender);

        radioButton.getChildren().addAll(label, male, female);
        radioButton.setAlignment(Pos.CENTER_LEFT);

        gender.selectToggle(male);
        gender.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            RadioButton buttonGender = (RadioButton) gender.getSelectedToggle();
            sex = buttonGender.getText();

        });

        // --- age slider ---
        VBox ageBox = new VBox();
        Label labelAge = new Label("Orphan's age"); 
        labelAge.setTextFill(Paint.valueOf("#FFD700"));
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
            labelAge.setText("Orphan's age: " + maxAge);
        });

        ageBox.setAlignment(Pos.CENTER);
        ageBox.getChildren().addAll(labelAge, age);

        // --- reveal button ---

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
        mainPane.getChildren().addAll(title, top, bottom);

        reveal.setOnAction(event -> {
            setOrphans();
            displayOrphans(bottom, reveal);

        });
    }

    private Pane createWelcomePage(Stage primaryStage) {
        VBox welcomeLayout = new VBox(20);
        welcomeLayout.setAlignment(Pos.CENTER);
        welcomeLayout.setPadding(new Insets(20));
        welcomeLayout.setStyle("-fx-background-color: #B0E0E6;");

        Label nameLabel = new Label("Orphanage Name");
        nameLabel.setStyle("-fx-font-size: 24;");

        Label motiveLabel = new Label(
                "An orphanage is a place where the cries of the innocent are heard, and the love of the forgotten is felt. - Anthony T. Hincks");
        motiveLabel.setWrapText(true);
        motiveLabel.setMaxWidth(400);

        Button enterButton = new Button("Enter");

        // Add an event listener to the button to navigate to the main application
        // window
        enterButton.setOnAction(e -> {
            VBox root = new VBox();
            setupControls(root);
            primaryStage.setScene(new Scene(root, 800, 600));
        });

        welcomeLayout.getChildren().addAll(nameLabel, motiveLabel, enterButton);

        return welcomeLayout;
    }

    /**
     * 
     * @param bottom
     */
    private void displayOrphans(HBox bottom, Button reveal) {

        bottom.getChildren().remove(reveal);
        orphanBox.setAlignment(Pos.CENTER);
        Button backward = new Button();
        backward.setText("Previous Orphan");

        Button forward = new Button();
        forward.setText("Next orphan");

        forward.setOnAction(e -> {
            if (counter < possibleOrphans.size()-1 && counter >= 0 && pressed == true) {
                counter++;
                orphanBox.getChildren().clear();
                bottom.getChildren().clear();
                Button childSkill = possibleOrphans.get(counter).getSkill().skill();
                childSkill.setText("Skill");

                //orphanBox.setStyle(STYLESHEET_CASPIAN);

                Button button = new Button("Adopt");
                button.setOnAction(event -> {
                    adopt();
                });

                Label label10 = new Label(possibleOrphans.get(counter).toString());
                label10.setTextFill(Paint.valueOf("#FFD700"));
                orphanBox.getChildren().addAll(label10, button, childSkill, forward, backward);
                bottom.getChildren().add(orphanBox);
            }
        });

        backward.setOnAction(e -> {
            if (counter <= possibleOrphans.size()-1 && counter > 0 && pressed == true) {
                try {
                counter--;
                orphanBox.getChildren().clear();
                bottom.getChildren().clear();
                Button childSkill = possibleOrphans.get(counter).getSkill().skill();
                childSkill.setText("Skill");

                //orphanBox.setStyle(STYLESHEET_CASPIAN);

                Button button = new Button("Adopt");
                button.setOnAction(event -> {
                    adopt();
                });

                Label label10 = new Label(possibleOrphans.get(counter).toString());
                label10.setTextFill(Paint.valueOf("#FFD700"));
                orphanBox.getChildren().addAll(label10, button, childSkill, forward, backward);
                bottom.getChildren().add(orphanBox);

                } catch(Exception f) {

                }
            }
        });

        if (pressed == false) {
            Button childSkill = possibleOrphans.get(counter).getSkill().skill();
            childSkill.setText("Skill");

            //orphanBox.setStyle(STYLESHEET_CASPIAN);

            Button button = new Button("Adopt");
            button.setOnAction(event -> {
                adopt();
            });

            Label label10 = new Label(possibleOrphans.get(counter).toString());
            label10.setTextFill(Paint.valueOf("#FFD700"));
            orphanBox.getChildren().addAll(label10, button, childSkill, forward, backward);
            bottom.getChildren().add(orphanBox);
            pressed = true;
        }

    }

    private void setOrphans() {
        possibleOrphans = orphanage.getPersons().parallelStream()
                .filter(person -> person instanceof Orphan)
                .map((person -> (Orphan) person))
                .filter(orphan -> orphan.getAge() - 5 <= maxAge && maxAge <= orphan.getAge() + 5)
                .filter(orphan -> orphan.getGender().equals(sex))
                .collect(Collectors.toList());
    }

    /**
     * adopting the orphan
     */
    private void adopt(){
        possibleOrphans.get(counter).AdoptionInfo();
        showAdoptionDetails(possibleOrphans.get(counter));
        possibleOrphans.remove(counter);
    }

    /**
     * 
     * @param adoptedOrphan
     */
    private void showAdoptionDetails(Orphan adoptedOrphan) {
        Stage detailsStage = new Stage();
        VBox detailsLayout = new VBox(20);
        detailsLayout.setAlignment(Pos.CENTER);
        detailsLayout.setPadding(new Insets(20));
        detailsLayout.setStyle("-fx-background-color: #B0E0E6");

    
        Label title = new Label("Adoption Details");
        title.setStyle("-fx-font-size: 24;");
    
        Label detailsLabel = new Label(adoptedOrphan.toString());
        detailsLabel.setWrapText(true);
        detailsLabel.setMaxWidth(400);
    
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> detailsStage.close());
    
        detailsLayout.getChildren().addAll(title, detailsLabel, closeButton);
    
        Scene detailsScene = new Scene(detailsLayout, 400, 300);
        detailsStage.setScene(detailsScene);
        detailsStage.setTitle("Adoption Details");
        detailsStage.initModality(Modality.APPLICATION_MODAL);
        detailsStage.showAndWait();
    }
    
}