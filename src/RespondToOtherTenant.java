package rentapp.behaviours.matchgroup;

import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class RespondToOtherTenant extends CyclicBehaviour {
    @Override
    public void action() {
        ACLMessage msg = myAgent.receive(template);
        if (msg != null) {
            System.out.println("Message matching custom template received: " + msg.getContent());
            System.out.println(msg);
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
