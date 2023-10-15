package fernandez.ioc.codelab12a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toastButton = findViewById<Button>(R.id.toast)
        toastButton.setOnClickListener {
            showToast(toastButton)
        }



    }

    fun showToast(view: View){
        val toast = Toast.makeText(this,"This is a toast message",Toast.LENGTH_SHORT)
        toast.show()
    }
}