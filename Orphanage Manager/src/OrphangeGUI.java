
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class OrphangeGUI extends Application {
    Orphanage orphanage = new Orphanage();
    int maxAge;
    String sex = "Male";
    Orphan orphanSelected;
    List<Orphan> possibleOrphans;
    private int counter = 0;
    private boolean pressed = false;
    private ArrayList<String> languages = new ArrayList<String>();
    VBox orphanBox = new VBox();
    private TabPane tabPane = new TabPane();
    private Tab childrenTab = new Tab("Children");
    private Tab staffTab = new Tab("Staff");
    private Tab roomsTab = new Tab("Rooms");
    private final int PASSWORD = 123456;
    private MenuBar menuBar = new MenuBar();
    private Menu fileMenu = new Menu("File");
    private Menu helpMenu = new Menu("Help");
    private Adopter adopter;
    private boolean isComplete;

    public static void main(String[] args) throws NoOrphanFoundException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        orphanage = orphanage.deserializeOrphanage();
        if (orphanage == null) {
            orphanage = new Orphanage();
        }
        Pane welcomePage = createWelcomePage(primaryStage);
        Scene scene = new Scene(welcomePage, 800, 600);

        primaryStage.setTitle("Orphanage Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupControls(VBox mainPane) {
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> System.exit(0));

        MenuItem refreshItem = new MenuItem("Refresh");

        refreshItem.setOnAction(e -> {
            SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
            Tab currentTab = selectionModel.getSelectedItem();

            if (currentTab == childrenTab) {
                pressed = false;
                orphanBox.getChildren().clear();
                setChilrenContent(mainPane);
            } else {
                setEmployeeContent(mainPane);
            }

        });

        fileMenu.getItems().addAll(exitItem);
        helpMenu.getItems().add(refreshItem);
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        setChilrenContent(mainPane);

        childrenTab.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                mainPane.getChildren().remove(menuBar);
                pressed = false;
                orphanBox.getChildren().clear();
                setChilrenContent(mainPane);
            }
        });
        staffTab.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                setEmployeeContent(mainPane);
            }
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
            Pane registrationPage = createRegistrationPage(primaryStage);
            primaryStage.setScene(new Scene(registrationPage, 800, 600));
            /*
             * VBox root = new VBox();
             * setupControls(root);
             * primaryStage.setScene(new Scene(root, 800, 600));
             */
        });

        welcomeLayout.getChildren().addAll(nameLabel, motiveLabel, enterButton);

        return welcomeLayout;
    }

    private Pane createRegistrationPage(Stage primaryStage) {
        // Making a title and updating the font
        HBox titleBox = new HBox();
        Label title = new Label("Verify your details");
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        title.setAlignment(Pos.CENTER);
        titleBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleBox.getChildren().add(title);
        titleBox.setAlignment(Pos.CENTER);

        VBox registrationLayout = new VBox(20);
        registrationLayout.setAlignment(Pos.CENTER);
        registrationLayout.setPadding(new Insets(20));
        registrationLayout.setStyle("-fx-background-color: #B0E0E6;");

        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");

        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");

        TextField mobileNumberField = new TextField();
        mobileNumberField.setPromptText("Mobile Number");

        TextField emailField = new TextField();
        emailField.setPromptText("Email ID");

        Button submitButton = new Button("Submit");

        // an event listener to the button to navigate to the main application window
        submitButton.setOnAction(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String mobileNumber = mobileNumberField.getText();
            String email = emailField.getText();

            // If the fields of the text box are left empty then gives out a message
            if (firstName.isEmpty() || lastName.isEmpty() || mobileNumber.isEmpty() || email.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Missing Information");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all the fields.");
                alert.showAndWait();
            } else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registration Successful");
                alert.setHeaderText(null);
                alert.setContentText("You're all set! Look at the orphans.");
                alert.showAndWait();

                VBox root = new VBox();
                setupControls(root);
                primaryStage.setScene(new Scene(root, 800, 600));
            }
        });

        registrationLayout.getChildren().addAll(title, firstNameField, lastNameField, mobileNumberField, emailField,
                submitButton);

        return registrationLayout;
    }

    /**
     * 
     * @param bottom
     */
    private void displayOrphans(HBox bottom, Button reveal) {

        try {
            bottom.getChildren().remove(reveal);
            orphanBox.setAlignment(Pos.CENTER);
            Button backward = new Button();
            backward.setText("Previous Orphan");

            Button forward = new Button();
            forward.setText("Next orphan");

            forward.setOnAction(e -> {
                if (counter < possibleOrphans.size() - 1 && counter >= 0 && pressed == true) {
                    counter++;
                    orphanBox.getChildren().clear();
                    bottom.getChildren().clear();
                    Button childSkill = possibleOrphans.get(counter).performTask();
                    childSkill.setText("Skill");

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
                if (counter <= possibleOrphans.size() - 1 && counter > 0 && pressed == true) {
                    try {
                        counter--;
                        orphanBox.getChildren().clear();
                        bottom.getChildren().clear();
                        Button childSkill = possibleOrphans.get(counter).getSkill().skill();
                        childSkill.setText("Skill");

                        // orphanBox.setStyle(STYLESHEET_CASPIAN);

                        Button button = new Button("Adopt");
                        button.setOnAction(event -> {
                            adopt();
                        });

                        Label label10 = new Label(possibleOrphans.get(counter).toString());
                        label10.setTextFill(Paint.valueOf("#FFD700"));
                        orphanBox.getChildren().addAll(label10, button, childSkill, forward, backward);
                        bottom.getChildren().add(orphanBox);

                    } catch (Exception f) {

                    }
                }
            });

            if (pressed == false) {
                Button childSkill = possibleOrphans.get(counter).getSkill().skill();
                childSkill.setText("Skill");

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
        } catch (Exception e) {
            System.err.println("Custom Exception:");
            throw new NoOrphanFoundException("No orphan was found for your given inputs");
        }

    }

    private void setOrphans() {
        possibleOrphans = orphanage.getPersons().parallelStream()
                .filter(person -> person instanceof Orphan)
                .map((person -> (Orphan) person))
                .filter(orphan -> orphan.getAge() - 5 <= maxAge && maxAge <= orphan.getAge() + 5)
                .filter(orphan -> orphan.getGender().equals(sex))
                .filter(orphan -> orphan.speaksLanguage(languages))
                .collect(Collectors.toList());
        if (possibleOrphans.size() == 0) {

        }
    }

    /**
     * adopting the orphan
     */
    private void adopt() {
        Stage stage = new Stage();
        stage.setHeight(300);
        stage.setWidth(400);
        stage.setTitle("Adopter Information");
        VBox pane = new VBox();
        Scene scene1 = new Scene(pane);
        pane.setStyle("-fx-background-color: #0A1C2E");
        
        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Enter your name");
       

        TextField ageTextField = new TextField(); 
        ageTextField.setPromptText("age");
       

        TextField gender = new TextField();
        gender.setPromptText("Enter your gender");
      

        ToggleGroup group = new ToggleGroup(); 
        Label label = new Label("Are you are felon?");
        label.setStyle("-fx-text-fill: #FFD700;");
        RadioButton yesFellon = new RadioButton("Yes"); 
        RadioButton noFellon = new RadioButton("No");
        yesFellon.setTextFill(Paint.valueOf("#FFD700"));
        noFellon.setTextFill(Paint.valueOf("#FFD700"));
        yesFellon.setToggleGroup(group); 
        noFellon.setToggleGroup(group); 

        VBox felonBox = new VBox(); 
        felonBox.getChildren().addAll(label, yesFellon, noFellon);
        

        TextField income = new TextField();
        income.setPromptText("Enter your gross income");
       

        VBox ComboVBox = new VBox();
        Label label1 = new Label("Enter your number of dependencies");
        label1.setStyle("-fx-text-fill: #FFD700;");
        ComboBox<Integer> dependencies = new ComboBox<>();
        dependencies.getItems().addAll(1, 2, 3,4,5,6,7,8,9,10);
        ComboVBox.getChildren().addAll(label1,dependencies);
       
       
   
      

        Button submitButton = new Button();
        submitButton.setText("Submit");
        pane.setAlignment(Pos.TOP_CENTER);
        pane.getChildren().addAll(nameTextField,ageTextField,gender,felonBox,income,ComboVBox, submitButton);
        submitButton.setOnAction(e -> {
            Toggle toggle = group.getSelectedToggle();
            RadioButton selectedRadioButton = (RadioButton) toggle;
            boolean felon = false;
            
            if(selectedRadioButton.getText().equals("Yes")) {
               felon= true;
            }
            try {
             adopter = new Adopter(orphanage,nameTextField.getText(),Integer.parseInt(ageTextField.getText()),gender.getText(),felon,Double.parseDouble(income.getText()), dependencies.getValue());
             isComplete = true;
             stage.close();
             if(!(adopter == null) && adopter.isEligableCareGiver()) {
                System.out.println("Ah");
                possibleOrphans.get(counter).AdoptionInfo();
                showAdoptionDetails(possibleOrphans.get(counter));
                possibleOrphans.remove(counter);
                orphanage.serializeOrphanage();
            }
            } catch(Exception f) {
              Label labelError = new Label("Please enter proper inputs");
              labelError.setStyle("-fx-text-fill: #FFD700;");
              pane.getChildren().add(labelError);
            }
        });

       
       
        stage.centerOnScreen();
        pane.setPrefHeight(100);
        pane.setPrefHeight(400);
        stage.setScene(scene1);
        stage.show();
        
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

        Label title = new Label("Adoption Valid!!");
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

    private void setChilrenContent(VBox mainPane) {
        mainPane.getChildren().clear();
        Button reveal = new Button("Click to Reveal Orphans under Filters");
        mainPane.setStyle("-fx-background-color: #0A1C2E");

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

        VBox LanguageBox = new VBox();
        Label languageLabel = new Label("Languages Known(3 years+)");
        languageLabel.setTextFill(Paint.valueOf("#FFD700"));
        CheckBox EnglishBox = new CheckBox("English");
        CheckBox SpanishBox = new CheckBox("Spanish");
        EnglishBox.setPrefHeight(10);
        EnglishBox.setPrefWidth(70);
        EnglishBox.setStyle("-fx-text-fill: #FFD700;");
        SpanishBox.setPrefHeight(10);
        SpanishBox.setPrefWidth(70);
        SpanishBox.setStyle("-fx-text-fill: #FFD700;");

        LanguageBox.getChildren().addAll(languageLabel, EnglishBox, SpanishBox);
        LanguageBox.setAlignment(Pos.CENTER);
        EnglishBox.setAlignment(Pos.CENTER);
        SpanishBox.setAlignment(Pos.CENTER);

        EnglishBox.setOnAction(event -> {
            if (EnglishBox.isSelected()) {
                languages.add(EnglishBox.getText());
            } else if (EnglishBox.isSelected() == false) {
                languages.remove(EnglishBox.getText());
            }
        });
        SpanishBox.setOnAction(event -> {
            if (SpanishBox.isSelected()) {
                languages.add(SpanishBox.getText());
            } else if (SpanishBox.isSelected() == false) {
                languages.remove(SpanishBox.getText());
            }
        });

        bottom.setSpacing(100);

        top.setAlignment(Pos.CENTER);

        HBox.setHgrow(top, Priority.ALWAYS);
        HBox.setHgrow(bottom, Priority.ALWAYS);

        VBox.setVgrow(top, Priority.ALWAYS);
        VBox.setVgrow(bottom, Priority.ALWAYS);

        VBox.setVgrow(mainPane, Priority.ALWAYS);
        bottom.setAlignment(Pos.CENTER);

        top.getChildren().addAll(radioButton, ageBox, LanguageBox);
        bottom.getChildren().addAll(reveal);
        mainPane.setAlignment(Pos.CENTER);
        mainPane.getChildren().addAll(title, top, bottom);

        reveal.setOnAction(event -> {
            setOrphans();
            displayOrphans(bottom, reveal);

        });
    }

    private void setEmployeeContent(VBox mainPane) {
        mainPane.getChildren().clear();
        mainPane.getChildren().addAll(menuBar, tabPane);

        mainPane.setAlignment(Pos.TOP_CENTER);
        mainPane.setPrefWidth(1000);
        HBox box1 = new HBox(1000);
        box1.setAlignment(Pos.CENTER);
        mainPane.setFillWidth(true);

        TextField textField = new TextField();
        textField.setPrefWidth(50);
        box1.getChildren().add(textField);
        Label label = new Label("Enter the Password");

        VBox.setMargin(label, new Insets(100, 0, 0, 0));
        VBox.setMargin(textField, new Insets(10, 0, 0, 0));
        Label error = new Label("Enter a valid passward");
        Label incorrect = new Label("Wrong passward entered, try again");
        label.setStyle("-fx-text-fill: #FFD700; -fx-font-size: 40px;");
        Button submitButton = new Button("Submit");
        VBox.setMargin(submitButton, new Insets(10, 0, 0, 0));
        submitButton.setOnAction((e) -> {
            String text = textField.getText();
            if (mainPane.getChildren().contains(error)) {
                mainPane.getChildren().remove(error);
            }
            if (mainPane.getChildren().contains(incorrect)) {
                mainPane.getChildren().remove(incorrect);
            }
            try {
                int enteredPassword = Integer.parseInt(text);
                if (enteredPassword == PASSWORD) {
                    setUpEmployeesGUI(mainPane);
                } else {
                    incorrect.setStyle("-fx-text-fill: #FFD700; -fx-font-size: 20px;");
                    mainPane.getChildren().add(incorrect);
                }

            } catch (Exception f) {
                error.setStyle("-fx-text-fill: #FFD700; -fx-font-size: 20px;");
                mainPane.getChildren().add(error);

            }
        });
        mainPane.getChildren().addAll(label, box1, submitButton);

    }

    private void setUpEmployeesGUI(VBox mainPane) {
        mainPane.getChildren().clear();
        tabPane.getTabs().addAll(childrenTab, staffTab, roomsTab);
        mainPane.getChildren().addAll(menuBar, tabPane);
        List<Employee> employees = orphanage.getPersons().parallelStream()
                .filter(person -> person instanceof Employee)
                .map((person -> (Employee) person))
                .collect(Collectors.toList());
        Label label1 = new Label("Employees");
        label1.setStyle("-fx-text-fill: #FFD700; -fx-font-size: 40px;");
        for (int i = 0; i < employees.size(); i++) {

            Label labelName = new Label(employees.get(i).getName());
            labelName.setStyle("-fx-text-fill: #FFD700; -fx-font-size: 20px;");

            Label labelAge = new Label("Age:" + employees.get(i).getAge());
            labelAge.setStyle("-fx-text-fill: #FFD700; -fx-font-size: 15px;");

            Label labelID = new Label("ID:" + employees.get(i).getID());
            labelID.setStyle("-fx-text-fill: #FFD700; -fx-font-size: 15px;");

            Button skill = employees.get(i).performTask();
            skill.setText("Skill");

            Label breaker = new Label("-----------------------");
            breaker.setStyle("-fx-text-fill: #000000; -fx-font-size: 15px;");

            VBox info = new VBox();
            info.getChildren().addAll(labelName, labelID, labelAge, skill, breaker);
            info.setAlignment(Pos.CENTER);
            HBox box = new HBox();
            box.setAlignment(Pos.CENTER);
            box.getChildren().addAll(info);
            mainPane.getChildren().add(box);

        }

    }


}
