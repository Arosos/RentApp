package rentapp.behaviours;

import jade.core.behaviours.*;

/**
 * 
  @author Tomasz Zieliński
*/

public class GroupFinderBehaviour extends ParallelBehaviour{
  public GroupFinderBehaviour(){
    super(WHEN_ANY);
  }

  @Override
  public void onStart(){
    addSubBehaviour(new ShareGroupPreferencesBehaviour());

    addSubBehaviour(new WrapperBehaviour(new GroupFlatSearchBehaviour()) {
      public boolean done() {
        return false;
      }
    });
    addSubBehaviour(new WrapperBehaviour(new SoloFlatSearchBehaviour()) {
      public boolean done() {
        return false;
      }
    });

  }
}