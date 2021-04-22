package common

import positions.model.Position

import scala.io.Source

object ApiKey {
  val API_KEY_FILE_PATH = "/Users/Jean/IdeaProjects/GraphicGame/src/main/resources/apiKey"

  private def using[A <: {def close() : Unit}, B](param: A)(f: A => B): B =
    try f(param) finally param.close()

  def readApiKeyFromFile: String = {
      using(Source.fromFile(API_KEY_FILE_PATH)) {
        textFromFile =>
          textFromFile.getLines().toString()
      }
  }
}
