package rentapp.behaviours;

import jade.core.behaviours.*;


/**
  @author Tomasz Zieliński
 */
public class TenantBehaviour extends FSMBehaviour {
  private static final String STATE_A = "A";
  private static final String STATE_B = "B";
  
  @Override
  public void onStart(){
    registerFirstState(new NamePrinter(), STATE_A);
    registerDefaultTransition(STATE_A, STATE_B);
    registerLastState(new NamePrinter(), STATE_B);
  }

  @Override
  public int onEnd() {
    System.out.println("FSM behaviour completed.");
    return super.onEnd();
  }

  /**
    Inner class NamePrinter.
    This behaviour just prints its name
  */
  private class NamePrinter extends OneShotBehaviour {
    public void action() {
      System.out.println("Executing behaviour "+getBehaviourName());
    }
  }
}
