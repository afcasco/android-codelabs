package afcasco.dev.whowroteit

import afcasco.dev.whowroteit.databinding.ActivityMainBinding
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import kotlinx.coroutines.delay
import org.json.JSONObject

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<String> {

    private lateinit var binding: ActivityMainBinding
    private lateinit var button: Button
    private lateinit var mBookInput: EditText
    private lateinit var mTitleText: TextView
    private lateinit var mAuthorText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        mBookInput = binding.bookInput
        mTitleText = binding.titleText
        mAuthorText = binding.authorText
        button = binding.searchButton

        if (supportLoaderManager.getLoader<String>(0) != null){
            supportLoaderManager.initLoader(0,null,this)
        }

            button.setOnClickListener {

                searchBooks()
            }

        setContentView(binding.root)
    }


    private fun searchBooks() {

        val queryString = mBookInput.text.toString()
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager

        inputManager.hideSoftInputFromWindow(
            currentFocus?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )

        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager?

        val networkInfo = connMgr?.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected && queryString.isNotEmpty()) {
            val queryBundle = Bundle()
            queryBundle.putString("queryString", queryString)
            supportLoaderManager.restartLoader(0, queryBundle, this)

            mAuthorText.text = ""
            mTitleText.text = getString(R.string.loading_result)
        } else {
            mAuthorText.text = ""
            if (queryString.isEmpty()) {
                mTitleText.text = "No search term"
            } else {
                mTitleText.text = "No network"
            }
        }


    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<String> {
        val queryString = args?.getString("queryString")
        return BookLoader(this, queryString)
    }

    override fun onLoaderReset(loader: Loader<String>) {

    }

    override fun onLoadFinished(loader: Loader<String>, data: String?) {
        try {

            val jsonObject = JSONObject(data!!)
            val itemsArray = jsonObject.getJSONArray("items")

            var i = 0
            var title: String? = null
            var authors: String? = null

            while (i < itemsArray.length() && authors == null && title == null) {
                val book = itemsArray.getJSONObject(i)
                val volumeInfo = book.getJSONObject("volumeInfo")

                try {
                    title = volumeInfo.getString("title")
                    authors = volumeInfo.getString("authors")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                i++
            }

            if (title != null && authors != null) {
                mTitleText.text = title
                mAuthorText.text = authors
            } else {
                mTitleText.text = "no results"
                mAuthorText.text = ""
            }
        } catch (e: Exception) {
            mTitleText.text = "no results"
            mAuthorText.text = ""
        }
    }


}