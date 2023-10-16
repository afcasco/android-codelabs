package afcasco.dev.reciclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.util.LinkedList

class WordListAdapter(val mWordList: LinkedList<String>) :
    RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {


    inner class WordViewHolder(view: View, adapter: WordListAdapter) : ViewHolder(view),
        View.OnClickListener {
        var wordItemView: TextView
        private var mAdapter: WordListAdapter


        init {
            wordItemView = view.findViewById(R.id.word)
            mAdapter = adapter
            itemView.setOnClickListener(this)


        }

        override fun onClick(v: View?) {
            val mPosition = layoutPosition
            val element = mWordList[mPosition]
            mWordList[mPosition] = "Clicked! $element"
            mAdapter.notifyDataSetChanged()


        }


    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): WordViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.wordlist_item, viewGroup, false)
        return WordViewHolder(view, this)
    }


    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.wordItemView.text = mWordList[position]
    }

    override fun getItemCount(): Int {
        return mWordList.size
    }
}