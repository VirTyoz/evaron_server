/**
  * Created by p.galonza on 04.08.2016.
  */
package spaceservice

import akka.actor.{Actor, ActorLogging, ActorRef}
import core.Case
import core.Case.{SomeSystemId}
import domain.Player

import scala.collection.mutable

class SpaceService extends Actor with ActorLogging{
  val storageService = context.actorSelection("akka.tcp://server@127.0.0.1:2558/user/storage")

  val sessions: mutable.HashMap[ActorRef, Player] = mutable.HashMap.empty[ActorRef, Player]
  val systems: mutable.HashMap[Long, ActorRef] = mutable.HashMap.empty[Long, ActorRef]


  override def preStart() {
    log.info("Starting Space service")
    storageService ! Case.GetSystemId()

  }


  override def receive = {
    case _ => log.info("unknown message")
    case cmdTask: SomeSystemId   => createSystem(cmdTask)
  }


  override def postStop() {
    // clean up resources
    log.info("Stoping Space service")
  }

  def createSystem(task: SomeSystemId) = {

    for (systemId <- task.comm) {
      val room = context.actorOf(System.props(systemId))
      systems.put(systemId, room)
      room
    }
  }
}



object SpaceService{

}