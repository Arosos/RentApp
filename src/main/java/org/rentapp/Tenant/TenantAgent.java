package org.rentapp.Tenant;

import org.rentapp.Tenant.Behaviors.RespondToOtherTenant;
import org.rentapp.Tenant.Behaviors.SendRequestToOtherTenantBehaviour;
import jade.core.Agent;
import jade.domain.*;
import jade.domain.FIPAAgentManagement.*;

/**
  @author Tomasz Zieliński
 */
public class TenantAgent extends Agent {
  protected void setup() {
    System.out.println("TenantAgent_"+getLocalName());
    //addBehaviour(new org.rentapp.Tenant.Behaviors.MatchGroupBehaviour());
    //addBehaviour(new org.rentapp.Tenant.Behaviors.SearchForOfferBehaviour());
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

