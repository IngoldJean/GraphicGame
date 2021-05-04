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

class ResearchScreen {

  var rowCurrentNumber = 1

  def createResearchResultRow(root: GridPane): BestMatches => Unit = {
    matche =>
      val text = new Text(matche.symbol)
      text.setFill(Color.ALICEBLUE)
      root.addRow(rowCurrentNumber, text)
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
