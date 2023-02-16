package origin

import scala.io.Source

trait Origin[A] {

  def extract(source: A): A

  def clean(extractedSource: A): A

  def extractAndClean(source: A): A =
    (extract _ andThen clean)(source)

}

final class Path(val p: String) extends AnyVal

object Path {
  def fromString(p: String): Option[Path] = {
    if (p.startsWith("/")) Option(new Path(p))
    else None
  }
}

case object FileOrigin extends Origin[String] {

  def extract(source: String): String = Source.fromFile(source).getLines.mkString

  def clean(extractedSource: String): String = {
    extractedSource.toLowerCase()
      .replaceAll(";|:", "")
  }
}

final case class FileOrigin2(loader: String => String) extends Origin[String] {

  def extract(source: String): String = loader(source)

  def clean(extractedSource: String): String = {
    extractedSource.toLowerCase()
      .replaceAll(";|:", "")
  }
}

// list of char and then fold


