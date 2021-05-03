package api.symbol

import common.ApiKey
import scalaj.http.Http

class SearchSymbol {

  def searchForASymbol(keyword: String): String = {
      Http("https://www.alphavantage.co/query")
        .param("function", "SYMBOL_SEARCH")
        .param("keywords", keyword)
        .param("apikey", ApiKey.readApiKeyFromFile).asString.body
    }

}
