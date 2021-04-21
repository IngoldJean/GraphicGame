package screen

import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.text.{Font, FontPosture, FontWeight, Text}

import java.io.File
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

    val lines = this.readDataFile.apply("/Users/Jean/IdeaProjects/GraphicGame/src/main/resources/data.txt")

    var rowIndex = 1
    for(line <- lines){
      val text = new Text(line)
      text.setFill(Color.ALICEBLUE)
      root.add(text, 0, rowIndex)
      rowIndex += 1
    }
    dateText.setFill(Color.ALICEBLUE)


    root.add(dateText,0,0)
  }
}
