package screen

import api.model.FullTimeSerieJson
import api.timeserie.ReadTimeSerie
import common.DateManager
import io.circe.parser
import javafx.collections.ObservableList
import javafx.scene.Node
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.text.Text
import positions.model.Position
import positions.reader.PositionsReader

import scala.io.Source

class DateScreen {

  var rowCurrentNumber = 1

  def readDataFile: String => Seq[String] = {
    fileName =>
      val textFromFile = Source.fromFile(fileName)
      val lines = for (line <- textFromFile.getLines())
        yield line

      lines.toSeq
  }

  def createHeader(root: GridPane): Unit = {
    val nameOfThePositionHeader = new Text("Position name")
    val numberOfStockHeader = new Text("Number of unit")
    val closeValueStockHeader = new Text("Value at close")
    val sumOfValueHeader = new Text("Total Value")
    nameOfThePositionHeader.setFill(Color.ALICEBLUE)
    numberOfStockHeader.setFill(Color.ALICEBLUE)
    closeValueStockHeader.setFill(Color.ALICEBLUE)
    sumOfValueHeader.setFill(Color.ALICEBLUE)
    root.addRow(rowCurrentNumber, nameOfThePositionHeader, numberOfStockHeader, closeValueStockHeader, sumOfValueHeader)
    rowCurrentNumber += 1
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

      root.addRow(rowCurrentNumber, nameOfThePosition, numberOfStock, closeValueStock, sumOfValue)
      rowCurrentNumber += 1
  }

  def getTotals(total1: Int, total2: Int, total3: Int, data: ObservableList[Node]): (Int, Int, Int) = {
    data.remove(0, 4)
    println("tata")
    data.size() match {
      case 0 => (total1,total2,total3)
      case _ =>
        getTotals(total1 + data.get(1).toString.toInt, total2 + data.get(2).toString.toInt, total3 + data.get(3).toString.toInt, data)
    }
  }

  def createTotal(root: GridPane): Unit = {
    val total = new Text("Total")
    val totalPositions = new Text("0")
    val totalUnitPrice = new Text("0")
    val totalValue = new Text("0")
    total.setFill(Color.ALICEBLUE)
    totalPositions.setFill(Color.ALICEBLUE)
    totalUnitPrice.setFill(Color.ALICEBLUE)
    totalValue.setFill(Color.ALICEBLUE)

    val childrens: ObservableList[Node] = root.getChildren

    val allTotalSums = getTotals(0,0,0, childrens)
    totalPositions.setText(allTotalSums._1.toString)
    totalUnitPrice.setText(allTotalSums._2.toString)
    totalValue.setText(allTotalSums._3.toString)

    println("tata")
    root.addRow(rowCurrentNumber, total, totalPositions, totalUnitPrice, totalValue)
  }

  def drawDateScreen(root:GridPane): Unit = {

    val dateText = new Text(DateManager.getTheDateString)
    dateText.setFill(Color.ALICEBLUE)

    createHeader(root)

    val lines = PositionsReader.readPositionsFromFile("/Users/Jean/IdeaProjects/GraphicGame/src/main/resources/data.txt")

    lines.foreach(createStockRow(root))
    createTotal(root)

    root.add(dateText,0,0, 4, 1)
  }
}
