package fernandez.ioc.cat.songdetail_start

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import fernandez.ioc.cat.songdetail_start.content.SongUtils

class MainActivity : AppCompatActivity() {

    private var mTwoPane = false

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title


        val recyclerView: RecyclerView = findViewById(R.id.song_list)
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(SongUtils.SONG_ITEMS)

        findViewById<FrameLayout>(R.id.song_detail_container)?.let { mTwoPane = true }
    }

    private inner class SimpleItemRecyclerViewAdapter(private val mValues: List<SongUtils.Song>) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {
        private inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var mView: View = itemView
            var mIdView: TextView = itemView.findViewById(R.id.id)
            var mContentView: TextView = itemView.findViewById(R.id.content)
            lateinit var mItem: SongUtils.Song
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.song_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.mItem = mValues[position]
            holder.mIdView.text = getString(R.string.song_list_position, position + 1)
            holder.mContentView.text = mValues[position].songTitle
            holder.mView.setOnClickListener {
                if (mTwoPane) {
                    val selectedSong = holder.adapterPosition
                    val fragment = SongDetailFragment.newInstance(selectedSong)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.song_detail_container, fragment)
                        .addToBackStack(null)
                        .commit()

                } else {
                    val intent = Intent(applicationContext, SongDetailActivity::class.java)
                    intent.putExtra(SongUtils.SONG_ID_KEY, holder.adapterPosition)
                    startActivity(intent)

                }
            }
        }

        override fun getItemCount(): Int {
            return mValues.size
        }
    }
}


