package rentapp.agents;

import jade.core.Agent;
import rentapp.behaviours.matchgroup.*;
import rentapp.behaviours.searchforoffer.*;

/**
  @author Tomasz Zieliński
 */
public class TenantAgent extends Agent {
  protected void setup() {
    System.out.println("TenantAgent_"+getLocalName());
    addBehaviour(new MatchGroupBehaviour());
    addBehaviour(new SearchForOfferBehaviour());
  }

  public int onEnd() {
    return 0;
  }
}

