package webfront

import akka.NotUsed
import akka.actor.{ActorSystem, _}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.ws.{BinaryMessage, Message, TextMessage}
import akka.http.scaladsl.server.Directives.{get, handleWebSocketMessages, path, _}
import akka.stream.{ActorMaterializer, _}
import akka.stream.scaladsl.{Flow, Source, _}
import akka.util.ByteString

/**
  * Created by nyanta on 16.03.17.
  */
object AkkaNetServerWeb{

  implicit val actorSystem = ActorSystem("server")
  implicit val materializer = ActorMaterializer()

  var idCounter = 0L


  def createSession(): Flow[Message, Message, Any] = {
    idCounter += 1
    val session = actorSystem.actorOf(Session.props(idCounter))
    println("session", session)

    val incomingMessages: Sink[Message, Any] =
      Flow[Message].map {
        // transform websocket message to domain message
        //case TextMessage.Strict(text) => Session.Recive(text)
        case TextMessage.Strict(text) => Session.Recive(text)
      }.to(Sink.actorRef[Session.Recive](session, PoisonPill))

    val outgoingMessages: Source[Message, Any] =
      Source.actorRef[Session.Send](10, OverflowStrategy.fail)
        .mapMaterializedValue { outActor =>
          // give the user actor a way to send messages out
          session ! Session.Connected(outActor)
          NotUsed
        }.map(
        // transform domain message to web socket message
        (outMsg: Session.Send) => TextMessage(outMsg.text))

    // then combine both to a flow

    Flow.fromSinkAndSource(incomingMessages, outgoingMessages)
  }

  val route =
    path("ws") {
      get {
        handleWebSocketMessages(createSession)
        //handleWebSocketMessages()
      }
    }

  val binding = Http().bindAndHandle(route, "127.0.0.1", 8080)
}