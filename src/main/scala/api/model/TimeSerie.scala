package api.model

import io.circe.Decoder.Result
import io.circe.{Decoder, HCursor}
import cats.syntax.either._




case class RootInterface(datas: TimeSeriesDaily)

case class TimeSeriesDaily (todayValue : TimeSerie)

case class TimeSerie(open: Double, high: Double, low: Double, close: Double, volume: Int)

object RootInterface {
  implicit val decoder: Decoder[RootInterface] = new Decoder[RootInterface] {
    override def apply(hCursor: HCursor): Result[RootInterface] =
      for {
        timeseries <- hCursor.get[TimeSeriesDaily]("Time Series (Daily)")
      } yield {
        RootInterface(timeseries)
      }
  }
}

object TimeSeriesDaily{
  implicit val decoder: Decoder[TimeSeriesDaily] = new Decoder[TimeSeriesDaily] {
    override def apply(hCursor: HCursor): Result[TimeSeriesDaily] =
      for {
        todayValue <- hCursor.get[TimeSerie]("2021-04-23")
      } yield {
        TimeSeriesDaily(todayValue)
    }
  }
}


object TimeSerie {

  implicit val decoder: Decoder[TimeSerie] = new Decoder[TimeSerie] {
    override def apply(hCursor: HCursor): Result[TimeSerie] =
      for {
        open <- hCursor.get[String]("1. open")
        high <- hCursor.get[String]("2. high")
        low <- hCursor.get[String]("3. low")
        close <- hCursor.get[String]("4. close")
        volume <- hCursor.get[String]("5. volume")
      } yield TimeSerie(open.toDouble, high.toDouble, low.toDouble, close.toDouble, volume.toInt)
  }
}
