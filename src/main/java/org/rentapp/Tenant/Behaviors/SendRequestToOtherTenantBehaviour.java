package org.rentapp.Tenant.Behaviors;

import java.util.*;
import java.util.stream.*;

import jade.core.*;
import jade.core.behaviours.*;
import jade.domain.*;
import jade.domain.FIPAAgentManagement.*;
import jade.lang.acl.*;

public class SendRequestToOtherTenantBehaviour extends CyclicBehaviour {
    public void action() {
        DFAgentDescription template = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType("tenant");
        template.addServices(sd);
        List<AID> otherTenants = new ArrayList<AID>();
        try {
            DFAgentDescription[] result = DFService.search(myAgent, template);
            otherTenants = Stream.of(result)
                    .map(t -> t.getName())
                    .collect(Collectors.toList());
        } catch (FIPAException fe) {
            fe.printStackTrace();
            done();
        }
        if (otherTenants.size() < 1)
            done();

        AID firstTenant = (AID) otherTenants.toArray()[0];
        System.out.println("Sending My Agent: " + myAgent.getName() + ". Sending to: " + firstTenant.getName());
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID(firstTenant.getLocalName(), AID.ISLOCALNAME));
        msg.setLanguage("x");
        msg.setOntology("xx");
        msg.setContent("Hej, chcesz być ze mną w grupieeeeeeee?");
        myAgent.send(msg);
    }
}
