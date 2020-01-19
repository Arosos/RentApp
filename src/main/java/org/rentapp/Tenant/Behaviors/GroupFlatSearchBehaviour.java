package org.rentapp.Tenant.Behaviors;

import java.util.*;
import java.util.stream.*;

import jade.core.*;
import jade.core.behaviours.*;
import jade.domain.*;
import jade.domain.FIPAAgentManagement.*;
import jade.lang.acl.*;

/**
 * 
  @author Tomasz Zieliński
 */
public class GroupFlatSearchBehaviour extends FSMBehaviour {
  private static final String Propose_ConsiderGroupCreation = "Propose_ConsiderGroupCreation";
  private static final String SendDecideGroupRequest = "SendDecideGroupRequest";
  private static final String CreateGroup = "CreateGroup";
  private static final String ShareGroup = "ShareGroup";
  private static final int CONTINUE_SHARE_GROUP = 1;
  private static final int END_SHARE_GROUP = 0;

  private static final String LastState = "LastState";

  @Override
  public void onStart(){
    registerFirstState(new SendRequestToOtherTenant(), Propose_ConsiderGroupCreation);
    registerDefaultTransition(Propose_ConsiderGroupCreation,SendDecideGroupRequest);
    
    registerState(new NamePrinter(), SendDecideGroupRequest);
    registerDefaultTransition(SendDecideGroupRequest, CreateGroup);

    registerState(new NamePrinter(), CreateGroup);
    registerDefaultTransition(CreateGroup,ShareGroup);

    registerState(new NamePrinter(), ShareGroup);
    registerDefaultTransition(ShareGroup, LastState);
    registerTransition(ShareGroup, ShareGroup, CONTINUE_SHARE_GROUP);
    registerTransition(ShareGroup, Propose_ConsiderGroupCreation, END_SHARE_GROUP);

    registerLastState(new LastStateBehaviour(), LastState);
  }

  @Override
  public int onEnd() {
    System.out.println("FSM behaviour completed.");
    return super.onEnd();
  }

  /**
    dummy behaviur
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

  private class SendRequestToOtherTenant extends OneShotBehaviour
  {
    public void action()
    {
      DFAgentDescription template = new DFAgentDescription();
      ServiceDescription sd = new ServiceDescription();
      sd.setType("tenant");
      template.addServices(sd);
      List<AID> otherTenants = new ArrayList<AID>();
      try
      {
        DFAgentDescription[] result = DFService.search(myAgent, template);
        otherTenants = Stream.of(result)
                .map(t -> t.getName())
                .collect(Collectors.toList());
      }
      catch (FIPAException fe)
      {
        fe.printStackTrace();
        done();
      }
      if (otherTenants.size() < 1)
        done();

      AID firstTenant = (AID)otherTenants.toArray()[0];
      ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
      msg.addReceiver(new AID(firstTenant.toString(), AID.ISLOCALNAME));
      msg.setLanguage("x");
      msg.setOntology("xx");
      msg.setContent("Hej, chcesz być ze mną w grupie?");
      myAgent.send(msg);
    }
  }
}
