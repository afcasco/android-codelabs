package afcasco.dev.materialme

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SportsAdapter(private val context: Context, private val mSportsData: List<Sport>) :
    RecyclerView.Adapter<SportsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mSportsData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentSport = mSportsData[position]
        holder.bindTo(currentSport)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        private val mTitleText: TextView = itemView.findViewById(R.id.title)
        private val mInfoText: TextView = itemView.findViewById(R.id.subTitle)
        private val mSportsImage: ImageView = itemView.findViewById(R.id.sportsImage)

        init {
            itemView.setOnClickListener(this)
        }

        fun bindTo(currentSport: Sport) {
            mTitleText.text = currentSport.title
            mInfoText.text = currentSport.info
            Glide.with(context).load(currentSport.imageResource).into(mSportsImage)
        }

        override fun onClick(v: View?) {
            val currentSport = mSportsData[adapterPosition]
            val sportIntent = Intent(context,DetailActivity::class.java)
            sportIntent.putExtra("title",currentSport.title)
            sportIntent.putExtra("image_resource",currentSport.imageResource)
            context.startActivity(sportIntent)
        }
    }
}