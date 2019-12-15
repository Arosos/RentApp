package rentapp.agents;

import jade.core.Agent;
import jade.domain.*;
import jade.domain.FIPAAgentManagement.*;

import rentapp.behaviours.matchgroup.*;
import rentapp.behaviours.searchforoffer.*;

/**
  @author Tomasz Zieliński
 */
public class TenantAgent extends Agent {
  protected void setup() {
    System.out.println("TenantAgent_"+getLocalName());
    //addBehaviour(new MatchGroupBehaviour());
    //addBehaviour(new SearchForOfferBehaviour());
    addBehaviour(new SendRequestToOtherTenantBehaviour());
    addBehaviour(new RespondToOtherTenant());
    RegisterInDF();
  }

  private void RegisterInDF()
  {
    DFAgentDescription dfd = new DFAgentDescription();
    dfd.setName(getAID());
    ServiceDescription sd = new ServiceDescription();
    sd.setType("tenant");
    sd.setName("tenant");
    dfd.addServices(sd);
    try
    {
      DFService.register(this, dfd);
    }
    catch (FIPAException fe)
    {
      fe.printStackTrace();
    }
  }

  public int onEnd() {
    return 0;
  }
}

