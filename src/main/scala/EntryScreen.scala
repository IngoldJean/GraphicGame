import javafx.geometry.VPos
import javafx.scene.{Group, Scene}
import javafx.scene.layout.{Pane, VBox}
import javafx.scene.shape.Line
import javafx.scene.text.{Font, FontPosture, FontWeight, Text, TextAlignment}
import javafx.stage.Stage

class EntryScreen extends javafx.application.Application{

  val width = 500
  val height  = 300

  def drawEntryScreen(root:Group): Unit = {

    val welcomeText = new Text("Hello World")

    welcomeText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 20))
    welcomeText.setX(250)
    welcomeText.setY(150)
    root.getChildren.add(welcomeText)
  }

  override def start(primaryStage: Stage): Unit = {
    primaryStage.setTitle("Entry Screen");
    val root = new Group()
    primaryStage.setScene(new Scene(root, width, height))
    primaryStage.show()
    //ici on appelle la fonction qui dessine sur le 'root'
    drawEntryScreen(root)
  }
}
