import java.io.Serializable;

import javafx.scene.control.Button;

/**
 * This interface models the behavior of someone taskable
 * @author Adnan Khaleeli
 * @verion April 24, 2023
 */
public interface Taskable extends Serializable{
    /**
     * getter for the Skill(So it can be used as a Button)
     * @return Skillable
     */
    public Skillable getSkill(); 

    /**
     * Setting their skill to a task, this for orphans can be making their bed for instance.
     * @param skill
     */
    public void assignTask(Skillable skill); 

    /**
     * This method returns the button that is used to actually persom the skill on the GUI.
     * @return Button
     */
    public Button performTask();
    
   
}
