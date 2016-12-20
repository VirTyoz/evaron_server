/**
  * Created by nyanta on 03.05.16.
  */
package authservice

import java.io.ByteArrayOutputStream

import akka.actor.{Actor, ActorLogging}
import core.Case._
import core._
import org.apache.thrift.protocol.TBinaryProtocol
import org.apache.thrift.transport.TIOStreamTransport
import packet.{LoginFail, LoginOk}



class AuthService extends Actor with ActorLogging{

  val chatService = context.actorSelection("akka.tcp://server@127.0.0.1:2559/user/chat")  //сервис чата
  val storageService = context.actorSelection("akka.tcp://server@127.0.0.1:2558/user/storage") //сервис базы данных

  override def preStart(){
    log.info("Starting auth service")
  }

  override def receive = {
    case task: Authenticate => handleAuth(task) //авторизация пользователя
    case task: SomePlayer => handleAuthenticated(task) //пользователь авторизирован
    case task: AuthenticatedFailed => handleFailed(task) //ошибка авторизации
    case _ => log.info("unknown message")
  }

  override def postStop() {
    // clean up resources
    log.info("Stoping auth service")
  }

    //авторизация
  def handleAuth(task: Authenticate) = {
      //передача лагина пароля в сервис базы данных для проверки
    storageService ! Case.GetPlayerByName(task.session, task.comm)
  }

    //автоизация успешна
  def handleAuthenticated(task: SomePlayer) = {
    val loginOk = LoginOk()   //пустой пакет
      //упаковка данных в пакет
    val bytes = {
      val out = new ByteArrayOutputStream()
      loginOk.write(new TBinaryProtocol(new TIOStreamTransport(out)))
      out.toByteArray
  }
      //передача в таск
    task.session ! Case.Send(Cmd.LoginOk, bytes)
    //gameService ! task
      //передача данных в чат сервис
    chatService ! task

  }
    //ошибка авторизации
  def handleFailed(task: AuthenticatedFailed) = {
       //заполнение покета данными
    val loginFail = LoginFail(error = task.error)
      //упаковка пакета
    val bytes = {
      val out = new ByteArrayOutputStream()
      loginFail.write(new TBinaryProtocol(new TIOStreamTransport(out)))
      out.toByteArray
    }
      //передача в таск
    task.session ! Case.Send(Cmd.LoginFail, bytes)
  }
}




object AuthService {

}