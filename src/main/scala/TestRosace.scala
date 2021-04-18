
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.scene.shape.Line
import javafx.stage.Stage


class TestRosace extends javafx.application.Application {
  val height  = 600
  val width = 600

  def dessineRosace(nbPoints:Int, root:Pane) {
    var l1,l2,l3,l4:Line= null
    var marge = height/nbPoints
    for (i<-0 to height by marge)
    {
      l1 = new Line(i,0 , height,i)
      l2 = new Line(height,i, height-i,height )
      l3 = new Line(height-i,height , 0,height-i)
      l4 = new Line(0,height-i, i,0)
      root.getChildren.addAll(l1,l2, l3, l4);
    }
  }

  override def start(primaryStage: Stage): Unit = {
    primaryStage.setTitle("Rosace");
    val root = new Pane()
    primaryStage.setScene(new Scene(root, width, height))
    primaryStage.show()
    //ici on appelle la fonction qui dessine sur le 'root'
    dessineRosace(100, root)
  }
}

