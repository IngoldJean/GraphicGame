package positions.reader

import positions.model.Position

import scala.io.Source

object PositionsReader {

  private def using[A <: {def close() : Unit}, B](param: A)(f: A => B): B =
    try f(param) finally param.close()

  def readPositionsFromFile: String => List[Position] = {
    fileName =>
      using(Source.fromFile(fileName)) {
        textFromFile =>
          try {
            val lines = textFromFile.getLines().map(text =>
              Position(text.split(",").apply(0), text.split(",").apply(1).toInt))
            lines.toList
          }
          catch {
            case e: Exception =>
              println("cannot read positions from file")
              List.empty[Position]
          }
      }
  }
}
