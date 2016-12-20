package gmservice

import akka.actor.{Actor, ActorLogging, ActorRef}

class GMService extends Actor with ActorLogging{

  override def  preStart(){
    log.info("Starting auth service")
  }

  override def receive = {

    case _ => log.info("unknown message")
  }

  override def postStop() {
    // clean up resources
    log.info("Stoping auth service")
  }
}

object GMService{

}