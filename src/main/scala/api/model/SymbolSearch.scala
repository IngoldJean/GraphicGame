package api.model

import io.circe.Decoder.Result
import io.circe.{Decoder, HCursor}
import cats.syntax.either._


case class SymbolSearch ( bestMatches: Seq[BestMatches] )

case class BestMatches (symbol: String, name: String, typeElement: String, region: String, marketOpen: String,
                        marketClose: String, timezone: String, currency: String, matchScore: String)


object SymbolSearch {
  implicit val decoder: Decoder[SymbolSearch] = new Decoder[SymbolSearch] {
    override def apply(hCursor: HCursor): Result[SymbolSearch] =
      for {
        bestMatches <- hCursor.downField("bestMatches").as[List[BestMatches]]
      } yield {
        SymbolSearch(bestMatches)
      }
  }
}

object BestMatches {
  implicit val decoder: Decoder[BestMatches] = new Decoder[BestMatches] {
    override def apply(hCursor: HCursor): Result[BestMatches] =
      for {
        symbol <- hCursor.get[String]("1. symbol")
        name <- hCursor.get[String]("2. name")
        typeElement <- hCursor.get[String]("3. type")
        region <- hCursor.get[String]("4. region")
        marketOpen <- hCursor.get[String]("5. marketOpen")
        marketClose <- hCursor.get[String]("6. marketClose")
        timezone <- hCursor.get[String]("7. timezone")
        currency <- hCursor.get[String]("8. currency")
        matchScore <- hCursor.get[String]("9. matchScore")
      } yield BestMatches(symbol, name, typeElement, region, marketOpen, marketClose, timezone, currency, matchScore)
  }
}
