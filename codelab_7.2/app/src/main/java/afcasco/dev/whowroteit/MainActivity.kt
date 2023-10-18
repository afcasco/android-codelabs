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

class MainActivity : AppCompatActivity() {

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
            InputMethodManager.HIDE_NOT_ALWAYS)

        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager?

        val networkInfo = connMgr?.activeNetworkInfo

        if(networkInfo!= null && networkInfo.isConnected && queryString.isNotEmpty()) {
            FetchBook(mTitleText, mAuthorText).execute(queryString)

            mAuthorText.text = ""
            mTitleText.text = getString(R.string.loading_result)
        } else {
            mAuthorText.text = ""
            if(queryString.isEmpty()){
                mTitleText.text = "No search term"
            } else {
               mTitleText.text = "No network"
            }
        }


    }







}