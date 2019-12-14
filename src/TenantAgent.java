package rentapp.agents;

import jade.core.Agent;
import rentapp.behaviours.TenantBehaviour;

/**
  @author Tomasz Zieliński
 */
public class TenantAgent extends Agent {
  protected void setup() {
    System.out.println("TenantAgent_"+getLocalName());
    addBehaviour(new TenantBehaviour());
  }

  public int onEnd() {
    return 0;
  }
}

