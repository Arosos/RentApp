package rentapp.behaviours.matchgroup;

import jade.core.behaviours.*;

/**
 * Complex behaviour for role GroupFinder.
  @author Tomasz Zieliński
*/

public class MatchGroupBehaviour extends ParallelBehaviour{
  public MatchGroupBehaviour(){
    super(WHEN_ANY);
  }

  @Override
  public void onStart(){
    addSubBehaviour(new ShareGroupPreferencesBehaviour());
    addSubBehaviour(new GroupFlatSearchBehaviour());
    addSubBehaviour(new SoloFlatSearchBehaviour());
  }
}