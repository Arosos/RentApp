package rentapp.behaviours.menageoffer;

import jade.core.behaviours.*;

/**
 * Complex behaviour for role LandlordAssistant.
  @author Tomasz Zieliński
*/

public class ManageOfferBehaviour extends ParallelBehaviour{
  public ManageOfferBehaviour(){
    super(WHEN_ANY);
  }

  @Override
  public void onStart(){
    addSubBehaviour(new ShareOfferBehaviour());
    addSubBehaviour(new SendGroupRequestBehaviour());
  }
}