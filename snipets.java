private static final String SetPreferences = "SetPreferences";

private static final String InitGroupSearch = "InitGroupSearch";

private static final String ConsiderGroup = "ConsiderGroup";
private static final int GROUP_CONFIRMED = 1;
private static final int GROUP_DENIED = 0;

private static final String ConsiderOffer = "ConsiderOffer";


  // public class CancelAppointmentBehaviour extends CyclicBehaviour {

//   private MessageTemplate mt=MessageTemplate.MatchPerformative(ACLMessage.CANCEL);
//   private ACLMessage cancel; 
//   private MeetingSchedulerAgent myAgent;

//   CancelAppointmentBehaviour(MeetingSchedulerAgent a) {
//     super(a);
//     myAgent = a;
//   }

//   public void action(){
//     cancel = myAgent.receive(mt);
//     if (cancel == null) {
//       block();
//       return;
//     }
//     //System.err.println("CancelAppointmentBehaviour: received "+cancel.toString());
//     try {
//       Appointment app = myAgent.extractAppointment(cancel);
//       if (app.getInviter().equals(myAgent.getAID())) 
// 	// I called the appointment and I have to inform other agents of that
// 	myAgent.cancelAppointment(app.getFixedDate());
//       else
// 	myAgent.removeMyAppointment(app);
//     }catch (FIPAException e) {
//       e.printStackTrace();
//     }
//   }
// }



