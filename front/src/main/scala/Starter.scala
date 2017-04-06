import akka.actor.ActorSystem
import tcpfront._
object Starter extends App {
  //val config = ConfigFactory.load()
  val actorSystem = ActorSystem("server")
  val actorNet = actorSystem.actorOf(AkkaNetServerTCP.props("127.0.0.1", 3132), "front")
}