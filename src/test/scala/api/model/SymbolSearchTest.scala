package api.model

import api.symbol.SearchSymbol
import io.circe.parser
import org.scalatest.FunSuite

class SymbolSearchTest extends FunSuite{

  test("decodeSymbolSearch") {
    val data = """{
                 |    "bestMatches": [
                 |        {
                 |            "1. symbol": "TESO",
                 |            "2. name": "Tesco Corporation USA",
                 |            "3. type": "Equity",
                 |            "4. region": "United States",
                 |            "5. marketOpen": "09:30",
                 |            "6. marketClose": "16:00",
                 |            "7. timezone": "UTC-04",
                 |            "8. currency": "USD",
                 |            "9. matchScore": "0.8889"
                 |        },
                 |        {
                 |            "1. symbol": "TSCO.LON",
                 |            "2. name": "Tesco PLC",
                 |            "3. type": "Equity",
                 |            "4. region": "United Kingdom",
                 |            "5. marketOpen": "08:00",
                 |            "6. marketClose": "16:30",
                 |            "7. timezone": "UTC+01",
                 |            "8. currency": "GBX",
                 |            "9. matchScore": "0.7273"
                 |        },
                 |        {
                 |            "1. symbol": "TSCDF",
                 |            "2. name": "Tesco plc",
                 |            "3. type": "Equity",
                 |            "4. region": "United States",
                 |            "5. marketOpen": "09:30",
                 |            "6. marketClose": "16:00",
                 |            "7. timezone": "UTC-04",
                 |            "8. currency": "USD",
                 |            "9. matchScore": "0.7143"
                 |        },
                 |        {
                 |            "1. symbol": "TSCDY",
                 |            "2. name": "Tesco plc",
                 |            "3. type": "Equity",
                 |            "4. region": "United States",
                 |            "5. marketOpen": "09:30",
                 |            "6. marketClose": "16:00",
                 |            "7. timezone": "UTC-04",
                 |            "8. currency": "USD",
                 |            "9. matchScore": "0.7143"
                 |        },
                 |        {
                 |            "1. symbol": "TCO.DEX",
                 |            "2. name": "Tesco PLC",
                 |            "3. type": "Equity",
                 |            "4. region": "XETRA",
                 |            "5. marketOpen": "08:00",
                 |            "6. marketClose": "20:00",
                 |            "7. timezone": "UTC+02",
                 |            "8. currency": "EUR",
                 |            "9. matchScore": "0.7143"
                 |        },
                 |        {
                 |            "1. symbol": "TCO.FRK",
                 |            "2. name": "Tesco PLC",
                 |            "3. type": "Equity",
                 |            "4. region": "Frankfurt",
                 |            "5. marketOpen": "08:00",
                 |            "6. marketClose": "20:00",
                 |            "7. timezone": "UTC+02",
                 |            "8. currency": "EUR",
                 |            "9. matchScore": "0.7143"
                 |        }
                 |    ]
                 |}""".stripMargin

    val symbol = parser.decode[SymbolSearch](data).right.get

    assert(symbol.bestMatches.length === 6)

    assert(symbol.bestMatches.head === BestMatches("TESO", "Tesco Corporation USA", "Equity", "United States", "09:30", "16:00", "UTC-04", "USD", "0.8889"))
  }

  test("full research process test") {
    val searchSymbol = new SearchSymbol
    val rawJson = searchSymbol.searchForASymbol("danone")

    val outputBestMatches = parser.decode[SymbolSearch](rawJson).right.get

    assert(outputBestMatches.bestMatches.head === BestMatches("DANOY","Danone","Equity","United States","09:30","16:00","UTC-04","USD","1.0000"))
  }

}
