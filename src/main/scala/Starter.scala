import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory

object Starter extends App {

  val config = ConfigFactory.load()
  // create the actor system and actors
  val actorSystem = ActorSystem("server",config.getConfig("myapp").withFallback(config))

  //val storageAuth   = actorSystem.actorOf(Props[StorageService], "storage")
  //val actorAuth     = actorSystem.actorOf(Props[AuthService],    "auth")
  //val actorTasks    = actorSystem.actorOf(Props[TaskService],    "task")
  //val actorGame   = actorSystem.actorOf(Props[GmService],      "game")
  //val chatService   = actorSystem.actorOf(Props[ChatService],    "chat")
 // val actorNet      = actorSystem.actorOf(AkkaNetServerTCP.props("127.0.0.1", 3132), "front")
}