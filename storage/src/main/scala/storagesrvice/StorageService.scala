/**
  * Created by nyanta on 03.05.16.
  */
package storagesrvice

import java.io.ByteArrayInputStream

import akka.actor.{Actor, ActorLogging}
import core.Case
import core.Case.{GetPlanetInfoById, GetPlayerByName, GetSystemId}
import domain.Player
import org.apache.thrift.protocol.TBinaryProtocol
import org.apache.thrift.transport.TIOStreamTransport
import packet._
import scalikejdbc._
import scalikejdbc.config._


class StorageService extends Actor with ActorLogging{

  // ----- actor -----
  override def preStart() {
    log.info("Starting storage service")
  }

  override def receive = {
    case cmdTask: GetPlayerByName => handleAuth(cmdTask)
    case cmdTask: GetPlanetInfoById   => handleGetPlanetInfoById(cmdTask)
    case cmdTask: GetSystemId     => handleGetSystemId(cmdTask)

    case _ => log.info("unknown message")
  }

  override def postStop() {
    // clean up resources
    log.info("Stoping storage service")
  }


  def handleAuth(task: GetPlayerByName) = {
    //Подключение баз данных world auth users
    DBs.setupAll()
    //DBs.setup('modx)
    //Декодирование пакета с логином и паролем
    val logPas = {
      val stream = new ByteArrayInputStream(task.comm)
      RequestAuthLogin.decode(new TBinaryProtocol(new TIOStreamTransport(stream)))
    }
    val auth: Option[Long]  = NamedDB('auth) readOnly { implicit session =>
      sql"SELECT * FROM account WHERE userlogin = ${logPas.login} AND md5_pass_hash = ${logPas.password}".map(rs => rs.long("guid")).single.apply()
    }

    //Проверка на наличе записи в базе
    if(auth.nonEmpty) {
      val online: Option[Int]  = NamedDB('auth) readOnly { implicit session =>
        sql"SELECT * FROM account WHERE id = $auth".map(rs => rs.int("online")).single.apply()
      }
      if (online.get != 1) {
        val userBan: Option[Int] = NamedDB('auth) readOnly { implicit session =>
          sql"SELECT * FROM account_banned WHERE id = $auth".map(rs => rs.int("active")).single.apply()
        }
        //Проверка аккаунта на блокировку
        if (userBan.get != 1) {
          val userData: List[Map[String, Any]] = NamedDB('users) readOnly { implicit session =>
            sql"SELECT * FROM users WHERE id = $auth".map(_.toMap).list.apply()
          }

          //Создание обекта Player с id игрока и имени
          //sender ! Case.SomePlayer(task.session, task.comm, new Player(userData.lift(0).get("guid").asInstanceOf[Long], userData.lift(0).get("name").asInstanceOf[String]))
          sender ! Case.SomePlayer(task.session,  Player(userData.headOption.get("guid").asInstanceOf[Long], userData.headOption.get("name").asInstanceOf[String]))
        } else {
          //Пользователь заблокирован
          sender ! Case.AuthenticatedFailed(task.session, task.comm, -1)
        }
      } else {
        //Пользователь онлайн
        sender ! Case.AuthenticatedFailed(task.session, task.comm, 1)// Довавить номер
      }
    } else {
      //Учетной записи не существует
      sender ! Case.AuthenticatedFailed(task.session, task.comm, 1)
    }
  }

  def handleGetSystemId(task: GetSystemId) = {
    DBs.setupAll()
    val planetsId: List[Long] = NamedDB('world) readOnly { implicit session =>
      sql"SELECT * FROM systems".list.apply()
    }
    sender ! Case.SomeSystemId(planetsId)
  }

  def handleGetPlanetInfoById(task: GetPlanetInfoById) = {
    DBs.setupAll()
    val planetInfo: List[Map[String, Any]] = NamedDB('world) readOnly { implicit session =>
      sql"SELECT * FROM planets WHERE id = ${task.comm}".map(_.toMap).list.apply()
    }
    sender ! Case.SomePlanetInfo(planetInfo)

  }

}

object StorageService {
}

