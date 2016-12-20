import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

object Starter extends App {

  val config = ConfigFactory.load()

  val actorSystem = ActorSystem("server")

  //val actorTasks    = actorSystem.actorOf(Props[AuthService],    "auth")
}