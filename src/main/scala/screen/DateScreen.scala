package screen

import api.model.FullTimeSerieJson
import api.timeserie.ReadTimeSerie
import common.DateManager
import io.circe
import io.circe.parser
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.text.Text
import positions.reader.PositionsReader

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.io.Source

class DateScreen {

  def readDataFile: String => Seq[String] = {
    fileName =>
      val textFromFile = Source.fromFile(fileName)
      val lines = for (line <- textFromFile.getLines())
        yield line

      lines.toSeq
  }

  def drawDateScreen(root:GridPane): Unit = {
    val dateText = new Text(DateManager.getTheDateString)
    dateText.setFill(Color.ALICEBLUE)

    val lines = PositionsReader.readPositionsFromFile("/Users/Jean/IdeaProjects/GraphicGame/src/main/resources/data.txt")

    var rowIndex = 1

    for(line <- lines){
      val nameOfThePosition = new Text(line.name)
      val numberOfStock = new Text(line.numberOfStock.toString)
      nameOfThePosition.setFill(Color.ALICEBLUE)
      numberOfStock.setFill(Color.ALICEBLUE)

      val closeValueStock = parser.decode[FullTimeSerieJson](ReadTimeSerie.timeSerieDaily(line.name)) match {
        case Right(a) => new Text(a.datas.todayValue.close.toString)
        case Left(a) => new Text("Unable to read API for that value")
      }

      closeValueStock.setFill(Color.ALICEBLUE)

      root.add(nameOfThePosition, 0, rowIndex)
      root.add(numberOfStock, 1, rowIndex)
      root.add(closeValueStock, 2, rowIndex)
      rowIndex += 1
    }

    root.add(dateText,0,0)
  }
}
