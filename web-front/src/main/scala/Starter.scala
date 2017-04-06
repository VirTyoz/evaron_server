import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import webfront.AkkaNetServerWeb

object Starter extends App{
  val actorSystem = ActorSystem("server")
  implicit val flowMaterializer = ActorMaterializer()
  val actorNet = actorSystem.actorOf(AkkaNetServerWeb.props("127.0.0.1", 3132), "front-web")


}
