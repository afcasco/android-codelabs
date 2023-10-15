package fernandez.ioc.codelab21

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_REPLY: String = "fernandez.ioc.codelab21.extra.REPLY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val textView = findViewById<TextView>(R.id.text_message)
        val replyButton = findViewById<Button>(R.id.button_second)

        val message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)
        textView.text = message

        replyButton.setOnClickListener {
            returnReply()
        }
    }

    private fun returnReply() {
        val mReply: EditText = findViewById(R.id.editText_reply)
        val reply = mReply.text.toString()
        val replyIntent = Intent()
        replyIntent.putExtra(EXTRA_REPLY, reply)
        setResult(RESULT_OK,replyIntent)
        finish()


    }
}