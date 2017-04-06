/**
  * Created by p.galonza on 04.08.2016.
  */
package tcpfront

import java.net.InetSocketAddress

import akka.actor._
import akka.io.Tcp
import akka.util.ByteString
import core.Case.Send
import core.Msg



class Session(val id: Long,
              connect: ActorRef,
              remote: InetSocketAddress,
              local: InetSocketAddress)extends Actor with ActorLogging{
  import Tcp._
    //val taskService = context.actorSelection("akka://server/user/task")
  val taskService = context.actorSelection("akka.tcp://server@127.0.0.1:2556/user/task")



  private var scheduler: Cancellable = _

  // ----- actor -----
  override def preStart() {
    // initialization code

      //scheduler = context.system.scheduler.schedule(period, period, self, Heartbeat)
    log.info("Session start: {}", toString)
  }


  override def receive = {
    case data: ByteString => log.info("test")
      //получение данных от клиента
    case Received(data) => receiveData(data)
      //отправка клиенту
    case Send(cmd, data) => sendData(cmd, data)
      //получение тика
    //case Heartbeat => sendHeartbeat()
    case _: Tcp.ConnectionClosed => Closed()
    case _ => log.info("unknown message")
  }


  override def postStop() {
    // clean up resources
    //scheduler.cancel()

    log.info("Session stop: {}", toString)
  }


  def receiveData(data: ByteString): Unit = {
     /* //жисериализация пакета 1lvl
    val packet = {
      val stream = new ByteArrayInputStream(data.toArray)
      PacketMSG.decode(new TBinaryProtocol(new TIOStreamTransport(stream)))
    }
      //отправка даных в сервис задач
    taskService ! Case.CommandTask(self, packet.id, packet.data.array())
    //taskService ! TaskService.CommandTask(self,packet)*/
  }

  def sendData(cmd: Msg, data: Array[Byte]): Unit = {
     /* //Упаковка данных в пакет 2lvl
    val packet = PacketMSG(id = cmd.code.toShort, data = ByteBuffer.wrap(data))

    val bytes = {
      val out = new ByteArrayOutputStream()
      packet.write(new TBinaryProtocol(new TIOStreamTransport(out)))
      out.toByteArray
    }
    log.info("send to client{}"+"code"+cmd.code.toShort, toString)
    connect ! Write(ByteString(bytes))*/
  }


  def sendHeartbeat(): Unit = {
    log.info("Heartbeat")

  }


  def Closed() {
    context stop self
  }
}


object Session{
    //val period = 10.seconds
  def props(id: Long, connect: ActorRef,
            remote: InetSocketAddress, local: InetSocketAddress) = Props(
    new Session(id, connect, remote, local)
  )
  //case object Heartbeat
}



/*
    var init = Init(sessionId = 1, connectionId = 2)

    val bytes1 = {
      val out = new ByteArrayOutputStream()
      init.write(new TBinaryProtocol(new TIOStreamTransport(out)))
      out.toByteArray
    }

    val packet = PacketMSG(id = 1, data = ByteBuffer.wrap(bytes1))

    val bytes = {
      val out = new ByteArrayOutputStream()
      packet.write(new TBinaryProtocol(new TIOStreamTransport(out)))
      out.toByteArray
    }

    val data2 = {
      val stream = new ByteArrayInputStream(bytes)
      PacketMSG.decode(new TBinaryProtocol(new TIOStreamTransport(stream)))
    }

    val data3 = {
      val stream = new ByteArrayInputStream(data2.data.array())
      Init.decode(new TBinaryProtocol(new TIOStreamTransport(stream)))
    }

    log.info(data3.connectionId.toString)
 */