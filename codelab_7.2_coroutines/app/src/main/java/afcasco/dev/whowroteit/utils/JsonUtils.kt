package afcasco.dev.whowroteit.utils

import android.widget.TextView
import org.json.JSONException
import org.json.JSONObject


object JsonUtils {

    fun processJsonResponse(
        jsonResponse: String, mTitleText: TextView,
        mAuthorText: TextView
    ) {

        val jsonObject = JSONObject(jsonResponse)
        val itemsArray = jsonObject.getJSONArray("items")

        var i = 0
        var title: String? = null
        var authors: String? = null

        try {
            while (i < itemsArray.length() && (authors == null && title == null)) {
                val book = itemsArray.getJSONObject(i)
                val volumeInfo = book.getJSONObject("volumeInfo")

                title = volumeInfo.getString("title")
                authors = volumeInfo.getString("authors")
                i++
            }

            title?.let { mTitleText.text = title } ?: { "no results" }
            authors?.let { mAuthorText.text = authors } ?: { "" }

            // Capturem possibles excepcions, per exemple, l'API pot retornar un resultat
            // sense el camp 'autors'
        } catch (e: JSONException) {
            mTitleText.text = "No results"
            mAuthorText.text = ""
        }

    }
}