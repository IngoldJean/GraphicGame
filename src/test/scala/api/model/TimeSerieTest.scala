package api.model

import io.circe.parser
import org.scalatest.FunSuite

class TimeSerieTest extends FunSuite{

  test("decodeTimeserie") {
    val data = """{
                 |            "1. open": "141.3100",
                 |            "2. high": "143.6100",
                 |            "3. low": "140.9500",
                 |            "4. close": "142.4300",
                 |            "5. volume": "4554472"
                 |        }""".stripMargin

    assert(parser.decode[TimeSerie](data).right.get ===  TimeSerie("141.3100".toDouble, "143.6100".toDouble, "140.9500".toDouble, "142.4300".toDouble, "4554472".toInt))
  }


  test("fullTest") {
    val data = """{
                 |        "2021-04-23": {
                 |            "1. open": "141.3100",
                 |            "2. high": "143.6100",
                 |            "3. low": "140.9500",
                 |            "4. close": "142.4300",
                 |            "5. volume": "4554472"
                 |        },
                 |        "2021-04-22": {
                 |            "1. open": "143.7000",
                 |            "2. high": "144.7400",
                 |            "3. low": "141.0000",
                 |            "4. close": "141.2800",
                 |            "5. volume": "7101368"
                 |        },
                 |        "2021-04-21": {
                 |            "1. open": "138.0600",
                 |            "2. high": "143.7300",
                 |            "3. low": "137.7100",
                 |            "4. close": "143.5500",
                 |            "5. volume": "11909005"
                 |        },
                 |        "2021-04-20": {
                 |            "1. open": "137.0700",
                 |            "2. high": "139.7700",
                 |            "3. low": "136.7000",
                 |            "4. close": "138.1600",
                 |            "5. volume": "15480579"
                 |        },
                 |        "2021-04-19": {
                 |            "1. open": "133.6000",
                 |            "2. high": "133.8150",
                 |            "3. low": "132.5800",
                 |            "4. close": "133.1200",
                 |            "5. volume": "8198582"
                 |        },
                 |        "2021-04-16": {
                 |            "1. open": "133.0000",
                 |            "2. high": "134.1000",
                 |            "3. low": "132.9500",
                 |            "4. close": "133.5900",
                 |            "5. volume": "5291756"
                 |        },
                 |        "2021-04-15": {
                 |            "1. open": "133.2800",
                 |            "2. high": "133.8700",
                 |            "3. low": "132.2200",
                 |            "4. close": "132.5800",
                 |            "5. volume": "3883955"
                 |        },
                 |        "2021-04-14": {
                 |            "1. open": "131.3050",
                 |            "2. high": "132.7800",
                 |            "3. low": "130.5200",
                 |            "4. close": "132.6300",
                 |            "5. volume": "5868049"
                 |        },
                 |        "2021-04-13": {
                 |            "1. open": "133.0000",
                 |            "2. high": "133.6200",
                 |            "3. low": "130.3800",
                 |            "4. close": "131.1800",
                 |            "5. volume": "8033530"
                 |        },
                 |        "2021-04-12": {
                 |            "1. open": "135.0200",
                 |            "2. high": "135.3700",
                 |            "3. low": "133.8500",
                 |            "4. close": "134.5900",
                 |            "5. volume": "3753959"
                 |        }}""".stripMargin

    assert(parser.decode[TimeSeriesDaily](data).right.get ===  TimeSeriesDaily(TimeSerie("141.3100".toDouble, "143.6100".toDouble, "140.9500".toDouble, "142.4300".toDouble, "4554472".toInt)))
  }


  test("FinalTes") {
    val data = """{
                 |    "Meta Data": {
                 |        "1. Information": "Daily Prices (open, high, low, close) and Volumes",
                 |        "2. Symbol": "IBM",
                 |        "3. Last Refreshed": "2021-04-23 16:00:01",
                 |        "4. Output Size": "Compact",
                 |        "5. Time Zone": "US/Eastern"
                 |    },
                 |    "Time Series (Daily)": {
                 |        "2021-04-23": {
                 |            "1. open": "141.3100",
                 |            "2. high": "143.6100",
                 |            "3. low": "140.9500",
                 |            "4. close": "142.4300",
                 |            "5. volume": "4554472"
                 |        },
                 |        "2021-04-22": {
                 |            "1. open": "143.7000",
                 |            "2. high": "144.7400",
                 |            "3. low": "141.0000",
                 |            "4. close": "141.2800",
                 |            "5. volume": "7101368"
                 |        },
                 |        "2021-04-21": {
                 |            "1. open": "138.0600",
                 |            "2. high": "143.7300",
                 |            "3. low": "137.7100",
                 |            "4. close": "143.5500",
                 |            "5. volume": "11909005"
                 |        }
                 |    }
                 |}""".stripMargin

    assert(parser.decode[RootInterface](data).right.get ===  RootInterface(TimeSeriesDaily(TimeSerie("141.3100".toDouble, "143.6100".toDouble, "140.9500".toDouble, "142.4300".toDouble, "4554472".toInt))))
  }
}
