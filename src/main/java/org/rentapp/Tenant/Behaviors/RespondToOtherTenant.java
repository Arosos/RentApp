package org.rentapp.Tenant.Behaviors;

import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class RespondToOtherTenant extends CyclicBehaviour {
    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            ACLMessage reply = msg.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            reply.setContent("TAK");
            System.out.println("Received message. My agent: " + myAgent.getName() + " Message matching custom template received: " + msg.getContent());
            myAgent.send(reply);
        }
        else {
            block();
        }
    }

    public class MatchXOntology implements MessageTemplate.MatchExpression {

        public boolean match(ACLMessage msg) {
            String ontology = msg.getOntology();
            return (ontology != null && ontology.startsWith("x"));
        }
    } // END of inner class MatchXOntology

    private MessageTemplate template = MessageTemplate.and(
            MessageTemplate.MatchPerformative(ACLMessage.INFORM),
            new MessageTemplate(new MatchXOntology()));
}
