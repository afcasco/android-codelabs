package fernandez.ioc.codelab23

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.app.ShareCompat
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val uriButton = findViewById<Button>(R.id.open_website_button)
        val locButton = findViewById<Button>(R.id.open_loc_button)
        val shareButton = findViewById<Button>(R.id.share_text_button)

        val uriText = findViewById<EditText>(R.id.website_edittext)
        val locText = findViewById<EditText>(R.id.edittext_loc)
        val shareText = findViewById<EditText>(R.id.edittext_share)

        uriButton.setOnClickListener() {
            val webUrl = Uri.parse(uriText.toString())
            startActivity(Intent(Intent.ACTION_VIEW,webUrl))
        }

        locButton.setOnClickListener {
            val location = locText.text
            val addressUri = Uri.parse("geo:0,0?q=$location")
            startActivity(Intent(Intent.ACTION_VIEW, addressUri))

        }

        shareButton.setOnClickListener {
            val text = shareText.text
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with:")
                .setText(text)
                .startChooser()

        }

    }

}