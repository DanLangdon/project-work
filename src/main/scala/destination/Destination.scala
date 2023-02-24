package destination
import java.io.{File, FileWriter}
import scala.io.Source

trait Destination[A] {

  def save(cleanedSource : A)
  def transform(source : A): A
}

case object StringDestination extends Destination[String]{

  def writeToFile(file: File, str: String): Unit = {
    val writer = new FileWriter(file)
    try {
      writer.append(str).append("\n")
    }
    finally {
      writer.close
    }
  }

  def save(cleanedSource : String) = {
    val fileObject = new File("/src/resources/output1.txt")
    writeToFile(fileObject, cleanedSource)
  }
  def transform(source : String): String = {

    val transformedSource = source.toLowerCase()
    if(transformedSource.contains('!')) transformedSource.replace('!', '.') else transformedSource
  }
}

final case class ListDestination(loader: List[Int] => String) extends Destination[List[Int]] {

  def save(cleanedSource: List[Int]) = loader(cleanedSource)

  def transform(source: List[Int]): List[Int] = {

    if (source.nonEmpty) {
      val result = source.map(i => i + 1)
      result
    } else source
  }
}
