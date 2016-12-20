/**
  * Created by nyanta on 18.09.16.
  */
import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import org.scalatest.FlatSpec
import tcpfront.AkkaNetServerTCP

class TestTCP extends FlatSpec {
  val actorSystem = ActorSystem("server")
  val actorNet = actorSystem.actorOf(AkkaNetServerTCP.props("127.0.0.1", 3132), "front")
}

