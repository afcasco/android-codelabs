package fernandez.ioc.codelab21

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

val TAG: String? = MainActivity::class.simpleName



class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MESSAGE: String = "fernandez.ioc.codelab21.extra.MESSAGE"
        val LOG_TAG = MainActivity::class.simpleName
    }


    private lateinit var mMessageEditText: EditText
    private lateinit var mReplyHeadTextView: TextView
    private lateinit var mReplyTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(LOG_TAG,"---------------------")
        Log.d(LOG_TAG, "onCreate")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMessageEditText = findViewById(R.id.editText_main)
        mReplyHeadTextView = findViewById(R.id.text_header_reply)
        mReplyTextView = findViewById(R.id.text_message_reply)

        if(savedInstanceState != null){
            val isVisible = savedInstanceState.getBoolean("reply_visible")
            if(isVisible){
                mReplyTextView.visibility = View.VISIBLE
                mReplyHeadTextView.visibility = View.VISIBLE
                mReplyTextView.text = savedInstanceState.getString("reply_text")
            }
        }


        val startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                    result: ActivityResult ->
                if(result.resultCode == Activity.RESULT_OK) {
                    val intent = result.data
                    val reply = intent?.getStringExtra(SecondActivity.EXTRA_REPLY)
                    if(reply.toString().isNotEmpty()){
                        mReplyHeadTextView.visibility = View.VISIBLE
                        mReplyTextView.visibility = View.VISIBLE
                        mReplyTextView.text = reply
                        mMessageEditText.setText("")
                    } else {
                        mReplyHeadTextView.visibility = View.INVISIBLE
                        mReplyTextView.visibility = View.INVISIBLE
                        mMessageEditText.setText("")
                    }
                }
            }

        val buttonMain = findViewById<Button>(R.id.button_main)
        buttonMain.setOnClickListener {
            launchSecondActivity(startForResult)
        }
    }


    private fun launchSecondActivity(startForResult: ActivityResultLauncher<Intent>) {
        Log.d(TAG, "Button clicked!")

        val message = mMessageEditText.text.toString()
        val intent = Intent(this, SecondActivity::class.java)

        intent.putExtra(EXTRA_MESSAGE, message)
        startForResult.launch(intent)

    }

    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG,"onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG,"onPause")

    }

    override fun onRestart() {
        super.onRestart()
        Log.d(LOG_TAG,"onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG,"onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG,"onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG,"onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(mReplyHeadTextView.visibility == View.VISIBLE){
            outState.putBoolean("reply_visible",true)
            outState.putString("reply_text",mReplyTextView.text.toString())
        }
    }
}

