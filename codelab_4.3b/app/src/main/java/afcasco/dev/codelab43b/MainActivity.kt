package afcasco.dev.codelab43b

import afcasco.dev.codelab43b.databinding.ActivityMainBinding
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.alertButton.setOnClickListener {
            onClickShowAlert()
        }

    }

    private fun onClickShowAlert() {
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle("Alert")
        alertBuilder.setMessage("Click OK to continue, or Cancel to stop:")
        alertBuilder.setPositiveButton("OK"){ _, _ ->
            Toast.makeText(applicationContext,"Pressed OK",Toast.LENGTH_LONG).show()
        }
        alertBuilder.setNegativeButton("Cancel") {_,_ ->
            Toast.makeText(applicationContext,"Pressed Cancel",Toast.LENGTH_SHORT).show()
        }
        alertBuilder.show()
    }
}