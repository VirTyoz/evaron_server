package webfront

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import webfront.AkkaNetServerWeb.idCounter

/**
  * Created by nyanta on 08.05.17.
  */
class Session(val id: Long) extends Actor with ActorLogging{
  import  Session._

  var outgoing: ActorRef = null
  val taskService = context.actorSelection("akka.tcp://server@127.0.0.1:2556/user/task")

  override def preStart() {
    // initialization code

    //scheduler = context.system.scheduler.schedule(period, period, self, Heartbeat)
    log.info("Session start: {}", toString)
  }

  override def receive = {
    case Connected(outgoing) => connected(outgoing)
    case Recive(text) =>
    case Send(text) => outgoing ! sendDataMessage(text)
  }

  override def postStop() {
    // clean up resources
    //scheduler.cancel()

    log.info("Session stop: {}", toString)
  }

  def receiveDataMessage(): Unit ={

  }

  def sendDataMessage(): Unit ={

  }

  def connected(outgoing: ActorRef) {
    //chatRoom ! ChatRoom.Join
    //idCounter += 1
    //outgoing ! OutgoingMessage(idCounter.toString)
    this.outgoing = outgoing

  }

  def Closed() {
    context stop self
  }

}

object Session {

  def props(id: Long) = Props(new Session(id))
  case class Connected(outgoing: ActorRef)
  case class Recive(text: String)
  case class Send(text: String)

}
