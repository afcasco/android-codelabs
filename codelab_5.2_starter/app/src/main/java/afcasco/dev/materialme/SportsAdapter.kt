package afcasco.dev.materialme

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

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


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mTitleText: TextView = itemView.findViewById(R.id.title)
        private val mInfoText: TextView = itemView.findViewById(R.id.subTitle)


        fun bindTo(currentSport: Sport) {
            mTitleText.text = currentSport.title
            mInfoText.text = currentSport.info
        }
    }
}