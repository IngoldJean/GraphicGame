import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.scene.shape.Line
import javafx.stage.Stage


class TestRosace extends javafx.application.Application {
  val cote = 600

  def dessineRosace(nbPoints:Int, root:Pane) {
    var l1,l2,l3,l4:Line= null
    var marge = cote/nbPoints
    for (i<-0 to cote by marge)
    {
      l1 = new Line(i,0 , cote,i)
      l2 = new Line(cote,i, cote-i,cote )
      l3 = new Line(cote-i,cote , 0,cote-i)
      l4 = new Line(0,cote-i, i,0)
      root.getChildren.addAll(l1,l2, l3, l4);
    }
  }

  override def start(primaryStage: Stage): Unit = {
    primaryStage.setTitle("Rosace");
    val root = new Pane()
    primaryStage.setScene(new Scene(root, cote, cote))
    primaryStage.show()
    //ici on appelle la fonction qui dessine sur le 'root'
    dessineRosace(100, root)
  }
}

