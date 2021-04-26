package screen

import api.model.FullTimeSerieJson
import api.timeserie.ReadTimeSerie
import common.DateManager
import io.circe.parser
import javafx.scene.Node
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.text.Text
import positions.model.Position
import positions.reader.PositionsReader

import scala.io.Source

class DateScreen {

  var number = 1

  def readDataFile: String => Seq[String] = {
    fileName =>
      val textFromFile = Source.fromFile(fileName)
      val lines = for (line <- textFromFile.getLines())
        yield line

      lines.toSeq
  }

  def createStockRow(root: GridPane): Position => Unit = {
    stockLine =>
      val nameOfThePosition = new Text(stockLine.name)
      val numberOfStock = new Text(stockLine.numberOfStock.toString)
      nameOfThePosition.setFill(Color.ALICEBLUE)
      numberOfStock.setFill(Color.ALICEBLUE)

      val (closeValueStock, sumOfValue) = parser.decode[FullTimeSerieJson](ReadTimeSerie.timeSerieDaily(stockLine.name)) match {
        case Right(a) => val closeValue = a.datas.todayValue.close
          (new Text(closeValue.toString), new Text((closeValue * stockLine.numberOfStock).toString))
        case Left(_) => (new Text("Unable to read API for that value"), new Text("Unable to read API for that value"))
      }
      closeValueStock.setFill(Color.ALICEBLUE)
      sumOfValue.setFill(Color.ALICEBLUE)

      root.addRow(number, nameOfThePosition, numberOfStock, closeValueStock, sumOfValue)
      number += 1
  }

  def drawDateScreen(root:GridPane): Unit = {
    val dateText = new Text(DateManager.getTheDateString)
    dateText.setFill(Color.ALICEBLUE)

    val lines = PositionsReader.readPositionsFromFile("/Users/Jean/IdeaProjects/GraphicGame/src/main/resources/data.txt")

    lines.foreach(createStockRow(root))

    root.add(dateText,0,0)
  }
}
