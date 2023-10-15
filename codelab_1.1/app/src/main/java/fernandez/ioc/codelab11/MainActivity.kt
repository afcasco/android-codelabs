package fernandez.ioc.codelab11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val text = findViewById<TextView>(R.id.text)

        button.setOnClickListener {
            if(text.text.equals("Happy birthday!")){
                text.text = "Happy birthday retard!"
            } else  {
                text.text = "Happy birthday!"
            }

        }


    }
}

