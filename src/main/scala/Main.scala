import javafx.application.Application
import screen.EntryScreen

object Main {
  def main(args: Array[String]): Unit = {
    Application.launch(classOf[EntryScreen], args: _*)
  }
}
