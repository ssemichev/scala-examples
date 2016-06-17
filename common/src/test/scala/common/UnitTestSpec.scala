package common

import org.scalamock.scalatest.MockFactory
import org.scalatest._

abstract class UnitTestSpec extends FlatSpec with Matchers with OptionValues with BeforeAndAfterAll
    with Inside with Inspectors with MockFactory {
  def sample(a: Any): String = a.toString
  def makeFloating(a: Int): Double = a.toDouble

  protected object Index {
    private[this] var _stream = Stream.from(0)

    def next: Int = {
      val i = _stream.head
      _stream = _stream.tail
      i
    }
  }

  def source: String = "unit_test"
}