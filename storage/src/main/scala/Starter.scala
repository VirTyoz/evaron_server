import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import storagesrvice.StorageService

object Starter extends App {
  val config = ConfigFactory.load()
  val actorSystem = ActorSystem("server")
  val actorTasks    = actorSystem.actorOf(Props[StorageService],    "storage")
}