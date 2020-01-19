package org.rentapp.Landlord.Behaviors;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * 
  @author Tomasz Zieliński
 */

public class ShareOfferBehaviour extends CyclicBehaviour {
  public void action() {
    ACLMessage msg = myAgent.receive(template);
    if (msg != null) {
      System.out.println("Message matching custom template received:");
      System.out.println(msg);
    }
    else {
      block();
    }
  }

  public class MatchXOntology implements MessageTemplate.MatchExpression {

    public boolean match(ACLMessage msg) {
      String ontology = msg.getOntology();
      return (ontology != null && ontology.startsWith("X"));
    }
  } // END of inner class MatchXOntology

  private MessageTemplate template = MessageTemplate.and(
    MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
    new MessageTemplate(new MatchXOntology()));
  
}


