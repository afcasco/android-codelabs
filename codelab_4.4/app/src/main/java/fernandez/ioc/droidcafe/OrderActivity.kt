package fernandez.ioc.droidcafe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

class OrderActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)



        val message = "Order: " + intent.getStringExtra(MainActivity.EXTRA_MESSAGE)
        val textView = findViewById<TextView>(R.id.order_textView)
        textView.text = message

        val spinner = findViewById<Spinner>(R.id.label_spinner)
        if (spinner != null) {
            spinner.onItemSelectedListener = this
        }

        val adapter = ArrayAdapter.createFromResource(
            this, R.array.labels_array,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        if (spinner != null) {
            spinner.adapter = adapter
        }

    }

    fun onRadioButtonClicked(view: View) {
        val radioButton = view as RadioButton
        val checked = radioButton.isChecked
        when (view.id) {
            R.id.sameday -> if (checked) displayToast(getString(R.string.same_day_messenger_service))
            R.id.nextday -> if (checked) displayToast(getString(R.string.next_day_ground_delivery))
            R.id.pickup -> if (checked) displayToast(getString(R.string.pick_up))
        }
    }

    private fun displayToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val spinnerLabel = parent?.getItemAtPosition(position).toString()
        displayToast(spinnerLabel)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        // TODO("Not yet implemented")
    }
}