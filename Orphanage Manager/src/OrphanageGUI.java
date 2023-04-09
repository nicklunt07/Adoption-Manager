import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
     HBox top = new HBox(); 
     HBox bottom = new HBox(); 
     
     Label label = new Label("This is the top HBox"); 
    
     Label label2 = new Label("This is one orphan");
     Label label3 = new Label("This is another orphan");
     Label label4 = new Label("This is a third orphan");
     
     VBox orphan1 = new VBox(); 
     VBox orphan2 = new VBox(); 
     VBox orphan3 = new VBox(); 

     orphan1.getChildren().add(label2);
     orphan2.getChildren().add(label3);
     orphan3.getChildren().add(label4);

     top.getChildren().add(label); 
     bottom.getChildren().addAll(orphan1, orphan2, orphan3);
     bottom.setSpacing(100);

     mainPane.setTop(top);
     mainPane.setBottom(bottom);
     
    }

    private void setStage(Stage mainStage, Scene scene) {
        mainStage.setTitle("Orphange");
        mainStage.setScene(scene);
        mainStage.show();
        
    }
}
