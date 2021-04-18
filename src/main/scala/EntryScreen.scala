import javafx.geometry.{HPos, Insets, Pos}
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.text._
import javafx.stage.Stage

class EntryScreen extends javafx.application.Application{

  val width = 500
  val height  = 300

  def drawEntryScreen(root:GridPane): Unit = {

    val welcomeText = new Text("Welcome to my graphical app")

    welcomeText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 20))
    welcomeText.setFill(Color.ALICEBLUE)

    val welcomeButton = new Button("Click to enter")

    root.setPadding(new Insets(10, 10, 10, 10))
    root.setVgap(5)
    root.setHgap(5)
    root.setAlignment(Pos.CENTER)
    root.setStyle("-fx-background-color: BLACK;")

    GridPane.setHalignment(welcomeText, HPos.CENTER)
    GridPane.setHalignment(welcomeButton, HPos.CENTER)

    root.add(welcomeText, 0, 0)
    root.add(welcomeButton, 0, 1)
  }

  override def start(primaryStage: Stage): Unit = {
    primaryStage.setTitle("Entry Screen");
    val gridPane = new GridPane
    val scene = new Scene(gridPane, width, height)
    primaryStage.setScene(scene)
    primaryStage.show()

    drawEntryScreen(gridPane)
  }
}
