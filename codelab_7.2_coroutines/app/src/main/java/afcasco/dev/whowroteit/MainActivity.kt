package afcasco.dev.whowroteit

import afcasco.dev.whowroteit.databinding.ActivityMainBinding
import afcasco.dev.whowroteit.utils.JsonUtils
import afcasco.dev.whowroteit.utils.NetworkUtils
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var button: Button
    private lateinit var mBookInput: EditText
    private lateinit var mTitleText: TextView
    private lateinit var mAuthorText: TextView


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        mBookInput = binding.bookInput
        mTitleText = binding.titleText
        mAuthorText = binding.authorText
        button = binding.searchButton

        setContentView(binding.root)

        button.setOnClickListener {
            lifecycleScope.launch {
                val jsonResponse = searchBooks().await()
                jsonResponse?.let { println(jsonResponse) }
                jsonResponse?.let { JsonUtils.processJsonResponse(it, mTitleText, mAuthorText) }
            }
        }
    }


    // Usa la api de manera asíncrona a l'scope IO
    @RequiresApi(Build.VERSION_CODES.M)
    private suspend fun searchBooks(): Deferred<String?> = withContext(Dispatchers.IO) {
        async {
            val queryString = mBookInput.text.toString()

            // Amaga el teclat al fer click
            hideKeyboardOnButtonClick()

            var jsonResponse: String? = null

            val internetIsActive = NetworkUtils.isInternetAvailable(applicationContext)

            if (internetIsActive && queryString.isNotEmpty()) {
                // Canviem l'scope per modificar elements de la UI
                withContext(Dispatchers.Main) {
                    mAuthorText.text = ""
                    mTitleText.text = getString(R.string.loading)
                }

                jsonResponse = NetworkUtils.getBookInfo(mBookInput.text.toString())

            } else {
                // Canviem el scope a Main per canviar els elements de la UI
                // I indicar si no hi ha internet o el input estava buit
                withContext(Dispatchers.Main) {
                    mAuthorText.text = ""
                    if (queryString.isEmpty()) {
                        mTitleText.text = getString(R.string.no_search_term)
                    } else {
                        mTitleText.text = getString(R.string.no_network)
                    }
                }
            }

            jsonResponse
        }
    }

    private fun hideKeyboardOnButtonClick() {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        inputMethodManager.hideSoftInputFromWindow(
            currentFocus?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}