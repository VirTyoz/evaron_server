/**
  * Created by nyanta on 19.04.16.
  */
package taskservice

import akka.actor.{Actor, ActorLogging}
import core.Case.CommandTask
import core.{Case, Cmd}




class TaskService extends Actor with ActorLogging{

  //val authService = context.actorSelection("akka://server/user/auth")
  //val gameService = context.actorSelection("akka://server/user/game")
  //val chatService = context.actorSelection("akka://server/user/chat")
  val authService = context.actorSelection("akka.tcp://server@127.0.0.1:2557/user/auth")
  val chatService = context.actorSelection("akka.tcp://server@127.0.0.1:2559/user/chat")


  override def preStart() {
    log.info("Starting task service")
  }


  override def receive = {
      //получение ответа от сервиса
    case task: CommandTask => handlePacket(task)

    case _ => log.info("unknown message")
  }


  override def postStop() {
    // clean up resources
    log.info("Stoping task service")
  }


  def handlePacket(task: CommandTask) = {
    log.info("handle Ok {}", toString)
    log.info("id"+task.id)

    task.id.toInt match {
        //авторизация
      case Cmd.RequestAuthLogin.code =>  authService ! Case.Authenticate(task.session, task.comm)
        //чат
      case Cmd.ChatToServer.code     => chatService ! Case.SendToAll(task.session, task.comm)
      case _ => log.info("Crazy message")
    }
  }
}



object TaskService {

}