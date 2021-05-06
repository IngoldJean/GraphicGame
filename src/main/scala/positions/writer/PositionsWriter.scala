package positions.writer

import positions.model.Position

import java.io.FileWriter

object PositionsWriter {

  private def using[A <: {def close() : Unit}, B](param: A)(f: A => B): B =
    try f(param) finally param.close()

  def writePositions(position: Position, fileName: String): Unit = {
    using(new FileWriter(fileName, true)) {
      fileWriter =>
        fileWriter.write(position.name + "," + position.numberOfStock)
        fileWriter.close()
    }
  }
}
