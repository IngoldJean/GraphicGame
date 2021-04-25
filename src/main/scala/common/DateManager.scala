package common

import java.time.format.DateTimeFormatter
import java.time.{DayOfWeek, LocalDate, LocalDateTime}
import scala.annotation.tailrec

object DateManager {

  val WEEKDAYS_NUMBER: Seq[DayOfWeek] = Seq.apply(DayOfWeek.MONDAY, DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY)

  def getTheDateString: String = {
    val dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
    dtf.format(LocalDateTime.now())
  }

  @tailrec
  def getPreviousWorkingDay(nowLocalDate: LocalDate): LocalDate  = {
    val yesterday = nowLocalDate.minusDays(1)
    WEEKDAYS_NUMBER.contains(yesterday.getDayOfWeek) match {
      case true => yesterday
      case false => getPreviousWorkingDay(yesterday)
    }
  }

}
