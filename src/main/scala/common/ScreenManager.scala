package common

import javafx.scene.layout.Pane

object ScreenManager {

  def removeAllChildren(pane: Pane): Unit = {
    pane.getChildren.clear()
  }

}
