import akka.actor.Cancellable

import scala.math._


object Test {
  private var scheduler: Cancellable = _


  def main(args: Array[String]) {
    var a: Double = 0

    var r: Double = 220
    var w: Double = 500
    var h: Double = 400
    var x: Double = w/2 + cos(a) * r
    var y: Double = h/2 + sin(a) * r

    println("x" + x)
    println("y" + y)
      println("a" + a)
      a = a + 0.02;
  }

}