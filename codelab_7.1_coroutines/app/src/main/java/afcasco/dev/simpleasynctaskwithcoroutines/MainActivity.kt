package afcasco.dev.simpleasynctaskwithcoroutines

import afcasco.dev.simpleasynctaskwithcoroutines.databinding.ActivityMainBinding
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


private const val TEXT_STATE: String = "currentText"

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {


    private lateinit var mTextView: TextView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mTextView = binding.textView1

        if (savedInstanceState != null) {
            mTextView.text = savedInstanceState.getString(TEXT_STATE)
        }

        binding.button.setOnClickListener {
            mTextView.text = getString(R.string.napping)
            launch {
                startTask()
            }

        }
    }

    private suspend fun startTask() {
        val delay = 3000L
        val resultString = withContext(Dispatchers.Main) {
            delay(delay)
            "Finally awake after $delay ms"
        }
        mTextView.text = resultString

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(TEXT_STATE, mTextView.text.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}