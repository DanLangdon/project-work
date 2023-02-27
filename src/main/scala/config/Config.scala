package config

import pureconfig._
import pureconfig.generic.auto._

class Config {

  sealed trait DataType

  case object StringType extends DataType

  case object IntegersType extends DataType

  case class Config(
                     input: String,
                     output: String,
                     dataType: DataType
                   )

  object Config {
    def configLoader(): ConfigReader.Result[Config] = ConfigSource.default.load[Config]

  }
}