import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OrphanageGUI extends Application {
    
    public static void main(String[] args) {
        Application.launch();
    }

    public void start(final Stage mainStage) {
        BorderPane root = new BorderPane();
        setUpControls(root);
        Scene scene = new Scene(root, 500, 500);
        setStage(mainStage, scene);
    }

    private void setUpControls(BorderPane mainPane) {
     HBox top = new HBox(100); 
     HBox bottom = new HBox(100); 
     
     Label label = new Label("This is the top HBox"); 
     Label label2 = new Label("This is the botton HBox");

     top.getChildren().add(label); 
     bottom.getChildren().add(label2);

     mainPane.setTop(top);
     mainPane.setBottom(bottom);
     
    }

    private void setStage(Stage mainStage, Scene scene) {
        mainStage.setTitle("Orphange");
        mainStage.setScene(scene);
        mainStage.show();
        
    }
}
