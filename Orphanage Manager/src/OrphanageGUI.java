import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;

public class OrphanageGUI extends Application {

    public static void main(String[] args) {
        Application.launch();
    }

    public void start(final Stage mainStage) {
        VBox root = new VBox();
        setUpControls(root);
        Scene scene = new Scene(root);
        setStage(mainStage, scene);
    }

    private void setUpControls(VBox mainPane) {
        HBox top = new HBox();
        HBox bottom = new HBox();

        top.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        bottom.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
     

        top.setFillHeight(true);
        bottom.setFillHeight(true);

        Label label = new Label("This is the top HBox(Adopter questions)");

        Label label2 = new Label("This is one orphan");
        Label label3 = new Label("This is another orphan");
        Label label4 = new Label("This is a third orphan");

        label2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        label3.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        label4.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

       
       

        VBox orphan1 = new VBox();
        VBox orphan2 = new VBox();
        VBox orphan3 = new VBox();

        

         top.getChildren().add(label);
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
        mainPane.getChildren().addAll(top, bottom);

    }

    private void setStage(Stage mainStage, Scene scene) {
        mainStage.setTitle("Orphange");
        mainStage.setScene(scene);
        mainStage.show();

    }
}
