package afcasco.dev.whowroteit

import android.net.Uri
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

private val LOG_TAG = NetworkUtils::class.java.simpleName
private const val BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes?"
private const val QUERY_PARAM = "q"
private const val MAX_RESULTS = "maxResults"
private const val PRINT_TYHPE = "printType"

class NetworkUtils {
    companion object {
        fun getBookInfo(queryString: String): String? {
            val urlConnection: HttpURLConnection?
            val bookJSONString: String?

            val builtUri = Uri.parse(BOOK_BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, queryString)
                .appendQueryParameter(MAX_RESULTS, "10")
                .appendQueryParameter(PRINT_TYHPE, "books")

            val requestUrl = URL(builtUri.toString())

            urlConnection = requestUrl.openConnection() as HttpURLConnection?
            urlConnection?.requestMethod = "GET"
            urlConnection?.connect()

            val inputStream = urlConnection?.inputStream
            val result = inputStream?.bufferedReader().use {it?.readText()  }
            if(result?.length == 0){
                return null
            }

            bookJSONString = result.toString()

            Log.d(LOG_TAG,bookJSONString)

            return bookJSONString

        }

    }
}