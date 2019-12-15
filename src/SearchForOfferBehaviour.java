package rentapp.behaviours.searchforoffer;

import jade.core.behaviours.*;

/**
 * Complex behaviour for role OfferFinder.
  @author Tomasz Zieliński
*/

public class SearchForOfferBehaviour extends FSMBehaviour {

  private static final String GetOffer = "GetOffer";
  private static final int GOOD_OFFER = 1;
  private static final int BAD_OFFER = 0;

  private static final String SendToTenantsOfferConsiderationRequest = "SendToTenantsOfferConsiderationRequest";
  private static final int ALL_AGREE = 1;
  private static final int SOMEONE_DISAGREE = 0;

  private static final String SendToLandlordGroupConsiderationRequest = "SendToLandlordGroupConsiderationRequest";
  private static final int LANDLORD_AGREE = 1;
  private static final int LANDLORD_DISAGREE = 0;

  private static final String SendConfirmationToTenant = "SendConfirmationToTenant";
  private static final String LastState = "LastState";

  @Override
  public void onStart(){
    registerFirstState(new NamePrinter(), GetOffer);
    registerTransition(GetOffer, GetOffer,BAD_OFFER);
    registerTransition(GetOffer, SendToTenantsOfferConsiderationRequest,GOOD_OFFER);

    registerState(new NamePrinter(), SendToTenantsOfferConsiderationRequest);
    registerTransition(SendToTenantsOfferConsiderationRequest,GetOffer,SOMEONE_DISAGREE);
    registerTransition(SendToTenantsOfferConsiderationRequest,SendToLandlordGroupConsiderationRequest,ALL_AGREE);

    registerState(new NamePrinter(), SendToLandlordGroupConsiderationRequest);
    registerTransition(SendToLandlordGroupConsiderationRequest, GetOffer, LANDLORD_DISAGREE);
    registerTransition(SendToLandlordGroupConsiderationRequest, GetOffer, LANDLORD_AGREE);

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
    private int exitValue = GOOD_OFFER;
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
