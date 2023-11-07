package fernandez.ioc.cat.fragmentexample1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.service.voice.VoiceInteractionSession.ActivityId
import android.widget.Button
import fernandez.ioc.cat.myapplicationfragmentexample1.R
import fernandez.ioc.cat.myapplicationfragmentexample1.databinding.ActivityMainBinding

const val STATE_FRAGMENT = "state_of_fragment"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mButton: Button
    private var isFragmentDisplayed = false

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mButton = findViewById(R.id.button)


        mButton.setOnClickListener {
            if (isFragmentDisplayed) closeFragment() else displayFragment()
        }
        super.onCreate(savedInstanceState)

    }

    private fun displayFragment() {
        val simpleFragment = SimpleFragment.newInstance()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container, simpleFragment).addToBackStack(null)
            .commit()

        mButton.text = resources.getString(R.string.close)
        isFragmentDisplayed = true
    }

    private fun closeFragment() {
        val fragmentManager = supportFragmentManager
        val simpleFragment = fragmentManager.findFragmentById(R.id.fragment_container)
        simpleFragment?.let {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.remove(simpleFragment).commit()
        }

        mButton.text = resources.getString(R.string.open)
        isFragmentDisplayed = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(STATE_FRAGMENT,isFragmentDisplayed)
        super.onSaveInstanceState(outState)

    }
}