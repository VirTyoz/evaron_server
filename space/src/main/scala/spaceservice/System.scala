/**
  * Created by p.galonza on 04.08.2016.
  */
package spaceservice

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import core.Case
import core.Case.SomePlanetInfo

import scala.collection.mutable
import domain._

import scala.math._

class System(SystemId: Long) extends Actor with  ActorLogging{
  val storageService = context.actorSelection("akka.tcp://server@127.0.0.1:2558/user/storage")

  val players: mutable.HashMap[Player, ActorRef] = mutable.HashMap.empty[Player, ActorRef]
  val planets: mutable.HashMap[Long, Planet] = mutable.HashMap.empty[Long, Planet]


  override def preStart(){
    log.info("Session start: {}", toString)
    storageService ! Case.GetPlanetInfoById(self, SystemId)

  }

  override def receive = {
    case _ => log.info("unknown message")
    case cmdTask: SomePlanetInfo => createPlanet(cmdTask)
  }

  override def postStop() {

    log.info("Session stop: {}", toString)
  }

  def createPlanet(task: SomePlanetInfo) ={
    for(planetInfo <- task.comm){
      planets.put(planetInfo.get("id").asInstanceOf[Long], Planet(planetInfo.get("id").asInstanceOf[Long], planetInfo.get("name").asInstanceOf[String], planetInfo.get("ownerId").asInstanceOf[Long]))
    }
  }
}

object System{
  def props(SystemId: Long) = Props(new System(SystemId))

}

/*
  def planetMove(){
    var a: Double = 0

    var r: Double = 220
    var w: Double = 500
    var h: Double = 400
    var x: Double = w/2 + cos(a) * r
    var y: Double = h/2 + sin(a) * r

    println("x" + x)
    println("y" + y)
    println("a" + a)
    a = a + 0.02;
  }

 */