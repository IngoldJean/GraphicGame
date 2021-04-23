package screen

import api.model.RootInterface
import api.timeserie.ReadTimeSerie
import io.circe.parser
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.text.Text
import positions.reader.PositionsReader

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.io.Source

class DateScreen {
  def getTheDateString: String = {
    val dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
    dtf.format(LocalDateTime.now())
  }

  def readDataFile: String => Seq[String] = {
    fileName =>
      val textFromFile = Source.fromFile(fileName)
      val lines = for (line <- textFromFile.getLines())
        yield line

      lines.toSeq
  }

  def drawDateScreen(root:GridPane): Unit = {
    val dateText = new Text(this.getTheDateString)

    val lines = PositionsReader.readPositionsFromFile("/Users/Jean/IdeaProjects/GraphicGame/src/main/resources/data.txt")

    var rowIndex = 1
    for(line <- lines){
      val nameOfThePosition = new Text(line.name)
      val numberOfStock = new Text(line.numberOfStock.toString)
      nameOfThePosition.setFill(Color.ALICEBLUE)
      numberOfStock.setFill(Color.ALICEBLUE)

      root.add(nameOfThePosition, 0, rowIndex)
      root.add(numberOfStock, 1, rowIndex)
      rowIndex += 1
    }
    dateText.setFill(Color.ALICEBLUE)

    root.add(dateText,0,0)
  }
}
