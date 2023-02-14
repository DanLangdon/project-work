package destination

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DestinationSpec extends AnyFlatSpec with Matchers {
  "Save" should "save file to the output folder" in {
    FileDestination.save("This is a cleaned source") shouldEqual None
  }

  "Transform" should "save file to the output folder" in {
    FileDestination.transform("This is a transformed source") shouldEqual "This is a transformed source"
  }
}
