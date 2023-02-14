package origin

import scala.io.Source

trait Origin[A] {

  def extract(source: A): A

  def clean(extractedSource: A): A

  def extractAndClean(source: A): A =
    (extract _ andThen clean)(source)

}

//trait Monad[F[_], A] extends Functor[F, A] {
//  def pure(a: A): F[A] = Monad(a)
//  def flatten(a: F[F[A]]): F[A]
//  def flatMap[B](f : A => F[B]): F[B] = flatten(map(f))
//}
//
//object Monad {
//
//  def apply[F[_], A](a: A): F[A] = ???
//  def flatMap[F[_], A, B](fa: F[A])(f : A => F[B]): F[B] = ???
//}
//
//trait Functor[F[_], A] {
//
//  def map[B](f : A => B): F[B]
//}
//
//object Functor {
//
//  def map[F[_], A, B](fa: F[A])(f : A => B): F[B] = ???
//}

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


