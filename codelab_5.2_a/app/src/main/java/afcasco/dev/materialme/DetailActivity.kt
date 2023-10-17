package afcasco.dev.materialme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val sportsTitle: TextView = findViewById(R.id.title_detail)
        val sportsImage: ImageView = findViewById(R.id.sportsImage_detail)

        sportsTitle.text = intent.getStringExtra("title")
        Glide.with(this)
            .load(intent.getIntExtra("image_resource", 0))
            .into(sportsImage)


    }
}