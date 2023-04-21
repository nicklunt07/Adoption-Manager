import javafx.scene.control.Button;

public interface  Taskable {
    
    public Skillable getSkill(); 
    public void assignTask(Skillable skill); 
    public Button performTask();
   

}
