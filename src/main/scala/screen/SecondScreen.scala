package screen

import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.text.{Font, FontPosture, FontWeight, Text}

class SecondScreen {

  def drawSecondScreen(root:GridPane): Unit = {
    val secondText = new Text("Hey that is the second plan")

    secondText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 20))
    secondText.setFill(Color.ALICEBLUE)

    root.add(secondText, 0, 0)
  }

}
