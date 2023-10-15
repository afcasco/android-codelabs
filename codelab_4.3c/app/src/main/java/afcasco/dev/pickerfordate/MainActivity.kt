package afcasco.dev.pickerfordate

import afcasco.dev.pickerfordate.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {

            showDatePicker()
        }

    }

    private fun showDatePicker() {
        val fragment = DatePickerFragment()
        fragment.show(supportFragmentManager, getString(R.string.datepicker))
    }

    fun processDatePickerResult(year: Int,month: Int, day: Int) {
        val monthString = month.toString()
        val dayString = day.toString()
        val yearString = year.toString()
        val dateMessage = "Date: $dayString/$monthString/$yearString"
        Toast.makeText(this,dateMessage,Toast.LENGTH_SHORT).show()
    }

}