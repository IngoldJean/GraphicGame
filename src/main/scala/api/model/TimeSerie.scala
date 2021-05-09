package api.model

import cats.syntax.either._
import common.DateManager
import io.circe.Decoder.Result
import io.circe.{Decoder, HCursor}

import java.time.LocalDate
import java.time.format.DateTimeFormatter

case class FullTimeSerieJson(datas: TimeSeriesDaily)

case class TimeSeriesDaily (todayValue : TimeSerie)

case class TimeSerie(open: Double, high: Double, low: Double, close: Double, volume: Int)

object FullTimeSerieJson {
  implicit val decoder: Decoder[FullTimeSerieJson] = new Decoder[FullTimeSerieJson] {
    override def apply(hCursor: HCursor): Result[FullTimeSerieJson] =
      for {
        timeseries <- hCursor.get[TimeSeriesDaily]("Time Series (Daily)")
      } yield {
        FullTimeSerieJson(timeseries)
      }
  }
}

object TimeSeriesDaily{
  implicit val decoder: Decoder[TimeSeriesDaily] = new Decoder[TimeSeriesDaily] {
    override def apply(hCursor: HCursor): Result[TimeSeriesDaily] = {

      val previousWorkingDay = DateManager.getPreviousWorkingDay(LocalDate.now())
      val dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd")
      for {
        todayValue <- hCursor.get[TimeSerie](dtf.format(previousWorkingDay))
      } yield {
        TimeSeriesDaily(todayValue)
      }
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
