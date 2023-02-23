package destination
import java.io.{File, FileWriter}
import scala.io.Source

trait Destination[A] {

  def save(cleanedSource : A)
  def transform(source : A): A
}

case object FileDestination extends Destination[String]{

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
    val fileContents = writeToFile(fileObject, cleanedSource)
  }
  def transform(source : String): String = {

    val transformedSource = source.toLowerCase()
    if(transformedSource.contains('!')) transformedSource.replace('!', '.') else transformedSource
  }
}

final case class FileDestination2(loader: String => String) extends Destination[String] {

  def save(cleanedSource: String) = loader(cleanedSource)

  def transform(source: String): String = ???
}
