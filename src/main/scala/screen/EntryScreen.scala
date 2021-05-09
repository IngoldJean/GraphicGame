package screen

import common.ScreenManager
import javafx.event.{ActionEvent, EventHandler}
import javafx.geometry.{HPos, Insets, Pos}
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.text._
import javafx.stage.Stage

class EntryScreen extends javafx.application.Application{

  val width = 1000
  val height  = 600

  def drawEntryScreen(root:GridPane, welcomeText: Text): Unit = {

    val currentPositionsButton = new Button("See my positions")

    val addStockButton = new Button("Add value")


    val eventMyPositons = new EventHandler[ActionEvent]() {
      override def handle(e: ActionEvent): Unit = {
        ScreenManager.removeAllChildren(root)
        val dateScreen = new DateScreen
        dateScreen.drawDateScreen(root)
      }
    }

    val eventAddPositon = new EventHandler[ActionEvent]() {
      override def handle(e: ActionEvent): Unit = {
        ScreenManager.removeAllChildren(root)
        val researchScreen = new ResearchScreen
        researchScreen.drawResearchScreen(root)
      }
    }

    currentPositionsButton.setOnAction(eventMyPositons)
    addStockButton.setOnAction(eventAddPositon)

    root.setPadding(new Insets(10, 10, 10, 10))
    root.setVgap(5)
    root.setHgap(5)
    root.setAlignment(Pos.CENTER)
    root.setStyle("-fx-background-color: BLACK;")

    GridPane.setHalignment(welcomeText, HPos.CENTER)
    GridPane.setHalignment(currentPositionsButton, HPos.CENTER)
    GridPane.setHalignment(addStockButton, HPos.CENTER)

    root.add(welcomeText, 0, 0)
    root.add(currentPositionsButton, 0, 1)
    root.add(addStockButton, 0, 2)
  }

  override def start(primaryStage: Stage): Unit = {
    primaryStage.setTitle("Entry Screen");
    val gridPane = new GridPane
    val scene = new Scene(gridPane, width, height)
    primaryStage.setScene(scene)
    primaryStage.show()

    val welcomeText = new Text("Welcome to my graphical app")

    welcomeText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 20))
    welcomeText.setFill(Color.ALICEBLUE)

    drawEntryScreen(gridPane, welcomeText)
  }
}
