/**
  * Created by nyanta on 07.05.16.
  */
package chatservice

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}
import akka.actor._
import core.Case.{SendToAll, SomePlayer}
import core.{Case, Cmd}
import domain._
import org.apache.thrift.protocol.TBinaryProtocol
import org.apache.thrift.transport.TIOStreamTransport
import packet._

import scala.collection.mutable

class ChatService extends Actor with ActorLogging{

  val players: mutable.HashMap[Player, ActorRef] = mutable.HashMap.empty[Player, ActorRef]   //массив с ключем игрок
  val sessions: mutable.HashMap[ActorRef, Player] = mutable.HashMap.empty[ActorRef, Player]  //массив с ключем сессия

  override def preStart() {
    log.info("Starting chat service")
  }


  override def receive = {
    case task: SomePlayer => handleAuthenticated(task)  //авторизация
    case task: SendToAll => handleSendToAll(task)  //рассылка всем
    case _ => log.info("unknown message")
  }


  override def postStop() {
    // clean up resources
    log.info("Stoping chat service")
  }

     //авторизация пользователя и добавление данных в массивы
  def handleAuthenticated(task: SomePlayer) {
    players.put(task.player,task.session)
    sessions.put(task.session, task.player)
  }

    //разослать всем
  def handleSendToAll(task: SendToAll) ={
      //распаковка пакета
    val chat = {
      val stream = new ByteArrayInputStream(task.comm)
      ChatToServer.decode(new TBinaryProtocol(new TIOStreamTransport(stream)))
    }

      //перемещение данных с добавлением имени игрока
    //val chatToClient = ChatToClient(0,sessions.get(task.session).get.name+": "+chat.text+"\n")
    val chatToClient = ChatToClient(0,sessions(task.session).name+": "+chat.text+"\n")
    val bytes = {
      val out = new ByteArrayOutputStream()
      chatToClient.write(new TBinaryProtocol(new TIOStreamTransport(out)))
      out.toByteArray
    }
    players.values.map(s=>s ! Case.Send(Cmd.ChatToClient, bytes))
  }

}

object ChatService{

}