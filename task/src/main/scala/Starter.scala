import java.io.File

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import taskservice.TaskService

object Starter extends App {

  val config = ConfigFactory.load()
  //val myConfigFile = new File("C:\Users\p.galonza\Documents\evaron.conf")
  //val fileConfig = ConfigFactory.parseFile(myConfigFile).getConfig("akka")
  //val config = ConfigFactory.load(fileConfig)
  val actorSystem = ActorSystem("server")
  val actorTasks    = actorSystem.actorOf(Props[TaskService],    "task")
}