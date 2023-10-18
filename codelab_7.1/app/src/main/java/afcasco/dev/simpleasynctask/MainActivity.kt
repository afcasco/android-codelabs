package afcasco.dev.simpleasynctask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

// Tried mixing kotlin and java (SimpleAsyncTask is written in java)
class MainActivity : AppCompatActivity() {

    companion object {
        const val TEXT_STATE: String = "currentText"
    }

    private lateinit var mTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTextView = findViewById(R.id.textView1)

        if (savedInstanceState != null) {
            mTextView.text = savedInstanceState.getString(TEXT_STATE)
        }
    }

    fun startTask(view: View) {
        mTextView.text = getString(R.string.napping)
        SimpleAsyncTask(mTextView).execute()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(TEXT_STATE, mTextView.text.toString())
    }
}