/**
  * Created by p.galonza on 04.08.2016.
  */
package tcpfront

import java.net.InetSocketAddress

import akka.actor.{Actor, ActorLogging, Props}
import akka.io.{IO, Tcp}
import akka.io.Tcp._


class AkkaNetServerTCP(address: String, port: Int) extends Actor with ActorLogging {
  var idCounter = 0L //счетчик подключившихся

  override def preStart() {
    import context.system
    log.info("Starting tcp net server")


    //val opts = List(SO.KeepAlive(on = true), SO.TcpNoDelay(on = true))
    //IO(Tcp) ! Bind(self, new InetSocketAddress(address, port), options = opts)
    IO(Tcp) ! Bind(self, new InetSocketAddress(address, port))
  }

  def receive = {
    case b@Bound(localAddress) =>
    // do some logging or setup ...

    case CommandFailed(_: Bind) =>
      log.info("Command failed tcp server")
      context stop self

    case c@Connected(remote, local) =>
      log.info("New incoming tcp connection on server")
      createSession(remote, local) //создание сессии
  }

  // ----- handles -----
  def createSession(remote: InetSocketAddress, local: InetSocketAddress) {

    idCounter += 1
    //val sessionProps = Session.props(idCounter, sender, remote, local)
    //val session = context.actorOf(sessionProps, remote.toString.replace("/", ""))
      //заполнение данных
    val session = context.actorOf(Session.props(idCounter, sender, remote, local),remote.toString.replace("/", ""))

      //регистрация сессии пользователя
    sender ! Register(session)
  }
}

object AkkaNetServerTCP {
    // конструктор
  def props(address: String, port: Int) = Props(new AkkaNetServerTCP(address, port))
}