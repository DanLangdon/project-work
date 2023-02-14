package hello

import origin.{FileOrigin, Origin}
import destination.{Destination, FileDestination}

class Hello {

  object Hello {
    val origin = FileOrigin
    val destination = FileDestination



    val input = origin.extract("/resources/input/input.txt")
    val cleaned =  origin.clean(input)
    val transformed = destination.transform(cleaned)
    destination.save(transformed)
  }
}
