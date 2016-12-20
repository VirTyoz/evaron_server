/**
  * Created by p.galonza on 04.08.2016.
  */
package core

sealed trait Msg { def code: Int}

case object Cmd{
  case object Ping                      extends Msg{val code =0}
  //case object Auth                    extends Msg{val code = 1}
  case object LoginFail                 extends Msg{val code = 2}
  case object LoginOk                   extends Msg{val code = 3}
  case object PlayOk                    extends Msg{val code = 4}
  case object RequestAuthLogin          extends Msg{val code = 5}
  case object RequestServerLogin        extends Msg{val code = 6}
  case object ChatToServer              extends Msg{val code = 7}
  case object ChatToClient              extends Msg{val code = 8}
  case object RequestPlanetsInfo        extends Msg{val code = 9}
  case object PlanetsInfo               extends Msg{val code = 10}
}
