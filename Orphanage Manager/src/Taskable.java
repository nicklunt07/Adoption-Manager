import java.io.Serializable;

import javafx.scene.control.Button;

public interface  Taskable extends Serializable{
    
    public Skillable getSkill(); 
    public void assignTask(Skillable skill); 
    public Button performTask();
   

}
