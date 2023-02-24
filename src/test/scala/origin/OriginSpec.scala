package origin

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class OriginSpec extends AnyFlatSpec with Matchers {
  "Extract" should "take a file path and extract the file" in {
    FileOrigin.extract("src/resources/input/test1.txt")  shouldEqual "test success"
  }

  "Clean" should "take an extracted file and remove unwanted characters" in {
    FileOrigin.clean("This is: a test;;;") shouldEqual "this is a test"
  }

  "FileOrigin2" should "take a file path and extract the file" in {
    StringOrigin(_ => "fish;;").extractAndClean("src/resources/input/test1.txt") shouldEqual "fish"
  }
}
