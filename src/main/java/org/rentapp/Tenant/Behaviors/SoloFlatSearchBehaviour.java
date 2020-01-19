package org.rentapp.Tenant.Behaviors;

import jade.core.behaviours.*;


/**
 * 
  @author Tomasz Zieliński
 */
public class SoloFlatSearchBehaviour extends FSMBehaviour {

  private static final String SendDecideGroupRequest = "SendDecideGroupRequest";
  private static final String CreateGroup = "CreateGroup";
  private static final String ShareGroup = "ShareGroup";
  private static final int CONTINUE_SHARE_GROUP = 1;
  private static final int END_SHARE_GROUP = 0;

  private static final String LastState = "LastState";

  @Override
  public void onStart(){
    registerFirstState(new NamePrinter(), SendDecideGroupRequest);
    registerDefaultTransition(SendDecideGroupRequest, CreateGroup);

    registerState(new NamePrinter(), CreateGroup);
    registerDefaultTransition(CreateGroup,ShareGroup);

    registerState(new NamePrinter(), ShareGroup);
    registerTransition(ShareGroup, ShareGroup, CONTINUE_SHARE_GROUP);
    registerTransition(ShareGroup, SendDecideGroupRequest, END_SHARE_GROUP);

    registerLastState(new LastStateBehaviour(), LastState);
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
    private int exitValue = END_SHARE_GROUP;
    public void action() {
      System.out.println("Executing behaviour "+getBehaviourName());
    }
    public int onEnd() {
			return exitValue;
		}
  }
  private class LastStateBehaviour extends OneShotBehaviour {
    public void action(){
    }
  }
}
