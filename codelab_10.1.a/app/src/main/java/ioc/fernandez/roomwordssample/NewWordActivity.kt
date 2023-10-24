package ioc.fernandez.roomwordssample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewWordActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_REPLY = "ioc.fernandez.roomwordssample.REPLY"
    }

    private lateinit var mEditWordView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        mEditWordView = findViewById(R.id.edit_word)

        findViewById<Button>(R.id.button_save).setOnClickListener {
            val replyIntent = Intent()

            if (TextUtils.isEmpty(mEditWordView.text)) {
                setResult(RESULT_CANCELED, replyIntent)
            } else {
                val word = mEditWordView.text
                replyIntent.putExtra(EXTRA_REPLY, word.toString())
                setResult(RESULT_OK, replyIntent)
            }

            finish()
        }
    }
}