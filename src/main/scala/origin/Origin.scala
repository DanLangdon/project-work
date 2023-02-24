package origin

import scala.io.Source

trait Origin[A, B] {

  def extract(source: A): B

  def clean(extractedSource: B): B

  // def extractAndClean(source: A): A =
  //  (extract _ andThen clean)(source)

}

final class Path(val p: String) extends AnyVal

object Path {
  def fromString(p: String): Option[Path] = {
    if (p.startsWith("/")) Option(new Path(p))
    else None
  }
}

final case class StringOrigin(loader: String => String) extends Origin[String, String] {

  def extract(source: String): String = loader(source)

  def clean(extractedSource: String): String = {
    extractedSource.toLowerCase()
      .replaceAll(";|:", "")
  }
}

final case class ListOrigin(loader: String => List[Int]) extends Origin[String, List[Int]] {

  def extract(source: String): List[Int] = loader(source)

  def clean(extractedSource: List[Int]): List[Int] = {

    if (extractedSource.nonEmpty) {

      val result = extractedSource.map(i => i - 1)
      result
    } else extractedSource
  }
}