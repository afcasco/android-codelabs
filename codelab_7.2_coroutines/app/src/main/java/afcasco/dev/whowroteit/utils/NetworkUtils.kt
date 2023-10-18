package afcasco.dev.whowroteit.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import java.net.HttpURLConnection
import java.net.URL

const val BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes?"
const val QUERY_PARAM = "q"
const val MAX_RESULTS = "maxResults"
const val PRINT_TYHPE = "printType"

class NetworkUtils {

    companion object {

        // Fetch API response for String queryString
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
            val result = inputStream?.bufferedReader().use { it?.readText() }
            if (result?.length == 0) {
                return null
            }

            bookJSONString = result.toString()
            return bookJSONString

        }

        // Return state of network availability
        @RequiresApi(Build.VERSION_CODES.M)
        fun isInternetAvailable(context: Context) =
            (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
                getNetworkCapabilities(activeNetwork)?.run {
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) or
                            hasTransport(NetworkCapabilities.TRANSPORT_WIFI) or
                            hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                } ?: false
            }
    }
}