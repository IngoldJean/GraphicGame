package api.timeserie

import common.ApiKey
import scalaj.http.Http

object ReadTimeSerie {

  def timeSerieDaily(symbol: String): String = {
      Http("https://www.alphavantage.co/query")
        .param("function", "TIME_SERIES_DAILY")
        .param("symbol", symbol)
        .param("apikey", ApiKey.readApiKeyFromFile).asString.body
  }

}
