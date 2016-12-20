/**
  * Created by nyanta on 24.04.16.
  */
import collection.mutable.Stack
import org.scalatest._
import tcpfront.Session
import packet._
import org.apache.thrift.protocol.TBinaryProtocol
import org.apache.thrift.transport.TIOStreamTransport
import java.io.ByteArrayOutputStream
import java.io.ByteArrayInputStream
import java.nio.ByteBuffer
import akka.util.ByteString



class Session_receiveDataTest extends FlatSpec with Matchers{

  "A Stack" should "pop values in last-in-first-out order" in {

    val stack = new Stack[Int]
    stack.push(1)
    stack.push(2)
    stack.pop() should be (21)
    stack.pop() should be (3)
  }

  it should "throw NoSuchElementException if an empty stack is popped" in {
    val emptyStack = new Stack[Int]
    a [NoSuchElementException] should be thrownBy {
      emptyStack.pop()
    }
  }
}
