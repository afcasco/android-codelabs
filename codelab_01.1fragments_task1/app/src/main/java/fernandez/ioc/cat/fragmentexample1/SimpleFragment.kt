package fernandez.ioc.cat.fragmentexample1

import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import fernandez.ioc.cat.myapplicationfragmentexample1.R
import fernandez.ioc.cat.myapplicationfragmentexample1.databinding.ActivityMainBinding
import fernandez.ioc.cat.myapplicationfragmentexample1.databinding.FragmentSimpleBinding

const val YES = 0
const val NO = 1

class SimpleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_simple, container, false)
        val radioGroup = rootView.findViewById<RadioGroup>(R.id.radio_group)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton: View = radioGroup.findViewById(checkedId)
            val index = radioGroup.indexOfChild(radioButton)
            val textView: TextView = rootView.findViewById(R.id.fragment_header)

            when (index) {
                YES -> textView.text = resources.getString(R.string.yes_message)
                NO -> textView.text = resources.getString(R.string.no_message)
            }
        }

        return rootView
    }

}