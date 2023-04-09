import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OrphanageGUI extends Application {
    
    public static void main(String[] args) {
        Application.launch();
    }

    public void start(final Stage mainStage) {
        Pane root = new Pane();
        setUpControls(root);
        Scene scene = new Scene(root, 600, 800);
        setStage(mainStage, scene);
    }

    private void setUpControls(Pane mainPane) {
     
    }

    private void setStage(Stage mainStage, Scene scene) {
        mainStage.setScene(scene);
         mainStage.show();
        
    }
}
