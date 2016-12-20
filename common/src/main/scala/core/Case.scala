/**
  * Created by p.galonza on 04.08.2016.
  */
package core

import akka.actor.ActorRef
import domain.Player

object  Case {

  //case class CommandTask(session: ActorRef, comm: PacketMSG)
  case class CommandTask(session: ActorRef, id: Short, comm: Array[Byte])

  //case class Authenticate(session: ActorRef, comm: PacketMSG)
  case class Authenticate(session: ActorRef, comm: Array[Byte])
  //case class AuthenticatedFailed(session: ActorRef, comm: PacketMSG, error: Short)
  case class AuthenticatedFailed(session: ActorRef, comm: Array[Byte], error: Short)

  //case class SendToAll(session: ActorRef, comm: PacketMSG)
  case class SendToAll(session: ActorRef, comm: Array[Byte])
  //case class GetPlayerByName(session: ActorRef, comm: PacketMSG)
  case class GetPlayerByName(session: ActorRef, comm: Array[Byte])
  case class GetPlanetInfoById(session: ActorRef, comm: Long)
  case class GetSystemId()
  case class SomeSystemId(comm: List[Long])
  case class SomePlanetInfo(comm: List[Map[String, Any]])
  //case class SomePlayer(session: ActorRef, comm: PacketMSG, player: Player)
  //case class SomePlayer(session: ActorRef, comm: Array[Byte], player: Player)
  case class SomePlayer(session: ActorRef,  player: Player)

  case class Send(cmd: Msg, data: Array[Byte])
}