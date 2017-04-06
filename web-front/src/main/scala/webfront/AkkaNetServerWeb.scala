package webfront

import akka.actor.{Actor, ActorLogging, Props}
import akka.http.scaladsl.Http

import scala.io.StdIn

/**
  * Created by nyanta on 16.03.17.
  */
class AkkaNetServerWeb (address: String, port: Int) extends Actor with ActorLogging {

  override def preStart(): Unit = {


  }

  def receive = {


  }

}

object AkkaNetServerWeb {
  def props(address: String, port: Int) = Props(new AkkaNetServerWeb(address, port))
}
