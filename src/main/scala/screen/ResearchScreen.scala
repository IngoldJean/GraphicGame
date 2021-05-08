package screen

import api.model.{BestMatches, SymbolSearch}
import api.symbol.SearchSymbol
import common.ScreenManager
import io.circe.parser
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.control.{Button, TextField}
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.text.Text
import positions.model.Position
import positions.writer.PositionsWriter

import scala.util.Try

class ResearchScreen {

  var rowCurrentNumber = 1

  def tryToInt( s: String ): Option[Int] = Try(s.toInt).toOption

  def createResearchResultRow(root: GridPane): BestMatches => Unit = {
    matche =>

      val symbol = new Text(matche.symbol)
      val name = new Text(matche.name)
      val region = new Text(matche.region)
      val currency = new Text(matche.currency)
      val buttonAdd = new Button("Add")
      val numberStock = new TextField("0")

      symbol.setFill(Color.ALICEBLUE)
      name.setFill(Color.ALICEBLUE)
      region.setFill(Color.ALICEBLUE)
      currency.setFill(Color.ALICEBLUE)

      val eventAddStock = new EventHandler[ActionEvent]() {
        override def handle(e: ActionEvent): Unit = {

          tryToInt(numberStock.getCharacters.toString) match {
            case None =>
            case _ => val position = Position(matche.symbol, numberStock.getCharacters.toString.toInt)
              PositionsWriter.writePositions(position, "/Users/Jean/IdeaProjects/GraphicGame/src/main/resources/data.txt")
          }
        }
      }

      buttonAdd.setOnAction(eventAddStock)

      root.addRow(rowCurrentNumber, symbol, name, region, currency, numberStock, buttonAdd)
      rowCurrentNumber += 1
  }

  def drawResearchScreen(root:GridPane): Unit = {

    val researchInput = new TextField()

    val submitButton = new Button("Search")

    val eventResearch = new EventHandler[ActionEvent]() {
      override def handle(e: ActionEvent): Unit = {
        ScreenManager.removeAllChildren(root)
        val inputUser = researchInput.getCharacters
        val searchSymbol = new SearchSymbol
        val result: SymbolSearch = parser.decode[SymbolSearch](searchSymbol.searchForASymbol(inputUser.toString)).right.get
        val bestMatchList: Seq[BestMatches] = result.bestMatches
        bestMatchList.foreach(createResearchResultRow(root))
      }
    }

    submitButton.setOnAction(eventResearch)

    root.add(researchInput, 0, 0)
    root.add(submitButton, 1, 0)
  }
}
